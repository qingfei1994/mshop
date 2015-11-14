/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.db.annotation;

import java.lang.annotation.*;

/**
 * Description		: 用于指定数据表的排序字段和排序方式
 * <p/>
 * <br><br>Time		: 2015/6/2 9:27
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Order {

    public static final String ASC = "asc";
    public static final String DESC = "desc";

    /**
     * 指定数据表的排序字段（写属性名）
     *
     * @return
     */
    String key() default "";

    /**
     * 指定字段的排序方式
     *
     * @return
     */
    String order() default "";

}
