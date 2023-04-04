package com.it.swim.exception;

/*
 * @description: CardRecord操作异常类
 */
public class CardRecordOperationException extends RuntimeException{
    private static final long serialVersionUID = 1110885646012316735L;

    public CardRecordOperationException(String msg){super(msg);}
}
