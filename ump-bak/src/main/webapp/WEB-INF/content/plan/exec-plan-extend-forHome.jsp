<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.springside.modules.utils.EncodeUtils"%>
<%@page import="com.hhz.ump.entity.oa.OaMeetingRoomRes"%>

<script language="javascript">
attention_sort_num=0;
$("#attention_table").html('<tr style="height:0px; display:none;" id="attention_start_tr"><td id="attention_start_td" colspan="2"></td></tr>');

try{
	if(Number("${meetingDay}")>0){
								   <%
								    List<OaMeetingRoomRes> meetingRoomList = (List)request.getAttribute("meetingRoomList");
							   		SimpleDateFormat  sdf = new SimpleDateFormat("HH:mm");
							   		String temp = "";
							   		String newStr = "";
								   	if (meetingRoomList!=null){
								   		if(meetingRoomList.size()>6){
								   			for(int i=0;i<6;i++){//OaMeetingRoomRes orm :meetingRoomList
									   			OaMeetingRoomRes orm = meetingRoomList.get(i);
									   			String subStr = "";
									   			String depStr = "";
									   			if(orm.getSubject()!=null){
									   				if(orm.getSubject().length()>8){
									   					subStr=orm.getSubject().substring(0,8)+"...";
									   				}else{
									   					subStr=orm.getSubject();
									   				}
									   			}
									   			if (orm.getRelatedDept()==null || orm.getRelatedDept().equals("")){
									   				depStr = "";
									   			}else{
									   				depStr = "("+orm.getRelatedDept()+")";
									   			}
									   			String dd = orm.getSubject()+"\\r会议室:"+orm.getRemark();
									  			if(meetingRoomList.size()>1){
									  				temp = temp+"<tr><td style=\\'font-size: 12px;\\' title=\""+dd+"\" onClick=\"openHomeAttentionSp(\\'"+orm.getOaMeetingRoomResId()+"\\',\\'"+orm.getAddrType()+"\\')\"><div>"+subStr+"</div><div>"+depStr+"</div></td></tr>";
									  			}else{
									  				temp = "<tr><td style=\\'font-size: 12px;\\' title=\""+dd+"\"  onClick=\"openHomeAttentionSp(\\'"+orm.getOaMeetingRoomResId()+"\\',\\'"+orm.getAddrType()+"\\')\"><div>"+subStr+"</div><div>"+depStr+"</div></td></tr>";
									  			}
									   		}
								   		}else{
								   			for(OaMeetingRoomRes orm :meetingRoomList){//
									   			String subStr = "";
									   			String depStr = "";
									   			if(orm.getSubject()!=null){
									   				if(orm.getSubject().length()>8){
									   					subStr=orm.getSubject().substring(0,8)+"...";
									   				}else{
									   					subStr=orm.getSubject();
									   				}
									   			}
									   			if (orm.getRelatedDept()==null || orm.getRelatedDept().equals("")){
									   				depStr = "";
									   			}else{
									   				depStr = "("+orm.getRelatedDept()+")";
									   			}
									   			String dd = orm.getSubject()+"\\r会议室:"+orm.getRemark();
									  			if(meetingRoomList.size()>1){
									  				temp = temp+"<tr><td style=\\'font-size: 12px;\\' title=\""+dd+"\" onClick=\"openHomeAttentionSp(\\'"+orm.getOaMeetingRoomResId()+"\\',\\'"+orm.getAddrType()+"\\')\"><div>"+subStr+"</div><div>"+depStr+"</div></td></tr>";
									  			}else{
									  				temp = "<tr><td style=\\'font-size: 12px;\\' title=\""+dd+"\"  onClick=\"openHomeAttentionSp(\\'"+orm.getOaMeetingRoomResId()+"\\',\\'"+orm.getAddrType()+"\\')\"><div>"+subStr+"</div><div>"+depStr+"</div></td></tr>";
									  			}
									   		}
								   		}
								   		
								   		newStr ="\'"+ temp+"\'";
								   		}
								   %>
								  
								   var new_record_html =<%=newStr%> ;
		$("#attention_start_tr").after(new_record_html);
		attention_sort_num++;
	}
}catch(e){}


</script>

<script language="javascript">
var all_daiban_attention_sort_num = Number(daiban_sort_num)+Number(attention_sort_num);	//待办和关注事项的类别数的和
var daiban_attention_height_num = Number(daiban_attention_height-50/24);	//剩余空间最多能放的条数，要在待办和关注都有显示的情况下
if(0==all_daiban_attention_sort_num){
	//如果什么提示都没有，就显示关注事项的空提示
	$("#attention_display_div").show();
	$("#attention_start_tr").css("height","22px");
	$("#attention_start_tr").show();
	$("#attention_start_td").html("没有更新的内容");
}else if(all_daiban_attention_sort_num<daiban_attention_height_num && daiban_sort_num>0 && attention_sort_num>0){
	//不足的情况，并且待办和关注都有显示，剩余空间平均分配给两块
	var rest_height = (daiban_attention_height-50-all_daiban_attention_sort_num*24)/3;
	$("#daiban_display_div").show();
	$("#attention_display_div").show();
	var daiban_div_height = daiban_sort_num*24+2+rest_height;
	var attention_div_height = attention_sort_num*24+2+2*rest_height;
	$("#daiban_height_div").css("height",daiban_div_height+"px");
	$("#daiban_height_div").css("height",daiban_div_height+"px");
}else{
	//超过的情况，填不满了，不用计算空间高度
	if(daiban_sort_num>0){
		$("#daiban_display_div").show();
		var daiban_div_height = daiban_sort_num*24+2;
		$("#daiban_height_div").css("height",daiban_div_height+"px");
	}
	if(attention_sort_num>0){
		$("#attention_display_div").show();
		var attention_div_height = attention_sort_num*24+2;
		$("#attention_height_div").css("height",attention_div_height+"px");
	}
}
</script>