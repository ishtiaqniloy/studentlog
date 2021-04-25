package com.ideal.studentlog.database.repositories;

import com.ideal.studentlog.database.models.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer>{
    List<Attendance> findAll();
}
