package com.ideal.studentlog.helpers.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
@AllArgsConstructor
public class TestDTO {

    @NonNull
    String subject;

    @NonNull
    String examiner;

    @NonNull
    Date date;

}
