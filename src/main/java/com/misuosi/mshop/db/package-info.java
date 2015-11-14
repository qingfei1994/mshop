/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
/**
 * Description		: 数据库底层代码
 *
 * <p>
 *     注意要点：
 *     <li>实体类必须继承自Entity类，如：Admin(Admin extends Entity)</li>
 *     <li>必须用@Id指定数据表的主键，如：adminId的定义(@Id private int adminId;)</li>
 *     <li>可以用@Transient指定临时域，临时域即只在实体中存在，在数据表中不存在的域</li>
 *
 *     <li>数据表的默认前缀为空，实体名与表名的对应规则如：实体名Admin对应表名admin</li>
 *     <li>属性名与表字段的对应规则如：属性名adminId对应表字段admin_id</li>
 *
 *     <li>内建sql按主键倒叙排序</li>
 *     <li>目前只支持单个整型主键</li>
 *
 *     <li>数据类型只支持：
 *          Byte/byte、Short/short、Integer/int、Long/long、Float/float、Double/double、
 *          Character/chat、Boolean/boolean、String、Date、Timestamp、BigDecimal
 *     </li>
 *
 *     <li>
 *         Dao可通过DaoFactory的getDao方法获取：
 *          1.Dao<Admin> adminDao = DaoFactory.getDao(Admin.class);
 *          2.在spring的xml文件中配置，然后通过注解获取
 *     </li>
 *     <li>
 *         可编写自定义的Dao，必须放在entity包同级路径的dao包下，且必须继承自QueryDao,
 *         同时需要实现无参构造方法：public AdminDao() { super(Admin.class); }
 *     </li>
 * </p>
 *
 * <br><br>Time		: 2015/4/13 17:40
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
package com.misuosi.mshop.db;