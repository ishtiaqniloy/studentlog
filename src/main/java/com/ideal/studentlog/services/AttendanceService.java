package com.ideal.studentlog.services;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.ideal.studentlog.database.models.Student;
import com.ideal.studentlog.database.models.Teacher;
import com.ideal.studentlog.database.models.Attendance;
import com.ideal.studentlog.helpers.dataclass.AttendanceDTO;
import com.ideal.studentlog.helpers.exceptions.ServiceException;
import com.ideal.studentlog.database.repositories.StudentRepository;
import com.ideal.studentlog.database.repositories.TeacherRepository;
import com.ideal.studentlog.database.repositories.AttendanceRepository;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendanceService {
    private final AttendanceRepository repository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;



    public List<AttendanceDTO> getAll() {
        return map(repository.findAll()); }

    public AttendanceDTO getById(Integer id) throws ServiceException {
        return map(getAttendance(id));
    }

    @Transactional
    public AttendanceDTO create(AttendanceDTO dto) throws ServiceException {
        Attendance attendance = new Attendance();
        map(dto, attendance);
        return map(repository.save(attendance));
    }

    @Transactional
    public AttendanceDTO update(Integer id, AttendanceDTO dto) throws ServiceException {
        Attendance attendance = getAttendance(id);
        map(dto, attendance);
        return map(repository.save(attendance));
    }

    @Transactional
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    private Attendance getAttendance(Integer id) throws ServiceException {
        return repository.findById(id).orElseThrow(() -> new ServiceException(
                "Attendance not found with ID: " + id,
                HttpStatus.NOT_FOUND
        ));
    }

    private void map(AttendanceDTO dto,  Attendance attendance) throws ServiceException {
        Student student = getStudent(dto.getStudentId());
        Teacher teacher = getTeacher(dto.getTeacherId());

        attendance.setDate(dto.getDate());
        attendance.setStudent(student);
        attendance.setTeacher(teacher);
        attendance.setIsPresent(dto.getIsPresent());

    }
    private Student getStudent(@NonNull Integer id) throws ServiceException {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ServiceException(
                        "Student not found with ID: " + id,
                        HttpStatus.NOT_FOUND
                ));
    }

    private Teacher getTeacher(@NonNull Integer id) throws ServiceException {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new ServiceException(
                        "Student not found with ID: " + id,
                        HttpStatus.NOT_FOUND
                ));
    }

    private List<AttendanceDTO> map(List<Attendance> attendance) {
        return attendance
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    private AttendanceDTO map(Attendance attendance) {
        return new AttendanceDTO(
                attendance.getDate(),
                attendance.getStudent().getId(),
                attendance.getTeacher().getId(),
                attendance.getIsPresent()
                );
    }

}

