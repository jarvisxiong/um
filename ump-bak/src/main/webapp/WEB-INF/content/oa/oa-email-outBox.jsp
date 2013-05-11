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
		<a class="mail_btn_del" onclick="deleteMails('delete',${boxId})" title="从发件箱中删除">删除</a>
	</div>
	<div>
		<a class="mail_btn_read" onclick="markMails()" title="将选中的标记为已读">标记为已读</a>
	</div>
	<div style="float:right;background: none;">
		<form id="mailPageForm" action="${ctx}/oa/oa-email!list.action">
	    	<s:hidden name="boxId" />
	    	<s:hidden name="sort" id="sort"/>
	    	<s:hidden name="pageEmail.pageSize"/>
	    	<p:page pageInfo="pageEmail"/>
	    </form>
	</div>
</div>
<div>
<table class="dataTable" id="mailBoxTable">
	<thead>
		<tr>
			<td width="30"></td>
			<td width="35">状态</td>
			<td width="60">收件人</td>
			<td width="">主题</td>
			<td width="40">附件</td>
			<td width="80" onclick="setSortInfo('created_Date','${sort}');" style="cursor: pointer;" title="点击排序">
				<div style="float:left">日期</div>
				<c:if test="${pageEmail.orderBy == 'createdDate'}">
					<c:if test="${pageEmail.order == 'desc'}">
					<div class="sort_desc"></div>
					</c:if>
					<c:if test="${pageEmail.order == 'asc'}">
					<div class="sort_asc"></div>
					</c:if>
				</c:if>
			</td>
			<td width="90" style="background: none;">操作</td>
		</tr>
	</thead>
	<s:if test="pageEmail.result.size()>0">
    	<tbody>
     		<s:iterator value="pageEmail.result">
     			<tr class="mainTr" id="info_${oaEmailBody.oaEmailBodyId}" isLoaded=false  oaEmailId="${oaEmailId}" oaEmailBodyId="${oaEmailBody.oaEmailBodyId}" boxId="${boxId}">
          			<td align="left"><input type="checkbox" name="oaEmailIds" readFlag="${readFlg}" value="${oaEmailId}"/></td>
          			
          			          			<s:if test="oaEmailBody.oaEmails.size() == 2">
			         	 <s:iterator value="oaEmailBody.oaEmails">
			          		<s:if test="userTypeCd != 0">	
			          			<s:if test="readFlg == 0">
						          	<td align="left"><img src="${ctx}/resources/images/email/unRead.gif" title="收件人未读" ></td>
			          			</s:if>
			          			<s:if test="readFlg == 1">
						          	<td align="left"><img src="${ctx}/resources/images/email/read.gif" title="收件人已读"></td>
			          			</s:if>
				          	<td align="left" >
				          		<div class="ellipsisDiv"><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("userCd"))%></div>
				          	</td>
			          		</s:if>	
	          			</s:iterator>
          		  </s:if>
		          <s:elseif test="oaEmailBody.oaEmails.size() > 2">
			          <td align="left" onclick="showMailStatus('${oaEmailBody.oaEmailBodyId}')">查看</td>
			          <td align="left" onclick="showMailStatus('${oaEmailBody.oaEmailBodyId}')">查看</td>
		          </s:elseif>
		          <s:else>
		          	<td colspan="2" title="所有收件人已经将该彻底删除"><del>已删除</del></td>
		          </s:else>
	            <td align="left" click2open=true>
	          		<div class="ellipsisDiv" style="float:left;" title="${subject}">${subject}</div>
	            </td>
	          <td align="left" click2expand=true>
	          <s:if test="uploadFlg == 1">
	           	<img title="该含有附件" src="${ctx}/resources/images/common/atta_y.gif">
	          </s:if>
	          <s:if test="uploadFlg != 1">
	          	&nbsp;
	          </s:if>
	          </td>
	          <td align="left" click2open=true title="<s:date name='updatedDate' format='yyyy-MM-dd HH:mm'></s:date>">
	          	<s:date name="updatedDate" format="MM-dd HH:mm"></s:date>
	          </td>
	          <td align="left">
				<s:if test="oaEmailBody.oaEmails.size() == 2">
			         	 <s:iterator value="oaEmailBody.oaEmails">
          			<s:if test="readFlg == 0">
          				<span onclick="readMail('${oaEmailId}','${oaEmailBody.oaEmailBodyId}',2,'edit');">编辑</span>
					</s:if>
					</s:iterator>
	          	</s:if>
	          	<%-- hidden by huangbj 2011-10-19
	        		<span onclick="readMail('${oaEmailId}','${oaEmailBody.oaEmailBodyId}',2,'resend');">再次发送</span>
	          	 --%>
	          </td>
       	</tr>
        <tr class="detailTr" id="detail_${oaEmailBody.oaEmailBodyId}" style="display: none;" title="双击关闭预览">
        	<td colspan="7">
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
<script type="text/javascript">
	function showMailStatus(id){
		try{
			ymPrompt.close();
		}catch(e){}
		ymPrompt.win({
			title:"阅读状态",
			message:"<div id='userMailStatusDiv'><img align='absMiddle' src='${ctx}/images/loading.gif'></div>",
			width:250,
			height:350,
			winPos:"t",
			allowRightMenu:true,
			showMask:false,
			afterShow:function(){
			$.post("${ctx}/oa/oa-email!status.action",{id:id},function(result){
				$("#userMailStatusDiv").html(result);
			});
		}
		})
	}
</script>
