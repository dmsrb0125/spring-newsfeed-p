package com.sparta.springnewsfeed.user.entity;

import com.sparta.springnewsfeed.comment.Comment;
import com.sparta.springnewsfeed.common.Timestamped;
import com.sparta.springnewsfeed.follow.Follow;
import com.sparta.springnewsfeed.like.CommentLike;
import com.sparta.springnewsfeed.like.PostLike;
import com.sparta.springnewsfeed.post.Post;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user")
public class User extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "User ID is mandatory")
    @Column(nullable = false, unique = true)
    private String userId;

    @NotBlank(message = "Password is mandatory")
    @Column(nullable = false)
    private String password;


    @NotBlank(message = "Email is mandatory")
    @Column(nullable = false)
    private String email;

    @Column
    private String name;

    @Column
    private String introduction;

    @Column
    private String pictureURL;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserStatusEnum status;

    @Column
    private String refreshToken;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private EmailVerification emailVerification;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PostLike> postLikes = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CommentLike> commentLikes = new ArrayList<>();

    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Follow> following = new ArrayList<>();

    @OneToMany(mappedBy = "followed", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Follow> followers = new ArrayList<>();

    public User(String userId, String password, String email) {
        this.userId = userId;
        this.password = password;
        this.email = email;
    }
}
