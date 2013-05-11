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
import com.intelitune.nwms.service.CheckingItemService;
import com.intelitune.nwms.service.CheckingItemServiceImpl;
import com.intelitune.nwms.service.UncheckedItemService;
import com.intelitune.nwms.service.UncheckedItemServiceImp;
/** 
 * @author louis
 *显示验货结果
 */
public class ShowCheckResult extends BorderPage {

	public WMSServiceInf ws =  WMSServiceImp.getInstance();
//	public OwnerZoneService ozs = new OwnerZoneServiceImp();
//	public InboundOrderItemService iois = new InboundOrderItemServiceImp();
	public CheckingItemService cis =  CheckingItemServiceImpl.getInstance();
//	public OrderItemStateService oiss = new OrderItemStateServiceImp();
//	public MaterialService ms = new MaterialServiceImpl();
	public UncheckedItemService uis =  UncheckedItemServiceImp.getInstance();
	public String title = this.getMessage("showcheckresult");
	public String position = this.getMessage("SHOWCHECKRESULT");
	public String JobId = this.getMessage("jobId");
	public String clientName = this.getMessage("clientName");
	public String isEmergency = this.getMessage("isEmergency");
	public String userName = this.getMessage("userName");
	
	public String sJobId;
	public String sClientName;
	public String sIsEmergency;
	public String sUserName;
	public String sChecking = this.getMessage("sChecking");
	public String sBad = this.getMessage("sBad");
	public String sQue = this.getMessage("sQue");
	public String sDuo = this.getMessage("sDuo");
	
	public String orderId;
	public String jobId;
	public WmsOrder wm;
	
	public TableEx checking = new TableEx("checking");
	public TableEx bad = new TableEx("bad");
	public TableEx que = new TableEx("que");
	public TableEx duo = new TableEx("duo");
	
	public Form form = new Form("form");
	public HiddenField hf = new HiddenField("orderId",String.class);
	
	public ShowCheckResult(){
		Submit back = new Submit("back",this.getMessage("back"),this,"onBack");
		form.add(hf);
		form.add(back);
		checking.setWidth("100%");
		checking.setPaginator(new TableInlinePaginator(checking));
		checking.setPaginatorAttachment(TableEx.PAGINATOR_INLINE);	
		checking.setPageSize(10);
		bad.setWidth("100%");
		bad.setPaginator(new TableInlinePaginator(bad));
		bad.setPaginatorAttachment(TableEx.PAGINATOR_INLINE);	
		bad.setPageSize(10);
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
		checking.addColumn(column);
		column = new Column("material.code",this.getMessage("code"));
		checking.addColumn(column);
		column = new Column("unitPackage.alias",this.getMessage("unit"));
		checking.addColumn(column);
		column = new Column("sn",this.getMessage("sn"));
		checking.addColumn(column);
		column = new Column("arrivedQty",this.getMessage("arrivedQty"));
		checking.addColumn(column);
		
		column = new Column("material.alias",this.getMessage("alias"));
		bad.addColumn(column);
		column = new Column("material.code",this.getMessage("code"));
		bad.addColumn(column);
		column = new Column("unitPackage.alias",this.getMessage("unit"));
		bad.addColumn(column);
		column = new Column("sn",this.getMessage("sn"));
		bad.addColumn(column);
		column = new Column("destroyedQty",this.getMessage("destroyedQty"));
		bad.addColumn(column);
		
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
		column = new Column("unwantedQty",this.getMessage("unwantedQty"));
		duo.addColumn(column);
	}
	
	public void onRender(){
		checking.setRowList(cis.findNormalByJobId(jobId));
		bad.setRowList(uis.findBad(jobId));
		que.setRowList(uis.findQue(jobId));
		duo.setRowList(uis.findDuo(jobId));
	}
	
	public boolean onBack(){
		this.setRedirect("upLoadCheckItems.htm?orderId=" + orderId);
		return true;
	}
}
