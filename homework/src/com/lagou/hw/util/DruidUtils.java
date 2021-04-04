package com.lagou.hw.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author lkjl_java
 * @Description:
 * @date 2021/2/20 - 15:45
 */
public class DruidUtils {

    // 1. 定义成员变量
    public static DataSource dataSource;

    // 2. 静态代码块
    static {
        try {
            // 创建属性集对象
            Properties p = new Properties();

            // Druid 连接池不能够主动加载配置文件 需要手动指定文件
            InputStream resourceAsStream = DruidUtils.class.getClassLoader().getResourceAsStream("druid.properties");

            // Properties对象的load方法从字节流中读取配置信息
            p.load(resourceAsStream);

            // 通过工厂类获取连接池对象
            dataSource = DruidDataSourceFactory.createDataSource(p);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 3. 获取连接池方法
    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 4. 释放资源
    public static void close(Connection con, PreparedStatement ps) {
        if (null != con && null != ps) {
            try {
                ps.close();
                // 归还连接
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    // 重载
    public static void close(Connection con, Statement statement, ResultSet resultSet) {
        if (null != con && null != statement && null != resultSet) {
            try {
                resultSet.close();
                statement.close();
                // 归还连接
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
