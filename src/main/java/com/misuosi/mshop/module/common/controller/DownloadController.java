/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.common.controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.misuosi.mshop.common.constants.FileUploadConstants;

/**
 * Description : 文件（图片）下载Controller
 * <p/>
 * <br>
 * <br>
 * Time : 2015年5月28日 下午4:15:01
 *
 * @author HONG
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping(value = "/common/download")
public class DownloadController {

	/**
	 * 以流的形式将图片数据打印到页面.
	 * 
	 * @param filePath
	 * @param response
	 * @throws java.io.IOException
	 */
	@RequestMapping(value = "/image/{filePath}")
	public void image(@PathVariable("filePath") String filePath,
			HttpServletResponse response) {
		String newFilePath = filePath.replace("_", "/");
		String formatName = newFilePath
				.substring(newFilePath.lastIndexOf("/") + 1);
		String realPath = newFilePath
				.substring(0, newFilePath.lastIndexOf("/")) + "." + formatName;

		String dirFile = FileUploadConstants.ROOT_PATH + realPath;
		File file = new File(dirFile);

		InputStream fis = null;
		OutputStream sos = null;
		try {
			if (!file.exists() || !file.isFile()) {
				BufferedImage img = new BufferedImage(640, 640, BufferedImage.TYPE_INT_RGB);
				Graphics g = img.getGraphics();
				g.setColor(new Color(221, 221, 221));
				g.fillRect(0, 0, 640, 640); // 暂无图片
				ByteArrayOutputStream bs = new ByteArrayOutputStream();
				ImageOutputStream imOut = ImageIO.createImageOutputStream(bs);
				ImageIO.write(img, "jpg", imOut);
				fis = new ByteArrayInputStream(bs.toByteArray());
			} else {
				fis = new FileInputStream(file);
			}
			sos = response.getOutputStream();
			ImageIO.write(ImageIO.read(fis), formatName, sos);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				sos.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 文件下载
	 * 
	 * @param filepath 文件路径
	 * @param newFileName 文件名
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/file/{filepath}")
	public void file(@PathVariable("filepath") String filepath, String newFileName, HttpServletRequest request,
			HttpServletResponse response){
		
	}
}
