package org.lanqiao.rbac.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "rbac_account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户信息id
     */
    @Column(name = "user_profile_id")
    private Integer userProfileId;

    /**
     * 账户标识
     */
    private String account;

    private String password;

    /**
     * 账户类型：邮箱，手机，微信，qq
     */
    private Short type;

    @Column(name = "login_time")
    private Date loginTime;

    @Column(name = "last_login_time")
    private Date lastLoginTime;

    @Column(name = "gmt_create")
    private Date gmtCreate;

    @Column(name = "gmt_modified")
    private Date gmtModified;
    @Column(name="token")
    private String token;

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
     * 获取用户信息id
     *
     * @return user_profile_id - 用户信息id
     */
    public Integer getUserProfileId() {
        return userProfileId;
    }

    /**
     * 设置用户信息id
     *
     * @param userProfileId 用户信息id
     */
    public void setUserProfileId(Integer userProfileId) {
        this.userProfileId = userProfileId;
    }

    /**
     * 获取账户标识
     *
     * @return account - 账户标识
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置账户标识
     *
     * @param account 账户标识
     */
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取账户类型：邮箱，手机，微信，qq
     *
     * @return type - 账户类型：邮箱，手机，微信，qq
     */
    public Short getType() {
        return type;
    }

    /**
     * 设置账户类型：邮箱，手机，微信，qq
     *
     * @param type 账户类型：邮箱，手机，微信，qq
     */
    public void setType(Short type) {
        this.type = type;
    }

    /**
     * @return login_time
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * @param loginTime
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * @return last_login_time
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * @param lastLoginTime
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * @return gmt_create
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * @param gmtCreate
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * @return gmt_modified
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * @param gmtModified
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

  public String getToken() {
    return this.token;
  }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Account{" +
            "id=" + id +
            ", userProfileId=" + userProfileId +
            ", account='" + account + '\'' +
            ", password='" + password + '\'' +
            ", type=" + type +
            ", loginTime=" + loginTime +
            ", lastLoginTime=" + lastLoginTime +
            ", gmtCreate=" + gmtCreate +
            ", gmtModified=" + gmtModified +
            ", token='" + token + '\'' +
            '}';
    }
}