package com.lagou.hw.servlet;

import com.lagou.hw.bean.Page;
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
 * @Description: 加载或者刷新页面数据
 * @date 2021/2/21 - 16:45
 */
@WebServlet(name = "StudentShowServlet", urlPatterns = "/show")
public class StudentShowServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        // 获取page参数
        String str = request.getParameter("page");
        int nowPage = 0; // 声明一个变量描述当前页码，初始值为0
        if (null != str) {
             nowPage = Integer.parseInt(str);  // 将page参数值转为整型
        }
        System.out.println("当前获取的页码是：" + nowPage);

        // 声明一个Page类型的对象作为，总页数totalPage初始值为0；
        Page page = new Page(nowPage, 0);
        // 转入service层
        StudentService ss = new StudentServiceImp();
        // 获取当前页面的数据，类型为泛型为Student的list集合；
        List<Student> studentList = ss.studentShowService(page);
        if (null != studentList) {
            System.out.println("获取到了数据库中的学生信息表！");
            // 通过属性值list、totalPage将学生信息和页码发送给前端页面
            request.setAttribute("list", studentList);
            request.setAttribute("totalPage", page.getTotalPage());
            System.out.println("当前总页码：" + page.getTotalPage());
        } else {
            // Dao层执行发生异常，则返回null, 通过message属性将信息发给前端
            request.setAttribute("message", "页面加载异常！");
        }
        // 转发跳转至主界面
        RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
        dispatcher.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
