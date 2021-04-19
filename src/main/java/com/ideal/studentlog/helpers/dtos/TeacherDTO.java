package com.ideal.studentlog.helpers.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.NonNull;

import java.util.Date;

@Data
@AllArgsConstructor
public class TeacherDTO {

    @NonNull
    String name;

    @NonNull
    Date dateOfBirth;

    @NonNull
    Date joiningDate;

    @NonNull
    Date resignationDate;

    @NonNull
    String highestEducationLevel;

    @NonNull
    String nationalRegistrationNo;

    @NonNull
    String teacherId;

    @NonNull
    String designation;

    @NonNull
    String contactNo;

    @NonNull
    String presentAddress;

    @NonNull
    String permanentAddress;

    @NonNull
    String bloodGroup;
}
