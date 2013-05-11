<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script charset="utf-8" src="${ctx}/kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="${ctx}/kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/sc/sc-contract-templet-info-common.js"></script>
<script type="text/javascript" src="${ctx}/kindeditor/postil.js"></script>


<br/>

<%-- 如果合同评审表还在网批中,并且当前登录人是最后审批人,显示操作列 --%>
<c:choose>
<c:when test="${approveLevel!=null}">
	<c:set var="resNodeCount" value="${fn:length(resApproveNodes)}" />
	<c:set var="isLastUser" value="1" />
	<c:forEach var="node" items="${resApproveNodes}" varStatus="status">
		<c:if test="${approveLevel<=node.approveLevel}">
			<c:if test="${curUserCd!=node.userCd || isLastUser!='1'}">
				<c:set var="isLastUser" value="0" />
			</c:if>
		</c:if>
	</c:forEach>
	<c:if test="${isLastUser=='1'}">
		<input type="hidden" id="display_oper_flag" name="display_oper_flag" value="true"/>
	</c:if>
	<c:if test="${isLastUser=='0'}">
		<input type="hidden" id="display_oper_flag" name="display_oper_flag" value="false"/>
	</c:if>
</c:when>
<c:otherwise>
	<input type="hidden" id="display_oper_flag" name="display_oper_flag" value="false"/>
</c:otherwise>
</c:choose>

<div align="center" class="billContent" id="divNoMark" style="display:none;">
<table class="mainTable" style="margin-bottom: 5px;">
	<tr alige="left"><td align="center">本合同使用了标准合同模板，但没有批注，查看合同请点击
		<span class="link scContractLink" onclick="getContById('${templateBean.contractTempletInfoId}','${templateBean.contractTempletHisId}');">
			<%-- 大多数模板包含的bid-contract-base.jsp,里面显示的合同号使用的contractNo --%>
			<%-- 老代码备份 <c:if test="${fn:length(templateBean.contractNo)>0}">${templateBean.contractNo}</c:if> --%>
			<s:if test="!templateBean.contractNo.isEmpty()">${templateBean.contractNo}</s:if>

			<%-- 有的模板包含的bid-contract-strategy-base.jsp,里面显示的合同号使用的scContractNo(例如strategy-purchase-bill.jsp模板) --%>
			<s:else>
				<s:if test="!templateBean.scContractNo.isEmpty()">${templateBean.scContractNo}</s:if>
			</s:else>
		</span>
	</td></tr>
</table>
</div>


<div align="center" class="billContent" id="divNoStandard" style="display:none;">
<table class="mainTable" style="margin-bottom: 5px;">
	<tr alige="left"><td align="center">
		<%-- 如果templateBean.scContractNo不存在,那么针对templateBean.scContractNo的判断都为false --%>

		<s:if test="templateBean.contractNo.isEmpty()">
			<s:if test="!templateBean.scContractNo.isEmpty()">本合同未使用标准合同模板，查看合同请点击
				<span class="link scContractLink" onclick="getContById('${templateBean.contractTempletInfoId}','${templateBean.contractTempletHisId}');">
				${templateBean.scContractNo}
				</span>
			</s:if>
			<s:else>本合同未使用合同文本库</s:else>
		</s:if>
		<s:else>本合同未使用标准合同模板，查看合同请点击
			<span class="link scContractLink" onclick="getContById('${templateBean.contractTempletInfoId}','${templateBean.contractTempletHisId}');">
			${templateBean.contractNo}
			</span>
		</s:else>
	</td></tr>
</table>
</div>

<div align="center" class="billContent" id="divMarkList"></div>

<script type="text/javascript">

//判断合同是不是标准合同
var isStandard=false;

//取得合同文本的批注列表页面,替换divMarkList
function getContHtml(id, hisId) {
	$.ajax({
		url :"${ctx}/sc/sc-contract-templet-info!markList.action",
		type : 'POST',
		data : {id:id, hisId:hisId},
		async : false, // ajax同步
		timeout : 1000,
		error : function(result) {
			$("#divMarkList").html(result);
		},
		success : function(result) {
			$("#divMarkList").html(result);
		}
	});
}

function getStandard(id){
	$.ajax({
		url :"${ctx}/sc/sc-contract-templet-info!isStandardCont.action",
		type : 'POST',
		data : {id:id},
		async : false, // ajax同步
		timeout : 1000,
		error : function(responseText) {
			if(responseText.indexOf("true")>-1){
				isStandard=true;
			}
		},
		success : function(responseText) {
			if(responseText.indexOf("true")>-1){
				isStandard=true;
			}
		}
	});
	//alert(isStandard);
}

//跳转到合同文本页面,高亮定位到指定的批注
function gotoCont(id, hisId, markId) {
	if (!isStandard) {
		var getContUrl = "${ctx}/sc/sc-contract-templet-info!readNonStandardCon.action?scContId=" + id;
		if (parent.TabUtils == null)
			window.open(getContUrl);
		else
			parent.TabUtils.newTab("sc-contract-templet-info", "非标准合同查看", getContUrl, true);
	} else {
		var getContUrl = "${ctx}/sc/sc-contract-templet-info!readContract.action?scContId=" + id//+ "&contractTempletHisId=" + hisId
				+ "&statusCd=${statusCd}"+"&highMarkId="+markId;
		if (parent.TabUtils == null)
			window.open(getContUrl);
		else
			parent.TabUtils.newTab("sc-contract-templet-info", "标准合同查看", getContUrl, true);
	}
}
//测试当前页面里是否引用了KindEditor.js
function _getBasePath() {
	var els = document.getElementsByTagName('script'), src;
	for (var i = 0, len = els.length; i < len; i++) {
		//alert(src);
		src = els[i].src || '';
		if (/kindeditor[\w\-\.]*\.js/.test(src)) {
			return src.substring(0, src.lastIndexOf('/') + 1);
		}
	}
	return '';
}

//是否是删除批注操作
function isDel(val){
	var flag = false;
	if(val==null || val==undefined){
		return false;
	}
	var tval = val.toLowerCase().trim();
	if(tval  == ""){
		flag = true;
	}
	else if(tval == "&nbsp;"){
		flag = true;
	}
	else if(tval == "<br />"){
		flag = true;
	}
	else if(tval == "<strong></strong>&nbsp;"){
		flag = true;
	}
	return flag;
}

//删除批注按钮事件
function rejectMarkContent(markId){
	
	//定义参数集
	ymPrompt.parames = {};

	//保存参数
	//ymPrompt.parames.arg = arg;
	ymPrompt.confirmInfo({
		message : '你确定要删除当前批注信息吗？',
		title : "删除批注",
		handler : function(tp) {
			if (tp == 'ok') {
				$("div[ctrType=mark]").attr("isDblclick", "false");
				$("div[ctrType=mark][forid="+markId+"]").attr("isDblclick","true");
				var $div = $("div[ctrType=mark][isDblclick=true][forid="+markId+"]");
				var _markId = markId;
				//alert(_markId);
				
				var _edit = $("#keEditIframe");
				var $source = $(_edit).contents().find("#" + _markId + "");
				var sourceHtml = $source.attr("comment").replace(/‘/ig, "'").replace(/“/ig, "\"");
				//alert(sourceHtml);
				
				$($div).parent().parent().remove();
				$($source).after(sourceHtml).remove();
				var _content = "";
				//var statusContent = "<span style='color:red;'>批注删除成功</span>";
				if (editor != null && editor != undefined) {
					_content = "" + editor.html();
				}
				//alert(_content);
				
				//保存合同文本和ContractMark
				$.post(_ctx	+ "/sc/sc-contract-templet-info!delHistoryMark.action",
					{scContractId : $("#scContractId").val(),
					 hisConId : $("#hisContId").val(),
					 markId : _markId,
					 actType : "del",
					 content : _content
					}, function(result) {
						var _resultJson = toJson(result);
						if (_resultJson != undefined&& _resultJson.status != undefined) {
							if (_resultJson.status != true) {
								alert(_resultJson.errorMsg);
							}
						} else {
							alert("无法解析返回的信息！");
						}
					});
				//删除对应的ContractMarkHis
				$.post(_ctx	+ "/sc/sc-contract-templet-info!deleteContractMarkHis.action",
						{contractMarkId : _markId},
						function(result) {
							var _resultJson = toJson(result);
							if (_resultJson != undefined&& _resultJson.status != undefined) {
								if (_resultJson.status != true) {
									alert(_resultJson.errorMsg);
								}
							} else {
								alert("无法解析返回的信息！");
							}
					});
				//$("#contractStatus").html(statusContent);
			}
		}
	});
}

//编辑批注按钮事件,调用插件
function doEditMarkContent(markId){
	$("div[ctrType=mark]").attr("isDblclick", "false");
	$("div[ctrType=mark][forid="+markId+"]").attr("isDblclick","true");
	editor.clickToolbar('modmark');
}

//取消修改批注按钮事件
function cancelEditMarkContent(markId){
	var _markId = markId;
	var _edit = $("#keEditIframe");
	var _val = $(_edit).contents().find("#" + _markId).html();
	var aHtml = _val;
	if (aHtml) {
		if(_val.toLowerCase().indexOf("<table")>-1 && _val.toLowerCase().indexOf("tableid")>-1){
			aHtml=_val.replace("background-color: rgb(255, 99, 71);","").replace("Tomato","").replace("yellow","").replace("BACKGROUND: tomato","");
		}
	}
	
	var modCss = "background-color: yellow;text-decoration: line-through;cursor: pointer;";
	_val = $("div[ctrType=markHis][forid="+_markId+"]").html();
	// 将p元素用正规全干掉
	_val = _val.replace(/<p>/i,"").replace(/<\/p>/i,"");
	_val = _val.trim();
	if(isDel(_val)){
		_val = "<span style='"+modCss+"'>"+aHtml+"</span>";
	}

	$(_edit).contents().find("#" + _markId).html(_val);
	$(_edit).contents().find("#" + _markId).find("table").css("background","yellow");
					
	var _content= "" +editor.html();
	_content=_content.replace(/'/ig, "‘");

	$.post(_ctx + "/sc/sc-contract-templet-info!saveHistory.action", {
		scContractId : $("#scContractId").val(),
		markContent:_val,
		markId:_markId,
		hisConId:$("#hisContId").val(),
	    content:_content
		}, function(result) {
			var _resultJson = toJson(result);
			if (_resultJson != undefined && _resultJson.status != undefined) {
				if (_resultJson.status != true) {
					alert(_resultJson.errorMsg);
				} else {
					$("#hisContId").val(_resultJson.hisConId);
					
					if(typeof callbackCancelEditMarkContent!='undefined' && callbackCancelEditMarkContent instanceof Function){        
						callbackCancelEditMarkContent(_markId,_val);  
					}  
					return;
				}
			} else {
				alert("无法解析返回的信息！");
			}
		}
	);
	return;
}

//修改批注完成后回调事件,用来调整页面内容和按钮显示
function callbackEditMarkContent(markId,markContent){
	//把修改后的markContent保存到html上
	var curMark = $("div[ctrType=mark][forid="+markId+"]");
	curMark.html(markContent);
	curMark.show();
	
	$(":button[btn_type=edit_mark][forid="+markId+"]").hide();
	$(":button[btn_type=cancel_edit_mark][forid="+markId+"]").show();
}

//取消修改批注完成后回调事件,用来调整页面内容和按钮显示
function callbackCancelEditMarkContent(markId,markContent){
	//把取消修改后的markContent保存到html上
	var curMark = $("div[ctrType=mark][forid="+markId+"]");
	curMark.html(markContent);
	curMark.hide();
	
	$(":button[btn_type=edit_mark][forid="+markId+"]").show();
	$(":button[btn_type=cancel_edit_mark][forid="+markId+"]").hide();
}
</script>

<script type="text/javascript">
getStandard('${templateBean.contractTempletInfoId}');
if (!isStandard) {
	$("#divNoStandard").show();
}else{
	getContHtml('${templateBean.contractTempletInfoId}','${templateBean.contractTempletHisId}');
}
</script>

