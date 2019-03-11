package com.xbz.vpase.persistent.entity;

import java.io.Serializable;
import java.util.Date;

public class XbzMachine implements Serializable {
    private Integer id;

    private String machineName;

    private String machineCode;

    private Short machineType;

    private Date createTime;

    private Short status;

    private Integer creatorId;

    private Boolean connectStatus;

    private Short temperatureStatus;

    private Integer addressId;

    private String creator;

    private Date openTime;

    private Date offTime;

    private Integer channelNum;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName == null ? null : machineName.trim();
    }

    public String getMachineCode() {
        return machineCode;
    }

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode == null ? null : machineCode.trim();
    }

    public Short getMachineType() {
        return machineType;
    }

    public void setMachineType(Short machineType) {
        this.machineType = machineType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Boolean getConnectStatus() {
        return connectStatus;
    }

    public void setConnectStatus(Boolean connectStatus) {
        this.connectStatus = connectStatus;
    }

    public Short getTemperatureStatus() {
        return temperatureStatus;
    }

    public void setTemperatureStatus(Short temperatureStatus) {
        this.temperatureStatus = temperatureStatus;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public Date getOffTime() {
        return offTime;
    }

    public void setOffTime(Date offTime) {
        this.offTime = offTime;
    }

    public Integer getChannelNum() {
        return channelNum;
    }

    public void setChannelNum(Integer channelNum) {
        this.channelNum = channelNum;
    }
}