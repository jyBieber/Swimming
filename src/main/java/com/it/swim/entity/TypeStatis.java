package com.it.swim.entity;


/*
 * @description: 营业额统计
 */
public class TypeStatis {

    //类别名称
    private String typeName;
    //营业额
    private Long money;


    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }
}
