package com.hhz.ump.util;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.hhz.core.utils.ChangeCharset;
import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.RandomUtils;
import com.hhz.ump.cache.PlasCache;
import com.hhz.uums.entity.ws.WsPlasOrg;

/**
 * Created by IntelliJ IDEA.
 * User: 123
 * Date: 12-2-14
 * Time: 下午5:00
 * 会议纪要管理 辅助工具类
 */
public class HelperUtil {

    //数据字典 反向查找类
    private static CodeNameUtil dataDictionary = new CodeNameUtil();

    //会议类别
    public Map getCategorys() {
        return DictMapUtil.getMesMeetingCategoryCd();
    }

    //纪要状态
    public Map getStatus() {
        return DictMapUtil.getMesMeetingStatusCd();
    }

    public CodeNameUtil getDataDictionary() {
        return dataDictionary;
    }

    /**
     * 防止缓存
     */
    public static void nocache() {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragrma", "no-cache");
        response.setDateHeader("Expires", 0);
    }

    /**
     * 当前时间
     */
    public Date getCurrentDate() {
        return new Date();
    }

    /**
     * 当前时间
     */
    public Date getCurrentDate(Integer day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }


    public Date getCurrentDate(Date date, Integer day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    public String expirationDate(Date start) {
        long hour = System.currentTimeMillis() - start.getTime();
        return String.valueOf(24 - (hour / 1000 / 60 / 60));
    }

    /**
     * 状态吗 映射
     *
     * @param code
     * @return
     */
    public String getStatus(String code) {
        if (!getStatus().containsKey(code))
            return "未知";
        return String.valueOf(getStatus().get(code).toString());
        /*switch (Integer.parseInt(code)) {
            case 0:
                return "新增";
            case 1:
                return "纪要填写";
            case 2:
                return "审批中";
            case 3:
                return "完成";
            case 4:
                return "驳回";
            case 5:
                return "删除";
        }
        return "未知";*/
    }

    /**
     * 清理 文本中的 HTML标记
     *
     * @param strBuffer
     * @return
     */
    public static String filterHtml(String strBuffer) {
        return strBuffer.replaceAll("\"", "&quot;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll(" ", "&nbsp;").replaceAll("\n", "<br>");
    }

    public static String clearHtml(String strBuffer) {
        return filterHtml(strBuffer.replaceAll("</?[^<>]*>", "").replaceAll("\n", "").replaceAll("\r", "").replaceAll(" ", "").replaceAll("&nbsp;", "").replaceAll("　", ""));
    }

    //获取字节 到 K/MB/GB 单位的转换
    public static String getByteCNM(Long size) {
        if (size == 0 || size < 1024)
            return "1K";
        else if (size / (1024) < 1024)
            return (size / 1024) + "K";
        else if (size / (1024 * 1024) < 1024)
            return (size / (1024 * 1024)) + "M";
        else
            return (size / (1024 * 1024 * 1024)) + "G";
    }

    /**
     * 生成文件名
     *
     * @param uploadFileName1
     * @return
     */
    public static String getFileName(String uploadFileName1) {
        Date now = new Date();
        String dateFormat = DateOperator.formatDate(now, "yyyyMMddHHmmss_" + System.currentTimeMillis());
        String tmpName = dateFormat + RandomUtils.generateString(4) + getSuffix(uploadFileName1);
        return tmpName;
    }

    /**
     * 取得文件后缀
     *
     * @param fileNameTmp
     * @return
     */
    private static String getSuffix(String fileNameTmp) {
        String suffix = null;
        if (StringUtils.isNotEmpty(fileNameTmp)) {
            suffix = fileNameTmp.substring(fileNameTmp.lastIndexOf("."));
        }
        return suffix;
    }

    public static String getHtmlTemplate(String filepath) {
        try {
            return IOUtils.toString(HelperUtil.class.getClassLoader().getResourceAsStream("htmlTemplates/"+filepath), ChangeCharset.UTF_8);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "会议纪要 Email 群发模板 读取失败";
        }

    }


    //比较撮的方法名～～～
    //判断是否 属于商业公司用户
    public static boolean isEveryChildCompany() {
        List<WsPlasOrg> orgList = PlasCache.getPhysicalBubbleOrgListByOrgCd(SpringSecurityUtils.getCurrentDeptCd());
        for (WsPlasOrg org : orgList) {
            if ("512".equals(org.getOrgCd()))
				return true;
        }
        return false;
    }



    public static void main(String[] args) {
        //System.out.println(getEmailTemplate());
    }
}
