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


public class UpdatePasswordRequestDtoTest {

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
    @DisplayName("현재 비밀번호 빈 문자열 입력시 실패 테스트")
    public void testCurrentPasswordNotBlank() {
        // Given
        UpdatePasswordRequestDto dto = new UpdatePasswordRequestDto("", "NewValidPassword123!");

        // When
        Set<ConstraintViolation<UpdatePasswordRequestDto>> violations = validator.validate(dto);

        // Then
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("현재 비밀번호는 필수 입력 값입니다.", violations.iterator().next().getMessage());
    }

    @Test
    @DisplayName("새 비밀번호 빈 문자열 입력시 실패 테스트")
    public void testNewPasswordNotBlank() {
        // Given
        UpdatePasswordRequestDto dto = new UpdatePasswordRequestDto("CurrentPassword123!", "");

        // When
        Set<ConstraintViolation<UpdatePasswordRequestDto>> violations = validator.validate(dto);

        // Then
        assertFalse(violations.isEmpty());
        assertEquals(3, violations.size());
        assertEquals("새 비밀번호는 최소 10글자 이상이어야 합니다.", violations.iterator().next().getMessage());
    }

    @Test
    @DisplayName("새 비밀번호 최소 길이 및 패턴 검증 실패 테스트")
    public void testNewPasswordConstraints() {
        // Given
        UpdatePasswordRequestDto dto = new UpdatePasswordRequestDto("CurrentPassword123!", "short");

        // When
        Set<ConstraintViolation<UpdatePasswordRequestDto>> violations = validator.validate(dto);

        // Then
        assertFalse(violations.isEmpty());
        assertEquals(2, violations.size());
    }

    @Test
    @DisplayName("DTO 자동 생성 및 검증 테스트")
    public void testDtoCreationAndValidation() {
        // Given
        UpdatePasswordRequestDto dto = fixtureMonkey.giveMeBuilder(UpdatePasswordRequestDto.class)
                .set("currentPassword", "CurrentPassword123!")
                .set("newPassword", "NewValidPassword123!")
                .sample();

        // When
        Set<ConstraintViolation<UpdatePasswordRequestDto>> violations = validator.validate(dto);

        // Then
        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("getter 메서드 테스트")
    public void testGetter() {
        // Given
        UpdatePasswordRequestDto dto = new UpdatePasswordRequestDto("CurrentPassword123!", "NewValidPassword123!");

        // When & Then
        assertEquals("CurrentPassword123!", dto.getCurrentPassword());
        assertEquals("NewValidPassword123!", dto.getNewPassword());
    }

    @Test
    @DisplayName("equals(), hashCode(), toString() 메서드 테스트")
    public void testEqualsAndHashCodeAndToString() {
        // Given
        UpdatePasswordRequestDto dto1 = new UpdatePasswordRequestDto("CurrentPassword123!", "NewValidPassword123!");
        UpdatePasswordRequestDto dto2 = new UpdatePasswordRequestDto("CurrentPassword123!", "NewValidPassword123!");
        UpdatePasswordRequestDto dto3 = new UpdatePasswordRequestDto("OtherCurrentPassword123!", "OtherNewPassword123!");

        // equals() 및 hashCode() 테스트
        assertEquals(dto1, dto2);
        assertNotEquals(dto1, dto3);
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1.hashCode(), dto3.hashCode());

        // toString() 테스트
        String toString = dto1.toString();
        assertTrue(toString.contains("CurrentPassword123!"));
        assertTrue(toString.contains("NewValidPassword123!"));
    }
}
