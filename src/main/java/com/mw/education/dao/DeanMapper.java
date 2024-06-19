package com.mw.education.dao;

import com.mw.education.domain.compose.Dean;
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

    @Select("SELECT count(id) from dean where code=#{code} and password=#{password}")
    int countByCodeAndPassword(@Param("code") String code, @Param("password") String password);
}