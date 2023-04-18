package com.it.swim.service;

import com.it.swim.dto.CoursePayExecution;
import com.it.swim.entity.CoursePay;

import java.util.List;

public interface CoursePayService {
    /*
     * @description: 查询全部课程缴费信息
     * @param
     * @return java.util.List< CoursePay>
     */
    List<CoursePay> getCoursePayList();

    /*
     * @description: 通过课程缴费ID获取指定课程缴费信息
     * @param coursePayId
     * @return  CoursePay
     */
    CoursePay getCoursePayById(long coursePayId);

    /*
     * @description: 通过会员ID获取指定课程缴费信息
     * @param vipId
     * @return  CoursePay
     */
    List<CoursePay> getCoursePayByVipId(long vipId);

    /*
     * @description: 新增课程缴费信息
     * @param coursePay
     * @return  CoursePayExecution
     */
    CoursePayExecution addCoursePay(CoursePay coursePay);

    /*
     * @description: 修改课程缴费信息
     * @param coursePay
     * @return  CoursePayExecution
     */
    CoursePayExecution modifyCoursePay(CoursePay coursePay);

    /*
     * @description: 删除指定课程缴费信息
     * @param coursePayId
     * @return  CoursePayExecution
     */
    CoursePayExecution deleteCoursePay(long coursePayId);
}
