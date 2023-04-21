package com.it.swim.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

/*
 * @description: 会员游泳记录实体类
 */
public class CardRecord {
    //会员游泳记录ID
    private Long cardRecordId;
    //游泳时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private Date createTime;
    //备注
    private String remarks;
    //会员卡
    private VipCard vipCard;

    public Long getCardRecordId() {
        return cardRecordId;
    }

    public void setCardRecordId(Long cardRecordId) {
        this.cardRecordId = cardRecordId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public VipCard getVipCard() {
        return vipCard;
    }

    public void setVipCard(VipCard vipCard) {
        this.vipCard = vipCard;
    }
}
