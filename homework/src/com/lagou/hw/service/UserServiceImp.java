package com.lagou.hw.service;

import com.lagou.hw.bean.User;
import com.lagou.hw.dao.UserDao;
import com.lagou.hw.factory.UserDaoFactory;

/**
 * @author lkjl_java
 * @Description:
 * @date 2021/2/20 - 13:31
 */
public class UserServiceImp implements UserService {
    private UserDao userDao;

    public UserServiceImp() {
        this.userDao = UserDaoFactory.getUserDao();
    }

    @Override
    public User userLoginService(User user) {
        return userDao.userLogin(user);
    }
}
