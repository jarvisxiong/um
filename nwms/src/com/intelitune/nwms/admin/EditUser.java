package com.intelitune.nwms.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.click.control.Checkbox;
import net.sf.click.control.Form;
import net.sf.click.control.HiddenField;
import net.sf.click.control.Label;
import net.sf.click.control.Option;
import net.sf.click.control.Select;
import net.sf.click.control.Submit;
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

public class EditUser extends BorderPage {
	public String userRoleId;

	Menu me = new Menu();
	public String menuInclude = me.getSystemset();
	public String position = this.getMessage("edituserpos");
	public String title = this.getMessage("titleedituserpos");

	public Form form = new Form();
	public String jsInclude = "admin/user-include.htm";
	public Select sel_role = new Select("sel_role");
	private CheckList ck_customer = new CheckList("ck_customer", this.getMessage("customer") + ":");
	public Select sel_language = new Select("sel_language");
	public Label lb_userName = new Label("lb_userName");
	public Checkbox cb_status = new Checkbox("cb_status");
	
	//add by wzz
	public EmailField email=new EmailField("email",getMessage("email"),true);
	
	public PasswordKeyboard pf_userPassword = new PasswordKeyboard("pf_userPassword", true);
	public PasswordKeyboard pf_userPasswordAgain = new PasswordKeyboard("pf_userPasswordAgain", true);
	public RoleService roleService = RoleServiceImp.getInstance();;
	public UserService userService = UserServiceImp.getInstance();;
	public UserRoleService userRoleService = UserRoleServiceImp.getInstance();;
	public UserCustomerService userCustomerService = UserCustomerServiceImp.getInstance();;
	private MCSService mcsService = MCSService.getInstance();

	private HiddenField idField = new HiddenField("id", String.class);
	private HiddenField hf_totalCK = new HiddenField("hf_totalCK", String.class);
	private CommonAccount commonAccount = null;

	public EditUser() {
		makeForm();
	}

	public void makeForm() {
		commonAccount = CommonAccount.getInstance(getContext().getSession());
		// form.setButtonAlign(Form.ALIGN_CENTER);
//		form.setLabelStyle(this.getContext().getSessionAttribute(LABELSTYLE).toString());
		form.setValidate(true);
		form.setJavaScriptValidation(true);
		// tf_userName.setDisabled(true);
		// form.add(tf_userName);
		form.add(lb_userName);
		pf_userPassword.setAttribute("onblur", "validate(this);");
		form.add(pf_userPassword);

		pf_userPasswordAgain.setAttribute("onblur", "validate(this);");
		form.add(pf_userPasswordAgain);

		form.add(sel_role);
		form.add(email);
		Checkbox cb_all = new Checkbox("cb_all", this.getMessage("choiceall"));
		cb_all.setAttribute("onclick", "checkAll(this);");
		form.add(cb_all);
		form.add(ck_customer);
		
		//add by wzz
	
		
		form.add(sel_language);
		form.add(cb_status);
		form.add(new Submit("submit",this.getMessage("submit"), this, "onSaveClick"));
		form.add(new Submit("return",this.getMessage("cancel"), this, "onReturnClick"));
		form.add(idField);
//		form.add(super.mustInputValue);
		form.add(hf_totalCK);
	}

	public void onInit() {
		super.onInit();
		super.checkAccess(form, this.getClass().getName(), null, menuInclude);
		initDDL();
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
		// iur = userRoleService.getUserRole(Integer.parseInt(userRoleId));
		iur = (InttUserRole) userRoleService.findById(Integer.parseInt(userRoleId));
		// form.getField("tf_userName").setValue(iur.getInttUser().getUserName());
		form.getField("lb_userName").setLabel(iur.getInttUser().getUserName());
		// form.getField("if_sessionTimeOut").setValue(String.valueOf(iur.getInttUser().getSessionTimeOut()));
		form.getField("sel_language").setValue(iur.getInttUser().getLanguage());

		String userPwd = iur.getInttUser().getUserPassword();
		String subUserPwd = userPwd.substring(0, 8);

		pf_userPassword.setValue(subUserPwd);
		pf_userPasswordAgain.setValue(subUserPwd);

		if (iur.getInttUser().getUserStatus() == UserConst.LOCKED) {
			form.getField("cb_status").setValue("false");
		} else if (iur.getInttUser().getUserStatus() == UserConst.UNLOCKED) {
			form.getField("cb_status").setValue("true");
		}
		form.getField("sel_role").setValue(String.valueOf(iur.getInttRole().getRoleId()));

		List<InttUserCustomer> listUserCustomer = userCustomerService.findByQuery("from InttUserCustomer as iuc where iuc.inttUser.userId = " + iur.getInttUser().getUserId());
		if (listUserCustomer != null) {
			List selected = new ArrayList();
			for (int i = 0; i < listUserCustomer.size(); i++) {
				selected.add(String.valueOf(listUserCustomer.get(i).getClientId()));
			}
			ck_customer.setSelectedValues(selected);
		}
		
		//add by wzz
		email.setValue(iur.getInttUser().getEmail());
	}

	@SuppressWarnings("unchecked")
	public boolean onSaveClick() {
		if (form.isValid()) { // update
			InttUser iu = new InttUser();
			InttRole ir = new InttRole();
			InttUserRole iur = new InttUserRole();
			iur = (InttUserRole) userRoleService.findById(Integer.parseInt(idField.getValue()));
			iu = (InttUser) userService.findById(iur.getInttUser().getUserId());
			ir = (InttRole) roleService.findById(iur.getInttRole().getRoleId());

			String newPwd = new MD5().getMD5ofStr(pf_userPassword.getValue());
			String oldPwd = iur.getInttUser().getUserPassword();
			if (pf_userPassword.getValue().equals(oldPwd.substring(0, 8)))
				iu.setUserPassword(oldPwd);
			else
				iu.setUserPassword(newPwd);

			// iu.setSessionTimeOut(Integer.parseInt(if_sessionTimeOut.getValue()));
			iu.setSessionTimeOut(600000);
			iu.setLanguage(sel_language.getValue());
			
			
			//add by wzz
			iu.setEmail(email.getValue());
			
			
			if (cb_status.getValue().equals("true")) {
				iu.setUserStatus(UserConst.UNLOCKED);
			} else if (cb_status.getValue().equals("false")) {
				iu.setUserStatus(UserConst.LOCKED);
			}
			iu.setCreator(commonAccount.accountId);
			iu.setCreationTime(new Date());
			ir = (InttRole) roleService.findById(Integer.parseInt(sel_role.getValue()));
			iur.setInttRole(ir);
			iur.setInttUser(iu);
			userService.update(iu);
			userRoleService.update(iur);
			List l = ck_customer.getSelectedValues();
			if (l.size() > 0) {
				List<InttUserCustomer> l1 = userCustomerService.findByQuery("from InttUserCustomer as iuc where iuc.inttUser.id = " + iu.getUserId());
				for (int i = 0; i < l1.size(); i++) {
					userCustomerService.delete(l1.get(i));
				}
				for (int i = 0; i < l.size(); i++) {
					InttUserCustomer iuc = new InttUserCustomer();
					iuc.setInttUser(iu);
					iuc.setClientId(Integer.parseInt(l.get(i).toString()));
					userCustomerService.save(iuc);
				}
			}
			saveNewSession(iur.getInttUser().getUserId());
			this.setRedirect("userInfo.htm?userRoleId=" + idField.getValue());
		}
		return true;
	}

	public void saveNewSession(int AccountId) {
		if (commonAccount.accountId == AccountId) {
			commonAccount.accountRole = Integer.parseInt(sel_role.getValue());
			commonAccount.accountLocaleLanguage = sel_language.getValue();
			commonAccount.login = true;
			getContext().getSession().setAttribute(CommonAccount.class.getName(), commonAccount);
		}
	}

	public void initDDL() {
		sel_role.addAll(roleService.findByquery("from InttRole"), "roleId", "roleName");
		List list = new ArrayList();
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
