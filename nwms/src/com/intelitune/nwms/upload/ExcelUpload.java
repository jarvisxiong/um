package com.intelitune.nwms.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import net.sf.click.control.FileField;
import net.sf.click.control.Form;
import net.sf.click.control.Option;
import net.sf.click.control.Select;
import net.sf.click.control.Submit;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.axis2.services.MCSserviceStub.InttClientDetailWS;
import com.intelitune.ccos.model.WmsOrder;
import com.intelitune.nwms.material.MaterialMappingModel;
import com.intelitune.nwms.menu.Menu;
import com.intelitune.nwms.model.Invoice;
import com.intelitune.nwms.model.ItemState;
import com.intelitune.nwms.model.StorageItem;
import com.intelitune.nwms.model.UnitPackage;
import com.intelitune.nwms.model.Warehouse;
import com.intelitune.nwms.search.StorageItemSearch;
import com.intelitune.nwms.service.BinService;
import com.intelitune.nwms.service.BinServiceImp;
import com.intelitune.nwms.service.InvoiceService;
import com.intelitune.nwms.service.InvoiceServiceImp;
import com.intelitune.nwms.service.ItemStateService;
import com.intelitune.nwms.service.ItemStateServiceImp;
import com.intelitune.nwms.service.MaterialService;
import com.intelitune.nwms.service.MaterialServiceImpl;
import com.intelitune.nwms.service.OwnerZoneService;
import com.intelitune.nwms.service.OwnerZoneServiceImp;
import com.intelitune.nwms.service.PickedItemService;
import com.intelitune.nwms.service.PickedItemServiceImp;
import com.intelitune.nwms.service.StorageItemService;
import com.intelitune.nwms.service.StorageItemServiceImp;
import com.intelitune.nwms.service.WarehouseService;
import com.intelitune.nwms.service.WarehouseServiceImp;

public class ExcelUpload extends com.intelitune.nwms.common.BorderPage {
	
public String position = this.getMessage("file_upload_position");
	Menu me = new Menu();
	
	public String menuInclude = me.getSystemSetting();	

	public String title=this.getMessage("import_storageitem");
	
	public WmsOrder wo=new WmsOrder();
	
	public FileField ff = new FileField("excelupload",this.getMessage("excelupload"));
	public Form form=new Form();
	
	public BinService bs= BinServiceImp.getInstance();
	public WarehouseService ws= WarehouseServiceImp.getInstance();
	public MaterialService ms= MaterialServiceImpl.getInstance();
	public PickedItemService pis= PickedItemServiceImp.getInstance();
	public ItemStateService iss= ItemStateServiceImp.getInstance();
	public Select warehouseSelect;
	public Select ownerSelect;
	public OwnerZoneService ozs= OwnerZoneServiceImp.getInstance();
	public StorageItemService sis= StorageItemServiceImp.getInstance();
	public InvoiceService iis= InvoiceServiceImp.getInstance();
	public String excelError="";
	public ExcelUpload(){
		ownerSelect = new Select(this.getMessage("zone_customer_name"),true);
		ownerSelect.setRequired(true);
		warehouseSelect = new Select(this.getMessage("warehouse"),true);
		warehouseSelect.setRequired(true);
		form.add(ownerSelect);
		form.add(warehouseSelect);
		form.add(ff);
		Submit submit = new Submit("submit",this.getMessage("submit"),this,"onSubmit");
		form.add(submit);
	}
	
	public void onInit(){
		List<InttClientDetailWS> list=ozs.findOwnerList();
		ownerSelect.add(new Option(0,""));
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
	}
	
	public boolean onSubmit(){
		String owner_id=ownerSelect.getValue();
		String warehouse_id=warehouseSelect.getValue();
		String url="";
		if(ff.getFileItem().getName().equals("")){
			ff.setError(this.getMessage("filecannotbeempty"));
			return false;
		}
		String path = getContext().getServletContext().getRealPath("/import");
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
		
		//读取EXCEL第一行标题到list_title
		List<MappingDto> list_title=new ArrayList<MappingDto>();
		List<StorageItem> list_item=new ArrayList<StorageItem>();
		HSSFWorkbook workbook;
		try {
			workbook = new HSSFWorkbook(new FileInputStream(url));
			HSSFSheet childSheet = workbook.getSheetAt(0);
			for(int i=0;i<childSheet.getRow(0).getPhysicalNumberOfCells();i++){
				HSSFCell cell = childSheet.getRow(0).getCell(i);
				MappingDto md=new MappingDto();
				md.setColumn(i);
				md.setValue(formatCell(cell));
				list_title.add(md);
			}

			//判断格式是否正确
			String[] strs={"储位编号","产品编号","产品序列号","库存数量","发票号"};
			for(int i=0;i<strs.length;i++){
				String str=strs[i];
				for(int j=0;j<list_title.size();j++){
					MappingDto mapping=list_title.get(j);
					if(str.equals(mapping.getValue().trim())){
						break;
					}
					if(j==list_title.size()-1){
						excelError=this.getMessage("excel_error");
						return false;
					}
				}
			}
			
			//封装数据
			String uuid=pis.createUuid();
			for(int i=1;i<childSheet.getPhysicalNumberOfRows();i++){
				if("".equals(formatCell(childSheet.getRow(i).getCell(1)))){
					break;
				}
				StorageItem si=new StorageItem();
				for(int j=0;j<list_title.size();j++){
					MappingDto md=list_title.get(j);
					if("储位编号".trim().equals(md.getValue().trim())){
						si.setBin(bs.findBinByCode(warehouse_id, formatCell(childSheet.getRow(i).getCell(md.getColumn())).toUpperCase().trim()));
					}
					else if("产品编号".equals(md.getValue().trim())){
						String bin_code=formatCell(childSheet.getRow(i).getCell(md.getColumn()));
//						System.out.print(bin_code);
//						if(bin_code.substring(bin_code.length()-2, bin_code.length()).equals(".0")){
//							String[] str=bin_code.split("\\.");
//							String s=str[0];
//							si.setMaterial(ms.findMaterialByCode(s, owner_id));
//							si.setUnitPackage(ms.findMaterialByCode(s, owner_id).getUnitPackages().get(0));
//						}else{
						si.setMaterial(ms.findMaterialByCode(bin_code, owner_id));
						List<UnitPackage> list_up=ms.findMaterialByCode(bin_code, owner_id).getUnitPackages();
						Iterator<UnitPackage> it=list_up.iterator();
						while(it.hasNext()){			
								UnitPackage up=it.next();
								if(up!=null){
									si.setUnitPackage(up);	
									break;
								}
						}
//						si.setUnitPackage(ms.findMaterialByCode(bin_code, owner_id).getUnitPackages().get(0));
//						}
					}else if("产品序列号".equals(md.getValue().trim())){
						si.setSn(formatCell(childSheet.getRow(i).getCell(md.getColumn())));
					}else if("库存数量".equals(md.getValue().trim())){
						if(!"".equals(formatCell(childSheet.getRow(i).getCell(md.getColumn())))&&!"NULL".equals(formatCell(childSheet.getRow(i).getCell(md.getColumn())).toUpperCase())){
							si.setQty(Float.parseFloat(formatCell(childSheet.getRow(i).getCell(md.getColumn()))));
						}
						
					}else if("发票号".equals(md.getValue().trim())){
						if(childSheet.getRow(i).getCell(md.getColumn())!=null&&!"".equals(childSheet.getRow(i).getCell(md.getColumn())+"")){
							String invoice_code=formatCell(childSheet.getRow(i).getCell(md.getColumn()));
//							if(invoice_code.substring(invoice_code.length()-2, invoice_code.length()).equals(".0")){
//								String[] str2=invoice_code.split("\\.");
//								invoice_code=str2[0];
//							}
							Invoice invoice=iis.findByCode(invoice_code);
							if(invoice==null){
								Invoice inv=new Invoice();
								inv.setCode(invoice_code);
								inv.setCreateTime(new Timestamp(System.currentTimeMillis()));
								iis.save(inv);
								si.setInvoice(inv);
							}else{
								si.setInvoice(invoice);
							}
						}	
					}	
					else{
						
					}
				}
				si.setCreationTime(new Timestamp(System.currentTimeMillis()));
				si.setJobId(uuid);
				si.setLastModifiedTime(new Timestamp(System.currentTimeMillis()));
				si.setState(iss.findItemStateByCode(ItemState.NORMAL));
				si.setWarehouse(ws.findWarehouseById(warehouse_id));
				list_item.add(si);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i=0;i<list_item.size();i++){
			StorageItem si=list_item.get(i);
			sis.addStorageItem(si);
		}
		this.setRedirect(StorageItemSearch.class);
		return true;
	}

	
	public String formatCell(HSSFCell cell){
		String str="";
		if(!"".equals(cell+"")&&cell!=null){
			if(cell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
				   BigDecimal value =  new BigDecimal(cell.getNumericCellValue());
				  str=value.toString();
			}else{
				str=cell.getStringCellValue();
			}
		}
		return str;
	}
	
}