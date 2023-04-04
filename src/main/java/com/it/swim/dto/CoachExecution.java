package com.it.swim.dto;

import com.it.swim.entity.Coach;
import com.it.swim.enums.CoachStateEnum;

import java.util.List;

/*
 * @description: Coach构造类
 */
public class CoachExecution {
    // 结果状态
    private int state;

    // 状态标识
    private String stateInfo;

    // 操作的coach
    private  Coach coach;

    // 获取的coach列表
    private List< Coach> coachList;

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

    public  Coach getCoach() {
        return coach;
    }

    public void setCoach( Coach coach) {
        this.coach = coach;
    }

    public List< Coach> getCoachList() {
        return coachList;
    }

    public void setCoachList(List<Coach> coachList) {
        this.coachList = coachList;
    }

    // 空的构造器
    public CoachExecution() {
    }

    // 失败的构造器
    public CoachExecution(CoachStateEnum coachStateEnum) {
        this.state = coachStateEnum.getState();
        this.stateInfo = coachStateEnum.getStateInfo();
    }

    // 成功的构造器
    public CoachExecution(CoachStateEnum coachStateEnum,  Coach coach) {
        this.state = coachStateEnum.getState();
        this.stateInfo = coachStateEnum.getStateInfo();
        this.coach = coach;
    }

    // 成功的构造器
    public CoachExecution(CoachStateEnum coachStateEnum, List< Coach> coachList) {
        this.state = coachStateEnum.getState();
        this.stateInfo = coachStateEnum.getStateInfo();
        this.coachList = coachList;
    }
}
