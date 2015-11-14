/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.db;

import com.misuosi.mshop.db.annotation.ManyToOne;
import com.misuosi.mshop.db.annotation.OneToMany;
import com.misuosi.mshop.db.annotation.OneToOne;
import com.misuosi.mshop.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Description		: 树形结构的联结器
 * <p/>
 * <br><br>Time		: 2015/6/1 9:44
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public class TreeCoupler<T extends Entity> {

    private static final Logger log = LoggerFactory.getLogger(TreeCoupler.class);

    private Coupler<T> coupler;
    private Map<String, Coupler<? extends Entity>> childCouplers;

    public TreeCoupler(Class<T> clazz) {
        String className = clazz.getName();
        int index = className.lastIndexOf(".");
        String classPath = className.substring(0, index + 1);

        coupler = CouplerFactory.getCoupler(clazz);
        childCouplers = new HashMap<String, Coupler<?>>();

        Map<String, OneToOne> oneToOneMap = coupler.getOneToOneMap();
        for (String fieldName : oneToOneMap.keySet()) {
            OneToOne oneToOne = oneToOneMap.get(fieldName);
            String appointClassPath = oneToOne.classPath();
            String table = oneToOne.table();
            setChildCoupler(classPath, appointClassPath, table, fieldName);
        }

        Map<String, OneToMany> oneToManyMap = coupler.getOneToManyMap();
        for (String fieldName : oneToManyMap.keySet()) {
            OneToMany oneToMany = oneToManyMap.get(fieldName);
            String appointClassPath = oneToMany.classPath();
            String table = oneToMany.table();
            setChildCoupler(classPath, appointClassPath, table, fieldName);
        }

        Map<String, ManyToOne> manyToOneMap = coupler.getManyToOneMap();
        for (String fieldName : manyToOneMap.keySet()) {
            ManyToOne manyToOne = manyToOneMap.get(fieldName);
            String appointClassPath = manyToOne.classPath();
            String table = manyToOne.table();
            setChildCoupler(classPath, appointClassPath, table, fieldName);
        }
    }

    /**
     * 设置子查询的coupler
     *
     * @param classPath
     * @param appointClassPath
     * @param table
     * @param fieldName
     */
    private void setChildCoupler(String classPath, String appointClassPath, String table,
                String fieldName) {
        Class<Entity> cla = null;
        try {
            if (StringUtils.isBlank(appointClassPath)) {
                cla = (Class<Entity>) Class.forName(classPath.concat(table));
            } else {
                cla = (Class<Entity>) Class.forName(appointClassPath.concat(".").concat(table));
            }
        } catch (ClassNotFoundException e) {
            log.error("TreeCoupler.TreeCoupler throws ClassNotFoundException", e);
        }
        childCouplers.put(fieldName, CouplerFactory.getCoupler(cla));
    }

    public Coupler<T> getCoupler() {
        return coupler;
    }

    public void setCoupler(Coupler<T> coupler) {
        this.coupler = coupler;
    }

    public Map<String, Coupler<? extends Entity>> getChildCouplers() {
        return childCouplers;
    }

    public void setChildCouplers(Map<String, Coupler<? extends Entity>> childCouplers) {
        this.childCouplers = childCouplers;
    }
    
}
