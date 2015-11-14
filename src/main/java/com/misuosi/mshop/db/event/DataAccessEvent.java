/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.db.event;

import com.misuosi.mshop.db.Entity;

/**
 * Description		: 数据存取事件
 * <p/>
 * <br><br>Time		: 2015/4/27 22:24
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public class DataAccessEvent {

    private Integer id;
    private Entity entity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

}
