/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.RefundInformation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description	 : service类 RefundInformationService
 * <br><br>Time	 : 2015/05/06 02:39
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class RefundInformationService {

	@Resource(name = "refundInformationDao")
	private Dao<RefundInformation> refundInformationDao;

	public int addRefundInformation(RefundInformation refundInformation) {
		int rows = refundInformationDao.save(refundInformation);
		return rows;
	}

	public int updateRefundInformation(RefundInformation refundInformation) {
		int rows = refundInformationDao.dynamicUpdate(refundInformation);
		return rows;
	}

	public int deleteRefundInformation(Integer id) {
		int rows = refundInformationDao.delete(id);
		return rows;
	}

	public int batchDeleteRefundInformations(Integer[] ids) {
		int rows = refundInformationDao.batchDelete(ids);
		return rows;
	}

	public RefundInformation getRefundInformation(Integer id) {
		RefundInformation refundInformation = refundInformationDao.get(id);
		return refundInformation;
	}

	public List<RefundInformation> getAllRefundInformations() {
		List<RefundInformation> list = refundInformationDao.findAll();
		return list;
	}
	public RefundInformation getRefundInformationByGoorId(Integer id) {
		RefundInformation refundInfo=refundInformationDao.findUniqueByProperty("goor_id", id);
		return refundInfo;
	}
}

