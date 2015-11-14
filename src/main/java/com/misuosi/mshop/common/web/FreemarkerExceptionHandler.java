package com.misuosi.mshop.common.web;

import java.io.Writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.misuosi.mshop.common.exception.FreemarkerViewException;

import freemarker.core.Environment;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

/**
 * Description		: freemarker页面上的异常控制，在sping-mvc.xml里面的freemarkerSettings里头配置
 * <p/>
 * <br><br>Time		: 2015-5-11  上午1:24:28
 *
 * @author YLM
 * @version 1.0
 * @since 1.0
 */
public class FreemarkerExceptionHandler implements TemplateExceptionHandler {
    private static final Logger log = LoggerFactory
            .getLogger(FreemarkerExceptionHandler.class);

    public void handleTemplateException(TemplateException te, Environment env,
            Writer out) throws TemplateException {
        log.warn("[Freemarker Error: " + te.getMessage() + "]");
        throw new FreemarkerViewException("freemarker error", env);
    }

}
