package com.vgs.vgsrecruitment.service.serviceImpl;

import com.vgs.vgsrecruitment.model.UserEntity;
import com.vgs.vgsrecruitment.payload.BirthdayResponse;
import com.vgs.vgsrecruitment.payload.UserRequest;
import com.vgs.vgsrecruitment.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository mockUserRepository;

    private UserServiceImpl userServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        userServiceImplUnderTest = new UserServiceImpl(mockUserRepository);
    }

    @Test
    void testSaveUser() {
        // Setup
        final UserRequest userRequest = new UserRequest("Francis", "Oyiogu", "foyiogu@gmail.com", LocalDate.of(2020, 12, 25));
        final BirthdayResponse expectedResult = new BirthdayResponse("Francis Oyiogu added successfully");
        when(mockUserRepository.existsByEmail("foyiogu@gmail.com")).thenReturn(false);

        // Configure UserRepository.save(...).
        final UserEntity userEntity = new UserEntity(1L, "foyiogu@gmail.com", "Francis", "Oyiogu", LocalDate.of(2020, 12, 25));
        when(mockUserRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        // Run the test
        final BirthdayResponse result = userServiceImplUnderTest.saveUser(userRequest);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockUserRepository).save(any(UserEntity.class));
    }

    @Test
    void testUpdateUser() {
        // Setup
        final UserRequest newUserRequest = new UserRequest("Francis", "Oyu", "foyiogu@yahoo.com", LocalDate.of(2020, 12, 26));
        final BirthdayResponse expectedResult = new BirthdayResponse("Francis Oyu updated successfully");

        // Configure UserRepository.findById(...).
        final Optional<UserEntity> userEntity = Optional.of(new UserEntity(1L, "foyiogu@gmail.com", "Francis", "Oyiogu", LocalDate.of(2020, Month.DECEMBER, 25)));
        when(mockUserRepository.findById(1L)).thenReturn(userEntity);

        when(mockUserRepository.existsByEmail("foyiogu@yahoo.com")).thenReturn(false);

        // Configure UserRepository.save(...).
        final UserEntity userEntity1 = new UserEntity(1L,"foyiogu@yahoo.com" , "Francis", "Oyu", LocalDate.of(2020, Month.DECEMBER, 26));
        when(mockUserRepository.save(any(UserEntity.class))).thenReturn(userEntity1);

        // Run the test
        final BirthdayResponse result = userServiceImplUnderTest.updateUser(1L, newUserRequest);
        System.err.println(result);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockUserRepository).save(any(UserEntity.class));
    }

    @Test
    void testUpdateUser_UserRepositoryFindByIdReturnsAbsent() {

        final UserRequest newUserRequest = new UserRequest("Francis", "Oyu", "foyiogu@yahoo.com", LocalDate.of(2020, 12, 26));

        assertThatIllegalArgumentException().isThrownBy(()-> {
                    when(mockUserRepository.findById(1L)).thenReturn(Optional.empty());
                    userServiceImplUnderTest.updateUser(1L,newUserRequest);
                })
                .withMessage("User does not exist");
    }

    @Test
    void testHelloBirthday() {
        // Setup
        final BirthdayResponse expectedResult = new BirthdayResponse("Hello, A B! Your birthday was " + 1 + " days ago");

        // Configure UserRepository.findByEmail(...).
        final Optional<UserEntity> userEntity = Optional.of(new UserEntity(1L, "foyiogu@yahoo.com", "A", "B", LocalDate.of(2020, Month.DECEMBER, 25)));
        when(mockUserRepository.findByEmail("foyiogu@yahoo.com")).thenReturn(userEntity);

        // Run the test
        final BirthdayResponse result = userServiceImplUnderTest.helloBirthday("foyiogu@yahoo.com");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testHelloBirthday_UserRepositoryReturnsAbsent() {

        assertThatIllegalArgumentException().isThrownBy(()-> {
            when(mockUserRepository.findByEmail("foyiogu@yahoo.com")).thenReturn(Optional.empty());
            userServiceImplUnderTest.helloBirthday("foyiogu@yahoo.com");
                })
                .withMessage("User does not exist");
    }

    @Test
    void testIsValidEmail() {
        // Setup

        // Run the test
        final boolean result = userServiceImplUnderTest.isValidEmail("oyioguf@yahoo.com");

        // Verify the results
        assertThat(result).isTrue();
    }

    @Test
    void testExistsByMail() {
        String email = "foyiogu@yahoo.com";
        // Setup
        when(mockUserRepository.existsByEmail(email)).thenReturn(false);

        // Run the test
        final boolean result = userServiceImplUnderTest.existsByMail(email);

        // Verify the results
        assertThat(result).isFalse();
    }
}
