/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
/**
 * Description		: 分页底层代码
 *
 * <p>
 *     注意要点：
 *     <li>为需要分页的页面添加分页拦截器：PaginationIntercepter</li>
 *     <li>为分页table的父元素加上class：paging-table</li>
 *     <li>在分页table元素的上方添加name为path，type为hidden的input元素，用以指定数据请求的url</li>
 *     <li>导入分页js</li>
 * </p>
 *
 * <br><br>Time		: 2015/4/29 15:28
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
package com.misuosi.mshop.db.page;