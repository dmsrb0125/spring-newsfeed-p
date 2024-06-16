package com.sparta.springnewsfeed.user.entity;

import com.sparta.springnewsfeed.user.repository.UserRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserTest {
    @Autowired
    private UserRepository userRepository;

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("사용자 저장 테스트")
    public void testSaveUser() {
        // given
        User user = new User();
        user.setUserId("validUser123");
        user.setPassword("Valid@1234");
        user.setEmail("valid@example.com");
        user.setStatus(UserStatusEnum.UNVERIFIED);

        // when
        User savedUser = userRepository.save(user);

        // then
        assertNotNull(savedUser.getId());
        assertEquals("validUser123", savedUser.getUserId());
        assertEquals("Valid@1234", savedUser.getPassword());
        assertEquals("valid@example.com", savedUser.getEmail());
        assertEquals(UserStatusEnum.UNVERIFIED, savedUser.getStatus());
    }

    @Test
    @DisplayName("유저 아이디 데이터가 비어있을 경우 실패")
    public void testUserIdConstraint() {
        // given
        User user = new User();
        user.setUserId(""); // Invalid userId
        user.setPassword("Valid@1234");
        user.setEmail("valid@example.com");
        user.setStatus(UserStatusEnum.UNVERIFIED);

        // when
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        // then
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("User ID is mandatory")));
    }




}
