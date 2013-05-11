package com.hhz.ump.service;

import java.util.List;

import javax.jws.WebService;

import com.hhz.ump.entity.res.ResNode;

@WebService
public interface WSPdService {

	// 调用接口的应用业务编号
	public void sendData2Email(String subject, String content, String sender, String emailType, String... toUserUiids) ;
	//取得网批节点,只取自定义审批人的节点
	public List<ResNode> loadCustomerResNode();
}