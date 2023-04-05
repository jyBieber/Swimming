package com.it.swim.service;

import com.it.swim.dto.CourseExecution;
import com.it.swim.entity.Course;

import java.util.List;

public interface CourseService {
    /*
     * @description: 查询全部课程列表信息
     * @param
     * @return java.util.List< Course>
     */
    List<Course> getCourseList();

    /*
     * @description: 通过课程ID获取指定课程信息
     * @param courseId
     * @return  Course
     */
     Course getCourseById(long courseId);
    /*
     * @description: 通过coachId查询课程信息列表
     * @param coachId
     * @return java.util.List<Course>
     */
    List<Course> getCourseByCoachId(long coachId);

    /*
     * @description: 新增课程信息
     * @param course
     * @param fileHolder
     * @return  CourseExecution
     */
    CourseExecution addCourse(Course course);

    /*
     * @description: 修改课程信息
     * @param course
     * @param fileHolder
     * @return  CourseExecution
     */
    CourseExecution modifyCourse(Course course);

    /*
     * @description: 删除指定课程信息
     * @param courseId
     * @return  CourseExecution
     */
    CourseExecution deleteCourse(long courseId);
}
