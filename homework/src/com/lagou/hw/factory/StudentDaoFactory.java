package com.lagou.hw.factory;

import com.lagou.hw.bean.Student;
import com.lagou.hw.dao.StudentDao;
import com.lagou.hw.dao.StudentDaoImp;

import java.util.List;

/**
 * @author lkjl_java
 * @Description:
 * @date 2021/2/21 - 17:37
 */
public class StudentDaoFactory {

    public static StudentDao getStudentDao() {
        return new StudentDaoImp();
    }
}
