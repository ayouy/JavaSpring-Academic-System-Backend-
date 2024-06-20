package com.mw.education.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mw.education.dao.StudentMapper;
import com.mw.education.domain.compose.Student;
import com.mw.education.domain.joined_entity.StudentAndClass;
import com.mw.education.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageInfo<StudentAndClass> selectAll(int pageSize, int pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<StudentAndClass> list = studentMapper.getAllStudentAndClass();
        return new PageInfo<>(list);
    }

    @Override
    public StudentAndClass selectById(int id) {
        return studentMapper.getStudentAndClassByStudentId(id);
    }

    @Override
    public boolean deleteById(int id) {
        return studentMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean edit(Student student) {
        return (student.getName() != null && student.getCode() != null &&
                student.getPassword() != null && student.getClassId() != null &&
                student.getRemark() != null ?
                studentMapper.updateByPrimaryKey(student) :
                studentMapper.updateByPrimaryKeySelective(student)) > 0;
    }

    @Override
    public boolean add(Student student) {
        return studentMapper.insertSelective(student) > 0;
    }
}