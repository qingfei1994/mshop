/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.db;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.db.dao.DaoFactory;

import java.io.Serializable;

/**
 * Description		: 实体基类
 * <p/>
 * <br><br>Time		: 2015/4/8 8:39
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public abstract class Entity implements Serializable {

    protected <T extends Entity> Dao<T> getDao(Class<T> clazz) {
        return DaoFactory.getDao(clazz);
    }

}
