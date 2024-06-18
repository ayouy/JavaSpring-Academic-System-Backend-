package com.mw.education.dao;

import com.mw.education.domain.compose.ClassCourse;

import java.util.List;

public interface ClassCourseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ClassCourse row);

    ClassCourse selectByPrimaryKey(Integer id);

    List<ClassCourse> selectAll();

    int updateByPrimaryKey(ClassCourse row);

    List<ClassCourse> selectClassCourseWithCourseDetails();
}