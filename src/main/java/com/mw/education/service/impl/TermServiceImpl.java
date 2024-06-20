package com.mw.education.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mw.education.dao.TermMapper;
import com.mw.education.domain.compose.Term;
import com.mw.education.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TermServiceImpl implements TermService {

    @Autowired
    private TermMapper termMapper;

    @Override
    public PageInfo<Term> selectAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Term> list = termMapper.selectAll();
        return new PageInfo<>(list);
    }

    @Override
    public Term selectById(int id) {
        return termMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean deleteById(int id) {
        return termMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean edit(Term term) {
        return termMapper.updateByPrimaryKeySelective(term) > 0;
    }

    @Override
    public boolean add(Term term) {
        return termMapper.insertSelective(term) > 0;
    }
}