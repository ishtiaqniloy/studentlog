package com.ideal.studentlog.helpers.dataclass;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class SubjectDetailsDTO {
    
    @NonNull
    Integer subjectId;

    @NonNull
    Integer teacherId;

    @NonNull
    Integer classDetailsId;

}
