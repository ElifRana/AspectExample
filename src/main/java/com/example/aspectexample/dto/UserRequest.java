package com.example.aspectexample.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class UserRequest {

    private int id;

    @Size(min = 2, max = 15)
    private String firstName;

    @Size(min = 2, max = 15)
    private String lastName;

    @NotBlank
    private String userName;

    @Email
    @ApiModelProperty(notes = "Person Email", example = "aa@gmail.com")
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Start date cannot be future time")
    private LocalDate yearOfBirth;
}


