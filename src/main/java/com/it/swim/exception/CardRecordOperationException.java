package com.it.swim.exception;

/*
 * @description: CardRecord操作异常类
 */
public class CardRecordOperationException extends RuntimeException{
    private static final long serialVersionUID = -4815731819042253407L;

    public CardRecordOperationException(String msg){super(msg);}
}
