package com.it.swim.exception;

/*
 * @description: Vip操作异常类
 */
public class VipOperationException extends RuntimeException{
    private static final long serialVersionUID = -6415835066202472815L;

    public VipOperationException(String msg){super(msg);}
}
