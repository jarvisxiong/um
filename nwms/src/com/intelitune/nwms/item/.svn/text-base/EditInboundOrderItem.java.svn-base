package com.intelitune.nwms.item;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.List;

import net.sf.click.control.Form;
import net.sf.click.control.Option;
import net.sf.click.control.Select;
import net.sf.click.control.Submit;
import net.sf.click.control.TextArea;
import net.sf.click.extras.control.AutoCompleteTextField;
import net.sf.click.extras.control.DoubleField;

import com.intelitune.ccos.client.WMSServicePortType;
import com.intelitune.ccos.client.WMSServicePortTypeProxy;
import com.intelitune.nwms.common.BorderPage;
import com.intelitune.nwms.menu.Menu;
import com.intelitune.nwms.model.InboundOrderItem;
import com.intelitune.nwms.model.Material;
import com.intelitune.nwms.model.OrderItem;
import com.intelitune.nwms.model.UnitPackage;
import com.intelitune.nwms.service.MaterialService;
import com.intelitune.nwms.service.MaterialServiceImpl;
import com.intelitune.nwms.service.OrderItemService;
import com.intelitune.nwms.service.OrderItemServiceImp;
import com.intelitune.nwms.service.OrderItemStateService;
import com.intelitune.nwms.service.OrderItemStateServiceImp;
import com.intelitune.nwms.service.UnitPackageService;
import com.intelitune.nwms.service.UnitPackageServiceImpl;

public class EditInboundOrderItem extends BorderPage
{
	Menu me = new Menu();
	public String menuInclude = me.getOrderitem();
	String orderId=(String)this.getContext().getSessionAttribute("orderId");
	Integer clientId=(Integer)this.getContext().getSessionAttribute("clientId");
	String jobId=(String)this.getContext().getSessionAttribute("jobId");
	String editOrderItemId=(String)this.getContext().getSessionAttribute("editOrderItemId");
	public AutoCompleteTextField  material;
	public Select unitPackage=new Select("unitPackage",getMessage("unitPackage"));
	public DoubleField qty=new DoubleField("qty",getMessage("qty"),true);
	public Form form=new Form("form");
	public TextArea remark=new TextArea("remark",getMessage("remark"));

	public Submit cancel=new Submit("cancel",getMessage("cancel"),this,"onCancel");
	
	public MaterialService ms=MaterialServiceImpl.getInstance();
	public UnitPackageService ups= UnitPackageServiceImpl.getInstance();
	public OrderItemService ois= OrderItemServiceImp.getInstance();
	public OrderItemStateService oiss= OrderItemStateServiceImp.getInstance();
	public WMSServicePortType wpt=new WMSServicePortTypeProxy();
	InboundOrderItem orderItem;
	public EditInboundOrderItem() throws NumberFormatException, RemoteException
	{
		orderItem=(InboundOrderItem)ois.findById(editOrderItemId);
		Submit save=new Submit("save",getMessage("save"),this,"onSave" );
		material= new AutoCompleteTextField("material", getMessage("material"), true)
		{
			public List getAutoCompleteList(String s)
			{
				List li1= (List)ms.findByHql(" from Material m where inttClientDetailWSId="+clientId +" and m.alias like '" + s + "%" + "'");
				for (int i = 0; i < li1.size(); i++) {
	            li1.set(i, ((Material)li1.get(i)).getAlias());
	        }
				return li1;
			}
		};
		
		material.setAttribute("onchange", "changePackage(this);");
		form.add(material);
		form.add(unitPackage);
		form.add(qty);
		form.add(remark);
		form.add(save);
		form.add(cancel);

		
		List<UnitPackage> li= ups.findAll();
		for(UnitPackage _unitPackage : li)
		{
			unitPackage.add(new Option(_unitPackage.getId(),_unitPackage.getAlias()));
		}
	}
	public void onRender()
	{
		material.setValue(orderItem.getMaterial().getAlias());
		unitPackage.setValue(orderItem.getUnitPackage().getId());
		qty.setValue(orderItem.getQty().toString());
		remark.setValue(orderItem.getRemark());
	}
	public boolean onSave()
	{
		InboundOrderItem orderItem=new InboundOrderItem();
	
		orderItem.setLastModifiedTime(new Timestamp(System.currentTimeMillis()));
	
		
		orderItem.setMaterial(ms.findByAlias(material.getValue()));
		orderItem.setUnitPackage(ups.getUnitPackage(unitPackage.getValue()));
		orderItem.setQty(qty.getFloat());
		orderItem.setRemark(remark.getValue());
		ois.updateOrderItem(orderItem);
		return true;
	}
	public boolean onCancel()
	{
		this.setRedirect("normalOrderInfo.htm");
		return true;
	}
}
