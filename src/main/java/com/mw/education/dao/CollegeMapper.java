package com.mw.education.dao;

import com.mw.education.domain.compose.College;
import com.mw.education.domain.joined_entity.CollegeAndDean;
import com.mw.education.domain.joined_entity.SpecialityAndCollege;

import java.util.List;

public interface CollegeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(College row);

    College selectByPrimaryKey(Integer id);

    List<College> selectAll();

    int updateByPrimaryKey(College row);

    List<CollegeAndDean>  getAllCollegeAndDean();
    CollegeAndDean getCollegeAndDeanByCollegeId(Integer id);

    SpecialityAndCollege getAllSpecialityAndCollege();

    List<SpecialityAndCollege> getSpecialityAndCollegeById(Integer id);
}