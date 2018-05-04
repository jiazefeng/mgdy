package com.mgdy.vesta.application.DTO;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

/**
 * 游客管理
 * Created by Jason on 2017/7/13.
 */
public class TouristDTO {
    private String touristId;
    private String touristName;
    private String touristImageUrl;
    private String createBy;
    private String modifyBy;
    private String createOn;
    private String modifyOn;

    public String getTouristId() {
        return touristId;
    }

    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }

    public String getTouristName() {
        return touristName;
    }

    public void setTouristName(String touristName) {
        this.touristName = touristName;
    }

    public String getTouristImageUrl() {
        return touristImageUrl;
    }

    public void setTouristImageUrl(String touristImageUrl) {
        this.touristImageUrl = touristImageUrl;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public String getCreateOn() {
        return createOn;
    }

    public void setCreateOn(String createOn) {
        this.createOn = createOn;
    }

    public String getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(String modifyOn) {
        this.modifyOn = modifyOn;
    }
}
