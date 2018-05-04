package com.mgdy.vesta.domain.house.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 房屋管理
 * Created by Jason on 2017/7/10.
 */
@Entity
@Table(name = "md_house")
public class HouseEntity {
    public final static String ECONOMICS="";//经济房
    public final static String COMFORTABLE="";//舒适房
    public final static String HIGH_GRADE="";//高档房
    public final static String LUCURY="";//豪华房


    private String houseId;//房屋ID
    private String houseType;//房屋类型
    private String housePrice;//房屋价格
    private String groupBuyingPrice;//团购价
    private String houseImge;//首页展示图
    private String houseDescribe;//房屋描述
    private Date createOn;//创建时间
    private String createBy;//创建人
    private Date modifyOn;//修改时间
    private String modifyBy;//修改人
    private String houseState;//房屋状态
    @Id
    @Column(name = "HOUSE_ID", length = 32)
    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }
    @Basic
    @Column(name = "HOUSE_TYPE", length = 16)
    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }
    @Basic
    @Column(name = "HOUSE_PRICE", length = 10)
    public String getHousePrice() {
        return housePrice;
    }

    public void setHousePrice(String housePrice) {
        this.housePrice = housePrice;
    }
    @Basic
    @Column(name = "GROUP_BUYING_PRICE", length = 10)
    public String getGroupBuyingPrice() {
        return groupBuyingPrice;
    }

    public void setGroupBuyingPrice(String groupBuyingPrice) {
        this.groupBuyingPrice = groupBuyingPrice;
    }
    @Basic
    @Column(name = "HOUSE_IMAGE", length = 200)
    public String getHouseImge() {
        return houseImge;
    }

    public void setHouseImge(String houseImge) {
        this.houseImge = houseImge;
    }

    public String getHouseDescribe() {
        return houseDescribe;
    }
    @Basic
    @Column(name = "HOUSE_DESCRIBE", length = 2000)
    public void setHouseDescribe(String houseDescribe) {
        this.houseDescribe = houseDescribe;
    }
    @Basic
    @Column(name = "CREATE_DATE")
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }
    @Basic
    @Column(name = "CREATE_BY", length = 32)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
    @Basic
    @Column(name = "MODIFY_ON")
    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }
    @Basic
    @Column(name = "MODIFY_BY", length = 32)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    @Basic
    @Column(name = "HOUSE_STATE", length = 6)
    public String getHouseState() {
        return houseState;
    }

    public void setHouseState(String houseState) {
        this.houseState = houseState;
    }
}
