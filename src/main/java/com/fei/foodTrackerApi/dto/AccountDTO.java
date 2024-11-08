package com.fei.foodTrackerApi.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AccountDTO {

    private Integer id;

    @Email()
    @NotNull()
    private String email;

    @Pattern(regexp = "^(?!\\s*$)(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{5,}$")
    @NotNull()
    private String password;

    @NotNull()
    @Enumerated(EnumType.STRING)
    private AccountTypes accountType;
}
