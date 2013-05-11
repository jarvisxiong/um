package com.hhz.ump.web.assm;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.assm.AssmModelManager;
import com.hhz.ump.dao.assm.AssmModelStandardManager;
import com.hhz.ump.dao.assm.AssmModelStandardVo;
import com.hhz.ump.dao.bis.BisProjectManager;
import com.hhz.ump.entity.assm.AssmModel;
import com.hhz.ump.entity.assm.AssmModelStandard;

@Namespace("/assm")
public class AssmModelStandardAction extends CrudActionSupport<AssmModelStandard> {

	
	private static final long serialVersionUID = 4187644772218326013L;
	
	@Autowired 
	private AssmModelStandardManager assmModelStandardManager;
	@Autowired 
	private AssmModelManager assmModelManager;
	@Autowired 
	private BisProjectManager bisProjectManager;
	
	private  List<AssmModelStandardVo> standVos;
	
	private AssmModel assmModel;
	private String listType;
	private AssmModelStandard entity;
	/**
	 * 总体已经配置数
	 */
	private BigDecimal totalHasNum;
	/**
	 * 总体标准配置数
	 */
	private BigDecimal totalStandNum;
	
	
	/**
	 * 搜索设备型号标准配置列表
	 * @return
	 */
	public String loadList(){
		
		//设备型号
		String assmModelId=Struts2Utils.getParameter("assmModelId");	
		//设备模型对象
		if(StringUtils.isNotBlank(assmModelId)){
			assmModel = assmModelManager.getEntity(assmModelId);
		}
		//首先搜索出所有的项目公司
		//List<BisProject> projects=bisProjectManager.findAllProject();
		//再根据项目公司是否已经存在标准配置
		if(assmModel!=null) {
			assmModelStandardManager.findNotExsitProject(assmModel);
		}
		//搜索出本设备型号的所有项目公司的配置列表
		standVos=assmModelStandardManager.findByassmModel(assmModel);
		if("onlyTable".equals(listType))
			return "onlyTable";
		else
			return "loadList";
		
	}
	
	/**
	 * 搜索特定设备的现实使用情况
	 * @return
	 */
	public String modelDetail(){
		//设备型号
		String assmModelId=Struts2Utils.getParameter("assmModelId");	
		//设备模型对象
		if(StringUtils.isNotBlank(assmModelId)){
			assmModel = assmModelManager.getEntity(assmModelId);
			//搜索出本设备型号的所有项目公司的配置列表
			standVos=assmModelStandardManager.findByassmModel(assmModel);
			
			//计算总体配置数量
			if(standVos!=null){
				totalHasNum = BigDecimal.ZERO;
				totalStandNum = BigDecimal.ZERO;
				for(AssmModelStandardVo vo:standVos){
					totalHasNum=totalHasNum.add(vo.getHasNum());
					totalStandNum=totalStandNum.add(vo.getStanNum());
				}
			}
		}
		Struts2Utils.renderText(totalHasNum.setScale(2, RoundingMode.HALF_UP).toString()+","+totalStandNum.setScale(2, RoundingMode.HALF_UP).toString());
		return null;
	}
	
	@Override
	public String delete() throws Exception {
		return null;
	}

	@Override
	public String deleteBatch() throws Exception {
		return null;
	}

	@Override
	public String input() throws Exception {
		return null;
	}

	@Override
	public String list() throws Exception {
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		getModel();
	}

	
	@Override
	public void prepareSave() throws Exception{
		prepareModel();
	}
	
	
	@Override
	public String save() throws Exception {
		
		if(entity!=null){
			assmModelStandardManager.saveAssmModelStandard(entity);
		}
		return null;
	}

	@Override
	public AssmModelStandard getModel() {
		String assmModelStandardId = Struts2Utils.getParameter("assmModelStandardId");
		if(StringUtils.isNotBlank(assmModelStandardId)){
			entity=assmModelStandardManager.getEntity(assmModelStandardId);
		}
		return entity;
	}
	public List<AssmModelStandardVo> getStandVos() {
		return standVos;
	}
	public void setStandVos(List<AssmModelStandardVo> standVos) {
		this.standVos = standVos;
	}

	public AssmModel getAssmModel() {
		return assmModel;
	}

	public void setAssmModel(AssmModel assmModel) {
		this.assmModel = assmModel;
	}

	public String getListType() {
		return listType;
	}

	public void setListType(String listType) {
		this.listType = listType;
	}

	public AssmModelStandard getEntity() {
		return entity;
	}

	public void setEntity(AssmModelStandard entity) {
		this.entity = entity;
	}

	public BigDecimal getTotalHasNum() {
		return totalHasNum;
	}

	public void setTotalHasNum(BigDecimal totalHasNum) {
		this.totalHasNum = totalHasNum;
	}

	public BigDecimal getTotalStandNum() {
		return totalStandNum;
	}

	public void setTotalStandNum(BigDecimal totalStandNum) {
		this.totalStandNum = totalStandNum;
	}
	
	
	
	
	

}
