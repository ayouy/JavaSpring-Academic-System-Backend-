package com.mw.education.domain.joined_entity;

import com.mw.education.domain.compose.ClassGroup;
import com.mw.education.domain.compose.Student;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ClassStudents implements Serializable {
    private ClassGroup classGroup;
    private List<Student> students;
}
