package com.it.swim.dto;

import com.it.swim.entity.CoursePay;
import com.it.swim.enums.CoursePayStateEnum;

import java.util.List;

/*
 * @description: CoursePay构造类
 */
public class CoursePayExecution{
    // 结果状态
    private int state;

    // 状态标识
    private String stateInfo;

    // 操作的CoursePay
    private CoursePay CoursePay;

    // 获取的CoursePay列表
    private List<CoursePay> CoursePayList;

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

    public CoursePay getCoursePay() { return CoursePay; }

    public void setCoursePay( CoursePay CoursePay) {
        this.CoursePay = CoursePay;
    }

    public List<CoursePay> getCoursePayList() { return CoursePayList; }

    public void setCoursePayList(List<CoursePay> CoursePayList) {
        this.CoursePayList = CoursePayList;
    }

    // 空的构造器
    public CoursePayExecution() {
    }

    // 失败的构造器
    public CoursePayExecution(CoursePayStateEnum CoursePayStateEnum) {
        this.state = CoursePayStateEnum.getState();
        this.stateInfo = CoursePayStateEnum.getStateInfo();
    }

    // 成功的构造器
    public CoursePayExecution(CoursePayStateEnum CoursePayStateEnum, CoursePay CoursePay) {
        this.state = CoursePayStateEnum.getState();
        this.stateInfo = CoursePayStateEnum.getStateInfo();
        this.CoursePay = CoursePay;
    }

    // 成功的构造器
    public CoursePayExecution(CoursePayStateEnum CoursePayStateEnum, List<CoursePay> CoursePayList) {
        this.state = CoursePayStateEnum.getState();
        this.stateInfo = CoursePayStateEnum.getStateInfo();
        this.CoursePayList = CoursePayList;
    }
}
