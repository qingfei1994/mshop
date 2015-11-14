/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.sd4324530.fastweixin.api.response.GetUserInfoResponse;
import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.WechatInformation;


/**
 * Description	 : service类 WechatInformationService
 * <br><br>Time	 : 2015/05/06 02:39
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class WechatInformationService {

	@Resource(name = "wechatInformationDao")
	private Dao<WechatInformation> wechatInformationDao;

	public int addWechatInformation(WechatInformation wechatInformation) {
		int rows = wechatInformationDao.save(wechatInformation);
		return rows;
	}

	public int updateWechatInformation(WechatInformation wechatInformation) {
		int rows = wechatInformationDao.dynamicUpdate(wechatInformation);
		return rows;
	}

	public int deleteWechatInformation(Integer id) {
		int rows = wechatInformationDao.delete(id);
		return rows;
	}

	public int batchDeleteWechatInformations(Integer[] ids) {
		int rows = wechatInformationDao.batchDelete(ids);
		return rows;
	}

	public WechatInformation getWechatInformation(Integer id) {
		WechatInformation wechatInformation = wechatInformationDao.get(id);
		return wechatInformation;
	}

	public List<WechatInformation> getAllWechatInformations() {
		List<WechatInformation> list = wechatInformationDao.findAll();
		return list;
	}
	
	public WechatInformation getWechatInfoByOpenId(String openId) {
        WechatInformation wechatInformation = wechatInformationDao.findUniqueByProperty("wein_openid", openId);
        return wechatInformation;
    }
	
	/**
     * 保存用户信息
     * @param getUserInfoResponse
     */
    public synchronized WechatInformation addWechatInformation(GetUserInfoResponse getUserInfoResponse) {
    	WechatInformation wechatInformation = new WechatInformation();
    	wechatInformation.setWeinCity(getUserInfoResponse.getCity());
    	wechatInformation.setWeinCountry(getUserInfoResponse.getCountry());
    	wechatInformation.setWeinHeadingimgurl(getUserInfoResponse.getHeadimgurl());
    	wechatInformation.setWeinLanguage(getUserInfoResponse.getLanguage());
    	wechatInformation.setWeinNickname(getUserInfoResponse.getNickname());
    	wechatInformation.setWeinOpenid(getUserInfoResponse.getOpenid());
    	wechatInformation.setWeinProvince(getUserInfoResponse.getProvince());
    	wechatInformation.setWeinSex(Byte.parseByte(getUserInfoResponse.getSex().toString()));
    	wechatInformation.setWeinUnionid(getUserInfoResponse.getUnionid());
    	
        wechatInformation.setWeinCreateTime(new Timestamp(new Date().getTime()));

        wechatInformationDao.save(wechatInformation);
        return wechatInformation;
    }
	
}

