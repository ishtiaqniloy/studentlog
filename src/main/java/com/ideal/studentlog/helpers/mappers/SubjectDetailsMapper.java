package com.ideal.studentlog.helpers.mappers;

import com.ideal.studentlog.database.models.SubjectDetails;
import com.ideal.studentlog.helpers.dataclass.SubjectDetailsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubjectDetailsMapper {
    SubjectDetailsMapper INSTANCE = Mappers.getMapper(SubjectDetailsMapper.class);


    SubjectDetailsDTO subjectDetailsToSubjectDetailsDto(SubjectDetails subjectDetails);
    void subjectDetailsDtoToSubjectDetails(SubjectDetailsDTO dto,
                                      @MappingTarget SubjectDetails subjectDetails);
}
