package com.mw.education.domain.compose;

import lombok.Data;

import java.io.Serializable;

/**
 * 教务(设置专业、编制班级、开设课程、组织选课等)
 */
@Data
public class Dean implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String password;
    private String code;
    private String remark;
}