/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.common.manager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Description		:
 * <p/>
 * <br><br>Time		: 2015/6/11 15:25
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
public class DistributionManagerTest {

    @Test
    public void testFindShipper() {
    }

}
