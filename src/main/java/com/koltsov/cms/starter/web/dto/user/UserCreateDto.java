package com.koltsov.cms.starter.web.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserCreateDto {
    private String login;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;
}
