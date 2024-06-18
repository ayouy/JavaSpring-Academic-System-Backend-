package com.mw.education.dao;

import com.mw.education.domain.compose.TeacherCourse;

import java.util.List;

public interface TeacherCourseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TeacherCourse row);

    TeacherCourse selectByPrimaryKey(Integer id);

    List<TeacherCourse> selectAll();

    int updateByPrimaryKey(TeacherCourse row);
}