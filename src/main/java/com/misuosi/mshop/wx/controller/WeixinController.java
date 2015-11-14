/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.wx.controller;

import com.github.sd4324530.fastweixin.handle.EventHandle;
import com.github.sd4324530.fastweixin.handle.MessageHandle;
import com.github.sd4324530.fastweixin.message.BaseMsg;
import com.github.sd4324530.fastweixin.message.TextMsg;
import com.github.sd4324530.fastweixin.message.req.TextReqMsg;
import com.misuosi.mshop.wx.WXParamsManager;
import com.misuosi.mshop.wx.handle.SubscribeHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Description		: 微信控制器
 * <br><br>Time		: 2015/4/23 20:48
 *
 * @author ICE
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/weixin")
public class WeixinController extends CustomWeixinControllerSupport {

    private static final Logger log = LoggerFactory.getLogger(WeixinController.class);

    @Override
    protected String getToken() {
        return WXParamsManager.getToken();
    }

    @Override
    protected String getAppId() {
        return null;
    }

    @Override
    protected String getAESKey() {
        return null;
    }

    /**
     * 重写父类方法，处理对应的微信消息
     *
     * @param msg
     * @return
     */
    @Override
    protected BaseMsg handleTextMsg(TextReqMsg msg) {
        String content = msg.getContent();
        log.debug("用户发送到服务器的内容:{}", content);
        return new TextMsg("服务器回复用户消息!");
    }

    /**
     * 1.1版本新增，重写父类方法，加入自定义微信消息处理器
     * 不是必须的，上面的方法是统一处理所有的文本消息，如果业务太复杂，上面的会显得比较乱
     * 这个机制就是为了应对这种情况，每个MessageHandle就是一个业务，只处理指定的那部分消息
     *
     * @return
     */
    @Override
    protected List<MessageHandle> initMessageHandles() {
        List<MessageHandle> handles = new ArrayList<MessageHandle>();
        return null;
    }

    /**
     * 1.1版本新增，重写父类方法，加入自定义微信事件处理器，同上
     *
     * @return
     */
    @Override
    protected List<EventHandle> initEventHandles() {
        List<EventHandle> handles = new ArrayList<EventHandle>();
        SubscribeHandle subscribeHandle = new SubscribeHandle();
        //handles.add(subscribeHandle);
        return handles;
    }

}
