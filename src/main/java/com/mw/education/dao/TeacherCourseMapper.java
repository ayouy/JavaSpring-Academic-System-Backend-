package com.mw.education.dao;

import com.mw.education.domain.compose.TeacherCourse;
import com.mw.education.domain.joined_entity.TeacherCourseAndCourse;

import java.util.List;

public interface TeacherCourseMapper {
    int deleteByPrimaryKey(Integer id);

    Integer getPrimaryKeyByTeacherIdAndCourseId(Integer id,Integer courseId);

    int insert(TeacherCourse row);
    int insertSelective(TeacherCourse row);

    TeacherCourse selectByPrimaryKeyAndForeignKey(Integer id);

    List<TeacherCourse> selectAll();

    int updateByPrimaryKey(TeacherCourse row);
    int updateByPrimaryKeySelective(TeacherCourse row);

    List<TeacherCourseAndCourse> getAllTeacherCourseAndCourse();

    TeacherCourseAndCourse getTeacherCourseAndCourseByTeacherCourseId(Integer id);

    List<TeacherCourseAndCourse> getTeacherCourseAndCourseByTeacherId(Integer id);
}