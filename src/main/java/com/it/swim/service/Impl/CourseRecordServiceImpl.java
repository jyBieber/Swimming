package com.it.swim.service.Impl;

import com.it.swim.dao.CourseRecordDao;
import com.it.swim.dto.CoursePayExecution;
import com.it.swim.dto.CourseRecordExecution;
import com.it.swim.dto.VipCardExecution;
import com.it.swim.entity.CourseRecord;
import com.it.swim.enums.CourseRecordStateEnum;
import com.it.swim.enums.VipCardStateEnum;
import com.it.swim.exception.CourseRecordOperationException;
import com.it.swim.service.CourseRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * @description: CourseRecordService实现类
 */
@Service
public class CourseRecordServiceImpl implements CourseRecordService {
    @Autowired
    private CourseRecordDao courseRecordDao;

    /*
     * @return java.util.List<  CourseRecord>
     * @description: 查询全部上课记录信息
     */
    @Override
    public List<CourseRecord> getCourseRecordList() {
        return courseRecordDao.queryCourseRecord();
    }

    /*
     * @param courseRecordId
     * @return   CourseRecord
     * @description: 通过上课记录ID获取指定上课记录信息
     */
    @Override
    public CourseRecord getCourseRecordById(long courseRecordId) {
        return courseRecordDao.queryCourseRecordById(courseRecordId);
    }

    /*
     * @param vipId
     * @return java.util.List<CourseRecord>
     * @description: 通过vipId查询上课记录信息列表
     */
    @Override
    public List<CourseRecord> getCourseRecordByVipId(long vipId) {
        return courseRecordDao.queryCourseRecordByVipId(vipId);
    }

    /*
     * @param coachId
     * @return java.util.List<CourseRecord>
     * @description: 通过coachId查询上课记录信息列表
     */
    @Override
    public List<CourseRecord> getCourseRecordByCoachId(long coachId) {
        return courseRecordDao.queryCourseRecordByCoachId(coachId);
    }

    /*
     * @param coursePayId
     * @return   CourseRecord
     * @description: 通过coursePayId查询上课记录信息列表
     */
    @Override
    public List<CourseRecord> getCourseRecordByCoursePayId(long coursePayId) {
        return courseRecordDao.queryCourseRecordByCoursePayId(coursePayId);
    }

    /*
     * @param courseId
     * @return   CourseRecord
     * @description: 通过courseId查询上课记录信息列表
     */
    @Override
    public List<CourseRecord> getCourseRecordByCourseId(long courseId) {
        return courseRecordDao.queryCourseRecordByCourseId(courseId);
    }

    /*
     * @param courseRecordId
     * @return   CourseRecord
     * @description: 通过courseRecord查询缴费信息列表
     */
    @Override
    public List<CourseRecord> getCoursePayByCourseRecordId(long courseRecordId) {
        return courseRecordDao.queryCoursePayByCourseRecordId(courseRecordId);
    }

    /*
     * @param courseRecord
     * @return   CourseRecordExecution
     * @description: 新增上课记录信息
     */
    @Override
    public CourseRecordExecution addCourseRecord(CourseRecord courseRecord) {
        //空值判断
        if (courseRecord == null){
            return new CourseRecordExecution(CourseRecordStateEnum.EMPTY);
        }
        //给上课记录信息赋初始值
        //添加上课记录信息
        int effectedNum = courseRecordDao.addCourseRecord(courseRecord);
        //判断是否添加成功
        if (effectedNum <= 0){
            throw new CourseRecordOperationException("添加上课记录信息失败");
        }
        return new CourseRecordExecution(CourseRecordStateEnum.SUCCESS,courseRecord);
    }

    /*
     * @param courseRecord
     * @return   CourseRecordExecution
     * @description: 修改上课记录信息
     */
    @Override
    public CourseRecordExecution modifyCourseRecord(CourseRecord courseRecord) {
        //空值判断
        if (courseRecord == null || courseRecord.getCourseRecordId() == null){
            return new CourseRecordExecution(CourseRecordStateEnum.EMPTY);
        }
        //给上课记录信息赋初始值
        //修改上课记录信息
        int effectedNum = courseRecordDao.modifyCourseRecord(courseRecord);
        //判断是否修改成功
        if (effectedNum <= 0){
            throw new CourseRecordOperationException("修改上课记录信息失败");
        }
        return new CourseRecordExecution(CourseRecordStateEnum.SUCCESS,courseRecord);
    }

    /*
     * @param courseRecordId
     * @return   CourseRecordExecution
     * @description: 删除指定上课记录信息
     */
    @Override
    public CourseRecordExecution deleteCourseRecord(long courseRecordId) {
        //删除该上课记录信息
        int effectedNum = courseRecordDao.deleteCourseRecord(courseRecordId);
        //判断是否删除成功
        if (effectedNum <= 0){
            throw new CourseRecordOperationException("上课记录信息删除失败");
        }else {
            return new CourseRecordExecution(CourseRecordStateEnum.SUCCESS);
        }
    }
}
