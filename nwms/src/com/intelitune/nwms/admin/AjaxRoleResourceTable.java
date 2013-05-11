package com.intelitune.nwms.admin;

import java.util.List;
import java.util.StringTokenizer;

import net.sf.click.Page;
import net.sf.click.control.Column;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.intelitune.common.PathHorse;
import com.intelitune.control.TableEx;
import com.intelitune.nwms.common.CommonAccount;
import com.intelitune.nwms.model.InttRole;
import com.intelitune.nwms.roleAccess.RoleParser;
import com.intelitune.nwms.service.RoleService;
import com.intelitune.nwms.service.RoleServiceImp;

public class AjaxRoleResourceTable extends Page {

	public TableEx table = new TableEx("table");
	private String RoleXMLPath = PathHorse.getPath() + "WEB-INF/RoleControl/RoleMapping.xml";
	private CommonAccount commonAccount = CommonAccount.getInstance(getContext().getSession());
//	public static ApplicationContext ctx = new ClassPathXmlApplicationContext("ServiceContext.xml");
	public RoleService roleService = RoleServiceImp.getInstance();

	public AjaxRoleResourceTable() {
		makeTable();
	}

	public void onInit() {
		super.onInit();
	}

	public void makeTable() {
		table.setWidth("100%");
		table.setHoverRows(true);
		table.setPageSize(10000);
		table.addColumn(new Column("resourceDescription"));
		table.addColumn(new Column("operation"));
	}

	@SuppressWarnings("unchecked")
	public void onGet() {

	}

	@SuppressWarnings("unchecked")
	public void onRender() {
		try {
			fillTableData();
		} catch (Exception e) {
		}
	}

	@SuppressWarnings("unchecked")
	public void fillTableData() throws Exception {
		try {
			InttRole ir = new InttRole();
			ir = (InttRole) roleService.findById(Integer.parseInt(this.getContext().getSessionAttribute("AccessDetail_RoleId").toString()));
			String resource = this.getContext().getRequestParameter("resource");
			if (resource != null && !resource.trim().equals("")) {
				String[] treeNode = resource.split(",");
				RoleParser ro = new RoleParser(RoleXMLPath);
				String Agent = this.getContext().getRequest().getHeader("User-Agent");
				StringTokenizer st = new StringTokenizer(Agent,";");
				st.nextToken();
				String userbrowser = st.nextToken();
				List list = ro.getRoleResourceListByTree(RoleXMLPath, ir.getRoleName(), commonAccount.accountLocaleLanguage, treeNode);
				table.setRowList(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getContentType()
	{
		return "text/xml; charset=UTF-8";
	}

}
