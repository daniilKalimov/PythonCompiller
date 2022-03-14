package com.itransition.rpcserver;

public class CalculatorException extends Exception{
    public CalculatorException(Throwable cause) {
        super(cause);
    }
    public CalculatorException(String message) {
        super(message);
    }

}
