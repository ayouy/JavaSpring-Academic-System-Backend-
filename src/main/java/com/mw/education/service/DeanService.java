package com.mw.education.service;

import com.github.pagehelper.PageInfo;
import com.mw.education.domain.compose.Dean;
import com.mw.education.domain.joined_entity.CollegeAndDean;

import java.util.List;

public interface DeanService {
    PageInfo<Dean> selectAll(int pageSize, int pageNum);
    Dean selectById(int id);
    List<CollegeAndDean> getAllCollegeAndDean();
    CollegeAndDean getCollegeAndDeanByDeanId(int id);
    boolean deleteById(int id);
    boolean edit(Dean dean);
    boolean add(Dean dean);
}