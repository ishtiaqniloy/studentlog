package com.ideal.studentlog.controllers;


import com.ideal.studentlog.database.models.StudentApplication;
import com.ideal.studentlog.helpers.dtos.StudentApplicationDTO;
import com.ideal.studentlog.services.StudentApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/student-applications")
@RequiredArgsConstructor
public class StudentApplicationController {

    private final StudentApplicationService service;

    @GetMapping
    public List<StudentApplication> getAll() {
        return service.getAll();
    }

    @GetMapping(path = "/{id}")
    public StudentApplicationDTO getById(@PathVariable("id") Integer id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody StudentApplicationDTO dto) {
        service.create(dto);
    }

    @PatchMapping(path = "/{id}")
    public void update(@PathVariable("id") Integer id, @RequestBody StudentApplicationDTO dto) {
        service.update(id, dto);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id) {
        service.delete(id);
    }
}
