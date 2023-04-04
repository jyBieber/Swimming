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
    //上课时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private Date createTime;
    //是否上课
    private Integer isAttend;
    //备注
    private String remarks;

    //缴费记录
    private CoursePay coursePay;
    //教练
    private Coach coach;

    public Long getCourseRecordId() {
        return courseRecordId;
    }

    public void setCourseRecordId(Long courseRecordId) {
        this.courseRecordId = courseRecordId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public CoursePay getCoursePay() {
        return coursePay;
    }

    public void setCoursePay(CoursePay coursePay) {
        this.coursePay = coursePay;
    }
}
