package com.mgdy.vesta.application.messageComment.DTO;

/**
 * Created by Jason on 2017/7/11.
 */
public class MessageCommentDTO {
    private String mcId;//id
    private String mcUserName;//用户名称
    private String mcContactWay;//联系方式
    private String mcGrade;//等级
    private String mcDescribe;//描述
    private String createOn;
    private String reply;//答复

    public String getMcId() {
        return mcId;
    }

    public void setMcId(String mcId) {
        this.mcId = mcId;
    }

    public String getMcUserName() {
        return mcUserName;
    }

    public void setMcUserName(String mcUserName) {
        this.mcUserName = mcUserName;
    }

    public String getMcContactWay() {
        return mcContactWay;
    }

    public void setMcContactWay(String mcContactWay) {
        this.mcContactWay = mcContactWay;
    }

    public String getMcGrade() {
        return mcGrade;
    }

    public void setMcGrade(String mcGrade) {
        this.mcGrade = mcGrade;
    }

    public String getMcDescribe() {
        return mcDescribe;
    }

    public void setMcDescribe(String mcDescribe) {
        this.mcDescribe = mcDescribe;
    }

    public String getCreateOn() {
        return createOn;
    }

    public void setCreateOn(String createOn) {
        this.createOn = createOn;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}
