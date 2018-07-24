package com.xulihuazj.pms.object.pmsdb.sms;

import com.xulihuazj.pms.entity.DOBaseEntity;

public class SmsLogDO extends DOBaseEntity {
	
	private static final long serialVersionUID = 998232859773681544L;

	private Long smsId;

    private Long mobile;

    private String content;

    private Integer serviceType;

    private String serviceConditions;

    private String sendStatus;

    private Integer failureCount;

    private String sendPeople;

    private String validationStatus;

    private String userAgent;

    public Long getSmsId() {
        return smsId;
    }

    public void setSmsId(Long smsId) {
        this.smsId = smsId;
    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceConditions() {
        return serviceConditions;
    }

    public void setServiceConditions(String serviceConditions) {
        this.serviceConditions = serviceConditions == null ? null : serviceConditions.trim();
    }

    public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus == null ? null : sendStatus.trim();
    }

    public Integer getFailureCount() {
        return failureCount;
    }

    public void setFailureCount(Integer failureCount) {
        this.failureCount = failureCount;
    }

    public String getSendPeople() {
        return sendPeople;
    }

    public void setSendPeople(String sendPeople) {
        this.sendPeople = sendPeople == null ? null : sendPeople.trim();
    }

    public String getValidationStatus() {
        return validationStatus;
    }

    public void setValidationStatus(String validationStatus) {
        this.validationStatus = validationStatus == null ? null : validationStatus.trim();
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent == null ? null : userAgent.trim();
    }
}