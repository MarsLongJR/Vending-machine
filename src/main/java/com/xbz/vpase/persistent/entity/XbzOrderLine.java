package com.xbz.vpase.persistent.entity;

import java.io.Serializable;
import java.util.Date;

public class XbzOrderLine implements Serializable {
    private Long id;

    private Long orderId;

    private Short status;

    private Date processingTime;

    private Long userId;

    private String processor;

    private String processorTel;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Date getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(Date processingTime) {
        this.processingTime = processingTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor == null ? null : processor.trim();
    }

    public String getProcessorTel() {
        return processorTel;
    }

    public void setProcessorTel(String processorTel) {
        this.processorTel = processorTel == null ? null : processorTel.trim();
    }
}