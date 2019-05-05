package com.example.networkdemo2;

import java.io.Serializable;

public class Student implements Serializable {

    private String stuName;
    private String stuNo;
    private String stuGender;
    private String imagePath;
    private String stuClass;
    public void setStuName(String stuName) {
        this.stuName = stuName;
    }
    public String getStuName() {
        return stuName;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }
    public String getStuNo() {
        return stuNo;
    }

    public void setStuGender(String stuGender) {
        this.stuGender = stuGender;
    }
    public String getStuGender() {
        return stuGender;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    public String getImagePath() {
        return imagePath;
    }

    public void setStuClass(String stuClass) {
        this.stuClass = stuClass;
    }
    public String getStuClass() {
        return stuClass;
    }

}