package com.it.swim;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/*
 * @description: 基类，初始化Spring的配置
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉Junit spring配置文件的位置
@ContextConfiguration
        ({
                "classpath:spring/spring-dao.xml",
                "classpath:spring/spring-service.xml"
        })
public class BaseTest {
}
