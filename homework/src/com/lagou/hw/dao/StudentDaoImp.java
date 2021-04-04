package com.lagou.hw.dao;

import com.lagou.hw.bean.Page;
import com.lagou.hw.bean.Student;
import com.lagou.hw.util.DruidUtils;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author lkjl_java
 * @Description: 实现数据库和学生信息表的连接
 * @date 2021/2/21 - 17:36
 */
public class StudentDaoImp implements StudentDao {
    private static final int limit = 5;  // 描述每页的显示对象个数

    /**
     * 获取当前前端页面显示的数据
     * @param page
     * @return
     */
    @Override
    public List<Student> studentShow(Page page) {
        int count = 0; // 学生信息总条数
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String sql;
        try {
            // 1. 获取数据库连接
            connection = DruidUtils.getConnection();

            // 2. 准备sql语句 查询学生信息总数
            sql = "select count(*) from t_student";
            // 3. 执行语句获取结果集
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            // 4. 处理查询结果，获取总页码的值
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
            // Math.ceil()中接收的参数是double类型， 为了避免丢失精度，除数limit*1.0
            page.setTotalPage((int)Math.ceil(count /(limit*1.0)));

            // 根据页码值返回需要显示的页面数据
            if(page.getNowPage() < 1) {  // 页面首次加载时页码为1
                page.setNowPage(1);
            } else if (page.getNowPage() > page.getTotalPage()) { // 获取的页码超出总页码时，设置当前页码为最后一页
                page.setNowPage(page.getTotalPage());
            }
            // 通过limit关键字查询
            // Select * from 表名 limit startRow,pageSize =>startRow从0开始， pageSize是每页显示的记录条数
            sql = "select * from t_student limit " + ((page.getNowPage()-1)*limit) + "," + limit + ";";
            resultSet = statement.executeQuery(sql);
            return getStudentList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // 5. 释放资源
            DruidUtils.close(connection,statement,resultSet);
        }
    }

    /**
     * 实现学生信息的增加和修改功能
     * @param student
     * @param type
     * @return
     */
    @Override
    public int studentUpdate(Student student, String type) {
        Connection connection = null;
        PreparedStatement ps = null;
        String sql;
        try {
            // 1. 获取数据库连接
            connection = DruidUtils.getConnection();
            // 2. 准备sql语句
            if ("add".equals(type)) {
                sql = "insert into t_student (studentName,sex,birthday,email,note,studentID) values(?,?,?,?,?,?)";
            } else if ("set".equals(type)) {
                sql = "update t_student set studentName=?, sex=?, birthday=?, email=?, note=? where studentID=?";
            } else {
                return 0;
            }
            // 3. 执行sql语句，返回插入数据结果
            ps = connection.prepareStatement(sql);
            ps.setString(1, student.getStudentName());
            ps.setString(2, student.getSex());
            ps.setString(3, student.getBirthday());
            ps.setString(4, student.getEmail());
            ps.setString(5, student.getNote());
            ps.setString(6, student.getStudentID());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            // 4. 释放资源
            DruidUtils.close(connection, ps);
        }
    }

    /**
     * 实现学生信息删除的方法
     * @param IDs
     * @return
     */
    @Override
    public int studentDelete(String IDs) {
        Connection connection = null;
        PreparedStatement ps = null;
        String sql;
        int res = 0; // 记录删除的数据数量
        String[] split = IDs.split("-");
        if (split.length > 5) { return 0; }

        try {
            // 1. 获取数据库连接
            connection = DruidUtils.getConnection();
            // 2. 准备sql语句
            sql = "delete from t_student where studentID=?";

            // 3. 执行sql语句，返回插入数据结果
            ps = connection.prepareStatement(sql);
            for (String id : split) {
                ps.setString(1,id);
                res += ps.executeUpdate();
            }
            return res;

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            // 4. 释放资源
            DruidUtils.close(connection, ps);
        }
    }

    /**
     * 实现学生信息的查询
     * @param idName
     * @return
     */
    @Override
    public List<Student> studentQuery(String idName) {
        String sql;
        String[] split = idName.split("=");
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            // 1. 获取数据库连接
            connection = DruidUtils.getConnection();
            // 2. 判断查询方式 准备sql语句
            if ("id".equals(split[0])) {
                sql = "select * from t_student where studentID=?";
                ps = connection.prepareStatement(sql);
                ps.setString(1, split[1]);
            } else {
                sql = "select * from t_student where studentName like ?";
                ps = connection.prepareStatement(sql);
                ps.setString(1, "%" + split[1] + "%");
            }
            // 3. 执行语句获取结果集
            resultSet = ps.executeQuery();
            // 4. 处理结果集对象
            return getStudentList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // 5. 释放资源
            DruidUtils.close(connection,ps,resultSet);
        }
    }

    /**
     * 将数据库查询到的每行结果封装为Student对象，将所有对象放入集合中并返回
     * @param resultSet
     * @return
     * @throws SQLException
     */
    private List<Student> getStudentList(ResultSet resultSet) throws SQLException {
        List<Student> list = new LinkedList<>();
        while (resultSet.next()) {
            Student st = new Student();
            st.setStudentID(resultSet.getString("studentID"));
            st.setStudentName(resultSet.getString("studentName"));
            st.setSex(resultSet.getString("sex"));
            st.setBirthday(resultSet.getString("birthday"));
            st.setEmail(resultSet.getString("email"));
            st.setNote(resultSet.getString("note"));
            list.add(st);
        }
        return list;
    }
}