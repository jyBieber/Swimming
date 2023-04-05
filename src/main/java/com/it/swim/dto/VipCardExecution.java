package com.it.swim.dto;

import com.it.swim.entity.VipCard;
import com.it.swim.enums.VipCardStateEnum;

import java.util.List;

/*
 * @description: VipCard构造类
 */
public class VipCardExecution {
    // 结果状态
    private int state;

    // 状态标识
    private String stateInfo;

    // 操作的vipCard
    private VipCard vipCard;

    // 获取的vipCard列表
    private List<VipCard> vipCardList;

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

    public VipCard getVipCard() { return vipCard; }

    public void setVipCard(VipCard vipCard) {
        this.vipCard = vipCard;
    }

    public List<VipCard> getVipCardList() { return vipCardList; }

    public void setVipCardList(List<VipCard> vipCardList) {
        this.vipCardList = vipCardList;
    }

    // 空的构造器
    public VipCardExecution() {
    }

    // 失败的构造器
    public VipCardExecution(VipCardStateEnum vipCardStateEnum) {
        this.state = vipCardStateEnum.getState();
        this.stateInfo = vipCardStateEnum.getStateInfo();
    }

    // 成功的构造器
    public VipCardExecution(VipCardStateEnum vipCardStateEnum, VipCard vipCard) {
        this.state = vipCardStateEnum.getState();
        this.stateInfo = vipCardStateEnum.getStateInfo();
        this.vipCard = vipCard;
    }

    // 成功的构造器
    public VipCardExecution(VipCardStateEnum vipCardStateEnum, List<VipCard> vipCardList) {
        this.state = vipCardStateEnum.getState();
        this.stateInfo = vipCardStateEnum.getStateInfo();
        this.vipCardList = vipCardList;
    }
}
