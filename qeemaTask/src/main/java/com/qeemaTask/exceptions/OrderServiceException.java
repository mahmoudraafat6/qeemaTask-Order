package com.qeemaTask.exceptions;

public class OrderServiceException extends RuntimeException {

    public OrderServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
