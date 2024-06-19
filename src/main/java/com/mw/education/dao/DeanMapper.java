package com.mw.education.dao;

import com.mw.education.domain.compose.Dean;
import com.mw.education.domain.joined_entity.CollegeAndDean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DeanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Dean record);

    int insertSelective(Dean record);

    Dean selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dean record);

    int updateByPrimaryKey(Dean record);

    List<Dean> selectAll();

    List<CollegeAndDean> getAllCollegeAndDean();

    CollegeAndDean getCollegeAndDeanByDeanId(Integer id);

    @Select("SELECT count(id) from dean where code=#{code} and password=#{password}")
    int countByCodeAndPassword(@Param("code") String code, @Param("password") String password);
}