package com.intelitune.nwms.inbound;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.sf.click.Context;
import net.sf.click.control.Column;
import net.sf.click.control.Decorator;
import net.sf.click.control.HiddenField;
import net.sf.click.control.Submit;
import net.sf.click.extras.control.TableInlinePaginator;

import com.axis2.services.MCSserviceStub.InttClientDetailWS;
import com.intelitune.ccos.increasedWebService.WMSServiceImp;
import com.intelitune.ccos.increasedWebService.WMSServiceInf;
import com.intelitune.ccos.model.WmsOrder;
import com.intelitune.control.FormTableEx;
import com.intelitune.control.TableEx;
import com.intelitune.export.Exporter;
import com.intelitune.export.Template;
import com.intelitune.export.jxls.DocumentImpl;
import com.intelitune.export.jxls.ExporterImpl;
import com.intelitune.export.jxls.TemplateImpl;
import com.intelitune.nwms.common.BorderPage;
import com.intelitune.nwms.common.Const;
import com.intelitune.nwms.model.InboundOrderItem;
import com.intelitune.nwms.model.StorageItem;
import com.intelitune.nwms.service.CheckingItemService;
import com.intelitune.nwms.service.CheckingItemServiceImpl;
import com.intelitune.nwms.service.InboundOrderItemService;
import com.intelitune.nwms.service.InboundOrderItemServiceImp;
import com.intelitune.nwms.service.OrderItemStateService;
import com.intelitune.nwms.service.OrderItemStateServiceImp;
import com.intelitune.nwms.service.OwnerZoneService;
import com.intelitune.nwms.service.OwnerZoneServiceImp;
import com.intelitune.nwms.service.StorageItemService;
import com.intelitune.nwms.service.StorageItemServiceImp;
import com.intelitune.nwms.test.Test;
/**
 * @author Louis
 *显示进库订单的明细
 */
public class InboundOrderItemDetail extends BorderPage{

	public FormTableEx table = new FormTableEx("table"); 
	public Test test = new Test();
	public WMSServiceInf ws =  WMSServiceImp.getInstance();
	public OwnerZoneService ozs = OwnerZoneServiceImp.getInstance();
	public InboundOrderItemService iois = InboundOrderItemServiceImp.getInstance();
	public CheckingItemService cis =  CheckingItemServiceImpl.getInstance();
	public OrderItemStateService oiss =  OrderItemStateServiceImp.getInstance();
	public StorageItemService sis =  StorageItemServiceImp.getInstance();
	public String title = this.getMessage("orderitemdetail");
	public String position = this.getMessage("ORDERITEMDETAIL");
	public HiddenField hf = new HiddenField("orderId",String.class);
	public String id ;
	public String client;
	public String jobId;
	public WmsOrder or = null;
	public String error7 = null;
	
	public InboundOrderItemDetail(){
		table.setWidth("100%");
		table.setPaginator(new TableInlinePaginator(table));
		table.setPaginatorAttachment(TableEx.PAGINATOR_INLINE);	
		table.setPageSize(10);
		Column column;
		column = new Column("material.code",this.getMessage("code"));
		table.addColumn(column);
		column = new Column("material.description",this.getMessage("description"));
		table.addColumn(column);
		column = new Column("unitPackage.alias",this.getMessage("unit"));
		table.addColumn(column);
		column = new Column("qty",this.getMessage("qty"));
		table.addColumn(column);
		
		table.addColumn(new Column("f_unit_price","单价"));
		table.addColumn(new Column("f_gross_price","总价"));		
		table.addColumn(new Column("f_unit_weight","单位重量"));
		table.addColumn(new Column("f_gross_weight","总重"));
		
		column = new Column("barCode",this.getMessage("barCode"));
		table.addColumn(column);
		column.setDecorator(new Decorator(){
			public String render(Object object, Context context) {
				InboundOrderItem ioi = (InboundOrderItem)object;
//				System.out.println(ioi.getMaterial());
//				System.out.println(ioi.getMaterial().getCode());	
				if(ioi.getMaterial()!=null){
					return "<a href='showBarCode.htm?code=" + ioi.getMaterial().getCode()+"' target='_blank'><img src='http://www.hmglog.com/barcode/BarcodeServlet?data=" + ioi.getMaterial().getCode() +"&width=1&height=50&resolution=200&type=Code128'/></a>";
				}
				return "";
			}
		});
		if(getContext().getRequestParameter("orderId")!=null){
			id = getContext().getRequestParameter("orderId");
		}else{
			id=this.getContext().getSessionAttribute("view_orderId").toString();
		}
		hf.setValue(id);
		try {
			or = ws.findWmsOrderById(Integer.parseInt(id));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		InttClientDetailWS ws = ozs.findOwnerById(or.getClientId());
		client = or.getClientName();
		jobId = or.getJobId();
		table.getControlLink().setParameter("orderId",id);
		Submit print = new Submit("print",this.getMessage("print"),this,"onPrint");
		table.getForm().add(print);
		table.getForm().add(hf);
		print.setAttribute("onclick", "return window.confirm('"
				+ this.getMessage("confirmPrint") + "?');");
	}
	
	public void onRender(){
		table.setRowList(iois.findNormalByOrderId(id));
	}
	
	/**
	 * @return true
	 * @throws Exception
	 * 打印进库单
	 */
	public boolean onPrint() throws Exception{
		if(or.getWmsOrderStatus().getId()!=Const.ORDER_TYPE_INBOUND_INIT){
			error7 = this.getMessage("error7");
			table.setRowList(iois.findByOrderId(id));
			return false;
		}
		List<InboundOrderItem> list = iois.findNormalByOrderId(id);
		String fName = generator(or.getJobId(),list);
		this.setRedirect("../Output/" + fName+".xls");
		return true;
	}
	private String generator(String jobId,List<InboundOrderItem> list) throws Exception{
		DocumentImpl doc = (DocumentImpl)DocumentImpl.getInstance();
		doc.setName("inboundList");
		Template tem = new TemplateImpl("examineGoodListTemp.xls");
		Exporter export = new ExporterImpl();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		WmsOrder wo = ws.findByJobId(jobId);
		HashMap map = new HashMap();
		map.put("date", format.format(ts));
		map.put("jobId", jobId);
		map.put("client", wo.getClientName());
		map.put("clientJobId", wo.getCrn());
		for(InboundOrderItem ioi:list){
			List<StorageItem> si = sis.findStorageItemByProductCodeAndClient_id(" from StorageItem i where i.material.code='" + ioi.getMaterial().getCode()+"' and i.material.inttClientDetailWSId='" + wo.getClientId() +"'");
			Set set = new HashSet();
			for(StorageItem s:si){
				set.add(s.getBin().getCode());
			}
			Iterator iter = set.iterator();
			StringBuffer bcc = new StringBuffer();
			while(iter.hasNext()){
				String binCode = (String)iter.next();
				bcc.append(binCode);
			}
			String bc = bcc.toString();
			ioi.setReferenceBin(bc);
		}
		export.generate(tem, list, map, doc);
		return doc.getFileName()+doc.getName();
	}
}
