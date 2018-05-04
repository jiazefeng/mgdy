package com.mgdy.vesta.domain.viewSpot.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 景点管理信息
 * Created by Jason on 2017/7/11.
 */
@Entity
@Table(name = "md_view_spot")
public class ViewSpotEntity {
    private String viewSpotId;//景点ID
    private String viewSpotTitle;//景点标题
    private String viewSpotDescribe;//景点描述
    private String viewSpotImageUrl;//图片url
    private Date createOn;//创建时间
    private Date modifyOn;//修改时间
    private String createBy;//创建人
    private String modifyBy;//修改人
    private String classify;//分类 1：美景；2：美食；3：娱乐

    @Id
    @Column(name = "VIEW_SPOT_ID", length = 32)
    public String getViewSpotId() {
        return viewSpotId;
    }

    public void setViewSpotId(String viewSpotId) {
        this.viewSpotId = viewSpotId;
    }

    @Basic
    @Column(name = "VIEW_SPOT_TITLE", length = 32)
    public String getViewSpotTitle() {
        return viewSpotTitle;
    }

    public void setViewSpotTitle(String viewSpotTitle) {
        this.viewSpotTitle = viewSpotTitle;
    }

    @Basic
    @Column(name = "VIEW_SPOT_DESCRIBE", length = 2000)
    public String getViewSpotDescribe() {
        return viewSpotDescribe;
    }

    public void setViewSpotDescribe(String viewSpotDescribe) {
        this.viewSpotDescribe = viewSpotDescribe;
    }

    @Basic
    @Column(name = "VIEW_SPOT_IMAGE_URL", length = 100)
    public String getViewSpotImageUrl() {
        return viewSpotImageUrl;
    }

    public void setViewSpotImageUrl(String viewSpotImageUrl) {
        this.viewSpotImageUrl = viewSpotImageUrl;
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

    @Basic
    @Column(name = "CREATE_BY", length = 32)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "MODIFY_BY", length = 32)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }
    @Basic
    @Column(name = "CLASSIFY", length = 6)
    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }
}
