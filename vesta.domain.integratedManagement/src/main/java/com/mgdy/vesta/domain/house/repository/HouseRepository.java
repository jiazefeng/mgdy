package com.mgdy.vesta.domain.house.repository;

import com.mgdy.vesta.domain.house.model.HouseEntity;
import com.mgdy.vesta.domain.house.model.HouseImageEntity;
import com.mgdy.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * Created by Jason on 2017/7/10.
 */
public interface HouseRepository {
    /**
     * 分页获取房屋信息
     *
     * @param map
     * @param webPage
     * @return
     */
    List<HouseEntity> getHouseInfoList(Map map, WebPage webPage);

    /**
     * 添加房屋信息
     *
     * @param houseEntity
     * @return
     */
    boolean addHouse(HouseEntity houseEntity);

    /**
     * 添加房屋图片信息
     *
     * @param houseImageEntity
     */
    void addHouseImage(HouseImageEntity houseImageEntity);

    /**
     * 按ID获取信息
     *
     * @param houseId
     * @return
     */
    HouseEntity getHouseInfoById(String houseId);

    /**
     * 获取房屋图片信息
     *
     * @return
     */
    List<HouseImageEntity> getHouseImageList();

    /**
     * 修改房屋信息
     *
     * @param houseEntity
     * @return
     */
    boolean updateHouse(HouseEntity houseEntity);

    /**
     * 根据房屋ID获取图片信息
     *
     * @param houseId
     * @return
     */
    List<HouseImageEntity> getHouseImageInfoById(String houseId);

    /**
     * 删除房屋对应的图片信息
     *
     * @param houseId
     */
    void deleteHouseImageInfoById(String houseId);

    /**
     * 删除房屋信息
     *
     * @param houseEntity
     */
    void deleteHouseInfo(HouseEntity houseEntity);

    /**
     * 获取房屋信息
     *
     * @return
     */
    List<HouseEntity> getHouseInfoList(String housType);
}
