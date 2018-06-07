package com.mgdy.vesta.domain.repository;


import com.mgdy.vesta.domain.model.RoleViewmodelEntity;
import com.mgdy.vesta.domain.model.TeamEntity;
import com.mgdy.vesta.domain.model.TouristEntity;
import com.mgdy.vesta.domain.model.UserPropertyStaffEntity;
import com.mgdy.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * Created by Jason on 2017/7/4
 * Describe:员工信息Repository接口
 */
public interface UserPropertyStaffRepository {

    /**
     * Describe:根据用户名、密码获取用户信息
     * CreateBy:Jason
     * CreateOn:2017-07-04
     */
    UserPropertyStaffEntity getByUserNameAndPassword(String userName, String password);

    List<RoleViewmodelEntity> getViewListByUserId(String property, String userid);

    List<RoleViewmodelEntity> getViewLisOthertByUserId(String property, String userid);

    /**
     * 获取一级菜单列表
     *
     * @param parId
     * @return
     */
    List<RoleViewmodelEntity> listMenu(String parId, WebPage webPage);

    /**
     * 根据用户名和手机号校验用户
     */
    UserPropertyStaffEntity getByUserNameAndMobile(String userName, String mobile);

    /**
     * Describe:根据Id获取员工信息
     * CreateBy:Jason
     * CreateOn:2017-07-04
     */
    UserPropertyStaffEntity get(String staffId);

    UserPropertyStaffEntity CheckStaffByIdAPwd(UserPropertyStaffEntity propertystaff);

    /**
     * 获取所有员工信息列表
     *
     * @return
     */
    public List<UserPropertyStaffEntity> listPropertyStaff(UserPropertyStaffEntity userPropertyStaffEntity, WebPage webPage);

    /**
     * 删除员工信息
     *
     * @param staffId
     * @return
     */
    public boolean deleteStaff(String staffId);

    /**
     * 添加新员工
     *
     * @param userPropertyStaffEntity
     * @return
     */
    public boolean addStaff(UserPropertyStaffEntity userPropertyStaffEntity);

    /**
     * 修改员工
     *
     * @param userPropertyStaffEntity
     * @return
     */
    public boolean updateStaff(UserPropertyStaffEntity userPropertyStaffEntity);


    /**
     * 根据项目和
     *
     * @param sectionId
     * @param projectId
     * @return
     */
    public List<UserPropertyStaffEntity> listStaffByCompanyAndSection(String projectId, String sectionId);

    /**
     * 判断该用户名是否存在
     *
     * @param userName
     * @return
     */
    public UserPropertyStaffEntity testStaffByUserName(String userName);

    /**
     * @param userName
     * @return 判断OA的用户名是否存在
     */
    UserPropertyStaffEntity checkOAUserName(String userName);

    /**
     * 添加员工
     *
     * @param userPropertyStaffEntity
     */
    void addUserPropertyStaff(UserPropertyStaffEntity userPropertyStaffEntity);

    /**
     * 修改员工信息
     *
     * @param userPropertyStaffEntity
     */
    void updateUserPropertyStaff(UserPropertyStaffEntity userPropertyStaffEntity);

    /**
     * Describe:This is describe.
     * CreateBy:Tom
     * CreateOn:2016-03-18 09:31:48
     */
    UserPropertyStaffEntity getByUserName(String userName);

    /**
     * 根据员工名搜索员工
     */
    List<UserPropertyStaffEntity> searchStaffByName(String staffName);

    /**
     * 根据用户名和ID来做非我判断
     */
    UserPropertyStaffEntity getByNameID(String userName, String id);


    /* -------------会员账户管理模块------------- */

    /**
     * 获取员工用户信息列表
     *
     * @param params  参数
     * @param webPage 分页
     * @return List<Map<String,Object>>
     */
    List<Map<String, Object>> getStaffUserList(Map<String, Object> params, WebPage webPage);

    /**
     * 检索员工用户总人数
     *
     * @param params 参数
     * @return Long
     */
    Long getStaffUserCount(Map<String, Object> params);

    /**
     * 通过员工用户Id获取角色信息
     *
     * @param staffId 员工用户Id
     * @return
     */
    List<Map<String, Object>> getRoleByStaffId(String staffId);

    /**
     * 通过员工用户Id检索员工详细信息
     *
     * @param staffId 员工用户Id
     * @return UserPropertyStaffEntity
     */
    UserPropertyStaffEntity getStaffUserByStaffId(String staffId);

    /**
     * 保存或更新Entity
     */
    <T> void saveOrUpdate(T entity);

    /**
     * 通过员工用户Id删除员工角色关系
     *
     * @param staffId 员工用户Id
     */
    void deleteRoleanthorityByStaffId(String staffId);

    /**
     * 通过员工用户Id获取范围权限
     *
     * @param staffId
     * @return
     */
    List<Map<String, Object>> getRoleScopeByStaffId(String staffId);

    /**
     * 获取城市列表(ALL)
     *
     * @return List<Map<String,Object>>
     */
    List<Map<String, Object>> listCity();

    /**
     * 通过项目Id检索城市信息
     *
     * @param projectId 项目Id
     * @return Map<String,Object>
     */
    Map<String, Object> getCityByProjectId(String projectId);

    /**
     * 通过城市Id检索项目列表
     *
     * @param cityId
     * @return
     */
    List<Object[]> listProjectByCity(String cityId);

    /**
     * 检索所有城市所有项目
     *
     * @return List<Map<String,Object>>
     */
    List<Map<String, Object>> listAllProject();

    /**
     * 通过城市Id检索城市信息
     *
     * @param cityId 城市Id
     * @return HouseCityEntity
     */
    Map<String, Object> getHouseCityByCityId(String cityId);

    /**
     * 通过项目Code检索项目信息
     *
     * @param projectCode 项目Code
     * @return HouseProjectEntity
     */
    Map<String, Object> getHouseProjectByCode(String projectCode);

    /**
     * 获取批量员工用户信息列表
     *
     * @param params 参数
     * @return List<Map<String,Object>>
     */
    List<Map<String, Object>> getBatchStaffUserList(Map<String, Object> params);

    /**
     * 获取菜单的最后一个
     *
     * @return
     */
    RoleViewmodelEntity getLastFirVeiwModel();

    /**
     * 添加菜单
     *
     * @param newMenu
     */
    void addViewModel(RoleViewmodelEntity newMenu);

    /**
     * 获取二级菜单的最后一个
     *
     * @param roleMenuParId
     * @return
     */
    RoleViewmodelEntity getLastSecViewModel(String roleMenuParId);

    /**
     * 根据ID获取菜单
     *
     * @param roleMenuParId
     * @return
     */
    RoleViewmodelEntity getModelById(String roleMenuParId);

    /**
     * 分页获取团队信息
     *
     * @param map
     * @param webPage
     * @return
     */
    List<TeamEntity> getTeamList(Map map, WebPage webPage);

    /**
     * 添加团队信息
     *
     * @param teamEntity
     */
    void addTeamEntity(TeamEntity teamEntity);

    /**
     * 根据Id获取信息
     *
     * @param teamId
     * @return
     */
    TeamEntity getTeamInfoById(String teamId);

    /**
     * 编辑团队信息
     *
     * @param teamEntity
     */
    void updateTeamEntity(TeamEntity teamEntity);

    /**
     * 删除团队信息
     *
     * @param teamEntity
     */
    void deleteTeamInfo(TeamEntity teamEntity);

    /**
     * 分页获取游客信息
     *
     * @param webPage
     * @param map
     * @return
     */
    List<TouristEntity> gteTouristList(WebPage webPage, Map map);

    /**
     * 保存游客信息
     *
     * @param touristEntity
     */
    void addTouristEntity(TouristEntity touristEntity);

    /**
     * 根据ID获取对应信息
     *
     * @param touristId
     * @return
     */
    TouristEntity getTouristInfoById(String touristId);

    /**
     * 修改游客信息
     *
     * @param touristEntity
     */
    void updateTouristEntity(TouristEntity touristEntity);

    /**
     * 删除游客信息
     *
     * @param touristEntity
     */
    void deleteTouristEntity(TouristEntity touristEntity);

    /**
     * 获取团队信息
     *
     * @return
     */
    List<TeamEntity> getTeamList();

    /**
     * 获取游客信息
     *
     * @return
     */
    List<TouristEntity> getTouristInfoList();

    UserPropertyStaffEntity GetUserByOpenId(String wc, String openid);
}
