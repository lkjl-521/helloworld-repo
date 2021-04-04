package com.lagou.hw.util;

import java.sql.*;

/**
 * @author lkjl_java
 * @Description:
 * @date 2021/2/10 - 11:56
 */
public class DbUtil {

    private static String jdbcName; // 用于描述驱动信息
    private static String dbUrl; // 用于描述URL信息
    private static String dbUserName; // 用于描述用户名信息
    private static String dbPassword;  // 用于描述密码信息

    // 进行静态成员的初始化
    static {
        jdbcName = "com.mysql.jdbc.Driver";
        dbUrl = "jdbc:mysql://localhost:3306/db_web?useUnicode=true&characterEncoding=utf-8";
        dbUserName = "root";
        dbPassword = "root123456";
        try {
            Class.forName(jdbcName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
        return con;
    }

    /**
     * 关闭连接
     * @param con
     * @throws SQLException
     */
    public static void closeConnection(Connection con) throws SQLException {
        if (null != con) {
            con.close();
        }
    }
    public static void closeResource(Connection con, PreparedStatement preparedStatement, ResultSet resultSet) throws SQLException {
        if (null != con) {
            con.close();
        }
        if (null != preparedStatement) {
            preparedStatement.close();
        }
        if (null != resultSet) {
            resultSet.close();
        }
    }
}
