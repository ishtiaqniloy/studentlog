package com.ideal.studentlog.services;

import com.ideal.studentlog.database.models.Subject;
import com.ideal.studentlog.database.repositories.SubjectRepository;
import com.ideal.studentlog.helpers.dataclass.SubjectDTO;
import com.ideal.studentlog.helpers.exceptions.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public List<SubjectDTO> getAll(){
        return subjectRepository
                .findAll()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    public SubjectDTO getById(Integer id) throws ServiceException {
        return map(getSubject(id));
    }

    @Transactional
    public SubjectDTO create(SubjectDTO dto) {
        Subject subject = new Subject();
        map(dto, subject);
        return map(subjectRepository.save(subject));
    }

    @Transactional
    public SubjectDTO update(Integer id, SubjectDTO dto) throws ServiceException {
        Subject subject = getSubject(id);
        map(dto, subject);
        return map(subjectRepository.save(subject));
    }

    @Transactional
    public void delete(Integer id) {
        subjectRepository.deleteById(id);
    }

    private Subject getSubject(Integer id) throws ServiceException {
        return subjectRepository.findById(id).orElseThrow(() -> new ServiceException(
                "Subject not found with ID: " + id,
                HttpStatus.NOT_FOUND
        ));
    }

    private void map(SubjectDTO dto, Subject subject) {
        subject.setName(dto.getName());
        subject.setCategory(dto.getCategory());
    }

    private SubjectDTO map(Subject subject) {
        return new SubjectDTO(
                subject.getName(),
                subject.getCategory()
        );
    }
}
