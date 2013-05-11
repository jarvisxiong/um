/**
 * 
 */
package com.hhz.ump.web.desk;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.ump.aop.HttpRequester;
import com.hhz.ump.aop.HttpRespons;
import com.hhz.ump.dao.oa.OaEmailManager;
import com.hhz.ump.web.desk.bean.CoremailCondition;
import com.hhz.ump.web.desk.bean.CoremailResult;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author huangjian
 *         2011-7-15
 */
@Namespace("/desk")
public class CoremailAction extends ActionSupport {
	private static final long serialVersionUID = 2006495495764593785L;

	@Autowired
	private OaEmailManager oaEmailManager;
	private CoremailCondition condition = new CoremailCondition();
	private CoremailResult result;

	// private static String COS =
	// "http://mail1.powerlong.com/coremail/demo/message/listMessages.jsp?fid=1&sid=BAgZLEppgACfWtZBYMppGVHZhMXlVRMg&page_no=1&itemPerPage=10";

	// private static String URL = "http://localhost/coremail/demo/message/listMessages.jsp?";
	// main.js,CoremailAction 涉及邮箱域名

	@Override
	public String execute() {

		try {
			String pageNo = Struts2Utils.getParameter("pageNo");
			String rows = Struts2Utils.getParameter("rows");
			String[] fields = SpringSecurityUtils.getCoreMailFields();
			String server = fields[0];
			String sid = fields[1];

			condition.setSid(sid);
			if (StringUtils.isNotBlank(pageNo)) {
				condition.setPageNo(pageNo);
			}
			if (StringUtils.isNotBlank(rows)) {
				condition.setItemPerPage(rows);
			}

			// HttpRespons respons = new HttpRequester().sendGet(COS);
			HttpRequester requester = new HttpRequester();
			requester.setDefaultContentEncoding("UTF-8");
			HttpRespons respons = requester.sendGet(server + "/coremail/demo/message/listMessages.jsp?"
					+ condition.getParams());
			result = CoremailXmlParser.parserXml(respons.getContent());
			// result = getDefaultResult();
			// System.out.println(respons.getContent());

		} catch (Exception e) {
			System.out.println("搜索邮件异常!" + e.getMessage());
		}
		return "coremail";
	}

	public CoremailResult getResult() {
		return result;
	}

	public void setResult(CoremailResult result) {
		this.result = result;
	}

	public CoremailCondition getCondition() {
		return condition;
	}

	public void setCondition(CoremailCondition condition) {
		this.condition = condition;
	}

	// 未读提醒
	public String getUnReadMailNum() {
		try {
			return String.valueOf("(" + oaEmailManager.getNoReadCount(SpringSecurityUtils.getCurrentUiid()) + ")");
			// return "";
		} catch (Exception e) {
			// System.out.println("获取内部邮件异常!" + e.getMessage());
			return "";
		}
	}

	// 未读邮件
	public String getUnReadMailOutNum() {
		try {
			return "";// String.valueOf("("+EmailUtil.getNewEmailNum()+")");
		} catch (Exception e) {
			// log.error("获取内部邮件异常!" + e);
			return "";
		}
	}
}
