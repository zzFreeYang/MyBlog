package com.springboot.demo.controller;
import com.springboot.demo.bean.Student;
import com.springboot.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RestController
public class TestController {
    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/querystudent", method = RequestMethod.GET)
    public Student queryStudentBysno(String sno){
        return this.studentService.queryStudentBysno(sno);
    }

}
