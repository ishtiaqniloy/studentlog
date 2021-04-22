package com.ideal.studentlog.helpers.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;
@Data
@AllArgsConstructor
public class AttendanceDTO {

    @NonNull
    Date date;

    @NonNull
    Integer studentId;

    @NonNull
    Integer teacherId;

    @NonNull
    Boolean isPresent;
}
