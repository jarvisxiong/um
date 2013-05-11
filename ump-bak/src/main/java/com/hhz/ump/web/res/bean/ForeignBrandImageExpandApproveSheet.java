package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/***
 * 对外品牌形象推广审批表
 * @author liwei
 * 2012-07-27
 */
public class ForeignBrandImageExpandApproveSheet extends BaseTemplate {

	// 标题
	private String title;
	// 单位/部门
	private String units;	
	// 推广类型#参加论坛
	private String expandType1;	
	// 推广类型#申报奖项
	private String expandType2;	
	// 推广类型#媒体采访
	private String expandType3;
	// 费用
	private String cost;
	
	/***
	 * 论坛/奖项情况
	 */
	// 日期
	private String date;
	// 地点
	private String place;
	// 主办方
	private String orgnizer;
	// 出席人
	private String attendee;
	// 活动介绍
	private String activityIntroduce;
	// 奖项名称
	private String awardsName;
	// 获奖对象
	private String awardsTarget;
	
	/***
	 * 媒体采访情况
	 */
	// 媒体名称
	private String mediaName;
	// 采访对象
	private String coverTarget;
	// 刊登栏目
	private String publishColumn;
	// 媒体联系人
	private String mediaLinkMan;
	// 职务
	private String position;
	// 采访形式#面访
	private String covertMode1;
	// 采访形式#电话
	private String covertMode2;
	// 采访形式#邮件
	private String covertMode3;
	// 手机
	private String phone;
	// 采访日期
	private String coverDate;
	// 座机
	private String telephone;
	// 采访耗时
	private String coverTime;
	// Email
	private String email;
	// 刊登日期
	private String publishDate;
	// 采访主题
	private String coverSubject;
	// 采访提纲
	private String coverOutLine;
	// 拍摄需求#有
	private String shootNeed1;
	// 拍摄需求#无
	private String shootNeed2;
	// 媒体介绍
	private String mediaIntroduce;
	
	
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the units
	 */
	public String getUnits() {
		return units;
	}
	/**
	 * @param units the units to set
	 */
	public void setUnits(String units) {
		this.units = units;
	}
	/**
	 * @return the expandType1
	 */
	public String getExpandType1() {
		return expandType1;
	}
	/**
	 * @param expandType1 the expandType1 to set
	 */
	public void setExpandType1(String expandType1) {
		this.expandType1 = expandType1;
	}
	/**
	 * @return the expandType2
	 */
	public String getExpandType2() {
		return expandType2;
	}
	/**
	 * @param expandType2 the expandType2 to set
	 */
	public void setExpandType2(String expandType2) {
		this.expandType2 = expandType2;
	}
	/**
	 * @return the expandType3
	 */
	public String getExpandType3() {
		return expandType3;
	}
	/**
	 * @param expandType3 the expandType3 to set
	 */
	public void setExpandType3(String expandType3) {
		this.expandType3 = expandType3;
	}
	/**
	 * @return the cost
	 */
	public String getCost() {
		return cost;
	}
	/**
	 * @param cost the cost to set
	 */
	public void setCost(String cost) {
		this.cost = cost;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the place
	 */
	public String getPlace() {
		return place;
	}
	/**
	 * @param place the place to set
	 */
	public void setPlace(String place) {
		this.place = place;
	}
	/**
	 * @return the orgnizer
	 */
	public String getOrgnizer() {
		return orgnizer;
	}
	/**
	 * @param orgnizer the orgnizer to set
	 */
	public void setOrgnizer(String orgnizer) {
		this.orgnizer = orgnizer;
	}
	/**
	 * @return the attendee
	 */
	public String getAttendee() {
		return attendee;
	}
	/**
	 * @param attendee the attendee to set
	 */
	public void setAttendee(String attendee) {
		this.attendee = attendee;
	}
	/**
	 * @return the activityIntroduce
	 */
	public String getActivityIntroduce() {
		return activityIntroduce;
	}
	/**
	 * @param activityIntroduce the activityIntroduce to set
	 */
	public void setActivityIntroduce(String activityIntroduce) {
		this.activityIntroduce = activityIntroduce;
	}
	/**
	 * @return the awardsName
	 */
	public String getAwardsName() {
		return awardsName;
	}
	/**
	 * @param awardsName the awardsName to set
	 */
	public void setAwardsName(String awardsName) {
		this.awardsName = awardsName;
	}
	/**
	 * @return the awardsTarget
	 */
	public String getAwardsTarget() {
		return awardsTarget;
	}
	/**
	 * @param awardsTarget the awardsTarget to set
	 */
	public void setAwardsTarget(String awardsTarget) {
		this.awardsTarget = awardsTarget;
	}
	/**
	 * @return the mediaName
	 */
	public String getMediaName() {
		return mediaName;
	}
	/**
	 * @param mediaName the mediaName to set
	 */
	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}
	/**
	 * @return the coverTarget
	 */
	public String getCoverTarget() {
		return coverTarget;
	}
	/**
	 * @param coverTarget the coverTarget to set
	 */
	public void setCoverTarget(String coverTarget) {
		this.coverTarget = coverTarget;
	}
	/**
	 * @return the publishColumn
	 */
	public String getPublishColumn() {
		return publishColumn;
	}
	/**
	 * @param publishColumn the publishColumn to set
	 */
	public void setPublishColumn(String publishColumn) {
		this.publishColumn = publishColumn;
	}
	/**
	 * @return the mediaLinkMan
	 */
	public String getMediaLinkMan() {
		return mediaLinkMan;
	}
	/**
	 * @param mediaLinkMan the mediaLinkMan to set
	 */
	public void setMediaLinkMan(String mediaLinkMan) {
		this.mediaLinkMan = mediaLinkMan;
	}
	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}
	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	/**
	 * @return the covertMode1
	 */
	public String getCovertMode1() {
		return covertMode1;
	}
	/**
	 * @param covertMode1 the covertMode1 to set
	 */
	public void setCovertMode1(String covertMode1) {
		this.covertMode1 = covertMode1;
	}
	/**
	 * @return the covertMode2
	 */
	public String getCovertMode2() {
		return covertMode2;
	}
	/**
	 * @param covertMode2 the covertMode2 to set
	 */
	public void setCovertMode2(String covertMode2) {
		this.covertMode2 = covertMode2;
	}
	/**
	 * @return the covertMode3
	 */
	public String getCovertMode3() {
		return covertMode3;
	}
	/**
	 * @param covertMode3 the covertMode3 to set
	 */
	public void setCovertMode3(String covertMode3) {
		this.covertMode3 = covertMode3;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the coverDate
	 */
	public String getCoverDate() {
		return coverDate;
	}
	/**
	 * @param coverDate the coverDate to set
	 */
	public void setCoverDate(String coverDate) {
		this.coverDate = coverDate;
	}
	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}
	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	/**
	 * @return the coverTime
	 */
	public String getCoverTime() {
		return coverTime;
	}
	/**
	 * @param coverTime the coverTime to set
	 */
	public void setCoverTime(String coverTime) {
		this.coverTime = coverTime;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the publishDate
	 */
	public String getPublishDate() {
		return publishDate;
	}
	/**
	 * @param publishDate the publishDate to set
	 */
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	/**
	 * @return the coverSubject
	 */
	public String getCoverSubject() {
		return coverSubject;
	}
	/**
	 * @param coverSubject the coverSubject to set
	 */
	public void setCoverSubject(String coverSubject) {
		this.coverSubject = coverSubject;
	}
	/**
	 * @return the coverOutLine
	 */
	public String getCoverOutLine() {
		return coverOutLine;
	}
	/**
	 * @param coverOutLine the coverOutLine to set
	 */
	public void setCoverOutLine(String coverOutLine) {
		this.coverOutLine = coverOutLine;
	}
	/**
	 * @return the shootNeed1
	 */
	public String getShootNeed1() {
		return shootNeed1;
	}
	/**
	 * @param shootNeed1 the shootNeed1 to set
	 */
	public void setShootNeed1(String shootNeed1) {
		this.shootNeed1 = shootNeed1;
	}
	/**
	 * @return the shootNeed2
	 */
	public String getShootNeed2() {
		return shootNeed2;
	}
	/**
	 * @param shootNeed2 the shootNeed2 to set
	 */
	public void setShootNeed2(String shootNeed2) {
		this.shootNeed2 = shootNeed2;
	}
	/**
	 * @return the mediaIntroduce
	 */
	public String getMediaIntroduce() {
		return mediaIntroduce;
	}
	/**
	 * @param mediaIntroduce the mediaIntroduce to set
	 */
	public void setMediaIntroduce(String mediaIntroduce) {
		this.mediaIntroduce = mediaIntroduce;
	}
	
	
	
}
