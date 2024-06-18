package com.mw.education.domain.compose;

import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
public class Course implements Serializable {
    private Integer id;

    private String name;

    private String code;

    private Integer termId;

    private BigDecimal credit;

    private static final long serialVersionUID = 1L;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Course other = (Course) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.getTermId() == null ? other.getTermId() == null : this.getTermId().equals(other.getTermId()))
            && (this.getCredit() == null ? other.getCredit() == null : this.getCredit().equals(other.getCredit()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getTermId() == null) ? 0 : getTermId().hashCode());
        result = prime * result + ((getCredit() == null) ? 0 : getCredit().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", name=" + name +
                ", code=" + code +
                ", termId=" + termId +
                ", credit=" + credit +
                "]";
    }
}