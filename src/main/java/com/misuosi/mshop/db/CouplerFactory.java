/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.db;

import java.util.HashMap;
import java.util.Map;

/**
 * Description		: 联结器工厂
 * <p/>
 * <br><br>Time		: 2015/4/13 9:00
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public class CouplerFactory {

    private static Map<String, Coupler<?>> couplerMap = new HashMap<String, Coupler<?>>();
    private static Map<String, TreeCoupler<?>> treeCouplerMap = new HashMap<String, TreeCoupler<?>>();

    /**
     * 获取联结器
     *
     * @param clazz
     * @return
     */
    public static <T extends com.misuosi.mshop.db.Entity> Coupler<T> getCoupler(Class<T> clazz) {
        String className = clazz.getName();
        Coupler<T> coupler = (Coupler<T>) couplerMap.get(className);
        if (coupler == null) {
            coupler = new Coupler<T>(clazz);
            couplerMap.put(className, coupler);
        }
        return coupler;
    }

    /**
     * 获取树形结构的联结器
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T extends Entity> TreeCoupler<T> getTreeCoupler(Class<T> clazz) {
        String className = clazz.getName();
        TreeCoupler<T> treeCoupler = (TreeCoupler<T>) treeCouplerMap.get(className);
        if (treeCoupler == null) {
            treeCoupler = new TreeCoupler<T>(clazz);
            treeCouplerMap.put(className, treeCoupler);
        }
        return treeCoupler;
    }

}
