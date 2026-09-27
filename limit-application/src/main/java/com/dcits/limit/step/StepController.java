package com.dcits.limit.step;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dcits.limit.facade.bo.ST001InputBO;
import com.dcits.limit.facade.bo.ST001OutputBO;
import com.dcits.limit.facade.bo.ST002InputBO;
import com.dcits.limit.facade.bo.ST002OutputBO;
import com.dcits.limit.facade.bo.ST003InputBO;
import com.dcits.limit.facade.bo.ST003OutputBO;
import com.dcits.limit.facade.bo.ST004InputBO;
import com.dcits.limit.facade.bo.ST004OutputBO;
import com.dcits.limit.facade.bo.ST005InputBO;
import com.dcits.limit.facade.bo.ST005OutputBO;
import com.dcits.limit.facade.bo.ST006InputBO;
import com.dcits.limit.facade.bo.ST006OutputBO;
import com.dcits.limit.facade.bo.ST007InputBO;
import com.dcits.limit.facade.bo.ST007OutputBO;
import com.dcits.limit.facade.bo.ST008InputBO;
import com.dcits.limit.facade.bo.ST008OutputBO;
import com.dcits.limit.facade.bo.ST009InputBO;
import com.dcits.limit.facade.bo.ST009OutputBO;
import com.dcits.limit.facade.bo.ST010InputBO;
import com.dcits.limit.facade.bo.ST010OutputBO;

/**
 * 步骤控制器
 */
@RestController
@RequestMapping("steps")
public class StepController {
    
    @Autowired
    private IST001 st001;

    @Autowired
    private IST002 st002;

    @Autowired
    private IST003 st003;

    @Autowired
    private IST004 st004;

    @Autowired
    private IST005 st005;

    @Autowired
    private IST006 st006;

    @Autowired
    private IST007 st007;

    @Autowired
    private IST008 st008;

    @Autowired
    private IST009 st009;

    @Autowired
    private IST010 st010;

    /**
     * 执行ST001-获取限额场景编码步骤
     * @param input 输入参数
     * @return 输出结果
     */
    @PostMapping("/ST001")
    public ST001OutputBO executeST001(@RequestBody ST001InputBO input) {
        return st001.execute(input);
    }

    /**
     * 执行ST002-检查账户机构是否可匹配到限额场景配置步骤
     * @param input 输入参数
     * @return 输出结果
     */
    @PostMapping("/ST002")
    public ST002OutputBO executeST002(@RequestBody ST002InputBO input) {
        return st002.execute(input);
    }

    /**
     * 执行ST003-计算限额累计金额步骤
     * @param input 输入参数
     * @return 输出结果
     */
    @PostMapping("/ST003")
    public ST003OutputBO executeST003(@RequestBody ST003InputBO input) {
        return st003.execute(input);
    }

    /**
     * 执行ST004-检查限额步骤
     * @param input 输入参数
     * @return 输出结果
     */
    @PostMapping("/ST004")
    public ST004OutputBO executeST004(@RequestBody ST004InputBO input) {
        return st004.execute(input);
    }

    /**
     * 执行ST005-处理限额步骤
     * @param input 输入参数
     * @return 输出结果
     */
    @PostMapping("/ST005")
    public ST005OutputBO executeST005(@RequestBody ST005InputBO input) {
        return st005.execute(input);
    }

    /**
     * 执行ST006-获取累计限额步骤
     * @param input 输入参数
     * @return 输出结果
     */
    @PostMapping("/ST006")
    public ST006OutputBO executeST006(@RequestBody ST006InputBO input) {
        return st006.execute(input);
    }

    /**
     * 执行ST007-匹配限额场景步骤
     * @param input 输入参数
     * @return 输出结果
     */
    @PostMapping("/ST007")
    public ST007OutputBO executeST007(@RequestBody ST007InputBO input) {
        return st007.execute(input);
    }

    /**
     * 执行ST008-登记累计限额步骤
     * @param input 输入参数
     * @return 输出结果
     */
    @PostMapping("/ST008")
    public ST008OutputBO executeST008(@RequestBody ST008InputBO input) {
        return st008.execute(input);
    }

    /**
     * 执行ST009-检查限额场景配置是否有效步骤
     * @param input 输入参数
     * @return 输出结果
     */
    @PostMapping("/ST009")
    public ST009OutputBO executeST009(@RequestBody ST009InputBO input) {
        return st009.execute(input);
    }

    /**
     * 执行ST010-更新累计限额步骤
     * @param input 输入参数
     * @return 输出结果
     */
    @PostMapping("/ST010")
    public ST010OutputBO executeST010(@RequestBody ST010InputBO input) {
        return st010.execute(input);
    }

}