package com.swe.project.entities.verification;

import com.swe.project.entities.users.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "T_VERIFICATION_TOKEN")
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TOKEN_ID")
    private Integer id;

    @Column(name = "TOKEN", nullable = false, unique = true)
    private String token;

    @Column(name = "USER_EMAIL", nullable = false)
    private String userEmail;

    @Column(name = "EXPIRY_DATE", nullable = false)
    private LocalDateTime expiryDate;

    @Column(name = "IS_USED", nullable = false)
    @Builder.Default
    private Boolean isUsed = false;

    @Column(name = "VERIFIED_AT")
    private LocalDateTime verifiedAt;

    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    /**
     * Kiểm tra token có hết hạn không
     */
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(this.expiryDate);
    }

    /**
     * Kiểm tra token có hợp lệ không (chưa hết hạn và chưa được sử dụng)
     */
    public boolean isValid() {
        return !isExpired() && !this.isUsed;
    }
}
