package com.ideal.studentlog.helpers.dataclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
public class StudentDTO {

    Integer id;

    @NonNull
    @Size(min = 5, max = 50)
    String name;

    @NonNull
    @Size(min = 8, max = 20)
    String birthRegistrationId;

    @NonNull
    @Size(min = 8, max = 20)
    String studentId;

    @NonNull
    Date dateOfBirth;

    @NonNull
    @Pattern(regexp = "^(A|B|AB|O)[+-]$")
    String bloodGroup;

    @NonNull
    @Size(min = 5, max = 100)
    String presentAddress;

    @NonNull
    @Size(min = 5, max = 100)
    String permanentAddress;

    @NonNull
    @Size(min = 5, max = 50)
    String guardianName;

    @Email
    String guardianEmail;

    @NonNull
    @Pattern(regexp = "^(\\+88)?01[0-9]{9}$")
    String guardianPhone;

}
