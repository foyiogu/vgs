package com.vgs.vgsrecruitment.service;

import com.vgs.vgsrecruitment.payload.BirthdayResponse;
import com.vgs.vgsrecruitment.payload.UserRequest;

public interface UserService {
     BirthdayResponse saveUser(UserRequest userRequest);

     BirthdayResponse updateUser(Long id, UserRequest newUserRequest);

     BirthdayResponse helloBirthday(String email);
}
