/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.db.dao;

import com.misuosi.mshop.db.page.Pagination;

import java.util.List;
import java.util.Map;

/**
 * Description		: 多表查询数据访问对象
 * <p/>
 * <br><br>Time		: 2015/5/3 20:54
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public interface MultiTableDao {

    public Map<String, Object> queryForMap(String sql, Object... values);

    public List<Map<String, Object>> queryForList(String sql, Object... values);

    public Pagination<Map<String, Object>> queryForPagination(String sql, int pageNo, int pageSize, Object... values);

}
