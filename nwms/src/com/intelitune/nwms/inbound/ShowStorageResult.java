package com.intelitune.nwms.inbound;

import java.rmi.RemoteException;

import net.sf.click.control.Column;
import net.sf.click.control.Form;
import net.sf.click.control.HiddenField;
import net.sf.click.control.Submit;
import net.sf.click.extras.control.TableInlinePaginator;

import com.intelitune.ccos.increasedWebService.WMSServiceImp;
import com.intelitune.ccos.increasedWebService.WMSServiceInf;
import com.intelitune.ccos.model.WmsOrder;
import com.intelitune.control.TableEx;
import com.intelitune.nwms.common.BorderPage;
import com.intelitune.nwms.common.Const;
import com.intelitune.nwms.service.StorageItemService;
import com.intelitune.nwms.service.StorageItemServiceImp;
import com.intelitune.nwms.service.UnstorageItemService;
import com.intelitune.nwms.service.UnstorageItemServiceImp;
/**
 * @author louis
 *显示上架结果
 */
public class ShowStorageResult extends BorderPage {
	public WMSServiceInf ws =  WMSServiceImp.getInstance();
	public StorageItemService sis =  StorageItemServiceImp.getInstance();
	public UnstorageItemService uis =  UnstorageItemServiceImp.getInstance();
	
	public String title = this.getMessage("showstorageresult");
	public String position = this.getMessage("SHOWSTORAGERESULT");
	public String JobId = this.getMessage("jobId");
	public String clientName = this.getMessage("clientName");
	public String isEmergency = this.getMessage("isEmergency");
	public String userName = this.getMessage("userName");
	
	public String sJobId;
	public String sClientName;
	public String sIsEmergency;
	public String sUserName;
	public String sStorage = this.getMessage("sStorage");
	public String sCuo = this.getMessage("sCuo");
	public String sQue = this.getMessage("sQue");
	public String sDuo = this.getMessage("sDuo");
	
	public String orderId;
	public String jobId;
	public WmsOrder wm;
	
	public TableEx storage = new TableEx("storage");
	public TableEx cuo = new TableEx("cuo");
	public TableEx que = new TableEx("que");
	public TableEx duo = new TableEx("duo");
	
	public Form form = new Form("form");
	public HiddenField hf = new HiddenField("orderId",String.class);
	
	public ShowStorageResult(){
		Submit back = new Submit("back",this.getMessage("back"),this,"onBack");
		form.add(hf);
		form.add(back);
		storage.setWidth("100%");
		storage.setPaginator(new TableInlinePaginator(storage));
		storage.setPaginatorAttachment(TableEx.PAGINATOR_INLINE);	
		storage.setPageSize(10);
		cuo.setWidth("100%");
		cuo.setPaginator(new TableInlinePaginator(cuo));
		cuo.setPaginatorAttachment(TableEx.PAGINATOR_INLINE);	
		cuo.setPageSize(10);
		que.setWidth("100%");
		que.setPaginator(new TableInlinePaginator(que));
		que.setPaginatorAttachment(TableEx.PAGINATOR_INLINE);	
		que.setPageSize(10);
		duo.setWidth("100%");
		duo.setPaginator(new TableInlinePaginator(duo));
		duo.setPaginatorAttachment(TableEx.PAGINATOR_INLINE);	
		duo.setPageSize(10);
		orderId = getContext().getRequestParameter("orderId");
		hf.setValue(orderId);
		wm = null;
		try {
			wm = ws.findWmsOrderById(Integer.parseInt(orderId));
			jobId = wm.getJobId();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		sJobId = wm.getJobId();
		sClientName = wm.getClientName();
		if(wm.getIsEmergency()==Const.IS_EMERGENCY){
			sIsEmergency = this.getMessage("yes");
		}else{
			sIsEmergency = this.getMessage("no");
		}
		sUserName = wm.getCreator().getUserName();
		Column column = null;
		column = new Column("material.alias",this.getMessage("alias"));
		storage.addColumn(column);
		column = new Column("material.code",this.getMessage("code"));
		storage.addColumn(column);
		column = new Column("unitPackage.alias",this.getMessage("unit"));
		storage.addColumn(column);
		column = new Column("sn",this.getMessage("sn"));
		storage.addColumn(column);
		column = new Column("bin.code",this.getMessage("binCode"));
		storage.addColumn(column);
		column = new Column("qty",this.getMessage("qty"));
		storage.addColumn(column);
		
		column = new Column("material.alias",this.getMessage("alias"));
		cuo.addColumn(column);
		column = new Column("material.code",this.getMessage("code"));
		cuo.addColumn(column);
		column = new Column("unitPackage.alias",this.getMessage("unit"));
		cuo.addColumn(column);
		column = new Column("sn",this.getMessage("sn"));
		cuo.addColumn(column);
		column = new Column("bin.code",this.getMessage("binCode"));
		cuo.addColumn(column);
		column = new Column("qty",this.getMessage("qty"));
		cuo.addColumn(column);
		
		column = new Column("material.alias",this.getMessage("alias"));
		que.addColumn(column);
		column = new Column("material.code",this.getMessage("code"));
		que.addColumn(column);
		column = new Column("unitPackage.alias",this.getMessage("unit"));
		que.addColumn(column);
		column = new Column("sn",this.getMessage("sn"));
		que.addColumn(column);
		column = new Column("wantQty",this.getMessage("deficiencyQty"));
		que.addColumn(column);
		
		
		column = new Column("material.alias",this.getMessage("alias"));
		duo.addColumn(column);
		column = new Column("material.code",this.getMessage("code"));
		duo.addColumn(column);
		column = new Column("unitPackage.alias",this.getMessage("unit"));
		duo.addColumn(column);
		column = new Column("sn",this.getMessage("sn"));
		duo.addColumn(column);
		column = new Column("bin.code",this.getMessage("binCode"));
		duo.addColumn(column);
		column = new Column("unwantedQty",this.getMessage("unwantedQty"));
		duo.addColumn(column);
	}
	
	public void onRender(){
		storage.setRowList(sis.findNormalByJobId(jobId));
		cuo.setRowList(uis.findCuo(jobId));
		que.setRowList(uis.findQue(jobId));
		duo.setRowList(uis.findDuo(jobId));
	}
	
	public boolean onBack(){
		this.setRedirect("doStorage.htm?orderId=" + orderId);
		return true;
	}
}
