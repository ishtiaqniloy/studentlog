package com.dsi.studentlog.services;

import com.dsi.studentlog.database.models.Student;
import com.dsi.studentlog.database.repositories.StudentRepository;
import com.dsi.studentlog.helpers.dtos.StudentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;

    public List<Student> getAll() {
        return repository.findAll();
    }

    public void create(StudentDTO dto) {
        Student student = new Student();
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
        repository.save(student);
    }

    public StudentDTO getById(Integer id) {
        Student student = repository.findById(id).orElseThrow();

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

    public void update(Integer id, StudentDTO dto) {
        Student student = repository.findById(id).orElseThrow();
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
        repository.save(student);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

}
