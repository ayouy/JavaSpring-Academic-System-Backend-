package com.mw.education.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mw.education.dao.StudentMapper;
import com.mw.education.domain.compose.Student;
import com.mw.education.domain.joined_entity.ClassCourseAndCourse;
import com.mw.education.domain.joined_entity.StudentAndClass;
import com.mw.education.domain.joined_entity.StudentCourseScoreAndCourse;
import com.mw.education.domain.joined_entity.TeacherAndCollege;
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

    @Override
    public PageInfo<StudentAndClass> selectClassmates(String code, int pageSize, int pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<StudentAndClass> list = studentMapper.getClassmates(code);
        return new PageInfo<>(list);
    }

    @Override
    public  PageInfo<TeacherAndCollege> selectCollegeTeachers(String code, int pageSize, int pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<TeacherAndCollege> list = studentMapper.getCollegeTeacherByStudentCode(code);
        return new PageInfo<>(list);
    }

    @Override
    public  PageInfo<ClassCourseAndCourse> selectClassCourses(String code, int pageSize, int pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<ClassCourseAndCourse> list = studentMapper.getClassCourseByStudentCode(studentMapper.getClassIdByStudentCode(code));
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<StudentCourseScoreAndCourse> selectScoreByStudentCode(String code, int pageSize, int pageNum){
        PageHelper.startPage(pageNum, pageSize);
        List<StudentCourseScoreAndCourse> list = studentMapper.getScoreByStudentCode(code);
        return new PageInfo<>(list);
    }
}