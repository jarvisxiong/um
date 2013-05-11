package com.intelitune.nwms.inbound;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.sf.click.control.FileField;
import net.sf.click.control.Form;
import net.sf.click.control.Option;
import net.sf.click.control.Select;
import net.sf.click.control.Submit;

import com.axis2.services.MCSserviceStub.InttClientDetailWS;
import com.intelitune.ccos.increasedWebService.WMSServiceImp;
import com.intelitune.ccos.increasedWebService.WMSServiceInf;
import com.intelitune.nwms.common.BorderPage;
import com.intelitune.nwms.menu.Menu;
import com.intelitune.nwms.model.Bin;
import com.intelitune.nwms.model.ItemState;
import com.intelitune.nwms.model.Material;
import com.intelitune.nwms.model.StorageItem;
import com.intelitune.nwms.model.UnitPackage;
import com.intelitune.nwms.model.Warehouse;
import com.intelitune.nwms.service.BinService;
import com.intelitune.nwms.service.BinServiceImp;
import com.intelitune.nwms.service.MaterialService;
import com.intelitune.nwms.service.MaterialServiceImpl;
import com.intelitune.nwms.service.OrderItemStateService;
import com.intelitune.nwms.service.OrderItemStateServiceImp;
import com.intelitune.nwms.service.OwnerZoneService;
import com.intelitune.nwms.service.OwnerZoneServiceImp;
import com.intelitune.nwms.service.StorageItemService;
import com.intelitune.nwms.service.StorageItemServiceImp;
import com.intelitune.nwms.service.WarehouseService;
import com.intelitune.nwms.service.WarehouseServiceImp;

public class DirectInbound extends BorderPage {

	public Form form = new Form("form");

	public WMSServiceInf ws = WMSServiceImp.getInstance();
	public OrderItemStateService oiss = OrderItemStateServiceImp.getInstance();
	public MaterialService ms = MaterialServiceImpl.getInstance();
	public Select client = new Select("client",this.getMessage("client"));
	public Select wareHouse = new Select("wareHouse",this.getMessage("warehouse"));
	public OwnerZoneService ozs = OwnerZoneServiceImp.getInstance();
	public StorageItemService sis = StorageItemServiceImp.getInstance();
	public BinService bs = BinServiceImp.getInstance();
	public WarehouseService whs = WarehouseServiceImp.getInstance();
	public FileField ff = new FileField("checkFile",this.getMessage("checkFile"));
	public Menu menu = new Menu();
	public String menuInclude = menu.getInbound();
	public String title = this.getMessage("directinbound");
	public String position = this.getMessage("DIRECTINBOUND");
	
	public DirectInbound(){
		Submit submit = new Submit("submit",this.getMessage("submit"),this,"onSubmit");
		form.add(client);
		form.add(wareHouse);
		form.add(ff);
		form.add(submit);
		client.add(Option.EMPTY_OPTION);
		wareHouse.add(Option.EMPTY_OPTION);
		List<Warehouse> wli = whs.findWarehouseList();
		for(Warehouse wh:wli){
			wareHouse.add(new Option(wh.getId(),wh.getAlias()));
		}
		
		List<InttClientDetailWS> list = ozs.findOwnerList();
		for(InttClientDetailWS inttClientDetailWS:list){
			client.add(new Option(inttClientDetailWS.getId(),inttClientDetailWS.getCnName()));
		}
		
		
	}
	
	public boolean onSubmit() throws Throwable{
		if(client.getValue().equals("error8")){
			client.setError(this.getMessage(""));
			return false;
		}
		if(wareHouse.getValue().equals("error9")){
			wareHouse.setError(this.getMessage(""));
			return false;
		}
		if(ff.getFileItem().getName().equals("error10")){
			ff.setError(this.getMessage(""));
			return false;
		}
		ItemState nor = oiss.findByCode(String.valueOf(ItemState.NORMAL));
		String path = getContext().getServletContext().getRealPath("/inbound/storageList");
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
		List<DirectInboundModel> list = null;
		ParseDirectInbound pd = new ParseDirectInbound();
		try{
			list = pd.getCollecton(file.getPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(DirectInboundModel di:list){
			StorageItem si = new StorageItem();
			Bin bin = bs.findBinByCode(wareHouse.getValue(),di.getBinCode());
			if(bin==null){
				ff.setError(this.getMessage("error11"));
				Throwable e = new Throwable(this.getMessage("error11"));
				throw e;
//				return false;
			}
			si.setBin(bin);
			si.setCreationTime(new Timestamp(System.currentTimeMillis()));
			Material material = ms.findMaterialByCode(di.getProductCode(),client.getValue());
			if(material==null){
				ff.setError(this.getMessage("error12"));
				Throwable e = new Throwable(this.getMessage("error12"));
				throw e;
//				return false;
			}
			si.setMaterial(material);
			si.setQty(di.getQty());
			si.setSn(di.getSn());
			si.setState(nor);
			si.setUnitPackage((UnitPackage)ms.findMaterialByCode(di.getProductCode(),client.getValue()).getUnitPackages().get(0));
			si.setWarehouse(whs.findWarehouseById(wareHouse.getValue()));	
			sis.addStorageItem(si);
		}
		return true;
	}
}
