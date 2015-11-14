package com.misuosi.mshop.db.dao;

import com.misuosi.mshop.entity.Goods;
import com.misuosi.mshop.entity.GoodsClassification;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description		: SimpleDao测试
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
        "classpath:spring/spring-beans.xml",
        "classpath:spring/spring-dao.xml"})
public class SimpleDaoTest {

    private static Logger log = LoggerFactory.getLogger(SimpleDaoTest.class);

    @Test
    public void test() {
        Dao<Goods> goodsDao = DaoFactory.getDao(Goods.class);
        Map<String, Object> map = ((MultiTableDao) goodsDao)
                .queryForMap("select * from goods where supp_id=?", 1);
        log.debug("{}", map);
    }

    @Test
    public void testSave() throws Exception {
        Dao<GoodsClassification> goodsClassificationDao = DaoFactory.getDao(GoodsClassification.class);
        GoodsClassification goodsClassification = new GoodsClassification();
        //goodsClassification.setGoclCreateTime(null);
        goodsClassificationDao.save(goodsClassification);
    }

    @Test
    public void testBatchSave() throws Exception {
        Dao<Goods> goodsDao = DaoFactory.getDao(Goods.class);

        List<Goods> goodses = new ArrayList<Goods>();
        Goods goods1 = getGoods(1);
        Goods goods2 = getGoods(2);
        goodses.add(goods1);
        goodses.add(goods2);
        goodsDao.batchSave(goodses);

        System.out.println("id:" + goods1.getGoodId());
        System.out.println("id:" + goods2.getGoodId());
    }

    @Test
    public void testBatchSaveWithId() throws Exception {
        Dao<Goods> goodsDao = DaoFactory.getDao(Goods.class);

        List<Goods> goodses = new ArrayList<Goods>();
        Goods goods1 = getGoods(31);
        goods1.setGoodId(31);
        Goods goods2 = getGoods(32);
        goods2.setGoodId(32);
        goodses.add(goods1);
        goodses.add(goods2);

        goodsDao.batchSaveWithId(goodses);
    }

    @Test
    public void testBatchSaveForSql() throws Exception {
        Dao<Goods> goodsDao = DaoFactory.getDao(Goods.class);

        String sql = "insert into goods(good_purchase_limit_amount," +
                "good_assume_expenses,good_create_time,good_member_privilege," +
                "supp_id,good_id,good_purchase_limit,good_end_putaway_time," +
                "good_putaway_time,good_sort,good_stock_calculate_type," +
                "good_putaway_type,good_name,good_modify_time,good_details) " +
                "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)," +
                "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        goodsDao.batchSave(sql, null, null, new Timestamp(System.currentTimeMillis()),
                0, null, 17, 0, new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()), null, 0, 0, "商品名称17",
                new Timestamp(System.currentTimeMillis()), null, null, null,
                new Timestamp(System.currentTimeMillis()), 0, null, 18, 0,
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()), null, 0, 0, "商品名称18",
                new Timestamp(System.currentTimeMillis()), null);
    }

    @Test
    public void testBatchUpdate() throws Exception {
        Dao<Goods> goodsDao = DaoFactory.getDao(Goods.class);

        List<Goods> goodses = new ArrayList<Goods>();
        Goods goods1 = getGoods(21);
        goods1.setGoodId(21);
        goods1.setGoodDetails("啦啦啦");
        Goods goods2 = getGoods(22);
        goods2.setGoodId(22);
        goods2.setGoodPurchaseLimit((byte) 1);
        goodses.add(goods1);
        goodses.add(goods2);

        goodsDao.batchUpdate(goodses);
    }

    @Test
    public void testBatchDynamicUpdate() throws Exception {
        Dao<Goods> goodsDao = DaoFactory.getDao(Goods.class);

        List<Goods> goodses = new ArrayList<Goods>();
        Goods goods1 = getGoods(21);
        goods1.setGoodId(21);
        goods1.setGoodDetails(null);
        Goods goods2 = getGoods(22);
        goods2.setGoodId(22);
        goods2.setGoodDetails("嘻嘻嘻");
        goodses.add(goods1);
        goodses.add(goods2);

        goodsDao.batchDynamicUpdate(goodses);
    }

    private Goods getGoods(int i) {
        Goods goods = new Goods();
        goods.setGoodName("商品名称" + i);
        goods.setGoodPurchaseLimit((byte) 0);
        goods.setGoodMemberPrivilege((byte) 0);
        goods.setGoodStockCalculateType((byte) 0);
        goods.setGoodPutawayType((byte) 0);
        goods.setGoodPutawayTime(new Timestamp(System.currentTimeMillis()));
        goods.setGoodEndPutawayTime(new Timestamp(System.currentTimeMillis()));
        goods.setGoodModifyTime(new Timestamp(System.currentTimeMillis()));
        goods.setGoodCreateTime(new Timestamp(System.currentTimeMillis()));
        return goods;
    }

    /*@Test
    public void testUpdate() throws Exception {
        Dao<Admin> adminDao = DaoFactory.getDao(Admin.class);
        Admin admin = adminDao.get(3);
        admin.setAdmiName("用户名-update");
        admin.setAdmiPassword("密码-update");
        adminDao.update(admin);
    }

    @Test
    public void testDynamicUpdate() throws Exception {
        Dao<Admin> adminDao = DaoFactory.getDao(Admin.class);
        Admin admin = new Admin();
        admin.setAdmiId(3);
        admin.setAdmiName("用户名-update3");
        adminDao.dynamicUpdate(admin);
    }

    @Test
    public void testDelete() throws Exception {
        Dao<Admin> adminDao = DaoFactory.getDao(Admin.class);
        adminDao.delete(3);
    }

    @Test
    public void testGet() throws Exception {
        Dao<Admin> adminDao = DaoFactory.getDao(Admin.class);
        Admin admin = adminDao.findUniqueByProperty("admiId", "1");
        show(admin);
    }

    @Test
    public void testFindUniqueByProperty() throws Exception {
        Dao<Admin> adminDao = DaoFactory.getDao(Admin.class);
        Admin admin = adminDao.findUniqueByProperty("admiName", "username");
        show(admin);
    }

    @Test
    public void testFindUnique() throws Exception {
        Dao<Admin> adminDao = DaoFactory.getDao(Admin.class);
        Admin admin = adminDao.findUnique("select * from admin where admi_name=?", "username");
        show(admin);
    }

    @Test
    public void testFindByProperty() throws Exception {
        Dao<Admin> adminDao = DaoFactory.getDao(Admin.class);
        List<Admin> list = adminDao.findByProperty("admiName", "username");
        for (Admin admin : list) {
            show(admin);
        }
    }

    @Test
    public void testFind() throws Exception {
        Dao<Admin> adminDao = DaoFactory.getDao(Admin.class);
        List<Admin> list = adminDao.find("select * from admin where admi_name=? order by admi_id desc",
                "username");
        for (Admin admin : list) {
            show(admin);
        }
    }

    @Test
    public void testFindAll() throws Exception {
        Dao<Admin> adminDao = DaoFactory.getDao(Admin.class);
        List<Admin> list = adminDao.findAll();
        for (Admin admin : list) {
            show(admin);
        }
    }

    @Test
    public void testFindPaginationForPageNoPageSize() throws Exception {
        Dao<Admin> adminDao = DaoFactory.getDao(Admin.class);
        Pagination<Admin> p = adminDao.findPagination(1, 3);
        show(p);
    }

    @Test
    public void testFindPaginationForSqlPageNoPageSizeValues() throws Exception {
        Dao<Admin> adminDao = DaoFactory.getDao(Admin.class);
        Pagination<Admin> p = adminDao.findPagination("select * from admin where admi_name like ? order by admi_id desc",
                1, 2, "username");
        show(p);
    }

    @Test
    public void testBatchUpdateForPropertyValueIds() throws Exception {
        Dao<Admin> adminDao = DaoFactory.getDao(Admin.class);
        adminDao.batchUpdate("admiName", "中文", new Integer[]{2, 3});
    }

    @Test
    public void testBatchUpdateForSqlValues() throws Exception {
        Dao<Admin> adminDao = DaoFactory.getDao(Admin.class);
        adminDao.batchUpdate("update admin set admi_name=? where admi_id in (2,3)", "中文2");
    }

    @Test
    public void testBatchDeleteForIds() throws Exception {
        Dao<Admin> adminDao = DaoFactory.getDao(Admin.class);
        adminDao.batchDelete(new Integer[]{2, 3});
    }

    @Test
    public void testBatchDeleteForSqlValues() throws Exception {
        Dao<Admin> adminDao = DaoFactory.getDao(Admin.class);
        adminDao.batchDelete("delete from admin where admi_id in (?,?)", 4, 5);
    }

    @Test
    public void queryForMap() throws Exception {
        Dao<Admin> adminDao = DaoFactory.getDao(Admin.class);
        Map<String, Object> map = ((MultiTableDao) adminDao).queryForMap(
                "select mein_wechat_nickname, inre_remark "
                        + "from member_info m, integration_record i "
                        + "where m.mein_id=i.mein_id and inre_id=?",
                39);
        log.debug(Arrays.deepToString(map.entrySet().toArray()));
    }

    @Test
    public void queryForList() throws Exception {
        Dao<Admin> adminDao = DaoFactory.getDao(Admin.class);
        List<Map<String, Object>> list = ((MultiTableDao) adminDao).queryForList(
                "select mein_wechat_nickname, inre_remark "
                        + "from member_info m, integration_record i "
                        + "where m.mein_id=i.mein_id and i.mein_id=?",
                4406);
        log.debug(Arrays.deepToString(list.toArray()));
    }

    private void show(Pagination<Admin> p) {
        log.debug("pageNo " + p.getPageNo());
        log.debug("pageSize " + p.getPageSize());
        log.debug("totalPage " + p.getTotalPage());
        log.debug("totalCount " + p.getTotalCount());
        log.debug("isFirstPage " + p.isFirstPage());
        log.debug("isLastPage " + p.isLastPage());
        log.debug("nextPage " + p.getNextPage());
        log.debug("prePage " + p.getPrePage());
        for (Admin admin : p.getList()) {
            show(admin);
        }
    }

    private void show(Admin admin) {
        log.debug("admiId " + admin.getAdmiId());
        log.debug("admiName " + admin.getAdmiName());
        log.debug("admiPassword " + admin.getAdmiPassword());
    }*/

} 
