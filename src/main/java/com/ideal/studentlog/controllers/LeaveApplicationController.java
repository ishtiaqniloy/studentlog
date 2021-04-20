package com.ideal.studentlog.controllers;

import com.ideal.studentlog.database.models.LeaveApplication;
import com.ideal.studentlog.helpers.dtos.LeaveApplicationDTO;
import com.ideal.studentlog.services.LeaveApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/leave_applications")
@RequiredArgsConstructor
public class LeaveApplicationController {

    private final LeaveApplicationService service;

    @GetMapping
    public List<LeaveApplication> getAll() {
        return service.getAll();
    }

    @GetMapping(path = "/{id}")
    public LeaveApplicationDTO getById(@PathVariable("id") Integer id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody LeaveApplicationDTO dto) {
        service.create(dto);
    }

    @PatchMapping(path = "/{id}")
    public void update(@PathVariable("id") Integer id, @RequestBody LeaveApplicationDTO dto) {
        service.update(id, dto);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id) {
        service.delete(id);
    }
}
