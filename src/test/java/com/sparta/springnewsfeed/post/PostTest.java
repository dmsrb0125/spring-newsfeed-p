package com.sparta.springnewsfeed.post;

import com.sparta.springnewsfeed.user.entity.User;

import com.sparta.springnewsfeed.user.entity.UserStatusEnum;
import com.sparta.springnewsfeed.user.repository.UserRepository;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


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


}
