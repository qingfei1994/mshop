/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.common.constants;

/**
 * Description		: 商品相关常量
 * <p/>
 * <br><br>Time		: 2015/6/16 16:18
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public class GoodsConstants {

    /** 库存计算方式：下单减库存 */
    public static final byte GOOD_STOCK_CALCULATE_TYPE_ORDER = 0;
    /** 库存计算方式：支付减库存 */
    public static final byte GOOD_STOCK_CALCULATE_TYPE_PAY = 1;

    /** 卖家承担运费：不承担运费 */
    public static final byte GOOD_ASSUME_EXPENSES_NO = 0;
    /** 卖家承担运费：承担运费 */
    public static final byte GOOD_ASSUME_EXPENSES_YES = 1;

}
