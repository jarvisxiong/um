package com.intelitune.nwms.exchange;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.intelitune.nwms.model.Bin;
import com.intelitune.nwms.model.Item;
import com.intelitune.nwms.model.ItemState;
import com.intelitune.nwms.model.LogStockMoveItem;
import com.intelitune.nwms.model.StorageItem;
import com.intelitune.nwms.service.ItemStateService;
import com.intelitune.nwms.service.ItemStateServiceImp;
import com.intelitune.nwms.service.LogStockMoveItemService;
import com.intelitune.nwms.service.LogStockMoveItemServiceImp;
import com.intelitune.nwms.service.StorageItemService;
import com.intelitune.nwms.service.StorageItemServiceImp;

public class MoveService {
	public ItemStateService iss=ItemStateServiceImp.getInstance();
	public StorageItemService sis=StorageItemServiceImp.getInstance();
	public LogStockMoveItemService lsmis=LogStockMoveItemServiceImp.getInstance();
	public void MoveItem(StorageItem si,float qty,Bin bin){
		ItemState is=iss.findItemStateByCode(ItemState.NORMAL);
		ItemState isd=iss.findItemStateByCode(ItemState.DELETE);
		
		LogStockMoveItem ls=new LogStockMoveItem();
		
		if(qty==si.getQty()){
			//新库存
			StorageItem si_new =new StorageItem();
			si_new.setBin(bin);
			si_new.setCreationTime(new Timestamp(System.currentTimeMillis()));
			si_new.setCrn(si.getCrn());
			si_new.setInvoice(si.getInvoice());
			si_new.setItemType(si.getItemType());
			si_new.setJobId(si.getJobId());
			Set<Item> nset=new HashSet<Item>();
			nset.add(si);
			si_new.setLastItems(nset);
			si_new.setLastModifiedTime(si.getLastModifiedTime());
			si_new.setMaterial(si.getMaterial());
			si_new.setOrderId(si.getOrderId());
			si_new.setQty(qty);
			si_new.setReferenceBin(si.getReferenceBin());
			si_new.setRemark(si.getRemark());
			si_new.setSn(si.getSn());
			si_new.setState(is);
			si_new.setUnitPackage(si.getUnitPackage());
			si_new.setWarehouse(si.getWarehouse());
			sis.addStorageItem(si_new);
			//删除原有storageItem
			Set<Item> mset=new HashSet<Item>();
			nset.add(si_new);
			si.setNextItems(mset);
			si.setState(isd);
			sis.modifyStorageItem(si);
			//添加日志
			ls.setBin(bin);
			ls.setCreationTime(new Timestamp(System.currentTimeMillis()));
			ls.setCrn(si.getCrn());
			ls.setInvoice(si.getInvoice());
			ls.setItemType(si.getItemType());
			ls.setJobId(si.getJobId());
			ls.setLastModifiedTime(si.getLastModifiedTime());
			ls.setMaterial(si.getMaterial());
			ls.setOrderId(si.getOrderId());
			ls.setQty(qty);
			ls.setReferenceBin(si.getReferenceBin());
			ls.setRemark(si.getRemark());
			ls.setSn(si.getSn());
			ls.setState(is);
			ls.setUnitPackage(si.getUnitPackage());
			ls.setWarehouse(si.getWarehouse());
			ls.setFormStorageItem(si);
			lsmis.addLog(ls);
		}
		if(qty<si.getQty()){
			//新库存
			StorageItem si_new =new StorageItem();
			si_new.setBin(bin);
			si_new.setCreationTime(new Timestamp(System.currentTimeMillis()));
			si_new.setCrn(si.getCrn());
			si_new.setInvoice(si.getInvoice());
			si_new.setItemType(si.getItemType());
			si_new.setJobId(si.getJobId());
			Set<Item> nset=new HashSet<Item>();
			nset.add(si);
			si_new.setLastItems(nset);
			si_new.setLastModifiedTime(si.getLastModifiedTime());
			si_new.setMaterial(si.getMaterial());
			si_new.setOrderId(si.getOrderId());
			si_new.setQty(qty);
			si_new.setReferenceBin(si.getReferenceBin());
			si_new.setRemark(si.getRemark());
			si_new.setSn(si.getSn());
			si_new.setState(is);
			si_new.setUnitPackage(si.getUnitPackage());
			si_new.setWarehouse(si.getWarehouse());
			sis.addStorageItem(si_new);
			//归还剩余库存
			StorageItem si_left=new StorageItem();
			si_left.setBin(si.getBin());
			si_left.setCreationTime(new Timestamp(System.currentTimeMillis()));
			si_left.setCrn(si.getCrn());
			si_left.setInvoice(si.getInvoice());
			si_left.setItemType(si.getItemType());
			si_left.setJobId(si.getJobId());
//			Set<Item> pset=new HashSet<Item>();
//			pset=si.getLastItems();
//			si_left.setLastItems(pset);
			si_left.setLastModifiedTime(si.getLastModifiedTime());
			si_left.setMaterial(si.getMaterial());
			si_left.setOrderId(si.getOrderId());
			float a=si.getQty();
			si_left.setQty(a-qty);
			si_left.setReferenceBin(si.getReferenceBin());
			si_left.setRemark(si.getRemark());
			si_left.setSn(si.getSn());
			si_left.setState(is);
			si_left.setUnitPackage(si.getUnitPackage());
			si_left.setWarehouse(si.getWarehouse());
			sis.addStorageItem(si_left);
			//删除原有storageItem
			Set<Item> mset=new HashSet<Item>();
			nset.add(si_new);
			si.setNextItems(mset);
			si.setState(isd);
			sis.modifyStorageItem(si);
			//添加日志
			ls.setBin(bin);
			ls.setCreationTime(new Timestamp(System.currentTimeMillis()));
			ls.setCrn(si.getCrn());
			ls.setInvoice(si.getInvoice());
			ls.setItemType(si.getItemType());
			ls.setJobId(si.getJobId());
			ls.setLastModifiedTime(si.getLastModifiedTime());
			ls.setMaterial(si.getMaterial());
			ls.setOrderId(si.getOrderId());
			ls.setQty(qty);
			ls.setReferenceBin(si.getReferenceBin());
			ls.setRemark(si.getRemark());
			ls.setSn(si.getSn());
			ls.setState(is);
			ls.setUnitPackage(si.getUnitPackage());
			ls.setWarehouse(si.getWarehouse());
			ls.setFormStorageItem(si);
			lsmis.addLog(ls);
		}
	}

}
