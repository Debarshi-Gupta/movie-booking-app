package com.projects.moviebookingapp.service.user;

import com.projects.moviebookingapp.model.dto.UserDto;
import com.projects.moviebookingapp.model.entity.User;

import java.util.List;

public interface UserService {

    User save(UserDto user);

    List<User> findAll();

    User findOne(String username);
}
