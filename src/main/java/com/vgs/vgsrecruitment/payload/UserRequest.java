package com.vgs.vgsrecruitment.payload;

import com.vgs.vgsrecruitment.model.UserEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


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

    @ApiModelProperty(notes = "User's Birth day")
    private int day;

    @NotBlank(message = "Birth month cannot be empty")
    @ApiModelProperty(notes = "User's Birth month")
    private String month;

    @NotBlank(message = "Birth year cannot be empty")
    @ApiModelProperty(notes = "User's Birth year")
    private int year;


}
