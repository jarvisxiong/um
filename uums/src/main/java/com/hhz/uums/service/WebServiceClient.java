package com.hhz.uums.service;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hhz.org.pd.service.WSPdService;
import com.hhz.uums.utils.GlobalConstants;
import com.hhz.uums.vo.vw.TreePanelNode;

/**
 * 将HelloWorld服务部署到服务器上，然后执行本测试用例来查看结果
 * 
 * @author lijin( 测试程序 )
 * 
 */
public class WebServiceClient {

	
	// 以下常量与配置文件保持一致
	private static final String CLIENT_BEANS = "wsclient-beans.xml";
	private static final String PLAS_CLIENT = "plas-client";
	private static final String PD_CLIENT = "pd-client";

	// 创建WebService客户端单态实例
	private static WebServiceClient instance;
	private WSPlasService plasService;
	private WSPdService pdService;
	private ClassPathXmlApplicationContext beanContext;
	public WebServiceClient() {
		
	}

	public static WebServiceClient getInstance() {
		if (instance == null) {
			instance = new WebServiceClient();
			instance.setBeanContext(new ClassPathXmlApplicationContext(
					CLIENT_BEANS));
		}
		return instance;
	}

	public WSPlasService getPlasService() {
		if (plasService == null) {
			plasService = (WSPlasService) instance.getBeanContext().getBean(PLAS_CLIENT);
			plasService.setAppBizCd(GlobalConstants.UAAP_BIZ_CD);
		}
		return plasService;
	}
	
	public WSPdService getPdService() {
		if (pdService == null) {
			pdService = (WSPdService) instance.getBeanContext().getBean(PD_CLIENT);
//			pdService.setAppBizCd(GlobalConstants.PD_BIZ_CD);
		}
		return pdService;
	}

	public ClassPathXmlApplicationContext getBeanContext() {
		return beanContext;
	}

	public void setBeanContext(ClassPathXmlApplicationContext beanContext) {
		this.beanContext = beanContext;
	}
	public static void getChilnd(TreePanelNode node){
		for(TreePanelNode n : node.getChildren()){
			if(null!=n.getChildren()){
				getChilnd(n);
			}
		}
	}
	public static void main(String[] args) {
try{
	
//		Util.getPdService().sendData2Email("subject", "content", "admin", "0", "huangjian");
	
	JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
	factory.getInInterceptors().add(new LoggingInInterceptor());
	factory.getOutInterceptors().add(new LoggingOutInterceptor());
	factory.setServiceClass(WSPlasService.class);
	factory.setAddress("http://localhost:8080/PowerDesk/ws/WSPdService");
	WSPlasService client2 = (WSPlasService) factory.create();
	
	
	
	
		// 配置调用
//		WSPlasService client = WebServiceClient.getInstance().getPlasService();
		//List<WsPlasOrg> orgs =client.getBubbleOrgList("127",DictContants.TREE_DIME_PHYSICAL);
		//List<WsPlasOrg> orgs =client.getDirectOrgs("135",DictContants.TREE_DIME_PHYSICAL);
//		client.getUserByUiid("dengyh");
/*		List<WsPlasOrg> orgs =client.getBizOrgs();
		for(WsPlasOrg org : orgs){
			if(org.getParentPhysicalOrgCd().equals("135")) {
				System.out.println(org.getOrgName());
			}

		}*/
		System.out.println("---------");
/*		int i = 0;
		TreePanelNode node = client.getOrgUserTree("2");
		System.out.println("-----------------");
		getChilnd(node);*/
		
	/*	for (WsPlasRole role : client.getAppRoles()) {
			System.out.println("Role info --------" + i++);
			System.out.println(role.getRoleBizCd());
			System.out.println(role.getRoleName());
		}

		// 设置调用
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.getInInterceptors().add(new LoggingInInterceptor());
		factory.getOutInterceptors().add(new LoggingOutInterceptor());
		factory.setServiceClass(WSPlasService.class);
		factory.setAddress("http://localhost:9091/plas/ws/WSPlasService");
		WSPlasService client2 = (WSPlasService) factory.create();
		int j = 0;
		for (WsPlasRole role : client2.getUserRoleList("admin")) {
			System.out.println("Role info --------" + j++);
			System.out.println(role.getRoleBizCd());
			System.out.println(role.getRoleName());
		}
		*/
		//System.out.println(client.getUserByUiid("admin").getUiid()+"--");
}catch(Exception e){
	e.printStackTrace();
}
	}
	
}
