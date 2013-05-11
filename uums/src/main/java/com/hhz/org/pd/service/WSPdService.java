package com.hhz.org.pd.service;

import java.util.List;

import javax.jws.WebService;

import com.hhz.uums.vo.pd.ResNode;

@WebService
public interface WSPdService {

	public void sendData2Email(String subject, String content, String sender, String emailType, String... toUserUiids);
	//取得网批节点,只取自定义审批人的节点
	public List<ResNode> loadCustomerResNode();
}