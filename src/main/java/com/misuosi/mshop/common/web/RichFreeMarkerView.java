/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.common.web;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Description		: WEB页面公用属性可在此部署
 * <p/>
 * <br><br>Time		: 2014/6/15 10:12
 *
 * @author ICE
 * @version 1.0
 * @since 1.0
 */
public class RichFreeMarkerView extends FreeMarkerView {

	/** 部署路径属性名称 */
	public static final String CONTEXT_PATH = "base";

	/**
	 * 在model中增加部署路径base，方便处理部署路径问题
	 *
	 * @param model
	 * @param request
	 * @throws Exception
	 */
	@Override
	protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request)
			throws Exception {
		super.exposeHelpers(model, request);
		model.put(CONTEXT_PATH, "http://" + request.getServerName() + ":"
				+ request.getServerPort() + request.getContextPath());
	}

}