package com.dcits.limit.facade.components;

import com.dcits.limit.enums.CtrlItemType;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.dcits.limit.facade.eo.RbLimitCtrlCustomInfoEO;

/*实体表【限额控制客户自定义配置(RB_LIMIT_CTRL_CUSTOM_INFO)】数据服务接口*/
public interface IRbLimitCtrlCustomInfoBcc {
    /** count数据库表记录根据入参com.dcits.limit.facade.eo.RbLimitCtrlCustomInfoEO中的属性字段组合 **/
    long countByEo(RbLimitCtrlCustomInfoEO eo);

    /** remove数据库表记录根据入参com.dcits.limit.facade.eo.RbLimitCtrlCustomInfoEO中的属性字段组合 **/
    int removeByEo(RbLimitCtrlCustomInfoEO eo);

    /** remove 根据主键: 限额检查对象值、限额场景编码 **/
    int removeByPrimaryKey(String checkObjVal, String limitSceneNo);

    int create(RbLimitCtrlCustomInfoEO eo);

    /** create数据库表记录，主键和EO对象中不允许为空的字段必填，其它可为空字段可选填，执行时根据com.dcits.limit.facade.eo.RbLimitCtrlCustomInfoEO中不为空的属性写入数据库**/
    int createSelective(RbLimitCtrlCustomInfoEO eo);

    /** find数据库表记录根据入参com.dcits.limit.facade.eo.RbLimitCtrlCustomInfoEO中的属性字段组合 **/
    List<RbLimitCtrlCustomInfoEO> findByEo(RbLimitCtrlCustomInfoEO eo);

    /** find 根据主键: 限额检查对象值、限额场景编码 **/
    RbLimitCtrlCustomInfoEO findByPrimaryKey(String checkObjVal, String limitSceneNo);

    /**  根据主键: 限额检查对象值、限额场景编码执行更新记录操作，仅更新入参com.dcits.limit.facade.eo.RbLimitCtrlCustomInfoEO中不为空的属性字段 **/
    int modifyByPrimaryKeySelective(RbLimitCtrlCustomInfoEO eo);

    /** modify 根据主键: 限额检查对象值、限额场景编码 **/
    int modifyByPrimaryKey(RbLimitCtrlCustomInfoEO eo);
}