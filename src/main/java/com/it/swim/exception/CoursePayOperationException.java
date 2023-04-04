package com.it.swim.exception;

/*
 * @description: CoursePay操作异常类
 */
public class CoursePayOperationException extends RuntimeException{
    private static final long serialVersionUID = -7989645915942446156L;

    public CoursePayOperationException(String msg){super(msg);}
}
