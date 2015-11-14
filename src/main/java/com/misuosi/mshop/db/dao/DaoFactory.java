/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.db.dao;

import com.misuosi.mshop.common.context.ApplicationContextHolder;
import com.misuosi.mshop.db.Entity;
import com.misuosi.mshop.db.query.QueryDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Description		: 数据访问对象工厂
 * <p/>
 * <br><br>Time		: 2015/4/7 11:49
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public class DaoFactory {

    private static final Logger log = LoggerFactory.getLogger(DaoFactory.class);

    private static final String DEFAULT_PACKAGE_PATH = null;
    private static final String DEFAULT_PACKAGE_NAME = "dao";
    private static final String DEFAULT_DAO_SUFFIX = "Dao";

    private static Map<String, Dao<?>> daoMap = new HashMap<String, Dao<?>>();
    private static Map<String, TreeDao<?>> treeDaoMap = new HashMap<String, TreeDao<?>>();

    /**
     * 获取数据访问对象
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T extends Entity> Dao<T> getDao(Class<T> clazz) {
        String className = clazz.getName();
        Dao<T> dao = (Dao<T>) daoMap.get(className);
        if (dao == null) {
            String classSimpleName = clazz.getSimpleName();
            String daoName = classSimpleName.concat(DEFAULT_DAO_SUFFIX);
            String daoPath = null;
            if (DEFAULT_PACKAGE_PATH != null) {
                daoPath = DEFAULT_PACKAGE_PATH.concat(".").concat(daoName);
            } else if (DEFAULT_PACKAGE_NAME != null) {
                String classPath = className.substring(0, className.lastIndexOf("."));
                classPath = classPath.substring(0, classPath.lastIndexOf("."));
                daoPath = classPath.concat(".").concat(DEFAULT_PACKAGE_NAME).concat(".")
                        .concat(daoName);
            }
            if (daoPath != null) {
                try {
                    Class<?> daoClass = Class.forName(daoPath);
                    dao = (Dao<T>) daoClass.newInstance();
                } catch (ClassNotFoundException e) {
                    //log.debug("DaoFactory.getDao throws ClassNotFoundException: {}", daoPath, e);
                    log.debug("DaoFactory.getDao throws ClassNotFoundException: {}", daoPath);
                } catch (InstantiationException e) {
                    log.error("DaoFactory.getDao throws InstantiationException", e);
                } catch (IllegalAccessException e) {
                    log.error("DaoFactory.getDao throws IllegalAccessException", e);
                }
            }
            if (dao == null) {
                dao = new QueryDao<T>(clazz);
            }
            ((SimpleDao) dao).setJdbcTemplate(ApplicationContextHolder.<JdbcTemplate>getBean("jdbcTemplate"));
            daoMap.put(className, dao);
        }
        return dao;
    }

    /**
     * 获取树形结构数据访问对象
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T extends Entity> TreeDao<T> getTreeDao(Class<T> clazz) {
        String className = clazz.getName();
        TreeDao<T> dao = (TreeDao<T>) treeDaoMap.get(className);
        if (dao == null) {
            dao = new SimpleTreeDao<T>(clazz);
            treeDaoMap.put(className, dao);
        }
        return dao;
    }

}
