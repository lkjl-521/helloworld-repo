function load() {
    let message = $("#message").text();
    // 若页面的属性message获取不到，则会返回null,因此#message标签中的内容为字符串null
    if (message !== "null") {
        alert(message);
    }
}

// 声明一个变量
var funcType;

$("#flush").on("click", function () {
    location.href="/homework/show";
});
$("#add").on("click",function() {
    init();
    funcType = "addStudent";
});
$("#set").on("click",function() {
    init();
    funcType = "setStudent";
    // 声明变量表示选中修改的元素，如果选中多个，取第一个
    let input = $(orderUsers());
    if (input.length === 0) {
        // // 为了避免没有修改选项时模态框的属性被打开
        // $(this).attr("data-toggle","none").attr("data-target","none");
        alert("请选择要修改的用户！");
    } else if (input.length > 1) {
        alert("当前仅支持修改一项！");
    } else {
        // 添加属性，打开模态框进行修改
        $(this).attr("data-toggle","modal").attr("data-target","#myModal");
        // 设置后修改学生信息时学号为只读，不能修改，但是可以传输
        $("#num").val($(input).parent().next().text()).attr("readonly","readonly");
    }
});
$("#delete").on("click", function () {
    let inputs = orderUsers(); // 获取所有name=order且被选中的input标签
    if (inputs.length === 0) {
        alert("请选择要删除的用户！");
    } else {
        if(confirm(`确认删除选中的${inputs.length}条数据吗？`)) {
            let arr = [];
            for(let i = 0;i<inputs.length;i++) {
                arr.push($(inputs[i]).parent().next().text());
            }
            $("#note").val(arr.join("-"));
            $("#updateForm").attr("action","delete").get(0).submit();
        }
    }
});
$("#query").on("click",function () {
    let id = $("#byNum").val().replace(/\s*/g, "");
    let name = $("#byName").val().replace(/\s*/g, "");
    if (id !== "") {
        location.href = "query?studentID=" + id;
    } else if (name !== "") {
        location.href = "query?studentName=" + name;
    } else {
        alert("请输入学号或姓名查询！")
    }
});


// 定义方法返回被选中的用户数据
function orderUsers() {
    return $("[name='order']:checked");
}

// 提交表单前判断操作类型 : add or set
function check() {
    if (notNull()) {
        let path = ""; // 表单提交的地址
        switch (funcType) {
            case "addStudent":
                path = "update?funcType=add";
                break;
            case "setStudent":
                path = "update?funcType=set";
                break;
            default:
                return false;
        }
        $("#updateForm").attr("action", path);
        return true;
    } else {
        return false;
    }
}
// 还原样式 避免发生错误
function init() {
    $("#num").attr("readonly",false).val("");
    $("#set").attr("data-toggle","none").attr("data-target","none");
    $("#name").val("");
    $("#birthday").val("");
    $("#email").val("");
    $("#note").val("");
}
// 表格信息非空判断
function notNull() {
    if ($("#num").val() === "") {
        alert("请输入学号！");
        return false;
    } else if ($("#name").val() === "") {
        alert("请输入姓名！");
        return false;
    } else if ($("#birthday").val() === "") {
        alert("请选择出生日期！");
        return false;
    } else if ($("#email").val() === "") {
        alert("请输入邮箱！");
        return false;
    }else if ($("#note").val() === "") {
        alert("请输入备注！");
        return false;
    }
    return true;
}

















// 用户信息非空和合理性判断
function userForm() {
    var num = document.getElementById("num").value;
    var trs = $("tbody>tr");
    var name = document.getElementById("name").value;
    var sex = document.getElementsByName("sex");
    var password = document.getElementById("password").value;
    var date = document.getElementById("date").value;
    // 合理性检测：工号不能重复
    if(num != "") {
        if (crudType == "add") {
            for(var i = 0;i<trs.length;i++) {
                if ($(trs[i]).children("td:eq(1)").text() == num) {
                    alert("工号冲突！");
                    return false;
                }
            }
        } else if(crudType =="set"){
            var inputs = orderUsers();
            for(var i = 0;i<trs.length;i++) {
                if ($(trs[i]).children("td:eq(1)").text() == num && num != $(inputs[0]).parent().next().text()) {
                    alert("工号冲突！");
                    return false;
                }
            }
        }
    }

    // 输入非空检测，日期格式及合理性检测
    if (num == "") {
        alert("请输入工号！");
        return false;
    }else if(name == "") {
        alert("请输入姓名！");
        return false;
    } else if(!sex[0].checked & !sex[1].checked) {
        alert("请选择性别！");
        return false;
    } else if(password == "") {
        alert("请设置密码！");
        return false;
    } else if(date == "") {
        alert("请输入出生日期！");
        return false;
    } else if (getAge(date) == null) {
        alert("日期格式错误！");
        return false;
    } else if (getAge(date) == -1) {
        alert("出生日期晚于当前时间，请重新输入！");
        return false;
    }
    return true;
}

// 根据日期计算年龄 :
//实参日期格式必须是 year-month-day
//@return null：输入的日期格式错误
//@return -1：出生日期晚于今天，数据有误
function getAge(strAge) {
    var birArr = strAge.split("-");
    var birYear = birArr[0];
    var birMonth = birArr[1];
    var birDay = birArr[2];

    var date = new Date();
    var nowYear = date.getFullYear();
    var nowMonth = date.getMonth() + 1; //记得加1
    var nowDay = date.getDate();
    var returnAge; // 返回年龄值

    if (birArr == null) {
        return false
    }
    ;
    var d = new Date(birYear, birMonth - 1, birDay);
    if (d.getFullYear() == birYear && (d.getMonth() + 1) == birMonth && d.getDate() == birDay) {
        if (nowYear == birYear && nowMonth >= birMonth && nowDay >= birDay) {
            returnAge = 0; //
        } else {
            var ageDiff = nowYear - birYear; //
            if (ageDiff > 0) {
                if (nowMonth == birMonth) {
                    var dayDiff = nowDay - birDay; //
                    if (dayDiff < 0) {
                        returnAge = ageDiff - 1;
                    } else {
                        returnAge = ageDiff;
                    }
                } else {
                    var monthDiff = nowMonth - birMonth; //
                    if (monthDiff < 0) {
                        returnAge = ageDiff - 1;
                    } else {
                        returnAge = ageDiff;
                    }
                }
            } else if (ageDiff == 0) {
                if (nowMonth == birMonth) {
                    var dayDiff = nowDay - birDay;
                    if (dayDiff < 0) {
                        returnAge = -1;
                    } else {
                        returnAge = ageDiff;
                    }
                } else {
                    var monthDiff = nowMonth - birMonth;
                    if (monthDiff < 0) {
                        returnAge = -1;
                    } else {
                        returnAge = ageDiff;
                    }
                }
            } else {
                return -1;//出生日期晚于今天，数据有误
            }
        }
        return returnAge;
    } else {
        return null; // 输入的日期格式错误
    }
}