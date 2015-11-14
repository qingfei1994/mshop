/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.admin.orders.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.db.dao.MultiTableDao;
import com.misuosi.mshop.entity.ClientAppeal;
import com.misuosi.mshop.entity.GoodsOrder;
import com.misuosi.mshop.entity.RefundInformation;

/**
 * Description :维权管理
 * <p/>
 * <br>
 * <br>
 * Time : 2015年6月8日 下午6:13:52
 * 
 * @author CHQ
 * @version 1.0
 * @since 1.0
 */
@Service
public class AdminOrderAppealService {

	@Resource(name = "clientAppealDao")
	private Dao<ClientAppeal> clientAppealDao;
	@Resource(name = "goodsOrderDao")
	private Dao<GoodsOrder> goodsOrderDao;
	@Resource(name = "refundInformationDao")
	private Dao<RefundInformation> refundInformationDao;

	/**
	 * 获取所有的维权信息
	 * 
	 * @return
	 */
	public List<Map<String, Object>> getGoodsAppealMap() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT client_appeal.*, ");
		sb.append("orders.goor_name,orders.goor_price, ");
		sb.append("orders.goor_first_picture,");
		sb.append("refund.rein_id,refund.rein_status,wechat.wein_nickname ");
		sb.append("FROM client_appeal ");
		sb.append("INNER JOIN goods_order orders ON orders.goor_id=client_appeal.goor_id ");
		sb.append("LEFT JOIN refund_information refund ON refund.goor_id=orders.goor_id ");
		sb.append("INNER JOIN order_information  ON order_information.orin_id=orders.orin_id ");
		sb.append("INNER JOIN wechat_information  wechat ON wechat.wein_id = order_information.wein_id");
		String sql = sb.toString();
		List<Map<String, Object>> maps = ((MultiTableDao) clientAppealDao)
				.queryForList(sql);
		return maps;
	}
	/**
	 * 审核维权记录
	 * @param clientAppeal
	 * @return
	 */
	public boolean saveCheck(ClientAppeal clientAppeal){
		Integer goorId=clientAppeal.getGoorId();
		//获取维权状态
		Byte status=clientAppeal.getClapFlowStatus();
		ClientAppeal temp=clientAppealDao.findUniqueByProperty("goor_id", goorId);
		clientAppeal.setClapId(temp.getClapId());
		//更新客户诉求信息
		int updateRow=clientAppealDao.dynamicUpdate(clientAppeal);
		RefundInformation refundInfo=new RefundInformation();
		refundInfo.setGoorId(goorId);
		if(status==1) {
			refundInfo.setReinAuditStatus(Byte.valueOf("2"));
			refundInfo.setReinAuditReason(clientAppeal.getClapReplyContent());
		} else if ( status == 2) {
			refundInfo.setReinAuditStatus(Byte.valueOf("1"));
			refundInfo.setReinReason(clientAppeal.getClapReplyContent());
			refundInfo.setReinStatus(Byte.valueOf("0"));
		}
		refundInfo.setReinMoney(temp.getClapMoney());
		 int saveRow=refundInformationDao.save(refundInfo);
		 return updateRow+saveRow>=2;
	}
	
	public boolean confirmRefund(Integer reinId) {
		RefundInformation refundInfo=new RefundInformation();
		refundInfo.setReinId(reinId);
		refundInfo.setReinStatus(Byte.valueOf("1"));
		int rows=refundInformationDao.dynamicUpdate(refundInfo);
		return rows>0;
	}
}
