package com.mgdy.vesta.application.messageComment.impl;

import com.mgdy.vesta.application.messageComment.DTO.MessageCommentDTO;
import com.mgdy.vesta.application.messageComment.inf.MessageCommentService;
import com.mgdy.vesta.common.restHTTPResult.ApiResult;
import com.mgdy.vesta.common.restHTTPResult.SuccessApiResult;
import com.mgdy.vesta.domain.messageComment.model.MessageCommentEntity;
import com.mgdy.vesta.domain.messageComment.repository.MessageCommentRepository;
import com.mgdy.vesta.domain.model.UserPropertyStaffEntity;
import com.mgdy.vesta.taglib.page.WebPage;
import com.mgdy.vesta.utility.DateUtils;
import com.mgdy.vesta.utility.IdGen;
import com.mgdy.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.*;

/**
 * Created by 90544 on 2017/7/11.
 */
@Service
public class MessageCommentServiceImpl implements MessageCommentService {
    @Autowired
    private MessageCommentRepository messageCommentRepository;

    @Override
    public ApiResult addMessageComment(MessageCommentDTO messageCommentDTO) {
        ModelMap modelMap = new ModelMap();
        if (messageCommentDTO != null) {
            MessageCommentEntity messageCommentEntity = new MessageCommentEntity();
            messageCommentEntity.setMcId(IdGen.uuid());
            messageCommentEntity.setMcUserName(messageCommentDTO.getMcUserName());
            messageCommentEntity.setMcContactWay(messageCommentDTO.getMcContactWay());
            messageCommentEntity.setMcGrade(messageCommentDTO.getMcGrade());
            messageCommentEntity.setMcDescribe(messageCommentDTO.getMcDescribe());
            messageCommentEntity.setCreateOn(new Date());
            messageCommentRepository.addMessageComment(messageCommentEntity);

            modelMap.addAttribute("success", "非常感谢您给的宝贵的建议！");
        }
        return new SuccessApiResult(modelMap);
    }

    @Override
    public ApiResult getMessageCommentList() {
        List<MessageCommentEntity> messageCommentEntities = messageCommentRepository.getMessageCommentList();
        List<MessageCommentDTO> messageCommentDTOS = new ArrayList<MessageCommentDTO>();
        if (messageCommentEntities != null && messageCommentEntities.size() > 0) {
            for (MessageCommentEntity messageCommentEntity : messageCommentEntities) {
                MessageCommentDTO messageCommentDTO = new MessageCommentDTO();
                messageCommentDTO.setMcUserName(messageCommentEntity.getMcUserName());
                messageCommentDTO.setMcDescribe(messageCommentEntity.getMcDescribe());
                messageCommentDTO.setMcGrade(messageCommentEntity.getMcGrade());
                messageCommentDTO.setCreateOn(DateUtils.format(messageCommentEntity.getCreateOn(), "yyyy-MM-dd HH:mm:ss"));
                messageCommentDTO.setReply(messageCommentEntity.getReply());
                messageCommentDTOS.add(messageCommentDTO);
            }
        }
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("messageComment", messageCommentDTOS);
        return new SuccessApiResult(modelMap);
    }

    @Override
    public List<MessageCommentDTO> getMessageCommentInfoList(MessageCommentDTO messageCommentDTO, WebPage webPage, UserPropertyStaffEntity userPropertystaff) {
        Map map = new HashMap();
        List<MessageCommentEntity> messageCommentEntityList = messageCommentRepository.getMessageCommentInfoList(webPage, map);
        List<MessageCommentDTO> messageCommentDTOList = new ArrayList<MessageCommentDTO>();
        if (messageCommentEntityList != null && messageCommentEntityList.size() > 0) {
            for (MessageCommentEntity messageCommentEntity : messageCommentEntityList) {
                MessageCommentDTO messageCommentDTO1 = new MessageCommentDTO();
                messageCommentDTO1.setMcId(messageCommentEntity.getMcId());
                messageCommentDTO1.setCreateOn(DateUtils.format(messageCommentEntity.getCreateOn(), "yyyy-MM-dd HH:mm:ss"));
                messageCommentDTO1.setMcUserName(messageCommentEntity.getMcUserName());
                messageCommentDTO1.setMcContactWay(messageCommentEntity.getMcContactWay());
                if(!StringUtil.isEmpty(messageCommentEntity.getMcGrade())){
                    messageCommentDTO1.setMcGrade(messageCommentEntity.getMcGrade());
                }else {
                    messageCommentDTO1.setMcGrade("0");
                }
                messageCommentDTO1.setMcDescribe(messageCommentEntity.getMcDescribe());
                messageCommentDTO1.setReply(messageCommentEntity.getReply());
                messageCommentDTOList.add(messageCommentDTO1);
            }
        }
        return messageCommentDTOList;
    }

    @Override
    public MessageCommentDTO getMessageCommentInfoById(String mcId) {
        MessageCommentEntity messageCommentEntity = messageCommentRepository.getMessageCommentInfoById(mcId);
        MessageCommentDTO messageCommentDTO = new MessageCommentDTO();
        if (messageCommentEntity != null) {
            messageCommentDTO.setMcId(messageCommentEntity.getMcId());
            messageCommentDTO.setCreateOn(DateUtils.format(messageCommentEntity.getCreateOn(), "yyyy-MM-dd HH:mm:ss"));
            messageCommentDTO.setMcUserName(messageCommentEntity.getMcUserName());
            messageCommentDTO.setMcContactWay(messageCommentEntity.getMcContactWay());
            if(!StringUtil.isEmpty(messageCommentEntity.getMcGrade())){
                messageCommentDTO.setMcGrade(messageCommentEntity.getMcGrade());
            }else {
                messageCommentDTO.setMcGrade("0");
            }
            messageCommentDTO.setMcDescribe(messageCommentEntity.getMcDescribe());
            messageCommentDTO.setReply(messageCommentEntity.getReply());
        }
        return messageCommentDTO;
    }

    @Override
    public void updateInfo(MessageCommentDTO messageCommentDTO) {
        MessageCommentEntity messageCommentEntity = messageCommentRepository.getMessageCommentInfoById(messageCommentDTO.getMcId());
        if(messageCommentEntity != null){
            messageCommentEntity.setReply(messageCommentDTO.getReply());
            messageCommentRepository.updateInfo(messageCommentEntity);
        }
    }

    @Override
    public void deleteInfoById(String mcId) {
        MessageCommentEntity messageCommentEntity = messageCommentRepository.getMessageCommentInfoById(mcId);
        if(messageCommentEntity != null){
            messageCommentRepository.deleteInfoById(messageCommentEntity);
        }
    }
}
