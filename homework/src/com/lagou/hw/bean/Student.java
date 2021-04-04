package com.lagou.hw.bean;

/**
 * @author lkjl_java
 * @Description: 学员对象的Javabean封装类
 * @date 2021/2/19 - 13:13
 */
public class Student {
    private int id; // 对应数据库主键
    private String studentID; // 学号
    private String studentName; // 姓名
    private String sex; // 性别
    private String birthday; // 出生日期
    private String email; // 邮箱
    private String note; // 备注

    public Student() {
    }

    public Student(String studentID, String studentName, String sex, String birthday, String email, String note) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.sex = sex;
        this.birthday = birthday;
        this.email = email;
        this.note = note;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
