package com.it.swim.enums;

/*
 * @description: Coach状态枚举类
 */
public enum CoachStateEnum {
    /*
     * @description: 状态枚举
     */
    SUCCESS(1, "创建成功"),
    INNER_ERROR(-1001, "操作失败"),
    EMPTY(-1002, "教练信息为空");

    private int state;
    private String stateInfo;

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    private CoachStateEnum(int state, String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }

    /*
     * @description: 依据传入的state返回相应的enum值
     * @param state
     * @return CoachStateEnum
     */
    public static CoachStateEnum stateOf(int state){
        for (CoachStateEnum stateEnum : values()){
            if (stateEnum.getState() == state){
                return stateEnum;
            }
        }
        return null;
    }
}
