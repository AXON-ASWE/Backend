package com.swe.project.services.mailService;

import com.swe.project.entities.verification.VerificationToken;
import com.swe.project.repositories.VerificationTokenRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailVerificationServiceImpl implements EmailVerificationService {

    private final JavaMailSender mailSender;
    private final VerificationTokenRepository verificationTokenRepository;
    private final Logger log = LoggerFactory.getLogger(EmailVerificationServiceImpl.class);

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Value("${application.frontend.url:http://localhost:3000}")
    private String frontendUrl;

    @Value("${application.backend.url:http://localhost:8080}")
    private String backendUrl;

    @Value("${application.email.verification.expiry:24}")
    private Integer expiryHours;

    @Override
    @Transactional
    public void sendVerificationEmail(String userEmail, String fullName) throws MessagingException {
        try {
            // Xóa token cũ nếu có
            verificationTokenRepository.deleteByUserEmail(userEmail);

            // Tạo token xác nhận
            String token = UUID.randomUUID().toString();
            LocalDateTime expiryDate = LocalDateTime.now().plusHours(expiryHours);

            VerificationToken verificationToken = VerificationToken.builder()
                    .token(token)
                    .userEmail(userEmail)
                    .expiryDate(expiryDate)
                    .isUsed(false)
                    .build();

            verificationTokenRepository.save(verificationToken);

            // Tạo link xác nhận
            String verificationLink = backendUrl + "/api/patient/mail/verify?token=" + token;

            // Gửi email
            sendEmail(userEmail, fullName, verificationLink);

            log.info("Verification email sent to: {}", userEmail);
        } catch (Exception e) {
            log.error("Failed to send verification email to {}: {}", userEmail, e.getMessage());
            throw new MessagingException("Gửi email xác nhận thất bại: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean verifyEmail(String token) {
        try {
            Optional<VerificationToken> verificationTokenOpt = verificationTokenRepository.findByToken(token);

            if (verificationTokenOpt.isEmpty()) {
                log.warn("Verification token not found: {}", token);
                return false;
            }

            VerificationToken verificationToken = verificationTokenOpt.get();

            // Kiểm tra token hết hạn
            if (verificationToken.isExpired()) {
                log.warn("Verification token expired: {}", token);
                return false;
            }

            // Kiểm tra token đã được sử dụng
            if (verificationToken.getIsUsed()) {
                log.warn("Verification token already used: {}", token);
                return false;
            }

            // Đánh dấu token đã được sử dụng
            verificationTokenRepository.markTokenAsUsed(verificationToken.getId(), LocalDateTime.now());

            log.info("Email verified successfully for: {}", verificationToken.getUserEmail());
            return true;

        } catch (Exception e) {
            log.error("Error verifying email with token {}: {}", token, e.getMessage());
            return false;
        }
    }

    @Override
    public String getEmailFromToken(String token) {
        Optional<VerificationToken> verificationTokenOpt = verificationTokenRepository.findByToken(token);

        if (verificationTokenOpt.isEmpty()) {
            log.warn("Verification token not found: {}", token);
            return null;
        }

        return verificationTokenOpt.get().getUserEmail();
    }

    /**
     * Gửi email HTML xác nhận
     */
    private void sendEmail(String toEmail, String fullName, String verificationLink) throws MessagingException {
        EmailVerificationTemplate template = new EmailVerificationTemplate(
                fromEmail,
                toEmail,
                mailSender,
                fullName,
                verificationLink,
                expiryHours
        );
        template.send();
    }
}
