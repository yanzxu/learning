package com.spring.ioc;

import lombok.Data;

@MyIoc
@Data
public class User {
    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
