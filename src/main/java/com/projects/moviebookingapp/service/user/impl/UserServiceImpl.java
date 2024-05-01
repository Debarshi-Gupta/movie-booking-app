package com.projects.moviebookingapp.service.user.impl;

import com.projects.moviebookingapp.exception.CustomRoleException;
import com.projects.moviebookingapp.exception.CustomUserException;
import com.projects.moviebookingapp.model.dto.UserDto;
import com.projects.moviebookingapp.model.entity.Role;
import com.projects.moviebookingapp.model.entity.User;
import com.projects.moviebookingapp.repository.UserRepository;
import com.projects.moviebookingapp.service.role.RoleService;
import com.projects.moviebookingapp.service.user.UserService;
import com.projects.moviebookingapp.service.user.mapper.CustomUserMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service(value = "userService")
@Log4j2
public class UserServiceImpl implements UserService, UserDetailsService{

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = null;

        try {
            log.info("Fetching User By Username..");
            user = userRepository.findByUsername(username);
            log.info("Fetched User: {}", user.toString());
        } catch (Exception e) {
            log.error("Error while fetching User");
            throw new CustomUserException("Error while Fetching User with username: "+username);
        }

        if(Objects.isNull(user))
        {
            log.error("User Not Found With Username: {}", username);
            throw new UsernameNotFoundException("Invalid Username");
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }

    @Override
    public UserDto save(UserDto userDto)
    {
        User user, savedUser = null;
        Role userRole = null;

        user = CustomUserMapper.mapToUser(userDto);
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

        try {
            log.info("Finding USER Role");
            userRole = roleService.findByName("USER");
            log.info("USER Role Fetched Successfully");
        } catch (Exception e) {
            log.error("Failed to fetch USER Role");
            throw new CustomRoleException("Failed to fetch USER Role");
        }

        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        user.setRoles(roles);

        try {
            log.info("Attempting to save User Object: {}", user.toString());
            savedUser = userRepository.save(user);
            log.info("User Saved Successfully: {}", savedUser.toString());
        } catch (Exception e) {
            log.error("Error while saving User object");
            throw new CustomUserException("Error while saving User object");
        }

        return CustomUserMapper.mapToUserDto(savedUser);
    }

    @Override
    public List<UserDto> findAll()
    {
        List<User> userList = null;

        try {
            log.info("Attempting to Fetch all Users..");
            userList = userRepository.findAll();
            log.info("All Users fetched successfully: {}", userList);
        } catch (Exception e) {
            log.error("Error while fetching all Users");
            throw new CustomUserException("Error while fetching all Users");
        }

        if(Objects.isNull(userList))
        {
            log.error("No Users found");
            throw new CustomUserException("No Users Found");
        }

        return userList.stream()
                .map(CustomUserMapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findOne(String username)
    {
        User user = null;

        try {
            log.info("Attempting to fetch User by Username");
            user = userRepository.findByUsername(username);
            log.info("User fetched successfully: {}", user.toString());
        } catch (Exception e) {
            log.error("Error while fetching User with username: {}", username);
            throw new CustomUserException("Error while fetching User");
        }

        if(Objects.isNull(user))
        {
            log.error("No User Found with Username: "+username);
            throw new CustomUserException("No User Found with Username: "+username);
        }

        return CustomUserMapper.mapToUserDto(user);
    }
}
