package com.springboot.demo.controller;

import com.springboot.demo.entity.Account;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
//@RestController
public class IndexController {

    @GetMapping("/account")
    public String index(Model m) {
        log.info("Slf4j");

        List<Account> list = new ArrayList<Account>();
        list.add(new Account("KangKang", "康康", "e10adc3949ba59abbe56e", "超级管理员", "17777777777"));
        list.add(new Account("Mike", "麦克", "e10adc3949ba59abbe56e", "管理员", "13444444444"));
        list.add(new Account("Jane", "简", "e10adc3949ba59abbe56e", "运维人员", "18666666666"));
        list.add(new Account("Maria", "玛利亚", "e10adc3949ba59abbe56e", "清算人员", "19999999999"));

        m.addAttribute("accountList", list);
        log.info("1111");
        return "account";
    }

    @ApiOperation(value = "跳转到欢迎界面")
    @GetMapping("/index")
    public String hello() {

        Map a = new HashMap();
        boolean jsbz = true;
        a.put("jsbz", jsbz);
        System.out.println(a.get("jsbz"));

        return "index1";
    }
}


