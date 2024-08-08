package com.qeemaTask.exceptions;

public class OrderItemServiceException  extends RuntimeException{

    public OrderItemServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
