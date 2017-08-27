package org.lanqiao.myBatis.entity;

public class UserProfile {

    private Integer id;


    private String nickName;


    private String realName;


    private Boolean gender;


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getNickName() {
        return nickName;
    }


    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }


    public String getRealName() {
        return realName;
    }


    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }


    public Boolean getGender() {
        return gender;
    }


    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
            "id=" + id +
            ", nickName='" + nickName + '\'' +
            ", realName='" + realName + '\'' +
            ", gender=" + gender +
            '}';
    }
}