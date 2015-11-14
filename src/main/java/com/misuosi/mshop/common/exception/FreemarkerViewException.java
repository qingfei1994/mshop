/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.common.exception;

import freemarker.core.Environment;
import freemarker.template.TemplateException;

/**
 * Description		: Freemarker页面异常
 * 
 * 
 * <br><br>Time		: 2015-5-11  上午1:24:28
 * 
 * @version 1.0
 * 
 * @since 1.0
 * 
 * @author YLM
 */
public class FreemarkerViewException extends TemplateException {

	public FreemarkerViewException(String message, Environment env) {  
    	super(env);
    }  
  
}
