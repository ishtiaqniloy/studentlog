package com.ideal.studentlog.services;

import com.ideal.studentlog.database.models.LeaveApplication;
import com.ideal.studentlog.database.repositories.LeaveApplicationRepository;
import com.ideal.studentlog.database.repositories.StudentRepository;
import com.ideal.studentlog.database.repositories.TeacherRepository;
import com.ideal.studentlog.helpers.dtos.LeaveApplicationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveApplicationService {

    private final LeaveApplicationRepository repository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public List<LeaveApplication> getAll() {
        return repository.findAll();
    }

    public void create(LeaveApplicationDTO dto) {
        LeaveApplication leaveApplication = new LeaveApplication();
        map(dto, leaveApplication);
    }

    public void update(Integer id, LeaveApplicationDTO dto) {
        LeaveApplication leaveApplication = repository.findById(id).orElseThrow();
        map(dto, leaveApplication);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    private void map(LeaveApplicationDTO dto, LeaveApplication leaveApplication) {
        leaveApplication.setDateFrom(dto.getDateFrom());
        leaveApplication.setDateTo(dto.getDateTo());
        leaveApplication.setStudent(studentRepository.findById(dto.getStudentId()).orElseThrow());
        leaveApplication.setApplicationBody(dto.getApplicationBody());
        leaveApplication.setApprovedBy(teacherRepository.findById(dto.getApprovedById()).orElseThrow());

        repository.save(leaveApplication);
    }

    public LeaveApplicationDTO getById(Integer id) {
        LeaveApplication leaveApplication = repository.findById(id).orElseThrow();

        return new LeaveApplicationDTO(
                leaveApplication.getDateFrom(),
                leaveApplication.getDateTo(),
                leaveApplication.getStudent().getId(),
                leaveApplication.getApplicationBody(),
                leaveApplication.getApprovedBy().getId()
        );
    }
}
