package com.mw.education.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mw.education.controller.AjaxResult;
import com.mw.education.dao.TeacherCourseMapper;
import com.mw.education.dao.TeacherMapper;
import com.mw.education.domain.compose.Teacher;
import com.mw.education.domain.compose.TeacherCourse;
import com.mw.education.domain.joined_entity.TeacherAndCollege;
import com.mw.education.domain.joined_entity.TeacherCourseAndCourse;
import com.mw.education.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public TeacherAndCollege selectById(int id) {
        return teacherMapper.getTeacherAndCollegeByTeacherId(id);
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
    public List<TeacherCourseAndCourse> getTeacherCourseAndCourseByTeacherId(Integer teacherId) {
        return teacherCourseMapper.getTeacherCourseAndCourseByTeacherId(teacherId);
    }

    @Override
    public int deleteTeacherCourseByTeacherIdAndCourseId(Integer id,Integer courseId) {
        Integer primaryKey = teacherCourseMapper.getPrimaryKeyByTeacherIdAndCourseId(id,courseId);
        if (primaryKey ==null) {
            return 0;
        }
        return teacherCourseMapper.deleteByPrimaryKey(teacherCourseMapper.getPrimaryKeyByTeacherIdAndCourseId(id,courseId));
    }

    @Override
    public int addTeacherCourse(TeacherCourse teacherCourse) {
        return teacherCourseMapper.insertSelective(teacherCourse);
    }

    @Override
    public int updateTeacherCourse(TeacherCourse teacherCourse) {
        return teacherCourseMapper.updateByPrimaryKeySelective(teacherCourse);
    }
}