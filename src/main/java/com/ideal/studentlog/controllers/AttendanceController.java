package com.ideal.studentlog.controllers;

import com.ideal.studentlog.database.models.Attendance;
import com.ideal.studentlog.helpers.dtos.AttendanceDTO;
import com.ideal.studentlog.services.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/attendance")
@RequiredArgsConstructor
public class AttendanceController {
    
    private final AttendanceService service;

    @GetMapping
    public List<Attendance> getAll() {
        return service.getAll();
    }

    @GetMapping(path = "/{id}")
    public AttendanceDTO getById(@PathVariable("id") Integer id) {
        return service.getById(id);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody AttendanceDTO dto) {
        service.create(dto);
    }
    
    @PatchMapping(path = "/{id}")
    public void update(@PathVariable("id") Integer id,  @RequestBody AttendanceDTO dto) {
        service.update(id, dto);
    }
    
    @DeleteMapping(path="/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id) {
          service.delete(id);
    }
}
