package com.g.library.e_Library.dto;

import lombok.*;

@Data
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
public class AuthDto {

    private String username;
    private String password;

}
