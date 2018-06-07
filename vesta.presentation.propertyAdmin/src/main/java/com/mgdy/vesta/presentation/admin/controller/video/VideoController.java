package com.mgdy.vesta.presentation.admin.controller.video;

import com.mgdy.vesta.application.video.DTO.VideoReturnDTO;
import com.mgdy.vesta.application.video.inf.VideoService;
import com.mgdy.vesta.domain.model.UserPropertyStaffEntity;
import com.mgdy.vesta.taglib.page.WebPage;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;
import java.util.Map;

/**
 * Created by Jiazefeng on 2018/6/7.
 */
@Controller
@RequestMapping(value = "/video")
@SessionAttributes(types = {UserPropertyStaffEntity.class, String.class}, value = {"propertystaff", "menulist", "secanViewlist"})
public class VideoController {
    @Autowired
    private VideoService videoService;
    @RequestMapping(value = "/getVideoList.html", method = RequestMethod.GET)
    public String getVideoList(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaff, Model model, WebPage webPage) {

        List<VideoReturnDTO> videoList = videoService.getVideoList(webPage, userPropertystaff);
        model.addAttribute("videoList", videoList);
        return "/integratedManagement/video/VideoList";
    }
    @RequestMapping(value = "/toReleaseOrCancel")
    public Map<String, Object> toReleaseOrCancel(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                         String videoId, String status) {
        Map<String, Object> resultMap = new HashedMap();
        boolean flag = videoService.toReleaseOrCancel(videoId,status, userPropertystaffEntity);
        if (flag) {
            resultMap.put("error", 0);
        } else {
            resultMap.put("error", -1);
        }
        return resultMap;
    }
}
