package com.it.swim.dto;

import com.it.swim.entity.CourseRecord;
import com.it.swim.enums.CourseRecordStateEnum;

import java.util.List;

/*
 * @description: CourseRecord构造类
 */
public class CourseRecordExecution {
    // 结果状态
    private int state;

    // 状态标识
    private String stateInfo;

    // 操作的courseRecord
    private CourseRecord courseRecord;

    // 获取的courseRecord列表
    private List<CourseRecord> courseRecordList;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public CourseRecord getCourseRecord() {
        return courseRecord;
    }

    public void setCourseRecord(CourseRecord courseRecord) {
        this.courseRecord = courseRecord;
    }

    public List<CourseRecord> getCourseRecordList() {
        return courseRecordList;
    }

    public void setCourseRecordList(List<CourseRecord> courseRecordList) {
        this.courseRecordList = courseRecordList;
    }

    // 空的构造器
    public CourseRecordExecution() {
    }

    // 失败的构造器
    public CourseRecordExecution(CourseRecordStateEnum courseRecordStateEnum) {
        this.state = courseRecordStateEnum.getState();
        this.stateInfo = courseRecordStateEnum.getStateInfo();
    }

    // 成功的构造器
    public CourseRecordExecution(CourseRecordStateEnum courseRecordStateEnum, CourseRecord courseRecord) {
        this.state = courseRecordStateEnum.getState();
        this.stateInfo = courseRecordStateEnum.getStateInfo();
        this.courseRecord = courseRecord;
    }

    // 成功的构造器
    public CourseRecordExecution(CourseRecordStateEnum courseRecordStateEnum, List<CourseRecord> courseRecordList) {
        this.state = courseRecordStateEnum.getState();
        this.stateInfo = courseRecordStateEnum.getStateInfo();
        this.courseRecordList = courseRecordList;
    }
}
