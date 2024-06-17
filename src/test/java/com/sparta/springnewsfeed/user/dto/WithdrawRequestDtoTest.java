package com.sparta.springnewsfeed.user.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

public class WithdrawRequestDtoTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("비밀번호 필수 입력 테스트")
    public void testPasswordNotBlank() {
        // Given
        WithdrawRequestDto dto = new WithdrawRequestDto("");

        // When
        Set<ConstraintViolation<WithdrawRequestDto>> violations = validator.validate(dto);

        // Then
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("비밀번호는 필수 입력 값입니다.", violations.iterator().next().getMessage());
    }

    @Test
    @DisplayName("DTO 생성 및 검증 테스트")
    public void testDtoCreationAndValidation() {
        // Given
        WithdrawRequestDto dto = new WithdrawRequestDto("ValidPassword123!");

        // When
        Set<ConstraintViolation<WithdrawRequestDto>> violations = validator.validate(dto);

        // Then
        assertTrue(violations.isEmpty());
        assertEquals("ValidPassword123!", dto.getPassword());
    }

    @Test
    @DisplayName("Getter 메서드 테스트")
    public void testGetter() {
        // Given
        WithdrawRequestDto dto = new WithdrawRequestDto("ValidPassword123!");

        // When & Then
        assertEquals("ValidPassword123!", dto.getPassword());
    }

    @Test
    @DisplayName("equals(), hashCode(), toString() 메서드 테스트")
    public void testEqualsHashCodeToString() {
        // Given
        WithdrawRequestDto dto1 = new WithdrawRequestDto("ValidPassword123!");
        WithdrawRequestDto dto2 = new WithdrawRequestDto("ValidPassword123!");

        // When & Then
        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertEquals("WithdrawRequestDto(password=ValidPassword123!)", dto1.toString());
    }
}
