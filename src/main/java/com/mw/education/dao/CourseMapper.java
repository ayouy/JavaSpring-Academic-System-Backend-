package com.mw.education.dao;

import com.mw.education.domain.compose.Course;
import com.mw.education.domain.joined_entity.ClassCourseAndCourse;
import com.mw.education.domain.joined_entity.CourseAndTerm;

import java.util.List;

public interface CourseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);

    List<Course> selectAll();

    List<CourseAndTerm> getAllCourseAndTerm();

    CourseAndTerm getCourseAndTermByCourseId(Integer id);

    List<ClassCourseAndCourse> getAllClassCourseAndCourse();
    ClassCourseAndCourse getClassCourseAndCourseByCourseId(Integer id);
}