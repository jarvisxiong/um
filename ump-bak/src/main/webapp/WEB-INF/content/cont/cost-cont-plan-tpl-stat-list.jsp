<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/global.jsp" %>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/thickbox.css"  />
	<link type="text/css" rel="stylesheet" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" /> 	
	<link rel="stylesheet" type="text/css" media="screen" href="/PowerDesk/resources/css/common/select.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="/PowerDesk/resources/js/jqueryplugin/jqModal/jqModal.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/cost/cost.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/cont/cont.css"  />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/customInput/customInput.css"  />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/superTables.css"  />	
	
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.resizable.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/loadMask/jquery.loadmask.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
	<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.highlight.js" ></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/superTables.js"></script>
	<style type="text/css">
	table{ border-collapse:collapse; }
	th{line-height:22px; padding:0px; border:0px;}
	.intable td{ border:#cccccc solid 1px; line-height:20px; padding-left:5px; padding-top:5px; }
	.intable td input{ border:0px; border-bottom:1px solid #999999; height:20px; width:90%;}
	.margindiv{ height:450px; min-height:200px; padding: 0px; overflow:auto;}
	</style>
	<script type="text/javascript">
	$(document).ready(function(){
		//fillPageData();
		//listenTr();
		//var myST = new superTable("MyTable",{fixedCols : 2,cssSkin : "sOrange",onFinish : function () {}});
	});
	function listenTr(){
		var trs = $("#trcount").val();
		if(trs>65){
			$("#maketablestyleBtn").click();
		}else{
			setInterval(listenTr(), 1000);
		}
	}
	/**填充页面表单数据*/
	function fillPageData(){
		var url = "${ctx}/cost/cost-cont-plan-tpl-stat!list2json.action?2=1";
		var param = {};
		$.getJSON(url,param,function(data){
			$.each(data,function(i,j){
				var rowTypeCd = j[3];
				var memoDesc =j[6];
				var contSubTargAmt =j[4];
				var remark =j[7];
				var subdiff = "";
				var typetr = i==0?0:i%6;
				fillTable(i,typetr,rowTypeCd,memoDesc,contSubTargAmt,subdiff,remark);
			});
		});
		
	}
	function valiBlank(demo){
		if(demo==null||demo==undefined||demo=="null"){
			return "";
		}else{
			return demo;
		}
	}
	function fillTable(index,typetr,rowTypeCd,memoDesc,contSubTargAmt,subdiff,remark){
		var trHtml = "";
		if(typetr==0){
			trHtml += '<tr>';
			trHtml += '  <td height="25" align="center">'+valiBlank(rowTypeCd)+'</td>';
			trHtml += '  <td>'+valiBlank(memoDesc)+'</td>';
			trHtml += '  <td><input name="plan'+index+'" type="hidden" id="plan'+index+'" value="'+valiBlank(contSubTargAmt)+'" /></td>';
			trHtml += '  <td><input name="plan'+index+'h" type="hidden" id="plan'+index+'h" value="'+valiBlank(subdiff)+'"/></td>';
			trHtml += '  <td><input name="remark'+index+'" type="text" id="remark'+index+'" value="'+valiBlank(remark)+'" /></td>';
			trHtml += '</tr>';
		}else if(typetr==1){
			trHtml += '<tr>';
			trHtml += '  <td height="25" align="center">'+valiBlank(rowTypeCd)+'</td>';
			trHtml += '  <td>'+valiBlank(memoDesc)+'</td>';
			trHtml += '  <td><input name="plan'+index+'" type="text" id="plan'+index+'" value="'+valiBlank(contSubTargAmt)+'" /></td>';
			trHtml += '  <td><input name="plan'+index+'h" type="text" id="plan'+index+'h" value="'+valiBlank(subdiff)+'" /></td>';
			trHtml += '  <td><input name="remark'+index+'" type="text" id="remark'+index+'" value="'+valiBlank(remark)+'"  /></td>';
			trHtml += '</tr>';
		}else if(typetr==2){
			trHtml += '<tr>';
			trHtml += '  <td height="25" align="center">'+valiBlank(rowTypeCd)+'</td>';
			trHtml += '  <td>'+valiBlank(memoDesc)+'</td>';
			trHtml += '  <td><input name="plan'+index+'" type="text" id="plan'+index+'" value="'+valiBlank(contSubTargAmt)+'" /></td>';
			trHtml += '  <td><input name="plan'+index+'h" type="text" id="plan'+index+'h"  value="'+valiBlank(subdiff)+'"/></td>';
			trHtml += '  <td><input name="remark'+index+'" type="text" id="remark'+index+'" value="'+valiBlank(remark)+'"  /></td>';
			trHtml += '</tr>';
		}else if(typetr==3){
			trHtml += '<tr>';
			trHtml += '  <td height="25" align="center">'+valiBlank(rowTypeCd)+'</td>';
			trHtml += '  <td>'+valiBlank(memoDesc)+'</td>';
			trHtml += '  <td><input name="plan'+index+'" type="text" id="plan'+index+'" value="'+valiBlank(contSubTargAmt)+'" /></td>';
			trHtml += '  <td><input name="plan'+index+'h" type="text" id="plan'+index+'h"  value="'+valiBlank(subdiff)+'"/></td>';
			trHtml += '  <td><input name="remark'+index+'" type="text" id="remark'+index+'" value="'+valiBlank(remark)+'"  /></td>';
			trHtml += '</tr>';
		}else if(typetr==4){
			trHtml += '<tr>';
			trHtml += '  <td height="25" align="center">'+valiBlank(rowTypeCd)+'</td>';
			trHtml += '  <td>'+valiBlank(memoDesc)+'</td>';
			trHtml += '  <td><input name="plan'+index+'" type="hidden" id="plan'+index+'" value="'+valiBlank(contSubTargAmt)+'" /></td>';
			trHtml += '  <td><input name="plan'+index+'h" type="hidden" id="plan'+index+'h"  value="'+valiBlank(subdiff)+'"/></td>';
			trHtml += '  <td><input name="remark'+index+'" type="text" id="remark'+index+'"  value="'+valiBlank(remark)+'" /></td>';
			trHtml += '</tr>';
		}else if(typetr==5){
			trHtml += '<tr>';
			trHtml += '  <td height="25" align="center">'+valiBlank(rowTypeCd)+'</td>';
			trHtml += '  <td>'+valiBlank(memoDesc)+'</td>';
			trHtml += '  <td><input name="plan'+index+'" type="hidden" id="plan'+index+'"  value="'+valiBlank(contSubTargAmt)+'"/></td>';
			trHtml += '  <td><input name="plan'+index+'h" type="hidden" id="plan'+index+'h" value="'+valiBlank(subdiff)+'" /></td>';
			trHtml += '  <td><input name="remark'+index+'" type="text" id="remark'+index+'" value="'+valiBlank(remark)+'"  /></td>';
			trHtml += '</tr>';
		}
		$("#trcount").val($("#trcount").val()+1);
		$(trHtml).appendTo("#MyTable");
	}
	</script>
<title>合约规划模板</title></head>
<body>
	<div id="mailMianContainer" >
	<div class="title_bar">
		<div style="float:left;margin-right: 5px;">
			<img src="${ctx}/images/fin/pic_Supplier.gif" style="margin-top:3px;"/>
		</div>
		<div style="float:left;">
			合约规划成本汇总
		</div>
		<div style="float:right;">
			<div class="btn_cutline_3_26"></div>
			<div style="float:left;width:50px;" class="btn_refresh" onclick='alert("fuck")' id="maketablestyleBtn">dotable</div>
		</div>
	</div>
	<!-- mailInfo end -->
	<div id="maiMainBottom" style="width:100%; height:100%;">
	
<form id="planForm" name="planForm" method="post" action="${ctx}/cost/cost-cont-plan-tpl-stat!input.action">
	<input id="trcount" type="hidden" value="0"/>
    <div class="margindiv">
	      <table id="MyTable" width="100%" border="1" cellpadding="0" cellspacing="0" class="intable">
	        <tr style=" border:#cccccc solid 1px;  ">
			  <th width="5%" height="30" scope="col">序号</th>
			  <th width="42%" scope="col">标段/合同名称</th>
			  <th width="18%" align="center" scope="col">合约子目标成本</th>
			  <th width="18%" align="center" scope="col">定标差异（仅动态版）</th>
			  <th width="17%" align="center" scope="col">备注</th>
			</tr>	
			<s:iterator value="costContPlanTplStatList">
			<div class="orgDiv">
					
					${rowTypeCd }
				</div>
			</s:iterator>
		
			<s:iterator value="costContPlanTplStatList">
				<tr>
				  <td height="25" align="center">${rowTypeCd} 121<s:property value="rowTypeCd"></s:property></td>
				  <td>${memoDesc}</td>
				  <td><input name="plan'+index+'" type="hidden" id="plan'+index+'"  value="'+valiBlank(contSubTargAmt)+'"/></td>
				  <td><input name="plan'+index+'h" type="hidden" id="plan'+index+'h" value="'+valiBlank(subdiff)+'" /></td>
				  <td><input name="remark'+index+'" type="text" id="remark'+index+'" value="'+valiBlank(remark)+'"  /></td>
				</tr>
			</s:iterator>
	        <!-- xin jia -->
	      </table>
      </div>
	    </form>
	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	  <tr>
		  <td height="25" align="center">&nbsp;</td>
		  <td align="center">&nbsp;</td>
		  <td valign="bottom"><input type="button" class="BUTTON" value="测试" onclick="fillPageData()" style="width: 100px;height: 24px; vertical-align: middle;"/></td>
		  <td valign="bottom"><input name="btnSave" type="button" class="BUTTON" id="btnSave" value="保存" style="width: 100px;height: 24px; vertical-align: middle;"/></td>
		  <td valign="bottom"><input name="btnCancel" type="button" class="BUTTON" id="btnCancel" value="取消" style="width: 100px;height: 24px; vertical-align: middle;" /></td>
	  </tr>
	</table>
	</div>
</div>
</body>
</html>

