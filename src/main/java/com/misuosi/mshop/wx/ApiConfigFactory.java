/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.wx;

import com.github.sd4324530.fastweixin.api.config.ApiConfig;

/**
 * Description		: apiConfig的工厂类
 * <br><br>Time		: 2015/4/25 15:37
 *
 * @author ICE
 * @version 1.0
 * @since 1.0
 */
public class ApiConfigFactory {

    private static ApiConfig apiConfig = null;

    static {
        String appId = WXParamsManager.getAppId();
        String appSecrect = WXParamsManager.getAppSecrect();
        apiConfig = new ApiConfig(appId, appSecrect, true);
    }

    /**
     * 刷新access_token
     */
    public static void refreshApiConfig() {
        apiConfig.init();
    }

    /**
     * 获取apiConfig
     * @return 返回apiConfig对象
     */
    public static ApiConfig getApiConfig() {
        return apiConfig;
    }

}
