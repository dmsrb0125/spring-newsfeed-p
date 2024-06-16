package com.sparta.springnewsfeed.user.entity;

import com.sparta.springnewsfeed.user.repository.UserRepository;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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


}
