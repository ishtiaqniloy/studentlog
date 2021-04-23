package com.ideal.studentlog.helpers.dtos;

import com.ideal.studentlog.database.models.Student;
import com.ideal.studentlog.database.models.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
@AllArgsConstructor
public class LeaveApplicationDTO {

    @lombok.NonNull
    Date dateFrom;

    @lombok.NonNull
    Date dateTo;

    @lombok.NonNull
    Integer studentId;

    @lombok.NonNull
    String applicationBody;

    Integer approvedById;
}
