package com.mgdy.vesta.domain.news.model;

import javax.persistence.*;
import java.util.Date;

/**
 * app新闻管理
 * Created by Jason on 2017/7/4.
 */

@Entity
@Table(name = "md_news")
public class NewsEntity {
    private String newsId;        //新闻ID
    private String newsTitle;     //新闻标题
    private String newsImgUrl;    //新闻插图
    private String newsContent;   //新闻内容
    private String newsSource;    //来源
    private String slideShow;     //"1"设置为轮播图
    private String order;//轮播图排序
    private String createName;    //创建人
    private Date createDate;      //创建时间
    private String modifyName;    //修改人
    private Date modifyDate;      //修改时间

    private String latitude;//所在纬度
    private String longitude;//所在经度

    @Id
    @Column(name = "NEWS_ID", length = 32)
    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    @Basic
    @Column(name = "NEWS_TITLE", length = 500)
    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    @Basic
    @Column(name = "NEWS_IMGURL", length = 500)
    public String getNewsImgUrl() {
        return newsImgUrl;
    }

    public void setNewsImgUrl(String newsImgUrl) {
        this.newsImgUrl = newsImgUrl;
    }

    @Basic
    @Column(name = "NEWS_CONTENT")
    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    @Basic
    @Column(name = "SLIDE_SHOW", length = 50)
    public String getSlideShow() {
        return slideShow;
    }

    public void setSlideShow(String slideShow) {
        this.slideShow = slideShow;
    }

    @Basic
    @Column(name = "NEWS_SOURCE", length = 500)
    public String getNewsSource() {
        return newsSource;
    }

    public void setNewsSource(String newsSource) {
        this.newsSource = newsSource;
    }

    @Basic
    @Column(name = "CREATE_NAME", length = 50)
    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    @Basic
    @Column(name = "CREATE_DATE", length = 50)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "MODIFY_NAME", length = 50)
    public String getModifyName() {
        return modifyName;
    }

    public void setModifyName(String modifyName) {
        this.modifyName = modifyName;
    }

    @Basic
    @Column(name = "MODIFY_DATE", length = 50)
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Basic
    @Column(name = "NEWS_ORDER", length = 50)
    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    @Basic
    @Column(name = "LATITUDE", length = 32)
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Basic
    @Column(name = "LONGITUDE", length = 32)
    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
