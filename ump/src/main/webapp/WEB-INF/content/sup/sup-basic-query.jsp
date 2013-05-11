<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%request.setAttribute("vEnter", "\r\n");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/meta.jsp" %>
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/desk/fin.css" />
<link href="dtree.css" rel="stylesheet" type="text/css"></link>
<link rel="stylesheet" type="text/css" href="${ctx}/css/desk/sup.css" />
<link href="${ctx}/resources/css/common/TreePanel.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" />
<script language="javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common.js"></script>
<script type="text/javascript" src="js/suppliers.js"></script>
<script type="text/javascript" src="../chengben/js/dialog.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
<script type="text/javascript" src="${ctx}/js/jqueryplugin/chilltip.js"></script>

<script type="text/javascript">
function createFile(sn){
	$("#addAttaFile"+sn).append("<input onchange=fileChange(this,'"+sn+"') name='upload"+sn+"' hidefocus=true type=file size=1 class=mailFile>");
}
function removeFile(dom){
	$(dom).parent().remove();
}
function fileChange(dom,sn){
	var sName,o;
    sName=dom.value.replace(/\\/g,"/").replace(/(.*\/)(.*)/,"$2");
   	if(!checkUploadFile(sName)){
   	   	return;
   	}
    o=document.createElement("nobr");
    o.style.cssText="float:left;margin-right:6;padding-top:3;color:darkgreen";
    o.innerHTML="|"+sName+"<font onclick=removeFile(this) class=hand style=color:red;font-weight:bold>"+unescape("×")+"</font>"; 
    dom.style.display="none";
    o.appendChild(dom);
    $("#attaContainer"+sn).append(o);
    createFile(sn);
}
var UPLOAD_FILE_NAMES = new Array();
function checkUploadFile(fileName){
	var uploadFileSuffix = ["gif", "png","jpg", "bmp","jpg","wma","avi","xlsx", "docx", "doc", "xls", "pdf", "txt", "tif","zip","rar"];
	if(UPLOAD_FILE_NAMES.contains(fileName)){
	return false;
	}
	return true;
} 
/**
 * 保存附件
 */
function doSupAttach(i){
	var haveAtt =0;
	var name=["_bas","_rang","_zx","_zz"];
	alert(i);
	if(i<name.length){
		if($("#attaContainer"+name[i]+" input").length>0){
			var bizId=$("#fileSn"+name[i]).val();
			//upName =upName+name[i]+",";
			$("#bizEntId").val(bizId);
			$("#upload"+name[i]).attr("name","upload");
			$("#entityId").val("");
			$("#supForm").attr("action","${ctx}/app/app-attachment!supUpload.action");
			$("#supForm").ajaxSubmit(function(result){
				alert('0');
				if(result=="1"){
					alert("new supAttach");
					doSupAttach(i++);
				}
			});
		}else{
			alert("new");
			doSupAttach(i++);
		}
	}
	
}
function doAddSaveSup(){
	if($("#supName").val()==""){
		alert("请输入供方名称");
	}
	if($("#supTypeSn").val()==""){
		alert("请输入供应类别");
	}
	$("#supForm").submit(); 
}
function doSupType(modId){
	var selectId ="";
	var selectName ="";
	var finType ="";
	ymPrompt.confirmInfo({
		icoCls:"",
		title:"现金流量项目",
		message:"<div id='parentDiv'></div>",
		useSlide:true,
		winPos:"c",
		width:300,
		height:400,
		allowRightMenu:true,
		afterShow:function(){
			$.post("${ctx}/sup/sup-basic!selectSupTree.action", function(result){
				var tree = new TreePanel({
					renderTo:"parentDiv",
					'root' : eval('('+result+')'),
					'ctx':'${ctx}'
				});
				tree.on(function(node){
					if(node.attributes.children==null||node.attributes.children==""){
					  selectId =node.attributes.finItemCd;
					  selectName =node.attributes.text;
					  finType =node.attributes.finType;
					}
				});
				tree.render();
			});
	},
	handler:function(e){
		if("ok"==e){
			$("#supTypeSn").val(selectId);
			if(modId!=""){
			    $("#supTypeName"+modId).val(selectName);
			}else{
				$("#supTypeName").val(selectName);
			}
		   $("#tableType").val(finType);
		}
	}
	});
}
function modSup(){
	var modId =$("#id").val();
	if(modId){
		var url ="${ctx}/sup/sup-basic!input.action?id="+modId;
		if(parent.TabUtils==null)
			window.open(url);
		else{
			parent.TabUtils.newTab("supModify","修改",url,true);
			var cfg = {};
			cfg.data = {tabId:'supQuery'};
			parent.TabUtils.closeTab(cfg);
		}
		   
	}
}
</script>
</head>
<body>
<div class="title_bar_sup">
  <div class="title_bar_h">
               供应商信息
  </div>
  <security:authorize ifAnyGranted="A_SUP_ADMIN">
   <div class="title_bar_ops">
    <div class="btn_cutline_3_26"></div>
	<div class="btn_title_bar_4" style="float: left;" onclick="modSup();">
	<div style="float: left; margin-top: 5px;margin-left:5px;">
		<img src="${ctx}/resources/images/plan/headline_pic_management.gif">
	</div>
	编辑
	</div>
   </div>
  </security:authorize>
 </div>
<form id="supForm" action="${ctx}/sup/sup-basic!save.action" method="post"  enctype="multipart/form-data">
<input type="hidden" name="bizModuleCd" value="model.suppliers" />
<input type="hidden" name="uiid" value="<%=SpringSecurityUtils.getCurrentUiid() %>" />
<input type="hidden" id="bizEntId" name="bizEntityId"/>
<input type="hidden" id="tableType" name="tableType"/>
<div style="height:40px;padding-left:5px;padding-right:5px;">
 <table style="width:100%;">
  <tr>
   <td width="30%" class="sup_title pd-chill-tip" title="${model.supName}">${model.supName}
   &nbsp;
   </td>
   <td class="sup_title_1 pd-chill-tip" title="<p:code2name mapCodeName="mapToItemNames" value="model.supTypeSn" />" style="width:40%;overflow: hidden; text-align: left;"><span class="td_bold">供应类别: </span><p:code2name mapCodeName="mapToItemNames" value="model.supTypeSn" />
   &nbsp;
   </td>
   <td width="15%;" class="sup_title_1 pd-chill-tip" title="${model.supDetails[0].supComeFrom}"><span class="td_bold">信息来源: </span>${model.supDetails[0].supComeFrom}
   &nbsp;
   </td>
   <td width="15%;" class="sup_title_1"><span class="td_bold" style="color:#0167a2;">更新时间：</span>
     <span style="color:#0167a2;"><s:date name ="model.updatedDate" format="yyyy-MM-dd"/></span>
   </td>
  </tr>
 </table> 
</div>
<div></div>
<div  style="height:90%; top:20px;overflow-y: auto;padding-left:5px;padding-right:5px;">
<input type="hidden" id="id" value="${model.supBasicId}" />
  <table id="supInfo" class="sup_table" cellspacing="0" cellpadding="0">
    <tr >
     <th  align="left" nowrap="nowrap" style="padding-left:5px;">序号</th>
     <th  align="left" nowrap="nowrap">|评审项目</th>
     <th  align="left" nowrap="nowrap" colspan="2" style="padding-left:0px;">|评审情况</th>
     <th  align="left" nowrap="nowrap">|附件</th>
    </tr>
    <tr style="height:8px;">
    <td colspan="5" style="border:0px;" ></td>
    </tr>
    <tr>
     <td rowspan="5" width="20px;" class="td_bold"  style="border-bottom:0px;border-right:0px;">&nbsp;1</td>
     <td rowspan="5" width="150px;"class="td_bold"  style="border-bottom:0px;">企业基本信息</td>
     <td >
       <span  class="td_bold"> 注册资金(万)：</span>
       <span>${model.comRegMoney}</span>
     </td>
     <td>
      <span class="td_bold">注册年份：</span>
      <span>${model.supDetails[0].comRegTime}</span>
      </td>
     <td rowspan="5" width="150px;"  style="border-bottom:0px;border-right: 1px solid #8c8f94;">
      <div id="uploadAttaContainer" class="sup_attach">
			<s:iterator value="appAttachFiles_bas">
				<s:url id="down" action="supDownload" namespace="/sup"  includeParams="none"  >
						  	  <s:param name="fileName">${fileName}</s:param>
						  	  <s:param name="realFileName">${realFileName}</s:param>
						  	  <s:param name="bizModuleCd">${bizModuleCd}</s:param>
						  	   <s:param name="uiid">${creator}</s:param>
						  	   <s:param name="id">${appAttachFileId}</s:param>
				</s:url>
				<a href="${down}" title="${realFileName}">${realFileName}</a>&nbsp;
				<input type="hidden" name="attaFileIds" value="${appAttachFileId}"/>
			</s:iterator>
		</div>
     </td>
    </tr>
    <tr>
     <td>
       <span class="td_bold">企业性质：</span>
       <span>${model.supDetails[0].comProperties}</span>
     </td>
     <td>
           <span class="td_bold">在职员工（人）：</span>
           <span>${model.supDetails[0].comPersonNo}</span>
     </td>
    </tr>
    <tr>
     <td>
      <span class="td_bold">总经理姓名：</span>
      <span>${model.supDetails[0].supManager}</span>
      </td>
     <td>
                 <span class="td_bold">联系方式：</span>
                 <span>${model.supDetails[0].supManagerTel}</span>
     </td>
    </tr>
    <tr>
    <td colspan="2">
    <table style="width:100%;">
    <tbody>
    <s:iterator value="model.supContactors" status="st">
     <tr class="pd-chill-tip" title="<p:code2name mapCodeName="mapToContract" value="supContactorId" />">
      <td colspan="4" style="border:0px;" class="td_bold">
      <img src="${ctx}/resources/images/common/point_6_6.png"/>&nbsp;联系人<s:property value="#st.index+1"/></td>
     </tr>
     <tr class="pd-chill-tip" title="<p:code2name mapCodeName="mapToContract" value="supContactorId" />">
       <td width="50%" style="border:0px;">
         <span class="td_bold">姓名：</span>
         <span>${name}</span>
       </td>
       <td width="50%" style="border:0px;">
         <span class="td_bold">区域：</span>
         <span>${area}</span>
       </td>
      </tr>
      <tr class="pd-chill-tip" title="<p:code2name mapCodeName="mapToContract" value="supContactorId" />">
        <td style="border:0px;">
         <span class="td_bold">电话：</span>
         <span>${telephone}</span>
        </td>
        <td style="border:0px;">
        <span class="td_bold">e-mail：</span>
        <span>${EMail}</span>
        </td>
       </tr>
    </s:iterator>
    </tbody>
    </table>
    </td>
    </tr>
    <tr style="height:25px;">
     <td style="border-bottom:0px;" colspan="2">
      <span class="td_bold">地址：</span>
      <span>${model.supDetails[0].address}</span>
      </td>
    </tr>
    <tr style="height:8px;">
    <td colspan="5" style="border-bottom:0px;border-right: 1px solid #8c8f94;" ></td>
    </tr>
    <tr style="height:3px;;">
    <td colspan="5" style="border:1px dashed #aaabb0;" ></td>
    </tr>
    <tr style="height:8px;">
    <td colspan="5" style="border:0px;" ></td>
    </tr>
    <tr>
    <td rowspan="2" class="td_bold"  style="border-bottom:0px;border-right:0px;">&nbsp;2</td>
    <td rowspan="2" class="td_bold" style="border-bottom:0px;">企业经营管理范围</td>
    <td colspan="2">
      <span class="td_bold">主营：</span>
      <span>${model.supDetails[0].comMainPro}</span>
    </td>
    <td rowspan="2" style="border-bottom:0px;border-right: 1px solid #8c8f94;">
      <div id="uploadAttaContainer_rang" class="sup_attach">
		<s:iterator value="appAttachFiles_rang">
				<s:url id="down" action="supDownload" namespace="/sup"  includeParams="none"  >
					<s:param name="fileName">${fileName}</s:param>
					<s:param name="realFileName">${realFileName}</s:param>
					<s:param name="bizModuleCd">${bizModuleCd}</s:param>
					<s:param name="uiid">${creator}</s:param>
					<s:param name="id">${appAttachFileId}</s:param>
				</s:url>
				<a href="${down}" title="${realFileName}">${realFileName}</a>&nbsp;
				<input type="hidden" name="attaFileIds" value="${appAttachFileId}"/>
			
		</s:iterator>
	 </div>
    </td>
    </tr>
    <tr>
    <td colspan="2" style="border-bottom:0px;">
    <span class="td_bold">兼营：</span>
    <span>${model.supDetails[0].comSecoPro}</span>
    </td>
    </tr>
    <tr style="height:8px;">
    <td colspan="5" style="border:0px;" ></td>
    </tr>
    <tr style="height:3px;">
    <td colspan="5" style="border:1px dashed #aaabb0;" ></td>
    </tr>
    <tr style="height:8px;">
    <td colspan="5" style="border:0px;" ></td>
    </tr>
    <tr>
      <td class="td_bold" style="border-bottom:0px;border-right:0px;">&nbsp;3</td>
      <td class="td_bold" style="border-bottom:0px;">企业资信等级</td>
      <td colspan="2" style="border-bottom:0px;">${model.supDetails[0].comZxGrade}</td>
      <td style="border-bottom:0px;border-right: 1px solid #8c8f94;">
       <div id="uploadAttaContainer_zx" class="sup_attach">
		<s:iterator value="appAttachFiles_zx">
				<s:url id="down" action="supDownload" namespace="/sup"  includeParams="none"  >
					<s:param name="fileName">${fileName}</s:param>
					<s:param name="realFileName">${realFileName}</s:param>
					<s:param name="bizModuleCd">${bizModuleCd}</s:param>
					<s:param name="uiid">${creator}</s:param>
					<s:param name="id">${appAttachFileId}</s:param>
				</s:url>
				<a href="${down}" title="${realFileName}">${realFileName}</a>&nbsp;
				<input type="hidden" name="attaFileIds" value="${appAttachFileId}"/>
		</s:iterator>
	   </div>
     </td>
    </tr>
    <tr style="height:8px;">
    <td colspan="5" style="border:0px;" ></td>
    </tr>
    <tr style="height:3px;">
    <td colspan="5" style="border:1px dashed #aaabb0;" ></td>
    </tr>
    <tr style="height:8px;">
    <td colspan="5" style="border:0px;" ></td>
    </tr>
    <tr>
      <td class="td_bold" style="border-bottom:0px;border-right:0px;">&nbsp;4</td>
      <td class="td_bold" style="border-bottom:0px">企业资质等级</td>
      <td colspan="2" style="border-bottom:0px">
       <span>${model.comZzGrade}</span><br></br>
       <span>${model.supDetails[0].comZzGrade2}</span>
      </td>
      <td style="border-bottom:0px;border-right: 1px solid #8c8f94;">
      <div id="uploadAttaContainer_zz" class="sup_attach">
		<s:iterator value="appAttachFiles_zz">
				<s:url id="down" action="supDownload" namespace="/sup"  includeParams="none"  >
					<s:param name="fileName">${fileName}</s:param>
					<s:param name="realFileName">${realFileName}</s:param>
					<s:param name="bizModuleCd">${bizModuleCd}</s:param>
					<s:param name="uiid">${creator}</s:param>
					<s:param name="id">${appAttachFileId}</s:param>
				</s:url>
				<a href="${down}" title="${realFileName}">${realFileName}</a>&nbsp;
				<input type="hidden" name="attaFileIds" value="${appAttachFileId}"/>
		</s:iterator>
	 </div>
      </td>
    </tr>
    <tr style="height:8px;">
    <td colspan="5" style="border:0px;" ></td>
    </tr>
    <tr style="height:3px;">
    <td colspan="5" style="border:1px dashed #aaabb0;" ></td>
    </tr>
    <tr style="height:8px;">
    <td colspan="5" style="border:0px;" ></td>
    </tr>
    <tr>
      <td class="td_bold" style="border-bottom:0px;border-right:0px;">&nbsp;5</td>
      <td class="td_bold" style="border-bottom:0px">企业安全生产许可证</td>
      <td style="border-bottom:0px">
      <span>${model.supDetails[0].comSaftCert}</span>
      </td>
      <td style="border-bottom:0px">
      <span class="td_bold">有效期：</span>
      <span>${model.supDetails[0].comSaftDate}</span>
      </td>
      <td style="border-bottom:0px;border-right: 1px solid #8c8f94;">
       <div id="uploadAttaContainer_safe" class="sup_attach">
        <s:iterator value="appAttachFiles_save">
				<s:url id="down" action="supDownload" namespace="/sup"  includeParams="none"  >
					<s:param name="fileName">${fileName}</s:param>
					<s:param name="realFileName">${realFileName}</s:param>
					<s:param name="bizModuleCd">${bizModuleCd}</s:param>
					<s:param name="uiid">${creator}</s:param>
					<s:param name="id">${appAttachFileId}</s:param>
				</s:url>
				<a href="${down}" title="${realFileName}">${realFileName}</a>&nbsp;
				<input type="hidden" name="attaFileIds" value="${appAttachFileId}"/>
			</s:iterator>
	   </div>
     </td>
    </tr>  
    <tr style="height:8px;">
    <td colspan="5" style="border:0px;" ></td>
    </tr>
    <tr style="height:3px;">
    <td colspan="5" style="border:1px dashed #aaabb0;" ></td>
    </tr>
    <tr style="height:8px;">
    <td colspan="5" style="border:0px;" ></td>
    </tr>
    <tr>
      <td class="td_bold" style="border-bottom:0px;border-right:0px;">&nbsp;6</td>
      <td class="td_bold" style="border-bottom:0px">质量、环境及职业健康安全认证</td>
      <td colspan="2" style="border-bottom:0px">
      <s:if test="model.supDetails[0].comQualCert1==1">质量&nbsp;&nbsp;</s:if>
      <s:if test="model.supDetails[0].comQualCert2==2">环境&nbsp;&nbsp;</s:if>
      <s:if test="model.supDetails[0].comQualCert3==3">职业健康安全&nbsp;&nbsp;</s:if>
      <s:if test="model.supDetails[0].comQualCert4==4">无</s:if>
      </td>
      <td style="border-bottom:0px;border-right: 1px solid #8c8f94;">
      <div id="uploadAttaContainer_qual" class="sup_attach">
       <s:iterator value="appAttachFiles_qual">
			<s:url id="down" action="supDownload" namespace="/sup"  includeParams="none"  >
					<s:param name="fileName">${fileName}</s:param>
					<s:param name="realFileName">${realFileName}</s:param>
					<s:param name="bizModuleCd">${bizModuleCd}</s:param>
					<s:param name="uiid">${creator}</s:param>
					<s:param name="id">${appAttachFileId}</s:param>
				</s:url>
				<a href="${down}" title="${realFileName}">${realFileName}</a>&nbsp;
				<input type="hidden" name="attaFileIds" value="${appAttachFileId}"/>
		
		</s:iterator>
	</div>
      </td>
    </tr>
    <tr style="height:8px;">
    <td colspan="5" style="border:0px;" ></td>
    </tr>
    <tr style="height:3px;">
    <td colspan="5" style="border:1px dashed #aaabb0;" ></td>
    </tr>
    <tr style="height:8px;">
    <td colspan="5" style="border:0px;" ></td>
    </tr>
    <tr>
      <td class="td_bold" style="border-bottom:0px;border-right:0px;">&nbsp;7</td>
      <td class="td_bold" style="border-bottom:0px">企业或产品主要荣誉</td>
      <td colspan="2" style="border-bottom:0px">${fn:replace(model.supDetails[0].comHonor,vEnter,"<br>")}</td>
      <td style="border-bottom:0px;border-right: 1px solid #8c8f94;">
      <div id="uploadAttaContainer_hono" class="sup_attach">
       <s:iterator value="appAttachFiles_hono">
				<s:url id="down" action="supDownload" namespace="/sup"  includeParams="none"  >
					<s:param name="fileName">${fileName}</s:param>
					<s:param name="realFileName">${realFileName}</s:param>
					<s:param name="bizModuleCd">${bizModuleCd}</s:param>
					<s:param name="uiid">${creator}</s:param>
					<s:param name="id">${appAttachFileId}</s:param>
				</s:url>
				<a href="${down}" title="${realFileName}">${realFileName}</a>&nbsp;
				<input type="hidden" name="attaFileIds" value="${appAttachFileId}"/>
			
		</s:iterator>
	 </div>
      </td>
    </tr>
    <tr style="height:8px;">
    <td colspan="5" style="border:0px;" ></td>
    </tr>
    <tr style="height:3px;">
    <td colspan="5" style="border:1px dashed #aaabb0;" ></td>
    </tr>
    <tr style="height:8px;">
    <td colspan="5" style="border:0px;" ></td>
    </tr>
    <tr>
      <td class="td_bold" style="border-bottom:0px;border-right:0px;">&nbsp;8</td>
      <td class="td_bold" style="border-bottom:0px">企业财务状况</td>
      <td colspan="2" style="border-bottom:0px">${fn:replace(model.supDetails[0].comFinSta,vEnter,"<br>")}</td>
      <td style="border-bottom:0px;border-right: 1px solid #8c8f94;">
       <div id="uploadAttaContainer_fin" class="sup_attach">
       <s:iterator value="appAttachFiles_fin">
			<s:url id="down" action="supDownload" namespace="/sup"  includeParams="none"  >
					<s:param name="fileName">${fileName}</s:param>
					<s:param name="realFileName">${realFileName}</s:param>
					<s:param name="bizModuleCd">${bizModuleCd}</s:param>
					<s:param name="uiid">${creator}</s:param>
					<s:param name="id">${appAttachFileId}</s:param>
				</s:url>
				<a href="${down}" title="${realFileName}">${realFileName}</a>&nbsp;
				<input type="hidden" name="attaFileIds" value="${appAttachFileId}"/>
			</s:iterator>
	  </div>
      </td>
    </tr>
    <tr style="height:8px;">
    <td colspan="5" style="border:0px;" ></td>
    </tr>
    <tr style="height:3px;">
    <td colspan="5" style="border:1px dashed #aaabb0;" ></td>
    </tr>
    <tr style="height:8px;">
    <td colspan="5" style="border:0px;" ></td>
    </tr>
    <tr>
      <td class="td_bold" style="border-right:0px;border-bottom:1px #8c8f94;">&nbsp;9</td>
      <td class="td_bold"style="border-bottom:1px #8c8f94;">类似工程业绩</td>
      <td colspan="2"style="border-bottom:1px #8c8f94;">${fn:replace(model.supDetails[0].comProPerf,vEnter,"<br>")}</td>
      <td style="border-right: 1px solid #8c8f94;border-bottom:1px #8c8f94;">
      <div id="uploadAttaContainer_pro" class="sup_attach">
       <s:iterator value="appAttachFiles_pro">
			<s:url id="down" action="supDownload" namespace="/sup"  includeParams="none"  >
					<s:param name="fileName">${fileName}</s:param>
					<s:param name="realFileName">${realFileName}</s:param>
					<s:param name="bizModuleCd">${bizModuleCd}</s:param>
					<s:param name="uiid">${creator}</s:param>
					<s:param name="id">${appAttachFileId}</s:param>
				</s:url>
				<a href="${down}" title="${realFileName}">${realFileName}</a>&nbsp;
				<input type="hidden" name="attaFileIds" value="${appAttachFileId}"/>
		</s:iterator>
		</div>
      </td>
    </tr>
    <tr style="height:8px;">
    <td colspan="5" style="border:0px;" ></td>
    </tr>
    <tr style="height:3px;">
    <td colspan="5" style="border:1px dashed #aaabb0;" ></td>
    </tr>
    <tr style="height:8px;">
    <td colspan="5" style="border:0px;" ></td>
    </tr>
    <tr>
      <td class="td_bold" style="border-bottom:0px;border-right:0px;">&nbsp;10</td>
      <td class="td_bold" style="border-bottom:0px">宝龙业绩</td>
      <td colspan="2" style="border-bottom:0px">
      <span>
         <s:if test="model.supDetails[0].cooperated==1">已合作  &nbsp;&nbsp;</s:if>
         <s:elseif test="model.supDetails[0].cooperated==2">未合作 &nbsp;&nbsp;</s:elseif>
         <br>
         ${model.supDetails[0].supChecView}
        </span>
      </td>
      <td style="border-bottom:0px;border-right: 1px solid #8c8f94;">
      <div id="uploadAttaContainer_mana" class="sup_attach">
        <s:iterator value="appAttachFiles_mana">
				<s:url id="down" action="supDownload" namespace="/sup"  includeParams="none"  >
					<s:param name="fileName">${fileName}</s:param>
					<s:param name="realFileName">${realFileName}</s:param>
					<s:param name="bizModuleCd">${bizModuleCd}</s:param>
					<s:param name="uiid">${creator}</s:param>
					<s:param name="id">${appAttachFileId}</s:param>
				</s:url>
				<a href="${down}" title="${realFileName}">${realFileName}</a>&nbsp;
				<input type="hidden" name="attaFileIds" value="${appAttachFileId}"/>
		</s:iterator>
		</div>
      </td>
    </tr>
  </table>
</div>
<div style="padding-top:15px;padding-left:5px;padding-right:5px;">
 <table  class="sup_table" cellspacing="0" cellpadding="0"style="background-color:#e4e7ec;">
  <tr style="height:10px;background-color: #878787;">
  <td colspan="5"></td>
  </tr>
  <tr>
   <td width="20px;" class="td_bold"  style="border-right:0px;" rowspan="5">&nbsp;11</td>
   <td width="150px;"class="td_bold"  rowspan="5">审核信息</td>
   <td>
   <span class="td_bold">资料验证：</span>
    <span><s:if test="model.supDetails[0].supInfoVeri==1">通过资格文件预审</s:if>
      <s:elseif test="model.supDetails[0].supInfoVeri==2">未通过资格预审，取消资格</s:elseif>
     </span>
   </td>
   <td width="150px;"></td>
  </tr>
  <tr>
   <td><span class="td_bold">现场考察结果：</span>
   <span>
     <s:if test="model.supExamResu==1">考察合格</s:if>
      <s:elseif test="model.supExamResu==2">考察不合格</s:elseif>
      <s:elseif test="model.supExamResu==3">未考察</s:elseif>
    </span>
    </td>
   <td>
     <div class="sup_attach">
       <s:iterator value="appAttachFiles_exam">
			<s:url id="down" action="supDownload" namespace="/sup"  includeParams="none"  >
					<s:param name="fileName">${fileName}</s:param>
					<s:param name="realFileName">${realFileName}</s:param>
					<s:param name="bizModuleCd">${bizModuleCd}</s:param>
					<s:param name="uiid">${creator}</s:param>
					<s:param name="id">${appAttachFileId}</s:param>
				</s:url>
				<a href="${down}"  title="${realFileName}">${realFileName}</a>&nbsp;
				<input type="hidden" name="attaFileIds" value="${appAttachFileId}"/>
			
		</s:iterator>
		</div>
   </td>
  </tr>
  <tr>
   <td>
    <span class="td_bold">履约评估 ：</span>
     <span>
       <s:if test="model.supDetails[0].supEvaluate==1">合格</s:if>
        <s:elseif test="model.supDetails[0].supEvaluate==2">不合格</s:elseif>
        <s:elseif test="model.supDetails[0].supEvaluate==3">未评估</s:elseif>
      </span>
    </td>
   <td>
    <s:iterator value="appAttachFiles_eval">
			<s:url id="down" action="supDownload" namespace="/sup"  includeParams="none"  >
					<s:param name="fileName">${fileName}</s:param>
					<s:param name="realFileName">${realFileName}</s:param>
					<s:param name="bizModuleCd">${bizModuleCd}</s:param>
					<s:param name="uiid">${creator}</s:param>
					<s:param name="id">${appAttachFileId}</s:param>
				</s:url>
				<a href="${down}"  title="${realFileName}">${realFileName}</a>&nbsp;
				<input type="hidden" name="attaFileIds" value="${appAttachFileId}"/>
			
		</s:iterator>
    </td>
  </tr>
  <tr>
   <td>
   <span class="td_bold">供方级别：</span>
   <span>
     <s:if test="model.supManaEval==1">优秀</s:if>
        <s:elseif test="model.supManaEval==4">可用</s:elseif>
        <s:elseif test="model.supManaEval==2">试用</s:elseif>
        <s:elseif test="model.supManaEval==3">停用</s:elseif>
        <s:elseif test="model.supManaEval==5">禁用</s:elseif>
   </span>
   </td>
   <td>
     <s:iterator value="appAttachFiles_main">
			<s:url id="down" action="supDownload" namespace="/sup"  includeParams="none"  >
					<s:param name="fileName">${fileName}</s:param>
					<s:param name="realFileName">${realFileName}</s:param>
					<s:param name="bizModuleCd">${bizModuleCd}</s:param>
					<s:param name="uiid">${creator}</s:param>
					<s:param name="id">${appAttachFileId}</s:param>
				</s:url>
				<a href="${down}" title="${realFileName}">${realFileName}</a>&nbsp;
				<input type="hidden" name="attaFileIds" value="${appAttachFileId}"/>
			
		</s:iterator>
   </td>
  </tr>
  <tr>
   <td>
   <span class="td_bold">备注：</span>
   <span>${model.remark}</span>
   </td>
   <td>
   </td>
  </tr>
 </table>
</div>
</form>
</body>
</html>
