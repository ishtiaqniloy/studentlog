package com.ideal.studentlog.services;

import com.ideal.studentlog.database.models.ClassStudent;
import com.ideal.studentlog.database.repositories.ClassDetailsRepository;
import com.ideal.studentlog.database.repositories.ClassStudentRepository;
import com.ideal.studentlog.database.repositories.StudentRepository;
import com.ideal.studentlog.helpers.dtos.ClassStudentDTO;
import com.ideal.studentlog.helpers.exceptions.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassStudentService {
    private final ClassStudentRepository repository;
    private final ClassDetailsRepository classDetailsRepository;
    private final StudentRepository studentRepository;

    //Todo: introduce model mapper
    public ClassStudentDTO map(ClassStudent classStudent){
        return new ClassStudentDTO(
                classStudent.getClassDetails().getId(),
                classStudent.getStudent().getId()
        );
    }

    public void map(ClassStudent classStudent, ClassStudentDTO classStudentDTO) throws ServiceException {
        classStudent.setClassDetails(classDetailsRepository.findById(classStudentDTO.getClassDetailsId()).orElseThrow(
                () -> new ServiceException(
                        "Class Details not found with ID: " + classStudentDTO.getClassDetailsId(),
                        HttpStatus.NOT_FOUND
                )
        ));
        classStudent.setStudent(studentRepository.findById(classStudentDTO.getStudentId()).orElseThrow(
                () -> new ServiceException(
                        "Student not found with ID: " + classStudentDTO.getStudentId(),
                        HttpStatus.NOT_FOUND
                )
        ));
    }

    public List<ClassStudentDTO> map(List<ClassStudent> classStudents){
        return classStudents
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    public ClassStudent createModelWithDTO(ClassStudentDTO classStudentDTO) throws ServiceException {
        ClassStudent classStudent = new ClassStudent();
        map(classStudent, classStudentDTO);
        return classStudent;
    }

    public List<ClassStudentDTO> getAll(){
        return map(repository.findAll());
    }

    public ClassStudentDTO getById(Integer id) throws ServiceException {
        ClassStudent classStudent = repository.findById(id).orElseThrow(() -> new ServiceException(
                "Class Student not found with ID: " + id,
                HttpStatus.NOT_FOUND
        ));
        return map(classStudent);
    }

    public ClassStudentDTO create(ClassStudentDTO classStudentDTO) throws ServiceException {
        return map(repository.save(createModelWithDTO(classStudentDTO)));
    }

    public ClassStudentDTO update(Integer id, ClassStudentDTO classStudentDTO) throws ServiceException {
        ClassStudent classStudent = repository.findById(id).orElseThrow(() -> new ServiceException(
                "Class Student not found with ID: " + id,
                HttpStatus.NOT_FOUND
        ));
        map(classStudent, classStudentDTO);
        return map(repository.save(classStudent));
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
