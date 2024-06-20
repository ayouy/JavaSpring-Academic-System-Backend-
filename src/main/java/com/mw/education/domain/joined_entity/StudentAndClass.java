package com.mw.education.domain.joined_entity;
import com.mw.education.domain.compose.ClassGroup;
import com.mw.education.domain.compose.Student;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class StudentAndClass {
    private Student student;
    private ClassGroup classGroup;

    public boolean isEmpty() {
        return student == null && classGroup == null;
    }
}
