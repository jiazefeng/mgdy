package com.mgdy.vesta.domain.video.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Jiazefeng on 2018/5/31.
 */
@Entity
@Table(name = "md_video")
public class VideoEntity {
    private String vId;
    private String vTitle;
    private String vUrl;
    private String vContent;
    private String createName;
    private Date createDate;
    private String  avatarUrl;
    private String opentId;
    private String userId;
    private int status;//1发布 0 不可发布
    @Id
    @Column(name = "VIDEO_ID", length = 32)
    public String getvId() {
        return vId;
    }

    public void setvId(String vId) {
        this.vId = vId;
    }
    @Basic
    @Column(name = "VIDEO_TITLE", length = 200)
    public String getvTitle() {
        return vTitle;
    }

    public void setvTitle(String vTitle) {
        this.vTitle = vTitle;
    }
    @Basic
    @Column(name = "VIDEO_URL", length = 500)
    public String getvUrl() {
        return vUrl;
    }

    public void setvUrl(String vUrl) {
        this.vUrl = vUrl;
    }
    @Basic
    @Column(name = "VIDEO_CONTENT", length = 200)
    public String getvContent() {
        return vContent;
    }

    public void setvContent(String vContent) {
        this.vContent = vContent;
    }
    @Basic
    @Column(name = "CREATE_NAME", length = 32)
    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }
    @Basic
    @Column(name = "CREATE_DATE")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    @Basic
    @Column(name = "AVATAR_URL", length = 500)
    public String  getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
    @Basic
    @Column(name = "OPENT_ID", length = 64)
    public String getOpentId() {
        return opentId;
    }

    public void setOpentId(String opentId) {
        this.opentId = opentId;
    }
    @Basic
    @Column(name = "USER_ID", length = 64)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    @Basic
    @Column(name = "STATUS", length = 2)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
