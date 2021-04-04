package com.lagou.hw.dao;

import com.lagou.hw.bean.User;

/**
 * @author lkjl_java
 * @Description: dao层的用户登录操作的接口
 * @date 2021/2/20 - 13:32
 */
public interface UserDao {
    abstract User userLogin(User user);

}
