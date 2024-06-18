package com.mw.education.dao;

import com.mw.education.domain.compose.Student;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StudentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Student row);

    Student selectByPrimaryKey(Integer id);

    List<Student> selectAll();

    int updateByPrimaryKey(Student row);

    @Select("SELECT count(id) from student where code=#{code} and password=#{password}")
    int countByCodeAndPassword(@Param("code")String code,@Param("password") String password);
}