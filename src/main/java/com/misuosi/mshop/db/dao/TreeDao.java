/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.db.dao;

import com.misuosi.mshop.db.Entity;
import com.misuosi.mshop.db.page.Pagination;

import java.util.List;

/**
 * Description		: 树形结构数据访问对象
 * <p/>
 * <br><br>Time		: 2015/6/1 8:53
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public interface TreeDao<T extends Entity> {

    public List<T> queryForTree();

    public List<T> queryForTree(String sql, Object... values);

    public Pagination<T> queryForPaginationTree(String sql, int pageNo, int pageSize, Object... values);

}
