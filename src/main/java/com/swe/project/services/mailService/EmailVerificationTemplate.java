package com.swe.project.services.mailService;

import jakarta.mail.MessagingException;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailVerificationTemplate extends MailTemplate {

    private final String fullName;
    private final String verificationLink;
    private final Integer expiryHours;

    public EmailVerificationTemplate(
            String fromEmail,
            String toUser,
            JavaMailSender mailSender,
            String fullName,
            String verificationLink,
            Integer expiryHours) {
        super(fromEmail, "Xác nhận tài khoản - Hệ thống Quản lý Bệnh viện", toUser, mailSender);
        this.fullName = fullName;
        this.verificationLink = verificationLink;
        this.expiryHours = expiryHours;
    }

    @Override
    public String generateContent() {
        return buildEmailContent(fullName, verificationLink, expiryHours);
    }

    /**
     * Xây dựng nội dung email HTML xác nhận
     */
    private String buildEmailContent(String fullName, String verificationLink, Integer expiryHours) {
        return """
                <!DOCTYPE html>
                <html>
                <head>
                    <meta charset="UTF-8">
                    <style>
                        body { font-family: Arial, sans-serif; line-height: 1.6; color: #333; }
                        .container { max-width: 600px; margin: 0 auto; background: #f9f9f9; padding: 20px; border-radius: 5px; }
                        .header { background: #2196F3; color: white; padding: 20px; text-align: center; border-radius: 5px 5px 0 0; }
                        .content { background: white; padding: 20px; }
                        .button { display: inline-block; background: #2196F3; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px; margin: 20px 0; }
                        .footer { background: #f0f0f0; padding: 10px; text-align: center; font-size: 12px; border-radius: 0 0 5px 5px; }
                        .warning { color: #d32f2f; font-size: 12px; margin-top: 15px; }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <div class="header">
                            <h1>Xác nhận tài khoản</h1>
                        </div>
                        <div class="content">
                            <p>Xin chào <strong>""" + fullName + """
                                </strong>,</p>
                            <p>Cảm ơn bạn đã đăng ký tài khoản tại hệ thống Quản lý Bệnh viện. Để hoàn tất quá trình đăng ký, vui lòng xác nhận địa chỉ email của bạn bằng cách click vào nút dưới đây:</p>
                            <center>
                                <a href=\"""" + verificationLink + """
                                \" class="button">Xác nhận Email</a>
                            </center>
                            <div class="warning">
                                <p><strong>⚠️ Lưu ý:</strong> Link này sẽ hết hạn trong """ + expiryHours + """
                                 giờ.</p>
                                <p>Nếu bạn không yêu cầu đăng ký, vui lòng bỏ qua email này.</p>
                            </div>
                        </div>
                        <div class="footer">
                            <p>© 2025 Hệ thống Quản lý Bệnh viện.</p>
                            <p>Email này được gửi tự động, vui lòng không trả lời.</p>
                        </div>
                    </div>
                </body>
                </html>""";
    }

    /**
     * Gửi email xác nhận
     */
    public void send() throws MessagingException {
        String content = generateContent();
        sendEmail(content);
    }
}
