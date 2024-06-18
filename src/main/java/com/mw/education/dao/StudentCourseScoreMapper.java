package com.mw.education.dao;

import com.mw.education.domain.compose.StudentCourseScore;

import java.util.List;

public interface StudentCourseScoreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StudentCourseScore row);

    StudentCourseScore selectByPrimaryKey(Integer id);

    List<StudentCourseScore> selectAll();

    int updateByPrimaryKey(StudentCourseScore row);
}