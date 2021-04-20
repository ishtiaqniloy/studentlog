package com.ideal.studentlog.database.repositories;

import com.ideal.studentlog.database.models.Student;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findAll();
}
