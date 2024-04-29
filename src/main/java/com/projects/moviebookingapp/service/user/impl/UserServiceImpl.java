package com.projects.moviebookingapp.service.user.impl;

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
        User user = userRepository.findByUsername(username);

        if(Objects.isNull(user))
        {
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
        User user = CustomUserMapper.mapToUser(userDto);
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

        Role userRole = roleService.findByName("USER");
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);

        user.setRoles(roles);

        return CustomUserMapper.mapToUserDto(userRepository.save(user));
    }

    @Override
    public List<UserDto> findAll()
    {
        return userRepository.findAll().stream()
                .map(CustomUserMapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findOne(String username)
    {
        return CustomUserMapper.mapToUserDto(userRepository.findByUsername(username));
    }
}
