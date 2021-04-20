package com.ideal.studentlog.helpers.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
@AllArgsConstructor
public class AdminDTO {

    @NonNull
    String name;

    @NonNull
    String designation;

    @NonNull
    Date dateOfBirth;

    @NonNull
    String bloodGroup;

    @NonNull
    String highestEducationLevel;

    @NonNull
    Date joiningDate;

    Date resignationDate;

    @NonNull
    String contactNumber;

    @NonNull
    String presentAddress;

    @NonNull
    String permanentAddress;
}
