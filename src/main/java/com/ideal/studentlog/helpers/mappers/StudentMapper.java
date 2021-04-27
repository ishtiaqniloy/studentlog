package com.ideal.studentlog.helpers.mappers;

import com.ideal.studentlog.database.models.Student;
import com.ideal.studentlog.helpers.dataclass.StudentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);


    StudentDTO studentToStudentDto(Student student);
    void studentDtoToStudent(StudentDTO dto, @MappingTarget Student student);
}
