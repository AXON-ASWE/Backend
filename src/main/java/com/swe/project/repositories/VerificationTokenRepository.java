package com.swe.project.repositories;

import com.swe.project.entities.verification.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Integer> {

    /**
     * Tìm token xác nhận bằng token string
     */
    Optional<VerificationToken> findByToken(String token);

    /**
     * Tìm token xác nhận chưa được sử dụng bằng email
     */
    Optional<VerificationToken> findByUserEmailAndIsUsedFalse(String userEmail);

    /**
     * Kiểm tra token đã tồn tại hay chưa
     */
    boolean existsByToken(String token);

    /**
     * Xóa tất cả token hết hạn và chưa được sử dụng
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM VerificationToken vt WHERE vt.expiryDate < :now AND vt.isUsed = false")
    void deleteExpiredTokens(@Param("now") LocalDateTime now);

    /**
     * Xóa token theo email
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM VerificationToken vt WHERE vt.userEmail = :userEmail")
    void deleteByUserEmail(@Param("userEmail") String userEmail);

    /**
     * Cập nhật token đã được sử dụng
     */
    @Modifying
    @Transactional
    @Query("UPDATE VerificationToken vt SET vt.isUsed = true, vt.verifiedAt = :verifiedAt WHERE vt.id = :tokenId")
    void markTokenAsUsed(@Param("tokenId") Integer tokenId, @Param("verifiedAt") LocalDateTime verifiedAt);
}
