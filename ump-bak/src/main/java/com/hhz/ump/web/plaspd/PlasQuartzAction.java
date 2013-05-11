package com.hhz.ump.web.plaspd;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.aop.HttpRequester;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.cache.PlasCacheUtil;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.dao.eas.EasService;
import com.hhz.ump.dao.oa.OaEmailBodyManager;
import com.hhz.ump.dao.oa.OaMeetingManager;
import com.hhz.ump.dao.res.ResApproveInfoManager;
import com.hhz.ump.util.PdCache;
import com.hhz.ump.web.res.ResConstants;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/plaspd")
public class PlasQuartzAction extends ActionSupport {
	private static Log logger = LogFactory.getLog(PlasQuartzAction.class);
	private static final long serialVersionUID = 2538682874178162191L;

	@Autowired
	private AppAttachFileManager appAttachFileManager;

	@Autowired
	private OaMeetingManager oaMeetingManager;
	@Autowired
	private OaEmailBodyManager oaEmailBodyManager;

	@Autowired
	private ResApproveInfoManager resApproveInfoManager;

	@Autowired
	private PdCache pdCache;

	@Autowired
	private PlasCache plasCache;

	@Autowired
	private EasService easService;

	private String className;

	public String main() throws Exception {

		return SUCCESS;
	}

	/**
	 *同时刷新两台服务器 -机构人员缓存
	 */
	private void refreshAllPlas() {
		try {
			HttpRequester request = new HttpRequester();
			InputStream is = PowerUtils.class.getClassLoader().getResourceAsStream("clusterNode.properties");
			PropertyResourceBundle bundle = new PropertyResourceBundle(is);
			String nodeUrl1 = (String) bundle.handleGetObject("nodeCache1");
			logger.info(nodeUrl1);
			request.sendSimple(nodeUrl1);
			String nodeUrl2 = (String) bundle.handleGetObject("nodeCache2");
			logger.info(nodeUrl2);
			request.sendSimple(nodeUrl2);
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error("连接异常", e);
		}
	}
	/**
	 *同时刷新两台服务器 -数据字典
	 */
	private void refreshAllDict() {
		try {
			HttpRequester request = new HttpRequester();
			InputStream is = PowerUtils.class.getClassLoader().getResourceAsStream("clusterNode.properties");
			PropertyResourceBundle bundle = new PropertyResourceBundle(is);
			String nodeUrl1 = (String) bundle.handleGetObject("nodeDict1");
			logger.info(nodeUrl1);
			request.sendSimple(nodeUrl1);
			String nodeUrl2 = (String) bundle.handleGetObject("nodeDict2");
			logger.info(nodeUrl2);
			request.sendSimple(nodeUrl2);
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error("连接异常", e);
		}
	}

	/**
	 * 刷新缓存
	 * 
	 * @throws Exception
	 */
	public void refreshCache() throws Exception {
		try {

			String localAddr = ServletActionContext.getRequest().getLocalAddr();
			String ctx = ServletActionContext.getRequest().getContextPath();
			if ("org".equals(className)) {
				// 刷新PLAS缓存(机构/人员/职位/其他关系映射)
				if (localAddr.equals("pd.powerlong.com")) {
					//服务器上
					logger.info("刷新服务器缓存");
					refreshAllPlas();
				} else {
					refreshPlas();
				}
			} else if ("dict".equals(className)) {
				if (localAddr.equals("pd.powerlong.com")) {
					//服务器上
					logger.info("刷新数据字典缓存");
					refreshAllDict();
				} else {
					refreshDict();
				}
				
			}
			Struts2Utils.renderText("success");
		} catch (Exception e) {
			Struts2Utils.renderText("error");
		}
	}
	/**
	 * 刷新数据字典缓存
	 * @throws Exception
	 */
	public void refreshDict() throws Exception {
		try {
			// 刷新PD审批节点
			ResConstants.refreshCustomNode();
			// 清空PD缓存(字典/职位)
			pdCache.cleanCache();
//			Struts2Utils.renderText("success");
		} catch (Exception e) {
			Struts2Utils.renderText("error");
		}
	}

	/**
	 * 刷新机构-人员缓存
	 * @throws Exception
	 */
	public void refreshPlas() throws Exception {
		try {
			plasCache.reloadCache();
//			Struts2Utils.renderText("success");
		} catch (Exception e) {
			Struts2Utils.renderText("error");
		}
	}

	/**
	 * 给所有有未审批记录的人员发送短信通知
	 * 
	 * @throws Exception
	 */
	public void notifyAllUser() throws Exception {
		try {
			resApproveInfoManager.notifyAllUser();
			Struts2Utils.renderText("success");
		} catch (Exception e) {
			Struts2Utils.renderText("error");
		}
	}

	/**
	 * 清除已删除、临时的文件
	 * 
	 * @return
	 * @throws Exception
	 */
	public String clearFile() throws Exception {
		try {
			appAttachFileManager.clearDelFile();
			Struts2Utils.renderText("success");
		} catch (Exception e) {
			Struts2Utils.renderText("error");
		}
		return null;
	}

	/**
	 * 立即执行执行总裁例会邮件提醒
	 * 
	 * @return
	 * @throws Exception
	 */
	public String exeMeetingRemain() throws Exception {
		try {
			oaMeetingManager.daylyRemind();
			Struts2Utils.renderText("success");
		} catch (Exception e) {
			Struts2Utils.renderText("error");
		}
		return null;
	}

	/**
	 * 立即执行每日事务邮件提醒
	 * 
	 * @return
	 * @throws Exception
	 */
	public String exeTaskRemind() throws Exception {
		try {
			oaEmailBodyManager.taskRemind();
			Struts2Utils.renderText("success");
		} catch (Exception e) {
			Struts2Utils.renderText("error");
		}
		return null;
	}

	public void sendEmail2Pwd123User() {
		List<String> list = oaEmailBodyManager.sendEmail2Pwd123User();
		Struts2Utils.renderJson(list);
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	//eas提醒
	public String tipEas() {

		String type = Struts2Utils.getParameter("type");
		if (easService.execute(type)) {
			Struts2Utils.renderText("success");
		} else {
			Struts2Utils.renderText("type 不对！");
		}
		return null;
	}
	public String testPlasNode(){

		try {
			//24 -地产公司总经理
			//157-区域总经理
			//153-区域技术管理部
			//156-区域成本管理部
			Set<String> uiid=PlasCacheUtil.getResUiidByNodeCd("156");
			System.out.println(">>>>>>>>>>>>>>>> " + PowerUtils.array2String(uiid));
			Struts2Utils.renderText("success");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("测试审批接口!", e);
		}
		return null;
	}
	//测试审批接口
	public String testResInf() {

		//		1	商业总部
		//		2	行业总部
		//		3	事业总部
		//		4	区域公司
		//		5	地产公司
		//		6	行业公司
		//		7	商业公司
		//		8	通用
		//		0	地产总部

		//		0	项目
		//		1	人力
		//		2	营销
		//		3	投资
		//		4	技术
		//		5	成本
		//		6	储备
		//		7	总部
		//		8	部门
		//		9	财务
		//		10	工程
		//		11	中心
		//		12	招商

		try {
			List<String> list = new ArrayList<String>();
			//	获取节点对应人员的逻辑，例如:
			//	1、区域总经理：根据当前用户所在机构+节点属性（节点级别=区域公司，机构类型=null，人员类型=机构负责人）
			//list = PlasCacheUtil.getVoSysPositionList("4", null, "1");

			//	2、区域公司相关部门负责人：根据当前用户所在机构+节点属性（节点级别=区域公司，机构类型=部门，人员类型=机构负责人）
			//list = PlasCacheUtil.getVoSysPositionList("4", "8", "1");
			
			//3、相关中心负责人：节点属性（节点级别=总部，机构类型=中心，人员类型=机构负责人）
			//list = PlasCacheUtil.getVoSysPositionList("0", "11", "1");

			//list = PlasCacheUtil.getVoSysPositionList("4", "1", "1");
			
			//4、区域副总经理：节点属性（节点级别=区域公司，机构类型=null，人员类型=节点名称匹配[根据系统职位名称匹配]）
			//list = PlasCacheUtil.getVoSysPositionList("4", "5", "2","招采副总经理");
			
			//5、区域公司投资发展部：节点属性（节点级别=区域公司，机构类型=投资，人员类型=机构负责人）
			list = PlasCacheUtil.getVoSysPositionList("4", "3", "1");


			//list = PlasCacheUtil.getCurNodeLevelList("fanwei");
			
			for (String t : list) {
				System.out.println(">>>>>>>>>>>>>>>> " + t);
			}
			Struts2Utils.renderText("success");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("测试审批接口!", e);
		}
		return null;
	}

}
