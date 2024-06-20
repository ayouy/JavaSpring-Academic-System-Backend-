package com.mw.education.dao;

import com.mw.education.domain.compose.StudentCourseScore;
import com.mw.education.domain.joined_entity.StudentCourseScoreAndCourse;

import java.util.List;

public interface StudentCourseScoreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StudentCourseScore record);

    int insertSelective(StudentCourseScore record);

    StudentCourseScore selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StudentCourseScore record);

    int updateByPrimaryKey(StudentCourseScore record);

    List<StudentCourseScore> selectAll();

    List<StudentCourseScoreAndCourse> getAllStudentCourseScoreAndCourse();

    StudentCourseScoreAndCourse getStudentCourseScoreAndCourseByStudentIdAndCourseId(Integer studentId, Integer courseId);
    List<StudentCourseScoreAndCourse> getStudentCourseScoreAndCourseByStudentId(Integer studentId );
    List<StudentCourseScoreAndCourse> getStudentCourseScoreAndCourseByCourseId(Integer courseId );
}