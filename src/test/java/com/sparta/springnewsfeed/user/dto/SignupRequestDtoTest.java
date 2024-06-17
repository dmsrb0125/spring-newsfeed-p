package com.sparta.springnewsfeed.user.dto;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.ConstructorPropertiesArbitraryIntrospector;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class SignupRequestDtoTest {

    private static Validator validator;
    private static FixtureMonkey fixtureMonkey;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        fixtureMonkey = FixtureMonkey.builder()
                .objectIntrospector(ConstructorPropertiesArbitraryIntrospector.INSTANCE)
                .build();
    }

    @Test
    @DisplayName("DTO가 올바르게 생성되고 필드 값이 정확하게 설정되는지 테스트")
    public void testDtoCreation() {
        // Given
        SignupRequestDto dto = fixtureMonkey.giveMeBuilder(SignupRequestDto.class)
                .set("userId", "validUserId123")
                .set("password", "ValidPassword123!")
                .set("email", "valid@example.com")
                .sample();

        // When & Then
        assertNotNull(dto);
        assertEquals("validUserId123", dto.getUserId());
        assertEquals("ValidPassword123!", dto.getPassword());
        assertEquals("valid@example.com", dto.getEmail());
    }


}
