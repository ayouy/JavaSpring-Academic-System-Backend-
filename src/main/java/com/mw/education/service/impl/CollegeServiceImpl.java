package com.mw.education.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mw.education.dao.*;
import com.mw.education.domain.compose.College;
import com.mw.education.domain.compose.Speciality;
import com.mw.education.domain.compose.Student;
import com.mw.education.domain.compose.Teacher;
import com.mw.education.domain.joined_entity.CollegeAndDean;
import com.mw.education.domain.joined_entity.SpecialityAndCollege;
import com.mw.education.service.ClassGroupService;
import com.mw.education.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeServiceImpl implements CollegeService {
    @Autowired
    private CollegeMapper collegeMapper;
    @Autowired
    private SpecialityMapper specialityMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private ClassGroupMapper classGroupMapper;

    @Override
    public PageInfo<College> selectAll(int pageSize, int pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<College> list = collegeMapper.selectAll();
        return new PageInfo<>(list);
    }

    @Override
    public College selectById(int id) {
        return collegeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CollegeAndDean> getAllCollegeAndDean() {
        return collegeMapper.getAllCollegeAndDean();
    }

    @Override
    public List<CollegeAndDean> getCollegeAndDeanByCollegeId(int id) {
        return collegeMapper.getCollegeAndDeanByCollegeId(id);
    }

    @Override
    public List<SpecialityAndCollege> getAllSpecialityAndCollege() {
        return collegeMapper.getAllSpecialityAndCollege();
    }

    @Override
    public List<SpecialityAndCollege> getSpecialityAndCollegeById(int id) {
        return collegeMapper.getSpecialityAndCollegeById(id);
    }

    @Override
    public boolean deleteById(int id) {
        return collegeMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean add(College college) {
        return collegeMapper.insertSelective(college) > 0;
    }

    @Override
    public boolean edit(College college) {
        return collegeMapper.updateByPrimaryKeySelective(college) > 0;
    }

    @Override
    public int getCollegeIdStudentCode(String code) {
        return collegeMapper.getCollegeIdStudentCode(code);
    }

    @Override
    public int getCollegeIdByTeacherCode(String code) {
        return collegeMapper.getCollegeIdByTeacherCode(code);
    }

    @Override
    public int getCollegeIdByDeanCode(String code) {
        return collegeMapper.getCollegeIdByDeanCode(code);
    }
}