package com.it.swim.enums;

/*
 * @description: CourseRecord状态枚举类
 */
public enum CourseRecordStateEnum {
    /*
     * @description: 状态枚举
     */
    SUCCESS(1, "创建成功"),
    INNER_ERROR(-1001, "操作失败"),
    EMPTY(-1002, "上课记录信息为空");

    private int state;
    private String stateInfo;

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    private CourseRecordStateEnum(int state, String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }

    /*
     * @description: 依据传入的state返回相应的enum值
     * @param state
     * @return CourseRecordStateEnum
     */
    public static CourseRecordStateEnum stateOf(int state){
        for (CourseRecordStateEnum stateEnum : values()){
            if (stateEnum.getState() == state){
                return stateEnum;
            }
        }
        return null;
    }
}
