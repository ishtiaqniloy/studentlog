package com.ideal.studentlog.helpers.dtos;

import com.ideal.studentlog.database.models.Student;
import com.ideal.studentlog.database.models.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.NonNull;

import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@AllArgsConstructor
public class LeaveApplicationDTO {

    @NonNull
    Date dateFrom;

    @NonNull
    Date dateTo;

    @NonNull
    @ManyToOne
    Student studentID;

    @NonNull
    String applicationBody;

    @NonNull
    @ManyToOne
    Teacher approvedBy;
}
