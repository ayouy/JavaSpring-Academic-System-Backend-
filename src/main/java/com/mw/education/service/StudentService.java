package com.mw.education.service;

import com.github.pagehelper.PageInfo;
import com.mw.education.domain.compose.Student;
import com.mw.education.domain.joined_entity.StudentAndClass;

public interface StudentService {
    PageInfo<StudentAndClass> selectAll(int pageSize, int pageNum);
    StudentAndClass selectById(int id);
    boolean deleteById(int id);
    boolean edit(Student student);
    boolean add(Student student);
}