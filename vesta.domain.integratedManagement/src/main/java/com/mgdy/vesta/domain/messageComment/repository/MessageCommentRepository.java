package com.mgdy.vesta.domain.messageComment.repository;

import com.mgdy.vesta.domain.messageComment.model.MessageCommentEntity;
import com.mgdy.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * Created by Jason on 2017/7/11.
 */
public interface MessageCommentRepository {
    /**
     * 添加评论信息
     *
     * @param messageCommentEntity
     */
    void addMessageComment(MessageCommentEntity messageCommentEntity);

    /**
     * 获取评论信息
     *
     * @return
     */
    List<MessageCommentEntity> getMessageCommentList();

    /**
     * 分页获取留言信息
     *
     * @param webPage
     * @param map
     * @return
     */
    List<MessageCommentEntity> getMessageCommentInfoList(WebPage webPage, Map map);

    /**
     * 根据ID 获取信息
     *
     * @param mcId
     * @return
     */
    MessageCommentEntity getMessageCommentInfoById(String mcId);

    /**
     * 编辑信息
     *
     * @param messageCommentEntity
     */
    void updateInfo(MessageCommentEntity messageCommentEntity);

    /**
     * 删除信息
     *
     * @param messageCommentEntity
     */
    void deleteInfoById(MessageCommentEntity messageCommentEntity);
}
