package com.lagou.hw.dao;

import com.lagou.hw.bean.User;
import com.lagou.hw.util.DbUtil;
import com.lagou.hw.util.DruidUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author lkjl_java
 * @Description:
 * @date 2021/2/20 - 13:36
 */
public class UserDaoImp implements UserDao {
    @Override
    public User userLogin(User user) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            // 1. 获取数据库连接
            connection = DruidUtils.getConnection();
            // 2. 准备sql语句
            String sql = "select * from t_user where userName=? and password=?";
            // 3. 执行sql语句并获取结果
            ps = connection.prepareStatement(sql);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            resultSet = ps.executeQuery();
            // 4. 处理结果集
            while (resultSet.next()) {
                User tu = new User(resultSet.getString("userName"), resultSet.getString("password"));
                return tu;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 5. 释放资源
            DruidUtils.close(connection, ps, resultSet);
        }

       /* Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            // 1. 获取数据库连接
            connection = DbUtil.getConnection();
            // 2. 准备SQL语句
            String sql = "select * from t_user where userName = ? and password = ?";
            // 3. 执行sql语句获取结果并返回
            ps = connection.prepareStatement(sql);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                User tu = new User(resultSet.getString("userName"), resultSet.getString("password"));
                return tu;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 4. 释放相关的资源
            try {
                DbUtil.closeResource(connection, ps, resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }*/
        return null;
    }
}
