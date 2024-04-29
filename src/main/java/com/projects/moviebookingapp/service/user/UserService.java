package com.projects.moviebookingapp.service.user;

import com.projects.moviebookingapp.model.dto.UserDto;
import com.projects.moviebookingapp.model.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService {

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    UserDto save(UserDto userDto);

    List<UserDto> findAll();

    UserDto findOne(String username);
}
