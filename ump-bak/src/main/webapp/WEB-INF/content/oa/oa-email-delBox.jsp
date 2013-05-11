<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<div class="operBar">
	<div >
		<input id="oFormCheckAllBot" type="checkbox" onclick="checkedAll($(this).attr('checked'));" title="全选/不选　本页所有"/>
	</div>
	<div>
		<a onclick="recoverMails(${boxId})" title="恢复">恢复</a>
	</div>
	<div>
		<a onclick="deleteMails('delete',${boxId})" title="彻底删除选中的，无法再进行恢复" class="mail_btn_del">永久删除</a>
	</div>
	<div>
		<a onclick="clearDelBox();" title="清空垃圾箱">清空垃圾箱</a>
	</div>
	<div style="float:right;background: none;">
		<form id="mailPageForm" action="${ctx}/oa/oa-email!list.action">
	    	<s:hidden name="boxId" />
	    	<s:hidden name="sort" id="sort"/>
	    	<p:page pageInfo="pageEmail"/>
	    </form>
	</div>
</div>
<div>
<table class="dataTable" id="mailBoxTable">
	<thead>
		<tr>
			<td width="30"></td>
			<td width="90" onclick="setSortInfo('creator','${sort}');" >发件人</td>
			<td width="">主题</td>
			<td width="40">附件</td>
			<td width="80" onclick="setSortInfo('created_Date','${sort}');" style="background: none;cursor: pointer;" title="点击排序">
				<div style="float:left">日期</div>
				<c:if test="${pageEmail.orderBy == 'creator'}">
					<c:if test="${pageEmail.order == 'desc'}">
					<div class="sort_desc"></div>
					</c:if>
					<c:if test="${pageEmail.order == 'asc'}">
					<div class="sort_asc"></div>
					</c:if>
				</c:if>
			</td>
		</tr>
	</thead>
	<s:if test="pageEmail.result.size()>0">
      <tbody>
      <s:iterator value="pageEmail.result">
      	<tr class="mainTr" id="info_${oaEmailBody.oaEmailBodyId}" isLoaded=false  oaEmailId="${oaEmailId}" oaEmailBodyId="${oaEmailBody.oaEmailBodyId}" boxId="${boxId}">
          <td align="left"><input type="checkbox" name="oaEmailIds" readFlag="${readFlg}" value="${oaEmailId}"/></td>
           <% String emailUserName = CodeNameUtil.getUserNameByCd(JspUtil.findString("sender")); %>
          <td click2open=true align="left" title="<%=emailUserName %>">
           <div class="ellipsisDiv"><%=emailUserName%></div>
          </td>
          <td align="left" click2open=true>
          	<div class="ellipsisDiv" style="float:left;">
          	<s:if test="importLevelCd == 2">
	          	<span style="color:#FF6600">[重要]</span>
          	</s:if>
          	<s:elseif test="importLevelCd == 3">
	          	<span style="color:#FF0000">[非常重要]</span>
          	</s:elseif>
          	<span title="${subject}">${subject}</span>
          </div>
           <div style="float:left">
	        	 <s:if test="readFlg == 0">
		          	<img src="${ctx}/resources/images/common/new.gif"/>
		          </s:if>
	        </div>
          </td>
          <td align="left" click2expand=true>
           &nbsp;
           <s:if test="uploadFlg == 1">
           		<img  title="该含有附件" src="${ctx}/resources/images/common/atta_y.gif">
           </s:if>
          </td>
          <td align="left" click2open=true title="<s:date name='createdDate' format='yyyy-MM-dd HH:mm'></s:date>">
          	<s:date name="createdDate" format="MM-dd HH:mm"></s:date>
          </td>
        </tr>
        <tr class="detailTr" id="detail_${oaEmailBody.oaEmailBodyId}" style="display: none;" title="双击关闭预览">
        	<td colspan="5">
        	</td>
        </tr>
      </s:iterator>
      </tbody>
	</s:if>
</table>	
<s:if test="pageEmail.result.size()==0">
	<div class="noResult">没有相关记录！</div>
</s:if>
</div>


<script language="javascript">
	function recoverMails(boxId){
		var checkbox_ids = new Array();
		$("input[name='oaEmailIds']:checked").each(function(i, dom) {
			checkbox_ids.push("oaEmailIds=" + $(dom).val());
		});
		if(checkbox_ids.length == 0){
			alert("请勾选需要恢复的");
			return false;
		}
		checkbox_ids.push("boxId="+boxId);
		var param = checkbox_ids.join("&");
		ajaxDiv("${ctx}/oa/oa-email!recover.action",param,"mailRight",setNum2LeftBar);
	}
</script>

