package com.mgdy.vesta.application.house.DTO;

import java.util.List;

/**
 * Created by Jason on 2017/7/12.
 */
public class HouseWebDTO {
    private String houseId;//房屋ID
    private String houseType;//房屋类型
    private String housePrice;//房屋价格
    private String groupBuyingPrice;//团购价
    private String houseDescribe;//房屋描述
    private String houseImge;//首页展示图

    private String id;
    private String title;
    private String imageUrl;
    private String describe;
    private List<String> imageUrls;

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
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
}
