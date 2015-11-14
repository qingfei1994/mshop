/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.common.service;

import com.google.zxing.WriterException;
import com.misuosi.mshop.common.constants.FileUploadConstants;
import com.misuosi.mshop.util.FileNameUtils;
import com.misuosi.mshop.util.QRCode;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * Description		: 二维码Service
 * <p/>
 * <br><br>Time		: 2015/6/12 15:38
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
@Service
public class QRCodeService {

    private String rootPath = "uploads".concat(File.separator).concat("qrcode").concat(File.separator);
    private int width = 430;
    private int height = 430;

    /**
     * 生成二维码
     *
     * @param text
     * @param width
     * @param height
     * @return 返回二维码相对路径
     */
    public String genQRCode(String text, int width, int height) {
        String filePath = rootPath.concat(FileNameUtils.getFileDir()).concat(File.separator);
        String realDir = FileUploadConstants.ROOT_PATH.concat(filePath).concat(File.separator);
        File dir = new File((realDir));
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String fileName = FileNameUtils.getFileName("png");
        try {
            QRCode.encode(text, realDir.concat(fileName), width, height);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        filePath = filePath.concat(fileName);
        String resultPath = FileUploadConstants.DOWNLOAD_PATH
                .concat(filePath.replace(File.separator, "_").replace(".", "_"));
        return resultPath;
    }

    /**
     * 生成二维码
     *
     * @param text
     * @return 返回二维码相对路径
     */
    public String genQRCode(String text) {
        return genQRCode(text, width, height);
    }

    public static void main(String args[]) {
        System.out.println(new QRCodeService().genQRCode("哈哈哈"));
    }

}
