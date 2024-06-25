package com.mw.education.dao;

import com.mw.education.domain.compose.Teacher;
import com.mw.education.domain.joined_entity.SpecialityAndClassStudents;
import com.mw.education.domain.joined_entity.TeacherAndCollege;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TeacherMapper {
    int getPrimaryKey(String code);
    int deleteByPrimaryKey(Integer id);

    int insert(Teacher row);
    int insertSelective(Teacher row);

    Teacher selectByPrimaryKey(Integer id);

    List<Teacher> selectAll();
    List<Teacher> selectAllByCollegeId(int collegeId);

    int updateByPrimaryKey(Teacher row);
    int updateByPrimaryKeySelective(Teacher row);

    TeacherAndCollege getTeacherAndCollegeByTeacherCode(String code);

    List<TeacherAndCollege> getTeacherAndCollegeByCollegeId(Integer id);
    List<TeacherAndCollege>  getAllTeacherAndCollege();

    List<TeacherAndCollege> getAllTeacherAndCollegeByTeacherCode(String code);
    List<SpecialityAndClassStudents> getAllSpecialityAndClassGroupAndStudents(String code);


    @Select("select count(id) from teacher where code =#{code} and password =#{password}")
    int countByCodeAndPassword(@Param("code")String code, @Param("password" ) String password);
}