/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.db.event;

/**
 * Description		: 数据存取事件监听接口
 * <p/>
 * <br><br>Time		: 2015/4/17 15:20
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public interface DataAccessListener {

    public void beforeSave(DataAccessEvent event);

    public void afterSave(DataAccessEvent event);

    public void beforeUpdate(DataAccessEvent event);

    public void afterUpdate(DataAccessEvent event);

    public void beforeDelete(DataAccessEvent event);

    public void afterDelete(DataAccessEvent event);

}
