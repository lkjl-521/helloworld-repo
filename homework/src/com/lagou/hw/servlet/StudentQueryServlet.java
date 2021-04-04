package com.lagou.hw.servlet;

import com.lagou.hw.bean.Student;
import com.lagou.hw.service.StudentService;
import com.lagou.hw.service.StudentServiceImp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author lkjl_java
 * @Description:
 * @date 2021/2/23 - 10:59
 */
@WebServlet(name = "StudentQueryServlet", urlPatterns = "/query")
public class StudentQueryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        // 1. 获取属性值
        String id = request.getParameter("studentID");
        String name = request.getParameter("studentName");
        String message = "";
        List<Student> list = null;
        if (id == null && name == null) {
            message = "查询异常！";
        } else {
            StudentService ss = new StudentServiceImp();
            if (id != null) {
                list = ss.studentQueryService("id=" + id);
                message = (list.size() > 0)?"查询成功！":"查询失败！没有找到学号为" + id + "的学生信息！";
            } else {
                list = ss.studentQueryService("name=" + name);
                message = (list.size() > 0)?"查询成功！":"查询失败！没有找到姓名包含" + name + "的学生信息！";
            }
        }
        if (list == null) {
            message = "查询异常！";
        } else {
            request.setAttribute("list", list);
            request.setAttribute("totalPage", 1);
        }
        request.setAttribute("message", message);

        // 3. 跳转页面
        RequestDispatcher dispatcher;
        if ("查询成功！".equals(message)) { // 查询成功，则显示查询到的信息
           dispatcher = request.getRequestDispatcher("main.jsp");
        } else { // 查询失败或异常则重新加载主页面
            dispatcher = request.getRequestDispatcher("/show");
        }
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
