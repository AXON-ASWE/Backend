package com.swe.project.exception;

import com.swe.project.constant.Constants;

public class UserWrongPasswordException extends RuntimeException {
    public UserWrongPasswordException() {
        super(Constants.INVALID_PASSWORD);
    }
}
