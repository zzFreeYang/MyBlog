package com.springboot.demo.service;

import com.springboot.demo.entity.SysLog;
import com.springboot.demo.entity.SysView;

import java.util.List;

/**
 * 日志/访问统计等系统相关Service
 */
public interface SysService {

    void addLog(SysLog sysLog);

    void addView(SysView sysView);

    int getLogCount();

    int getViewCount();

    List<SysLog> listAllLog();

    List<SysView> listAllView();



}
