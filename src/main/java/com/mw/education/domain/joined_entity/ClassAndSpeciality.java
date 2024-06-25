package com.mw.education.domain.joined_entity;

import com.mw.education.domain.compose.ClassGroup;
import com.mw.education.domain.compose.Speciality;
import lombok.Data;

@Data
public class ClassAndSpeciality {
    private ClassGroup classGroup;
    private Speciality speciality;
    int totalStudents;
    String collegeName;
}
