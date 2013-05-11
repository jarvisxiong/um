package com.intelitune.nwms.admin;

import net.sf.click.control.ActionLink;
import net.sf.click.control.Column;
import net.sf.click.control.Form;
import net.sf.click.control.HiddenField;
import net.sf.click.control.Option;
import net.sf.click.control.Select;
import net.sf.click.control.Submit;
import net.sf.click.control.Table;
import net.sf.click.control.TextField;
import net.sf.click.extras.control.LinkDecorator;

import com.intelitune.common.PathHorse;
import com.intelitune.control.TableEx;
import com.intelitune.nwms.common.BorderPage;
import com.intelitune.nwms.common.Const;
import com.intelitune.nwms.menu.Menu;
import com.intelitune.nwms.model.InttRole;
import com.intelitune.nwms.roleAccess.RoleWriter;
import com.intelitune.nwms.service.RoleService;
import com.intelitune.nwms.service.RoleServiceImp;

public class Access extends BorderPage {
	Menu me = new Menu();
	public String menuInclude = me.getSystemset();
	public String position = this.getMessage("accesspos");
	public String title = this.getMessage("titleaccesspos");

	public Form form = new Form("form");
	public TextField tf_roleName = new TextField("tf_roleName", true);
	public TextField tf_roleDescription = new TextField("tf_roleDescription");
	public Select sel_roleTemplate = new Select("sel_roleTemplate");

	public TableEx table = new TableEx("table");
	public ActionLink editLink = new ActionLink("edit",this.getMessage("edit"), this, "onEditLink");
	public ActionLink setLink = new ActionLink("setScope",this.getMessage("setScope"), this, "onSetScopeLink");
	private ActionLink[] links;
	public HiddenField hf_roleId = new HiddenField("hf_roleId", String.class);

	public RoleService roleService = RoleServiceImp.getInstance();;

	private String XMLPath = PathHorse.getPath() + "WEB-INF/RoleControl/RoleMapping.xml";

	public Access() {
		makeForm();
		makeTable();
	}

	public void makeForm() {
		form.setButtonAlign("center");
		form.setLabelAlign("left");
		form.setValidate(true);
		form.setJavaScriptValidation(true);
		form.add(hf_roleId);
		tf_roleName.setAttribute("onblur", "validate(this);");
		form.add(tf_roleName);
		form.add(tf_roleDescription);
		form.add(sel_roleTemplate);

		form.add(new Submit("submit",this.getMessage("submit"), this, "onSaveClick"));
		form.add(new Submit("reset",this.getMessage("cancel"), this, "onResetClick"));

//		form.setLabelStyle(this.getContext().getSessionAttribute(LABELSTYLE).toString());
//		form.add(super.mustInputValue);
	}

	public void makeTable() {
//		table.setPaginator(new InlinePaginator());
		table.setPaginatorAttachment(Table.PAGINATOR_INLINE);
		table.setPageSize(10);
		table.setHoverRows(true);
		// table.setClass(this.getContext().getSessionAttribute(TABLECLASS).toString());
		// table.setStyle("color",
		// this.getContext().getSessionAttribute(TABLESTYLE).toString());
		table.setWidth("100%");
		table.addColumn(new Column("roleId"));
		table.addColumn(new Column("roleName"));
		table.addColumn(new Column("description"));

		Column column = new Column("action");
		column.setSortable(false);
		links = new ActionLink[] { editLink, setLink };
		column.setDecorator(new LinkDecorator(table, links, "roleId"));
		table.addColumn(column);

		
		table.setRowList(roleService.findAll());
	}

	public void onInit() {
		super.onInit();
		super.checkAccess(form, this.getClass().getName(), links, menuInclude);
		initDDL();
	}

	public void onGet() {
		super.onGet();
		if (getContext().getRequestParameter("value") != null) {
			hf_roleId.setValueObject(getContext().getRequestParameter("value").toString());
		}
	}

	@SuppressWarnings("unchecked")
	public void initDDL() {
		sel_roleTemplate.add(new Option("crystalBlue", "水晶蓝"));
		sel_roleTemplate.add(new Option("darkblue", "深海蓝"));
		sel_roleTemplate.add(new Option("white", "晶莹白"));
	}

	@SuppressWarnings("unchecked")
	public boolean onSaveClick() {
		if (form.isValid()) {
			InttRole ir = new InttRole();
			if (hf_roleId.getValueObject() == null || hf_roleId.getValueObject().equals("")) { // new
				if (existTheSameRoleName(tf_roleName.getValue())) {
					tf_roleName.setValue(null);
					return false;
				}
				ir.setRoleName(tf_roleName.getValue());
				ir.setDescription(tf_roleDescription.getValue());
				ir.setTemplate(sel_roleTemplate.getValue());
				roleService.addRole(ir);
				onResetClick();
				try { // 新建角色时初始化其在RoleMapping中的权限并全部为无权限。
					addRoleMapping(ir, Const.ROLE_NO);
				} catch (Exception e) {

				}
			} else { // modify
				ir = (InttRole) roleService.findById(Integer.parseInt(hf_roleId.getValueObject().toString()));
				ir.setRoleName(tf_roleName.getValue());
				ir.setDescription(tf_roleDescription.getValue());
				ir.setTemplate(sel_roleTemplate.getValue());
				roleService.Update(ir);
				onResetClick();
			}
		}
		return true;
	}

	private void addRoleMapping(InttRole ir, String RoleFunction) {
		try { // 新建角色时初始化其在RoleMapping中的权限并全部为无权限。
			RoleWriter rw = new RoleWriter(XMLPath);
			rw.addRoleMappingXML(XMLPath, ir.getRoleName(), ir.getDescription(), RoleFunction);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 判断是否有重名的角色名称
	private boolean existTheSameRoleName(String RoleName) {
		if (roleService.findByquery(" from InttRole as ir where ir.roleName = '" + RoleName + "' ").size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean onResetClick() {
		this.setRedirect(this.getClass());
		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean onEditLink() {
		InttRole ir = (InttRole) roleService.findById(editLink.getValueInteger());
		tf_roleName.setValue(ir.getRoleName());
		tf_roleDescription.setValue(ir.getDescription());
		sel_roleTemplate.setValue(ir.getTemplate());
		return true;
	}

	public boolean onSetScopeLink() {
		this.getContext().removeSessionAttribute("AccessDetail_RoleId");
		this.setRedirect("acessDetail.htm?roleId=" + setLink.getValue());
		return true;
	}
}
