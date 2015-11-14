/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.MemberInformation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description	 : service类 MemberInformationService
 * <br><br>Time	 : 2015/05/06 02:39
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class MemberInformationService {

	@Resource(name = "memberInformationDao")
	private Dao<MemberInformation> memberInformationDao;

	public int addMemberInformation(MemberInformation memberInformation) {
		int rows = memberInformationDao.save(memberInformation);
		return rows;
	}

	public int updateMemberInformation(MemberInformation memberInformation) {
		int rows = memberInformationDao.dynamicUpdate(memberInformation);
		return rows;
	}

	public int deleteMemberInformation(Integer id) {
		int rows = memberInformationDao.delete(id);
		return rows;
	}

	public int batchDeleteMemberInformations(Integer[] ids) {
		int rows = memberInformationDao.batchDelete(ids);
		return rows;
	}

	public MemberInformation getMemberInformation(Integer id) {
		MemberInformation memberInformation = memberInformationDao.get(id);
		return memberInformation;
	}

	public List<MemberInformation> getAllMemberInformations() {
		List<MemberInformation> list = memberInformationDao.findAll();
		return list;
	}

	/**
	 * 通过微信用户ID获得会员信息  add by YLM
	 * @param weinId
	 * @return
	 */
	public MemberInformation getMemberInformationByWeinId(int weinId) {
		MemberInformation memberInformation = memberInformationDao.findUniqueByProperty("wein_id", weinId);
		return memberInformation;
	}
	
	public List<MemberInformation> getMemberInformations() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT mein_id,mein_name,mein_phone,mein_integral,mein_balance,mein_create_time ");
		sb.append("FROM member_information ");
		sb.append("INNER JOIN wechat_information ");
		sb.append("ON member_information.wein_id = wechat_information.wein_id ");
		String sql = sb.toString();
		List<MemberInformation> list = memberInformationDao.find(sql);
		return list;
	}

}

