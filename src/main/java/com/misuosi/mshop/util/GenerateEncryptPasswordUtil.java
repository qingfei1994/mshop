///*
// * Copyright (c) 2015
// * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD)
// * All rights reserved.
// */
//package com.misuosi.mshop.util;
//
//import com.misuosi.mshop.entity.Administrator;
//import com.misuosi.mshop.entity.DistributorAccount;
//import com.misuosi.mshop.entity.Supplier;
//
///**
// * Description : 管理员、供货商、分销商生成密码的工具类
// * <p/>
// * <br>
// * <br>
// * Time : 2015-6-4 上午9:40:33
// *
// * @author CHQ
// * @version 1.0
// * @since 1.0
// */
//public class GenerateEncryptPasswordUtil {
//    /**
//     * 生成管理员的加密后的密码
//     *
//     * @param admin
//     * @return
//     */
//    public static Administrator generateAdminPassword(Administrator admin) {
//        admin.setAdmiSalt(com.misuosi.mshop.util.EncryptUtils.getSalt());
//        String admiPassword = com.misuosi.mshop.util.EncryptUtils.getPassword(admin.getAdmiPassword(),
//                admin.getCredentialsSalt());
//        admin.setAdmiPassword(admiPassword);
//        return admin;
//
//    }
//
//    /**
//     * 生成供货商的加密后的密码
//     *
//     * @param supplier
//     * @return
//     */
//    public static Supplier generateSupplierPassword(Supplier supplier) {
//        supplier.setSuppSalt(com.misuosi.mshop.util.EncryptUtils.getSalt());
//        String suppPassword = com.misuosi.mshop.util.EncryptUtils.getPassword(supplier.getSuppPassword(),
//                supplier.getCredentialsSalt());
//        supplier.setSuppPassword(suppPassword);
//        return supplier;
//
//    }
//
//    /**
//     * 生成分销商加密后的密码
//     *
//     * @param distributorAccount
//     * @return
//     */
//    public static DistributorAccount generateDistributorPassword(DistributorAccount distributorAccount) {
//        distributorAccount.setDiacSalt(com.misuosi.mshop.util.EncryptUtils.getSalt());
//        String suppPassword = com.misuosi.mshop.util.EncryptUtils.getPassword(distributorAccount.getDiacPassword(),
//                distributorAccount.getCredentialsSalt());
//        distributorAccount.setDiacPassword(suppPassword);
//        return distributorAccount;
//
//    }
//
//}
