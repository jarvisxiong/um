package com.hhz.ump.dao.bis;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bis.BisManageDay;
import com.hhz.ump.util.FormatUtil;
import com.hhz.ump.web.vo.BisManageDayVo;

@Service
@Transactional
public class BisManageDayManager extends BaseService<BisManageDay, String> {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    private BisManageDayDao bisManageDayDao;
    //新增/初始状态
    public static String STATUS_INIT = "0";
    //完成/通过
    public static String STATUS_SUCCESS = "1";
    //删除
    public static String STATUS_DELETE = "2";

    public void saveBisManageDay(BisManageDay bisManageDay) throws Exception {

        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        if (StringUtils.isNotBlank(Struts2Utils.getParameter("authDate"))) {
            calendar.setTime(simpleDateFormat.parse(Struts2Utils.getParameter("authDate")));
            if (simpleDateFormat.format(calendar2.getTime()).equalsIgnoreCase(Struts2Utils.getParameter("authDate"))) {
                if (calendar2.get(Calendar.HOUR_OF_DAY) < 12) {
                    calendar2.add(Calendar.DAY_OF_MONTH, -1);
                }
            }
        } else {
            if (calendar.get(Calendar.HOUR_OF_DAY) < 12) {
                calendar.add(Calendar.DAY_OF_MONTH, -1);
            }
        }
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar2.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 58);
        calendar2.set(Calendar.MINUTE, 59);
        if (calendar.getTime().getTime() > calendar2.getTime().getTime())
            throw new RuntimeException("录入日期不能大于当前日期，当日记录必须在12点之后录入！");

        Map params = new HashMap();
        params.put("create", simpleDateFormat.format(calendar.getTime()));
        if (null != bisManageDay.getBisProject()) {
            params.put("bisProjectId", bisManageDay.getBisProject().getBisProjectId());
        }
        List<BisManageDay> values = find(builderSql(params).toString(), params);
        BisManageDay bisManageDayTemp2 = null;
        for (BisManageDay bisManageDayTemp : values) {
            if (bisManageDayTemp.getBisManageDayId().equalsIgnoreCase(bisManageDay.getBisManageDayId())) {
                bisManageDayTemp2 = bisManageDayTemp;
            }
        }
        if (null != bisManageDayTemp2) {
            values.remove(bisManageDayTemp2);
        }
        if (null != values && values.size() != 0)
            throw new RuntimeException("当日记录已存在,请明天12点以后在录入。但你可以修改当日的记录哦！");

        PowerUtils.setEmptyStr2Null(bisManageDay);

        if (StringUtils.isBlank(bisManageDay.getBisManageDayId()) || StringUtils.isNotBlank(Struts2Utils.getParameter("authDate"))) {
            bisManageDay.setAuthDate(calendar.getTime());
        }
        bisManageDayDao.save(bisManageDay);
    }

    public void deleteBisManageDay(String id) {
        bisManageDayDao.delete(id);
    }

    @Override
    public HibernateDao<BisManageDay, String> getDao() {
        return bisManageDayDao;
    }


    /**
     * 拼装 HSql语句 ，方便重用
     *
     * @param params
     * @return HSql
     */
    private StringBuffer builderSql(Map params) {
        String currentUiid = SpringSecurityUtils.getCurrentUiid();
        StringBuffer queryString = new StringBuffer("from BisManageDay t1 where 1=1");
        BisManageDay bisManageDay = (BisManageDay) params.get("entity");
        //所属项目
        if (null != params.get("bisProjectId") && StringUtils.isNotBlank(String.valueOf(params.get("bisProjectId")))) {
            queryString.append(" AND t1.bisProject.bisProjectId=:bisProjectId");
            params.put("bisProjectId", params.get("bisProjectId"));
        }

        if (null != params.get("startYear") && StringUtils.isNotBlank(String.valueOf(params.get("startYear")))) {
            queryString.append(" AND to_char(t1.authDate,'yyyy')=:startYear");
            params.put("startYear", params.get("startYear"));
        }
        if (null != params.get("startMonth") && StringUtils.isNotBlank(String.valueOf(params.get("startMonth")))) {
            queryString.append(" AND to_char(t1.authDate,'MM')=:startMonth");
            params.put("startMonth", params.get("startMonth"));
        }
        if (null != params.get("create") && StringUtils.isNotBlank(String.valueOf(params.get("create")))) {
            queryString.append(" AND to_char(t1.authDate,'yyyy-MM-dd')=:create");
            params.put("create", params.get("create"));
        }
        if (null != params.get("currentDate2") && StringUtils.isNotBlank(String.valueOf(params.get("currentDate2")))) {
            queryString.append(" AND t1.authDate < :currentDate2 ");
            params.put("currentDate2", params.get("currentDate2"));
        }
        return queryString;
    }

    public Page findBisManageDayByConditions(Page pager, Map params) {
        StringBuffer queryString = builderSql(params);
        queryString.append(" order by t1.authDate desc");
        return bisManageDayDao.findPage(pager, queryString.toString(), params);
    }

    public List findReportByConditions(Map params) {
        //获取当前 录入 数据的 所属日期,12点后 为当天，12点前 为前一天
        Calendar calendar = Calendar.getInstance();
        if (calendar.get(Calendar.HOUR_OF_DAY) < 12) {
            calendar.add(Calendar.DAY_OF_MONTH, -1);
        }
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 2);
        StringBuffer queryString = new StringBuffer("select t2.bis_project_id,\n" +
                "       t2.project_name,\n" +
                "       sum(t1.super_market) super_market,\n" +
                "       sum(t1.department_store) department_store,\n" +
                "       sum(t1.cinema) as cinema,\n" +
                "       sum(t1.main_total) as main_total,\n" +
                "       sum(t1.store_total) as store_total,\n" +
                "       sum(t1.plaza_total) as plaza_total,\n" +
                "       sum(t1.customer_total) as customer_total,\n" +
                "       sum(t1.car_total) as car_total\n," +
                "       (\n" +
                "         select count(*) from bis_manage_day t3 where 1=1 and t3.manage_event is not null and t3.manage_event<>'无' and t3.bis_project_id=t2.bis_project_id and t3.auth_date < :currentDate \n" +
                "       ) as manage_event ,\n" +
                "       (\n" +
                "         select count(*) from bis_manage_day t3 where 1=1 and t3.plan_activite_event is not null and t3.plan_activite_event<>'无' and t3.bis_project_id=t2.bis_project_id and t3.auth_date < :currentDate \n" +
                "       ) as plan_activite_event,'',count(t1.super_market) as count" +
                "  from bis_manage_day t1 right join bis_project t2 on t2.bis_project_id=t1.bis_project_id and t1.auth_date < :currentDate");
        params.put("currentDate", calendar.getTime());
        params.put("active", true);
        String childSql = "1=1 ";
        if (null != params.get("bisProjectId") && StringUtils.isNotBlank(params.get("bisProjectId").toString())) {
            queryString.append(" AND t1.bis_project_id=:bisProjectId ");
        }
        if (null != params.get("startYear") && StringUtils.isNotBlank(String.valueOf(params.get("startYear")))) {
            queryString.append(" AND to_char(t1.auth_date,'yyyy')=:startYear");
            childSql += " AND to_char(t3.auth_date,'yyyy')=:startYear";
        }
        if (null != params.get("startMonth") && StringUtils.isNotBlank(String.valueOf(params.get("startMonth")))) {
            queryString.append(" AND to_char(t1.auth_date,'MM')=:startMonth");
            childSql += " AND to_char(t3.auth_date,'MM')=:startMonth";
        }
        if (null != params.get("startDay") && StringUtils.isNotBlank(String.valueOf(params.get("startDay")))) {
            queryString.append(" AND to_char(t1.auth_date,'dd')=:startDay");
            childSql += " AND to_char(t3.auth_date,'dd')=:startDay";
        }
        queryString.append(" where t2.active=:active"); 
        queryString.append(" group by t2.bis_project_id, t2.project_name order by sum(t2.SEQUENCE_NO2) asc");
        List<Object[]> values = bisManageDayDao.findBySql(queryString.toString().replace("1=1", childSql), params);

        /**
         * 月报表,搜索明细信息
         */ 
        if (null != params.get("startYear") && StringUtils.isNotBlank(String.valueOf(params.get("startYear")))
                && (null == params.get("startDay") || StringUtils.isNotBlank(String.valueOf(params.get("startDay"))))) {
            for (Object[] val : values) {
                Map ps = new HashMap();
                ps.put("bisProjectId", val[0]);
                ps.put("startYear", params.get("startYear").toString());
                ps.put("startMonth", params.get("startMonth").toString());
                ps.put("currentDate2", calendar.getTime());
                StringBuffer singleString = builderSql(ps);
                singleString.append(" order by t1.authDate desc");
                val[12] = bisManageDayDao.find(singleString.toString(), ps);
            }
        }

        if (null != values && values.size() != 0) {
            String[] value = new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"};
            for (Object[] val : values) {
                value[1] = "";
                for (int i = 2; i < 12; i++) {
                    if (val[i] == null) {
                        val[i] = 0;
                    }
                }
                value[2] = String.valueOf(Float.valueOf(value[2]) + Float.valueOf(val[2].toString()));
                value[3] = String.valueOf(Float.valueOf(value[3]) + Float.valueOf(val[3].toString()));
                value[4] = String.valueOf(Float.valueOf(value[4]) + Float.valueOf(val[4].toString()));
                value[5] = String.valueOf(Float.valueOf(value[5]) + Float.valueOf(val[5].toString()));
                value[6] = String.valueOf(Float.valueOf(value[6]) + Float.valueOf(val[6].toString()));
                value[7] = String.valueOf(Float.valueOf(value[7]) + Float.valueOf(val[7].toString()));
                value[8] = String.valueOf(Float.valueOf(value[8]) + Float.valueOf(val[8].toString()));
                value[9] = String.valueOf(Float.valueOf(value[9]) + Float.valueOf(val[9].toString()));
                value[10] = String.valueOf(Float.valueOf(value[10]) + Float.valueOf(val[10].toString()));
                value[11] = String.valueOf(Float.valueOf(value[11]) + Float.valueOf(val[11].toString()));
            }
            values.add(value);
        }
        return values;
    }

    /**
     * 决策平台-商家营业业绩状况报表
     * (取值数据参考上面的findReportByConditions()方法)
     * @param yea 年
     * @param month 月
     * @return
     */
	public List<BisManageDayVo> getBisManageDayList(String year,String month) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer queryString = new StringBuffer("select t2.bis_project_id as project_id,\n" +
			"       t2.project_name as project_name,\n" +
			"       sum(t1.super_market) as super_market,\n" +
			"       sum(t1.department_store) as department_store,\n" +
			"       sum(t1.cinema) as cinema,\n" +
			"       sum(t1.main_total) as main_total,\n" +
			"       sum(t1.baolai) as bao_lai,\n" +
			"       sum(t1.longmai) as long_mai,\n" +
			"       sum(t1.caoshangfei) as cao_shang_fei,\n" +
			"       sum(t1.baolai)+sum(t1.longmai)+sum(t1.caoshangfei) as other_total,\n" +
			"       sum(t1.store_total) as store_total,\n" +
			"       sum(t1.plaza_total) as plaza_total" +
			"  from bis_manage_day t1 right join bis_project t2 on t2.bis_project_id=t1.bis_project_id and t1.auth_date < :currentDate");
		String childSql = "1=1 ";
		if (StringUtils.isNotBlank(year)) {
			queryString.append(" AND to_char(t1.auth_date,'yyyy')=:startYear");
			childSql += " AND to_char(t3.auth_date,'yyyy')=:startYear";
			params.put("startYear", year);
		}
		if (StringUtils.isNotBlank(month)) {
			String m = null;
			if(Long.valueOf(month)<10){
				m = "0"+month;
			}else{
				m = month;
			}
			queryString.append(" AND to_char(t1.auth_date,'MM')=:startMonth");
			childSql += " AND to_char(t3.auth_date,'MM')=:startMonth";
			params.put("startMonth", m);
		}
		params.put("currentDate", new Date());
		params.put("active", true);
		queryString.append(" where t2.active=:active"); 
		queryString.append(" group by t2.bis_project_id, t2.project_name order by sum(t2.SEQUENCE_NO2) asc");
		List list = bisManageDayDao.findBySql(queryString.toString().replace("1=1", childSql), params);
		List<BisManageDayVo> voList = new ArrayList<BisManageDayVo>();
		if(!list.isEmpty()){
			Object[] obj = null;
			BisManageDayVo vo = null;
			for (int i = 0; i < list.size(); i++) {
				obj = (Object[])list.get(i);
				vo = new BisManageDayVo();
				vo.setBisProjectId((String)obj[0]);
				vo.setBisProjectName((String)obj[1]);
				vo.setSuperMarket(getMoneyValue((BigDecimal)obj[2]));
				vo.setDepartmentStore(getMoneyValue((BigDecimal)obj[3]));
				vo.setCinema(getMoneyValue((BigDecimal)obj[4]));
				vo.setMainTotal(getMoneyValue((BigDecimal)obj[5]));
				vo.setBaolai(getMoneyValue((BigDecimal)obj[6]));
				vo.setLongmai(getMoneyValue((BigDecimal)obj[7]));
				vo.setCaoshangfei(getMoneyValue((BigDecimal)obj[8]));
				vo.setOtherTotal(getMoneyValue((BigDecimal)obj[9]));
				vo.setStoreTotal(getMoneyValue((BigDecimal)obj[10]));
				vo.setPlazaTotal(getMoneyValue((BigDecimal)obj[11]));
				voList.add(vo);
			}
		}
		return voList;
	}
	
	/**
	 * 合计行数据
	 * @param list
	 * @return
	 */
	public BisManageDayVo getBisManageDayCount(List<BisManageDayVo> list){
		BisManageDayVo countVo = new BisManageDayVo();
		if(!list.isEmpty()){
			for (BisManageDayVo tmpVo : list) {
				countVo.setSuperMarket(getMoneyValue2(tmpVo.getSuperMarket(),countVo.getSuperMarket()));
				countVo.setDepartmentStore(getMoneyValue2(tmpVo.getDepartmentStore(),countVo.getDepartmentStore()));
				countVo.setCinema(getMoneyValue2(tmpVo.getCinema(),countVo.getCinema()));
				countVo.setMainTotal(getMoneyValue2(tmpVo.getMainTotal(),countVo.getMainTotal()));
				countVo.setBaolai(getMoneyValue2(tmpVo.getBaolai(),countVo.getBaolai()));
				countVo.setLongmai(getMoneyValue2(tmpVo.getLongmai(),countVo.getLongmai()));
				countVo.setCaoshangfei(getMoneyValue2(tmpVo.getCaoshangfei(),countVo.getCaoshangfei()));
				countVo.setOtherTotal(getMoneyValue2(tmpVo.getOtherTotal(),countVo.getOtherTotal()));
				countVo.setStoreTotal(getMoneyValue2(tmpVo.getStoreTotal(),countVo.getStoreTotal()));
				countVo.setPlazaTotal(getMoneyValue2(tmpVo.getPlazaTotal(),countVo.getPlazaTotal()));
			}
		}
		return countVo;
	}

	public String getMoneyValue(BigDecimal value){
		String retStr = null;
		if(value != null) {
			retStr = FormatUtil.formatMoney(value,2);
		}else{
			retStr = "0";
		}
		return retStr;
	}
	public String getMoneyValue2(String value1,String value2){
		BigDecimal big1 = new BigDecimal(0);
		BigDecimal big2 = new BigDecimal(0);
		if(StringUtils.isNotBlank(value1)) {
			big1 = FormatUtil.formatMoney(value1);
		}
		if(StringUtils.isNotBlank(value2)) {
			big2 = FormatUtil.formatMoney(value2);
		}
		String retStr = FormatUtil.formatMoney(big1.add(big2),2);
		return retStr;
	}
}