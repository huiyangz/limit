package com.dcits.limit.step;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dcits.limit.facade.bo.ST009InputBO;
import com.dcits.limit.facade.bo.ST009OutputBO;
import com.dcits.limit.facade.components.IRbLimitCtrlConfBcc;
import com.dcits.limit.facade.eo.RbLimitCtrlConfEO;

/**
 * ST009 检查限额场景配置是否有效 步骤实现
 *
 * <p>步骤描述1：根据限额机构编码、限额场景编码查询限额控制配置，获取限额控制开始日期、
 * 限额控制结束日期、限额控制开始时间、限额控制结束时间；未查询到配置记录时按检查不通过处理，
 * 限额场景编码返回为空。</p>
 *
 * <p>步骤描述2：交易日期、交易时间均在控制区间内则返回限额场景编码及四个控制区间字段，
 * 否则限额场景编码与四个控制区间字段全部返回为空。</p>
 */
@Service
public class ST009Pbc implements IST009 {

    private static final Logger LOGGER = LoggerFactory.getLogger(ST009Pbc.class);

    private final IRbLimitCtrlConfBcc rbLimitCtrlConfBcc;

    public ST009Pbc(IRbLimitCtrlConfBcc rbLimitCtrlConfBcc) {
        this.rbLimitCtrlConfBcc = rbLimitCtrlConfBcc;
    }

    @Override
    public ST009OutputBO execute(ST009InputBO input) {
        validateInput(input);
        ST009OutputBO output = new ST009OutputBO();

        // 步骤描述1 获取限额场景控制区间：根据[限额机构编码]、[限额场景编码]查询【限额控制配置】
        RbLimitCtrlConfEO conf =
                rbLimitCtrlConfBcc.findByPrimaryKey(input.getLimitBranchId(), input.getLimitSceneNo());
        if (conf == null) {
            // 未查询到配置记录时按检查不通过处理，[限额场景编码]返回为空
            LOGGER.info("ST009未查询到限额控制配置，限额机构编码={}，限额场景编码={}，检查不通过",
                    input.getLimitBranchId(), input.getLimitSceneNo());
            output.setSucceed(true);
            return output;
        }

        // 步骤描述2 检查交易时间：日期、时间均在控制区间内则返回场景编码及四个控制区间字段，否则全部返回为空
        if (isDateInRange(input.getTranDate(), conf)
                && isTimeInRange(input.getTranTimestamp(), conf)) {
            output.setLimitSceneNo(conf.getLimitSceneNo());
            output.setLimitCtrlBgnDate(conf.getLimitCtrlBgnDate());
            output.setLimitCtrlEndDate(conf.getLimitCtrlEndDate());
            output.setLimitCtrlBgnTime(conf.getLimitCtrlBgnTime());
            output.setLimitCtrlEndTime(conf.getLimitCtrlEndTime());
        } else {
            LOGGER.info("ST009交易日期或交易时间不在限额控制区间内，限额机构编码={}，限额场景编码={}，检查不通过",
                    input.getLimitBranchId(), input.getLimitSceneNo());
        }
        output.setSucceed(true);
        return output;
    }

    /** 输入必填校验：不满足时按技术异常抛出，本步骤无业务失败场景 */
    private static void validateInput(ST009InputBO input) {
        if (input == null) {
            throw new IllegalArgumentException("ST009输入BO不能为空");
        }
        if (input.getTranDate() == null) {
            throw new IllegalArgumentException("交易日期不能为空");
        }
        if (isBlank(input.getTranTimestamp())) {
            throw new IllegalArgumentException("交易时间不能为空");
        }
        if (!input.getTranTimestamp().matches("\\d{6}")) {
            throw new IllegalArgumentException("交易时间格式必须为HHmmss: " + input.getTranTimestamp());
        }
        if (isBlank(input.getLimitBranchId())) {
            throw new IllegalArgumentException("限额机构编码不能为空");
        }
        if (isBlank(input.getLimitSceneNo())) {
            throw new IllegalArgumentException("限额场景编码不能为空");
        }
    }

    /** 交易日期是否在[限额控制开始日期,限额控制结束日期]闭区间内 */
    private static boolean isDateInRange(Date tranDate, RbLimitCtrlConfEO conf) {
        return !tranDate.before(conf.getLimitCtrlBgnDate()) && !tranDate.after(conf.getLimitCtrlEndDate());
    }

    /** 交易时间是否在[限额控制开始时间,限额控制结束时间]闭区间内（按HH:mm:ss时间量值比较） */
    private static boolean isTimeInRange(String tranTimestamp, RbLimitCtrlConfEO conf) {
        int tranSeconds = parseSecondsOfDay(tranTimestamp);
        int bgnSeconds = toSecondsOfDay(conf.getLimitCtrlBgnTime());
        int endSeconds = toSecondsOfDay(conf.getLimitCtrlEndTime());
        return tranSeconds >= bgnSeconds && tranSeconds <= endSeconds;
    }

    /** 解析HHmmss格式交易时间为当天秒数 */
    private static int parseSecondsOfDay(String tranTimestamp) {
        int hour = Integer.parseInt(tranTimestamp.substring(0, 2));
        int minute = Integer.parseInt(tranTimestamp.substring(2, 4));
        int second = Integer.parseInt(tranTimestamp.substring(4, 6));
        if (hour > 23 || minute > 59 || second > 59) {
            throw new IllegalArgumentException("交易时间格式必须为HHmmss: " + tranTimestamp);
        }
        return hour * 3600 + minute * 60 + second;
    }

    /** 取Date中的时间量（当天秒数），忽略日期部分 */
    private static int toSecondsOfDay(Date time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        return calendar.get(Calendar.HOUR_OF_DAY) * 3600
                + calendar.get(Calendar.MINUTE) * 60
                + calendar.get(Calendar.SECOND);
    }

    private static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
