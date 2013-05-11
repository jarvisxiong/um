package com.hhz.ump.web.bis;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.hhz.core.utils.DateOperator;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.bis.BisContManager;
import com.hhz.ump.dao.bis.BisFlatManager;
import com.hhz.ump.dao.bis.BisMultiManager;
import com.hhz.ump.dao.bis.BisProjectManager;
import com.hhz.ump.entity.bis.BisCont;
import com.hhz.ump.entity.bis.BisFlat;
import com.hhz.ump.entity.bis.BisMulti;
import com.hhz.ump.util.BisFlatReportUtil;
import com.hhz.ump.web.vo.VoCombo;

public class BisFlatAction extends CrudActionSupport<BisFlat>{
	
	private BisFlat entity;
	@Autowired
	private BisFlatManager bisFlatManager;
	@Autowired
	private BisContManager bisContManager;
	@Autowired
	private BisMultiManager bisMultiManager;
	private String chargeTypeCd;
	private String reportDate;
	private String bisProjectId;
	private List<BisFlatReportUtil> flatUtils =new ArrayList<BisFlatReportUtil>();
	List<VoCombo> vos = new ArrayList<VoCombo>();
	@Autowired
	private BisProjectManager bisProjectManager;

	@Override
	public BisFlat getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	@Override
	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = bisFlatManager.getEntity(getId());
			entity.setRecordVersion(entity.getRecordVersion() + 1);
		}else{
			entity = new BisFlat();
		}
	}
	
	public String platReport(){
		
		return "report";
	}
	//测试
	public String select(){
		return "select";
	}
	public String selectMultiByPrompt(){
		//先搜索出有合同的多经
		List<BisCont> contList =bisContManager.getContListByMulti(bisProjectId);
		//索索出无合同的多经
		List<BisMulti> multiList =bisMultiManager.getBisMultiList(bisProjectId);
		VoCombo tmpVO = null;
		StringBuffer multiName = new StringBuffer("");
		for(BisCont cont:contList){
			tmpVO = new VoCombo();
			tmpVO.setId(cont.getBisContId());
			tmpVO.setText(cont.getContNo());
			for(int j=0;j<cont.getBisMultiContRels().size();j++){
				BisMulti bisMulti =cont.getBisMultiContRels().get(j).getBisMulti();
				multiName.append(bisMulti.getMultiName()).append(",");
			}
			if(multiName.length()>1){
				multiName.delete(multiName.length()-1, multiName.length());
			}
			tmpVO.setTextAttach(multiName.toString());
			tmpVO.setKey("cont");
			vos.add(tmpVO);
			multiName.delete(0, multiName.length());
		}
		for (BisMulti o : multiList) {
			tmpVO = new VoCombo();
			tmpVO.setId(o.getBisMultiId());
			tmpVO.setText(o.getMultiName());
			tmpVO.setKey("mul");
			vos.add(tmpVO);
		}
		return "multiPrompt";
	}
	public String flatList(){
		//搜索条件
		if(StringUtils.isBlank(chargeTypeCd) ){
			setChargeTypeCd("32,35");
		}
		String yearQuery;
		String monthQuery;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if(StringUtils.isBlank(reportDate)){
			yearQuery = format.format(DateOperator.getDateNow()).substring(0,4);
			monthQuery = format.format(DateOperator.getDateNow()).substring(5,7);
		}else{
			yearQuery = reportDate.substring(0,4);
			monthQuery = reportDate.substring(5,7);
		}
		if(StringUtils.isBlank(yearQuery)){
			yearQuery = format.format(DateOperator.getDateNow()).substring(0,4);
		}
		if(StringUtils.isBlank(monthQuery)){
			monthQuery = format.format(DateOperator.getDateNow()).substring(5,7);
		}
		if(StringUtils.isBlank(bisProjectId)){
			bisProjectId ="40282b8927a42dff0127a4316b830001";
		}
		Integer month = Integer.parseInt(monthQuery);
		flatUtils =bisFlatManager.queryFlatReport(bisProjectId, yearQuery, month, yearQuery, month,chargeTypeCd);
		
		return "flatList";
	}

	public String getChargeTypeCd() {
		return chargeTypeCd;
	}

	public void setChargeTypeCd(String chargeTypeCd) {
		this.chargeTypeCd = chargeTypeCd;
	}
	/**
	 * 初始化项目列表
	 * @return
	 */
	public Map<String, String> getMapBisProject() {
		Map<String, String> mapBisProject = bisProjectManager.getMapBisProject();
		return mapBisProject;
	}

	public List<BisFlatReportUtil> getFlatUtils() {
		return flatUtils;
	}

	public void setFlatUtils(List<BisFlatReportUtil> flatUtils) {
		this.flatUtils = flatUtils;
	}

	public String getBisProjectId() {
		return bisProjectId;
	}

	public void setBisProjectId(String bisProjectId) {
		this.bisProjectId = bisProjectId;
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public List<VoCombo> getVos() {
		return vos;
	}

	public void setVos(List<VoCombo> vos) {
		this.vos = vos;
	}
}
