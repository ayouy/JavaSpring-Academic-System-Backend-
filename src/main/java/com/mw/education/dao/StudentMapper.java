package com.mw.education.dao;

import com.mw.education.domain.compose.*;
import com.mw.education.domain.joined_entity.ClassCourseAndCourse;
import com.mw.education.domain.joined_entity.StudentAndClass;
import com.mw.education.domain.joined_entity.StudentCourseScoreAndCourse;
import com.mw.education.domain.joined_entity.TeacherAndCollege;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StudentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Student row);
    int insertSelective(Student row);

    Student selectByPrimaryKey(Integer id);

    List<Student> selectAll();

    int updateByPrimaryKey(Student row);
    int updateByPrimaryKeySelective(Student row);

    List<StudentAndClass> getAllStudentAndClass();

    StudentAndClass getStudentAndClassByStudentId(@Param("id") Integer id);
    StudentAndClass getStudentAndClassByClassId(@Param("id") Integer id);

    List<StudentAndClass> getClassmates(@Param("code") String code);
    List<TeacherAndCollege> getCollegeTeacherByStudentCode(@Param("id") String code);

    List<ClassCourseAndCourse> getClassCourseByStudentCode(@Param("id") int id);

    List<StudentCourseScoreAndCourse> getScoreByStudentCode(@Param("code") String code);

    int getClassIdByStudentCode(@Param("id") String code);

    @Select("SELECT count(id) from student where code=#{code} and password=#{password}")
    int countByCodeAndPassword(@Param("code")String code,@Param("password") String password);
}