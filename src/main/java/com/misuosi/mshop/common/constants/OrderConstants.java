/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.common.constants;

import java.io.File;

/**
 * Description :
 * <p/>
 * Time : 2015年6月11日 下午9:14:14
 *
 * @author HONG
 * @version 1.0
 * @since 1.0
 */
public class OrderConstants {

	// 订单支付状态
	/** 待支付 */
	public static final Byte ORIN_PAY_STATUS_UNPAID = 0;
	/** 已支付 */
	public static final Byte ORIN_PAY_STATUS_PAID = 1;

	// 订单支付方式
	/** 微信支付 */
	public static final Byte ORIN_PAY_WAY_WEIXIN = 0;
	/** 支付宝 */
	public static final Byte ORIN_PAY_WAY_ALIPAY = 1;
	/** 财付通 */
	public static final Byte ORIN_PAY_WAY_TENPAY = 2;
	/** 银行卡支付 */
	public static final Byte ORIN_PAY_WAY_BANKCARD = 3;
	/** 货到付款 */
	public static final Byte ORIN_PAY_WAY_COD = 4;
	/** 会员余额 */
	public static final Byte ORIN_PAY_WAY_MEMBER_BALANCE = 5;

	// 订单状态
	/** 交易关闭 */
	public static final Byte ORIN_STATUS_CLOSE = 0;
	/** 交易中 */
	public static final Byte ORIN_STATUS_DEALING = 1;
	/** 交易完成 */
	public static final Byte ORIN_STATUS_FINISH = 2;
	
	// 发货状态
	/** 未发货 */
	public static final Byte SHIN_SHINMENT_STATUS_NOT = 0;
	/** 已发货 */
	public static final Byte SHIN_SHINMENT_STATUS_YES = 1;

	// 签收状态
	/** 未签收*/
	public static final Byte SHIN_SIGN_STATUS_NOT = 0;
	/** 客户已签收 */
	public static final Byte SHIN_SIGN_STATUS_YES = 1;
	
	// 评论显示状态
	/** 不显示 */
	public static final Byte GOCO_SHOW_NOT = 0;
	/** 显示 */
	public static final Byte GOCO_SHOW_YES = 1;

	public static final String TEMPLATE_PATH = File.separator + "WEB-INF/views/common/_template";
	
	// 订单设置常量
	/** 退款/退货功能开启 */
	public static final Byte ORSE_REFUND_ON = 1;
	/** 退款/退货功能关闭 */
	public static final Byte ORSE_REFUND_OFF = 0;
	/** 自动确认收货功能开启 */
	public static final Byte ORSE_AUTOMATE_AFFIRM_ON = 1;
	/** 自动确认收货功能关闭 */
	public static final Byte ORSE_AUTOMATE_AFFIRM_OFF = 0;

	// 申请退款状态
	/** 未申请退款 */
	public static final Byte GOOR_REFUND_STATUS_NOT = 0;
	/** 已申请退款 */
	public static final Byte GOOR_REFUND_STATUS_YES = 1;

	// 客户诉求流程状态
	/** 待审核 */
	public static final Byte CLAP_FLOW_STATUS_PENDING = 0;
 	/** 拒绝 */
	public static final Byte CLAP_FLOW_STATUS_REFUSE = 1;
 	/** 通过 */
	public static final Byte CLAP_FLOW_STATUS_PASS = 2;
 	/** 撤销（客户主动撤销） */
	public static final Byte CLAP_FLOW_STATUS_CANCLE = 3;

	// 客户诉求类型
	/** 退款 */
	public static final Byte CLAP_TYPE_REFUND = 0;
	/** 退货退款 */
	public static final Byte CLAP_TYPE_RETURNED_PURCHASE = 1;

}
