package com.mgdy.vesta.domain.viewSpot.repository;

import com.mgdy.vesta.domain.viewSpot.model.ViewSpotEntity;
import com.mgdy.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * Created by Jason on 2017/7/11.
 */
public interface ViewSpotRepository {
    /**
     * 分页查询景点信息
     *
     * @param map
     * @param webPage
     * @return
     */
    List<ViewSpotEntity> getViewSpotInfoList(Map map, WebPage webPage);

    /**
     * 添加景点信息
     *
     * @param viewSpotEntity
     */
    void addViewSpot(ViewSpotEntity viewSpotEntity);

    /**
     * 获取对应的信息
     *
     * @param viewSpotId
     * @return
     */
    ViewSpotEntity getViewSpotInfoById(String viewSpotId);

    /**
     * 编辑景点信息
     *
     * @param viewSpotEntity
     */
    void updateViewSpot(ViewSpotEntity viewSpotEntity);

    /**
     * 删除景点信息
     *
     * @param viewSpotEntity
     */
    void deleteViewSpotInfo(ViewSpotEntity viewSpotEntity);

    /**
     * 获取景点信息
     * @return
     */
    List<ViewSpotEntity> getViewSpotInfoList();
}
