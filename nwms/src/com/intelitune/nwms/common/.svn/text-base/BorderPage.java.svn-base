package com.intelitune.nwms.common;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.Cookie;

import net.sf.click.Page;
import net.sf.click.control.AbstractLink;
import net.sf.click.control.ActionLink;
import net.sf.click.control.Form;
import net.sf.click.control.PageLink;
import net.sf.click.control.Select;

import com.intelitune.nwms.admin.AccessError;
import com.intelitune.nwms.roleAccess.RoleManager;

import edu.yale.its.tp.cas.client.filter.CASFilter;

public class BorderPage extends Page {
	
	public String username="";

	public String newWindowJS; // jump new JS Window

	public String boxTitle;

	public static CommonAccount commonAccount = null;

	public String javascriptInclude = "/assets/js/include.htm";

	public String defaultTableClass = "blue2";

	public String defaultTableStyle = "black";

	public String defaultLableStyle = "black";

	public String defaultTemp = "/templates/wms/wms_template.htm";

	public String TABLECLASS = "table-class";

	public String TABLESTYLE = "table-style";

	public String LABELSTYLE = "label-style";

	public String DETAIL_TABLE_TITLE_BGCOLOR;

	public String DETAIL_TABLE_TITLE_STYLE;

	public String DETAIL_TABLE_ROW1_COLOR;

	public String ONMOUSEOVER;

	public String ONMOUSEOUT;

	public String DETAIL_TABLE_STYLE;

	public String BORDERCOLOR;

	public PageLink n1;

	public PageLink n2;

	public PageLink n3;

	public PageLink n4;
	
	public String logout = "";

	//added by success for show bar by role access
	public String strSystemSet = "";
	public String strBasicManagement = "";
	public String strOrderManagement = "";
	public String strDeliveryManagement = "";
	public String strTransTaskManagement = "";
	public String strBillingManagement = "";
	
	public String accountLoginCheck = "";

	public String loadData = "Loading data...";
	
	public String welcomeInfo = "";	
	
//	public HiddenField mustInputValue = new HiddenField("mustInputValue", String.class);

	@SuppressWarnings("unchecked")
	public String getTemplate() {
		return defaultTemp;
	}

	public void setBoxTitle(String boxTitle) {
		this.boxTitle = boxTitle;
	}

	@SuppressWarnings("unchecked")
	public BorderPage() {		
		commonAccount = CommonAccount.getInstance(getContext().getSession());
//		welcomeInfo = "娆㈣繋" + commonAccount.accountName + "璁块棶璐ц繍蹇嚎!";
		welcomeInfo = "welcome!";
//		if(getContext().getSession().getAttribute(CASFilter.CAS_FILTER_USER) != null){
//			logout = "../loginsso.htm?Logout=true";
//		}else{
//			logout = "../login.htm?Logout=true";
//		}
		changeLogoutHref();
		hideBar();
		IsSessionExpired();
	}

	public void onInit() {		
		
		changeLanguage();
	}
	
	public void changeLogoutHref() {
		/*
		if(commonAccount.accountLoginType == Const.ACCOUNT_LOGIN_TYPE_STATION) {
			logout = "../login.htm?Logout=true";
		}
		else if(commonAccount.accountLoginType == Const.ACCOUNT_LOGIN_TYPE_OWNER) {
			logout = "../loginOwner.htm?Logout=true";
		}
		else if(commonAccount.accountLoginType == Const.ACCOUNT_LOGIN_TYPE_VENDOR) {
			logout = "../loginVendor.htm?Logout=true";
		}
		*/
	}

	/**
	 * check user access for each page
	 * 
	 * @author Success
	 * @date 2008-06-10
	 * @edited by success 2008-12-05
	 * @param form
	 * @param ClassName
	 * @param actionLink
	 */
	public void checkAccess(Form form, String ClassName, ActionLink[] actionLink, String menuInclude) {
		String scope = RoleManager.isOperationAlive(commonAccount.accountRoleName, ClassName, null);
		if (scope == null || scope.equals("n")) {
			this.setRedirect(AccessError.class);
			return;
		} else if (scope.equals("r")) {
			if (form != null) {
				form.setDisabled(true);
			}
			for (int i = 0; i < actionLink.length; i++) {
				actionLink[i].setDisabled(true);
			}
		}
	}
	
	/**
	 * @author Success
	 * @date 2008-12-05
	 */
	public void hideBar() {
		/*
		if(commonAccount.accountLoginType == Const.ACCOUNT_LOGIN_TYPE_STATION) {
			String scope_userList = RoleManager.isOperationAlive(commonAccount.accountRoleName, UserList.class.getName(), null);
			if(scope_userList == null || scope_userList.equals("n")){
				strSystemSet = "";
			}
			else {
				strSystemSet = "<td><a href=\"../admin/userList.htm\"><img src=\"../templates/huoyunkuaixian/img/xitongshezhi.jpg\" /></a></td>";
			}
		}
		
		if(commonAccount.accountLoginType == Const.ACCOUNT_LOGIN_TYPE_STATION) {
			String scope_conveyanceManage = RoleManager.isOperationAlive(commonAccount.accountRoleName, ConveyanceManage.class.getName(), null);
			if(scope_conveyanceManage == null || scope_conveyanceManage.equals("n")){
				strBasicManagement = "";
			}
			else {
				strBasicManagement = "<td><a href=\"../basicset/conveyanceManage.htm\"><img src=\"../templates/huoyunkuaixian/img/jichuguanli.jpg\" /></a></td>";
			}
		}
		
		if(commonAccount.accountLoginType == Const.ACCOUNT_LOGIN_TYPE_STATION || commonAccount.accountLoginType == Const.ACCOUNT_LOGIN_TYPE_OWNER) {
			String scope_orderList = RoleManager.isOperationAlive(commonAccount.accountRoleName, OrderList.class.getName(), null);
			if(scope_orderList == null || scope_orderList.equals("n")){
				strOrderManagement = "";
			}
			else {
				strOrderManagement = "<td><a href=\"../tmsorder/orderList.htm\"><img src=\"../templates/huoyunkuaixian/img/dingdanguanli.jpg\" /></a></td>";
			}
		}
		
		if(commonAccount.accountLoginType == Const.ACCOUNT_LOGIN_TYPE_STATION) {
			String scope_deliveryList = RoleManager.isOperationAlive(commonAccount.accountRoleName, DeliveryList.class.getName(), null);
			if(scope_deliveryList == null || scope_deliveryList.equals("n")){
				strDeliveryManagement = "";
			}
			else {
				strDeliveryManagement = "<td><a href=\"../delivery/deliveryList.htm\"><img src=\"../templates/huoyunkuaixian/img/diaodufenpei.jpg\" /></a></td>";
			}
		}
		
		if(commonAccount.accountLoginType == Const.ACCOUNT_LOGIN_TYPE_STATION || commonAccount.accountLoginType == Const.ACCOUNT_LOGIN_TYPE_VENDOR) {
			String scope_transTaskList = RoleManager.isOperationAlive(commonAccount.accountRoleName, TransTaskList.class.getName(), null);
			if(scope_transTaskList == null || scope_transTaskList.equals("n")){
				strTransTaskManagement = "";
			}
			else {
				strTransTaskManagement = "<td><a href=\"../transTask/transTaskList.htm\"><img src=\"../templates/huoyunkuaixian/img/paichedanguanli.jpg\" /></a></td>";
			}
		}
		
		if(commonAccount.accountLoginType == Const.ACCOUNT_LOGIN_TYPE_STATION) {
			String scope_feeItemSearch = RoleManager.isOperationAlive(commonAccount.accountRoleName, FeeItemSearch.class.getName(), null);
			if(scope_feeItemSearch == null || scope_feeItemSearch.equals("n")){
				strBillingManagement = "";
			}
			else {
				strBillingManagement = "<td><a href=\"../billing/feeItemSearch.htm\"><img src=\"../templates/huoyunkuaixian/img/jiesuanguanli.jpg\" /></a></td>";
			}
		}
		*/
	}

	public void changeLanguage() {
		try {
//			if (commonAccount.accountLocaleLanguage.equals("en")) {
//				this.getContext().setLocale(Locale.ENGLISH);
//			} else if (commonAccount.accountLocaleLanguage.equals("cn")) {
				this.getContext().setLocale(Locale.CHINESE);
//			}
		} catch (Exception e) {
			setRedirect(PageURI.SESSION_EXPIRED_PAGE);
		}
	}

	public void onGet() {
//		mustInputValue.setValue(this.getMessage("mustInputValue"));
	}

	
	public boolean IsSessionExpired() {		
		if (CommonAccount.getInstance(getContext().getSession()) == null) {
			setRedirect(PageURI.SESSION_EXPIRED_PAGE);
			return true;
		} else {
			// add for check login in one place
			
			//String username = "";
			username = CommonAccount.getInstance(getContext().getSession()).accountName;
			Cookie cookie = getContext().getCookie("ACCOUNT_TOKEN_HSS");
			String token = null;
			if (cookie != null)
				token = cookie.getValue();
			if (!OnlineUsers.checkLogin(username, token)) {
				alertAndRedirect("Your account has been logon, please logout and login again!", PageURI.SESSION_EXPIRED_PAGE);
				return true;
			}
		}
		return false;
	}
	

	public void alertAndRedirect(String text, String url) {
		accountLoginCheck = "<script language=\"JavaScript\">alert('" + Common.replaceForXML(text) + "'); location=\"" + url.replaceAll("&", "&amp;") + "\";</script>";
	}

	/**
	 * @author Success
	 * @date 2008-08-07 Get Current Time
	 * @return Timestamp of current time
	 */
	public static java.sql.Timestamp getCurrentTime() {
		return new java.sql.Timestamp(System.currentTimeMillis());
	}

	/**
	 * replace new js to js window (Example pdf print open new window)
	 * 
	 * @author Success
	 * @date 2008-08-12
	 * @param URL
	 */
	public void openNewWindow(String URL) {
		newWindowJS = "<script language=\"JavaScript\">" + "window.open('" + URL + "')" + "</script>";
	}

	// added by success 2008-09-22
	/*
	public void fillCustomerDDL(Select sel_customer) {
		String customerName = null;
		long customerId = -1;
		InttUser iu = new InttUser();
		UserService userService = (UserService) ctx.getBean("userService");
		McsService mcsService = (McsService) ctx.getBean("mcsService");
		iu = userService.getUserById(commonAccount.accountId);
		if (iu != null) {
			if (iu.getCustomer() != null && iu.getCustomer() != -1) {
				customerName = mcsService.findById(iu.getCustomer());
				customerId = iu.getCustomer();
			}
		}
		if (customerName == null && customerId == -1) { // show all owner
			sel_customer.add(Option.EMPTY_OPTION);
			sel_customer.addAll(mcsService.findAllClient(), "id", "name");
		} else { // show by owner
			sel_customer.add(new Option(customerId, customerName));
		}
	}
	*/

	/**
	 * the name exist or not
	 * 
	 * @param ModelName
	 * @param Name
	 * @return
	 */
	// public boolean existTheSameName(String ModelName, String Name) {
	// RoleService roleService = (RoleService) ctx.getBean("roleService");
	// if (roleService.queryRoleByHql(
	// "from " + ModelName + " as t where t.name = '" + Name + "' ")
	// .size() > 0) {
	// return true;
	// } else {
	// return false;
	// }
	// }
	
	public String getHtmlImports(Select province, Select city) {
		Map model = new HashMap();
		model.put("provinceId", province.getId());
		model.put("cityId", city.getId());

		// populate-on-select.js is a Veloprovince template which is rendered
		// directly
		// from this Page
		String templatePath = "/assets/js/populate-on-select.js";
		String template = getContext().renderTemplate(templatePath, model);

		// Click's JavaScript and CSS import parser does not yet handle
		// multiline
		// imports so coerce it into a single line
		return "<script type=\"text/javascript\">" + template.replace('\n', ' ') + "</script>\n";
	}

	/**
	 * for set the button css
	 * 
	 * @author success
	 * @date 2008-09-28
	 * @param form
	 * @param btnName
	 */
	public void setBtnStyle(Form form, String btnName) {
		form.getField(btnName).setStyle("border-width", "0px");
		form.getField(btnName).setStyle("cursor", "hand");
		form.getField(btnName).setStyle("background-image", "url(../templates/white/img/buttonBack.gif)");
		form.getField(btnName).setStyle("background-position", "top");
		form.getField(btnName).setStyle("background-repeat", "repeat-x");
		form.getField(btnName).setStyle("margin", "0px");
		form.getField(btnName).setStyle("padding-left", "5px");
		form.getField(btnName).setStyle("padding-right", "5px");
		form.getField(btnName).setStyle("height", "22px");
		form.getField(btnName).setStyle("font-family", "Verdana, Arial, Helvetica, sans-serif");
		form.getField(btnName).setStyle("font-size", "10pt");
		form.getField(btnName).setStyle("color", "#FFFFFF");
	}

}