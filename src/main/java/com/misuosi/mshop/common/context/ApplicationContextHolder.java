/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.common.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Description		: spring上下文工具
 * 
 * <br><br>Time		: 2014-7-9  下午9:29:03
 * 
 * @version 1.0
 * 
 * @since 1.0
 * 
 * @author ICE
 */
public class ApplicationContextHolder implements ApplicationContextAware {
	
	private static ApplicationContext applicationContext;
	
    /**
     * 向ApplicationContextHolder里设置ApplicationContext.
     * 
     * @param applicationContext ApplicationContext
     */
    @Override
	public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
    	this.applicationContext = applicationContext;
    }

    /**
     * 获得ApplicationContext.
     * 
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 根据class获得bean.
     * 
     * @param clz Class
     * @return T
     */
    public static <T> T getBean(Class<T> clz) {
        return applicationContext.getBean(clz);
    }

    /**
     * 根据id获得bean.
     * 
     * @param id String
     * @return T
     */
	public static <T> T getBean(String id) {
        return (T) applicationContext.getBean(id);
    }

}
