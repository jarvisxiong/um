package com.intelitune.nwms.service;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.intelitune.nwms.dao.RowHome;
import com.intelitune.nwms.model.Bin;
import com.intelitune.nwms.model.BinState;
import com.intelitune.nwms.model.Column;
import com.intelitune.nwms.model.ColumnState;
import com.intelitune.nwms.model.Row;
import com.intelitune.nwms.model.RowState;
import com.intelitune.nwms.model.Tier;
import com.intelitune.nwms.model.TierState;
import com.intelitune.nwms.model.Zone;

public class RowServiceImp implements RowService {
	
	private final static RowServiceImp instance =new RowServiceImp();
	
	private RowServiceImp(){
		
	}
	
	public static final RowServiceImp getInstance() {
		return instance;
	}

	public RowHome rh=RowHome.getInstance();
	public TierService ts=TierServiceImp.getInstance();
	public ColumnService cs=ColumnServiceImp.getInstance();
	public RowStateService rss=RowStateServiceImp.getInstance();
	public TierStateService tss=TierStateServiceImp.getInstance();
	public ColumnStateService css=ColumnStateServiceImp.getInstance();
	public BinStateService bss=BinStateServiceImp.getInstance();
	public BinService bs=BinServiceImp.getInstance();
	
	
	
	
	public List<Row> findRowListByFloor_id(String floor_id) {
		List<Row> list=rh.findRowByFloor_id(floor_id);
		return list;
	}

	public void addRow(Row r,int tier,int column,List<Zone> list) {
		int code1=0;
//		String code2=null;
		//创建row对象
		rh.persist(r);
		
		//创建tier对象
		for(int i=0;i<tier;i++){
			Tier t=new Tier();
			code1=i+1;
			t.setCode(code1+"");
			t.setRow(r);
			t.setState(tss.findTierStateByCode(TierState.NORMAL));
			ts.addTier(t);
		}
		//创建column对象
		for(int i=0;i<column;i++){
			Column c=new Column();
			code1=i+1;
			c.setCode(code1+"");
			c.setRow(r);
			c.setState(css.findColumnStateByCode(ColumnState.NORMAL));
			cs.addColumn(c);
		}
		//创建bin对象
		for(int j=0;j<tier;j++){
			for(int k=0;k<column;k++){
				Bin b=new Bin();
				 b.setWarehouse(r.getFloor().getBuilding().getWarehouse());
				 b.setBuilding(r.getFloor().getBuilding());
				 b.setFloor(r.getFloor());
				 b.setRow(r);
				 Tier t=ts.findTierByCode(r.getId(), j+1+"");
				 Column c=cs.findColumnByCode(r.getId(), k+1+"");
				 b.setTier(t);
				 b.setColumn(c);
				 b.setState(bss.findBinStateByCode(BinState.NORMAL));
				 b.setCode(r.getFloor().getCode()+"-"+r.code+"-"+t.code+"-"+c.code);
				 b.setWeight(Float.parseFloat("10000"));
				 b.setLength(Float.parseFloat("10000"));
				 b.setBreadth(Float.parseFloat("10000"));
				 b.setHeight(Float.parseFloat("10000"));
				 b.setVolume(Float.parseFloat("10000"));
				 b.setZones(list);
				 bs.addBin(b);
			}
		}
	}

	public void delRowById(String id) {
		Row r=rh.findById(id);
		//修改BIN状态为删除
		Set<Bin> bins=r.getBins();
		Iterator<Bin> it1 = bins.iterator();
		while(it1.hasNext()){
			Bin b=it1.next();
			b.setState(bss.findBinStateByCode(BinState.DELETE));
			bs.delBinById(b.getId());
		}
		//修改column状态为删除
		Set<Column> columns=r.getColumns();
		Iterator<Column> it2=columns.iterator();
		while(it2.hasNext()){
			Column c=it2.next();
			c.setState(css.findColumnStateByCode(ColumnState.DELETE));
			cs.delColumnById(c.getId());
		}
		//修改tier状态为删除
		Set<Tier> tiers=r.getTiers();
		Iterator<Tier> it3=tiers.iterator();
		while(it3.hasNext()){
			Tier t=it3.next();
			t.setState(tss.findTierStateByCode(TierState.DELETE));
			ts.delTierById(t.getId());
		}
		//修改row状态为删除
		r.setState(rss.findRowStateByCode(RowState.DELETE));
		rh.attachDirty(r);
	}

	public Row findRowById(String id) {
		return rh.findById(id);
	}

	public void modifyRow(Row r) {
		rh.attachDirty(r);
		
	}

	public String findNameById(String id) {
		return rh.findById(id).getAlias();
	}

}
