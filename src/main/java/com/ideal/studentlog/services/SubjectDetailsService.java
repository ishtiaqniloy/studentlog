package com.ideal.studentlog.services;

import java.util.List;
import java.util.stream.Collectors;

import com.ideal.studentlog.database.models.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.ideal.studentlog.database.models.SubjectDetails;
import com.ideal.studentlog.helpers.dataclass.SubjectDetailsDTO;
import com.ideal.studentlog.helpers.exceptions.ServiceException;
import org.springframework.transaction.annotation.Transactional;
import com.ideal.studentlog.database.repositories.TeacherRepository;
import com.ideal.studentlog.database.repositories.SubjectRepository;
import com.ideal.studentlog.database.repositories.ClassDetailsRepository;
import com.ideal.studentlog.database.repositories.SubjectDetailsRepository;

@Service
@RequiredArgsConstructor
public class SubjectDetailsService {

    private final SubjectDetailsRepository repository;
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;
    private final ClassDetailsRepository classDetailsRepository;

    public List<SubjectDetailsDTO> getAll() {
        return repository
                .findAll()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    public SubjectDetailsDTO getById(Integer id) throws ServiceException {
        return map(getSubjectDetails(id));
    }

    @Transactional
    public SubjectDetailsDTO create(SubjectDetailsDTO dto) throws ServiceException {
        SubjectDetails subjectDetails = new SubjectDetails();
        map(dto, subjectDetails);
        return map(repository.save(subjectDetails));
    }

    @Transactional
    public SubjectDetailsDTO update(Integer id, SubjectDetailsDTO dto) throws ServiceException {
        SubjectDetails subjectDetails = new SubjectDetails();
        map(dto, subjectDetails);
        return map(repository.save(subjectDetails));
    }

    @Transactional
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    private SubjectDetails getSubjectDetails(Integer id) throws ServiceException {
        return repository.findById(id).orElseThrow(() -> new ServiceException(
                "Subject Details not found with ID: " + id,
                HttpStatus.NOT_FOUND
        ));
    }

    private Teacher getTeacher(@NonNull Integer id) throws ServiceException {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new ServiceException(
                        "Teacher not found with ID: " + id,
                        HttpStatus.NOT_FOUND
                ));
    }

    private Subject getSubject(@NonNull Integer id) throws ServiceException {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new ServiceException(
                        "Subject not found with ID: " + id,
                        HttpStatus.NOT_FOUND
                ));
    }

    private ClassDetails getClassDetails(@NonNull Integer id) throws ServiceException {
        return classDetailsRepository.findById(id)
                .orElseThrow(() -> new ServiceException(
                        "Student not found with ID: " + id,
                        HttpStatus.NOT_FOUND
                ));
    }

    private void map(SubjectDetailsDTO dto, SubjectDetails subjectDetails) throws ServiceException {
        Teacher teacher = getTeacher(dto.getTeacherId());
        Subject subject = getSubject(dto.getSubjectId());
        ClassDetails classDetails = getClassDetails(dto.getClassDetailsId());

        subjectDetails.setSubject(subject);
        subjectDetails.setTeacher(teacher);
        subjectDetails.setClassDetails(classDetails);
    }

    private SubjectDetailsDTO map(SubjectDetails subjectDetails) {
        return new SubjectDetailsDTO(
                subjectDetails.getSubject().getId(),
                subjectDetails.getTeacher().getId(),
                subjectDetails.getClassDetails().getId()
        );
    }

}
