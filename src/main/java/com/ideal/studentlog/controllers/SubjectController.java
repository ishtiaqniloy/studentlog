package com.ideal.studentlog.controllers;

import com.ideal.studentlog.database.models.Subject;
import com.ideal.studentlog.helpers.dtos.SubjectDTO;
import com.ideal.studentlog.services.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping
    public List<Subject> getAll(){
        return subjectService.getAll();
    }

    @GetMapping(path = "/{id}")
    public SubjectDTO getById(@PathVariable("id") Integer id){
        return subjectService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody SubjectDTO dto){
        subjectService.create(dto);
    }

    @PatchMapping(path = "/{id}")
    public void update (@PathVariable("id") Integer id, @RequestBody SubjectDTO dto){
        subjectService.update(id, dto);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id){
        subjectService.delete(id);
    }
}
