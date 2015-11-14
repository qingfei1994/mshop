/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.wx;

import com.github.sd4324530.fastweixin.api.BaseAPI;
import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.enums.ResultType;
import com.github.sd4324530.fastweixin.api.response.BaseResponse;
import com.github.sd4324530.fastweixin.util.BeanUtil;
import com.github.sd4324530.fastweixin.util.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Description		:
 * 模版消息API
 * <p/>
 * <br><br>Time		: 2015/4/25 2:43
 *
 * @author ICE
 * @version 1.0
 * @since 1.0
 */
public class TemplateAPI extends BaseAPI {

    private static final Logger LOG = LoggerFactory.getLogger(TemplateAPI.class);

    public TemplateAPI(ApiConfig config) {
        super(config);
    }

    /**
     * 发送模版消息
     *
     * @param openid     发送用户的OpneId
     * @param templateId 模版的Id通过公众号可以获得
     * @param data       填充模版的数据
     * @return
     */
    public ResultType sendTemplateMessage(String openid, String templateId, Map<String, Map<String, String>> data) {
        BeanUtil.requireNonNull(openid, "openid is null");
        BeanUtil.requireNonNull(templateId, "templateId is null");
        LOG.debug("发布客服消息......");
        String url = BASE_API_URL + "cgi-bin/message/template/send?access_token=#";
        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("touser", openid);
        params.put("template_id", templateId);
        params.put("url", "http://weixin.qq.com/download");
        params.put("topcolor", "#FF0000");
        params.put("data", data);
        BaseResponse response = executePost(url, JSONUtil.toJson(params));
        return ResultType.get(response.getErrcode());
    }

}
