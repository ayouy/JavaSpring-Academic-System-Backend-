package com.mw.education.dao;

import com.mw.education.domain.compose.Teacher;
import com.mw.education.domain.joined_entity.TeacherAndCollege;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TeacherMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Teacher row);
    int insertSelective(Teacher row);

    Teacher selectByPrimaryKey(Integer id);

    List<Teacher> selectAll();

    int updateByPrimaryKey(Teacher row);
    int updateByPrimaryKeySelective(Teacher row);

    TeacherAndCollege getTeacherAndCollegeByTeacherId(Integer id);

    List<TeacherAndCollege> getTeacherAndCollegeByCollegeId(Integer id);

    List<TeacherAndCollege> getAllTeacherAndCollege();

    @Select("select count(id) from teacher where code =#{code} and password =#{password}")
    int countByCodeAndPassword(@Param("code")String code, @Param("password" ) String password);
}