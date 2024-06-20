package com.mw.education.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mw.education.dao.SpecialityMapper;
import com.mw.education.domain.compose.Speciality;
import com.mw.education.domain.joined_entity.SpecialityAndCollege;
import com.mw.education.service.SpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialityServiceImpl implements SpecialityService {
    @Autowired
    private SpecialityMapper specialityMapper;

    @Override
    public PageInfo<SpecialityAndCollege> selectAllJoinedCollege(int pageSize, int pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<SpecialityAndCollege> list = specialityMapper.getAllSpecialityAndCollege();
        return new PageInfo<>(list);
    }

    @Override
    public SpecialityAndCollege selectByIdJoinedCollege(int id) {
        return specialityMapper.getSpecialityAndCollegeBySpecialityId(id);
    }

    @Override
    public boolean deleteByPrimaryKey(int id) {
        return specialityMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean insert(Speciality speciality) {
        return specialityMapper.insertSelective(speciality) > 0;
    }

    @Override
    public boolean updateByPrimaryKey(Speciality speciality) {
        return specialityMapper.updateByPrimaryKeySelective(speciality) > 0;
    }

    @Override
    public Speciality selectByPrimaryKey(int id) {
        return specialityMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Speciality> selectAll() {
        return specialityMapper.selectAll();
    }
}