package com.ideal.studentlog.services;

import com.ideal.studentlog.database.models.Teacher;
import com.ideal.studentlog.database.repositories.TeacherRepository;
import com.ideal.studentlog.helpers.dataclass.TeacherDTO;
import com.ideal.studentlog.helpers.exceptions.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository repository;

    public List<TeacherDTO> getAll() {
        return repository
                .findAll()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    public TeacherDTO getById(Integer id) throws ServiceException {
        return map(getTeacher(id));
    }

    @Transactional
    public TeacherDTO create(TeacherDTO dto) {
        Teacher teacher = new Teacher();
        map(dto, teacher);
        return map(repository.save(teacher));
    }

    @Transactional
    public TeacherDTO update(Integer id, TeacherDTO dto) throws ServiceException {
        Teacher teacher = getTeacher(id);
        map(dto, teacher);
        return map(repository.save(teacher));
    }

    @Transactional
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public Teacher getTeacher(Integer id) throws ServiceException {
        return repository.findById(id).orElseThrow(() -> new ServiceException(
                "Teacher not found with ID: " + id,
                HttpStatus.NOT_FOUND
        ));
    }

    private void map(TeacherDTO dto, Teacher teacher) {
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
    }

    private TeacherDTO map(Teacher teacher) {
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
