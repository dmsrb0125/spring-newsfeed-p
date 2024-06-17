package com.sparta.springnewsfeed.user.dto;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.ConstructorPropertiesArbitraryIntrospector;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class LoginRequestDtoTest {

    private static Validator validator;
    private static FixtureMonkey fixtureMonkey;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        fixtureMonkey = FixtureMonkey.builder()
                .objectIntrospector(ConstructorPropertiesArbitraryIntrospector.INSTANCE)
                .build();
    }

    @Test
    @DisplayName("사용자 ID 빈 문자열 입력시 실패 테스트")
    public void testUserIdNotBlank() {
        // Given
        LoginRequestDto dto = new LoginRequestDto("", "ValidPassword123!");

        // When
        Set<ConstraintViolation<LoginRequestDto>> violations = validator.validate(dto);

        // Then
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("사용자 ID는 필수 입력 값입니다.", violations.iterator().next().getMessage());
    }

    @Test
    @DisplayName("비밀번호 빈 문자열 입력시 실패 테스트")
    public void testPasswordNotBlank() {
        // Given
        LoginRequestDto dto = new LoginRequestDto("validUserId", "");

        // When
        Set<ConstraintViolation<LoginRequestDto>> violations = validator.validate(dto);

        // Then
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("비밀번호는 필수 입력 값입니다.", violations.iterator().next().getMessage());
    }

    @Test
    @DisplayName("DTO 자동 생성 및 검증 테스트")
    public void testDtoCreationAndValidation() {
        // Given
        LoginRequestDto dto = fixtureMonkey.giveMeBuilder(LoginRequestDto.class)
                .set("userId", "validUserId")
                .set("password", "ValidPassword123!")
                .sample();

        // When
        Set<ConstraintViolation<LoginRequestDto>> violations = validator.validate(dto);

        // Then
        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("getter 메서드 테스트")
    public void testGetter() {
        // Given
        LoginRequestDto dto = fixtureMonkey.giveMeBuilder(LoginRequestDto.class)
                .set("userId", "validUserId")
                .set("password", "ValidPassword123!")
                .sample();

        // When & Then
        assertNotNull(dto.getUserId());
        assertNotNull(dto.getPassword());
        assertEquals("validUserId", dto.getUserId());
        assertEquals("ValidPassword123!", dto.getPassword());
    }

    @Test
    @DisplayName("equals(), hashCode(), toString() 메서드 테스트")
    public void testEqualsAndHashCodeAndToString() {
        // Given
        LoginRequestDto dto1 = new LoginRequestDto("validUserId", "ValidPassword123!");
        LoginRequestDto dto2 = new LoginRequestDto("validUserId", "ValidPassword123!");
        LoginRequestDto dto3 = new LoginRequestDto("otherUserId", "OtherPassword123!");

        // equals() 및 hashCode() 테스트
        assertEquals(dto1, dto2);
        assertNotEquals(dto1, dto3);
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1.hashCode(), dto3.hashCode());

        // toString() 테스트
        String toString = dto1.toString();
        assertTrue(toString.contains("validUserId"));
        assertTrue(toString.contains("ValidPassword123!"));
    }
}
