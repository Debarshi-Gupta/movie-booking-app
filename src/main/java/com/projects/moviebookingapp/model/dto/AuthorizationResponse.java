package com.projects.moviebookingapp.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class AuthorizationResponse {

    private String username;

    private String jwtToken;
}
