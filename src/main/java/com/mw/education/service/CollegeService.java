package com.mw.education.service;

import com.github.pagehelper.PageInfo;
import com.mw.education.domain.compose.College;
import com.mw.education.domain.joined_entity.CollegeAndDean;
import com.mw.education.domain.joined_entity.SpecialityAndCollege;

import java.util.List;

public interface CollegeService {
    int getCollegeIdByTeacherCode(String code);
    int getCollegeIdStudentCode(String code);
    int getCollegeIdByDeanCode(String code);
    PageInfo<College> selectAll(int pageSize, int pageNum);
    College selectById(int id);
    List<CollegeAndDean> getAllCollegeAndDean();
    List<CollegeAndDean> getCollegeAndDeanByCollegeId(int id);
    List<SpecialityAndCollege> getAllSpecialityAndCollege();
    List<SpecialityAndCollege> getSpecialityAndCollegeById(int id);

    boolean deleteById(int id);
    boolean add(College college);
    boolean edit(College college);
}