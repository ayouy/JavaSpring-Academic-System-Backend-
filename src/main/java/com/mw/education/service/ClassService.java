package com.mw.education.service;

import com.mw.education.domain.compose.ClassGroup;
import com.mw.education.domain.joined_entity.ClassAndSpeciality;
import com.mw.education.domain.joined_entity.ClassCourseAndCourse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClassService {
    List<ClassGroup> getAllClasses();
    ClassGroup createClass(ClassGroup classGroup);
    ClassGroup updateClass(Integer id, ClassGroup classGroup);
    boolean deleteClassById(Integer id);
    ClassGroup getClassById(Integer id);
    List<ClassAndSpeciality> getAllClassAndSpecialities();
    List<ClassCourseAndCourse> getClassCoursesByClassId(Integer classId);
}