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

public class UpdateProfileRequestDtoTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("름 길이 초과시 실패 테스트")
    public void testNameSizeConstraint() {
        // Given
        UpdateProfileRequestDto dto = new UpdateProfileRequestDto("이름이너무길다아아아아아", "한줄소개");

        // When
        Set<ConstraintViolation<UpdateProfileRequestDto>> violations = validator.validate(dto);

        // Then
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("이름은 최대 10글자까지 입력 가능합니다.", violations.iterator().next().getMessage());
    }

    @Test
    @DisplayName("한줄소개 길이 초과시 실패 테스트")
    public void testIntroductionSizeConstraint() {
        // Given
        UpdateProfileRequestDto dto = new UpdateProfileRequestDto("이름", "한줄소개가너무길어서제한글자를초과한다한줄소개가너무길어서제한글자를초과한다한줄소개가너무길어서제한글자를초과한다한줄소개가너무길어서제한글자를초과한다");

        // When
        Set<ConstraintViolation<UpdateProfileRequestDto>> violations = validator.validate(dto);

        // Then
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("한줄소개는 최대 20글자까지 입력 가능합니다.", violations.iterator().next().getMessage());
    }

    @Test
    @DisplayName("DTO 생성 및 검증 테스트")
    public void testDtoCreationAndValidation() {
        // Given
        UpdateProfileRequestDto dto = new UpdateProfileRequestDto("홍길동", "안녕하세요");

        // When
        Set<ConstraintViolation<UpdateProfileRequestDto>> violations = validator.validate(dto);

        // Then
        assertTrue(violations.isEmpty());
        assertEquals("홍길동", dto.getName());
        assertEquals("안녕하세요", dto.getIntroduction());
    }

    @Test
    @DisplayName("Getter 메서드 테스트")
    public void testGetter() {
        // Given
        UpdateProfileRequestDto dto = new UpdateProfileRequestDto("홍길동", "안녕하세요");

        // When & Then
        assertEquals("홍길동", dto.getName());
        assertEquals("안녕하세요", dto.getIntroduction());
    }

    @Test
    @DisplayName("equals(), hashCode(), toString() 메서드 테스트")
    public void testEqualsHashCodeToString() {
        // Given
        UpdateProfileRequestDto dto1 = new UpdateProfileRequestDto("홍길동", "안녕하세요");
        UpdateProfileRequestDto dto2 = new UpdateProfileRequestDto("홍길동", "안녕하세요");

        // When & Then
        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertEquals("UpdateProfileRequestDto(name=홍길동, introduction=안녕하세요)", dto1.toString());
    }
}
