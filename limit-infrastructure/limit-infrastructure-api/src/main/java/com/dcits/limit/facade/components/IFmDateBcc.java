package com.dcits.limit.facade.components;

import java.util.Date;
import java.util.List;

import com.dcits.limit.facade.eo.FmDateEO;

/*实体表【系统日期表(FM_DATE)】数据服务接口*/
public interface IFmDateBcc {
    /** count数据库表记录根据入参com.dcits.limit.facade.eo.FmDateEO中的属性字段组合 **/
    long countByEo(FmDateEO eo);

    /** remove数据库表记录根据入参com.dcits.limit.facade.eo.FmDateEO中的属性字段组合 **/
    int removeByEo(FmDateEO eo);

    /** remove 根据主键: 法人 **/
    int removeByPrimaryKey(String company);

    int create(FmDateEO eo);

    /** create数据库表记录，主键和EO对象中不允许为空的字段必填，其它可为空字段可选填，执行时根据com.dcits.limit.facade.eo.FmDateEO中不为空的属性写入数据库**/
    int createSelective(FmDateEO eo);

    /** find数据库表记录根据入参com.dcits.limit.facade.eo.FmDateEO中的属性字段组合 **/
    List<FmDateEO> findByEo(FmDateEO eo);

    /** find 根据主键: 法人 **/
    FmDateEO findByPrimaryKey(String company);

    /**  根据主键: 法人执行更新记录操作，仅更新入参com.dcits.limit.facade.eo.FmDateEO中不为空的属性字段 **/
    int modifyByPrimaryKeySelective(FmDateEO eo);

    /** modify 根据主键: 法人 **/
    int modifyByPrimaryKey(FmDateEO eo);
}