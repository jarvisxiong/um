package com.intelitune.nwms.outbound;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.click.control.Column;
import net.sf.click.control.FileField;
import net.sf.click.control.Form;
import net.sf.click.control.HiddenField;
import net.sf.click.control.Submit;
import net.sf.click.extras.control.TableInlinePaginator;

import com.intelitune.ccos.common.COSServiceCommonPortTypeProxy;
import com.intelitune.ccos.increasedWebService.WMSServiceImp;
import com.intelitune.ccos.increasedWebService.WMSServiceInf;
import com.intelitune.ccos.model.WmsOrder;
import com.intelitune.control.TableEx;
import com.intelitune.export.Template;
import com.intelitune.export.jxls.DocumentImpl;
import com.intelitune.export.jxls.ExporterInboundImpl;
import com.intelitune.export.jxls.TemplateImpl;
import com.intelitune.nwms.common.CommonAccount;
import com.intelitune.nwms.menu.Menu;
import com.intelitune.nwms.model.Bin;
import com.intelitune.nwms.model.ItemState;
import com.intelitune.nwms.model.LogPickedItem;
import com.intelitune.nwms.model.OutboundRePickItem;
import com.intelitune.nwms.model.OutboundRePutAwayItem;
import com.intelitune.nwms.model.OutboundTxtData;
import com.intelitune.nwms.model.PickedItem;
import com.intelitune.nwms.model.PickingItem;
import com.intelitune.nwms.model.StorageItem;
import com.intelitune.nwms.service.BinService;
import com.intelitune.nwms.service.BinServiceImp;
import com.intelitune.nwms.service.ItemStateService;
import com.intelitune.nwms.service.ItemStateServiceImp;
import com.intelitune.nwms.service.LogPickedItemService;
import com.intelitune.nwms.service.LogPickedItemServiceImp;
import com.intelitune.nwms.service.MaterialService;
import com.intelitune.nwms.service.MaterialServiceImpl;
import com.intelitune.nwms.service.OutboundRePickItemService;
import com.intelitune.nwms.service.OutboundRePickItemServiceImp;
import com.intelitune.nwms.service.OutboundRePutAwayItemService;
import com.intelitune.nwms.service.OutboundRePutAwayItemServiceImp;
import com.intelitune.nwms.service.OutboundTxtDataService;
import com.intelitune.nwms.service.OutboundTxtDataServiceImp;
import com.intelitune.nwms.service.PickedItemService;
import com.intelitune.nwms.service.PickedItemServiceImp;
import com.intelitune.nwms.service.PickingItemService;
import com.intelitune.nwms.service.PickingItemServiceImp;
import com.intelitune.nwms.service.StorageItemService;
import com.intelitune.nwms.service.StorageItemServiceImp;

public class UploadCheckFile extends com.intelitune.nwms.common.BorderPage {
	
	Menu me = new Menu();
	
	public String menuInclude = me.getOutbound();
	
	public String position = this.getMessage("file_upload_position");
	
	public String title;
	
	public WmsOrder wo=new WmsOrder();
	
	public WMSServiceInf service=WMSServiceImp.getInstance();
	public LogPickedItemService lpis=LogPickedItemServiceImp.getInstance();
	public COSServiceCommonPortTypeProxy ccpt = new COSServiceCommonPortTypeProxy();
	public PickingItemService pis= PickingItemServiceImp.getInstance();
	public OutboundRePickItemService orpis= OutboundRePickItemServiceImp.getInstance();
	public OutboundRePutAwayItemService orpais= OutboundRePutAwayItemServiceImp.getInstance();
	public PickedItemService opis= PickedItemServiceImp.getInstance();
	public OutboundTxtDataService otds= OutboundTxtDataServiceImp.getInstance();
	public MaterialService ms= MaterialServiceImpl.getInstance();
	public BinService bs= BinServiceImp.getInstance();
	public ItemStateService iss= ItemStateServiceImp.getInstance();
	public StorageItemService sis= StorageItemServiceImp.getInstance();
	
//	public List<OutboundRePickItem> list_pick;
//	public List<OutboundRePutAwayItem> list_putaway;
//	public List<PickedItem> list_right;
	
	public Form form=new Form();
	
	public String order_id;
	public String job_id;
	public String warehouse_id;
	public String client_id;
	
	public Form form_finish=new Form();
	
	public TableEx table_correct =new TableEx();
	
	public TableEx table_back =new TableEx();
	
	public TableEx table_fetch =new TableEx();
	
	public String correct_table=this.getMessage("correct_table");
	public String back_table=this.getMessage("back_table");
	public String fetch_table=this.getMessage("fetch_table");
	
	public HiddenField hf=new HiddenField("id",String.class);
	public HiddenField hf1=new HiddenField("id",String.class);
	public HiddenField hf2=new HiddenField("id",String.class);
	
	public FileField ff = new FileField("checkFile",this.getMessage("checkFile"));

	
	public String code1="";
	public String code2="";
	
	public Form form1=new Form();
	
	public Form form2=new Form();
	
	public String accountName = CommonAccount.getInstance(getContext().getSession()).accountName;
	
	public UploadCheckFile(){
		order_id=this.getContext().getRequestParameter("id");
		if(order_id!=null){
			getContext().setSessionAttribute("ttu", order_id);
		}else{
			order_id = String.valueOf(getContext().getSessionAttribute("ttu"));
		}
		hf.setValue(order_id);
//		if(order_id!=null){
//			this.getContext().getSession().setAttribute("orderupload_id", order_id);
//		}
		hf.setValue(order_id);
		hf1.setValue(order_id);
		hf2.setValue(order_id);
		try{
			 wo=service.findWmsOrderById(Integer.parseInt(order_id));
			 job_id=wo.getJobId();
			 warehouse_id=wo.getWarehouseId();
			 client_id=wo.getClientId().toString();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		form.add(ff);
		form.add(hf);
		Submit submit = new Submit("submit",this.getMessage("submit"),this,"onSubmit");
		
		form.add(submit);
		
		form_finish.add(new Submit(this.getMessage("finish"),this,"onFinish"));
		
			table_correct.setWidth("100%");
			table_correct.setPaginator(new TableInlinePaginator(table_correct));
			table_correct.setPaginatorAttachment(TableEx.PAGINATOR_INLINE);	
			table_correct.setPageSize(10);
			table_correct.addColumn(new Column("product_code",this.getMessage("product_code")));
			table_correct.addColumn(new Column("bin_code",this.getMessage("bin_code")));
			table_correct.addColumn(new Column("sn",this.getMessage("sn")));
			table_correct.addColumn(new Column("qty",this.getMessage("qty")));
			

			table_back.setWidth("100%");
			table_back.setPaginator(new TableInlinePaginator(table_back));
			table_back.setPaginatorAttachment(TableEx.PAGINATOR_INLINE);	
			table_back.setPageSize(10);
			table_back.addColumn(new Column("product_code",this.getMessage("product_code")));
			table_back.addColumn(new Column("bin_code",this.getMessage("bin_code")));
			table_back.addColumn(new Column("sn",this.getMessage("sn")));
			table_back.addColumn(new Column("qty",this.getMessage("qty")));
			

			table_fetch.setWidth("100%");
			table_fetch.setPaginator(new TableInlinePaginator(table_fetch));
			table_fetch.setPaginatorAttachment(TableEx.PAGINATOR_INLINE);	
			table_fetch.setPageSize(10);
			table_fetch.addColumn(new Column("product_code",this.getMessage("product_code")));
			table_fetch.addColumn(new Column("bin_code",this.getMessage("bin_code")));
			table_fetch.addColumn(new Column("sn",this.getMessage("sn")));
			table_fetch.addColumn(new Column("qty",this.getMessage("qty")));
			
			
			 List<PickedItem> list_right1=opis.findPickedItemByJobId(job_id);
			 List<OutboundRePickItem>  list_pick1=orpis.findOutboundRePickItemByJobId(job_id);
			List<OutboundRePutAwayItem>  list_putaway1=orpais.findOutboundRePutAwayItemByJobId(job_id);
			Submit  submit1= new Submit("print",this.getMessage("print_error"),this,"onPrint");
			Submit  submit2= new Submit("complete",this.getMessage("complete"),this,"onComplete");
			Submit  submit3=new Submit("enforce_complete",this.getMessage("enforce"),this,"onEnforce");
			form1.add(hf1);
			if(list_pick1.size()==0&&list_putaway1.size()==0){
				submit1.setDisabled(true);
				submit2.setDisabled(false);
			}
			else{
				submit1.setDisabled(false);
				submit2.setDisabled(true);
			}
			form1.add(submit1);
			form1.add(submit2);
			form2.add(hf2);
			//form2.add(submit3);
			if(list_putaway1.size()!=0){
				form2.setAttribute("onSubmit", "alert('"+this.getMessage("putaway_not_null")+"');return false;");
			}
		}
		
		
		


	public void onRender(){
		title=wo.getJobId()+this.getMessage("order_upload");
		List<OutboundGoodModel> list_correct=new ArrayList<OutboundGoodModel>();
		List<OutboundGoodModel> list_back=new ArrayList<OutboundGoodModel>();
		List<OutboundGoodModel> list_fetch=new ArrayList<OutboundGoodModel>();
		
		List<PickingItem> list_item=pis.findPickingItemByOrderIdhebing(order_id);//需要获取的货物
		
		 List<PickedItem> list_right1=opis.findPickedItemByJobId(job_id);
		 List<OutboundRePickItem>  list_pick1=orpis.findOutboundRePickItemByJobId(job_id);
		List<OutboundRePutAwayItem>  list_putaway1=orpais.findOutboundRePutAwayItemByJobId(job_id);
		if(list_right1.size()==0&&list_pick1.size()==0&&list_putaway1.size()==0){
			for(int i=0;i<list_item.size();i++){
				PickingItem pi=list_item.get(i);
				OutboundGoodModel ogm=new OutboundGoodModel();
				ogm.setBin_code(pi.getBin().getCode());
				ogm.setProduct_code(pi.getMaterial().getCode());
				ogm.setSn(pi.getSn());
				ogm.setQty(pi.getQty());
				list_fetch.add(ogm);
			}
			table_fetch.setRowList(list_fetch);
		}else{
			for(int i=0;i<list_right1.size();i++){
				PickedItem pi=list_right1.get(i);
				OutboundGoodModel ogm=new OutboundGoodModel();
				ogm.setJob_id(pi.getJobId());
				ogm.setBin_code(pi.getBin().getCode());
				ogm.setProduct_code(pi.getMaterial().getCode());
				ogm.setSn(pi.getSn());
				ogm.setQty(pi.getQty());
				list_correct.add(ogm);
			}
			if(list_pick1.size()!=0){
				for(int i=0;i<list_pick1.size();i++){
					OutboundRePickItem orpi=list_pick1.get(i) ;
					OutboundGoodModel ogm=new OutboundGoodModel();
					ogm.setJob_id(orpi.getJobId());
					System.out.print(orpi.getMaterial().getCode());
					ogm.setProduct_code(orpi.getMaterial().getCode());
					ogm.setBin_code(orpi.getBin().getCode());
					ogm.setSn(orpi.getSn());
					ogm.setQty(orpi.getQty());
					list_fetch.add(ogm);
				}
				code1="http://www.hmglog.com/barcode/BarcodeServlet?data="+list_pick1.get(0).getTempJobId() +"&width=1&height=50&resolution=200&type=Code128";
			}
			if(list_putaway1.size()!=0){
				for(int i=0;i<list_putaway1.size();i++){
					OutboundRePutAwayItem orpai=list_putaway1.get(i) ;
					OutboundGoodModel ogm=new OutboundGoodModel();
					ogm.setJob_id(orpai.getJobId());
					System.out.print(orpai.getMaterial().getCode());
					ogm.setProduct_code(orpai.getMaterial().getCode());
					ogm.setBin_code(orpai.getBin().getCode());
					ogm.setSn(orpai.getSn());
					ogm.setQty(orpai.getQty());
					list_back.add(ogm);
				}
				code2="http://www.hmglog.com/barcode/BarcodeServlet?data="+list_putaway1.get(0).getTempJobId() +"&width=1&height=50&resolution=200&type=Code128";
			}
			table_correct.setRowList(list_correct);
			table_back.setRowList(list_back);
			table_fetch.setRowList(list_fetch);
		}
		
		
		
	}
	
	public boolean onSubmit() throws Exception{
		String url="";
		if(ff.getFileItem().getName().equals("")){
			ff.setError(this.getMessage("filecannotbeempty"));
			return false;
		}
		String path = getContext().getServletContext().getRealPath("/outbound/outboundlist");
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
				buffer = new byte[1024];
			}
			fos.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		url= file.getPath();
		//读取TXT数据
		List<OutboundGoodModel> list_txt=new ArrayList<OutboundGoodModel>();
		ExamineOutboundGood eog=new ExamineOutboundGood();
		try{
			 list_txt=eog.getCollecton(url);
		}catch(Exception e){
			e.printStackTrace();
		}
		List<OutboundRePickItem> list_pick=orpis.findOutboundRePickItemByJobId(job_id);
		List<OutboundRePutAwayItem>  list_putaway=orpais.findOutboundRePutAwayItemByJobId(job_id);
		//添加到TXTDATA对象中
		for(int i=0;i<list_txt.size();i++){
			OutboundGoodModel ogm=list_txt.get(i);
			OutboundTxtData otd=new OutboundTxtData();
			//如果是第一次
			if(list_pick.size()==0&&list_putaway.size()==0){
				if(ogm.getJob_id().equals(job_id)){
					otd.setBinCode(ogm.getBin_code());
					otd.setProductCode(ogm.getProduct_code());
					otd.setQty(ogm.getQty()+"");
					otd.setSN(ogm.getSn());
					otd.setJobId(ogm.getJob_id());
					otd.setOrderJobId(job_id);
					otd.setOpType(OutboundTxtData.outbound);
					otds.addOutboundTxtData(otd);
				}
			}else{//不是第一次
				//如果和fetch业务编号吻合
				if(list_pick.size()!=0){
					if(ogm.getJob_id().trim().toUpperCase().equals(list_pick.get(0).getTempJobId().trim().toUpperCase())){
						otd.setBinCode(ogm.getBin_code());
						otd.setProductCode(ogm.getProduct_code());
						otd.setSN(ogm.getSn());
						otd.setJobId(ogm.getJob_id());
						otd.setOrderJobId(job_id);
						otd.setQty(ogm.getQty()+"");
						otd.setOpType(OutboundTxtData.outbound);
						otds.addOutboundTxtData(otd);
					}
				}
				//如何和back业务编号吻合
				if(list_putaway.size()!=0){
					if(ogm.getJob_id().trim().toUpperCase().equals(list_putaway.get(0).getTempJobId().trim().toUpperCase())){
						otd.setBinCode(ogm.getBin_code());
						otd.setProductCode(ogm.getProduct_code());
						otd.setSN(ogm.getSn());
						otd.setQty(ogm.getQty()+"");
						otd.setJobId(ogm.getJob_id());
						otd.setOrderJobId(job_id);
						otd.setOpType(OutboundTxtData.inbound);
						otds.addOutboundTxtData(otd);
					}
				}
			}
		}
		List<OutboundTxtData> list_result=otds.comformTxtByJobId(job_id);//获取实际数据库中所有上传TXT的整合信息
		List<PickingItem> list_item=pis.findPickingItemByOrderIdhebing(order_id);//需要获取的货物
		//删除原有结果
		orpis.delAllByJobId(job_id);
		orpais.delAllByJobId(job_id);
		opis.delAllByJobId(job_id);
		//做比较保存结果
		List<PickedItem> list_picked=new ArrayList<PickedItem>();
		List<OutboundRePickItem> list_repick=new ArrayList<OutboundRePickItem>();
		List<OutboundRePutAwayItem> list_reputaway=new ArrayList<OutboundRePutAwayItem>();
		String uuid_repick=opis.createUuid();
		String uuid_reputaway=opis.createUuid();
		//比较
		for(int i=0;i<list_result.size();i++){
			OutboundTxtData otd=list_result.get(i);
			//如果有SN的情况下
			if(!"N/A".equals(otd.getSN())&&!"".equals(otd.getSN())){
				int j=0;
				for(;j<list_item.size();j++){
					PickingItem pi=list_item.get(j);
					if(otd.getSN().trim().equals(pi.getSn().trim())&&otd.getBinCode().trim().equals(pi.getBin().getCode().trim())&&otd.getProductCode().equals(pi.getMaterial().getCode().trim())){
						PickedItem picked=new PickedItem();
						picked.setJobId(job_id);
						Bin bin = bs.findBinByCode(warehouse_id, otd.getBinCode());
						if(bin==null){
							this.getContext().setFlashAttribute("eee","没有这样的库位:"+otd.getBinCode());
							throw new Exception();
						}
						picked.setBin(bin);
						picked.setMaterial(ms.findMaterialByCode(otd.getProductCode(),client_id));
						picked.setSn(otd.getSN());
						picked.setInvoice(pi.getInvoice());
						
						//michael_wang 2009-11-18
						picked.setF_currency(pi.getF_currency());
						picked.setF_gross_price(pi.getF_gross_price());
						picked.setF_gross_weight(pi.getF_gross_weight());
						picked.setF_unit_price(pi.getF_unit_price());
						picked.setF_unit_weight(pi.getF_unit_weight());
						
						picked.setQty(1.0f);
						picked.setUnitPackage(pi.getUnitPackage());
						picked.setState(iss.findItemStateByCode(ItemState.NORMAL));
						picked.setWarehouse(pi.getWarehouse());
						picked.setOrderId(order_id);
						list_picked.add(picked);
						pi.setIsCorrect(1);
						break;
					}
					//在列表里没有相应的
					if(j==list_item.size()-1){
						OutboundRePutAwayItem orpai=new OutboundRePutAwayItem();
						orpai.setJobId(job_id);
						orpai.setBin(bs.findBinByCode(warehouse_id, otd.getBinCode()));
						orpai.setMaterial(ms.findMaterialByCode(otd.getProductCode(),client_id));
						orpai.setSn(otd.getSN());
						orpai.setInvoice(pi.getInvoice());
						
						//michael_wang 2009 11-18
						orpai.setF_currency(pi.getF_currency());
						orpai.setF_gross_price(pi.getF_gross_price());
						orpai.setF_gross_weight(pi.getF_gross_weight());
						orpai.setF_unit_price(pi.getF_unit_price());
						orpai.setF_unit_weight(pi.getF_unit_weight());
						
						orpai.setQty(1.0f);
						orpai.setUnitPackage(pi.getUnitPackage());
						orpai.setTempJobId(uuid_reputaway);
						orpai.setState(iss.findItemStateByCode(ItemState.NORMAL));
						orpai.setWarehouse(pi.getWarehouse());
						orpai.setOrderId(order_id);
						list_reputaway.add(orpai);
					}
				}
				
			}
			//无SN的情况下
			if("N/A".equals(otd.getSN())||"".equals(otd.getSN())){
				int j=0;
				for(;j<list_item.size();j++){
					PickingItem pi=list_item.get(j);
					//如果有料号和库位都吻合
					if(otd.getBinCode().trim().equals(pi.getBin().getCode().trim())&&otd.getProductCode().trim().equals(pi.getMaterial().getCode().trim())){
						//如果数量相等
						Bin bin = bs.findBinByCode(warehouse_id, otd.getBinCode());
						if(bin==null){
							this.getContext().setFlashAttribute("eee","没有这样的库位:"+otd.getBinCode());
							throw new Exception();
						}
						if(Float.parseFloat(otd.getQty())==pi.getQty()){
							PickedItem picked=new PickedItem();
							picked.setJobId(job_id);
							picked.setBin(bs.findBinByCode(warehouse_id, otd.getBinCode()));
							picked.setMaterial(ms.findMaterialByCode(otd.getProductCode(),client_id));
							picked.setSn(otd.getSN());
							picked.setQty(pi.getQty());
							picked.setState(iss.findItemStateByCode(ItemState.NORMAL));
							picked.setUnitPackage(pi.getUnitPackage());
							picked.setInvoice(pi.getInvoice());
							
							//michael_wang 2009-11-18
							picked.setF_currency(pi.getF_currency());
							picked.setF_gross_price(pi.getF_gross_price());
							picked.setF_gross_weight(pi.getF_gross_weight());
							picked.setF_unit_price(pi.getF_unit_price());
							picked.setF_unit_weight(pi.getF_unit_weight());
							
							
							picked.setWarehouse(pi.getWarehouse());
							picked.setOrderId(order_id);
							list_picked.add(picked);
							pi.setIsCorrect(1);
							break;
						}else if(Float.parseFloat(otd.getQty())>pi.getQty()){//如果实际数量多
							PickedItem picked=new PickedItem();
							picked.setJobId(job_id);
							picked.setBin(bs.findBinByCode(warehouse_id, otd.getBinCode()));
							picked.setMaterial(ms.findMaterialByCode(otd.getProductCode(),client_id));
							picked.setSn(otd.getSN());
							picked.setUnitPackage(pi.getUnitPackage());
							picked.setQty(pi.getQty());
							picked.setInvoice(pi.getInvoice());
							
							//michael_wang 2009-11-18
							picked.setF_currency(pi.getF_currency());
							picked.setF_gross_price(pi.getF_gross_price());
							picked.setF_gross_weight(pi.getF_gross_weight());
							picked.setF_unit_price(pi.getF_unit_price());
							picked.setF_unit_weight(pi.getF_unit_weight());
							
							picked.setState(iss.findItemStateByCode(ItemState.NORMAL));
							picked.setWarehouse(pi.getWarehouse());
							picked.setOrderId(order_id);
							list_picked.add(picked);
							OutboundRePutAwayItem orpai=new OutboundRePutAwayItem();
							orpai.setJobId(job_id);
							orpai.setBin(bs.findBinByCode(warehouse_id, otd.getBinCode()));
							orpai.setMaterial(ms.findMaterialByCode(otd.getProductCode(),client_id));
							orpai.setSn(otd.getSN());
							orpai.setInvoice(pi.getInvoice());
							
							//michael_wang 2009-11-18
							orpai.setF_currency(pi.getF_currency());
							orpai.setF_gross_price(pi.getF_gross_price());
							orpai.setF_gross_weight(pi.getF_gross_weight());
							orpai.setF_unit_price(pi.getF_unit_price());
							orpai.setF_unit_weight(pi.getF_unit_weight());
							
							orpai.setState(iss.findItemStateByCode(ItemState.NORMAL));
							orpai.setUnitPackage(pi.getUnitPackage());
							float c=Float.parseFloat(otd.getQty())-pi.getQty();
							orpai.setQty(c);
							orpai.setTempJobId(uuid_reputaway);
							orpai.setWarehouse(pi.getWarehouse());
							orpai.setOrderId(order_id);
							list_reputaway.add(orpai);
							pi.setIsCorrect(1);
							break;
						}else{//如果实际数量少
							PickedItem picked=new PickedItem();
							picked.setJobId(job_id);
							picked.setBin(bs.findBinByCode(warehouse_id, otd.getBinCode()));
							picked.setMaterial(ms.findMaterialByCode(otd.getProductCode(),client_id));
							picked.setSn(otd.getSN());
							picked.setInvoice(pi.getInvoice());
							
							//michael_wang 2009-11-18
							picked.setF_currency(pi.getF_currency());
							picked.setF_gross_price(pi.getF_gross_price());
							picked.setF_gross_weight(pi.getF_gross_weight());
							picked.setF_unit_price(pi.getF_unit_price());
							picked.setF_unit_weight(pi.getF_unit_weight());
							
							picked.setQty(Float.parseFloat(otd.getQty()));
							picked.setState(iss.findItemStateByCode(ItemState.NORMAL));
							picked.setUnitPackage(pi.getUnitPackage());
							picked.setWarehouse(pi.getWarehouse());
							picked.setOrderId(order_id);
							list_picked.add(picked);
							OutboundRePickItem orpi=new OutboundRePickItem();
							orpi.setJobId(job_id);
							orpi.setBin(bs.findBinByCode(warehouse_id, otd.getBinCode()));
							orpi.setMaterial(ms.findMaterialByCode(otd.getProductCode(),client_id));
							orpi.setSn(otd.getSN());
							orpi.setInvoice(pi.getInvoice());
							
							//michael_wang 2009-11-18
							orpi.setF_currency(pi.getF_currency());
							orpi.setF_gross_price(pi.getF_gross_price());
							orpi.setF_gross_weight(pi.getF_gross_weight());
							orpi.setF_unit_price(pi.getF_unit_price());
							orpi.setF_unit_weight(pi.getF_unit_weight());
							
							float d=pi.getQty()-Float.parseFloat(otd.getQty());
							orpi.setQty(d);
							orpi.setState(iss.findItemStateByCode(ItemState.NORMAL));
							orpi.setTempJobId(uuid_repick);
							orpi.setUnitPackage(pi.getUnitPackage());
							orpi.setWarehouse(pi.getWarehouse());
							orpi.setOrderId(order_id);
							list_repick.add(orpi);
							pi.setIsCorrect(1);
							break;
						}
						
					}
					if(j==list_item.size()-1){
						OutboundRePutAwayItem orpai=new OutboundRePutAwayItem();
						orpai.setJobId(job_id);
						orpai.setBin(bs.findBinByCode(warehouse_id, otd.getBinCode()));
						orpai.setMaterial(ms.findMaterialByCode(otd.getProductCode(),client_id));
						orpai.setSn(otd.getSN());
						orpai.setQty(Float.parseFloat(otd.getQty()));
						orpai.setTempJobId(uuid_reputaway);
						orpai.setUnitPackage(pi.getUnitPackage());
						orpai.setInvoice(pi.getInvoice());
						
						//michael_wang 2009-11-18
						orpai.setF_currency(pi.getF_currency());
						orpai.setF_gross_price(pi.getF_gross_price());
						orpai.setF_gross_weight(pi.getF_gross_weight());
						orpai.setF_unit_price(pi.getF_unit_price());
						orpai.setF_unit_weight(pi.getF_unit_weight());
						
						orpai.setState(iss.findItemStateByCode(ItemState.NORMAL));
						orpai.setWarehouse(pi.getWarehouse());
						orpai.setOrderId(order_id);
						list_reputaway.add(orpai);
					}
				}
				
				
			}
		}
		for(int k=0;k<list_item.size();k++){
			if(list_item.get(k).getIsCorrect()!=1){
				PickingItem pi=list_item.get(k);
				OutboundRePickItem orpi=new OutboundRePickItem();
				orpi.setJobId(job_id);
				orpi.setBin(pi.getBin());
				orpi.setMaterial(pi.getMaterial());
				orpi.setSn(pi.getSn());
				orpi.setQty(pi.getQty());
				orpi.setInvoice(pi.getInvoice());
				
				//michael_wang 2009-11-18
				orpi.setF_currency(pi.getF_currency());
				orpi.setF_gross_price(pi.getF_gross_price());
				orpi.setF_gross_weight(pi.getF_gross_weight());
				orpi.setF_unit_price(pi.getF_unit_price());
				orpi.setF_unit_weight(pi.getF_unit_weight());
				
				orpi.setTempJobId(uuid_repick);
				orpi.setState(iss.findItemStateByCode(ItemState.NORMAL));
				orpi.setUnitPackage(pi.getUnitPackage());
				orpi.setWarehouse(pi.getWarehouse());
				orpi.setOrderId(order_id);
				list_repick.add(orpi);
			}
		}
		//比较结束
		//结果存入数据库
		for(int i=0;i<list_picked.size();i++){
			opis.addPickedItem(list_picked.get(i));
		}
		for(int i=0;i<list_repick.size();i++){
			orpis.addOutboundRePickItem(list_repick.get(i));
		}
		for(int i=0;i<list_reputaway.size();i++){
			orpais.addOutboundRePutAwayItem(list_reputaway.get(i));
		}
		
		this.setRedirect("uploadCheckFile.htm?&id="+order_id);
		return true;
	}
	
	public boolean onFinish(){
		List<PickingItem> list_item=pis.findPickingItemByOrderId(order_id);
		//添加出库记录
		for(int i=0;i<list_item.size();i++){
			PickingItem pi=list_item.get(i);
			LogPickedItem lpi=new LogPickedItem();
			lpi.setBin(pi.getBin());
			lpi.setCreationTime(new Timestamp(System.currentTimeMillis()));
			lpi.setInvoice(pi.getInvoice());
			
			//michael_wang 2009-11-18
			lpi.setF_currency(pi.getF_currency());
			lpi.setF_gross_price(pi.getF_gross_price());
			lpi.setF_gross_weight(pi.getF_gross_weight());
			lpi.setF_unit_price(pi.getF_unit_price());
			lpi.setF_unit_weight(pi.getF_unit_weight());
			
			//michael_wang 20091214
			lpi.setHawb(pi.getHawb());
			lpi.setMawb(pi.getMawb());
			
			lpi.setOutboundInvoice(pi.getOutboundInvoice());
			lpi.setItemType(pi.getItemType());
			lpi.setJobId(pi.getJobId());
			lpi.setMaterial(pi.getMaterial());
			lpi.setOrderId(order_id);
			lpi.setCrn(wo.getCrn());
			lpi.setQty(pi.getQty());
			lpi.setRemark(pi.getRemark());
			lpi.setSn(pi.getSn());
			lpi.setState(iss.findItemStateByCode(ItemState.NORMAL));
			lpi.setUnitPackage(pi.getUnitPackage());
			lpi.setWarehouse(pi.getWarehouse());
			lpis.addLogPickedItem(lpi);
		}
		
		for(int i=0;i<list_item.size();i++){
			PickingItem pi=list_item.get(i);
			pis.delPickingItem(pi.getId());
		}
			
		try {
			ccpt.alterStatusToCompleted(job_id,accountName);
			service.alterToOutboundComplete(Integer.parseInt(order_id));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setRedirect(OrderCheckout.class);
		return true;
	}
	
	
	public boolean onPrint() throws Exception{
		List<OutboundRePickItem>  xia=orpis.findOutboundRePickItemByJobId(job_id);
		List<OutboundRePutAwayItem>  shang=orpais.findOutboundRePutAwayItemByJobId(job_id);
		String fName = generator(job_id,xia,shang);
		this.setRedirect("../Output/" + fName+".xls");
		return true;
	}
	private String generator(String jobId,List<OutboundRePickItem> xia,List<OutboundRePutAwayItem> shang) throws Exception{
		DocumentImpl doc = (DocumentImpl)DocumentImpl.getInstance();
		doc.setName("outboundList");
		Template tem = new TemplateImpl("outboundProcess.xls");
		ExporterInboundImpl export = new ExporterInboundImpl();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		try{
			 wo=service.findWmsOrderById(Integer.parseInt(order_id));
		}catch(Exception e){
			e.printStackTrace();
		}
		HashMap map = new HashMap();
		map.put("date", format.format(ts));
		if(xia.size()!=0){
			map.put("xiatempJobId", xia.get(0).getTempJobId());
		}
		if(shang.size()!=0){
			map.put("shangtempJobId", shang.get(0).getTempJobId());
		}
		map.put("job_id", job_id);
		map.put("client", wo.getClientName());
		export.generate(tem, xia,shang, map, doc);
		return doc.getFileName()+doc.getName();
	}
	
	
	public boolean onComplete(){
		List<PickingItem> list_item=pis.findPickingItemByOrderId(order_id);
		//添加出库记录
		for(int i=0;i<list_item.size();i++){
			PickingItem pi=list_item.get(i);
			LogPickedItem lpi=new LogPickedItem();
			lpi.setBin(pi.getBin());
			lpi.setCreationTime(new Timestamp(System.currentTimeMillis()));
			lpi.setInvoice(pi.getInvoice());
			
			//michael_wang 2009-11-18
			lpi.setF_currency(pi.getF_currency());
			lpi.setF_gross_price(pi.getF_gross_price());
			lpi.setF_gross_weight(pi.getF_gross_weight());
			lpi.setF_unit_price(pi.getF_unit_price());
			lpi.setF_unit_weight(pi.getF_unit_weight());
			
			
			//michael_wang 20091214
			lpi.setHawb(pi.getHawb());
			lpi.setMawb(pi.getMawb());
			
			lpi.setOutboundInvoice(pi.getOutboundInvoice());
			lpi.setJobId(pi.getJobId());
			lpi.setMaterial(pi.getMaterial());
			lpi.setOrderId(order_id);
			lpi.setQty(pi.getQty());
			lpi.setCrn(wo.getCrn());
			lpi.setRemark(pi.getRemark());
			lpi.setSn(pi.getSn());
			lpi.setState(iss.findItemStateByCode(ItemState.NORMAL));
			lpi.setUnitPackage(pi.getUnitPackage());
			lpi.setWarehouse(pi.getWarehouse());
			lpis.addLogPickedItem(lpi);
		}
		for(int i=0;i<list_item.size();i++){
			PickingItem pi=list_item.get(i);
			pis.delPickingItem(pi.getId());
		}
		
		service.alterToOutboundComplete(Integer.parseInt(order_id));
		try {
			ccpt.alterStatusToCompleted(job_id,CommonAccount.getInstance(getContext().getSession()).accountName);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setRedirect(OrderList.class);
		return true;
	}
	
	public boolean onEnforce(){
		List<OutboundRePickItem> list_pick=orpis.findOutboundRePickItemByJobId(job_id);
		//返还少拿的货物
		for(int i=0;i<list_pick.size();i++){
			OutboundRePickItem orpi=list_pick.get(i);
			StorageItem si=new StorageItem();
			si.setBin(orpi.getBin());
			si.setCreationTime(new Timestamp(System.currentTimeMillis()));
			si.setMaterial(orpi.getMaterial());
			si.setQty(orpi.getQty());
			si.setRemark(orpi.getRemark());
			si.setSn(orpi.getSn());
			si.setState(iss.findItemStateByCode(ItemState.NORMAL));
			si.setUnitPackage(orpi.getUnitPackage());
			si.setWarehouse(orpi.getWarehouse());
			
			//michael_wang 2009-11-18
			si.setF_currency(orpi.getF_currency());
			si.setF_gross_price(orpi.getF_gross_price());
			si.setF_gross_weight(orpi.getF_gross_weight());
			si.setF_unit_price(orpi.getF_unit_price());
			si.setF_unit_weight(orpi.getF_unit_weight());
			
			sis.addStorageItem(si);
		}	
		List<PickedItem> list_picked=opis.findPickedItemByOrder_id(order_id);
		for(int i=0;i<list_picked.size();i++){
			PickedItem pi=list_picked.get(i);
			LogPickedItem lpi=new LogPickedItem();
			lpi.setBin(pi.getBin());
			lpi.setCreationTime(new Timestamp(System.currentTimeMillis()));
			lpi.setInvoice(pi.getInvoice());
			
			//michael_wang 2009-11-18
			lpi.setF_currency(pi.getF_currency());
			lpi.setF_gross_price(pi.getF_gross_price());
			lpi.setF_gross_weight(pi.getF_gross_weight());
			lpi.setF_unit_price(pi.getF_unit_price());
			lpi.setF_unit_weight(pi.getF_unit_weight());
			
			lpi.setJobId(pi.getJobId());
			lpi.setMaterial(pi.getMaterial());
			lpi.setOrderId(order_id);
			lpi.setQty(pi.getQty());
			lpi.setRemark(pi.getRemark());
			lpi.setSn(pi.getSn());
			lpi.setState(iss.findItemStateByCode(ItemState.NORMAL));
			lpi.setUnitPackage(pi.getUnitPackage());
			lpi.setWarehouse(pi.getWarehouse());
			lpis.addLogPickedItem(lpi);
		}
		service.alterToOutboundComplete(Integer.parseInt(order_id));
		this.setRedirect(OrderList.class);
		return true;
	}
}