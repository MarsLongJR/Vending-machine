package com.xbz.vpase.persistent.entity;

import java.io.Serializable;
import java.util.Date;

public class XbzVip implements Serializable {
    private Integer id;

    private String account;

    private String password;

    private String vipName;

    private String vipCode;

    private String wxAddress;

    private String tel;

    private String email;

    private Short status;

    private Date createDate;

    private Date lastUpdateDate;

    private String openId;

    private String sex;

    private String headImg;

    private Short vipStyle;

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

    public String getVipName() {
        return vipName;
    }

    public void setVipName(String vipName) {
        this.vipName = vipName == null ? null : vipName.trim();
    }

    public String getVipCode() {
        return vipCode;
    }

    public void setVipCode(String vipCode) {
        this.vipCode = vipCode == null ? null : vipCode.trim();
    }

    public String getWxAddress() {
        return wxAddress;
    }

    public void setWxAddress(String wxAddress) {
        this.wxAddress = wxAddress == null ? null : wxAddress.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg == null ? null : headImg.trim();
    }

    public Short getVipStyle() {
        return vipStyle;
    }

    public void setVipStyle(Short vipStyle) {
        this.vipStyle = vipStyle;
    }
}