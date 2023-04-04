package com.it.swim.service.Impl;

import com.it.swim.dao.CourseDao;
import com.it.swim.dto.CourseExecution;
import com.it.swim.entity.Course;
import com.it.swim.enums.CourseStateEnum;
import com.it.swim.exception.CourseOperationException;
import com.it.swim.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/*
 * @description: CourseService实现类
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseDao courseDao;

    /*
     * @return java.util.List<  Course>
     * @description: 查询全部课程列表信息
     */
    @Override
    public List<Course> getCourseList() {
        return courseDao.queryCourse();
    }

    /*
     * @param courseId
     * @return   Course
     * @description: 通过课程ID获取指定课程信息
     */
    @Override
    public   Course getCourseById(long courseId) {
        return courseDao.queryCourseById(courseId);
    }

    /*
     * @param coachId
     * @return java.util.List<Course>
     * @description: 通过coachId查询课程信息列表
     */
    @Override
    public List<Course> getCourseByCoachId(long coachId) {
        return courseDao.queryCourseByCoachId(coachId);
    }

    /*
     * @param courseRecordId
     * @return   Course
     * @description: 通过课程ID获取会员列表信息
     */
    /*@Override
    public List<Course> getCoachByCourseId(long CourseId) {
        return courseDao.queryCoachByCourseId(CourseId);
    }*/

    /*
     * @param course
     * @param fileHolder
     * @return   CourseExecution
     * @description: 新增课程信息
     */
    @Override
    public CourseExecution addCourse(Course course) {
        //空值判断
        if (course == null ){
            return new CourseExecution(CourseStateEnum.EMPTY);
        }
        try {
            //设置创建时间
            course.setCreateTime(new Date());
            //添加课程信息
            int effectedNum = courseDao.addCourse(course);
            //判断是否添加成功
            if (effectedNum <= 0){
                throw new CourseOperationException("添加课程信息失败");
            }
        }catch (Exception e){
            throw new CourseOperationException("addCourse error:" + e.getMessage());
        }
        return new CourseExecution(CourseStateEnum.SUCCESS,course);
    }

    /*
     * @param course
     * @return   CourseExecution
     * @description: 修改课程信息
     */
    @Override
    public CourseExecution modifyCourse(Course course) {
        //空值判断
        if (course == null || course.getCourseId() == null){
            return new CourseExecution(CourseStateEnum.EMPTY);
        }
        try {
            //修改课程信息
            int effectedNum = courseDao.modifyCourse(course);
            //判断是否修改成功
            if (effectedNum <= 0){
                return new CourseExecution(CourseStateEnum.INNER_ERROR);
            }else {
                course = courseDao.queryCourseById(course.getCourseId());
                return new CourseExecution(CourseStateEnum.SUCCESS,course);
            }
        }catch (Exception e){
            throw new CourseOperationException("modifyCourseError:" + e.getMessage());
        }
    }

    /*
     * @param courseId
     * @return   CourseExecution
     * @description: 删除指定课程信息
     */
    @Override
    public CourseExecution deleteCourse(long courseId) {
        // 删除该课程信息
        try {
            int effectedNum = courseDao.deleteCourse(courseId);
            //判断是否删除成功
            if (effectedNum <= 0) {
                throw new CourseOperationException("课程信息删除失败");
            } else {
                return new CourseExecution(CourseStateEnum.SUCCESS);
            }
        } catch (Exception e) {
            throw new CourseOperationException("deleteCourse error:" + e.getMessage());
        }
    }
}
