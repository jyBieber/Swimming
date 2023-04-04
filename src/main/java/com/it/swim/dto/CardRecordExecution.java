package com.it.swim.dto;

import com.it.swim.entity.CardRecord;
import com.it.swim.enums.CardRecordStateEnum;

import java.util.List;

/*
 * @description: CardRecord构造类
 */
public class CardRecordExecution {
    // 结果状态
    private int state;

    // 状态标识
    private String stateInfo;

    // 操作的cardRecord
    private CardRecord cardRecord;

    // 获取的cardRecord列表
    private List<CardRecord> cardRecordList;

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

    public CardRecord getCardRecord() { return cardRecord; }

    public void setCardRecord(CardRecord cardRecord) {
        this.cardRecord = cardRecord;
    }

    public List<CardRecord> getCardRecordList() { return cardRecordList; }

    public void setCardRecordList(List<CardRecord> cardRecordList) {
        this.cardRecordList = cardRecordList;
    }

    // 空的构造器
    public CardRecordExecution() {
    }

    // 失败的构造器
    public CardRecordExecution(CardRecordStateEnum cardRecordStateEnum) {
        this.state = cardRecordStateEnum.getState();
        this.stateInfo = cardRecordStateEnum.getStateInfo();
    }

    // 成功的构造器
    public CardRecordExecution(CardRecordStateEnum cardRecordStateEnum, CardRecord cardRecord) {
        this.state = cardRecordStateEnum.getState();
        this.stateInfo = cardRecordStateEnum.getStateInfo();
        this.cardRecord = cardRecord;
    }

    // 成功的构造器
    public CardRecordExecution(CardRecordStateEnum cardRecordStateEnum, List<CardRecord> cardRecordList) {
        this.state = cardRecordStateEnum.getState();
        this.stateInfo = cardRecordStateEnum.getStateInfo();
        this.cardRecordList = cardRecordList;
    }
}
