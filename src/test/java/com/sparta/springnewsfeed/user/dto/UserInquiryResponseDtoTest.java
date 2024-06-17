package com.sparta.springnewsfeed.user.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserInquiryResponseDtoTest {

    @Test
    @DisplayName("DTO 생성 및 검증 테스트")
    public void testDtoCreationAndValidation() {
        // Given
        UserInquiryResponseDto dto = new UserInquiryResponseDto();
        dto.setUserId("validUserId");
        dto.setName("홍길동");
        dto.setIntroduction("안녕하세요");
        dto.setEmail("valid@example.com");
        dto.setPictureURL("http://example.com/pic.jpg");

        // When & Then
        assertEquals("validUserId", dto.getUserId());
        assertEquals("홍길동", dto.getName());
        assertEquals("안녕하세요", dto.getIntroduction());
        assertEquals("valid@example.com", dto.getEmail());
        assertEquals("http://example.com/pic.jpg", dto.getPictureURL());
    }

    @Test
    @DisplayName("Getter 메서드 테스트")
    public void testGetter() {
        // Given
        UserInquiryResponseDto dto = new UserInquiryResponseDto();
        dto.setUserId("validUserId");
        dto.setName("홍길동");
        dto.setIntroduction("안녕하세요");
        dto.setEmail("valid@example.com");
        dto.setPictureURL("http://example.com/pic.jpg");

        // When & Then
        assertEquals("validUserId", dto.getUserId());
        assertEquals("홍길동", dto.getName());
        assertEquals("안녕하세요", dto.getIntroduction());
        assertEquals("valid@example.com", dto.getEmail());
        assertEquals("http://example.com/pic.jpg", dto.getPictureURL());
    }

    @Test
    @DisplayName("equals(), hashCode(), toString() 메서드 테스트")
    public void testEqualsHashCodeToString() {
        // Given
        UserInquiryResponseDto dto1 = new UserInquiryResponseDto();
        dto1.setUserId("validUserId");
        dto1.setEmail("valid@example.com");

        UserInquiryResponseDto dto2 = new UserInquiryResponseDto();
        dto2.setUserId("validUserId");
        dto2.setEmail("valid@example.com");

        // When & Then
        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertEquals("UserInquiryResponseDto(userId=validUserId, name=null, introduction=null, email=valid@example.com, pictureURL=null)", dto1.toString());
    }
}
