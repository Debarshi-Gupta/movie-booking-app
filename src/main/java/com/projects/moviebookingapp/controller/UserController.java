package com.projects.moviebookingapp.controller;

import com.projects.moviebookingapp.model.dto.AuthorizationResponse;
import com.projects.moviebookingapp.model.dto.LoginUser;
import com.projects.moviebookingapp.model.dto.UserDto;
import com.projects.moviebookingapp.service.jwt.JwtTokenProvider;
import com.projects.moviebookingapp.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1.0/users")
@CrossOrigin
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;

    @Operation(summary = "Generates a JWT Token for a User", description = "This REST API allows a User to login and generates a JWT Token")
    @ApiResponse(responseCode = "200", description = "Http Status Code OK")
    @PostMapping("/login")
    public ResponseEntity<AuthorizationResponse> generateJwtToken(@RequestBody @Valid LoginUser loginUser)
    {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtTokenProvider.generateToken(authentication);
        return new ResponseEntity<>(new AuthorizationResponse(loginUser.getUsername(), jwtToken),
                HttpStatus.OK);
    }

    @Operation(summary = "Creates a New User", description = "This REST API allows a User to register in our application")
    @ApiResponse(responseCode = "200", description = "Http Status Code OK")
    @PostMapping("/register")
    public ResponseEntity<UserDto> saveUser(@RequestBody @Valid UserDto userDto)
    {
        UserDto savedUserDto = userService.save(userDto);
        return new ResponseEntity<>(savedUserDto, HttpStatus.OK);
    }

}
