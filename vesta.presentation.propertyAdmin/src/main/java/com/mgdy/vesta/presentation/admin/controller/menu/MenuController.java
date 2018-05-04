package com.mgdy.vesta.presentation.admin.controller.menu;

import com.mgdy.vesta.application.menu.DTO.MenuDTO;
import com.mgdy.vesta.application.menu.inf.MenuService;
import com.mgdy.vesta.domain.model.UserPropertyStaffEntity;
import com.mgdy.vesta.taglib.page.WebPage;
import org.jboss.logging.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * 菜单管理
 * Created by Jason on 2017/7/9.
 */
@Controller
@RequestMapping(value = "/menu")
@SessionAttributes(types = {UserPropertyStaffEntity.class, String.class}, value = {"propertystaff", "menulist", "secanViewlist"})
public class MenuController {
    @Autowired
    private MenuService menuService;
    /**
     * 初始化一级菜单列表
     * @return
     */
    @RequestMapping(value = "/menuManage.html",method = RequestMethod.GET)
    public String menuManage(Model model, WebPage webPage){
        List<MenuDTO> roleMenuDTOs = menuService.getFirstMenuList(webPage);
        model.addAttribute("roleMenuDTOs", roleMenuDTOs);

        return "/integratedManagement/menu/MenuManage";
    }
    /**
     * 添加一级菜单
     * @param roleMenuDTO
     * @param
     * @return
     */
    @RequestMapping(value = "/addFirMenu",method = RequestMethod.GET)
    public ModelAndView addFirMenu(@Valid MenuDTO roleMenuDTO){

        menuService.addFirMenu(roleMenuDTO);

        return new ModelAndView("redirect:../menu/menuManage.html");
    }
    /**
     * 初始化二级菜单列表
     * @return
     */
    @RequestMapping(value = "/menuSecManage",method = RequestMethod.GET)
    public String menuSecManage(Model model, @Param String parId, WebPage webPage){
        List<MenuDTO> roleMenuDTOs = menuService.listSecMenu(parId,webPage);
        model.addAttribute("roleMenuDTOs", roleMenuDTOs);
        model.addAttribute("parId",parId);
        return "/integratedManagement/menu/MenuSecManage";
    }
    /**
     * 添加二级菜单
     * @param roleMenuDTO
     * @param
     * @return
     */
    @RequestMapping(value = "/addSecMenu",method = RequestMethod.GET)
    public ModelAndView addSecMenu(@Valid MenuDTO roleMenuDTO){
        menuService.addSecMenu(roleMenuDTO);
        return new ModelAndView("redirect:../menu/menuSecManage");
    }
}
