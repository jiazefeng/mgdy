package com.mgdy.vesta.presentation.admin.controller.leaveComments;

import com.mgdy.vesta.application.messageComment.DTO.MessageCommentDTO;
import com.mgdy.vesta.application.messageComment.inf.MessageCommentService;
import com.mgdy.vesta.common.restHTTPResult.ApiResult;
import com.mgdy.vesta.common.restHTTPResult.SuccessApiResult;
import com.mgdy.vesta.domain.model.UserPropertyStaffEntity;
import com.mgdy.vesta.taglib.page.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 留言评论管理
 * Created by Jason on 2017/7/4.
 */
@Controller
@RequestMapping(value = "/leave")
@SessionAttributes(types = {UserPropertyStaffEntity.class, String.class}, value = {"propertystaff", "menulist", "secanViewlist"})
public class LeaveCommentsController {
    @Autowired
    private MessageCommentService messageCommentService;

    /**
     * 分页获取留言评论信息
     *
     * @param userPropertystaff
     * @param messageCommentDTO
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/getMessageCommentInfoList.html", method = RequestMethod.GET)
    public String getMessageCommentInfoList(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaff, @Valid MessageCommentDTO messageCommentDTO, Model model, WebPage webPage) {

        List<MessageCommentDTO> messageCommentDTOS = messageCommentService.getMessageCommentInfoList(messageCommentDTO, webPage, userPropertystaff);
        model.addAttribute("messageCommentList", messageCommentDTOS);
        model.addAttribute("messageComment", messageCommentDTO);
        return "/integratedManagement/messageComment/MessageComentList";
    }

    /**
     * 根据Id获取信息
     *
     * @return
     */
    @RequestMapping(value = "/getMessageCommentInfoById")
    public ApiResult getMessageCommentInfoById(@RequestParam(value = "mcId") String mcId) {
        MessageCommentDTO messageCommentDTO = messageCommentService.getMessageCommentInfoById(mcId);
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("messageCommentInfo", messageCommentDTO);
        return new SuccessApiResult(modelMap);
    }

    /**
     * 编辑评论
     *
     * @return
     */
    @RequestMapping(value = "/updateMessageComment", method = RequestMethod.POST)
    public String updateMessageComment(@Valid MessageCommentDTO messageCommentDTO, Model model) {
        messageCommentService.updateInfo(messageCommentDTO);
        return "redirect:../leave/getMessageCommentInfoList.html";
    }
    /**
     * 删除信息
     *
     * @return
     */
    @RequestMapping(value = "/deleteInfoById")
    public String deleteInfoById(@RequestParam(value = "mcId") String mcId) {
         messageCommentService.deleteInfoById(mcId);
        return "redirect:../leave/getMessageCommentInfoList.html";
    }
}
