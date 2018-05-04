package com.mgdy.vesta.domain.news.repository;

import com.mgdy.vesta.domain.news.model.NewsEntity;
import com.mgdy.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * Created by Jason on 2017/7/4.
 */
public interface NewsRepository {
    /**
     * 分页获取新闻列表
     *
     * @param map
     * @param webPage
     * @param staffId
     * @return
     */
    List<NewsEntity> getNewsList(Map map, WebPage webPage, String staffId);

    /**
     * 新增新闻
     *
     * @param newsEntity
     */
    void addNews(NewsEntity newsEntity);

    /**
     * 根据Id查询新闻信息
     *
     * @param newsId
     * @return
     */
    NewsEntity getNewsInfoById(String newsId);

    /**
     * 编辑新闻
     *
     * @param newsEntity
     */
    void updateNews(NewsEntity newsEntity);

    /**
     * 删除新闻
     *
     * @param newsEntity
     */
    void deleteNews(NewsEntity newsEntity);

    /**
     * 查询条数
     *
     * @param slideShow
     * @return
     */
    int getNewsCount(String slideShow);

    /**
     * 获取新闻信息
     *
     * @return
     */
    List<NewsEntity> getNewsList();
}
