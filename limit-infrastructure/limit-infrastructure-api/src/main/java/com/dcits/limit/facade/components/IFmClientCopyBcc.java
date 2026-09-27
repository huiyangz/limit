package com.dcits.limit.facade.components;

import com.dcits.limit.enums.CategoryType;
import com.dcits.limit.enums.City;
import com.dcits.limit.enums.ClassLevel;
import com.dcits.limit.enums.ClientClass;
import com.dcits.limit.enums.ClientIndicator;
import com.dcits.limit.enums.ClientStatus;
import com.dcits.limit.enums.ClientType;
import com.dcits.limit.enums.ClientVerificationResult;
import com.dcits.limit.enums.ContactType;
import com.dcits.limit.enums.Country;
import com.dcits.limit.enums.CountryLoc;
import com.dcits.limit.enums.CrRating;
import com.dcits.limit.enums.District;
import com.dcits.limit.enums.Education;
import com.dcits.limit.enums.Industry;
import com.dcits.limit.enums.IndustryLevel;
import com.dcits.limit.enums.LimitBranchId;
import com.dcits.limit.enums.Nation;
import com.dcits.limit.enums.OccupationCode;
import com.dcits.limit.enums.Sex;
import com.dcits.limit.enums.SpokenLanguage;
import com.dcits.limit.enums.TaxFlag;
import com.dcits.limit.enums.TaxResidentFlag;
import com.dcits.limit.enums.ThawDocumentType2;
import java.util.Date;
import java.util.List;

import com.dcits.limit.facade.eo.FmClientCopyEO;

/*实体表【客户副本表(FM_CLIENT_COPY)】数据服务接口*/
public interface IFmClientCopyBcc {
    /** count数据库表记录根据入参com.dcits.limit.facade.eo.FmClientCopyEO中的属性字段组合 **/
    long countByEo(FmClientCopyEO eo);

    /** remove数据库表记录根据入参com.dcits.limit.facade.eo.FmClientCopyEO中的属性字段组合 **/
    int removeByEo(FmClientCopyEO eo);

    /** remove 根据主键: 客户号 **/
    int removeByPrimaryKey(String clientNo);

    int create(FmClientCopyEO eo);

    /** create数据库表记录，主键和EO对象中不允许为空的字段必填，其它可为空字段可选填，执行时根据com.dcits.limit.facade.eo.FmClientCopyEO中不为空的属性写入数据库**/
    int createSelective(FmClientCopyEO eo);

    /** find数据库表记录根据入参com.dcits.limit.facade.eo.FmClientCopyEO中的属性字段组合 **/
    List<FmClientCopyEO> findByEo(FmClientCopyEO eo);

    /** find 根据主键: 客户号 **/
    FmClientCopyEO findByPrimaryKey(String clientNo);

    /**  根据主键: 客户号执行更新记录操作，仅更新入参com.dcits.limit.facade.eo.FmClientCopyEO中不为空的属性字段 **/
    int modifyByPrimaryKeySelective(FmClientCopyEO eo);

    /** modify 根据主键: 客户号 **/
    int modifyByPrimaryKey(FmClientCopyEO eo);
}