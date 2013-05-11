<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>
<%@ include file="/common/global.jsp" %>

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.DictContants"%>

<div id="credit_div">
<s:set var="creditItemCount"><s:property value="bisContCredits.size()"/></s:set>

<table class="content_table" id="tbCreditItem" >
	<col width="60"/>
	<col />
	<col />
	<col />
	<col width="40"/>
	<col width="40"/>
	<thead>
		<tr class="header">
			<th align="left" style="background: none;">序号</th>
			<th align="left">资料名称</th>
			<th align="left">资料时限</th>
			<th align="left">备注</th>
			<th align="left">附件</th>
			<th align="left">操作</th>
		</tr>
	</thead>
	<tbody>
		<tr id="trCreditItem" class="mainTr" style="display: none; height: 35px;">
			<input type="hidden" class="inputContCredit" id="entityTmpId_0" name="bisContCredits[0].entityTmpId" />
			<input type="hidden" class="inputContCredit" id="attachFlg_0" name="bisContCredits[0].attachFlg" />
			<td click2expand="true">
				<div class="bis-read" style="display:none;" ></div>
				<div class="bis-edit" >
				<input class="inputContCredit" type="text" name="bisContCredits[0].sequenceNo" id="sequenceNo_0" />
				</div>
			</td>
			<td click2expand="true">
				<div class="bis-read pd-chill-tip" style="display:none;" ></div>
				<div class="bis-edit" >
				<input validate="required" class="inputContCredit" type="text" name="bisContCredits[0].infoName" id="infoName_0" altname="资信资料名称" />
				</div>
			</td>
			<td click2expand="true">
				<div class="bis-read" style="display:none;" ></div>
				<div class="bis-edit" >
				<input class="inputContCredit" type="text" name="bisContCredits[0].infoLimit" id="infoLimit_0" />
				</div>
			</td>
			<td click2expand="true" style="padding-top: 2px;padding-bottom: 2px;">
				<div class="bis-read pd-chill-tip" style="display:none;" ></div>
				<div class="bis-edit" >
				<textarea style="width:95%;" class="inputContCredit" name="bisContCredits[0].remark" ></textarea>
				</div>
			</td>
			<td>
				<a style="cursor:pointer">
				<img id="bisContCreditAttachId_0" class="inputContCredit" src="${ctx}/resources/images/common/atta.gif" />
				</a>
			</td>
			<td align="center"><a href="javascript:void(0)" onclick="delCreditItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a></td>
		</tr>
		<s:iterator value="bisContCredits" var="item" status="s">
		<s:set var="i"><s:property value="#s.index" /></s:set>
		<tr class="mainTr" id="main_${bisContCreditId}" >
			<input type="hidden" name="bisContCredits[<s:property value="#s.index" />].bisContCreditId" value="${bisContCreditId}" />
			<input type="hidden" name="bisContCredits[<s:property value="#s.index" />].recordVersion" value="${recordVersion}" />
			<input type="hidden" id="attachFlg_<s:property value="#s.index" />" name="bisContCredits[<s:property value="#s.index" />].attachFlg" value="${attachFlg}" />
			<td click2expand="true">
				<div class="bis-read">${sequenceNo}</div>
				<div class="bis-edit" style="display:none;" >
				<input style="width:95%;" type="text" name="bisContCredits[<s:property value="#s.index" />].sequenceNo" value="${sequenceNo}" />
				</div>
			</td>
			<td click2expand="true">
				<div class="bis-read pd-chill-tip" title="${infoName}" >${infoName}</div>
				<div class="bis-edit" style="display:none;" >
				<input style="width:95%;" validate="required" type="text" name="bisContCredits[<s:property value="#s.index" />].infoName" value="${infoName}" altname="资信资料名称" />
				</div>
			</td>
			<td click2expand="true">
				<div class="bis-read" >${infoLimit}</div>
				<div class="bis-edit" style="display:none;" >
				<input style="width:95%;" type="text" name="bisContCredits[<s:property value="#s.index" />].infoLimit" value="${infoLimit}" />
				</div>
			</td>
			<td click2expand="true">
				<div class="bis-read pd-chill-tip" title="${remark}" >
					<div class="ellipsisDiv_full" >
					${remark}
					</div>
				</div>
				<div class="bis-edit" style="display:none;" >
				<textarea style="width:95%; height:35px;" name="bisContCredits[<s:property value="#s.index" />].remark" >${remark}</textarea>
				</div>
			</td>
			<td>
				<s:if test="#canEdit=='true'">
					<a href="javascript:openAttachment('附件管理','${bisContCreditId}','bisContCreditAttachId_<s:property value="#s.index" />','attachFlg_<s:property value="#s.index" />');" >
						<img id='bisContCreditAttachId_<s:property value="#s.index" />' <s:if test='attachFlg==1'>src="${ctx}/resources/images/common/atta_y.gif"</s:if><s:else>src="${ctx}/resources/images/common/atta.gif"</s:else> />
					</a>
				</s:if>
				<s:else>
					<s:if test='attachFlg==1'>
					<a href="javascript:showAttachment('${bisContCreditId}');" >
						<img id='bisContCreditAttachId_<s:property value="#s.index" />' src="${ctx}/resources/images/common/atta_y.gif" />
					</a>
					</s:if>
				</s:else>
			</td>
			<td align="center">
			<s:if test="#canEdit=='true'">
			<a href="javascript:void(0)" onclick="delCreditItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
			</s:if>
			</td>
		</tr>
		</s:iterator>
	</tbody>
</table>
</div>

<script type="text/javascript">
	var trCreditItem=$("#trCreditItem").clone();
	$("#trCreditItem").remove();
	var creditCloneCount = 0;
	var indexCredit=${creditItemCount};
	
	function addCreditItem(){
		var trCreditItem_new=trCreditItem.clone();
		creditCloneCount++;
		$(trCreditItem_new).show();
		$(trCreditItem_new).find(".inputContCredit").each(function(i){
			this.name=this.name.replace('0',indexCredit);
			this.id=this.id.replace('0',indexCredit);
		});
		$(trCreditItem_new).find(":textarea").val('');
		bindClickCreditAtta(trCreditItem_new, indexCredit);
		$("#tbCreditItem").append(trCreditItem_new);
		indexCredit++;
	}
	function delCreditItem(dom){
		$(dom).parent().parent().remove();
	}
	
	function bindClickCreditAtta(trCreditItem_new, indexCredit) {
		$.post("${ctx}/bis/bis-cont!getAttachTmpId.action", function(result) {
			var domId='bisContCreditAttachId_'+indexCredit;
			var attachFlgId='attachFlg_'+indexCredit;
			$(trCreditItem_new).find("a").bind("click", function() {
				openAttachment('附件管理',result,domId,attachFlgId);
			});
			$("#entityTmpId_"+indexCredit).val(result);
		});
	}
	
	bindTblEv('tbCreditItem');
	
</script>
<s:if test="#canEdit!='true'">
<script type="text/javascript">
	$("#tbCreditItem :input").attr("readonly","readonly");
	$("#tbCreditItem :textarea").attr("readonly","readonly");
	$("#tbCreditItem :input").filter(".Wdate").attr("onfocus","");
	$("#tbCreditItem :input").filter(".Wdate").removeClass("Wdate");
</script>
</s:if>

