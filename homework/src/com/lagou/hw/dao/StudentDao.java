package com.lagou.hw.dao;

import com.lagou.hw.bean.Page;
import com.lagou.hw.bean.Student;

import java.util.List;

/**
 * @author lkjl_java
 * @Description: 学生信息连接数据库操作的接口
 * @date 2021/2/21 - 17:30
 */
public interface StudentDao {
    abstract List<Student> studentShow(Page page);
    int studentUpdate(Student student, String type);
    int studentDelete(String IDs);
    List<Student> studentQuery(String idName);
}
