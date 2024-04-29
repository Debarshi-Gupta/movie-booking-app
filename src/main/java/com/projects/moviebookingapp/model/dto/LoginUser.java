package com.projects.moviebookingapp.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class LoginUser {

    private String username;

    private String password;
}
