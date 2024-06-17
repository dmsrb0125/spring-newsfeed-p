package com.sparta.springnewsfeed.comment;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CommentRequestDtoTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("CommentRequestDto 생성 및 유효성 검증 테스트")
    public void testCommentRequestDtoCreationAndValidation() {
        // Given
        CommentRequestDto dto = new CommentRequestDto("This is a comment.");

        // When
        Set<ConstraintViolation<CommentRequestDto>> violations = validator.validate(dto);

        // Then
        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("댓글 내용이 비어 있을 때 실패 테스트")
    public void testCommentContentsNotBlank() {
        // Given
        CommentRequestDto dto = new CommentRequestDto("");

        // When
        Set<ConstraintViolation<CommentRequestDto>> violations = validator.validate(dto);

        // Then
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("댓글 내용은 필수 입력 값입니다.", violations.iterator().next().getMessage());
    }

    @Test
    @DisplayName("getter 메서드 테스트")
    public void testGetter() {
        // Given
        CommentRequestDto dto = new CommentRequestDto("This is a comment.");

        // When & Then
        assertEquals("This is a comment.", dto.getCommentContents());
    }

    @Test
    @DisplayName("equals(), hashCode(), toString() 메서드 테스트")
    public void testEqualsHashCodeToString() {
        // Given
        CommentRequestDto dto1 = new CommentRequestDto("This is a comment.");
        CommentRequestDto dto2 = new CommentRequestDto("This is a comment.");
        CommentRequestDto dto3 = new CommentRequestDto("This is another comment.");

        // When & Then
        assertEquals(dto1, dto2);
        assertNotEquals(dto1, dto3);
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1.hashCode(), dto3.hashCode());
        assertNotNull(dto1.toString());
    }
}
