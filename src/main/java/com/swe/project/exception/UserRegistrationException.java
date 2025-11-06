package com.swe.project.exception;

import com.swe.project.constant.Constants;

public class UserRegistrationException extends RuntimeException {
    public UserRegistrationException(String userEmail) {
        super(
                String.format(Constants.USER_ALREADY_EXISTS, userEmail)
        );
    }
}
