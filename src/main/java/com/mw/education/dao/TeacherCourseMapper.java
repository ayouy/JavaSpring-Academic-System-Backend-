package com.mw.education.dao;

import com.mw.education.domain.compose.TeacherCourse;
import com.mw.education.domain.joined_entity.TeacherCourseAndCourse;

import java.util.List;

public interface TeacherCourseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TeacherCourse row);

    TeacherCourse selectByPrimaryKey(Integer id);

    List<TeacherCourse> selectAll();

    int updateByPrimaryKey(TeacherCourse row);

    List<TeacherCourseAndCourse> getAllTeacherCourseAndCourse();

    TeacherCourseAndCourse getTeacherCourseAndCourseByTeacherCourseId(Integer id);

    List<TeacherCourseAndCourse> getTeacherCourseAndCourseByTeacherId(Integer id);
}