package com.mw.education.dao;

import com.mw.education.domain.compose.Speciality;
import com.mw.education.domain.joined_entity.SpecialityAndCollege;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SpecialityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Speciality row);
    int insertSelective(Speciality row);

    Speciality selectByPrimaryKey(Integer id);

    List<Speciality> selectAll();
    List<Speciality> selectAllByCollegeId(int collegeId);

    int updateByPrimaryKey(Speciality row);
    int updateByPrimaryKeySelective(Speciality row);

    SpecialityAndCollege getSpecialityAndCollegeBySpecialityId(Integer id);

    List<SpecialityAndCollege> getAllSpecialityAndCollege();

}