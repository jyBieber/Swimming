package com.it.swim.service.Impl;

import com.it.swim.dao.CoursePayDao;
import com.it.swim.dto.CoursePayExecution;
import com.it.swim.entity.CoursePay;
import com.it.swim.enums.CoursePayStateEnum;
import com.it.swim.exception.CoursePayOperationException;
import com.it.swim.service.CoursePayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/*
 * @description: CoursePayService实现类
 */
@Service
public class CoursePayServiceImpl implements CoursePayService {
    @Autowired
    private CoursePayDao coursePayDao;

    /*
     * @return java.util.List<  CoursePay>
     * @description: 查询全部课程缴费信息
     */
    @Override
    public List<CoursePay> getCoursePayList() {
        return getSurplusNum( coursePayDao.queryCoursePay());
    }

    /*
     * @param coursePayId
     * @return   CoursePay
     * @description: 通过课程缴费ID获取指定课程缴费信息
     */
    @Override
    public CoursePay getCoursePayById(long coursePayId) {
        return getSurplusNum( coursePayDao.queryCoursePayById(coursePayId));
    }

    /*
     * @param vipId
     * @return   CoursePay
     * @description: 通过vipID获取指定课程缴费信息
     */
    @Override
    public List<CoursePay> getCoursePayByVipId(long vipId) {
        return getSurplusNum( coursePayDao.queryCoursePayByVipId(vipId));
    }

    /*
     * @param coursePay
     * @return   CoursePayExecution
     * @description: 新增课程缴费信息
     */
    @Override
    public CoursePayExecution addCoursePay(CoursePay coursePay) {
        //空值判断
        if (coursePay == null) {
            return new CoursePayExecution(CoursePayStateEnum.EMPTY);
        }
        //设置上课时间
        //coursePay.setCreateTime(new Date());
        //添加课程缴费信息
        int effectedNum = coursePayDao.addCoursePay(coursePay);
        //判断是否添加成功
        if (effectedNum <= 0) {
            throw new CoursePayOperationException("添加课程缴费信息失败");
        }
        return new CoursePayExecution(CoursePayStateEnum.SUCCESS, coursePay);
    }

    /*
     * @param coursePay
     * @return   CoursePayExecution
     * @description: 修改课程缴费信息
     */
    @Override
    public CoursePayExecution modifyCoursePay(CoursePay coursePay) {
        //空值判断
        if (coursePay == null || coursePay.getCoursePayId() == null) {
            return new CoursePayExecution(CoursePayStateEnum.EMPTY);
        }
        //设置更新时间
        //coursePay.setLastEditTime(new Date());
        //修改课程缴费信息
        int effectedNum = coursePayDao.modifyCoursePay(coursePay);
        //判断是否修改成功
        if (effectedNum <= 0) {
            throw new CoursePayOperationException("修改课程缴费信息失败");
        }
        return new CoursePayExecution(CoursePayStateEnum.SUCCESS, coursePay);
    }

    /*
     * @param coursePayId
     * @return   CoursePayExecution
     * @description: 删除指定课程缴费信息
     */
    @Override
    public CoursePayExecution deleteCoursePay(long coursePayId) {
        //删除该课程缴费信息
        int effectedNum = coursePayDao.deleteCoursePay(coursePayId);
        //判断是否删除成功
        if (effectedNum <= 0) {
            throw new CoursePayOperationException("课程缴费信息删除失败");
        } else {
            return new CoursePayExecution(CoursePayStateEnum.SUCCESS);
        }
    }


    /*
     * @param coursePays
     * @return List<CoursePay>
     * @description: 获取已用次数和剩余次数
     */
    private List<CoursePay> getSurplusNum(List<CoursePay> coursePays) {
        if (!CollectionUtils.isEmpty(coursePays)) {
            coursePays.forEach(this::getSurplusNum);
        }
        return coursePays;
    }

    /*
     * @param coursePays
     * @return List<CoursePay>
     * @description: 获取已用次数和剩余次数
     */
    private CoursePay getSurplusNum(CoursePay coursePay) {
        coursePay.setUseNum(coursePayDao.countByCoursePayId(coursePay.getCoursePayId()));
        coursePay.setSurplusNum(coursePay.getNum() - coursePay.getUseNum());
        return coursePay;
    }
}
