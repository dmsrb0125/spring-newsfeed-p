package com.sparta.springnewsfeed.user.service;


import com.sparta.springnewsfeed.common.ResponseCode;
import com.sparta.springnewsfeed.common.S3Uploader;
import com.sparta.springnewsfeed.user.entity.UserStatusEnum;
import com.sparta.springnewsfeed.user.dto.SignupRequestDto;
import com.sparta.springnewsfeed.user.dto.UpdatePasswordRequestDto;
import com.sparta.springnewsfeed.user.dto.UpdateProfileRequestDto;
import com.sparta.springnewsfeed.user.dto.UserInquiryResponseDto;
import com.sparta.springnewsfeed.user.entity.User;
import com.sparta.springnewsfeed.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final S3Uploader s3Uploader;


    @Autowired
    public UserService(UserRepository userRepository, S3Uploader s3Uploader, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.s3Uploader = s3Uploader;
        this.passwordEncoder = passwordEncoder;
    }


    // 회원가입
    @Transactional
    public ResponseCode signup(SignupRequestDto requestDto) {
        String userId = requestDto.getUserId();
        String password = requestDto.getPassword();
        String email = requestDto.getEmail();

        // 중복된 사용자 확인
        if (userRepository.existsByUserId(userId)) {
            throw new IllegalArgumentException(ResponseCode.DUPLICATE_ENTITY.getMessage());
        }

        // 탈퇴한 사용자 확인
        if (userRepository.existsByUserIdAndStatus(userId, UserStatusEnum.DELETED)) {
            throw new IllegalArgumentException(ResponseCode.INVALID_INPUT_VALUE.getMessage());
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(password);

        // 사용자 생성 및 저장
        User user = new User();
        user.setUserId(userId);
        user.setPassword(encodedPassword); // 암호화된 비밀번호 저장
        user.setEmail(email);
        user.setStatus(UserStatusEnum.UNVERIFIED); // 초기 상태를 UNVERIFIED로 설정

        userRepository.save(user);

        return ResponseCode.CREATED;
    }

    // 프로필 수정
    @Transactional
    public ResponseCode updateProfile(UpdateProfileRequestDto requestDto, MultipartFile profilePicture, String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // 이름과 한줄소개 업데이트
        if (requestDto.getName() != null) {
            user.setName(requestDto.getName());
        }
        if (requestDto.getIntroduction() != null) {
            user.setIntroduction(requestDto.getIntroduction());
        }

        // 프로필 사진 업로드 및 URL 설정
        if (profilePicture != null && !profilePicture.isEmpty()) {
            try {
                String profileImageUrl = s3Uploader.upload(profilePicture, "profile-pictures");
                user.setPictureURL(profileImageUrl);
            } catch (IOException e) {
                throw new IllegalArgumentException("프로필 사진 업로드 중 오류가 발생했습니다.");
            }
        }

        userRepository.save(user);
        return ResponseCode.SUCCESS;
    }

    // 비밀 번호 변경
    @Transactional
    public ResponseCode updatePassword(UpdatePasswordRequestDto requestDto, String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        if (!passwordEncoder.matches(requestDto.getCurrentPassword(), user.getPassword())) {
            return ResponseCode.UNAUTHORIZED;
        }

        if (passwordEncoder.matches(requestDto.getNewPassword(), user.getPassword())) {
            return ResponseCode.INVALID_INPUT_VALUE;
        }

        user.setPassword(passwordEncoder.encode(requestDto.getNewPassword()));
        userRepository.save(user);

        return ResponseCode.SUCCESS;
    }

    // 유저 조회
    @Transactional(readOnly = true)
    public UserInquiryResponseDto getUserProfile(String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        UserInquiryResponseDto responseDto = new UserInquiryResponseDto();
        responseDto.setUserId(user.getUserId());
        responseDto.setName(user.getName());
        responseDto.setIntroduction(user.getIntroduction());
        responseDto.setEmail(user.getEmail());
        responseDto.setPictureURL(user.getPictureURL());

        return responseDto;
    }

}