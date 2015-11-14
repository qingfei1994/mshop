/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.db.dao;

import com.misuosi.mshop.entity.Permission;
import com.misuosi.mshop.entity.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Description		: SimpleTreeDao测试
 * <p/>
 * <br><br>Time		: 2015/6/1 14:05
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-application.xml",
        "classpath:spring/spring-jdbc.xml",
        "classpath:spring/spring-beans.xml",
        "classpath:spring/spring-dao.xml"})
public class SimpleTreeDaoTest {

    private static Logger log = LoggerFactory.getLogger(SimpleTreeDaoTest.class);

    @Test
    public void testQueryTree() {
        TreeDao<Role> roleTreeDao = DaoFactory.getTreeDao(Role.class);
        List<Role> roleList = roleTreeDao.queryForTree();

        TreeDao<Permission> permissionTreeDao = DaoFactory.getTreeDao(Permission.class);
        List<Permission> permissionList = permissionTreeDao.queryForTree("select * from permission where perm_scope=3");
    }

}
