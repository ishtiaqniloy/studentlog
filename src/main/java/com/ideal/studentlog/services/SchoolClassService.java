package com.ideal.studentlog.services;

import com.ideal.studentlog.database.models.SchoolClass;
import com.ideal.studentlog.database.repositories.SchoolClassRepository;
import com.ideal.studentlog.helpers.dtos.SchoolClassDTO;
import com.ideal.studentlog.helpers.exceptions.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SchoolClassService {
    private final SchoolClassRepository repository;

    //Todo: introduce model mapper
    public SchoolClassDTO map(SchoolClass schoolClass){
        return new SchoolClassDTO(
                schoolClass.getName(),
                schoolClass.getGrade()
        );
    }

    public void map(SchoolClass schoolClass, SchoolClassDTO schoolClassDTO){
        schoolClass.setName(schoolClassDTO.getName());
        schoolClass.setGrade(schoolClassDTO.getGrade());
    }

    public List<SchoolClassDTO> map(List<SchoolClass> schoolClasses){
        return schoolClasses
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }


    public SchoolClass createModelWithDTO(SchoolClassDTO schoolClassDTO){
        SchoolClass schoolClass = new SchoolClass();
        map(schoolClass, schoolClassDTO);
        return schoolClass;
    }

    public List<SchoolClassDTO> getAll(){
        return map(repository.findAll());
    }

    public SchoolClassDTO getById(Integer id) throws ServiceException {
        SchoolClass schoolClass = repository.findById(id).orElseThrow(() -> new ServiceException(
                "School Class not found with ID: " + id,
                HttpStatus.NOT_FOUND
        ));
        return map(schoolClass);
    }

    public SchoolClassDTO create(SchoolClassDTO schoolClassDTO){
        return map(repository.save(createModelWithDTO(schoolClassDTO)));
    }

    public SchoolClassDTO update(Integer id, SchoolClassDTO schoolClassDTO) throws ServiceException {
        SchoolClass schoolClass = repository.findById(id).orElseThrow(() -> new ServiceException(
                "School Class not found with ID: " + id,
                HttpStatus.NOT_FOUND
        ));
        map(schoolClass, schoolClassDTO);
        return map(repository.save(schoolClass));
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
