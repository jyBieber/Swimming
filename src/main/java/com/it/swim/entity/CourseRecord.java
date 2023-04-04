package com.it.swim.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/*
 * @description: 会员上课记录实体类
 */
public class CourseRecord {
    //会员上课记录ID
    private Long courseRecordId;
    //是否上课
    private Integer isAttend;
    //备注
    private String remarks;

    //缴费记录
    private CoursePay coursePay;
    //课程
    private Course course;

    public Long getCourseRecordId() {
        return courseRecordId;
    }

    public void setCourseRecordId(Long courseRecordId) {
        this.courseRecordId = courseRecordId;
    }

    public Integer getIsAttend() {
        return isAttend;
    }

    public void setIsAttend(Integer isAttend) {
        this.isAttend = isAttend;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public CoursePay getCoursePay() {
        return coursePay;
    }

    public void setCoursePay(CoursePay coursePay) {
        this.coursePay = coursePay;
    }
}
