package com.it.swim.exception;

/*
 * @description: VipCard操作异常类
 */
public class VipCardOperationException extends RuntimeException{
    private static final long serialVersionUID = 8353226200023490354L;

    public VipCardOperationException(String msg){super(msg);}
}
