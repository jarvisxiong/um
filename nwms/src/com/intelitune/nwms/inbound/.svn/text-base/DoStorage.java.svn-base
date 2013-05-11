package com.intelitune.nwms.inbound;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
import com.intelitune.nwms.common.BorderPage;
import com.intelitune.nwms.common.CommonAccount;
import com.intelitune.nwms.common.Const;
import com.intelitune.nwms.model.Bin;
import com.intelitune.nwms.model.BinState;
import com.intelitune.nwms.model.CheckingItem;
import com.intelitune.nwms.model.InboundRePickItem;
import com.intelitune.nwms.model.InboundRePutAwayItem;
import com.intelitune.nwms.model.Item;
import com.intelitune.nwms.model.ItemState;
import com.intelitune.nwms.model.LogStorageItem;
import com.intelitune.nwms.model.OwnerZone;
import com.intelitune.nwms.model.PutAwayItem;
import com.intelitune.nwms.model.StorageItem;
import com.intelitune.nwms.model.Zone;
import com.intelitune.nwms.service.BinService;
import com.intelitune.nwms.service.BinServiceImp;
import com.intelitune.nwms.service.CheckingItemService;
import com.intelitune.nwms.service.CheckingItemServiceImpl;
import com.intelitune.nwms.service.InboundOrderItemService;
import com.intelitune.nwms.service.InboundOrderItemServiceImp;
import com.intelitune.nwms.service.InboundRePickItemService;
import com.intelitune.nwms.service.InboundRePickItemServiceImp;
import com.intelitune.nwms.service.InboundRePutAwayItemService;
import com.intelitune.nwms.service.InboundRePutAwayItemServiceImp;
import com.intelitune.nwms.service.ItemStateService;
import com.intelitune.nwms.service.ItemStateServiceImp;
import com.intelitune.nwms.service.LogStorageItemService;
import com.intelitune.nwms.service.LogStorageItemServiceImp;
import com.intelitune.nwms.service.MaterialService;
import com.intelitune.nwms.service.MaterialServiceImpl;
import com.intelitune.nwms.service.OrderItemStateService;
import com.intelitune.nwms.service.OrderItemStateServiceImp;
import com.intelitune.nwms.service.OwnerZoneService;
import com.intelitune.nwms.service.OwnerZoneServiceImp;
import com.intelitune.nwms.service.PutAwayItemService;
import com.intelitune.nwms.service.PutAwayItemServiceImp;
import com.intelitune.nwms.service.StorageItemService;
import com.intelitune.nwms.service.StorageItemServiceImp;
import com.intelitune.nwms.service.WarehouseService;
import com.intelitune.nwms.service.WarehouseServiceImp;

/**
 * @author Louis
 *上架流程
 */
public class DoStorage extends BorderPage {
	public Form form = new Form("form");
//	public TableEx table = new TableEx("table"); 

	public WMSServiceInf ws = WMSServiceImp.getInstance();
	public COSServiceCommonPortTypeProxy ccpt = new COSServiceCommonPortTypeProxy();
	public OwnerZoneService ozs = OwnerZoneServiceImp.getInstance();
	public InboundOrderItemService iois = InboundOrderItemServiceImp.getInstance();
	public CheckingItemService cis = CheckingItemServiceImpl.getInstance();
	public OrderItemStateService oiss = OrderItemStateServiceImp.getInstance();
	public PutAwayItemService pais = PutAwayItemServiceImp.getInstance();
	public BinService bs = BinServiceImp.getInstance();
	public MaterialService ms = MaterialServiceImpl.getInstance();
	public StorageItemService sis = StorageItemServiceImp.getInstance();
	public InboundRePickItemService irpis = InboundRePickItemServiceImp.getInstance();
	public InboundRePutAwayItemService irpas = InboundRePutAwayItemServiceImp.getInstance();
	
	public LogStorageItemService lsis=LogStorageItemServiceImp.getInstance();
	public ItemStateService iss=ItemStateServiceImp.getInstance();

//	public UnstorageItemService uis = new UnstorageItemServiceImp();
	public WarehouseService whs = WarehouseServiceImp.getInstance();
	public HiddenField hf = new HiddenField("orderId",String.class);
	public String title = this.getMessage("dostorage");
	public String position = this.getMessage("DOSTORAGE");
	public FileField ff = new FileField("checkFile",this.getMessage("checkFile"));
	
	public String JobId = this.getMessage("jobId");
	public String clientName = this.getMessage("clientName");
	public String isEmergency = this.getMessage("isEmergency");
	public String userName = this.getMessage("userName");
	//2009-12-11 Kirin
	public String hawb = "分运单";
	public String mawb = "主运单";
	
	public String sJobId;
	public String sClientName;
	public String sIsEmergency;
	public String sUserName;
	//2009-12-11 Kirin
	public String sHawb;
	public String sMawb;
	
	public String orderId;
	public String jobId;
	public String clientId;
	public WmsOrder wm = null;
	
	public TableEx  fin = new TableEx("fin");
	public TableEx shang = new TableEx("shang");
	public TableEx xia = new TableEx("xia");
	
	public String wancheng = this.getMessage("wancheng");
	public String shangjia = this.getMessage("shangjia");
	public String xiajia = this.getMessage("xiajia");
	
	public String accountName = CommonAccount.getInstance(getContext().getSession()).accountName;
	
	public DoStorage(){
		form.add(ff);
		Submit submit = new Submit("submit",this.getMessage("submit"),this,"onPart");
		Submit print = new Submit("print",this.getMessage("print"),this,"onPrint");
//		Submit back = new Submit("back",this.getMessage("back"),this,"onBack");
		Submit sure = new Submit("sure",this.getMessage("sure"),this,"onSure");
//		Submit part = new Submit("part",this.getMessage("parts"),this,"onPart");
		form.add(submit);
		form.add(print);
//		form.add(see);
//		form.add(back);
		form.add(sure);
//		form.add(part);
		orderId = getContext().getRequestParameter("orderId");
		if(orderId!=null){
			getContext().setSessionAttribute("ttd", orderId);
		}else{
			orderId = String.valueOf(getContext().getSessionAttribute("ttd"));
		}
		hf.setValue(orderId);
		hf.setValue(orderId);
		form.add(hf);
		try {
			wm = ws.findWmsOrderById(Integer.parseInt(orderId));
			jobId = wm.getJobId();
			clientId = String.valueOf(wm.getClientId());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		sJobId = wm.getJobId();
		sClientName = wm.getClientName();
		//2009-12-11
		sHawb = wm.getHawb();
		sMawb =  wm.getMawb();
		
		if(wm.getIsEmergency()==Const.IS_EMERGENCY){
			sIsEmergency = this.getMessage("yes");
		}else{
			sIsEmergency = this.getMessage("no");
		}
		sUserName = wm.getCreator().getUserName();
		
		Column column = new Column("material.code",this.getMessage("code"));
		column.setWidth("15%");
		fin.addColumn(column);
		column = new Column("material.alias",this.getMessage("zwalias"));
		column.setWidth("15%");
		fin.addColumn(column);
		column = new Column("sn",this.getMessage("sn"));
		column.setWidth("15%");
		fin.addColumn(column);
		column = new Column("unitPackage.alias",this.getMessage("unitPackage"));
		column.setWidth("15%");
		fin.addColumn(column);
		column = new Column("qty",this.getMessage("qty"));
		column.setWidth("15%");
		fin.addColumn(column);
		column = new Column("bin.code",this.getMessage("bincode"));
		column.setWidth("25%");
		fin.addColumn(column);
		
		column = new Column("material.code",this.getMessage("code"));
		column.setWidth("15%");
		shang.addColumn(column);
		column = new Column("material.alias",this.getMessage("zwalias"));
		column.setWidth("15%");
		shang.addColumn(column);
		column = new Column("sn",this.getMessage("sn"));
		column.setWidth("15%");
		shang.addColumn(column);
		column = new Column("unitPackage.alias",this.getMessage("unitPackage"));
		column.setWidth("15%");
		shang.addColumn(column);
		column = new Column("qty",this.getMessage("qty"));
		column.setWidth("15%");
		shang.addColumn(column);
		column = new Column("bin.code",this.getMessage("bincode"));
		column.setWidth("25%");
		shang.addColumn(column);
		
		column = new Column("material.code",this.getMessage("code"));
		column.setWidth("15%");
		xia.addColumn(column);
		column = new Column("material.alias",this.getMessage("zwalias"));
		column.setWidth("15%");
		xia.addColumn(column);
		column = new Column("sn",this.getMessage("sn"));
		column.setWidth("15%");
		xia.addColumn(column);
		column = new Column("unitPackage.alias",this.getMessage("unitPackage"));
		column.setWidth("15%");
		xia.addColumn(column);
		column = new Column("qty",this.getMessage("qty"));
		column.setWidth("15%");
		xia.addColumn(column);
		column = new Column("bin.code",this.getMessage("bincode"));
		column.setWidth("25%");
		xia.addColumn(column);
		
		fin.setWidth("100%");
		fin.setPaginator(new TableInlinePaginator(fin));
		fin.setPaginatorAttachment(TableEx.PAGINATOR_INLINE);	
		fin.setPageSize(10);
		shang.setWidth("100%");
		shang.setPaginator(new TableInlinePaginator(shang));
		shang.setPaginatorAttachment(TableEx.PAGINATOR_INLINE);	
		shang.setPageSize(10);
		xia.setWidth("100%");
		xia.setPaginator(new TableInlinePaginator(xia));
		xia.setPaginatorAttachment(TableEx.PAGINATOR_INLINE);	
		xia.setPageSize(10);
//		sure.setAttribute("onclick", "return window.confirm('"+ this.getMessage("sures")+"');");
//		submit.setAttribute("onclick", "return window.confirm('"+ this.getMessage("submits")+"');");
//		part.setAttribute("onclick", "return window.confirm('"+ this.getMessage("part")+"');");
	}
	
//	public boolean onSubmit(){
//		if(ff.getFileItem().getName().equals("")){
//			ff.setError(this.getMessage("filecannotbeempty"));
//			return false;
//		}
//		String path = getContext().getServletContext().getRealPath("/inbound/storageList");
//		int a = ff.getFileItem().getName().length();
//		int b = ff.getFileItem().getName().lastIndexOf("\\");
//		String fName = ff.getFileItem().getName().substring(b+1);
//		Date t = new java.util.Date(System.currentTimeMillis());
//		SimpleDateFormat f = new SimpleDateFormat(
//				"yyyyMMddHHmmssSSS");
//		String timeStr = f.format(t);
//		fName = timeStr+ fName;
//		File file = new File(path,fName);
//		FileOutputStream fos;
//		InputStream is;
//		try {
//			is = ff.getFileItem().getInputStream();
//			byte[] buffer = new byte[1024];
//			fos = new FileOutputStream(file);
//			int c = 0;
//			while ((c = is.read(buffer)) != -1) {
//				fos.write(buffer);
//			}
//			fos.close();
//			is.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		Map map = null;
//		StorageGood sg = new StorageGood();
//		try {
//			map = sg.getCollecton(file.getPath());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		if(!map.containsKey(jobId)){
//			ff.setError(this.getMessage("error3"));
//			return false;
//		}
//		writeAllStorageResult(map);
//		return true;
//	}
//	
//	/**
//	 * @param map
//	 * 写上架表
//	 */
//	public void writeAllStorageResult(Map map){
//		//查处该业务编号下的上架操作
//		List list = (List)map.get(jobId);
//		StorageItem si= null;
//		UnstorageItem uso = null;
//		StorageGoodModel  sgm = null;
//		CheckingItem ci = null;
//		ItemState is = oiss.findByCode(String.valueOf(ItemState.NORMAL));
//		//查找该业务编号下所有正常的CheckingItem记录
//		List<StorageItem> storageItemList = new ArrayList<StorageItem>();
//		List<UnstorageItem> unstorageItemList = new ArrayList<UnstorageItem>();
//		List<CheckingItem> cli = cis.findNormalByJobId(jobId);
//		for(int i=0;i<cli.size();i++){
//			ci = cli.get(i);
//			for(int j=0;j<list.size();j++){
//				sgm = (StorageGoodModel)list.get(j);
//				if(sgm.getProductCode().equals(ci.getMaterial().getCode())){
//					if(sgm.getSn().equals(ci.getSn())){
//						Bin bin = bs.findBinByCode(wm.getWarehouseId(), sgm.getBinCode());
//						//判断是不是放错库位
//						boolean binLabel = false;
//						List<Zone> li = bin.getZones();
//						for(Zone zone:li){
//							if(zone instanceof OwnerZone){
//								OwnerZone zo = (OwnerZone) zone;
//								if(zo.getInttClientDetailWSId().equals(wm.getClientId())){
//									binLabel = true;
//									break;
//								}
//							}
//						}
//						//如果库位被锁定或者库位不是这个货主的，那么写unstorageitem
//						if(bin.getState().getCode().equals(String.valueOf(BinState.LOCKED))||binLabel==false){
//							uso = new UnstorageItem();
//							uso.setBin(bin);
//							uso.setCreationTime(new Timestamp(System.currentTimeMillis()));
//							uso.setJobId(jobId);
//							Set<Item> last = new HashSet<Item>();
//							last.add(ci);
//							uso.setLastItems(last);
//							uso.setMaterial(ci.getMaterial());
//							Set<Item> next = ci.getNextItems();
//							if(next!=null){
//								next.add(uso);
//							}else{
//								next = new HashSet<Item>();
//								next.add(uso);
//							}
//							ci.setNextItems(next);
//							uso.setOrderId(orderId);
//							uso.setQty(sgm.getQty());
//							uso.setSn(sgm.getSn());
//							uso.setState(is);
//							uso.setUnitPackage(ci.getUnitPackage());
//							uso.setWarehouse(whs.findWarehouseById(wm.getWarehouseId()));
//							//设置error数量
//							if(ci.getErrorBinQty()!=null){
//								ci.setErrorBinQty(ci.getErrorBinQty()+sgm.getQty());
//							}else{
//								ci.setErrorBinQty(sgm.getQty());
//							}
//							unstorageItemList.add(uso);
//							uis.saveUnstorageItem(uso);
//							//如果库位放对，则放
//						}else{
//							si = new StorageItem();
//							si.setBin(bin);
//							si.setCreationTime(new Timestamp(System.currentTimeMillis()));
//							si.setJobId(jobId);
//							//设置next和last
//							Set<Item> last = new HashSet<Item>();
//							last.add(ci);
//							si.setLastItems(last);
//							si.setMaterial(ci.getMaterial());
//							Set<Item> next = ci.getNextItems();
//							if(next!=null){
//								next.add(si);
//							}else{
//								next = new HashSet<Item>();
//								next.add(si);
//							}
//							ci.setNextItems(next);
//							si.setOrderId(orderId);
//							si.setQty(sgm.getQty());
//							si.setSn(sgm.getSn());
//							si.setState(is);
//							si.setUnitPackage(ci.getUnitPackage());
//							si.setWarehouse(whs.findWarehouseById(wm.getWarehouseId()));
//							//设置putaway数量
//							if(ci.getPutawayQty()!=null){
//								ci.setPutawayQty(ci.getPutawayQty()+sgm.getQty());
//							}else{
//								ci.setPutawayQty(sgm.getQty());
//							}
//							storageItemList.add(si);
//							sis.addStorageItem(si);
//						}
//					}		
//				}
//				ItemState iss = oiss.findByCode(String.valueOf(ItemState.INBOUND_COMPLETED));
//				//设置checkingItem的状态，查找所有的checkingItem，判断putaway数量和arrived数量，相等则置上架完成状态，不对则要具体分析
//				for(CheckingItem cc:cli){
//					//相等则直接置置为上架完成状态
//					if(cc.getPutawayQty()==cc.getArrivedQty()){
//						ci.setState(iss);
//						cis.updateCheckItem(cc);
//					}
//					//少上，则不置状态，但是要写unstroageitem表
//					if(cc.getPutawayQty()+cc.getErrorBinQty()<cc.getArrivedQty()){						
//						uso = new UnstorageItem();
////						uso.setBin(bin);
//						uso.setCreationTime(new Timestamp(System.currentTimeMillis()));
//						uso.setJobId(jobId);
//						Set<Item> last = new HashSet<Item>();
//						last.add(cc);
//						uso.setLastItems(last);
//						uso.setMaterial(cc.getMaterial());
//						Set<Item> next = cc.getNextItems();
//						if(next!=null){
//							next.add(uso);
//						}else{
//							next = new HashSet<Item>();
//							next.add(uso);
//						}
//						cc.setNextItems(next);
//						uso.setOrderId(orderId);
//						//设置数量
//						uso.setQty(0f);
//						uso.setUnwantedQty(0f);
//						uso.setWantQty(cc.getArrivedQty()-cc.getPutawayQty()-cc.getErrorBinQty());
//						uso.setSn(sgm.getSn());
//						uso.setState(is);
//						uso.setUnitPackage(ci.getUnitPackage());
//						uso.setWarehouse(whs.findWarehouseById(wm.getWarehouseId()));
//						cis.updateCheckItem(cc);
//						unstorageItemList.add(uso);
//						uis.saveUnstorageItem(uso);
//					}
//					//多上，置状态，但是也要写unstorageitem表
//					if(cc.getPutawayQty()+cc.getErrorBinQty()>cc.getArrivedQty()){
//						String pCode = cc.getMaterial().getCode();
//						String sn = cc.getSn();
//						ci.setState(iss);
//						cis.updateCheckItem(cc);
//						float spare = cc.getPutawayQty()-cc.getArrivedQty()-cc.getErrorBinQty();
//						for(StorageItem ss:storageItemList){
//							if(ss.getMaterial().getCode().equals(pCode)){
//								if(ss.getSn().equals(sn)){
//									float qty = ss.getQty();
//									//如果一个库位够下，则写完unstroageitem跳出循环
//									if(qty-spare>=0){
//										uso = new UnstorageItem();
//										uso.setBin(ss.getBin());
//										uso.setCreationTime(new Timestamp(System.currentTimeMillis()));
//										uso.setJobId(jobId);
//										Set<Item> last = new HashSet<Item>();
//										last.add(ci);
//										uso.setLastItems(last);
//										uso.setMaterial(ci.getMaterial());
//										Set<Item> next = ci.getNextItems();
//										if(next!=null){
//											next.add(uso);
//										}else{
//											next = new HashSet<Item>();
//											next.add(uso);
//										}
//										ci.setNextItems(next);
//										uso.setOrderId(orderId);
//										//设置数量
//										uso.setQty(0f);
//										uso.setUnwantedQty(spare);
//										uso.setSn(sgm.getSn());
//										uso.setState(is);
//										uso.setUnitPackage(ci.getUnitPackage());
//										uso.setWarehouse(whs.findWarehouseById(wm.getWarehouseId()));
//										unstorageItemList.add(uso);
//										uis.saveUnstorageItem(uso);
//										break;
//										//这个storageItem里面不够扣，则扣完不能跳出循环
//									}else{
//										uso = new UnstorageItem();
//										uso.setBin(ss.getBin());
//										uso.setCreationTime(new Timestamp(System.currentTimeMillis()));
//										uso.setJobId(jobId);
//										Set<Item> last = new HashSet<Item>();
//										last.add(ci);
//										uso.setLastItems(last);
//										uso.setMaterial(ci.getMaterial());
//										Set<Item> next = ci.getNextItems();
//										if(next!=null){
//											next.add(uso);
//										}else{
//											next = new HashSet<Item>();
//											next.add(uso);
//										}
//										ci.setNextItems(next);
//										uso.setOrderId(orderId);
//										//设置数量
//										uso.setQty(0f);
//										uso.setUnwantedQty(ss.getQty());
//										uso.setSn(sgm.getSn());
//										uso.setState(is);
//										uso.setUnitPackage(ci.getUnitPackage());
//										uso.setWarehouse(whs.findWarehouseById(wm.getWarehouseId()));
//										unstorageItemList.add(uso);
//										uis.saveUnstorageItem(uso);
//									}
//								}
//							}
//						}
//					}
//				}
//			}		
//		}
//		boolean label = true;
//		for(CheckingItem cci:cli){
//			if(!cci.getState().code.equals(ItemState.INBOUND_COMPLETED)){
//				label = false;
//				break;
//			}
//		}
//		if(label==true){
//			ws.alterToInboundComplete(Integer.parseInt(orderId));
//		}
//		this.setRedirect("showStorageResult.htm?orderId=" + orderId);
//	}
	
	public void onRender(){
		fin.setRowList(pais.findNormalByJobId(jobId));
		shang.setRowList(irpas.findNormalByJobId(jobId));
		xia.setRowList(irpis.findNormalByJobId(jobId));
	}
	
	//解析文件，生成集合
	public boolean onPart() throws Exception{
		if(ff.getFileItem().getName().equals("")){
			ff.setError(this.getMessage("filecannotbeempty"));
			return false;
		}
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
		List<StorageGoodModel> list = null;
		StorageGood sg = new StorageGood();
		try {
			list = sg.getCollecton(file.getPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		writePartStorageResult(list);
		return true;
	}
	
	public void writePartStorageResult(List<StorageGoodModel> list) throws Exception{
		ItemState nor = oiss.findByCode(String.valueOf(ItemState.NORMAL));
		ItemState del = oiss.findByCode(String.valueOf(ItemState.DELETE));
//		ItemState lock = oiss.findByCode(String.valueOf(ItemState.EXCEPTION));
		ItemState com = oiss.findByCode(String.valueOf(ItemState.INBOUND_COMPLETED));
		
		InboundRePutAwayItem inboundRePutAwayItem = null;
		InboundRePickItem inboundRePickItem = null;
		PutAwayItem putAwayItem = null;
		StorageItem storageItem = null;
		List<InboundRePutAwayItem> inboundRePutAwayItemLisst = new ArrayList<InboundRePutAwayItem>();
		List<InboundRePickItem> inboundRePickItemList = new ArrayList<InboundRePickItem>();
		List<InboundRePutAwayItem> inboundRePutAwayItemList = new ArrayList<InboundRePutAwayItem>();
				
//		第一次先把所有的CheckingItem写入RePutAwayItem记录
		if(irpas.findByJobId(jobId).size()==0&&irpis.findByJobId(jobId).size()==0){
			List<CheckingItem> cli = cis.findNormalByJobId(jobId);
			for(CheckingItem ci:cli){
				inboundRePutAwayItem = new InboundRePutAwayItem();
				inboundRePutAwayItem.setCreationTime(new Timestamp(System.currentTimeMillis()));
				inboundRePutAwayItem.setJobId(jobId);
				
				Set<Item> next = ci.getNextItems();
				if(next==null){
					next = new HashSet<Item>();
					next.add(inboundRePutAwayItem);
				}else{
					next.add(inboundRePutAwayItem);
				}
				ci.setNextItems(next);
				
				Set<Item> last = new HashSet<Item>();
				last.add(ci);
				inboundRePutAwayItem.setLastItems(last);
				
				inboundRePutAwayItem.setMaterial(ci.getMaterial());
				inboundRePutAwayItem.setOrderId(orderId);
				inboundRePutAwayItem.setQty(ci.getArrivedQty());
				inboundRePutAwayItem.setSn(ci.getSn());
				inboundRePutAwayItem.setState(nor);
				inboundRePutAwayItem.setTempJobId(jobId);
				inboundRePutAwayItem.setUnitPackage(ci.getUnitPackage());
				inboundRePutAwayItem.setWarehouse(ci.getWarehouse());
				inboundRePutAwayItem.setInvoice(ci.getInvoice());
				
				
				inboundRePutAwayItem.setItemType(ci.getItemType());
				
				//michael 2009_10_29
				inboundRePutAwayItem.setF_currency(ci.getF_currency());
				inboundRePutAwayItem.setF_gross_price(ci.getF_gross_price());
				inboundRePutAwayItem.setF_gross_weight(ci.getF_gross_weight());
				inboundRePutAwayItem.setF_unit_price(ci.getF_unit_price());
				inboundRePutAwayItem.setF_unit_weight(ci.getF_unit_weight());
								
				irpas.saveInboundRePutAwayItem(inboundRePutAwayItem);
//				inboundRePutAwayItemList.add(inboundRePutAwayItem);
				cis.updateCheckItem(ci);			
			}
		}
			PutAwayItem p = new PutAwayItem();
			pais.savePutAwayItem(p);
			String tempJobIdInbound = p.getId();
			pais.deletePutAwayItem(p);
			
			PutAwayItem p1 = new PutAwayItem();
			pais.savePutAwayItem(p1);
			String tempJobIdOutbound = p1.getId();
			pais.deletePutAwayItem(p1);
			
			inboundRePutAwayItemList = irpas.findNormalByJobId(jobId);
			inboundRePickItemList = irpis.findNormalByJobId(jobId);
//			开始验证
//			先检查下架
			for(StorageGoodModel sgm:list){
				InboundRePickItem irpi = null;
				Bin bin = null;
				
				bin = bs.findBinByCode(wm.getWarehouseId(), sgm.getBinCode());	
				if(bin==null){
					this.getContext().setFlashAttribute("eee","没有这样的库位:"+sgm.getBinCode());
					throw new Exception();
				}
				Boolean label = null;
				for(InboundRePickItem ii:inboundRePickItemList){
//					jobID对不上直接跳出
					if(!sgm.getJobId().equalsIgnoreCase(ii.getTempJobId())){
						label = null;
						break;
					}
//					所有情况都对上
					if(sgm.getJobId().equalsIgnoreCase(ii.getTempJobId())&&sgm.getProductCode().equalsIgnoreCase(ii.getMaterial().getCode())&&sgm.getSn().equalsIgnoreCase(ii.getSn())&&ii.getBin().getCode().equalsIgnoreCase(bin.getCode())){
						label = true;
						irpi = ii;
						break;
					}
					label = false;
				}
				if(label!=null){
				if(label==true){
					if(irpi.getQty()>=sgm.getQty()){
						irpi.setQty(irpi.getQty()-sgm.getQty());
							if(irpi.getQty()==0f){
								irpi.setState(del);
							}
						irpis.updateInboundRePickItem(irpi);
						continue;
//					数量不对
					}else{
						inboundRePutAwayItem = new InboundRePutAwayItem();
						inboundRePutAwayItem.setCreationTime(new Timestamp(System.currentTimeMillis()));
						inboundRePutAwayItem.setJobId(jobId);
						
						Set<Item> next = irpi.getNextItems();
						if(next==null){
							next = new HashSet<Item>();
							next.add(inboundRePutAwayItem);
						}else{
							next.add(inboundRePutAwayItem);
						}
						irpi.setNextItems(next);
						
						Set<Item> last = new HashSet<Item>();
						last.add(irpi);
						inboundRePutAwayItem.setLastItems(last);
						
						inboundRePutAwayItem.setBin(bin);
						inboundRePutAwayItem.setMaterial(irpi.getMaterial());
						inboundRePutAwayItem.setOrderId(orderId);
						inboundRePutAwayItem.setQty(sgm.getQty()-irpi.getQty());
						inboundRePutAwayItem.setSn(sgm.getSn());
						inboundRePutAwayItem.setState(nor);
						inboundRePutAwayItem.setUnitPackage(irpi.getUnitPackage());
						inboundRePutAwayItem.setWarehouse(irpi.getWarehouse());
						inboundRePutAwayItem.setTempJobId(tempJobIdInbound);
						inboundRePutAwayItem.setInvoice(irpi.getInvoice());
						
						inboundRePutAwayItem.setItemType(irpi.getItemType());
						
						//michael_2009_10_27
						inboundRePutAwayItem.setF_currency(irpi.getF_currency());
						inboundRePutAwayItem.setF_gross_price(irpi.getF_gross_price());
						inboundRePutAwayItem.setF_gross_weight(irpi.getF_gross_weight());
						inboundRePutAwayItem.setF_unit_price(irpi.getF_unit_price());
						inboundRePutAwayItem.setF_unit_weight(irpi.getF_unit_weight());
						
						irpas.saveInboundRePutAwayItem(inboundRePutAwayItem);
						irpi.setState(del);
						irpis.updateInboundRePickItem(irpi);
					}
				}else{
					inboundRePutAwayItem = new InboundRePutAwayItem();
					inboundRePutAwayItem.setCreationTime(new Timestamp(System.currentTimeMillis()));
					inboundRePutAwayItem.setJobId(jobId);
					
					Set<Item> next = irpi.getNextItems();
					if(next==null){
						next = new HashSet<Item>();
						next.add(inboundRePutAwayItem);
					}else{
						next.add(inboundRePutAwayItem);
					}
					irpi.setNextItems(next);
					
					Set<Item> last = new HashSet<Item>();
					last.add(irpi);
					inboundRePutAwayItem.setLastItems(last);
					
					inboundRePutAwayItem.setBin(bin);
					inboundRePutAwayItem.setMaterial(ms.findMaterialByCode(sgm.getProductCode(),clientId));
					inboundRePutAwayItem.setOrderId(orderId);
					inboundRePutAwayItem.setQty(sgm.getQty());
					inboundRePutAwayItem.setSn(sgm.getSn());
					inboundRePutAwayItem.setState(nor);
					inboundRePutAwayItem.setWarehouse(irpi.getWarehouse());
					inboundRePutAwayItem.setTempJobId(tempJobIdInbound);
					inboundRePutAwayItem.setUnitPackage(irpi.getUnitPackage());
					inboundRePutAwayItem.setInvoice(irpi.getInvoice());
					
					//michael_2009-11-16
					inboundRePutAwayItem.setItemType(irpi.getItemType());
					
					//michael_2009_10_27
					inboundRePutAwayItem.setF_currency(irpi.getF_currency());
					inboundRePutAwayItem.setF_gross_price(irpi.getF_gross_price());
					inboundRePutAwayItem.setF_gross_weight(irpi.getF_gross_weight());
					inboundRePutAwayItem.setF_unit_price(irpi.getF_unit_price());
					inboundRePutAwayItem.setF_unit_weight(irpi.getF_unit_weight());
					
					irpas.saveInboundRePutAwayItem(inboundRePutAwayItem);
					irpis.updateInboundRePickItem(irpi);
				}
				}
			}
//				for(InboundRePickItem irpi:inboundRePickItemList){
//					if(irpi.getTempJobId().equalsIgnoreCase(sgm.getJobId())){
////						Boolean label = false;
//						Bin bin = bs.findBinByCode(wm.getWarehouseId(), sgm.getBinCode());
//						if(irpi.getMaterial().getCode().equalsIgnoreCase(sgm.getProductCode())){
//							if(irpi.getSn().equalsIgnoreCase(sgm.getSn())){
//								if(irpi.getBin().getCode().equalsIgnoreCase(bin.getCode())){
////									label = true;
////									因为从同一个库位拿同一个料号同一个SN的东西的记录在之前合并了
////									判断数量
//									if(irpi.getQty()>=sgm.getQty()){
//										irpi.setQty(irpi.getQty()-sgm.getQty());
//											if(irpi.getQty()==0f){
//												irpi.setState(del);
//											}
//										irpis.updateInboundRePickItem(irpi);
//										continue;
////									数量不对
//									}else{
//										inboundRePutAwayItem = new InboundRePutAwayItem();
//										inboundRePutAwayItem.setCreationTime(new Timestamp(System.currentTimeMillis()));
//										inboundRePutAwayItem.setJobId(jobId);
//										
//										Set<Item> next = irpi.getNextItems();
//										if(next==null){
//											next = new HashSet<Item>();
//											next.add(inboundRePutAwayItem);
//										}else{
//											next.add(inboundRePutAwayItem);
//										}
//										irpi.setNextItems(next);
//										
//										Set<Item> last = new HashSet<Item>();
//										last.add(irpi);
//										inboundRePutAwayItem.setLastItems(last);
//										
//										inboundRePutAwayItem.setBin(bin);
//										inboundRePutAwayItem.setMaterial(irpi.getMaterial());
//										inboundRePutAwayItem.setOrderId(orderId);
//										inboundRePutAwayItem.setQty(sgm.getQty()-irpi.getQty());
//										inboundRePutAwayItem.setSn(sgm.getSn());
//										inboundRePutAwayItem.setState(nor);
////										inboundRePutAwayItem.setTempJobId(jobId);
//										inboundRePutAwayItem.setUnitPackage(irpi.getUnitPackage());
//										inboundRePutAwayItem.setWarehouse(irpi.getWarehouse());
//										inboundRePutAwayItem.setTempJobId(tempJobIdInbound);
//										inboundRePutAwayItem.setInvoice(irpi.getInvoice());
//										irpas.saveInboundRePutAwayItem(inboundRePutAwayItem);
////										判断tempJobId是否存在
////										if(tempJobId.equals("")){
////											irpas.saveInboundRePutAwayItem(inboundRePutAwayItem);
////											inboundRePutAwayItem.setTempJobId(inboundRePutAwayItem.getId());
////											tempJobId = inboundRePutAwayItem.getId();
////											irpas.updateInboundRePutAwayItem(inboundRePutAwayItem);
////										}else{
////											inboundRePutAwayItem.setTempJobId(tempJobId);
////											irpas.saveInboundRePutAwayItem(inboundRePutAwayItem);
////										}
//										irpi.setState(del);
//										irpis.updateInboundRePickItem(irpi);
//										continue;
//									}									
//								}
//							}
//						}
////						出错则要把这些东西全部上架
////						if(label==false){
//						inboundRePutAwayItem = new InboundRePutAwayItem();
//						inboundRePutAwayItem.setCreationTime(new Timestamp(System.currentTimeMillis()));
//						inboundRePutAwayItem.setJobId(jobId);
//						
//						Set<Item> next = irpi.getNextItems();
//						if(next==null){
//							next = new HashSet<Item>();
//							next.add(inboundRePutAwayItem);
//						}else{
//							next.add(inboundRePutAwayItem);
//						}
//						irpi.setNextItems(next);
//						
//						Set<Item> last = new HashSet<Item>();
//						last.add(irpi);
//						inboundRePutAwayItem.setLastItems(last);
//						
//						inboundRePutAwayItem.setBin(bin);
//						inboundRePutAwayItem.setMaterial(ms.findMaterialByCode(sgm.getProductCode(),clientId));
//						inboundRePutAwayItem.setOrderId(orderId);
//						inboundRePutAwayItem.setQty(sgm.getQty());
//						inboundRePutAwayItem.setSn(sgm.getSn());
//						inboundRePutAwayItem.setState(nor);
//						inboundRePutAwayItem.setWarehouse(irpi.getWarehouse());
//						inboundRePutAwayItem.setTempJobId(tempJobIdInbound);
//						inboundRePutAwayItem.setUnitPackage(irpi.getUnitPackage());
//						inboundRePutAwayItem.setInvoice(irpi.getInvoice());
//						irpas.saveInboundRePutAwayItem(inboundRePutAwayItem);
//						irpis.updateInboundRePickItem(irpi);	
//						continue;
////						}
//					}
//				}
//			}
//			检查上架
			for(StorageGoodModel sgm:list){
				InboundRePutAwayItem irpai= null;
				Boolean label = null;
				Bin bin = bs.findBinByCode(wm.getWarehouseId(), sgm.getBinCode().toUpperCase());	
				for(InboundRePutAwayItem ii:inboundRePutAwayItemList){
//					jobID对不上直接跳出
					if(!sgm.getJobId().equalsIgnoreCase(ii.getTempJobId())){
						label = null;
						break;
					}
//					所有情况都对上
					if(sgm.getJobId().equalsIgnoreCase(ii.getTempJobId())&&sgm.getProductCode().equalsIgnoreCase(ii.getMaterial().getCode())&&sgm.getSn().equalsIgnoreCase(ii.getSn())){
						label = true;
						irpai = ii;
						break;
					}
					label = false;
				}
				if(label!=null){
				if(label==true){
					boolean binLabel = false;
					List<Zone> li = bin.getZones();
					for(Zone zone:li){
						OwnerZone zo = ozs.findOwnerZoneById(zone.getId());
						if(zo!=null){
							if(zo.getInttClientDetailWSId().equals(String.valueOf(wm.getClientId()))){
								binLabel = true;
								break;
							}
						}
					}
					if(bin.getState().getCode().equals(String.valueOf(BinState.LOCKED))||binLabel==false){
						inboundRePickItem = new InboundRePickItem();
						inboundRePickItem.setBin(bin);
						inboundRePickItem.setCreationTime(new Timestamp(System.currentTimeMillis()));
						inboundRePickItem.setJobId(jobId);
					
						Set<Item> next = irpai.getNextItems();
						if(next==null){
							next = new HashSet<Item>();
							next.add(inboundRePickItem);
						}else{
							next.add(inboundRePickItem);
						}
						irpai.setNextItems(next);
					
						Set<Item> last = new HashSet<Item>();
						last.add(irpai);
						inboundRePickItem.setLastItems(last);
					
						inboundRePickItem.setMaterial(irpai.getMaterial());
						inboundRePickItem.setOrderId(orderId);
						inboundRePickItem.setQty(sgm.getQty());
						inboundRePickItem.setSn(sgm.getSn());
						inboundRePickItem.setState(nor);
						inboundRePickItem.setUnitPackage(irpai.getUnitPackage());
						inboundRePickItem.setWarehouse(irpai.getWarehouse());
						inboundRePickItem.setTempJobId(tempJobIdOutbound);
						inboundRePickItem.setInvoice(irpai.getInvoice());
						
						//michael_2009-11-16
						inboundRePickItem.setItemType(irpai.getItemType());
						
						//michael_2009-10_27
						inboundRePickItem.setF_currency(irpai.getF_currency());
						inboundRePickItem.setF_gross_price(irpai.getF_gross_price());
						inboundRePickItem.setF_gross_weight(irpai.getF_gross_weight());
						inboundRePickItem.setF_unit_price(irpai.getF_unit_price());
						inboundRePickItem.setF_unit_weight(irpai.getF_unit_weight());
						
						irpis.saveInboundRePickItem(inboundRePickItem);
						irpas.updateInboundRePutAwayItem(irpai);
						continue;
					}else{
//						当数量够扣
						if(irpai.getQty()>=sgm.getQty()){	
							putAwayItem = new PutAwayItem();
							putAwayItem.setBin(bin);
							putAwayItem.setCreationTime(new Timestamp(System.currentTimeMillis()));
							putAwayItem.setJobId(jobId);
							Set<Item> next = irpai.getNextItems();
							if(next!=null){
								next.add(putAwayItem);
							}else{
								next = new HashSet<Item>();
								next.add(putAwayItem);
							}
							irpai.setNextItems(next);
							Set<Item> last = new HashSet<Item>();
							last.add(irpai);
							putAwayItem.setLastItems(last);
							putAwayItem.setMaterial(irpai.getMaterial());
							putAwayItem.setOrderId(orderId);
							putAwayItem.setQty(sgm.getQty());
							putAwayItem.setSn(sgm.getSn());
							putAwayItem.setState(nor);
							putAwayItem.setUnitPackage(irpai.getUnitPackage());
							putAwayItem.setWarehouse(irpai.getWarehouse());
							putAwayItem.setInvoice(irpai.getInvoice());
							
							//michael_2009-11-16
							putAwayItem.setItemType(irpai.getItemType());
							
							//michael_2009_10_27
							putAwayItem.setF_currency(irpai.getF_currency());
							putAwayItem.setF_gross_price(irpai.getF_gross_price());
							putAwayItem.setF_gross_weight(irpai.getF_gross_weight());
							putAwayItem.setF_unit_price(irpai.getF_unit_price());
							putAwayItem.setF_unit_weight(irpai.getF_unit_weight());
							
							irpai.setQty(irpai.getQty()-sgm.getQty());
							if(irpai.getQty()==0f){
//								是零则删除这条记录
								irpai.setState(del);
							}
							pais.savePutAwayItem(putAwayItem);
							irpas.updateInboundRePutAwayItem(irpai);
							continue;
						}else{
//						当数量不够扣
//						先写PutAway数量
							putAwayItem = new PutAwayItem();							
							putAwayItem.setBin(bin);
							putAwayItem.setCreationTime(new Timestamp(System.currentTimeMillis()));
							putAwayItem.setJobId(jobId);
							Set<Item> next = irpai.getNextItems();
							if(next!=null){
								next.add(putAwayItem);
							}else{
								next = new HashSet<Item>();
								next.add(putAwayItem);
							}
							irpai.setNextItems(next);
							Set<Item> last = new HashSet<Item>();
							last.add(irpai);
							putAwayItem.setLastItems(last);
							putAwayItem.setMaterial(irpai.getMaterial());
							putAwayItem.setOrderId(orderId);
							putAwayItem.setQty(irpai.getQty());
							putAwayItem.setSn(sgm.getSn());
							putAwayItem.setState(nor);
							putAwayItem.setUnitPackage(irpai.getUnitPackage());
							putAwayItem.setWarehouse(irpai.getWarehouse());
							putAwayItem.setInvoice(irpai.getInvoice());
							
							//michael_2009_10_27
							putAwayItem.setF_currency(irpai.getF_currency());
							putAwayItem.setF_gross_price(irpai.getF_gross_price());
							putAwayItem.setF_gross_weight(irpai.getF_gross_weight());
							putAwayItem.setF_unit_price(irpai.getF_unit_price());
							putAwayItem.setF_unit_weight(irpai.getF_unit_weight());
							
							//michael_2009-11-16
							putAwayItem.setItemType(irpai.getItemType());
							
							if(putAwayItem.getQty()==0f){
								putAwayItem.setState(del);
							}
							pais.savePutAwayItem(putAwayItem);
						
//							再写RePick
							inboundRePickItem = new InboundRePickItem();
							inboundRePickItem.setBin(bin);
							inboundRePickItem.setCreationTime(new Timestamp(System.currentTimeMillis()));
							inboundRePickItem.setJobId(jobId);
						
							Set<Item> next1 = irpai.getNextItems();
							if(next1==null){
								next1 = new HashSet<Item>();
								next1.add(inboundRePickItem);
							}else{
								next1.add(inboundRePickItem);
							}
							irpai.setNextItems(next1);
						
							Set<Item> last1 = new HashSet<Item>();
							last1.add(irpai);
							inboundRePickItem.setLastItems(last1);
						
							inboundRePickItem.setMaterial(irpai.getMaterial());
							inboundRePickItem.setOrderId(orderId);
							inboundRePickItem.setQty(sgm.getQty()-irpai.getQty());
							inboundRePickItem.setSn(sgm.getSn());
							inboundRePickItem.setState(nor);
							inboundRePickItem.setUnitPackage(irpai.getUnitPackage());
							inboundRePickItem.setWarehouse(irpai.getWarehouse());
							inboundRePickItem.setTempJobId(tempJobIdOutbound);
							inboundRePickItem.setInvoice(irpai.getInvoice());
							
							//michael_2009_10_27
							inboundRePickItem.setF_currency(irpai.getF_currency());
							inboundRePickItem.setF_gross_price(irpai.getF_gross_price());
							inboundRePickItem.setF_gross_weight(irpai.getF_gross_weight());
							inboundRePickItem.setF_unit_price(irpai.getF_unit_price());
							inboundRePickItem.setF_unit_weight(irpai.getF_unit_weight());
							
							//michael_2009-11-16
							inboundRePickItem.setItemType(irpai.getItemType());
							
							irpis.saveInboundRePickItem(inboundRePickItem);
															
//							是零则删除这条记录									
							irpai.setQty(0f);
							irpai.setState(del);
							irpas.updateInboundRePutAwayItem(irpai);
						}
					}
				}else{
					inboundRePickItem = new InboundRePickItem();
					inboundRePickItem.setBin(bin);
					inboundRePickItem.setCreationTime(new Timestamp(System.currentTimeMillis()));
					inboundRePickItem.setJobId(jobId);		
					inboundRePickItem.setMaterial(ms.findMaterialByCode(sgm.getProductCode(),clientId));
					inboundRePickItem.setOrderId(orderId);
					inboundRePickItem.setQty(sgm.getQty());
					inboundRePickItem.setSn(sgm.getSn());
					inboundRePickItem.setState(nor);
					
					//michael_2009-11-16
					inboundRePickItem.setItemType(irpai.getItemType());
					
					if(irpai!=null){
						inboundRePickItem.setUnitPackage(irpai.getUnitPackage());
						inboundRePickItem.setWarehouse(irpai.getWarehouse());
						inboundRePickItem.setTempJobId(tempJobIdOutbound);
						inboundRePickItem.setInvoice(irpai.getInvoice());
						
						//michael_2009_10_27
						inboundRePickItem.setF_currency(irpai.getF_currency());
						inboundRePickItem.setF_gross_price(irpai.getF_gross_price());
						inboundRePickItem.setF_gross_weight(irpai.getF_gross_weight());
						inboundRePickItem.setF_unit_price(irpai.getF_unit_price());
						inboundRePickItem.setF_unit_weight(irpai.getF_unit_weight());
						
					}
				
					irpis.saveInboundRePickItem(inboundRePickItem);
				}
				}
			}
					
					
					
//				for(InboundRePutAwayItem irpai:inboundRePutAwayItemList){				
//					Bin bin = bs.findBinByCode(wm.getWarehouseId(), sgm.getBinCode());		
//					if(sgm.getJobId().equalsIgnoreCase(irpai.getTempJobId())){
////						Boolean label = false;
//						if(sgm.getProductCode().equalsIgnoreCase(irpai.getMaterial().getCode())){
//							if(sgm.getSn().equalsIgnoreCase(irpai.getSn())){	
////								label = true;
//							//看Bin正不正确	
//								boolean binLabel = false;
//								List<Zone> li = bin.getZones();
//								for(Zone zone:li){
//									OwnerZone zo = ozs.findOwnerZoneById(zone.getId());
//									if(zo!=null){
//										if(zo.getInttClientDetailWSId().equals(String.valueOf(wm.getClientId()))){
//											binLabel = true;
//											break;
//										}
//									}
//								}
////								如果Bin错误，则写RePickItem记录，RePutAway记录不去做
//								if(bin.getState().getCode().equals(String.valueOf(BinState.LOCKED))||binLabel==false){
//									inboundRePickItem = new InboundRePickItem();
//									inboundRePickItem.setBin(bin);
//									inboundRePickItem.setCreationTime(new Timestamp(System.currentTimeMillis()));
//									inboundRePickItem.setJobId(jobId);
//								
//									Set<Item> next = irpai.getNextItems();
//									if(next==null){
//										next = new HashSet<Item>();
//										next.add(inboundRePickItem);
//									}else{
//										next.add(inboundRePickItem);
//									}
//									irpai.setNextItems(next);
//								
//									Set<Item> last = new HashSet<Item>();
//									last.add(irpai);
//									inboundRePickItem.setLastItems(last);
//								
//									inboundRePickItem.setMaterial(irpai.getMaterial());
//									inboundRePickItem.setOrderId(orderId);
//									inboundRePickItem.setQty(sgm.getQty());
//									inboundRePickItem.setSn(sgm.getSn());
//									inboundRePickItem.setState(nor);
//									inboundRePickItem.setUnitPackage(irpai.getUnitPackage());
//									inboundRePickItem.setWarehouse(irpai.getWarehouse());
//									inboundRePickItem.setTempJobId(tempJobIdOutbound);
//									inboundRePickItem.setInvoice(irpai.getInvoice());
//									irpis.saveInboundRePickItem(inboundRePickItem);
//									irpas.updateInboundRePutAwayItem(irpai);
//									continue;
//								}else{
////									当数量够扣
//									if(irpai.getQty()>=sgm.getQty()){	
//										putAwayItem = new PutAwayItem();
//										putAwayItem.setBin(bin);
//										putAwayItem.setCreationTime(new Timestamp(System.currentTimeMillis()));
//										putAwayItem.setJobId(jobId);
//										Set<Item> next = irpai.getNextItems();
//										if(next!=null){
//											next.add(putAwayItem);
//										}else{
//											next = new HashSet<Item>();
//											next.add(putAwayItem);
//										}
//										irpai.setNextItems(next);
//										Set<Item> last = new HashSet<Item>();
//										last.add(irpai);
//										putAwayItem.setLastItems(last);
//										putAwayItem.setMaterial(irpai.getMaterial());
//										putAwayItem.setOrderId(orderId);
//										putAwayItem.setQty(sgm.getQty());
//										putAwayItem.setSn(sgm.getSn());
//										putAwayItem.setState(nor);
//										putAwayItem.setUnitPackage(irpai.getUnitPackage());
//										putAwayItem.setWarehouse(irpai.getWarehouse());
//										putAwayItem.setInvoice(irpai.getInvoice());
//										irpai.setQty(irpai.getQty()-sgm.getQty());
//										if(irpai.getQty()==0f){
////											是零则删除这条记录
//											irpai.setState(del);
//										}
//										pais.savePutAwayItem(putAwayItem);
//										irpas.updateInboundRePutAwayItem(irpai);
//										continue;
//									}else{
////									当数量不够扣
////									先写PutAway数量
//										putAwayItem = new PutAwayItem();
//										putAwayItem.setBin(bin);
//										putAwayItem.setCreationTime(new Timestamp(System.currentTimeMillis()));
//										putAwayItem.setJobId(jobId);
//										Set<Item> next = irpai.getNextItems();
//										if(next!=null){
//											next.add(putAwayItem);
//										}else{
//											next = new HashSet<Item>();
//											next.add(putAwayItem);
//										}
//										irpai.setNextItems(next);
//										Set<Item> last = new HashSet<Item>();
//										last.add(irpai);
//										putAwayItem.setLastItems(last);
//										putAwayItem.setMaterial(irpai.getMaterial());
//										putAwayItem.setOrderId(orderId);
//										putAwayItem.setQty(irpai.getQty());
//										putAwayItem.setSn(sgm.getSn());
//										putAwayItem.setState(nor);
//										putAwayItem.setUnitPackage(irpai.getUnitPackage());
//										putAwayItem.setWarehouse(irpai.getWarehouse());
//										putAwayItem.setInvoice(irpai.getInvoice());
//										if(putAwayItem.getQty()==0f){
//											putAwayItem.setState(del);
//										}
//										pais.savePutAwayItem(putAwayItem);
//									
////										再写RePick
//										inboundRePickItem = new InboundRePickItem();
//										inboundRePickItem.setBin(bin);
//										inboundRePickItem.setCreationTime(new Timestamp(System.currentTimeMillis()));
//										inboundRePickItem.setJobId(jobId);
//									
//										Set<Item> next1 = irpai.getNextItems();
//										if(next1==null){
//											next1 = new HashSet<Item>();
//											next1.add(inboundRePickItem);
//										}else{
//											next1.add(inboundRePickItem);
//										}
//										irpai.setNextItems(next1);
//									
//										Set<Item> last1 = new HashSet<Item>();
//										last1.add(irpai);
//										inboundRePickItem.setLastItems(last1);
//									
//										inboundRePickItem.setMaterial(irpai.getMaterial());
//										inboundRePickItem.setOrderId(orderId);
//										inboundRePickItem.setQty(sgm.getQty()-irpai.getQty());
//										inboundRePickItem.setSn(sgm.getSn());
//										inboundRePickItem.setState(nor);
//										inboundRePickItem.setUnitPackage(irpai.getUnitPackage());
//										inboundRePickItem.setWarehouse(irpai.getWarehouse());
//										inboundRePickItem.setTempJobId(tempJobIdOutbound);
//										inboundRePickItem.setInvoice(irpai.getInvoice());
//										irpis.saveInboundRePickItem(inboundRePickItem);
//																		
////										是零则删除这条记录									
//										irpai.setQty(0f);
//										irpai.setState(del);
//										irpas.updateInboundRePutAwayItem(irpai);
//										continue;
//									}
//								}
//							}
//						}
////						false则说明在这个JobId下没有这个料件，则这条记录需要下架
////						if(label==false){
//						inboundRePickItem = new InboundRePickItem();
//						inboundRePickItem.setBin(bin);
//						inboundRePickItem.setCreationTime(new Timestamp(System.currentTimeMillis()));
//						inboundRePickItem.setJobId(jobId);		
//						inboundRePickItem.setMaterial(ms.findMaterialByCode(sgm.getProductCode(),clientId));
//						inboundRePickItem.setOrderId(orderId);
//						inboundRePickItem.setQty(sgm.getQty());
//						inboundRePickItem.setSn(sgm.getSn());
//						inboundRePickItem.setState(nor);
//						inboundRePickItem.setUnitPackage(irpai.getUnitPackage());
//						inboundRePickItem.setWarehouse(irpai.getWarehouse());
//						inboundRePickItem.setTempJobId(tempJobIdOutbound);
//						inboundRePickItem.setInvoice(irpai.getInvoice());
//						irpis.saveInboundRePickItem(inboundRePickItem);
//						continue;
////						}
//					}
//				}
//			}
//			确保每个TemplateJobId在第一此后都变成UUID
//			for(InboundRePutAwayItem in:inboundRePutAwayItemList){
//				in.setTempJobId(in.getId());
//				irpas.updateInboundRePutAwayItem(in);
//			}
//		}else{
//			inboundRePutAwayItemList = irpas.findNormalByJobId(jobId);
//			inboundRePickItemList = irpis.findNormalByJobId(jobId);
////			先检查下架
//			for(InboundRePickItem irpi:inboundRePickItemList){
//				for(StorageGoodModel sgm:list){
//					if(irpi.getTempJobId().equals(sgm.getJobId())){
//						boolean label = false;
//						Bin bin = bs.findBinByCode(wm.getWarehouseId(), sgm.getBinCode());
//						if(irpi.getMaterial().getCode().equals(sgm.getProductCode())){
//							if(irpi.getSn().equals(sgm.getSn())){
//								if(irpi.getBin().getCode().equals(bin.getCode())){
//									label = true;
////									因为从同一个库位拿同一个料号同一个SN的东西的记录在之前合并了
////									判断数量
//									if(irpi.getQty()>=sgm.getQty()){
//										irpi.setQty(irpi.getQty()-sgm.getQty());
//										if(irpi.getQty()==0f){
//											irpi.setState(del);
//											irpis.updateInboundRePickItem(irpi);
//										}
////									数量不对
//									}else{
//										inboundRePutAwayItem = new InboundRePutAwayItem();
//										inboundRePutAwayItem.setCreationTime(new Timestamp(System.currentTimeMillis()));
//										inboundRePutAwayItem.setJobId(jobId);
//										
//										Set<Item> next = irpi.getNextItems();
//										if(next==null){
//											next = new HashSet<Item>();
//											next.add(inboundRePutAwayItem);
//										}else{
//											next.add(inboundRePutAwayItem);
//										}
//										irpi.setNextItems(next);
//										
//										Set<Item> last = new HashSet<Item>();
//										last.add(irpi);
//										inboundRePutAwayItem.setLastItems(last);
//										
//										inboundRePutAwayItem.setBin(bin);
//										inboundRePutAwayItem.setMaterial(irpi.getMaterial());
//										inboundRePutAwayItem.setOrderId(orderId);
//										inboundRePutAwayItem.setQty(sgm.getQty()-irpi.getQty());
//										inboundRePutAwayItem.setSn(sgm.getSn());
//										inboundRePutAwayItem.setState(nor);
////										inboundRePutAwayItem.setTempJobId(jobId);
//										inboundRePutAwayItem.setUnitPackage(irpi.getUnitPackage());
//										inboundRePutAwayItem.setWarehouse(irpi.getWarehouse());
//										
//										irpas.saveInboundRePutAwayItem(inboundRePutAwayItem);
//										inboundRePutAwayItem.setTempJobId(inboundRePutAwayItem.getId());
//										irpas.updateInboundRePutAwayItem(inboundRePutAwayItem);
//										irpi.setState(del);
//										irpis.updateInboundRePickItem(irpi);
//									}									
//								}
//							}
//						}
////						出错则要把这些东西全部上架
//						if(label==false){
//							inboundRePutAwayItem = new InboundRePutAwayItem();
//							inboundRePutAwayItem.setCreationTime(new Timestamp(System.currentTimeMillis()));
//							inboundRePutAwayItem.setJobId(jobId);
//						
//							Set<Item> next = irpi.getNextItems();
//							if(next==null){
//								next = new HashSet<Item>();
//								next.add(inboundRePutAwayItem);
//							}else{
//								next.add(inboundRePutAwayItem);
//							}
//							irpi.setNextItems(next);
//						
//							Set<Item> last = new HashSet<Item>();
//							last.add(irpi);
//							inboundRePutAwayItem.setLastItems(last);
//						
//							inboundRePutAwayItem.setBin(bin);
//							inboundRePutAwayItem.setMaterial(ms.findMaterialByCode(sgm.getProductCode()));
//							inboundRePutAwayItem.setOrderId(orderId);
//							inboundRePutAwayItem.setQty(sgm.getQty());
//							inboundRePutAwayItem.setSn(sgm.getSn());
//							inboundRePutAwayItem.setState(nor);
////							inboundRePutAwayItem.setTempJobId(jobId);
////							inboundRePutAwayItem.setUnitPackage(ci.getUnitPackage());
//							inboundRePutAwayItem.setWarehouse(irpi.getWarehouse());
//						
//							irpas.saveInboundRePutAwayItem(inboundRePutAwayItem);
//							inboundRePutAwayItem.setTempJobId(inboundRePutAwayItem.getId());
//							irpas.updateInboundRePutAwayItem(inboundRePutAwayItem);
//							irpis.updateInboundRePickItem(irpi);
//						}
//					}
//				}
//			}
////			再检查上架
//			for(InboundRePutAwayItem irpai:inboundRePutAwayItemList){
//				for(StorageGoodModel sgm:list){
//					if(irpai.getTempJobId().equals(sgm.getJobId())){
//						boolean label = false;
////						Bin bin = bs.findBinByCode(wm.getWarehouseId(), sgm.getBinCode());
//						if(irpai.getMaterial().getCode().equals(sgm.getProductCode())){
//							if(irpai.getSn().equals(sgm.getSn())){
////								有些记录是一开始没做，有些记录是错了需要你拿回去，区别就是有没有bin
//								if(irpai.getBin()!=null){
//									label = true;
////									一开始做了
//									if(irpai.getBin().getCode().equals(sgm.getBinCode())){
//										if(irpai.getQty()>=sgm.getQty()){
//											irpai.setQty(irpai.getQty()-sgm.getQty());
//											if(irpai.getQty()==0f){
//												irpai.setState(del);
//											}
//											irpas.updateInboundRePutAwayItem(irpai);
//										}else{
//											inboundRePickItem = new InboundRePickItem();
//											Bin bin = bs.findBinByCode(wm.getWarehouseId(), sgm.getBinCode());
//											inboundRePickItem.setBin(bin);
//											inboundRePickItem.setCreationTime(new Timestamp(System.currentTimeMillis()));
//											inboundRePickItem.setJobId(jobId);
//										
//											Set<Item> next = irpai.getNextItems();
//											if(next==null){
//												next = new HashSet<Item>();
//												next.add(inboundRePickItem);
//											}else{
//												next.add(inboundRePickItem);
//											}
//											irpai.setNextItems(next);
//										
//											Set<Item> last = new HashSet<Item>();
//											last.add(irpai);
//											inboundRePickItem.setLastItems(last);
//										
//											inboundRePickItem.setMaterial(irpai.getMaterial());
//											inboundRePickItem.setOrderId(orderId);
//											inboundRePickItem.setQty(sgm.getQty()-irpai.getQty());
//											inboundRePickItem.setSn(sgm.getSn());
//											inboundRePickItem.setState(nor);
//											inboundRePickItem.setUnitPackage(irpai.getUnitPackage());
//											inboundRePickItem.setWarehouse(irpai.getWarehouse());
//										
//											irpis.saveInboundRePickItem(inboundRePickItem);
//											inboundRePickItem.setTempJobId(inboundRePickItem.getId());
//											irpis.updateInboundRePickItem(inboundRePickItem);
//											irpai.setQty(0f);
//											irpai.setState(del);
//											irpas.updateInboundRePutAwayItem(irpai);
//										}
//									}else{
//										inboundRePickItem = new InboundRePickItem();
//										Bin bin = bs.findBinByCode(wm.getWarehouseId(), sgm.getBinCode());
//										inboundRePickItem.setBin(bin);
//										inboundRePickItem.setCreationTime(new Timestamp(System.currentTimeMillis()));
//										inboundRePickItem.setJobId(jobId);
//									
//										Set<Item> next = irpai.getNextItems();
//										if(next==null){
//											next = new HashSet<Item>();
//											next.add(inboundRePickItem);
//										}else{
//											next.add(inboundRePickItem);
//										}
//										irpai.setNextItems(next);
//									
//										Set<Item> last = new HashSet<Item>();
//										last.add(irpai);
//										inboundRePickItem.setLastItems(last);
//									
//										inboundRePickItem.setMaterial(irpai.getMaterial());
//										inboundRePickItem.setOrderId(orderId);
//										inboundRePickItem.setQty(sgm.getQty()-irpai.getQty());
//										inboundRePickItem.setSn(sgm.getSn());
//										inboundRePickItem.setState(nor);
//										inboundRePickItem.setUnitPackage(irpai.getUnitPackage());
//										inboundRePickItem.setWarehouse(irpai.getWarehouse());
//									
//										irpis.saveInboundRePickItem(inboundRePickItem);
//										inboundRePickItem.setTempJobId(inboundRePickItem.getId());
//										irpis.updateInboundRePickItem(inboundRePickItem);
//										irpas.updateInboundRePutAwayItem(irpai);
//									}
//								}else{
//									label = true;
////									一开始没做
//									Bin bin = bs.findBinByCode(wm.getWarehouseId(), sgm.getBinCode());
////									看Bin正不正确	
//									boolean binLabel = false;
//									List<Zone> li = bin.getZones();
//									for(Zone zone:li){
//										OwnerZone zo = ozs.findOwnerZoneById(zone.getId());
//										if(zo!=null){
//											if(zo.getInttClientDetailWSId().equals(String.valueOf(wm.getClientId()))){
//												binLabel = true;
//												break;
//											}
//										}
//									}
////									如果Bin错误，则写RePickItem记录，RePutAway记录不去做
//									if(bin.getState().getCode().equals(String.valueOf(BinState.LOCKED))||binLabel==false){
//										inboundRePickItem = new InboundRePickItem();
//										inboundRePickItem.setBin(bin);
//										inboundRePickItem.setCreationTime(new Timestamp(System.currentTimeMillis()));
//										inboundRePickItem.setJobId(jobId);
//									
//										Set<Item> next = irpai.getNextItems();
//										if(next==null){
//											next = new HashSet<Item>();
//											next.add(inboundRePickItem);
//										}else{
//											next.add(inboundRePickItem);
//										}
//										irpai.setNextItems(next);
//									
//										Set<Item> last = new HashSet<Item>();
//										last.add(irpai);
//										inboundRePickItem.setLastItems(last);
//									
//										inboundRePickItem.setMaterial(irpai.getMaterial());
//										inboundRePickItem.setOrderId(orderId);
//										inboundRePickItem.setQty(sgm.getQty());
//										inboundRePickItem.setSn(sgm.getSn());
//										inboundRePickItem.setState(nor);
//										inboundRePickItem.setUnitPackage(irpai.getUnitPackage());
//										inboundRePickItem.setWarehouse(irpai.getWarehouse());
//									
//										irpis.saveInboundRePickItem(inboundRePickItem);
//										inboundRePickItem.setTempJobId(inboundRePickItem.getId());
//										irpis.updateInboundRePickItem(inboundRePickItem);
//										irpas.updateInboundRePutAwayItem(irpai);
//									}else{
////										当数量够扣
//										if(irpai.getQty()>=sgm.getQty()){	
//											putAwayItem = new PutAwayItem();
//											putAwayItem.setBin(bin);
//											putAwayItem.setCreationTime(new Timestamp(System.currentTimeMillis()));
//											putAwayItem.setJobId(jobId);
//											Set<Item> next = irpai.getNextItems();
//											if(next!=null){
//												next.add(putAwayItem);
//											}else{
//												next = new HashSet<Item>();
//												next.add(putAwayItem);
//											}
//											irpai.setNextItems(next);
//											Set<Item> last = new HashSet<Item>();
//											last.add(irpai);
//											putAwayItem.setLastItems(last);
//											putAwayItem.setMaterial(irpai.getMaterial());
//											putAwayItem.setOrderId(orderId);
//											putAwayItem.setQty(sgm.getQty());
//											putAwayItem.setSn(sgm.getSn());
//											putAwayItem.setState(nor);
//											putAwayItem.setUnitPackage(irpai.getUnitPackage());
//											putAwayItem.setWarehouse(irpai.getWarehouse());
//										
//											irpai.setQty(irpai.getQty()-sgm.getQty());
//											if(irpai.getQty()==0f){
////												是零则删除这条记录
//												irpai.setState(del);
//											}
//											pais.savePutAwayItem(putAwayItem);
//											irpas.updateInboundRePutAwayItem(irpai);
//										}else{
////										当数量不够扣
////										先写RePutAway数量
//											putAwayItem = new PutAwayItem();
//											putAwayItem.setBin(bin);
//											putAwayItem.setCreationTime(new Timestamp(System.currentTimeMillis()));
//											putAwayItem.setJobId(jobId);
//											Set<Item> next = irpai.getNextItems();
//											if(next!=null){
//												next.add(putAwayItem);
//											}else{
//												next = new HashSet<Item>();
//												next.add(putAwayItem);
//											}
//											irpai.setNextItems(next);
//											Set<Item> last = new HashSet<Item>();
//											last.add(irpai);
//											putAwayItem.setLastItems(last);
//											putAwayItem.setMaterial(irpai.getMaterial());
//											putAwayItem.setOrderId(orderId);
//											putAwayItem.setQty(irpai.getQty());
//											putAwayItem.setSn(sgm.getSn());
//											putAwayItem.setState(nor);
//											putAwayItem.setUnitPackage(irpai.getUnitPackage());
//											putAwayItem.setWarehouse(irpai.getWarehouse());
//											pais.savePutAwayItem(putAwayItem);
//										
////											再写RePick
//											inboundRePickItem = new InboundRePickItem();
//											inboundRePickItem.setBin(bin);
//											inboundRePickItem.setCreationTime(new Timestamp(System.currentTimeMillis()));
//											inboundRePickItem.setJobId(jobId);
//										
//											Set<Item> next1 = irpai.getNextItems();
//											if(next1==null){
//												next1 = new HashSet<Item>();
//												next1.add(inboundRePickItem);
//											}else{
//												next1.add(inboundRePickItem);
//											}
//											irpai.setNextItems(next1);
//										
//											Set<Item> last1 = new HashSet<Item>();
//											last1.add(irpai);
//											inboundRePickItem.setLastItems(last1);
//										
//											inboundRePickItem.setMaterial(irpai.getMaterial());
//											inboundRePickItem.setOrderId(orderId);
//											inboundRePickItem.setQty(sgm.getQty()-irpai.getQty());
//											inboundRePickItem.setSn(sgm.getSn());
//											inboundRePickItem.setState(nor);
//											inboundRePickItem.setUnitPackage(irpai.getUnitPackage());
//											inboundRePickItem.setWarehouse(irpai.getWarehouse());
//											irpis.saveInboundRePickItem(inboundRePickItem);
//											inboundRePickItem.setTempJobId(inboundRePickItem.getId());
//											irpis.updateInboundRePickItem(inboundRePickItem);
//										
////											是零则删除这条记录									
//											irpai.setQty(0f);
//											irpai.setState(del);
//											irpas.updateInboundRePutAwayItem(irpai);
//										}
//									}
//								}
//							}
//						}
//						if(label==false){
//							inboundRePickItem = new InboundRePickItem();
//							Bin bin = bs.findBinByCode(wm.getWarehouseId(), sgm.getBinCode());
//							inboundRePickItem.setBin(bin);
//							inboundRePickItem.setCreationTime(new Timestamp(System.currentTimeMillis()));
//							inboundRePickItem.setJobId(jobId);
//						
//							Set<Item> next1 = irpai.getNextItems();
//							if(next1==null){
//								next1 = new HashSet<Item>();
//								next1.add(inboundRePickItem);
//							}else{
//								next1.add(inboundRePickItem);
//							}
//							irpai.setNextItems(next1);
//						
//							Set<Item> last1 = new HashSet<Item>();
//							last1.add(irpai);
//							inboundRePickItem.setLastItems(last1);
//						
//							inboundRePickItem.setMaterial(irpai.getMaterial());
//							inboundRePickItem.setOrderId(orderId);
//							inboundRePickItem.setQty(sgm.getQty());
//							inboundRePickItem.setSn(sgm.getSn());
//							inboundRePickItem.setState(nor);
//							inboundRePickItem.setUnitPackage(irpai.getUnitPackage());
//							inboundRePickItem.setWarehouse(irpai.getWarehouse());
//							irpis.saveInboundRePickItem(inboundRePickItem);
//							inboundRePickItem.setTempJobId(inboundRePickItem.getId());
//							irpis.updateInboundRePickItem(inboundRePickItem);
//						}
//					}
//				}
//			}
//		}
//		如果需要上架和需要下架数为0，则这票进库完成
		List<InboundRePickItem> li = irpis.findNormalByJobId(jobId);
		List<InboundRePutAwayItem> lis = irpas.findNormalByJobId(jobId);
		if(li.size()!=0){
			for(InboundRePickItem ip:li){
				ip.setTempJobId(tempJobIdOutbound);
				irpis.updateInboundRePickItem(ip);
			}
		}
		
		if(lis.size()!=0){
			for(InboundRePutAwayItem ip:lis){
				ip.setTempJobId(tempJobIdInbound);
				irpas.updateInboundRePutAwayItem(ip);
			}
		}
		if(li.size()==0&&lis.size()==0){
			List<PutAwayItem> ii = pais.findNormalByJobId(jobId);
			for(PutAwayItem pai:ii){
				storageItem = new StorageItem();
				storageItem.setBin(pai.getBin());
				storageItem.setCreationTime(new Timestamp(System.currentTimeMillis()));
				storageItem.setJobId(jobId);
				
				Set<Item> next = pai.getNextItems();
				if(next!=null){
					next.add(storageItem);
				}else{
					next = new HashSet<Item>();
					next.add(storageItem);
				}
				pai.setNextItems(next);
				
				Set<Item> last = new HashSet<Item>();
				last.add(pai);
				storageItem.setLastItems(last);
				
				storageItem.setMaterial(pai.getMaterial());
				storageItem.setOrderId(orderId);
				storageItem.setQty(pai.getQty());
				storageItem.setState(nor);
				storageItem.setSn(pai.getSn());
				storageItem.setUnitPackage(pai.getUnitPackage());
				storageItem.setWarehouse(pai.getWarehouse());
				storageItem.setInvoice(pai.getInvoice());
				
				//michael_wang_2009_10_27
				storageItem.setF_currency(pai.getF_currency());
				storageItem.setF_gross_price(pai.getF_gross_price());
				storageItem.setF_gross_weight(pai.getF_unit_weight());
				storageItem.setF_unit_price(pai.getF_unit_price());
				storageItem.setF_unit_weight(pai.getF_unit_weight());
				
				//michael_wang 200901214
				storageItem.setMawb(wm.getMawb());
				storageItem.setHawb(wm.getHawb());
				
				storageItem.setItemType(pai.getItemType());
				
				sis.addStorageItem(storageItem);
				pais.updatePutAwayItem(pai);
				List<CheckingItem> cl = cis.findNormalByJobId(jobId);
				for(CheckingItem ci:cl){
					ci.setState(com);
					cis.updateCheckItem(ci);
				}
			}
			//存入入库记录
			for(int i=0;i<ii.size();i++){
				PutAwayItem pai=ii.get(i);
				LogStorageItem lsi=new LogStorageItem();
				lsi.setBin(pai.getBin());
				lsi.setCreationTime(new Timestamp(System.currentTimeMillis()));
				lsi.setInvoice(pai.getInvoice());
				
				//michael_wang_2009_10_27
				lsi.setF_currency(pai.getF_currency());
				lsi.setF_gross_price(pai.getF_gross_price());
				lsi.setF_gross_weight(pai.getF_unit_weight());
				lsi.setF_unit_price(pai.getF_unit_price());
				lsi.setF_unit_weight(pai.getF_unit_weight());
				
				//michael_wang 20091214
				lsi.setHawb(wm.getHawb());
				lsi.setMawb(wm.getMawb());
				
				lsi.setItemType(pai.getItemType());
				lsi.setJobId(pai.getJobId());
				lsi.setMaterial(pai.getMaterial());
				lsi.setOrderId(orderId);
				lsi.setQty(pai.getQty());
				lsi.setRemark(pai.getRemark());
				lsi.setSn(pai.getSn());
				lsi.setCrn(wm.getCrn());
				lsi.setState(iss.findItemStateByCode(ItemState.NORMAL));
				lsi.setUnitPackage(pai.getUnitPackage());
				lsi.setWarehouse(pai.getWarehouse());
				lsis.addLogStorageItem(lsi);
			}
			
					
			try {
				ccpt.alterStatusToCompleted(jobId,accountName);
				ws.alterToInboundComplete(Integer.parseInt(orderId));
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public boolean onPrint() throws Exception{
		List<InboundRePickItem> xia = irpis.findNormalByJobId(jobId); 
		List<InboundRePutAwayItem> shang = irpas.findNormalByJobId(jobId);
		String fName = generator(jobId,xia,shang);
		this.setRedirect("../Output/" + fName+".xls");
		return true;
	}
	private String generator(String jobId,List<InboundRePickItem> xia,List<InboundRePutAwayItem> shang) throws Exception{
		DocumentImpl doc = (DocumentImpl)DocumentImpl.getInstance();
		doc.setName("inboundList");
		Template tem = new TemplateImpl("inboundProcess.xls");
		ExporterInboundImpl export = new ExporterInboundImpl();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		HashMap map = new HashMap();
		if(xia.size()!=0){
			map.put("xiatempJobId", xia.get(0).getTempJobId());
		}
		if(shang.size()!=0){
			map.put("shangtempJobId", shang.get(0).getTempJobId());
		}
		WmsOrder wo = ws.findByJobId(jobId);
		
		map.put("date", format.format(ts));
		map.put("jobId", jobId);
		map.put("client", wo.getClientName());
		map.put("clientJobId", wo.getRn());
		for(InboundRePutAwayItem ir:shang){
			if(ir.getBin()==null){
				List<StorageItem> si = sis.findStorageItemByProductCodeAndClient_id(" from StorageItem i where i.material.code='" + ir.getMaterial().getCode()+"' and i.material.inttClientDetailWSId='" + wo.getClientId() +"'");
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
				ir.setReferenceBin(bc);
			}
		}
		export.generate(tem, xia,shang, map, doc);
		return doc.getFileName()+doc.getName();
	}
	
	public boolean onSure(){
		List<InboundRePickItem> inspr = irpis.findNormalByJobId(jobId);
//		如果size是另则可以强行确认，不是则不行。
		if(inspr.size()!=0){
			ff.setError(this.getMessage("error4"));
			return false;
		}else{
			ItemState nor = oiss.findByCode(String.valueOf(ItemState.NORMAL));
			ItemState del = oiss.findByCode(String.valueOf(ItemState.DELETE));
//			ItemState lock = oiss.findByCode(String.valueOf(ItemState.EXCEPTION));
			ItemState com = oiss.findByCode(String.valueOf(ItemState.INBOUND_COMPLETED));
			List<PutAwayItem> pl = pais.findNormalByJobId(jobId);
			StorageItem storageItem = null;
			for(PutAwayItem pai:pl){
				storageItem = new StorageItem();
				storageItem.setBin(pai.getBin());
				storageItem.setCreationTime(new Timestamp(System.currentTimeMillis()));
				storageItem.setJobId(jobId);
				
				Set<Item> next = pai.getNextItems();
				if(next!=null){
					next.add(storageItem);
				}else{
					next = new HashSet<Item>();
					next.add(storageItem);
				}
				pai.setNextItems(next);
				
				Set<Item> last = new HashSet<Item>();
				last.add(pai);
				storageItem.setLastItems(last);
				
				storageItem.setMaterial(pai.getMaterial());
				storageItem.setOrderId(orderId);
				storageItem.setQty(pai.getQty());
				storageItem.setState(nor);
				storageItem.setSn(pai.getSn());
				storageItem.setUnitPackage(pai.getUnitPackage());
				storageItem.setWarehouse(pai.getWarehouse());
				storageItem.setInvoice(pai.getInvoice());
				

				//michael_wang_2009_10_27
				storageItem.setF_currency(pai.getF_currency());
				storageItem.setF_gross_price(pai.getF_gross_price());
				storageItem.setF_gross_weight(pai.getF_unit_weight());
				storageItem.setF_unit_price(pai.getF_unit_price());
				storageItem.setF_unit_weight(pai.getF_unit_weight());
				
				storageItem.setItemType(pai.getItemType());
				
				//michael_wang 200901214
				storageItem.setMawb(wm.getMawb());
				storageItem.setHawb(wm.getHawb());
				
				sis.addStorageItem(storageItem);
				pais.updatePutAwayItem(pai);
			}
			List<CheckingItem> cl = cis.findNormalByJobId(jobId);
			for(CheckingItem ci:cl){
				ci.setState(com);
				cis.updateCheckItem(ci);
			}
			//存入入库记录
			for(int i=0;i<pl.size();i++){
				PutAwayItem pai=pl.get(i);
				LogStorageItem lsi=new LogStorageItem();
				lsi.setBin(pai.getBin());
				lsi.setCreationTime(new Timestamp(System.currentTimeMillis()));
				lsi.setInvoice(pai.getInvoice());
				
				//michael_wang_2009_10_27
				lsi.setF_currency(pai.getF_currency());
				lsi.setF_gross_price(pai.getF_gross_price());
				lsi.setF_gross_weight(pai.getF_unit_weight());
				lsi.setF_unit_price(pai.getF_unit_price());
				lsi.setF_unit_weight(pai.getF_unit_weight());
				
				//michael_wang 20091214
				lsi.setHawb(wm.getHawb());
				lsi.setMawb(wm.getMawb());
				
				lsi.setItemType(pai.getItemType());
				lsi.setJobId(pai.getJobId());
				lsi.setMaterial(pai.getMaterial());
				lsi.setOrderId(orderId);
				lsi.setQty(pai.getQty());
				lsi.setCrn(wm.getCrn());
				lsi.setRemark(pai.getRemark());
				lsi.setSn(pai.getSn());
				lsi.setState(iss.findItemStateByCode(ItemState.NORMAL));
				lsi.setUnitPackage(pai.getUnitPackage());
				lsi.setWarehouse(pai.getWarehouse());
				lsis.addLogStorageItem(lsi);
			}
			ws.alterToInboundComplete(Integer.parseInt(orderId));
			try {
				ccpt.alterStatusToCompleted(jobId,CommonAccount.getInstance(getContext().getSession()).accountName);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
	
	//和CheckingItem作对比
//	public void writePartStorageResult(Map map){
//		ItemState nor = oiss.findByCode(String.valueOf(ItemState.NORMAL));
//		ItemState del = oiss.findByCode(String.valueOf(ItemState.DELETE));
//		List<CheckingItem> cli = cis.findNotComplete(jobId);
//		//先将所有的checkingitem的放错数置为0
//		for(CheckingItem c:cli){
//			c.setErrorBinQty(0f);
//		}
//		//删除所有出错信息
//		List<UnstorageItem> usi = uis.findNormalByJobId(jobId);
//		if(usi.size()!=0){
//			for(UnstorageItem u:usi){
//				u.setState(del);
//				uis.updateUnstorageItem(u);
//			}
//		}
//		List list = (List)map.get(jobId);
//		StorageItem si= null;
//		UnstorageItem uso = null;
//		StorageGoodModel  sgm = null;
//		CheckingItem ci = null;
//		List<StorageItem> storageItemList = new ArrayList<StorageItem>();
//		List<UnstorageItem> unstorageItemList = new ArrayList<UnstorageItem>();
//		for(int i=0;i<cli.size();i++){
//			ci = cli.get(i);
//			for(int j=0;j<list.size();j++){
//				sgm = (StorageGoodModel)list.get(j);
//				if(sgm.getProductCode().equals(ci.getMaterial().getCode())){
//					if(sgm.getSn().equals(ci.getSn())){
//						Bin bin = bs.findBinByCode(wm.getWarehouseId(), sgm.getBinCode());
//						//判断是不是放错库位
//						boolean binLabel = false;
//						List<Zone> li = bin.getZones();
//						for(Zone zone:li){
//							OwnerZone zo = ozs.findOwnerZoneById(zone.getId());
//							if(zo!=null){
//								if(zo.getInttClientDetailWSId().equals(String.valueOf(wm.getClientId()))){
//									binLabel = true;
//									break;
//								}
//							}
//						}
//						//如果库位被锁定或者库位不是这个货主的，那么写unstorageitem
//						if(bin.getState().getCode().equals(String.valueOf(BinState.LOCKED))||binLabel==false){
//							uso = new UnstorageItem();
//							uso.setBin(bin);
//							uso.setCreationTime(new Timestamp(System.currentTimeMillis()));
//							uso.setJobId(jobId);
//							Set<Item> last = new HashSet<Item>();
//							last.add(ci);
//							uso.setLastItems(last);
//							uso.setMaterial(ci.getMaterial());
//							Set<Item> next = ci.getNextItems();
//							if(next!=null){
//								next.add(uso);
//							}else{
//								next = new HashSet<Item>();
//								next.add(uso);
//							}
//							ci.setNextItems(next);
//							uso.setOrderId(orderId);
//							uso.setQty(sgm.getQty());
//							uso.setSn(sgm.getSn());
//							uso.setState(nor);
//							uso.setUnitPackage(ci.getUnitPackage());
//							uso.setWarehouse(whs.findWarehouseById(wm.getWarehouseId()));
//							//设置error数量
//							if(ci.getErrorBinQty()!=null){
//								ci.setErrorBinQty(ci.getErrorBinQty()+sgm.getQty());
//							}else{
//								ci.setErrorBinQty(sgm.getQty());
//							}
//							unstorageItemList.add(uso);
//							uis.saveUnstorageItem(uso);
//							//如果库位放对，则放
//						}else{
//							si = new StorageItem();
//							si.setBin(bin);
//							si.setCreationTime(new Timestamp(System.currentTimeMillis()));
//							si.setJobId(jobId);
//							//设置next和last
//							Set<Item> last = new HashSet<Item>();
//							last.add(ci);
//							si.setLastItems(last);
//							si.setMaterial(ci.getMaterial());
//							Set<Item> next = ci.getNextItems();
//							if(next!=null){
//								next.add(si);
//							}else{
//								next = new HashSet<Item>();
//								next.add(si);
//							}
//							ci.setNextItems(next);
//							si.setOrderId(orderId);
//							si.setQty(sgm.getQty());
//							si.setSn(sgm.getSn());
//							si.setState(nor);
//							si.setUnitPackage(ci.getUnitPackage());
//							si.setWarehouse(whs.findWarehouseById(wm.getWarehouseId()));
//							//设置putaway数量
//							if(ci.getPutawayQty()!=null){
//								ci.setPutawayQty(ci.getPutawayQty()+sgm.getQty());
//							}else{
//								ci.setPutawayQty(sgm.getQty());
//							}
//							storageItemList.add(si);
//							sis.addStorageItem(si);
//						}
//					}		
//				}
//			}
//		}
//		ItemState com = oiss.findByCode(String.valueOf(ItemState.INBOUND_COMPLETED));
//		//设置checkingItem的状态，查找所有的checkingItem，判断putaway数量和arrived数量，相等则置上架完成状态，不对则要具体分析
//		for(CheckingItem cc:cli){
////			System.out.println(cc.getMaterial().getAlias());
//			//相等则直接置置为上架完成状态
//			if(cc.getPutawayQty().floatValue()==cc.getArrivedQty().floatValue()){
//				cc.setState(com);
//				cis.updateCheckItem(cc);
//			}
//				//少上，则不置状态，但是要写unstroageitem表
//			if(cc.getPutawayQty().floatValue()+cc.getErrorBinQty().floatValue()<cc.getArrivedQty().floatValue()){						
//				uso = new UnstorageItem();
////				uso.setBin(bin);
//				uso.setCreationTime(new Timestamp(System.currentTimeMillis()));
//				uso.setJobId(jobId);
//				Set<Item> last = new HashSet<Item>();
//				last.add(cc);
//				uso.setLastItems(last);
//				uso.setMaterial(cc.getMaterial());
//				Set<Item> next = cc.getNextItems();
//				if(next!=null){
//					next.add(uso);
//				}else{
//					next = new HashSet<Item>();
//					next.add(uso);
//				}
//				cc.setNextItems(next);
//				uso.setOrderId(orderId);
//				//设置数量
//				uso.setQty(0f);
//				uso.setUnwantedQty(0f);
//				uso.setWantQty(cc.getArrivedQty()-cc.getPutawayQty()-cc.getErrorBinQty());
//				uso.setSn(sgm.getSn());
//				uso.setState(nor);
//				uso.setUnitPackage(cc.getUnitPackage());
//				uso.setWarehouse(whs.findWarehouseById(wm.getWarehouseId()));
//				unstorageItemList.add(uso);
//				uis.saveUnstorageItem(uso);
//				cis.updateCheckItem(cc);
//			}
//			//多上，置状态，但是也要写unstorageitem表
//			if(cc.getPutawayQty().floatValue()+cc.getErrorBinQty().floatValue()>cc.getArrivedQty().floatValue()){
//				String pCode = cc.getMaterial().getCode();
//				String sn = cc.getSn();
//				cc.setState(com);
//				cis.updateCheckItem(cc);
//				float spare = cc.getPutawayQty()-cc.getArrivedQty()-cc.getErrorBinQty();
//				for(StorageItem ss:storageItemList){
//					if(ss.getMaterial().getCode().equals(pCode)){
//						if(ss.getSn().equals(sn)){
//							float qty = ss.getQty();
//							//如果一个库位够下，则写完unstroageitem跳出循环
//							if(qty-spare>=0){
//								uso = new UnstorageItem();
//								uso.setBin(ss.getBin());
//								uso.setCreationTime(new Timestamp(System.currentTimeMillis()));
//								uso.setJobId(jobId);
//								Set<Item> last = new HashSet<Item>();
//								last.add(cc);
//								uso.setLastItems(last);
//								uso.setMaterial(cc.getMaterial());
//								Set<Item> next = cc.getNextItems();
//								if(next!=null){
//									next.add(uso);
//								}else{
//									next = new HashSet<Item>();
//									next.add(uso);
//								}
//								cc.setNextItems(next);
//								uso.setOrderId(orderId);
//									//设置数量
//								uso.setQty(0f);
//								uso.setUnwantedQty(spare);
//								uso.setSn(sgm.getSn());
//								uso.setState(nor);
//								uso.setUnitPackage(cc.getUnitPackage());
//								uso.setWarehouse(whs.findWarehouseById(wm.getWarehouseId()));
//								unstorageItemList.add(uso);
//								uis.saveUnstorageItem(uso);
//								cis.updateCheckItem(cc);
//										
//								break;
//								//这个storageItem里面不够扣，则扣完不能跳出循环
//							}else{
//								uso = new UnstorageItem();
//								uso.setBin(ss.getBin());
//								uso.setCreationTime(new Timestamp(System.currentTimeMillis()));
//								uso.setJobId(jobId);
//								Set<Item> last = new HashSet<Item>();
//								last.add(cc);
//								uso.setLastItems(last);
//								uso.setMaterial(cc.getMaterial());
//								Set<Item> next = cc.getNextItems();
//								if(next!=null){
//									next.add(uso);
//								}else{
//									next = new HashSet<Item>();
//									next.add(uso);
//								}
//								cc.setNextItems(next);
//								uso.setOrderId(orderId);
//								//设置数量
//								uso.setQty(0f);
//								uso.setUnwantedQty(ss.getQty());
//								uso.setSn(sgm.getSn());
//								uso.setState(nor);
//								uso.setUnitPackage(ci.getUnitPackage());
//								uso.setWarehouse(whs.findWarehouseById(wm.getWarehouseId()));
//								unstorageItemList.add(uso);
//								uis.saveUnstorageItem(uso);
//								cis.updateCheckItem(cc);
//							}
//						}
//					}
//				}
//			}		
//		}
//		//判断是不是上了不是这个jobId下的物品
//		boolean llabel = false;
//		for(int i=0;i<list.size();i++){
//			sgm = (StorageGoodModel)list.get(i);
//			for(int j=0;j<cli.size();j++){
//				ci = cli.get(j);
//				if(sgm.getJobId().equals(ci.getJobId())){
//					if(sgm.getProductCode().equals(ci.getMaterial().getCode())){
//						llabel = true;
//						break;
//					}
//				}
//			}
//			if(llabel==true){
//				
//			}else{
//				//发现又不在该JobId下的料件，则记录程错误上架，但是不用记录之前之后操作了
//				uso = new UnstorageItem();
//				Bin bin = bs.findBinByCode(wm.getWarehouseId(), sgm.getBinCode());
//				uso.setBin(bin);
//				uso.setCreationTime(new Timestamp(System.currentTimeMillis()));
//				uso.setJobId(jobId);
////				Set<Item> last = new HashSet<Item>();
////				last.add(ci);
////				uso.setLastItems(last);
//				uso.setMaterial(ci.getMaterial());
////				Set<Item> next = ci.getNextItems();
////				if(next!=null){
////					next.add(uso);
////				}else{
////					next = new HashSet<Item>();
////					next.add(uso);
////				}
////				ci.setNextItems(next);
//				uso.setOrderId(orderId);
//				uso.setQty(sgm.getQty());
//				uso.setSn(sgm.getSn());
//				uso.setState(nor);
//				uso.setUnitPackage(ci.getUnitPackage());
//				uso.setWarehouse(whs.findWarehouseById(wm.getWarehouseId()));
//				//设置error数量
////				if(ci.getErrorBinQty()!=null){
////					ci.setErrorBinQty(ci.getErrorBinQty()+sgm.getQty());
////				}else{
////					ci.setErrorBinQty(sgm.getQty());
////				}
//				unstorageItemList.add(uso);
//				uis.saveUnstorageItem(uso);
//				llabel=false;
//			}
//		}
//		//判断是不是要改变进库订单的状态
//		boolean label = true;
//		for(CheckingItem cci:cli){
//			if(!cci.getState().getCode().equals(ItemState.INBOUND_COMPLETED)){
//				label = false;
//				break;
//			}
//		}
//		if(label==true){
//			ws.alterToInboundComplete(Integer.parseInt(orderId));
//		}
//		this.setRedirect("showStorageResult.htm?orderId=" + orderId);
//	}
	
	public boolean onBack(){
		this.setRedirect("searchInboundOrder.htm");
		return true;
	}
	
//	public boolean onSure(){
//		List<UnstorageItem> cli = uis.findCuo(jobId);
//		List<UnstorageItem> dli = uis.findDuo(jobId);
//		ItemState nor = oiss.findByCode(String.valueOf(ItemState.NORMAL));
//		ItemState com = oiss.findByCode(String.valueOf(ItemState.INBOUND_COMPLETED));
//		ItemState del = oiss.findByCode(String.valueOf(ItemState.DELETE));
//		//将错上的和多上的记录上storageItem里面，因为货物已经上架了
//		for(UnstorageItem un:cli){
//			un.setState(del);
//			StorageItem si = new StorageItem();
//			si.setBin(un.getBin());
//			si.setCreationTime(new Timestamp(System.currentTimeMillis()));
//			si.setJobId(jobId);
//			//设置next和last
//			Set<Item> last = new HashSet<Item>();
//			last.add(un);
//			si.setLastItems(last);
//			si.setMaterial(un.getMaterial());
//			Set<Item> next = un.getNextItems();
//			if(next!=null){
//				next.add(si);
//			}else{
//				next = new HashSet<Item>();
//				next.add(si);
//			}
//			un.setNextItems(next);
//			si.setOrderId(orderId);
//			si.setQty(un.getQty());
//			si.setSn(un.getSn());
//			si.setState(nor);
//			si.setUnitPackage(un.getUnitPackage());
//			si.setWarehouse(whs.findWarehouseById(wm.getWarehouseId()));
//			//设置putaway数量
////			if(si.getPutawayQty()!=null){
////				si.setPutawayQty(ci.getPutawayQty()+sgm.getQty());
////			}else{
////				ci.setPutawayQty(sgm.getQty());
////			}
//			sis.addStorageItem(si);
//			uis.updateUnstorageItem(un);
//		}
//		for(UnstorageItem un:dli){
//			un.setState(del);
//			StorageItem si = new StorageItem();
//			si.setBin(un.getBin());
//			si.setCreationTime(new Timestamp(System.currentTimeMillis()));
//			si.setJobId(jobId);
//			//设置next和last
//			Set<Item> last = new HashSet<Item>();
//			last.add(un);
//			si.setLastItems(last);
//			si.setMaterial(un.getMaterial());
//			Set<Item> next = un.getNextItems();
//			if(next!=null){
//				next.add(si);
//			}else{
//				next = new HashSet<Item>();
//				next.add(si);
//			}
//			un.setNextItems(next);
//			si.setOrderId(orderId);
//			si.setQty(un.getQty());
//			si.setSn(un.getSn());
//			si.setState(nor);
//			si.setUnitPackage(un.getUnitPackage());
//			si.setWarehouse(whs.findWarehouseById(wm.getWarehouseId()));
//			//设置putaway数量
////			if(si.getPutawayQty()!=null){
////				si.setPutawayQty(ci.getPutawayQty()+sgm.getQty());
////			}else{
////				ci.setPutawayQty(sgm.getQty());
////			}
//			sis.addStorageItem(si);
//			uis.updateUnstorageItem(un);
//		}
//		//将所有的checkingitem设为上架完成状态
//		List<CheckingItem> chli = cis.findNormalByJobId(jobId);
//		for(CheckingItem ci:chli){
//			ci.setState(com);
//			cis.updateCheckItem(ci);
//		}
//		ws.alterToInboundComplete(Integer.parseInt(orderId));
//		return true;
//	}
	
	public boolean onSee(){
		this.setRedirect("showStorageResult.htm?orderId=" + orderId);
		return true;
	}
}
