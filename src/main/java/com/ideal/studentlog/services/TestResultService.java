package com.ideal.studentlog.services;

import com.ideal.studentlog.database.models.Student;
import com.ideal.studentlog.database.models.Test;
import com.ideal.studentlog.database.models.TestResult;
import com.ideal.studentlog.database.repositories.StudentRepository;
import com.ideal.studentlog.database.repositories.TestRepository;
import com.ideal.studentlog.database.repositories.TestResultRepository;
import com.ideal.studentlog.helpers.dtos.TestResultDTO;
import com.ideal.studentlog.helpers.exceptions.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TestResultService {

    private final TestResultRepository repository;
    private final TestRepository testRepository;
    private final StudentRepository studentRepository;

    public List<TestResultDTO> getAll() {
        return repository
                .findAll()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    public TestResultDTO getById(Integer id) throws ServiceException {
        return map(getTestResult(id));
    }

    @Transactional
    public TestResultDTO create(TestResultDTO dto) throws ServiceException {
        TestResult test = new TestResult();
        map(dto, test);
        return map(repository.save(test));
    }

    @Transactional
    public TestResultDTO update(Integer id, TestResultDTO dto) throws ServiceException {
        TestResult test = getTestResult(id);
        map(dto, test);
        return map(repository.save(test));
    }

    @Transactional
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    private TestResult getTestResult(Integer id) throws ServiceException {
        return repository.findById(id).orElseThrow(() -> new ServiceException(
                "Test Result not found with ID: " + id,
                HttpStatus.NOT_FOUND
        ));
    }

    private void map(TestResultDTO dto, TestResult result) throws ServiceException {
        Test test = testRepository.findById(dto.getTestId())
                .orElseThrow(() -> new ServiceException(
                        "Test not found with ID: " + dto.getTestId(),
                        HttpStatus.NOT_FOUND
                ));
        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new ServiceException(
                        "Student not found with ID: " + dto.getStudentId(),
                        HttpStatus.NOT_FOUND
                ));

        result.setTest(test);
        result.setStudent(student);
        result.setGrade(dto.getGrade());
    }

    private TestResultDTO map(TestResult result) {
        return new TestResultDTO(
                result.getTest().getId(),
                result.getStudent().getId(),
                result.getGrade()
        );
    }

}
