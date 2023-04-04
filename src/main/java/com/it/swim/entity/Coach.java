package com.it.swim.entity;


/*
 * @description: 教练实体类
 */
public class Coach {
    //教练ID
    private Long coachId;
    //密码
    private String password;
    //教练姓名
    private String coachName;
    //头像路径
    private String profileImg;
    //性别
    private String gender;
    //年龄
    private Integer age;
    //电话
    private String phone;
    //邮箱
    private String email;
    //备注
    private String remarks;

    public Long getCoachId() { return coachId; }

    public void setCoachId(Long coachId) {
        this.coachId = coachId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
