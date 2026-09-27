package com.dcits.limit.facade.components;

import com.dcits.limit.enums.CheckObjType;
import com.dcits.limit.enums.LimitConvert;
import com.dcits.limit.enums.LimitMainType;
import com.dcits.limit.enums.TranCcy;
import java.util.List;

import com.dcits.limit.facade.eo.RbLimitSceneDefEO;

/*实体表【限额场景定义(RB_LIMIT_SCENE_DEF)】数据服务接口*/
public interface IRbLimitSceneDefBcc {
    /** count数据库表记录根据入参com.dcits.limit.facade.eo.RbLimitSceneDefEO中的属性字段组合 **/
    long countByEo(RbLimitSceneDefEO eo);

    /** remove数据库表记录根据入参com.dcits.limit.facade.eo.RbLimitSceneDefEO中的属性字段组合 **/
    int removeByEo(RbLimitSceneDefEO eo);

    /** remove 根据主键: 限额场景编码 **/
    int removeByPrimaryKey(String limitSceneNo);

    int create(RbLimitSceneDefEO eo);

    /** create数据库表记录，主键和EO对象中不允许为空的字段必填，其它可为空字段可选填，执行时根据com.dcits.limit.facade.eo.RbLimitSceneDefEO中不为空的属性写入数据库**/
    int createSelective(RbLimitSceneDefEO eo);

    /** find数据库表记录根据入参com.dcits.limit.facade.eo.RbLimitSceneDefEO中的属性字段组合 **/
    List<RbLimitSceneDefEO> findByEo(RbLimitSceneDefEO eo);

    /** find 根据主键: 限额场景编码 **/
    RbLimitSceneDefEO findByPrimaryKey(String limitSceneNo);

    /**  根据主键: 限额场景编码执行更新记录操作，仅更新入参com.dcits.limit.facade.eo.RbLimitSceneDefEO中不为空的属性字段 **/
    int modifyByPrimaryKeySelective(RbLimitSceneDefEO eo);

    /** modify 根据主键: 限额场景编码 **/
    int modifyByPrimaryKey(RbLimitSceneDefEO eo);
}