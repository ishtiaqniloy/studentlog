package com.ideal.studentlog.helpers.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class ClassStudentDTO {
    @NonNull
    Integer classDetailsId;

    @NonNull
    Integer studentId;
}
