package com.mgdy.vesta.application.news.DTO;

/**
 * Created by Jason on 2017/7/4.
 */
public class NewsDTO {
    private String newsId;        //新闻ID
    private String newsTitle;     //新闻标题
    private String newsImgUrl;    //新闻插图
    private String newsContent;   //新闻内容
    private String newsSource;    //来源
    private String slideShow;     //"1"设置为轮播图
    private String order;//轮播图排序
    private String createName;    //创建人
    private String createDate;      //创建时间
    private String modifyName;    //修改人
    private String modifyDate;      //修改时间
    private String latitude;//所在纬度
    private String longitude;//所在经度

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsImgUrl() {
        return newsImgUrl;
    }

    public void setNewsImgUrl(String newsImgUrl) {
        this.newsImgUrl = newsImgUrl;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public String getNewsSource() {
        return newsSource;
    }

    public void setNewsSource(String newsSource) {
        this.newsSource = newsSource;
    }

    public String getSlideShow() {
        return slideShow;
    }

    public void setSlideShow(String slideShow) {
        this.slideShow = slideShow;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getModifyName() {
        return modifyName;
    }

    public void setModifyName(String modifyName) {
        this.modifyName = modifyName;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }
}
