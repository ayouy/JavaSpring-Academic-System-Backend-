package com.mw.education.domain.joined_entity;


import com.mw.education.domain.compose.ClassCourse;
import com.mw.education.domain.compose.Course;
import com.mw.education.domain.compose.Term;
import lombok.Data;

@Data
public class ClassCourseAndCourse {
    private ClassCourse classCourse;
    private Term term;
    private Course course;
}
