<%--
  Created by IntelliJ IDEA.
  User: ThinkPad
  Date: 2021/2/19
  Time: 13:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>登录页面</title>
  </head>
  <style>
    body {
      background: rgba(255, 255, 0, 0.514);
      margin:10% 0;
    }
    #loginPage{
      background:white;
      width:200px;
      padding:50px;
      border:2px solid white;
      border-radius: 15%;
      box-shadow: 10px 0px 10px 10px gray;
      text-align: center;
      margin:auto;
    }

    h3 {
      font-family: 幼圆;
      margin-bottom: 30px;
    }

    #inf {
      color:red;
      margin-bottom: 5px;
    }

    #loginPage input{
      display: block;
      width:200px;
      height:30px;
      margin:10px auto;
      box-sizing: border-box;
    }

    #sub{
      background:rgb(78, 127, 207);
      color:#fff;
      border-radius: 50px;
      cursor: pointer;
      border: 0px;
      outline: none;
    }
    #sub:hover {
      background:  rgb(67, 132, 236);
    }
  </style>
  <body>
    <div id="loginPage">
      <h3>学生信息管理平台</h3>
      <h6 id="inf"><%= request.getAttribute("error")==null?"":request.getAttribute("error")%></h6>
      <form id="login" action="login" method="post" onsubmit="return check()">
        <input id="userName" type="text" name="userName" placeholder="用户名" onkeyup="value=value.replace(/ |\d*/g,'')">
        <input id="password" type="password" name="password" placeholder="密码" onkeyup="value=value.replace(/[^\w\d]/g,'')">
        <input id="sub" type="submit" value="登录">
      </form>
    </div>

  <script>
    function check() {
      if (document.getElementById("userName").value === "") {
        document.getElementById("inf").innerHTML = "请输入用户名";
        return false;
      }
      if (document.getElementById("password").value === "") {
        document.getElementById("inf").innerHTML = "请输入密码";
        return false;
      }
      return true;
    }
  </script>
  </body>
</html>
