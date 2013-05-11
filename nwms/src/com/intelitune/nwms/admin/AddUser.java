package com.intelitune.nwms.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.click.control.Checkbox;
import net.sf.click.control.Form;
import net.sf.click.control.HiddenField;
import net.sf.click.control.Option;
import net.sf.click.control.Select;
import net.sf.click.control.Submit;
import net.sf.click.control.TextField;
import net.sf.click.extras.control.CheckList;
import net.sf.click.extras.control.EmailField;

import com.axis2.services.MCSserviceStub;
import com.intelitune.nwms.model.InttUser;
import com.intelitune.nwms.common.BorderPage;
import com.intelitune.nwms.common.CommonAccount;
import com.intelitune.nwms.common.MD5;
import com.intelitune.nwms.common.PasswordKeyboard;
import com.intelitune.nwms.common.UserConst;
import com.intelitune.nwms.menu.Menu;
import com.intelitune.nwms.model.InttRole;
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

public class AddUser extends BorderPage {
	Menu me = new Menu();
	public String menuInclude = me.getSystemset();
	public String position = this.getMessage("adduserpos");
	public String title = this.getMessage("titleadduserpos");
	public Form form = new Form();
	// add by wzz
	public EmailField email = new EmailField("email", getMessage("email"), true);
	public String jsInclude = "admin/user-include.htm";
	public Select sel_role = new Select("sel_role");
	private CheckList ck_customer = new CheckList("ck_customer", this.getMessage("customer"));
	public Select sel_language = new Select("sel_language");
	public TextField tf_userName = new TextField("tf_userName", true);
	public PasswordKeyboard pf_userPassword = new PasswordKeyboard("pf_userPassword", true);
	public PasswordKeyboard pf_userPasswordAgain = new PasswordKeyboard("pf_userPasswordAgain", true);
	public RoleService roleService = RoleServiceImp.getInstance();;
	public UserService userService = UserServiceImp.getInstance();;
	public UserCustomerService userCustomerService = UserCustomerServiceImp.getInstance();;
	public UserRoleService userRoleService = UserRoleServiceImp.getInstance();
	private CommonAccount commonAccount = null;

	private HiddenField hf_totalCK = new HiddenField("hf_totalCK", String.class);

	public AddUser() {
		commonAccount = CommonAccount.getInstance(getContext().getSession());
		makeForm();
	}

	public void makeForm() {
		form.setValidate(true);
		form.setJavaScriptValidation(true);
		form.add(tf_userName);
		form.add(pf_userPassword);
		form.add(pf_userPasswordAgain);
		form.add(email);
		Checkbox cb_all = new Checkbox("cb_all", this.getMessage("choiceall"));
		cb_all.setAttribute("onclick", "checkAll(this);");
		form.add(cb_all);
		form.add(ck_customer);
		form.add(hf_totalCK);
		// by wzz

		form.add(sel_role);
		form.add(sel_language);
		form.add(new Submit("submit",this.getMessage("submit"), this, "onSaveClick"));
		setBtnStyle(form, "submit");
		form.add(new Submit("reset",this.getMessage("reset"), this, "onResetClick"));
		setBtnStyle(form, "reset");
		form.add(new Submit("return",this.getMessage("cancel"), this, "onReturnClick"));
		setBtnStyle(form, "return");
//		form.setLabelStyle(this.getContext().getSessionAttribute(LABELSTYLE).toString());
		// tf_userName.setAttribute("onblur", "validate(this);");
		// pf_userPassword.setAttribute("onblur", "validate(this);");
		// pf_userPasswordAgain.setAttribute("onblur", "validate(this);");
		tf_userName.setAttribute("onblur", "startRequestCheckUserCreate(this);");
		pf_userPassword.setAttribute("onblur", "startRequestCheckPasswordFirst(this);");
		pf_userPasswordAgain.setAttribute("onblur", "startRequestCheckPassword(this);");
//		form.add(super.mustInputValue);
	}

	public void onInit() {
		super.onInit();
		super.checkAccess(form, this.getClass().getName(), null, menuInclude);
		initDDL();
	}

	public void onGet() {
		super.onGet();
	}

	public boolean onSaveClick() {
		if (form.isValid()) { // new
			int userRoleId;
			InttUser iu = new InttUser();
			InttRole ir = new InttRole();
			InttUserRole iur = new InttUserRole();
			List l1 = userService.findByQuery("from InttUser as iu where iu.userName = '" + tf_userName.getValue() + "' ");
			if (l1.size() > 0) {
				return false;
			}
			iu.setUserName(tf_userName.getValue());
			if (pf_userPassword.getValue() != null && pf_userPasswordAgain.getValue() != null && !pf_userPassword.getValue().equals("") && !pf_userPasswordAgain.getValue().equals("")) {
				if (!pf_userPassword.getValue().equals(pf_userPasswordAgain.getValue())) {
					return false;
				}
			} else {
				return false;
			}
			iu.setUserPassword(new MD5().getMD5ofStr(pf_userPassword.getValue()));
			iu.setSessionTimeOut(600000);
			iu.setLanguage(sel_language.getValue());
			iu.setUserStatus(UserConst.UNLOCKED);
			iu.setCreator(commonAccount.accountId);
			iu.setCreationTime(new Date());
			iu.setEmail(email.getValue());
			ir = (InttRole) roleService.findById(Integer.parseInt(sel_role.getValue()));
			iur.setInttRole(ir);
			iur.setInttUser(iu);
			userService.save(iu);
			userRoleService.addUserRole(iur);
			List l = ck_customer.getSelectedValues();
			if (l.size() > 0) {
				for (int i = 0; i < l.size(); i++) {
					InttUserCustomer iuc = new InttUserCustomer();
					iuc.setInttUser(iu);
					iuc.setClientId(Integer.parseInt(l.get(i).toString()));
					userCustomerService.save(iuc);
				}
			}
			userRoleId = iur.getUserRoleId();
			this.setRedirect("userInfo.htm?userRoleId=" + userRoleId);
		}
		return true;
	}

	public boolean onResetClick() {
		this.setRedirect(this.getClass());
		return true;
	}

	public void initDDL() {
		sel_role.addAll(roleService.findByquery("from InttRole"), "roleId", "roleName");
		// sel_customer.add(Option.EMPTY_OPTION);
		// sel_customer.addAll(customerService.getDao().query("from Customer"),
		// "id", "name");
		List list = new ArrayList();
		MCSService mcsService = MCSService.getInstance();
		MCSserviceStub.InttClientDetailWS[] l = mcsService.getAllClient();
		for (int i = 0; i < l.length; i++) {
			list.add(new Option(l[i].getId(), l[i].getCnName()));
		}
		ck_customer.setOptionList(list);
		sel_language.add(new Option("en", "en"));
		sel_language.add(new Option("cn", "cn"));
		hf_totalCK.setValue(String.valueOf(l.length));
	}

	public boolean onReturnClick() {
		this.setRedirect(UserList.class);
		return true;
	}
}
