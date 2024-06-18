package com.mw.education.domain.joined_entity;

import com.mw.education.domain.compose.College;
import com.mw.education.domain.compose.Teacher;
import lombok.Data;


@Data
public class TeacherAndCollege {
    private Teacher teacher;
    private College college;

}
