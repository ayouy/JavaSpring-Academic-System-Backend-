package com.mw.education.domain.joined_entity;
import com.mw.education.domain.compose.ClassGroup;
import com.mw.education.domain.compose.Student;
import lombok.Data;

@Data
public class StudentAndClass {
    private Student student;
    private ClassGroup classGroup;

}
