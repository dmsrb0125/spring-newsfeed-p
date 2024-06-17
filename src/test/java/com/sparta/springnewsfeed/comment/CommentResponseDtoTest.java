package com.sparta.springnewsfeed.comment;

import com.sparta.springnewsfeed.post.Post;
import com.sparta.springnewsfeed.user.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class CommentResponseDtoTest {

    private Comment comment;
    private Post post;
    private User user;

    @BeforeEach
    public void setUp() {
        // Mocking the Post entity
        post = Mockito.mock(Post.class);
        Mockito.when(post.getId()).thenReturn(1L);

        // Mocking the User entity
        user = Mockito.mock(User.class);
        Mockito.when(user.getName()).thenReturn("Test User");

        // Mocking the Comment entity
        comment = Mockito.mock(Comment.class);
        Mockito.when(comment.getPost()).thenReturn(post);
        Mockito.when(comment.getCommentId()).thenReturn(1L);
        Mockito.when(comment.getUser()).thenReturn(user);
        Mockito.when(comment.getContent()).thenReturn("This is a comment.");
        Mockito.when(comment.getCreatedAt()).thenReturn(LocalDateTime.of(2023, 6, 15, 12, 0));
        Mockito.when(comment.getModifiedAt()).thenReturn(LocalDateTime.of(2023, 6, 16, 12, 0));
    }

    @Test
    @DisplayName("CommentResponseDto 생성 및 필드 값 검증 테스트")
    public void testCommentResponseDtoCreation() {
        // Given
        CommentResponseDto dto = new CommentResponseDto(comment);

        // When & Then
        assertEquals(1L, dto.getCommentId());
        assertEquals("Test User", dto.getUsername());
        assertEquals("This is a comment.", dto.getCommentContents());
        assertEquals(1L, dto.getPostId());
        assertEquals(LocalDateTime.of(2023, 6, 15, 12, 0), dto.getCreatedAt());
        assertEquals(LocalDateTime.of(2023, 6, 16, 12, 0), dto.getUpdatedAt());
    }

    @Test
    @DisplayName("getter 메서드 테스트")
    public void testGetter() {
        // Given
        CommentResponseDto dto = new CommentResponseDto(comment);

        // When & Then
        assertEquals(1L, dto.getCommentId());
        assertEquals("Test User", dto.getUsername());
        assertEquals("This is a comment.", dto.getCommentContents());
        assertEquals(1L, dto.getPostId());
        assertEquals(LocalDateTime.of(2023, 6, 15, 12, 0), dto.getCreatedAt());
        assertEquals(LocalDateTime.of(2023, 6, 16, 12, 0), dto.getUpdatedAt());
    }

    @Test
    @DisplayName("equals(), hashCode(), toString() 메서드 테스트")
    public void testEqualsHashCodeToString() {
        // Given
        CommentResponseDto dto1 = new CommentResponseDto(comment);
        CommentResponseDto dto2 = new CommentResponseDto(comment);
        Comment anotherComment = Mockito.mock(Comment.class);
        Mockito.when(anotherComment.getPost()).thenReturn(post);
        Mockito.when(anotherComment.getCommentId()).thenReturn(2L);
        Mockito.when(anotherComment.getUser()).thenReturn(user);
        Mockito.when(anotherComment.getContent()).thenReturn("This is another comment.");
        Mockito.when(anotherComment.getCreatedAt()).thenReturn(LocalDateTime.of(2023, 6, 15, 12, 0));
        Mockito.when(anotherComment.getModifiedAt()).thenReturn(LocalDateTime.of(2023, 6, 16, 12, 0));
        CommentResponseDto dto3 = new CommentResponseDto(anotherComment);

        // When & Then
        assertEquals(dto1, dto2);
        assertNotEquals(dto1, dto3);
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1.hashCode(), dto3.hashCode());
        assertNotNull(dto1.toString());
    }
}
