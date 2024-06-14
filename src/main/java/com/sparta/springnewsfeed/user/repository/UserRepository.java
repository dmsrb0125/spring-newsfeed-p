package com.sparta.springnewsfeed.user.repository;

import java.util.Optional;

import com.sparta.springnewsfeed.user.entity.UserStatusEnum;
import com.sparta.springnewsfeed.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserId(String userId);
    Optional<User> findByEmail(String email);
    boolean existsByUserId(String userId);
    boolean existsByUserIdAndStatus(String userId, UserStatusEnum status);
}