package com.it.swim.dao;

import com.it.swim.entity.CoursePay;

import java.util.List;

/*
 * @description: 课程缴费充值记录实体类dao层接口
 */
public interface CoursePayDao {
    /*
     * @description: 查询所有课程缴费充值记录列表
     * @param
     * @return java.util.List< CoursePay>
     */
    List<CoursePay> queryCoursePay();

    /*
     * @description: 通过coursePayId查询指定课程缴费充值记录信息
     * @param coursePayId
     * @return  CoursePay
     */
    CoursePay queryCoursePayById(long coursePayId);
    /*
     * @description: 通过vipId查询课程缴费充值记录信息列表
     * @param vipId
     * @return java.util.List< CardRecord>
     */
    List<CoursePay> queryCoursePayByVipId(long vipId);

    /*
     * @description: 新增课程缴费充值记录信息
     * @param coursePay
     * @return int
     */
    int addCoursePay(CoursePay coursePay);

    /*
     * @description: 修改课程缴费充值记录信息
     * @param coursePay
     * @return int
     */
    int modifyCoursePay(CoursePay coursePay);

    /*
     * @description: 通过coursePayId删除课程缴费充值记录信息
     * @param coursePayId
     * @return int
     */
    int deleteCoursePay(long coursePayId);
    /*
     * @description: 通过 coursePayId 查询游泳记录信息次数
     * @param coursePayId
     * @return Integer
     */
    Integer countByCoursePayId(Long coursePayId);
}
