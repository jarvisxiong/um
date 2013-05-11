package com.hhz.ump.dao.eas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springside.modules.spring.SpringContextHolder;

import com.hhz.core.utils.DateParser;
import com.hhz.ump.dao.hr.HrTipEasManager;
import com.hhz.ump.dao.oa.OaEmailBodyManager;
import com.hhz.ump.entity.hr.HrTipEas;
import com.hhz.ump.util.EmailUtil;

@Service
public class EasService {
	
	private static Log log = LogFactory.getLog(EasService.class);
	
	public void tipEas(){
		birthDay();
		positiveView1();
		positiveView2();
		contEndView1();
		contEndView2();
	}
	
	/**
	 * 生日提醒: 姓名	中心	部门	职位	出生日期
	 */
	public void birthDay(){

		try{
			String title = "生日提醒"; 
			String sqlToUser = new StringBuffer()
				.append("select distinct(recpdnumber) as recpdnumber ")
				.append("  from vw_eas_birthview ")
				.append(" where recpdnumber is not null ")
				.toString();
	
			String[] headNames = new String[]{"姓名","中心","部门","职位","出生日期"};
			String sqlUserList = new StringBuffer()
				.append("select username, centername, deptname, positionname, birthday,")
				.append("       tiptypecd, reldate, pduiid, pdusername, hruiid, hrname ")
				.append("  from vw_eas_birthview ")
				.append(" where recpdnumber = :recpdnumber ")
				.append(" order by sequence_no asc,centername asc, deptname asc, levelnumber ")
				.toString();
	
			commonProcess(title, headNames, sqlToUser, sqlUserList, "recpdnumber");
		}catch(Exception e){
			log.error(" 员工生日提醒出现异常! ", e);
		}
	}
	
	/**
	 * 月度考核: 姓名	中心	部门	职位	入职日期
	 */
	public void positiveView1(){

		try{
			String title = "月度考核提醒";
			String[] headNames = new String[]{"姓名","中心","部门","职位","入职日期"};
	
			String sqlToUser = new StringBuffer()
				.append("select distinct(recpdnumber) as recpdnumber ")
				.append("  from vw_eas_positiveview1 ")
				.append(" where recpdnumber is not null ")
				.toString();
			
			String sqlUserList = new StringBuffer()
				.append("select username, centername, deptname, positionname, enterdate,")
				.append("       tiptypecd, reldate, pduiid, pdusername, hruiid, hrname ")
				.append("  from vw_eas_positiveview1 ")
				.append(" where recpdnumber = :recpdnumber ")
				.append(" order by sequence_no asc,centername asc, deptname asc, levelnumber ")
				.toString();
	
			commonProcess(title, headNames, sqlToUser, sqlUserList, "recpdnumber");
		}catch(Exception e){
			log.error(" 月度考核提醒出现异常! ", e);
		}
	}
	/**
	 * 转正考核: 姓名	中心	部门	职位	入职日期
	 */
	public void positiveView2(){

		try{
			String title = "转正考核提醒";
			String[] headNames = new String[]{"姓名","中心","部门","职位","入职日期"};
	
			String sqlToUser = new StringBuffer()
				.append("select distinct(recpdnumber) as recpdnumber ")
				.append("  from vw_eas_positiveview2 ")
				.append(" where recpdnumber is not null ")
				.toString();
			
			String sqlUserList = new StringBuffer()
				.append("select username, centername, deptname, positionname, enterdate,")
				.append("       tiptypecd, reldate, pduiid, pdusername, hruiid, hrname ")
				.append("  from vw_eas_positiveview2 ")
				.append(" where recpdnumber = :recpdnumber ")
				.append(" order by sequence_no asc,centername asc, deptname asc, levelnumber ")
				.toString();
			
			commonProcess(title, headNames, sqlToUser, sqlUserList, "recpdnumber");
		}catch(Exception e){
			log.error(" 月度考核提醒出现异常! ", e);
		}
	}
	
	/**
	 * 合同到期.一般员工.预警: 姓名	中心	部门	职位	入职日期	劳动合同到期日
	 */
	public void contEndView1(){

		try{
			String title = "合同到期(一般员工)提醒";
			String[] headNames = new String[]{"姓名","中心","部门","职位","入职日期","劳动合同到期日"};
	
			String sqlToUser = new StringBuffer()
				.append("select distinct(recpdnumber) as recpdnumber ")
				.append("  from vw_eas_contendview1 ")
				.append(" where recpdnumber is not null ")
				.toString();
			
			String sqlUserList = new StringBuffer()
				.append("select username, centername, deptname, positionname, enterdate, to_char(contenddate,'yyyy-MM-dd'),")
				.append("       tiptypecd, reldate, pduiid, pdusername, hruiid, hrname ")
				.append("  from vw_eas_contendview1 ")
				.append(" where recpdnumber = :recpdnumber ")
				.append(" order by sequence_no asc,centername asc, deptname asc, levelnumber ")
				.toString();
	
			commonProcess(title, headNames, sqlToUser, sqlUserList, "recpdnumber");
		}catch(Exception e){
			log.error(" 合同到期(一般员工)提醒出现异常! ", e);
		}
	}
	
	/**
	 * 合同到期预.总经理级以上员工.预警 : 姓名	中心	部门	职位	入职日期	劳动合同到期日
	 */
	public void contEndView2(){

		try{
			String title = "合同到期(总经理级以上)提醒"; 
			String[] headNames = new String[]{"姓名","中心","部门","职位","入职日期","劳动合同到期日"};
	
			//搜索需要提醒的用户列表
			String sqlToUser = new StringBuffer()
				.append("select distinct(recpdnumber) as recpdnumber ")
				.append("  from vw_eas_contendview2 ")
				.append(" where recpdnumber is not null ")
				.toString();
			
			String sqlUserList = new StringBuffer()
				.append("select username, centername, deptname, positionname, enterdate, to_char(contenddate,'yyyy-MM-dd'),")
				.append("       tiptypecd, reldate, pduiid, pdusername, hruiid, hrname ")
				.append("  from vw_eas_contendview2 ")
				.append(" where recpdnumber = :recpdnumber ")
				.append(" order by sequence_no asc,centername asc, deptname asc, levelnumber ")
				.toString();
			
			commonProcess(title, headNames, sqlToUser, sqlUserList, "recpdnumber");
		}catch(Exception e){
			log.error(" 合同到期(总经理级以上)提醒出现异常! ", e);
		}
	}
	
	/**
	 * @param title  标题 
	 * @param headNames  表格列头 
	 * @param sqlToUser   接收人力资源同仁
	 * @param sqlUserList  被提醒员工
	 * @param userFieldName 接收人力资源同仁uiid对应的字段
	 */
	private void commonProcess(String title, String[] headNames, String sqlToUser, String sqlUserList, String userFieldName){

		log.info("["+title+"] 开始执行... ");

		OaEmailBodyManager oaEmailBodyManager = SpringContextHolder.getBean("oaEmailBodyManager");
		HrTipEasManager hrTipEasManager = SpringContextHolder.getBean("hrTipEasManager");

		StringBuffer preBuf = new StringBuffer()
				.append(" <table class=\"tip_email\">")
				.append(" <thead>");
		
		//动态表头
		for(int i=0; i<headNames.length; i++){
			preBuf.append("<th>").append(headNames[i]).append("</th>");
		}
		
		preBuf.append("</thead>")
			  .append("<tbody>");
		
		StringBuffer bodyBuf = null;
		
		StringBuffer endBuf = new StringBuffer()
			.append(" </tbody>")
			.append(" </table>");
		 

		Map<String,Object> values = new HashMap<String,Object>();
		
		List<String> toUserUiidList = oaEmailBodyManager.findBySql(sqlToUser, values);
		log.info(">>>>>>>>>>>>>>["+ title +"] 接收邮件的用户列表：" + toUserUiidList.size());
		
		String content = null;
		
		List<Object[]> tmpList = null;
		HrTipEas tmpTipEas = null;

		for (String tmpToUiid : toUserUiidList) {
			values.put(userFieldName, tmpToUiid);
			//搜索清单信息列表
			tmpList = oaEmailBodyManager.findBySql(sqlUserList, values);
			log.info(">>>>>>>>>>>>>>>> ["+ title +"] 发送给: "+ tmpToUiid +", 员工列表:" +tmpList.size());
			if(tmpList != null && tmpList.size() > 0){
				bodyBuf = new StringBuffer();
				for (Object[] tmpObj : tmpList) {
					bodyBuf.append("<tr>");
					
					for(int i=0; i<headNames.length; i++){
						bodyBuf .append("<td>").append(getVal((String)tmpObj[i])).append("</td>");
					}
					
					//"提醒类型", "到期日期", "员工uiid", "员工姓名", "人力资源部-员工uiid", "人力资源部-员工姓名"
					tmpTipEas = new HrTipEas();
					tmpTipEas.setTipTypeCd(String.valueOf((tmpObj[headNames.length])));
					tmpTipEas.setRelDate(DateParser.parse(((String)tmpObj[headNames.length + 1]), "yyyy-MM-dd"));
					tmpTipEas.setRelUserUiid(((String)tmpObj[headNames.length + 2]));
					tmpTipEas.setRelUserName(((String)tmpObj[headNames.length + 3]));
					tmpTipEas.setRelHrUiid(((String)tmpObj[headNames.length + 4]));
					tmpTipEas.setRelHrName(((String)tmpObj[headNames.length + 5]));
					hrTipEasManager.saveHrTipEas(tmpTipEas);
					
					bodyBuf.append("</tr>");
				}
				content = new StringBuffer().append(preBuf).append(bodyBuf).append(endBuf).toString();
				log.info("--send to ["+tmpToUiid+"] 内容如下： \n" + content);
				
				//发送邮件
				try{
					oaEmailBodyManager.sendData2Email(title, content, "email_admin", "1", tmpToUiid);
				}catch(Exception e){
					log.error("定时任务,发送HR内部邮件异常!" + content, e);
				}
				try{
					EmailUtil.sendAsyncSys(title, content, new String[]{tmpToUiid});
				}catch(Exception e){
					log.error("定时任务,发送HR外部邮件异常!" + content, e);
				}
			}
		}
		log.info("["+title+"] 执行完成!  ");
	}
	
	private String getVal(String val){
		return StringUtils.isBlank(val)?"":val;
	}

	public boolean execute(String type) {

		if ("all".equals(type)) {

			tipEas();

		} else if ("birthDay".equals(type)) {

			birthDay();

		} else if ("positiveView1".equals(type)) {

			positiveView1();

		} else if ("positiveView2".equals(type)) {
	
			positiveView2();
	
		} else if ("contEndView1".equals(type)) {

			contEndView1();

		} else if ("contEndView2".equals(type)) {

			contEndView2();

		} else
			return false;
		return true;
	}

}