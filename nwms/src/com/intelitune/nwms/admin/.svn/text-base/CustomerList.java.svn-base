package com.intelitune.nwms.admin;

import java.util.Date;

import net.sf.click.control.ActionLink;
import net.sf.click.control.Column;
import net.sf.click.control.Form;
import net.sf.click.control.HiddenField;
import net.sf.click.control.Label;
import net.sf.click.control.Submit;
import net.sf.click.control.Table;
import net.sf.click.control.TextField;
import net.sf.click.extras.control.LinkDecorator;

import com.intelitune.control.RichTextArea;
import com.intelitune.control.TableEx;
import com.intelitune.nwms.common.BorderPage;
import com.intelitune.nwms.common.CommonAccount;
import com.intelitune.nwms.menu.Menu;
import com.intelitune.nwms.model.Customer;
import com.intelitune.nwms.service.CustomerService;
import com.intelitune.nwms.service.CustomerServiceImp;

public class CustomerList extends BorderPage {
	Menu me = new Menu();
	public String menuInclude = me.getSystemset();
	public String position = this.getMessage("customerpos");
	public String title = this.getMessage("titlecustomerpos");

	public Form form = new Form("form");
	public TextField tf_customerName = new TextField("tf_customerName", true);
	public TextField tf_customerAddress = new TextField("tf_customerAddress",
			true);
	public RichTextArea rta_customerRemark = new RichTextArea("remark");

	public TableEx table = new TableEx("table");
	public ActionLink editLink = new ActionLink("edit", this, "onEditLink");
	private ActionLink[] links;
	public HiddenField hf_customerId = new HiddenField("hf_customerId",
			String.class);

	public CustomerService customerService = CustomerServiceImp.getInstance(); 

	private CommonAccount commonAccount = null;

	public CustomerList() {
		commonAccount = CommonAccount.getInstance(getContext().getSession());
		makeForm();
		makeTable();
	}

	public void makeForm() {
		form.setButtonAlign("center");
		form.setLabelAlign("left");
		form.setValidate(true);
		form.setJavaScriptValidation(true);
		form.add(hf_customerId);
		tf_customerName.setAttribute("onblur", "validate(this);");
		form.add(tf_customerName);
		Label l_msgRepeatedName = new Label("l_msgRepeatedName", "");
		form.add(l_msgRepeatedName);
		tf_customerAddress.setAttribute("onblur", "validate(this);");
		form.add(tf_customerAddress);
		rta_customerRemark.setCols(30);
		rta_customerRemark.setRows(5);
		form.add(rta_customerRemark);

		form.add(new Submit("submit", this, "onSaveClick"));
		form.add(new Submit("reset", this, "onResetClick"));

		form.setLabelStyle(this.getContext().getSessionAttribute(LABELSTYLE)
				.toString());
//		form.add(super.mustInputValue);
	}

	public void makeTable() {
//		table.setPaginator(new InlinePaginator());
		table.setPaginatorAttachment(Table.PAGINATOR_INLINE);
		table.setPageSize(5);
		table.setHoverRows(true);
//		table.setClass(this.getContext().getSessionAttribute(TABLECLASS)
//				.toString());
//		table.setStyle("color", this.getContext().getSessionAttribute(
//				TABLESTYLE).toString());
		table.setWidth("100%");
		table.addColumn(new Column("name"));
		table.addColumn(new Column("address"));
		table.addColumn(new Column("creationTime")).setFormat(
				"{0,date,yyyy-MM-dd hh:mm:ss}");

		Column column = new Column("action");
		column.setSortable(false);
		links = new ActionLink[] { editLink };
		column.setDecorator(new LinkDecorator(table, links, "id"));
		table.addColumn(column);

		table.setRowList(customerService.findAll());
	}

	public void onInit() {
		super.onInit();
		super.checkAccess(form, this.getClass().getName(), links, menuInclude);
	}

	public void onGet() {
		super.onGet();
		if (getContext().getRequestParameter("value") != null) {
			hf_customerId.setValueObject(getContext().getRequestParameter(
					"value").toString());
		}
	}

	@SuppressWarnings("unchecked")
	public boolean onSaveClick() {
		if (form.isValid()) {
			Customer c = new Customer();
			if (hf_customerId.getValueObject() == null
					|| hf_customerId.getValueObject().equals("")) { // new
				if (existTheSameCustomerName(tf_customerName.getValue())) {
					tf_customerName.setValue(null);
					return false;
				}
				c.setName(tf_customerName.getValue());
				c.setAddress(tf_customerAddress.getValue());
				c.setCreationTime(new Date());
				c.setRemark(rta_customerRemark.getValue());
				customerService.save(c);
				onResetClick();
			} else { // modify
				c = (Customer) customerService.findById(
						Integer.parseInt(hf_customerId.getValueObject()
								.toString()));
				c.setName(tf_customerName.getValue());
				c.setAddress(tf_customerAddress.getValue());
				c.setRemark(rta_customerRemark.getValue());
				customerService.update(c);
				onResetClick();
			}
		}
		return true;
	}

	// 判断是否有重名的角色名称
	private boolean existTheSameCustomerName(String CustomerName) {
		if (customerService.findByQuery(
				" from Customer as c where c.name = '" + CustomerName + "' ")
				.size() > 0) {
			if (commonAccount.accountLocaleLanguage.equals("en")) {
				form.getField("l_msgRepeatedName").setLabel(
						"There exists the same customer name!");
			} else if (commonAccount.accountLocaleLanguage.equals("cn")) {
				form.getField("l_msgRepeatedName").setLabel("已存在一个相同的客户名称!");
			}
			return true;
		} else {
			form.getField("l_msgRepeatedName").setLabel("");
			return false;
		}
	}

	public boolean onResetClick() {
		this.setRedirect(this.getClass());
		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean onEditLink() {
		Customer c = (Customer) customerService.findById(
				editLink.getValueInteger());
		tf_customerName.setValue(c.getName());
		tf_customerAddress.setValue(c.getAddress());
		rta_customerRemark.setValue(c.getRemark());
		return true;
	}

}
