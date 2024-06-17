package com.sparta.springnewsfeed.post;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class PostRequestTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("DTO 생성 및 검증 테스트")
    public void testDtoCreationAndValidation() {
        // Given
        PostRequest dto = new PostRequest("Valid Title", "Valid Content");

        // When & Then
        assertEquals("Valid Title", dto.getTitle());
        assertEquals("Valid Content", dto.getContent());
    }

    @Test
    @DisplayName("Title이 빈 문자열일 경우 실패 테스트")
    public void testTitleNotBlank() {
        // Given
        PostRequest dto = new PostRequest("", "Valid Content");

        // When
        Set<ConstraintViolation<PostRequest>> violations = validator.validate(dto);

        // Then
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("Title is mandatory", violations.iterator().next().getMessage());
    }

    @Test
    @DisplayName("Content가 null일 경우 실패 테스트")
    public void testContentNotNull() {
        // Given
        PostRequest dto = new PostRequest("Valid Title", null);

        // When
        Set<ConstraintViolation<PostRequest>> violations = validator.validate(dto);

        // Then
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("Content is mandatory", violations.iterator().next().getMessage());
    }

    @Test
    @DisplayName("Getter 메서드 테스트")
    public void testGetter() {
        // Given
        PostRequest dto = new PostRequest("Valid Title", "Valid Content");

        // When & Then
        assertEquals("Valid Title", dto.getTitle());
        assertEquals("Valid Content", dto.getContent());
    }

    @Test
    @DisplayName("equals(), hashCode(), toString() 메서드 테스트")
    public void testEqualsHashCodeToString() {
        // Given
        PostRequest dto1 = new PostRequest("Title", "Content");
        PostRequest dto2 = new PostRequest("Title", "Content");

        // When & Then
        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertEquals("PostRequest(title=Title, content=Content)", dto1.toString());
    }
}
