package com.ideal.studentlog.controllers;

import com.ideal.studentlog.database.models.Teacher;
import com.ideal.studentlog.helpers.dtos.TeacherDTO;
import com.ideal.studentlog.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService service;

    @GetMapping
    public List<Teacher> getAll() {
        return service.getAll();
    }

    @GetMapping(path = "/{id}")
    public TeacherDTO getById(@PathVariable("id") Integer id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody TeacherDTO dto) {
        service.create(dto);
    }

    @PatchMapping(path = "/{id}")
    public void update(@PathVariable("id") Integer id, @RequestBody TeacherDTO dto) {
        service.update(id, dto);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id) {
        service.delete(id);
    }
}
