package com.mw.education.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mw.education.dao.ClassGroupMapper;
import com.mw.education.domain.compose.ClassGroup;
import com.mw.education.domain.compose.Student;
import com.mw.education.domain.joined_entity.ClassAndSpeciality;
import com.mw.education.domain.joined_entity.ClassCourseAndCourse;
import com.mw.education.service.ClassGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassGroupServiceImpl implements ClassGroupService {

    @Autowired
    private ClassGroupMapper classGroupMapper;

    @Override
    public PageInfo<ClassGroup> getAllClasses(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ClassGroup> list = classGroupMapper.selectAll();
        return new PageInfo<>(list);
    }

    @Override
    public ClassGroup getClassById(Integer id) {
        return classGroupMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<ClassAndSpeciality> getAllClassAndSpeciality(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ClassAndSpeciality> list = classGroupMapper.getAllClassAndSpeciality();
        return new PageInfo<>(list);
    }

    @Override
    public List<ClassCourseAndCourse> getClassCoursesByClassId(Integer classId) {
        return classGroupMapper.getClassCourseAndCourseByClassId(classId);
    }

    @Override
    public int addClass(ClassGroup classGroup) {
        return classGroupMapper.insertSelective(classGroup);
    }

    @Override
    public int updateClass(ClassGroup classGroup) {
        return classGroupMapper.updateByPrimaryKeySelective(classGroup);
    }

    @Override
    public boolean deleteClassById(Integer id) {
        return classGroupMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public PageInfo<Student> getClassStudents(Integer classId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Student> list = classGroupMapper.getClassStudents(classId);
        return new PageInfo<>(list);
    }
}