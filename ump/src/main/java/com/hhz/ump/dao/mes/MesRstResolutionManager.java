package com.hhz.ump.dao.mes;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.CoreContants;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.entity.app.AppAttachFile;
import com.hhz.ump.entity.mes.MesRstResolution;
import com.hhz.ump.util.EmailUtil;
import com.hhz.ump.util.HelperUtil;
import com.hhz.ump.util.LoginUtil;
import com.hhz.ump.util.Util;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
@Transactional

public class MesRstResolutionManager extends BaseService<MesRstResolution, String> {

    public static String KEY_STATUS_INIT = "0";
    public static String KEY_STATUS_SUCCESS = "1";
    @Autowired
    private MesRstResolutionDao mesRstResolutionDao;
    @Autowired
    private MesMeetingInfoManager mesMeetingInfoManager;
    @Autowired
    private AppAttachFileManager appAttachFileManager;


    public final static SimpleDateFormat rstResolutionDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
    public static String FILE_UPLOADER_SAVE_DIRECTORY = "mesmeeting";
    public static File savePathDirectory = new File(PowerUtils.getFilePath(FILE_UPLOADER_SAVE_DIRECTORY, true));
    public static HelperUtil mesHelper = new HelperUtil();

    public void saveMesRstResolution(Map input) throws Exception {
        if (!input.containsKey("mesRstResolution"))
            throw new RuntimeException("mesRstResolution 不能为空");

        //CurrentLoginUserUiid
        String uiid = SpringSecurityUtils.getCurrentUiid();
        //保存纪要信息
        MesRstResolution mesRstResolution = (MesRstResolution) input.get("mesRstResolution");
        PowerUtils.setEmptyStr2Null(mesRstResolution);


        if (StringUtils.isBlank(mesRstResolution.getMesRstResolutionId())||isExistsSerialNo(mesRstResolution.getRstYear(), mesRstResolution.getMesRstResolutionId(), mesRstResolution.getSerialNo())) {
            mesRstResolution.setSerialNo(generateSerialNo(String.valueOf(mesRstResolution.getRstYear()), String.valueOf(mesRstResolution.getSerialNo())));
        }

        mesRstResolutionDao.save(mesRstResolution);
        mesRstResolution.getMesMeetingInfo().setIsRst(true);
        mesMeetingInfoManager.saveMesMeetingInfo(mesRstResolution.getMesMeetingInfo());

        //附件保存
        if (input.containsKey("fileuploader") && null != input.get("fileuploader") && ((File[]) input.get("fileuploader")).length > 0) {
            if (!savePathDirectory.exists()) {
                savePathDirectory.mkdirs();
            }
            File[] fileuploader = ((File[]) input.get("fileuploader"));
            String[] fileuploaderContentType = (String[]) input.get("fileuploaderContentType");
            String[] fileuploaderFileName = (String[]) input.get("fileuploaderFileName");
            for (int i = 0; i < fileuploader.length; i++) {
                String newFileName = HelperUtil.getFileName(fileuploaderFileName[i]);
                File fout = new File(savePathDirectory, newFileName);
                FileOutputStream fos = new FileOutputStream(fout);
                FileInputStream fin = new FileInputStream(fileuploader[i]);
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = fin.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                AppAttachFile attachFile = new AppAttachFile();
                attachFile.setBizModuleCd(FILE_UPLOADER_SAVE_DIRECTORY);
                attachFile.setBizEntityId(mesRstResolution.getMesRstResolutionId());
                attachFile.setRealFileName(fileuploaderFileName[i]);
                attachFile.setFileName(newFileName);
                attachFile.setFilePath(savePathDirectory.getAbsolutePath());
                attachFile.setFileTypeName(fileuploaderContentType[i]);
                attachFile.setFileSize(new BigDecimal(fout.length()));
                attachFile.setStatusCd("1");

                Long mbLength = (fout.length() / (1024 * 1024));
                if (mbLength > 20)
                    throw new RuntimeException("第" + (i + 1) + "个文件：" + fileuploaderFileName[i] + "超过20MB 限制，服务器拒绝存储。");

                appAttachFileManager.saveAppAttachFile(attachFile);
                mesRstResolution.setAttachFlg("1");
            }
        }
        mesRstResolutionDao.save(mesRstResolution);
    }

    /**
     * 决策完成
     *
     * @param mesRstResolution
     * @throws Exception
     */
    public void completeMesRstResolution(MesRstResolution mesRstResolution) throws Exception {
        mesRstResolution.setStatusCd(MesRstResolutionManager.KEY_STATUS_SUCCESS);
        mesRstResolutionDao.save(mesRstResolution);
    }

    /**
     * 导出　Word
     *
     * @param mesRstResolutionId
     * @throws Exception
     */
    public void export(String mesRstResolutionId) throws Exception {

        MesRstResolution mesRstResolution = getEntity(mesRstResolutionId);
        if (null == mesRstResolution)
            throw new RuntimeException("决议不存在:" + mesRstResolutionId);

        InputStream docsTemplate = MesRstResolutionManager.class.getClassLoader().getResourceAsStream("docsTemplates/mesrstresolution.docx");
        XWPFDocument doc = new XWPFDocument(docsTemplate);

        //提取模板需要数据 定义 模板变量集合
        Map<String, String> params = new HashMap<String, String>();
        params.put("bianhao", "决策委【" + mesRstResolution.getRstYear() + "】" + mesRstResolution.getSerialNo() + "号");
        params.put("jueceriqi", rstResolutionDateFormat.format(mesRstResolution.getRstDate()));
        params.put("juecezhuti", mesRstResolution.getRstSubject());
        params.put("juecezuchengyuan", mesHelper.getDataDictionary().getUserNamesByUiids(mesRstResolution.getGroupUsers(), ";"));

        //决议内容 格式重新 整理
        String summary = Util.clob2String(mesRstResolution.getRstContet());
        summary = summary.replaceAll("<\\/p>|<br\\/>|<br[^<>]*>", "\n").replaceAll("</?[^<>]*/?>", "").replaceAll("\r", "").replaceAll(" ", "").replaceAll("&nbsp;", "").replaceAll("　", "");
        params.put("jueyineirong", summary);


        //模板中变量替换
        //获取 doc 中 全部段落 引用
        List<XWPFParagraph> xwpfParagraphs = new ArrayList<XWPFParagraph>(doc.getParagraphs());
        List<XWPFTableRow> xwpfTableRows = doc.getTables().get(0).getRows();
        for (XWPFTableRow xwpfTableRow : xwpfTableRows) {
            xwpfParagraphs.addAll(xwpfTableRow.getCell(1).getParagraphs());
        }
        for (XWPFParagraph xwpfParagraph : xwpfParagraphs) {
            //获取段落中 每一子段落 容器。
            List<XWPFRun> xwpfRuns = xwpfParagraph.getRuns();
            for (XWPFRun xwpfRun : xwpfRuns) {
                //获取容器内文本。
                String text = xwpfRun.getText(xwpfRun.getTextPosition());
                if (null == text) {//空内容舍弃
                    continue;
                }
                //检查容器内文本 在 变量定义集合中是否存在，进行 内容替换。
                if (params.containsKey(text)) {
                    //遇到纪要 内容 字段 做特殊处理
                    if (text.equalsIgnoreCase("jueyineirong")) {
                        //获取 重新整理后的 纪要内容 以 回车为 分割 返回 全部行。
                        String[] pline = summary.split("\n");
                        for (int i = 0; i < pline.length; i++) {
                            if (i == 0) {//第一行 指定 插入文本位置。
                                xwpfRun.setText(pline[i], 0);
                            } else {
                                //以后行 直接追加
                                xwpfRun.setText(pline[i]);
                            }
                            //每操作一行结束 追加 回车
                            xwpfRun.addBreak();
                        }
                    } else {
                        //普通字段 内容替换
                        xwpfRun.setText(params.get(text), 0);
                    }
                }
            }
        }


        String contextType = PowerUtils.getSamPlaceValue(CoreContants.FILE_TYPE, CoreContants.FILE_SUFFIX, "docx");
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        StringBuffer sbFileName = new StringBuffer();
        sbFileName.append("决策委【").append(mesRstResolution.getRstYear()).append("】");
        sbFileName.append(mesRstResolution.getSerialNo()).append("号");
        sbFileName.append("_").append(mesRstResolution.getRstSubject());
        String filename = sbFileName.toString();
        String userAgent = request.getHeader("User-Agent").toLowerCase();
        if (userAgent.indexOf("firefox") > 0 || userAgent.indexOf("safari") > 0) {
            filename = new String(filename.getBytes("UTF-8"), "ISO8859-1");
        } else {
            filename = URLEncoder.encode(filename, "UTF-8");
        }
        response.setContentType(contextType);
        response.setHeader("Content-Disposition", "attachment;filename=\"" + filename + ".docx\"");
        doc.write(response.getOutputStream());
    }

    /**
     * 发送邮件
     *
     * @param input
     * @throws Exception
     */
    public void sendMesRstResolutionEmail(Map input) throws Exception {

        String currentUiid = SpringSecurityUtils.getCurrentUiid();

        MesRstResolution mesRstResolution = (MesRstResolution) input.get("mesRstResolution");
        StringBuffer subject = new StringBuffer("【决议发布】").append(mesRstResolution.getRstSubject());
        String content = Util.clob2String(mesRstResolution.getRstContet());
        String[] toUsers = StringUtils.isBlank(mesRstResolution.getGroupUsers()) ? new String[]{} : mesRstResolution.getGroupUsers().split(";");

        Map<String, String> params = new HashMap();
        params.put("bianhao", "决策委【" + mesRstResolution.getRstYear() + "】" + mesRstResolution.getSerialNo() + "号");
        params.put("jueyiriqi", rstResolutionDateFormat.format(mesRstResolution.getRstDate()));
        params.put("jueyizhuti", mesRstResolution.getRstSubject());
        params.put("jueyizuchengyuan", mesHelper.getDataDictionary().getUserNamesByUiids(mesRstResolution.getGroupUsers(), ";"));
        params.put("jueyineirong", content);
        //params.put("href", "http://" + ServletActionContext.getRequest().getLocalAddr() + Struts2Utils.getRequest().getContextPath() + "/mes/mes-summary!readonly.action?mesMeetingInfoId=" + mesMeetingInfo.getMesMeetingInfoId());
        String template = HelperUtil.getHtmlTemplate("mes/mesrstresolution.html");

        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = "${" + entry.getKey() + "}";
            String value = null == entry.getValue() ? "" : entry.getValue();
            template = template.replace(key, value);
        }
        /**
         * 邮件发送
         */
        String[] toUserEmails = new String[toUsers.length];
        if (!input.containsKey("sendme")) {
            for (int i = 0; i < toUsers.length; i++) {
                toUserEmails[i] = toUsers[i] + "@powerlong.com";
            }
        } else {
            toUserEmails = new String[]{currentUiid + "@powerlong.com"};
        }
        String rootPath = ServletActionContext.getServletContext().getRealPath("");
        String realPath = Struts2Utils.getRequest().getContextPath();
        //System.out.println(template);
        EmailUtil.send("决议发布", SpringSecurityUtils.getCurrentUiid(), LoginUtil.getPwd(null), toUserEmails, null, null, subject.toString(), template, null, null, rootPath, realPath);
        if (!input.containsKey("sendme")) {
            mesRstResolution.setEmailSendCnt(mesRstResolution.getEmailSendCnt() == null ? 1 : mesRstResolution.getEmailSendCnt() + 1);
            mesRstResolution.setLastSendDate(new Date());
            mesRstResolutionDao.save(mesRstResolution);
        }
    }

    public void deleteMesRstResolution(String id) {
        mesRstResolutionDao.delete(id);
    }

    @Override
    public HibernateDao<MesRstResolution, String> getDao() {
        return mesRstResolutionDao;
    }

    public long generateSerialNo(String year, String MES_RST_RESOLUTION_ID) {
        Map params = new HashMap();
        params.put("year", year);
        params.put("MES_RST_RESOLUTION_ID", MES_RST_RESOLUTION_ID);
        StringBuffer queryString = new StringBuffer("select max(t1.serial_no)+1 from MES_RST_RESOLUTION t1 where 1=1");
        if (StringUtils.isNotBlank(year)) {
            queryString.append(" and to_char(t1.RST_DATE,'yyyy') =:year");
        }
        if (StringUtils.isNotBlank(MES_RST_RESOLUTION_ID)) {
            queryString.append(" and t1.MES_RST_RESOLUTION_ID <> :MES_RST_RESOLUTION_ID");
        }
        List values = mesRstResolutionDao.findBySql(queryString.toString(), params);
        return null == values.get(0) ? 1 : ((BigDecimal) values.get(0)).longValue();
    }

    public boolean isExistsSerialNo(long year, String MES_RST_RESOLUTION_ID, long serialNo) {
        Map params = new HashMap();
        params.put("year", year);
        params.put("MES_RST_RESOLUTION_ID", MES_RST_RESOLUTION_ID);
        params.put("SERIAL_NO", serialNo);

        StringBuffer queryString = new StringBuffer("select count(*) from MES_RST_RESOLUTION t1 where 1=1");
        queryString.append(" and to_char(t1.RST_DATE,'yyyy') =:year");
        queryString.append(" and t1.MES_RST_RESOLUTION_ID <>:MES_RST_RESOLUTION_ID ");
        queryString.append(" and t1.SERIAL_NO= :SERIAL_NO");
        List values = null;
        try {
            values = mesRstResolutionDao.findBySql(queryString.toString(), params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null == values.get(0) ? false : ((BigDecimal) values.get(0)).longValue() != 0;
    }

}

