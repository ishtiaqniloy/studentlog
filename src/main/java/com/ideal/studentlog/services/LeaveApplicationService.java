package com.ideal.studentlog.services;

import com.ideal.studentlog.database.models.LeaveApplication;
import com.ideal.studentlog.database.repositories.LeaveApplicationRepository;
import com.ideal.studentlog.helpers.dtos.LeaveApplicationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveApplicationService {

    private final LeaveApplicationRepository repository;

    public List<LeaveApplication> getAll() {
        return repository.findAll();
    }

    public void create(LeaveApplicationDTO dto) {
        LeaveApplication leaveApplication = new LeaveApplication();
        dtoToLeaveApplication(dto, leaveApplication);
    }

    public void update(Integer id, LeaveApplicationDTO dto) {
        LeaveApplication leaveApplication = repository.findById(id).orElseThrow();
        dtoToLeaveApplication(dto, leaveApplication);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    private void dtoToLeaveApplication(LeaveApplicationDTO dto, LeaveApplication leaveApplication) {
        leaveApplication.setDateFrom(dto.getDateFrom());
        leaveApplication.setDateTo(dto.getDateTo());
        leaveApplication.setStudentID(dto.getStudentID());
        leaveApplication.setApplicationBody(dto.getApplicationBody());
        leaveApplication.setApprovedBy(dto.getApprovedBy());

        repository.save(leaveApplication);
    }

    public LeaveApplicationDTO getById(Integer id) {
        LeaveApplication leaveApplication = repository.findById(id).orElseThrow();

        return new LeaveApplicationDTO(
                leaveApplication.getDateFrom(),
                leaveApplication.getDateTo(),
                leaveApplication.getStudentID(),
                leaveApplication.getApplicationBody(),
                leaveApplication.getApprovedBy()
        );
    }
}
