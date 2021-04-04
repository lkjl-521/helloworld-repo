package com.lagou.hw.servlet;

import com.lagou.hw.bean.Student;
import com.lagou.hw.bean.User;
import com.lagou.hw.service.StudentService;
import com.lagou.hw.service.StudentServiceImp;
import com.lagou.hw.service.UserService;
import com.lagou.hw.service.UserServiceImp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * @author lkjl_java
 * @Description:
 * @date 2021/2/22 - 11:36
 */
@WebServlet(name = "StudentAddServlet", urlPatterns = "/update")
public class StudentUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        // 1. 获取提交表单的参数名值对
        Map<String, String[]> map = request.getParameterMap();
        System.out.println("获取到了update表单！");
        Set<Map.Entry<String, String[]>> entries = map.entrySet();
        Student student = new Student();
        // 遍历
        for (Map.Entry<String, String[]> me : entries) {
            /*// 打印
            System.out.print(me.getKey() + "对应的数值有：");
            for(String ts : me.getValue()) {
                System.out.print(ts + " ");
            }
            System.out.println();*/
            // student对象的成员变量赋值
            switch (me.getKey()) {
                case "studentID":student.setStudentID(me.getValue()[0]); break;
                case "studentName":student.setStudentName(me.getValue()[0]); break;
                case "sex":student.setSex(me.getValue()[0]); break;
                case "birthday":student.setBirthday(me.getValue()[0]); break;
                case "email":student.setEmail(me.getValue()[0]); break;
                case "note":student.setNote(me.getValue()[0]); break;
            }
        }

        // 2. 创造StudentService类型的对象去实现数据校验功能
        String type = request.getParameter("funcType");
        StudentService ss = new StudentServiceImp();
        int i = ss.studentUpdateService(student, type);
        if (1 == i) {
            System.out.println("学生信息更新成功！");
            request.setAttribute("message", ("add".equals(type))?"学生信息添加成功！":"学生信息修改成功！");
        } else {
            System.out.println("学生信息更新失败！");
            request.setAttribute("message",("add".equals(type))?"学生信息添加失败，请检查学号是否冲突！":"学生信息修改失败！");
        }
        // 3. 跳转到show Servlet刷新页面数据
        RequestDispatcher dispatcher = request.getRequestDispatcher("/show");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
