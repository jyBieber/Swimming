package com.it.swim.service;

import com.it.swim.BaseTest;
import com.it.swim.dto.ImageHolder;
import com.it.swim.dto.CoachExecution;
import com.it.swim.enums.CoachStateEnum;
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
 * @description: CoachService层测试类
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CoachServiceTest extends BaseTest {
    @Autowired
    private CoachService coachService;

    @Test
    public void testAQueryCoach(){
        List<com.it.swim.entity.Coach> coachList = coachService.getCoachList();
        assertEquals(2,coachList.size());
        System.out.println(coachList.size());
    }

    @Test
    public void testBQueryCoachById(){
        com.it.swim.entity.Coach coach = coachService.getCoachById(30001L);
        assertEquals("赵雷",coach.getCoachName());
        System.out.println(coach.getCoachName());
    }

    @Test
    public void testCAddCoach() throws FileNotFoundException {
        com.it.swim.entity.Coach coach = new com.it.swim.entity.Coach();
        coach.setCoachId(30006L);
        coach.setCoachName("张丽");
        coach.setGender("女");
        coach.setAge(22);
        coach.setPhone("18841774322");
        coach.setEmail("zhangli1@163.cn");
        coach.setRemarks("仰泳、自由泳");

        // 创建缩略图文件流
        File thumbnailFile = new File("D:/照片/17301053.JPG");
        InputStream is = new FileInputStream(thumbnailFile);
        ImageHolder thumbnail = new ImageHolder(thumbnailFile.getName(), is);

        CoachExecution coachExecution = coachService.addCoach(coach,thumbnail);
        assertEquals(CoachStateEnum.SUCCESS.getState(),coachExecution.getState());
        System.out.println(coachExecution.getStateInfo());
    }

    @Test
    public void testDModifyCoach() throws FileNotFoundException {
        com.it.swim.entity.Coach coach = new com.it.swim.entity.Coach();
        coach.setCoachId(30006L);
        coach.setCoachName("张梅");

        // 创建缩略图文件流
        File thumbnailFile = new File("D:/壁纸/微信图片_20180221212912.jpg");
        InputStream is = new FileInputStream(thumbnailFile);
        ImageHolder thumbnail = new ImageHolder(thumbnailFile.getName(), is);

        CoachExecution coachExecution = coachService.modifyCoach(coach,thumbnail);
        assertEquals(CoachStateEnum.SUCCESS.getState(),coachExecution.getState());
        System.out.println(coachExecution.getStateInfo());
    }

    @Test
    public void testEDeleteCoach(){
        CoachExecution coachExecution = coachService.deleteCoach(30006L);
        assertEquals(CoachStateEnum.SUCCESS.getState(),coachExecution.getState());
        System.out.println(coachExecution.getStateInfo());
    }
}
