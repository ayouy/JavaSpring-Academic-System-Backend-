package com.mw.education.service;

import com.github.pagehelper.PageInfo;
import com.mw.education.domain.compose.Speciality;
import com.mw.education.domain.joined_entity.SpecialityAndCollege;

import java.util.List;

public interface SpecialityService {
    PageInfo<SpecialityAndCollege> selectAllJoinedCollege(int pageSize, int pageNum);
    SpecialityAndCollege selectByIdJoinedCollege(int id);
    boolean deleteByPrimaryKey(int id);
    boolean insert(Speciality speciality);
    boolean updateByPrimaryKey(Speciality speciality);
    Speciality selectByPrimaryKey(int id);
    List<Speciality> selectAll();
}