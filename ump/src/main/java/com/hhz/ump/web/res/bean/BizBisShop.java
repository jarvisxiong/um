package com.hhz.ump.web.res.bean;
/**
 * 商家资源入库
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springside.modules.spring.SpringContextHolder;

import com.hhz.ump.dao.bis.BisShopManager;
import com.hhz.ump.dao.bis.BisShopSortManager;
import com.hhz.ump.entity.bis.BisShop;
import com.hhz.ump.entity.bis.BisShopConn;
import com.hhz.ump.entity.bis.BisShopSort;
import com.hhz.ump.entity.bis.BisShopSortRel;
import com.hhz.ump.web.res.baseBean.BaseTemplate;
import com.hhz.ump.web.res.baseBean.IAutoImport;

public class BizBisShop extends BaseTemplate  implements IAutoImport {
	private String nameCn;//品牌名(中)
	private String nameEn;//品牌名(英)
	private String manageCd1;//主力店
	private String manageCd2;//次主力店
	private String manageCd3;//小商铺
	private String brandDesc;//品牌简介
	private String companyName;//公司名称
	private String founderPeople;//创建人
	private String founderDate;//创建时间
	private String produceAddr;//产地
	private String registerAddr;//注册地
	private String brandCradleland;//品牌发源地
	private String webSite;//网站
	private String bisSortId;//商家类别Id
	private String bisSortName;//商家类别
    private List<BizBisShopConn>  bizBisShopConns =new ArrayList<BizBisShopConn>();
    
	public String getNameCn() {
		return nameCn;
	}
	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}
	public String getNameEn() {
		return nameEn;
	}
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}
	public String getManageCd1() {
		return manageCd1;
	}
	public void setManageCd1(String manageCd1) {
		this.manageCd1 = manageCd1;
	}
	public String getManageCd2() {
		return manageCd2;
	}
	public void setManageCd2(String manageCd2) {
		this.manageCd2 = manageCd2;
	}
	public String getManageCd3() {
		return manageCd3;
	}
	public void setManageCd3(String manageCd3) {
		this.manageCd3 = manageCd3;
	}
	public String getBrandDesc() {
		return brandDesc;
	}
	public void setBrandDesc(String brandDesc) {
		this.brandDesc = brandDesc;
	}
	@Override
	public String getCompanyName() {
		return companyName;
	}
	@Override
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getFounderPeople() {
		return founderPeople;
	}
	public void setFounderPeople(String founderPeople) {
		this.founderPeople = founderPeople;
	}
	public String getFounderDate() {
		return founderDate;
	}
	public void setFounderDate(String founderDate) {
		this.founderDate = founderDate;
	}
	public String getProduceAddr() {
		return produceAddr;
	}
	public void setProduceAddr(String produceAddr) {
		this.produceAddr = produceAddr;
	}
	public String getRegisterAddr() {
		return registerAddr;
	}
	public void setRegisterAddr(String registerAddr) {
		this.registerAddr = registerAddr;
	}
	public String getBrandCradleland() {
		return brandCradleland;
	}
	public void setBrandCradleland(String brandCradleland) {
		this.brandCradleland = brandCradleland;
	}
	public String getWebSite() {
		return webSite;
	}
	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}
	public List<BizBisShopConn> getBizBisShopConns() {
		return bizBisShopConns;
	}
	public void setBizBisShopConns(List<BizBisShopConn> bizBisShopConns) {
		this.bizBisShopConns = bizBisShopConns;
	}
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return nameCn;
	}
	@Override
	public void doImport() {
		BisShopManager bisShopManager = SpringContextHolder.getBean("bisShopManager");
		BisShop bisShop =new BisShop();
		//新增品牌数据
		bisShop.setNameCn(nameCn);
		bisShop.setNameEn(nameEn);
		if(StringUtils.isNotBlank(manageCd1)){
			bisShop.setManageCd("1");
		}else if(StringUtils.isNotBlank(manageCd2)){
			bisShop.setManageCd("2");
		}else{
			bisShop.setManageCd("3");
		}
		bisShop.setBrandDesc(brandDesc);
		bisShop.setCompanyName(companyName);
		bisShop.setFounderPeople(founderPeople);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			bisShop.setFounderDate(format.parse(founderDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bisShop.setProduceAddr(produceAddr);
		bisShop.setRegisterAddr(registerAddr);
		bisShop.setBrandCradleland(brandCradleland);
		bisShop.setWebSite(webSite);
		bisShop.setDeleteBl("0");
		//默认为未提交状态
		bisShop.setBisShopAudit("0");
		if(StringUtils.isNotBlank(bisSortId)){
			BisShopSortManager bisShopSortManager = SpringContextHolder.getBean("bisShopSortManager");
			 List<BisShopSortRel> sortRelList = new ArrayList<BisShopSortRel>();
			String[] bisSortIds =bisSortId.split(",");
			for(int i=0;i<bisSortIds.length;i++){
				if(!"".equals(bisSortIds[i])){
					String hql="select a from BisShopSort a where a.bisShopSortId='"+bisSortIds[i]+"'";
					List<BisShopSort> sortList = bisShopSortManager.find(hql);
					if(sortList!=null&&sortList.size()>0){
						BisShopSortRel sortRel =new BisShopSortRel();
						sortRel.setBisShop(bisShop);
						sortRel.setBisShopSort(sortList.get(0));
						sortRelList.add(sortRel);
					}
					
				}
			}
			if(sortRelList!=null&&sortRelList.size()>0){
				bisShop.setBisShopSortRels(sortRelList);
			}
		}
		bisShop.setCreator(getResApproveInfo().getCreator());
		List<BisShopConn> connList =new ArrayList<BisShopConn>();
		for(BizBisShopConn conn:getBizBisShopConns()){
			if(conn!=null&&StringUtils.isNotBlank(conn.getPartName())){
				BisShopConn bisConn=new BisShopConn();
				bisConn.setPartName(conn.getPartName());
				if(StringUtils.isNotBlank(conn.getSupplierAttributeCd1())){
					bisConn.setSupplierAttributeCd("1");
				}else if(StringUtils.isNotBlank(conn.getSupplierAttributeCd2())){
					bisConn.setSupplierAttributeCd("2");
				}else if(StringUtils.isNotBlank(conn.getSupplierAttributeCd3())){
					bisConn.setSupplierAttributeCd("3");
				}else if(StringUtils.isNotBlank(conn.getSupplierAttributeCd4())){
					bisConn.setSupplierAttributeCd("4");
				}else if(StringUtils.isNotBlank(conn.getSupplierAttributeCd5())){
					bisConn.setSupplierAttributeCd("5");
				}else if(StringUtils.isNotBlank(conn.getSupplierAttributeCd6())){
					bisConn.setSupplierAttributeCd("6");
				}else if(StringUtils.isNotBlank(conn.getSupplierAttributeCd7())){
					bisConn.setSupplierAttributeCd("7");
				}
				bisConn.setPartAddress(conn.getPartAddress());
				bisConn.setPrincipalPhone(conn.getPrincipalPhone());
				bisConn.setPrincipalTel(conn.getPrincipalTel());
				bisConn.setPrincipal(conn.getPrincipal());
				bisConn.setPrincipalPos(conn.getPrincipalPos());
				bisConn.setFax(conn.getFax());
				bisConn.setPostCode(conn.getPostCode());
				bisConn.setCooperatedShop(conn.getConditionForCooperation());
				bisConn.setToCooperateShop(conn.getToCooperateShop());
				bisConn.setManageCity(conn.getManageCity());
				bisConn.setConditionForCooperation(conn.getConditionForCooperation());
				bisConn.setRemark(conn.getRemark());
				bisConn.setBisShop(bisShop);
				connList.add(bisConn);
			}
			if(connList!=null&&connList.size()>0) {
				bisShop.setBisShopConns(connList);
			}
		}
		bisShopManager.saveBisShop(bisShop);
	}
	@Override
	public boolean isAutoImport() {
		// TODO Auto-generated method stub
		return true;
	}
	public String getBisSortId() {
		return bisSortId;
	}
	public void setBisSortId(String bisSortId) {
		this.bisSortId = bisSortId;
	}
	public String getBisSortName() {
		return bisSortName;
	}
	public void setBisSortName(String bisSortName) {
		this.bisSortName = bisSortName;
	}
}
