package com.projects.moviebookingapp.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserDto {

    private String username;

    private String password;

    private String fullName;

    private String email;

    private String phone;

    private String address;
}
