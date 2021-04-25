package com.ideal.studentlog.helpers.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;
@Data
@AllArgsConstructor
public class AttendanceDTO {

    @NonNull
    String studentId;

    @NonNull
    String teacherId;

    @NonNull
    Date date;

    @NonNull
    Boolean isPresent;
}
