/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.db.query;

import com.alibaba.fastjson.JSON;
import com.misuosi.mshop.db.Constants;
import com.misuosi.mshop.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description		: 查询管理器
 * <p/>
 * <br><br>Time		: 2015/5/4 9:35
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public class QueryManager {

    private static final ThreadLocal<List<QueryItem>> queryList = new ThreadLocal<List<QueryItem>>();

    private static final ThreadLocal<HttpServletRequest> currentRequest = new ThreadLocal<HttpServletRequest>();

    private static QueryManager instance;

    private QueryManager() {

    }

    public static QueryManager instance() {
        if (instance == null) {
            instance = new QueryManager();
        }
        return instance;
    }

    /**
     * 清空上次查询的残留参数
     */
    public void clearAll() {
        queryList.set(null);
        currentRequest.set(null);
    }

    public boolean isNeedQuery() {
        List list = queryList.get();
        if (list != null && !list.isEmpty()) {
            return true;
        }
        return false;
    }

    public List<QueryItem> getQueryList() {
        return this.queryList.get();
    }

    /**
     * 收集查询子项
     *
     * @param request
     */
    public void collectQueryItem(HttpServletRequest request) {
        List<QueryItem> list = new ArrayList<QueryItem>();
        Map<String, String[]> params = request.getParameterMap();
        for (String key : params.keySet()) {
            String[] values = params.get(key);
            for (String value : values) {
                QueryItem queryItem = parseQueryItem(key, value);
                if (queryItem != null) {
                    list.add(queryItem);
                }
            }
        }
        this.queryList.set(list);
    }

    /**
     * 解析查询子项
     *
     * @param queryKey
     * @return
     */
    public QueryItem parseQueryItem(String queryKey, String value) {
        String regular = "^q_(like|eq|gt|ge|lt|le|is)_(s|n|d)_[A-Za-z]+[A-Za-z0-9_\\.]+$";
        Pattern pattern = Pattern.compile(regular);
        Matcher matcher = pattern.matcher(queryKey);
        boolean match = matcher.matches();
        if (!match || StringUtils.isBlank(value)) {
            return null;
        }

        QueryItem queryItem = null;
        int pos1 = queryKey.indexOf(Constants.UNDERLINE, Constants.QUERY_PREFIX.length()) + 1;
        int pos2 = queryKey.indexOf(Constants.UNDERLINE, pos1) + 1;
        String operatorKey = queryKey.substring(0, pos1);
        String typeKey = queryKey.substring(pos1, pos2);
        String property = queryKey.substring(pos2);

        String operator = null;
        if (Constants.QUERY_LIKE_KEY.equals(operatorKey)) {
            operator = Constants.OPERATOR_LIKE;
        } else if (Constants.QUERY_EQ_KEY.equals(operatorKey)) {
            operator = Constants.OPERATOR_EQ;
        } else if (Constants.QUERY_GT_KEY.equals(operatorKey)) {
            operator = Constants.OPERATOR_GT;
        } else if (Constants.QUERY_GE_KEY.equals(operatorKey)) {
            operator = Constants.OPERATOR_GE;
        } else if (Constants.QUERY_LT_KEY.equals(operatorKey)) {
            operator = Constants.OPERATOR_LT;
        } else if (Constants.QUERY_LE_KEY.equals(operatorKey)) {
            operator = Constants.OPERATOR_LE;
        } else if (Constants.QUERY_IS_KEY.equals(operatorKey)) {
            operator = Constants.OPERATOR_IS;
            value = value.replace("_", " ");
        }

        String type = Constants.QUERY_TYPE_STRING; // 默认为字符串类型
        if (Constants.QUERY_TYPE_STRING_KEY.equals(typeKey)) {
            type = Constants.QUERY_TYPE_STRING;
        } else if (Constants.QUERY_TYPE_NUMBER_KEY.equals(typeKey)) {
            type = Constants.QUERY_TYPE_NUMBER;
        } else if (Constants.QUERY_TYPE_DATE_KEY.equals(typeKey)) {
            type = Constants.QUERY_TYPE_DATE;
        }

        if (operator != null) {
            queryItem = new QueryItem();

            try {
                // 解决get方式参数提交的乱码问题
                // 因为tomcat get 方式的默认编码是ISO-8859-1，所以这样转码，如果服务器编码变了，可能会出现乱码
                value = new String(value.getBytes("ISO-8859-1"), "UTF-8");

                int orIndex = value.indexOf(Constants.QUERY_CONDITION_OR);
                int andIndex = value.indexOf(Constants.QUERY_CONDITION_AND);
                if (orIndex > 0 || andIndex > 0) {
                    int index, invalidLength;
                    if (orIndex < 0) {
                        index = andIndex;
                        invalidLength = Constants.QUERY_CONDITION_AND.length();
                    } else if (andIndex < 0) {
                        index = orIndex;
                        invalidLength = Constants.QUERY_CONDITION_OR.length();
                    } else if (orIndex < andIndex) {
                        index = orIndex;
                        invalidLength = Constants.QUERY_CONDITION_OR.length();
                    } else {
                        index = andIndex;
                        invalidLength = Constants.QUERY_CONDITION_AND.length();
                    }

                    String queryString = value.substring(index + invalidLength, value.length());
                    int separateIndex = queryString.indexOf("=");
                    if (invalidLength == Constants.QUERY_CONDITION_OR.length()) {
                        queryItem.setOrItem(parseQueryItem(queryString.substring(0, separateIndex),
                                queryString.substring(separateIndex + 1, queryString.length())));
                    } else {
                        queryItem.setAndItem(parseQueryItem(queryString.substring(0, separateIndex),
                                queryString.substring(separateIndex + 1, queryString.length())));
                    }

                    value = value.substring(0, index);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            queryItem.setQueryKey(queryKey);
            queryItem.setProperty(property);
            queryItem.setValue(value);
            queryItem.setOperator(operator);
            queryItem.setType(type);
        }

        return queryItem;
    }

    /**
     * 将querItem发回页面
     */
    public void sendBackQuery() {
        HttpServletRequest request = currentRequest.get();
        List<QueryItem> queryList = getQueryList();
        if (request != null && queryList != null && !queryList.isEmpty()) {
            request.setAttribute(Constants.QUERY_LIST_KEY, JSON.toJSONString(queryList));
        }
        clearAll();
    }

    public HttpServletRequest getCurrentRequest() {
        return currentRequest.get();
    }

    public void setCurrentRequest(HttpServletRequest currentRequest) {
        this.currentRequest.set(currentRequest);
    }

}
