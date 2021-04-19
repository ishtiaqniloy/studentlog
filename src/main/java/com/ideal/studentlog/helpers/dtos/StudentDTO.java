package com.ideal.studentlog.helpers.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
@AllArgsConstructor
public class StudentDTO {

    @NonNull
    String name;

    @NonNull
    String birthRegistrationId;

    @NonNull
    String studentId;

    @NonNull
    Date dateOfBirth;

    @NonNull
    String bloodGroup;

    @NonNull
    String presentAddress;

    @NonNull
    String permanentAddress;

    @NonNull
    String guardianName;

    String guardianEmail;

    @NonNull
    String guardianPhone;

}
