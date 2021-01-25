package com.zhanysh.pam.exception;

/**
 * Custom exception if customer not found in database.
 */
public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(Long id) {
        super(String.format("Customer with Id %d not found", id));
    }
}
