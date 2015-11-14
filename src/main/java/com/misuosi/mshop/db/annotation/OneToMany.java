/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.db.annotation;

import java.lang.annotation.*;

/**
 * Description		: 用于指定数据表的一对多关联关系（指定后可以进行级联操作）
 * <p/>
 * <br><br>Time		: 2015/8/12 10:38
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OneToMany {

    /**
     * 指定关联实体的类路径（eg: com.misuosi.xxx.entity）
     *
     * @return
     */
    String classPath() default "";

    /**
     * 指定关联的数据表（写实体名）
     *
     * @return
     */
    String table() default "";

    /**
     * 指定关联的外键（写属性名）
     *
     * @return
     */
    String foreignKey() default "";

}
