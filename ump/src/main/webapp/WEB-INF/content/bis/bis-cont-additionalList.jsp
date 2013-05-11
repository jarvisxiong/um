<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>
<%@ include file="/common/global.jsp" %>

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.DictContants"%>

<div id="additional_div">
<s:set var="conItemCountAdd"><s:property value="bisContAdditionals.size()"/></s:set>

<table class="content_table" id="tbConItemAdd" >
	<col />
	<col width="120"/>
	<col />
	<col />
	<col width="40"/>
	<col width="40"/>
	<thead>
		<tr class="header">
			<th align="left" style="background: none;">名称</th>
			<th align="left">签订日期</th>
			<th align="left">条款</th>
			<th align="left">备注</th>
			<th align="left">附件</th>
			<th align="left">操作</th>
		</tr>
	</thead>
	<tbody>
		<tr id="trConItemAdd" class="mainTr" style="display: none; height: 35px;">
			<input type="hidden" class="inputContAddi" id="entityTmpId_0" name="bisContAdditionals[0].entityTmpId" />
			<input type="hidden" class="inputContAddi" id="attachFlg_0" name="bisContAdditionals[0].attachFlg" />
			<td click2expand="true">
				<div class="bis-read pd-chill-tip" style="display:none;" ></div>
				<div class="bis-edit" >
				<input validate="required" class="inputContAddi" type="text" name="bisContAdditionals[0].contAdditionalName" id="contAdditionalName_0" altname="补充合同名称" />
				</div>
			</td>
			<td click2expand="true">
				<div class="bis-read" style="display:none;" ></div>
				<div class="bis-edit" >
				<input validate="required" class="inputContAddi edit-date Wdate" type="text" name="bisContAdditionals[0].signDate" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'signDate\')}'})" altname="补充合同签约时间" />
				</div>
			</td>
			<td click2expand="true" style="padding-top: 2px;padding-bottom: 2px;">
				<div class="bis-read pd-chill-tip" style="display:none;" ></div>
				<div class="bis-edit" >
				<textarea style="width:95%;" class="inputContAddi" validate="required" name="bisContAdditionals[0].contAdditionalContent" maxlength="4000" altname="补充合同条款" ></textarea>
				</div>
			</td>
			<td click2expand="true">
				<div class="bis-read pd-chill-tip" style="display:none;" ></div>
				<div class="bis-edit" >
				<textarea style="width:95%;" class="inputContAddi" name="bisContAdditionals[0].contAdditionalDesc" ></textarea>
				</div>
			</td>
			<td>
				<a style="cursor:pointer">
				<img id="bisContAddiAttachId_0" class="inputContAddi" src="${ctx}/resources/images/common/atta.gif" />
				</a>
			</td>
			<td align="center"><a href="javascript:void(0)" onclick="delItemAdd(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a></td>
		</tr>
		<s:iterator value="bisContAdditionals" var="item" status="s">
		<s:set var="i"><s:property value="#s.index" /></s:set>
		<tr class="mainTr" id="main_${bisContAdditionalId}" >
			<input type="hidden" name="bisContAdditionals[<s:property value="#s.index" />].bisContAdditionalId" value="${bisContAdditionalId}" />
			<input type="hidden" name="bisContAdditionals[<s:property value="#s.index" />].recordVersion" value="${recordVersion}" />
			<input type="hidden" id="attachFlg_<s:property value="#s.index" />" name="bisContAdditionals[<s:property value="#s.index" />].attachFlg" value="${attachFlg}" />
			<td click2expand="true">
				<div class="bis-read pd-chill-tip" title="${contAdditionalName}" >${contAdditionalName}</div>
				<div class="bis-edit" style="display:none;" >
				<input style="width:95%;" validate="required" type="text" name="bisContAdditionals[<s:property value="#s.index" />].contAdditionalName" value="${contAdditionalName}" altname="补充合同名称" />
				</div>
			</td>
			<td click2expand="true">
				<div class="bis-read" ><s:date name="signDate" format="yyyy-MM-dd"/></div>
				<div class="bis-edit" style="display:none;" >
				<input style="width:95%;" validate="required" class="edit-date Wdate" type="text" name="bisContAdditionals[<s:property value="#s.index" />].signDate" value="<s:date name="signDate" format="yyyy-MM-dd"/>" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'signDate\')}'})" altname="补充合同签约时间" />
				</div>
			</td>
			<td click2expand="true">
				<div class="bis-read pd-chill-tip" title="${contAdditionalContent}" >
					<div class="ellipsisDiv_full" >
					${contAdditionalContent}
					</div>
				</div>
				<div class="bis-edit" style="display:none;" >
				<textarea style="width:95%; height:35px;" validate="required" name="bisContAdditionals[<s:property value="#s.index" />].contAdditionalContent" maxlength="4000" altname="补充合同条款" >${contAdditionalContent}</textarea>
				</div>
			</td>
			<td click2expand="true">
				<div class="bis-read pd-chill-tip" title="${contAdditionalDesc}" >
					<div class="ellipsisDiv_full" >
					${contAdditionalDesc}
					</div>
				</div>
				<div class="bis-edit" style="display:none;" >
				<textarea style="width:95%; height:35px;" name="bisContAdditionals[<s:property value="#s.index" />].contAdditionalDesc" >${contAdditionalDesc}</textarea>
				</div>
			</td>
			<td>
				<s:if test="#canEdit=='true'">
					<a href="javascript:openAttachment('附件管理','${bisContAdditionalId}','bisContAddiAttachId_<s:property value="#s.index" />','attachFlg_<s:property value="#s.index" />');" >
						<img id='bisContAddiAttachId_<s:property value="#s.index" />' <s:if test='attachFlg==1'>src="${ctx}/resources/images/common/atta_y.gif"</s:if><s:else>src="${ctx}/resources/images/common/atta.gif"</s:else> />
					</a>
				</s:if>
				<s:else>
					<s:if test='attachFlg==1'>
					<a href="javascript:showAttachment('${bisContAdditionalId}');" >
						<img id='bisContAddiAttachId_<s:property value="#s.index" />' src="${ctx}/resources/images/common/atta_y.gif" />
					</a>
					</s:if>
				</s:else>
			</td>
			<td align="center">
			<s:if test="#canEdit=='true'">
			<a href="javascript:void(0)" onclick="delItemAdd(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
			</s:if>
			</td>
		</tr>
		</s:iterator>
	</tbody>
</table>
</div>

<script type="text/javascript">
	var trApproverAdd=$("#trConItemAdd").clone();
	$("#trConItemAdd").remove();
	var cloneCountAdd = 0;
	var indexAdd=${conItemCountAdd};
	
	function addItemAdd(){
		var trApproverAdd_new=trApproverAdd.clone();
		cloneCountAdd++;
		$(trApproverAdd_new).show();
		$(trApproverAdd_new).find(".inputContAddi").each(function(i){
			this.name=this.name.replace('0',indexAdd);
			this.id=this.id.replace('0',indexAdd);
		});
		$(trApproverAdd_new).find(":textarea").val('');
		bindClickAtta(trApproverAdd_new, indexAdd);
		$("#tbConItemAdd").append(trApproverAdd_new);
		indexAdd++;
	}
	function delItemAdd(dom){
		$(dom).parent().parent().remove();
	}
	
	function bindClickAtta(trApproverAdd_new, indexAdd) {
		$.post("${ctx}/bis/bis-cont!getAttachTmpId.action", function(result) {
			var domId='bisContAddiAttachId_'+indexAdd;
			var attachFlgId='attachFlg_'+indexAdd;
			$(trApproverAdd_new).find("a").bind("click", function() {
				openAttachment('附件管理',result,domId,attachFlgId);
			});
			$("#entityTmpId_"+indexAdd).val(result);
		});
	}
	
	bindTblEv('tbConItemAdd');
	
</script>
<s:if test="#canEdit!='true'">
<script type="text/javascript">
	$("#tbConItemAdd :input").attr("readonly","readonly");
	$("#tbConItemAdd :textarea").attr("readonly","readonly");
	$("#tbConItemAdd :input").filter(".Wdate").attr("onfocus","");
	$("#tbConItemAdd :input").filter(".Wdate").removeClass("Wdate");
</script>
</s:if>

