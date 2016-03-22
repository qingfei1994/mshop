/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.common.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.misuosi.mshop.util.FileUploadUtil;
import com.misuosi.mshop.util.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.misuosi.mshop.module.common.service.UploadService;
import com.misuosi.mshop.common.constants.FileUploadConstants;
import com.misuosi.mshop.util.CompressPic;

/**
 * Description : 后台文件上传接口
 * <p/>
 * <br>
 * <br>
 * Time : 2015/5/15 15:26
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/common/upload")
public class UploadController {
	private static Logger log = LoggerFactory.getLogger(UploadController.class);

	private static final String COMPRESS_SUCCESS = "ok";

	@Autowired
	private UploadService uploadService;

	/**
	 * 图片上传接口
	 * 
	 * @param request
	 * @param width
	 *            图片的宽度
	 * @param height
	 *            图片的高度
	 * @return
	 */
	@RequestMapping("/image")
	@ResponseBody
	public JSONObject uploadImage(HttpServletRequest request,
			Integer width, Integer height) {
		JSONObject jsonObject = new JSONObject();
		String filePath = uploadService.upload(FileUploadConstants.IMAGE_TYPE,
				request);
		if (!StringUtils.isBlank(filePath)) {
			jsonObject.put("success", "1");
			jsonObject.put("src", FileUploadConstants.DOWNLOAD_PATH + filePath.replace("/", "_").replace(".", "_"));
			jsonObject.put("filePath", filePath);

			// 图片压缩
			if (width != null && height != null) {
				String fileName = filePath
						.substring(filePath.lastIndexOf("/") + 1);
				/*String fileDir = request
						.getSession()
						.getServletContext()
						.getRealPath(
								filePath.substring(0, filePath.lastIndexOf("/")));*/
				String fileDir = FileUploadConstants.ROOT_PATH.concat(filePath.substring(0, filePath.lastIndexOf("/")));
				CompressPic compressPic = new CompressPic();
				String result = compressPic.compressPic(fileDir, fileDir,
						fileName, fileName, width, height, false);
				if (result.equals(COMPRESS_SUCCESS)) {
					log.error("图片压缩成功");
				}
			}
		} else {
			jsonObject.put("error", "1");
		}
	
		return jsonObject;
	}
	@RequestMapping("/carouselimage")
	@ResponseBody
	public String uploadCarouselImage(HttpServletRequest request,
			Integer width, Integer height) {
		JSONObject jsonObject = new JSONObject(); 
		//jsonObject = new HashMap<String,Object>();
		String filePath = uploadService.upload(FileUploadConstants.IMAGE_TYPE,
				request);
		if (!StringUtils.isBlank(filePath)) {
			jsonObject.put("success", "1");
			jsonObject.put("src", FileUploadConstants.DOWNLOAD_PATH + filePath.replace("/", "_").replace(".", "_"));
			jsonObject.put("filePath", filePath);

			// 图片压缩
			if (width != null && height != null) {
				String fileName = filePath
						.substring(filePath.lastIndexOf("/") + 1);
				/*String fileDir = request
						.getSession()
						.getServletContext()
						.getRealPath(
								filePath.substring(0, filePath.lastIndexOf("/")));*/
				String fileDir = FileUploadConstants.ROOT_PATH.concat(filePath.substring(0, filePath.lastIndexOf("/")));
				CompressPic compressPic = new CompressPic();
				String result = compressPic.compressPic(fileDir, fileDir,
						fileName, fileName, width, height, false);
				if (result.equals(COMPRESS_SUCCESS)) {
					log.error("图片压缩成功");
				}
			}
		} else {
			jsonObject.put("error", "1");
		}
	
		return jsonObject.toJSONString();
	}
	/**
	 * 一般文件上传
	 * @param request
	 * @return
	 */
	@RequestMapping("/file")
	@ResponseBody
	public Map<String, Object> file(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String filePath = uploadService.upload(FileUploadConstants.FILE_TYPE,
				request);
		if (!StringUtils.isBlank(filePath)) {
			map.put("success", "1");
			map.put("src", filePath);
		} else {
			map.put("error", "1");
		}
		return map;
	}

	/**
	 * 文件删除
	 * @param request
	 * @param filepath 文件路径
	 * @return
	 */
	@RequestMapping(value = "/deleteFile")
	@ResponseBody
	public Map<String, Object> deleteFile(HttpServletRequest request,
			String filepath) {
		Map<String, Object> map = new HashMap<String, Object>();
		String dirFile = FileUploadConstants.ROOT_PATH + filepath;
		Integer result = FileUploadUtil.deleteFile(dirFile);
		if (result == 1) {
			map.put("Status", "success");
		} else {
			map.put("Status", "failure");
		}
		return map;
	}
}
