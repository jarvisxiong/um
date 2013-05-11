package com.intelitune.nwms;

import com.intelitune.nwms.common.BaseController;
import com.intelitune.nwms.common.CommonAccount;
import com.intelitune.nwms.common.OnlineUsers;
import com.intelitune.nwms.common.PageURI;

import edu.yale.its.tp.cas.client.filter.CASFilter;

import net.sf.click.control.Form;

public class Loginsso extends BaseController {
	public Form form = new Form();

	public Loginsso() {
	}

	public void onGet() {
		String username = null;
		if (getContext().getSession().getAttribute(CASFilter.CAS_FILTER_USER) != null && getContext().getRequest().getParameter("Logout") == null) {
			username = getContext().getSession().getAttribute(CASFilter.CAS_FILTER_USER).toString();
			CommonAccount commonAccount = CommonAccount.accountLogin(getContext().getSession(), username, null);
			if (!commonAccount.login) {
				form.getField("label_message").setLabel(commonAccount.loginError);
			} else {
				// set the session max alive time
				getContext().getSession().setMaxInactiveInterval(100000000);
				// add for check login in one place
				String token = OnlineUsers.userLogin(commonAccount.accountName);
				getContext().setCookie("ACCOUNT_TOKEN_HSS", token, -1);
				setRedirect(PageURI.PAGE_WELCOME);
			}
		}

		if (getContext().getRequest().getParameter("Logout") != null) {
			if (!getContext().getSession().isNew()) {
				if (CommonAccount.getInstance(getContext().getSession()) != null) {
					CommonAccount.getInstance(getContext().getSession()).accountLogout(getContext().getSession());
				}
			}
		}
	}

}