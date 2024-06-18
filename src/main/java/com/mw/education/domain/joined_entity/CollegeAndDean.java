package com.mw.education.domain.joined_entity;

import com.mw.education.domain.compose.College;
import com.mw.education.domain.compose.Dean;
import lombok.Data;

@Data
public class CollegeAndDean {
    private College college;
    private Dean dean;
}
