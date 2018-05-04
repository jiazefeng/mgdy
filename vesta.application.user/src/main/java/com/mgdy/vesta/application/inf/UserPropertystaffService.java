package com.mgdy.vesta.application.inf;

import com.mgdy.vesta.application.DTO.TeamDTO;
import com.mgdy.vesta.application.DTO.TouristDTO;
import com.mgdy.vesta.application.DTO.UserPropertystaffDTO;
import com.mgdy.vesta.common.restHTTPResult.ApiResult;
import com.mgdy.vesta.domain.model.RoleViewmodelEntity;
import com.mgdy.vesta.domain.model.UserPropertyStaffEntity;
import com.mgdy.vesta.taglib.page.WebPage;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Jason on 2017/7/4.
 */
public interface UserPropertystaffService {
    UserPropertyStaffEntity CheckStaffByIdAPwd(UserPropertyStaffEntity propertystaff);

    /**
     * Code:For admin login
     * Type:Server Method
     * Describe:返回指定员工ID的员工信息
     * CreateBy:Jason
     * CreateOn:2017-07-04 09:30:48
     */
    UserPropertyStaffEntity get(String staffId);

    /**
     * Code:UI0001
     * Type:UI Method
     * Describe:This is describe.
     * CreateBy:Jason
     * CreateOn:2017-07-04 09:30:48
     */
    UserPropertyStaffEntity getByUserName(String userName);

    /**
     * 获取员工列表
     *
     * @return
     */
    public List<UserPropertystaffDTO> listStaffDTO(UserPropertystaffDTO staffDto, WebPage webPage, UserPropertyStaffEntity userPropertyStaffEntity);

    /**
     * 删除员工
     *
     * @param staffId
     * @return
     */
    public boolean deleteStaff(String staffId, UserPropertyStaffEntity userPropertystaffEntity);

    /**
     * 添加新员工
     *
     * @param userPropertystaffDTO
     * @return
     */
    public void addStaff(UserPropertystaffDTO userPropertystaffDTO, UserPropertyStaffEntity userPropertyStaffEntity);

    /**
     * 根据Id查找员工详情
     *
     * @param id
     * @return
     */
    public UserPropertystaffDTO getStaffById(String id);

    List<RoleViewmodelEntity> getViewListByUserId(String property, String userid);

    List<RoleViewmodelEntity> getViewListOtherByUserId(String property, String userid);

    /**
     * 根据用户名查询信息
     *
     * @param userName
     * @return
     */
    ApiResult searchStaffByName(String userName);

    /**
     * 编辑员工信息
     *
     * @param userPropertystaffDTO
     * @param userPropertystaffEntity
     */
    void updateStaff(UserPropertystaffDTO userPropertystaffDTO, UserPropertyStaffEntity userPropertystaffEntity);

    /**
     * 获取团队信息
     *
     * @param teamDTO
     * @param webPage
     * @param userPropertystaff
     * @return
     */
    List<TeamDTO> getTeamList(TeamDTO teamDTO, WebPage webPage, UserPropertyStaffEntity userPropertystaff);

    /**
     * 保存团队信息
     *
     * @param userPropertystaffEntity
     * @param teamPageimgFile
     * @param teamDTO
     * @param req
     * @param imgType
     */
    void saveTeamInfo(UserPropertyStaffEntity userPropertystaffEntity, MultipartFile teamPageimgFile, TeamDTO teamDTO, HttpServletRequest req, String imgType);

    /**
     * 根据ID 获取信息
     *
     * @param teamId
     * @return
     */
    TeamDTO getTeamInfoById(String teamId);

    /**
     * 编辑团队信息
     *
     * @param userPropertystaffEntity
     * @param teamPageimgFile
     * @param teamDTO
     * @param req
     * @param imgType
     */
    void updateTeamInfo(UserPropertyStaffEntity userPropertystaffEntity, MultipartFile teamPageimgFile, TeamDTO teamDTO, HttpServletRequest req, String imgType);

    /**
     * 删除团队信息
     *
     * @param teamId
     */
    void deleteTeamInfo(String teamId);

    /**
     * 分页获取游客列表
     *
     * @param touristDTO
     * @param webPage
     * @param userPropertystaff
     * @return
     */
    List<TouristDTO> getTouristList(TouristDTO touristDTO, WebPage webPage, UserPropertyStaffEntity userPropertystaff);

    /**
     * 保存游客的信息
     *
     * @param userPropertystaffEntity
     * @param touristPageimgFile
     * @param touristDTO
     * @param req
     * @param imgType
     */
    void addTouristInfo(UserPropertyStaffEntity userPropertystaffEntity, MultipartFile touristPageimgFile, TouristDTO touristDTO, HttpServletRequest req, String imgType);

    /**
     * 根据ID获取信息
     *
     * @param touristId
     * @return
     */
    TouristDTO getTouristInfoById(String touristId);

    /**
     * 编辑游客信息
     *
     * @param userPropertystaffEntity
     * @param touristPageimgFile
     * @param touristDTO
     * @param req
     * @param imgType
     */
    void updateTouristInfo(UserPropertyStaffEntity userPropertystaffEntity, MultipartFile touristPageimgFile, TouristDTO touristDTO, HttpServletRequest req, String imgType);

    /**
     * 删除信息
     *
     * @param touristId
     */
    void deleteTouristInfo(String touristId);

    /**
     * 修改密码
     *
     * @param userPropertystaffDTO
     */
    void altPassword(UserPropertystaffDTO userPropertystaffDTO);

    ApiResult checkPwd(UserPropertyStaffEntity userPropertyStaffEntity, String pwd);

    /**
     * 获取团队信息
     *
     * @return
     */
    ApiResult getTeamInfoList();

    /**
     * 获取游客信息
     *
     * @return
     */
    ApiResult getTouristInfoList();
}
