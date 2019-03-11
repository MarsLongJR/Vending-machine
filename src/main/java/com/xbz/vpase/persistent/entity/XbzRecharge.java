package com.xbz.vpase.persistent.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class XbzRecharge implements Serializable {
    private Long id;

    private Integer vipId;

    private BigDecimal accounts;

    private Short status;

    private Short payType;

    private Date rechargeTime;

    private Boolean enable;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVipId() {
        return vipId;
    }

    public void setVipId(Integer vipId) {
        this.vipId = vipId;
    }

    public BigDecimal getAccounts() {
        return accounts;
    }

    public void setAccounts(BigDecimal accounts) {
        this.accounts = accounts;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Short getPayType() {
        return payType;
    }

    public void setPayType(Short payType) {
        this.payType = payType;
    }

    public Date getRechargeTime() {
        return rechargeTime;
    }

    public void setRechargeTime(Date rechargeTime) {
        this.rechargeTime = rechargeTime;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}