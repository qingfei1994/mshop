/*
 * Copyright (c) 2015
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.module.wap.usercenter.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.db.dao.MultiTableDao;
import com.misuosi.mshop.entity.OrderInformation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Description		: 
 * <p/>
 * <br><br>Time		: 2015-6-27 下午8:22:44
 *
 * @author YLM
 * @version 1.0
 * @since 1.0
 */
@Service
public class WapUserCenterService {

	@Resource(name = "orderInformationDao")
	private Dao<OrderInformation> orderInformationDao;
	/**
	 * 通过微信ID获得用户各个类型订单的统计
	 * @param weinId
	 * @return
	 */
	public Map<String, Object> getOrderStatisticByWeinId(int weinId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT count(CASE WHEN")
        	.append(" (orin_status = 1 AND orin_pay_status = 0 AND shin_shipments_status = 0 AND shin_sign_status = 0)")
        	.append(" THEN 1 ELSE NULL END) AS type0_count,")
        	.append(" count(CASE WHEN")
        	.append(" (orin_status = 1 AND orin_pay_status = 1 AND shin_shipments_status = 0 AND shin_sign_status = 0)")
        	.append(" THEN 1 ELSE NULL END) AS type1_count,")
        	.append(" count(CASE WHEN")
        	.append(" (orin_status = 1 AND orin_pay_status = 1 AND shin_shipments_status = 1 AND shin_sign_status = 0)")
        	.append(" THEN 1 ELSE NULL END) AS type2_count")
        	/*.append(" count(CASE WHEN")
        	.append(" (orin_status = 2 AND orin_pay_status = 1 AND shin_shipments_status = 1 AND shin_sign_status = 1)")
        	.append(" THEN 1 ELSE NULL END) AS type3_count")*/
        	.append(" FROM order_information")
        	.append(" INNER JOIN shipments_information ON shipments_information.orin_id = order_information.orin_id")
        	/*.append(" INNER JOIN goods_order ON goods_order.orin_id = order_information.orin_id")
        	.append(" LEFT JOIN goods_comment ON goods_comment.goors_id = goods_order.orin_id")*/
        	.append(" WHERE order_information.wein_id = ?");
		Map<String,Object> totalCount= ((MultiTableDao)orderInformationDao).queryForMap(sql.toString(), weinId);
		StringBuilder unCommentSql=new StringBuilder();
		unCommentSql.append("SELECT count(DISTINCT ");
		unCommentSql.append("	CASE WHEN (goods_comment.goco_comment_content is NULL)");
		unCommentSql.append(" THEN 1 ELSE NULL  END)  as type3_count");
		unCommentSql.append(" FROM order_information");
		unCommentSql.append(" INNER JOIN shipments_information ON shipments_information.orin_id = order_information.orin_id");
		unCommentSql.append(" INNER JOIN goods_order ON goods_order.orin_id = order_information.orin_id");
		unCommentSql.append(" LEFT JOIN goods_comment ON goods_comment.goor_id = goods_order.goor_id");
		unCommentSql.append(" WHERE order_information.wein_id = ? ");
		Map<String,Object> unCommentCount= ((MultiTableDao)orderInformationDao).queryForMap(unCommentSql.toString(), weinId);
		totalCount.put("type3_count", unCommentCount.get("type3_count"));
		return totalCount;
	}
}

