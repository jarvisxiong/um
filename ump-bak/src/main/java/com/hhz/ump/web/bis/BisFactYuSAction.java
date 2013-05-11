package com.hhz.ump.web.bis;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.bis.BisFactManager;
import com.hhz.ump.dao.bis.BisFactYuSManager;
import com.hhz.ump.dao.bis.BisMustManager;
import com.hhz.ump.dao.bis.BisShopManager;
import com.hhz.ump.dao.bis.BisTenantManager;
import com.hhz.ump.entity.bis.BisFactYuS;
import com.hhz.ump.entity.bis.BisMust;
import com.hhz.ump.entity.bis.ShopStoreVo;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.DictMapUtil;
import com.hhz.ump.util.HelperUtil;
import com.hhz.ump.util.Util;
import com.hhz.ump.web.vo.VoFact;

public class BisFactYuSAction extends CrudActionSupport<BisFactYuS> {
    private BisFactYuS entity;
    private String bisProjectId;
    private String tenant;
    private Map<String, String> tenantMap;
    @Autowired
    private BisFactYuSManager bisFactYuSManager;
    @Autowired
    private BisFactManager bisFactManager;
    @Autowired
    private BisTenantManager bisTenantManager;
    @Autowired
    private BisShopManager bisShopManager;
    
    @Autowired
	private BisMustManager bisMustManager;
    
    private VoFact voFact;

    //工具辅助类
    public HelperUtil helper = new HelperUtil();

    @Override
    public BisFactYuS getModel() {
        return entity;
    }

    @Override
    public String deleteBatch() throws Exception {
        return null;
    }

    @Override
    public String list() throws Exception {
        return null;
    }

    @Override
    public void prepareInput() throws Exception {
        prepareModel();
    }

    @Override
    public String input() throws Exception {
        return "input";
    }

    public void prepareEdit() throws Exception {
        prepareModel();
    }
 
    public String edit() throws Exception {
        tenant = bisShopManager.getShopName(entity.getBisTenantId());
        return "edit";
    }
    

    @Action(results = @Result(location = "bis-fact-yu-s-dichong.ftl"))
    public String dichong() throws Exception {
        prepareModel();
        tenant = bisShopManager.getShopName(entity.getBisTenantId());
        Struts2Utils.getRequest().setAttribute("chargeTypes", DictMapUtil.getMapChargeType());
        return SUCCESS;
    }

    public String dichongSave() throws Exception {
    	entity = bisFactYuSManager.getEntity(getId());
    	bisFactYuSManager.dichong(entity);
    	
    	 /***********处理先有应收明细，新增预收抵充后，应收明细中对应的实收金额会更新  author:wangming start ************/
    	VoFact voFact = new VoFact();
        String chargeTypeCd = Struts2Utils.getParameter("chargeTypeCd");
    	String factYear = Struts2Utils.getParameter("factYear");
    	String factMonth = Struts2Utils.getParameter("factMonth");
    	System.out.println(entity.getBisFactId()+"  bisTenantId : "+entity.getBisTenantId()+"  "+entity.getBisProjectId()+"   "+chargeTypeCd + "  "+factYear + "  "+factMonth);
    	voFact.setBisFactYuSId(entity.getBisFactId());
    	voFact.setBisTenantId(entity.getBisTenantId());
        voFact.setBisProjectId(entity.getBisProjectId());
        //费用类别
        voFact.setChargeTypeCd(chargeTypeCd);
        //年
        voFact.setFactYear(factYear);
        //月
        voFact.setFactMonth(factMonth);
    	//实收金额(所有金额)
        Double dichongMoney = bisFactManager.getAllDichongMoney(voFact);
        voFact.setMoney(BigDecimal.valueOf(dichongMoney));
        
//        Struts2Utils.getRequest().setAttribute("bisFactId", entity.getBisFactId());
//        Struts2Utils.getRequest().setAttribute("bisProjectId", entity.getBisProjectId());
//        Struts2Utils.getRequest().setAttribute("chargeTypeCd", chargeTypeCd);
//        Struts2Utils.getRequest().setAttribute("factYear", factYear);
//        Struts2Utils.getRequest().setAttribute("factMonth", factMonth);
        
        bisMustManager.updateBisMustMoney(voFact);
        /************处理应收金额存在，而实收没有更新的情况  author:wangming end  ************/
        //获取应收明细表中的应收金额
        BisMust bisMust = bisMustManager.getBisMustMoney(voFact);
        //更新收费历史记录中的应收金额
        voFact.setMustMoney(bisMust.getMoney());
        bisFactManager.updateBisFactMoney(voFact);
        Struts2Utils.renderText("success");
        return null;
    }

    @Override
    public void prepareSave() throws Exception {
        prepareModel();
    }

    @Override
    public String save() throws Exception {
        entity.setRemark((entity.getRemark())); 
        entity.setSurplusMoney(entity.getMoney());
        //entity
        bisFactYuSManager.saveBisFactYuS(entity);
        Struts2Utils.renderText("success");
        return null;
    }

    @Override
    public String delete() throws Exception {
        bisFactYuSManager.deleteBisFactYuS(Struts2Utils.getParameter("id"));
        return null;
    }

    @Override
    protected void prepareModel() throws Exception {
        if (StringUtils.isNotBlank(getId())) {
            entity = bisFactYuSManager.getEntity(getId());
            //处理是否已抵充
            Integer dichongCount = bisFactManager.getDichongCount(getId());
            Struts2Utils.getRequest().setAttribute("hasDichong", dichongCount);
        } else {
            entity = new BisFactYuS();
            entity.setStatusCd(DictContants.BIS_FACT_NO_CHECK);
        }
    }
    
    public String passAll() {
        String bisFactIds = Struts2Utils.getParameter("bisFactIds");
        String[] tmp = bisFactIds.split(",");
        for (int i = 0; i < tmp.length; i++) {
            bisFactYuSManager.factPass(SpringSecurityUtils.getCurrentUiid(), tmp[i]);
        }
        Struts2Utils.renderText("success");
        return null;
    }

    public void preparePass() throws Exception {
        prepareModel();
    }

    // 审核通过
    public void pass() {
        entity.setStatusCd(DictContants.BIS_AUDIT_PASS);
        entity.setCheckUserCd(SpringSecurityUtils.getCurrentUiid());
        //FIXME: 待获取审核人
        //entity.setCheckUserCd(checkUserCd);
        Date now = new Date();
        entity.setCheckDate(now);
        entity.setUpdatedDate(now);
        bisFactYuSManager.saveBisFactYuS(entity);
    }

    public void prepareReject() throws Exception {
        prepareModel();
    }

    // 驳回
    public void reject() {
        entity.setStatusCd(DictContants.BIS_AUDIT_REJECT);
        //FIXME: 待获取审核人
        //entity.setCheckUserCd(checkUserCd);
        Date now = new Date();
        entity.setCheckDate(now);
        entity.setUpdatedDate(now);
        bisFactYuSManager.saveBisFactYuS(entity);

    }

    private void pageSet() {
        voFact = new VoFact();
        String tmp = Struts2Utils.getParameter("bisTenantId");
        if (bisFactManager.notBlank(tmp)) {
            voFact.setBisTenantId(tmp.trim());
        }
        tmp = Struts2Utils.getParameter("bisProjectId");
        if (bisFactManager.notBlank(tmp)) {
            voFact.setBisProjectId(tmp);
        }
        tmp = Struts2Utils.getParameter("factYear");
        if (bisFactManager.notBlank(tmp)) {
            voFact.setFactYear(tmp);
        }
        tmp = Struts2Utils.getParameter("factMonth");
        if (bisFactManager.notBlank(tmp)) {
            voFact.setFactMonth(tmp);
        }
        tmp = Struts2Utils.getParameter("creator");
        if (bisFactManager.notBlank(tmp)) {
            voFact.setCreator(tmp);
        }
        tmp = Struts2Utils.getParameter("statusCd");
        if (bisFactManager.notBlank(tmp)) {
            voFact.setStatusCd(tmp);
        }
        tmp = Struts2Utils.getParameter("minMonth");
        if (bisFactManager.notBlank(tmp)) {
            voFact.setMinMonth(tmp);
        }
        tmp = Struts2Utils.getParameter("maxMonth");
        if (bisFactManager.notBlank(tmp)) {
            voFact.setMaxMonth(tmp);
        }
        String factDate = Struts2Utils.getParameter("factDate");
        if (factDate != null && bisFactManager.notBlank(factDate)) {
            voFact.setFactDate(factDate);
        }
        String minMoney = Struts2Utils.getParameter("minMoney");
        if (bisFactManager.notBlank(minMoney)) {
            voFact.setMinMoney(new BigDecimal(minMoney));
        }
        String mustMoney = Struts2Utils.getParameter("mustMoney");
        if (bisFactManager.notBlank(mustMoney)) {
            voFact.setMustMoney(new BigDecimal(mustMoney));
        }
        String maxMoney = Struts2Utils.getParameter("maxMoney");
        if (bisFactManager.notBlank(maxMoney)) {
            voFact.setMaxMoney(new BigDecimal(maxMoney));
        }
        String factInBegin = Struts2Utils.getParameter("factInBegin");
        if (bisFactManager.notBlank(factInBegin)) {
            voFact.setInDateBegin(factInBegin);
        }
        String factInEnd = Struts2Utils.getParameter("factInEnd");
        if (bisFactManager.notBlank(factInEnd)) {
            voFact.setInDateEnd(factInEnd);
        }
        String checkUserCd = Struts2Utils.getParameter("checkUserCd");
        if (bisFactManager.notBlank(checkUserCd)) {
            voFact.setCheckUserCd(checkUserCd);
        }
        
        //点击预收明细历史记录操作，计算是否有抵充记录，如果有，则计算出，直接更新
    	String bisProjectId = Struts2Utils.getParameter("bisProjectId");
    	if(StringUtils.isNotBlank(bisProjectId)){
    		List<BisFactYuS> bisFactYuSList = bisFactYuSManager.getBisFactYuSList(bisProjectId);
    		if(null !=bisFactYuSList && bisFactYuSList.size()>0) {
				for (BisFactYuS vo :bisFactYuSList) {
					Double dichongMoney = bisFactManager.getAllDichongMoney(bisProjectId,vo.getBisFactId());
					Log.debug("money: "+vo.getMoney()+" ,surplusMoney: "+vo.getSurplusMoney()+" , dichongMoney: "+dichongMoney+", total : "+(Double.valueOf(vo.getMoney().toString()) - dichongMoney));
			    	bisFactYuSManager.updateBatchProcessing(vo.getBisFactId(),BigDecimal.valueOf(Double.valueOf(vo.getMoney().toString()) - dichongMoney));
				}
    		}
    	}
    }


    public String searchForStoreYuShou() {
        pageSet();
        StringBuffer sql = new StringBuffer();
        sql.append(" 	 from BisFactYuS s where 1=1 ")
        // .append(" 	where s.bisProjectId:= bisProjectId")
        ;
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("bisProjectId", bisProjectId);
        if (StringUtils.isNotBlank(tenant)) {
            List<ShopStoreVo> tenants = bisTenantManager.getTenants(bisProjectId, tenant, "");
            if (tenants != null && tenants.size() > 0) {
                sql.append(" and s.bisTenantId in (");
                
                for (int i = 0; i < tenants.size(); i++) {
                    ShopStoreVo vo = tenants.get(i);
                    if (i == 0) {
                        sql.append(":bisTenantId").append(i);
                    } else {
                        sql.append(",").append(":bisTenantId").append(i);
                    }
                    values.put("bisTenantId" + i, vo.getBisTenantId());
                }
                sql.append(" )");
            }
            
        }
        if (StringUtils.isNotBlank(bisProjectId)) {
            sql.append(" and s.bisProjectId=:bisProjectId ");
            values.put("bisProjectId", bisProjectId);
        }
        if (StringUtils.isNotBlank(voFact.getStatusCd())) {
            sql.append(" and s.statusCd=:statusCd ");
            values.put("statusCd", voFact.getStatusCd());
        }
        if (StringUtils.isNotBlank(voFact.getCreator())) {
            sql.append(" and s.creator=:creator ");
            values.put("creator", voFact.getCreator());
        }
        if (StringUtils.isNotBlank(voFact.getCheckUserCd())) {
            sql.append(" and s.checkUserCd=:checkUserCd ");
            values.put("checkUserCd", voFact.getCheckUserCd());
        }
        if (null != voFact.getMaxMoney()) {
            sql.append(" and s.money<=:maxMoney ");
            values.put("maxMoney", voFact.getMaxMoney());
        }
        if (null != voFact.getMinMoney()) {
            sql.append(" and s.money>=:minMoney ");
            values.put("minMoney", voFact.getMinMoney());
        }
        if (StringUtils.isNotBlank(voFact.getMinMonth())) {
            sql.append(" and s.factDate>=to_date(:minMonth,'yyyy-MM-dd') ");
            values.put("minMonth", voFact.getMinMonth());
        }
        if (StringUtils.isNotBlank(voFact.getMaxMonth())) {
            sql.append(" and s.factDate<=to_date(:maxMonth,'yyyy-MM-dd') ");
            values.put("maxMonth", voFact.getMaxMonth());
        }
        Util.obj2Param(voFact, values);
        
        bisFactYuSManager.findPage(page, sql.toString(), values);
        tenantMap = new HashMap<String, String>();
        for (BisFactYuS vo : page.getResult()) {
            if (StringUtils.isNotBlank(vo.getBisTenantId())) {
                tenantMap.put(vo.getBisTenantId(), bisShopManager.getShopName(vo.getBisTenantId()));
            }
        }
        //获取所有抵充后的值
//        Double dichongMoney = bisFactManager.getAllDichongMoney(getId());
//        Struts2Utils.getRequest().setAttribute("dichongMoney", dichongMoney);
        return "list";

    }
    
    public String getBisProjectId() {
        return bisProjectId;
    }

    public void setBisProjectId(String bisProjectId) {
        this.bisProjectId = bisProjectId;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public String getTenantName() {
        // return bisTenantManager.getMapBisTenant(this.)
        return null;
    }

    public Map<String, String> getTenantMap() {
        return tenantMap;
    }

    public void setTenantMap(Map<String, String> tenantMap) {
        this.tenantMap = tenantMap;
    }
}
