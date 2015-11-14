/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;

import java.io.*;
import java.util.*;

/**
 * Description		: 文件上传、下载工具类.
 * <p/>
 * <br><br>Time		: 2014-7-12  下午12:01:21
 *
 * @author HONG
 * @version 1.0
 * @since 1.0
 */
public class FileUploadUtil {

    private static Logger log = LoggerFactory.getLogger(FileUploadUtil.class);

    /**
     * 文件上传
     *
     * @param request 请求的request
     * @param fileUrl 文档路径
     * @return 是否成功
     */
    public static boolean saveFile(HttpServletRequest request, String fileUrl) {
        if (StringUtils.isEmpty(fileUrl)) {
            return false;
        }

        CommonsMultipartResolver multipartResolver =
                new CommonsMultipartResolver(request.getSession().getServletContext()); // 创建一个通用的多部分解析器.
        if (multipartResolver.isMultipart(request)) { // 判断request是否有文件上传
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request; // 转换成多部分request
            Iterator<String> ite = multiRequest.getFileNames();    //获得上传文件名称

            if (ite.hasNext()) {
                MultipartFile file = multiRequest.getFile(ite.next()); // 根据 name 获取上传的文件

                if (file != null) {
                    String filePath = request.getSession().getServletContext().getRealPath(fileUrl);
                    File localFile = new File(filePath.toString());  // 上传后记录的文件
                    try {
                        file.transferTo(localFile); // 将上传文件写到服务器上指定的文件
                        return true;
                    } catch (IllegalStateException e) {
                        log.error("文件上传失败", e);
                    } catch (IOException e) {
                        log.error("文件上传失败", e);
                    }
                }
            }
        }
        return false;
    }

    /**
     * 验证文件格式
     *
     * @param fileName     需要验证的文件名
     * @param allowFileFmt 允许的文件格式
     * @return
     */
    public static boolean checkFileFormat(String fileName, String allowFileFmt[]) {
        String extendFileName = fileName.substring(fileName.lastIndexOf(".") + 1);
        for (String s : allowFileFmt) {
            if (s.equals(extendFileName)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 删除文件
     *
     * @param dirPath 文件路径
     * @return
     */
    public static int deleteFile(String dirPath) {
        File file = new File(dirPath);
        if (file.exists() && file.isFile()) {
            try {
                file.delete();
                return 1;
            } catch (Exception e) {
                return 0;
            }
        }
        return 0;
    }

}
