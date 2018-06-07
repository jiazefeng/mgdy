package com.mgdy.vesta.presentation.admin.controller.viewSpot;

import com.mgdy.vesta.application.house.DTO.HouseDTO;
import com.mgdy.vesta.application.house.inf.HouseService;
import com.mgdy.vesta.application.viewSpot.DTO.ViewSpotDTO;
import com.mgdy.vesta.application.viewSpot.inf.ViewSpotService;
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
import java.util.List;

/**
 * Created by Jason on 2017/7/10.
 */
@Controller
@RequestMapping(value = "/viewSpot")
@SessionAttributes(types = {UserPropertyStaffEntity.class, String.class}, value = {"propertystaff", "menulist", "secanViewlist"})
public class ViewSpotController {
    @Autowired
    private ViewSpotService viewSpotService;
    @Value("${IMAGE_SERVER_URL}")
    private String IMAGE_SERVER_URL;
    /**
     * 分页获取景点列表
     *
     * @param userPropertystaff
     * @param viewSpiotDTO
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/getViewSpotInfoList.html", method = RequestMethod.GET)
    public String getViewSpotInfoList(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaff, @Valid ViewSpotDTO viewSpiotDTO, Model model, WebPage webPage) {

        List<ViewSpotDTO> viewSpotDTOList = viewSpotService.getViewSpotDTOInfoList(viewSpiotDTO, webPage, userPropertystaff);
        model.addAttribute("viewSpotInfoList", viewSpotDTOList);
        model.addAttribute("viewSpotInfo", viewSpiotDTO);
        return "/integratedManagement/viewSpot/ViewSpotInfoList";
    }

    /**
     * 跳转到添加景点信息页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/toAddViewSpot.html")
    public String toAdd(Model model) {
        return "/integratedManagement/viewSpot/AddViewSpotInfo";
    }

    /**
     * 保存景点信息
     */
    @RequestMapping(value = "/saveViewSpotInfo", method = RequestMethod.POST)
    public ModelAndView saveViewSpotInfo(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                        @RequestParam(value = "viewSpotImgFile", required = false) MultipartFile viewSpotImgFile,
                                        ViewSpotDTO viewSpotDTO,Model model, HttpServletRequest req) {
        String imgType = "houseImage";
        viewSpotService.saveViewSpotInfo(userPropertystaffEntity, viewSpotImgFile,viewSpotDTO,req,imgType,IMAGE_SERVER_URL);
        return new ModelAndView("redirect:../viewSpot/getViewSpotInfoList.html");
    }
    /**
     * 跳转到编辑页面
     */
    @RequestMapping(value = "/toModifyViewSpot.html")
    public String toModifyViewSpot(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity, Model model, @RequestParam String viewSpotId) {
        ViewSpotDTO viewSpotDTO = viewSpotService.getViewSpotInfoById(viewSpotId);
        model.addAttribute("viewSpotInfo", viewSpotDTO);
        return "/integratedManagement/viewSpot/UpdateViewSpotInfo";
    }
    /**
     * 保存编辑景点信息
     */
    @RequestMapping(value = "/updateViewSpotInfo", method = RequestMethod.POST)
    public ModelAndView updateViewSpotInfo(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                      @RequestParam(value = "viewSpotImgFile", required = false) MultipartFile viewSpotImgFile,
                                      ViewSpotDTO viewSpotDTO,Model model, HttpServletRequest req) {
        String imgType = "houseImage";
        viewSpotService.updateViewSpotInfo(userPropertystaffEntity, viewSpotImgFile,viewSpotDTO,req,imgType,IMAGE_SERVER_URL);
        return new ModelAndView("redirect:../viewSpot/getViewSpotInfoList.html");
    }
    /**
     * 删除景点信息
     */
    @RequestMapping(value = "/deleteViewSpotInfo.html")
    public String deleteViewSpotInfo(Model model, @RequestParam String viewSoptId) {
        viewSpotService.deleteViewSpotInfo(viewSoptId);
        return "redirect:../viewSpot/getViewSpotInfoList.html";
    }
}
