package com.ideal.studentlog.services;

import com.ideal.studentlog.database.models.Student;
import com.ideal.studentlog.database.repositories.StudentRepository;
import com.ideal.studentlog.helpers.dtos.StudentDTO;
import com.ideal.studentlog.helpers.exceptions.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;

    public List<StudentDTO> getAll() {
        return repository
                .findAll()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    public StudentDTO getById(Integer id) throws ServiceException {
        return map(getStudent(id));
    }

    @Transactional
    public StudentDTO create(StudentDTO dto) {
        Student student = new Student();
        map(dto, student);
        return map(repository.save(student));
    }

    @Transactional
    public StudentDTO update(Integer id, StudentDTO dto) throws ServiceException {
        Student student = getStudent(id);
        map(dto, student);
        return map(repository.save(student));
    }

    @Transactional
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    private Student getStudent(Integer id) throws ServiceException {
        return repository.findById(id).orElseThrow(() -> new ServiceException(
                "Student not found with ID: " + id,
                HttpStatus.NOT_FOUND
        ));
    }

    private void map(StudentDTO dto, Student student) {
        student.setName(dto.getName());
        student.setBirthRegistrationId(dto.getBirthRegistrationId());
        student.setStudentId(dto.getStudentId());
        student.setDateOfBirth(dto.getDateOfBirth());
        student.setBloodGroup(dto.getBloodGroup());
        student.setPresentAddress(dto.getPresentAddress());
        student.setPermanentAddress(dto.getPermanentAddress());
        student.setGuardianName(dto.getGuardianName());
        student.setGuardianEmail(dto.getGuardianEmail());
        student.setGuardianPhone(dto.getGuardianPhone());
    }

    private StudentDTO map(Student student) {
        return new StudentDTO(
                student.getName(),
                student.getBirthRegistrationId(),
                student.getStudentId(),
                student.getDateOfBirth(),
                student.getBloodGroup(),
                student.getPresentAddress(),
                student.getPermanentAddress(),
                student.getGuardianName(),
                student.getGuardianEmail(),
                student.getGuardianPhone()
        );
    }

}
