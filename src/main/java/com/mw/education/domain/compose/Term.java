package com.mw.education.domain.compose;

import java.util.Date;
import lombok.Data;

/**
 * 学期表
 */
@Data
public class Term {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private Date startDate;
    private Date endDate;
}