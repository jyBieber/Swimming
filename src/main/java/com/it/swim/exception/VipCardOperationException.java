package com.it.swim.exception;

/*
 * @description: VipCard操作异常类
 */
public class VipCardOperationException extends RuntimeException{
    private static final long serialVersionUID = -7989645915942446156L;

    public VipCardOperationException(String msg){super(msg);}
}
