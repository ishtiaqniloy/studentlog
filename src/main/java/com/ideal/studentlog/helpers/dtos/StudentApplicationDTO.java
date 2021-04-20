package com.ideal.studentlog.helpers.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
public class StudentApplicationDTO implements Serializable {

    @NonNull
    Date appliedDate;

    @NonNull
    String approvedBy;

    @NonNull
    String name;

    @NonNull
    Date dateOfBirth;

    @NonNull
    String bloodGroup;

    @NonNull
    String birthRegistrationId;

    @NonNull
    String registrationId;

    @NonNull
    String presentAddress;

    @NonNull
    String permanentAddress;

    @NonNull
    String guardianName;

    String guardianEmail;

    @NonNull
    String guardianPhone;

    @NonNull
    Integer appliedForGrade;
}
