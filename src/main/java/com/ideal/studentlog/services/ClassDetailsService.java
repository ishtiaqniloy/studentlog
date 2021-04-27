package com.ideal.studentlog.services;

import com.ideal.studentlog.database.models.ClassDetails;
import com.ideal.studentlog.database.repositories.ClassDetailsRepository;
import com.ideal.studentlog.database.repositories.SchoolClassRepository;
import com.ideal.studentlog.database.repositories.TeacherRepository;
import com.ideal.studentlog.helpers.dtos.ClassDetailsDTO;
import com.ideal.studentlog.helpers.exceptions.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassDetailsService {
    private final ClassDetailsRepository repository;
    private final SchoolClassRepository schoolClassRepository;
    private final TeacherRepository teacherRepository;

    //Todo: introduce model mapper
    public ClassDetailsDTO map(ClassDetails classDetails){
        return new ClassDetailsDTO(
                classDetails.getSchoolClass().getId(),
                classDetails.getYear(),
                classDetails.getTeacher().getId()
        );
    }

    public void map(ClassDetails classDetails, ClassDetailsDTO classDetailsDTO) throws ServiceException {
        classDetails.setSchoolClass(schoolClassRepository.findById(classDetailsDTO.getSchoolClassId()).orElseThrow(
                () -> new ServiceException(
                "School Class not found with ID: " + classDetailsDTO.getSchoolClassId(),
                HttpStatus.NOT_FOUND
        )));
        classDetails.setYear(classDetailsDTO.getYear());
        classDetails.setTeacher(teacherRepository.findById(classDetailsDTO.getTeacherId()).orElseThrow(
                () -> new ServiceException(
                "Teacher not found with ID: " + classDetailsDTO.getTeacherId(),
                HttpStatus.NOT_FOUND
        )));
    }

    public List<ClassDetailsDTO> map(List<ClassDetails> classDetailsList){
        return classDetailsList
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    public ClassDetails createModelWithDTO(ClassDetailsDTO classDetailsDTO) throws ServiceException {
        ClassDetails classDetails = new ClassDetails();
        map(classDetails, classDetailsDTO);
        return classDetails;
    }

    public List<ClassDetailsDTO> getAll(){
        return map(repository.findAll());
    }

    public ClassDetailsDTO getById(Integer id) throws ServiceException {
        ClassDetails classDetails = repository.findById(id).orElseThrow(() -> new ServiceException(
                        "Class Details not found with ID: " + id,
                        HttpStatus.NOT_FOUND
                ));
        return map(classDetails);
    }

    public ClassDetailsDTO create(ClassDetailsDTO classDetailsDTO) throws ServiceException {
        return map(repository.save(createModelWithDTO(classDetailsDTO)));
    }

    public ClassDetailsDTO update(Integer id, ClassDetailsDTO classDetailsDTO) throws ServiceException {
        ClassDetails classDetails = repository.findById(id).orElseThrow(() -> new ServiceException(
                "Class Details not found with ID: " + id,
                HttpStatus.NOT_FOUND
        ));
        map(classDetails, classDetailsDTO);
        return map(repository.save(classDetails));
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
