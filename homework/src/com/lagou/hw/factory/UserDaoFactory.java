package com.lagou.hw.factory;

import com.lagou.hw.dao.UserDao;
import com.lagou.hw.dao.UserDaoImp;

/**
 * @author lkjl_java
 * @Description:
 * @date 2021/2/20 - 13:48
 */
public class UserDaoFactory {

    public static UserDao getUserDao() {
        return new UserDaoImp();
    }

}
