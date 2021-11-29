package com.vgs.vgsrecruitment.service.serviceImpl;

import com.vgs.vgsrecruitment.exceptions.BadRequestException;
import com.vgs.vgsrecruitment.model.UserEntity;
import com.vgs.vgsrecruitment.payload.BirthdayResponse;
import com.vgs.vgsrecruitment.payload.UserRequest;
import com.vgs.vgsrecruitment.repository.UserRepository;
import com.vgs.vgsrecruitment.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    public BirthdayResponse saveUser(UserRequest userRequest){

        if(!isValidEmail(userRequest.getEmail())){
            throw new BadRequestException("Error: Email must be valid");
        }

        if(existsByMail(userRequest.getEmail())){
            throw new BadRequestException("Error: Email is already taken!");
        }

        LocalDate birthDate = LocalDate.of(userRequest.getYear(),  Month.valueOf(userRequest.getMonth().toUpperCase()), userRequest.getDay());

        UserEntity userEntity = new UserEntity(userRequest.getEmail(), userRequest.getFirstName(), userRequest.getLastName(), birthDate);

        userRepository.save(userEntity);

        return new BirthdayResponse(userEntity.getUserName() + " added successfully");

    }

    public BirthdayResponse updateUser(Long id, UserRequest newUserRequest){

        if(!isValidEmail(newUserRequest.getEmail())){
            throw new BadRequestException("Error: Email must be valid");
        }

        UserEntity newUserEntity = userRepository.findById(id).
                orElseThrow(() ->{
                    throw new IllegalArgumentException("User does not exist");
                });

        newUserEntity.setFirstName(newUserRequest.getFirstName());
        newUserEntity.setFirstName(newUserRequest.getLastName());

        if(existsByMail(newUserRequest.getEmail())){
            throw new BadRequestException("Error: Email is already taken!");
        }else {
            newUserEntity.setEmail(newUserRequest.getEmail());
        }

        LocalDate newBirthDate = LocalDate.of(newUserRequest.getYear(),  Month.valueOf(newUserRequest.getMonth().toUpperCase()), newUserRequest.getDay());
        newUserEntity.setBirthDate(newBirthDate);

        userRepository.save(newUserEntity);

        return new BirthdayResponse( newUserEntity.getUserName() +" updated successfully");
    }

    public BirthdayResponse helloBirthday(String email){

        BirthdayResponse birthdayResponse = new BirthdayResponse();

        UserEntity userEntity = userRepository.findByEmail(email).
                orElseThrow(() ->{
                    throw new IllegalArgumentException("User does not exist");
                });

//        Birthday in the year
        LocalDate currentCelebrantYear = LocalDate.of(LocalDate.now().getYear(), userEntity.getBirthDate().getMonth(),userEntity.getBirthDate().getDayOfMonth());

        long N = DAYS.between(LocalDate.now(),currentCelebrantYear);

        if (Objects.equals(currentCelebrantYear, LocalDate.now())){
            birthdayResponse.setMessage("Hello " +userEntity.getUserName() +"! Happy birthday!");
        }else if (currentCelebrantYear.isBefore(LocalDate.now())){

            birthdayResponse.setMessage("Hello, "+ userEntity.getUserName() + "! Your birthday is in " + -N + " days" );
        }else {
            birthdayResponse.setMessage("Hello, "+ userEntity.getUserName() + "! Your birthday was " + N + " days ago" );
        }

        return birthdayResponse;
    }

    private boolean isValidEmail(String email) {
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);
        if (email == null) {
            throw new BadRequestException("Error: Email cannot be null");
        }
        Matcher m = p.matcher(email);
        return m.matches();
    }
    private boolean existsByMail(String email) {
        return userRepository.existsByEmail(email);
    }
}
