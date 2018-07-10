package com.baidu.mybaidu.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class NotNullTest {
    @NotEmpty
    private String not;
    @NotNull
    private Integer Null;

    public String getNot() {
        return not;
    }

    public void setNot(String not) {
        this.not = not;
    }

    public Integer getNull() {
        return Null;
    }

    public void setNull(Integer aNull) {
        Null = aNull;
    }
}
