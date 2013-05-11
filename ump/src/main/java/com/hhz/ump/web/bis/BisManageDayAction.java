package com.hhz.ump.web.bis;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.bis.BisManageDayManager;
import com.hhz.ump.dao.bis.BisProjectManager;
import com.hhz.ump.entity.bis.BisManageDay;
import com.hhz.ump.entity.bis.BisProject;
import com.hhz.ump.util.HelperUtil;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangGaoJiang
 * Date: 12-2-28
 * Time: 下午4:19
 * 商业管理系统 每日经营数据管理
 */

@Namespace("/bis")
public class BisManageDayAction extends CrudActionSupport<BisManageDay> {

    @Autowired
    private BisManageDayManager bisManageDayManager;
    @Autowired
    private BisProjectManager bisProjectManager;

    public BisManageDay entity = new BisManageDay();
    public BisProject project = new BisProject();

    //工具辅助类
    public HelperUtil helper = new HelperUtil();
    public Date boundaryDate = new Date();

    @Override
    public String deleteBatch() throws Exception {
        return null;
    }

    @Override
    public void prepare() throws Exception {
        super.prepare();
        String bisProjectId = Struts2Utils.getParameter("bisProjectId");
        //加载项目信息
        if (StringUtils.isNotBlank(bisProjectId)) {
            project = bisProjectManager.getEntity(bisProjectId.trim());
        }
        //经营性数据加载
        if (StringUtils.isNotBlank(getId())) {
            entity = (BisManageDay) bisManageDayManager.getDao().getSession().get(BisManageDay.class, getId());
        }
        Calendar calendar = Calendar.getInstance();
        if (calendar.get(Calendar.HOUR_OF_DAY) < 12) {
            calendar.add(Calendar.DAY_OF_MONTH, -1);
        }
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 1);
        boundaryDate = calendar.getTime();
    }

    @Override
    public String list() throws Exception {
        Map params = new HashMap();
        params.put("entity", entity);
        params.put("startYear", Struts2Utils.getParameter("startYear"));
        params.put("startMonth", Struts2Utils.getParameter("startMonth"));
        params.put("bisProjectId", Struts2Utils.getParameter("bisProjectId"));
        bisManageDayManager.findBisManageDayByConditions(page, params);
        return "list";
    }

    @Override
    public String input() throws Exception {
        return null;
    }

    @Override
    public String save() throws Exception {
        return null;
    }

    @Override
    public String delete() throws Exception {
        return null;
    }

    @Override
    protected void prepareModel() throws Exception {
    }

    @Override
    public BisManageDay getModel() {
        return entity;
    }

    /**
     * 默认页列表页
     *
     * @return
     * @throws Exception
     */
    public String index() throws Exception {

        return "index";
    }

    /**
     * 填写/编辑
     *
     * @return
     * @throws Exception
     */
    public String publish() throws Exception {
        return "publish";
    }

    /**
     * 报表
     *
     * @return
     * @throws Exception
     */
    public String report() throws Exception {
        if (null != bisProjectManager.getCurrProject()) {
            Struts2Utils.getRequest().setAttribute("bisProjectId", bisProjectManager.getCurrProject().getBisProjectId());
        }
        return "report";
    }

    /**
     * 报表内容
     *
     * @return
     * @throws Exception
     */
    public String reportBody() throws Exception {
        String startDate = Struts2Utils.getParameter("startDate");
        Map params = new HashMap();
        params.put("entity", entity);
        if (StringUtils.isNotBlank(startDate)) {
            String[] date = startDate.split("-");
            //为计算 确切日期 构建 日历控件 执行  上一天/月 下一天/月 操作
            Calendar calendar = Calendar.getInstance(Locale.CHINA);
            calendar.clear();
            if (date.length > 2) {
                calendar.set(Integer.valueOf(date[0]), Integer.valueOf(date[1]) - 1, Integer.valueOf(date[2]));
                Struts2Utils.getRequest().setAttribute("isMonth", false);
            } else {
                calendar.set(Calendar.YEAR, Integer.valueOf(date[0]));
                calendar.set(Calendar.MONTH, Integer.valueOf(date[1]) - 1);
                Struts2Utils.getRequest().setAttribute("isMonth", true);
            }
            //是否需要 执行 上一天下一天操作
            String bar = Struts2Utils.getParameter("bar");
            if (StringUtils.isNotBlank(bar)) {
                //日报 或月报
                if (bar.equalsIgnoreCase("0")) {//上
                    if (date.length > 2) {//日
                        calendar.add(Calendar.DATE, -1);
                    } else {//月
                        calendar.add(Calendar.MONTH, -1);
                    }
                } else {//下
                    if (date.length > 2) {//日
                        calendar.add(Calendar.DATE, 1);
                    } else {//月
                        calendar.add(Calendar.MONTH, 1);
                    }
                }
            }

            params.put("startYear", calendar.get(Calendar.YEAR));
            params.put("startMonth", calendar.get(Calendar.MONTH) < 9 ? "0" + (calendar.get(Calendar.MONTH) + 1) : (calendar.get(Calendar.MONTH) + 1));

            startDate = params.get("startYear") + "-" + params.get("startMonth");
            //日操作
            if (date.length > 2) {
                params.put("startDay", calendar.get(Calendar.DATE) < 10 ? "0" + calendar.get(Calendar.DATE) : calendar.get(Calendar.DATE));
                startDate += "-" + params.get("startDay");
            }
            Struts2Utils.getRequest().setAttribute("startDate", startDate);
        }
        params.put("bisProjectId", Struts2Utils.getParameter("bisProjectId"));
        List list = bisManageDayManager.findReportByConditions(params);
        Struts2Utils.getRequest().setAttribute("values", list);
        /**
         * 如果不等于空，处理的是日报表；如果等于空，则处理月报表
         */
        if(null != params.get("startDay")){
        	Struts2Utils.getRequest().setAttribute("hasDay", "1");
        }else{
        	Struts2Utils.getRequest().setAttribute("hasDay", "0");
        	/**
             * 判断选中的日期是否为当前日期：如果不是当前日期，则计算整月的天数；如果是当前日期，则计算当前月当天的天数
             */
            if(getReplaceVal(getCurrentDate()).equals(getReplaceVal(startDate))){
            	//计算当前月的天数(本月)
            	Struts2Utils.getRequest().setAttribute("monthOfDays",Integer.parseInt(new SimpleDateFormat("yyyy-MM-dd").format(new Date()).split("-")[2])-1);
            }else{
            	//计算当前月外的整天数
            	Integer monthOfDays = getCurrentMonthOfDay(params.get("startYear").toString(),params.get("startMonth").toString());
            	Struts2Utils.getRequest().setAttribute("monthOfDays", monthOfDays);
            }
        }
        return "report-body";
    }	

    /**
     * 去掉日期中带"-"后的值
     * @param date
     * @return
     * @throws Exception
     */
    public String getReplaceVal(String date) throws Exception{
    	if(StringUtils.isNotBlank(date)){
	    	if(date.contains("-"))
				return date.replaceAll("-", "");
    	}
    	return null;
    }
    /**
     * 获取当前日期(格式：2012-12-12)
     * @return
     * @throws Exception
     */
    public String getCurrentDate() throws Exception{
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
    	return sdf.format(new Date());
    }
    
    /**
     * 获取当前选中月的天数
     * @param year
     * @param month
     * @return
     * @throws Exception
     */
    public Integer getCurrentMonthOfDay(String year,String month) throws Exception{
    	Calendar calendar = Calendar.getInstance(Locale.CHINA);
    	//每次必须得清除
    	calendar.clear();
    	calendar.set(Calendar.YEAR, Integer.parseInt(year));
    	//月份默认是从0开始
    	calendar.set(Calendar.MONTH,Integer.parseInt(month)-1);
    	return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 远程 调用 接口
     * 统一处理 ，方便维护
     */
    public String ajax() throws Exception {
        String currentUiid = SpringSecurityUtils.getCurrentUiid();
        Map result = new HashMap() {{
            put("success", true);
            put("message", "返回消息！");
        }};
        int action = Integer.valueOf(Struts2Utils.getParameter("action"));
        if (action < 1) {
            result.put("success", false);
            result.put("message", "参数异常！");
        } else {
            switch (action) {
                case 1001://保存数据
                    if (StringUtils.isBlank(entity.getBisManageDayId())) {
                        entity.setStatus(BisManageDayManager.STATUS_INIT);
                        entity.setBisProject(project);
                    }
                    try {
                        bisManageDayManager.saveBisManageDay(entity);
                        result.put("message", "操作成功！");
                    } catch (Exception ex) {
                        result.put("success", false);
                        result.put("message", ex.getMessage());
                    }
                    break;
                case 1002://删除数据
                    bisManageDayManager.delete(entity);
                    result.put("message", "操作成功！");
                    break;
            }
        }
        Struts2Utils.renderJson(result);
        return null;
    }

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();

        if (calendar.get(Calendar.HOUR_OF_DAY) > 12) {
            calendar.set(Calendar.HOUR_OF_DAY, 12);
        } else {
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 1);
        }
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.MILLISECOND, 0);
//        calendar.set(Calendar.HOUR, 12);

    }
}
