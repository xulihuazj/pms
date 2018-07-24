package com.xulihuazj.pms.object.pmsdb.ip;

import com.xulihuazj.pms.entity.DOBaseEntity;

public class IpWhiteListDO extends DOBaseEntity {

    private Long id;

    private String location;

    private String remark;

    private String status;

    private String urlPath;

    private Integer throttle;

    private Integer throttleCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath == null ? null : urlPath.trim();
    }

    public Integer getThrottle() {
        return throttle;
    }

    public void setThrottle(Integer throttle) {
        this.throttle = throttle;
    }

    public Integer getThrottleCount() {
        return throttleCount;
    }

    public void setThrottleCount(Integer throttleCount) {
        this.throttleCount = throttleCount;
    }
}