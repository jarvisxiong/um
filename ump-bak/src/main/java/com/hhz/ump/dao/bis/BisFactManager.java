package com.hhz.ump.dao.bis;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.NumberUtil;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.cache.BisCache;
import com.hhz.ump.entity.bis.BisCont;
import com.hhz.ump.entity.bis.BisFact;
import com.hhz.ump.entity.bis.BisFlat;
import com.hhz.ump.entity.bis.BisFlatContRel;
import com.hhz.ump.entity.bis.BisFloor;
import com.hhz.ump.entity.bis.BisMulti;
import com.hhz.ump.entity.bis.BisMultiContRel;
import com.hhz.ump.entity.bis.BisMust;
import com.hhz.ump.entity.bis.BisShop;
import com.hhz.ump.entity.bis.BisStore;
import com.hhz.ump.entity.bis.BisStoreContRel;
import com.hhz.ump.entity.bis.BisTenant;
import com.hhz.ump.entity.bis.PayRequisitionVo;
import com.hhz.ump.util.DateUtil;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.DictMapUtil;
import com.hhz.ump.util.Util;
import com.hhz.ump.web.vo.VoCombo;
import com.hhz.ump.web.vo.VoFact;
import com.hhz.ump.web.vo.VoFactOpera;

@Service
@Transactional
@SuppressWarnings({"rawtypes", "unused", "unchecked"})
public class BisFactManager extends BaseService<BisFact, String> {
    private static Log log = LogFactory.getLog(BisFactManager.class);
    @Autowired
    private BisFactDao bisFactDao;
    @Autowired
    private BisFactYuSDao bisFactYuSDao;
    @Autowired
    private BisContDao bisContDao;
    @Autowired
    private BisMustManager bisMustManager;
    @Autowired
    private BisTenantManager bisTenantManager;
    @Autowired
    private BisFlatContRelManager bisFlatContRelManager;
    @Autowired
    private BisShopManager bisShopManager;
    @Autowired
    private BisFlatDao bisFlatDao;
    @Autowired
    private BisMultiDao bisMultiDao;
    @Autowired
    private BisStoreDao bisStoreDao;
    @Autowired
    private BisStoreContRelManager bisStoreContRelManager;
    public static final int BIS_FACT_CHECK_NO = 6;// 提交申请，待审核
    public static final int BIS_FACT_CHECK_PASS = 5;// 审核通过
    public static final int BIS_FACT_CHECK_REJECT = 4;// 审核驳回
    public static final int BIS_FACT_MODIFY = 8;// 更新
    public static final int BIS_FACT_REMOVE = 7;// 删除
    public static final int BIS_FACT_DEDUCT = 9;// 扣除

    public static final int BIS_CONT_TYPE_STORE = 1;// 商铺
    public static final int BIS_CONT_TYPE_FLAT = 2;// 公寓
    public static final int BIS_CONT_TYPE_MULTI = 3;// 多经

    public void saveBisFact(BisFact bisFact) {
        PowerUtils.setEmptyStr2Null(bisFact);
        bisFactDao.save(bisFact);
    }

    public void deleteBisFact(String id) {
        bisFactDao.delete(id);
    }

    @Override
    public HibernateDao<BisFact, String> getDao() {
        return bisFactDao;
    }

    public void factRemove(String uiid, String bisFactId) {
        factOpera(uiid, getEntity(bisFactId), BIS_FACT_REMOVE);
    }

    public void factRemove(String uiid, BisFact fact) {
        factOpera(uiid, fact, BIS_FACT_REMOVE);
    }

    public void factUpdate(String uiid, String bisFactId) {
        factOpera(uiid, getEntity(bisFactId), BIS_FACT_MODIFY);
    }

    public void factPass(String uiid, String bisFactId) {
        factOpera(uiid, getEntity(bisFactId), BIS_FACT_CHECK_PASS);
    }

    public void factReject(String uiid, String bisFactId) {
        factOpera(uiid, getEntity(bisFactId), BIS_FACT_CHECK_REJECT);
    }

    public void factInput2Store(String uiid, BisFact fact) {
        factOpera(uiid, fact, BIS_CONT_TYPE_STORE);
    }

    public void factInput2Flat(String uiid, BisFact fact) {
        factOpera(uiid, fact, BIS_CONT_TYPE_FLAT);
    }

    public void factInput2Multi(String uiid, BisFact fact) {
        factOpera(uiid, fact, BIS_CONT_TYPE_MULTI);
    }

    public void factOpera(String uiid, String bisFactId, int opera) {
        factOpera(uiid, getEntity(bisFactId), opera);
    }

    public void factOperae(String uiid, BisFact bisFact, int opera) {
        factOpera(uiid, bisFact, opera);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void factOpera(String uiid, BisFact fact, int opera) {
        factOpera(uiid, fact, opera, "");
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void factOpera(String uiid, BisFact fact, int opera, String bisProjectId) {
        StringBuffer operaLog = new StringBuffer();
        VoFactOpera check = null;
        switch (opera) {
            case BIS_FACT_CHECK_PASS: {
                fact.setStatusCd(DictContants.BIS_FACT_PASS);
                fact.setCheckUserCd(uiid);
                fact.setCheckDate(DateUtil.getCurrtDate());
                if (notBlank(fact.getParentId())) {
                    // 如果此记录是抵扣款
                    BisFact parentFact = this.getEntity(fact.getParentId());
                    parentFact.setDeductMoney(fact.getMoney());// 需扣除金额
                    parentFact.setMoney(parentFact.getMoney().subtract(fact.getMoney()));// 余额
                    saveBisFact(parentFact);
                }
                if (notBlank(fact.getBisTenantId())) {
                    bisTenantManager.refreshTenantDebtInfo(fact.getBisTenantId());
                }
                operaLog.append("[实收记录编号：").append(fact.getBisFactId()).append("  审核通过]");
                break;
            }
            case BIS_FACT_CHECK_REJECT: {
                fact.setStatusCd(DictContants.BIS_FACT_REJECT);
                fact.setCheckUserCd(uiid);
                fact.setCheckDate(DateUtil.getCurrtDate());
                operaLog.append("[实收记录编号：").append(fact.getBisFactId()).append("  审核驳回]");
                break;
            }
            case BIS_FACT_CHECK_NO: {
                BisTenant tenant = bisTenantManager.getEntity(fact.getBisTenantId());
                operaLog.append("[实收租户(").append(tenant.getConnName()).append(")金额人民币:").append(fact.getMoney())
                        .append("]");
                break;
            }
            case BIS_CONT_TYPE_STORE: {// 录入商铺实收记录
                check = saveCheck(BIS_CONT_TYPE_STORE, bisProjectId, fact);
                BisTenant tenant = bisTenantManager.getEntity(fact.getBisTenantId());
                operaLog.append("[实收租户(").append(tenant.getConnName()).append(")金额:").append(fact.getMoney()).append("]");
                fact.setBisShopId(tenant.getBisShopId());
                break;
            }
            case BIS_CONT_TYPE_FLAT: {// 录入公寓实收记录
                check = saveCheck(BIS_CONT_TYPE_FLAT, bisProjectId, fact);
                if (StringUtils.isNotBlank(fact.getBisFlatId())) {
                    BisFlat flat = bisFlatDao.get(fact.getBisFlatId());
                    operaLog.append("[公寓编号(").append(flat.getFlatNo()).append(")录入金额:").append(fact.getMoney()).append("]");
                }
                break;
            }
            case BIS_CONT_TYPE_MULTI: {// 录入多经实收记录
                check = saveCheck(BIS_CONT_TYPE_MULTI, bisProjectId, fact);
                /*
                 * BisMulti multi = bisMultiDao.get(fact.getBisMultiId());
                 * operaLog.append("[实收多经项目(") .append(multi.getMultiName())
                 * .append(")金额:").append(fact.getMoney()).append("]");
                 */
                break;
            }
            case BIS_FACT_REMOVE: {// 删除：校验是否有权限，是否已审核
                operaLog.append("删除实收记录[").append("录入人：").append(fact.getCreator()).append(",录入时间:")
                        .append(fact.getFactDate()).append(",录入对应合同:").append(fact.getBisContId()).append(",金额:")
                        .append(fact.getMoney()).append("]");
                this.delete(fact);
                return;
            }
            case BIS_FACT_MODIFY: {// 修改：校验是否有权限，是否已审核
                operaLog.append("修改实收记录[").append("录入人：").append(fact.getCreator()).append(",录入时间:")
                        .append(fact.getFactDate()).append(",录入对应合同:").append(fact.getBisContId()).append(",金额:")
                        .append(fact.getMoney()).append("]");
                break;
            }
        }
        if (null != check
                && (VoFactOpera.BIS_CONT_CONFLICT_04 == check.getType() || VoFactOpera.BIS_CONT_CONFLICT_04 == check
                .getType()))
            // 抛出异常
            return;
        saveBisFact(fact);
        operaLog.append(" 操作人：").append(uiid);
        // 记录操作日志
    }

    public boolean factDeduct(String uiid, String type, BisFact parentFact, BisFact fact, String currDetail) {
        // 校验抵扣目标月份是否可以录入实收
        String bisDetailId = null;
        switch (Integer.valueOf(type)) {
            case 1:
                fact.setBisTenantId(currDetail);
                break;
            case 2:
                fact.setBisFlatId(currDetail);
                break;
            case 3:
                fact.setBisMultiId(currDetail);
                break;
        }
        /*
           * TODU 数据不对，暂时关闭 if(VoFactOpera.BIS_CONT_CONFLICT_04==
           * factCheck(Integer.valueOf(type), fact.getBisProjectId(), currDetail,
           * fact.getChargeTypeCd(), fact.getFactYear(),
           * fact.getFactMonth()).getType()) return false;
           */
        // 校验余额是否充足
        countFactDeduct(parentFact.getBisFactId());
        if (parentFact.getMoney().subtract(fact.getMoney().add(countFactDeduct(parentFact.getBisFactId()))).intValue() > 0) {
            fact.setStatusCd(DictContants.BIS_FACT_NO_CHECK);
            fact.setDisposableMoney(fact.getMoney());
            fact.setParentId(parentFact.getBisFactId());
            // 实收时间与预收款同步
            fact.setFactDate(parentFact.getFactDate());
            fact.setBisContId(parentFact.getBisContId());
            // 新增抵扣的实收
            this.saveBisFact(fact);
            /*
                * //修改预收款 这个动作应该放在抵扣审核通过之后
                * parentFact.setDeductMoney(fact.getMoney());//需扣除金额
                * parentFact.setMoney
                * (parentFact.getMoney().subtract(fact.getMoney()));//余额
                * this.saveBisFact(parentFact);
                */
            return true;
        } else
            return false;
    }

    /**
     * 搜索实收 条件：合同id、年、月、费用类别
     */
    public List<BisFact> getFactList(String bisContId, String year, String month, String chargeTypeCd) {
        StringBuffer hSql = new StringBuffer("from BisFact f where 1=1 ");
        this.appendIsNotBlankSql(hSql, "and f.chargeTypeCd =:chargeTypeCd", chargeTypeCd);
        this.appendIsNotBlankSql(hSql, "and f.bisContId =:bisContId", bisContId);
        this.appendIsNotBlankSql(hSql, "and f.factYear =:year", year);
        this.appendIsNotBlankSql(hSql, "and f.factMonth =:month", month);
        Map map = new HashMap<String, Object>();
        map.put("chargeTypeCd", chargeTypeCd);
        map.put("bisContId", bisContId);
        map.put("month", month);
        map.put("year", year);
        return find(hSql.toString(), map);


        /*	if(StringUtils.isNotBlank(bisContId)){
                    hSql.append("  ");
                }
                if(StringUtils.isNotBlank(year)){
                    hSql.append(" and  f.factYear=:year ");
                    map.put("year", year);
                }
                if(StringUtils.isNotBlank(year)){
                    hSql.append(" and  f.factYear=:year ");

                }
                String sql = " from BisFact f where f.bisContId =:bisContId and f.factYear=:year and f.factMonth =:month and f.chargeTypeCd =:chargeTypeCd";
        */

    }

    private void appendIsNotBlankSql(StringBuffer hSql, String appendSql, String val) {

        if (StringUtils.isNotBlank(val)) {
            hSql.append(" ").append(appendSql).append(" ");
        }
    }

    /**
     * 搜索实收总和 条件：合同id、年、月、费用类别
     */
    public BigDecimal getFactSum(String bisContId, String year, String month, String chargeTypeCd) {
        BigDecimal sum = new BigDecimal(0);
        for (BisFact vo : getFactList(bisContId, year, month, chargeTypeCd)) {
            sum = sum.add(vo.getMoney());
        }
        return sum;
    }

    public BigDecimal countFactDeduct(String parentId) {
        String sql = " select count(f.money) from bis_fact f where f.parent_id =:parentId";
        Map map = new HashMap<String, Object>();
        map.put("parentId", parentId);
        List<Object> result = this.getDao().findBySql(sql, map);
        for (Object o : result)
            return (BigDecimal) o;
        return new BigDecimal(0);
    }

    /**
     * 校验合同是否冲突 1 若费用类型为物业履约保证费|租金履约保证费|经营保证费|广告收入|停车费，无需校验 2
     * 条件1不成立，若满足条件合同没有当月应收，报错 3 条件1不成立，若满足条件合同存在多条应收，并且合同时间冲突，报错
     *
     * @throws Exception
     */
    /**
     * 1 费用类型为保证金，无年月，无合同 2 若费用类型为物业履约保证费|租金履约保证费|经营保证费|广告收入|停车费，无应收款，无合同 3
     * 存在唯一合同，关联应收:应收费用类别，年，月，合同表项目id，租户id， 4 存在多条合同，不冲突，合并应收：多条应收，合并 5 冲突，报错
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    public VoFactOpera factCheckForStore(String bisProjectId, String bisTenantId, String chargeTypeCd, String year,
                                         String month) {
        return factCheck(BIS_CONT_TYPE_STORE, bisProjectId, bisTenantId, chargeTypeCd, year, month);
    }

    public VoFactOpera factCheckForFlat(String bisProjectId, String bisFlatId, String chargeTypeCd, String year,
                                        String month) {
        return factCheck(BIS_CONT_TYPE_FLAT, bisProjectId, bisFlatId, chargeTypeCd, year, month);
    }

    public VoFactOpera factCheckForMulti(String bisProjectId, String bisMultiId, String chargeTypeCd, String year,
                                         String month) {
        return factCheck(BIS_CONT_TYPE_MULTI, bisProjectId, bisMultiId, chargeTypeCd, year, month);
    }

    public VoFactOpera factCheck(int type, String bisProjectId, String bisDetailId, String chargeTypeCd, String year,
                                 String month) {
        if (DictContants.BIS_CHARGE_TYPE_02.equals(chargeTypeCd)
                || DictContants.BIS_CHARGE_TYPE_03.equals(chargeTypeCd)
                || DictContants.BIS_CHARGE_TYPE_38.equals(chargeTypeCd))
            return new VoFactOpera(VoFactOpera.BIS_CONT_CONFLICT_NO_00, "");
        else if (DictContants.BIS_CHARGE_TYPE_41.equals(chargeTypeCd)
                || DictContants.BIS_CHARGE_TYPE_42.equals(chargeTypeCd))
            return new VoFactOpera(VoFactOpera.BIS_CONT_CONFLICT_NO_01, "");

        StringBuffer hql = new StringBuffer();

        switch (type) {
            case BIS_CONT_TYPE_STORE:
                hql.append("select m.bisCont from BisMust m  ").append(" where ").append("  m.chargeTypeCd =?")
                        .append(" and m.mustYear=?").append(" and m.mustMonth=?").append(" and m.bisCont.bisProjectId=?")
                        .append(" and m.bisTenantId=?");
                break;
            case BIS_CONT_TYPE_FLAT:
                // 公寓应收可有可无
                return new VoFactOpera(VoFactOpera.BIS_CONT_CONFLICT_NO_00, "");
            case BIS_CONT_TYPE_MULTI:
                hql.append("select m.bisCont from BisMust m,BisMulti mu   ")
                        .append(" where m.bisCont.bisContId = mu.bisContId").append(" and m.chargeTypeCd =?")
                        .append(" and m.mustYear=?").append(" and m.mustMonth=?").append(" and mu.bisProjectId=?")
                        .append(" and mu.bisMultiId=?");
                break;
        }
        List<BisCont> tmpCont = this.getDao()
                .createQuery(hql.toString(), chargeTypeCd, year, month, bisProjectId, bisDetailId).list();
        StringBuffer sb = new StringBuffer();
        // 存在多条应收，判断费用类别等于'chargeTypeCd'等应收合同时间是否冲突
        if (null != tmpCont && tmpCont.size() > 1) {
            BisCont tmpA = null;
            BisCont tmpB = null;
            BisTenant tenant = bisTenantManager.getEntity(bisDetailId);
            for (int i = 0; i < tmpCont.size(); i++) {
                tmpA = tmpCont.get(i);
                for (int j = i + 1; j < tmpCont.size(); j++) {
                    tmpB = tmpCont.get(j);
                    if (!DateUtil.checkDateConflict(tmpA.getContStartDate(), tmpA.getContEndDate(),
                            tmpB.getContStartDate(), tmpB.getContEndDate())) {

                        // 合同冲突，报错
                        sb.append(tenant.getConnName()).append(" 所签合同【").append(tmpA.getContName()).append(",")
                                .append(tmpB.getContName()).append("]时间冲突\n");
                        return new VoFactOpera(VoFactOpera.BIS_CONT_CONFLICT_04, sb.toString());
                    }

                }
            }
            return new VoFactOpera(VoFactOpera.BIS_CONT_CONFLICT_NO_03, "");
        } else if (null == tmpCont || tmpCont.size() == 0) {
            // 没有应收，报错
            sb.append("错误：没有应收金额");
            return new VoFactOpera(VoFactOpera.BIS_CONT_CONFLICT_04, sb.toString());
        }
        return new VoFactOpera(VoFactOpera.BIS_CONT_CONFLICT_NO_02, "");
    }

    /**
     * 保存之前做校验 已经校验过合同是否冲突，不再校验 获取项目、租户、费用类别、年月条件下所有的商铺，合同，应收。
     * 合并应收值，拼接商铺编号，合同编号，并赋值给BisFact
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    public VoFactOpera saveCheck(int type, String bisProjectId, BisFact fact) {
        // 同步预存金额
        if (null != fact.getMoney()) {
            fact.setDisposableMoney(fact.getMoney());
        }
        if (DictContants.BIS_CHARGE_TYPE_02.equals(fact.getChargeTypeCd())
                || DictContants.BIS_CHARGE_TYPE_03.equals(fact.getChargeTypeCd())
                || DictContants.BIS_CHARGE_TYPE_38.equals(fact.getChargeTypeCd())

                ) {
            if (blank(fact.getFactYear())) {
                fact.setFactYear("");
            }
            if (blank(fact.getFactMonth())) {
                fact.setFactMonth("");
            }
            return new VoFactOpera(VoFactOpera.BIS_CONT_CONFLICT_NO_00, "");
        } else if (DictContants.BIS_CHARGE_TYPE_41.equals(fact.getChargeTypeCd())
                || DictContants.BIS_CHARGE_TYPE_42.equals(fact.getChargeTypeCd()))
            return new VoFactOpera(VoFactOpera.BIS_CONT_CONFLICT_NO_01, "");

        StringBuffer hql = new StringBuffer();

        String detailId = null;
        switch (type) {
            case BIS_CONT_TYPE_STORE:
                detailId = fact.getBisTenantId();
                hql.append("select m from BisMust m  ").append(" where  m.chargeTypeCd =?").append(" and m.mustYear=?")
                        .append(" and m.mustMonth=?").append(" and m.bisCont.bisProjectId=?")
                        .append(" and m.bisTenantId=?");
                break;
            case BIS_CONT_TYPE_FLAT:
                detailId = fact.getBisFlatId();
                hql.append("select m from BisMust m,BisFlat f,BisFlatContRel  r ")
                        .append(" where m.bisCont.bisContId in ( r.bisCont.bisContId ) and f.bisFlatId = r.bisFlat.bisFlatId")
                        .append(" and m.chargeTypeCd =?").append(" and m.mustYear=?").append(" and m.mustMonth=?")
                        .append(" and f.bisProjectId=?").append(" and f.bisFlatId=?");
                break;
            case BIS_CONT_TYPE_MULTI:
                detailId = fact.getBisMultiId();
                hql.append("select m from BisMust m,BisMulti mu   ").append(" where m.bisCont.bisContId = mu.bisContId")
                        .append(" and m.chargeTypeCd =?").append(" and m.mustYear=?").append(" and m.mustMonth=?")
                        .append(" and mu.bisProjectId=?").append(" and mu.bisMultiId=?");
                break;
        }
        List<BisMust> tmpMust = this
                .getDao()
                .createQuery(hql.toString(), fact.getChargeTypeCd(), fact.getFactYear(), fact.getFactMonth(),
                        fact.getBisProjectId(), fact.getBisTenantId()).list();
        StringBuffer sb = new StringBuffer();
        /*
           * StringBuffer bisContIds = new StringBuffer(); ;
           *
           * for (BisMust must : tmpMust) {
           * bisContIds.append(must.getBisCont().getBisContId()).append(","); }
           * fact.setBisContId(bisContIds.toString());// TODO
           * 合同字段可能多个，建议新建一张实收合同表。
           */
        if (BIS_CONT_TYPE_STORE == type) {
            // TODU 目前商铺编号值从页面上选择
            saveCheckForStore(fact, tmpMust);
        }
        return new VoFactOpera(VoFactOpera.BIS_CONT_CONFLICT_NO_02, "");
    }

    public String getFloorNum(String[] bisStoreNo) {
        Map values = new HashMap<String, Object>();
        values.put("storeNos", bisStoreNo);
        List<BisFloor> floor = this.getDao().find(
                " select f from BisStore s ,BisFloor f "
                        + "where s.bisFloor.bisFloorId = f.bisFloorId and s.storeNo in (:storeNos)", values);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < floor.size(); i++) {
            BisFloor f = floor.get(i);
            sb.append(f.getFloorNum()).append("-").append(bisStoreNo[i]);
            if (i != 0) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    /**
     * 搜索商铺编号
     */
    public String getStoreNo(String bisTenantId, String bisContId) {
        List<Object> store = getStoreInfo(bisTenantId, bisContId);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < store.size(); i++) {
            Object[] s = (Object[]) store.get(i);
            sb.append((String) s[0]);
            if (i != store.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public void saveCheckForStore(BisFact fact, List<BisMust> tmpMust) {
        String bisStoreIds = getStoreNo(fact.getBisTenantId(), null);
        fact.setBisStoreId(bisStoreIds);
        fact.setBisStoreId(bisStoreIds.toString());
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public VoFactOpera saveCheckForStore(BisFact fact, String bisProjectId) {
        if (DictContants.BIS_CHARGE_TYPE_02.equals(fact.getChargeTypeCd())
                || DictContants.BIS_CHARGE_TYPE_03.equals(fact.getChargeTypeCd())
                || DictContants.BIS_CHARGE_TYPE_38.equals(fact.getChargeTypeCd()))
            return new VoFactOpera(VoFactOpera.BIS_CONT_CONFLICT_NO_00, "");
        else if (DictContants.BIS_CHARGE_TYPE_41.equals(fact.getChargeTypeCd())
                || DictContants.BIS_CHARGE_TYPE_42.equals(fact.getChargeTypeCd()))
            return new VoFactOpera(VoFactOpera.BIS_CONT_CONFLICT_NO_01, "");

        StringBuffer hql = new StringBuffer();
        hql.append("select m from BisCont c,BisMust m   ").append(" where m.bisCont.bisContId = c.bisContId")
                .append(" and m.chargeTypeCd =?").append(" and m.mustYear=?").append(" and m.mustMonth=?")
                .append(" and c.bisTenantId =?").append(" and c.bisProjectId=?");
        List<BisMust> tmpMust = this
                .getDao()
                .createQuery(hql.toString(), fact.getChargeTypeCd(), fact.getFactYear(), fact.getFactMonth(),
                        fact.getBisTenantId(), bisProjectId).list();
        StringBuffer sb = new StringBuffer();
        BigDecimal sumMoney = new BigDecimal(0);
        StringBuffer bisContIds = new StringBuffer();
        ;
        StringBuffer bisStoreIds = new StringBuffer();
        ;
        for (BisMust must : tmpMust) {
            sumMoney = sumMoney.add(must.getMoney());
            bisContIds.append(must.getBisCont().getBisContId()).append(",");
            for (BisStoreContRel rel : must.getBisCont().getBisStoreContRels()) {
                bisStoreIds.append(rel.getBisStore().getBisStoreId()).append(",");
            }
        }
        fact.setMoney(sumMoney);
        fact.setBisContId(bisContIds.toString());
        fact.setBisStoreId(bisStoreIds.toString());
        return new VoFactOpera(VoFactOpera.BIS_CONT_CONFLICT_NO_02, "");
    }

    /**
     * 保存之前做校验 获取项目、多经、费用类别、年月条件下所有的公寓，合同，应收。 合并应收值，
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    public VoFactOpera saveCheckForFlat(BisFact fact, String bisProjectId) {
        if (DictContants.BIS_CHARGE_TYPE_02.equals(fact.getChargeTypeCd())
                || DictContants.BIS_CHARGE_TYPE_03.equals(fact.getChargeTypeCd())
                || DictContants.BIS_CHARGE_TYPE_38.equals(fact.getChargeTypeCd()))
            return new VoFactOpera(VoFactOpera.BIS_CONT_CONFLICT_NO_00, "");
        else if (DictContants.BIS_CHARGE_TYPE_41.equals(fact.getChargeTypeCd())
                || DictContants.BIS_CHARGE_TYPE_42.equals(fact.getChargeTypeCd()))
            return new VoFactOpera(VoFactOpera.BIS_CONT_CONFLICT_NO_01, "");

        StringBuffer hql = new StringBuffer();
        hql.append("select m from BisCont c,BisMust m   ").append(" where m.bisCont.bisContId = c.bisContId")
                .append(" and m.chargeTypeCd =?").append(" and m.mustYear=?").append(" and m.mustMonth=?")
                .append(" and c.bisFlatId =?").append(" and c.bisProjectId=?");
        List<BisMust> tmpMust = this
                .getDao()
                .createQuery(hql.toString(), fact.getChargeTypeCd(), fact.getFactYear(), fact.getFactMonth(),
                        fact.getBisFlatId(), bisProjectId).list();
        StringBuffer sb = new StringBuffer();
        BigDecimal sumMoney = new BigDecimal(0);
        StringBuffer bisContIds = new StringBuffer();
        ;
        for (BisMust must : tmpMust) {
            sumMoney = sumMoney.add(must.getMoney());
            bisContIds.append(must.getBisCont().getBisContId()).append(",");
        }
        fact.setMoney(sumMoney);
        fact.setBisContId(bisContIds.toString());
        return new VoFactOpera(VoFactOpera.BIS_CONT_CONFLICT_NO_02, "");
    }

    /**
     * 搜索公寓应收记录
     *
     * ***************
     * 修改人：qilb 4/28/2012 
     * 增加费用类别，合同ID
     * @param voPage
     * @param voFact
     * @return
     */
    public Page<VoFact> searchMustForFlat(Page voPage, VoFact voFact) {
        Map<Integer, Object> map = paramMap(voFact, voPage);
        map.put(20, voFact.getBuildingNum());
        map.put(21, voFact.getFlatNo());
        Page<BisFact> page = new Page<BisFact>();
        page.setPageSize(30);
        if (voPage.getPageNo() != 0) {
            page.setPageNo(voPage.getPageNo());
        }
        Page<BisMust> mustPage = bisMustManager.queryMustByCont(voPage, voFact);

        voPage.setTotalCount(mustPage.getTotalCount());
        List<VoFact> result = new ArrayList<VoFact>();
        Boolean flag = true;
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
        for (BisMust bisMust : mustPage.getResult()) {
            VoFact fact = new VoFact();
            //如果有合同ID，则从合同模块里面查找相关的floor、flat数据
            if (bisMust.getBisCont() != null) {
                BisCont bisCont = bisMust.getBisCont();
                //合同ID
                fact.setBisContId(bisCont.getBisContId());
                //合同编号
                fact.setContNo(bisCont.getContNo());
                //公寓编号
                StringBuffer flatNo = new StringBuffer("");
                //楼号
                StringBuffer floorNum = new StringBuffer("");
                BisFlat bisFlat = null;
                for (BisFlatContRel rel : bisCont.getBisFlatContRels()) {
                    bisFlat = rel.getBisFlat();
                    flatNo.append(bisFlat.getFlatNo()).append(",");
                    BisFloor floor = bisFlat.getBisFloor();
                    floorNum.append(floor.getBuildingNum()).append(",");
                }
                if (flatNo.toString().length() > 1) {
                    flatNo.delete(flatNo.toString().length() - 1, flatNo.toString().length());
                }
                if (floorNum.toString().length() > 1) {
                    floorNum.delete(floorNum.toString().length() - 1, floorNum.toString().length());
                }
                //实收ID
                fact.setBisFlatId(bisFlat.getBisFlatId());
                fact.setFlatNo(flatNo.toString());
                fact.setBuildingNum(floorNum.toString());
            } else {
                //否则，直接根据实收ID获取相关数据
                BisFlat bisFlat = bisFlatDao.get(bisMust.getBisFlatId());
                fact.setFlatNo(bisFlat.getFlatNo());
                BisFloor floor = bisFlat.getBisFloor();
                fact.setBuildingNum(floor.getBuildingNum());
                fact.setBisFlatId(bisMust.getBisFlatId());
            }
            
            //费用类别   
            fact.setChargeTypeCd(bisMust.getChargeTypeCd());
            
            fact.setChargeTypeCdName(DictMapUtil.getMapChargeType(bisMust.getChargeTypeCd()));
            fact.setBisMustId(bisMust.getBisMustId());
            fact.setBisProjectId(bisMust.getBisProjectId());
            fact.setLayoutCd("2");//2为公寓
            //fact.setmus
            fact.setFactYear(bisMust.getMustYear());
            fact.setFactMonth(bisMust.getMustMonth());
            if (bisMust.getMoney() != null) {
                fact.setMustMoney(bisMust.getMoney());
            }
            if (bisMust.getTotalFactMoney() != null) {
                fact.setMoney(bisMust.getTotalFactMoney());
            }
            //收款时间
            fact.setFactDate(format.format(bisMust.getCollDate()));
            result.add(fact);
        }
        //公寓应收total值
        VoFact total = new VoFact();
        total.setMustMoney(bisMustManager.mustSumByFlat(voPage, voFact));
        total.setMoney(factSumByFlat(voPage, voFact));
        total.setChargeTypeCd("000");
        total.setChargeTypeCdName("合计");
        if (mustPage.getResult().size() > 0) {
            result.add(total);
        }

        if (mustPage.getResult().size() > 0) {
            result.add(total);
        }
        voPage.setResult(result);
        return voPage;
    }

    /**
     * 查找实收合同
     * a）录入商铺实收时，根据所选项目、租户、费用类别、年、月自动获取合同及应收款项，如多个应收款项（或合同）的开始和结束日期都在一个月里面
     * ，则由用户选择其中一个应收款项（或合同）
     * b）录入公寓实收时，根据所选项目、公寓编号、费用类别、年、月自动获取合同及应收款项，如多个应收款项（或合同
     * ）的开始和结束日期都在一个月里面，则由用户选择其中一个合同（或应收款项）
     * c）录入多经实收时，根据所选项目、多经名称、费用类别、年、月自动获取合同及应收款项
     * ，如多个应收款项（或合同）的开始和结束日期都在一个月里面，则由用户选择其中一个合同（或应收款项） d）保证金不需要选择年、月，绑定项目、租户即可
     *
     * @param bisProjectId
     * @param bisShopId
     */

    public Page<VoFact> searchFactForFlat(Page voPage, VoFact voFact) {
        Map<Integer, Object> map = paramMap(voFact, voPage);
        map.put(20, voFact.getBuildingNum());
        map.put(21, voFact.getFlatNo());
        /*List<VoFact> list = (List<VoFact>) this.executeFunction(
                  "{ call BIS_FACT_PKG.BIS_FACT_FLAT(?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,?,?)}", map,
                  VoFact.class);*/
        Page<BisFact> page = new Page<BisFact>();
        page.setPageSize(30);
        if (voPage.getPageNo() != 0) {
            page.setPageNo(voPage.getPageNo());
        }
        Page<BisFact> factPage = queryFactBySql(page, voFact);
        voPage.setTotalCount(factPage.getTotalCount());
        List<VoFact> result = new ArrayList<VoFact>();
        Boolean flag = true;
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
        for (BisFact bisFact : factPage.getResult()) {
            VoFact fact = new VoFact();
            //如果有合同ID，则从合同模块里面查找相关的floor、flat数据
            if (StringUtils.isNotBlank(bisFact.getBisContId())) {
                BisCont bisCont = bisContDao.get(bisFact.getBisContId());
                //合同编号
                fact.setContNo(bisCont.getContNo());
                //公寓编号
                StringBuffer flatNo = new StringBuffer("");
                //楼号
                StringBuffer floorNum = new StringBuffer("");
                BisFlat bisFlat = null;
                for (BisFlatContRel rel : bisCont.getBisFlatContRels()) {
                    bisFlat = rel.getBisFlat();
                    flatNo.append(bisFlat.getFlatNo()).append(",");
                    BisFloor floor = bisFlat.getBisFloor();
                    floorNum.append(floor.getBuildingNum()).append(",");
                }
                if (flatNo.toString().length() > 1) {
                    flatNo.delete(flatNo.toString().length() - 1, flatNo.toString().length());
                }
                if (floorNum.toString().length() > 1) {
                    floorNum.delete(floorNum.toString().length() - 1, floorNum.toString().length());
                }
                fact.setFlatNo(flatNo.toString());
                fact.setBuildingNum(floorNum.toString());
            } else {
                //否则，直接根据实收ID获取相关数据
                BisFlat bisFlat = bisFlatDao.get(bisFact.getBisFlatId());
                fact.setFlatNo(bisFlat.getFlatNo());
                BisFloor floor = bisFlat.getBisFloor();
                fact.setBuildingNum(floor.getBuildingNum());
            }
            fact.setChargeTypeCdName(DictMapUtil.getMapChargeType(bisFact.getChargeTypeCd()));
            fact.setBisFactId(bisFact.getBisFactId());
            fact.setFactYear(bisFact.getFactYear());
            fact.setFactMonth(bisFact.getFactMonth());
            if (bisFact.getTotalMustMoney() != null) {
                fact.setMustMoney(bisFact.getTotalMustMoney());
            }
            if (bisFact.getMoney() != null) {
                fact.setMoney(bisFact.getMoney());
            }
            fact.setStatusCd(bisFact.getStatusCd());
            fact.setStatusCdName(DictMapUtil.getMapBisFactStatus(bisFact.getStatusCd()));
            //收款时间
            fact.setFactDate(format.format(bisFact.getFactDate()));
            result.add(fact);
        }
        /*for (VoFact vo : list) {
              if (vo.getMustMoney() != null) {
                  totalMust = totalMust.add(new BigDecimal(vo.getMustMoney()));
              }
              if (vo.getMoney() != null) {
                  totalFact = totalFact.add(new BigDecimal(vo.getMoney()));
              }
              if (voFact.getMustOrFact() == 1) {

                  vo.setStatusCdName(DictMapUtil.getMapBisFactStatus(vo.getStatusCd()));
              } else {
                  vo.setStatusCdName(DictMapUtil.getMapBisMustStatus().get(vo.getStatusCd()));
              }
              if (notBlank(vo.getShopTypeCd())) {
                  vo.setShopTypeCd(DictMapUtil.getMapBisShopType().get(vo.getShopTypeCd()));
              }
              if (notBlank(vo.getManageCd())) {
                  vo.setManageCd(DictMapUtil.getMapShopManageType().get(vo.getManageCd()));
              }
              vo.setChargeTypeCdName(DictMapUtil.getMapChargeType(vo.getChargeTypeCd()));
              result.add(vo);
              if (flag) {
                  voPage.setPageNo(vo.getPageNo() == null ? 1 : vo.getPageNo().intValue());
                  voPage.setTotalCount(vo.getMaxrowno().intValue());
                  flag = false;
              }
          }*/
        VoFact total = new VoFact();
        total.setMustMoney(bisMustManager.mustSumByFlat(voPage, voFact));
        total.setMoney(factSumByFlat(voPage, voFact));
        total.setChargeTypeCd("000");
        total.setChargeTypeCdName("合计");
        if (factPage.getResult().size() > 0) {
            result.add(total);
        }
        voPage.setResult(result);
        return voPage;
    }

    /**
     * 查找实收记录--公寓
     *
     * @param voPage
     * @param voFact
     * @return
     */
    private Page<BisFact> queryFactBySql(Page voPage, VoFact voFact) {
        Page<BisFact> factPage = queryFlatSql(voPage, voFact, "*", "");
        return factPage;
    }

    public BigDecimal factSumByFlat(Page voPage, VoFact voFact) {
        Page<BisFact> factPage = queryFlatSql(voPage, voFact, "sum(money)", "total");
        if (factPage != null && factPage.getResult() != null && factPage.getResult().size() > 0)
            return factPage.getResult().get(0).getMoney();
        else
            return new BigDecimal(0);
    }

    private Page<BisFact> queryFlatSql(Page voPage, VoFact voFact, String queryCont, String type) {
        Map<String, Object> param = new HashMap<String, Object>();
        //若跟合同关联，则取与合同关联的公寓实收记录
        StringBuffer sql = new StringBuffer("select ");
        sql.append(queryCont);
        sql.append(" from ((select fc.* from Bis_fact fc where fc.bis_Project_Id=:bisProjectId ");
        param.put("bisProjectId", voFact.getBisProjectId());
        sql.append(" and exists (select 1 from Bis_Flat_Cont_Rel r where r.bis_Cont_Id=fc.bis_Cont_Id and r.bis_Flat_Id is not null ");

        if (StringUtils.isNotBlank(voFact.getFlatNo()) || StringUtils.isNotBlank(voFact.getBuildingNum())) {
            sql.append(" and exists (select 1 from Bis_Flat ft where ft.bis_Flat_Id=r.bis_Flat_Id");
            //公寓编号
            if (StringUtils.isNotBlank(voFact.getFlatNo())) {
                sql.append(" and ft.flat_No like :flatNo ");
                param.put("flatNo", "%" + voFact.getFlatNo() + "%");
            }
            //楼号
            if (StringUtils.isNotBlank(voFact.getBuildingNum())) {
                sql.append(" and exists (select 1 from Bis_Floor fr where fr.bis_Floor_Id=fc.bis_Floor_Id and fr.building_Num like :buildingNum) ");
                param.put("buildingNum", "%" + voFact.getBuildingNum() + "%");
            }
            sql.append(")");
        }
        sql.append(")");
        //类别
        if (StringUtils.isNotBlank(voFact.getChargeTypeCd())) {
            sql.append(" and fc.CHARGE_TYPE_CD =:chargeTypeCd");
            param.put("chargeTypeCd", voFact.getChargeTypeCd());
        }
        //年
        if (StringUtils.isNotBlank(voFact.getFactYear())) {
            sql.append(" and fc.fact_year =:factYear");
            param.put("factYear", voFact.getFactYear());
        }
        //月
        if (StringUtils.isNotBlank(voFact.getFactMonth())) {
            sql.append(" and fc.fact_month =:factMonth");
            param.put("factMonth", voFact.getFactMonth());
        }
        if (StringUtils.isNotBlank(voFact.getReportDateStart())) {
        	//由于实收权责年月在设计时是分开的，所以处理起来比较麻烦需要先合并年月
        	sql.append(" and (ltrim(fc.fact_year)||'-'||lpad(fc.fact_month,2,'0'))>=:reportDateStart");
        	param.put("reportDateStart", voFact.getReportDateStart());
        }
        if (StringUtils.isNotBlank(voFact.getReportDateEnd())) {
        	//由于实收权责年月在设计时是分开的，所以处理起来比较麻烦需要先合并年月
        	sql.append(" and (ltrim(fc.fact_year)||'-'||lpad(fc.fact_month,2,'0'))<=:reportDateEnd");
            param.put("reportDateEnd", voFact.getReportDateEnd());
        }
        
        //高级搜索的实收年、月
        if (StringUtils.isNotBlank(voFact.getMinMonth())) {
            sql.append(" and fc.fact_date >=to_date(:minMonth,'yyyy-MM-dd')");
            param.put("minMonth", voFact.getMinMonth());
        }
        if (StringUtils.isNotBlank(voFact.getMaxMonth())) {
            sql.append(" and fc.fact_date <=to_date(:maxMonth,'yyyy-MM-dd')");
            param.put("maxMonth", voFact.getMaxMonth());
        }
        //创建人
        if (StringUtils.isNotBlank(voFact.getCreator())) {
            sql.append(" and fc.CREATOR =:creator");
            param.put("creator", voFact.getCreator());
        }
        //审核人
        if (StringUtils.isNotBlank(voFact.getCheckUserCd())) {
            sql.append(" and fc.CHECK_USER_CD =:checkUserCd");
            param.put("checkUserCd", voFact.getCheckUserCd());
        }
        //实收金额
        if (voFact.getMinMoney() != null) {
            sql.append(" and fc.money >=:minMoney");
            param.put("minMoney", voFact.getMinMoney());
        }
        if (voFact.getMaxMoney() != null) {
            sql.append(" and fc.money<=:maxMoney");
            param.put("maxMoney", voFact.getMaxMoney());
        }
        if (StringUtils.isNotBlank(voFact.getStatusCd())) {
            sql.append(" and fc.status_cd=:statusCd");
            if("2".equals(voFact.getStatusCd())){
            	param.put("statusCd", "1");
        	}else{
        		param.put("statusCd", voFact.getStatusCd());
        	}
           
        }
        //若无合同，则搜索商铺数据
        sql.append(") union select bfc.* from bis_fact bfc where bfc.bis_Project_Id=:bisProjectId and bfc.BIS_FLAT_ID is not null ");

        if (StringUtils.isNotBlank(voFact.getFlatNo()) || StringUtils.isNotBlank(voFact.getBuildingNum())) {
            sql.append(" and exists (select 1 from Bis_Flat ft where ft.bis_Flat_Id=bfc.bis_Flat_Id");
            //公寓编号
            if (StringUtils.isNotBlank(voFact.getFlatNo())) {
                sql.append(" and ft.flat_No like :flatNo");
            }
            //楼号
            if (StringUtils.isNotBlank(voFact.getBuildingNum())) {
                sql.append(" and exists (select 1 from Bis_Floor fr where fr.bis_Floor_Id=ft.bis_Floor_Id and fr.building_Num like :buildingNum) ");
            }
            sql.append(")");
        }
        //类别
        if (StringUtils.isNotBlank(voFact.getChargeTypeCd())) {
            sql.append(" and bfc.CHARGE_TYPE_CD =:chargeTypeCd");
        }
        //年
        if (StringUtils.isNotBlank(voFact.getFactYear())) {
            sql.append(" and bfc.fact_year =:factYear");
        }
        //月
        if (StringUtils.isNotBlank(voFact.getFactMonth())) {
            sql.append(" and bfc.fact_month =:factMonth");
        }
        if (StringUtils.isNotBlank(voFact.getReportDateStart())) {
        	//由于实收权责年月在设计时是分开的，所以处理起来比较麻烦需要先合并年月
        	sql.append(" and (ltrim(bfc.fact_year)||'-'||lpad(bfc.fact_month,2,'0'))>=:reportDateStart");
        }
        if (StringUtils.isNotBlank(voFact.getReportDateEnd())) {
        	//由于实收权责年月在设计时是分开的，所以处理起来比较麻烦需要先合并年月
        	sql.append(" and (ltrim(bfc.fact_year)||'-'||lpad(bfc.fact_month,2,'0'))<=:reportDateEnd");
        }
        //高级搜索的实收年、月
        if (StringUtils.isNotBlank(voFact.getMinMonth())) {
            sql.append(" and bfc.FACT_DATE >=to_date(:minMonth,'yyyy-MM-dd')");
        }
        if (StringUtils.isNotBlank(voFact.getMaxMonth())) {
            sql.append(" and bfc.FACT_DATE <=to_date(:maxMonth,'yyyy-MM-dd')");
        }
        //创建人
        if (StringUtils.isNotBlank(voFact.getCreator())) {
            sql.append(" and bfc.CREATOR =:creator");
        }
        //审核人
        if (StringUtils.isNotBlank(voFact.getCheckUserCd())) {
            sql.append(" and bfc.CHECK_USER_CD =:checkUserCd");
        }
        //实收金额
        if (voFact.getMinMoney() != null) {
            sql.append(" and bfc.money >=:minMoney");
        }
        if (voFact.getMaxMoney() != null) {
            sql.append(" and bfc.money<=:maxMoney");
        }
        if (StringUtils.isNotBlank(voFact.getStatusCd())) {
            sql.append(" and bfc.status_cd=:statusCd");
        }
        sql.append(") where 1=1 order by bis_floor_id,bis_flat_id");
        if ("total".equals(type)) {
            List<Object> list = this.findBySql(sql.toString(), param);
            if (list != null && list.size() > 0 && list.get(0) != null) {
                BisFact fact = new BisFact();
                fact.setMoney(new BigDecimal(list.get(0).toString()));
                List<BisFact> factList = new ArrayList<BisFact>();
                factList.add(fact);
                Page<BisFact> factPage = new Page<BisFact>();
                factPage.setResult(factList);
                return factPage;
            } else
                return null;

        } else {
            Map<String, Class> mapClazz = new HashMap<String, Class>();
            mapClazz.put("bis", BisFact.class);
            Page<BisFact> factPage = this.findPageSql(voPage, sql.toString(), param, mapClazz);
            return factPage;
        }
    }

    /**
     * 搜索多经实收记录
     *
     * @param voPage
     * @param voFact
     * @return
     */
    public Page<VoFact> searchFactForMulti(Page voPage, VoFact voFact) {
        Map<Integer, Object> map = paramMap(voFact, voPage);
        /*List<VoFact> list = (List<VoFact>) this
                  .executeFunction("{ call BIS_FACT_PKG.BIS_FACT_MULTI(?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?)}",
                          map, VoFact.class);*/
        Page<BisFact> page = new Page<BisFact>();
        page.setPageSize(30);
        if (voPage.getPageNo() != 0) {
            page.setPageNo(voPage.getPageNo());
        }
        Page<BisFact> factPage = queryMultiBySql(page, voFact);
        voPage.setTotalCount(factPage.getTotalCount());
        List<VoFact> result = new ArrayList<VoFact>();
        Boolean flag = true;
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
        for (BisFact bisFact : factPage.getResult()) {
            VoFact fact = new VoFact();
            BisMulti bisMulti = null;
            //如果有合同ID，则从合同模块里面查找相关的多经数据
            if (StringUtils.isNotBlank(bisFact.getBisContId())) {
                BisCont bisCont = bisContDao.get(bisFact.getBisContId());
                //合同编号
                fact.setContNo(bisCont.getContNo());
                //多经名称
                StringBuffer multiName = new StringBuffer("");
                //多经ID
                StringBuffer bisMultiId = new StringBuffer("");
                int i = 0;
                for (BisMultiContRel rel : bisCont.getBisMultiContRels()) {
                    bisMulti = rel.getBisMulti();
                    multiName.append(bisMulti.getMultiName()).append(",");
                    bisMultiId.append(bisMulti.getBisMultiId()).append(",");
                    if (i == 0) {
                        //承租方renterName
                        fact.setRenterName(bisMulti.getRenterName());
                        //经营项目
                        fact.setOperationProjectCd(bisMulti.getOperationProjectCd());
                    }
                    i++;
                }
                if (multiName.toString().length() > 1) {
                    multiName.delete(multiName.toString().length() - 1, multiName.toString().length());
                }
                if (bisMultiId.toString().length() > 1) {
                    bisMultiId.delete(bisMultiId.toString().length() - 1, bisMultiId.toString().length());
                }
                fact.setMultiName(multiName.toString());

            } else {
                //否则，直接根据多经ID获取相关数据
                bisMulti = bisMultiDao.get(bisFact.getBisMultiId());
                fact.setMultiName(bisMulti.getMultiName());
                //承租方renterName
                fact.setRenterName(bisMulti.getRenterName());
                //经营项目
                fact.setOperationProjectCd(bisMulti.getOperationProjectCd());
            }
            fact.setBisFactId(bisFact.getBisFactId());
            fact.setChargeTypeCdName(DictMapUtil.getMapChargeType(bisFact.getChargeTypeCd()));

            fact.setFactYear(bisFact.getFactYear());
            fact.setFactMonth(bisFact.getFactMonth());
            if (bisFact.getTotalMustMoney() != null) {
                fact.setMustMoney(bisFact.getTotalMustMoney());
            }
            if (bisFact.getMoney() != null) {
                fact.setMoney(bisFact.getMoney());
            }
            fact.setStatusCd(bisFact.getStatusCd());
            fact.setStatusCdName(DictMapUtil.getMapBisFactStatus(bisFact.getStatusCd()));
            //收款时间
            fact.setFactDate(format.format(bisFact.getFactDate()));
            result.add(fact);
        }
        VoFact total = new VoFact();
        total.setMustMoney(bisMustManager.mustSumByMulti(voPage, voFact));
        total.setMoney(factSumByMulti(voPage, voFact));
        total.setChargeTypeCd("000");
        total.setChargeTypeCdName("合计");
        if (factPage.getResult().size() > 0) {
            result.add(total);
        }
        voPage.setResult(result);
        return voPage;
    }

    /**
     * 搜索多经的应收记录
     *
     * @param voPage
     * @param voFact
     * @return
     */
    public Page<VoFact> searchMustForMulti(Page voPage, VoFact voFact) {
        Map<Integer, Object> map = paramMap(voFact, voPage);
        map.put(20, voFact.getBuildingNum());
        map.put(21, voFact.getFlatNo());
        Page<BisFact> page = new Page<BisFact>();
        page.setPageSize(30);
        if (voPage.getPageNo() != 0) {
            page.setPageNo(voPage.getPageNo());
        }
        Page<BisMust> mustPage = bisMustManager.queryMultiByMust(voPage, voFact);

        voPage.setTotalCount(mustPage.getTotalCount());
        List<VoFact> result = new ArrayList<VoFact>();
        Boolean flag = true;
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
        for (BisMust bisMust : mustPage.getResult()) {
            VoFact fact = new VoFact();
            //如果有合同ID，则从合同模块里面查找相关的floor、flat数据
            if (bisMust.getBisCont() != null) {
                BisCont bisCont = bisMust.getBisCont();
                fact.setBisContId(bisCont.getBisContId());
                //合同编号
                fact.setContNo(bisCont.getContNo());
                //多经编号
                StringBuffer multiName = new StringBuffer("");
                StringBuffer bisMultiId = new StringBuffer("");
                BisMulti bisMulti = null;
                int i = 0;
                for (BisMultiContRel rel : bisCont.getBisMultiContRels()) {
                    bisMulti = rel.getBisMulti();
                    multiName.append(bisMulti.getMultiName()).append(",");
                    bisMultiId.append(bisMulti.getBisMultiId()).append(",");
                    if (i == 0) {
                        fact.setOperationProjectCd(bisMulti.getOperationProjectCd());
                        //承租方
                        fact.setRenterName(bisMulti.getRenterName());
                    }
                    i++;
                }
                if (multiName.toString().length() > 1) {
                    multiName.delete(multiName.toString().length() - 1, multiName.toString().length());
                }
                if (bisMultiId.toString().length() > 1) {
                    bisMultiId.delete(bisMultiId.toString().length() - 1, bisMultiId.toString().length());
                }
                //多经ID
                fact.setBisMultiId(bisMultiId.toString());
                fact.setMultiName(multiName.toString());

            } else {
                //否则，直接根据多经ID获取相关数据
                BisMulti bisMulti = bisMultiDao.get(bisMust.getBisMultiId());
                fact.setMultiName(bisMulti.getMultiName());
                //经营项目
                fact.setOperationProjectCd(bisMulti.getOperationProjectCd());
                //承租方
                fact.setRenterName(bisMulti.getRenterName());
                fact.setBisMultiId(bisMulti.getBisMultiId());
            }
            fact.setChargeTypeCdName(DictMapUtil.getMapChargeType(bisMust.getChargeTypeCd()));
            fact.setChargeTypeCd(bisMust.getChargeTypeCd());
            fact.setBisMustId(bisMust.getBisMustId());
            fact.setBisProjectId(bisMust.getBisProjectId());
            fact.setLayoutCd("3");//2为多经
            //fact.setmus
            fact.setFactYear(bisMust.getMustYear());
            fact.setFactMonth(bisMust.getMustMonth());
            if (bisMust.getMoney() != null) {
                fact.setMustMoney(bisMust.getMoney());
            }
            if (bisMust.getTotalFactMoney() != null) {
                fact.setMoney(bisMust.getTotalFactMoney());
            }
            //收款时间
            fact.setFactDate(format.format(bisMust.getCollDate()));
            result.add(fact);
        }
        VoFact total = new VoFact();
        total.setMustMoney(bisMustManager.mustSumByMulti(voPage, voFact));
        total.setMoney(factSumByMulti(voPage, voFact));
        total.setChargeTypeCd("000");
        total.setChargeTypeCdName("合计");
        if (mustPage.getResult().size() > 0) {
            result.add(total);
        }
        voPage.setResult(result);
        return voPage;

    }


    /**
     * 欠费
     *
     * @param voPage
     * @param voFact
     * @return
     */
    public Page<VoFact> searchOwe(Page voPage, VoFact voFact) {

        Map<String, Object> param = new HashMap<String, Object>();
        //若跟合同关联，则取与合同关联的商铺实收记录
        StringBuffer sql = new StringBuffer("select fc.* from Bis_Must fc where fc.bis_Project_Id=:bisProjectId ");
        param.put("bisProjectId", voFact.getBisProjectId());
        sql.append(" and fc.bis_Tenant_Id is not null  and fc.bis_cont_id is not null ");//and fc.bis_shop_id is not null
        //类别
        if (StringUtils.isNotBlank(voFact.getChargeTypeCd())) {
            sql.append(" and fc.CHARGE_TYPE_CD =:chargeTypeCd");
            param.put("chargeTypeCd", voFact.getChargeTypeCd());
        }


        //租户
        if (StringUtils.isNotBlank(voFact.getBisTenantIds())) {
            sql.append(" and fc.Bis_Tenant_Id in (");
            String[] tenantIds = voFact.getBisTenantIds().split(",");
            for (int i = 0; i < tenantIds.length; i++) {
                if (i == tenantIds.length - 1) {
                    sql.append("'").append(tenantIds[i]).append("'");
                } else {
                    sql.append("'").append(tenantIds[i]).append("'").append(",");
                }
            }
            sql.append(")");
        }
        //审核状态
        if (StringUtils.isNotBlank(voFact.getStatusCd())) {
            sql.append(" AND fc.status_cd = :statusCd");
            param.put("statusCd", voFact.getStatusCd());
        }
        //年
        if (StringUtils.isNotBlank(voFact.getFactYear())) {
            sql.append(" and fc.must_year =:factYear");
            param.put("factYear", voFact.getFactYear());
        }
        //月
        if (StringUtils.isNotBlank(voFact.getFactMonth())) {
            sql.append(" and fc.must_month =:factMonth");
            param.put("factMonth", voFact.getFactMonth());
        }
        
        //实收月份起止时间 add by zhengyi at 2012-06-29
        //实收月份起止时间 update by liuzhihui at 2012-07-19
        
        if (StringUtils.isNotBlank(voFact.getReportDateStart())) {
        	//由于实收年月在设计时是分开的，所以处理起来比较麻烦需要先合并年月
        	sql.append(" and (ltrim(fc.must_year)||'-'||lpad(fc.must_month,2,'0'))>=:reportDateStart");
        	param.put("reportDateStart", voFact.getReportDateStart());
        }
        if (StringUtils.isNotBlank(voFact.getReportDateEnd())) {
        	//由于实收年月在设计时是分开的，所以处理起来比较麻烦需要先合并年月
        	sql.append(" and (ltrim(fc.must_year)||'-'||lpad(fc.must_month,2,'0'))<=:reportDateEnd");
            param.put("reportDateEnd", voFact.getReportDateEnd());
        }
        //高级搜索的实收年、月
        if (StringUtils.isNotBlank(voFact.getMinMonth())) {
            sql.append(" and fc.COLL_DATE >=to_date(:minMonth,'yyyy-MM-dd')");
            param.put("minMonth", voFact.getMinMonth());
        }
        if (StringUtils.isNotBlank(voFact.getMaxMonth())) {
            sql.append(" and fc.COLL_DATE <=to_date(:maxMonth,'yyyy-MM-dd')");
            param.put("maxMonth", voFact.getMaxMonth());
        }
        //创建人
        if (StringUtils.isNotBlank(voFact.getCreator())) {
            sql.append(" and fc.CREATOR =:creator");
            param.put("creator", voFact.getCreator());
        }
        //审核人
        if (StringUtils.isNotBlank(voFact.getCheckUserCd())) {
            sql.append(" and fc.CHECK_USER_CD =:checkUserCd");
            param.put("checkUserCd", voFact.getCheckUserCd());
        }
        //实收金额
        if (voFact.getMinMoney() != null) {
            sql.append(" and fc.money >=:minMoney");
            param.put("minMoney", voFact.getMinMoney());
        }
        if (voFact.getMaxMoney() != null) {
            sql.append(" and fc.money<=:maxMoney");
            param.put("maxMoney", voFact.getMaxMoney());
        }

        sql.append(" and (fc.money > fc.total_fact_money or fc.total_fact_money is null) ");


        //搜索当前欠费的记录
        //sql.append(" and fc.status_cd = '2' and  fc.COLL_DATE <=to_date(:nowdate,'yyyy-MM-dd')");
        sql.append(" and fc.status_cd = '2' and  to_date(fc.must_year||'-'||fc.must_month,'yyyy-MM') <add_months(sysdate,-1) and  fc.money is not null and fc.money<>0");
        //

        //param.put("nowdate", DateOperator.formatDate(DateOperator.getDateNow(), "yyyy-MM-dd"));

        sql.append(" order by fc.must_year,fc.must_month,fc.charge_type_cd desc");
        Map<String, Class> mapClazz = new HashMap<String, Class>();
        mapClazz.put("bis", BisMust.class);

        Page<BisMust> mustPage = bisMustManager.findPageSql(voPage, sql.toString(), param, mapClazz);


        Map<Integer, Object> map = paramMap(voFact, voPage);
        map.put(20, voFact.getBisTenantIds());

        List<VoFact> result = new ArrayList<VoFact>();
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
        Boolean flag = true;
        VoFact fact = null;
        for (BisMust vo : mustPage.getResult()) {
            fact = new VoFact();
            //应收
            if (vo.getMoney() != null) {
                fact.setMustMoney(vo.getMoney());
            }
            if (null != vo.getBisCont()) {
                //商铺编号
                fact.setStoreNo(BisCache.getStoreNos(vo.getBisCont().getBisContId()));
                fact.setBisContId(vo.getBisCont().getBisContId());
            }
            //商家名称
            if (null != vo.getBisShopId()) {
                BisShop shop = bisShopManager.getEntity(vo.getBisShopId());
                fact.setConnName(shop.getNameCn());
            }
            fact.setBisProjectId(vo.getBisProjectId());
            fact.setBisTenantId(vo.getBisTenantId());
            fact.setStatusCd(vo.getStatusCd());
            fact.setStatusCdName(DictMapUtil.getMapBisFactStatus(vo.getStatusCd()));
            fact.setChargeTypeCd(vo.getChargeTypeCd());
            fact.setChargeTypeCdName(DictMapUtil.getMapChargeType(vo.getChargeTypeCd()));
            fact.setFactYear(vo.getMustYear());
            fact.setFactMonth(vo.getMustMonth());
            if (vo.getTotalFactMoney() != null) {
                fact.setMoney(vo.getTotalFactMoney());
            }
            //应收日期
            if (null != vo.getCollDate()) {
                fact.setFactDate(format.format(vo.getCollDate()));
            }
            if (null != vo.getUpdatedDate()) {
                fact.setUpdateDate(format.format(vo.getUpdatedDate()));
            }
            result.add(fact);
        }

        //合计 搜索应收的TOTAL值
        //=============================================================
        param = new HashMap<String, Object>();
        sql = new StringBuffer("select sum(fc.money),sum(fc.total_fact_money) from Bis_Must fc where fc.bis_Project_Id=:bisProjectId ");
        param.put("bisProjectId", voFact.getBisProjectId());
        sql.append(" and fc.bis_Tenant_Id is not null  and fc.bis_cont_id is not null");

        //类别
        if (StringUtils.isNotBlank(voFact.getChargeTypeCd())) {
            sql.append(" and fc.CHARGE_TYPE_CD =:chargeTypeCd");
            param.put("chargeTypeCd", voFact.getChargeTypeCd());
        }
        //年
        if (StringUtils.isNotBlank(voFact.getFactYear())) {
            sql.append(" and fc.must_year =:factYear");
            param.put("factYear", voFact.getFactYear());
        }
        //月
        if (StringUtils.isNotBlank(voFact.getFactMonth())) {
            sql.append(" and fc.must_month =:factMonth");
            param.put("factMonth", voFact.getFactMonth());
        }
        //租户
        if (StringUtils.isNotBlank(voFact.getBisTenantIds())) {
            sql.append(" and fc.Bis_Tenant_Id in (");
            String[] tenantIds = voFact.getBisTenantIds().split(",");
            for (int i = 0; i < tenantIds.length; i++) {
                if (i == tenantIds.length - 1) {
                    sql.append("'").append(tenantIds[i]).append("'");
                } else {
                    sql.append("'").append(tenantIds[i]).append("'").append(",");
                }
            }
            sql.append(")");
        }
        //高级搜索的实收年、月
        if (StringUtils.isNotBlank(voFact.getMinMonth())) {
            sql.append(" and fc.COLL_DATE >=to_date(:minMonth,'yyyy-MM-dd')");
            param.put("minMonth", voFact.getMinMonth());
        }
        if (StringUtils.isNotBlank(voFact.getMaxMonth())) {
            sql.append(" and fc.COLL_DATE <=to_date(:maxMonth,'yyyy-MM-dd')");
            param.put("maxMonth", voFact.getMaxMonth());
        }
        //创建人
        if (StringUtils.isNotBlank(voFact.getCreator())) {
            sql.append(" and fc.CREATOR =:creator");
            param.put("creator", voFact.getCreator());
        }
        //审核人
        if (StringUtils.isNotBlank(voFact.getCheckUserCd())) {
            sql.append(" and fc.CHECK_USER_CD =:checkUserCd");
            param.put("checkUserCd", voFact.getCheckUserCd());
        }
        //实收金额
        if (voFact.getMinMoney() != null) {
            sql.append(" and fc.money >=:minMoney");
            param.put("minMoney", voFact.getMinMoney());
        }
        if (voFact.getMaxMoney() != null) {
            sql.append(" and fc.money<=:maxMoney");
            param.put("maxMoney", voFact.getMaxMoney());
        }
        sql.append(" and to_date(fc.must_year||'-'||fc.must_month,'yyyy-MM') <add_months(sysdate,-1)");
        sql.append(" and (fc.money > fc.total_fact_money or fc.total_fact_money is null ) ");

        List<Object[]> list = this.findBySql(sql.toString(), param);

        VoFact total = new VoFact();
        if (list != null && list.size() > 0 && list.get(0) != null) {
            if (null != ((Object[]) list.get(0))[0]) {
                total.setMustMoney(new BigDecimal(((Object[]) list.get(0))[0].toString()));
            }else{
                total.setMustMoney(new BigDecimal(0));
            }

            if (null != ((Object[]) list.get(0))[1]) {
                total.setMoney(new BigDecimal(((Object[]) list.get(0))[1].toString()));
            } else {
                total.setMoney(new BigDecimal(0));
            }
        } else {
            total.setMustMoney(new BigDecimal(0));
            total.setMoney(new BigDecimal(0));
        }

        /*
        //实收合计
        param = new HashMap<String, Object>();
        sql = new StringBuffer("select sum(fc.money) from Bis_Fact fc where fc.bis_Project_Id=:bisProjectId ");
        param.put("bisProjectId", voFact.getBisProjectId());
        sql.append(" and fc.bis_Tenant_Id is not null  and fc.bis_cont_id is not null");
        //类别
        if (StringUtils.isNotBlank(voFact.getChargeTypeCd())) {
            sql.append(" and fc.CHARGE_TYPE_CD =:chargeTypeCd");
            param.put("chargeTypeCd", voFact.getChargeTypeCd());
        }
        //年
        if (StringUtils.isNotBlank(voFact.getFactYear())) {
            sql.append(" and fc.fact_year =:factYear");
            param.put("factYear", voFact.getFactYear());
        }
        //月
        if (StringUtils.isNotBlank(voFact.getFactMonth())) {
            sql.append(" and fc.fact_month =:factMonth");
            param.put("factMonth", voFact.getFactMonth());
        }
        //租户
        if (StringUtils.isNotBlank(voFact.getBisTenantIds())) {
            sql.append(" and fc.Bis_Tenant_Id in (");
            String[] tenantIds = voFact.getBisTenantIds().split(",");
            for (int i = 0; i < tenantIds.length; i++) {
                if (i == tenantIds.length - 1) {
                    sql.append("'").append(tenantIds[i]).append("'");
                } else {
                    sql.append("'").append(tenantIds[i]).append("'").append(",");
                }
            }
            sql.append(")");
        }
        //高级搜索的实收年、月
        if (StringUtils.isNotBlank(voFact.getMinMonth())) {
            sql.append(" and fc.FACT_DATE >=to_date(:minMonth,'yyyy-MM-dd')");
            param.put("minMonth", voFact.getMinMonth());
        }
        if (StringUtils.isNotBlank(voFact.getMaxMonth())) {
            sql.append(" and fc.FACT_DATE <=to_date(:maxMonth,'yyyy-MM-dd')");
            param.put("maxMonth", voFact.getMaxMonth());
        }
        //创建人
        if (StringUtils.isNotBlank(voFact.getCreator())) {
            sql.append(" and fc.CREATOR =:creator");
            param.put("creator", voFact.getCreator());
        }
        //审核人
        if (StringUtils.isNotBlank(voFact.getCheckUserCd())) {
            sql.append(" and fc.CHECK_USER_CD =:checkUserCd");
            param.put("checkUserCd", voFact.getCheckUserCd());
        }
        //实收金额
        if (voFact.getMinMoney() != null) {
            sql.append(" and fc.money >=:minMoney");
            param.put("minMoney", voFact.getMinMoney());
        }
        if (voFact.getMaxMoney() != null) {
            sql.append(" and fc.money<=:maxMoney");
            param.put("maxMoney", voFact.getMaxMoney());
        }
        sql.append(" and to_date(fc.fact_year||'-'||fc.fact_month,'yyyy-MM') <add_months(sysdate,-1)");
        //sql.append(" and (fc.money > fc.total_fact_money or fc.total_fact_money is null) ");

        list = this.findBySql(sql.toString(), param);
        if (list != null && list.size() > 0 && list.get(0) != null){
            total.setMoney(new BigDecimal(list.get(0).toString()));
        }else{
            total.setMoney(new BigDecimal(0));
        }
         */
        total.setChargeTypeCd("000");
        total.setChargeTypeCdName("合计");
        if (mustPage.getResult().size() > 0) {
            result.add(total);
        }
        voPage.setResult(result);
        return voPage;
    }

    /**
     * 搜索页面——多经实收类型
     *
     * @param voPage
     * @param voFact
     * @return
     */
    private Page<BisFact> queryMultiBySql(Page voPage, VoFact voFact) {
        return multiBySql(voPage, voFact, "*", "");
    }

    public BigDecimal factSumByMulti(Page voPage, VoFact voFact) {
        Page<BisFact> factPage = multiBySql(voPage, voFact, "sum(money)", "total");
        if (factPage != null && factPage.getResult() != null && factPage.getResult().size() > 0)
            return factPage.getResult().get(0).getMoney();
        else
            return new BigDecimal(0);
    }

    private Page<BisFact> multiBySql(Page voPage, VoFact voFact, String queryCont, String type) {
        Map<String, Object> param = new HashMap<String, Object>();
        //若跟合同关联，则取与合同关联的多经实收记录
        StringBuffer sql = new StringBuffer("select ");
        sql.append(queryCont);
        sql.append(" from ((select fc.* from Bis_fact fc where fc.bis_Project_Id=:bisProjectId ");
        param.put("bisProjectId", voFact.getBisProjectId());
        sql.append(" and exists (select 1 from bis_multi_cont_rel r where r.bis_Cont_Id=fc.bis_Cont_Id ");
        sql.append(")");
        //类别
        if (StringUtils.isNotBlank(voFact.getChargeTypeCd())) {
            sql.append(" and fc.CHARGE_TYPE_CD =:chargeTypeCd");
            param.put("chargeTypeCd", voFact.getChargeTypeCd());
        }
        //如果有多经合同，则搜索多经合同内容
        if (StringUtils.isNotBlank(voFact.getBisContId())) {
            sql.append(" and fc.bis_Cont_Id =:bisContId");
            param.put("bisContId", voFact.getBisContId());
        } else if (StringUtils.isNotBlank(voFact.getBisMultiId())) {
            sql.append(" and fc.bis_Multi_Id =:bisMultiId");
            param.put("bisMultiId", voFact.getBisMultiId());
        }
        //年
        if (StringUtils.isNotBlank(voFact.getFactYear())) {
            sql.append(" and fc.fact_year =:factYear");
            param.put("factYear", voFact.getFactYear());
        }
        //月
        if (StringUtils.isNotBlank(voFact.getFactMonth())) {
            sql.append(" and fc.fact_month =:factMonth");
            param.put("factMonth", voFact.getFactMonth());
        }
        if (StringUtils.isNotBlank(voFact.getReportDateStart())) {
        	//由于实收权责年月在设计时是分开的，所以处理起来比较麻烦需要先合并年月
        	sql.append(" and (ltrim(fc.fact_year)||'-'||lpad(fc.fact_month,2,'0'))>=:reportDateStart");
        	param.put("reportDateStart", voFact.getReportDateStart());
        }
        if (StringUtils.isNotBlank(voFact.getReportDateEnd())) {
        	//由于实收权责年月在设计时是分开的，所以处理起来比较麻烦需要先合并年月
        	sql.append(" and (ltrim(fc.fact_year)||'-'||lpad(fc.fact_month,2,'0'))<=:reportDateEnd");
            param.put("reportDateEnd", voFact.getReportDateEnd());
        }
        //高级搜索的实收年、月
        if (StringUtils.isNotBlank(voFact.getMinMonth())) {
            sql.append(" and fc.FACT_DATE >=to_date(:minMonth,'yyyy-MM-dd')");
            param.put("minMonth", voFact.getMinMonth());
        }
        if (StringUtils.isNotBlank(voFact.getMaxMonth())) {
            sql.append(" and fc.FACT_DATE <=to_date(:maxMonth,'yyyy-MM-dd')");
            param.put("maxMonth", voFact.getMaxMonth());
        }
        //创建人
        if (StringUtils.isNotBlank(voFact.getCreator())) {
            sql.append(" and fc.CREATOR =:creator");
            param.put("creator", voFact.getCreator());
        }
        //审核人
        if (StringUtils.isNotBlank(voFact.getCheckUserCd())) {
            sql.append(" and fc.CHECK_USER_CD =:checkUserCd");
            param.put("checkUserCd", voFact.getCheckUserCd());
        }
        //实收金额
        if (voFact.getMinMoney() != null) {
            sql.append(" and fc.money >=:minMoney");
            param.put("minMoney", voFact.getMinMoney());
        }
        if (voFact.getMaxMoney() != null) {
            sql.append(" and fc.money<=:maxMoney");
            param.put("maxMoney", voFact.getMaxMoney());
        }
        if (StringUtils.isNotBlank(voFact.getStatusCd())) {
            sql.append(" and fc.status_cd=:statusCd");
            if("2".equals(voFact.getStatusCd())){
            	param.put("statusCd", "1");
            }else{
            	param.put("statusCd", voFact.getStatusCd());
            }
            
        }
        //若无合同，则搜索商铺数据
        sql.append(") union select bfc.* from bis_fact bfc where bfc.bis_Project_Id=:bisProjectId and bfc.BIS_MULTI_ID is not null ");
        //多经ID
        /*if(StringUtils.isNotBlank(voFact.getBisMultiId())){
              sql.append(" and bfc.BIS_MULTI_ID =:multiId");
          }*/
        //类别
        if (StringUtils.isNotBlank(voFact.getChargeTypeCd())) {
            sql.append(" and bfc.CHARGE_TYPE_CD =:chargeTypeCd");
        }
        //如果有多经合同，则搜索多经合同内容
        if (StringUtils.isNotBlank(voFact.getBisContId())) {
            sql.append(" and bfc.bis_Cont_Id =:bisContId");
        } else if (StringUtils.isNotBlank(voFact.getBisMultiId())) {
            sql.append(" and bfc.bis_Multi_Id =:bisMultiId");
        }
        //年
        if (StringUtils.isNotBlank(voFact.getFactYear())) {
            sql.append(" and bfc.fact_year =:factYear");
        }
        //月
        if (StringUtils.isNotBlank(voFact.getFactMonth())) {
            sql.append(" and bfc.fact_month =:factMonth");
        }
        if (StringUtils.isNotBlank(voFact.getReportDateStart())) {
        	//由于实收权责年月在设计时是分开的，所以处理起来比较麻烦需要先合并年月
        	sql.append(" and (ltrim(bfc.fact_year)||'-'||lpad(bfc.fact_month,2,'0'))>=:reportDateStart");
        }
        if (StringUtils.isNotBlank(voFact.getReportDateEnd())) {
        	//由于实收权责年月在设计时是分开的，所以处理起来比较麻烦需要先合并年月
        	sql.append(" and (ltrim(bfc.fact_year)||'-'||lpad(bfc.fact_month,2,'0'))<=:reportDateEnd");
        }
        //高级搜索的实收年、月
        if (StringUtils.isNotBlank(voFact.getMinMonth())) {
            sql.append(" and bfc.FACT_DATE >=to_date(:minMonth,'yyyy-MM-dd')");
        }
        if (StringUtils.isNotBlank(voFact.getMaxMonth())) {
            sql.append(" and bfc.FACT_DATE <=to_date(:maxMonth,'yyyy-MM-dd')");
        }
        //创建人
        if (StringUtils.isNotBlank(voFact.getCreator())) {
            sql.append(" and bfc.CREATOR =:creator");
        }
        //审核人
        if (StringUtils.isNotBlank(voFact.getCheckUserCd())) {
            sql.append(" and bfc.CHECK_USER_CD =:checkUserCd");
        }
        //实收金额
        if (voFact.getMinMoney() != null) {
            sql.append(" and bfc.money >=:minMoney");
        }
        if (voFact.getMaxMoney() != null) {
            sql.append(" and bfc.money<=:maxMoney");
        }
        if (StringUtils.isNotBlank(voFact.getStatusCd())) {
            sql.append(" and bfc.status_cd=:statusCd");
        }
        sql.append(") where 1=1");
        if ("total".equals(type)) {
            List<Object> list = this.findBySql(sql.toString(), param);
            if (list != null && list.size() > 0 && list.get(0) != null) {
                BisFact fact = new BisFact();
                fact.setMoney(new BigDecimal(list.get(0).toString()));
                List<BisFact> factList = new ArrayList<BisFact>();
                factList.add(fact);
                Page<BisFact> factPage = new Page<BisFact>();
                factPage.setResult(factList);
                return factPage;
            } else
                return null;
        } else {
            Map<String, Class> mapClazz = new HashMap<String, Class>();
            mapClazz.put("bis", BisFact.class);
            Page<BisFact> factPage = this.findPageSql(voPage, sql.toString(), param, mapClazz);
            return factPage;
        }
    }

    /**
     * 统计商铺搜索应收、实收和 total值
     */
    public VoFact searchFactForShoreSum(Page voPage, VoFact voFact) {
        /*Map<Integer, Object> map = paramMap(voFact, voPage);
          map.put(20, voFact.getBisTenantIds());
          String mustMoney =bisMustManager.mustMoneySum(voFact);
          String factMoney =factMoneySum(voFact);
          List<VoFact> l = (List<VoFact>) this.executeFunction(
                  "{ call BIS_FACT_PKG.BIS_FACT_STORES_SUM(?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,? ,?)}", map,
                  VoFact.class);

          if (l.size() == 1)
              return l.get(0);
          else*/
        return null;
    }
    //查询实收总和
    private BigDecimal factMoneySum(VoFact voFact) {
        Map<String, Object> param = new HashMap<String, Object>();
        StringBuffer sql = new StringBuffer("select sum(fc.money) from Bis_Fact fc where fc.bis_Project_Id=:bisProjectId ");
        param.put("bisProjectId", voFact.getBisProjectId());
        sql.append(" and fc.bis_Tenant_Id is not null  and fc.bis_cont_id is not null");
        //类别
        if (StringUtils.isNotBlank(voFact.getChargeTypeCd())) {
            sql.append(" and fc.CHARGE_TYPE_CD =:chargeTypeCd");
            param.put("chargeTypeCd", voFact.getChargeTypeCd());
        }
        /*//年
        if (StringUtils.isNotBlank(voFact.getFactYear())) {
            sql.append(" and fc.fact_year =:factYear");
            param.put("factYear", voFact.getFactYear());
        }
        //月
        if (StringUtils.isNotBlank(voFact.getFactMonth())) {
            sql.append(" and fc.fact_month =:factMonth");
            param.put("factMonth", voFact.getFactMonth());
        }*/
        
        //实收权责年月起止时间 add by liuzhihui at 2012-07-17
		if (StringUtils.isNotBlank(voFact.getReportDateStart())) {
	      	//由于实收权责年月在设计时是分开的，所以处理起来比较麻烦需要先合并年月
	      	sql.append(" and (ltrim(fc.fact_year)||'-'||lpad(fc.fact_month,2,'0'))>=:reportDateStart");
	      	param.put("reportDateStart", voFact.getReportDateStart());
		}
		if (StringUtils.isNotBlank(voFact.getReportDateEnd())) {
			//由于实收权责年月在设计时是分开的，所以处理起来比较麻烦需要先合并年月
			sql.append(" and (ltrim(fc.fact_year)||'-'||lpad(fc.fact_month,2,'0'))<=:reportDateEnd");
			param.put("reportDateEnd", voFact.getReportDateEnd());
		}
        
        //租户
        if (StringUtils.isNotBlank(voFact.getBisTenantIds())) {
            sql.append(" and fc.Bis_Tenant_Id in (");
            String[] tenantIds = voFact.getBisTenantIds().split(",");
            for (int i = 0; i < tenantIds.length; i++) {
                if (i == tenantIds.length - 1) {
                    sql.append("'").append(tenantIds[i]).append("'");
                } else {
                    sql.append("'").append(tenantIds[i]).append("'").append(",");
                }
            }
            sql.append(")");
        }
        //高级搜索的实收年、月
        if (StringUtils.isNotBlank(voFact.getMinMonth())) {
            sql.append(" and fc.FACT_DATE >=to_date(:minMonth,'yyyy-MM-dd')");
            param.put("minMonth", voFact.getMinMonth());
        }
        if (StringUtils.isNotBlank(voFact.getMaxMonth())) {
            sql.append(" and fc.FACT_DATE <=to_date(:maxMonth,'yyyy-MM-dd')");
            param.put("maxMonth", voFact.getMaxMonth());
        }
        //创建人
        if (StringUtils.isNotBlank(voFact.getCreator())) {
            sql.append(" and fc.CREATOR =:creator");
            param.put("creator", voFact.getCreator());
        }
        //审核人
        if (StringUtils.isNotBlank(voFact.getCheckUserCd())) {
            sql.append(" and fc.CHECK_USER_CD =:checkUserCd");
            param.put("checkUserCd", voFact.getCheckUserCd());
        }
        //实收金额
        if (voFact.getMinMoney() != null) {
            sql.append(" and fc.money >=:minMoney");
            param.put("minMoney", voFact.getMinMoney());
        }
        if (voFact.getMaxMoney() != null) {
            sql.append(" and fc.money<=:maxMoney");
            param.put("maxMoney", voFact.getMaxMoney());
        }
        if (StringUtils.isNotBlank(voFact.getStatusCd())) {
            sql.append(" and fc.status_cd=:statusCd");
            //如果实收状态CD为2,则改为1
            if("2".equals(voFact.getStatusCd())){
            	 param.put("statusCd","1");
            }else{
            	param.put("statusCd",voFact.getStatusCd());
            }
           
        }
        List<Object> list = this.findBySql(sql.toString(), param);
        if (list != null && list.size() > 0 && list.get(0) != null)
            return new BigDecimal(list.get(0).toString());
        else
            return new BigDecimal(0);
    }

    public List<VoFact> searchFactForShore(String bisTenantId) {
        Page voPage = new Page();
        VoFact voFact = new VoFact();
        voFact.setNoPage(true);
        voFact.setContLayOutCd(String.valueOf(BIS_CONT_TYPE_STORE));
        voFact.setMustOrFact(3);
        voFact.setBisTenantIds(bisTenantId);
        List<VoFact> result = new ArrayList<VoFact>();
        for (VoFact vo : searchFactForStore(voPage, voFact, "").getResult()) {
            BigDecimal mustMoney = vo.getMustMoney() == null ? (new BigDecimal(0)) : vo.getMustMoney();
            BigDecimal money = vo.getMoney() == null ? (new BigDecimal(0)) : vo.getMoney();
            vo.setOverDueMoney(mustMoney.subtract(money));
            result.add(vo);
        }
        return result;
    }

    /**
     * 搜索应收费
     *
     * @param voPage
     * @param voFact
     * @return
     */
    public Page<VoFact> searchBisMustPage(Page voPage, VoFact voFact) {

        StringBuffer hsql = new StringBuffer(" from BisMust where 1=1");

        return null;
    }

    public Page<VoFact> searMustForStore(Page voPage, VoFact voFact, String isExport) {
        Map<Integer, Object> map = paramMap(voFact, voPage);
        map.put(20, voFact.getBisTenantIds());
        Page<BisMust> mustPage = bisMustManager.mustStoreBySql(voPage, voFact);
        List<VoFact> result = new ArrayList<VoFact>();
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
        Boolean flag = true;
        VoFact fact = null;
       // BisCache.reloadCache();
        for (BisMust vo : mustPage.getResult()) {
            fact = new VoFact();
            //应收
            if (vo.getMoney() != null) {
                fact.setMustMoney(vo.getMoney());
            }
            if (null != vo.getBisCont()) {
                //商铺编号
                fact.setStoreNo(BisCache.getStoreNos(vo.getBisCont().getBisContId()));
                fact.setBisContId(vo.getBisCont().getBisContId());
            }
            //商家名称
            if (null != vo.getBisShopId()) {
                BisShop shop = bisShopManager.getEntity(vo.getBisShopId());
                fact.setBisShopId(vo.getBisShopId());
                fact.setConnName(shop.getNameCn());
            }
            fact.setBisProjectId(vo.getBisProjectId());
            fact.setBisTenantId(vo.getBisTenantId());
            fact.setStatusCd(vo.getStatusCd());
            fact.setStatusCdName(DictMapUtil.getMapBisFactStatus(vo.getStatusCd()));
            fact.setChargeTypeCd(vo.getChargeTypeCd());
            fact.setChargeTypeCdName(DictMapUtil.getMapChargeType(vo.getChargeTypeCd()));
            fact.setFactYear(vo.getMustYear());
            fact.setFactMonth(vo.getMustMonth());
            if (vo.getTotalFactMoney() != null) {
                fact.setMoney(vo.getTotalFactMoney());
            }
            //应收日期
            if (null != vo.getCollDate()) {
                fact.setFactDate(format.format(vo.getCollDate()));
            }
            if (null != vo.getUpdatedDate()) {
                fact.setUpdateDate(format.format(vo.getUpdatedDate()));
            }
            result.add(fact);
        }

        //VoFact total = searchFactForShoreSum(voPage, voFact);
        /*
          BigDecimal mustMoney = new BigDecimal(0);
          BigDecimal factMoney = new BigDecimal(0);
          for(int i=0;null!=result && i<result.size();i++){
              VoFact total = result.get(i);
              try{
                  mustMoney = mustMoney.add(BigDecimal.valueOf(Double.parseDouble(total.getMustMoney())));
              }catch(Exception e){}
              try{
                  factMoney = factMoney.add(BigDecimal.valueOf(Double.parseDouble(total.getMoney())));
              }catch(Exception e){}
          }
          */
        if (!"export".equals(isExport)) {
            VoFact total = new VoFact();
            total.setMustMoney(bisMustManager.mustMoneySum(voFact));
            total.setMoney(factMoneySum(voFact));
            //total.setMustMoney(mustMoney.toString());
            //total.setMoney(factMoney.toString());
            total.setChargeTypeCd("000");
            total.setChargeTypeCdName("合计");
            if (mustPage.getResult().size() > 0) {
                result.add(total);
            }
        }
        voPage.setResult(result);
        return voPage;
    }

    /**
     * (新) 实收明细查询
     * 
     */
    public Page<VoFact> searchActIncomeForStore( Page voPage, VoFact voFact, String isExport) {
        List<VoFact> result = new ArrayList<VoFact>();
        /**避免重复值问题*/
        List<String> distinctList = new ArrayList<String>();
        /**总记录条数赋值*/
//        Map<String, Double> totalMap = new HashMap<String, Double>();
        DecimalFormat df = new DecimalFormat("0.00");
        Boolean flag = true;
        VoFact fact = null;
        //////////////declared sql part///////////////////////
        StringBuffer part1 = new StringBuffer(" from BisStore a where 1=1");
        StringBuffer part2 = null;
        String part3 = "select bis_tenant_id,bis_shop_id,bis_shop_conn_id,bis_project_id from bis_tenant where bis_tenant_id in ("+
        				"select bis_tenant_id from bis_cont where bis_cont_id in(:bis_cont_id))";
        Map<String, Object> param1 = new HashMap<String, Object>();
        Map<String, Object> param2 = new HashMap<String, Object>();
        Map<String, Object> param3 = new HashMap<String, Object>();
        /**从前台赋值 storeNo**/
        if(StringUtils.isNotBlank(voFact.getStoreNo())){
        	param1.put("storeNo", "%"+voFact.getStoreNo()+"%");
	    	part1.append(" and a.storeNo like :storeNo ");
        }
        if(StringUtils.isNotBlank(voFact.getBisProjectId())){
        	part1.append(" and bisProjectId=:bisProjectId ");
        	param1.put("bisProjectId", voFact.getBisProjectId());
        }
        part1.append(" order by storeNo ASC");
        
		/**用findpageSql查询限制条数*/
		Page<BisStore> bisStorePage = bisStoreDao.findPage(voPage, part1.toString(), param1);
		/** 总计 应收实收预收欠费*/
		double totalMust = 0;
		double totalFact = 0;
		double totalYu	 = 0;
		double totalQian = 0;
		for (BisStore bisStore : bisStorePage.getResult()) {
			VoFact vo = new VoFact();
			/**设置商铺号*/
			vo.setStoreNo(bisStore.getStoreNo());
			vo.setBisStoreId(bisStore.getBisStoreId());
			/**商铺定位*/
			vo.setManageCd(bisStore.getShopPosition());
			/**计租面积*/
			vo.setMoney(bisStore.getRentSquare());
			vo.setSplitStatus(bisStore.getSplitStatus());
			List<String> contList = new ArrayList<String>();
			List<BisTenant> tenateList = new ArrayList<BisTenant>();
			/**拼装biscont集合*/
			if(bisStore.getBisStoreId()!=null&&!bisStore.getBisStoreId().isEmpty()){
				part2 = new StringBuffer("select t2.bis_cont_id from bis_cont t1, bis_store_cont_rel t2 where 1 = 1 ");
				part2.append(" and t1.bis_cont_id = t2.bis_cont_id");
				part2.append(" and t2.bis_store_id = :bisStoreId");
				part2.append(" and t1.status_cd = 3");//已审核
				part2.append(" and t1.active_bl = 1");//未失效
				param2.put("bisStoreId", bisStore.getBisStoreId());
				/**循环查询*/
				List<Object> bscrList = bisStoreContRelManager.findBySql(part2.toString(), param2);
				for (Object obj: bscrList) {
					contList.add(obj.toString());
				}
			}
			/**拼装bisTenant集合*/
			if(contList.size()>0){
				param3.put("bisContId", contList);
				param3.put("bis_cont_id", contList);
				List<Object[]> bisTenateList1 = bisTenantManager.findBySql(part3.toString(), param3);
				BisTenant bnt = null;
				for (Object[] object : bisTenateList1) {
					bnt = new BisTenant();
					bnt.setBisTenantId(object[0]==null?"":object[0].toString());
					bnt.setBisShopId(object[1]==null?"":object[1].toString());
					bnt.setBisShopConnId(object[2]==null?"":object[2].toString());
					bnt.setBisProjectId(object[3]==null?"":object[3].toString());
					tenateList.add(bnt);
				}
			}
			
			/**查询商铺名字的数据库语句*/
			StringBuffer getStoreNameSql = new StringBuffer("select NAME_CN from bis_shop where rownum = 1 ");
			/**查询备用商铺名字数据库语句(责任人)*/
			StringBuffer getOwnerNameSql = new StringBuffer("select owner from bis_tenant where bis_shop_id is null and bis_tenant_id =:bis_tenant_id");
			/**查询所占商铺号的语句*/
			StringBuffer getStoreNos = new StringBuffer("select store_no from bis_store where bis_store_id in");
			/**查询累计应收语句*/
			String sumMustSql = "select sum(money) from bis_must where status_cd =2 and bis_tenant_id = :bis_tenant_id";
			/**查询累计实收语句*/
			String sumFactSql = "select sum(money) from bis_fact where status_cd =1 and bis_tenant_id = :bis_tenant_id";
			/**查询累计预收语句*/
			String sumYusql = "select sum(money) from bis_fact_yu_s where status_cd =1 and bis_tenant_id = :bis_tenant_id";
			
			List<Object> tlistStName = null;
			List<Object> tlistOwner = null;
			List<Object> tlistNos = null;
			List<Object> tlistMust = null;
			List<Object> tlistFact = null;
			List<Object> tlistYu = null;
			if(!tenateList.isEmpty()){
				getStoreNameSql.append(" and bis_shop_id = :bis_shop_id");
				getStoreNos.append(" (select bis_store_id from bis_store_cont_rel where bis_cont_id in");
				getStoreNos.append(" (select bis_cont_id from bis_cont  where bis_tenant_id =:bis_tenant_id))");
				getStoreNos.append(" order by store_no asc");
				Map<String, Object> param4 = new HashMap<String, Object>();
				/**此处取tenateList第一条数据作为参数*/
				param4.put("bis_shop_id", tenateList.get(0).getBisShopId());
				param4.put("bis_tenant_id", tenateList.get(0).getBisTenantId());
				/**查询出store中文名*/
				tlistStName = bisShopManager.findBySql(getStoreNameSql.toString(), param4);
				/**查询出备用名字*/
				tlistOwner = bisTenantManager.findBySql(getOwnerNameSql.toString(), param4);
				tlistNos =  bisStoreDao.findBySql(getStoreNos.toString(), param4);
				tlistMust = bisMustManager.findBySql(sumMustSql, param4);
				tlistFact = bisFactDao.findBySql(sumFactSql, param4);
				tlistYu = bisFactYuSDao.findBySql(sumYusql, param4);
				
				/**赋值商家名称,在此判断bisShopId是否为空，如果为空，则取合同责任人、如果不为空，则继续取商铺名*/
				if(param4.get("bis_shop_id")==null||param4.get("bis_shop_id").toString().length()<1){
					vo.setNameCn(valiListForEmpty(tlistOwner)?"":tlistOwner.toString());
				}else{
					vo.setNameCn(valiListForEmpty(tlistStName)?"":tlistStName.get(0).toString());
				}
				/**赋值所占商铺号*/
				vo.setStatusCd(valiListForEmpty(tlistNos)?"":tlistNos.toString());
				/**租户编号启用*/
				vo.setBisTenantId(tenateList.get(0).getBisTenantId());
				
				/***判断出现重复的情况*/
				if(!valiListForEmpty(tlistNos)&&vo.getStoreNo().equals(tlistNos.get(0).toString())){
					/**赋值累计应收*/
					vo.setSumMustMoney(valiListForEmpty(tlistMust)?"0":tlistMust.get(0).toString());
					/**赋值累计实收*/
					vo.setSumFactMoney(valiListForEmpty(tlistFact)?"0":tlistFact.get(0).toString());
					/**赋值累计预收*/
					vo.setSumYuMoney(valiListForEmpty(tlistYu)?"0":tlistYu.get(0).toString());
					/**赋值累计欠费*/
					double tdb = Double.parseDouble(vo.getSumMustMoney())-Double.parseDouble(vo.getSumFactMoney());
//					tdb = tdb<0?0:tdb;
					vo.setSumQianMoney(df.format(tdb));
				}else{
					vo.setSumMustMoney("0");
					vo.setSumFactMoney("0");
					vo.setSumYuMoney("0");
					vo.setSumQianMoney("0");
				}
			}
			/**排除已经拆分的状态*/
			if(vo.getSplitStatus()==null||!"1".equals(vo.getSplitStatus())||"0".equals(vo.getSplitStatus())){
				result.add(vo);
			}
			totalMust += Double.parseDouble(vo.getSumMustMoney());
			totalFact += Double.parseDouble(vo.getSumFactMoney());
			totalYu	  += Double.parseDouble(vo.getSumYuMoney());
			totalQian += Double.parseDouble(vo.getSumQianMoney());
		}
//		/**合计*/
//		totalCountMap.put("totalMust", df.format(totalMust));
//		totalCountMap.put("totalFact", df.format(totalFact));
//		totalCountMap.put("totalYu", df.format(totalYu));
//		totalCountMap.put("totalQian", df.format(totalQian<0?0:totalQian));//如果小于0，则统一为0
		voPage.setResult(result);
        return voPage;
    }
    /***
     * 查询应收明细中的合计
     * 与查询应收明细相似，目前基于该方法改写
     * @param voPage
     * @param voFact
     * @return
     */
    public Map<String, String> getTotalCount(VoFact voFact) {
        Map<String, String> totalCountMap = new HashMap<String,String>();
        DecimalFormat df = new DecimalFormat("0.00");
        /** 总计 应收实收预收欠费*/
        String totalMust = "";
        String totalFact = "";
        String totalYu	 = "";
        double totalQian = 0;
		List<Object> tlistMust = null;
		List<Object> tlistFact = null;
		List<Object> tlistYu = null;
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("bisProjectId", voFact.getBisProjectId());
        param.put("storeNo", "%" + voFact.getStoreNo()+"%");
        /**通用拼凑sql*/
        StringBuffer sql = new StringBuffer("");
        sql.append("   (select distinct (c.bis_tenant_id) from bis_cont c,")
        .append("       (select a.bis_cont_id from bis_store_cont_rel a,")
        .append("               (select bis_store_id from bis_store ts")
        .append("                 where ts.bis_project_id = :bisProjectId");
        //如果该项值为空
        if(StringUtils.isNotBlank(voFact.getStoreNo())&&!"搜索商家/商铺".equals(voFact.getStoreNo().trim())){
        	sql.append("                   and ts.store_no like :storeNo ");
        }
        sql.append("         ) b ")
        .append("         where a.bis_store_id = b.bis_store_id) d")
        .append("     where c.bis_cont_id = d.bis_cont_id and c.status_cd = 3 and c.active_bl = 1) b")
        .append(" where a.bis_tenant_id = b.bis_tenant_id");
        //应收
        StringBuffer countMust = new StringBuffer("select sum(a.money) from bis_must a,");
        countMust.append(sql.toString()+" and a.status_cd = 2");
        tlistMust = bisMustManager.findBySql(countMust.toString(), param);
        //实收
        StringBuffer countFact = new StringBuffer("select sum(a.money) from bis_fact a,");
        countFact.append(sql.toString()+" and a.status_cd = 1");
        tlistFact = bisFactDao.findBySql(countFact.toString(), param);
        //预收
        StringBuffer countYu = new StringBuffer("select sum(a.money) from bis_fact_yu_s a,");
        countYu.append(sql.toString()+" and a.status_cd = 1");
        tlistYu = bisFactYuSDao.findBySql(countYu.toString(), param);
		
        /**赋值累计应收*/
		totalMust = valiListForEmpty(tlistMust)?"0":tlistMust.get(0).toString();
		/**赋值累计实收*/
		totalFact = valiListForEmpty(tlistFact)?"0":tlistFact.get(0).toString();
		/**赋值累计预收*/
		totalYu = valiListForEmpty(tlistYu)?"0":tlistYu.get(0).toString();
		/**赋值累计欠费*/
		totalQian = Double.parseDouble(totalMust)-Double.parseDouble(totalFact);
        
		totalCountMap.put("totalMust", totalMust);
		totalCountMap.put("totalFact", totalFact);
		totalCountMap.put("totalYu", totalYu);
		totalCountMap.put("totalQian", df.format(totalQian));//如果小于0，则统一为0
        return totalCountMap;
    }
    /***
     * 验证各种为空的集合
     * @param list
     * @return
     */
    private boolean valiListForEmpty(List list){
    	if(list==null||list.isEmpty()||list.get(0)==null||list.get(0)=="null"||list.get(0)=="")
			return true;
    	return false;
    }
    /**
     * 1 费用类型为保证金，无年月，无合同 2 若费用类型为物业履约保证费|租金履约保证费|经营保证费|广告收入|停车费，无应收款，无合同 3
     * 存在唯一合同，关联应收 4 存在多条合同，不冲突，合并应收 5 冲突，报错
     */
    public Page<VoFact> searchFactForStore(Page voPage, VoFact voFact, String isExport) {
        Map<Integer, Object> map = paramMap(voFact, voPage);
        map.put(20, voFact.getBisTenantIds());
        /*List<VoFact> list = (List<VoFact>) this.executeFunction(
                  "{ call BIS_FACT_PKG.BIS_FACT_STORES(?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,? ,?)}", map,
                  VoFact.class);*/
        Page<BisFact> factPage = factStoreBySql(voPage, voFact);
        List<VoFact> result = new ArrayList<VoFact>();
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
        Boolean flag = true;
        VoFact fact = null;
        for (BisFact vo : factPage.getResult()) {
            fact = new VoFact();
            //实收
            if (vo.getMoney() != null) {
                fact.setMoney(vo.getMoney());
            }
            if (null != vo.getBisContId()) {
                //商铺编号
                fact.setStoreNo(BisCache.getStoreNos(vo.getBisContId()));
            }
            //商家名称
            if (null != vo.getBisShopId()) {
                BisShop shop = bisShopManager.getEntity(vo.getBisShopId());
                fact.setConnName(shop.getNameCn());
            }
            fact.setBisProjectId(vo.getBisProjectId());
            fact.setBisTenantId(vo.getBisTenantId());
            fact.setStatusCd(vo.getStatusCd());
            fact.setStatusCdName(DictMapUtil.getMapBisFactStatus(vo.getStatusCd()));
            fact.setBisFactId(vo.getBisFactId());
            fact.setChargeTypeCdName(DictMapUtil.getMapChargeType(vo.getChargeTypeCd()));
            fact.setFactYear(vo.getFactYear());
            fact.setFactMonth(vo.getFactMonth());
            if (vo.getTotalMustMoney() != null) {
                fact.setMustMoney(vo.getTotalMustMoney());
            }
            //实收日期
            if (null != vo.getFactDate()) {
                fact.setFactDate(format.format(vo.getFactDate()));
            }
            if (null != vo.getUpdatedDate()) {
                fact.setUpdateDate(format.format(vo.getUpdatedDate()));
            }
            //用于处理预收明细与应付明细的不同数据状态（很重要）
            fact.setBisFactYuSId(vo.getBisFactYuSId());
            result.add(fact);
            /*if (notBlank(vo.getShopTypeCd())) {
                   vo.setShopTypeCd(DictMapUtil.getMapBisShopType().get(vo.getShopTypeCd()));
               }
               if (notBlank(vo.getManageCd())) {
                   vo.setManageCd(DictMapUtil.getMapShopManageType().get(vo.getManageCd()));
               }*/

            /*if (vo.getMoney() != null) {
                   if (vo.getMoney().indexOf(".") == 0) {
                       vo.setMoney("0" + vo.getMoney());
                   }
                   vo.setMoney(NumberUtil.formatDecimal2(vo.getMoney()));
               }
               if (null != vo.getBisContId()) {
                   vo.setStoreNo(BisCache.getStoreNos(vo.getBisContId()));
               }
               vo.setStatusCdName(DictMapUtil.getMapBisFactStatus(vo.getStatusCd()));
               vo.setChargeTypeCdName(DictMapUtil.getMapChargeType(vo.getChargeTypeCd()));
               if (notBlank(vo.getShopTypeCd())) {
                   vo.setShopTypeCd(DictMapUtil.getMapBisShopType().get(vo.getShopTypeCd()));
               }
               if (notBlank(vo.getManageCd())) {
                   vo.setManageCd(DictMapUtil.getMapShopManageType().get(vo.getManageCd()));
               }

                * if (2 == voFact.getMustOrFact()||3 == voFact.getMustOrFact()) {
                * vo.setStoreNo(this.getStoreNo(vo.getBisTenantId())); }

               result.add(vo);
               if (flag) {
                   voPage.setPageNo(vo.getPageNo() == null ? 1 : vo.getPageNo().intValue());
                   voPage.setTotalCount(vo.getMaxrowno().intValue());

                   flag = false;
               }
           */
        }
        if (!"export".equals(isExport)) {
            VoFact total = new VoFact();
            total.setMustMoney(bisMustManager.mustMoneySum(voFact));
            total.setMoney(factMoneySum(voFact));
            total.setChargeTypeCd("000");
            total.setChargeTypeCdName("合计");
            if (factPage.getResult().size() > 0) {
                result.add(total);
            }
        }
        voPage.setResult(result);
        return voPage;
    }

    /**
     * 搜索商铺实收
     *
     * @param voFact
     * @return
     */
    private Page<BisFact> factStoreBySql(Page voPage, VoFact voFact) {
        Map<String, Object> param = new HashMap<String, Object>();
        //若跟合同关联，则取与合同关联的商铺实收记录
        StringBuffer sql = new StringBuffer("select fc.* from Bis_fact fc where fc.bis_Project_Id=:bisProjectId ");
        param.put("bisProjectId", voFact.getBisProjectId());
        sql.append(" and fc.bis_Tenant_Id is not null  and fc.bis_cont_id is not null");
        //类别
        if (StringUtils.isNotBlank(voFact.getChargeTypeCd())) {
            sql.append(" and fc.CHARGE_TYPE_CD =:chargeTypeCd");
            param.put("chargeTypeCd", voFact.getChargeTypeCd());
        }
        //租户
        if (StringUtils.isNotBlank(voFact.getBisTenantIds())) {
            sql.append(" and fc.Bis_Tenant_Id in (");
            String[] tenantIds = voFact.getBisTenantIds().split(",");
            for (int i = 0; i < tenantIds.length; i++) {
                if (i == tenantIds.length - 1) {
                    sql.append("'").append(tenantIds[i]).append("'");
                } else {
                    sql.append("'").append(tenantIds[i]).append("'").append(",");
                }
            }
            sql.append(")");
            //param.put("bisTenantId", voFact.getBisTenantIds());
        }

        /*//年
        if (StringUtils.isNotBlank(voFact.getFactYear())) {
            sql.append(" and fc.fact_year =:factYear");
            param.put("factYear", voFact.getFactYear());
        }
        //月
        if (StringUtils.isNotBlank(voFact.getFactMonth())) {
            sql.append(" and fc.fact_month =:factMonth");
            param.put("factMonth", voFact.getFactMonth());
        }*/
        //实收权责年月起止时间 add by zhengyi at 2012-06-29
        
        if (StringUtils.isNotBlank(voFact.getReportDateStart())) {
        	//由于实收权责年月在设计时是分开的，所以处理起来比较麻烦需要先合并年月
        	sql.append(" and (ltrim(fc.fact_year)||'-'||lpad(fc.fact_month,2,'0'))>=:reportDateStart");
        	param.put("reportDateStart", voFact.getReportDateStart());
        }
        if (StringUtils.isNotBlank(voFact.getReportDateEnd())) {
        	//由于实收权责年月在设计时是分开的，所以处理起来比较麻烦需要先合并年月
        	sql.append(" and (ltrim(fc.fact_year)||'-'||lpad(fc.fact_month,2,'0'))<=:reportDateEnd");
            param.put("reportDateEnd", voFact.getReportDateEnd());
        }
        
        //高级搜索的实收年、月
        if (StringUtils.isNotBlank(voFact.getMinMonth())) {
            sql.append(" and fc.FACT_DATE >=to_date(:minMonth,'yyyy-MM-dd')");
            param.put("minMonth", voFact.getMinMonth());
        }
        if (StringUtils.isNotBlank(voFact.getMaxMonth())) {
            sql.append(" and fc.FACT_DATE <=to_date(:maxMonth,'yyyy-MM-dd')");
            param.put("maxMonth", voFact.getMaxMonth());
        }
        //创建人
        if (StringUtils.isNotBlank(voFact.getCreator())) {
            sql.append(" and fc.CREATOR =:creator");
            param.put("creator", voFact.getCreator());
        }
        //审核人
        if (StringUtils.isNotBlank(voFact.getCheckUserCd())) {
            sql.append(" and fc.CHECK_USER_CD =:checkUserCd");
            param.put("checkUserCd", voFact.getCheckUserCd());
        }
        //实收金额
        if (voFact.getMinMoney() != null) {
            sql.append(" and fc.money >=:minMoney");
            param.put("minMoney", voFact.getMinMoney());
        }
        if (voFact.getMaxMoney() != null) {
            sql.append(" and fc.money<=:maxMoney");
            param.put("maxMoney", voFact.getMaxMoney());
        }

        if (StringUtils.isNotBlank(voFact.getStatusCd())) {
            sql.append(" and fc.status_cd=:statusCd");
            param.put("statusCd", voFact.getStatusCd());
        }

        sql.append(" order by fc.UPDATED_DATE desc");
        Map<String, Class> mapClazz = new HashMap<String, Class>();
        mapClazz.put("bis", BisFact.class);
        Page<BisFact> factPage = this.findPageSql(voPage, sql.toString(), param, mapClazz);
        return factPage;
    }

    // 搜索实收列表
    public List<BisFact> getFactForStore(String bisProjectId, String bisTenantId, String chargeTypeCd, String factYear,
                                         String factMonth, String bisContId) {
        return getFact(bisProjectId, bisTenantId, null, null, chargeTypeCd, factYear, factMonth, bisContId);
    }

    public List<BisFact> getFactForFlat(String bisProjectId, String bisFlatId, String chargeTypeCd, String factYear,
                                        String factMonth) {
        return getFact(bisProjectId, null, bisFlatId, null, chargeTypeCd, factYear, factMonth, null);
    }

    public List<BisFact> getFactForMulti(String bisProjectId, String bisMultiId, String chargeTypeCd, String factYear,
                                         String factMonth) {
        return getFact(bisProjectId, null, null, bisMultiId, chargeTypeCd, factYear, factMonth, null);
    }

    public List<BisFact> getFact(String bisProjectId, String bisTenantId, String bisFlatId, String bisMultiId,
                                 String chargeTypeCd, String factYear, String factMonth, String bisContId) {
        StringBuffer hql = new StringBuffer();
        Map<String, Object> values = new HashMap<String, Object>();
        hql.append(" from BisFact f ").append(" where 1=1 ")
                .append(" and f.factYear =:factYear")
                .append(" and f.factMonth =:factMonth");
        if (notBlank(chargeTypeCd)) {
            hql.append(" and f.chargeTypeCd =:chargeTypeCd");
            values.put("chargeTypeCd", chargeTypeCd);
        }
        if (notBlank(bisTenantId)) {
            hql.append(" and f.bisTenantId =:bisTenantId");
            values.put("bisTenantId", bisTenantId);
        }
        if (notBlank(bisContId)) {
            hql.append(" and f.bisContId =:bisContId");
            values.put("bisContId", bisContId);
        } else {
            if (notBlank(bisMultiId)) {
                hql.append(" and f.bisMultiId =:bisMultiId");
                values.put("bisMultiId", bisMultiId);
            }
            if (notBlank(bisFlatId)) {
                hql.append(" and f.bisFlatId =:bisFlatId");
                values.put("bisFlatId", bisFlatId);
            }
        }
        if (notBlank(bisProjectId)) {
            values.put("bisProjectId", bisProjectId);
            hql.append(" and f.bisProjectId =:bisProjectId");
        }
        values.put("factYear", factYear);
        values.put("factMonth", factMonth);
        List<BisFact> result = this.getDao().find(hql.toString(), values);
        return result;
    }

    public List<VoFact> getFactVo4Store(String bisProjectId, String bisTenantId, String bisFlatId, String bisMultiId,
                                        String chargeTypeCd, String factYear, String factMonth) {
        StringBuffer hql = new StringBuffer(
                "	select f.bis_fact_id,f.bis_cont_id,f.bis_tenant_id,f.bis_project_id,						 "
                        + "		f.charge_type_cd,f.fact_year,f.fact_month,f.status_cd,f.fact_date,					 "
                        + "		m.money													 "
                        + "	 from bis_fact f												 "
                        + "															 "
                        + "	left join (													 "
                        + "	select mm.bis_cont_id,mm.charge_type_cd,mm.must_year,mm.must_month ,sum(mm.money) money from bis_must mm	 "
                        + "	     group by mm.bis_cont_id,mm.charge_type_cd,mm.must_year,mm.must_month 					 "
                        + "	)m														 " + "															 " + "	on f.bis_cont_id = m.bis_cont_id										 "
                        + "	and f.charge_type_cd = m.charge_type_cd										 "
                        + "	and f.fact_year      = m.must_year										 "
                        + "	and f.fact_month     = m.must_month										 ");
        hql.append("select f.*, m.* from BisFact f,BisMust m ").append(" where f.bisProjectId =:bisProjectId")
                .append(" and f.bisProjectId =:bisProjectId").append(" and f.chargeTypeCd =:chargeTypeCd")
                .append(" and f.factYear =:factYear").append(" and f.factMonth =:factMonth");
        if (notBlank(bisTenantId)) {
            hql.append(" and f.bisTenantId =:bisTenantId");
        }
        if (notBlank(bisFlatId)) {
            hql.append(" and f.bisFlatId =:bisFlatId");
        }
        if (notBlank(bisMultiId)) {
            hql.append(" and f.bisMultiId =:bisMultiId");
        }
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("bisProjectId", bisProjectId);
        values.put("bisTenantId", bisTenantId);
        values.put("bisFlatId", bisFlatId);
        values.put("bisMultiId", bisMultiId);
        values.put("chargeTypeCd", chargeTypeCd);
        values.put("factYear", factYear);
        values.put("factMonth", factMonth);
        List<Object> result = this.getDao().find(hql.toString(), values);
        return null;// til.ResultsToBeans(new VoFact(),result);
    }

    public Map<Integer, Object> paramMap(VoFact voFact, Page voPage) {
        Map<Integer, Object> values = new HashMap<Integer, Object>();
        values.put(1, voPage.getPageNo());
        if (!voFact.isNoPage()) {
            values.put(2, voPage.getPageSize());
        } else {
            values.put(2, null);

        }
        values.put(3, voFact.getBisProjectId());
		if (NumberUtil.isNumber(voFact.getContLayOutCd())) {
			int type = Integer.valueOf(voFact.getContLayOutCd());
			switch (type) {
			case BIS_CONT_TYPE_STORE:
				values.put(4, voFact.getBisTenantId());
				break;
			case BIS_CONT_TYPE_FLAT:
				values.put(4, voFact.getBisFlatId());
				break;
			case BIS_CONT_TYPE_MULTI:
				values.put(4, voFact.getBisMultiId());
				break;
			}

			values.put(5, voFact.getChargeTypeCd());
			values.put(6, voFact.getFactYear());
			values.put(7, voFact.getFactMonth());
			values.put(8, voFact.getBisFactId());
			values.put(9, voFact.getMinMonth());
			values.put(10, voFact.getMaxMonth());
			values.put(11, voFact.getCreator());
			values.put(12, voFact.getCheckUserCd());
			values.put(13, voFact.getStatusCd());
			values.put(14, voFact.getMinMoney());
			values.put(15, voFact.getMaxMoney());
			values.put(16, voFact.getMustOrFact());
			values.put(17, voFact.getInDateBegin());
			values.put(18, voFact.getInDateEnd());
			values.put(19, voFact.getOverdue());
		}else{
			log.error("voFact.getContLayOutCd() is not number");
		}
        return values;
    }

    public static boolean blank(String str) {
        if (null == str || StringUtils.isBlank(str) || "undefined".equals(str) || "null".equals(str))
            return true;
        return false;
    }

    public static boolean notBlank(String str) {
        return !blank(str);
    }

    public List<VoCombo> loadCreatUser(String bisProjectId, String layoutCd) {
        return loadFactUser(bisProjectId, layoutCd, "creator");
    }

    public List<VoCombo> loadCheckUser(String bisProjectId, String layoutCd) {
        return loadFactUser(bisProjectId, layoutCd, "check");
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<VoCombo> loadFactUser(String bisProjectId, String layoutCd, String creatorOrCheck) {
        StringBuffer hql = new StringBuffer(" from BisFact t where t.bisProjectId = ? and  ");
        int BIS_CONT_TYPE = Integer.valueOf(layoutCd);
        switch (BIS_CONT_TYPE) {
            case BIS_CONT_TYPE_STORE:
                hql.append(" t.bisTenantId is not null");
                break;
            case BIS_CONT_TYPE_FLAT:
                hql.append(" t.bisFlatId is not null");
                break;
            case BIS_CONT_TYPE_MULTI:
                hql.append(" t.bisMultiId is not null");
                break;
        }
        List<BisFact> result = this.getDao().createQuery(hql.toString(), bisProjectId).list();
        List<VoCombo> list = new ArrayList<VoCombo>();
        VoCombo bo = null;
        for (BisFact vo : result) {
            bo = new VoCombo();
            if (creatorOrCheck.equals("creator")) {
                if (StringUtils.isBlank(vo.getCreator())) {
                    continue;
                }
                bo.setId(vo.getCreator());
                bo.setText(vo.getCreator());
            } else if (creatorOrCheck.equals("check")) {
                if (StringUtils.isBlank(vo.getCheckUserCd())) {
                    continue;
                }
                bo.setId(vo.getCheckUserCd());
                bo.setText(vo.getCheckUserCd());

            }
            list.add(bo);
        }
        return list;
    }

    /**
     * 搜索缴费通知 逻辑：统计 某合同当年月的：租金、综合管理费、公摊费、空调费、水费、电费等，取最少的应收日期为缴费
     * 条件：指定年月，指定项目，模糊搜索商家、商铺， 逻辑：搜索符合以上条件的应收记录，不考虑是否录入实收
     */
    public Page<PayRequisitionVo> searchPayRequisitions(PayRequisitionVo vo) {

        Page<PayRequisitionVo> voPage = new Page<PayRequisitionVo>();
        Map<Integer, Object> map = new HashMap<Integer, Object>();
        map.put(1, vo.getShopName());
        map.put(2, vo.getStoreNo());
        map.put(3, vo.getFloorNum());
        map.put(4, vo.getBisProjectId());
        map.put(5, vo.getYear());
        map.put(6, vo.getMonth());
        map.put(7, vo.getPageNo());
        map.put(8, vo.getPageSize());
        List<PayRequisitionVo> list = (List<PayRequisitionVo>) this.executeFunction(
                "{ call BIS_FACT_PKG.BIS_FACT_PAY_REQUISITION(?,?,?,?,? ,?,?,?,?)}", map, PayRequisitionVo.class);

        StringBuffer hql = new StringBuffer(" from BisMust m where 1=1 and m.statusCd='2'   ");
        List<PayRequisitionVo> result = new ArrayList<PayRequisitionVo>();
        boolean flag = true;
        for (PayRequisitionVo obj : list) {
            result.add(convert(obj));
            if (flag) {
                voPage.setPageNo(obj.getPageNo() == null ? 1 : obj.getPageNo().intValue());
                voPage.setTotalCount(obj.getMaxrowno().intValue());
                flag = false;
            }
        }
        voPage.setResult(result);
        return voPage;
    }

    /**
     * 处理缴费通知
     */
    private PayRequisitionVo convert(PayRequisitionVo vo) {
        String[] chargeTypeMoneys = vo.getChargetTypeMoney().split(",");
        String charge = null;
        BigDecimal tmpMoney;
        BigDecimal sum;
        Date mustDate = null;
        try {
            for (int i = 0; i < chargeTypeMoneys.length; i++) {
                charge = chargeTypeMoneys[i].split("_")[0];

                mustDate = DateUtil.parseStringToDate(chargeTypeMoneys[i].split("_")[2]);
                if (null != vo.getMustDate()) {
                    mustDate = mustDate.before(vo.getMustDate()) ? mustDate : vo.getMustDate();

                }
                vo.setYearMonth(vo.getYear() + "年" + vo.getMonth() + "月");
                vo.setMustDate(mustDate);
                vo.setStoreNos(BisCache.getStoreNos(vo.getBisContId()));
                vo.setSquare(BisCache.getStoreSquares(vo.getBisContId()));

                tmpMoney = new BigDecimal(chargeTypeMoneys[i].split("_")[1]);
                sum = vo.getTotalNum() == null ? new BigDecimal(0) : vo.getTotalNum();
                switch (Integer.valueOf(charge)) {
                    case 4:
                        vo.setChargeType1Must(tmpMoney);
                        sum = sum.add(vo.getChargeType1Must());
                        vo.setChargeType1Fact(getOverDueMoney(vo, charge, tmpMoney));
                        break;
                    case 7:
                        vo.setChargeType2Must(tmpMoney);
                        sum = sum.add(vo.getChargeType2Must());
                        vo.setChargeType2Fact(getOverDueMoney(vo, charge, tmpMoney));
                        break;
                    case 8:
                        vo.setChargeType3Must(tmpMoney);
                        sum = sum.add(vo.getChargeType3Must());
                        vo.setChargeType3Fact(getOverDueMoney(vo, charge, tmpMoney));
                        break;
                    case 6:
                        vo.setChargeType4Must(tmpMoney);
                        sum = sum.add(vo.getChargeType4Must());
                        vo.setChargeType4Fact(getOverDueMoney(vo, charge, tmpMoney));
                        break;
                    case 5:
                        vo.setChargeType5Must(tmpMoney);
                        sum = sum.add(vo.getChargeType5Must());
                        vo.setChargeType5Fact(getOverDueMoney(vo, charge, tmpMoney));
                        break;
                    default: {
                        continue;
                    }
                }
                vo.setTotalNum(sum);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vo;
    }

    /**
     * 计算尚欠费用
     */
    private BigDecimal getOverDueMoney(PayRequisitionVo vo, String charge, BigDecimal mustMoney) {
        return mustMoney.subtract(getFactSum(vo.getBisContId(), vo.getYear(), vo.getMonth(), charge));
    }

    /**
     * 统计商铺建筑面积之和
     */
    public BigDecimal getStoreSquares(String bisContId) {

        List<Object> store = getStoreInfo(null, bisContId);
        BigDecimal sumSquare = new BigDecimal(0);
        for (int i = 0; i < store.size(); i++) {
            Object[] s = (Object[]) store.get(i);
            sumSquare = sumSquare.add((BigDecimal) s[2]);
        }
        return sumSquare;
    }

    private List<Object> getStoreInfo(String bisTenantId, String bisContId) {
        StringBuffer sql = new StringBuffer();
        sql.append(
                " select distinct to_char(s.store_no),to_char(f.floor_num),s.square from bis_store_cont_rel r ,bis_floor f, bis_store s ,bis_cont c")
                .append("	where r.bis_store_id = s.bis_store_id  and s.bis_floor_id = f.bis_floor_id and c.bis_cont_id = r.bis_cont_id ");
        if (StringUtils.isNotBlank(bisTenantId)) {
            sql.append(" and c.bis_tenant_id =:bisTenantId");
        }
        if (StringUtils.isNotBlank(bisContId)) {
            sql.append(" and c.bis_cont_id =:bisContId ");
        }
        Map values = new HashMap<String, Object>();
        values.put("bisTenantId", bisTenantId);
        values.put("bisContId", bisContId);
        return this.getDao().findBySql(sql.toString(), values);
    }

    /**
     * 应收、实收分摊到铺
     */
    public void bisShareToStore(String bisFactId, String bisMustId) {
        if (StringUtils.isNotBlank(bisFactId)) {
            Map<Integer, Object> map = new HashMap<Integer, Object>();
            map.put(2, bisFactId);
            Util.executeFunction("{ call BIS_FACT_PKG.BIS_SHARE_TO_FACT_STORE(?,?,?)}", map);
        }
        if (StringUtils.isNotBlank(bisMustId)) {
            Map<Integer, Object> map = new HashMap<Integer, Object>();
            map.put(2, bisMustId);
            Util.executeFunction("{ call BIS_FACT_PKG.BIS_SHARE_TO_MUST_STORE(?,?,?)}", map);
        }

    }

    /**
     * 计算应收、实收总额
     */
    public boolean processFactMoney(String bisFactId, String bisMustId) {
        String chargeTypeCd;
        String year;
        String month;
        int floorTypeCd;
        String floorType;
        String bisContId = null;
        String bisFlatId = null;
        String bisMultiId = null;

        if (StringUtils.isNotBlank(bisFactId)) {
            BisFact curFact = this.getEntity(bisFactId);
            chargeTypeCd = curFact.getChargeTypeCd();
            year = curFact.getFactYear();
            month = curFact.getFactMonth();
            floorType = curFact.getFloorType();
            bisContId = curFact.getBisContId();
            if (StringUtils.isNotBlank(floorType)) {
                floorTypeCd = Integer.valueOf(floorType);
                switch (floorTypeCd) {
                    case BIS_CONT_TYPE_STORE:
                        break;
                    case BIS_CONT_TYPE_FLAT:
                        bisFlatId = curFact.getBisFlatId();
                        break;
                    case BIS_CONT_TYPE_MULTI:
                        bisMultiId = curFact.getBisMultiId();
                        break;
                }
            }
        } else if (StringUtils.isNotBlank(bisMustId)) {
            BisMust curMust = bisMustManager.getEntity(bisMustId);
            chargeTypeCd = curMust.getChargeTypeCd();
            year = curMust.getMustYear();
            month = curMust.getMustMonth();
            floorType = curMust.getFloorType();
            bisContId = curMust.getBisCont().getBisContId();
            if (StringUtils.isNotBlank(floorType)) {
                floorTypeCd = Integer.valueOf(floorType);
                switch (floorTypeCd) {
                    case BIS_CONT_TYPE_STORE:
                        break;
                    case BIS_CONT_TYPE_FLAT:
                        bisFlatId = curMust.getBisFlatId();
                        break;
                    case BIS_CONT_TYPE_MULTI:
                        bisMultiId = curMust.getBisMultiId();
                        break;
                }
            }
        } else
            return false;
        // 统计实收金额
        BigDecimal totalFact = new BigDecimal(0);
        BigDecimal totalMust = new BigDecimal(0);
        StringBuffer hql = new StringBuffer();
        hql.append(" from BisFact where 1=1 ").append(" and chargeTypeCd =:chargeTypeCd ")
                .append(" and factYear =:year").append(" and factMonth =:month");
        if (StringUtils.isNotBlank(bisContId)) {
            hql.append(" and bisContId=:bisContId");
        }
        if (StringUtils.isNotBlank(bisFlatId)) {
            hql.append(" and bisFlatId=:bisFlatId");
        }
        if (StringUtils.isNotBlank(bisMultiId)) {
            hql.append(" and bisMultiId=:bisMultiId");
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("bisContId", bisContId);
        param.put("bisFlatId", bisFlatId);
        param.put("bisMultiId", bisMultiId);
        param.put("chargeTypeCd", chargeTypeCd);
        param.put("year", year);
        param.put("month", month);
        List<BisFact> facts = this.find(hql.toString(), param);
        for (BisFact vo : facts) {
            totalFact = totalFact.add(vo.getMoney());
        }

        hql = new StringBuffer();
        hql.append(" from BisMust where 1=1 ").append(" and chargeTypeCd =:chargeTypeCd ")
                .append(" and mustYear =:year").append(" and mustMonth =:month");
        if (StringUtils.isNotBlank(bisContId)) {
            hql.append(" and bisCont.bisContId=:bisContId");
        }
        if (StringUtils.isNotBlank(bisFlatId)) {
            hql.append(" and bisFlatId=:bisFlatId");
        }
        if (StringUtils.isNotBlank(bisMultiId)) {
            hql.append(" and bisMultiId=:bisMultiId");
        }
        List<BisMust> musts = bisMustManager.find(hql.toString(), param);
        for (BisMust vo : musts) {
            totalMust = totalMust.add(vo.getMoney());
        }

        StringBuffer updateFacthql = new StringBuffer();
        StringBuffer updateMusthql = new StringBuffer();
        param.put("totalFact", totalFact);
        param.put("totalMust", totalMust);
        updateMusthql.append("update  BisMust set totalFactMoney=:totalFact  where 1=1 ")
                .append(" and chargeTypeCd =:chargeTypeCd ").append(" and mustYear =:year")
                .append(" and mustMonth =:month");
        if (StringUtils.isNotBlank(bisContId)) {
            updateMusthql.append(" and bisCont.bisContId=:bisContId");
        }
        if (StringUtils.isNotBlank(bisFlatId)) {
            updateMusthql.append(" and bisFlatId=:bisFlatId");
        }
        if (StringUtils.isNotBlank(bisMultiId)) {
            updateMusthql.append(" and bisMultiId=:bisMultiId");
        }
        updateFacthql.append(" update BisFact set totalFactMoney=:totalFact, totalMustMoney=:totalMust where 1=1 ")
                .append(" and chargeTypeCd =:chargeTypeCd ").append(" and factYear =:year")
                .append(" and factMonth =:month");
        if (StringUtils.isNotBlank(bisContId)) {
            updateFacthql.append(" and bisContId=:bisContId");
        }
        if (StringUtils.isNotBlank(bisFlatId)) {
            updateFacthql.append(" and bisFlatId=:bisFlatId");
        }
        if (StringUtils.isNotBlank(bisMultiId)) {
            updateFacthql.append(" and bisMultiId=:bisMultiId");
        }
        this.getDao().batchExecute(updateFacthql.toString(), param);
        this.getDao().batchExecute(updateMusthql.toString(), param);
        // 计算应收
        return true;
    }

    public List<BisFact> getExistsFact(String bisContId, String bisFlatId, String bisMultiId,
                                       String chargeTypeCd, String factYear, String factMonth) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("chargeTypeCd", chargeTypeCd);
        param.put("factYear", factYear);
        param.put("factMonth", factMonth);
        StringBuffer hql = new StringBuffer("from BisFact ");
        hql.append(" where chargeTypeCd=:chargeTypeCd and factYear=:factYear and factMonth=:factMonth and typeCd=:typeCd ");
        if ("1".equals(chargeTypeCd) || "3".equals(chargeTypeCd) || "4".equals(chargeTypeCd)) {
            param.put("typeCd", "0");
        } else {
            param.put("typeCd", "1");
        }
        if (StringUtils.isNotBlank(bisContId)) {
            hql.append(" and bisContId=:bisContId");
            param.put("bisContId", bisContId);
        }
        if (StringUtils.isNotBlank(bisFlatId)) {
            hql.append(" and bisFlatId=:bisFlatId");
            param.put("bisFlatId", bisFlatId);
        }
        //多经ID
        if (StringUtils.isNotBlank(bisMultiId)) {
            hql.append(" and bisMultiId=:bisMultiId");
            param.put("bisMultiId", bisMultiId);
        }
        List<BisFact> list = getDao().find(hql.toString(), param);
        if (list != null && list.size() > 0)
            return list;

        return null;
    }

    @Transactional
    public void batchExecute(List<BisFact> factList) {
        Session session = getDao().getSession();
        for (int i = 0; i < factList.size(); i++) {
            session.saveOrUpdate(factList.get(i));
            if (i % 50 == 0) {
                session.flush();
                session.clear();
            }
        }
    }

    @Transactional
    public void batchDelete(List<BisFact> factList) {
        Session session = getDao().getSession();
        for (int i = 0; i < factList.size(); i++) {
            session.delete(factList.get(i));
            if (i % 50 == 0) {
                session.flush();
                session.clear();
            }
        }
    }
    
    /**
     * 获取预收明细中审核通过且抵充后的记录
     * @param bisProjectId
     * @return
     */
    public Integer getDichongCount(String bisFactYsId){
    	Map<String,Object> map=new HashMap<String,Object>();
		StringBuilder sb=new StringBuilder();
		if(StringUtils.isNotBlank(bisFactYsId)){
			sb.append(" select count(*) from Bis_fact fc ");
			sb.append(" where fc.bis_fact_yu_s_id = :bisFactYsId ");
			map.put("bisFactYsId", bisFactYsId);
		}
		
		Object obj= bisFactDao.createSQLQuery(sb.toString(), map).uniqueResult();
		if(obj!=null)
			return Integer.parseInt(String.valueOf(obj));
		return 0;
    }
    
    
    /**
     * 获取所有抵充后的金额
     * @param bisProjectId
     * @return
     */
    public Double getAllDichongMoney(String bisProjectId,String bisFactYsId){
    	Map<String,Object> map=new HashMap<String,Object>();
		StringBuilder sb=new StringBuilder();
		if(StringUtils.isNotBlank(bisFactYsId)){
			sb.append(" select sum(fc.money) from Bis_fact fc ");
			sb.append(" where fc.bis_fact_yu_s_id = :bisFactYsId and fc.STATUS_CD =1");
			map.put("bisFactYsId", bisFactYsId);
			if(StringUtils.isNotBlank(bisProjectId)){
				sb.append(" and fc.BIS_PROJECT_ID =:bisProjectId");
				map.put("bisProjectId", bisProjectId);
			}
		}
		
		Object obj= bisFactDao.createSQLQuery(sb.toString(), map).uniqueResult();
		if(obj!=null)
			return Double.valueOf(String.valueOf(obj));
		return 0.0;
    }
    
    /**
     * 获取所有抵充后的金额(根据实际预收费Id、bisTenantId、收费类型、年、月更新)
     * @param bisProjectId
     * @return
     */
    public Double getAllDichongMoney(VoFact voFact){
    	Map<String,Object> map=new HashMap<String,Object>();
		StringBuilder sb=new StringBuilder();
		if(StringUtils.isNotBlank(voFact.getBisFactYuSId())){
			sb.append(" select sum(fc.money) from Bis_fact fc ");
			sb.append(" where fc.bis_fact_yu_s_id =:bisFactYsId and fc.STATUS_CD =1");
			map.put("bisFactYsId", voFact.getBisFactYuSId());
			if(StringUtils.isNotBlank(voFact.getBisTenantId())){
				sb.append(" and fc.BIS_TENANT_ID =:bisTenantId");
				map.put("bisTenantId", voFact.getBisTenantId());
			}
			if(StringUtils.isNotBlank(voFact.getBisProjectId())){
				sb.append(" and fc.BIS_PROJECT_ID =:bisProjectId");
				map.put("bisProjectId", voFact.getBisProjectId());
			}
			if(StringUtils.isNotBlank(voFact.getChargeTypeCd())){
				sb.append(" and fc.charge_type_cd =:chargeTypeCd");
				map.put("chargeTypeCd", voFact.getChargeTypeCd());
			}
			if(StringUtils.isNotBlank(voFact.getFactYear())){
				sb.append(" and fc.fact_year =:factYear");
				map.put("factYear", voFact.getFactYear());
			}
			if(StringUtils.isNotBlank(voFact.getFactMonth())){
				sb.append(" and fc.fact_month =:factMonth");
				map.put("factMonth", voFact.getFactMonth());
			}
		}
		
		Object obj= bisFactDao.createSQLQuery(sb.toString(), map).uniqueResult();
		if(obj!=null)
			return Double.valueOf(String.valueOf(obj));
		return 0.0;
    }
    
    /**
     * 更新当前预收明细中抵充后实收金额更新，同时更新应收金额(根据预收费Id、bisProjectId、收费类型、年、月更新)
     * @param voFact
     * @return
     */
    public void updateBisFactMoney(VoFact voFact){
    	Map<String,Object> map=new HashMap<String,Object>();
		StringBuilder sb=new StringBuilder();
		if(StringUtils.isNotBlank(voFact.getBisFactYuSId())){
			sb.append(" update Bis_fact fc set fc.TOTAL_MUST_MONEY =:mustMoney ");
			sb.append(" where fc.STATUS_CD =1");
			map.put("mustMoney", voFact.getMustMoney());
			if(StringUtils.isNotBlank(voFact.getBisFactYuSId())){
				sb.append(" and fc.bis_fact_yu_s_id =:bisFactYsId");
				map.put("bisFactYsId", voFact.getBisFactYuSId());
			}
			if(StringUtils.isNotBlank(voFact.getBisTenantId())){
				sb.append(" and fc.BIS_TENANT_ID =:bisTenantId");
				map.put("bisTenantId", voFact.getBisTenantId());
			}
			if(StringUtils.isNotBlank(voFact.getBisProjectId())){
				sb.append(" and fc.BIS_PROJECT_ID =:bisProjectId");
				map.put("bisProjectId", voFact.getBisProjectId());
			}
			if(StringUtils.isNotBlank(voFact.getChargeTypeCd())){
				sb.append(" and fc.charge_type_cd =:chargeTypeCd");
				map.put("chargeTypeCd", voFact.getChargeTypeCd());
			}
			if(StringUtils.isNotBlank(voFact.getFactYear())){
				sb.append(" and fc.fact_year =:factYear");
				map.put("factYear", voFact.getFactYear());
			}
			if(StringUtils.isNotBlank(voFact.getFactMonth())){
				sb.append(" and fc.fact_month =:factMonth");
				map.put("factMonth", voFact.getFactMonth());
			}
		}
		Query query=bisFactYuSDao.createSQLQuery(sb.toString(), map);
		query.executeUpdate();
		
    }
    
    
    /**
     * 获取所有抵充后记录条数(根据实际预收费Id、bisProjectId、收费类型、年、月更新)
     * @param bisProjectId
     * @return
     */
    public Integer getAllDichongMoneyCount(VoFact voFact){
    	Map<String,Object> map=new HashMap<String,Object>();
		StringBuilder sb=new StringBuilder();
		if(StringUtils.isNotBlank(voFact.getBisFactYuSId())){
			sb.append(" select count(*) from Bis_fact fc ");
			sb.append(" where fc.bis_fact_yu_s_id =:bisFactYsId and fc.STATUS_CD =1");
			map.put("bisFactYsId", voFact.getBisFactYuSId());
			if(StringUtils.isNotBlank(voFact.getBisProjectId())){
				sb.append(" and fc.BIS_PROJECT_ID =:bisProjectId");
				map.put("bisProjectId", voFact.getBisProjectId());
			}
			if(StringUtils.isNotBlank(voFact.getChargeTypeCd())){
				sb.append(" and fc.charge_type_cd =:chargeTypeCd");
				map.put("chargeTypeCd", voFact.getChargeTypeCd());
			}
			if(StringUtils.isNotBlank(voFact.getFactYear())){
				sb.append(" and fc.fact_year =:factYear");
				map.put("factYear", voFact.getFactYear());
			}
			if(StringUtils.isNotBlank(voFact.getFactMonth())){
				sb.append(" and fc.fact_month =:factMonth");
				map.put("factMonth", voFact.getFactMonth());
			}
		}
		
		Object obj= bisFactDao.createSQLQuery(sb.toString(), map).uniqueResult();
		if(obj!=null)
			return Integer.valueOf(String.valueOf(obj));
		return 0;
    }
}
