package com.ideal.studentlog.services;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.ideal.studentlog.database.models.SubjectDetails;
import com.ideal.studentlog.helpers.dataclass.SubjectDetailsDTO;
import com.ideal.studentlog.helpers.exceptions.ServiceException;
import org.springframework.transaction.annotation.Transactional;
import com.ideal.studentlog.database.repositories.SubjectDetailsRepository;

@Service
@RequiredArgsConstructor
public class SubjectDetailsService {

    private final SubjectDetailsRepository repository;

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
    public SubjectDetailsDTO create(SubjectDetailsDTO dto) {
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
    private void map(SubjectDetailsDTO dto, SubjectDetails subjectDetails) {
        subjectDetails.setSubjectId(dto.getSubjectId());
        subjectDetails.setTeacherId(dto.getTeacherId());
        subjectDetails.setClassDetailsId(dto.getClassDetailsId());
    }

    private SubjectDetailsDTO map(SubjectDetails subjectDetails) {
        return new SubjectDetailsDTO(
                subjectDetails.getSubjectId(),
                subjectDetails.getTeacherId(),
                subjectDetails.getClassDetailsId()
        );
    }

}
