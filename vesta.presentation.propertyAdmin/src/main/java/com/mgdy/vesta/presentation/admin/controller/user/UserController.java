package com.mgdy.vesta.presentation.admin.controller.user;

import com.mgdy.vesta.application.DTO.TeamDTO;
import com.mgdy.vesta.application.DTO.TouristDTO;
import com.mgdy.vesta.application.DTO.UserPropertystaffDTO;
import com.mgdy.vesta.application.inf.UserPropertystaffService;
import com.mgdy.vesta.common.restHTTPResult.ApiResult;
import com.mgdy.vesta.common.restHTTPResult.SuccessApiResult;
import com.mgdy.vesta.domain.model.UserPropertyStaffEntity;
import com.mgdy.vesta.taglib.page.WebPage;
import com.mgdy.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by Jason on 2017/7/13.
 */
@Controller
@RequestMapping(value = "/user")
@SessionAttributes(types = {UserPropertyStaffEntity.class, String.class}, value = {"propertystaff", "menulist", "secanViewlist"})
public class UserController {
    @Autowired
    private UserPropertystaffService userPropertystaffService;

    /**
     * 分页获取用户列表
     *
     * @param userPropertystaff
     * @param userPropertystaffDTO
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/getUserStaffList.html", method = RequestMethod.GET)
    public String getNewsList(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaff, @Valid UserPropertystaffDTO userPropertystaffDTO, Model model, WebPage webPage) {

        List<UserPropertystaffDTO> userPropertystaffDTOS = userPropertystaffService.listStaffDTO(userPropertystaffDTO, webPage, userPropertystaff);
        model.addAttribute("userStaffList", userPropertystaffDTOS);
        model.addAttribute("userStaff", userPropertystaffDTO);
        return "/integratedManagement/user/StaffManage";
    }

    /**
     * 根据用户名判断是否已存在
     *
     * @return
     */
    @RequestMapping(value = "/searchStaff")
    public ApiResult gotoAddStaff(@RequestParam(value = "userName") String userName) {
        UserPropertyStaffEntity userPropertyStaffEntity = userPropertystaffService.getByUserName(userName);
        ModelMap modelMap = new ModelMap();
        if (userPropertyStaffEntity == null) {
            modelMap.addAttribute("success", "用户名已存在");
        } else {
            modelMap.addAttribute("error", "用户名已存在");
        }
        return new SuccessApiResult(modelMap);
    }

    /**
     * 添加员工
     *
     * @param model
     * @param userPropertystaffDTO
     * @return
     */
    @RequestMapping(value = "/addStaff")
    public String addStaff(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity, Model model, @Valid UserPropertystaffDTO userPropertystaffDTO) {
        if (StringUtil.isEmpty(userPropertystaffDTO.getStaffIdDto())) {
            userPropertystaffService.addStaff(userPropertystaffDTO, userPropertystaffEntity);
        } else {
            userPropertystaffService.updateStaff(userPropertystaffDTO, userPropertystaffEntity);
        }
        return "redirect:../user/getUserStaffList.html";
    }

    /**
     * 根据Id获取信息
     *
     * @return
     */
    @RequestMapping(value = "/searchStaffById")
    public ApiResult searchStaffById(@RequestParam(value = "staffId") String staffId) {
        UserPropertystaffDTO userPropertystaffDTO = userPropertystaffService.getStaffById(staffId);
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("userStaffInfo", userPropertystaffDTO);
        return new SuccessApiResult(modelMap);
    }

    /**
     * 删除用户信息
     *
     * @return
     */
    @RequestMapping(value = "/daleteStaffById")
    public String daleteStaffById(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity, @RequestParam(value = "staffId") String staffId, Model model) {
        userPropertystaffService.deleteStaff(staffId, userPropertystaffEntity);
        return "redirect:../user/getUserStaffList.html";
    }

    /**
     * 分页获取团队列表
     *
     * @param userPropertystaff
     * @param teamDTO
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/getTeamList.html", method = RequestMethod.GET)
    public String getTeamList(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaff, @Valid TeamDTO teamDTO, Model model, WebPage webPage) {

        List<TeamDTO> teamDTOS = userPropertystaffService.getTeamList(teamDTO, webPage, userPropertystaff);
        model.addAttribute("teamInfoList", teamDTOS);
        model.addAttribute("teamInfo", teamDTO);
        return "/integratedManagement/user/TeamList";
    }

    /**
     * 跳转到新增团队信息
     *
     * @return
     */
    @RequestMapping(value = "/toAddTeam.html", method = RequestMethod.GET)
    public String getTeamList() {
        return "/integratedManagement/user/AddTeamInfo";
    }

    /**
     * 保存团队信息
     */
    @RequestMapping(value = "/saveTeamInfo", method = RequestMethod.POST)
    public ModelAndView saveTeamInfo(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                     @RequestParam(value = "teamPageimgFile", required = false) MultipartFile teamPageimgFile,
                                     TeamDTO teamDTO, Model model, HttpServletRequest req) {
        String imgType = "houseImage";
        userPropertystaffService.saveTeamInfo(userPropertystaffEntity, teamPageimgFile, teamDTO, req, imgType);
        return new ModelAndView("redirect:../user/getTeamList.html");
    }

    /**
     * 根据Id获取信息
     *
     * @return
     */
    @RequestMapping(value = "/searchTeamById")
    public String searchTeamById(@RequestParam(value = "teamId") String teamId, Model model) {
        TeamDTO teamDTO = userPropertystaffService.getTeamInfoById(teamId);
        model.addAttribute("teamInfo", teamDTO);
        return "/integratedManagement/user/UpdateTeamInfo";
    }

    /**
     * 编辑团队信息
     */
    @RequestMapping(value = "/updateTeamInfo", method = RequestMethod.POST)
    public ModelAndView updateTeamInfo(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                       @RequestParam(value = "teamPageimgFile", required = false) MultipartFile teamPageimgFile,
                                       TeamDTO teamDTO, Model model, HttpServletRequest req) {
        String imgType = "houseImage";
        userPropertystaffService.updateTeamInfo(userPropertystaffEntity, teamPageimgFile, teamDTO, req, imgType);
        return new ModelAndView("redirect:../user/getTeamList.html");
    }

    /**
     * 删除信息
     *
     * @return
     */
    @RequestMapping(value = "/deleteTeamInfo")
    public String deleteTeamInfo(@RequestParam(value = "teamId") String teamId, Model model) {
        userPropertystaffService.deleteTeamInfo(teamId);
        return "redirect:../user/getTeamList.html";
    }

    /**
     * 分页获取游客列表
     *
     * @param userPropertystaff
     * @param touristDTO
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/getTouristList.html", method = RequestMethod.GET)
    public String getTouristList(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaff, @Valid TouristDTO touristDTO, Model model, WebPage webPage) {

        List<TouristDTO> touristDTOS = userPropertystaffService.getTouristList(touristDTO, webPage, userPropertystaff);
        model.addAttribute("touristInfoList", touristDTOS);
        model.addAttribute("touristInfo", touristDTO);
        return "/integratedManagement/user/TouristList";
    }

    /**
     * 跳转到新增游客信息
     *
     * @return
     */
    @RequestMapping(value = "/toAddTourist.html", method = RequestMethod.GET)
    public String toAddTourist() {
        return "/integratedManagement/user/AddTouristInfo";
    }

    /**
     * 保存游客信息
     */
    @RequestMapping(value = "/addTouristInfo", method = RequestMethod.POST)
    public ModelAndView addTouristInfo(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                       @RequestParam(value = "touristPageimgFile", required = false) MultipartFile touristPageimgFile,
                                       TouristDTO touristDTO, Model model, HttpServletRequest req) {
        String imgType = "houseImage";
        userPropertystaffService.addTouristInfo(userPropertystaffEntity, touristPageimgFile, touristDTO, req, imgType);
        return new ModelAndView("redirect:../user/getTouristList.html");
    }

    /**
     * 根据Id获取信息
     *
     * @return
     */
    @RequestMapping(value = "/getTouristInfoById")
    public String getTouristInfoById(@RequestParam(value = "touristId") String touristId, Model model) {
        TouristDTO touristDTO = userPropertystaffService.getTouristInfoById(touristId);
        model.addAttribute("touristInfo", touristDTO);
        return "/integratedManagement/user/UpdateTouristInfo";
    }

    /**
     * 编辑游客信息
     */
    @RequestMapping(value = "/updateTouristInfo", method = RequestMethod.POST)
    public ModelAndView updateTouristInfo(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                          @RequestParam(value = "touristPageimgFile", required = false) MultipartFile touristPageimgFile,
                                          TouristDTO touristDTO, Model model, HttpServletRequest req) {
        String imgType = "houseImage";
        userPropertystaffService.updateTouristInfo(userPropertystaffEntity, touristPageimgFile, touristDTO, req, imgType);
        return new ModelAndView("redirect:../user/getTouristList.html");
    }

    /**
     * 删除信息
     *
     * @return
     */
    @RequestMapping(value = "/deleteTouristInfo")
    public String deleteTouristInfo(@RequestParam(value = "touristId") String touristId, Model model) {
        userPropertystaffService.deleteTouristInfo(touristId);
        return "redirect:../user/getTouristList.html";
    }

    /**
     * 获取登录用户信息
     *
     * @return
     */
    @RequestMapping(value = "/getUserDetailById")
    public String getUserDetailById(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity, Model model) {
        model.addAttribute("userInfo", userPropertystaffEntity);
        return "/integratedManagement/user/userDetail";
    }
    /**
     * 后台修改密码
     */
    @RequestMapping(value = "/altPassword")
    public ModelAndView altPassword(@Valid UserPropertystaffDTO userPropertystaffDTO) {
        userPropertystaffService.altPassword(userPropertystaffDTO);
        ModelAndView mav=new ModelAndView();
        mav = new ModelAndView("/error/500");
        mav.addObject("errormsg", "密码已修改，请重新登录！");
        return mav;
    }
    /**
     * 退出系统
     */
    @RequestMapping(value = "/logOut.html")
    public String  altPassword() {

        return "/index";
    }
    /**
     * 根据用户名判断是否已存在
     *
     * @return
     */
    @RequestMapping(value = "/checkPwd")
    public ApiResult checkPwd(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,@RequestParam(value = "pwd") String pwd) {
        return userPropertystaffService.checkPwd(userPropertystaffEntity,pwd);
    }
}