package org.lanqiao.rbac.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "rbac_menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 文字标题
     */
    private String title;

    /**
     * 父级菜单
     */
    private Integer pid;

    /**
     * 对应的后台url
     */
    private String url;

    /**
     * 状态
     */
    private Boolean state;

    /**
     * 是否为按钮资源
     */
    @Column(name = "is_button")
    private Boolean isButton;

    private String description;

    @Column(name = "gmt_modified")
    private Date gmtModified;

    @Column(name = "gmt_create")
    private Date gmtCreate;

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
     * 获取文字标题
     *
     * @return title - 文字标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置文字标题
     *
     * @param title 文字标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 获取父级菜单
     *
     * @return pid - 父级菜单
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 设置父级菜单
     *
     * @param pid 父级菜单
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 获取对应的后台url
     *
     * @return url - 对应的后台url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置对应的后台url
     *
     * @param url 对应的后台url
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 获取状态
     *
     * @return state - 状态
     */
    public Boolean getState() {
        return state;
    }

    /**
     * 设置状态
     *
     * @param state 状态
     */
    public void setState(Boolean state) {
        this.state = state;
    }

    /**
     * 获取是否为按钮资源
     *
     * @return is_button - 是否为按钮资源
     */
    public Boolean getIsButton() {
        return isButton;
    }

    /**
     * 设置是否为按钮资源
     *
     * @param isButton 是否为按钮资源
     */
    public void setIsButton(Boolean isButton) {
        this.isButton = isButton;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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
}