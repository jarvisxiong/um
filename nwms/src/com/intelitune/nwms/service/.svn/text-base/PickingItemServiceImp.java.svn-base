package com.intelitune.nwms.service;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.intelitune.ccos.increasedWebService.WMSServiceImp;
import com.intelitune.ccos.increasedWebService.WMSServiceInf;
import com.intelitune.nwms.dao.PickingItemHome;
import com.intelitune.nwms.model.Item;
import com.intelitune.nwms.model.ItemState;
import com.intelitune.nwms.model.OutboundAssigned;
import com.intelitune.nwms.model.PickingItem;
import com.intelitune.nwms.model.StorageItem;

public class PickingItemServiceImp implements PickingItemService {
	
	private final static PickingItemServiceImp instance =new PickingItemServiceImp();
	
	private PickingItemServiceImp(){
		
	}
	
	public static final PickingItemServiceImp getInstance() {
		return instance;
	}
	public WMSServiceInf service=WMSServiceImp.getInstance();
	public PickingItemHome pih=PickingItemHome.getInstance();
	public StorageItemService sis=StorageItemServiceImp.getInstance();
	public ItemStateService iss=ItemStateServiceImp.getInstance();
	public OutboundAssignedService oas=OutboundAssignedServiceImp.getInstance();
	public void addPickingItem(PickingItem pi) {
		pih.persist(pi);
	}
	public PickingItem findPickingItemById(String id) {
		return pih.findById(id);
	}
	
	public void delPickingItem(String id) {
		PickingItem pi=pih.findById(id);
		pi.setState(iss.findItemStateByCode(ItemState.DELETE));
		pih.attachDirty(pi);
	}
	
	public List<PickingItem> findPickingItemByOrderId(String id) {
		return pih.findPickingItemByOrderId(id);
	}
	
	public List<PickingItem> findPickingItemByOrderIdIngoreStatus(String id) {
		return pih.findPickingItemByOrderIdIgnoreStatus(id);
	}
	
	public List<PickingItem> findPickingItemByOrderIdhebing(String id){
		return pih.findPickingItemByOrderIdhe(id);
	}

	public void transferPickingItemByAssigned(String order_id,String job_id) throws Exception  {
		ItemState is=iss.findItemStateByCode(ItemState.NORMAL);
		ItemState isd=iss.findItemStateByCode(ItemState.DELETE);
		List<OutboundAssigned> list=oas.findAssignedByOrderId(order_id);
		for(int i=0;i<list.size();i++){
			OutboundAssigned oa=list.get(i);
			StorageItem si=oa.getSi();
			if(!ItemState.NORMAL.toString().equals(si.getState().getCode())){
				throw new Exception();
			}
			if(si.getQty()<oa.getAssignedQty()){
				throw new Exception();
			}
			//数量正好相同
			if(oa.getAssignedQty()==oa.getSi().getQty()){
				PickingItem pi=new PickingItem();
				pi.setBin(si.getBin());
				pi.setCreationTime(new Timestamp(System.currentTimeMillis()));
				pi.setJobId(job_id);
				Set<Item> set=new HashSet<Item>();
				set.add(si);
				pi.setLastItems(set);
				pi.setLastModifiedTime(new Timestamp(System.currentTimeMillis()));
				pi.setMaterial(si.getMaterial());
				pi.setOrderId(order_id);
				pi.setQty(oa.getAssignedQty());
				pi.setRemark(si.getRemark());
				pi.setSn(si.getSn());
				pi.setUnitPackage(si.getUnitPackage());
				pi.setState(is);
				pi.setWarehouse(si.getWarehouse());
				pi.setInvoice(si.getInvoice());
				pi.setOutboundInvoice(oa.getOoi().getInvoice());
				pi.setItemType(si.getItemType());
				
				//michael_wang 20091214
				pi.setF_currency(si.getF_currency());
				pi.setF_gross_price(si.getF_gross_price());
				pi.setF_gross_weight(si.getF_gross_weight());
				pi.setF_unit_price(si.getF_unit_price());
				pi.setF_unit_weight(si.getF_unit_weight());
				pi.setHawb(si.getHawb());
				pi.setMawb(si.getMawb());
				
				this.addPickingItem(pi);
				//添加storageItem的状态为删除 并且添加lastItem
				Set<Item> nset=new HashSet<Item>();
				nset.add(pi);
				si.setNextItems(nset);
				si.setState(isd);
				sis.modifyStorageItem(si);	
			}else{
				PickingItem pi=new PickingItem();
				pi.setBin(si.getBin());
				pi.setCreationTime(new Timestamp(System.currentTimeMillis()));
				pi.setJobId(job_id);
				Set<Item> set=new HashSet<Item>();
				set.add(si);
				pi.setLastItems(set);
				pi.setLastModifiedTime(new Timestamp(System.currentTimeMillis()));
				pi.setMaterial(si.getMaterial());
				pi.setOrderId(order_id);
				pi.setQty(oa.getAssignedQty());
				pi.setRemark(si.getRemark());
				pi.setSn(si.getSn());
				pi.setUnitPackage(si.getUnitPackage());
				pi.setState(is);
				pi.setWarehouse(si.getWarehouse());
				pi.setInvoice(si.getInvoice());
				pi.setOutboundInvoice(oa.getOoi().getInvoice());
				pi.setItemType(si.getItemType());
				
				//michael_wang 20091214
				pi.setF_currency(si.getF_currency());
				if(si.getF_unit_price()!=null){
					pi.setF_gross_price(oa.getAssignedQty()*si.getF_unit_price());
				}else{
					pi.setF_gross_price(null);
				}
				if(si.getF_unit_weight()!=null){
					pi.setF_gross_weight(oa.getAssignedQty()*si.getF_unit_weight());
				}else{
					pi.setF_gross_weight(null);
				}
				pi.setF_unit_price(si.getF_unit_price());
				pi.setF_unit_weight(si.getF_unit_weight());
				pi.setHawb(si.getHawb());
				pi.setMawb(si.getMawb());
				
				this.addPickingItem(pi);
				//添加剩余数量
				StorageItem si_left=new StorageItem();
				si_left.setBin(si.getBin());
				si_left.setCreationTime(si.getCreationTime());
				si_left.setJobId(si.getJobId());
				Set<Item> nset=new HashSet<Item>();
				nset.add(si);
				si_left.setLastItems(nset);
				si_left.setLastModifiedTime(new Timestamp(System.currentTimeMillis()));
				si_left.setMaterial(si.getMaterial());
				si_left.setOrderId(si.getOrderId());
				float a=si.getQty();
				float b=oa.getAssignedQty();
				si_left.setQty(a-b);
				si_left.setRemark(si.getRemark());
				si_left.setSn(si.getSn());
				si_left.setUnitPackage(si.getUnitPackage());
				si_left.setWarehouse(si.getWarehouse());
				si_left.setInvoice(si.getInvoice());
				si_left.setItemType(si.getItemType());
				si_left.setState(is);
		
				//michael_wang 20091214
				si_left.setF_currency(si.getF_currency());
				if(si.getF_unit_price()!=null){
					pi.setF_gross_price(oa.getAssignedQty()*si.getF_unit_price());
				}else{
					pi.setF_gross_price(null);
				}
				if(si.getF_unit_weight()!=null){
					pi.setF_gross_weight(oa.getAssignedQty()*si.getF_unit_weight());
				}else{
					pi.setF_gross_weight(null);
				}
				si_left.setF_unit_price(si.getF_unit_price());
				si_left.setF_unit_weight(si.getF_unit_weight());
				si_left.setHawb(si.getHawb());
				si_left.setMawb(si.getMawb());
				

				sis.addStorageItem(si_left);
				//添加storageItem的状态为删除 并且添加lastItem
				Set<Item> mset=new HashSet<Item>();
				mset.add(pi);
				mset.add(si_left);
				si.setNextItems(mset);
				si.setState(isd);
				sis.modifyStorageItem(si);
			}
		}
		service.alterToOutboundInPicking(Integer.parseInt(order_id));
	}
	
}
