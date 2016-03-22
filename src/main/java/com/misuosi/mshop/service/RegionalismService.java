/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.Regionalism;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description	 : service类 RegionalismService
 *
 * <br><br>Time	 : 2015/05/06 02:39
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */
@Service
public class RegionalismService {

    private static final Logger log = LoggerFactory.getLogger(RegionalismService.class);

	@Resource(name = "regionalismDao")
	private Dao<Regionalism> regionalismDao;
	
	/** 地区ID与其父ID的Map，key为子地区ID，value为父地区ID */
	private static Map<Integer, Integer> regionalismMap =new HashMap<Integer, Integer>();
	
	/**
	 * 初始化地区ID map.
	 */
	@PostConstruct
	public void init() {
		List<Regionalism> RegionalismList = getAllRegionalisms();
		for(Regionalism regionalism : RegionalismList) {
			regionalismMap.put(regionalism.getRegiId(), regionalism.getRegiParentId());
		}
		log.debug("初始化地区ID Map完成");
	}
	
	/**
	 * 通过子地区的ID获得父地区的ID
	 * @param regiId 子地区的ID
	 * @return 父地区的ID
	 */
	public static int getParentId(int regiId) {
		Integer parentId = regionalismMap.get(regiId);
		return parentId == null ? 0 : parentId;
	}
	/**
	 * 通过地区id字符串获取Regionalism的集合
	 * @param regiIdStr
	 * @return
	 */
	public List<Regionalism> getRegionalismsByRegiIds(Integer[] regiIds) {
		List<Regionalism> regionalisms=null;
    	if(regiIds!=null) {
    		regionalisms=new ArrayList<Regionalism>();
    		for(Integer id:regiIds){
    			Regionalism temp=getRegionalism(id);
    			regionalisms.add(temp);
    		}
    	}
    	return regionalisms;
	}
	public int addRegionalism(Regionalism regionalism) {
		int rows = regionalismDao.save(regionalism);
		return rows;
	}

	public int updateRegionalism(Regionalism regionalism) {
		int rows = regionalismDao.dynamicUpdate(regionalism);
		return rows;
	}

	public int deleteRegionalism(Integer id) {
		int rows = regionalismDao.delete(id);
		return rows;
	}

	public int batchDeleteRegionalisms(Integer[] ids) {
		int rows = regionalismDao.batchDelete(ids);
		return rows;
	}

	public Regionalism getRegionalism(Integer id) {
		Regionalism regionalism = regionalismDao.get(id);
		return regionalism;
	}

	public List<Regionalism> getAllRegionalisms() {
		List<Regionalism> list = regionalismDao.findAll();
		return list;
	}
	
	/**
	 * @param regiGrade
	 * @return
	 */
	public List<Regionalism> getRegionalismByRegiGrade(int regiGrade) {
		String sql = "SELECT * FROM regionalism WHERE regi_grade = ?";
		List<Regionalism> regionalisms = regionalismDao.find(sql, regiGrade);
		return regionalisms;
	}

	/**
	 * @param regiId
	 * @return
	 */
	public List<Regionalism> getRegionalismByRegiParentId(Integer regiId) {
		String sql = "SELECT * FROM regionalism WHERE regi_parent_id = ?";
		List<Regionalism> regionalisms = regionalismDao.find(sql, regiId);
		return regionalisms;
	}

	/**
	 * 根据子集地区Id获取地区字符串
	 *
	 * @param regiId
	 * @return
	 */
	public String getRegionalismStr(Integer regiId) {
		String regionalismStr = "";
		while (regiId != null && regiId > 0) {
			Regionalism regionalism = regionalismDao.get(regiId);
			regionalismStr = regionalism.getRegiName().concat(" ").concat(regionalismStr);
			regiId = regionalism.getRegiParentId();
		}
		return regionalismStr;
	}

}

