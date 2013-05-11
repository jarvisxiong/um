package com.hhz.ump.service.impl;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhz.ump.dao.oa.OaEmailBodyManager;
import com.hhz.ump.dao.res.ResNodeManager;
import com.hhz.ump.entity.res.ResNode;
import com.hhz.ump.service.WSPdService;

/*
 * 本类提供接入项目调用pd接口,模拟用户,角色
 */

@WebService(endpointInterface = "com.hhz.ump.service.WSPdService")
@Transactional
@Service
public class WSPdServiceImpl implements WSPdService {
	@Autowired
	private OaEmailBodyManager oaEmailBodyManager;
	@Autowired
	private ResNodeManager resNodeManager;

	@Override
	public void sendData2Email(String subject, String content, String sender, String emailType, String... toUserUiids) {
		oaEmailBodyManager.sendData2Email(subject, content, sender, emailType, toUserUiids);
	}
	//取得网批节点,只取自定义审批人的节点
	@Override
	public List<ResNode> loadCustomerResNode() {
		return resNodeManager.getCustomerNodeMap();
	}
}
