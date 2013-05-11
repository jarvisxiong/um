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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.click.control.Column;
import net.sf.click.control.FileField;
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
import com.intelitune.nwms.model.CheckingItem;
import com.intelitune.nwms.model.InboundOrderItem;
import com.intelitune.nwms.model.Item;
import com.intelitune.nwms.model.ItemState;
import com.intelitune.nwms.model.UncheckedItem;
import com.intelitune.nwms.service.CheckingItemService;
import com.intelitune.nwms.service.CheckingItemServiceImpl;
import com.intelitune.nwms.service.InboundOrderItemService;
import com.intelitune.nwms.service.InboundOrderItemServiceImp;
import com.intelitune.nwms.service.MaterialService;
import com.intelitune.nwms.service.MaterialServiceImpl;
import com.intelitune.nwms.service.OrderItemStateService;
import com.intelitune.nwms.service.OrderItemStateServiceImp;
import com.intelitune.nwms.service.OwnerZoneService;
import com.intelitune.nwms.service.OwnerZoneServiceImp;
import com.intelitune.nwms.service.UncheckedItemService;
import com.intelitune.nwms.service.UncheckedItemServiceImp;
import com.intelitune.nwms.service.WarehouseService;
import com.intelitune.nwms.service.WarehouseServiceImp;
/**
 * @author Louis
 *检验上传的验货信息
 */
public class UpLoadCheckItems extends BorderPage {
	
	public Form form = new Form("form");
//	public TableEx table = new TableEx("table"); 
	public WMSServiceInf ws =  WMSServiceImp.getInstance();
	public OwnerZoneService ozs =  OwnerZoneServiceImp.getInstance();
	public InboundOrderItemService iois =  InboundOrderItemServiceImp.getInstance();
	public CheckingItemService cis =  CheckingItemServiceImpl.getInstance();
	public OrderItemStateService oiss =  OrderItemStateServiceImp.getInstance();
	public MaterialService ms =  MaterialServiceImpl.getInstance();
	public UncheckedItemService uis =  UncheckedItemServiceImp.getInstance();
	public WarehouseService whs =  WarehouseServiceImp.getInstance();
	public HiddenField hf = new HiddenField("orderId",String.class);
	public String query;
	public String title = this.getMessage("uploadcheckitems");
	public String position = this.getMessage("UPLOADCHECKITEMS");
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
	public WmsOrder wm;
	
	public TableEx checking = new TableEx("checking");
	public TableEx bad = new TableEx("bad");
	public TableEx que = new TableEx("que");
	public TableEx duo = new TableEx("duo");
	
	public String sChecking = this.getMessage("sChecking");
	public String sBad = this.getMessage("sBad");
	public String sQue = this.getMessage("sQue");
	public String sDuo = this.getMessage("sDuo");
	
	public UpLoadCheckItems(){
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
		form.add(ff);
		Submit submit = new Submit("submit",this.getMessage("submit"),this,"onAll");
//		Submit see = new Submit("see",this.getMessage("see"),this,"onSee");
//		Submit back = new Submit("back",this.getMessage("back"),this,"onBack");
//		Submit part = new Submit("part",this.getMessage("parts"),this,"onPart");
		Submit sure = new Submit("sure",this.getMessage("sure"),this,"onSure");
//		Submit back1 = new Submit("back1",this.getMessage("back"),this,"onBack");
		form.add(submit);
//		form.add(see);
//		form.add(back);
//		form.add(part);
//		form.add(back1);
		form.add(sure);
		orderId = getContext().getRequestParameter("orderId");
		if(orderId!=null){
			getContext().setSessionAttribute("tt", orderId);
		}else{
			orderId = String.valueOf(getContext().getSessionAttribute("tt"));
		}
		hf.setValue(orderId);
		form.add(hf);
		wm = null;
		try {
			wm = ws.findWmsOrderById(Integer.parseInt(orderId));
			jobId = wm.getJobId();
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
//		all.setAttribute("onclick", "return window.confirm('"+ this.getMessage("all")+"');");
//		parts.setAttribute("onclick", "return window.confirm('"+ this.getMessage("part")+"');");
		sure.setAttribute("onclick", "return window.confirm('"+ this.getMessage("sures")+"');");
		Column column = null;
		column = new Column("material.code",this.getMessage("code"));
		column.setWidth("15%");
		checking.addColumn(column);
		column = new Column("material.alias",this.getMessage("zwalias"));
		column.setWidth("25%");
		checking.addColumn(column);
		column = new Column("sn",this.getMessage("sn"));
		column.setWidth("25%");
		checking.addColumn(column);
		column = new Column("unitPackage.alias",this.getMessage("unitPackage"));
		column.setWidth("15%");
		checking.addColumn(column);
		column = new Column("arrivedQty",this.getMessage("arrivedQty"));
		column.setWidth("20%");
		checking.addColumn(column);
		
	
		column = new Column("material.code",this.getMessage("code"));
		column.setWidth("15%");
		bad.addColumn(column);
		column = new Column("material.alias",this.getMessage("zwalias"));
		column.setWidth("25%");
		bad.addColumn(column);
		column = new Column("sn",this.getMessage("sn"));
		column.setWidth("25%");
		bad.addColumn(column);
		column = new Column("unitPackage.alias",this.getMessage("unitPackage"));
		column.setWidth("15%");
		bad.addColumn(column);
		column = new Column("destroyedQty",this.getMessage("destroyedQty"));
		column.setWidth("20%");
		bad.addColumn(column);
		
		column = new Column("material.code",this.getMessage("code"));
		column.setWidth("15%");
		que.addColumn(column);
		column = new Column("material.alias",this.getMessage("zwalias"));
		column.setWidth("25%");
		que.addColumn(column);
		column = new Column("sn",this.getMessage("sn"));
		column.setWidth("25%");
		que.addColumn(column);
		column = new Column("unitPackage.alias",this.getMessage("unitPackage"));
		column.setWidth("15%");
		que.addColumn(column);
		column = new Column("wantQty",this.getMessage("deficiencyQty"));
		column.setWidth("20%");
		que.addColumn(column);
			
		column = new Column("material.code",this.getMessage("code"));
		column.setWidth("15%");
		duo.addColumn(column);
		column = new Column("material.alias",this.getMessage("zwalias"));
		column.setWidth("25%");
		duo.addColumn(column);
		column = new Column("sn",this.getMessage("sn"));
		column.setWidth("25%");
		duo.addColumn(column);
		column = new Column("unitPackage.alias",this.getMessage("unitPackage"));
		column.setWidth("15%");
		duo.addColumn(column);
		column = new Column("unwantedQty",this.getMessage("unwantedQty"));
		column.setWidth("20%");
		duo.addColumn(column);
	}
	
	public void onRender(){
		checking.setRowList(cis.findNormalByJobId(jobId));
		bad.setRowList(uis.findBad(jobId));
		que.setRowList(uis.findQue(jobId));
		duo.setRowList(uis.findDuo(jobId));
	}
	
	/**
	 * 全部验证
	 */
	public boolean onAll(){
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
				buffer = new byte[1024];
			}
			fos.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map map = null;
		ExamineGood eg = new ExamineGood();
		try {
			map = eg.getCollecton(file.getPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(!map.containsKey(jobId)){
			ff.setError(this.getMessage("error3"));
			return false;
		}
		//判断是不是有货物不是该JobId下的，一旦发现有不是，将不写数据库直接报错
		ExamineGoodModel rgm = null;
		List list = (List)map.get(jobId);
		boolean label = true;
		int l=0;
		for(int i=0;i<list.size();i++){
			boolean innerLabel = false;
			rgm = (ExamineGoodModel)list.get(i);
			List<InboundOrderItem> inboundOrderItemList = iois.findByOrderId(orderId);
			for(InboundOrderItem ii:inboundOrderItemList){
				if(ii.getMaterial().getCode().toUpperCase().equals(rgm.getProductCode())){
					innerLabel = true;
					break;
				}
			}
			if(innerLabel==false){
				label = false;
				l=i+1;
				break;
			}
		}
		if(label==false){			
			ff.setError(this.getMessage("error5")+"  "+rgm.getProductCode());
			return false;
		}
		writeAllCheckingResult(map);
//		this.setRedirect("showCheckResult.htm?orderId=" + orderId);
		return true;
	}
	
	/**
	 * @param map
	 * 完整验证情况
	 */
	public void writeAllCheckingResult(Map map){
		ItemState del = oiss.findByCode(String.valueOf(ItemState.DELETE));
		ItemState nor = oiss.findByCode(String.valueOf(ItemState.NORMAL));
		List list = (List)map.get(jobId);
		//先把已经在数据库中该条记录的checkingitem置为删除状态
		List<CheckingItem> cli = cis.findNormalByJobId(jobId);
		if(cli.size()!=0){
			for(CheckingItem ct:cli){
				ct.setState(del);
				cis.updateCheckItem(ct);
			}
		}
		//把uncheckeditem也置为删除状态
		List<UncheckedItem> uli = uis.findNormalByJobId(jobId);
		for(UncheckedItem ui:uli){
			ui.setState(del);
			uis.updateUncheckedItem(ui);
		}
		
		//遍历查出checkingitem数据,并且创建一个个checkingItem对象将其封装好。
		InboundOrderItem ioi = null;
		ExamineGoodModel rgm = null;
		CheckingItem ci = null;
		UncheckedItem uci = null;
		List<InboundOrderItem> inboundOrderItemList = iois.findByOrderId(orderId);
		List<CheckingItem> checkingItemList = new ArrayList<CheckingItem>();
		List<UncheckedItem> unCheckedItemList = new ArrayList<UncheckedItem>();
		//先生成CheckingItem和UncheckedItem记录，再去计算缺货数。
		for(int i=0;i<list.size();i++){
			rgm = (ExamineGoodModel)list.get(i);
			for(InboundOrderItem ii:inboundOrderItemList){
				if(ii.getMaterial().getCode().equalsIgnoreCase(rgm.getProductCode())){
					ioi = ii;
				}
			}
			//如果坏件数为0
			if(rgm.getBadQty()==0){
				ci = new CheckingItem();
				ci.setWarehouse(whs.findWarehouseById(wm.getWarehouseId()));
				ci.setJobId(rgm.getJobId());
				ci.setMaterial(ioi.getMaterial());
				ci.setCreationTime(new Timestamp(System.currentTimeMillis()));
				ci.setJobId(rgm.getJobId());
				ci.setOrderId(orderId);
				ci.setDestroyedQty(0f);
				ci.setPutawayQty(0f);
				ci.setErrorBinQty(0f);
				ci.setArrivedQty(rgm.getArriveQty());
				ci.setInvoice(ioi.getInvoice());				
				
				//michael_wang 2009_11-16
				ci.setItemType(ioi.getItemType());
				
				ci.setF_currency(ioi.getF_currency());
				ci.setF_gross_price(ioi.getF_gross_price());
				ci.setF_gross_weight(ioi.getF_gross_weight());
				ci.setF_unit_price(ioi.getF_unit_price());
				ci.setF_unit_weight(ioi.getF_unit_weight());
				
				if(rgm.getSn()!=null){
					ci.setSn(rgm.getSn());
				}
				ci.setState(nor);
				ci.setUnitPackage(ioi.getUnitPackage());
				Set<Item> set =  ci.getLastItems();
				if(set!=null){
					set.add(ioi); 
				}else{
					set = new HashSet<Item>();
					set.add(ioi);
				}
				ci.setLastItems(set);
				checkingItemList.add(ci);
				Set<Item> set1 = ioi.getNextItems();
				if(set1!=null){
					set1.add(ci);
				}else{
					set1 = new HashSet<Item>();
					set1.add(ci);
				}
				ioi.setNextItems(set1);
				cis.saveCheckItem(ci);
				iois.updateInboundOrderItem(ioi);
			//有坏件数,好的数量加到checkingItem里面，快件数量记到UncheckedItem里面
			}else{
				//快件数量等于到达数量，则不需要创建checkingItem
				if(rgm.getArriveQty()==rgm.getBadQty()){
					uci = new UncheckedItem();
					uci.setWarehouse(whs.findWarehouseById(wm.getWarehouseId()));
					uci.setJobId(rgm.getJobId());
					uci.setMaterial(ioi.getMaterial());
					uci.setCreationTime(new Timestamp(System.currentTimeMillis()));
					uci.setJobId(rgm.getJobId());
					uci.setOrderId(orderId);
					uci.setDestroyedQty(rgm.getBadQty());
					uci.setArrivedQty(rgm.getArriveQty());
					uci.setWantQty(0f);
					uci.setUnwantedQty(0f);
					uci.setInvoice(ioi.getInvoice());
					
					uci.setF_currency(ioi.getF_currency());
					uci.setF_gross_price(ioi.getF_gross_price());
					uci.setF_gross_weight(ioi.getF_unit_weight());
					uci.setF_unit_price(ioi.getF_unit_price());
					uci.setF_unit_weight(ioi.getF_unit_weight());
					
					if(rgm.getSn()!=null){
						uci.setSn(rgm.getSn());
					}
					uci.setState(nor);
					uci.setUnitPackage(ioi.getUnitPackage());
					Set<Item> set =  uci.getLastItems();
					if(set!=null){
						set.add(ioi);
					}else{
						set = new HashSet<Item>();
						set.add(ioi);
					}
					uci.setLastItems(set);
					unCheckedItemList.add(uci);
					Set<Item> set1 = ioi.getNextItems();
					if(set1!=null){
						set1.add(uci);
					}else{
						set1 = new HashSet<Item>();
						set1.add(uci);
					}
					
					//michael_wang 2009 11-16
					uci.setItemType(ioi.getItemType());
					
					ioi.setNextItems(set1);
					uis.saveUncheckedItem(uci);
					iois.updateInboundOrderItem(ioi);
					//坏件数量小于到达数量，需要设置checkingItem
					}else{
						//置checkingItem
						ci = new CheckingItem();
						ci.setWarehouse(whs.findWarehouseById(wm.getWarehouseId()));
						ci.setJobId(rgm.getJobId());
						ci.setMaterial(ioi.getMaterial());
						ci.setCreationTime(new Timestamp(System.currentTimeMillis()));
						ci.setJobId(rgm.getJobId());
						ci.setOrderId(orderId);
						ci.setDestroyedQty(0f);
						ci.setPutawayQty(0f);
						ci.setErrorBinQty(0f);
						ci.setInvoice(ioi.getInvoice());
						
						
						//michael_wang 2009 11-16
						ci.setItemType(ioi.getItemType());
						
						ci.setF_currency(ioi.getF_currency());
						ci.setF_gross_price(ioi.getF_gross_price());
						ci.setF_gross_weight(ioi.getF_gross_weight());
						ci.setF_unit_price(ioi.getF_unit_price());
						ci.setF_unit_weight(ioi.getF_unit_weight());
						
						ci.setArrivedQty(rgm.getArriveQty()-rgm.getBadQty());
						if(rgm.getSn()!=null){
							ci.setSn(rgm.getSn());
						}
						ci.setState(nor);
						ci.setUnitPackage(ioi.getUnitPackage());
						Set<Item> set =  ci.getLastItems();
						if(set!=null){
							set.add(ioi);
						}else{
							set = new HashSet<Item>();
							set.add(ioi);
						}
						ci.setLastItems(set);
						checkingItemList.add(ci);
						Set<Item> set1 = ioi.getNextItems();
						if(set1!=null){
							set1.add(ci);
						}else{
							set1 = new HashSet<Item>();
							set1.add(ci);
						}
						ioi.setNextItems(set1);
						cis.saveCheckItem(ci);
						iois.updateInboundOrderItem(ioi);
						
						//置UncheckedItem
						uci = new UncheckedItem();
						uci.setWarehouse(whs.findWarehouseById(wm.getWarehouseId()));
						uci.setJobId(rgm.getJobId());
						uci.setMaterial(ioi.getMaterial());
						uci.setCreationTime(new Timestamp(System.currentTimeMillis()));
						uci.setJobId(rgm.getJobId());
						uci.setOrderId(orderId);
						//在这里到达数等于坏件数
						uci.setDestroyedQty(rgm.getBadQty());
						uci.setArrivedQty(rgm.getBadQty());
						uci.setWantQty(0f);
						uci.setUnwantedQty(0f);
						uci.setInvoice(ioi.getInvoice());
						
						
						//michael_wang 2009 11-16
						uci.setItemType(ioi.getItemType());
						
						uci.setF_currency(ioi.getF_currency());
						uci.setF_gross_price(ioi.getF_gross_price());
						uci.setF_gross_weight(ioi.getF_unit_weight());
						uci.setF_unit_price(ioi.getF_unit_price());
						uci.setF_unit_weight(ioi.getF_unit_weight());
						
						if(rgm.getSn()!=null){
							uci.setSn(rgm.getSn());
						}
						uci.setState(nor);
						uci.setUnitPackage(ioi.getUnitPackage());
						Set<Item> set2 =  uci.getLastItems();
						if(set2!=null){
							set2.add(ioi);
						}else{
							set2 = new HashSet<Item>();
							set2.add(ioi);
						}
						uci.setLastItems(set2);
						unCheckedItemList.add(uci);
						Set<Item> set3 = ioi.getNextItems();
						if(set1!=null){
							set3.add(uci);
						}else{
							set3 = new HashSet<Item>();
							set3.add(uci);
						}
						ioi.setNextItems(set3);
						uis.saveUncheckedItem(uci);
						iois.updateInboundOrderItem(ioi);
					}
			}
		}
		//查验是否缺货，如果缺货记UncheckedItem表，但是不和已经有的记录合并。
		for(InboundOrderItem ii:inboundOrderItemList){
			String pCode = ii.getMaterial().getCode();
			float qty = ii.getQty();
			float arr = 0f;
			if(checkingItemList.size()!=0){
				for(CheckingItem check:checkingItemList){
					if(check.getMaterial().getCode().equals(pCode)){
						arr += check.getArrivedQty();
					}
				}
			}
			if(unCheckedItemList.size()!=0){
				for(UncheckedItem unCheck:unCheckedItemList){
					if(unCheck.getMaterial().getCode().equals(pCode)){
						arr += unCheck.getArrivedQty();
					}
				}
			}
			if(qty>arr){
				uci = new UncheckedItem();
				uci.setWarehouse(whs.findWarehouseById(wm.getWarehouseId()));
				uci.setJobId(rgm.getJobId());
				uci.setMaterial(ii.getMaterial());
				uci.setCreationTime(new Timestamp(System.currentTimeMillis()));
				uci.setJobId(rgm.getJobId());
				uci.setOrderId(orderId);
				//只用设置缺货数就可以了
				uci.setUnwantedQty(0f);
				uci.setWantQty(qty-arr);
				uci.setDestroyedQty(0f);
				uci.setArrivedQty(0f);
				uci.setState(nor);
				uci.setUnitPackage(ii.getUnitPackage());
				uci.setInvoice(ii.getInvoice());
				
				
				//michael_wang 2009 11-16
				uci.setItemType(ii.getItemType());
				
				uci.setF_currency(ii.getF_currency());
				uci.setF_gross_price(ii.getF_gross_price());
				uci.setF_gross_weight(ii.getF_unit_weight());
				uci.setF_unit_price(ii.getF_unit_price());
				uci.setF_unit_weight(ii.getF_unit_weight());
				
				Set<Item> set5 =  uci.getLastItems();
				if(set5!=null){
					set5.add(ii);
				}else{
					set5 = new HashSet<Item>();
					set5.add(ii);
				}
				uci.setLastItems(set5);
				unCheckedItemList.add(uci);
				Set<Item> set6 = ii.getNextItems();
				if(set6!=null){
					set6.add(uci);
				}else{
					set6 = new HashSet<Item>();
					set6.add(uci);
				}
				ii.setNextItems(set6);
				uis.saveUncheckedItem(uci);
				iois.updateInboundOrderItem(ii);
			}
			//假如货物比inboundIntem多
			if(qty<arr){
				uci = new UncheckedItem();
				uci.setWarehouse(whs.findWarehouseById(wm.getWarehouseId()));
				uci.setJobId(rgm.getJobId());
				uci.setMaterial(ii.getMaterial());
				uci.setCreationTime(new Timestamp(System.currentTimeMillis()));
				uci.setJobId(rgm.getJobId());
				uci.setOrderId(orderId);
				//只用设置多货物数
				uci.setWantQty(0f);
				uci.setUnwantedQty(arr-qty);
				uci.setDestroyedQty(0f);
				uci.setArrivedQty(arr-qty);
				uci.setState(nor);
				uci.setUnitPackage(ii.getUnitPackage());
				uci.setInvoice(ii.getInvoice());
				
				
				//michael_wang 2009 11-16
				uci.setItemType(ii.getItemType());
				
				uci.setF_currency(ii.getF_currency());
				uci.setF_gross_price(ii.getF_gross_price());
				uci.setF_gross_weight(ii.getF_unit_weight());
				uci.setF_unit_price(ii.getF_unit_price());
				uci.setF_unit_weight(ii.getF_unit_weight());
				
				Set<Item> set5 =  uci.getLastItems();
				if(set5!=null){
					set5.add(ii);
				}else{
					set5 = new HashSet<Item>();
					set5.add(ii);
				}
				uci.setLastItems(set5);
				unCheckedItemList.add(uci);
				Set<Item> set6 = ii.getNextItems();
				if(set6!=null){
					set6.add(uci);
				}else{
					set6 = new HashSet<Item>();
					set6.add(uci);
				}
				ii.setNextItems(set6);
				uis.saveUncheckedItem(uci);
				iois.updateInboundOrderItem(ii);
			}
		}
		//查看数否需要将整个进库订单置为已验货状态状态。如果没有正常装态的该业务下的UncheckedItem，则可以改变
		List<UncheckedItem> unList = uis.findNormalByJobId(jobId);
		if(unList.size()==0){
			ws.alterToInboundChecked(Integer.parseInt(orderId));
		}
	}
	
	
	public boolean onBack(){
		this.setRedirect("searchInboundOrder.htm");
		return true;
	}
	
	/**
	 * 部分验证
	 */
//	public boolean onPart(){
//		if(ff.getFileItem().getName().equals("")){
//			ff.setError(this.getMessage("filecannotbeempty"));
//			return false;
//		}
//		String path = getContext().getServletContext().getRealPath("/inbound/inboundList");
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
//		ExamineGood eg = new ExamineGood();
//		try {
//			map = eg.getCollecton(file.getPath());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		if(!map.containsKey(jobId)){
//			ff.setError(this.getMessage("error3"));
//			return false;
//		}
//		//判断是不是有货物不是该JobId下的，如果确实有，则不写数据库，直接跳出
//		ExamineGoodModel rgm = null;
//		List list = (List)map.get(jobId);
//		boolean label = true;
//		for(int i=0;i<list.size();i++){
//			boolean innerLabel = false;
//			rgm = (ExamineGoodModel)list.get(i);
//			List<InboundOrderItem> inboundOrderItemList = iois.findByOrderId(orderId);
//			for(InboundOrderItem ii:inboundOrderItemList){
//				if(ii.getMaterial().getCode().equals(rgm.getProductCode())){
//					innerLabel = true;
//					break;
//				}
//			}
//			if(innerLabel==false){
//				label = false;
//				break;
//			}
//		}
//		if(label==false){
//			ff.setError(this.getMessage("error5"));
//			return false;
//		}
//		writePartCheckingResult(map);
//		this.setRedirect("showCheckResult.htm?orderId=" + orderId);
//		return true;
//	}
	
	/**
	 * 写部分验证结果
	 */	
//	public void writePartCheckingResult(Map map){
//		ItemState del = oiss.findByCode(String.valueOf(ItemState.DELETE));
//		ItemState nor = oiss.findByCode(String.valueOf(ItemState.NORMAL));
//		List list = (List)map.get(jobId);
//		List<CheckingItem> cli = cis.findNormalByJobId(jobId);
//		//把uncheckeditem置为删除状态
//		List<UncheckedItem> uli = uis.findByJobId(jobId);
//		for(UncheckedItem ui:uli){
//			ui.setState(del);
//			uis.updateUncheckedItem(ui);
//		}
//		//遍历查出checkingitem数据,并且创建一个个checkingItem对象将其封装好。
//		InboundOrderItem ioi = null;
//		ExamineGoodModel rgm = null;
//		CheckingItem ci = null;
//		UncheckedItem uci = null;
//		List<InboundOrderItem> inboundOrderItemList = iois.findByOrderId(orderId);
//		List<CheckingItem> checkingItemList = new ArrayList<CheckingItem>();
//		List<UncheckedItem> unCheckedItemList = new ArrayList<UncheckedItem>();
//		//先生成CheckingItem和UncheckedItem记录，再去计算缺货数。
//		for(int i=0;i<list.size();i++){
//			rgm = (ExamineGoodModel)list.get(i);
//			for(InboundOrderItem ii:inboundOrderItemList){
//				if(ii.getMaterial().getCode().equals(rgm.getProductCode())){
//					ioi = ii;
//				}
//			}
//			//如果坏件数为0
//			if(rgm.getBadQty()==0){
//				ci = new CheckingItem();
//				ci.setJobId(rgm.getJobId());
//				ci.setMaterial(ioi.getMaterial());
//				ci.setCreationTime(new Timestamp(System.currentTimeMillis()));
//				ci.setJobId(rgm.getJobId());
//				ci.setOrderId(orderId);
//				ci.setDestroyedQty(0f);
//				ci.setArrivedQty(rgm.getArriveQty());
//				if(rgm.getSn()!=null){
//					ci.setSn(rgm.getSn());
//				}
//				ci.setState(nor);
//				ci.setUnitPackage(ioi.getUnitPackage());
//				Set<Item> set =  ci.getLastItems();
//				if(set!=null){
//					set.add(ioi);
//				}else{
//					set = new HashSet<Item>();
//					set.add(ioi);
//				}
//				ci.setLastItems(set);
//				checkingItemList.add(ci);
//				Set<Item> set1 = ioi.getNextItems();
//				if(set1!=null){
//					set1.add(ci);
//				}else{
//					set1 = new HashSet<Item>();
//					set1.add(ci);
//				}
//				ioi.setNextItems(set1);
//				cis.saveCheckItem(ci);
//				iois.updateInboundOrderItem(ioi);
//			//有坏件数,好的数量加到checkingItem里面，快件数量记到UncheckedItem里面
//			}else{
//				//快件数量等于到达数量，则不需要创建checkingItem
//				if(rgm.getArriveQty()==rgm.getBadQty()){
//					uci = new UncheckedItem();
//					uci.setJobId(rgm.getJobId());
//					uci.setMaterial(ioi.getMaterial());
//					uci.setCreationTime(new Timestamp(System.currentTimeMillis()));
//					uci.setJobId(rgm.getJobId());
//					uci.setOrderId(orderId);
//					uci.setDestroyedQty(rgm.getBadQty());
//					uci.setArrivedQty(rgm.getArriveQty());
//					uci.setWantQty(0f);
//					if(rgm.getSn()!=null){
//						uci.setSn(rgm.getSn());
//					}
//					uci.setState(nor);
//					uci.setUnitPackage(ioi.getUnitPackage());
//					Set<Item> set =  uci.getLastItems();
//					if(set!=null){
//						set.add(ioi);
//					}else{
//						set = new HashSet<Item>();
//						set.add(ioi);
//					}
//					uci.setLastItems(set);
//					unCheckedItemList.add(uci);
//					Set<Item> set1 = ioi.getNextItems();
//					if(set1!=null){
//						set1.add(uci);
//					}else{
//						set1 = new HashSet<Item>();
//						set1.add(uci);
//					}
//					ioi.setNextItems(set1);
//					uis.saveUncheckedItem(uci);
//					iois.updateInboundOrderItem(ioi);
//					//坏件数量小于到达数量，需要设置checkingItem
//					}else{
//						//置checkingItem
//						ci = new CheckingItem();
//						ci.setJobId(rgm.getJobId());
//						ci.setMaterial(ioi.getMaterial());
//						ci.setCreationTime(new Timestamp(System.currentTimeMillis()));
//						ci.setJobId(rgm.getJobId());
//						ci.setOrderId(orderId);
//						ci.setDestroyedQty(0f);
//						ci.setArrivedQty(rgm.getArriveQty()-rgm.getBadQty());
//						if(rgm.getSn()!=null){
//							ci.setSn(rgm.getSn());
//						}
//						ci.setState(nor);
//						ci.setUnitPackage(ioi.getUnitPackage());
//						Set<Item> set =  ci.getLastItems();
//						if(set!=null){
//							set.add(ioi);
//						}else{
//							set = new HashSet<Item>();
//							set.add(ioi);
//						}
//						ci.setLastItems(set);
//						checkingItemList.add(ci);
//						Set<Item> set1 = ioi.getNextItems();
//						if(set1!=null){
//							set1.add(ci);
//						}else{
//							set1 = new HashSet<Item>();
//							set1.add(ci);
//						}
//						ioi.setNextItems(set1);
//						cis.saveCheckItem(ci);
//						iois.updateInboundOrderItem(ioi);
//						
//						//置UncheckedItem
//						uci = new UncheckedItem();
//						uci.setJobId(rgm.getJobId());
//						uci.setMaterial(ioi.getMaterial());
//						uci.setCreationTime(new Timestamp(System.currentTimeMillis()));
//						uci.setJobId(rgm.getJobId());
//						uci.setOrderId(orderId);
//						//在这里到达数等于坏件数
//						uci.setDestroyedQty(rgm.getBadQty());
//						uci.setArrivedQty(rgm.getBadQty());
//						uci.setWantQty(0f);
//						if(rgm.getSn()!=null){
//							uci.setSn(rgm.getSn());
//						}
//						uci.setState(nor);
//						uci.setUnitPackage(ioi.getUnitPackage());
//						Set<Item> set2 =  uci.getLastItems();
//						if(set2!=null){
//							set2.add(ioi);
//						}else{
//							set2 = new HashSet<Item>();
//							set2.add(ioi);
//						}
//						uci.setLastItems(set2);
//						unCheckedItemList.add(uci);
//						Set<Item> set3 = ioi.getNextItems();
//						if(set1!=null){
//							set3.add(uci);
//						}else{
//							set3 = new HashSet<Item>();
//							set3.add(uci);
//						}
//						ioi.setNextItems(set3);
//						uis.saveUncheckedItem(uci);
//						iois.updateInboundOrderItem(ioi);
//					}
//			}
//		}
//		//查验是否缺货，如果缺货记UncheckedItem表，但是不和已经有的记录合并。
//		for(InboundOrderItem ii:inboundOrderItemList){
//			String pCode = ii.getMaterial().getCode();
//			float qty = ii.getQty();
//			float arr = 0f;
//			if(checkingItemList.size()!=0){
//				for(CheckingItem check:checkingItemList){
//					if(check.getMaterial().getCode().equals(pCode)){
//						arr += check.getArrivedQty();
//					}
//				}
//			}
//			if(unCheckedItemList.size()!=0){
//				for(UncheckedItem unCheck:unCheckedItemList){
//					if(unCheck.getMaterial().getCode().equals(pCode)){
//						arr += unCheck.getArrivedQty();
//					}
//				}
//			}
//			//还要看本来的CheckingItem
//			if(cli.size()!=0){
//				for(CheckingItem cc:cli){
//					if(cc.getMaterial().getCode().equals(pCode)){
//						arr += cc.getArrivedQty();
//					}
//				}
//			}
//			if(qty!=arr){
//				uci = new UncheckedItem();
//				uci.setJobId(rgm.getJobId());
//				uci.setMaterial(ioi.getMaterial());
//				uci.setCreationTime(new Timestamp(System.currentTimeMillis()));
//				uci.setJobId(rgm.getJobId());
//				uci.setOrderId(orderId);
//				//只用设置缺货数就可以了
//				uci.setWantQty(qty-arr);
//				uci.setDestroyedQty(0f);
//				uci.setState(nor);
//				uci.setUnitPackage(ioi.getUnitPackage());
//				Set<Item> set5 =  uci.getLastItems();
//				if(set5!=null){
//					set5.add(ioi);
//				}else{
//					set5 = new HashSet<Item>();
//					set5.add(ioi);
//				}
//				uci.setLastItems(set5);
//				unCheckedItemList.add(uci);
//				Set<Item> set6 = ioi.getNextItems();
//				if(set6!=null){
//					set6.add(uci);
//				}else{
//					set6 = new HashSet<Item>();
//					set6.add(uci);
//				}
//				ioi.setNextItems(set6);
//				uis.saveUncheckedItem(uci);
//				iois.updateInboundOrderItem(ioi);
//			}
//		}
//		//查看数否需要将整个进库订单置为已验货状态状态。如果没有正常装态的该业务下的UncheckedItem，则可以改变
//		List<UncheckedItem> unList = uis.findByJobId(jobId);
//		if(unList.size()==0){
//			ws.alterToInboundChecked(Integer.parseInt(orderId));
//		}
//	}
	
	public boolean onSure(){
		List<CheckingItem> cli = cis.findNormalByJobId(jobId);
		if(cli.size()==0){
			ff.setError(this.getMessage("error4"));
			return false;
		}else{
			ws.alterToInboundChecked(Integer.parseInt(orderId));
		}
		return true;
	}
	
	public boolean onSee(){
		this.setRedirect("showCheckResult.htm?orderId=" + orderId);
		return true;
	}
}
