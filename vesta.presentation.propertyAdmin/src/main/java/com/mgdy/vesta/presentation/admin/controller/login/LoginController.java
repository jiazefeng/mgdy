package com.mgdy.vesta.presentation.admin.controller.login;

import com.mgdy.vesta.application.inf.UserPropertystaffService;
import com.mgdy.vesta.common.restHTTPResult.ApiResult;
import com.mgdy.vesta.common.restHTTPResult.SuccessApiResult;
import com.mgdy.vesta.domain.model.RoleViewmodelEntity;
import com.mgdy.vesta.domain.model.UserPropertyStaffEntity;
import com.mgdy.vesta.taglib.vesta.Viewmodel;
import com.mgdy.vesta.utility.PasswordEncode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Jason on 2017/7/3.
 */

@Controller
@RequestMapping(value = "/login")
@SessionAttributes(types = {UserPropertyStaffEntity.class, String.class}, value = {"propertystaff", "menulist", "secanViewlist", "tabValue"})

public class LoginController {
    @Autowired
    UserPropertystaffService userPropertystaffService;

    @RequestMapping(value = "/loginCheck.html", method = RequestMethod.POST)
    public ModelAndView loginCheck(HttpServletRequest request, HttpServletResponse response) {
        String userid = request.getParameter("userId");
        String old_pwd = request.getParameter("password");
        String pwd = PasswordEncode.digestPassword(old_pwd);
        ModelAndView mav;
        mav = new ModelAndView("/main/main");
        try {

            //查询人员
            UserPropertyStaffEntity propertystaff = new UserPropertyStaffEntity();
            propertystaff.setStaffId(userid);
            propertystaff.setUserName(userid);
            propertystaff.setPassword(pwd);
            UserPropertyStaffEntity rePropertystaff = userPropertystaffService.CheckStaffByIdAPwd(propertystaff);


            if (rePropertystaff != null) {
                rePropertystaff.setPassword(""); ///置空密码
                mav.addObject("propertystaff", rePropertystaff);


                //-----------查询角色权限 ---取出的是第一級

                List<RoleViewmodelEntity> oldlistone = userPropertystaffService.getViewListByUserId("property", rePropertystaff.getStaffId());
                //獲取除一級外的其他菜單
                List<RoleViewmodelEntity> oldlisttwo = userPropertystaffService.getViewListOtherByUserId("property", rePropertystaff.getStaffId());

                List<Viewmodel> viewmodelList = new ArrayList<>();
                List<Viewmodel> secanViewlist = new ArrayList<>();

                List<Viewmodel> view1 = new ArrayList<>();
                List<Viewmodel> view2 = new ArrayList<>();
                List<Viewmodel> view3 = new ArrayList<>();
                List<Viewmodel> view4 = new ArrayList<>();
                List<Viewmodel> view5 = new ArrayList<>();
                List<Viewmodel> view6 = new ArrayList<>();

                if (oldlistone != null && oldlistone.size() > 0) {

                    for (RoleViewmodelEntity viewone : oldlistone) {
                        Viewmodel viewmodelnew = new Viewmodel();
                        viewmodelnew.setChildFlag(viewone.getChildFlag());
                        viewmodelnew.setMenuDescription(viewone.getMenuDescription());
                        viewmodelnew.setMenuId(viewone.getMenuId());
                        viewmodelnew.setMenulevel(viewone.getMenulevel());
                        viewmodelnew.setMenuName(viewone.getMenuName());
                        viewmodelnew.setMenuorder(viewone.getMenuorder());
                        viewmodelnew.setMenuState(viewone.getMenuState());
                        viewmodelnew.setOperator(viewone.getOperator());
                        viewmodelnew.setOwner(viewone.getOwner());
                        viewmodelnew.setParantmenuid(viewone.getParantmenuid());
                        viewmodelnew.setRunscript(viewone.getRunscript());
                        viewmodelnew.setBelong(viewone.getBelong());
                        viewmodelList.add(viewmodelnew);
                        switch (viewone.getBelong()) {
                            case "1":
                                view1.add(viewmodelnew);
                            case "2":
                                view2.add(viewmodelnew);
                            case "3":
                                view3.add(viewmodelnew);
                            case "4":
                                view4.add(viewmodelnew);
                            case "5":
                                view5.add(viewmodelnew);
                            case "6":
                                view6.add(viewmodelnew);
                        }
                    }

                    if (oldlisttwo != null && oldlisttwo.size() > 0) {
                        for (RoleViewmodelEntity viewone2 : oldlisttwo) {
                            Viewmodel viewmodelnew2 = new Viewmodel();
                            viewmodelnew2.setChildFlag(viewone2.getChildFlag());
                            viewmodelnew2.setMenuDescription(viewone2.getMenuDescription());
                            viewmodelnew2.setMenuId(viewone2.getMenuId());
                            viewmodelnew2.setMenulevel(viewone2.getMenulevel());
                            viewmodelnew2.setMenuName(viewone2.getMenuName());
                            viewmodelnew2.setMenuorder(viewone2.getMenuorder());
                            viewmodelnew2.setMenuState(viewone2.getMenuState());
                            viewmodelnew2.setOperator(viewone2.getOperator());
                            viewmodelnew2.setOwner(viewone2.getOwner());
                            viewmodelnew2.setParantmenuid(viewone2.getParantmenuid());
                            viewmodelnew2.setRunscript(viewone2.getRunscript());
                            secanViewlist.add(viewmodelnew2);
                        }
                    }
                }

                mav.addObject("menulist", viewmodelList);
                mav.addObject("secanViewlist", secanViewlist);

                String tabValue = "";

                if (view1 != null && view1.size() > 0) {
                    tabValue = "1";
                } else if (view2 != null && view2.size() > 0) {
                    tabValue = "2";
                } else if (view3 != null && view3.size() > 0) {
                    tabValue = "3";
                } else if (view4 != null && view4.size() > 0) {
                    tabValue = "4";
                } else if (view5 != null && view5.size() > 0) {
                    tabValue = "5";
                } else if (view6 != null && view6.size() > 0) {
                    tabValue = "6";
                }

                Cookie cookie = new Cookie("tabValue", tabValue);
                cookie.setPath("/");
                cookie.setDomain(request.getServerName());
                cookie.setMaxAge(3600 * 24 * 30);
                response.addCookie(cookie);
                response.setHeader("P3P", "CP='CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR'");
            } else {
                mav = new ModelAndView("/error/500");
                mav.addObject("errormsg", "用户名或密码错误");
            }
            return mav;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 菜单session传递
     */
    @RequestMapping(value = "/getViewSession", method = RequestMethod.GET)
    public ApiResult getViewSession(@ModelAttribute("menulist") List<Viewmodel> viewmodels) {
        return new SuccessApiResult(viewmodels);
    }

    /**
     * 保存tab页active值
     */
    @RequestMapping(value = "saveTab", method = RequestMethod.GET)
    public ApiResult saveTab(@RequestParam(value = "tabValue") String tabValue, HttpServletRequest request, HttpServletResponse response) {

        Cookie cookie = new Cookie("tabValue", tabValue);
        cookie.setPath("/");
        cookie.setDomain(request.getServerName());
        cookie.setMaxAge(3600 * 24 * 30);
        response.addCookie(cookie);
        response.setHeader("P3P", "CP='CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR'");
        return new SuccessApiResult();
    }

    /**
     * 获取tab页active值
     */
    @RequestMapping(value = "getTab", method = RequestMethod.GET)
    public ApiResult getTabValue(@CookieValue(value = "tabValue", required = false) String tabValue) {
        System.out.println(tabValue);
        return new SuccessApiResult(tabValue);
    }
}
