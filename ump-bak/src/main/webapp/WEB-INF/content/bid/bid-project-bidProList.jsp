<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>

<table class="content_table"  style="width: expression(this.parentNode.offsetHeight >this.parentNode.scrollHeight ? '100%' :parseInt(this.parentNode.offsetWidth - 18) + 'px');">					                 	      
        <col width="20"/>
		<col width="50"/>
		<col width="120"/>
		<col width="20"/>
		<col width="50"/>
         <tr  class="titlePro">
            <th nowrap="nowrap" style="background: none;cursor: pointer;" >
            	<div style="float:left;"><h4>编号</h4></div>
            </th>
            <th><div style="float:left;"><h4>工程项目</h4></div></th>
            <th><div style="float:left;"><h4>工程介绍</h4></div></th>
            <th><div style="float:left;"><h4>附件</h4></div></th>
            <th><div style="float:left;"><h4>操作</h4></div></th>
         </tr>
        <s:iterator value="bidPros" var="bidPro"  status="index">
           <tr class="mainTr"  style="background-color:#FFFFFF;" id="bidProjectListTr" bidProjectId="${bidPro.bidProjectId}">
              <td><s:property value="#index.index+1"/></td>
              <td>
	               <div ptd="ptd" class="ellipsisDiv_full " id="projectNameTd_${bidPro.bidProjectId}">${bidPro.projectName}</div>
	               <div pInput="pInput" class="unshow" id="projectNameInput_${bidPro.bidProjectId}" bidProjectId="${bidPro.bidProjectId}">
	               		<textarea name="projectName"  style="height:100px;width:100%" onchange="updateBidProject('${bidPro.bidProjectId}','projectName',this);">${bidPro.projectName}</textarea>
	               </div>
              </td>
              <td >
              	   <div  ptd="ptd" class="ellipsisDiv_full" id="projectDescTd_${bidPro.bidProjectId}">${bidPro.projectDesc}</div>
              	   <div  pInput="pInput" class="unshow" id="projectDescInput_${bidPro.bidProjectId}"  >
              	   		<textarea cols="40" style="height:100px;width:100%" name="projectDesc"  onchange="updateBidProject('${bidPro.bidProjectId}','projectDesc',this);">${bidPro.projectDesc}</textarea>
              	   </div>
              </td>
              <td>
               	 <a href="javascript: attachManage('', event);" onclick="openAttachment('附件管理','${bidPro.bidProjectId}','bidProject'); return false;" >
					<s:if test="attachFlg==1"><img src="${ctx}/resources/images/common/atta_y.gif" /></s:if>
					<s:else><img src="${ctx}/resources/images/common/atta.gif" /></s:else>
				 </a>
       	       </td>
       	       <td>
       	          <div>
        	        <input type="button" value="编辑" onclick="doEdit('${bidPro.bidProjectId}');" class="btn_green"/>
               		<input type="button" value="删除" onclick="doDeleteProject('${bidPro.bidProjectId}');" style="width:35px;" class="btn_red"/>
              	  </div>
              </td>
           </tr>
        </s:iterator>
</table>
					                 	   
