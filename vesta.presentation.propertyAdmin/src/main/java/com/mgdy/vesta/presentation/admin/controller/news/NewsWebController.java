package com.mgdy.vesta.presentation.admin.controller.news;

import com.mgdy.vesta.application.house.inf.HouseService;
import com.mgdy.vesta.application.inf.UserPropertystaffService;
import com.mgdy.vesta.application.messageComment.DTO.MessageCommentDTO;
import com.mgdy.vesta.application.messageComment.inf.MessageCommentService;
import com.mgdy.vesta.application.news.inf.NewsService;
import com.mgdy.vesta.application.viewSpot.inf.ViewSpotService;
import com.mgdy.vesta.common.restHTTPResult.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
