package com.hexaware.insurancemgmt.exception;

public class PolicyNotFoundException extends Exception {
    public PolicyNotFoundException(String message) {
        super(message);
    }
}