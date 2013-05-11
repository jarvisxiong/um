package com.intelitune.nwms.admin;

import java.util.List;

import net.sf.click.control.Column;
import net.sf.click.control.Form;
import net.sf.click.control.HiddenField;
import net.sf.click.control.Radio;
import net.sf.click.control.RadioGroup;
import net.sf.click.control.Submit;
import net.sf.click.control.Table;

import com.intelitune.common.PathHorse;
import com.intelitune.control.TableEx;
import com.intelitune.nwms.common.BorderPage;
import com.intelitune.nwms.common.CommonAccount;
import com.intelitune.nwms.menu.Menu;
import com.intelitune.nwms.model.InttRole;
import com.intelitune.nwms.roleAccess.RoleParser;
import com.intelitune.nwms.roleAccess.RoleWriter;
import com.intelitune.nwms.service.RoleService;
import com.intelitune.nwms.service.RoleServiceImp;

public class AcessDetail extends BorderPage {
	private CommonAccount commonAccount = CommonAccount.getInstance(getContext().getSession());;

	Menu me = new Menu();
	public String menuInclude = me.getSystemset();
	public String position = this.getMessage("accessdetailpos");
	public String title = this.getMessage("titleaccessdetailpos");
	public TableEx table = new TableEx("table");

	private Form form;
	public static final String TREE_NODES_SESSION_KEY = "checkboxTreeNodes";
	public RoleService roleService =RoleServiceImp.getInstance();
	private String RoleXMLPath = PathHorse.getPath() + "WEB-INF/RoleControl/RoleMapping.xml";
	private HiddenField hf_treeNode = new HiddenField("hf_treeNode", String.class);

	public AcessDetail() {
		makeTable();
	}

	public void onInit() {
		super.onInit();
		super.checkAccess(form, this.getClass().getName(), null, menuInclude);
		form = new Form("form");
		RadioGroup rg = new RadioGroup("rg_RoleScope");
		if (commonAccount.accountLocaleLanguage.equals("en")) {
			rg.add(new Radio("n", "none")); // 无任何权限
			rg.add(new Radio("r", "read")); // 只读
			rg.add(new Radio("w", "write")); // 可读写
		} else if (commonAccount.accountLocaleLanguage.equals("cn")) {
			rg.add(new Radio("n", "无权限")); // 无任何权限
			rg.add(new Radio("r", "可浏览")); // 只读
			rg.add(new Radio("w", "可操作")); // 可读写
		}
		rg.setValue("n");
		rg.setVerticalLayout(false);
		form.add(rg);
		form.add(new Submit("enquiry", this, "onEnquiryClick"));
		form.add(new Submit("submit",this.getMessage("submit"), this, "onSaveClick"));
		form.add(new Submit("reset",this.getMessage("reset"), this, "onResetClick"));
		form.add(new Submit("return",this.getMessage("cancel"), this, "onReturnClick"));
		form.add(hf_treeNode);
		addControl(form);
	}

	public void onGet() {
		super.onGet();
		if (getContext().getRequestParameter("roleId") != null) {
			this.getContext().setSessionAttribute("AccessDetail_RoleId", new String(getContext().getRequestParameter("roleId")));
		}
	}

	public boolean onReturnClick() {
		clearSession();
		this.setRedirect(Access.class);
		return true;
	}

	public void clearSession() {
		this.getContext().removeSessionAttribute("AccessDetail_RoleId");
		// this.getContext().removeSessionAttribute("AccessDetail_ArrayList_SourceName");
	}

	/**
	 * 将ResourceMapping.xml中的资源根据页面上 的roleScope的选择进行处理存入RoleMapping.xml
	 * 中，从而各个页面的权限由RoleMapping.xml来控制。 增加权限
	 * 
	 * @author Success
	 * @date 2008-06-05
	 * @return
	 */
	public boolean onSaveClick() throws Exception {
		InttRole ir = new InttRole();
		ir = (InttRole) roleService.findById(Integer.parseInt(this.getContext().getSessionAttribute("AccessDetail_RoleId").toString()));
		String[] treeNode = hf_treeNode.getValue().split(",");
		RoleWriter rw = new RoleWriter(RoleXMLPath);
		rw.modifyRoleMappingXML(RoleXMLPath, treeNode, ir.getRoleName(), ir.getDescription(), form.getFieldValue("rg_RoleScope"));
		onEnquiryClick();
		return true;
	}

	public boolean onResetClick() {
		return true;
	}

	public boolean onEnquiryClick() throws Exception {
		InttRole ir = new InttRole();
		ir = (InttRole) roleService.findById(Integer.parseInt(this.getContext().getSessionAttribute("AccessDetail_RoleId").toString()));
		String[] treeNode = hf_treeNode.getValue().split(",");
		RoleParser ro = new RoleParser(RoleXMLPath);
		List list = ro.getRoleResourceListByTree(RoleXMLPath, ir.getRoleName(), commonAccount.accountLocaleLanguage, treeNode);
		this.getContext().setSessionAttribute(this.getClass().getName() + "_listRole", list);
		return true;
	}

	public void makeTable() {
		table.setWidth("100%");
		table.setShowBanner(true);
		table.setHoverRows(true);
//		table.setPaginator(new InlinePaginator());
		table.setPaginatorAttachment(Table.PAGINATOR_INLINE);
		table.setPageSize(10);
		table.setSortable(true);
		table.addColumn(new Column("resourceDescription"));
		table.addColumn(new Column("operation"));
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
		if (this.getContext().getSessionAttribute(this.getClass().getName() + "_listRole") != null) {
			table.setRowList((List) this.getContext().getSessionAttribute(this.getClass().getName() + "_listRole"));
		}
	}

}
