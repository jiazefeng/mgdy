package com.mgdy.vesta.application.viewSpot.DTO;

/**
 * Created by Jason on 2017/7/11.
 */
public class ViewSpotDTO {
    private String viewSpotId;//景点ID
    private String viewSpotTitle;//景点标题
    private String viewSpotDescribe;//景点描述
    private String viewSpotImageUrl;//图片url
    private String createOn;//创建时间
    private String modifyOn;//修改时间
    private String createBy;//创建人
    private String modifyBy;//修改人
    private String classify;//分类 1：美景；2：美食；3：娱乐

    private String id;
    private String title;
    private String imageUrl;
    private String describe;
    public String getViewSpotId() {
        return viewSpotId;
    }

    public void setViewSpotId(String viewSpotId) {
        this.viewSpotId = viewSpotId;
    }

    public String getViewSpotTitle() {
        return viewSpotTitle;
    }

    public void setViewSpotTitle(String viewSpotTitle) {
        this.viewSpotTitle = viewSpotTitle;
    }

    public String getViewSpotDescribe() {
        return viewSpotDescribe;
    }

    public void setViewSpotDescribe(String viewSpotDescribe) {
        this.viewSpotDescribe = viewSpotDescribe;
    }

    public String getViewSpotImageUrl() {
        return viewSpotImageUrl;
    }

    public void setViewSpotImageUrl(String viewSpotImageUrl) {
        this.viewSpotImageUrl = viewSpotImageUrl;
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

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
