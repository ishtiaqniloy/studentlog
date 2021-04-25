package com.ideal.studentlog.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ideal.studentlog.database.repositories.AttendanceRepository;
import com.ideal.studentlog.helpers.dtos.AttendanceDTO;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AttendanceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private AttendanceRepository repository;

    @Test
    public void shouldReturnAvailableAttendances() throws Exception {
        mockMvc
                .perform(get("/attendance"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].studentId", is(1)))
                .andExpect(jsonPath("$[1].teacherId", is(1)))
                .andExpect(jsonPath("$", hasSize(9))) ;
    }

    @Test
    public void shouldReturnAttendanceById() throws Exception {
        mockMvc
                .perform(get("/attendance/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.date", containsString("2021-04-23")))
                .andExpect(jsonPath("$.studentId", is(3)))
                .andExpect(jsonPath("$.teacherId", is(1))) ;
    }

    @Test
    @Transactional
    public void shouldCreateAttendance() throws Exception {
        mockMvc
                .perform(
                        post("/attendance")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(getDto()))
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.studentId", is(5)))
                .andExpect(jsonPath("$.teacherId", is(2)));


        assertEquals(repository.count(), 10);
    }

    @Test
    @Transactional
    public void shouldUpdateAttendance() throws Exception {
        mockMvc
                .perform(
                        patch("/attendance/4")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(getDto()))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studentId", is(5)))
                .andExpect(jsonPath("$.teacherId", is(2)));

        assertEquals(repository.count(), 9);
    }

    @Test
    @Transactional
    public void shouldDeleteAttendance() throws Exception {
        mockMvc
                .perform(
                        delete("/attendance/5")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(getDto()))
                )
                .andDo(print())
                .andExpect(status().isNoContent());

        assertEquals(repository.count(), 8);
    }

    @Test
    public void shouldReturnNotFoundResponseForNonExistentAttendance() throws Exception {
        mockMvc
                .perform(get("/attendance/11"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error", is("Not Found")))
                .andExpect(jsonPath("$.code", is("API-404")))
                .andExpect(jsonPath("$.message", is("Attendance not found with ID: 11")));
    }

    @NotNull
    @Contract(" -> new")
    private AttendanceDTO getDto() {
        return new AttendanceDTO(
                new Date(),
                5,
                2,
                true
        );
    }

}
