package com.mgdy.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 游客管理
 * Created by Jason on 2017/7/13.
 */
@Entity
@Table(name = "md_tourist")
public class TouristEntity {
    private String touristId;
    private String touristName;
    private String touristImageUrl;
    private String createBy;
    private String modifyBy;
    private Date createOn;
    private Date modifyOn;

    @Id
    @Column(name = "TOURIST_ID", length = 32)
    public String getTouristId() {
        return touristId;
    }

    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }

    @Basic
    @Column(name = "TOURIST_NAME", length = 50)
    public String getTouristName() {
        return touristName;
    }

    public void setTouristName(String touristName) {
        this.touristName = touristName;
    }

    @Basic
    @Column(name = "TOURIST_IMAGE_URL", length = 200)
    public String getTouristImageUrl() {
        return touristImageUrl;
    }

    public void setTouristImageUrl(String touristImageUrl) {
        this.touristImageUrl = touristImageUrl;
    }

    @Basic
    @Column(name = "CREATE_BY", length = 50)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "MODIFY_BY", length = 50)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    @Basic
    @Column(name = "CREATE_ON")
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    @Basic
    @Column(name = "MODIFY_ON")
    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }
}
