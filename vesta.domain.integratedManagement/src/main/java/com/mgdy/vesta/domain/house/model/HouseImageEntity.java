package com.mgdy.vesta.domain.house.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 房屋图片
 * Created by Jason on 2017/7/10.
 */
@Entity
@Table(name = "md_house_image")
public class HouseImageEntity {
    private String imageId;//主键ID
    private String houseId;//房屋ID
    private String imgUrl;//图片地址
    private Date createOn;//创建时间
    @Id
    @Column(name = "IMAGE_ID", length = 32)
    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
    @Basic
    @Column(name = "HOUSE_ID", length = 32)
    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }
    @Basic
    @Column(name = "IMAGE_URL", length = 200)
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    @Basic
    @Column(name = "CREATE_ON")
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }
}
