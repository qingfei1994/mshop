/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.db.dao;

import com.misuosi.mshop.db.page.Pagination;

import java.util.List;

/**
 * Description		: 数据访问对象抽象接口
 * <p/>
 * <br><br>Time		: 2015/4/8 8:35
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public interface Dao<T> {

    public int save(T entity);

    public int saveWithId(T entity);

    public int update(T entity);

    public int dynamicUpdate(T entity);

    public int delete(Integer id);

    public T get(Integer id);

    public T findUniqueByProperty(String property, Object value);

    public T findUnique(String sql, Object... values);

    public List<T> findByProperty(String property, Object value);

    public List<T> find(String sql, Object... values);

    public List<T> findAll();

    public Pagination<T> findPagination(int pageNo, int pageSize);

    public Pagination<T> findPagination(String sql, int pageNo, int pageSize, Object... values);

    public int count(String sql, Object... values);

    public int batchSave(List<T> entities);

    public int batchSaveWithId(List<T> entities);

    public int batchSave(String sql, Object... values);

    public int batchUpdate(List<T> entities);

    public int batchDynamicUpdate(List<T> entities);

    public int batchUpdate(String property, Object value, Integer[] ids);

    public int batchUpdate(String sql, Object... values);

    public int batchDelete(Integer[] ids);

    public int batchDelete(String sql, Object... values);

}
