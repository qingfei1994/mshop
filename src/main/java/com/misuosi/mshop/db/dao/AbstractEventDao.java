/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.db.dao;

import com.misuosi.mshop.db.Entity;
import com.misuosi.mshop.db.event.DataAccessEvent;
import com.misuosi.mshop.db.event.DataAccessListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Description		: 可添加事件监听器的数据访问对象
 * <p/>
 * <br><br>Time		: 2015/4/17 15:37
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public abstract class AbstractEventDao<T extends Entity> implements com.misuosi.mshop.db.dao.Dao<T> {

    private List<DataAccessListener> listeners = new ArrayList<DataAccessListener>();

    protected abstract int saveWithEvent(T entity);
    protected abstract int saveWithIdWithEvent(T entity);
    protected abstract int updateWithEvent(T entity);
    protected abstract int dynamicUpdateWithEvent(T entity);
    protected abstract int deleteWithEvent(Integer id);

    @Override
    public int save(T entity) {
        DataAccessEvent event = new DataAccessEvent();
        event.setEntity(entity);
        for (DataAccessListener listener : listeners) {
            listener.beforeSave(event);
        }
        int rows = this.saveWithEvent(entity);
        for (DataAccessListener listener : listeners) {
            listener.afterSave(event);
        }
        return rows;
    }

    @Override
    public int saveWithId(T entity) {
        DataAccessEvent event = new DataAccessEvent();
        event.setEntity(entity);
        for (DataAccessListener listener : listeners) {
            listener.beforeSave(event);
        }
        int rows = this.saveWithIdWithEvent(entity);
        for (DataAccessListener listener : listeners) {
            listener.afterSave(event);
        }
        return rows;
    }

    @Override
    public int update(T entity) {
        DataAccessEvent event = new DataAccessEvent();
        event.setEntity(entity);
        for (DataAccessListener listener : listeners) {
            listener.beforeUpdate(event);
        }
        int rows = this.updateWithEvent(entity);
        for (DataAccessListener listener : listeners) {
            listener.afterUpdate(event);
        }
        return rows;
    }

    @Override
    public int dynamicUpdate(T entity) {
        DataAccessEvent event = new DataAccessEvent();
        event.setEntity(entity);
        for (DataAccessListener listener : listeners) {
            listener.beforeUpdate(event);
        }
        int rows = this.dynamicUpdateWithEvent(entity);
        for (DataAccessListener listener : listeners) {
            listener.afterUpdate(event);
        }
        return rows;
    }

    @Override
    public int delete(Integer id) {
        DataAccessEvent event = new DataAccessEvent();
        event.setId(id);
        for (DataAccessListener listener : listeners) {
            listener.beforeDelete(event);
        }
        int rows = this.deleteWithEvent(id);
        for (DataAccessListener listener : listeners) {
            listener.afterDelete(event);
        }
        return rows;
    }

    /**
     * 添加数据访问监听器
     *
     * @param listener
     */
    public void addDataAccessListener(DataAccessListener listener) {
        listeners.add(listener);
    }

}
