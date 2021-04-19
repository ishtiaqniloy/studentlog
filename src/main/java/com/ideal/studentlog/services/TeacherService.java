package com.ideal.studentlog.services;

import com.ideal.studentlog.database.models.Teacher;
import com.ideal.studentlog.database.repositories.TeacherRepository;
import com.ideal.studentlog.helpers.dtos.TeacherDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository repository;

    public List<Teacher> getAll() {
        return repository.findAll();
    }

    public void create(TeacherDTO dto) {
        Teacher teacher = new Teacher();
        dtoToTeacher(dto, teacher);
    }

    public void update(Integer id, TeacherDTO dto) {
        Teacher teacher = repository.findById(id).orElseThrow();
        dtoToTeacher(dto, teacher);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    private void dtoToTeacher(TeacherDTO dto, Teacher teacher) {
        teacher.setName(dto.getName());
        teacher.setDateOfBirth(dto.getDateOfBirth());
        teacher.setJoiningDate(dto.getJoiningDate());
        teacher.setResignationDate(dto.getResignationDate());
        teacher.setHighestEducationLevel(dto.getHighestEducationLevel());
        teacher.setNationalRegistrationNo(dto.getNationalRegistrationNo());
        teacher.setTeacherId(dto.getTeacherId());
        teacher.setDesignation(dto.getDesignation());
        teacher.setContactNo(dto.getContactNo());
        teacher.setPresentAddress(dto.getPresentAddress());
        teacher.setPermanentAddress(dto.getPermanentAddress());
        teacher.setBloodGroup(dto.getBloodGroup());

        repository.save(teacher);
    }

    public TeacherDTO getById(Integer id) {
        Teacher teacher = repository.findById(id).orElseThrow();

        return new TeacherDTO(
                teacher.getName(),
                teacher.getDateOfBirth(),
                teacher.getJoiningDate(),
                teacher.getResignationDate(),
                teacher.getHighestEducationLevel(),
                teacher.getNationalRegistrationNo(),
                teacher.getTeacherId(),
                teacher.getDesignation(),
                teacher.getContactNo(),
                teacher.getPresentAddress(),
                teacher.getPermanentAddress(),
                teacher.getBloodGroup()
        );
    }
}
