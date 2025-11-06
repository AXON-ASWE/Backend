package com.swe.project.services.mailService;

import jakarta.mail.MessagingException;

public interface EmailVerificationService {

    /**
     * Gửi email xác nhận tài khoản
     */
    void sendVerificationEmail(String userEmail, String fullName) throws MessagingException;

    /**
     * Xác nhận email bằng token
     */
    boolean verifyEmail(String token);

    /**
     * Lấy email từ token
     */
    String getEmailFromToken(String token);
}
