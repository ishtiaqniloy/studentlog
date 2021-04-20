package com.ideal.studentlog.helpers.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class SubjectDTO {

    @NonNull
    String name;

    @NonNull
    String category;
}
