package com.mgdy.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 团队管理
 * Created by Jason on 2017/7/13.
 */
@Entity
@Table(name = "md_team")
public class TeamEntity {
    private String teamId;
    private String teamName;
    private String teamTitle;
    private String teamDescribe;
    private String createBy;
    private String modifyBy;
    private Date createOn;
    private Date modifyOn;
    private String teamImageUrl;

    @Id
    @Column(name = "TEAM_ID", length = 32)
    public String getTeamId() {
        return teamId;
    }


    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    @Basic
    @Column(name = "TEAM_NAME", length = 50)
    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Basic
    @Column(name = "TEAM_TITLE", length = 50)
    public String getTeamTitle() {
        return teamTitle;
    }

    public void setTeamTitle(String teamTitle) {
        this.teamTitle = teamTitle;
    }

    @Basic
    @Column(name = "TEAM_DESCRIBE", length = 2000)
    public String getTeamDescribe() {
        return teamDescribe;
    }

    public void setTeamDescribe(String teamDescribe) {
        this.teamDescribe = teamDescribe;
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
    @Column(name = "MODIFY_ON", length = 50)
    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }

    @Basic
    @Column(name = "TEAM_IMAGE_URL", length = 200)
    public String getTeamImageUrl() {
        return teamImageUrl;
    }

    public void setTeamImageUrl(String teamImageUrl) {
        this.teamImageUrl = teamImageUrl;
    }
}
