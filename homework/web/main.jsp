<%@ page import="com.lagou.hw.bean.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.LinkedList" %>
<%--
  Created by IntelliJ IDEA.
  User: ThinkPad
  Date: 2021/2/20
  Time: 21:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生信息管理界面</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
</head>
<style>
    body {
        padding: 20px;
        background-color: honeydew;
    }

    #message {
        display: none;
    }

    #func {
        margin-bottom:10px;
    }
    #func h1 {
        font-family: 黑体;
        color: white;
        text-align: center;
        display: inline-block;
        margin-right: 20px;
        background-color:rgb(69, 216, 184);
        border-radius: 8px;
        box-shadow:2px 2px 0px 2px gainsboro;
    }
    /* 定义表格隔行变色和鼠标悬停样式 */
    tbody tr:nth-child(odd) {
        background-color: rgba(9, 212, 248, 0.178);
    }

    tbody tr:hover {
        background-color: aqua;
    }
    #page {
        text-align: center;
    }
</style>

<body onload="load()">
<h1 id="message"><%=request.getAttribute("message")%></h1>
<!--增删改查按钮-->
<div id="func">
    <h1>学生信息表</h1>
    <button id="flush" class="btn btn-primary">刷新</button>
    <button id="add" class="btn btn-primary" data-toggle="modal" data-target="#myModal">新增</button>
    <button id="delete" class="btn btn-primary">删除</button>
    <button id="set" class="btn btn-primary">编辑</button>
    <button id="query" class="btn btn-primary">查询</button>
    <input id="byNum" type="text" style="width:150px" placeholder="按学号查询">
    <input id="byName" type="text" style="width:150px" placeholder="按姓名查询">
</div>

<!--声明定义模态框组件：用于增加和修改操作-->
<div class="modal" id="myModal" data-backdrop="static">
    <!--窗口声明-->
    <div class="modal-dialog">
        <!--内容声明-->
        <div class="modal-content">
            <!--1.框的标题-->
            <div class="modal-header">
                <button class="close" data-dismiss="modal">
                    <span>&times;</span>
                </button>
                <h4>请输入学生信息：</h4>
            </div>
            <!--2.框的内容-->
            <form id="updateForm" onsubmit="return check()" method="post">
                <div class="modal-body">
                    <!--四位数字-->
                    <p>学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：
                        <input id="num" name="studentID" type="text" placeholder="请输入学号" onkeyup="value=value.replace(/[^\d]/g,'')">
                    </p>
                    <!--四位汉字或者字母，去除空格-->
                    <p>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：
                        <input id="name" name="studentName" type="text" placeholder="请输入姓名" onkeyup="value=value.replace(/ |\d*/g,'')">
                    </p>
                    <p>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：
                        <input type="radio" name="sex" checked="checked" value="男">男
                        <input type="radio" name="sex" value="女">女
                    </p>
                    <!--出生日期：属性type=date-->
                    <p>出生日期：<input id="birthday" name="birthday" type="date"></p>
                    <!--密码最长六位，只能用字母加数字-->
                    <p>邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：
                        <input id="email" name="email" type="email">
                    </p>
                    <p>备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：<input id="note" name="note" type="text"></p>
                </div>
                <!--3.框的按钮-->
                <div class="modal-footer">
                    <button id="confirm" class="btn btn-info">确定</button>
                    <button class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!--用户列表-->
<table id="userList" class="table">
    <thead>
    <tr>
        <th>序号</th>
        <th>学号</th>
        <th>姓名</th>
        <th>性别</th>
        <th>出生日期</th>
        <th>邮箱</th>
        <th>备注</th>
    </tr>
    </thead>
    <tbody>
        <%
            List<Student> list = (LinkedList<Student>)request.getAttribute("list");
            for (Student st : list) {

        %>
        <tr>
            <td><input type="checkbox" name="order"></td>
            <td><%= st.getStudentID()%></td>
            <td><%= st.getStudentName()%></td>
            <td><%= st.getSex()%></td>
            <td><%= st.getBirthday()%></td>
            <td><%= st.getEmail()%></td>
            <td><%= st.getNote()%></td>
        </tr>
        <%
            }
        %>
    </tbody>
</table>

<nav id="page" aria-label="Page navigation">
    <ul class="pagination">
        <li>
            <a href="show?page=<%= (request.getParameter("page") == null)?1:(Integer.parseInt(request.getParameter("page"))-1)%>" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
            </a>
        </li>
        <%
            int totalPage = (int) request.getAttribute("totalPage");
            for (int i = 1;i <= totalPage;i++) {
        %>
        <li>
            <a href="show?page=<%=i%>"><%= i %></a>
        </li>
        <%
            }
        %>
        <li>
            <a href="show?page=<%= (request.getParameter("page") == null)?2:(Integer.parseInt(request.getParameter("page"))+1)%>" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
            </a>
        </li>
    </ul>
</nav>

<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/mainPage.js" type="text/javascript"></script>
</body>
</html>
