package com.projects.moviebookingapp.service.user.impl;

import com.projects.moviebookingapp.model.dto.UserDto;
import com.projects.moviebookingapp.model.entity.User;
import com.projects.moviebookingapp.service.user.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class UserServiceImpl implements UserService {


    @Override
    public User save(UserDto user) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findOne(String username) {
        return null;
    }
}
