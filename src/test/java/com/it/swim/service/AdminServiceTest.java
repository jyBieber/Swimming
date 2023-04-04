package com.it.swim.service;

import com.it.swim.BaseTest;
import com.it.swim.entity.Admin;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

/*
 * @description: AdminService层测试类
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdminServiceTest extends BaseTest {
    @Autowired
    private AdminService adminService;

    @Test
    public void testBQueryAdminById(){
        Admin admin = adminService.getAdminById(10002L);
        assertEquals("ysp",admin.getAdminName());
        System.out.println(admin.getAdminName());
    }
}
