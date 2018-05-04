package com.mgdy.vesta.domain.messageComment.model;

import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.util.Date;

/**
 * 留言评论
 * Created by Jason on 2017/7/11.
 */
@Entity
@Table(name = "md_message_comment")
public class MessageCommentEntity {
    private String mcId;//id
    private String mcUserName;//用户名称
    private String mcContactWay;//联系方式
    private String mcGrade;//等级
    private String mcDescribe;//描述
    private Date createOn;//时间
    private String reply;//答复

    @Id
    @Column(name = "MC_ID", length = 32)
    public String getMcId() {
        return mcId;
    }

    public void setMcId(String mcId) {
        this.mcId = mcId;
    }

    @Basic
    @Column(name = "MC_USER_NAME", length = 32)
    public String getMcUserName() {
        return mcUserName;
    }

    public void setMcUserName(String mcUserName) {
        this.mcUserName = mcUserName;
    }

    @Basic
    @Column(name = "MC_CONTACT_WAY", length = 32)
    public String getMcContactWay() {
        return mcContactWay;
    }

    public void setMcContactWay(String mcContactWay) {
        this.mcContactWay = mcContactWay;
    }

    @Basic
    @Column(name = "MC_GRADE", length = 32)
    public String getMcGrade() {
        return mcGrade;
    }

    public void setMcGrade(String mcGrade) {
        this.mcGrade = mcGrade;
    }

    @Basic
    @Column(name = "MC_DESCRIBE", length = 2000)
    public String getMcDescribe() {
        return mcDescribe;
    }

    public void setMcDescribe(String mcDescribe) {
        this.mcDescribe = mcDescribe;
    }

    @Basic
    @Column(name = "CREATE_ON")
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    @Basic
    @Column(name = "MC_REPLY", length = 2000)
    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}
