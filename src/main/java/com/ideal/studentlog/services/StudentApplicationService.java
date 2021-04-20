package com.ideal.studentlog.services;

import com.ideal.studentlog.database.models.StudentApplication;
import com.ideal.studentlog.database.repositories.StudentApplicationRepository;
import com.ideal.studentlog.helpers.dtos.StudentApplicationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentApplicationService {
    private final StudentApplicationRepository repository;

    //Todo: introduce model mapper
    public StudentApplicationDTO model2dto(StudentApplication studentApplication){
        return new StudentApplicationDTO(
                studentApplication.getAppliedDate(),
                studentApplication.getApprovedBy(),
                studentApplication.getName(),
                studentApplication.getDateOfBirth(),
                studentApplication.getBloodGroup(),
                studentApplication.getBirthRegistrationId(),
                studentApplication.getRegistrationId(),
                studentApplication.getPresentAddress(),
                studentApplication.getPermanentAddress(),
                studentApplication.getGuardianName(),
                studentApplication.getGuardianEmail(),
                studentApplication.getGuardianPhone(),
                studentApplication.getAppliedForGrade()
        );
    }

    public StudentApplication dto2model(StudentApplication studentApplication, StudentApplicationDTO dto){
        studentApplication.setAppliedDate(dto.getAppliedDate());
        studentApplication.setApprovedBy(dto.getApprovedBy());
        studentApplication.setName(dto.getName());
        studentApplication.setDateOfBirth(dto.getDateOfBirth());
        studentApplication.setBloodGroup(dto.getBloodGroup());
        studentApplication.setBirthRegistrationId(dto.getBirthRegistrationId());
        studentApplication.setRegistrationId(dto.getRegistrationId());
        studentApplication.setPresentAddress(dto.getPresentAddress());
        studentApplication.setPermanentAddress(dto.getPermanentAddress());
        studentApplication.setGuardianName(dto.getGuardianName());
        studentApplication.setGuardianEmail(dto.getGuardianEmail());
        studentApplication.setGuardianPhone(dto.getGuardianPhone());
        studentApplication.setAppliedForGrade(dto.getAppliedForGrade());

        return studentApplication;
    }

    public StudentApplication createModelWithDTO(StudentApplicationDTO dto){
        StudentApplication studentApplication = new StudentApplication();
        studentApplication = dto2model(studentApplication, dto);
        return studentApplication;
    }

    public List<StudentApplication> getAll() {
        return repository.findAll();
    }

    public void create(StudentApplicationDTO dto) {
        StudentApplication studentApplication = createModelWithDTO(dto);
        repository.save(studentApplication);
    }

    public StudentApplicationDTO getById(Integer id) {
        StudentApplication studentApplication = repository.findById(id).orElseThrow();
        return model2dto(studentApplication);
    }

    public void update(Integer id, StudentApplicationDTO dto) {
        StudentApplication studentApplication = repository.findById(id).orElseThrow();
        studentApplication = dto2model(studentApplication, dto);
        repository.save(studentApplication);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }






}
