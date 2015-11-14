/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.util;

/**
 * Description		: 文件名工具类
 * <p/>
 * <br><br>Time		: 2015/5/15 15:49
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public class FileNameUtils {

    /**
     * 获取文件路径
     *
     * @return
     */
    public static String getFileDir() {
        return DateUtils.getDate("yyyy-MM");
    }

    /**
     * 获取文件名
     *
     * @param suffix
     * @return
     */
    public static String getFileName(String suffix) {
        return DateUtils.getDate("yyyyMMddHHmmss").concat(StringUtils.createNonceStr(4))
                .concat(".").concat(suffix);
    }

    /**
     * 获取文件名后缀
     *
     * @param fileName
     * @return
     */
    public static String getFileSuffix(String fileName) {
        int index = fileName.lastIndexOf(".") + 1;
        return fileName.substring(index);
    }

}
