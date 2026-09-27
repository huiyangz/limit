package com.dcits.limit.step;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dcits.limit.enums.LimitBranchId;
import com.dcits.limit.facade.bo.ST002InputBO;
import com.dcits.limit.facade.bo.ST002OutputBO;
import com.dcits.limit.facade.components.IFmBranchBcc;
import com.dcits.limit.facade.components.IRbBusAcctBcc;
import com.dcits.limit.facade.components.IRbLimitCtrlConfBcc;
import com.dcits.limit.facade.eo.FmBranchEO;
import com.dcits.limit.facade.eo.RbBusAcctEO;
import com.dcits.limit.facade.eo.RbLimitCtrlConfEO;

/**
 * ST002 检查账户机构是否可匹配到限额场景配置。
 *
 * <p>子步骤1 根据账号查询【账户信息】获取账户开立行行号；
 * 子步骤2 按账户开立行行号查询【限额控制配置表】取启用标志等于"Y-启用"的限额场景编码；
 * 子步骤3 限额场景编码为空时跳转至子步骤《获取上级机构集合》，否则返回限额场景编码；
 * 子步骤4 根据账户开立行行号查询【机构信息表】获取上级机构集合；
 * 子步骤5 按上级机构集合中的机构层级从大到小遍历，逐个机构查询启用限额场景配置，
 * 命中即中断遍历返回，未命中继续下一条，遍历结束返回空限额场景编码。
 * 本步骤无业务失败场景，未匹配到启用配置按成功返回。</p>
 */
@Service
public class ST002Pbc implements IST002 {

	/** 启用标志取值：Y-启用 */
	private static final String VALID_FLAG_ENABLED = "Y";

	private final IRbBusAcctBcc rbBusAcctBcc;

	private final IRbLimitCtrlConfBcc rbLimitCtrlConfBcc;

	private final IFmBranchBcc fmBranchBcc;

	public ST002Pbc(IRbBusAcctBcc rbBusAcctBcc, IRbLimitCtrlConfBcc rbLimitCtrlConfBcc, IFmBranchBcc fmBranchBcc) {
		this.rbBusAcctBcc = rbBusAcctBcc;
		this.rbLimitCtrlConfBcc = rbLimitCtrlConfBcc;
		this.fmBranchBcc = fmBranchBcc;
	}

	@Override
	public ST002OutputBO execute(ST002InputBO input) {
		ST002OutputBO output = new ST002OutputBO();
		output.setBaseAcctNo(input.getBaseAcctNo());
		// 子步骤1 获取账户开立行行号：根据{账号}查询【账户信息】
		LimitBranchId acctBranch = queryAcctBranch(input.getBaseAcctNo());
		output.setAcctBranch(acctBranch);
		// 子步骤2 获取限额场景编码：根据[账户开立行行号]查询【限额控制配置表】取启用标志等于"Y-启用"的配置
		RbLimitCtrlConfEO matched = acctBranch == null ? null : findEnabledConfig(acctBranch);
		if (matched == null) {
			// 子步骤3 判断是否继续匹配上级机构：限额场景编码为空，跳转至子步骤《获取上级机构集合》，
			// 子步骤3 的直接返回分支与子步骤4 未触达的机构信息输出在下方统一处理
			// 子步骤4 获取上级机构集合：根据[账户开立行行号]查询【机构信息表】取账户开立行自身机构记录，
			// 并沿归属上级机构号逐级上溯得到上级机构集合
			FmBranchEO openingBranch = acctBranch == null ? null : fmBranchBcc.findByBranch(acctBranch);
			if (openingBranch != null) {
				output.setBranch(openingBranch.getBranch());
				output.setAttachedTo(openingBranch.getAttachedTo());
			}
			List<FmBranchEO> superiors = collectSuperiors(openingBranch);
			// 子步骤5 检查上级机构匹配的限额场景：按[上级机构集合]中的机构层级从大到小遍历
			matched = matchSuperiorConfig(superiors);
		}
		// 子步骤3 否则返回[限额场景编码] / 子步骤5.3 遍历结束返回空限额场景编码
		if (matched != null) {
			output.setLimitSceneNo(matched.getLimitSceneNo());
			output.setLimitBranchId(matched.getLimitBranchId());
			output.setLimitBranchRange(matched.getLimitBranchRange());
			output.setValidFlag(matched.getValidFlag());
		}
		// 本步骤无业务失败场景，正常完成（含未匹配到启用配置）按成功返回
		output.setSucceed(true);
		return output;
	}

	/**
	 * 子步骤1 获取账户开立行行号：根据{账号}查询【账户信息 RB_BUS_ACCT】，取账户开立行行号。
	 */
	private LimitBranchId queryAcctBranch(String baseAcctNo) {
		RbBusAcctEO condition = new RbBusAcctEO();
		condition.setBaseAcctNo(baseAcctNo);
		List<RbBusAcctEO> accounts = rbBusAcctBcc.findByEo(condition);
		return accounts.isEmpty() ? null : accounts.get(0).getAcctBranch();
	}

	/**
	 * 子步骤2 / 子步骤5.1 按[机构号]查询【限额控制配置 RB_LIMIT_CTRL_CONF】，
	 * 取启用标志等于"Y-启用"的限额场景配置记录。
	 */
	private RbLimitCtrlConfEO findEnabledConfig(LimitBranchId branchId) {
		RbLimitCtrlConfEO condition = new RbLimitCtrlConfEO();
		condition.setLimitBranchId(branchId);
		condition.setValidFlag(VALID_FLAG_ENABLED);
		List<RbLimitCtrlConfEO> configs = rbLimitCtrlConfBcc.findByEo(condition);
		return configs.isEmpty() ? null : configs.get(0);
	}

	/**
	 * 子步骤4 获取上级机构集合：从账户开立行自身机构记录起，按归属上级机构号逐级查询
	 * 【机构信息表 FM_BRANCH】，直至记录无归属上级机构号；结果按机构层级从大到小
	 * （支行→分行→总行，就近上级优先）排序，即遍历用的上级机构集合。
	 */
	private List<FmBranchEO> collectSuperiors(FmBranchEO openingBranch) {
		List<FmBranchEO> superiors = new ArrayList<>();
		FmBranchEO current = openingBranch;
		while (current != null && current.getAttachedTo() != null) {
			FmBranchEO parent = fmBranchBcc.findByBranch(current.getAttachedTo());
			if (parent == null) {
				break;
			}
			superiors.add(parent);
			current = parent;
		}
		superiors.sort(Comparator.comparingInt(ST002Pbc::hierarchyLevel).reversed());
		return superiors;
	}

	/** 机构层级代码取值转数值，用于从大到小排序；机构层级代码为空时视为最低层级。 */
	private static int hierarchyLevel(FmBranchEO branch) {
		return branch.getHierarchyCode() == null ? Integer.MIN_VALUE
				: Integer.parseInt(branch.getHierarchyCode().getValue());
	}

	/**
	 * 子步骤5 检查上级机构匹配的限额场景：按[上级机构集合]中的机构层级从大到小遍历；
	 * 子步骤5.1 按上级机构号查询启用限额场景配置；
	 * 子步骤5.2 命中则中断本次遍历并返回，未命中继续遍历下一条；
	 * 子步骤5.3 遍历结束返回空（null 由调用方按未匹配处理）。
	 */
	private RbLimitCtrlConfEO matchSuperiorConfig(List<FmBranchEO> superiors) {
		for (FmBranchEO superior : superiors) {
			RbLimitCtrlConfEO matched = findEnabledConfig(superior.getBranch());
			if (matched != null) {
				return matched;
			}
		}
		return null;
	}
}
