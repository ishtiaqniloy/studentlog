package com.ideal.studentlog.helpers.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
@AllArgsConstructor
public class TeacherDTO {

    @lombok.NonNull
    String name;

    @lombok.NonNull
    Date dateOfBirth;

    @lombok.NonNull
    Date joiningDate;

    @lombok.NonNull
    Date resignationDate;

    @lombok.NonNull
    String highestEducationLevel;

    @lombok.NonNull
    String nationalRegistrationNo;

    @lombok.NonNull
    String teacherId;

    @lombok.NonNull
    String designation;

    @lombok.NonNull
    String contactNo;

    @lombok.NonNull
    String presentAddress;

    @lombok.NonNull
    String permanentAddress;

    @lombok.NonNull
    String bloodGroup;
}
