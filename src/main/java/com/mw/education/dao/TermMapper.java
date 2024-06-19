package com.mw.education.dao;

import com.mw.education.domain.compose.Term;

import java.util.List;

public interface TermMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Term record);

    int insertSelective(Term record);

    Term selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Term record);

    int updateByPrimaryKey(Term record);

    List<Term> selectAll();
}