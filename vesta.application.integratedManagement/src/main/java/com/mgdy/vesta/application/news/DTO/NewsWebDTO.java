package com.mgdy.vesta.application.news.DTO;

import java.util.List;

/**
 * Created by Jason on 2017/7/5.
 */
public class NewsWebDTO {
    private String newsId;        //新闻ID
    private String newsTitle;     //新闻标题
    private String newsImgUrl;    //新闻插图
    private String newsContent;   //新闻内容
    private String createDate;//发布日期
    private String createTime;//发布时间
    private List<NewsWebDTO> slideShowList;//轮播图

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

    public List<NewsWebDTO> getSlideShowList() {
        return slideShowList;
    }

    public void setSlideShowList(List<NewsWebDTO> slideShowList) {
        this.slideShowList = slideShowList;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
