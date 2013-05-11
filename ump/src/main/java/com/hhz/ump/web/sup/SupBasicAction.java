package com.hhz.ump.web.sup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.ChangeCharset;
import com.hhz.core.utils.CoreContants;
import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.PowerUtils;
import com.hhz.core.utils.RandomUtils;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.dao.app.AppDictTypeManager;
import com.hhz.ump.dao.sup.SupBasicDao;
import com.hhz.ump.dao.sup.SupBasicManager;
import com.hhz.ump.dao.sup.SupContactorManager;
import com.hhz.ump.dao.sup.SupItemManager;
import com.hhz.ump.entity.app.AppAttachFile;
import com.hhz.ump.entity.sup.SupApproveRes;
import com.hhz.ump.entity.sup.SupBasic;
import com.hhz.ump.entity.sup.SupContactor;
import com.hhz.ump.entity.sup.SupDetail;
import com.hhz.ump.entity.sup.SupItem;
import com.hhz.ump.uaap.util.RoleUtil;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.Constants;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.TreeNode;
import com.hhz.ump.web.cost.vo.VoSupTag;

@Namespace("/sup")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "sup-basic!list.action", type = "redirect"),
		@Result(name = "show", location = "sup-basic!input.action", type = "redirect", params = { "id", "${id}" }) })
public class SupBasicAction extends CrudActionSupport<SupBasic> {

	private static final long serialVersionUID = 8071052318603147332L;
	@Autowired
	private SupBasicManager supBasicManager;
	@Autowired
	private SupBasicDao supBasicDao;
	
	@Autowired
	private SupItemManager supItemManager;
	@Autowired
	private SupContactorManager supContactorManager;
	@Autowired
	private AppAttachFileManager appAttachFileManager;
	@Autowired
	private AppDictTypeManager appDictTypeManager;

	private Map<String, String> mapToItemNames = new HashMap<String, String>();
	private Map<String, String> mapToTableNames = new HashMap<String, String>();
	private Map<String, String> mapToContract = new HashMap<String, String>();
	/**
	 * office文件
	 */
	public static final String FILTER_TYPE_OFFICE = "office";
	/**
	 * 图片文件
	 */
	public static final String FILTER_TYPE_IMAGE = "image";

	/**
	 * 视频文件
	 */
	public static final String FILTER_TYPE_VIDEO = "video";
	/**
	 * 视频文件
	 */
	public static final String FILTER_TYPE_PDF = "pdf";

	/**
	 * 视频文件
	 */
	public static final String FILTER_TYPE_IMAGE_OR_OFFICE = "image|office";

	private String[] allowTypes = CoreContants.FILE_TYPE;
	/**
	 * 允许上传的文件类型代码
	 */
	private String filterType;

	private SupBasic entity;

	private String supType;

	private String supTypeSn;

	//供方类别(选中的)
	private String suppTypes;
	
	//供方名称
	private String supName;
	//供方级别
	private Short supManaEval;
	//考察
	private Short supExamResu;
	//审核状态 add by huangbijin 2012-06-05
	private String supAudit;
	//合作情况
	private Short supCooperated;
	//是否来自官网
	private String isOfficalWeb;
	//邀标次数
	private int invitationBidSize;
	//是否定标
	private String isBid;
	//定标时间
	private String appCompTime1;
	private String appCompTime2;

	//定标次数
	private int bidSize;
	
	//合同次数
	private int contractSize;
	
	private String fileName;
	
	private int pageSize = 10;

	private String contactorId;

	private String downFileName;

	private String realFileName;
	private InputStream is;

	/**
	 * 上传文件名
	 */
	private String contentType;

	private List<AppAttachFile> appAttachFiles_bas;
	private List<AppAttachFile> appAttachFiles_rang;
	private List<AppAttachFile> appAttachFiles_zx;
	private List<AppAttachFile> appAttachFiles_zz;
	private List<AppAttachFile> appAttachFiles_save;
	private List<AppAttachFile> appAttachFiles_mana;
	private List<AppAttachFile> appAttachFiles_qual;
	private List<AppAttachFile> appAttachFiles_hono;
	private List<AppAttachFile> appAttachFiles_fin;
	private List<AppAttachFile> appAttachFiles_pro;
	private List<AppAttachFile> appAttachFiles_exam;
	private List<AppAttachFile> appAttachFiles_eval;
	private List<AppAttachFile> appAttachFiles_main;
	private List<AppAttachFile> appAttachFiles_cop;
	private List<AppAttachFile> appAttachFiles_oper;

	private File[] upload_bas;
	private String[] upload_basFileName;
	private String[] upload_basContentType;

	private File[] upload_rang;
	private String[] upload_rangFileName;
	private String[] upload_rangContentType;

	private File[] upload_zx;
	private String[] upload_zxFileName;
	private String[] upload_zxContentType;

	private File[] upload_zz;
	private String[] upload_zzFileName;
	private String[] upload_zzContentType;

	private File[] upload_safe;
	private String[] upload_safeFileName;
	private String[] upload_safeContentType;

	private File[] upload_mana;
	private String[] upload_manaFileName;
	private String[] upload_manaContentType;

	private File[] upload_qual;
	private String[] upload_qualFileName;
	private String[] upload_qualContentType;

	private File[] upload_hono;
	private String[] upload_honoFileName;
	private String[] upload_honoContentType;

	private File[] upload_fin;
	private String[] upload_finFileName;
	private String[] upload_finContentType;

	private File[] upload_pro;
	private String[] upload_proFileName;
	private String[] upload_proContentType;

	private File[] upload_exam;
	private String[] upload_examFileName;
	private String[] upload_examContentType;

	private File[] upload_eval;
	private String[] upload_evalFileName;
	private String[] upload_evalContentType;

	private File[] upload_main;
	private String[] upload_mainFileName;
	private String[] upload_mainContentType;

	private File[] upload_cop;
	private String[] upload_copFileName;
	private String[] upload_copContentType;

	private File[] upload_oper;
	private String[] upload_operFileName;
	private String[] upload_operContentType;
	
	
	//标签类别
	private List<VoSupTag> listTag1;
	private List<VoSupTag> listTag2;
	
	//搜索条件
	private String tag1Cds;//逗号隔开
	private String tag2Cds;//逗号隔开

	@Override
	public String delete() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			// entity = supBasicManager.getSupBasic(getId());
			supBasicManager.deleteSupBasic(getId());
			Struts2Utils.renderJson("1");
			addActionMessage(getText("common.success"));
		}
		return null;
	}

	public String supDeleteFlg() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = supBasicManager.getSupBasic(getId());
			entity.setDeleteFlg("1");
			Struts2Utils.renderJson("1");
			supBasicManager.saveSupBasic(entity, null);
		}
		return null;
	}

	public String deleteContactor() {
		if (StringUtils.isNotBlank(contactorId)) {
			supContactorManager.deleteSupContactor(contactorId);
			Struts2Utils.renderJson("1");
			addActionMessage(getText("common.success"));
		}
		return null;
	}

	@Override
	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String input() throws Exception {
		if (StringUtils.isNotBlank(entity.getSupBasicId())) {
			// 修改
			List<SupBasic> list = new ArrayList<SupBasic>();
			list.add(entity);
			buildToItemNames(list);
			SupDetail supDetail = entity.getSupDetails().get(0);
			if (supDetail.getComBasFileSn() != null) {
				Criterion criterion1 = Restrictions.eq("bizEntityId", entity.getSupDetails().get(0).getComBasFileSn());
				List<AppAttachFile> result = appAttachFileManager.findBy(criterion1);
				setAppAttachFiles_bas(result);
			}
			if (supDetail.getComZxFileSn() != null) {
				Criterion criterion1 = Restrictions.eq("bizEntityId", entity.getSupDetails().get(0).getComZxFileSn());
				List<AppAttachFile> result = appAttachFileManager.findBy(criterion1);
				setAppAttachFiles_zx(result);
			}
			if (supDetail.getComRangFileSn() != null) {
				Criterion criterion1 = Restrictions.eq("bizEntityId", entity.getSupDetails().get(0).getComRangFileSn());
				List<AppAttachFile> result = appAttachFileManager.findBy(criterion1);
				setAppAttachFiles_rang(result);
			}
			if (supDetail.getComZzFileSn() != null) {
				Criterion criterion1 = Restrictions.eq("bizEntityId", entity.getSupDetails().get(0).getComZzFileSn());
				List<AppAttachFile> result = appAttachFileManager.findBy(criterion1);
				setAppAttachFiles_zz(result);
			}
			if (supDetail.getComSaveFileSn() != null) {
				Criterion criterion1 = Restrictions.eq("bizEntityId", entity.getSupDetails().get(0).getComSaveFileSn());
				List<AppAttachFile> result = appAttachFileManager.findBy(criterion1);
				setAppAttachFiles_save(result);
			}
			if (supDetail.getComManaFileSn() != null) {
				Criterion criterion1 = Restrictions.eq("bizEntityId", entity.getSupDetails().get(0).getComManaFileSn());
				List<AppAttachFile> result = appAttachFileManager.findBy(criterion1);
				setAppAttachFiles_mana(result);
			}
			if (supDetail.getComQualFileSn() != null) {
				Criterion criterion1 = Restrictions.eq("bizEntityId", entity.getSupDetails().get(0).getComQualFileSn());
				List<AppAttachFile> result = appAttachFileManager.findBy(criterion1);
				setAppAttachFiles_qual(result);
			}
			if (supDetail.getComHonoFileSn() != null) {
				Criterion criterion1 = Restrictions.eq("bizEntityId", entity.getSupDetails().get(0).getComHonoFileSn());
				List<AppAttachFile> result = appAttachFileManager.findBy(criterion1);
				setAppAttachFiles_hono(result);
			}
			if (supDetail.getComFinFileSn() != null) {
				Criterion criterion1 = Restrictions.eq("bizEntityId", entity.getSupDetails().get(0).getComFinFileSn());
				List<AppAttachFile> result = appAttachFileManager.findBy(criterion1);
				setAppAttachFiles_fin(result);
			}
			if (supDetail.getComProFileSn() != null) {
				Criterion criterion1 = Restrictions.eq("bizEntityId", entity.getSupDetails().get(0).getComProFileSn());
				List<AppAttachFile> result = appAttachFileManager.findBy(criterion1);
				setAppAttachFiles_pro(result);
			}
			if (supDetail.getComExamFileSn() != null) {
				Criterion criterion1 = Restrictions.eq("bizEntityId", entity.getSupDetails().get(0).getComExamFileSn());
				List<AppAttachFile> result = appAttachFileManager.findBy(criterion1);
				setAppAttachFiles_exam(result);
			}
			if (supDetail.getComEvalFileSn() != null) {
				Criterion criterion1 = Restrictions.eq("bizEntityId", entity.getSupDetails().get(0).getComEvalFileSn());
				List<AppAttachFile> result = appAttachFileManager.findBy(criterion1);
				setAppAttachFiles_eval(result);
			}
			if (supDetail.getComMainFileSn() != null) {
				Criterion criterion1 = Restrictions.eq("bizEntityId", entity.getSupDetails().get(0).getComMainFileSn());
				List<AppAttachFile> result = appAttachFileManager.findBy(criterion1);
				setAppAttachFiles_main(result);
			}
			if (supDetail.getCooperatedSn() != null) {
				Criterion criterion1 = Restrictions.eq("bizEntityId", entity.getSupDetails().get(0).getCooperatedSn());
				List<AppAttachFile> result = appAttachFileManager.findBy(criterion1);
				setAppAttachFiles_cop(result);
			}
			if (entity.getOperateRangeSn() != null) {
				Criterion criterion1 = Restrictions.eq("bizEntityId", entity.getOperateRangeSn());
				List<AppAttachFile> result = appAttachFileManager.findBy(criterion1);
				setAppAttachFiles_oper(result);
			}
			buildContactorAttributes(entity.getSupContactors());
			invitationBidSize =0;
			bidSize=0;
			contractSize=0;
			if(entity.getSupApproveReses()!=null&&entity.getSupApproveReses().size()>0){
				for(SupApproveRes app: entity.getSupApproveReses()){
					if(StringUtils.isNotBlank(app.getSupAppType())){
						if("0".equals(app.getSupAppType())){
							invitationBidSize++;
						}if("1".equals(app.getSupAppType())){
							bidSize++;
						}else if("2".equals(app.getSupAppType())){
							contractSize++;
						}
					}
				}
			}
			
		} else {
			entity = new SupBasic();
		}
		return "input";
	}

	@Override
	public String list() throws Exception {
		
		Map<String, Object> values = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append(" select a from SupBasic a,SupDetail b ");
		hql.append("  where b.supBasic.supBasicId=a.supBasicId ");
		if (StringUtils.isNotBlank(suppTypes)) {
			String[] types = suppTypes.replaceAll(" ", "").split(",");
			hql.append(" and (");
			for (int i = 0; i < types.length; i++) {
				hql.append(" a.supTypeSn like :supTypeSn").append(i);
				values.put("supTypeSn"+i, "%"+types[i]+"%");
				if (i != (types.length - 1)) {
					hql.append(" or ");
				}
			}
			hql.append(" )");
		}
		//标签1
		if(StringUtils.isNotBlank(tag1Cds)){
			String[] arr1 = tag1Cds.split(",");
			if(arr1.length > 0){
				hql.append(" and (");
				for(int i=0; i<arr1.length; i++){
					if(i>0){
						hql.append(" or ");
					}
					if("未设置".equals(arr1[i])){//未设置为空
						hql.append(" a.tag1 is null ");
					}else{
						hql.append(" a.tag1 like :tag1_"+i+" ");
						values.put("tag1_" + i, "%"+arr1[i]+"%");
					}
				}
				hql.append(" )");
			}
		}
		//标签2
		if(StringUtils.isNotBlank(tag2Cds)){
			String[] arr2 = tag2Cds.split(",");
			if(arr2.length > 0){
				hql.append(" and (");
				for(int i=0; i<arr2.length; i++){
					if(i>0){
						hql.append(" or ");
					}
					if("未设置".equals(arr2[i])){//未设置为空
						hql.append(" a.tag2 is null ");
					}else{
						hql.append(" a.tag2 like :tag2_"+i+" ");
						values.put("tag2_" + i, "%"+arr2[i]+"%");
					}
				}
				hql.append(" )");
			}
		}
		
		if (supName != null && (!("".equals(supName) || "0".equals(supName)))) {
			hql.append(" and a.supName like :supName");
			values.put("supName", "%"+supName+"%");
		}
		if (supManaEval != null) {
			hql.append(" and a.supManaEval =").append(supManaEval);
			//param.put("supManaEval", supManaEval);
		}
		if (supExamResu != null && (supExamResu != 0)) {
			hql.append(" and a.supExamResu =").append(supExamResu);
			//param.put("supExamResu", supExamResu);
		}
		if (StringUtils.isNotBlank(supAudit)) {
			hql.append(" and a.supAudit =").append(supAudit);
			//param.put("supAudit", supAudit);
		}
		if (supCooperated != null && (supCooperated != 0)) {
			hql.append(" and b.cooperated=").append(supCooperated);
			//param.put("supCooperated", supCooperated);
		}
		if (isOfficalWeb != null && "1".equals(isOfficalWeb)) {
			hql.append(" and a.supInfoId is not null");
		}
		if(isBid!=null&&"1".equals(isBid)||StringUtils.isNotBlank(appCompTime1)||StringUtils.isNotBlank(appCompTime2)){
			//搜索定标的供应商数据 ：2
			hql.append(" and exists(select 1 from  SupApproveRes c where c.supBasic.supBasicId =a.supBasicId and c.supAppType ='1'");
			if(StringUtils.isNotBlank(appCompTime1)){
				hql.append(" and c.appCompTime >= :appCompTime1");
				values.put("appCompTime1", DateOperator.parse(appCompTime1, "yyyy-MM-dd"));
			}
			if(StringUtils.isNotBlank(appCompTime2)){
				hql.append(" and c.appCompTime <= :appCompTime2");
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date endDate=DateOperator.addDays(format.parse(appCompTime2), 1);
				values.put("appCompTime2",  DateOperator.parse(format.format(endDate), "yyyy-MM-dd"));
			}
			hql.append(")");
		}
		// 过滤删除标志为1的记录
		hql.append(" and a.deleteFlg=:deleteFlg");
		values.put("deleteFlg", "0");
		hql.append(" order by case when a.supManaEval =1 then 8 ");
		hql.append("when a.supManaEval =7 then 7 ");
		hql.append("when a.supManaEval =6 then 6 ");
		hql.append("when a.supManaEval =4 then 5 ");
		hql.append("when a.supManaEval =2 then 4 ");
		hql.append("when a.supManaEval =8 then 3 ");
		hql.append("when a.supManaEval =3 then 2 ");
		hql.append("when a.supManaEval =5 then 1 ");
		hql.append("when a.supManaEval =0 then 0 ");
		hql.append("when a.supManaEval is null then 0 end desc,");
		hql.append("case when a.supExamResu=1 then 4 ");
		hql.append("when a.supExamResu=4 then 3 ");
		hql.append("when a.supExamResu=2 then 2 ");
		hql.append("when a.supExamResu=3 then 1 ");
		hql.append("when a.supExamResu is null then 0 end desc,");
		hql.append("case when b.cooperated =1 then 2 ");
		hql.append("when b.cooperated =2 then 1 ");
		hql.append("when b.cooperated =0 then 0 ");
		hql.append("when b.cooperated is null then 0 end desc,a.supBasicId desc");

		//前台传入翻页,不能用page.pageNo 传参数。
		String pageNo = Struts2Utils.getParameter("pageNo");
		if(StringUtils.isNotBlank(pageNo)){
			page.setPageNo(Integer.valueOf(pageNo).intValue());
		}
			
		page.setPageSize(pageSize);
		page = supBasicManager.findPage(page, hql.toString(), values);
		buildToItemNames(page.getResult());

		return "list";

	}

	public void buildToItemNames(List<SupBasic> supItemlList) {
		for (SupBasic details : supItemlList) {
			String finItem = details.getSupTypeSn();
			// String table = details.getTableType();
			StringBuffer itemName = new StringBuffer("");
			// StringBuffer tableName = new StringBuffer();
			if (finItem != null && !"".equals(finItem)) {
				String[] finItemCd = finItem.split(",");
				for (int i = 0; i < finItemCd.length; i++) {
					List<String> itemList = new ArrayList<String>();
					List<String> nameList = supItemManager.getItemName(finItemCd[i], itemList);
					StringBuffer name = new StringBuffer();
					if (nameList != null && nameList.size() > 0) {
						for (int j = nameList.size(); j > 0; j--) {
							// 如果存在相同的父类，则其中一个父类不显示
							if (itemName.toString().indexOf(nameList.get(j - 1)) < 0) {
								// name.append(nameList.get(j - 1) + "/");
								if (j > 1) {
									// 若没有相同的父结点，则用。表示
									if (j == nameList.size() && !"".equals(itemName.toString())) {
										itemName.delete(itemName.length() - 1, itemName.length());
										itemName.append("。");
									}
									name.append(nameList.get(j - 1) + "/");
								} else {
									name.append(nameList.get(j - 1));
								}
							}

						}
					}
					// 填充设计类
					if (name != null && !"".equals(name)) {
						if (i == (finItemCd.length - 1)) {
							itemName.append(name);
						} else {
							itemName.append(name).append(";");
						}
					}
				}
			}
			if (!"".equals(itemName)) {
				mapToItemNames.put(finItem, itemName.toString());
				// mapToTableNames.put(table, tableName.toString());
			}
		}
	}

	public String query() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = supBasicManager.getSupBasic(getId());
			List<SupBasic> list = new ArrayList<SupBasic>();
			list.add(entity);
			// buildToItemNames(list);
			if (entity.getSupDetails() != null && entity.getSupDetails().size() > 0) {
				Criterion criterion1 = Restrictions.eq("bizEntityId", entity.getSupDetails().get(0).getComBasFileSn());
				List<AppAttachFile> result = appAttachFileManager.findBy(criterion1);
				setAppAttachFiles_bas(result);
				criterion1 = Restrictions.eq("bizEntityId", entity.getSupDetails().get(0).getComRangFileSn());
				result = appAttachFileManager.findBy(criterion1);
				setAppAttachFiles_rang(result);
				criterion1 = Restrictions.eq("bizEntityId", entity.getSupDetails().get(0).getComZxFileSn());
				result = appAttachFileManager.findBy(criterion1);
				setAppAttachFiles_zx(result);
				criterion1 = Restrictions.eq("bizEntityId", entity.getSupDetails().get(0).getComZzFileSn());
				result = appAttachFileManager.findBy(criterion1);
				setAppAttachFiles_zz(result);
				criterion1 = Restrictions.eq("bizEntityId", entity.getSupDetails().get(0).getComSaveFileSn());
				result = appAttachFileManager.findBy(criterion1);
				setAppAttachFiles_save(result);
				criterion1 = Restrictions.eq("bizEntityId", entity.getSupDetails().get(0).getComManaFileSn());
				result = appAttachFileManager.findBy(criterion1);
				setAppAttachFiles_mana(result);
				criterion1 = Restrictions.eq("bizEntityId", entity.getSupDetails().get(0).getComQualFileSn());
				result = appAttachFileManager.findBy(criterion1);
				setAppAttachFiles_qual(result);
				criterion1 = Restrictions.eq("bizEntityId", entity.getSupDetails().get(0).getComHonoFileSn());
				result = appAttachFileManager.findBy(criterion1);
				setAppAttachFiles_hono(result);
				criterion1 = Restrictions.eq("bizEntityId", entity.getSupDetails().get(0).getComProFileSn());
				result = appAttachFileManager.findBy(criterion1);
				setAppAttachFiles_pro(result);
				criterion1 = Restrictions.eq("bizEntityId", entity.getSupDetails().get(0).getComExamFileSn());
				result = appAttachFileManager.findBy(criterion1);
				setAppAttachFiles_exam(result);
				criterion1 = Restrictions.eq("bizEntityId", entity.getSupDetails().get(0).getComFinFileSn());
				result = appAttachFileManager.findBy(criterion1);
				setAppAttachFiles_fin(result);
				criterion1 = Restrictions.eq("bizEntityId", entity.getSupDetails().get(0).getComEvalFileSn());
				result = appAttachFileManager.findBy(criterion1);
				setAppAttachFiles_eval(result);
				criterion1 = Restrictions.eq("bizEntityId", entity.getSupDetails().get(0).getComMainFileSn());
				result = appAttachFileManager.findBy(criterion1);
				setAppAttachFiles_main(result);
				criterion1 = Restrictions.eq("bizEntityId", entity.getSupDetails().get(0).getCooperatedSn());
				result = appAttachFileManager.findBy(criterion1);
				setAppAttachFiles_cop(result);
				buildContactorAttributes(entity.getSupContactors());
			}
			return "query";
		}
		return "input";
	}

	@Override
	public void prepareSave() {
		// TODO Auto-generated method stub
		if (StringUtils.isNotBlank(getId())) {
			entity = supBasicManager.getSupBasic(getId());

		} else {
			entity = new SupBasic();
		}
	}

	@Override
	public String save() throws Exception {
		// 保存附件

		String bizModuleCd = "suppliers";
		// 文件路径
		File dir = new File(PowerUtils.getFilePath(bizModuleCd,true));
		if (!dir.exists()) {
			dir.mkdirs();
		}
		List<AppAttachFile> lstAdd = new ArrayList<AppAttachFile>();
		initFilterType();
		List<File[]> fileList = new ArrayList<File[]>();
		List<String[]> nameList = new ArrayList<String[]>();
		List<String[]> conTypeList = new ArrayList<String[]>();
		List<String> bizEntityId = new ArrayList<String>();
		if (upload_bas != null && upload_bas[0] != null) {
			fileList.add(upload_bas);
			nameList.add(upload_basFileName);
			conTypeList.add(upload_basContentType);
			bizEntityId.add(entity.getSupDetails().get(0).getComBasFileSn());
		}
		if (upload_rang != null && upload_rang[0] != null) {
			fileList.add(upload_rang);
			nameList.add(upload_rangFileName);
			conTypeList.add(upload_rangContentType);
			bizEntityId.add(entity.getSupDetails().get(0).getComRangFileSn());
		}
		if (upload_zx != null && upload_zx[0] != null) {
			fileList.add(upload_zx);
			nameList.add(upload_zxFileName);
			conTypeList.add(upload_zxContentType);
			bizEntityId.add(entity.getSupDetails().get(0).getComZxFileSn());
		}
		if (upload_zz != null && upload_zz[0] != null) {
			fileList.add(upload_zz);
			nameList.add(upload_zzFileName);
			conTypeList.add(upload_zzContentType);
			bizEntityId.add(entity.getSupDetails().get(0).getComZzFileSn());
		}
		if (upload_safe != null && upload_safe[0] != null) {
			fileList.add(upload_safe);
			nameList.add(upload_safeFileName);
			conTypeList.add(upload_safeContentType);
			bizEntityId.add(entity.getSupDetails().get(0).getComSaveFileSn());
		}
		if (upload_mana != null && upload_mana[0] != null) {
			fileList.add(upload_mana);
			nameList.add(upload_manaFileName);
			conTypeList.add(upload_manaContentType);
			bizEntityId.add(entity.getSupDetails().get(0).getComManaFileSn());
		}
		if (upload_qual != null && upload_qual[0] != null) {
			fileList.add(upload_qual);
			nameList.add(upload_qualFileName);
			conTypeList.add(upload_qualContentType);
			bizEntityId.add(entity.getSupDetails().get(0).getComQualFileSn());
		}
		if (upload_hono != null && upload_hono[0] != null) {
			fileList.add(upload_hono);
			nameList.add(upload_honoFileName);
			conTypeList.add(upload_honoContentType);
			bizEntityId.add(entity.getSupDetails().get(0).getComHonoFileSn());
		}
		if (upload_fin != null && upload_fin[0] != null) {
			fileList.add(upload_fin);
			nameList.add(upload_finFileName);
			conTypeList.add(upload_finContentType);
			bizEntityId.add(entity.getSupDetails().get(0).getComFinFileSn());
		}
		if (upload_pro != null && upload_pro[0] != null) {
			fileList.add(upload_pro);
			nameList.add(upload_proFileName);
			conTypeList.add(upload_proContentType);
			bizEntityId.add(entity.getSupDetails().get(0).getComProFileSn());
		}
		if (upload_exam != null && upload_exam[0] != null) {
			fileList.add(upload_exam);
			nameList.add(upload_examFileName);
			conTypeList.add(upload_examContentType);
			bizEntityId.add(entity.getSupDetails().get(0).getComExamFileSn());
		}
		if (upload_eval != null && upload_eval[0] != null) {
			fileList.add(upload_eval);
			nameList.add(upload_evalFileName);
			conTypeList.add(upload_evalContentType);
			bizEntityId.add(entity.getSupDetails().get(0).getComEvalFileSn());
		}
		if (upload_main != null && upload_main[0] != null) {
			fileList.add(upload_main);
			nameList.add(upload_mainFileName);
			conTypeList.add(upload_mainContentType);
			bizEntityId.add(entity.getSupDetails().get(0).getComMainFileSn());
		}
		if (upload_cop != null && upload_cop[0] != null) {
			fileList.add(upload_cop);
			nameList.add(upload_copFileName);
			conTypeList.add(upload_copContentType);
			bizEntityId.add(entity.getSupDetails().get(0).getCooperatedSn());
		}
		if (upload_oper != null && upload_oper[0] != null) {
			fileList.add(upload_oper);
			nameList.add(upload_operFileName);
			conTypeList.add(upload_operContentType);
			bizEntityId.add(entity.getOperateRangeSn());
		}

		if (fileList != null && fileList.size() > 0) {
			for (int k = 0; k < fileList.size(); k++) {
				File[] upload = fileList.get(k);
				String[] uploadFileName = nameList.get(k);
				String[] uploadContentType = conTypeList.get(k);
				String bizEntId = bizEntityId.get(k);
				Long maxFileSize = 0l;
				for (int i = 0; i < upload.length; i++) {
					maxFileSize += upload[i].length();
				}
				if (maxFileSize > Constants.MAX_FILE_SIZE) {
					Struts2Utils.renderHtml("maxSizeError-" + Constants.MAX_FILE_SIZE / (1024 * 1000));
					return null;
				}
				for (int i = 0; i < upload.length; i++) {
					if (upload[i] != null) {
						String newFileName = getFileName(uploadFileName[i]);
						File fout = new File(dir, newFileName);
						FileOutputStream fos = new FileOutputStream(fout);
						FileInputStream fin = new FileInputStream(upload[i]);
						byte[] buffer = new byte[1024];
						int len = 0;
						while ((len = fin.read(buffer)) > 0) {
							fos.write(buffer, 0, len);
						}
						AppAttachFile attachFile = new AppAttachFile();
						attachFile.setBizModuleCd(bizModuleCd);
						attachFile.setBizEntityId(bizEntId);
						attachFile.setRealFileName(uploadFileName[i]);
						attachFile.setFileName(newFileName);
						attachFile.setFilePath(PowerUtils.getFilePath(bizModuleCd,true));
						attachFile.setFileTypeName(uploadContentType[i]);
						attachFile.setFileSize(new BigDecimal(fout.length()));
						String strStatus = AppAttachFileManager.STATUS_NORMAL;
						if (bizEntId.length() == 10) {
							strStatus = AppAttachFileManager.STATUS_TEMP;
						}
						attachFile.setStatusCd(strStatus);// 1-正常，0-删除
						lstAdd.add(attachFile);
						// }
					}
				}
			}
		}
		//若果供方评级为空，则默认为待定
		if(StringUtils.isBlank(entity.getSupManaEval())) {
			entity.setSupManaEval("0");
		}
		supBasicManager.saveSupBasic(entity, lstAdd);
		setId(entity.getSupBasicId());
		// Struts2Utils.renderHtml("success");
		return "show";
	}

	private void initFilterType() {
		if (StringUtils.isNotEmpty(filterType)) {
			String[] stoke = filterType.split("\\|");
			Set<String> typeSet = new HashSet<String>();
			for (String string : stoke) {
				if (string.equals(FILTER_TYPE_IMAGE)) {
					for (String fileType : CoreContants.FILE_TYPE_IMAGE) {
						typeSet.add(fileType);
					}
				} else if (string.equals(FILTER_TYPE_OFFICE)) {
					for (String fileType : CoreContants.FILE_TYPE_OFFICE) {
						typeSet.add(fileType);
					}
				} else if (string.equals(FILTER_TYPE_VIDEO)) {
					for (String fileType : CoreContants.FILE_TYPE_VIDEO) {
						typeSet.add(fileType);
					}
				} else if (string.equals(FILTER_TYPE_PDF)) {
					typeSet.add("application/pdf");
				}
			}
			allowTypes = new String[typeSet.size()];
			typeSet.toArray(allowTypes);
		}

	}

	/**
	 * 生成文件名
	 * 
	 * @param uploadFileName1
	 * @return
	 */
	private String getFileName(String uploadFileName1) {
		String tmpName;
		if (!StringUtils.isEmpty(fileName)) {
			tmpName = fileName;
		} else {
			Date now = new Date();
			String dateFormat = DateOperator.formatDate(now, "yyyyMMddHHmmss");
			tmpName = dateFormat + RandomUtils.generateString(4) + getSuffix(uploadFileName1);
		}
		return tmpName;
	}

	/**
	 * 取得文件后缀
	 * 
	 * @param fileNameTmp
	 * @return
	 */
	private String getSuffix(String fileNameTmp) {
		String suffix = null;
		if (StringUtils.isNotEmpty(fileNameTmp)) {
			suffix = fileNameTmp.substring(fileNameTmp.lastIndexOf("."));
		}
		return suffix;
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub

		if (StringUtils.isNotBlank(getId())) {

			entity = supBasicManager.getSupBasic(getId());

		} else {
			String sName=Struts2Utils.getParameter("sName");
			if (StringUtils.isNotBlank(sName)) {
				String sNameTmp=ChangeCharset.decode2GBK(sName);
				boolean flag=Pattern.matches(Constants.PATTERN_NORMAL, sNameTmp);
				if (!flag){
					sNameTmp=ChangeCharset.decode2UTF_8(sName);
				}
				sName=sNameTmp;
				entity = supBasicManager.getSupBasicByName(sName);
			} else {
				entity = new SupBasic();
			}
		}
	}

	public SupBasic getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	/**
	 * 供应商模块初始化
	 * 
	 * @return
	 * @throws Exception
	 */
	public String main() throws Exception {
		/*
		 * if (supType == null || "".equals(supType)) { supType = "1"; }
		 * List<PropertyFilter> filters =
		 * HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		 * PropertyFilter filter = new PropertyFilter("EQs_tableType", supType);
		 * filters.add(filter); page.setOrderBy("updatedDate");
		 * page.setPageSize(pageSize); page.setOrder(Page.DESC); page =
		 * supBasicManager.findPage(page, filters);
		 */
		
		//不自动获取
//		loadSupTags();
		
		return "main";
	}
	/**
	 * 供应商台账名称校验
	 */
	public void supNameValidate(){
		if(StringUtils.isNotBlank(supName)){
			supName=supName.replaceAll(" ", "");
			List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
			filters.add(new PropertyFilter("EQS_supName", supName));
			List<SupBasic> basicList = supBasicManager.find(filters);
			if(basicList!=null){
				Struts2Utils.renderText(String.valueOf(basicList.size()));
			}else{
				Struts2Utils.renderText("0");
			}
		}else{
			Struts2Utils.renderText("0");
		}
	}

	/**
	 * 获取功用上类型树
	 * @return
	 * @throws Exception
	 */
	public String getSupTree() throws Exception {
		
		TreeNode node = new TreeNode();
		node.setId("0");
		node.setText("供应类别");
		node.setOrderNo(new Long(0));
		node.setChecked("0");
		String hql = " select a from SupItem a order by a.dispOrderNum asc ";
		List<SupItem> itemList = supItemManager.find(hql);

		List<TreeNode> childNode = null;
		if (StringUtils.isNotBlank(supTypeSn)) {
			List<String> itemCdList = new ArrayList<String>();
			String[] typeSn = supTypeSn.split(",");
			for (int i = 0; i < typeSn.length; i++) {
				supItemManager.getItemCd(typeSn[i], itemCdList);
			}
			childNode = supItemManager.getChildrenNode(node, true, itemCdList, itemList);
		} else {
			childNode = supItemManager.getChildrenNode(node, true, itemList);
		}
		node.setChildren(childNode);
		
		

		// List<String> typeList = new ArrayList<String>();
		/*
		 * if (supTypeSn != null && !"".equals(supTypeSn)) { //
		 * 把childNode全部放到同一层级 List<TreeNode> newTreeNode = new
		 * ArrayList<TreeNode>(); supItemManager.queryChildNode(childNode,
		 * newTreeNode); String[] typeSn = supTypeSn.split(","); for (int i = 0;
		 * i < typeSn.length; i++) { List<String> allCheckList = new
		 * ArrayList<String>(); allCheckList =
		 * supItemManager.getAllParentChecked(typeSn, childNode, allCheckList);
		 * } }
		 */
		/*
		 * if (supTypeSn != null && !"".equals(supTypeSn)) {
		 * supItemManager.refreshNodeStatus(supTypeSn.split(","), node); }
		 */
		Struts2Utils.renderJson(node);

		return null;
	}
	
	
	/**
	 * 获取定义标签
	 * @return
	 */
	public String loadSupTags(){
		
		String tmpItemTypeCd = Struts2Utils.getParameter("itemTypeCd");
		listTag1 = supBasicManager.getPropItemList(tmpItemTypeCd, "tag1");
		listTag2 = supBasicManager.getPropItemList(tmpItemTypeCd, "tag2");
		
		if(listTag1 != null && listTag1.size() == 0){
			listTag1 = null;
		}
		
		if(listTag2 != null && listTag2.size() == 0){
			listTag2 = null;
		}
		return "tags";
	}

	/**
	 * 在搜索界面上显示供应商类别的树结构
	 */
	public void itemTreeByQuery() {
		if (StringUtils.isNotBlank(getId())) {
			SupBasic supBasic = supBasicManager.getEntity(getId());
			List<SupItem> itemList = supItemManager.getAll();
			TreeNode node = new TreeNode();
			node.setId("0");
			node.setText("供应类别");
			node.setOrderNo(new Long(0));
			List<TreeNode> childNode = new ArrayList<TreeNode>();
			List<String> itemCdList = new ArrayList<String>();
			String[] typeSn = supBasic.getSupTypeSn().split(",");
			for (int i = 0; i < typeSn.length; i++) {
				supItemManager.getItemCd(typeSn[i], itemCdList);
			}
			childNode = supItemManager.getChildrenNode(node, true, itemCdList, itemList);
			getRealChildNode(childNode);

			node.setChildren(childNode);
			Struts2Utils.renderJson(node);
		}

	}

	private List<TreeNode> getRealChildNode(List<TreeNode> childNode) {
		// 对childNode进行遍历，去掉不要的child
		for (Iterator<TreeNode> it = childNode.iterator(); it.hasNext();) {
			TreeNode treeNode = it.next();
			if ("0".equals(treeNode.getChecked())) {
				it.remove();
			} else {
				getRealChildNode(treeNode.getChildren());
			}
			treeNode.setChecked(null);
		}
		return childNode;
	}

	public String selectSupTree() throws Exception {
		TreeNode node = new TreeNode();
		node.setId("0");
		node.setText("供应类别");
		node.setOrderNo(new Long(0));
		List<String> typeList = new ArrayList<String>();
		if (supTypeSn != null && !"".equals(supTypeSn)) {
			String[] typeSn = supTypeSn.split(",");
			for (int i = 0; i < typeSn.length; i++) {
				typeList.add(typeSn[i]);
			}
		}
		List<SupItem> itemList = supItemManager.getAll();
		node.setChildren(supItemManager.getChildrenNode(node, false, itemList));
		Struts2Utils.renderJson(node);

		return null;
	}

	private void buildContactorAttributes(List<SupContactor> supContactors) {
		int i = 1;
		for (SupContactor contactor : supContactors) {
			// 鼠标放在标题上时，显示标题和最新的3条评论
			StringBuffer nameTitle = new StringBuffer("联系人");
			nameTitle.append(i);
			StringBuffer tip = new StringBuffer();
			tip.append("<h3 style='margin-top: 10px; font-size: 14px;'>");
			tip.append(nameTitle);
			tip.append("</h3><ul class='commentList' style='margin-top: 3px;font-size: 14px;'>");
			tip.append("<li><strong>姓名：");
			if (contactor.getName() == null) {
				tip.append(" ");
			} else {
				tip.append(contactor.getName());
			}
			tip.append("</strong></li>");
			tip.append("</h3><ul class='commentList' style='margin-top: 3px;font-size: 14px;'>");
			tip.append("<li><strong>区域：");
			if (contactor.getArea() == null) {
				tip.append(" ");
			} else {
				tip.append(contactor.getArea());
			}
			tip.append("</strong></li>");
			tip.append("</h3><ul class='commentList' style='margin-top: 3px;font-size: 14px;'>");
			tip.append("<li><strong>电话：");
			if (contactor.getTelephone() == null) {
				tip.append(" ");
			} else {
				tip.append(contactor.getTelephone());
			}
			tip.append("</strong></li>");
			tip.append("</h3><ul class='commentList' style='margin-top: 3px;font-size: 14px;'>");
			tip.append("<li><strong>e-mail：");
			if (contactor.getEMail() == null) {
				tip.append(" ");
			} else {
				tip.append(contactor.getEMail());
			}
			tip.append("</strong></li>");

			tip.append("</ul>");
			mapToContract.put(contactor.getSupContactorId(), tip.toString());
			i++;
		}
	}

	/**
	 * 文件下载
	 * 
	 * @return
	 * @throws Exception
	 */
	public String download() throws Exception {
		String bizModuleCd = "suppliers";
		File file = new File(PowerUtils.getFilePath(bizModuleCd,false) + "\\" + fileName);
		if (!file.exists()) {
			// 如果找不到，根据文件名寻找文件
			AppAttachFile attachFile = appAttachFileManager.getAttachFile(fileName);
			if (attachFile != null) {
				file = new File(attachFile.getFilePath() + "\\" + fileName);
				if (!file.exists()) {
					file = new File(attachFile.getFilePath() + "\\" + attachFile.getCreator() + "\\" + fileName);
				}
			}
		}
		String fileSuf = fileName.substring(fileName.indexOf("."));
		// ChangeCharset.toISO_8859_1(realFileName);
		// setContentDisposition(operator);
		setDownFileName(new String(ChangeCharset.decode2UTF_8(realFileName).getBytes("GBK"), "ISO8859-1"));
		String fileType = PowerUtils.getSamPlaceValue(CoreContants.FILE_TYPE, CoreContants.FILE_SUFFIX, fileSuf);
		setContentType(fileType + ";charset=utf-8");
		// setFileName(null);
		is = new FileInputStream(file);
		return SUCCESS;
	}

	/**
	 * @param value 必传，不可空
	 * @param isNew  
	 * @param isDetail 
	 * @param supStatus 
	 * @param excludeSupBasicIds 排除的ID结合; 非必传，可空，若多个以英文逗号(,)隔开,例如: "id1"或 "id1,id2,id3"
	 * 
	 * @return
	 * @throws Exception
	 */
	public String quickSearch() throws Exception {
		
		supName = Struts2Utils.getParameter("value");
		String isNew = Struts2Utils.getParameter("isNew");
		String isDetail = Struts2Utils.getParameter("isDetail");
		String supStatus = Struts2Utils.getParameter("supStatus");
		
		//返回查询结果中，需要排除的ID结合
		String excludeSupBasicIds = Struts2Utils.getParameter("excludeSupBasicIds");
		
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("LIKES_supName", supName));
		Object[] objs = new Object[] { "1", "2", "4","6","7" ,"8"}; // 优秀，可用，试用,试用未考察
		if (StringUtils.equals(supStatus, "0")) { // 邀标单位
			objs = new Object[] { "1","7","6","4", "2", "0" }; // 优秀，可用，试用，待定
		} else if (StringUtils.equals(supStatus, "1")) { // 供方评级（原单位）
			objs = new Object[] { "1","7","6","4", "2", "3" }; // 优秀，可用，试用，停用
		}
		if (StringUtils.equals(isNew, "true")) {
			objs = new Object[] { "0" };// 待定
		}
		filters.add(new PropertyFilter("INA_supManaEval", objs));
		// 仍需要添加部分过滤条件 比如 审核 不合格之类,
		// 因需求尚未定夺,待需求定下来后再添加
		filters.add(new PropertyFilter("EQS_deleteFlg", "0"));
		page.setPageSize(10);
		page = supBasicManager.findPage(page, filters);
		if (StringUtils.equals(isDetail, "true")) {
			buildToItemNames(page.getResult());
		}
		
		
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	
		Map<String, String> map = null;
		String tmpSupId = null;
		for (SupBasic supBasic : page.getResult()) {
			tmpSupId = supBasic.getSupBasicId();
			
			//是否追加到返回列表
			boolean eanbleRtnFlg = true;
			if(StringUtils.isNotBlank(excludeSupBasicIds)){
				String tmpExcludeIds = excludeSupBasicIds.trim();
				String[] tmpArrIds = tmpExcludeIds.split(",");
				for (String tId : tmpArrIds) {
					if(StringUtils.isNotBlank(tId) && tmpSupId.equals(tId.trim())){
						eanbleRtnFlg = false;
						break;
					}
				}
			}
			//若存在排除ID集合,在跳过不处理.
			if(!eanbleRtnFlg){
				continue;
			}
			
			map = new HashMap<String, String>();
			map.put("sid", tmpSupId);// ID
			map.put("sname", supBasic.getSupName());// 名称
			map.put("contractor", StringUtils.trimToEmpty(supBasic.getSupContractor()));// 联系人
			map.put("tel", StringUtils.trimToEmpty(supBasic.getSupTel()));// 联系电话
			map.put("grade", CodeNameUtil.getDictNameByCd(DictContants.SUP_BASIC_EVALUATE, supBasic.getSupManaEval()));// 供方级别
			map.put("comRegMoney", StringUtils.trimToEmpty(supBasic.getComRegMoney()));// 注册资金
			map.put("supType", mapToItemNames.get(supBasic.getSupTypeSn()));// 类别
			map.put("updatedDate", DateOperator.formatDate(supBasic.getUpdatedDate()));// 更新时间
			map.put("companyWebSite",  StringUtils.trimToEmpty(supBasic.getCompanyWebSite()));// 官网地址
			if (StringUtils.equals(isDetail, "true")) {
				if (supBasic.getSupDetails().size() > 0) {
					SupDetail detail = supBasic.getSupDetails().get(0);
					map.put("comMainPro", StringUtils.trimToEmpty(detail.getComMainPro()));// 主营
					StringBuilder sbzzdj = new StringBuilder();
					sbzzdj.append(StringUtils.trimToEmpty(supBasic.getComZzGrade()));
					if (detail.getComZzGrade2() != null) {
						sbzzdj.append("\n");
						sbzzdj.append(detail.getComZzGrade2());
					}
					map.put("comZzGrade", StringUtils.trimToEmpty(sbzzdj.toString()));// 企业资质等级
					map.put("comZzFileSn", detail.getComZzFileSn());// 企业资质等级附件
					map.put("comHonor", StringUtils.trimToEmpty(detail.getComHonor()));// 企业或产品主要荣誉
					map.put("comHonoFileSn", detail.getComHonoFileSn());// 企业或产品主要荣誉
					map.put("comProPerf", StringUtils.trimToEmpty(detail.getComProPerf()));// 类似工程业绩
					map.put("comProFileSn", detail.getComProFileSn());// 类似工程业绩
					map.put("supComeFrom", StringUtils.trimToEmpty(detail.getSupComeFrom()));// 信息来源
					map.put("comBasFileSn", detail.getComBasFileSn());// 注册资金附件
					map.put("comMainFileSn", detail.getComMainFileSn());// 现有供方级别附件
					map.put("supExamResu", CodeNameUtil.getDictNameByCd(DictContants.SUP_EXAM_RESU, supBasic.getSupExamResu()));// 现场考察结果
					map.put("supExamResuByCd", supBasic.getSupExamResu());//现场考察结果CD
					String comExamFileSn=detail.getComExamFileSn();
					if (comExamFileSn!=null && comExamFileSn.length()==10){
						comExamFileSn=null;
					}
					map.put("comExamFileSn", StringUtils.trimToEmpty(comExamFileSn));// 现场考察结果附件
					StringBuilder sbblyj = new StringBuilder();
					sbblyj.append(StringUtils.trimToEmpty(detail.getSupChecView()));
					if (detail.getComZzGrade2() != null) {
						sbblyj.append("\n");
						sbblyj.append(CodeNameUtil.getDictNameByCd(DictContants.SUP_COOPERATED, detail.getCooperated()));
					}
					map.put("baolongYeji", sbblyj.toString());// 宝龙业绩
					map.put("cooperatedSn", detail.getCooperatedSn());// 宝龙业绩附件
					map.put("supEvaluate", CodeNameUtil.getDictNameByCd(DictContants.SUP_REAL_EVALUATE, detail.getSupEvaluate()));// 履约评估
					map.put("comEvalFileSn", detail.getComEvalFileSn());// 履约评估附件
					if(StringUtils.isNotBlank(supBasic.getExamResuPerson()))
					 {
						map.put("examResuPerson",supBasic.getExamResuPerson());//人员
					}
					if(StringUtils.isNotBlank(supBasic.getExamResuContent())) {
						map.put("examResuContent", supBasic.getExamResuContent());
					}
				}
			}
			list.add(map);
		}
		Struts2Utils.renderJson(list);
		return null;
	}

	public Map<String, String> getMapSupEvaluate() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.SUP_BASIC_EVALUATE);
	}

	public Map<String, String> getMapRealEvaluate() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.SUP_REAL_EVALUATE);
	}

	public Map<String, String> getMapSupExamResu() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.SUP_EXAM_RESU);
	}

	public Map<String, String> getMapSupInfoVeri() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.SUP_INFO_VERI);
	}

	public Map<String, String> getMapCooperated() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.SUP_COOPERATED);
	}

	public Map<String, String> getMapSupAudit() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.SUP_AUDIT);
	}

	public String getSupType() {
		return supType;
	}

	public void setSupType(String supType) {
		this.supType = supType;
	}

	public File[] getUpload_bas() {
		return upload_bas;
	}

	public void setUpload_bas(File[] uploadBas) {
		upload_bas = uploadBas;
	}

	public String[] getUpload_basFileName() {
		return upload_basFileName;
	}

	public void setUpload_basFileName(String[] uploadBasFileName) {
		upload_basFileName = uploadBasFileName;
	}

	public String[] getUpload_basContentType() {
		return upload_basContentType;
	}

	public void setUpload_basContentType(String[] uploadBasContentType) {
		upload_basContentType = uploadBasContentType;
	}

	public File[] getUpload_rang() {
		return upload_rang;
	}

	public void setUpload_rang(File[] uploadRang) {
		upload_rang = uploadRang;
	}

	public String[] getUpload_rangFileName() {
		return upload_rangFileName;
	}

	public void setUpload_rangFileName(String[] uploadRangFileName) {
		upload_rangFileName = uploadRangFileName;
	}

	public String[] getUpload_rangContentType() {
		return upload_rangContentType;
	}

	public void setUpload_rangContentType(String[] uploadRangContentType) {
		upload_rangContentType = uploadRangContentType;
	}

	public File[] getUpload_zx() {
		return upload_zx;
	}

	public void setUpload_zx(File[] uploadZx) {
		upload_zx = uploadZx;
	}

	public String[] getUpload_zxFileName() {
		return upload_zxFileName;
	}

	public void setUpload_zxFileName(String[] uploadZxFileName) {
		upload_zxFileName = uploadZxFileName;
	}

	public String[] getUpload_zxContentType() {
		return upload_zxContentType;
	}

	public void setUpload_zxContentType(String[] uploadZxContentType) {
		upload_zxContentType = uploadZxContentType;
	}

	public File[] getUpload_zz() {
		return upload_zz;
	}

	public void setUpload_zz(File[] uploadZz) {
		upload_zz = uploadZz;
	}

	public String[] getUpload_zzFileName() {
		return upload_zzFileName;
	}

	public void setUpload_zzFileName(String[] uploadZzFileName) {
		upload_zzFileName = uploadZzFileName;
	}

	public String[] getUpload_zzContentType() {
		return upload_zzContentType;
	}

	public void setUpload_zzContentType(String[] uploadZzContentType) {
		upload_zzContentType = uploadZzContentType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSuppTypes() {
		return suppTypes;
	}

	public void setSuppTypes(String suppTypes) {
		this.suppTypes = suppTypes;
	}

	public Map<String, String> getMapToItemNames() {
		return mapToItemNames;
	}

	public void setMapToItemNames(Map<String, String> mapToItemNames) {
		this.mapToItemNames = mapToItemNames;
	}

	public Map<String, String> getMapToTableNames() {
		return mapToTableNames;
	}

	public void setMapToTableNames(Map<String, String> mapToTableNames) {
		this.mapToTableNames = mapToTableNames;
	}

	public List<AppAttachFile> getAppAttachFiles_bas() {
		return appAttachFiles_bas;
	}

	public void setAppAttachFiles_bas(List<AppAttachFile> appAttachFilesBas) {
		appAttachFiles_bas = appAttachFilesBas;
	}

	public String getDownFileName() {
		return downFileName;
	}

	public void setDownFileName(String downFileName) {
		this.downFileName = downFileName;
	}

	public String getRealFileName() {
		return realFileName;
	}

	public void setRealFileName(String realFileName) {
		this.realFileName = realFileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * 提供下载的数据流
	 * 
	 * @return
	 * @throws Exception
	 */

	public InputStream getTargetFile() throws Exception {
		return is;
	}

	public List<AppAttachFile> getAppAttachFiles_rang() {
		return appAttachFiles_rang;
	}

	public void setAppAttachFiles_rang(List<AppAttachFile> appAttachFilesRang) {
		appAttachFiles_rang = appAttachFilesRang;
	}

	public List<AppAttachFile> getAppAttachFiles_zx() {
		return appAttachFiles_zx;
	}

	public void setAppAttachFiles_zx(List<AppAttachFile> appAttachFilesZx) {
		appAttachFiles_zx = appAttachFilesZx;
	}

	public List<AppAttachFile> getAppAttachFiles_zz() {
		return appAttachFiles_zz;
	}

	public void setAppAttachFiles_zz(List<AppAttachFile> appAttachFilesZz) {
		appAttachFiles_zz = appAttachFilesZz;
	}

	public List<AppAttachFile> getAppAttachFiles_save() {
		return appAttachFiles_save;
	}

	public void setAppAttachFiles_save(List<AppAttachFile> appAttachFilesSave) {
		appAttachFiles_save = appAttachFilesSave;
	}

	public List<AppAttachFile> getAppAttachFiles_mana() {
		return appAttachFiles_mana;
	}

	public void setAppAttachFiles_mana(List<AppAttachFile> appAttachFilesMana) {
		appAttachFiles_mana = appAttachFilesMana;
	}

	public List<AppAttachFile> getAppAttachFiles_qual() {
		return appAttachFiles_qual;
	}

	public void setAppAttachFiles_qual(List<AppAttachFile> appAttachFilesQual) {
		appAttachFiles_qual = appAttachFilesQual;
	}

	public List<AppAttachFile> getAppAttachFiles_hono() {
		return appAttachFiles_hono;
	}

	public void setAppAttachFiles_hono(List<AppAttachFile> appAttachFilesHono) {
		appAttachFiles_hono = appAttachFilesHono;
	}

	public List<AppAttachFile> getAppAttachFiles_pro() {
		return appAttachFiles_pro;
	}

	public void setAppAttachFiles_pro(List<AppAttachFile> appAttachFilesPro) {
		appAttachFiles_pro = appAttachFilesPro;
	}

	public List<AppAttachFile> getAppAttachFiles_exam() {
		return appAttachFiles_exam;
	}

	public void setAppAttachFiles_exam(List<AppAttachFile> appAttachFilesExam) {
		appAttachFiles_exam = appAttachFilesExam;
	}

	public List<AppAttachFile> getAppAttachFiles_eval() {
		return appAttachFiles_eval;
	}

	public void setAppAttachFiles_eval(List<AppAttachFile> appAttachFilesEval) {
		appAttachFiles_eval = appAttachFilesEval;
	}

	public List<AppAttachFile> getAppAttachFiles_main() {
		return appAttachFiles_main;
	}

	public void setAppAttachFiles_main(List<AppAttachFile> appAttachFilesMain) {
		appAttachFiles_main = appAttachFilesMain;
	}

	public List<AppAttachFile> getAppAttachFiles_fin() {
		return appAttachFiles_fin;
	}

	public void setAppAttachFiles_fin(List<AppAttachFile> appAttachFilesFin) {
		appAttachFiles_fin = appAttachFilesFin;
	}

	public File[] getUpload_safe() {
		return upload_safe;
	}

	public void setUpload_safe(File[] uploadSafe) {
		upload_safe = uploadSafe;
	}

	public String[] getUpload_safeFileName() {
		return upload_safeFileName;
	}

	public void setUpload_safeFileName(String[] uploadSafeFileName) {
		upload_safeFileName = uploadSafeFileName;
	}

	public String[] getUpload_safeContentType() {
		return upload_safeContentType;
	}

	public void setUpload_safeContentType(String[] uploadSafeContentType) {
		upload_safeContentType = uploadSafeContentType;
	}

	public File[] getUpload_mana() {
		return upload_mana;
	}

	public void setUpload_mana(File[] uploadMana) {
		upload_mana = uploadMana;
	}

	public String[] getUpload_manaFileName() {
		return upload_manaFileName;
	}

	public void setUpload_manaFileName(String[] uploadManaFileName) {
		upload_manaFileName = uploadManaFileName;
	}

	public String[] getUpload_manaContentType() {
		return upload_manaContentType;
	}

	public void setUpload_manaContentType(String[] uploadManaContentType) {
		upload_manaContentType = uploadManaContentType;
	}

	public File[] getUpload_qual() {
		return upload_qual;
	}

	public void setUpload_qual(File[] uploadQual) {
		upload_qual = uploadQual;
	}

	public String[] getUpload_qualFileName() {
		return upload_qualFileName;
	}

	public void setUpload_qualFileName(String[] uploadQualFileName) {
		upload_qualFileName = uploadQualFileName;
	}

	public String[] getUpload_qualContentType() {
		return upload_qualContentType;
	}

	public void setUpload_qualContentType(String[] uploadQualContentType) {
		upload_qualContentType = uploadQualContentType;
	}

	public File[] getUpload_fin() {
		return upload_fin;
	}

	public void setUpload_fin(File[] uploadFin) {
		upload_fin = uploadFin;
	}

	public String[] getUpload_finFileName() {
		return upload_finFileName;
	}

	public void setUpload_finFileName(String[] uploadFinFileName) {
		upload_finFileName = uploadFinFileName;
	}

	public String[] getUpload_finContentType() {
		return upload_finContentType;
	}

	public void setUpload_finContentType(String[] uploadFinContentType) {
		upload_finContentType = uploadFinContentType;
	}

	public File[] getUpload_pro() {
		return upload_pro;
	}

	public void setUpload_pro(File[] uploadPro) {
		upload_pro = uploadPro;
	}

	public String[] getUpload_proFileName() {
		return upload_proFileName;
	}

	public void setUpload_proFileName(String[] uploadProFileName) {
		upload_proFileName = uploadProFileName;
	}

	public String[] getUpload_proContentType() {
		return upload_proContentType;
	}

	public void setUpload_proContentType(String[] uploadProContentType) {
		upload_proContentType = uploadProContentType;
	}

	public File[] getUpload_exam() {
		return upload_exam;
	}

	public void setUpload_exam(File[] uploadExam) {
		upload_exam = uploadExam;
	}

	public String[] getUpload_examFileName() {
		return upload_examFileName;
	}

	public void setUpload_examFileName(String[] uploadExamFileName) {
		upload_examFileName = uploadExamFileName;
	}

	public String[] getUpload_examContentType() {
		return upload_examContentType;
	}

	public void setUpload_examContentType(String[] uploadExamContentType) {
		upload_examContentType = uploadExamContentType;
	}

	public File[] getUpload_eval() {
		return upload_eval;
	}

	public void setUpload_eval(File[] uploadEval) {
		upload_eval = uploadEval;
	}

	public String[] getUpload_evalFileName() {
		return upload_evalFileName;
	}

	public void setUpload_evalFileName(String[] uploadEvalFileName) {
		upload_evalFileName = uploadEvalFileName;
	}

	public String[] getUpload_evalContentType() {
		return upload_evalContentType;
	}

	public void setUpload_evalContentType(String[] uploadEvalContentType) {
		upload_evalContentType = uploadEvalContentType;
	}

	public File[] getUpload_main() {
		return upload_main;
	}

	public void setUpload_main(File[] uploadMain) {
		upload_main = uploadMain;
	}

	public String[] getUpload_mainFileName() {
		return upload_mainFileName;
	}

	public void setUpload_mainFileName(String[] uploadMainFileName) {
		upload_mainFileName = uploadMainFileName;
	}

	public String[] getUpload_mainContentType() {
		return upload_mainContentType;
	}

	public void setUpload_mainContentType(String[] uploadMainContentType) {
		upload_mainContentType = uploadMainContentType;
	}

	public File[] getUpload_hono() {
		return upload_hono;
	}

	public void setUpload_hono(File[] uploadHono) {
		upload_hono = uploadHono;
	}

	public String[] getUpload_honoFileName() {
		return upload_honoFileName;
	}

	public void setUpload_honoFileName(String[] uploadHonoFileName) {
		upload_honoFileName = uploadHonoFileName;
	}

	public String[] getUpload_honoContentType() {
		return upload_honoContentType;
	}

	public void setUpload_honoContentType(String[] uploadHonoContentType) {
		upload_honoContentType = uploadHonoContentType;
	}

	public String getSupName() {
		return supName;
	}

	public void setSupName(String supName) {
		this.supName = supName;
	}

	public Short getSupManaEval() {
		return supManaEval;
	}

	public void setSupManaEval(Short supManaEval) {
		this.supManaEval = supManaEval;
	}

	public Short getSupExamResu() {
		return supExamResu;
	}

	public void setSupExamResu(Short supExamResu) {
		this.supExamResu = supExamResu;
	}

	public File[] getUpload_cop() {
		return upload_cop;
	}

	public void setUpload_cop(File[] uploadCop) {
		upload_cop = uploadCop;
	}

	public String[] getUpload_copFileName() {
		return upload_copFileName;
	}

	public void setUpload_copFileName(String[] uploadCopFileName) {
		upload_copFileName = uploadCopFileName;
	}

	public String[] getUpload_copContentType() {
		return upload_copContentType;
	}

	public void setUpload_copContentType(String[] uploadCopContentType) {
		upload_copContentType = uploadCopContentType;
	}

	public List<AppAttachFile> getAppAttachFiles_cop() {
		return appAttachFiles_cop;
	}

	public void setAppAttachFiles_cop(List<AppAttachFile> appAttachFilesCop) {
		appAttachFiles_cop = appAttachFilesCop;
	}

	public Short getSupCooperated() {
		return supCooperated;
	}

	public void setSupCooperated(Short supCooperated) {
		this.supCooperated = supCooperated;
	}

	public String getSupTypeSn() {
		return supTypeSn;
	}

	public void setSupTypeSn(String supTypeSn) {
		this.supTypeSn = supTypeSn;
	}

	public String getContactorId() {
		return contactorId;
	}

	public void setContactorId(String contactorId) {
		this.contactorId = contactorId;
	}

	public Map<String, String> getMapToContract() {
		return mapToContract;
	}

	public void setMapToContract(Map<String, String> mapToContract) {
		this.mapToContract = mapToContract;
	}

	public String getIsOfficalWeb() {
		return isOfficalWeb;
	}

	public void setIsOfficalWeb(String isOfficalWeb) {
		this.isOfficalWeb = isOfficalWeb;
	}

	public File[] getUpload_oper() {
		return upload_oper;
	}

	public void setUpload_oper(File[] upload_oper) {
		this.upload_oper = upload_oper;
	}

	public String[] getUpload_operFileName() {
		return upload_operFileName;
	}

	public void setUpload_operFileName(String[] upload_operFileName) {
		this.upload_operFileName = upload_operFileName;
	}

	public String[] getUpload_operContentType() {
		return upload_operContentType;
	}

	public void setUpload_operContentType(String[] upload_operContentType) {
		this.upload_operContentType = upload_operContentType;
	}

	public List<AppAttachFile> getAppAttachFiles_oper() {
		return appAttachFiles_oper;
	}

	public void setAppAttachFiles_oper(List<AppAttachFile> appAttachFiles_oper) {
		this.appAttachFiles_oper = appAttachFiles_oper;
	}

	public int getInvitationBidSize() {
		return invitationBidSize;
	}

	public int getBidSize() {
		return bidSize;
	}

	public int getContractSize() {
		return contractSize;
	}

	public String getIsBid() {
		return isBid;
	}

	public void setIsBid(String isBid) {
		this.isBid = isBid;
	}

	public String getAppCompTime1() {
		return appCompTime1;
	}

	public void setAppCompTime1(String appCompTime1) {
		this.appCompTime1 = appCompTime1;
	}

	public String getAppCompTime2() {
		return appCompTime2;
	}

	public void setAppCompTime2(String appCompTime2) {
		this.appCompTime2 = appCompTime2;
	}

	public List<VoSupTag> getListTag1() {
		return listTag1;
	}

	public void setListTag1(List<VoSupTag> listTag1) {
		this.listTag1 = listTag1;
	}

	public List<VoSupTag> getListTag2() {
		return listTag2;
	}

	public void setListTag2(List<VoSupTag> listTag2) {
		this.listTag2 = listTag2;
	}


	public String getTag1Cds() {
		return tag1Cds;
	}

	public void setTag1Cds(String tag1Cds) {
		this.tag1Cds = tag1Cds;
	}

	public String getTag2Cds() {
		return tag2Cds;
	}

	public void setTag2Cds(String tag2Cds) {
		this.tag2Cds = tag2Cds;
	}

	public String getSupAudit() {
		return supAudit;
	}

	public void setSupAudit(String supAudit) {
		this.supAudit = supAudit;
	}

	
	/**
	 * 更改供应商级别
	 * @return
	 */
	public String changeEvalLevel(){
		String id = Struts2Utils.getParameter("id");
		String supEvalLevel = Struts2Utils.getParameter("supEvalLevel");
		if(StringUtils.isBlank(id)){
			Struts2Utils.renderText("参数不对!");
			return null;
		}
		//检查角色
		if(!RoleUtil.validateRole("A_ADMIN")){
			Struts2Utils.renderText("对不起，您没有权限!");
			return null;
		}
		SupBasic supBasic = supBasicManager.getEntity(id);
		supBasic.setSupManaEval(supEvalLevel);
		supBasicManager.save(supBasic);
		Struts2Utils.renderText("success");
		
		return null;
	}
}
