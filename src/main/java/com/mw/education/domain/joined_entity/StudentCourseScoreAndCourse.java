package com.mw.education.domain.joined_entity;

import com.mw.education.domain.compose.Course;
import lombok.Data;

@Data
public class StudentCourseScoreAndCourse {
    private StudentCourseScoreAndCourse studentCourseScoreAndCourse;
    private Course course;
}
