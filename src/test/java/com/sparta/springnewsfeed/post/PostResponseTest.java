package com.sparta.springnewsfeed.post;
import com.sparta.springnewsfeed.user.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PostResponseTest {

    @Test
    @DisplayName("PostResponse 생성 테스트")
    public void testPostResponseCreation() {
        // Given
        User user = new User("testUser", "password123", "test@example.com");
        Post post = new Post("Test Title", "Test Content", user);

        // When
        PostResponse postResponse = new PostResponse(post);

        // Then
        assertNotNull(postResponse);
        assertEquals(post.getId(), postResponse.getPostId());
        assertEquals(post.getTitle(), postResponse.getTitle());
        assertEquals(post.getContent(), postResponse.getContent());
    }

    @Test
    @DisplayName("PostResponse 필드 값 테스트")
    public void testPostResponseFields() {
        // Given
        User user = new User("testUser", "password123", "test@example.com");
        Post post = new Post("Test Title", "Test Content", user);

        // When
        PostResponse postResponse = new PostResponse(post);

        // Then
        assertEquals(post.getId(), postResponse.getPostId());
        assertEquals("Test Title", postResponse.getTitle());
        assertEquals("Test Content", postResponse.getContent());
    }
}
