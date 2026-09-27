package com.dcits.limit.step;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dcits.limit.enums.DealFlow;
import com.dcits.limit.facade.bo.ST005InputBO;
import com.dcits.limit.facade.bo.ST005OutputBO;
import com.dcits.limit.facade.components.IRbLimitCtrlConfBcc;
import com.dcits.limit.facade.eo.RbLimitCtrlConfEO;

/**
 * ST005 处理限额步骤实现。
 */
@Service
public class ST005Pbc implements IST005 {

	private final IRbLimitCtrlConfBcc rbLimitCtrlConfBcc;

	public ST005Pbc(IRbLimitCtrlConfBcc rbLimitCtrlConfBcc) {
		this.rbLimitCtrlConfBcc = rbLimitCtrlConfBcc;
	}

	@Override
	public ST005OutputBO execute(ST005InputBO input) {
		ST005OutputBO output = new ST005OutputBO();
		// 子步骤1：获取处理方式——根据限额场景编码查询【限额控制配置】
		DealFlow dealFlow = queryDealFlow(input.getLimitSceneNo());
		if (dealFlow == null) {
			// 子步骤1：查询无记录时本步骤返回成功，处理方式为空，不进入子步骤2
			output.setSucceed(true);
			return output;
		}
		// 子步骤2：返回处理结果——按处理方式返回对应检查结果
		if (DealFlow.B == dealFlow) {
			// 子步骤2a：处理方式等于"拒绝"，返回检查结果"拒绝"
			output.setDealFlow(DealFlow.B);
		} else if (DealFlow.D == dealFlow) {
			// 子步骤2b：处理方式等于"提醒"，返回检查结果"提醒"
			output.setDealFlow(DealFlow.D);
		} else if (DealFlow.A == dealFlow) {
			// 子步骤2c：处理方式等于"授权"，返回检查结果"授权"
			output.setDealFlow(DealFlow.A);
		}
		output.setSucceed(true);
		return output;
	}

	/**
	 * 子步骤1：获取处理方式。根据限额场景编码查询【限额控制配置】；
	 * 同一限额场景编码仅对应一条【限额控制配置】，查询无记录时返回 null。
	 */
	private DealFlow queryDealFlow(String limitSceneNo) {
		RbLimitCtrlConfEO queryEo = new RbLimitCtrlConfEO();
		queryEo.setLimitSceneNo(limitSceneNo);
		List<RbLimitCtrlConfEO> confList = rbLimitCtrlConfBcc.findByEo(queryEo);
		if (confList == null || confList.isEmpty()) {
			return null;
		}
		return confList.get(0).getDealFlow();
	}
}
