package com.dcits.limit.facade.components;

import com.dcits.limit.enums.TranCcy;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.dcits.limit.facade.eo.RbLimitSumInfoEO;

/*实体表【限额累计信息表(RB_LIMIT_SUM_INFO)】数据服务接口*/
public interface IRbLimitSumInfoBcc {
    /** count数据库表记录根据入参com.dcits.limit.facade.eo.RbLimitSumInfoEO中的属性字段组合 **/
    long countByEo(RbLimitSumInfoEO eo);

    /** remove数据库表记录根据入参com.dcits.limit.facade.eo.RbLimitSumInfoEO中的属性字段组合 **/
    int removeByEo(RbLimitSumInfoEO eo);

    /** remove 根据主键: 限额检查对象值、限额场景编码 **/
    int removeByPrimaryKey(String checkObjVal, String limitSceneNo);

    int create(RbLimitSumInfoEO eo);

    /** create数据库表记录，主键和EO对象中不允许为空的字段必填，其它可为空字段可选填，执行时根据com.dcits.limit.facade.eo.RbLimitSumInfoEO中不为空的属性写入数据库**/
    int createSelective(RbLimitSumInfoEO eo);

    /** find数据库表记录根据入参com.dcits.limit.facade.eo.RbLimitSumInfoEO中的属性字段组合 **/
    List<RbLimitSumInfoEO> findByEo(RbLimitSumInfoEO eo);

    /** find 根据主键: 限额检查对象值、限额场景编码 **/
    RbLimitSumInfoEO findByPrimaryKey(String checkObjVal, String limitSceneNo);

    /**  根据主键: 限额检查对象值、限额场景编码执行更新记录操作，仅更新入参com.dcits.limit.facade.eo.RbLimitSumInfoEO中不为空的属性字段 **/
    int modifyByPrimaryKeySelective(RbLimitSumInfoEO eo);

    /** modify 根据主键: 限额检查对象值、限额场景编码 **/
    int modifyByPrimaryKey(RbLimitSumInfoEO eo);
}