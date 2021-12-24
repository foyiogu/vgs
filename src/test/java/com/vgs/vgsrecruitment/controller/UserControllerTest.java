package com.vgs.vgsrecruitment.controller;

import com.vgs.vgsrecruitment.payload.BirthdayResponse;
import com.vgs.vgsrecruitment.payload.UserRequest;
import com.vgs.vgsrecruitment.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService mockUserService;



    @Test
    void testUpdate() throws Exception {
        // Setup

        final UserRequest userRequest = new UserRequest("Francis", "Oyiogu", "foyiogu@gmail.com", LocalDate.of(1994, 12, 25));

        when(mockUserService.updateUser(eq(1L), any(UserRequest.class))).thenReturn(new BirthdayResponse("  string updated successfully"));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(put("/api/hello/update/{userId}", 1)
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getContentAsString()).isNotEqualTo(mockUserService.updateUser(1L, userRequest));
    }

    @Test
    void testGetBirthday() throws Exception {

        String email = "foyiogu@yahoo.com";
        // Setup
        when(mockUserService.helloBirthday(email)).thenReturn(new BirthdayResponse("Hello,  string ! Your birthday was " + 19 + " days ago"));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/hello/hello/{email}", email)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();


        // Verify the results
        assertThat(response.getContentAsString().split("\"")[3]).isEqualTo(mockUserService.helloBirthday(email).getMessage());
    }
}
