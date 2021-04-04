package com.lagou.hw.service;

import com.lagou.hw.bean.Page;
import com.lagou.hw.bean.Student;

import java.util.List;

/**
 * @author lkjl_java
 * @Description: 描述学生信息表增删改查操作的接口
 * @date 2021/2/21 - 17:08
 */
public interface StudentService {
    // 显示学生信息的功能
    List<Student> studentShowService(Page page);
    // 添加学生信息的操功能
    int studentUpdateService(Student student, String type);
    int studentDeleteService(String IDs);
    List<Student> studentQueryService(String idName);
}
