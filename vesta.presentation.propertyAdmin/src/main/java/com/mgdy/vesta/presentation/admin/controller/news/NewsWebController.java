package com.mgdy.vesta.presentation.admin.controller.news;

import com.mgdy.vesta.application.house.inf.HouseService;
import com.mgdy.vesta.application.inf.UserPropertystaffService;
import com.mgdy.vesta.application.messageComment.DTO.MessageCommentDTO;
import com.mgdy.vesta.application.messageComment.inf.MessageCommentService;
import com.mgdy.vesta.application.news.inf.NewsService;
import com.mgdy.vesta.application.video.DTO.VideoDTO;
import com.mgdy.vesta.application.video.inf.VideoService;
import com.mgdy.vesta.application.viewSpot.inf.ViewSpotService;
import com.mgdy.vesta.common.restHTTPResult.ApiResult;
import com.mgdy.vesta.common.restHTTPResult.ErrorApiResult;
import com.mgdy.vesta.common.restHTTPResult.SuccessApiResult;
import com.mgdy.vesta.domain.model.UserTokenEntity;
import org.apache.bcel.generic.RET;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Jason on 2017/7/5.
 */
@RestController
@RequestMapping(value = "webNews")
public class NewsWebController {
    @Autowired
    private NewsService newsService;
    @Autowired
    private MessageCommentService messageCommentService;
    @Autowired
    private HouseService houseService;
    @Autowired
    private ViewSpotService viewSpotService;
    @Autowired
    private UserPropertystaffService userPropertystaffService;
    @Autowired
    private VideoService videoService;
    @Value("${IMAGE_SERVER_URL}")
    private String IMAGE_SERVER_URL;


    /**
     * 获取新闻信息
     *
     * @return
     */
    @RequestMapping(value = "/getWebNewsList", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public ApiResult getWebNewsList() {
        return newsService.getWebNewsList();
    }

    /**
     * 根据id查询新闻信息
     */
    @RequestMapping(value = "/getWebNewsInfoById/{id}", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public ApiResult getNewsInfoById(@PathVariable("id") String id) {
        return newsService.getWebNewsInfoById(id);
    }

    /**
     * 添加评论
     *
     * @return
     */
    @RequestMapping(value = "/addMessageComment", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public ApiResult addMessageComment(@RequestBody MessageCommentDTO messageCommentDTO) {
        return messageCommentService.addMessageComment(messageCommentDTO);
    }

    /**
     * 获取评论信息
     *
     * @return
     */
    @RequestMapping(value = "/getMessageCommentList", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public ApiResult getMessageCommentList() {
        return messageCommentService.getMessageCommentList();
    }

    /**
     * 获取房屋信息
     *
     * @return
     */
    @RequestMapping(value = "/getHouseInfo", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public ApiResult getHouseInfo() {
        return houseService.getHouseInfoList();
    }

    /**
     * 根据id查询房屋信息
     */
    @RequestMapping(value = "/getHouseInfoById/{id}", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public ApiResult getHouseInfoById(@PathVariable("id") String id) {
        return houseService.getHouseDetailInfoById(id);
    }

    /**
     * 获取景点展示
     *
     * @return
     */
    @RequestMapping(value = "/getViewSpotInfo", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public ApiResult getViewSpotInfo() {
        return viewSpotService.getViewSpotInfo();
    }

    /**
     * 获取团队信息
     *
     * @return
     */
    @RequestMapping(value = "/getTeamInfoList", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public ApiResult getTeamInfoList() {
        return userPropertystaffService.getTeamInfoList();
    }

    /**
     * 获取游客信息
     *
     * @return
     */
    @RequestMapping(value = "/getTouristInfoList", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public ApiResult getTouristInfoList() {
        return userPropertystaffService.getTouristInfoList();
    }

    /**
     * 根据id查询新闻信息
     */
    @RequestMapping(value = "/getSortDetailByIdAPI/{id}", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public ApiResult getViewSpotInfoById(@PathVariable("id") String id) {
        return viewSpotService.getViewSpotInfoByIdAPI(id);
    }


    @RequestMapping(value = "/uploadFile", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public ApiResult uploadFile(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        return videoService.uploadFile(file, request, response, IMAGE_SERVER_URL);
    }

    @RequestMapping(value = "/upload", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public ApiResult upload(@RequestBody VideoDTO videoDTO) {
        return videoService.upload(videoDTO);
    }

    @RequestMapping(value = "/getVideos", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public ApiResult getVideos() {
        return videoService.getVideos();
    }

    @RequestMapping(value = "/loginWechat/{code}", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public ApiResult loginByWechat(@PathVariable("code") String code, HttpServletRequest request, HttpServletResponse response) {
        try {
            //2、验证code是否存在
            if (code == null || "".equals(code.trim())) {
                System.out.println("----------loginWechat---------20201");
                return new ErrorApiResult(20201, "Wechat code is last!");

            } else {
                System.out.println("----------loginWechat---------checktoken");
                //3、根据token获取用户
                UserTokenEntity userToken = userPropertystaffService.GetLoginByWeChatCode(code);
                if (userToken != null) {

                    System.out.println("----------loginWechat---------checktoken-------success---" + userToken.getTokenId());
                    //将token注册并返回给前端
                    Cookie cookie = new Cookie("vestaToken", userToken.getTokenId());
                    cookie.setPath("/");
                    System.out.println("----------loginWechat---------checktoken-------success---" + userToken.getTokenId());

                    cookie.setDomain(request.getServerName());
                    cookie.setMaxAge(3600 * 24 * 30);
                    response.addCookie(cookie);
                    response.setHeader("P3P", "CP='CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR'");


                    ModelMap result = new ModelMap();
                    result.addAttribute("vestaToken", userToken.getTokenId());
                    return new SuccessApiResult(result);
                } else {
                    return new ErrorApiResult(20202, "code is invalid!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorApiResult(20203, "Login failed.");
        }
    }
}
