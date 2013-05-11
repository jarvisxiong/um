package com.hhz.ump.web.bis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.DateFormat;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.DateOperator;
import com.hhz.ump.dao.app.AppDictTypeManager;
import com.hhz.ump.dao.bis.BisContManager;
import com.hhz.ump.dao.bis.BisFactManager;
import com.hhz.ump.dao.bis.BisFlatManager;
import com.hhz.ump.dao.bis.BisFloorManager;
import com.hhz.ump.dao.bis.BisIncomeOtherManager;
import com.hhz.ump.dao.bis.BisMultiManager;
import com.hhz.ump.dao.bis.BisMustManager;
import com.hhz.ump.dao.bis.BisProjectManager;
import com.hhz.ump.entity.bis.BisCont;
import com.hhz.ump.entity.bis.BisFact;
import com.hhz.ump.entity.bis.BisFlat;
import com.hhz.ump.entity.bis.BisFlatContRel;
import com.hhz.ump.entity.bis.BisFloor;
import com.hhz.ump.entity.bis.BisMulti;
import com.hhz.ump.entity.bis.BisMultiContRel;
import com.hhz.ump.entity.bis.BisMust;
import com.hhz.ump.entity.bis.BisProject;
import com.hhz.ump.util.BisConstants;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.DictMapUtil;
import com.hhz.ump.util.JXLExcelUtil;
import com.hhz.ump.web.vo.BisIncomeOtherVo;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 商业其他费用收入(水、电、气等)
 * 
 * @author baolm 2011-8-17
 */
@Namespace("/bis")
@Results( {
		@Result(name = "list", location = "/WEB-INF/content/bis/bis-income-other-list.ftl", type = "freemarker"),
		@Result(name = "export", type = "stream", params = { "contentType", "application/vnd.ms-excel", "inputName", "excelFile", "contentDisposition",
				"attachment;filename=${excelFileName}.xls" }) })
public class BisIncomeOtherAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3388087444760492601L;

	private String bisProjectId; // 项目
	private String bisProjectName;

	private String floorType; // 楼宇类型

	private String bisFloorId; // 楼层

	private String feeDate; // 收费日期

	private String moduleType;// 模板类型：1为应收，2为实收

	private String authDate1;// 权责年月1

	private String authDate2;// 权责年月2

	private String feeTypeId;// 费用类别

	private List<BisIncomeOtherVo> volist = new ArrayList<BisIncomeOtherVo>();

	private InputStream excelFile;// 导出Excel需要的参数
	private String excelFileName;

	private File importFile;

	@Autowired
	private BisProjectManager bisProjectManager;

	@Autowired
	private BisFloorManager bisFloorManager;

	@Autowired
	private BisContManager bisContManager;

	@Autowired
	private BisMustManager bisMustManager;

	@Autowired
	private BisFlatManager bisFlatManager;

	@Autowired
	private BisMultiManager bisMultiManager;

	@Autowired
	private BisIncomeOtherManager bisIncomeOtherManager;

	@Autowired
	private BisFactManager bisFactManager;

	@Autowired
	private AppDictTypeManager appDictTypeManager;

	@Override
	public String execute() throws Exception {
		Struts2Utils.getRequest().setAttribute("isProjectBusinessCompany", bisProjectManager.getCurrProject() != null);

		if (StringUtils.isNotBlank(bisProjectId)) {
			bisProjectName = bisProjectManager.getEntity(bisProjectId).getProjectName();
		} else {
			BisProject bisProject = bisProjectManager.getCurrProject();
			if (bisProject != null) {
				bisProjectId = bisProject.getBisProjectId();
				bisProjectName = bisProject.getProjectName();
			}
		}

		feeDate = DateOperator.formatDate(new Date(), "yyyy-MM");

		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String list() throws Exception {

		String[] date = feeDate.split("-");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("bisProjectId", bisProjectId);
		param.put("bisFloorId", bisFloorId);
		param.put("year", date[0]);
		String month = date[1];
		if (Integer.valueOf(month) < 10) {
			month = date[1].replace("0", "");
		}
		param.put("month", month);

		// String sql = "";
		if ("1".equals(floorType)) {
			// sql = buildStoreQuerySql();
			setStoreListValue(bisIncomeOtherManager.findBySql(buildStoreQuerySql(), param));
		} else if ("2".equals(floorType)) {
			// sql = buildFlatQuerySql();
			setFlatListValue(bisIncomeOtherManager.findBySql(buildFlatQuerySql2(), param));
			setFlatListValue(bisIncomeOtherManager.findBySql(buildFlatQuerySql(), param));
		} else if ("3".equals(floorType)) {
			// sql = buildMultiQuerySql();
			setMultiListValue(bisIncomeOtherManager.findBySql(buildMultiQuerySql2(), param));
			setMultiListValue(bisIncomeOtherManager.findBySql(buildMultiQuerySql(), param));
		}
		// sql = buildQuerySql();
		// List<Object[]> list = bisIncomeOtherManager.findBySql(sql, param);
		// setListValue(list);

		return "list";
	}

	private String buildStoreQuerySql() {

		StringBuffer sql = new StringBuffer();

		sql.append(" select j.bis_tenant_id,j.bis_shop_id,j.name_cn,j.part_name,j.bis_cont_id,j.cont_no,j.cont_small_type_cd,k.store_no,").append(
				" l.must1,l.must2,l.must3,l.must4,l.must5,l.must6,l.must7,l.must8,l.must9,l.must10,l.must11,l.must12,l.must13").append(" from (").append(
				" select bt.bis_tenant_id,bs.bis_shop_id,bs.name_cn,bsc.part_name,bc.bis_cont_id,bc.cont_no,bc.cont_small_type_cd").append(
				" from bis_tenant bt,bis_shop bs, bis_shop_conn bsc, bis_cont bc ").append(" where bt.bis_shop_id=bs.bis_shop_id").append(
				" and bt.bis_shop_conn_id=bsc.bis_shop_conn_id").append(" and bt.bis_tenant_id=bc.bis_tenant_id")
				.append(" and bt.bis_project_id=:bisProjectId").append(" and bc.bis_cont_id in (").append(" select NVL(c1.bis_cont_id,c2.bis_cont_id)").append(
						" from bis_tenant t ").append(
						" left join (select * from bis_cont c where c.cont_small_type_cd='3') c1 on t.bis_tenant_id=c1.bis_tenant_id").append(
						" left join (select * from bis_cont c where c.cont_small_type_cd='1') c2 on t.bis_tenant_id=c2.bis_tenant_id").append(
						" where t.bis_project_id=:bisProjectId)");
		if (StringUtils.isNotBlank(bisFloorId)) {
			sql
					.append(" and exists (select 1 from bis_store st, bis_store_cont_rel scr where st.bis_store_id=scr.bis_store_id and scr.bis_cont_id=bc.bis_cont_id and st.bis_floor_id=:bisFloorId)");
		}
		sql.append(") j").append(" left join (").append(" SELECT c.bis_cont_id, TRANSLATE(LTRIM(c.text, '/'), '*/', '*,') store_no").append(" FROM (").append(
				" SELECT ROW_NUMBER() OVER(PARTITION BY b.bis_cont_id ORDER BY b.bis_cont_id, num DESC) rn, b.bis_cont_id, b.text").append(" FROM (").append(
				" SELECT a.bis_cont_id, level num, SYS_CONNECT_BY_PATH(a.store_no, '/') text").append(" FROM (").append(
				" select bc.bis_cont_id, bs.store_no, ROW_NUMBER() OVER(PARTITION BY bc.bis_cont_id ORDER BY bc.bis_cont_id, bs.store_no) x").append(
				" from bis_cont bc, bis_store_cont_rel scr, bis_store bs").append(" where bc.bis_cont_id=scr.bis_cont_id").append(
				" and scr.bis_store_id=bs.bis_store_id").append(" and bc.bis_project_id=:bisProjectId").append(" and bc.bis_tenant_id is not null").append(
				" group by bc.bis_cont_id, bs.store_no ").append(" ) a").append(" CONNECT BY a.bis_cont_id = PRIOR a.bis_cont_id AND a.x - 1 = PRIOR a.x ")
				.append(" ) b ").append(" ) c ").append(" WHERE c.rn = 1 ").append(" ORDER BY c.bis_cont_id ").append(" ) k on j.bis_cont_id=k.bis_cont_id")
				.append(" left join (").append(" select m.bis_cont_id,").append(" sum(decode(m.charge_type_cd, '5', m.money, null)) must1,").append(
						" sum(decode(m.charge_type_cd, '6', m.money, null)) must2,").append(" sum(decode(m.charge_type_cd, '7', m.money, null)) must3,")
				.append(" sum(decode(m.charge_type_cd, '8', m.money, null)) must4,").append(" sum(decode(m.charge_type_cd, '9', m.money, null)) must5,")
				.append(" sum(decode(m.charge_type_cd, '10', m.money, null)) must6,").append(" sum(decode(m.charge_type_cd, '11', m.money, null)) must7,")
				.append(" sum(decode(m.charge_type_cd, '15', m.money, null)) must8,").append(" sum(decode(m.charge_type_cd, '17', m.money, null)) must9,")
				.append(" sum(decode(m.charge_type_cd, '19', m.money, null)) must10,").append(" sum(decode(m.charge_type_cd, '20', m.money, null)) must11,")
				.append(" sum(decode(m.charge_type_cd, '21', m.money, null)) must12,").append(" sum(decode(m.charge_type_cd, '22', m.money, null)) must13")
				.append(" from bis_must m").append(" where m.bis_project_id=:bisProjectId").append(" and m.must_year=:year").append(" and m.must_month=:month")
				.append(" and m.bis_cont_id is not null").append(" and m.type_cd='1'");
		if (StringUtils.isNotBlank(bisFloorId)) {
			sql.append(" and m.bis_floor_id=:bisFloorId");
		}
		sql.append(" group by m.bis_cont_id").append(" ) l on j.bis_cont_id=l.bis_cont_id").append(" order by k.store_no");

		return sql.toString();
	}

	private String buildFlatQuerySql() {

		StringBuffer sql = new StringBuffer();

		sql.append(" select bf.bis_flat_id,bf.flat_no, ").append(
				" l.must1,l.must2,l.must3,l.must4,l.must5,l.must6,l.must7,l.must8,l.must9,l.must10,l.must11,l.must12,l.must13").append(" from bis_flat bf")
				.append(" left join (").append(" select m.bis_flat_id,").append(" sum(decode(m.charge_type_cd, '5', m.money, null)) must1,").append(
						" sum(decode(m.charge_type_cd, '6', m.money, null)) must2,").append(" sum(decode(m.charge_type_cd, '7', m.money, null)) must3,")
				.append(" sum(decode(m.charge_type_cd, '8', m.money, null)) must4,").append(" sum(decode(m.charge_type_cd, '9', m.money, null)) must5,")
				.append(" sum(decode(m.charge_type_cd, '10', m.money, null)) must6,").append(" sum(decode(m.charge_type_cd, '11', m.money, null)) must7,")
				.append(" sum(decode(m.charge_type_cd, '15', m.money, null)) must8,").append(" sum(decode(m.charge_type_cd, '17', m.money, null)) must9,")
				.append(" sum(decode(m.charge_type_cd, '19', m.money, null)) must10,").append(" sum(decode(m.charge_type_cd, '20', m.money, null)) must11,")
				.append(" sum(decode(m.charge_type_cd, '21', m.money, null)) must12,").append(" sum(decode(m.charge_type_cd, '22', m.money, null)) must13")
				.append(" from bis_must m").append(" where m.bis_project_id=:bisProjectId").append(" and m.must_year=:year").append(" and m.must_month=:month")
				.append(" and m.bis_flat_id is not null").append(" and m.type_cd='1'");
		if (StringUtils.isNotBlank(bisFloorId)) {
			sql.append(" and m.bis_floor_id=:bisFloorId");
		}
		sql.append(" group by m.bis_flat_id").append(" ) l on bf.bis_flat_id=l.bis_flat_id").append(" where bf.bis_project_id=:bisProjectId");
		if (StringUtils.isNotBlank(bisFloorId)) {
			sql.append(" and bf.bis_floor_id=:bisFloorId");
		}
		sql.append(" and not exists (select 1 from bis_flat_cont_rel r where r.bis_flat_id=bf.bis_flat_id)").append(" order by bf.flat_no");

		return sql.toString();
	}

	private String buildFlatQuerySql2() {

		StringBuffer sql = new StringBuffer();

		sql.append(" select bc.bis_cont_id,k.flat_no,").append(
				" l.must1,l.must2,l.must3,l.must4,l.must5,l.must6,l.must7,l.must8,l.must9,l.must10,l.must11,l.must12,l.must13").append(",bc.cont_no").append(
				" from bis_cont bc").append(" left join (").append(" select m.bis_cont_id,")
				.append(" sum(decode(m.charge_type_cd, '5', m.money, null)) must1,").append(" sum(decode(m.charge_type_cd, '6', m.money, null)) must2,")
				.append(" sum(decode(m.charge_type_cd, '7', m.money, null)) must3,").append(" sum(decode(m.charge_type_cd, '8', m.money, null)) must4,")
				.append(" sum(decode(m.charge_type_cd, '9', m.money, null)) must5,").append(" sum(decode(m.charge_type_cd, '10', m.money, null)) must6,")
				.append(" sum(decode(m.charge_type_cd, '11', m.money, null)) must7,").append(" sum(decode(m.charge_type_cd, '15', m.money, null)) must8,")
				.append(" sum(decode(m.charge_type_cd, '17', m.money, null)) must9,").append(" sum(decode(m.charge_type_cd, '19', m.money, null)) must10,")
				.append(" sum(decode(m.charge_type_cd, '20', m.money, null)) must11,").append(" sum(decode(m.charge_type_cd, '21', m.money, null)) must12,")
				.append(" sum(decode(m.charge_type_cd, '22', m.money, null)) must13").append(" from bis_must m")
				.append(" where m.bis_project_id=:bisProjectId").append(" and m.must_year=:year").append(" and m.must_month=:month").append(
						" and m.bis_cont_id is not null").append(" and m.type_cd='1'").append(" group by m.bis_cont_id").append(
						" ) l on bc.bis_cont_id=l.bis_cont_id").append(" left join (").append(
						" SELECT c.bis_cont_id, TRANSLATE(LTRIM(c.text, '/'), '*/', '*,') flat_no").append(" FROM (").append(
						" SELECT ROW_NUMBER() OVER(PARTITION BY b.bis_cont_id ORDER BY b.bis_cont_id, num DESC) rn, b.bis_cont_id, b.text").append(" FROM (")
				.append(" SELECT a.bis_cont_id, level num, SYS_CONNECT_BY_PATH(a.flat_no, '/') text").append(" FROM (").append(
						" select bc.bis_cont_id, bf.flat_no, ROW_NUMBER() OVER(PARTITION BY bc.bis_cont_id ORDER BY bc.bis_cont_id, bf.flat_no) x").append(
						" from bis_cont bc, bis_flat_cont_rel rel, bis_flat bf").append(" where bc.bis_cont_id=rel.bis_cont_id").append(
						" and rel.bis_flat_id=bf.bis_flat_id").append(" and bc.bis_project_id=:bisProjectId").append(" group by bc.bis_cont_id, bf.flat_no")
				.append(" ) a").append(" CONNECT BY a.bis_cont_id = PRIOR a.bis_cont_id AND a.x - 1 = PRIOR a.x").append(" ) b ) c").append(" WHERE c.rn = 1")
				.append(" ORDER BY c.bis_cont_id").append(" ) k on bc.bis_cont_id=k.bis_cont_id").append(" where bc.bis_project_id=:bisProjectId").append(
						" and exists (select 1 from bis_flat_cont_rel r join bis_flat f on r.bis_flat_id=f.bis_flat_id").append(
						" where r.bis_cont_id=bc.bis_cont_id");
		if (StringUtils.isNotBlank(bisFloorId)) {
			sql.append(" and f.bis_floor_id=:bisFloorId");
		}
		sql.append(")");
		return sql.toString();
	}

	private String buildMultiQuerySql() {

		StringBuffer sql = new StringBuffer();

		sql.append(" select bm.bis_multi_id,bm.multi_name,").append(
				" l.must1,l.must2,l.must3,l.must4,l.must5,l.must6,l.must7,l.must8,l.must9,l.must10,l.must11,l.must12,l.must13").append(" from bis_multi bm")
				.append(" left join (").append(" select m.bis_multi_id,").append(" sum(decode(m.charge_type_cd, '5', m.money, null)) must1,").append(
						" sum(decode(m.charge_type_cd, '6', m.money, null)) must2,").append(" sum(decode(m.charge_type_cd, '7', m.money, null)) must3,")
				.append(" sum(decode(m.charge_type_cd, '8', m.money, null)) must4,").append(" sum(decode(m.charge_type_cd, '9', m.money, null)) must5,")
				.append(" sum(decode(m.charge_type_cd, '10', m.money, null)) must6,").append(" sum(decode(m.charge_type_cd, '11', m.money, null)) must7,")
				.append(" sum(decode(m.charge_type_cd, '15', m.money, null)) must8,").append(" sum(decode(m.charge_type_cd, '17', m.money, null)) must9,")
				.append(" sum(decode(m.charge_type_cd, '19', m.money, null)) must10,").append(" sum(decode(m.charge_type_cd, '20', m.money, null)) must11,")
				.append(" sum(decode(m.charge_type_cd, '21', m.money, null)) must12,").append(" sum(decode(m.charge_type_cd, '22', m.money, null)) must13")
				.append(" from bis_must m").append(" where m.bis_project_id=:bisProjectId").append(" and m.must_year=:year").append(" and m.must_month=:month")
				.append(" and m.bis_multi_id is not null").append(" and m.type_cd='1'")
				// .append(" and m.floor_type='3'")
				.append(" group by m.bis_multi_id").append(" ) l on bm.bis_multi_id=l.bis_multi_id").append(" where bm.bis_project_id=:bisProjectId").append(
						" and not exists (select 1 from bis_multi_cont_rel r where r.bis_multi_id=bm.bis_multi_id)").append(" order by bm.multi_name");

		return sql.toString();
	}

	private String buildMultiQuerySql2() {

		StringBuffer sql = new StringBuffer();

		sql.append(" select bc.bis_cont_id,k.multi_name,").append(
				" l.must1,l.must2,l.must3,l.must4,l.must5,l.must6,l.must7,l.must8,l.must9,l.must10,l.must11,l.must12,l.must13").append(",bc.cont_no").append(
				" from bis_cont bc").append(" left join (").append(" select m.bis_cont_id,")
				.append(" sum(decode(m.charge_type_cd, '5', m.money, null)) must1,").append(" sum(decode(m.charge_type_cd, '6', m.money, null)) must2,")
				.append(" sum(decode(m.charge_type_cd, '7', m.money, null)) must3,").append(" sum(decode(m.charge_type_cd, '8', m.money, null)) must4,")
				.append(" sum(decode(m.charge_type_cd, '9', m.money, null)) must5,").append(" sum(decode(m.charge_type_cd, '10', m.money, null)) must6,")
				.append(" sum(decode(m.charge_type_cd, '11', m.money, null)) must7,").append(" sum(decode(m.charge_type_cd, '15', m.money, null)) must8,")
				.append(" sum(decode(m.charge_type_cd, '17', m.money, null)) must9,").append(" sum(decode(m.charge_type_cd, '19', m.money, null)) must10,")
				.append(" sum(decode(m.charge_type_cd, '20', m.money, null)) must11,").append(" sum(decode(m.charge_type_cd, '21', m.money, null)) must12,")
				.append(" sum(decode(m.charge_type_cd, '22', m.money, null)) must13").append(" from bis_must m")
				.append(" where m.bis_project_id=:bisProjectId").append(" and m.must_year=:year").append(" and m.must_month=:month").append(
						" and m.bis_cont_id is not null")
				.append(" and m.type_cd='1'")
				// .append(" and m.floor_type='3'")
				.append(" group by m.bis_cont_id").append(" ) l on bc.bis_cont_id=l.bis_cont_id").append(" left join (").append(
						" SELECT c.bis_cont_id, TRANSLATE(LTRIM(c.text, '/'), '*/', '*,') multi_name").append(" FROM (").append(
						" SELECT ROW_NUMBER() OVER(PARTITION BY b.bis_cont_id ORDER BY b.bis_cont_id, num DESC) rn, b.bis_cont_id, b.text").append(" FROM (")
				.append(" SELECT a.bis_cont_id, level num, SYS_CONNECT_BY_PATH(a.multi_name, '/') text").append(" FROM (").append(
						" select bc.bis_cont_id, bm.multi_name, ROW_NUMBER() OVER(PARTITION BY bc.bis_cont_id ORDER BY bc.bis_cont_id, bm.multi_name) x")
				.append(" from bis_cont bc, bis_multi_cont_rel rel, bis_multi bm").append(" where bc.bis_cont_id=rel.bis_cont_id").append(
						" and rel.bis_multi_id=bm.bis_multi_id").append(" and bc.bis_project_id=:bisProjectId").append(
						" group by bc.bis_cont_id, bm.multi_name").append(" ) a").append(
						" CONNECT BY a.bis_cont_id = PRIOR a.bis_cont_id AND a.x - 1 = PRIOR a.x").append(" ) b ) c").append(" WHERE c.rn = 1").append(
						" ORDER BY c.bis_cont_id").append(" ) k on bc.bis_cont_id=k.bis_cont_id").append(" where bc.bis_project_id=:bisProjectId").append(
						" and exists (select 1 from bis_multi_cont_rel r where r.bis_cont_id=bc.bis_cont_id)");

		return sql.toString();
	}

	private void setStoreListValue(List<Object[]> list) {
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = list.get(i);
			String contType = appDictTypeManager.getDictNameByCd(DictContants.BIS_CONT_SMALL_TYPE, (String) obj[6]);
			BisIncomeOtherVo vo = new BisIncomeOtherVo();
			vo.setId((String) obj[4]);
			vo.setName((String) obj[2]);
			vo.setContNo((String) obj[5]);
			vo.setContType(contType);
			vo.setStoreNos((String) obj[7]);
			vo.setBisShopId((String) obj[1]);
			vo.setMust1((BigDecimal) obj[8]);
			vo.setMust2((BigDecimal) obj[9]);
			vo.setMust3((BigDecimal) obj[10]);
			vo.setMust4((BigDecimal) obj[11]);
			vo.setMust5((BigDecimal) obj[12]);
			vo.setMust6((BigDecimal) obj[13]);
			vo.setMust7((BigDecimal) obj[14]);
			vo.setMust8((BigDecimal) obj[15]);
			vo.setMust9((BigDecimal) obj[16]);
			vo.setMust10((BigDecimal) obj[17]);
			vo.setMust11((BigDecimal) obj[18]);
			vo.setMust12((BigDecimal) obj[19]);
			vo.setMust13((BigDecimal) obj[20]);
			volist.add(vo);
		}
	}

	private void setFlatListValue(List<Object[]> list) {
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = list.get(i);
			BisIncomeOtherVo vo = new BisIncomeOtherVo();
			vo.setId((String) obj[0]);
			vo.setName((String) obj[1]);
			vo.setMust1((BigDecimal) obj[2]);
			vo.setMust2((BigDecimal) obj[3]);
			vo.setMust3((BigDecimal) obj[4]);
			vo.setMust4((BigDecimal) obj[5]);
			vo.setMust5((BigDecimal) obj[6]);
			vo.setMust6((BigDecimal) obj[7]);
			vo.setMust7((BigDecimal) obj[8]);
			vo.setMust8((BigDecimal) obj[9]);
			vo.setMust9((BigDecimal) obj[10]);
			vo.setMust10((BigDecimal) obj[11]);
			vo.setMust11((BigDecimal) obj[12]);
			vo.setMust12((BigDecimal) obj[13]);
			vo.setMust13((BigDecimal) obj[14]);
			if (obj.length == 16) {
				vo.setContNo((String) obj[15]);
			}
			volist.add(vo);
		}
	}

	private void setMultiListValue(List<Object[]> list) {
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = list.get(i);
			BisIncomeOtherVo vo = new BisIncomeOtherVo();
			vo.setId((String) obj[0]);
			vo.setName((String) obj[1]);
			vo.setMust1((BigDecimal) obj[2]);
			vo.setMust2((BigDecimal) obj[3]);
			vo.setMust3((BigDecimal) obj[4]);
			vo.setMust4((BigDecimal) obj[5]);
			vo.setMust5((BigDecimal) obj[6]);
			vo.setMust6((BigDecimal) obj[7]);
			vo.setMust7((BigDecimal) obj[8]);
			vo.setMust8((BigDecimal) obj[9]);
			vo.setMust9((BigDecimal) obj[10]);
			vo.setMust10((BigDecimal) obj[11]);
			vo.setMust11((BigDecimal) obj[12]);
			vo.setMust12((BigDecimal) obj[13]);
			vo.setMust13((BigDecimal) obj[14]);
			if (obj.length == 16) {
				vo.setContNo((String) obj[15]);
			}
			volist.add(vo);
		}
	}

	/*
	 * private String buildQuerySql() {
	 * 
	 * String idname = ""; String name = ""; String mustfloorStr = ""; String
	 * factfloorStr = ""; if("1".equals(floorType) &&
	 * StringUtils.isNotBlank(bisFloorId)) { idname = "bis_tenant_id"; name =
	 * "NVL(d.name_cn,d.name_en),"; mustfloorStr =
	 * " and mo.bis_floor_id=:bisFloorId"; factfloorStr =
	 * " and fo.bis_floor_id=:bisFloorId"; } else if("2".equals(floorType) &&
	 * StringUtils.isNotBlank(bisFloorId)) { idname = "bis_flat_id"; name =
	 * "d.flat_no,"; mustfloorStr = " and mo.bis_floor_id=:bisFloorId";
	 * factfloorStr = " and fo.bis_floor_id=:bisFloorId"; } else
	 * if("3".equals(floorType)) { idname = "bis_multi_id"; name =
	 * "d.multi_name,"; }
	 * 
	 * StringBuffer sql = new StringBuffer();
	 * sql.append("  select c.id,c.bis_store_nos,c.bis_shop_id,").append(name)
	 * .append
	 * (" c.quantity_must1,c.quantity_fact1,c.quantity_must2,c.quantity_fact2,")
	 * .append(
	 * " c.must1,c.fact1,c.must2,c.fact2,c.must3,c.fact3,c.must4,c.fact4,c.must5,c.fact5,"
	 * ).append(
	 * " c.must6,c.fact6,c.must7,c.fact7,c.must8,c.fact8,c.must9,c.fact9,c.must10,c.fact10,c.must11,c.fact11,c.must12,c.fact12,c.must13,c.fact13"
	 * ) .append(" from (").append(
	 * " select NVL(a.id,b.id) as id, NVL(a.bis_shop_id,b.bis_shop_id) as bis_shop_id,NVL(a.bis_store_nos,b.bis_store_nos) as bis_store_nos,NVL(a.sequence_no,b.sequence_no) as sequence_no,"
	 * ).append(
	 * " a.quantity_must1,b.quantity_fact1,a.quantity_must2,b.quantity_fact2,")
	 * .append(
	 * " a.must1,b.fact1,a.must2,b.fact2,a.must3,b.fact3,a.must4,b.fact4,a.must5,b.fact5,"
	 * ).append(
	 * " a.must6,b.fact6,a.must7,b.fact7,a.must8,b.fact8,a.must9,b.fact9,a.must10,b.fact10,a.must11,b.fact11,a.must12,b.fact12,a.must13,b.fact13"
	 * ) .append(" from (").append(" select mo.").append(idname).append(
	 * " as id,mo.bis_shop_id,mo.bis_store_nos,mo.sequence_no,").append(
	 * " sum(decode(mo.charge_type_cd, '5', mo.quantity, null)) quantity_must1,"
	 * ).append(
	 * " sum(decode(mo.charge_type_cd, '6', mo.quantity, null)) quantity_must2,"
	 * ) .append(" sum(decode(mo.charge_type_cd, '5', mo.money, null)) must1,")
	 * .append(" sum(decode(mo.charge_type_cd, '6', mo.money, null)) must2,")
	 * .append(" sum(decode(mo.charge_type_cd, '9', mo.money, null)) must3,")
	 * .append(" sum(decode(mo.charge_type_cd, '10', mo.money, null)) must4,")
	 * .append(" sum(decode(mo.charge_type_cd, '11', mo.money, null)) must5,")
	 * .append(" sum(decode(mo.charge_type_cd, '15', mo.money, null)) must6,")
	 * .append(" sum(decode(mo.charge_type_cd, '17', mo.money, null)) must7,")
	 * .append(" sum(decode(mo.charge_type_cd, '19', mo.money, null)) must8,")
	 * .append(" sum(decode(mo.charge_type_cd, '20', mo.money, null)) must9,")
	 * .append(" sum(decode(mo.charge_type_cd, '21', mo.money, null)) must10,")
	 * .append(" sum(decode(mo.charge_type_cd, '22', mo.money, null)) must11,")
	 * .append(" sum(decode(mo.charge_type_cd, '7', mo.money, null)) must12,")
	 * .append(" sum(decode(mo.charge_type_cd, '8', mo.money, null)) must13")
	 * .append(
	 * " from bis_must_other mo where mo.bis_project_id=:bisProjectId and mo.must_year=:year and mo.must_month=:month"
	 * ) .append(mustfloorStr)
	 * .append(" and mo.").append(idname).append(" is not null")
	 * .append(" group by mo."
	 * ).append(idname).append(",mo.bis_shop_id,mo.bis_store_nos,mo.sequence_no"
	 * ) .append(" ) a full join") .append(" (")
	 * .append(" select fo.").append(idname).append(
	 * " as id,fo.bis_shop_id,fo.bis_store_id as bis_store_nos,fo.sequence_no,")
	 * .append(
	 * " sum(decode(fo.charge_type_cd, '5', fo.quantity, null)) quantity_fact1,"
	 * ).append(
	 * " sum(decode(fo.charge_type_cd, '6', fo.quantity, null)) quantity_fact2,"
	 * ) .append(" sum(decode(fo.charge_type_cd, '5', fo.money, null)) fact1,")
	 * .append(" sum(decode(fo.charge_type_cd, '6', fo.money, null)) fact2,")
	 * .append(" sum(decode(fo.charge_type_cd, '9', fo.money, null)) fact3,")
	 * .append(" sum(decode(fo.charge_type_cd, '10', fo.money, null)) fact4,")
	 * .append(" sum(decode(fo.charge_type_cd, '11', fo.money, null)) fact5,")
	 * .append(" sum(decode(fo.charge_type_cd, '15', fo.money, null)) fact6,")
	 * .append(" sum(decode(fo.charge_type_cd, '17', fo.money, null)) fact7,")
	 * .append(" sum(decode(fo.charge_type_cd, '19', fo.money, null)) fact8,")
	 * .append(" sum(decode(fo.charge_type_cd, '20', fo.money, null)) fact9,")
	 * .append(" sum(decode(fo.charge_type_cd, '21', fo.money, null)) fact10,")
	 * .append(" sum(decode(fo.charge_type_cd, '22', fo.money, null)) fact11,")
	 * .append(" sum(decode(fo.charge_type_cd, '7', fo.money, null)) fact12,")
	 * .append(" sum(decode(fo.charge_type_cd, '8', fo.money, null)) fact13")
	 * .append(
	 * " from bis_fact_other fo where fo.bis_project_id=:bisProjectId and fo.fact_year=:year and fo.fact_month=:month"
	 * ) .append(factfloorStr)
	 * .append(" and fo.").append(idname).append(" is not null ")
	 * .append(" group by fo."
	 * ).append(idname).append(",fo.bis_shop_id,fo.bis_store_id,fo.sequence_no")
	 * .append(" ) b on a.id=b.id") .append(" ) c"); if("1".equals(floorType)) {
	 * sql.append(" left join bis_shop d on c.bis_shop_id=d.bis_shop_id"); }
	 * else if("2".equals(floorType)) {
	 * sql.append(" left join bis_flat d on c.id=d.bis_flat_id"); } else
	 * if("3".equals(floorType)) {
	 * sql.append(" left join bis_multi d on c.id=d.bis_multi_id"); }
	 * sql.append(" order by c.sequence_no"); return sql.toString(); }
	 */

	/**
	 * 下载模板
	 */
	public String exportTemplate() throws Exception {

		bisProjectName = bisProjectManager.getEntity(bisProjectId).getProjectName();

		excelFile = buildTemplateExcel();
		excelFileName = new String(excelFileName.getBytes("GBK"), "ISO8859-1");

		return "export";
	}

	/**
	 * 导出现金流模板
	 * 
	 * @return
	 */
	public String exportCashExcel() throws Exception {
		bisProjectName = bisProjectManager.getEntity(bisProjectId).getProjectName();

		excelFile = buildCashExcel();
		excelFileName = new String(excelFileName.getBytes("GBK"), "ISO8859-1");
		return "export";
	}

	private InputStream buildTemplateExcel() {

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		WritableWorkbook wbook = JXLExcelUtil.createWorkBook(bos);

		if (wbook != null) {
			try {
				populateData(wbook);
			} catch (Exception e) {
				e.printStackTrace();
			}
			JXLExcelUtil.closeWorkBook(wbook);
		}

		byte[] data = bos.toByteArray();
		try {
			bos.close();
		} catch (IOException e) {
			throw new RuntimeException("关闭输出流失败", e);
		}

		InputStream inputStream = new ByteArrayInputStream(data);
		return inputStream;
	}

	private InputStream buildCashExcel() {

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		WritableWorkbook wbook = JXLExcelUtil.createWorkBook(bos);

		if (wbook != null) {
			try {
				cashExcelData(wbook);
			} catch (Exception e) {
				e.printStackTrace();
			}
			JXLExcelUtil.closeWorkBook(wbook);
		}

		byte[] data = bos.toByteArray();
		try {
			bos.close();
		} catch (IOException e) {
			throw new RuntimeException("关闭输出流失败", e);
		}

		InputStream inputStream = new ByteArrayInputStream(data);
		return inputStream;
	}

	private void cashExcelData(WritableWorkbook wbook) throws Exception {
		String floorTypeName = appDictTypeManager.getDictNameByCd(DictContants.BIS_CONT_BIG_TYPE, floorType);

		excelFileName = "实收模板-" + bisProjectName + "-" + floorTypeName + "(" + feeDate + ")";

		WritableSheet wsheet0 = wbook.createSheet(floorTypeName, 0);
		wsheet0.getSettings().setHorizontalFreeze(6);
		wsheet0.getSettings().setVerticalFreeze(3);
		wsheet0.getSettings().setProtected(true);
		// aa
		if (DictContants.BIS_CONT_TYPE_STORE.equals(floorType)) {
			initStoreCashSheet(wsheet0);
		} else if (DictContants.BIS_CONT_TYPE_FLAT.equals(floorType)) {
			initFlatCashSheet(wsheet0);
		} else if (DictContants.BIS_CONT_TYPE_MULTI.equals(floorType)) {
			initMultiCashShee(wsheet0);
		} else {

		}
	}

	private void populateData(WritableWorkbook wbook) throws Exception {

		String floorTypeName = appDictTypeManager.getDictNameByCd(DictContants.BIS_CONT_BIG_TYPE, floorType);
		/*
		 * if(StringUtils.isBlank(moduleType)){ moduleType="1"; }
		 */
		if ("2".equals(moduleType)) {
			excelFileName = "实收模板-" + bisProjectName + "-" + floorTypeName + "(" + feeDate + ")";
		} else {
			excelFileName = "应收模板-" + bisProjectName + "-" + floorTypeName + "(" + feeDate + ")";
		}

		WritableSheet wsheet0 = wbook.createSheet(floorTypeName, 0);
		wsheet0.getSettings().setHorizontalFreeze(6);
		wsheet0.getSettings().setVerticalFreeze(3);
		wsheet0.getSettings().setProtected(true);
		if (DictContants.BIS_CONT_TYPE_STORE.equals(floorType)) {
			initStoreSheet(wsheet0);
		} else if (DictContants.BIS_CONT_TYPE_FLAT.equals(floorType)) {
			initFlatSheet(wsheet0);
		} else if (DictContants.BIS_CONT_TYPE_MULTI.equals(floorType)) {
			initMultiSheet(wsheet0);
		} else {
			// WritableSheet wsheet0 = wbook.createSheet("商铺", 0);
			// WritableSheet wsheet1 = wbook.createSheet("公寓", 1);
			// WritableSheet wsheet2 =wbook.createSheet("多经", 2);
		}
	}

	/**
	 * 现金流月实收（商铺模板）
	 * 
	 * @param wsheet
	 * @throws Exception
	 */
	public void initStoreCashSheet(WritableSheet wsheet) throws Exception {
		if (feeTypeId == null && feeTypeId.length() <= 0)
			return;
		WritableFont font_Bold_10 = new WritableFont(WritableFont.createFont("Times New Roman"), 10);
		font_Bold_10.setBoldStyle(WritableFont.BOLD);
		WritableFont font_Red_11 = new WritableFont(WritableFont.createFont("Times New Roman"), 11);
		font_Red_11.setColour(Colour.RED);
		WritableFont font_Bold_14 = new WritableFont(WritableFont.createFont("Times New Roman"), 14);
		font_Bold_14.setBoldStyle(WritableFont.BOLD);

		// 输入框样式
		WritableCellFormat format_unlock = new WritableCellFormat(NumberFormats.FORMAT3);
		format_unlock.setLocked(false); // 不加锁
		format_unlock.setAlignment(Alignment.RIGHT);

		// 日期标题样式
		WritableCellFormat format_label2 = new WritableCellFormat(font_Red_11);
		format_label2.setAlignment(Alignment.RIGHT);
		format_label2.setVerticalAlignment(VerticalAlignment.CENTRE);
		// format_label2.setBackground(Colour.GRAY_25);

		// 日期样式
		WritableCellFormat format_lock_date = new WritableCellFormat(new DateFormat("yyyy-MM"));
		format_lock_date.setLocked(true); // 不加锁
		format_lock_date.setAlignment(Alignment.LEFT);
		format_lock_date.setVerticalAlignment(VerticalAlignment.CENTRE);
		// format_unlock_date.setBackground(Colour.YELLOW);

		// 普通框样式
		WritableCellFormat format_label = new WritableCellFormat();
		format_label.setAlignment(Alignment.LEFT);

		// 一级标题样式
		WritableCellFormat format_head1 = new WritableCellFormat(font_Bold_14);
		format_head1.setAlignment(Alignment.CENTRE); // 水平对齐
		format_head1.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直对齐
		format_head1.setBackground(Colour.GRAY_25);

		// 二级标题样式
		WritableCellFormat format_head2 = new WritableCellFormat(font_Bold_10);
		format_head2.setAlignment(Alignment.CENTRE);
		format_head2.setVerticalAlignment(VerticalAlignment.CENTRE);

		String bisFloorNo = "";
		if (StringUtils.isNotBlank(bisFloorId)) {
			BisFloor bisFloor = bisFloorManager.getEntity(bisFloorId);
			bisFloorNo = bisFloor.getBuildingNum() + "~" + bisFloor.getFloorNum();
		}

		wsheet.addCell(new Label(0, 0, bisProjectName + "(现金流日期：" + feeDate + ")", format_head1));
		wsheet.addCell(new Label(0, 1, "", format_head2));
		wsheet.mergeCells(0, 1, 0, 2);
		wsheet.addCell(new Label(1, 1, "楼层号", format_head2));
		wsheet.mergeCells(1, 1, 1, 2);
		wsheet.addCell(new Label(2, 1, "商家", format_head2));
		wsheet.mergeCells(2, 1, 2, 2);
		wsheet.addCell(new Label(3, 1, "合同编号", format_head2));
		wsheet.mergeCells(3, 1, 3, 2);
		wsheet.addCell(new Label(4, 1, "合同类型", format_head2));
		wsheet.mergeCells(4, 1, 4, 2);
		wsheet.addCell(new Label(5, 1, "商铺", format_head2));
		wsheet.mergeCells(5, 1, 5, 2);

		// 每一列宽度
		wsheet.setColumnView(0, 0);
		if (StringUtils.isBlank(bisFloorId)) {
			wsheet.setColumnView(1, 0);
		} else {
			wsheet.setColumnView(1, 15);
		}
		wsheet.setColumnView(2, 18);
		wsheet.setColumnView(3, 15);
		wsheet.setColumnView(4, 10);
		wsheet.setColumnView(5, 28);

		int col = 5;
		// 比较权责月份
		int compMonth = compareMonths(authDate1, authDate2);
		String authDate = authDate1;
		// 得到每个月份的数组
		String[] authMonth = new String[compMonth + 1];
		authMonth[0] = authDate;
		for (int i = 0; i < compMonth; i++) {
			String[] str = authDate.split("-");
			Long year = DateOperator.getNextYear(Long.valueOf(str[0]), str[1]);
			String month = DateOperator.getNextMonth(Long.valueOf(str[0]), str[1]);
			authMonth[i + 1] = year + "-" + month;
			authDate = authMonth[i + 1];
		}
		authMonth[compMonth] = authDate2;

		String[] feeType = feeTypeId.split(",");
		if (feeTypeId != null && feeTypeId.length() > 0) {

			String feeTypeName;
			for (int i = 0; i < feeType.length; i++) {
				feeTypeName = DictMapUtil.getMapChargeTypeNew().get(feeType[i]);
				wsheet.addCell(new Label(++col, 1, feeTypeName, format_head2));
				wsheet.addCell(new Label(col, 2, feeType[i], format_head2));
				// 费用类别占compMonth*两格：每单元格一格为费用，另一格为日期

				wsheet.mergeCells(col, 1, col + (authMonth.length - 1) * 2 + 1, 1);
				// 在实收金额上批量填写费用类别数据
				for (int j = 1; j < authMonth.length; j++) {
					wsheet.addCell(new Label(col + 2 * j, 2, feeType[i], format_head2));
				}
				wsheet.setColumnView(col, 12);
				col = col + (authMonth.length - 1) * 2 + 1;
				wsheet.setColumnView(col, 12);
			}
		}
		wsheet.setRowView(0, 700);
		wsheet.setRowView(1, 600);
		wsheet.setRowView(2, 1);

		wsheet.mergeCells(0, 0, 5, 0);
		wsheet.addCell(new Label(6, 0, "请于对应的权责月份中按费用类别填写金额，例：租金为1000元，对应下填写为：1000", format_label2));
		wsheet.mergeCells(6, 0, 12, 0);
		// 在第3列的输入提示：存放金额、权责日
		if (feeTypeId != null && feeTypeId.length() > 0) {
			int col2 = 5;
			if (feeType.length > 0) {
				for (int i = 0; i < (feeType.length * 2 * authMonth.length); i++) {
					if (col2 % 2 == 1) {
						wsheet.addCell(new Label(++col2, 3, "金额", format_head2));
					} else {
						wsheet.addCell(new Label(++col2, 3, "权责月", format_head2));
					}
				}
			}
		}

		List<Object[]> storeList = bisIncomeOtherManager.getStoreList(bisProjectId, bisFloorId);

		for (int i = 0; i < storeList.size(); i++) {
			Object[] obj = storeList.get(i);
			String contType = appDictTypeManager.getDictNameByCd(DictContants.BIS_CONT_SMALL_TYPE, (String) obj[6]);
			String hiddenIds = floorType + ";" + bisProjectId + ";" + bisFloorId + ";" + (String) obj[0] + ";" + (String) obj[1] + ";" + (String) obj[4];

			wsheet.addCell(new Label(0, i + 4, hiddenIds, format_label));
			wsheet.addCell(new Label(1, i + 4, bisFloorNo, format_label));
			wsheet.addCell(new Label(2, i + 4, (String) obj[2], format_label));
			wsheet.addCell(new Label(3, i + 4, (String) obj[5], format_label));
			wsheet.addCell(new Label(4, i + 4, contType, format_label));
			wsheet.addCell(new Label(5, i + 4, (String) obj[7]));
			int col2 = 5;
			if (feeTypeId != null && feeTypeId.length() > 0) {
				int compInt = 0;
				if (feeType.length > 0) {
					for (int j = 0; j < (feeType.length * 2 * authMonth.length); j++) {
						if (compInt >= authMonth.length) {
							compInt = 0;
						}
						if (col2 % 2 == 1) {
							// 若为单数，则为金额格式
							wsheet.addCell(new Label(++col2, i + 4, "", format_unlock));
						} else {
							// 否则为日期格式
							wsheet.addCell(new Label(++col2, i + 4, authMonth[compInt], format_lock_date));
							compInt++;
						}
					}
				}
			}
			wsheet.setRowView(i + 3, 500);
		}
	}

	/**
	 * 现金流月实收（公寓模板）
	 * 
	 * @param wsheet
	 * @throws Exception
	 */
	public void initFlatCashSheet(WritableSheet wsheet) throws Exception {
		if (feeTypeId == null && feeTypeId.length() <= 0)
			return;
		WritableFont font_Bold_10 = new WritableFont(WritableFont.createFont("Times New Roman"), 10);
		font_Bold_10.setBoldStyle(WritableFont.BOLD);
		WritableFont font_Red_11 = new WritableFont(WritableFont.createFont("Times New Roman"), 11);
		font_Red_11.setColour(Colour.RED);
		WritableFont font_Bold_14 = new WritableFont(WritableFont.createFont("Times New Roman"), 14);
		font_Bold_14.setBoldStyle(WritableFont.BOLD);

		// 输入框样式
		WritableCellFormat format_unlock = new WritableCellFormat(NumberFormats.FORMAT3);
		format_unlock.setLocked(false); // 不加锁
		format_unlock.setAlignment(Alignment.RIGHT);

		// 日期标题样式
		WritableCellFormat format_label2 = new WritableCellFormat(font_Red_11);
		format_label2.setAlignment(Alignment.RIGHT);
		format_label2.setVerticalAlignment(VerticalAlignment.CENTRE);
		// format_label2.setBackground(Colour.GRAY_25);

		// 日期样式
		WritableCellFormat format_lock_date = new WritableCellFormat(new DateFormat("yyyy-MM"));
		format_lock_date.setLocked(true); // 不加锁
		format_lock_date.setAlignment(Alignment.LEFT);
		format_lock_date.setVerticalAlignment(VerticalAlignment.CENTRE);
		// format_unlock_date.setBackground(Colour.YELLOW);

		// 普通框样式
		WritableCellFormat format_label = new WritableCellFormat();
		format_label.setAlignment(Alignment.LEFT);

		// 一级标题样式
		WritableCellFormat format_head1 = new WritableCellFormat(font_Bold_14);
		format_head1.setAlignment(Alignment.CENTRE); // 水平对齐
		format_head1.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直对齐
		format_head1.setBackground(Colour.GRAY_25);

		// 二级标题样式
		WritableCellFormat format_head2 = new WritableCellFormat(font_Bold_10);
		format_head2.setAlignment(Alignment.CENTRE);
		format_head2.setVerticalAlignment(VerticalAlignment.CENTRE);

		String bisFloorNo = "";
		if (StringUtils.isNotBlank(bisFloorId)) {
			BisFloor bisFloor = bisFloorManager.getEntity(bisFloorId);
			bisFloorNo = bisFloor.getBuildingNum() + "~" + bisFloor.getFloorNum();
		}
		/*
		 * wsheet.addCell(new Label(0, 0, bisProjectName + "(现金流日期：" + feeDate +
		 * ")", format_head1)); wsheet.addCell(new Label(0, 1, "",
		 * format_head2)); wsheet.mergeCells(0, 1, 0, 2); wsheet.addCell(new
		 * Label(1, 1, "楼层号", format_head2)); wsheet.mergeCells(1, 1, 1, 2);
		 * wsheet.addCell(new Label(2, 1, "商家", format_head2));
		 * wsheet.mergeCells(2, 1, 2, 2); wsheet.addCell(new Label(3, 1, "合同编号",
		 * format_head2)); wsheet.mergeCells(3, 1, 3, 2); wsheet.addCell(new
		 * Label(4, 1, "合同类型", format_head2)); wsheet.mergeCells(4, 1, 4, 2);
		 * wsheet.addCell(new Label(5, 1, "商铺", format_head2));
		 * wsheet.mergeCells(5, 1, 5, 2);
		 */
		wsheet.addCell(new Label(0, 0, bisProjectName + "(现金流日期：" + feeDate + ")", format_head1));
		wsheet.addCell(new Label(0, 1, "", format_head2));
		wsheet.mergeCells(0, 1, 0, 2);
		wsheet.addCell(new Label(1, 1, "楼层号", format_head2));
		wsheet.mergeCells(1, 1, 1, 2);
		wsheet.addCell(new Label(2, 1, "合同编号", format_head2));
		wsheet.mergeCells(2, 1, 2, 2);
		wsheet.addCell(new Label(3, 1, "公寓编号", format_head2));
		wsheet.mergeCells(3, 1, 3, 2);
		wsheet.addCell(new Label(4, 1, "", format_head2));
		wsheet.mergeCells(4, 1, 4, 2);
		wsheet.addCell(new Label(5, 1, "", format_head2));
		wsheet.mergeCells(5, 1, 5, 2);
		// 每一列宽度
		wsheet.setColumnView(0, 0);
		if (StringUtils.isBlank(bisFloorId)) {
			wsheet.setColumnView(1, 0);
		} else {
			wsheet.setColumnView(1, 15);
		}
		wsheet.setColumnView(2, 18);
		wsheet.setColumnView(3, 15);
		wsheet.setColumnView(4, 10);
		wsheet.setColumnView(5, 10);
		int col = 5;
		// 比较权责月份
		int compMonth = compareMonths(authDate1, authDate2);
		String authDate = authDate1;
		// 得到每个月份的数组
		String[] authMonth = new String[compMonth + 1];
		authMonth[0] = authDate;
		for (int i = 0; i < compMonth; i++) {
			String[] str = authDate.split("-");
			Long year = DateOperator.getNextYear(Long.valueOf(str[0]), str[1]);
			String month = DateOperator.getNextMonth(Long.valueOf(str[0]), str[1]);
			authMonth[i + 1] = year + "-" + month;
			authDate = authMonth[i + 1];
		}
		authMonth[compMonth] = authDate2;
		String[] feeType = feeTypeId.split(",");
		if (feeTypeId != null && feeTypeId.length() > 0) {

			String feeTypeName;
			for (int i = 0; i < feeType.length; i++) {
				feeTypeName = DictMapUtil.getMapChargeTypeNew().get(feeType[i]);
				wsheet.addCell(new Label(++col, 1, feeTypeName, format_head2));
				wsheet.addCell(new Label(col, 2, feeType[i], format_head2));
				// 费用类别占compMonth*两格：每单元格一格为费用，另一格为日期
				wsheet.mergeCells(col, 1, col + (authMonth.length - 1) * 2 + 1, 1);
				// 在实收金额上批量填写费用类别数据
				for (int j = 1; j < authMonth.length; j++) {
					wsheet.addCell(new Label(col + 2 * j, 2, feeType[i], format_head2));
				}
				wsheet.setColumnView(col, 12);
				col = col + (authMonth.length - 1) * 2 + 1;
				wsheet.setColumnView(col, 12);
			}
		}
		wsheet.setRowView(0, 700);
		wsheet.setRowView(1, 600);
		wsheet.setRowView(2, 1);

		wsheet.mergeCells(0, 0, 5, 0);
		wsheet.addCell(new Label(6, 0, "请于对应的权责月份中按费用类别填写金额，例：租金为1000元，对应下填写为：1000", format_label2));
		wsheet.mergeCells(6, 0, 12, 0);
		// 在第3列的输入提示：存放金额、权责日
		if (feeTypeId != null && feeTypeId.length() > 0) {
			int col2 = 5;
			if (feeType.length > 0) {
				for (int i = 0; i < (feeType.length * 2 * authMonth.length); i++) {
					if (col2 % 2 == 1) {
						wsheet.addCell(new Label(++col2, 3, "金额", format_head2));
					} else {
						wsheet.addCell(new Label(++col2, 3, "权责月", format_head2));
					}
				}
			}
		}

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("bisProjectId", bisProjectId);
		param.put("bisFloorId", bisFloorId);
		// 无合同签订的公寓数据
		StringBuffer hql = new StringBuffer("from BisFlat bf where bf.bisProjectId=:bisProjectId");
		hql.append(" and not exists (select 1 from BisFlatContRel r where r.bisFlat.bisFlatId=bf.bisFlatId)");
		if (StringUtils.isNotBlank(bisFloorId)) {
			hql.append(" and bf.bisFloor.bisFloorId=:bisFloorId");
		}
		hql.append(" order by bf.flatNo");
		List<BisFlat> flatList = bisFlatManager.find(hql.toString(), param);
		// 找出有签订合同的公寓数据
		StringBuffer hql2 = new StringBuffer("from BisCont bc where bc.bisProjectId=:bisProjectId");
		hql2.append(" and exists (select 1 from BisFlatContRel r where r.bisCont.bisContId=bc.bisContId");
		if (StringUtils.isNotBlank(bisFloorId)) {
			hql2.append(" and r.bisFlat.bisFloor.bisFloorId=:bisFloorId");
		}
		hql2.append(" ) order by bc.contNo");
		List<BisCont> contList = bisContManager.find(hql2.toString(), param);

		int row = 4;
		for (int i = 0; i < contList.size(); i++) {
			BisCont bisCont = contList.get(i);
			StringBuffer flatNos = new StringBuffer();
			for (BisFlatContRel rel : bisCont.getBisFlatContRels()) {
				flatNos.append(rel.getBisFlat().getFlatNo()).append(",");
				if (null != rel.getBisFlat().getBisFloor().getBuildingNum()) {
					bisFloorNo = rel.getBisFlat().getBisFloor().getBuildingNum();
				}
			}
			String hiddenIds = floorType + ";" + bisProjectId + ";" + bisFloorId + ";" + bisCont.getBisContId() + ";0";
			wsheet.addCell(new Label(0, row, hiddenIds, format_label));
			wsheet.addCell(new Label(1, row, bisFloorNo, format_label));
			wsheet.addCell(new Label(2, row, bisCont.getContNo(), format_label));
			wsheet.addCell(new Label(3, row, flatNos.substring(0, flatNos.length() - 1), format_label));
			wsheet.addCell(new Label(4, row, "", format_label));
			wsheet.addCell(new Label(5, row, "", format_label));
			int col2 = 5;
			if (feeTypeId != null && feeTypeId.length() > 0) {
				int compInt = 0;
				if (feeType.length > 0) {
					for (int j = 0; j < (feeType.length * 2 * authMonth.length); j++) {
						if (compInt >= authMonth.length) {
							compInt = 0;
						}
						if (col2 % 2 == 1) {
							// 若为单数，则为金额格式
							wsheet.addCell(new Label(++col2, i + 4, "", format_unlock));
						} else {
							// 否则为日期格式
							wsheet.addCell(new Label(++col2, i + 4, authMonth[compInt], format_lock_date));
							compInt++;
						}
					}
				}
			}
			wsheet.setRowView(row++, 500);
		}
		for (int i = 0; i < flatList.size(); i++) {
			BisFlat bisFlat = flatList.get(i);
			String hiddenIds = floorType + ";" + bisProjectId + ";" + bisFloorId + ";" + bisFlat.getBisFlatId() + ";1";
			wsheet.addCell(new Label(0, row, hiddenIds, format_label));
			wsheet.addCell(new Label(1, row, bisFloorNo, format_label));
			wsheet.addCell(new Label(2, row, "", format_label));
			wsheet.addCell(new Label(3, row, bisFlat.getFlatNo(), format_label));
			wsheet.addCell(new Label(4, row, "", format_label));
			wsheet.addCell(new Label(5, row, "", format_label));
			int col2 = 5;
			if (feeTypeId != null && feeTypeId.length() > 0) {
				int compInt = 0;
				if (feeType.length > 0) {
					for (int j = 0; j < (feeType.length * 2 * authMonth.length); j++) {
						if (compInt >= authMonth.length) {
							compInt = 0;
						}
						if (col2 % 2 == 1) {
							// 若为单数，则为金额格式
							wsheet.addCell(new Label(++col2, i + 4, "", format_unlock));
						} else {
							// 否则为日期格式
							wsheet.addCell(new Label(++col2, i + 4, authMonth[compInt], format_lock_date));
							compInt++;
						}
					}
				}
			}
			wsheet.setRowView(row++, 500);
		}

	}

	/**
	 * 现金流多经实收 模板
	 * @author qilb 4/28/2012
	 * @param wsheet
	 * @throws Exception
	 */
	public void initMultiCashShee(WritableSheet wsheet) throws Exception {

		WritableFont font_Bold_10 = new WritableFont(WritableFont.createFont("Times New Roman"), 10);
		font_Bold_10.setBoldStyle(WritableFont.BOLD);
		WritableFont font_Red_11 = new WritableFont(WritableFont.createFont("Times New Roman"), 11);
		font_Red_11.setColour(Colour.RED);
		WritableFont font_Bold_14 = new WritableFont(WritableFont.createFont("Times New Roman"), 14);
		font_Bold_14.setBoldStyle(WritableFont.BOLD);

		// 输入框样式
		WritableCellFormat format_unlock = new WritableCellFormat(NumberFormats.FORMAT3);
		format_unlock.setLocked(false); // 不加锁
		format_unlock.setAlignment(Alignment.RIGHT);

		// 日期标题样式
		WritableCellFormat format_label2 = new WritableCellFormat(font_Red_11);
		format_label2.setAlignment(Alignment.RIGHT);
		format_label2.setVerticalAlignment(VerticalAlignment.CENTRE);
		// format_label2.setBackground(Colour.GRAY_25);

		// 日期样式
		WritableCellFormat format_unlock_date = new WritableCellFormat(new DateFormat("yyyy-MM-dd"));
		format_unlock_date.setLocked(false); // 不加锁
		format_unlock_date.setAlignment(Alignment.LEFT);
		format_unlock_date.setVerticalAlignment(VerticalAlignment.CENTRE);
		// format_unlock_date.setBackground(Colour.YELLOW);

		// 日期样式
		WritableCellFormat format_lock_date = new WritableCellFormat(new DateFormat("yyyy-MM"));
		format_lock_date.setLocked(true); // 不加锁
		format_lock_date.setAlignment(Alignment.LEFT);
		format_lock_date.setVerticalAlignment(VerticalAlignment.CENTRE);
		// format_unlock_date.setBackground(Colour.YELLOW);

		// 普通框样式
		WritableCellFormat format_label = new WritableCellFormat(NumberFormats.TEXT);
		format_label.setAlignment(Alignment.LEFT);

		// 一级标题样式
		WritableCellFormat format_head1 = new WritableCellFormat(font_Bold_14);
		format_head1.setAlignment(Alignment.CENTRE); // 水平对齐
		format_head1.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直对齐
		format_head1.setBackground(Colour.GRAY_25);

		// 二级标题样式
		WritableCellFormat format_head2 = new WritableCellFormat(font_Bold_10);
		format_head2.setAlignment(Alignment.CENTRE);
		format_head2.setVerticalAlignment(VerticalAlignment.CENTRE);

		Map<String, String> chargeTypeMap = DictMapUtil.getMapChargeTypeNew();

		wsheet.addCell(new Label(0, 0, bisProjectName + "(现金流日期：" + feeDate + ")", format_head1));

		wsheet.addCell(new Label(0, 1, "", format_head2));
		wsheet.mergeCells(0, 1, 0, 2);
		wsheet.addCell(new Label(1, 1, "合同编号", format_head2));
		wsheet.mergeCells(1, 1, 1, 2);
		wsheet.addCell(new Label(2, 1, "多经编号", format_head2));
		wsheet.mergeCells(2, 1, 2, 2);
		wsheet.addCell(new Label(3, 1, "承租方", format_head2));
		wsheet.mergeCells(3, 1, 3, 2);
		wsheet.addCell(new Label(4, 1, "", format_head2));
		wsheet.mergeCells(4, 1, 4, 2);
		wsheet.addCell(new Label(5, 1, "", format_head2));
		wsheet.mergeCells(5, 1, 5, 2);

		// 每一列宽度
		wsheet.setColumnView(0, 0);
		wsheet.setColumnView(1, 25);
		wsheet.setColumnView(2, 18);
		wsheet.setColumnView(3, 15);
		wsheet.setColumnView(4, 10);
		wsheet.setColumnView(5, 10);

		int col = 5;
		// 比较权责月份
		int compMonth = compareMonths(authDate1, authDate2);
		String authDate = authDate1;
		// 得到每个月份的数组
		String[] authMonth = new String[compMonth + 1];
		authMonth[0] = authDate;
		for (int i = 0; i < compMonth; i++) {
			String[] str = authDate.split("-");
			Long year = DateOperator.getNextYear(Long.valueOf(str[0]), str[1]);
			String month = DateOperator.getNextMonth(Long.valueOf(str[0]), str[1]);
			authMonth[i + 1] = year + "-" + month;
			authDate = authMonth[i + 1];
		}
		authMonth[compMonth] = authDate2;
		String[] feeType = feeTypeId.split(",");
		if (feeTypeId != null && feeTypeId.length() > 0) {

			String feeTypeName;
			for (int i = 0; i < feeType.length; i++) {
				feeTypeName = DictMapUtil.getMapChargeTypeNew().get(feeType[i]);
				wsheet.addCell(new Label(++col, 1, feeTypeName, format_head2));
				wsheet.addCell(new Label(col, 2, feeType[i], format_head2));
				// 费用类别占compMonth*两格：每单元格一格为费用，另一格为日期
				wsheet.mergeCells(col, 1, col + (authMonth.length - 1) * 2 + 1, 1);
				// 在实收金额上批量填写费用类别数据
				for (int j = 1; j < authMonth.length; j++) {
					wsheet.addCell(new Label(col + 2 * j, 2, feeType[i], format_head2));
				}
				wsheet.setColumnView(col, 12);
				col = col + (authMonth.length - 1) * 2 + 1;
				wsheet.setColumnView(col, 12);
			}
		}
		
		
		wsheet.setRowView(0, 700);
		wsheet.setRowView(1, 600);
		wsheet.setRowView(2, 1);

		wsheet.mergeCells(0, 0, 5, 0);
		wsheet.addCell(new Label(6, 0, "请于对应的权责月份中按费用类别填写金额，例：租金为1000元，对应下填写为：1000", format_label2));
		wsheet.mergeCells(6, 0, 12, 0);
		// 在第3列的输入提示：存放金额、权责日
		if (feeTypeId != null && feeTypeId.length() > 0) {
			int col2 = 5;
			if (feeType.length > 0) {
				for (int i = 0; i < (feeType.length * 2 * authMonth.length); i++) {
					if (col2 % 2 == 1) {
						wsheet.addCell(new Label(++col2, 3, "金额", format_head2));
					} else {
						wsheet.addCell(new Label(++col2, 3, "权责月", format_head2));
					}
				}
			}
		}

		/*
		 * wsheet.addCell(new Label(10, 0, "", format_unlock_date));
		 * wsheet.mergeCells(10, 0, 11, 0);
		 */
		// wsheet.mergeCells(0, 0, col, 0);

		String hql = "from BisMulti bm where bisProjectId=? and not exists (select 1 from BisMultiContRel r where r.bisMulti.bisMultiId=bm.bisMultiId)";
		List<BisMulti> multiList = bisMultiManager.find(hql, bisProjectId);

		String hql2 = "from BisCont bc where bc.bisProjectId=? and exists (select 1 from BisMultiContRel r where r.bisCont.bisContId=bc.bisContId)";
		List<BisCont> contList = bisContManager.find(hql2, bisProjectId);

		int row = 4;
		for (int i = 0; i < contList.size(); i++) {
			BisCont bisCont = contList.get(i);
			StringBuffer multiNames = new StringBuffer();
			for (BisMultiContRel rel : bisCont.getBisMultiContRels()) {
				multiNames.append(rel.getBisMulti().getMultiName()).append(",");
			}
			// 承租方
			if (bisCont.getBisContMultis() != null && bisCont.getBisContMultis().size() > 0) {
				wsheet.addCell(new Label(3, row, bisCont.getBisContMultis().get(0).getRenterName(), format_label));
			}
			String hiddenIds = floorType + ";" + bisProjectId + ";" + bisCont.getBisContId() + ";0";
			wsheet.addCell(new Label(0, row, hiddenIds, format_label));
			wsheet.addCell(new Label(1, row, bisCont.getContNo(), format_label));
			wsheet.addCell(new Label(2, row, multiNames.substring(0, multiNames.length() - 1), format_label));

			wsheet.addCell(new Label(4, row, "", format_label));
			wsheet.addCell(new Label(5, row, "", format_label));

			int col2 = 5;
			if (feeTypeId != null && feeTypeId.length() > 0) {
				int compInt = 0;
				if (feeType.length > 0) {
					for (int j = 0; j < (feeType.length * 2 * authMonth.length); j++) {
						if (compInt >= authMonth.length) {
							compInt = 0;
						}
						if (col2 % 2 == 1) {
							// 若为单数，则为金额格式
							wsheet.addCell(new Label(++col2, i + 4, "", format_unlock));
						} else {
							// 否则为日期格式
							wsheet.addCell(new Label(++col2, i + 4, authMonth[compInt], format_lock_date));
							compInt++;
						}
					}
				}
			}
			wsheet.setRowView(row++, 500);
		}

		for (int i = 0; i < multiList.size(); i++) {
			BisMulti bisMulti = multiList.get(i);
			String hiddenIds = floorType + ";" + bisProjectId + ";" + bisMulti.getBisMultiId() + ";1";
			wsheet.addCell(new Label(0, row, hiddenIds, format_label));
			wsheet.addCell(new Label(1, row, "", format_label));
			wsheet.addCell(new Label(2, row, bisMulti.getMultiName(), format_label));
			// 承租方
			wsheet.addCell(new Label(3, row, bisMulti.getRenterName(), format_label));
			wsheet.addCell(new Label(4, row, "", format_label));
			wsheet.addCell(new Label(5, row, "", format_label));
			int col2 = 5;
			if (feeTypeId != null && feeTypeId.length() > 0) {
				int compInt = 0;
				if (feeType.length > 0) {
					for (int j = 0; j < (feeType.length * 2 * authMonth.length); j++) {
						if (compInt >= authMonth.length) {
							compInt = 0;
						}
						if (col2 % 2 == 1) {
							// 若为单数，则为金额格式
							wsheet.addCell(new Label(++col2, i + 4, "", format_unlock));
						} else {
							// 否则为日期格式
							wsheet.addCell(new Label(++col2, i + 4, authMonth[compInt], format_lock_date));
							compInt++;
						}
					}
				}
			}
			wsheet.setRowView(row++, 500);
		}

	}

	/**
	 * 应收、实收的商铺导出模板
	 */
	public void initStoreSheet(WritableSheet wsheet) throws Exception {

		WritableFont font_Bold_10 = new WritableFont(WritableFont.createFont("Times New Roman"), 10);
		font_Bold_10.setBoldStyle(WritableFont.BOLD);
		WritableFont font_Red_11 = new WritableFont(WritableFont.createFont("Times New Roman"), 11);
		font_Red_11.setColour(Colour.RED);
		WritableFont font_Bold_14 = new WritableFont(WritableFont.createFont("Times New Roman"), 14);
		font_Bold_14.setBoldStyle(WritableFont.BOLD);

		// 输入框样式
		WritableCellFormat format_unlock = new WritableCellFormat(NumberFormats.FORMAT3);
		format_unlock.setLocked(false); // 不加锁
		format_unlock.setAlignment(Alignment.RIGHT);

		// 日期标题样式
		WritableCellFormat format_label2 = new WritableCellFormat(font_Red_11);
		format_label2.setAlignment(Alignment.RIGHT);
		format_label2.setVerticalAlignment(VerticalAlignment.CENTRE);
		// format_label2.setBackground(Colour.GRAY_25);

		// 日期样式
		WritableCellFormat format_unlock_date = new WritableCellFormat(new DateFormat("yyyy-MM-dd"));
		format_unlock_date.setLocked(false); // 不加锁
		format_unlock_date.setAlignment(Alignment.LEFT);
		format_unlock_date.setVerticalAlignment(VerticalAlignment.CENTRE);
		// format_unlock_date.setBackground(Colour.YELLOW);

		// 普通框样式
		WritableCellFormat format_label = new WritableCellFormat();
		format_label.setAlignment(Alignment.LEFT);

		// 一级标题样式
		WritableCellFormat format_head1 = new WritableCellFormat(font_Bold_14);
		format_head1.setAlignment(Alignment.CENTRE); // 水平对齐
		format_head1.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直对齐
		format_head1.setBackground(Colour.GRAY_25);

		// 二级标题样式
		WritableCellFormat format_head2 = new WritableCellFormat(font_Bold_10);
		format_head2.setAlignment(Alignment.CENTRE);
		format_head2.setVerticalAlignment(VerticalAlignment.CENTRE);

		String bisFloorNo = "";
		if (StringUtils.isNotBlank(bisFloorId)) {
			BisFloor bisFloor = bisFloorManager.getEntity(bisFloorId);
			bisFloorNo = bisFloor.getBuildingNum() + "~" + bisFloor.getFloorNum();
		}

		Map<String, String> chargeTypeMap = DictMapUtil.getMapChargeTypeNew();

		wsheet.addCell(new Label(0, 0, bisProjectName + "(" + feeDate + ")", format_head1));
		wsheet.addCell(new Label(0, 1, "", format_head2));
		wsheet.mergeCells(0, 1, 0, 2);
		wsheet.addCell(new Label(1, 1, "楼层号", format_head2));
		wsheet.mergeCells(1, 1, 1, 2);
		wsheet.addCell(new Label(2, 1, "商家", format_head2));
		wsheet.mergeCells(2, 1, 2, 2);
		// wsheet.addCell(new Label(3, 1, "经销商", format_head2));
		// wsheet.mergeCells(3, 1, 3, 3);
		wsheet.addCell(new Label(3, 1, "合同编号", format_head2));
		wsheet.mergeCells(3, 1, 3, 2);
		wsheet.addCell(new Label(4, 1, "合同类型", format_head2));
		wsheet.mergeCells(4, 1, 4, 2);
		wsheet.addCell(new Label(5, 1, "商铺", format_head2));
		wsheet.mergeCells(5, 1, 5, 2);

		// 每一列宽度
		wsheet.setColumnView(0, 0);
		if (StringUtils.isBlank(bisFloorId)) {
			wsheet.setColumnView(1, 0);
		} else {
			wsheet.setColumnView(1, 15);
		}
		wsheet.setColumnView(2, 18);
		// wsheet.setColumnView(3, 20);
		wsheet.setColumnView(3, 15);
		wsheet.setColumnView(4, 10);
		wsheet.setColumnView(5, 28);

		int col = 5;
		for (Entry<String, String> entry : chargeTypeMap.entrySet()) {

			wsheet.addCell(new Label(++col, 1, entry.getValue(), format_head2));
			// 费用类别占两格：一格为费用，另一格为日期
			wsheet.mergeCells(col, 1, col + 1, 1);
			wsheet.setColumnView(col, 12);
			wsheet.addCell(new Label(col, 2, entry.getKey()));
			++col;
			// wsheet.mergeCells(col, 2, col + 1, 2);
			// wsheet.addCell(new Label(col, 3, "应收", format_head2));
			// wsheet.addCell(new Label(++col, 3, "应收日期", format_head2));

			wsheet.setColumnView(col, 12);
		}

		wsheet.setRowView(0, 700);
		wsheet.setRowView(1, 600);
		wsheet.setRowView(2, 1);
		// wsheet.setRowView(3, 400);

		// String collDate = DateOperator.formatDate(
		// DateOperator.getFirstDayOfMonth(DateOperator.addMonthes(DateOperator.parse(feeDate,
		// "yyyy-MM"), 1)),
		// "yyyy-MM-dd");
		wsheet.mergeCells(0, 0, 5, 0);
		wsheet.addCell(new Label(6, 0, "请于费用类别下按标准填写内容，例：租金为1000元，对应下填写为：1000,2011-10-10", format_label2));
		wsheet.mergeCells(6, 0, 12, 0);
		// wsheet.addCell(new Label(10, 0, "", format_unlock_date));
		// wsheet.mergeCells(10, 0, 11, 0);
		// wsheet.mergeCells(10, 0, col, 0);

		// List<Object[]> tenantList =
		// bisTenantManager.getTenantList(bisProjectId, bisFloorId, null, null);

		List<Object[]> storeList = bisIncomeOtherManager.getStoreList(bisProjectId, bisFloorId);

		for (int i = 0; i < storeList.size(); i++) {
			Object[] obj = storeList.get(i);
			String contType = appDictTypeManager.getDictNameByCd(DictContants.BIS_CONT_SMALL_TYPE, (String) obj[6]);
			String hiddenIds = floorType + ";" + bisProjectId + ";" + bisFloorId + ";" + (String) obj[0] + ";" + (String) obj[1] + ";" + (String) obj[4];

			wsheet.addCell(new Label(0, i + 3, hiddenIds, format_label));
			wsheet.addCell(new Label(1, i + 3, bisFloorNo, format_label));
			wsheet.addCell(new Label(2, i + 3, (String) obj[2], format_label));
			// wsheet.addCell(new Label(3, i + 3, (String) obj[3],
			// format_label));
			wsheet.addCell(new Label(3, i + 3, (String) obj[5], format_label));
			wsheet.addCell(new Label(4, i + 3, contType, format_label));
			wsheet.addCell(new Label(5, i + 3, (String) obj[7]));
			int col2 = 5;
			for (int j = 0; j < (chargeTypeMap.size() * 2); j++) {
				if (col2 % 2 == 1) {
					// 若为单数，则为金额格式
					wsheet.addCell(new Label(++col2, i + 3, "", format_unlock));
				} else {
					// 否则为日期格式
					wsheet.addCell(new Label(++col2, i + 3, "", format_unlock_date));

				}
				// wsheet.addCell(new Label(++col2, i + 3, collDate,
				// format_unlock));
			}
			wsheet.setRowView(i + 3, 500);
		}

	}

	/**
	 * 公寓模板
	 */
	public void initFlatSheet(WritableSheet wsheet) throws WriteException {

		WritableFont font_Bold_10 = new WritableFont(WritableFont.createFont("Times New Roman"), 10);
		font_Bold_10.setBoldStyle(WritableFont.BOLD);
		WritableFont font_Red_11 = new WritableFont(WritableFont.createFont("Times New Roman"), 11);
		font_Red_11.setColour(Colour.RED);
		WritableFont font_Bold_14 = new WritableFont(WritableFont.createFont("Times New Roman"), 14);
		font_Bold_14.setBoldStyle(WritableFont.BOLD);

		// 输入框样式
		WritableCellFormat format_unlock = new WritableCellFormat(NumberFormats.FORMAT3);
		format_unlock.setLocked(false); // 不加锁
		format_unlock.setAlignment(Alignment.RIGHT);

		// 日期标题样式
		WritableCellFormat format_label2 = new WritableCellFormat(font_Red_11);
		format_label2.setAlignment(Alignment.RIGHT);
		format_label2.setVerticalAlignment(VerticalAlignment.CENTRE);
		// format_label2.setBackground(Colour.GRAY_25);

		// 日期样式
		WritableCellFormat format_unlock_date = new WritableCellFormat(new DateFormat("yyyy-MM-dd"));
		format_unlock_date.setLocked(false); // 不加锁
		format_unlock_date.setAlignment(Alignment.LEFT);
		format_unlock_date.setVerticalAlignment(VerticalAlignment.CENTRE);
		// format_unlock_date.setBackground(Colour.YELLOW);

		// 普通框样式
		WritableCellFormat format_label = new WritableCellFormat(NumberFormats.TEXT);
		format_label.setAlignment(Alignment.LEFT);

		// 一级标题样式
		WritableCellFormat format_head1 = new WritableCellFormat(font_Bold_14);
		format_head1.setAlignment(Alignment.CENTRE); // 水平对齐
		format_head1.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直对齐
		format_head1.setBackground(Colour.GRAY_25);

		// 二级标题样式
		WritableCellFormat format_head2 = new WritableCellFormat(font_Bold_10);
		format_head2.setAlignment(Alignment.CENTRE);
		format_head2.setVerticalAlignment(VerticalAlignment.CENTRE);

		String bisFloorNo = "";
		if (StringUtils.isNotBlank(bisFloorId)) {
			BisFloor bisFloor = bisFloorManager.getEntity(bisFloorId);
			bisFloorNo = bisFloor.getBuildingNum();
		}

		Map<String, String> chargeTypeMap = DictMapUtil.getMapChargeTypeNew();

		wsheet.addCell(new Label(0, 0, bisProjectName + "(" + feeDate + ")", format_head1));
		wsheet.addCell(new Label(0, 1, "", format_head2));
		wsheet.mergeCells(0, 1, 0, 2);
		wsheet.addCell(new Label(1, 1, "楼层号", format_head2));
		wsheet.mergeCells(1, 1, 1, 2);
		wsheet.addCell(new Label(2, 1, "合同编号", format_head2));
		wsheet.mergeCells(2, 1, 2, 2);
		wsheet.addCell(new Label(3, 1, "公寓编号", format_head2));
		wsheet.mergeCells(3, 1, 3, 2);
		wsheet.addCell(new Label(4, 1, "", format_head2));
		wsheet.mergeCells(4, 1, 4, 2);
		wsheet.addCell(new Label(5, 1, "", format_head2));
		wsheet.mergeCells(5, 1, 5, 2);

		// 每一列宽度
		wsheet.setColumnView(0, 0);
		/*
		 * if(StringUtils.isBlank(bisFloorId)) { wsheet.setColumnView(1, 0); }
		 * else {
		 */
		wsheet.setColumnView(1, 18);
		// }
		wsheet.setColumnView(2, 20);
		wsheet.setColumnView(3, 15);
		wsheet.setColumnView(4, 0);
		wsheet.setColumnView(5, 0);

		int col = 5;
		for (Entry<String, String> entry : chargeTypeMap.entrySet()) {

			wsheet.addCell(new Label(++col, 1, entry.getValue(), format_head2));
			wsheet.mergeCells(col, 1, col + 1, 1);
			wsheet.setColumnView(col, 12);
			wsheet.addCell(new Label(col, 2, entry.getKey()));
			++col;
			// wsheet.mergeCells(col, 2, col+1, 2);
			// wsheet.addCell(new Label(col, 3, "应收", format_head2));
			// wsheet.addCell(new Label(++col, 3, "实收", format_head2));

			wsheet.setColumnView(col, 12);
		}

		wsheet.setRowView(0, 700);
		wsheet.setRowView(1, 600);
		wsheet.setRowView(2, 1);
		// wsheet.setRowView(3, 400);

		// String collDate = DateOperator.formatDate(
		// DateOperator.getFirstDayOfMonth(DateOperator.addMonthes(DateOperator.parse(feeDate,
		// "yyyy-MM"), 1)),
		// "yyyy-MM-dd");

		wsheet.mergeCells(0, 0, 5, 0);
		wsheet.addCell(new Label(6, 0, "请于费用类别下按标准填写内容，例：租金为1000元，对应下填写为：1000,2011-10-10", format_label2));
		wsheet.mergeCells(6, 0, 12, 0);

		// wsheet.addCell(new Label(6, 0, "应收日期(yyyy-MM-dd)(在黄色区域填写)：",
		// format_label2));
		// wsheet.mergeCells(6, 0, 9, 0);
		// wsheet.addCell(new Label(10, 0, "", format_unlock_date));
		// wsheet.mergeCells(10, 0, 11, 0);
		// wsheet.mergeCells(0, 0, col, 0);

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("bisProjectId", bisProjectId);
		param.put("bisFloorId", bisFloorId);
		// 无合同签订的公寓数据
		StringBuffer hql = new StringBuffer("from BisFlat bf where bf.bisProjectId=:bisProjectId");
		hql.append(" and not exists (select 1 from BisFlatContRel r where r.bisFlat.bisFlatId=bf.bisFlatId)");
		if (StringUtils.isNotBlank(bisFloorId)) {
			hql.append(" and bf.bisFloor.bisFloorId=:bisFloorId");
		}
		hql.append(" order by bf.flatNo");
		List<BisFlat> flatList = bisFlatManager.find(hql.toString(), param);
		// 找出有签订合同的公寓数据
		StringBuffer hql2 = new StringBuffer("from BisCont bc where bc.bisProjectId=:bisProjectId");
		hql2.append(" and exists (select 1 from BisFlatContRel r where r.bisCont.bisContId=bc.bisContId");
		if (StringUtils.isNotBlank(bisFloorId)) {
			hql2.append(" and r.bisFlat.bisFloor.bisFloorId=:bisFloorId");
		}
		hql2.append(" ) order by bc.contNo");
		List<BisCont> contList = bisContManager.find(hql2.toString(), param);

		int row = 3;
		for (int i = 0; i < contList.size(); i++) {
			BisCont bisCont = contList.get(i);
			StringBuffer flatNos = new StringBuffer();
			for (BisFlatContRel rel : bisCont.getBisFlatContRels()) {
				flatNos.append(rel.getBisFlat().getFlatNo()).append(",");
				if (null != rel.getBisFlat().getBisFloor().getBuildingNum()) {
					bisFloorNo = rel.getBisFlat().getBisFloor().getBuildingNum();
				}
			}
			String hiddenIds = floorType + ";" + bisProjectId + ";" + bisFloorId + ";" + bisCont.getBisContId() + ";0";
			wsheet.addCell(new Label(0, row, hiddenIds, format_label));
			wsheet.addCell(new Label(1, row, bisFloorNo, format_label));
			wsheet.addCell(new Label(2, row, bisCont.getContNo(), format_label));
			wsheet.addCell(new Label(3, row, flatNos.substring(0, flatNos.length() - 1), format_label));
			wsheet.addCell(new Label(4, row, "", format_label));
			wsheet.addCell(new Label(5, row, "", format_label));
			int col2 = 5;
			for (int j = 0; j < (chargeTypeMap.size() * 2); j++) {
				if (col2 % 2 == 1) {
					// 若为单数，则为金额格式
					wsheet.addCell(new Label(++col2, row, "", format_unlock));
				} else {
					// 否则为日期格式
					wsheet.addCell(new Label(++col2, row, "", format_unlock_date));

				}
			}
			wsheet.setRowView(row++, 500);
		}

		for (int i = 0; i < flatList.size(); i++) {
			BisFlat bisFlat = flatList.get(i);
			String hiddenIds = floorType + ";" + bisProjectId + ";" + bisFloorId + ";" + bisFlat.getBisFlatId() + ";1";
			wsheet.addCell(new Label(0, row, hiddenIds, format_label));
			wsheet.addCell(new Label(1, row, bisFloorNo, format_label));
			wsheet.addCell(new Label(2, row, "", format_label));
			wsheet.addCell(new Label(3, row, bisFlat.getFlatNo(), format_label));
			wsheet.addCell(new Label(4, row, "", format_label));
			wsheet.addCell(new Label(5, row, "", format_label));
			int col2 = 5;
			for (int j = 0; j < (chargeTypeMap.size() * 2); j++) {
				if (col2 % 2 == 1) {
					// 若为单数，则为金额格式
					wsheet.addCell(new Label(++col2, row, "", format_unlock));
				} else {
					// 否则为日期格式
					wsheet.addCell(new Label(++col2, row, "", format_unlock_date));

				}
			}
			wsheet.setRowView(row++, 500);
		}

	}

	/**
	 * 多经模板
	 */
	public void initMultiSheet(WritableSheet wsheet) throws WriteException {

		WritableFont font_Bold_10 = new WritableFont(WritableFont.createFont("Times New Roman"), 10);
		font_Bold_10.setBoldStyle(WritableFont.BOLD);
		WritableFont font_Red_11 = new WritableFont(WritableFont.createFont("Times New Roman"), 11);
		font_Red_11.setColour(Colour.RED);
		WritableFont font_Bold_14 = new WritableFont(WritableFont.createFont("Times New Roman"), 14);
		font_Bold_14.setBoldStyle(WritableFont.BOLD);

		// 输入框样式
		WritableCellFormat format_unlock = new WritableCellFormat(NumberFormats.FORMAT3);
		format_unlock.setLocked(false); // 不加锁
		format_unlock.setAlignment(Alignment.RIGHT);

		// 日期标题样式
		WritableCellFormat format_label2 = new WritableCellFormat(font_Red_11);
		format_label2.setAlignment(Alignment.RIGHT);
		format_label2.setVerticalAlignment(VerticalAlignment.CENTRE);
		// format_label2.setBackground(Colour.GRAY_25);

		// 日期样式
		WritableCellFormat format_unlock_date = new WritableCellFormat(new DateFormat("yyyy-MM-dd"));
		format_unlock_date.setLocked(false); // 不加锁
		format_unlock_date.setAlignment(Alignment.LEFT);
		format_unlock_date.setVerticalAlignment(VerticalAlignment.CENTRE);
		// format_unlock_date.setBackground(Colour.YELLOW);

		// 普通框样式
		WritableCellFormat format_label = new WritableCellFormat(NumberFormats.TEXT);
		format_label.setAlignment(Alignment.LEFT);

		// 一级标题样式
		WritableCellFormat format_head1 = new WritableCellFormat(font_Bold_14);
		format_head1.setAlignment(Alignment.CENTRE); // 水平对齐
		format_head1.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直对齐
		format_head1.setBackground(Colour.GRAY_25);

		// 二级标题样式
		WritableCellFormat format_head2 = new WritableCellFormat(font_Bold_10);
		format_head2.setAlignment(Alignment.CENTRE);
		format_head2.setVerticalAlignment(VerticalAlignment.CENTRE);

		Map<String, String> chargeTypeMap = DictMapUtil.getMapChargeTypeNew();

		wsheet.addCell(new Label(0, 0, bisProjectName + "(" + feeDate + ")", format_head1));

		wsheet.addCell(new Label(0, 1, "", format_head2));
		wsheet.mergeCells(0, 1, 0, 2);
		wsheet.addCell(new Label(1, 1, "合同编号", format_head2));
		wsheet.mergeCells(1, 1, 1, 2);
		wsheet.addCell(new Label(2, 1, "多经编号", format_head2));
		wsheet.mergeCells(2, 1, 2, 2);
		wsheet.addCell(new Label(3, 1, "承租方", format_head2));
		wsheet.mergeCells(3, 1, 3, 2);
		wsheet.addCell(new Label(4, 1, "", format_head2));
		wsheet.mergeCells(4, 1, 4, 2);
		wsheet.addCell(new Label(5, 1, "", format_head2));
		wsheet.mergeCells(5, 1, 5, 2);

		// 每一列宽度
		wsheet.setColumnView(0, 0);
		wsheet.setColumnView(1, 30);
		wsheet.setColumnView(2, 30);
		wsheet.setColumnView(3, 30);
		wsheet.setColumnView(4, 0);
		wsheet.setColumnView(5, 0);

		int col = 5;
		for (Entry<String, String> entry : chargeTypeMap.entrySet()) {

			wsheet.addCell(new Label(++col, 1, entry.getValue(), format_head2));
			wsheet.mergeCells(col, 1, col + 1, 1);
			wsheet.setColumnView(col, 12);
			wsheet.addCell(new Label(col, 2, entry.getKey()));
			++col;
			// wsheet.mergeCells(col, 2, col+1, 2);
			// wsheet.addCell(new Label(col, 3, "应收", format_head2));
			// wsheet.addCell(new Label(++col, 3, "实收", format_head2));

			wsheet.setColumnView(col, 12);
		}

		wsheet.setRowView(0, 700);
		wsheet.setRowView(1, 600);
		wsheet.setRowView(2, 1);
		// wsheet.setRowView(3, 400);

		// String collDate = DateOperator.formatDate(
		// DateOperator.getFirstDayOfMonth(DateOperator.addMonthes(DateOperator.parse(feeDate,
		// "yyyy-MM"), 1)),
		// "yyyy-MM-dd");
		wsheet.mergeCells(0, 0, 5, 0);
		wsheet.addCell(new Label(6, 0, "请于费用类别下按标准填写内容，例：租金为1000元，对应下填写为：1000,2011-10-10", format_label2));
		wsheet.mergeCells(6, 0, 12, 0);
		/*
		 * wsheet.addCell(new Label(10, 0, "", format_unlock_date));
		 * wsheet.mergeCells(10, 0, 11, 0);
		 */
		// wsheet.mergeCells(0, 0, col, 0);

		String hql = "from BisMulti bm where bisProjectId=? and not exists (select 1 from BisMultiContRel r where r.bisMulti.bisMultiId=bm.bisMultiId)";
		List<BisMulti> multiList = bisMultiManager.find(hql, bisProjectId);

		String hql2 = "from BisCont bc where bc.bisProjectId=? and exists (select 1 from BisMultiContRel r where r.bisCont.bisContId=bc.bisContId)";
		List<BisCont> contList = bisContManager.find(hql2, bisProjectId);

		int row = 3;
		for (int i = 0; i < contList.size(); i++) {
			BisCont bisCont = contList.get(i);
			StringBuffer multiNames = new StringBuffer();
			for (BisMultiContRel rel : bisCont.getBisMultiContRels()) {
				multiNames.append(rel.getBisMulti().getMultiName()).append(",");
			}
			// 承租方
			if (bisCont.getBisContMultis() != null && bisCont.getBisContMultis().size() > 0) {
				wsheet.addCell(new Label(3, row, bisCont.getBisContMultis().get(0).getRenterName(), format_label));
			}
			String hiddenIds = floorType + ";" + bisProjectId + ";" + bisCont.getBisContId() + ";0";
			wsheet.addCell(new Label(0, row, hiddenIds, format_label));
			wsheet.addCell(new Label(1, row, bisCont.getContNo(), format_label));
			wsheet.addCell(new Label(2, row, multiNames.substring(0, multiNames.length() - 1), format_label));

			wsheet.addCell(new Label(4, row, "", format_label));
			wsheet.addCell(new Label(5, row, "", format_label));
			int col2 = 5;
			for (int j = 0; j < (chargeTypeMap.size() * 2); j++) {
				if (col2 % 2 == 1) {
					// 若为单数，则为金额格式
					wsheet.addCell(new Label(++col2, row, "", format_unlock));
				} else {
					// 否则为日期格式
					wsheet.addCell(new Label(++col2, row, "", format_unlock_date));

				}
				// wsheet.addCell(new Label(++col2, row, "", format_unlock));
			}
			wsheet.setRowView(row++, 500);
		}

		for (int i = 0; i < multiList.size(); i++) {
			BisMulti bisMulti = multiList.get(i);
			String hiddenIds = floorType + ";" + bisProjectId + ";" + bisMulti.getBisMultiId() + ";1";
			wsheet.addCell(new Label(0, row, hiddenIds, format_label));
			wsheet.addCell(new Label(1, row, "", format_label));
			wsheet.addCell(new Label(2, row, bisMulti.getMultiName(), format_label));
			// 承租方
			wsheet.addCell(new Label(3, row, bisMulti.getRenterName(), format_label));
			wsheet.addCell(new Label(4, row, "", format_label));
			wsheet.addCell(new Label(5, row, "", format_label));
			int col2 = 5;
			for (int j = 0; j < (chargeTypeMap.size() * 2); j++) {
				if (col2 % 2 == 1) {
					// 若为单数，则为金额格式
					wsheet.addCell(new Label(++col2, row, "", format_unlock));
				} else {
					// 否则为日期格式
					wsheet.addCell(new Label(++col2, row, "", format_unlock_date));

				}
				// wsheet.addCell(new Label(++col2, row, "", format_unlock));
			}
			wsheet.setRowView(row++, 500);
		}

	}

	/**
	 * 导入数据(POI)
	 */
	public String importDataPoi() throws Exception {

		long begin = new Date().getTime();

		int insert = 0;
		int update = 0;
		int r = 0;
		int c = 0;

		int row = 0;
		int col = 0;

		try {

			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(importFile));
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);

			HSSFRow firstrow = hssfSheet.getRow(0);
			String title = firstrow.getCell(0).getStringCellValue();
			String[] date = title.substring(title.indexOf("(") + 1, title.indexOf(")")).split("-");
			String year = date[0];
			String month = date[1];
			if (Integer.valueOf(month) < 10) {
				month = date[1].replace("0", "");
			}

			/*
			 * 应收日期格式填写 Date collDate = null; int dateCellType =
			 * firstrow.getCell(10).getCellType(); if (dateCellType ==
			 * HSSFCell.CELL_TYPE_BLANK) {
			 * Struts2Utils.renderText(",error, 请填写应收日期,"); return null; } else
			 * if (dateCellType == HSSFCell.CELL_TYPE_NUMERIC) { collDate =
			 * firstrow.getCell(10).getDateCellValue(); if
			 * (StringUtils.isBlank(DateOperator.formatDate(collDate,
			 * "yyyy-MM-dd"))) {
			 * Struts2Utils.renderText(",error, 应收日期格式错误(填写如2011-11-01),");
			 * return null; } } else {
			 * Struts2Utils.renderText(",error, 应收日期格式错误(填写如2011-11-01),");
			 * return null; }
			 */

			String user = SpringSecurityUtils.getCurrentUiid();
			Date now = new Date();
			// 应收列表数据
			List<BisMust> mustList = new ArrayList<BisMust>();
			// 实收列表数据,若找得到应收对应的实收，则修改其实收数据
			List<BisFact> factList = new ArrayList<BisFact>();

			HSSFRow chargeTypeRow = hssfSheet.getRow(2);

			for (row = 3; row <= hssfSheet.getLastRowNum(); row++) {

				HSSFRow hssfRow = hssfSheet.getRow(row);

				if (hssfRow.getCell(0).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
					continue;
				}
				String[] hiddenIds = hssfRow.getCell(0).getStringCellValue().split(";");
				String floorTypeCd = hiddenIds[0].trim();
				String projectId = hiddenIds[1].trim();

				String floorId = "";
				String bisTenantId = "";
				String bisShopId = "";
				String bisFlatId = "";
				String bisMultiId = "";
				String bisContId = "";

				if ("1".equals(floorTypeCd)) { // 商铺
					floorId = hiddenIds[2].trim();
					bisTenantId = hiddenIds[3].trim();
					bisShopId = hiddenIds[4].trim();
					bisContId = hiddenIds[5].trim();
				} else if ("2".equals(floorTypeCd)) { // 公寓
					floorId = hiddenIds[2].trim();
					if ("0".equals(hiddenIds[4].trim())) {
						bisContId = hiddenIds[3].trim();
					} else {
						bisFlatId = hiddenIds[3].trim();
					}
				} else { // 多经
					if ("0".equals(hiddenIds[3].trim())) {
						bisContId = hiddenIds[2].trim();
					} else {
						bisMultiId = hiddenIds[2].trim();
					}
				}

				BisCont bisCont = bisContManager.getEntity(bisContId);

				// 从第6列取得应收费用数据
				for (col = 6; col < hssfRow.getLastCellNum(); col++) {
					c = col;
					HSSFCell hssfCell = hssfRow.getCell(col);
					if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
						BigDecimal mustFee = new BigDecimal(hssfCell.getNumericCellValue());

						/*
						 * if (mustFee == null) { //新增或修改应收日期 ++col; ++c;
						 * continue; }
						 */
						String chargeTypeCd = chargeTypeRow.getCell(col).getStringCellValue();
						BisMust bisMust = bisMustManager.getExistsMust(bisContId, bisFlatId, bisMultiId, chargeTypeCd, year, month);
						// 如果不存在该应收数据，则新增一条数据
						if (bisMust == null) {
							insert++;
							bisMust = new BisMust();
							bisMust.setBisProjectId(projectId);
							if ("1".equals(floorTypeCd)) { // 商铺
								if (StringUtils.isNotBlank(floorId)) {
									bisMust.setBisFloorId(floorId);
								}
								bisMust.setBisTenantId(bisTenantId);
								bisMust.setBisShopId(bisShopId);
								bisMust.setBisCont(bisCont);
								bisMust.setFloorType("1");
								// 取合同对应的租户ID
								bisMust.setBisTenantId(bisCont.getBisTenantId());

							} else if ("2".equals(floorTypeCd)) { // 公寓
								if (StringUtils.isNotBlank(floorId)) {
									bisMust.setBisFloorId(floorId);
								}
								if (StringUtils.isNotBlank(bisFlatId)) {
									bisMust.setBisFlatId(bisFlatId);
								}
								if (StringUtils.isNotBlank(bisContId)) {
									bisMust.setBisCont(bisContManager.getEntity(bisContId));
								}
								bisMust.setFloorType("2");
							} else { // 多经
								if (StringUtils.isNotBlank(bisMultiId)) {
									bisMust.setBisMultiId(bisMultiId);
								}
								if (StringUtils.isNotBlank(bisContId)) {
									bisMust.setBisCont(bisContManager.getEntity(bisContId));
								}
								bisMust.setFloorType("3");
							}
							bisMust.setChargeTypeCd(chargeTypeCd);
							bisMust.setMustYear(year);
							bisMust.setMustMonth(month);
							bisMust.setMoney(mustFee);
							bisMust.setStatusCd("2"); // 已审核
							if ("1".equals(chargeTypeCd) || "3".equals(chargeTypeCd) || "4".equals(chargeTypeCd)) {
								bisMust.setTypeCd("0");
							} else {
								bisMust.setSequenceNo(new Long(r - 3));
								bisMust.setTypeCd("1");
							}
							bisMust.setCreator(user);
							bisMust.setCreatedDate(now);
						} else {
							// 否则修改应收费用
							if (bisMust.getMoney() == null || bisMust.getMoney().compareTo(mustFee) != 0 || !"2".equals(bisMust.getStatusCd())) {
								bisMust.setMoney(mustFee);
								bisMust.setStatusCd("2"); // 已审核
								bisMust.setUpdator(user);
								bisMust.setUpdatedDate(now);
								update++;

							}
						}
						// 新增或修改应收日期
						++col;
						++c;
						HSSFCell cellDate = hssfRow.getCell(col);

						if (cellDate.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
							Struts2Utils.renderText(",error, 未导入成功，请于第" + (row + 1) + "行第" + BisConstants.letters.get(c + 1) + "列填写应收日期,");
							return null;
						} else if (cellDate.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
							// collDate =
							// firstrow.getCell(10).getDateCellValue();
							if (StringUtils.isBlank(DateOperator.formatDate(cellDate.getDateCellValue(), "yyyy-MM-dd"))) {
								Struts2Utils.renderText(",error, 第" + (row + 1) + "行第" + BisConstants.letters.get(c + 1) + "列，应收日期格式错误(填写如2011-11-01),");
								return null;
							}
						} else {
							Struts2Utils.renderText(",error, 第" + (row + 1) + "行第" + BisConstants.letters.get(c + 1) + "列应收日期格式错误(填写如2011-11-01),");
							return null;
						}
						bisMust.setCollDate(cellDate.getDateCellValue());
						// 搜索应收对应的实收，若找得到数据，则更新应收的totFactMoney数据，后更新实收对应的应收totMustMoney数据，放入
						// factList中
						List<BisFact> bisFactList = bisFactManager.getFact(bisProjectId, bisTenantId, bisFlatId, bisMultiId, chargeTypeCd, year, month,
								bisContId);
						BigDecimal totFactMoney = new BigDecimal(0);
						for (BisFact fact : bisFactList) {
							totFactMoney = totFactMoney.add(fact.getMoney());
							fact.setTotalMustMoney(bisMust.getMoney());
							factList.add(fact);
						}
						bisMust.setTotalFactMoney(totFactMoney);
						mustList.add(bisMust);
					} else {
						// 若费用没有数据则跳一格（日期）
						++col;
					}
				}
				r = row;
			}
			bisIncomeOtherManager.batchExecute(mustList);
			if (factList != null && factList.size() > 0) {
				bisFactManager.batchExecute(factList);
			}

			long end = new Date().getTime();

			Struts2Utils.renderText(",success," + insert + "," + update + "," + (end - begin) / 1000 + ",");
		} catch (Exception e) {
			e.printStackTrace();
			String errorLocation = (r + 1) + "行 " + BisConstants.letters.get(c + 1) + "列";
			Struts2Utils.renderText(",error," + e + ", " + errorLocation + ",");
		}
		return null;
	}

	public String getBisProjectId() {
		return bisProjectId;
	}

	public void setBisProjectId(String bisProjectId) {
		this.bisProjectId = bisProjectId;
	}

	public String getBisProjectName() {
		return bisProjectName;
	}

	public void setBisProjectName(String bisProjectName) {
		this.bisProjectName = bisProjectName;
	}

	public String getFloorType() {
		return floorType;
	}

	public void setFloorType(String floorType) {
		this.floorType = floorType;
	}

	public String getBisFloorId() {
		return bisFloorId;
	}

	public void setBisFloorId(String bisFloorId) {
		this.bisFloorId = bisFloorId;
	}

	public String getFeeDate() {
		return feeDate;
	}

	public void setFeeDate(String feeDate) {
		this.feeDate = feeDate;
	}

	public List<BisIncomeOtherVo> getVolist() {
		return volist;
	}

	public InputStream getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}

	public String getExcelFileName() {
		return excelFileName;
	}

	public void setExcelFileName(String excelFileName) {
		this.excelFileName = excelFileName;
	}

	public File getImportFile() {
		return importFile;
	}

	public void setImportFile(File importFile) {
		this.importFile = importFile;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

	public String getAuthDate1() {
		return authDate1;
	}

	public void setAuthDate1(String authDate1) {
		this.authDate1 = authDate1;
	}

	public String getAuthDate2() {
		return authDate2;
	}

	public void setAuthDate2(String authDate2) {
		this.authDate2 = authDate2;
	}

	public String getFeeTypeId() {
		return feeTypeId;
	}

	public void setFeeTypeId(String feeTypeId) {
		this.feeTypeId = feeTypeId;
	}

	private int compareMonths(String startMonth, String endMonth) {
		String[] startMonthes = startMonth.split("-");
		String[] endMonthes = endMonth.split("-");
		int year1 = Integer.parseInt(startMonthes[0]);
		int year2 = Integer.parseInt(endMonthes[0]);
		int month1 = Integer.parseInt(startMonthes[1]);
		int month2 = Integer.parseInt(endMonthes[1]);
		if (year1 > year2)
			return 0;
		else if (year1 == year2) {
			if (month1 > month2)
				return 0;
			else
				return month2 - month1;
		} else
			// 跨年
			return (year2 - year1 - 1) * 12 + (12 - month1) + month2;
	}

}
