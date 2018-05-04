package com.mgdy.vesta.application.DTO;

import java.util.List;

/**
 * Created by Jason on 2017/7/4.
 */
public class UserPropertystaffDTO {

    private String staffIdDto;//员工ID
    private String userNameDto;//用户名
    private String passwordDto;//密码
    private String staffNameDto;//名称
    private String staffStateDto;//状态
    private String mobileDto;//手机
    private String roleSetIdDto;//权限Id
    private String roleSetNameDto;//角色名称
    private String createByDto;//创建人
    private String createOnDto;//创建时间
    private String modifyByDto;//修改人
    private String modifyOnDto;//修改时间
    private String beginTimeDto;//开始时间
    private String endTimeDto;//结束时间
    private String email;       //邮件
    private List<String> roledescList;  //角色Id集合
    private String roledesc;    //角色描述
    private String menuId;      //菜单ID

    public String getStaffIdDto() {
        return staffIdDto;
    }

    public void setStaffIdDto(String staffIdDto) {
        this.staffIdDto = staffIdDto;
    }

    public String getUserNameDto() {
        return userNameDto;
    }

    public void setUserNameDto(String userNameDto) {
        this.userNameDto = userNameDto;
    }

    public String getPasswordDto() {
        return passwordDto;
    }

    public void setPasswordDto(String passwordDto) {
        this.passwordDto = passwordDto;
    }

    public String getStaffNameDto() {
        return staffNameDto;
    }

    public void setStaffNameDto(String staffNameDto) {
        this.staffNameDto = staffNameDto;
    }

    public String getStaffStateDto() {
        return staffStateDto;
    }

    public void setStaffStateDto(String staffStateDto) {
        this.staffStateDto = staffStateDto;
    }

    public String getMobileDto() {
        return mobileDto;
    }

    public void setMobileDto(String mobileDto) {
        this.mobileDto = mobileDto;
    }

    public String getRoleSetIdDto() {
        return roleSetIdDto;
    }

    public void setRoleSetIdDto(String roleSetIdDto) {
        this.roleSetIdDto = roleSetIdDto;
    }

    public String getRoleSetNameDto() {
        return roleSetNameDto;
    }

    public void setRoleSetNameDto(String roleSetNameDto) {
        this.roleSetNameDto = roleSetNameDto;
    }

    public String getCreateByDto() {
        return createByDto;
    }

    public void setCreateByDto(String createByDto) {
        this.createByDto = createByDto;
    }

    public String getCreateOnDto() {
        return createOnDto;
    }

    public void setCreateOnDto(String createOnDto) {
        this.createOnDto = createOnDto;
    }

    public String getModifyByDto() {
        return modifyByDto;
    }

    public void setModifyByDto(String modifyByDto) {
        this.modifyByDto = modifyByDto;
    }

    public String getModifyOnDto() {
        return modifyOnDto;
    }

    public void setModifyOnDto(String modifyOnDto) {
        this.modifyOnDto = modifyOnDto;
    }

    public String getBeginTimeDto() {
        return beginTimeDto;
    }

    public void setBeginTimeDto(String beginTimeDto) {
        this.beginTimeDto = beginTimeDto;
    }

    public String getEndTimeDto() {
        return endTimeDto;
    }

    public void setEndTimeDto(String endTimeDto) {
        this.endTimeDto = endTimeDto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoledescList() {
        return roledescList;
    }

    public void setRoledescList(List<String> roledescList) {
        this.roledescList = roledescList;
    }

    public String getRoledesc() {
        return roledesc;
    }

    public void setRoledesc(String roledesc) {
        this.roledesc = roledesc;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}
