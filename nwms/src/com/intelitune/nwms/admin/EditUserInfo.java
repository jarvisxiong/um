package com.intelitune.nwms.admin;

import java.util.Date;

import net.sf.click.control.Form;
import net.sf.click.control.Label;
import net.sf.click.control.Option;
import net.sf.click.control.Select;
import net.sf.click.control.Submit;

import com.intelitune.nwms.common.BorderPage;
import com.intelitune.nwms.common.CommonAccount;
import com.intelitune.nwms.common.MD5;
import com.intelitune.nwms.common.PasswordKeyboard;
import com.intelitune.nwms.menu.Menu;
import com.intelitune.nwms.model.InttUser;
import com.intelitune.nwms.model.InttUserRole;
import com.intelitune.nwms.service.RoleService;
import com.intelitune.nwms.service.RoleServiceImp;
import com.intelitune.nwms.service.UserRoleService;
import com.intelitune.nwms.service.UserRoleServiceImp;
import com.intelitune.nwms.service.UserService;
import com.intelitune.nwms.service.UserServiceImp;

public class EditUserInfo extends BorderPage
{
	public String userRoleId;
	
	Menu me = new Menu();
	public String menuInclude = me.getSystemset();
	public String position = this.getMessage("edituserpos");
	public String title = this.getMessage("titleedituserpos");
	
	public Form form = new Form();
	public String jsInclude = "admin/user-include.htm";
	public Select sel_language = new Select("sel_language");
	public Label lb_userName = new Label("lb_userName");
	public PasswordKeyboard pf_userPassword = new PasswordKeyboard("pf_userPassword", true);
	public PasswordKeyboard pf_userPasswordAgain = new PasswordKeyboard("pf_userPasswordAgain", true);
	public RoleService roleService = RoleServiceImp.getInstance();;
	public UserService userService = UserServiceImp.getInstance();;
	public UserRoleService userRoleService = UserRoleServiceImp.getInstance();;
	
	private CommonAccount commonAccount = null;
	
	
	public EditUserInfo() {
		makeForm();
	}
	
	public void makeForm() {
		commonAccount = CommonAccount.getInstance(getContext().getSession());
		form.setLabelStyle(this.getContext().getSessionAttribute(LABELSTYLE).toString());
		form.setValidate(true);
		form.setJavaScriptValidation(true);
//		tf_userName.setDisabled(true);
//		form.add(tf_userName);
		form.add(lb_userName);
//		pf_userPassword.setAttribute("onblur", "isEmptyCheck(this);");
		pf_userPassword.setAttribute("onblur", "validate(this);");
		form.add(pf_userPassword);
		
//		pf_userPasswordAgain.setAttribute("onblur", "isEmptyEquationCheck(this,form_password1);");
		pf_userPasswordAgain.setAttribute("onblur", "validate(this);");
		form.add(pf_userPasswordAgain);
		
		form.add(sel_language);
		form.add(new Submit("submit", this, "onSaveClick"));
		form.add(new Label("l_result"));
//		form.add(super.mustInputValue);
	}

	public void onInit() {
		super.onInit();
		super.checkAccess(form, this.getClass().getName(), null, menuInclude);
		initDDL();
	}

	public void onGet() {
		super.onGet();
		if(commonAccount.accountUserRoleId > 0) {
			initModifiedValue();
		}
	}
	
	public void initModifiedValue() {
		InttUserRole iur = new InttUserRole();
		iur = (InttUserRole) userRoleService.findById(commonAccount.accountUserRoleId);
//		form.getField("tf_userName").setValue(iur.getInttUser().getUserName());
		form.getField("lb_userName").setLabel(iur.getInttUser().getUserName());
		form.getField("sel_language").setValue(iur.getInttUser().getLanguage());
		String userPwd = iur.getInttUser().getUserPassword();
		String subUserPwd = userPwd.substring(0, 8);
		pf_userPassword.setValue(subUserPwd);
		pf_userPasswordAgain.setValue(subUserPwd);
		form.getField("l_result").setLabel("");
	}
	
	public boolean onSaveClick() {
		if (form.isValid()) {	//update
			try {
				InttUser iu = new InttUser();
				InttUserRole iur = new InttUserRole();
				iur = (InttUserRole) userRoleService.findById(commonAccount.accountUserRoleId);
				iu = (InttUser) userService.findById(iur.getInttUser().getUserId());
				
				String newPwd = new MD5().getMD5ofStr(pf_userPassword.getValue());
				String oldPwd = iur.getInttUser().getUserPassword();
				if (pf_userPassword.getValue().equals(oldPwd.substring(0, 8)))
					iu.setUserPassword(oldPwd);
				else
					iu.setUserPassword(newPwd);
				
				iu.setSessionTimeOut(600000);
				iu.setLanguage(sel_language.getValue());
				iu.setCreator(1);
				iu.setCreationTime(new Date());
				iur.setInttUser(iu);
				userService.update(iu);
				userRoleService.update(iur);
				saveNewSession(iur.getInttUser().getUserId());
				form.getField("l_result").setLabel("save successfully");
				form.setDisabled(true);
			}
			catch (Exception e) {
				form.getField("l_result").setLabel("save failed");
			}
		}
		setRedirect("../admin/welcome.htm");
		return true;
	}
	
	public void saveNewSession(int AccountId) {
		if(commonAccount.accountId == AccountId) {
			commonAccount.accountLocaleLanguage = sel_language.getValue();
			commonAccount.login = true;
			getContext().getSession().setAttribute(CommonAccount.class.getName(), commonAccount);
		}
	}

	public void initDDL() {
		sel_language.add(new Option("en", "en"));
		sel_language.add(new Option("cn", "cn"));
	}
}
