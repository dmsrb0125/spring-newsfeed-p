package com.sparta.springnewsfeed.post;

import com.sparta.springnewsfeed.user.entity.User;

import com.sparta.springnewsfeed.user.entity.UserStatusEnum;
import com.sparta.springnewsfeed.user.repository.UserRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostTest {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("게시물 저장 테스트")
    public void testSavePost() {
        // given
        User user = new User();
        user.setUserId("validUser123");
        user.setPassword("Valid@1234");
        user.setEmail("valid@example.com");
        user.setStatus(UserStatusEnum.UNVERIFIED);


        User savedUser = userRepository.save(user);

        Post post = new Post();
        post.setTitle("Valid Title");
        post.setContent("Valid Content");
        post.setUser(savedUser);

        // when
        Post savedPost = postRepository.save(post);

        // then
        assertNotNull(savedPost.getId());
        assertEquals("Valid Title", savedPost.getTitle());
        assertEquals("Valid Content", savedPost.getContent());
        assertEquals(savedUser, savedPost.getUser());
    }

    @Test
    @DisplayName("게시물 제목이 비어있을 경우 실패")
    public void testTitleConstraint() {
        // given
        User user = new User();
        user.setUserId("validUser123");
        user.setPassword("Valid@1234");
        user.setEmail("valid@example.com");
        user.setStatus(UserStatusEnum.UNVERIFIED);
        User savedUser = userRepository.save(user);

        Post post = new Post();
        post.setTitle("");
        post.setContent("Valid content");
        post.setUser(savedUser);

        // when
        Set<ConstraintViolation<Post>> violations = validator.validate(post);

        // then
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("게시물 내용이 비어있을 경우 실패")
    public void testContentConstraint() {
        // given
        User user = new User();
        user.setUserId("validUser123");
        user.setPassword("Valid@1234");
        user.setEmail("valid@example.com");
        user.setStatus(UserStatusEnum.UNVERIFIED);
        User savedUser = userRepository.save(user);

        Post post = new Post();
        post.setTitle("Valid title");
        post.setContent("");
        post.setUser(savedUser);

        // when
        Set<ConstraintViolation<Post>> violations = validator.validate(post);

        // then
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("Getter, Setter 메서드 테스트")
    public void testGetterSetter() {
        // given
        User user = new User();
        user.setUserId("validUser123");
        user.setPassword("Valid@1234");
        user.setEmail("valid@example.com");
        user.setStatus(UserStatusEnum.UNVERIFIED);

        Post post = new Post();
        post.setTitle("Valid Title");
        post.setContent("Valid Content");
        post.setUser(user);

        // when & then
        assertEquals("Valid Title", post.getTitle());
        assertEquals("Valid Content", post.getContent());
        assertEquals(user, post.getUser());
    }


    @Test
    @DisplayName("equals() 메서드 테스트")
    public void testEquals() {
        // given
        User user = new User();
        user.setUserId("validUser123");
        user.setPassword("Valid@1234");
        user.setEmail("valid@example.com");
        user.setStatus(UserStatusEnum.UNVERIFIED);

        Post post1 = new Post();
        post1.setTitle("Valid Title");
        post1.setContent("Valid Content");
        post1.setUser(user);

        Post post2 = new Post();
        post2.setTitle("Valid Title");
        post2.setContent("Valid Content");
        post2.setUser(user);

        // when & then
        assertEquals(post1, post2);
    }
}
