package com.intelitune.nwms.search;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import net.sf.click.control.Form;
import net.sf.click.control.Option;
import net.sf.click.control.Select;
import net.sf.click.control.Submit;
import net.sf.click.extras.control.DateField;

import com.axis2.services.MCSserviceStub.InttClientDetailWS;
import com.intelitune.export.Exporter;
import com.intelitune.export.Template;
import com.intelitune.export.jxls.DocumentImpl;
import com.intelitune.export.jxls.ExporterImpl;
import com.intelitune.export.jxls.TemplateImpl;
import com.intelitune.nwms.menu.Menu;
import com.intelitune.nwms.model.Warehouse;
import com.intelitune.nwms.service.LogPickedItemService;
import com.intelitune.nwms.service.LogPickedItemServiceImp;
import com.intelitune.nwms.service.LogStorageItemService;
import com.intelitune.nwms.service.LogStorageItemServiceImp;
import com.intelitune.nwms.service.OwnerZoneService;
import com.intelitune.nwms.service.OwnerZoneServiceImp;
import com.intelitune.nwms.service.StorageItemService;
import com.intelitune.nwms.service.StorageItemServiceImp;
import com.intelitune.nwms.service.WarehouseService;
import com.intelitune.nwms.service.WarehouseServiceImp;
import com.intelitune.nwms.service.ZoneStateService;
import com.intelitune.nwms.service.ZoneStateServiceImp;

public class RecordSearch extends com.intelitune.nwms.common.BorderPage {
	Menu me = new Menu();
	
	public String menuInclude = me.getSearch();
	
	public String position = this.getMessage("record_search");
	
	public String title=this.getMessage("record_search");
	
	public Form form=new Form();
	
	public Select ownerSelect;
	public Select warehouseSelect;
	public Select patternSelect;
	public OwnerZoneService ozs=OwnerZoneServiceImp.getInstance();
	public StorageItemService sis=StorageItemServiceImp.getInstance();
	public ZoneStateService zss=ZoneStateServiceImp.getInstance();
	public WarehouseService ws=WarehouseServiceImp.getInstance();
	public LogStorageItemService lsis=LogStorageItemServiceImp.getInstance();
	public LogPickedItemService lpis=LogPickedItemServiceImp.getInstance();
	
	public DateField df1=new DateField("date1",this.getMessage("start_date"),true);
	public DateField df2=new DateField("date2",this.getMessage("end_date"),true);
	
	public void onInit(){
		super.onInit();
		List<InttClientDetailWS> list=ozs.findOwnerList();
		ownerSelect.add(new Option(""));
	        for (Iterator<InttClientDetailWS> i = list.iterator(); i.hasNext();) {
	        	InttClientDetailWS icd = (InttClientDetailWS) i.next();
	        	ownerSelect.add(new Option(icd.getId(),icd.getCnName()));
	        }
	    List<Warehouse> list1=ws.findWarehouseList();
	    warehouseSelect.add(new Option(""));
        for (Iterator<Warehouse> i = list1.iterator(); i.hasNext();) {
        	Warehouse w = (Warehouse) i.next();
        	warehouseSelect.add(new Option(w.getId(),w.getAlias()));
        }
        patternSelect.add(new Option(""));
        patternSelect.add(new Option(0,this.getMessage("inbound")));
        patternSelect.add(new Option(1,this.getMessage("outbound")));
	}
	
	public RecordSearch(){
		ownerSelect = new Select(this.getMessage("zone_customer_name"),true);
		ownerSelect.setRequired(true);
		warehouseSelect = new Select(this.getMessage("warehouse"),true);
		warehouseSelect.setRequired(true);
		patternSelect=new Select(this.getMessage("job_pattern"),true);
		form.add(ownerSelect);
		form.add(warehouseSelect);
		form.add(patternSelect);
		form.add(df1);
		df1.setFormatPattern("yy-MM-dd");
		form.add(df2);
		df2.setFormatPattern("yy-MM-dd");
//		form.add(new Submit(this.getMessage("search"),this,"onSearch"));
//		form.add(new Submit(this.getMessage("reset"),this,"onReset"));
		form.add(new Submit(this.getMessage("print"),this,"onPrint"));
	}
		
		
	
	
	public void onRender(){	
		
	}
	
	public boolean onPrint(){
		if(form.isValid()){
			String warehouse_id=warehouseSelect.getValue();
			String owner_id=ownerSelect.getValue();
			String pattern_id=patternSelect.getValue();
			String date1=df1.getValue();
			String date2=df2.getValue();
			String fName="";
			try {
				fName = generator(owner_id,warehouse_id,pattern_id,date1,date2);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.setRedirect("../Output/" + fName+".xls");
		//处理打印
		}
		return true;
	}
	
	
	private String generator(String owner_id,String warehouse_id,String pattern_id,String date1,String date2) throws Exception{
		//String warehouseName=ws.findNameById(warehouse_id);
//		List result=new ArrayList();
//		if("0".equals(pattern_id)){
//			result=lsis.findLogStorageItemByWarehouseAndClient(owner_id, warehouse_id,date1,date2);
//		}
//		if("1".equals(pattern_id)){
//			result=lpis.findLogPickedItemByWarehouseAndClient(owner_id, warehouse_id,date1,date2);
//		}
//		InttClientDetailWS idws=ozs.findOwnerById(Integer.parseInt(owner_id));
//		DocumentImpl doc = (DocumentImpl)DocumentImpl.getInstance();
//		doc.setName("logItemList");
//		Template tem = new TemplateImpl("logItemTemp.xls");
//		Exporter export = new ExporterImpl();
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		Timestamp ts = new Timestamp(System.currentTimeMillis());
//		HashMap map = new HashMap();
//		map.put("clientName", idws.getCnName());
//		map.put("date", ts);
//		map.put("warehouse", warehouseName);
//		map.put("start", date1);
//		map.put("end", date2);
//		if("0".equals(pattern_id)){
//			map.put("pattern", this.getMessage("inbound"));
//		}
//		if("1".equals(pattern_id)){
//			map.put("pattern", this.getMessage("outbound"));		
//		}
//		export.generate(tem, result, map, doc);
//		return doc.getFileName()+doc.getName();
		
		String warehouseName=ws.findNameById(warehouse_id);
		InttClientDetailWS idws=ozs.findOwnerById(Integer.parseInt(owner_id));
		DocumentImpl doc = (DocumentImpl)DocumentImpl.getInstance();	
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		HashMap map = new HashMap();
		map.put("clientName", idws.getCnName());
		map.put("date", ts);
		map.put("warehouse", warehouseName);
		map.put("start", date1);
		map.put("end", date2);
		Exporter export = new ExporterImpl();
		List result = new ArrayList();
		Template tem = null;	
		if("0".equals(pattern_id)){
			result=lsis.findLogStorageItemByWarehouseAndClient(owner_id, warehouse_id,date1,date2);
			doc.setName("logItemList_In");		
			tem = new TemplateImpl("logItemTemp_In.xls");
			map.put("pattern", this.getMessage("inbound"));			
		}else{
			result=lpis.findLogPickedItemByWarehouseAndClient(owner_id, warehouse_id,date1,date2);
			doc.setName("logItemList_Out");			
			tem = new TemplateImpl("logItemTemp_Out.xls");
			map.put("pattern", this.getMessage("outbound"));				
		}			
		if(tem != null){
			export.generate(tem, result, map, doc);
			return doc.getFileName()+doc.getName();
		}
		
		return null;
		
	}

}