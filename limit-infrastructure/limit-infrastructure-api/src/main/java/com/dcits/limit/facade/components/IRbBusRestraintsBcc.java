package com.dcits.limit.facade.components;

import com.dcits.limit.enums.AcctInternalKeyType;
import com.dcits.limit.enums.LimitBranchId;
import com.dcits.limit.enums.PeriodType;
import com.dcits.limit.enums.ResAcctRange;
import com.dcits.limit.enums.RestraintLevel;
import com.dcits.limit.enums.RestraintSource;
import com.dcits.limit.enums.RestraintsStatus;
import com.dcits.limit.enums.RestraintType;
import com.dcits.limit.enums.SourceModule;
import com.dcits.limit.enums.ThawDocumentType2;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.dcits.limit.facade.eo.RbBusRestraintsEO;

/*实体表【对公存款账户限制表(RB_BUS_RESTRAINTS)】数据服务接口*/
public interface IRbBusRestraintsBcc {
    /** count数据库表记录根据入参com.dcits.limit.facade.eo.RbBusRestraintsEO中的属性字段组合 **/
    long countByEo(RbBusRestraintsEO eo);

    /** remove数据库表记录根据入参com.dcits.limit.facade.eo.RbBusRestraintsEO中的属性字段组合 **/
    int removeByEo(RbBusRestraintsEO eo);

    /** remove 根据主键: 限制编号 **/
    int removeByPrimaryKey(String resSeqNo);

    int create(RbBusRestraintsEO eo);

    /** create数据库表记录，主键和EO对象中不允许为空的字段必填，其它可为空字段可选填，执行时根据com.dcits.limit.facade.eo.RbBusRestraintsEO中不为空的属性写入数据库**/
    int createSelective(RbBusRestraintsEO eo);

    /** find数据库表记录根据入参com.dcits.limit.facade.eo.RbBusRestraintsEO中的属性字段组合 **/
    List<RbBusRestraintsEO> findByEo(RbBusRestraintsEO eo);

    /** find 根据主键: 限制编号 **/
    RbBusRestraintsEO findByPrimaryKey(String resSeqNo);

    /**  根据主键: 限制编号执行更新记录操作，仅更新入参com.dcits.limit.facade.eo.RbBusRestraintsEO中不为空的属性字段 **/
    int modifyByPrimaryKeySelective(RbBusRestraintsEO eo);

    /** modify 根据主键: 限制编号 **/
    int modifyByPrimaryKey(RbBusRestraintsEO eo);
}