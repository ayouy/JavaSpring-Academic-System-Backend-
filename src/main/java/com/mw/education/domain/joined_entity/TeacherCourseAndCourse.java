package com.mw.education.domain.joined_entity;

import com.mw.education.domain.compose.Course;
import com.mw.education.domain.compose.Term;
import lombok.Data;

@Data
public class TeacherCourseAndCourse {
    private TeacherAndCollege teacherAndCollege;
    private Course course;
    private Term term;
}
