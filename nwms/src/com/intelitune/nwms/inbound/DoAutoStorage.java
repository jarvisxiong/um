package com.intelitune.nwms.inbound;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.click.control.FileField;
import net.sf.click.control.Form;
import net.sf.click.control.HiddenField;
import net.sf.click.control.Submit;
import net.sf.click.control.Table;

import com.intelitune.ccos.increasedWebService.WMSServiceImp;
import com.intelitune.ccos.increasedWebService.WMSServiceInf;
import com.intelitune.ccos.model.WmsOrder;
import com.intelitune.control.FormTableEx;
import com.intelitune.nwms.model.InboundOrderItem;
import com.intelitune.nwms.service.InboundOrderItemService;
import com.intelitune.nwms.service.InboundOrderItemServiceImp;

public class DoAutoStorage extends com.intelitune.nwms.common.BorderPage {
	
	public String title = this.getMessage("doautostorage");
	
	public FormTableEx table =new FormTableEx();
	public String orderId;
	public String jobId;
	public String clientId;
	public HiddenField hf = new HiddenField("orderId",String.class);
	public FileField ff = new FileField("checkFile",this.getMessage("checkFile"));
	public Form form=new Form();
	public WmsOrder wo;
	
	public WMSServiceInf ws = WMSServiceImp.getInstance();
	
	
	
	public DoAutoStorage(){
		orderId = this.getContext().getRequestParameter("orderId");
		try {
			wo = ws.findWmsOrderById(Integer.parseInt(orderId));
			jobId = wo.getJobId();
			clientId = String.valueOf(wo.getClientId());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		hf.setValue(orderId);
		form.add(ff);
		form.add(new Submit("submit",this.getMessage("submit"),this,"onSubmit"));
		
		
		table.setClass(Table.CLASS_ITS);
		table.setWidth("200px");
		table.setShowBanner(true);
		table.getForm().setButtonAlign(Form.ALIGN_RIGHT);
		table.setPageSize(10);
		
	}
	
	
	public void onRender(){
		
	}
	
	public boolean onSubmit(){
		String url="";
		if(ff.getFileItem().getName().equals("")){
			ff.setError(this.getMessage("filecannotbeempty"));
			return false;
		}
		String path = getContext().getServletContext().getRealPath("/inbound/inboundList");
		int a = ff.getFileItem().getName().length();
		int b = ff.getFileItem().getName().lastIndexOf("\\");
		String fName = ff.getFileItem().getName().substring(b+1);
		Date t = new java.util.Date(System.currentTimeMillis());
		SimpleDateFormat f = new SimpleDateFormat(
				"yyyyMMddHHmmssSSS");
		String timeStr = f.format(t);
		fName = timeStr+ fName;
		File file = new File(path,fName);
		FileOutputStream fos;
		InputStream is;
		try {
			is = ff.getFileItem().getInputStream();
			byte[] buffer = new byte[1024];
			fos = new FileOutputStream(file);
			int c = 0;
			while ((c = is.read(buffer)) != -1) {
				fos.write(buffer);
			}
			fos.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		url= file.getPath();
		//读取手持机的txt数据
		List<TxtData> list_txt=new ArrayList<TxtData>();
		ReadInboundTxt rit=new ReadInboundTxt();
		try{
			 list_txt=rit.getCollecton(url);
		}catch(Exception e){
			e.printStackTrace();
		}
		//订单orderitem
		
		
		
		return true;
	}
	
	
	

}