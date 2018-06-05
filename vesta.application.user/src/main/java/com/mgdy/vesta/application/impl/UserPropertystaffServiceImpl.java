package com.mgdy.vesta.application.impl;

import com.mgdy.vesta.application.DTO.TeamDTO;
import com.mgdy.vesta.application.DTO.TouristDTO;
import com.mgdy.vesta.application.DTO.UserPropertystaffDTO;
import com.mgdy.vesta.application.inf.UserPropertystaffService;
import com.mgdy.vesta.common.restHTTPResult.ApiResult;
import com.mgdy.vesta.common.restHTTPResult.SuccessApiResult;
import com.mgdy.vesta.domain.model.*;
import com.mgdy.vesta.domain.repository.UserPropertyStaffRepository;
import com.mgdy.vesta.secret.Md5Util;
import com.mgdy.vesta.taglib.page.WebPage;
import com.mgdy.vesta.utility.*;
import com.mgdy.vesta.utility.ImgUpdate.FileUpload;
import com.mgdy.vesta.utility.ImgUpdate.ImageUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Jason on 2016/1/13.
 */
@Service
public class UserPropertystaffServiceImpl implements UserPropertystaffService {

    @Autowired
    UserPropertyStaffRepository userPropertystaffReposiroty;

    @Override
    public UserPropertyStaffEntity CheckStaffByIdAPwd(UserPropertyStaffEntity propertystaff) {
        UserPropertyStaffEntity propertystaff1 = userPropertystaffReposiroty.CheckStaffByIdAPwd(propertystaff);
        if (propertystaff1 == null) {
            return null;
        }
        return propertystaff1;
    }

    /**
     * Code:For admin login
     * Type:Server Method
     * Describe:返回指定员工ID的员工信息
     * CreateBy:Tom
     * CreateOn:2016-02-17 11:23:51
     */
    @Override
    public UserPropertyStaffEntity get(String staffId) {
        return userPropertystaffReposiroty.get(staffId);
    }

    /**
     * Code:UI0001
     * Type:UI Method
     * Describe:This is describe.
     * CreateBy:Tom
     * CreateOn:2016-03-18 09:31:22
     */
    @Override
    public UserPropertyStaffEntity getByUserName(String userName) {
        return userPropertystaffReposiroty.getByUserName(userName);
    }

    /**
     * 获取员工列表
     *
     * @return
     */
    @Override
    public List<UserPropertystaffDTO> listStaffDTO(UserPropertystaffDTO staffDto, WebPage webPage, UserPropertyStaffEntity userPropertyStaffEntity) {
        UserPropertyStaffEntity staff = new UserPropertyStaffEntity();
        if (staffDto.getUserNameDto() != null && !"".equals(staffDto.getUserNameDto())) {//搜索用户名
            staff.setUserName(staffDto.getUserNameDto());
        }
        if (staffDto.getStaffNameDto() != null && !"".equals(staffDto.getStaffNameDto())) {//搜索名称
            staff.setStaffName(staffDto.getStaffNameDto());
        }
        staff.setStaffId(userPropertyStaffEntity.getStaffId());
        List<UserPropertyStaffEntity> userPropertyStaffEntities = userPropertystaffReposiroty.listPropertyStaff(staff, webPage);

        List<UserPropertystaffDTO> userPropertystaffDTOs = new ArrayList<UserPropertystaffDTO>();
        if (userPropertyStaffEntities.size() > 0) {
            for (UserPropertyStaffEntity userPropertyStaffEntity1 : userPropertyStaffEntities) {
                UserPropertystaffDTO userPropertystaffDTO = new UserPropertystaffDTO();
                userPropertystaffDTO.setStaffIdDto(userPropertyStaffEntity1.getStaffId());
                userPropertystaffDTO.setUserNameDto(userPropertyStaffEntity1.getUserName());
                userPropertystaffDTO.setPasswordDto(userPropertyStaffEntity1.getPassword());
                userPropertystaffDTO.setStaffNameDto(userPropertyStaffEntity1.getStaffName());
                userPropertystaffDTO.setStaffStateDto(userPropertyStaffEntity1.getStaffState());
                userPropertystaffDTO.setMobileDto(userPropertyStaffEntity1.getMobile());
                userPropertystaffDTO.setCreateByDto(userPropertyStaffEntity1.getCreateBy());
                userPropertystaffDTO.setCreateOnDto(DateUtils.format(userPropertyStaffEntity1.getCreateOn(), "yyyy-MM-dd HH:mm:ss"));
                userPropertystaffDTO.setModifyByDto(userPropertyStaffEntity1.getModifyBy());
                userPropertystaffDTO.setModifyOnDto(DateUtils.format(userPropertyStaffEntity1.getModifyOn()));
                userPropertystaffDTOs.add(userPropertystaffDTO);
            }

        }

        return userPropertystaffDTOs;
    }

    /**
     * 删除员工
     *
     * @param staffId
     * @return
     */
    @Override
    public boolean deleteStaff(String staffId, UserPropertyStaffEntity propertystaff) {
        UserPropertyStaffEntity userPropertyStaffEntity = userPropertystaffReposiroty.get(staffId);
        userPropertyStaffEntity.setStaffState("0");
        boolean result = userPropertystaffReposiroty.updateStaff(userPropertyStaffEntity);
        return result;
    }

    /**
     * 添加新员工
     *
     * @param userPropertystaffDTO
     * @return
     */
    @Override
    public void addStaff(UserPropertystaffDTO userPropertystaffDTO, UserPropertyStaffEntity userPropertyStaffEntity) {
        UserPropertyStaffEntity userPropertyStaffEntity1 = new UserPropertyStaffEntity();
        userPropertyStaffEntity1.setStaffId(IdGen.uuid());//id
        userPropertyStaffEntity1.setUserName(userPropertystaffDTO.getUserNameDto());//用户名
        //密码默认为123456，然后加密
        String pwd = PasswordEncode.digestPassword("123456");
        userPropertyStaffEntity1.setPassword(pwd);//密码password,默认123456
        userPropertyStaffEntity1.setStaffName(userPropertystaffDTO.getStaffNameDto());//姓名Name    1
        userPropertyStaffEntity1.setMobile(userPropertystaffDTO.getMobileDto());//手机
        userPropertyStaffEntity1.setCreateBy(userPropertyStaffEntity.getStaffName());//创建人
        userPropertyStaffEntity1.setCreateOn(DateUtils.getDate());//创建时间
        userPropertyStaffEntity1.setModifyBy(userPropertyStaffEntity.getStaffName());//修改人
        userPropertyStaffEntity1.setModifyOn(DateUtils.getDate());//修改时间
        userPropertyStaffEntity1.setStaffState("1");
        userPropertystaffReposiroty.addStaff(userPropertyStaffEntity1);
    }

    //根据Id查询元员工详情
    @Override
    public UserPropertystaffDTO getStaffById(String id) {
        UserPropertystaffDTO userPropertystaffDTO = new UserPropertystaffDTO();
        UserPropertyStaffEntity userPropertyStaffEntity = userPropertystaffReposiroty.get(id);
        if (userPropertyStaffEntity != null) {
            userPropertystaffDTO.setStaffIdDto(userPropertyStaffEntity.getStaffId());//staffId
            userPropertystaffDTO.setUserNameDto(userPropertyStaffEntity.getUserName());//userName
            userPropertystaffDTO.setStaffNameDto(userPropertyStaffEntity.getStaffName());//姓名
            userPropertystaffDTO.setMobileDto(userPropertyStaffEntity.getMobile());//手机号
        }
        return userPropertystaffDTO;
    }

    @Override
    public List<RoleViewmodelEntity> getViewListByUserId(String property, String userid) {
        List<RoleViewmodelEntity> viewmodels = new ArrayList<>();
        if (userid == null) {
            return null;
        } else {
            viewmodels = userPropertystaffReposiroty.getViewListByUserId(property, userid);
            if (viewmodels != null && viewmodels.size() > 0) {
                return viewmodels;
            }
        }
        return null;
    }

    @Override
    public List<RoleViewmodelEntity> getViewListOtherByUserId(String property, String userid) {
        List<RoleViewmodelEntity> viewmodels = new ArrayList<>();
        if (userid == null) {
            return null;
        } else {
            viewmodels = userPropertystaffReposiroty.getViewLisOthertByUserId(property, userid);
            if (viewmodels != null && viewmodels.size() > 0) {
                return viewmodels;
            }
        }
        return null;
    }

    @Override
    public ApiResult searchStaffByName(String userName) {
        return null;
    }

    @Override
    public void updateStaff(UserPropertystaffDTO userPropertystaffDTO, UserPropertyStaffEntity userPropertystaffEntity) {
        UserPropertyStaffEntity userPropertyStaffEntity1 = userPropertystaffReposiroty.getStaffUserByStaffId(userPropertystaffDTO.getStaffIdDto());
        if (userPropertyStaffEntity1 != null) {
            userPropertyStaffEntity1.setUserName(userPropertystaffDTO.getUserNameDto());//用户名
            userPropertyStaffEntity1.setStaffName(userPropertystaffDTO.getStaffNameDto());//姓名Name    1
            userPropertyStaffEntity1.setMobile(userPropertystaffDTO.getMobileDto());//手机
            userPropertyStaffEntity1.setModifyBy(userPropertystaffEntity.getStaffName());//修改人
            userPropertyStaffEntity1.setModifyOn(DateUtils.getDate());//修改时间
            userPropertystaffReposiroty.updateStaff(userPropertyStaffEntity1);
        }
    }

    @Override
    public List<TeamDTO> getTeamList(TeamDTO teamDTO, WebPage webPage, UserPropertyStaffEntity userPropertystaff) {
        Map map = new HashMap();
        List<TeamEntity> teamEntityList = userPropertystaffReposiroty.getTeamList(map, webPage);
        List<TeamDTO> teamDTOS = new ArrayList<TeamDTO>();
        if (teamEntityList != null && teamEntityList.size() > 0) {
            for (TeamEntity teamEntity : teamEntityList) {
                TeamDTO teamDTO1 = new TeamDTO();
                teamDTO1.setTeamId(teamEntity.getTeamId());
                teamDTO1.setTeamName(teamEntity.getTeamName());
                teamDTO1.setTeamTitle(teamEntity.getTeamTitle());
                teamDTO1.setTeamImageUrl(teamEntity.getTeamImageUrl());
                teamDTO1.setTeamDescribe(teamEntity.getTeamDescribe());
                teamDTO1.setModifyBy(teamEntity.getModifyBy());
                teamDTO1.setModifyOn(DateUtils.format(teamEntity.getModifyOn(), "yyyy-MM-dd HH:mm:ss"));
                teamDTO1.setCreateBy(teamEntity.getCreateBy());
                teamDTO1.setCreateOn(DateUtils.format(teamEntity.getCreateOn(), "yyyy-MM-dd HH:mm:ss"));
                teamDTOS.add(teamDTO1);
            }
        }
        return teamDTOS;
    }

    @Override
    public void saveTeamInfo(UserPropertyStaffEntity userPropertystaffEntity, MultipartFile teamPageimgFile, TeamDTO teamDTO, HttpServletRequest req, String imgType) {
        if (teamDTO != null) {
            TeamEntity teamEntity = new TeamEntity();
            teamEntity.setTeamId(IdGen.uuid());
            teamEntity.setTeamName(teamDTO.getTeamName());
            teamEntity.setTeamTitle(teamDTO.getTeamTitle());
            teamEntity.setTeamDescribe(teamDTO.getTeamDescribe());
            teamEntity.setCreateOn(new Date());
            teamEntity.setModifyOn(new Date());
            teamEntity.setCreateBy(userPropertystaffEntity.getStaffName());
            teamEntity.setModifyBy(userPropertystaffEntity.getStaffName());
            if (!teamPageimgFile.isEmpty()) {
                String url = FileUpload.upload(req, teamPageimgFile, imgType);
                teamEntity.setTeamImageUrl(url);
            }
            userPropertystaffReposiroty.addTeamEntity(teamEntity);
        }
    }

    @Override
    public TeamDTO getTeamInfoById(String teamId) {
        TeamEntity teamEntity = userPropertystaffReposiroty.getTeamInfoById(teamId);
        TeamDTO teamDTO = null;
        if (teamEntity != null) {
            teamDTO = new TeamDTO();
            teamDTO.setTeamId(teamEntity.getTeamId());
            teamDTO.setTeamName(teamEntity.getTeamName());
            teamDTO.setTeamTitle(teamEntity.getTeamTitle());
            teamDTO.setTeamImageUrl(teamEntity.getTeamImageUrl());
            teamDTO.setTeamDescribe(teamEntity.getTeamDescribe());
            teamDTO.setModifyBy(teamEntity.getModifyBy());
            teamDTO.setModifyOn(DateUtils.format(teamEntity.getModifyOn(), "yyyy-MM-dd HH:mm:ss"));
            teamDTO.setCreateBy(teamEntity.getCreateBy());
            teamDTO.setCreateOn(DateUtils.format(teamEntity.getCreateOn(), "yyyy-MM-dd HH:mm:ss"));
        }
        return teamDTO;
    }

    @Override
    public void updateTeamInfo(UserPropertyStaffEntity userPropertystaffEntity, MultipartFile teamPageimgFile, TeamDTO teamDTO, HttpServletRequest req, String imgType) {
        TeamEntity teamEntity = userPropertystaffReposiroty.getTeamInfoById(teamDTO.getTeamId());
        if (teamEntity != null) {
            teamEntity.setTeamName(teamDTO.getTeamName());
            teamEntity.setTeamTitle(teamDTO.getTeamTitle());
            teamEntity.setTeamDescribe(teamDTO.getTeamDescribe());
            teamEntity.setModifyOn(new Date());
            teamEntity.setModifyBy(userPropertystaffEntity.getStaffName());
            if (!teamPageimgFile.isEmpty()) {
                String url = FileUpload.upload(req, teamPageimgFile, imgType);
                teamEntity.setTeamImageUrl(url);
            }
            userPropertystaffReposiroty.updateTeamEntity(teamEntity);
        }
    }

    @Override
    public void deleteTeamInfo(String teamId) {
        TeamEntity teamEntity = userPropertystaffReposiroty.getTeamInfoById(teamId);
        if (teamEntity != null) {
            userPropertystaffReposiroty.deleteTeamInfo(teamEntity);
        }
    }

    @Override
    public List<TouristDTO> getTouristList(TouristDTO touristDTO, WebPage webPage, UserPropertyStaffEntity userPropertystaff) {
        Map map = new HashMap();
        List<TouristEntity> touristEntities = userPropertystaffReposiroty.gteTouristList(webPage, map);
        List<TouristDTO> touristDTOS = new ArrayList<TouristDTO>();
        if (touristEntities != null && touristEntities.size() > 0) {
            for (TouristEntity touristEntity : touristEntities) {
                TouristDTO touristDTO1 = new TouristDTO();
                touristDTO1.setTouristId(touristEntity.getTouristId());
                touristDTO1.setTouristName(touristEntity.getTouristName());
                touristDTO1.setModifyOn(DateUtils.format(touristEntity.getModifyOn(), "yyyy-MM-dd HH:mm:ss"));
                touristDTOS.add(touristDTO1);
            }
        }
        return touristDTOS;
    }

    @Override
    public void addTouristInfo(UserPropertyStaffEntity userPropertystaffEntity, MultipartFile touristPageimgFile, TouristDTO touristDTO, HttpServletRequest req, String imgType) {
        if (touristDTO != null) {
            TouristEntity touristEntity = new TouristEntity();
            touristEntity.setTouristId(IdGen.uuid());
            if (!StringUtil.isEmpty(touristDTO.getTouristName())) {
                touristEntity.setTouristName(touristDTO.getTouristName());
            }
            if (!touristPageimgFile.isEmpty()) {
                String url = FileUpload.upload(req, touristPageimgFile, imgType);
                touristEntity.setTouristImageUrl(url);
            }
            touristEntity.setCreateBy(userPropertystaffEntity.getStaffName());
            touristEntity.setCreateOn(new Date());
            touristEntity.setModifyBy(userPropertystaffEntity.getStaffName());
            touristEntity.setModifyOn(new Date());
            userPropertystaffReposiroty.addTouristEntity(touristEntity);
        }
    }

    @Override
    public TouristDTO getTouristInfoById(String touristId) {
        TouristEntity touristEntity = userPropertystaffReposiroty.getTouristInfoById(touristId);
        TouristDTO touristDTO = new TouristDTO();
        if (touristEntity != null) {
            touristDTO.setTouristId(touristEntity.getTouristId());
            touristDTO.setTouristName(touristEntity.getTouristName());
            touristDTO.setTouristImageUrl(touristEntity.getTouristImageUrl());
        }
        return touristDTO;
    }

    @Override
    public void updateTouristInfo(UserPropertyStaffEntity userPropertystaffEntity, MultipartFile touristPageimgFile, TouristDTO touristDTO, HttpServletRequest req, String imgType) {
        TouristEntity touristEntity = userPropertystaffReposiroty.getTouristInfoById(touristDTO.getTouristId());
        if (touristEntity != null) {
            touristEntity.setTouristName(touristDTO.getTouristName());
            touristEntity.setModifyOn(new Date());
            touristEntity.setModifyBy(userPropertystaffEntity.getStaffName());
            if (!touristPageimgFile.isEmpty()) {
                String url = FileUpload.upload(req, touristPageimgFile, imgType);
                touristEntity.setTouristImageUrl(url);
            }
            userPropertystaffReposiroty.updateTouristEntity(touristEntity);
        }
    }

    @Override
    public void deleteTouristInfo(String touristId) {
        TouristEntity touristEntity = userPropertystaffReposiroty.getTouristInfoById(touristId);
        if (touristEntity != null) {
            userPropertystaffReposiroty.deleteTouristEntity(touristEntity);
        }
    }

    @Override
    public void altPassword(UserPropertystaffDTO userPropertystaffDTO) {
        UserPropertyStaffEntity userPropertyStaffEntity = userPropertystaffReposiroty.getStaffUserByStaffId(userPropertystaffDTO.getStaffIdDto());
        if (userPropertyStaffEntity != null) {
            String pwd = PasswordEncode.digestPassword(userPropertystaffDTO.getPasswordDto());
            userPropertyStaffEntity.setPassword(pwd);
            userPropertystaffReposiroty.updateStaff(userPropertyStaffEntity);
        }
    }

    @Override
    public ApiResult checkPwd(UserPropertyStaffEntity userPropertyStaffEntity, String pwd) {
        UserPropertyStaffEntity userPropertyStaffEntity1 = userPropertystaffReposiroty.getStaffUserByStaffId(userPropertyStaffEntity.getStaffId());
        ModelMap modelMap = new ModelMap();
        if (userPropertyStaffEntity1 != null) {
            String password = PasswordEncode.digestPassword(pwd);
            if (password.equals(userPropertyStaffEntity1.getPassword())) {
                modelMap.addAttribute("success", "密码正确");
            } else {
                modelMap.addAttribute("error", "密码输入不正确");
            }
        }
        return new SuccessApiResult(modelMap);
    }

    @Override
    public ApiResult getTeamInfoList() {
        List<TeamEntity> teamEntityList = userPropertystaffReposiroty.getTeamList();
        List<TeamDTO> teamDTOS = new ArrayList<TeamDTO>();
        if (teamEntityList != null && teamEntityList.size() > 0) {
            for (TeamEntity teamEntity : teamEntityList) {
                TeamDTO teamDTO = new TeamDTO();
                teamDTO.setTeamId(teamEntity.getTeamId());
                teamDTO.setTeamName(teamEntity.getTeamName());
                teamDTO.setTeamDescribe(teamEntity.getTeamDescribe());
                teamDTO.setTeamImageUrl(teamEntity.getTeamImageUrl());
                teamDTOS.add(teamDTO);
            }
        }
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("teamInfoList", teamDTOS);
        return new SuccessApiResult(modelMap);
    }

    @Override
    public ApiResult getTouristInfoList() {
        List<TouristEntity> touristEntityList = userPropertystaffReposiroty.getTouristInfoList();
        List<TouristDTO> touristDTOS = new ArrayList<TouristDTO>();
        if (touristEntityList != null && touristEntityList.size() > 0) {
            for (TouristEntity touristEntity : touristEntityList) {
                TouristDTO touristDTO = new TouristDTO();
                touristDTO.setTouristId(touristEntity.getTouristId());
                touristDTO.setTouristName(touristEntity.getTouristName());
                touristDTO.setTouristImageUrl(touristEntity.getTouristImageUrl());
                touristDTOS.add(touristDTO);
            }
        }
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("touristInfoList", touristDTOS);
        return new SuccessApiResult(modelMap);
    }

    @Override
    public UserTokenEntity GetLoginByWeChatCode(String code) {
        System.out.println("-----通过code换取网页授权access_token-----");
//        2 第二步：通过code换取网页授权access_token
        ClientAccessToken clientAccessToken = WeixinUtil.authorizationUser(code);
        if (clientAccessToken == null) {
            return null;
        }
//        3 第三步：刷新access_token（如果需要）---在内部方法中刷新，此处不需要

//        4 第四步：拉取用户信息(需scope为 snsapi_userinfo)
        System.out.println("-----拉取用户信息(需scope为 snsapi_userinfo)-----");
        WeChatUser wechatuser = WeixinUtil.getWeChatUser(clientAccessToken);
        if (wechatuser == null) {
            return null;
        }
//        5 附：检验授权凭证（access_token）是否有效
        /**
         * 第六步  与系统用户匹配--------start
         */

        System.out.println("-----判断该openid是否已经创建过用户-----");
        return null;
    }
}
