package com.tecsun.sisp.adapter.modules.intelligence.entity.response;

import java.util.List;

/**捷通华声-单一回答
 * Created by danmeng on 2017/8/29.
 */
public class SingleNode {
    private long standardQuestionId;//标准问题ID
    private String standardQuestion;//标准问题
    private int isRichText;//是否图文 0标准问答，1图文
    private String answerMsg;//标准问答问题回复
    private List<NewsNode> list;//图文回复
    private double score;//得分

    public String getStandardQuestion() {
        return standardQuestion;
    }

    public void setStandardQuestion(String standardQuestion) {
        this.standardQuestion = standardQuestion;
    }

    public long getStandardQuestionId() {
        return standardQuestionId;
    }

    public void setStandardQuestionId(long standardQuestionId) {
        this.standardQuestionId = standardQuestionId;
    }

    public int getIsRichText() {
        return isRichText;
    }

    public void setIsRichText(int isRichText) {
        this.isRichText = isRichText;
    }

    public String getAnswerMsg() {
        return answerMsg;
    }

    public void setAnswerMsg(String answerMsg) {
        this.answerMsg = answerMsg;
    }

    public List<NewsNode> getList() {
        return list;
    }

    public void setList(List<NewsNode> list) {
        this.list = list;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
