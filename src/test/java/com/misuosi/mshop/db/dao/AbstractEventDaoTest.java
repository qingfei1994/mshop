package com.misuosi.mshop.db.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Description		: AbstractEventDao测试
 * <p/>
 * <br><br>Time		: 2015/4/17 17:54
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-application.xml",
        "classpath:spring/spring-jdbc.xml",
        "classpath:spring/spring-beans.xml"})
public class AbstractEventDaoTest {

    private static Logger log = LoggerFactory.getLogger(AbstractEventDaoTest.class);

    @Test
    public void testAddDataAccessListener() throws Exception {
        /*Dao<Admin> adminDao = DaoFactory.getDao(Admin.class);
        ((AbstractEventDao) adminDao).addDataAccessListener(new DataAccessAdapter() {
            @Override
            public void beforeSave(DataAccessEvent event) {
                log.debug("beforeSave");
            }

            @Override
            public void afterSave(DataAccessEvent event) {
                log.debug("afterSave");
            }

            @Override
            public void beforeUpdate(DataAccessEvent event) {
                log.debug("beforeUpdate");
            }

            @Override
            public void afterUpdate(DataAccessEvent event) {
                log.debug("afterUpdate");
            }

            @Override
            public void beforeDelete(DataAccessEvent event) {
                log.debug("beforeDelete");
            }

            @Override
            public void afterDelete(DataAccessEvent event) {
                log.debug("afterDelete");
            }
        });

        Admin admin = new Admin();
        admin.setAdmiName("username");
        admin.setAdmiPassword("password");

        adminDao.save(admin);

        admin.setAdmiId(2);
        adminDao.update(admin);

        adminDao.delete(3);*/
    }

} 
