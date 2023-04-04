package com.it.swim.dao;

import com.it.swim.entity.Course;

import java.util.List;

/*
 * @description: 课程实体类dao层接口
 */
public interface CourseDao {
    /*
     * @description: 查询所有课程列表
     * @param
     * @return java.util.List< Course>
     */
    List<Course> queryCourse();

    /*
     * @description: 通过courseId查询指定课程信息
     * @param courseId
     * @return  Course
     */
    Course queryCourseById(long courseId);
    /*
     * @description: 通过coachId查询课程信息列表
     * @param coachId
     * @return java.util.List< Course>
     */
    List<Course> queryCourseByCoachId(long coachId);

    /*
     * @description: 通过courseId查询教练信息列表
     * @param courseId
     * @return  Course
     */
    /*List<Course> queryCoachByCourseId(long courseId);*/
    /*
     * @description: 新增课程信息
     * @param course
     * @return int
     */
    int addCourse(Course course);

    /*
     * @description: 修改课程信息
     * @param course
     * @return int
     */
    int modifyCourse(Course course);

    /*
     * @description: 通过courseId删除课程信息
     * @param courseId
     * @return int
     */
    int deleteCourse(long courseId);
}
