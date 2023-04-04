package com.it.swim.exception;

/*
 * @description: Course操作异常类
 */
public class CourseOperationException extends RuntimeException{
    private static final long serialVersionUID = 8353226200023490354L;

    public CourseOperationException(String msg){super(msg);}
}
