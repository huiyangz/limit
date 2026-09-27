package com.dcits.limit.facade.components;

import com.dcits.limit.enums.LimitBranchId;
import com.dcits.limit.enums.RecordStatus;
import com.dcits.limit.enums.SourceType;
import com.dcits.limit.enums.SumType;
import com.dcits.limit.enums.TranCcy;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.dcits.limit.facade.eo.RbLimitSumJnlEO;

/*实体表【限额累计流水表(RB_LIMIT_SUM_JNL)】数据服务接口*/
public interface IRbLimitSumJnlBcc {
    /** count数据库表记录根据入参com.dcits.limit.facade.eo.RbLimitSumJnlEO中的属性字段组合 **/
    long countByEo(RbLimitSumJnlEO eo);

    /** remove数据库表记录根据入参com.dcits.limit.facade.eo.RbLimitSumJnlEO中的属性字段组合 **/
    int removeByEo(RbLimitSumJnlEO eo);

    /** remove 根据主键: 限额检查对象值、限额场景编码、客户号 **/
    int removeByPrimaryKey(String checkObjVal, String limitSceneNo, String clientNo);

    int create(RbLimitSumJnlEO eo);

    /** create数据库表记录，主键和EO对象中不允许为空的字段必填，其它可为空字段可选填，执行时根据com.dcits.limit.facade.eo.RbLimitSumJnlEO中不为空的属性写入数据库**/
    int createSelective(RbLimitSumJnlEO eo);

    /** find数据库表记录根据入参com.dcits.limit.facade.eo.RbLimitSumJnlEO中的属性字段组合 **/
    List<RbLimitSumJnlEO> findByEo(RbLimitSumJnlEO eo);

    /** find 根据主键: 限额检查对象值、限额场景编码、客户号 **/
    RbLimitSumJnlEO findByPrimaryKey(String checkObjVal, String limitSceneNo, String clientNo);

    /**  根据主键: 限额检查对象值、限额场景编码、客户号执行更新记录操作，仅更新入参com.dcits.limit.facade.eo.RbLimitSumJnlEO中不为空的属性字段 **/
    int modifyByPrimaryKeySelective(RbLimitSumJnlEO eo);

    /** modify 根据主键: 限额检查对象值、限额场景编码、客户号 **/
    int modifyByPrimaryKey(RbLimitSumJnlEO eo);
}