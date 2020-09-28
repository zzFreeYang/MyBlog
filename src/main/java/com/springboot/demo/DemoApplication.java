package com.springboot.demo;

import com.springboot.demo.service.CommentService;
import com.springboot.demo.service.impl.CommentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@SpringBootApplication()
@MapperScan("com.springboot.mapper")
@EnableCaching(proxyTargetClass = true)
public class DemoApplication implements ApplicationRunner {

    @Autowired
    private CommentService commentService;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        //测试Cache的使用
        log.info("  ===========  start test cache   ===========    ");
        for(int i = 0 ; i < 10 ; i++){
            log.info("Reading from cache.");
            commentService.listAllComment();
        }
            commentService.reloadComment();
            log.info("Reading after refresh");
            commentService.listAllComment().forEach(c ->log.info("Comment"));
    }
}
