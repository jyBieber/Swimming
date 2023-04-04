package com.it.swim.service;

import com.it.swim.dto.CourseRecordExecution;
import com.it.swim.entity.CourseRecord;
import java.util.List;

public interface CourseRecordService {
    /*
     * @description: 查询全部上课记录信息
     * @param
     * @return java.util.List< CourseRecord>
     */
    List<CourseRecord> getCourseRecordList();

    /*
     * @description: 通过上课记录ID获取指定上课记录信息
     * @param courseRecordId
     * @return  CourseRecord
     */
     CourseRecord getCourseRecordById(long courseRecordId);

    /*
     * @description: 通过vipId查询上课记录信息列表
     * @param vipId
     * @return java.util.List< CourseRecord>
     */
    List<CourseRecord> getCourseRecordByVipId(long vipId);

    /*
     * @description: 通过coachId查询上课记录信息列表
     * @param coachId
     * @return java.util.List<CourseRecord>
     */
    List<CourseRecord> getCourseRecordByCoachId(long coachId);

    /*
     * @description: 通过coursePayId查询上课记录信息列表
     * @param coursePayId
     * @return  CourseRecord
     */
    List<CourseRecord> getCourseRecordByCoursePayId(long coursePayId);

    /*
     * @description: 通过courseRecord查询缴费信息列表
     * @param coursePayId
     * @return  CourseRecord
     */
    List<CourseRecord> getCoursePayByCourseRecordId(long CourseRecordId);

    /*
     * @description: 新增上课记录信息
     * @param courseRecord
     * @return  CourseRecordExecution
     */
    CourseRecordExecution addCourseRecord(CourseRecord courseRecord);

    /*
     * @description: 修改上课记录信息
     * @param courseRecord
     * @return  CourseRecordExecution
     */
    CourseRecordExecution modifyCourseRecord(CourseRecord courseRecord);

    /*
     * @description: 删除指定上课记录信息
     * @param courseRecordId
     * @return  CourseRecordExecution
     */
    CourseRecordExecution deleteCourseRecord(long courseRecordId);
}
