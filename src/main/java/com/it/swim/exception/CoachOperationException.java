package com.it.swim.exception;

/*
 * @description: Coach操作异常类
 */
public class CoachOperationException extends RuntimeException{
    private static final long serialVersionUID = -5542113967321598807L;

    public CoachOperationException(String msg){super(msg);}
}
