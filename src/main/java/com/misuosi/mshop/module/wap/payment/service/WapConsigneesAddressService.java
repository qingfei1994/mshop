/*
 * Copyright (c) 2015
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.module.wap.payment.service;

import com.misuosi.mshop.common.constants.UserCenterConstants;
import com.misuosi.mshop.common.context.SessionContextHolder;
import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.ConsigneesAddress;
import com.misuosi.mshop.util.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Description		: 
 * <p/>
 * <br><br>Time		: 2015-6-12 下午10:44:44
 *
 * @author YLM
 * @version 1.0
 * @since 1.0
 */
@Service
public class WapConsigneesAddressService {

	@Resource(name = "consigneesAddressDao")
	private Dao<ConsigneesAddress> consigneesAddressDao;

	/**
	 * 更新收货地址
	 * @param consigneesAddress
	 * @return
	 */
	public int updateConsigneesAddress(ConsigneesAddress consigneesAddress) {
		if(consigneesAddress.getCoadDefaultAddress() == null){
			consigneesAddress.setCoadDefaultAddress(UserCenterConstants.COAD_DEFAULT_ADDRESS_NO);
		} else {
			consigneesAddressDao.batchUpdate("update consignees_address set coad_default_address = 0 where wein_id = ?", SessionContextHolder.getWechatInformationId());
		}
		int rows = consigneesAddressDao.dynamicUpdate(consigneesAddress);
		return rows;
	}
	
	/**
	 * 新增收货地址
	 * @param consigneesAddress
	 * @return
	 */
	public int addConsigneesAddress(ConsigneesAddress consigneesAddress) {
		consigneesAddress.setCoadCreateTime(DateUtils.getCurrentTime());
		consigneesAddress.setWeinId(SessionContextHolder.getWechatInformationId());
		if(consigneesAddress.getCoadDefaultAddress() == null){
			consigneesAddress.setCoadDefaultAddress(UserCenterConstants.COAD_DEFAULT_ADDRESS_NO);
		} else {
			consigneesAddressDao.batchUpdate("update consignees_address set coad_default_address = 0 where wein_id = ?", consigneesAddress.getWeinId());
		}
		int rows = consigneesAddressDao.save(consigneesAddress);
		return rows;
	}
}

