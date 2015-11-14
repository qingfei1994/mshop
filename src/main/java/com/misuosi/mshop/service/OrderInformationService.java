/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.builder.SimpleSqlBuilder;
import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.db.dao.TreeDao;
import com.misuosi.mshop.entity.OrderInformation;
import com.misuosi.mshop.util.DateUtils;
import com.misuosi.mshop.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description	 : service类 OrderInformationService
 * <br><br>Time	 : 2015/05/06 02:39
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class OrderInformationService {

	@Resource(name = "orderInformationDao")
	private Dao<OrderInformation> orderInformationDao;

	public int addOrderInformation(OrderInformation orderInformation) {
		int rows = orderInformationDao.save(orderInformation);
		return rows;
	}

	public int updateOrderInformation(OrderInformation orderInformation) {
		int rows = orderInformationDao.dynamicUpdate(orderInformation);
		return rows;
	}

	public int deleteOrderInformation(Integer id) {
		int rows = orderInformationDao.delete(id);
		return rows;
	}

	public int batchDeleteOrderInformations(Integer[] ids) {
		int rows = orderInformationDao.batchDelete(ids);
		return rows;
	}

	public OrderInformation getOrderInformation(Integer id) {
		OrderInformation orderInformation = orderInformationDao.get(id);
		return orderInformation;
	}

	public OrderInformation getTreeOrderInformation(Integer id) {
		String sql = "SELECT * FROM order_information WHERE orin_id=?";
		List<OrderInformation> orderInformations = ((TreeDao) orderInformationDao).queryForTree(sql, id);
		OrderInformation orderInformation = null;
		if (orderInformations != null && !orderInformations.isEmpty()) {
			orderInformation = orderInformations.get(0);
		}
		return orderInformation;
	}

	public OrderInformation getOrderInformationByOrinNo(String orinNo) {
		OrderInformation orderInformation = orderInformationDao.findUniqueByProperty("orin_no", orinNo);
		return orderInformation;
	}

	public OrderInformation getTreeOrderInformationByOrinNo(String orinNo) {
		String sql = "SELECT * FROM order_information WHERE orin_no=?";
		List<OrderInformation> orderInformations = ((TreeDao) orderInformationDao).queryForTree(sql, orinNo);
		OrderInformation orderInformation = null;
		if (orderInformations != null && !orderInformations.isEmpty()) {
			orderInformation = orderInformations.get(0);
		}
		return orderInformation;
	}

	public List<OrderInformation> getAllOrderInformations() {
		List<OrderInformation> list = orderInformationDao.findAll();
		return list;
	}

	private String orderNoPrefix = "5102";
	private String typeNo = "23";

	/**
	 * 生成订单编号
	 * 编号规则：4位前缀+yyyyMMdd+2位类型编码+4位随机数
	 * 必须保证订单号的唯一性
	 *
	 * @return
	 */
	public synchronized String genOrderNo() {
		String orderNo = orderNoPrefix.concat(DateUtils.getDate("yyyyMMdd"))
				.concat(typeNo).concat(StringUtils.createNonceNum(4));
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM order_information ");
		sql.append("WHERE orin_no=?");
		OrderInformation orderInformation = orderInformationDao.findUnique(sql.toString(), orderNo);
		while (orderInformation != null) {
			orderNo = orderNoPrefix.concat(DateUtils.getDate("yyyyMMdd"))
					.concat(typeNo).concat(StringUtils.createNonceNum(4));
			orderInformation = orderInformationDao.findUnique(sql.toString(), orderNo);
		}
		return orderNo;
	}

	public List<OrderInformation> getOrderInformationTreeByWeinId(Integer weinId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM order_information ");
		sql.append("WHERE wein_id=? ");
		sql.append("ORDER BY orin_id DESC ");
		List<OrderInformation> list = ((TreeDao) orderInformationDao).queryForTree(sql.toString(), weinId);
		return list;
	}

	public OrderInformation getOrderInformationTree(Integer orinId) {
		String sql = SimpleSqlBuilder.getQuerySql(OrderInformation.class, orinId);
		List<OrderInformation> list = ((TreeDao) orderInformationDao).queryForTree(sql);
		OrderInformation orderInformation = null;
		if (list != null && !list.isEmpty()) {
			orderInformation = list.get(0);
		}
		return orderInformation;
	}

}

