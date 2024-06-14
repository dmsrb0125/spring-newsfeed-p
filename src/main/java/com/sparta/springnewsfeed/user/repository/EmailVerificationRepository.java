package com.sparta.springnewsfeed.user.repository;

import com.sparta.springnewsfeed.user.entity.User;
import com.sparta.springnewsfeed.user.entity.EmailVerification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailVerificationRepository extends JpaRepository<EmailVerification, Long> {
    Optional<EmailVerification> findByCode(String code);
    Optional<EmailVerification> findByUser(User user);
}
