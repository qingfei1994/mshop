/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.db.builder;

import java.util.List;

/**
 * Description		: sql语句构造结果
 * <p/>
 * <br><br>Time		: 2015/4/7 16:33
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public class BuildResult {

    private String sql;
    private List<Object> params;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<Object> getParams() {
        return params;
    }

    public void setParams(List<Object> params) {
        this.params = params;
    }

}
