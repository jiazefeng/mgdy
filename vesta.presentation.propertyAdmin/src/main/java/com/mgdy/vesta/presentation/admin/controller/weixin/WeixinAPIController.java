package com.mgdy.vesta.presentation.admin.controller.weixin;

import com.mgdy.vesta.application.DTO.WechatDTO;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Created by Jason on 2017/7/5.
 */
@RestController
@RequestMapping(value = "weixinapi")
public class WeixinAPIController {
    @Autowired
    private NewsService newsService;
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
    @RequestMapping(value = "/getNewsList", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public ApiResult getWebNewsList() {
        return newsService.getWebNewsList();
    }

    /**
     * 根据id查询新闻信息
     */
    @RequestMapping(value = "/getNewsInfoById/{id}", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public ApiResult getNewsInfoById(@PathVariable("id") String id) {
        return newsService.getWebNewsInfoById(id);
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

    @RequestMapping(value = "/getVideosByUserId/{userId}", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public ApiResult getVideosByUserId(@PathVariable("userId") String userId) {
        return videoService.getVideosByUserId(userId);
    }

    @RequestMapping(value = "/loginWechat/{code}", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public ApiResult loginByWechat(@PathVariable("code") String code, HttpServletRequest request, HttpServletResponse response, @Valid WechatDTO wechatDTO) {
        try {
            //2、验证code是否存在
            if (code == null || "".equals(code.trim())) {
                System.out.println("----------loginWechat---------20201");
                return new ErrorApiResult(20201, "Wechat code is last!");
            } else {
                return userPropertystaffService.GetLoginByWeChatCode(code, wechatDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorApiResult(20203, "Login failed.");
        }
    }
}
