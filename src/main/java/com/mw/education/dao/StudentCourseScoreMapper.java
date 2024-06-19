package com.mw.education.dao;

import com.mw.education.domain.compose.StudentCourseScore;

import java.util.List;

public interface StudentCourseScoreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StudentCourseScore record);

    int insertSelective(StudentCourseScore record);

    StudentCourseScore selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StudentCourseScore record);

    int updateByPrimaryKey(StudentCourseScore record);

    List<StudentCourseScore> selectAll();
}