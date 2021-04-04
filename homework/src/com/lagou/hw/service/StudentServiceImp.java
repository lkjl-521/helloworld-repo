package com.lagou.hw.service;

import com.lagou.hw.bean.Page;
import com.lagou.hw.bean.Student;
import com.lagou.hw.dao.StudentDao;
import com.lagou.hw.factory.StudentDaoFactory;

import java.util.List;

/**
 * @author lkjl_java
 * @Description:
 * @date 2021/2/21 - 17:29
 */
public class StudentServiceImp implements StudentService {
    private StudentDao studentDao;

    public StudentServiceImp() {
        this.studentDao = StudentDaoFactory.getStudentDao();
    }

    @Override
    public List<Student> studentShowService(Page page) {
        return studentDao.studentShow(page);
    }

    @Override
    public int studentUpdateService(Student student, String type) {
        return studentDao.studentUpdate(student, type);
    }

    @Override
    public int studentDeleteService(String IDs) {
        return studentDao.studentDelete(IDs);
    }

    @Override
    public List<Student> studentQueryService(String idName) {
        return studentDao.studentQuery(idName);
    }
}
