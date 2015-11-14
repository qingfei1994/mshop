/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.common.constants;

/**
 * Description		: 运费相关常量
 * <p/>
 * <br><br>Time		: 2015/6/16 12:29
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public class TransportationExpensesConstants {

    /** 计价方式：按数量 */
    public static final byte PRICING_MANNER_NUMBER = 0;
    /** 计价方式：按重量 */
    public static final byte PRICING_MANNER_WEIGHT = 1;
    /** 计价方式：按体积 */
    public static final byte PRICING_MANNER_VOLUME = 2;

    /** 运送方式：快递 */
    public static final byte SHIPPING_METHOD_EXPRESS = 0;
    /** 运送方式：EMS */
    public static final byte SHIPPING_METHOD_EMS = 1;
    /** 运送方式：平邮 */
    public static final byte SHIPPING_METHOD_POST = 2;
    /** 运送方式：混合 */
    public static final byte SHIPPING_METHOD_MIX = 3;

    /** 配送全部地区 */
    public static final byte ALL_REGION = 1;

}
