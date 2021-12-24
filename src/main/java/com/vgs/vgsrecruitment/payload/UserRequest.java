package com.vgs.vgsrecruitment.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vgs.vgsrecruitment.model.UserEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
public class UserRequest {

    @NotBlank(message = "First Name cannot be empty")
    @ApiModelProperty(notes = "User's First Name")
    private String firstName;

    @NotBlank(message = "Last Name cannot be empty")
    @ApiModelProperty(notes = "User's Last Name")
    private String lastName;

    @NotBlank(message = "email cannot be empty")
    @Email(message = "Email should be valid")
    @ApiModelProperty(notes = "User's Email")
    private String email;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate birthDate;


}
