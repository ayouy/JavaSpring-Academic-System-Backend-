package com.mw.education.dao;

import com.mw.education.domain.compose.ClassCourse;
import com.mw.education.domain.joined_entity.ClassCourseAndCourse;

import java.util.List;

public interface ClassCourseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ClassCourse record);

    int insertSelective(ClassCourse record);

    ClassCourse selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClassCourse record);

    int updateByPrimaryKey(ClassCourse record);

    List<ClassCourse> selectAll();

    List<ClassCourseAndCourse> selectAllJoinedCourse();
    ClassCourseAndCourse selectByPrimaryKeyJoinedCourse(Integer id);
}