package com.mw.education.dao;

import com.mw.education.domain.compose.ClassGroup;
import com.mw.education.domain.compose.Student;
import com.mw.education.domain.joined_entity.ClassAndSpeciality;
import com.mw.education.domain.joined_entity.ClassCourseAndCourse;

import java.util.List;

public interface ClassGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ClassGroup row);
    int insertSelective(ClassGroup row);

    ClassGroup selectByPrimaryKey(Integer id);

    List<ClassGroup> selectAll();

    int updateByPrimaryKey(ClassGroup row);
    int updateByPrimaryKeySelective(ClassGroup row);

    List<ClassAndSpeciality> getAllClassAndSpeciality();

    ClassAndSpeciality getClassAndSpecialityById(Integer id);

    List<ClassCourseAndCourse> getAllClassCourseAndCourse();

    List<ClassCourseAndCourse> getClassCourseAndCourseByClassId(Integer id);

    List<Student> getClassStudents(Integer id);
    
}