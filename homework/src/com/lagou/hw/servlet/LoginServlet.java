package com.lagou.hw.servlet;

import com.lagou.hw.bean.User;
import com.lagou.hw.service.UserService;
import com.lagou.hw.service.UserServiceImp;

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
 * @date 2021/2/19 - 18:11
 */
@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 获取页面中的账户名和密码
        request.setCharacterEncoding("utf-8");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        System.out.println("获取到到的用户：" + userName + "  密码：" + password);
        // 2. 创造userService类型的对象去实现数据校验功能
        UserService userService = new UserServiceImp();
        User user = userService.userLoginService(new User(userName, password));
        if (null == user) {
            System.out.println("登录失败，用户名或者密码错误！");
            request.setAttribute("error", "登录失败，用户名或者密码错误！");
            // 返回输入页面， 转发
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(request, response);
        } else {
            System.out.println("登录成功！欢迎使用！");
            // 实现页面跳转，重定向学生信息展示界面
            response.sendRedirect("/homework/show");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
