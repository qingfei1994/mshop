/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.pojo;

/**
 * Description		: 运费统计结果
 * <p/>
 * <br><br>Time		: 2015/6/16 10:34
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public class TransportationExpensesResult {

    private Double totalMixExpenses; // 各项最高运费混合总和
    private Double totalExpressExpenses; // 快递总运费
    private Double totalEmsExpenses; // EMS总运费
    private Double totalPostExpenses; // 平邮总运费

    public Double getTotalMixExpenses() {
        return totalMixExpenses;
    }

    public void setTotalMixExpenses(Double totalMixExpenses) {
        this.totalMixExpenses = totalMixExpenses;
    }

    public Double getTotalExpressExpenses() {
        return totalExpressExpenses;
    }

    public void setTotalExpressExpenses(Double totalExpressExpenses) {
        this.totalExpressExpenses = totalExpressExpenses;
    }

    public Double getTotalEmsExpenses() {
        return totalEmsExpenses;
    }

    public void setTotalEmsExpenses(Double totalEmsExpenses) {
        this.totalEmsExpenses = totalEmsExpenses;
    }

    public Double getTotalPostExpenses() {
        return totalPostExpenses;
    }

    public void setTotalPostExpenses(Double totalPostExpenses) {
        this.totalPostExpenses = totalPostExpenses;
    }

}
