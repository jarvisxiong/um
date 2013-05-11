package com.intelitune.nwms.common;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.intelitune.ccos.increasedWebService.WMSServiceImp;
import com.intelitune.ccos.increasedWebService.WMSServiceInf;
import com.intelitune.ccos.model.WmsOrder;
import com.intelitune.nwms.model.Item;
import com.intelitune.nwms.model.ItemState;
import com.intelitune.nwms.model.OutboundOrderItem;
import com.intelitune.nwms.model.PickingItem;
import com.intelitune.nwms.model.StorageItem;
import com.intelitune.nwms.service.ItemStateService;
import com.intelitune.nwms.service.ItemStateServiceImp;
import com.intelitune.nwms.service.PickingItemService;
import com.intelitune.nwms.service.PickingItemServiceImp;
import com.intelitune.nwms.service.StorageItemService;
import com.intelitune.nwms.service.StorageItemServiceImp;

public class CreatePickingItem {
	public  StorageItemService sis=StorageItemServiceImp.getInstance();
	public WMSServiceInf wsi= WMSServiceImp.getInstance();
	public ItemStateService iss=ItemStateServiceImp.getInstance();
	public PickingItemService pis=PickingItemServiceImp.getInstance();
	public WMSServiceInf service=WMSServiceImp.getInstance();
	
	//先考虑整取  没有整取则按顺序取
	public void transferPickingItem(List<OutboundOrderItem> olist,int orderid){
		ItemState is=iss.findItemStateByCode(ItemState.NORMAL);
		ItemState isd=iss.findItemStateByCode(ItemState.DELETE);
		//client_id为MCS中客户的ID 为int类型自动增长列
		WmsOrder wo=new WmsOrder();
		try{
			 wo=wsi.findWmsOrderById(orderid);
		}catch(Exception e){
			e.printStackTrace();
		}
//		List<StorageItem> slist=sis.findStorageItemByWmsOrder(wo);
//		List<StorageItem> slist=new ArrayList<StorageItem>();
//		for(int i=0;i<slist1.size();i++){
//			if(i==0){
//				slist.add(slist1.get(0));
//				continue;
//			}
//			StorageItem si1=slist1.get(i);
//			int j=0;
//			for(;j<slist.size();j++){
//				StorageItem si=slist.get(j);
//				if("N/A".equals(si1.getSn().trim())&&"N/A".equals(si.getSn().trim())&&si1.getMaterial().getCode().trim().equals(si.getMaterial().getCode().trim())&&si1.getBin().getCode().trim().equals(si.getBin().getCode().trim())){
//					si.setQty(si.getQty()+si1.getQty());
//					break;
//				}				
//			}
//			if(j==slist.size()){
//				slist.add(si1);
//			}
//		}
		List<PickingItem> result=new ArrayList<PickingItem>();
		PickingItem pi;		
		List<StorageItem> slist=new ArrayList<StorageItem>();
		//循环遍历OutboundOrderItem
	//ooi为要取的对象  si为储位中的对象
		for(int i=0;i<olist.size();i++){
			OutboundOrderItem ooi=olist.get(i);
//			if(ooi.getInvoice()==null||ooi.getInvoice().getCode()==null||"".equals(ooi.getInvoice().getCode())){
				slist=sis.findStorageItemByWmsOrder(wo,ooi.getMaterial().getCode());
//			}else{
//				slist=sis.findStorageItemByWmsOrderAndInvoice(wo, ooi.getMaterial().getCode(), ooi.getInvoice().getCode());
//			}
			//如果该outboundOrderItem包含
			//如果有SN的情况
			if(ooi.getSn()!=null&&!"N/A".equals(ooi.getSn())){
				for(int m=0;m<slist.size();m++){
					StorageItem si=slist.get(m);
					if(si.getSn().trim().equals(ooi.getSn().trim())){
						//加入pickingItem中
						pi=new PickingItem();
						pi.setBin(si.getBin());
						pi.setCreationTime(new Timestamp(System.currentTimeMillis()));
						pi.setJobId(wo.getJobId());
						Set<Item> set=new HashSet<Item>();
						set.add(si); 
						pi.setLastItems(set);
						pi.setLastModifiedTime(new Timestamp(System.currentTimeMillis()));
						pi.setMaterial(si.getMaterial());
						pi.setOrderId(orderid+"");
						pi.setQty(ooi.getQty());
						pi.setRemark(si.getRemark());
						pi.setSn(si.getSn());
						pi.setUnitPackage(si.getUnitPackage());
						pi.setState(is);
						pi.setWarehouse(si.getWarehouse());
						pi.setInvoice(si.getInvoice());
						pi.setOutboundInvoice(ooi.getInvoice());
						pi.setItemType(ooi.getItemType());
						pi.setOrderId(ooi.getOrderId());
						pis.addPickingItem(pi);
						result.add(pi);
						//添加storageItem的状态为删除 并且添加lastItem
						Set<Item> nset=new HashSet<Item>(); 
						nset.add(pi);
						si.setNextItems(nset);
						si.setState(isd);
						sis.modifyStorageItem(si);
					}
				}
				continue;
			}
			//没有SN的情况
			else{
				float qty=ooi.getQty();//qty为需要取出的货物数量
				for(int j=0;j<slist.size();j++){
					StorageItem si=slist.get(j);
					//判断储位中的Item是否与订单中的一致
					if(ooi.getMaterial().getCode().trim().equals(si.getMaterial().getCode().trim())){
						//判断数量是否够
						if(ooi.getQty()<=si.getQty()){
							//数量够 如下动作 
							//1扣除StorageItem的数量 如果数量为相等则删除StorageItem对象 
							//2存入PickingItem对象
							if(ooi.getQty()==si.getQty()){
								pi=new PickingItem();
								pi.setBin(si.getBin());
								pi.setCreationTime(new Timestamp(System.currentTimeMillis()));
								pi.setJobId(wo.getJobId());
								Set<Item> set=new HashSet<Item>();
								set.add(si);
								pi.setLastItems(set);
								pi.setLastModifiedTime(new Timestamp(System.currentTimeMillis()));
								pi.setMaterial(si.getMaterial());
								pi.setOrderId(orderid+"");
								pi.setQty(ooi.getQty());
								pi.setRemark(si.getRemark());
								pi.setSn(si.getSn());
								pi.setUnitPackage(si.getUnitPackage());
								pi.setState(is);
								pi.setWarehouse(si.getWarehouse());
								pi.setInvoice(si.getInvoice());
								pi.setOutboundInvoice(ooi.getInvoice());
								pi.setItemType(ooi.getItemType());
								pi.setOrderId(ooi.getOrderId());
								pis.addPickingItem(pi);
								//添加storageItem的状态为删除 并且添加lastItem
								Set<Item> nset=new HashSet<Item>();
								nset.add(pi);
								si.setNextItems(nset);
								si.setState(isd);
								sis.modifyStorageItem(si);
								//将pickingItem加入List返回
								result.add(pi);
								break;
								
							}
							//1不为0则删除对象后再加入剩余数量的对象
							//2存入PickingItem对象
							if(ooi.getQty()<si.getQty()){
							
								//添加pickingItem
								pi=new PickingItem();
								pi.setBin(si.getBin());
								pi.setCreationTime(new Timestamp(System.currentTimeMillis()));
								pi.setJobId(wo.getJobId());
								Set<Item> set=new HashSet<Item>();
								set.add(si);
								pi.setLastItems(set);
								pi.setLastModifiedTime(new Timestamp(System.currentTimeMillis()));
								pi.setMaterial(si.getMaterial());
								pi.setOrderId(orderid+"");
								pi.setQty(ooi.getQty());
								pi.setRemark(si.getRemark());
								pi.setSn(si.getSn());
								pi.setUnitPackage(si.getUnitPackage());
								pi.setState(is);
								pi.setWarehouse(si.getWarehouse());
								pi.setInvoice(si.getInvoice());
								pi.setOutboundInvoice(ooi.getInvoice());
								pi.setItemType(ooi.getItemType());
								pi.setOrderId(ooi.getOrderId());
								pis.addPickingItem(pi);
								result.add(pi);
								//添加剩余StorageItem
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
								si_left.setQty(si.getQty()-ooi.getQty());
								si_left.setRemark(si.getRemark());
								si_left.setSn(si.getSn());
								si_left.setUnitPackage(si.getUnitPackage());
								si_left.setWarehouse(si.getWarehouse());
								si_left.setInvoice(si.getInvoice());
								si_left.setItemType(si.getItemType());
								System.out.print(si.getWarehouse().getId());
								si_left.setState(is);
								sis.addStorageItem(si_left);
								//添加storageItem的状态为删除 并且添加lastItem
								Set<Item> mset=new HashSet<Item>();
								mset.add(pi);
								mset.add(si_left);
								si.setNextItems(mset);
								si.setState(isd);
								sis.modifyStorageItem(si);
								break;
								
							}
						}
						//如果没有满足整出的储位
						if(j==slist.size()-1){
							//进行散取
							StorageItem sitem = null;
							for(int k=0;k<slist.size();k++){
								sitem = slist.get(k);
								if(ooi.getMaterial().getCode().trim().equals(sitem.getMaterial().getCode().trim())){
									//如果需要的数量比储位中的多
									if(qty>=sitem.getQty()){
										qty-=sitem.getQty();
										PickingItem pitem=new PickingItem();
										pitem=new PickingItem();
										pitem.setBin(sitem.getBin());
										pitem.setCreationTime(new Timestamp(System.currentTimeMillis()));
										pitem.setJobId(wo.getJobId());
										Set<Item> set=new HashSet<Item>();
										set.add(sitem);
										pitem.setLastItems(set);
										pitem.setLastModifiedTime(new Timestamp(System.currentTimeMillis()));
										pitem.setMaterial(sitem.getMaterial());
										pitem.setOrderId(orderid+"");
										pitem.setQty(sitem.getQty());
										pitem.setRemark(sitem.getRemark());
										pitem.setSn(sitem.getSn());
										pitem.setUnitPackage(sitem.getUnitPackage());
										pitem.setState(is);
										pitem.setWarehouse(sitem.getWarehouse());
										pitem.setInvoice(sitem.getInvoice());
										pitem.setOutboundInvoice(ooi.getInvoice());
										pitem.setItemType(ooi.getItemType());
										pis.addPickingItem(pitem);
										result.add(pitem);
										//添加storageItem的状态为删除 并且添加lastItem
										Set<Item> mset=new HashSet<Item>();
										mset.add(pitem);
										sitem.setNextItems(mset);
										sitem.setState(isd);
										sis.modifyStorageItem(sitem);
										//剩余等0则推出循环 不去下个储位寻找
										if(qty==0){
											break;
										}
									}else{ //如果需要的数量没有储位中的多
										//存入PickingItem 
										PickingItem pitem=new PickingItem();
										pitem=new PickingItem();
										pitem.setBin(sitem.getBin());
										pitem.setCreationTime(new Timestamp(System.currentTimeMillis()));
										pitem.setJobId(wo.getJobId());
										Set<Item> set=new HashSet<Item>();
										set.add(sitem);
										pitem.setLastItems(set);
										pitem.setLastModifiedTime(new Timestamp(System.currentTimeMillis()));
										pitem.setMaterial(sitem.getMaterial());
										pitem.setOrderId(orderid+"");
										pitem.setQty(qty);
										pitem.setRemark(sitem.getRemark());
										pitem.setSn(sitem.getSn());
										pitem.setUnitPackage(sitem.getUnitPackage());
										pitem.setState(is);
										pitem.setWarehouse(sitem.getWarehouse());
										pitem.setInvoice(sitem.getInvoice());
										pitem.setOutboundInvoice(ooi.getInvoice());
										pitem.setItemType(ooi.getItemType());
										pis.addPickingItem(pitem);
										result.add(pitem);
										//再追加存入剩余的StorageItem
										StorageItem sitem_left=new StorageItem();
										sitem_left.setBin(sitem.getBin());
										sitem_left.setCreationTime(sitem.getCreationTime());
										sitem_left.setJobId(sitem.getJobId());
										Set<Item> nset=new HashSet<Item>();
										nset.add(sitem);
										sitem_left.setLastItems(nset);
										sitem_left.setLastModifiedTime(new Timestamp(System.currentTimeMillis()));
										sitem_left.setMaterial(sitem.getMaterial());
										sitem_left.setOrderId(sitem.getOrderId());
										sitem_left.setQty(sitem.getQty()-qty);
										sitem_left.setRemark(sitem.getRemark());
										sitem_left.setSn(sitem.getSn());
										sitem_left.setUnitPackage(sitem.getUnitPackage());
										sitem_left.setWarehouse(sitem.getWarehouse());
										sitem_left.setInvoice(sitem.getInvoice());
										sitem_left.setItemType(sitem.getItemType());
										sitem_left.setState(is);
										sis.addStorageItem(sitem_left);
										//添加storageItem的状态为删除 并且添加lastItem
										Set<Item> mset=new HashSet<Item>();
										mset.add(pitem);
										mset.add(sitem_left);
										sitem.setNextItems(mset);
										sitem.setState(isd);
										sis.modifyStorageItem(sitem);
										break;
									}
								}
							}
						}
					}
				}
			}
		//修改订单状态
	
		}
		service.alterToOutboundInPicking(orderid);
	}
}
