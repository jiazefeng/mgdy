package com.mgdy.vesta.application.house.DTO;

import java.util.List;

/**
 * Created by Jason on 2017/7/10.
 */
public class HouseDTO {
    private String houseId;//房屋ID
    private String houseType;//房屋类型
    private String housePrice;//房屋价格
    private String groupBuyingPrice;//团购价
    private String houseDescribe;//房屋描述
    private String houseImge;//首页展示图
    private List<String> houseImageList;//房屋详情图片

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getHousePrice() {
        return housePrice;
    }

    public void setHousePrice(String housePrice) {
        this.housePrice = housePrice;
    }

    public String getGroupBuyingPrice() {
        return groupBuyingPrice;
    }

    public void setGroupBuyingPrice(String groupBuyingPrice) {
        this.groupBuyingPrice = groupBuyingPrice;
    }

    public String getHouseDescribe() {
        return houseDescribe;
    }

    public void setHouseDescribe(String houseDescribe) {
        this.houseDescribe = houseDescribe;
    }

    public String getHouseImge() {
        return houseImge;
    }

    public void setHouseImge(String houseImge) {
        this.houseImge = houseImge;
    }

    public List<String> getHouseImageList() {
        return houseImageList;
    }

    public void setHouseImageList(List<String> houseImageList) {
        this.houseImageList = houseImageList;
    }
}
