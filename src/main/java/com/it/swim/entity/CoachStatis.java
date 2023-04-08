package com.it.swim.entity;


/*
 * @description: 教练统计
 */
public class CoachStatis {
    //教练ID
    private Long coachId;
    //教练姓名
    private String coachName;
    //上课次数
    private Long num;

    public Long getCoachId() {
        return coachId;
    }

    public void setCoachId(Long coachId) {
        this.coachId = coachId;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }
}
