package com.lagou.hw.servlet;

import com.lagou.hw.service.StudentService;
import com.lagou.hw.service.StudentServiceImp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lkjl_java
 * @Description:
 * @date 2021/2/23 - 9:49
 */
@WebServlet(name = "StudentDeleteServlet", urlPatterns = "/delete")
public class StudentDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        // 1. 获取提交表单的学号组合字符串
        String IDs = request.getParameter("note");
        if (IDs != null) {
            StudentService ss = new StudentServiceImp();
            int res = ss.studentDeleteService(IDs);
            System.out.println("共删除了" + res + "条数据！");
            if (0 == res) {
                IDs = "学生信息删除失败！";
            } else {
                IDs = "成功删除" + res + "条记录!";
            }
        } else {
            IDs = "操作错误！";
        }
        request.setAttribute("message",IDs);
        // 3. 跳转到show Servlet进行数据刷新
        RequestDispatcher dispatcher = request.getRequestDispatcher("/show");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
