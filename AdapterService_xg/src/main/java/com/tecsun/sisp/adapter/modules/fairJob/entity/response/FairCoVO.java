package com.tecsun.sisp.adapter.modules.fairJob.entity.response;

import java.util.List;

/**招聘会企业类
 * Created by Administrator on 2017/11/2 0002.
 */
public class FairCoVO {
    private String id;              //企业ID
    private String name;            //企业名称
    private String fairId;          //招聘会ID
    private String contact;         //联系人
    private String tel;             //联系电话
    private String phone;          //手机
    private String address;         //企业地址
    private String summary;         //企业简介
    private List<FairPositionVo> fairPositionVos;    //岗位列表
    private String fairName;        //招聘会名称

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getFairId() {
        return fairId;
    }

    public void setFairId(String fairId) {
        this.fairId = fairId;
    }

    public String getContact() { return contact; }

    public void setContact(String contact) { this.contact = contact; }

    public String getTel() { return tel; }

    public void setTel(String tel) { this.tel = tel; }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address;}

    public String getSummary() { return summary; }

    public void setSummary(String summary) { this.summary = summary; }

    public List<FairPositionVo> getFairPositionVos() {
        return fairPositionVos;
    }

    public void setFairPositionVos(List<FairPositionVo> fairPositionVos) {
        this.fairPositionVos = fairPositionVos;
    }

    public String getFairName() { return fairName; }

    public void setFairName(String fairName) { this.fairName = fairName; }
}
