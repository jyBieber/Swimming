package com.it.swim.exception;

/*
 * @description: CourseRecord操作异常类
 */
public class CourseRecordOperationException extends RuntimeException{
    private static final long serialVersionUID = -4815731819042253407L;

    public CourseRecordOperationException(String msg){super(msg);}
}

