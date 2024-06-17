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

public class EmailVerificationRequestDtoTest {

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
    @DisplayName("인증 코드 빈 문자열 입력시 실패 테스트")
    public void testCodeNotBlank() {
        // Given
        EmailVerificationRequestDto dto = new EmailVerificationRequestDto("");

        // When
        Set<ConstraintViolation<EmailVerificationRequestDto>> violations = validator.validate(dto);

        // Then
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("인증 코드를 입력해주세요.", violations.iterator().next().getMessage());
    }

    @Test
    @DisplayName("DTO 자동 생성 및 검증 테스트")
    public void testDtoCreationAndValidation() {
        // Given
        EmailVerificationRequestDto dto = fixtureMonkey.giveMeBuilder(EmailVerificationRequestDto.class)
                .set("code", "123456")
                .sample();

        // When
        Set<ConstraintViolation<EmailVerificationRequestDto>> violations = validator.validate(dto);

        // Then
        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("getter 메서드 테스트")
    public void testGetter() {
        // Given
        EmailVerificationRequestDto dto = fixtureMonkey.giveMeBuilder(EmailVerificationRequestDto.class)
                .set("code", "123456")
                .sample();

        // When & Then
        assertNotNull(dto.getCode());
    }

    @Test
    @DisplayName("equals(), hashCode(), toString() 메서드 테스트")
    public void testEqualsAndHashCodeAndToString() {
        // Given
        EmailVerificationRequestDto dto1 = new EmailVerificationRequestDto("123456");
        EmailVerificationRequestDto dto2 = new EmailVerificationRequestDto("123456");
        EmailVerificationRequestDto dto3 = new EmailVerificationRequestDto("654321");

        // equals() 및 hashCode() 테스트
        assertEquals(dto1, dto2);
        assertNotEquals(dto1, dto3);
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1.hashCode(), dto3.hashCode());

        // toString() 테스트
        String toString = dto1.toString();
        assertTrue(toString.contains("123456"));
    }
}
