package com.it.swim.dao;

import com.it.swim.BaseTest;
import com.it.swim.entity.Admin;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/*
 * @description: AdminDao层测试类
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdminDaoTest extends BaseTest {
    @Autowired
    private AdminDao adminDao;

    @Test
    public void testAQueryAdminById(){
        Admin admin = adminDao.queryAdminById(9180L);
        System.out.println(admin.getAdminName());
    }

    @Test
    public void testBModifyAdminById(){
        Admin admin = new Admin();
        admin.setAdminId(9180L);
        admin.setAdminName("Ysp");
        //admin.setLastEditTime(new Date());
        int effectedNum = adminDao.modifyAdmin(admin);
        assertEquals(1,effectedNum);
        System.out.println(effectedNum);
    }
}
