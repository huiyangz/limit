package com.dcits.limit.facade.components;

import com.dcits.limit.enums.AcctCcy;
import com.dcits.limit.enums.AcctNatureNo;
import com.dcits.limit.enums.AcctRiskLevel;
import com.dcits.limit.enums.AcctStatus;
import com.dcits.limit.enums.AcctVerifyFlag;
import com.dcits.limit.enums.AcctVerifyResult;
import com.dcits.limit.enums.AllDepInd;
import com.dcits.limit.enums.AllDraInd;
import com.dcits.limit.enums.AllDraRange;
import com.dcits.limit.enums.AnnualStatus;
import com.dcits.limit.enums.AutoRenewInd;
import com.dcits.limit.enums.BalType;
import com.dcits.limit.enums.CheckCertificateType;
import com.dcits.limit.enums.ClientType;
import com.dcits.limit.enums.DepositNature;
import com.dcits.limit.enums.FarmerFlag;
import com.dcits.limit.enums.FixedCall;
import com.dcits.limit.enums.IntIndFlag;
import com.dcits.limit.enums.LimitBranchId;
import com.dcits.limit.enums.ManageType;
import com.dcits.limit.enums.OsaFlag;
import com.dcits.limit.enums.PeriodType;
import com.dcits.limit.enums.RbAcctType;
import com.dcits.limit.enums.RbBusAcctPurpose;
import com.dcits.limit.enums.RenewMethod;
import com.dcits.limit.enums.SimpleAcct;
import com.dcits.limit.enums.SourceType;
import com.dcits.limit.enums.SpecAcctFlag;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.dcits.limit.facade.eo.RbBusAcctEO;

/*实体表【对公存款账户主表(RB_BUS_ACCT)】数据服务接口*/
public interface IRbBusAcctBcc {
    /** count数据库表记录根据入参com.dcits.limit.facade.eo.RbBusAcctEO中的属性字段组合 **/
    long countByEo(RbBusAcctEO eo);

    /** remove数据库表记录根据入参com.dcits.limit.facade.eo.RbBusAcctEO中的属性字段组合 **/
    int removeByEo(RbBusAcctEO eo);

    /** remove 根据主键: 账户内部键值 **/
    int removeByPrimaryKey(Integer internalKey);

    int create(RbBusAcctEO eo);

    /** create数据库表记录，主键和EO对象中不允许为空的字段必填，其它可为空字段可选填，执行时根据com.dcits.limit.facade.eo.RbBusAcctEO中不为空的属性写入数据库**/
    int createSelective(RbBusAcctEO eo);

    /** find数据库表记录根据入参com.dcits.limit.facade.eo.RbBusAcctEO中的属性字段组合 **/
    List<RbBusAcctEO> findByEo(RbBusAcctEO eo);

    /** find 根据主键: 账户内部键值 **/
    RbBusAcctEO findByPrimaryKey(Integer internalKey);

    /**  根据主键: 账户内部键值执行更新记录操作，仅更新入参com.dcits.limit.facade.eo.RbBusAcctEO中不为空的属性字段 **/
    int modifyByPrimaryKeySelective(RbBusAcctEO eo);

    /** modify 根据主键: 账户内部键值 **/
    int modifyByPrimaryKey(RbBusAcctEO eo);
}