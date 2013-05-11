package com.hhz.ump.web.cost;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.cost.CostMateColManager;
import com.hhz.ump.dao.cost.CostMateManager;
import com.hhz.ump.dao.cost.CostMatePriceManager;
import com.hhz.ump.entity.cost.CostMate;
import com.hhz.ump.entity.cost.CostMateCol;
import com.hhz.ump.entity.cost.CostMatePrice;

@Namespace("/cost")
public class CostMateColAction extends CrudActionSupport<CostMateCol> {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private CostMateManager costMateManager;
	@Autowired
	private CostMateColManager costMateColManager;
	@Autowired
	private CostMatePriceManager costMatePriceManager;

	private List<CostMateCol> costMateColList;
	private CostMate costMate;
	private CostMateCol entity;
	
	private String costMateId;
	private String costMateName;
	private String inputFlag;
	private String id;
	
	//save
	private String[] costMateColId;
	private String[] colField;
	private String[] colName;
	private String[] colDataTypeCd;
	private String[] levelCd;
	private String[] sequenceNo;
	
	@Override
	public String list() throws Exception {
		if(StringUtils.isNotBlank(costMateId)){
			Map<String, Object> values = new HashMap<String, Object>();
			StringBuffer hql = new StringBuffer();
			hql.append("from CostMateCol t where t.costMate.costMateId = :costMateId");
			values.put("costMateId", costMateId);
			hql.append(" order by t.colField asc");
			costMateColList = costMateColManager.find(hql.toString(), values);
			costMate = costMateManager.getEntity(costMateId);
			costMateName = costMate.getMateName();
		}
		return "input";
	}
	
	@Override
	public String save() throws Exception {
		List<String> colNameList = new ArrayList<String>();
		costMate = costMateManager.getEntity(costMateId);
		if(colField.length !=0 && costMate != null){
			for (int i = 0; i < colName.length; i++) {
				if(StringUtils.isNotBlank(colName[i])){
					if (!colNameList.contains(colName[i])) {
						colNameList.add(colName[i]);
					}else{
						Struts2Utils.renderText("realy");
						return null;
					}
				}
			}
			for (int i = 0; i < colField.length; i++) {
				if(StringUtils.isNotBlank(costMateColId[i])){
					entity = costMateColManager.getEntity(costMateColId[i]);
				}else{
					entity = new CostMateCol();
				}
				entity.setCostMate(costMate);
				entity.setColField(returnStr(colField[i]));
				entity.setColName(returnStr(colName[i]));
				if(StringUtils.isNotBlank(colName[i].trim())){
					entity.setColDataTypeCd(returnStr(colDataTypeCd[i]));
					entity.setLevelCd(BigDecimal.valueOf(Long.valueOf(returnStr(levelCd[i]))));
					if(StringUtils.isNotBlank(sequenceNo[i])){
						entity.setSequenceNo(Long.valueOf(sequenceNo[i]));
					}else{
						entity.setSequenceNo(null);
					}
				}
				try {
					costMateColManager.saveCostMateCol(entity);
					Struts2Utils.renderText("success");
				} catch (Exception e) {
					e.printStackTrace();
					Struts2Utils.renderText("fail");
				}
			}
		}
		return null;
	}
	
	public String returnStr(String str){
		String s = "";
		if(StringUtils.isNotBlank(str)){
			s = str;
		}
		return s;
	}
	
	@Override
	public String deleteBatch() throws Exception {
		return null;
	}

	@Override
	public String input() throws Exception {
		if(StringUtils.isNotBlank(costMateId)){
			costMate = costMateManager.getEntity(costMateId);
			costMateName = costMate.getMateName();
		}
		return INPUT;
	}
	
	public List<CostMateCol> getCostMateColByMateId(){
		List<CostMateCol> list = new ArrayList<CostMateCol>();
		if(StringUtils.isNotBlank(costMateId)){
			Map<String, Object> values = new HashMap<String, Object>();
			StringBuffer hql = new StringBuffer();
			hql.append("from CostMateCol t where t.costMate.costMateId = :costMateId");
			values.put("costMateId", costMateId);
			list = costMateColManager.find(hql.toString(), values);
		}
		return list;
	}
	
	public Map<String, String> getMapColDataTypeCd() {
		Map<String, String> mapColDataTypeCd = new LinkedHashMap<String, String>();
		mapColDataTypeCd.put("1", "");
		mapColDataTypeCd.put("2", "数字");
		mapColDataTypeCd.put("3", "小数点");
		return mapColDataTypeCd;
	}

	public Map<String, String> getMapLevelCd() {
		Map<String, String> mapLevelCd = new LinkedHashMap<String, String>();
		mapLevelCd.put("1", "");
		mapLevelCd.put("2", "高级");
		return mapLevelCd;
	}
	public Map<String, String> getMapExtFlg() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("0", "");
		map.put("1", "是");
		return map;
	}
	public Map<String, String> getMapExtPrice() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("0", "");
		map.put("1", "是");
		return map;
	}
	
	@Override
	public String delete() throws Exception {
		if (StringUtils.isNotBlank(getId()) && StringUtils.isNotBlank(costMateId)) {
			entity = costMateColManager.getEntity(getId());
			List<CostMatePrice> priceList = costMatePriceManager.quickSearch(null, null, costMateId);
			if(entity != null){
				String f = entity.getColField();
				for (CostMatePrice tPrice : priceList) {
					if ("f01".equals(f)) {
						if (StringUtils.isNotBlank(tPrice.getF01())) {
							tPrice.setF01("");
						}
					}else if("f02".equals(f)){
						if (StringUtils.isNotBlank(tPrice.getF02())) {
							tPrice.setF02("");
						}
					}else if("f03".equals(f)){
						if (StringUtils.isNotBlank(tPrice.getF03())) {
							tPrice.setF03("");
						}
					}else if("f04".equals(f)){
						if (StringUtils.isNotBlank(tPrice.getF04())) {
							tPrice.setF04("");
						}
					}else if("f05".equals(f)){
						if (StringUtils.isNotBlank(tPrice.getF05())) {
							tPrice.setF05("");
						}
					}else if("f06".equals(f)){
						if (StringUtils.isNotBlank(tPrice.getF06())) {
							tPrice.setF06("");
						}
					}else if("f07".equals(f)){
						if (StringUtils.isNotBlank(tPrice.getF07())) {
							tPrice.setF07("");
						}
					}else if("f08".equals(f)){
						if (StringUtils.isNotBlank(tPrice.getF08())) {
							tPrice.setF08("");
						}
					}else if("f09".equals(f)){
						if (StringUtils.isNotBlank(tPrice.getF09())) {
							tPrice.setF09("");
						}
					}else if("f10".equals(f)){
						if (StringUtils.isNotBlank(tPrice.getF10())) {
							tPrice.setF10("");
						}
					}else if("f11".equals(f)){
						if (StringUtils.isNotBlank(tPrice.getF11())) {
							tPrice.setF11("");
						}
					}else if("f12".equals(f)){
						if (StringUtils.isNotBlank(tPrice.getF12())) {
							tPrice.setF12("");
						}
					}else if("f13".equals(f)){
						if (StringUtils.isNotBlank(tPrice.getF13())) {
							tPrice.setF13("");
						}
					}else if("f14".equals(f)){
						if (StringUtils.isNotBlank(tPrice.getF14())) {
							tPrice.setF14("");
						}
					}else if("f15".equals(f)){
						if (StringUtils.isNotBlank(tPrice.getF15())) {
							tPrice.setF15("");
						}
					}
					costMatePriceManager.saveCostMatePrice(tPrice);
				}
				entity.setColName("");
				entity.setColDataTypeCd("");
				entity.setLevelCd(null);
				entity.setSequenceNo(null);
				costMateColManager.saveCostMateCol(entity);
				Struts2Utils.renderHtml("success");
			}
		}
		return null;
	}
	
	public String checkColValue() throws Exception {
		if (StringUtils.isNotBlank(getId()) && StringUtils.isNotBlank(costMateId)) {
			entity = costMateColManager.getEntity(getId());
			List<CostMatePrice> priceList = costMatePriceManager.quickSearch(null, null, costMateId);
			if(entity != null){
				String f = entity.getColField();
				for (CostMatePrice tPrice : priceList) {
					if ("f01".equals(f)) {
						if (StringUtils.isNotBlank(tPrice.getF01())) {
							Struts2Utils.renderText("realy");
							break;
						}
					}else if("f02".equals(f)){
						if (StringUtils.isNotBlank(tPrice.getF02())) {
							Struts2Utils.renderText("realy");
							break;
						}
					}else if("f03".equals(f)){
						if (StringUtils.isNotBlank(tPrice.getF03())) {
							Struts2Utils.renderText("realy");
							break;
						}
					}else if("f04".equals(f)){
						if (StringUtils.isNotBlank(tPrice.getF04())) {
							Struts2Utils.renderText("realy");
							break;
						}
					}else if("f05".equals(f)){
						if (StringUtils.isNotBlank(tPrice.getF05())) {
							Struts2Utils.renderText("realy");
							break;
						}
					}else if("f06".equals(f)){
						if (StringUtils.isNotBlank(tPrice.getF06())) {
							Struts2Utils.renderText("realy");
							break;
						}
					}else if("f07".equals(f)){
						if (StringUtils.isNotBlank(tPrice.getF07())) {
							Struts2Utils.renderText("realy");
							break;
						}
					}else if("f08".equals(f)){
						if (StringUtils.isNotBlank(tPrice.getF08())) {
							Struts2Utils.renderText("realy");
							break;
						}
					}else if("f09".equals(f)){
						if (StringUtils.isNotBlank(tPrice.getF09())) {
							Struts2Utils.renderText("realy");
							break;
						}
					}else if("f10".equals(f)){
						if (StringUtils.isNotBlank(tPrice.getF10())) {
							Struts2Utils.renderText("realy");
							break;
						}
					}else if("f11".equals(f)){
						if (StringUtils.isNotBlank(tPrice.getF11())) {
							Struts2Utils.renderText("realy");
							break;
						}
					}else if("f12".equals(f)){
						if (StringUtils.isNotBlank(tPrice.getF12())) {
							Struts2Utils.renderText("realy");
							break;
						}
					}else if("f13".equals(f)){
						if (StringUtils.isNotBlank(tPrice.getF13())) {
							Struts2Utils.renderText("realy");
							break;
						}
					}else if("f14".equals(f)){
						if (StringUtils.isNotBlank(tPrice.getF14())) {
							Struts2Utils.renderText("realy");
							break;
						}
					}else if("f15".equals(f)){
						if (StringUtils.isNotBlank(tPrice.getF15())) {
							Struts2Utils.renderText("realy");
							break;
						}
					}
				}
			}
		}
		return null;
	}
	
	@Override
	public CostMateCol getModel() {
		return entity;
	}
	
	@Override
	protected void prepareModel() throws Exception {

	}
	
	@Override
	public String getId() {
		return id;
	}
	@Override
	public void setId(String id) {
		this.id = id;
	}
	
	public String getCostMateId() {
		return costMateId;
	}

	public void setCostMateId(String costMateId) {
		this.costMateId = costMateId;
	}

	public List<CostMateCol> getCostMateColList() {
		return costMateColList;
	}

	public void setCostMateColList(List<CostMateCol> costMateColList) {
		this.costMateColList = costMateColList;
	}

	public CostMateCol getEntity() {
		return entity;
	}
	public void setEntity(CostMateCol entity) {
		this.entity = entity;
	}
	
	public CostMate getCostMate() {
		return costMate;
	}

	public void setCostMate(CostMate costMate) {
		this.costMate = costMate;
	}

	public String getCostMateName() {
		return costMateName;
	}

	public void setCostMateName(String costMateName) {
		this.costMateName = costMateName;
	}

	public String[] getColField() {
		return colField;
	}

	public void setColField(String[] colField) {
		this.colField = colField;
	}

	public String[] getColName() {
		return colName;
	}

	public void setColName(String[] colName) {
		this.colName = colName;
	}

	public String[] getColDataTypeCd() {
		return colDataTypeCd;
	}

	public void setColDataTypeCd(String[] colDataTypeCd) {
		this.colDataTypeCd = colDataTypeCd;
	}

	public String[] getLevelCd() {
		return levelCd;
	}

	public void setLevelCd(String[] levelCd) {
		this.levelCd = levelCd;
	}

	public String[] getSequenceNo() {
		return sequenceNo;
	}

	public void setSequenceNo(String[] sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	public String[] getCostMateColId() {
		return costMateColId;
	}

	public void setCostMateColId(String[] costMateColId) {
		this.costMateColId = costMateColId;
	}

	public String getInputFlag() {
		return inputFlag;
	}

	public void setInputFlag(String inputFlag) {
		this.inputFlag = inputFlag;
	}
}
