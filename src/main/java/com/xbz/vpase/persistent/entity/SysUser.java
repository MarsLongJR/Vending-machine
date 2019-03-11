package com.xbz.vpase.persistent.entity;

import com.xbz.vpase.authorities.UserInfo;

import java.io.Serializable;
import java.util.Date;

public class SysUser implements Serializable {
    private Integer id;

    private String account;

    private String password;

    private String userName;

    private String userCode;

    private String phone;

    private String email;

    private String openId;

    private Short status;

    private Date createTime;

    private String createId;

    private Boolean firstLogin;

    private Integer modifierId;

    private Date modifyTime;

    private String imageUrl;

    private Date lastTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    public Boolean getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(Boolean firstLogin) {
        this.firstLogin = firstLogin;
    }

    public Integer getModifierId() {
        return modifierId;
    }

    public void setModifierId(Integer modifierId) {
        this.modifierId = modifierId;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public SysUser(Integer id, String account, String password, String userName, String userCode, String phone, String email, String openId, Short status, Date createTime, String createId, Boolean firstLogin, Integer modifierId, Date modifyTime, String imageUrl, Date lastTime) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.userName = userName;
        this.userCode = userCode;
        this.phone = phone;
        this.email = email;
        this.openId = openId;
        this.status = status;
        this.createTime = createTime;
        this.createId = createId;
        this.firstLogin = firstLogin;
        this.modifierId = modifierId;
        this.modifyTime = modifyTime;
        this.imageUrl = imageUrl;
        this.lastTime = lastTime;
    }

    public SysUser(){

    }
}