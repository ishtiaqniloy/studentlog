package com.ideal.studentlog.services;

import com.ideal.studentlog.database.models.Attendance;
import com.ideal.studentlog.database.models.Student;
import com.ideal.studentlog.database.repositories.AttendanceRepository;
import com.ideal.studentlog.helpers.dtos.AttendanceDTO;
import com.ideal.studentlog.helpers.dtos.StudentDTO;
import com.ideal.studentlog.helpers.exceptions.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendanceService {
    private final AttendanceRepository repository;

    public List<AttendanceDTO> getAll() {
        return repository
                .findAll()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    public AttendanceDTO getById(Integer id) throws ServiceException {
        return map(getAttendance(id));
    }

    @Transactional
    public AttendanceDTO create(AttendanceDTO dto) {
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

    private void map(AttendanceDTO dto, Attendance attendance) {
        attendance.setDate(dto.getDate());
        attendance.setStudentId(dto.getStudentId());
        attendance.setTeacherId(dto.getTeacherId());
        attendance.setIsPresent(dto.getIsPresent());
    }

    private AttendanceDTO map(Attendance attendance) {
        return new AttendanceDTO(
                attendance.getDate(),
                attendance.getStudentId(),
                attendance.getTeacherId(),
                attendance.getIsPresent()
                );
    }

}

