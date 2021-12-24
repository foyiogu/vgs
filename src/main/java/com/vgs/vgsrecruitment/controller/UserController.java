package com.vgs.vgsrecruitment.controller;

import com.vgs.vgsrecruitment.payload.BirthdayResponse;
import com.vgs.vgsrecruitment.payload.UserRequest;
import com.vgs.vgsrecruitment.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/hello")
@ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "This is a bad request, please follow the API documentation for the proper request format."),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "The server is down, please make sure that the Application is running")
})
@AllArgsConstructor
public class UserController {

//    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    @PostMapping(path = "/save")
    @ApiOperation(value = "Registers a new User")
    public ResponseEntity<BirthdayResponse> register(@RequestBody UserRequest userRequest) {
        return new ResponseEntity<>(userService.saveUser(userRequest), HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/update/{userId}")
    @ApiOperation(value = "Updates an existing User")
    public ResponseEntity<BirthdayResponse> update(@PathVariable("userId")Long id,  @RequestBody UserRequest newUserRequest) {
        return new ResponseEntity<>(userService.updateUser(id,newUserRequest), HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/hello/{email}")
    @ApiOperation(value = "Gets the birthday of a User")
    public ResponseEntity<?> getBirthday(@PathVariable("email") String email) {
        return new ResponseEntity<>(userService.helloBirthday(email), HttpStatus.OK);
    }

}
