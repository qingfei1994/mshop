/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.wap.goods.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.db.dao.MultiTableDao;
import com.misuosi.mshop.db.page.Pagination;
import com.misuosi.mshop.entity.Goods;
import com.misuosi.mshop.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Description	 :
 * <br><br>Time	 : 2015/6/12 14:26
 *
 * @author ICE
 * @version 1.0
 * @since 1.0
 */
@Service
public class WapGoodsService {

    @Resource(name = "goodsDao")
    private Dao<Goods> goodsDao;

    public Pagination<Map<String, Object>> getGoodsList(
            Integer weinId,
            Integer goclId,
            String orderStr,
            String orderType,
            int pageNo,
            int pageSize) {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT");
        sb.append(" goods.good_id,");
        sb.append(" goods.good_name,");
        sb.append(" goods.good_price,");
        sb.append(" goods.good_first_picture,");
        sb.append(" goods_label.gola_name,");
        sb.append(" collect_goods.wein_id,");
        sb.append(" goods_price_stock.gpst_market_price,");
        sb.append(" goods_price_stock.gpst_sales_price");
        sb.append(" FROM goods");
        sb.append(" LEFT JOIN goods_label_goods");
        sb.append(" ON goods.good_id = goods_label_goods.good_id");
        sb.append(" LEFT JOIN goods_label");
        sb.append(" ON goods_label.gola_id = goods_label_goods.gola_id");
        sb.append(" INNER JOIN goods_classification_goods");
        sb.append(" ON goods_classification_goods.good_id = goods.good_id");
        sb.append(" INNER JOIN goods_classification");
        sb.append(" ON goods_classification.gocl_id = goods_classification_goods.gocl_id");
        sb.append(" LEFT JOIN collect_goods");
        sb.append(" ON collect_goods.good_id = goods.good_id AND collect_goods.wein_id = ?");
        sb.append(" INNER JOIN goods_goods_specification_value");
        sb.append(" ON goods_goods_specification_value.good_id = goods.good_id");
        sb.append(" INNER JOIN goods_price_stock");
        sb.append(" ON goods_price_stock.gpst_id = goods_goods_specification_value.gpst_id");
        sb.append(" WHERE (goods.good_putaway_type = 0");
        sb.append(" OR (goods.good_putaway_type = 2");
        sb.append(" AND goods.good_putaway_time < NOW()");
        sb.append(" AND goods.good_end_putaway_time > NOW()))");

        //分类
        if (goclId!=null && StringUtils.isNumeric(goclId.toString())) {
            sb.append(" AND goods_classification.gocl_id = ?");
        }
        sb.append(" GROUP BY goods.good_id");
        if (StringUtils.isNotBlank(orderStr)) {
            sb.append(" ORDER BY " + orderStr);
        }
        if (StringUtils.isNotBlank(orderType)) {
            if("ASC".equalsIgnoreCase(orderType)) {
                sb.append(" ASC");
            } else {
                sb.append(" DESC");
            }
        }

        Pagination<Map<String, Object>> goodsPagination;
        if (goclId!=null && StringUtils.isNumeric(goclId.toString())) {
            goodsPagination = ((MultiTableDao)goodsDao).queryForPagination(sb.toString(), pageNo, pageSize, weinId, goclId);
        } else {
            goodsPagination = ((MultiTableDao)goodsDao).queryForPagination(sb.toString(), pageNo, pageSize, weinId);
        }

        return goodsPagination;
    }

    public Map<String, Object> getGoodsDetail(int goodId, int weinId) {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT *");
        sb.append(" FROM goods");
        sb.append(" LEFT JOIN collect_goods");
        sb.append(" ON collect_goods.good_id = goods.good_id");
        sb.append(" AND collect_goods.wein_id = ?");
        sb.append(" INNER JOIN goods_goods_specification_value");
        sb.append(" ON goods_goods_specification_value.good_id = goods.good_id");
        sb.append(" INNER JOIN goods_price_stock");
        sb.append(" ON goods_price_stock.gpst_id = goods_goods_specification_value.gpst_id");
        sb.append(" WHERE goods.good_id = ?");

        Map<String, Object> result = ((MultiTableDao) goodsDao).queryForMap(sb.toString(), weinId, goodId);
        return result;
    }
}
