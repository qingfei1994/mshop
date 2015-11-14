/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.db;

/**
 * Description		: 常量类
 * <p/>
 * <br><br>Time		: 2015/4/13 17:24
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public class Constants {

    /** 数据表默认前缀 */
    public static final String DEFAULT_TABLE_PREFIX = "";
    /** 表字段默认前缀 */
    public static final String DEFAULT_COLUMN_PREFIX = "";

    /** 是否需要分页：key */
    public static final String NEED_PAGING_KEY = "needPaging";
    /** 页码：key */
    public static final String PAGE_NO_KEY = "pageNo";
    /** 分页大小：key */
    public static final String PAGE_SIZE_KEY = "pageSize";
    /** 总记录数：key */
    public static final String TOTAL_COUNT_KEY = "totalCount";
    /** 最小页码 */
    public static final int MIN_PAGE_NO = 1;
    /** 最小分页大小 */
    public static final int MIN_PAGE_SIZE = 1;
    /** 最大分页大小 */
    public static final int MAX_PAGE_SIZE = 1000;
    /** 默认分页大小 */
    public static final int DEFAULT_PAGE_SIZE = 10;

    /** 排序字段：key */
    public static final String SORT_KEY = "sort_key";
    /** 排序字段分隔符 */
    public static final String SORT_SEPARATOR = ",";

    /** 查询列表：key（将查询列表回传到页面时用的key） */
    public static final String QUERY_LIST_KEY = "query_list_key";
    /** 查询：前缀 */
    public static final String QUERY_PREFIX = "q_";
    /** 查询：like key */
    public static final String QUERY_LIKE_KEY = QUERY_PREFIX.concat("like_");
    /** 查询：eq key */
    public static final String QUERY_EQ_KEY = QUERY_PREFIX.concat("eq_");
    /** 查询：gt key */
    public static final String QUERY_GT_KEY = QUERY_PREFIX.concat("gt_");
    /** 查询：ge key */
    public static final String QUERY_GE_KEY = QUERY_PREFIX.concat("ge_");
    /** 查询：lt key */
    public static final String QUERY_LT_KEY = QUERY_PREFIX.concat("lt_");
    /** 查询：le key */
    public static final String QUERY_LE_KEY = QUERY_PREFIX.concat("le_");
    /** 查询：is key */
    public static final String QUERY_IS_KEY = QUERY_PREFIX.concat("is_");
    /** 运算符：like */
    public static final String OPERATOR_LIKE = "like";
    /** 运算符：= */
    public static final String OPERATOR_EQ = "=";
    /** 运算符：> */
    public static final String OPERATOR_GT = ">";
    /** 运算符：>= */
    public static final String OPERATOR_GE = ">=";
    /** 运算符：< */
    public static final String OPERATOR_LT = "<";
    /** 运算符：<= */
    public static final String OPERATOR_LE = "<=";
    /** 运算符：is */
    public static final String OPERATOR_IS = "is";
    /** 查询类型：字符串 key */
    public static final String QUERY_TYPE_STRING_KEY = "s_";
    /** 查询类型：数字 key */
    public static final String QUERY_TYPE_NUMBER_KEY = "n_";
    /** 查询类型：日期 key */
    public static final String QUERY_TYPE_DATE_KEY = "d_";
    /** 查询类型：字符串 */
    public static final String QUERY_TYPE_STRING = "string";
    /** 查询类型：数字 */
    public static final String QUERY_TYPE_NUMBER = "number";
    /** 查询类型：日期 */
    public static final String QUERY_TYPE_DATE = "date";
    /** 查询条件：OR */
    public static final String QUERY_CONDITION_OR = "_or_";
    /** 查询条件：AND */
    public static final String QUERY_CONDITION_AND = "_and_";

    /** 下划线 */
    public static final String UNDERLINE = "_";

}
