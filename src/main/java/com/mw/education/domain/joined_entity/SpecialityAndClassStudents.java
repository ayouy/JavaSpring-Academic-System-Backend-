package com.mw.education.domain.joined_entity;

import com.mw.education.domain.compose.ClassGroup;
import com.mw.education.domain.compose.College;
import com.mw.education.domain.compose.Speciality;
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.Data;

import java.util.List;


@Data
public class SpecialityAndClassStudents {
    private Speciality speciality;
    private List<ClassStudents> classStudentsList;


    public SpecialityAndClassStudents(Speciality speciality, List<ClassStudents> classStudentsList) {
        this.speciality = speciality;
        this.classStudentsList = classStudentsList;
    }
    public SpecialityAndClassStudents() {
    }
}
