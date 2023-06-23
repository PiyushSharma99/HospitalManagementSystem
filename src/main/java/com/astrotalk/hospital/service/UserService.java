package com.astrotalk.hospital.service;

import java.util.List;

import com.astrotalk.hospital.dto.UserDto;
import com.astrotalk.hospital.model.User;

public interface UserService {
	
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}