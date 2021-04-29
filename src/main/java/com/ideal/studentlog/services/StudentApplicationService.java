package com.ideal.studentlog.services;

import com.ideal.studentlog.database.models.StudentApplication;
import com.ideal.studentlog.database.models.Admin;
import com.ideal.studentlog.database.repositories.AdminRepository;
import com.ideal.studentlog.database.repositories.StudentApplicationRepository;
import com.ideal.studentlog.helpers.dataclass.StudentApplicationDTO;
import com.ideal.studentlog.helpers.exceptions.ServiceException;
import com.ideal.studentlog.helpers.mappers.StudentApplicationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentApplicationService {
    private static final StudentApplicationMapper mapper = StudentApplicationMapper.INSTANCE;

    private final StudentApplicationRepository repository;
    private final AdminRepository adminRepository;


    public List<StudentApplicationDTO> getAll() {
        return repository
                .findAll()
                .stream()
                .map(mapper::studentApplicationToStudentApplicationDto)
                .collect(Collectors.toList());
    }

    public StudentApplicationDTO create(StudentApplicationDTO dto) throws ServiceException {
        StudentApplication studentApplication = new StudentApplication();

        mapper.studentApplicationDtoToStudentApplication(dto, studentApplication);
        studentApplication.setApprovedBy(getAdmin(dto.getApprovedById()));

        return mapper.studentApplicationToStudentApplicationDto(repository.save(studentApplication));
    }

    public StudentApplicationDTO getById(Integer id) throws ServiceException {
        return mapper.studentApplicationToStudentApplicationDto(getStudentApplication(id));
    }

    public StudentApplicationDTO update(Integer id, StudentApplicationDTO dto) throws ServiceException {
        StudentApplication studentApplication = getStudentApplication(id);

        mapper.studentApplicationDtoToStudentApplication(dto, studentApplication);
        studentApplication.setApprovedBy(getAdmin(dto.getApprovedById()));

        return mapper.studentApplicationToStudentApplicationDto(repository.save(studentApplication));
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public StudentApplication getStudentApplication(Integer id) throws ServiceException {
        return repository.findById(id).orElseThrow(() -> new ServiceException(
                "Student Application not found with ID: " + id,
                HttpStatus.NOT_FOUND
        ));
    }

    private Admin getAdmin(Integer id) throws ServiceException {
        return adminRepository.findById(id).orElseThrow(() -> new ServiceException(
                "Admin not found with ID: " + id,
                HttpStatus.NOT_FOUND
        ));
    }

}
