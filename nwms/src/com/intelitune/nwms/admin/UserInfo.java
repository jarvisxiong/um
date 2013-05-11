package com.intelitune.nwms.admin;

import java.util.List;

import net.sf.click.control.Form;
import net.sf.click.control.HiddenField;
import net.sf.click.control.Label;
import net.sf.click.control.Submit;

import com.axis2.services.MCSserviceStub.InttClientDetailWS;
import com.intelitune.nwms.common.BorderPage;
import com.intelitune.nwms.common.CommonAccount;
import com.intelitune.nwms.common.UserConst;
import com.intelitune.nwms.menu.Menu;
import com.intelitune.nwms.model.InttUserCustomer;
import com.intelitune.nwms.model.InttUserRole;
import com.intelitune.nwms.service.MCSService;
import com.intelitune.nwms.service.RoleService;
import com.intelitune.nwms.service.RoleServiceImp;
import com.intelitune.nwms.service.UserCustomerService;
import com.intelitune.nwms.service.UserCustomerServiceImp;
import com.intelitune.nwms.service.UserRoleService;
import com.intelitune.nwms.service.UserRoleServiceImp;
import com.intelitune.nwms.service.UserService;
import com.intelitune.nwms.service.UserServiceImp;

public class UserInfo extends BorderPage {
	public String userRoleId;
	Menu me = new Menu();
	public String menuInclude = me.getSystemset();
	public String position = this.getMessage("userinfopos");
	public String title = this.getMessage("titleuserinfopos");
	public Form form = new Form();

	// private static ApplicationContext ctx = new
	// ClassPathXmlApplicationContext("ServiceContext.xml");
	public RoleService roleService = RoleServiceImp.getInstance();
	public UserService userService = UserServiceImp.getInstance();
	public UserRoleService userRoleService = UserRoleServiceImp.getInstance();;
	public UserCustomerService userCustomerService = UserCustomerServiceImp.getInstance();;
	private MCSService mcsService = MCSService.getInstance();

	private HiddenField idField = new HiddenField("id", String.class);

	private CommonAccount commonAccount = null;

	public UserInfo() {
		makeForm();
	}

	public void makeForm() {
		form.setColumns(2);
		form.add(new Label("l_title_userName"));
		form.add(new Label("l_userName", ""));
		// form.add(new Label("l_title_sessionTimeOut"));
		// form.add(new Label("l_sessionTimeOut", ""));
		form.add(new Label("l_title_language"));
		form.add(new Label("l_language", ""));
		form.add(new Label("l_title_status"));
		form.add(new Label("l_status", ""));
		form.add(new Label("l_title_role"));
		form.add(new Label("l_role", ""));
		form.add(new Label("l_title_customer"));
		form.add(new Label("l_customer", ""));
		form.add(new Submit("edit", this, "onEditClick"));
		form.add(new Submit("return", this, "onReturnClick"));
		form.add(idField);
	}

	public void onInit() {
		super.onInit();
		super.checkAccess(form, this.getClass().getName(), null, menuInclude);
	}

	public void onGet() {
		super.onGet();
		if (getContext().getRequestParameter("userRoleId") != null) {
			userRoleId = new String(getContext().getRequestParameter("userRoleId"));
			idField.setValueObject(userRoleId);
			initModifiedValue();
		}
	}

	public void initModifiedValue() {
		InttUserRole iur = new InttUserRole();
		iur = (InttUserRole) userRoleService.findById(Integer.parseInt(userRoleId));
		form.getField("l_userName").setLabel(iur.getInttUser().getUserName());
		// form.getField("l_sessionTimeOut").setLabel(String.valueOf(iur.getInttUser().getSessionTimeOut()));
		form.getField("l_language").setLabel(iur.getInttUser().getLanguage());
		if (iur.getInttUser().getUserStatus() == UserConst.LOCKED) {
			form.getField("l_status").setLabel("locked");
		} else if (iur.getInttUser().getUserStatus() == UserConst.UNLOCKED) {
			form.getField("l_status").setLabel("unlocked");
		}
		form.getField("l_role").setLabel(iur.getInttRole().getRoleName());
		String customerName = "";
		List<InttUserCustomer> list = userCustomerService.findByQuery("from InttUserCustomer as iuc where iuc.inttUser.userId = " + iur.getInttUser().getUserId());
		if (list != null) {
			int columnNumber = 3;
			for (int i = 1; i <= list.size(); i++) {
				InttClientDetailWS client = mcsService.findClientById(list.get(i - 1).getClientId());
				if (i == 1) {
					customerName += "<table><tr><td>" + client.getCnName() + "(" + client.getCompanyCode() + ")</td>";
				} else if (i % columnNumber == 1 && i != 1 && i != list.size()) {
					customerName += "<tr><td>" + client.getCnName() + "(" + client.getCompanyCode() + ")</td>";
				} else if (i % columnNumber > 1 && i != 1 && i != list.size()) {
					customerName += "<td>" + client.getCnName() + "(" + client.getCompanyCode() + ")</td>";
				} else if (i % columnNumber == 0 && i != 1 && i != list.size()) {
					customerName += "<td>" + client.getCnName() + "(" + client.getCompanyCode() + ")</td></tr>";
				} else if (i == list.size()) {
					customerName += "<td>" + client.getCnName() + "(" + client.getCompanyCode() + ")</td></tr></table> ";
				}
			}
		}
		if (customerName != null && !customerName.equals("")) {
			form.getField("l_customer").setLabel(customerName);
		} else {
			form.getField("l_customer").setLabel("");
		}
		/*
		 * String customerName = ""; List<InttUserCustomer> listUserCustomer =
		 * userCustomerService.getDao().query(
		 * "from InttUserCustomer as iuc where iuc.inttUser.userId = " +
		 * iur.getInttUser().getUserId()); if(listUserCustomer != null) {
		 * for(int i = 0; i < listUserCustomer.size(); i++) { customerName +=
		 * mcsService
		 * .findClientById(listUserCustomer.get(i).getClientId()).getCnName() +
		 * " "; } } if(customerName != null && !customerName.equals("")) {
		 * form.getField("l_customer").setLabel(customerName); } else {
		 * form.getField("l_customer").setLabel(""); }
		 */
	}

	public boolean onEditClick() {
		this.setRedirect("editUser.htm?userRoleId=" + idField.getValue());
		return true;
	}

	public boolean onReturnClick() {
		this.setRedirect(UserList.class);
		return true;
	}
}
