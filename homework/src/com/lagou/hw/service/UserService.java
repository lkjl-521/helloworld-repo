package com.lagou.hw.service;

import com.lagou.hw.bean.User;

/**
 * @author lkjl_java
 * @Description: service层 描述用户登录操作的接口
 * @date 2021/2/20 - 11:56
 */
public interface UserService {
    public abstract User userLoginService(User user);
}
