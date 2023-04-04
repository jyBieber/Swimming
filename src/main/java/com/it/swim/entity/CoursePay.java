package com.it.swim.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/*
 * @description: 会员卡实体类
 */
public class CoursePay {
    //会员卡号
    private Long coursePayId;
    //缴费金额
    private Integer amount;
    //充值次数
    private Integer num;
    //积分
    private Integer points;
    //是否兑换
    private Integer isRedeem;
    //兑换次数
    private Integer redeemNum;

    //激活时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private Date createTime;
    //到期时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private Date expireTime;
    //备注
    private String remarks;
    private Vip vip;

    public Long getCoursePayId() {
        return coursePayId;
    }

    public void setCoursePayId(Long coursePayId) {
        this.coursePayId = coursePayId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getIsRedeem() {
        return isRedeem;
    }

    public void setIsRedeem(Integer isRedeem) {
        this.isRedeem = isRedeem;
    }

    public Integer getRedeemNum() {
        return redeemNum;
    }

    public void setRedeemNum(Integer redeemNum) {
        this.redeemNum = redeemNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Vip getVip() {
        return vip;
    }

    public void setVip(Vip vip) {
        this.vip = vip;
    }
}
