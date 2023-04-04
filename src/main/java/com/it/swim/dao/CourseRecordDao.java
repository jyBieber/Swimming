package com.it.swim.dao;

import com.it.swim.entity.CourseRecord;

import java.util.List;

/*
 * @description: 上课记录实体类dao层接口
 */
public interface CourseRecordDao {
    /*
     * @description: 查询所有上课记录列表
     * @param 
     * @return java.util.List< CourseRecord>
     */
    List<CourseRecord> queryCourseRecord();
    
    /*
     * @description: 通过courseRecordId查询指定上课记录信息
     * @param courseRecordId
     * @return  CourseRecord
     */
     CourseRecord queryCourseRecordById(long courseRecordId);


    /*
     * @description: 通过vipId查询上课记录信息列表
     * @param vipId
     * @return java.util.List< CoursePay>
     */
    List<CourseRecord> queryCourseRecordByVipId(long vipId);

    /*
     * @description: 通过coachId查询上课记录信息列表
     * @param coachId
     * @return  CourseRecord
     */
    List<CourseRecord> queryCourseRecordByCoachId(long coachId);

    /*
     * @description: 通过coursePayId查询上课记录信息列表
     * @param coursePayId
     * @return  CourseRecord
     */
    List<CourseRecord> queryCourseRecordByCoursePayId(long coursePayId);

    /*
     * @description: 通过courseRecord查询缴费信息列表
     * @param courseRecord
     * @return  CourseRecord
     */
    List<CourseRecord> queryCoursePayByCourseRecordId(long courseRecordId);
    
    /*
     * @description: 新增上课记录信息
     * @param courseRecord
     * @return int
     */
    int addCourseRecord( CourseRecord courseRecord);
    
    /*
     * @description: 修改上课记录信息
     * @param courseRecord
     * @return int
     */
    int modifyCourseRecord( CourseRecord courseRecord);
    
    /*
     * @description: 通过courseRecordId删除指定上课记录信息
     * @param courseRecordId
     * @return int
     */
    int deleteCourseRecord(long courseRecordId);
}
