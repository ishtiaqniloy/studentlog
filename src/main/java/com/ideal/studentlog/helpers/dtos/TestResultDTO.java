package com.ideal.studentlog.helpers.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class TestResultDTO {

    @NonNull
    Integer testId;

    @NonNull
    Integer studentId;

    @NonNull
    String grade;

}
