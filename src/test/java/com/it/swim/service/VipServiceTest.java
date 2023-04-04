package com.it.swim.service;

import com.it.swim.BaseTest;
import com.it.swim.dto.ImageHolder;
import com.it.swim.dto.VipExecution;
import com.it.swim.entity.Vip;
import com.it.swim.enums.VipStateEnum;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/*
 * @description: VipService层测试类
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VipServiceTest extends BaseTest {
    @Autowired
    private VipService vipService;

    @Test
    public void testAQueryVip(){
        List<Vip> vipList = vipService.getVipList();
        //assertEquals(3,vipList.size());
        System.out.println(vipList.size());
    }

    @Test
    public void testBQueryVipById(){
        Vip vip = vipService.getVipById(10001L);
        assertEquals("李明",vip.getVipName());
        System.out.println(vip.getVipName());
    }

    @Test
    public void testCAddVip() throws FileNotFoundException {
        Vip vip = new Vip();
        vip.setVipId(10004L);
        vip.setVipName("小明");
        vip.setGender("男");
        vip.setAge(22);
        vip.setPhone("18841774322");
        vip.setEmail("xiaoming@163.cn");
        vip.setRemarks("无");

        // 创建缩略图文件流
        File thumbnailFile = new File("D:/projectSwim/image/touxiang.png");
        InputStream is = new FileInputStream(thumbnailFile);
        ImageHolder thumbnail = new ImageHolder(thumbnailFile.getName(), is);

        VipExecution vipExecution = vipService.addVip(vip,thumbnail);
        assertEquals(VipStateEnum.SUCCESS.getState(),vipExecution.getState());
        System.out.println(vipExecution.getStateInfo());
    }

    @Test
    public void testDModifyVip() throws FileNotFoundException {
        Vip vip = new Vip();
        vip.setVipId(10004L);
        vip.setPhone("18911114322");

        // 创建缩略图文件流
        File thumbnailFile = new File("D:/壁纸/微信图片_20180221212912.jpg");
        InputStream is = new FileInputStream(thumbnailFile);
        ImageHolder thumbnail = new ImageHolder(thumbnailFile.getName(), is);

        VipExecution vipExecution = vipService.modifyVip(vip,thumbnail);
        assertEquals(VipStateEnum.SUCCESS.getState(),vipExecution.getState());
        System.out.println(vipExecution.getStateInfo());
    }

    @Test
    public void testEDeleteVip(){
        VipExecution vipExecution = vipService.deleteVip(10004L);
        assertEquals(VipStateEnum.SUCCESS.getState(),vipExecution.getState());
        System.out.println(vipExecution.getStateInfo());
    }
}
