package com.mgdy.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Jason on 2017/7/4
 * Describe:员工信息表
 */
@Entity
@Table(name = "user_propertyStaff")
public class UserPropertyStaffEntity {
    private String staffId;//员工ID
    private String userName;//用户名
    private String password;//密码
    private String staffName;//名称
    private String staffState;//状态
    private String mobile;//手机
    private String roleSetId;//权限Id
    private String createBy;//创建人
    private Date createOn;//创建时间
    private String modifyBy;//修改人
    private Date modifyOn;//修改时间
    private String logo;//头像
    private String sex;  //性别
    private Date birthday;//生日
    private String birthdayType;//生日类型
    private String email; //邮件
    private String officePhone; //工作电话
    private Integer orderNum; //排序
    private String memo;      //备注
    private String city;
    private String province;
    private String country;
    private String language;
    private String WC_nickName;//微信用户昵称
    private String sourceType;//数据来源
    private String openId;
    private String sessionKey;
    @Id
    @Column(name = "STAFF_ID", nullable = false, length = 32)
    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    @Basic
    @Column(name = "USERNAME", length = 50)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "PASSWORD",  length = 100)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "STAFF_NAME", length = 30)
    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    @Basic
    @Column(name = "STAFF_STATE", length = 10)
    public String getStaffState() {
        return staffState;
    }

    public void setStaffState(String staffState) {
        this.staffState = staffState;
    }

    @Basic
    @Column(name = "MOBILE", length = 32)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    @Basic
    @Column(name = "ROLE_SET_ID", length = 32)
    public String getRoleSetId() {
        return roleSetId;
    }

    public void setRoleSetId(String roleSetId) {
        this.roleSetId = roleSetId;
    }

    @Basic
    @Column(name = "CREATE_BY",  length = 50)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "CREATE_ON")
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    @Basic
    @Column(name = "MODIFY_BY", length = 50)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
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
    @Column(name = "LOGO", length = 500)
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Basic
    @Column(name = "BIRTHDAY")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }


    @Basic
    @Column(name = "BIRTHDAY_TYPE", length = 2)
    public String getBirthdayType() {
        return birthdayType;
    }

    public void setBirthdayType(String birthdayType) {
        this.birthdayType = birthdayType;
    }

    @Basic
    @Column(name = "EMAIL", length = 100)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "OFFICE_PHONE", length = 100)
    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    @Basic
    @Column(name = "STAFF_SEX", length = 2)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "ORDER_NUMBER")
    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    @Basic
    @Column(name = "STAFF_MEMO", length = 50)
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
    @Basic
    @Column(name = "WX_CITY", length = 50)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    @Basic
    @Column(name = "WX_PROVINCE", length = 50)
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
    @Basic
    @Column(name = "WX_COUNTRY", length = 50)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    @Basic
    @Column(name = "WX_LANGUAGE", length = 50)
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
    @Basic
    @Column(name = "WX_NICK_NAME", length = 50)
    public String getWC_nickName() {
        return WC_nickName;
    }

    public void setWC_nickName(String WC_nickName) {
        this.WC_nickName = WC_nickName;
    }
    @Basic
    @Column(name = "SOURCE_TYPE", length = 6)
    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }
    @Basic
    @Column(name = "OPENT_ID", length = 50)
    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
    @Basic
    @Column(name = "SESSION_KEY", length = 50)
    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }
}
