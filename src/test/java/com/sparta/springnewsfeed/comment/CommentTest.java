package com.sparta.springnewsfeed.comment;

import com.sparta.springnewsfeed.post.Post;
import com.sparta.springnewsfeed.post.PostRepository;
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
class CommentTest {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("댓글 저장 테스트")
    public void testSaveComment() {
        // given
        User user = new User();
        user.setUserId("validUser123");
        user.setPassword("Valid@1234");
        user.setEmail("valid@example.com");
        user.setStatus(UserStatusEnum.UNVERIFIED);

        // 먼저 User 엔티티를 저장
        User savedUser = userRepository.save(user);

        Post post = new Post();
        post.setTitle("Valid Title");
        post.setContent("Valid Content");
        post.setUser(savedUser);

        // 먼저 Post 엔티티를 저장
        Post savedPost = postRepository.save(post);

        Comment comment = new Comment();
        comment.setContent("This is a valid comment.");
        comment.setUser(savedUser);
        comment.setPost(savedPost);

        // when
        Comment savedComment = commentRepository.save(comment);

        // then
        assertNotNull(savedComment.getCommentId());
        assertEquals("This is a valid comment.", savedComment.getContent());
        assertEquals(savedUser, savedComment.getUser());
        assertEquals(savedPost, savedComment.getPost());
    }


}
