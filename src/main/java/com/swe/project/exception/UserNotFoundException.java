package com.swe.project.exception;

import com.swe.project.constant.Constants;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super(Constants.USER_NOT_FOUND);
    }
}
