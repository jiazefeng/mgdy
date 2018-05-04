package com.mgdy.vesta.application.messageComment.inf;

import com.mgdy.vesta.application.messageComment.DTO.MessageCommentDTO;
import com.mgdy.vesta.common.restHTTPResult.ApiResult;
import com.mgdy.vesta.domain.model.UserPropertyStaffEntity;
import com.mgdy.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by Jason on 2017/7/11.
 */
public interface MessageCommentService {
    /**
     * 添加评论信息
     *
     * @param messageCommentDTO
     * @return
     */
    ApiResult addMessageComment(MessageCommentDTO messageCommentDTO);

    /**
     * 获取评论信息
     *
     * @return
     */
    ApiResult getMessageCommentList();

    /**
     * 分页获取留言评论
     *
     * @param messageCommentDTO
     * @param webPage
     * @param userPropertystaff
     * @return
     */
    List<MessageCommentDTO> getMessageCommentInfoList(MessageCommentDTO messageCommentDTO, WebPage webPage, UserPropertyStaffEntity userPropertystaff);

    /**
     * 根据ID获取信息
     *
     * @param mcId
     * @return
     */
    MessageCommentDTO getMessageCommentInfoById(String mcId);

    /**
     * 编辑信息
     *
     * @param messageCommentDTO
     */
    void updateInfo(MessageCommentDTO messageCommentDTO);

    /**
     * 删除信息
     *
     * @param mcId
     */
    void deleteInfoById(String mcId);
}
