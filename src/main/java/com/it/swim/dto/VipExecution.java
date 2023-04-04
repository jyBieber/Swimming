package com.it.swim.dto;

import com.it.swim.entity.Vip;
import com.it.swim.enums.VipStateEnum;

import java.util.List;

/*
 * @description: Vip构造类
 */
public class VipExecution {
    // 结果状态
    private int state;

    // 状态标识
    private String stateInfo;

    // 操作的vip
    private Vip vip;

    // 获取的vip列表
    private List<Vip> vipList;

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

    public Vip getVip() {
        return vip;
    }

    public void setVip(Vip vip) {
        this.vip = vip;
    }

    public List<Vip> getCoachList() {
        return vipList;
    }

    public void setVipList(List<Vip> vipList) {
        this.vipList = vipList;
    }

    // 空的构造器
    public VipExecution() {
    }

    // 失败的构造器
    public VipExecution(VipStateEnum vipStateEnum) {
        this.state = vipStateEnum.getState();
        this.stateInfo = vipStateEnum.getStateInfo();
    }

    // 成功的构造器
    public VipExecution(VipStateEnum vipStateEnum, Vip vip) {
        this.state = vipStateEnum.getState();
        this.stateInfo = vipStateEnum.getStateInfo();
        this.vip = vip;
    }

    // 成功的构造器
    public VipExecution(VipStateEnum vipStateEnum, List<Vip> vipList) {
        this.state = vipStateEnum.getState();
        this.stateInfo = vipStateEnum.getStateInfo();
        this.vipList = vipList;
    }
}
