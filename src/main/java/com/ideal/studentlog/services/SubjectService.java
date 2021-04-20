package com.ideal.studentlog.services;

import com.ideal.studentlog.database.models.Subject;
import com.ideal.studentlog.database.repositories.SubjectRepository;
import com.ideal.studentlog.helpers.dtos.SubjectDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public List<Subject> getAll(){
        return subjectRepository.findAll();
    }

    public void create(SubjectDTO dto){
        Subject subject = new Subject();

        subject.setName(dto.getName());
        subject.setCategory(dto.getCategory());

        subjectRepository.save(subject);
    }

    public SubjectDTO getById(Integer id){
        Subject subject = subjectRepository.findById(id).orElseThrow();

        return new SubjectDTO(
                subject.getName(),
                subject.getCategory()
        );
    }

    public void update(Integer id, SubjectDTO dto){
        Subject subject = subjectRepository.findById(id).orElseThrow();

        subject.setName(dto.getName());
        subject.setCategory(dto.getCategory());

        subjectRepository.save(subject);
    }

    public void delete(Integer id){
        subjectRepository.deleteById(id);
    }
}
