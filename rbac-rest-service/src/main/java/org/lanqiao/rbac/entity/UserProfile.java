package org.lanqiao.rbac.entity;


import com.alibaba.fastjson.annotation.JSONField;
import org.web2017.fastjson.serializer.GenderSerializer;
import tk.mybatis.mapper.entity.IDynamicTableName;

import javax.persistence.*;

@Table(name = "rbac_user_profile")
public class UserProfile /*implements IDynamicTableName*/{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "real_name")
    private String realName;
    @JSONField(serializeUsing = GenderSerializer.class)
    private Boolean gender;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return nick_name
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * @param nickName
     */
    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    /**
     * @return real_name
     */
    public String getRealName() {
        return realName;
    }

    /**
     * @param realName
     */
    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    /**
     * @return gender
     */
    public Boolean getGender() {
        return gender;
    }

    /**
     * @param gender
     */
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

    // @Override
    // public String getDynamicTableName() {
    //     return "rbac_user_profile"+id%100;
    // }
}