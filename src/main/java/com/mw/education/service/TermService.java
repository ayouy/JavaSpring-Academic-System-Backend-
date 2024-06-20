package com.mw.education.service;


import com.mw.education.domain.compose.Term;
import com.github.pagehelper.PageInfo;

public interface TermService {
    PageInfo<Term> selectAll(int pageNum, int pageSize);

    Term selectById(int id);

    boolean deleteById(int id);

    boolean edit(Term term);

    boolean add(Term term);
}