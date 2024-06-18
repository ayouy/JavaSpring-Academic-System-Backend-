package com.mw.education.domain.joined_entity;

import com.mw.education.domain.compose.College;
import com.mw.education.domain.compose.Teacher;
import com.mw.education.domain.compose.Speciality;
import lombok.Data;


@Data
public class SpecialityAndCollege {
    private Speciality speciality;
    private College college;

}
