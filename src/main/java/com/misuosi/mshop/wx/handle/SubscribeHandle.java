/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.wx.handle;

import com.github.sd4324530.fastweixin.handle.EventHandle;
import com.github.sd4324530.fastweixin.message.BaseMsg;
import com.github.sd4324530.fastweixin.message.req.BaseEvent;

/**
 * Description		:
 * <br><br>Time		: 2015/4/26 16:37
 *
 * @author ICE
 * @version 1.0
 * @since 1.0
 */
public class SubscribeHandle implements EventHandle {

    @Override
    public BaseMsg handle(BaseEvent event) {
        return null;
    }

    @Override
    public boolean beforeHandle(BaseEvent event) {
        return false;
    }

}
