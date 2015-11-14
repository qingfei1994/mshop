/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.db.event;

/**
 * Description		: 数据存取事件监听适配器
 * <p/>
 * <br><br>Time		: 2015/4/17 15:31
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public abstract class DataAccessAdapter implements DataAccessListener {

    @Override
    public void beforeSave(DataAccessEvent event) {
    }

    @Override
    public void afterSave(DataAccessEvent event) {
    }

    @Override
    public void beforeUpdate(DataAccessEvent event) {
    }

    @Override
    public void afterUpdate(DataAccessEvent event) {
    }

    @Override
    public void beforeDelete(DataAccessEvent event) {
    }

    @Override
    public void afterDelete(DataAccessEvent event) {
    }

}
