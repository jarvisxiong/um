package com.intelitune.nwms;

import net.sf.click.control.Checkbox;
import net.sf.click.control.Form;
import net.sf.click.control.ImageSubmit;
import net.sf.click.control.Label;
import net.sf.click.control.TextField;

import com.intelitune.control.PasswordKeyboard;
import com.intelitune.nwms.common.BaseController;
import com.intelitune.nwms.common.CommonAccount;
import com.intelitune.nwms.common.OnlineUsers;
import com.intelitune.nwms.common.PageURI;

import edu.yale.its.tp.cas.client.filter.CASFilter;

public class Login extends BaseController {
	public Form form = new Form();
//	private Label label_message = new Label("label_message");
	public static final String COOKIE_REMEMBER_ME = "com.intelitune.ccos.RememberMe";
	public static final String COOKIE_USER_NAME = "com.intelitune.ccos.userName";
	public static final int COOKIE_MAX_AGE = 9000000;
	public Login() {
		
		form.setErrorsPosition(Form.POSITION_BOTTOM);
		form.setButtonAlign("center");
		form.setLabelAlign(Form.ALIGN_LEFT);
		form.setLabelStyle("color: blue; font-size: 16px;");

		TextField tfAccount = new TextField("tfAccount", "", true);
		tfAccount.setSize(20);
		tfAccount.setFocus(true);
		form.add(tfAccount);

		PasswordKeyboard pfPassword = new PasswordKeyboard("pfPassword", true);
		pfPassword.setSize(20);
		pfPassword.setFocus(true);
		form.add(pfPassword);

		form.add(new Checkbox("cbRemeberMe", "Remember Me"));

		ImageSubmit sbLogin = new ImageSubmit("sbLogin", "image/login_button.jpg", this, "onLoginClick");
		form.add(sbLogin);

		Label label_message = new Label("label_message");
		label_message.setLabel("");
		form.add(label_message);
	}
	
//	public void onGet() {
//		if (getContext().getRequest().getParameter("Logout") != null) {
//			if (!getContext().getSession().isNew()) {
//				if (CommonAccount.getInstance(getContext().getSession()) != null) {
//					CommonAccount.getInstance(getContext().getSession()).accountLogout(getContext().getSession());
//				}
//			}
//		}
//		if (getCookie(COOKIE_REMEMBER_ME) != null) {
//			if ("True".equals(getCookie(COOKIE_REMEMBER_ME).getValue())) {
//				form.getField("cbRemeberMe").setValue("true");
//				form.getField("tfAccount").setValue(getCookie(COOKIE_USER_NAME).getValue());
//			}
//		}
		public void onGet() {
			/*
			 * String username = null; if
			 * (getContext().getSession().getAttribute(CASFilter.CAS_FILTER_USER) !=
			 * null && getContext().getRequest().getParameter("Logout") == null) {
			 * username =
			 * getContext().getSession().getAttribute(CASFilter.CAS_FILTER_USER).toString();
			 * CommonAccount commonAccount =
			 * CommonAccount.accountLogin(getContext().getSession(), username,
			 * null); if (!commonAccount.login) {
			 * form.getField("label_message").setLabel(commonAccount.loginError); }
			 * else { // set the session max alive time
			 * getContext().getSession().setMaxInactiveInterval(100000000); if
			 * (form.getFieldValue("cbRemeberMe").equals("true")) {
			 * addCookie(COOKIE_REMEMBER_ME, "True", COOKIE_MAX_AGE);
			 * addCookie(COOKIE_USER_NAME, commonAccount.accountName,
			 * COOKIE_MAX_AGE); } // add for check login in one place String token =
			 * OnlineUsers.userLogin(commonAccount.accountName);
			 * getContext().setCookie("ACCOUNT_TOKEN_HSS", token, -1);
			 * setRedirect(PageURI.PAGE_WELCOME); } }
			 */
			if (getContext().getRequest().getParameter("Logout") != null) {
				if (!getContext().getSession().isNew()) {
					if (CommonAccount.getInstance(getContext().getSession()) != null) {
						CommonAccount.getInstance(getContext().getSession()).accountLogout(getContext().getSession());
						if (getContext().getSession().getAttribute(CASFilter.CAS_FILTER_USER) != null) {
							this.setRedirect("https://www.hmglog.com:8443/sso/login");
						}
					}
				}
			}
			if (getCookie(COOKIE_REMEMBER_ME) != null) {
				if ("True".equals(getCookie(COOKIE_REMEMBER_ME).getValue())) {
					form.getField("cbRemeberMe").setValue("true");
					form.getField("tfAccount").setValue(getCookie(COOKIE_USER_NAME).getValue());
				}
			}
		}
//	}

	public boolean onLoginClick() {
//		this.setRedirect("item/orderList.htm");
		if(form.isValid() == true) {
			if (!isCookieAvailable()) {
				form.getField("label_message").setLabel("Cookie is not available!");
				return false;
			}
			
			if (!form.getFieldValue("cbRemeberMe").equals("true")) {
				addCookie(COOKIE_REMEMBER_ME, "False", COOKIE_MAX_AGE);
				addCookie(COOKIE_USER_NAME, "", COOKIE_MAX_AGE);
			}

			String username = form.getFieldValue("tfAccount");
			String password = form.getFieldValue("pfPassword");

			form.getField("pfPassword").setValue("");
			
			CommonAccount commonAccount = CommonAccount.accountLogin(getContext().getSession(), username, password);
			if (!commonAccount.login) {
//				label_message.setLabel(commonAccount.loginError);
				form.getField("label_message").setLabel(commonAccount.loginError);
				return false;
			}

//			getContext().getSession().setMaxInactiveInterval(100000);	//set the session max alive time
			
			if (form.getFieldValue("cbRemeberMe").equals("true")) {
				addCookie(COOKIE_REMEMBER_ME, "True", COOKIE_MAX_AGE);
				addCookie(COOKIE_USER_NAME, commonAccount.accountName, COOKIE_MAX_AGE);
			}
			
//			add for check login in one place
			String token = OnlineUsers.userLogin(commonAccount.accountName);
			getContext().setCookie("ACCOUNT_TOKEN_HSS", token, -1);
			setRedirect(PageURI.PAGE_WELCOME);

		}
        return true;
    }
	
}