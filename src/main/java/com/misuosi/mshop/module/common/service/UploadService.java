/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.common.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.misuosi.mshop.common.constants.FileUploadConstants;
import com.misuosi.mshop.util.FileNameUtils;

import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * Description		: 文件上传service
 * <p/>
 * <br><br>Time		: 2015/5/15 15:37
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
@Service
public class UploadService {

    private static final Logger log = LoggerFactory.getLogger(UploadService.class);

    private String rootPath = "uploads".concat(File.separator);

    /**
     * 只支持单文件上传
     *
     * @param fileType
     * @param request
     * @return
     */
    public String upload(String fileType, HttpServletRequest request) {
        String filePath = rootPath;
        if (fileType.equals(FileUploadConstants.IMAGE_TYPE)) {
            filePath += "images".concat(File.separator);
        } else if (fileType.equals(FileUploadConstants.FILE_TYPE)) {
            filePath += "files".concat(File.separator);
        } else if (fileType.equals(FileUploadConstants.EXCEL_TYPE)) {
            filePath += "excel".concat(File.separator);
        } else if (fileType.equals(FileUploadConstants.WORD_TYPE)) {
            filePath += "word".concat(File.separator);
        }
        filePath += FileNameUtils.getFileDir().concat(File.separator);
        //String realDir = request.getSession().getServletContext().getRealPath(filePath);
        String realDir = FileUploadConstants.ROOT_PATH + filePath;
        File dir = new File(realDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        CommonsMultipartResolver multipartResolver =
                new CommonsMultipartResolver(request.getSession().getServletContext());
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator<String> ite = multiRequest.getFileNames();

            if (ite.hasNext()) {
                MultipartFile file = multiRequest.getFile(ite.next());

                if (file != null) {
                    String fileName = file.getOriginalFilename();
                    String suffix = FileNameUtils.getFileSuffix(fileName);
                    if (fileType.equals(FileUploadConstants.IMAGE_TYPE)) {
                    	if (!suffix.equals("jpg") && !suffix.equals("png")
                                && !suffix.equals("gif") && !suffix.equals("bmp")) {
                            return null;
                        }
                    }
                    

                    filePath += FileNameUtils.getFileName(suffix);

                   // String realPath = request.getSession().getServletContext().getRealPath(filePath);
                    String realPath = FileUploadConstants.ROOT_PATH + filePath;
                    File localFile = new File(realPath.toString());
                    try {
                        file.transferTo(localFile);
                        return filePath.replaceAll("\\\\", "/");
                    } catch (IllegalStateException e) {
                        log.error("文件上传失败", e);
                    } catch (IOException e) {
                        log.error("文件上传失败", e);
                    }
                }
            }
        }
        return null;
    }

}
