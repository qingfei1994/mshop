/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

/**
 * Description		: 文件下载Util
 * <p/>
 * <br><br>Time		: 2015年6月23日 上午10:22:24
 *
 * @author HONG
 * @version 1.0
 * @since 1.0
 */
public class FileDownloadUtils {

    /**
     * 文件下载
     *
     * @param response
     * @param file     需要下载文件
     */
    public static void download(HttpServletResponse response, File file) {
        FileInputStream fis = null;
        BufferedInputStream buff = null;
        OutputStream myout = null;

        try {
            String fileName = file.getName();
            // 设置response的编码方式
            response.setContentType("application/x-msdownload");
            // 设置附加文件名
            response.setHeader("Content-Disposition", "attachment;filename="
                    + new String(fileName.getBytes("gbk"), "iso-8859-1"));

            // 读出文件到i/o流
            fis = new FileInputStream(file);
            buff = new BufferedInputStream(fis);

            byte[] b = new byte[1024];// 相当于我们的缓存
            long k = 0;// 该值用于计算当前实际下载了多少字节

            // 从response对象中得到输出流,准备下载
            myout = response.getOutputStream();

            // 开始循环下载
            while (k < file.length()) {
                int j = buff.read(b, 0, 1024);
                k += j;

                // 将b中的数据写到客户端的内存
                myout.write(b, 0, j);
            }
            // 将写入到客户端的内存的数据,刷新到磁盘
            myout.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                myout.close();
                buff.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
