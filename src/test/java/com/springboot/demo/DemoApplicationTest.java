package com.springboot.demo;

import com.springboot.demo.bean.Student;
import com.springboot.demo.service.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.PostConstruct;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class DemoApplicationTest {
    @Autowired
    private StudentService studentService;


    @Test
    public void Test() throws Exception {
        Student student1 = this.studentService.queryStudentBysno("1");
        System.out.println("学号" + student1.getSno() + "的学生姓名为：" + student1.getName());

        Student student2 = this.studentService.queryStudentBysno("1");
        System.out.println("学号" + student2.getSno() + "的学生姓名为：" + student2.getName());

    }
}

