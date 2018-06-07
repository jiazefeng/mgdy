package com.mgdy.vesta.presentation.admin.controller.house;

import com.mgdy.vesta.application.house.DTO.HouseDTO;
import com.mgdy.vesta.application.house.inf.HouseService;
import com.mgdy.vesta.domain.model.UserPropertyStaffEntity;
import com.mgdy.vesta.taglib.page.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jason on 2017/7/10.
 */
@Controller
@RequestMapping(value = "/house")
@SessionAttributes(types = {UserPropertyStaffEntity.class, String.class}, value = {"propertystaff", "menulist", "secanViewlist"})
public class HouseController {
    @Autowired
    private HouseService houseService;
    @Value("${IMAGE_SERVER_URL}")
    private String IMAGE_SERVER_URL;
    /**
     * 分页获取新闻列表
     *
     * @param userPropertystaff
     * @param houseDTO
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/getHouseInfoList.html", method = RequestMethod.GET)
    public String getNewsList(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaff, @Valid HouseDTO houseDTO, Model model, WebPage webPage) {

        List<HouseDTO> houseDTOList = houseService.getHouseInfoList(houseDTO, webPage, userPropertystaff);
        model.addAttribute("houseInfoList", houseDTOList);
        model.addAttribute("houseInfo", houseDTO);
        return "/integratedManagement/house/HouseInfoList";
    }

    /**
     * 跳转到添加房间信息页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/toAddHouse.html")
    public String toAdd(Model model) {
        return "/integratedManagement/house/AddHouseInfo";
    }

    /**
     * 保存房屋信息
     */
    @RequestMapping(value = "/saveHouseInfo", method = RequestMethod.POST)
    public ModelAndView saveHouseInfo(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                        @RequestParam(value = "homePageimgFile", required = false) MultipartFile homePageimgFile,
                                        @RequestParam(value = "houseDetailImage", required = false) MultipartFile[] houseDetailImages,
                                        HouseDTO houseDTO,Model model, HttpServletRequest req) {
        String imgType = "houseImage";
        houseService.saveHouseInfo(userPropertystaffEntity, homePageimgFile,houseDetailImages,houseDTO,req,imgType,IMAGE_SERVER_URL);
        return new ModelAndView("redirect:../house/getHouseInfoList.html");
    }
    /**
     * 跳转到编辑页面
     */
    @RequestMapping(value = "/toModifyHouse.html")
    public String toModifyNews(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity, Model model, @RequestParam String houseId) {
        HouseDTO houseDTO = houseService.getHouseInfoById(houseId);
        model.addAttribute("houseInfo", houseDTO);
        return "/integratedManagement/house/UpdateHouseInfo";
    }
    /**
     * 保存房屋信息
     */
    @RequestMapping(value = "/updateHouseInfo", method = RequestMethod.POST)
    public ModelAndView updateHouseInfo(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                      @RequestParam(value = "homePageimgFile", required = false) MultipartFile homePageimgFile,
                                      @RequestParam(value = "houseDetailImage", required = false) MultipartFile[] houseDetailImages,
                                      HouseDTO houseDTO,Model model, HttpServletRequest req) {
        String imgType = "houseImage";
        houseService.updateHouseInfo(userPropertystaffEntity, homePageimgFile,houseDetailImages,houseDTO,req,imgType,IMAGE_SERVER_URL);
        return new ModelAndView("redirect:../house/getHouseInfoList.html");
    }
    /**
     * 删除房屋信息
     */
    @RequestMapping(value = "/deleteHouseInfo.html")
    public String deleteHouseInfo(Model model, @RequestParam String houseId) {
        houseService.deleteHouseInfo(houseId);
        return "redirect:../house/getHouseInfoList.html";
    }
}
