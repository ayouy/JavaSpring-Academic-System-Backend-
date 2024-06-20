package com.mw.education.service;

import com.github.pagehelper.PageInfo;
import com.mw.education.domain.compose.Course;
import com.mw.education.domain.joined_entity.CourseAndTerm;
import com.mw.education.domain.joined_entity.ClassCourseAndCourse;

import java.util.List;

public interface CourseService {
    PageInfo<Course> selectAll(int pageSize, int pageNum);
    Course selectById(int id);
    CourseAndTerm getCourseAndTermByCourseId(int id);
    List<ClassCourseAndCourse> getAllClassCourseAndCourse();
    ClassCourseAndCourse getClassCourseAndCourseByCourseId(int id);
    boolean deleteById(int id);
    boolean edit(Course course);
    boolean add(Course course);
}