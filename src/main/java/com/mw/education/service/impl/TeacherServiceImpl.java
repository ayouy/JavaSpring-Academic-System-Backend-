package com.mw.education.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mw.education.controller.AjaxResult;
import com.mw.education.dao.TeacherCourseMapper;
import com.mw.education.dao.TeacherMapper;
import com.mw.education.domain.compose.ClassGroup;
import com.mw.education.domain.compose.Speciality;
import com.mw.education.domain.compose.Teacher;
import com.mw.education.domain.compose.TeacherCourse;
import com.mw.education.domain.joined_entity.ClassStudents;
import com.mw.education.domain.joined_entity.SpecialityAndClassStudents;
import com.mw.education.domain.joined_entity.TeacherAndCollege;
import com.mw.education.domain.joined_entity.TeacherCourseAndCourse;
import com.mw.education.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherCourseMapper teacherCourseMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public PageInfo<TeacherAndCollege> selectAll(int pageSize, int pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<TeacherAndCollege> list = teacherMapper.getAllTeacherAndCollege();
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<TeacherAndCollege> getAllTeacherAndCollegeByTeacherCode(String code, int pageSize, int pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<TeacherAndCollege> list = teacherMapper.getAllTeacherAndCollegeByTeacherCode(code);
        return new PageInfo<>(list);
    }
    @Override
    public TeacherAndCollege selectByTeacherCode(String code) {
        return teacherMapper.getTeacherAndCollegeByTeacherCode(code);
    }

    @Override
    public AjaxResult deleteById(int id) {
        int n = teacherMapper.deleteByPrimaryKey(id);
        if (n > 0) {
            return AjaxResult.success().msg("删除成功");
        } else {
            return AjaxResult.error().msg("删除失败，未找到对应教师");
        }
    }
    private boolean isTeacherFullyPopulated(Teacher teacher) {
        return teacher.getName() != null && teacher.getCode() != null &&
                teacher.getPassword() != null && teacher.getCollegeId() != null &&
                teacher.getRemark() != null;
    }
    @Override
    public AjaxResult edit(Teacher teacher) {
        int n = isTeacherFullyPopulated(teacher) ? teacherMapper.updateByPrimaryKey(teacher) : teacherMapper.updateByPrimaryKeySelective(teacher);
        if (n > 0) {
            return AjaxResult.success().msg("更新成功");
        } else {
            return AjaxResult.error().msg("更新失败，未找到对应教师");
        }
    }

    @Override
    public AjaxResult add(Teacher teacher) {
        int n = isTeacherFullyPopulated(teacher) ? teacherMapper.insert(teacher) : teacherMapper.insertSelective(teacher);
        if (n > 0) {
            return AjaxResult.success().msg("添加成功");
        } else {
            return AjaxResult.error().msg("添加失败");
        }
    }
    @Override
    public List<TeacherCourseAndCourse> getAllTeacherCourseAndCourse() {
        return teacherCourseMapper.getAllTeacherCourseAndCourse();
    }

    @Override
    public TeacherCourseAndCourse getTeacherCourseAndCourseByTeacherCourseId(Integer id) {
        return teacherCourseMapper.getTeacherCourseAndCourseByTeacherCourseId(id);
    }

    @Override
    public List<TeacherCourseAndCourse> getTeacherCourseAndCourseByTeacherCode(String teacherCode) {
        return teacherCourseMapper.getTeacherCourseAndCourseByTeacherCode(teacherCode);
    }

    @Override
    public int deleteTeacherCourseByTeacherCodeAndCourseId(String code, Integer courseId) {
        Integer primaryKey = teacherCourseMapper.getPrimaryKeyByTeacherCodeAndCourseId(code,courseId);
        if (primaryKey ==null) {
            return 0;
        }
        return teacherCourseMapper.deleteByPrimaryKey(primaryKey);
    }

    @Override
    public int addTeacherCourse(TeacherCourse teacherCourse) {
        return teacherCourseMapper.insertSelective(teacherCourse);
    }

    @Override
    public int updateTeacherCourse(TeacherCourse teacherCourse) {
        return teacherCourseMapper.updateByPrimaryKeySelective(teacherCourse);
    }

    @Override
    public PageInfo<SpecialityAndClassStudents> getAllTeacherSpecialityAndClassStudents(String code, int pageSize, int pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<SpecialityAndClassStudents> dataList = teacherMapper.getAllSpecialityAndClassGroupAndStudents(code);
        Map<Integer, ClassStudents> classStudentsMap = new HashMap<>();
        for (SpecialityAndClassStudents item : dataList) {
            for (ClassStudents classStudents : item.getClassStudentsList()) {
                if (!classStudentsMap.containsKey(classStudents.getClassGroup().getId())) {
                    classStudentsMap.put(classStudents.getClassGroup().getId(), classStudents);
                } else {
                    classStudentsMap.get(classStudents.getClassGroup().getId())
                            .getStudents().addAll(classStudents.getStudents());
                }
            }
        }
        Map<Integer, SpecialityAndClassStudents> specialityMap = new HashMap<>();
        for (SpecialityAndClassStudents item : dataList) {
            Speciality speciality = item.getSpeciality();
            if (!specialityMap.containsKey(speciality.getId())) {
                specialityMap.put(speciality.getId(), new SpecialityAndClassStudents(speciality, new ArrayList<>()));
            }
            for (ClassStudents classStudents : item.getClassStudentsList()) {
                if (!specialityMap.get(speciality.getId()).getClassStudentsList().contains(classStudentsMap.get(classStudents.getClassGroup().getId()))) {
                    specialityMap.get(speciality.getId()).getClassStudentsList().add(classStudentsMap.get(classStudents.getClassGroup().getId()));
                }
            }
        }
        return new PageInfo<>(new ArrayList<>(specialityMap.values()));

    }

}