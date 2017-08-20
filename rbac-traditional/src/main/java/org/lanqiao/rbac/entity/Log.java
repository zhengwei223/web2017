package org.lanqiao.rbac.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "rbac_log")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    /**
     * 操作类型
     */
    @Column(name = "opt_type")
    private Byte optType;

    /**
     * 记录内容
     */
    private String content;

    @Column(name = "gmt_created")
    private Date gmtCreated;

    @Column(name = "gmt_modified")
    private Date gmtModified;

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
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取操作类型
     *
     * @return opt_type - 操作类型
     */
    public Byte getOptType() {
        return optType;
    }

    /**
     * 设置操作类型
     *
     * @param optType 操作类型
     */
    public void setOptType(Byte optType) {
        this.optType = optType;
    }

    /**
     * 获取记录内容
     *
     * @return content - 记录内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置记录内容
     *
     * @param content 记录内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * @return gmt_created
     */
    public Date getGmtCreated() {
        return gmtCreated;
    }

    /**
     * @param gmtCreated
     */
    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
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
}