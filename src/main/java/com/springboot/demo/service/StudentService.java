package com.springboot.demo.service;

import com.springboot.demo.bean.Student;

public interface StudentService {
    int add(Student student);
    int update(Student student);
    int deleteBysno(String sno);
    Student queryStudentBysno(String sno);
}
