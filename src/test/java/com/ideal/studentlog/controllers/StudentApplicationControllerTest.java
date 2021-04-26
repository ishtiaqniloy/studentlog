package com.ideal.studentlog.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ideal.studentlog.database.repositories.StudentApplicationRepository;
import com.ideal.studentlog.helpers.dtos.StudentApplicationDTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StudentApplicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private StudentApplicationRepository repository;

    @Test
    public void shouldReturnAvailableStudentApplications() throws Exception {
        mockMvc
                .perform(get("/student-applications"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("Tori Muldoon")))
                .andExpect(jsonPath("$[1].bloodGroup", is("A-")))
                .andExpect(jsonPath("$", hasSize(20)));
    }

    @Test
    public void shouldReturnStudentApplicationGetById() throws Exception {
        mockMvc
                .perform(get("/student-applications/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Luise Halesworth")))
                .andExpect(jsonPath("$.presentAddress", is("3 Esch Way")));
    }

    @Test
    @Transactional
    public void shouldCreateStudentApplication() throws Exception {
        mockMvc
                .perform(
                        post("/student-applications")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(getDto()))
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("applicant name")))
                .andExpect(jsonPath("$.birthRegistrationId", is("1231541241")));

        assertEquals(repository.count(), 21);
    }

    @Test
    @Transactional
    public void shouldUpdateStudentApplication() throws Exception {
        mockMvc
                .perform(
                        patch("/student-applications/4")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(getDto()))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("applicant name")))
                .andExpect(jsonPath("$.birthRegistrationId", is("1231541241")));

        assertEquals(repository.count(), 20);
    }

    @Test
    @Transactional
    public void shouldDeleteStudentApplication() throws Exception {
        mockMvc
                .perform(
                        delete("/student-applications/10")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(getDto()))
                )
                .andDo(print())
                .andExpect(status().isNoContent());

        assertEquals(repository.count(), 19);
    }

    @Test
    public void shouldReturnNotFoundResponseForNonExistentStudentApplication() throws Exception {
        mockMvc
                .perform(get("/student-applications/101"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error", is("Not Found")))
                .andExpect(jsonPath("$.code", is("API-404")))
                .andExpect(jsonPath("$.message", is("Student Application not found with ID: 101")));
    }

    @NotNull
//    @Contract(" -> new")
    private StudentApplicationDTO getDto() throws ParseException {
        return new StudentApplicationDTO(
                new Date(),
                1,
                "applicant name",
                new SimpleDateFormat("yyyy-MM-dd").parse("2000-01-08"),
                "A+ve",
                "1231541241",
                "11212",
                "flat, house, road, area",
                "vill, po, ps, dist",
                "Mrs. Mother",
                "mother@test_email.com",
                "01231121412",
                4
        );
    }

}
