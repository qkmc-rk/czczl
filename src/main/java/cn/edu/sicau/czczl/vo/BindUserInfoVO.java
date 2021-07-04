package cn.edu.sicau.czczl.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author qkmc
 */
@ApiModel
public class BindUserInfoVO {
    @ApiModelProperty("学校")
    private String university;
    @ApiModelProperty("学院")
    private String college;
    @ApiModelProperty("专业")
    private String major;
    @ApiModelProperty("年级")
    private String grade;
    @ApiModelProperty("班级")
    private String clazz;
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("身份证号码")
    private String idcard;
    @ApiModelProperty("学号")
    private String stuno;

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getStuno() {
        return stuno;
    }

    public void setStuno(String stuno) {
        this.stuno = stuno;
    }

    @Override
    public String toString() {
        return "BindUserInfo{" +
                "university='" + university + '\'' +
                ", college='" + college + '\'' +
                ", major='" + major + '\'' +
                ", grade='" + grade + '\'' +
                ", clazz='" + clazz + '\'' +
                ", name='" + name + '\'' +
                ", idcard='" + idcard + '\'' +
                ", stuno='" + stuno + '\'' +
                '}';
    }


}
