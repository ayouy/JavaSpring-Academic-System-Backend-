package com.mw.education.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mw.education.dao.DeanMapper;
import com.mw.education.domain.compose.Dean;
import com.mw.education.domain.joined_entity.CollegeAndDean;
import com.mw.education.service.DeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeanServiceImpl implements DeanService {

    @Autowired
    private DeanMapper deanMapper;

    @Override
    public PageInfo<Dean> selectAll(int pageSize, int pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<Dean> list = deanMapper.selectAll();
        return new PageInfo<>(list);
    }

    @Override
    public Dean selectById(int id) {
        return deanMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CollegeAndDean> getAllCollegeAndDean() {
        return deanMapper.getAllCollegeAndDean();
    }

    @Override
    public CollegeAndDean getCollegeAndDeanByDeanId(int id) {
        return deanMapper.getCollegeAndDeanByDeanId(id);
    }

    @Override
    public boolean deleteById(int id) {
        return deanMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean edit(Dean dean) {
        return deanMapper.updateByPrimaryKeySelective(dean) > 0;
    }

    @Override
    public boolean add(Dean dean) {
        return deanMapper.insertSelective(dean) > 0;
    }
}