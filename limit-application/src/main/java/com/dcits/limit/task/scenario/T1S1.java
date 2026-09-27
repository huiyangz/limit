package com.dcits.limit.task.scenario;

import java.util.ResourceBundle;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dcits.common.task.RespHeader;
import com.dcits.limit.facade.bo.ST001InputBO;
import com.dcits.limit.facade.bo.ST001OutputBO;
import com.dcits.limit.facade.bo.ST002InputBO;
import com.dcits.limit.facade.bo.ST002OutputBO;
import com.dcits.limit.facade.bo.ST003InputBO;
import com.dcits.limit.facade.bo.ST003OutputBO;
import com.dcits.limit.facade.bo.ST004InputBO;
import com.dcits.limit.facade.bo.ST004OutputBO;
import com.dcits.limit.facade.bo.ST005InputBO;
import com.dcits.limit.facade.bo.ST005OutputBO;
import com.dcits.limit.facade.bo.ST006InputBO;
import com.dcits.limit.facade.bo.ST006OutputBO;
import com.dcits.limit.facade.bo.ST007InputBO;
import com.dcits.limit.facade.bo.ST007OutputBO;
import com.dcits.limit.facade.bo.ST008InputBO;
import com.dcits.limit.facade.bo.ST008OutputBO;
import com.dcits.limit.facade.bo.ST009InputBO;
import com.dcits.limit.facade.bo.ST009OutputBO;
import com.dcits.limit.facade.bo.ST010InputBO;
import com.dcits.limit.facade.bo.ST010OutputBO;
import com.dcits.limit.step.IST001;
import com.dcits.limit.step.IST002;
import com.dcits.limit.step.IST003;
import com.dcits.limit.step.IST004;
import com.dcits.limit.step.IST005;
import com.dcits.limit.step.IST006;
import com.dcits.limit.step.IST007;
import com.dcits.limit.step.IST008;
import com.dcits.limit.step.IST009;
import com.dcits.limit.step.IST010;
import com.dcits.limit.task.dto.T1S1InputDTO;
import com.dcits.limit.task.dto.T1S1OutputDTO;

/**
 * T1S1 检查限额 场景。
 *
 * <p>按 SPEC 执行步骤表顺序编排 ST001-ST010：ST001 获取限额场景编码、
 * ST002 检查账户机构是否可匹配到限额场景配置、ST003 计算限额累计金额、ST004 检查限额、
 * ST005 处理限额、ST006 获取累计限额、ST007 匹配限额场景、ST008 登记累计限额、
 * ST009 检查限额场景配置是否有效、ST010 更新累计限额。</p>
 *
 * <p>字段接线（SPEC 已接受结论与用例设计决定）：各步骤限额场景编码统一取场景输入
 * limitSceneNo（多前序来源覆盖关系未决，场景输入为唯一确定来源，ST007 匹配编码无消费方）；
 * ST003.checkObjVal 取场景输入账号（账号即限额检查对象值）；ST008.runDate 取场景输入交易日期；
 * ST003 计算的限额累计金额/笔数向 ST004、ST008、ST010 传递（不取 ST006 既有值或登记回显）；
 * ST002 限额机构编码（LimitBranchId 枚举）经 getValue() 转 String 供 ST004、ST009 使用。</p>
 *
 * <p>事务：IST008、IST010 接口契约声明以 Spring 本地事务执行限额累计信息表
 * （RB_LIMIT_SUM_INFO）写入，场景入口按同一本地事务组织调用（组件微服务归属材料缺失，
 * 见 outputs/事务边界判定.md，不承诺跨组件原子性）。</p>
 */
@Component
public class T1S1 {

	/** 响应头业务文案唯一来源：工程错误码资源 */
	private static final String ERROR_CODES = "errorcodes";

	/** 限额检查结果「未超限」：ST008 登记、ST010 更新分支的已声明触发值（检查结果数据链未决，见 SPEC 已接受结论，ST004 输出契约无该字段，按步骤描述已声明值传递） */
	private static final String LIMIT_CHECK_RESULT_NOT_OVER_LIMIT = "未超限";

	private final IST001 ist001;
	private final IST002 ist002;
	private final IST003 ist003;
	private final IST004 ist004;
	private final IST005 ist005;
	private final IST006 ist006;
	private final IST007 ist007;
	private final IST008 ist008;
	private final IST009 ist009;
	private final IST010 ist010;

	public T1S1(IST001 ist001, IST002 ist002, IST003 ist003, IST004 ist004, IST005 ist005,
			IST006 ist006, IST007 ist007, IST008 ist008, IST009 ist009, IST010 ist010) {
		this.ist001 = ist001;
		this.ist002 = ist002;
		this.ist003 = ist003;
		this.ist004 = ist004;
		this.ist005 = ist005;
		this.ist006 = ist006;
		this.ist007 = ist007;
		this.ist008 = ist008;
		this.ist009 = ist009;
		this.ist010 = ist010;
	}

	/**
	 * 检查限额场景入口：顺序执行 ST001-ST010，任一步骤失败立即短路返回失败头，
	 * 全部成功后清理并设置响应头成功状态。
	 */
	@Transactional
	public T1S1OutputDTO execute(RespHeader header, T1S1InputDTO input) {
		T1S1OutputDTO output = new T1S1OutputDTO();

		// ST001 获取限额场景编码
		ST001InputBO st001In = new ST001InputBO();
		st001In.setBaseAcctNo(input.getBaseAcctNo());
		st001In.setClientNo(input.getClientNo());
		st001In.setLimitSceneNo(input.getLimitSceneNo());
		st001In.setTranDate(input.getTranDate());
		ST001OutputBO st001Out = ist001.execute(st001In);
		if (!st001Out.isSucceed()) {
			handleError(header, st001Out.getErrorCode(), errorMessage(st001Out.getErrorCode()));
			return output;
		}

		// ST002 检查账户机构是否可匹配到限额场景配置
		ST002InputBO st002In = new ST002InputBO();
		st002In.setBaseAcctNo(input.getBaseAcctNo());
		ST002OutputBO st002Out = ist002.execute(st002In);
		if (!st002Out.isSucceed()) {
			handleError(header, st002Out.getErrorCode(), errorMessage(st002Out.getErrorCode()));
			return output;
		}

		// ST003 计算限额累计金额
		ST003InputBO st003In = new ST003InputBO();
		st003In.setLimitSceneNo(input.getLimitSceneNo());
		st003In.setTranAmt(input.getTranAmt());
		st003In.setCheckObjVal(input.getBaseAcctNo());
		st003In.setClientNo(input.getClientNo());
		ST003OutputBO st003Out = ist003.execute(st003In);
		if (!st003Out.isSucceed()) {
			handleError(header, st003Out.getErrorCode(), errorMessage(st003Out.getErrorCode()));
			return output;
		}

		// ST004 检查限额
		ST004InputBO st004In = new ST004InputBO();
		st004In.setLimitSceneNo(input.getLimitSceneNo());
		st004In.setLimitSumAmt(st003Out.getLimitSumAmt());
		st004In.setLimitSumNum(st003Out.get否());
		st004In.setLimitBranchId(st002Out.getLimitBranchId().getValue());
		ST004OutputBO st004Out = ist004.execute(st004In);
		if (!st004Out.isSucceed()) {
			handleError(header, st004Out.getErrorCode(), errorMessage(st004Out.getErrorCode()));
			return output;
		}

		// ST005 处理限额
		ST005InputBO st005In = new ST005InputBO();
		st005In.setLimitSceneNo(input.getLimitSceneNo());
		ST005OutputBO st005Out = ist005.execute(st005In);
		if (!st005Out.isSucceed()) {
			handleError(header, st005Out.getErrorCode(), errorMessage(st005Out.getErrorCode()));
			return output;
		}

		// ST006 获取累计限额
		ST006InputBO st006In = new ST006InputBO();
		st006In.setBaseAcctNo(input.getBaseAcctNo());
		st006In.setClientNo(input.getClientNo());
		st006In.setLimitSceneNo(input.getLimitSceneNo());
		ST006OutputBO st006Out = ist006.execute(st006In);
		if (!st006Out.isSucceed()) {
			handleError(header, st006Out.getErrorCode(), errorMessage(st006Out.getErrorCode()));
			return output;
		}

		// ST007 匹配限额场景
		ST007InputBO st007In = new ST007InputBO();
		st007In.setFactorName(input.getFactorName());
		ST007OutputBO st007Out = ist007.execute(st007In);
		if (!st007Out.isSucceed()) {
			handleError(header, st007Out.getErrorCode(), errorMessage(st007Out.getErrorCode()));
			return output;
		}

		// ST008 登记累计限额
		ST008InputBO st008In = new ST008InputBO();
		st008In.setCheckResult(LIMIT_CHECK_RESULT_NOT_OVER_LIMIT);
		st008In.setBaseAcctNo(input.getBaseAcctNo());
		st008In.setClientNo(input.getClientNo());
		st008In.setTranAmt(input.getTranAmt());
		st008In.setLimitSceneNo(input.getLimitSceneNo());
		st008In.setRunDate(input.getTranDate());
		st008In.setLimitSumAmt(st003Out.getLimitSumAmt());
		st008In.set否(st003Out.get否());
		ST008OutputBO st008Out = ist008.execute(st008In);
		if (!st008Out.isSucceed()) {
			handleError(header, st008Out.getErrorCode(), errorMessage(st008Out.getErrorCode()));
			return output;
		}

		// ST009 检查限额场景配置是否有效
		ST009InputBO st009In = new ST009InputBO();
		st009In.setTranDate(input.getTranDate());
		st009In.setTranTimestamp(input.getTranTimestamp());
		st009In.setLimitBranchId(st002Out.getLimitBranchId().getValue());
		st009In.setLimitSceneNo(input.getLimitSceneNo());
		ST009OutputBO st009Out = ist009.execute(st009In);
		if (!st009Out.isSucceed()) {
			handleError(header, st009Out.getErrorCode(), errorMessage(st009Out.getErrorCode()));
			return output;
		}

		// ST010 更新累计限额
		ST010InputBO st010In = new ST010InputBO();
		st010In.setLimitCheckResult(LIMIT_CHECK_RESULT_NOT_OVER_LIMIT);
		st010In.setBaseAcctNo(input.getBaseAcctNo());
		st010In.setLimitSceneNo(input.getLimitSceneNo());
		st010In.setClientNo(input.getClientNo());
		st010In.setLimitSumAmt(st003Out.getLimitSumAmt());
		st010In.setLimitSumNum(st003Out.get否());
		ST010OutputBO st010Out = ist010.execute(st010In);
		if (!st010Out.isSucceed()) {
			handleError(header, st010Out.getErrorCode(), errorMessage(st010Out.getErrorCode()));
			return output;
		}

		// 场景输出「限额检查结果」无已确认来源与取值域（SPEC 已接受结论第 1 条、用例未决 Q1），不虚构赋值
		header.setSucceed(true);
		header.setErrorCode(null);
		header.setErrorMessage(null);
		return output;
	}

	/** 按工程错误码资源加载响应头业务文案（唯一消息来源，不设备用文案） */
	private String errorMessage(String errorCode) {
		return ResourceBundle.getBundle(ERROR_CODES).getString(errorCode);
	}

	/** 步骤失败统一出口：设置失败响应头，业务输出保持未填充 */
	private void handleError(RespHeader header, String errorCode, String errorMessage) {
		header.setSucceed(false);
		header.setErrorCode(errorCode);
		header.setErrorMessage(errorMessage);
	}
}
