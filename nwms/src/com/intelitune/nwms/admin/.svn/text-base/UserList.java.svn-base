package com.intelitune.nwms.admin;

import net.sf.click.control.ActionLink;
import net.sf.click.control.Column;
import net.sf.click.control.Form;
import net.sf.click.control.Submit;
import net.sf.click.control.Table;
import net.sf.click.extras.control.LinkDecorator;

import com.intelitune.control.TableEx;
import com.intelitune.nwms.common.BorderPage;
import com.intelitune.nwms.menu.Menu;
import com.intelitune.nwms.service.UserRoleService;
import com.intelitune.nwms.service.UserRoleServiceImp;

public class UserList extends BorderPage {
	Menu me = new Menu();
	public String menuInclude = me.getSystemset();
	public String position = this.getMessage("userlistpos");
	public String title = this.getMessage("titleUserList");
	public ActionLink editLink = new ActionLink("edit",this.getMessage("Edit"), this, "onEditClick");
	private ActionLink[] links;
	public Form form = new Form("form");
	public TableEx table = new TableEx("table");
	public static UserRoleService userRoleService = UserRoleServiceImp.getInstance();
	
	public UserList() {
		makeForm();
		makeTable();
	}
	
	public void onInit() {
		super.onInit();
		super.checkAccess(form, this.getClass().getName(), links, menuInclude);
	} 
	
	public void onGet() {
		super.onGet();
	}
	
	public void onRender() {
		table.setRowList(userRoleService.getList());
	}
	
	public void makeForm() {
		form.add(new Submit("new",this.getMessage("create"), this, "onNewClick"));
	}
	
	public void makeTable() {
//		table.setPaginator(new InlinePaginator());
		table.setPaginatorAttachment(Table.PAGINATOR_INLINE);
		table.setPageSize(10);
		table.setSortable(true);
		table.setHoverRows(true);
		table.setWidth("100%");
		table.addColumn(new Column("inttUser.userName"));
		table.addColumn(new Column("InttRole.roleName"));
		table.addColumn(new Column("bkt_lockStatus"));
		Column column = new Column("inttUser.creationTime");
		column.setFormat("{0, date, yyyy-MM-dd hh:mm:ss}");
		table.addColumn(column);
		column = new Column("inttUser.lastLogin");
		

		column.setFormat("{0, date, yyyy-MM-dd hh:mm:ss}");
		table.addColumn(column);
		column = new Column("Action",this.getMessage("Action"));
		column.setSortable(false);
		links = new ActionLink[]{ editLink };
		column.setDecorator(new LinkDecorator(table, links, "userRoleId"));
		table.addColumn(column);
	}
	
	public boolean onNewClick() {
		this.setRedirect(AddUser.class);
		return true;
	}
	
	public boolean onEditClick() {
		this.setRedirect("editUser.htm?userRoleId=" + editLink.getValue());
		return true;
	}
}
