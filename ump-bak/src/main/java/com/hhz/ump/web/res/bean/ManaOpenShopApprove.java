package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * 开店方案、定位、撤场审批表
 * @author baolm
 *
 * 2011-05-31
 */
public class ManaOpenShopApprove extends BaseTemplate {
	
	private String name; // 名称
	private String shop; // □ 百货
	private String ktv; // □ KTV
	private String game; // □ 电玩
	private String desc; // 内容简述(详细内容附后)
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShop() {
		return shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public String getKtv() {
		return ktv;
	}

	public void setKtv(String ktv) {
		this.ktv = ktv;
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return null;
	}

}
