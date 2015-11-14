/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.db.annotation;

import java.lang.annotation.*;

/**
 * Description		: 用于标识数据表的主键
 * <p/>
 * <br><br>Time		: 2015/4/13 16:43
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Id {
}
