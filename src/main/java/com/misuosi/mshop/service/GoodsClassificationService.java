/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.misuosi.mshop.module.common.manager.GoodsClassificationManager;
import org.springframework.stereotype.Service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.GoodsClassification;

/**
 * Description : service类 GoodsClassificationService <br>
 * <br>
 * Time : 2015/05/06 02:39
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class GoodsClassificationService {

    @Resource(name = "goodsClassificationDao")
    private Dao<GoodsClassification> goodsClassificationDao;

    public int addGoodsClassification(GoodsClassification goodsClassification) {
        int rows;
        Integer goclSort;
        //如果添加的是子分组
        if (goodsClassification.getGoclParentId() != null) {
            //获取最新子分组的排序
            goclSort = updateSubGoodsClassificationSort(goodsClassification.getGoclParentId());

        } else {//如果添加的是父分组
            //获取最新父分组排序
            goclSort = updateGoodsClassificationSort();
        }
        goodsClassification.setGoclSort(goclSort.byteValue());
        rows = goodsClassificationDao.save(goodsClassification);

        GoodsClassificationManager.refresh();
        return rows;
    }

    public int updateGoodsClassification(GoodsClassification goodsClassification) {
        int rows = goodsClassificationDao.update(goodsClassification);
        GoodsClassificationManager.refresh();
        return rows;
    }

    public int updateGoodsClassificationDynamically(GoodsClassification goodsClassification) {
        int rows = goodsClassificationDao.dynamicUpdate(goodsClassification);
        GoodsClassificationManager.refresh();
        return rows;
    }

    public int deleteGoodsClassification(Integer id) {
        int rows = goodsClassificationDao.delete(id);
        GoodsClassificationManager.refresh();
        return rows;
    }

    public int batchDeleteGoodsClassifications(Integer[] ids) {
        int rows = goodsClassificationDao.batchDelete(ids);
        GoodsClassificationManager.refresh();
        return rows;
    }

    public GoodsClassification getGoodsClassification(Integer id) {
        GoodsClassification goodsClassification = goodsClassificationDao
                .get(id);
        return goodsClassification;
    }

    public List<GoodsClassification> getAllGoodsClassifications() {
        List<GoodsClassification> list = goodsClassificationDao.findAll();
        return list;
    }

    public List<GoodsClassification> getGoodsClassificationsByGoodId(Integer goodId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT gocl.* FROM goods_classification AS gocl ");
        sql.append("INNER JOIN goods_classification_goods AS gcgo ");
        sql.append("ON gocl.gocl_id=gcgo.gocl_id ");
        sql.append("WHERE gcgo.good_id=?");
        List<GoodsClassification> list = goodsClassificationDao.find(sql.toString(), goodId);
        return list;
    }

    /**
     * 根据供货商ID获取所有的商品分组列表
     *
     * @return 商品分组列表
     */
    public List<Map<String, Object>> getAllGoodsClassfications() {
        //获取所有的父分组
        List<GoodsClassification> goodsClassifications = getAllParentGoodsClassification();
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;
        //逐个将父分组中的子分组放入父分组中
        for (GoodsClassification goods : goodsClassifications) {
            map = new HashMap<String, Object>();
            List<GoodsClassification> children = getSubGoodsClassificationByParentId(goods
                    .getGoclId());
            map.put("goclName", goods.getGoclName());
            map.put("goclId", goods.getGoclId());
            map.put("goclIconUrl", goods.getGoclIconUrl());
            if (children.size() != 0) {
                map.put("children", children);
            }
            result.add(map);
        }
        return result;
    }

    /**
     * 动态删除分组 若删除分组为父分组，则先删除其子分组
     *
     * @param goclId 分组ID
     */
    public void deleteGoodsClassificationDynamically(Integer goclId) {
        GoodsClassification goodsClassification = getGoodsClassification(goclId);
        // 如果要删除的分组是父分组
        if (goodsClassification.getGoclParentId() == null) {
            // 获取其所有的子分组
            List<GoodsClassification> children = goodsClassificationDao
                    .findByProperty("gocl_parent_id", goclId);
            if (!children.isEmpty()) {
                Integer[] childrenIds = new Integer[children.size()];
                // 获取所有的子分组的ID
                for (int i = 0; i < childrenIds.length; i++) {
                    childrenIds[i] = children.get(i).getGoclId();
                }
                // 批量删除子分组
                batchDeleteGoodsClassifications(childrenIds);
            }
        }
        // 删除对应分组
        deleteGoodsClassification(goclId);
    }

    /**
     * 根据父分组ID按照顺序获取所有子分组
     *
     * @param parentGoclId
     * @return
     */
    private List<GoodsClassification> getSubGoodsClassificationByParentId(
            int parentGoclId) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM goods_classification ");
        stringBuilder.append("WHERE gocl_parent_id=? ");
        stringBuilder.append("ORDER BY gocl_sort");
        List<GoodsClassification> children = goodsClassificationDao
                .find(stringBuilder.toString(), parentGoclId);
        return children;
    }

    /**
     * 获取所有父分组（即parent_id为空的）
     *
     * @return 分组集合
     */
    private List<GoodsClassification> getAllParentGoodsClassification() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM goods_classification WHERE gocl_parent_id IS NULL ");
        sql.append("ORDER BY gocl_sort");
        List<GoodsClassification> goodsClassifications =
                goodsClassificationDao.find(sql.toString());
        return goodsClassifications;

    }

    /**
     * 更新父分组下的子分组的排序
     *
     * @param goclId
     * @return 返回下一个编号
     */
    private Integer updateSubGoodsClassificationSort(int goclId) {
        List<GoodsClassification> children = getSubGoodsClassificationByParentId(goclId);
        Integer i;
        for (i = 0; i < children.size(); i++) {
            Integer temp = i + 1;
            children.get(i).setGoclSort(temp.byteValue());
            updateGoodsClassification(children.get(i));
        }
        return i + 1;
    }

    /**
     * 更新父分组的排序
     *
     * @return 返回下一个编号
     */
    private Integer updateGoodsClassificationSort() {
        List<GoodsClassification> parent = getAllParentGoodsClassification();
        Integer i;
        for (i = 0; i < parent.size(); i++) {
            Integer temp = i + 1;
            parent.get(i).setGoclSort(temp.byteValue());
            updateGoodsClassification(parent.get(i));
        }
        return i + 1;
    }


    /**
     * 添加子分组
     *
     * @param goodsClassification
     */
    public void addSubGoodsClassification(
            GoodsClassification goodsClassification) {
        Integer goclSort = updateSubGoodsClassificationSort(goodsClassification.getGoclParentId());
        goodsClassification.setGoclSort(goclSort.byteValue());
        addGoodsClassification(goodsClassification);
    }

    /**
     * 保存分组列表的拖拽结果
     *
     * @param classifications
     */
    public void updateSortedClassification(List<Map<String, Object>> classifications) {
        for (int i = 0; i < classifications.size(); i++) {
            Integer temp = i + 1;
            Map<String, Object> parent = classifications.get(i);
            Integer id = (Integer) parent.get("id");
            GoodsClassification parentGroup = getGoodsClassification(id);
            parentGroup.setGoclSort(temp.byteValue());
            parentGroup.setGoclParentId(null);
            updateGoodsClassification(parentGroup);
            List<Map<String, Object>> children = (List<Map<String, Object>>) parent.get("children");
            if (children != null) {
                for (int j = 0; j < children.size(); j++) {
                    Integer t = j + 1;
                    Map<String, Object> child = children.get(j);
                    GoodsClassification childGroup = getGoodsClassification((Integer) child
                            .get("id"));
                    childGroup.setGoclSort(t.byteValue());
                    childGroup.setGoclParentId(id);
                    updateGoodsClassification(childGroup);
                }
            }
        }
    }

}
