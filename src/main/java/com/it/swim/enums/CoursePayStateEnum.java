package com.it.swim.enums;

/*
 * @description: CoursePay状态枚举类
 */
public enum CoursePayStateEnum {
    /*
     * @description: 状态枚举
     */
    SUCCESS(1, "创建成功"),
    INNER_ERROR(-1001, "操作失败"),
    EMPTY(-1002, "课程缴费信息为空");

    private int state;
    private String stateInfo;

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    private CoursePayStateEnum(int state, String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }

    /*
     * @description: 依据传入的state返回相应的enum值
     * @param state
     * @return CoursePay
     */
    public static CoursePayStateEnum stateOf(int state){
        for (CoursePayStateEnum stateEnum : values()){
            if (stateEnum.getState() == state){
                return stateEnum;
            }
        }
        return null;
    }
}
