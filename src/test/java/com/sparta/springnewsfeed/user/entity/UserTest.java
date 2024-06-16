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
        user.setUserId("");
        user.setPassword("Valid@1234");
        user.setEmail("valid@example.com");
        user.setStatus(UserStatusEnum.UNVERIFIED);

        // when
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        // then
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("User ID is mandatory")));
    }

    @Test
    @DisplayName("유저 비밀번호 데이터가 비어있을 경우 실패")
    public void testPasswordConstraint() {
        // given
        User user = new User();
        user.setUserId("validUser123");
        user.setPassword("");
        user.setEmail("valid@example.com");
        user.setStatus(UserStatusEnum.UNVERIFIED);

        // when
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        // then
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Password is mandatory")));
    }

    @Test
    @DisplayName("유저 이메일 데이터가 비어있을 경우 실패")
    public void testEmailConstraint() {
        // given
        User user = new User();
        user.setUserId("validUser123");
        user.setPassword("Valid@1234");
        user.setEmail("");
        user.setStatus(UserStatusEnum.UNVERIFIED);

        // when
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        // then
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("must be a well-formed email address")));
    }

    @Test
    @DisplayName("Getter, Setter 메서드 테스트")
    public void testGetterSetter() {
        // given
        User user = new User();
        user.setUserId("validUser123");
        user.setPassword("Valid@1234");
        user.setEmail("valid@example.com");
        user.setName("홍길동");
        user.setIntroduction("동해번쩍 서해번쩍.");
        user.setPictureURL("http://example.com/pic.jpg");
        user.setStatus(UserStatusEnum.UNVERIFIED);
        user.setRefreshToken("some-refresh-token");

        // when & then
        assertEquals("validUser123", user.getUserId());
        assertEquals("Valid@1234", user.getPassword());
        assertEquals("valid@example.com", user.getEmail());
        assertEquals("홍길동", user.getName());
        assertEquals("동해번쩍 서해번쩍.", user.getIntroduction());
        assertEquals("http://example.com/pic.jpg", user.getPictureURL());
        assertEquals(UserStatusEnum.UNVERIFIED, user.getStatus());
        assertEquals("some-refresh-token", user.getRefreshToken());
    }

    @Test
    @DisplayName("equals() 메서드  테스트")
    public void testEquals() {
        // given
        User user1 = new User();
        user1.setUserId("user1");
        user1.setPassword("password1");
        user1.setEmail("user1@example.com");
        user1.setStatus(UserStatusEnum.UNVERIFIED);

        User user2 = new User();
        user2.setUserId("user1");
        user2.setPassword("password1");
        user2.setEmail("user1@example.com");
        user2.setStatus(UserStatusEnum.UNVERIFIED);

        // when & then
        assertEquals(user1, user2);
    }

    @Test
    @DisplayName("hashCode() 메서드 테스트")
    public void testHashCode() {
        // given
        User user1 = new User();
        user1.setUserId("user1");
        user1.setPassword("password1");
        user1.setEmail("user1@example.com");
        user1.setStatus(UserStatusEnum.UNVERIFIED);

        User user2 = new User();
        user2.setUserId("user1");
        user2.setPassword("password1");
        user2.setEmail("user1@example.com");
        user2.setStatus(UserStatusEnum.UNVERIFIED);

        // when & then
        assertEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    @DisplayName("toString() 메서드 테스트")
    public void testToString() {
        // given
        User user = new User();
        user.setUserId("validUser123");
        user.setPassword("Valid@1234");
        user.setEmail("valid@example.com");
        user.setName("홍길동");
        user.setIntroduction("동해번쩍 서해번쩍.");
        user.setPictureURL("http://example.com/pic.jpg");
        user.setStatus(UserStatusEnum.UNVERIFIED);
        user.setRefreshToken("some-refresh-token");

        // when
        String userString = user.toString();

        // then
        assertTrue(userString.contains("validUser123"));
        assertTrue(userString.contains("Valid@1234"));
        assertTrue(userString.contains("valid@example.com"));
        assertTrue(userString.contains("홍길동"));
        assertTrue(userString.contains("동해번쩍 서해번쩍."));
        assertTrue(userString.contains("http://example.com/pic.jpg"));
        assertTrue(userString.contains("UNVERIFIED"));
        assertTrue(userString.contains("some-refresh-token"));
    }
}
