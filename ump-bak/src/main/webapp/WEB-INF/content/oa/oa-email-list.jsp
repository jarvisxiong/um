<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<div class="operBar">
	<div >
		<input id="oFormCheckAllBot" type="checkbox" onclick="checkedAll($(this).attr('checked'));" title="全选/不选　本页所有"/>
	</div>
	<div>
		<a class="mail_btn_del" onclick="deleteMails('remove',${boxId})" title="删除选中的，删除后可在【已删除】箱中查看">删除</a>
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
			<td width="40"><img src="${ctx}/resources/images/email/mail.gif"/></td>
			<td width="30">置顶</td>
			<td width="40">预览</td>
			<td width="90" onclick="setSortInfo('creator','${sort}');" style="cursor: pointer;" title="点击排序">
				<div style="float:left">发件人</div>
				<c:if test="${pageEmail.orderBy == 'creator'}">
					<c:if test="${pageEmail.order == 'desc'}">
					<div class="sort_desc"></div>
					</c:if>
					<c:if test="${pageEmail.order == 'asc'}">
					<div class="sort_asc"></div>
					</c:if>
				</c:if>
			</td>
			<td>主题</td>
			<td width="40">附件</td>
			<td width="150" onclick="setSortInfo('created_Date','${sort}');" style="background: none;cursor: pointer;" title="点击排序">
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
		</tr>
	</thead>
	<s:if test="pageEmail.result.size()>0">
      <tbody>
      <s:iterator value="pageEmail.result">
       <tr 
       <s:if test="readFlg == 0">
       class="mainTr unReadEmail" 
       </s:if>
       <s:if test="readFlg != 0">
       class="mainTr" 
       </s:if>
       id="info_${oaEmailBody.oaEmailBodyId}" isLoaded=false  oaEmailId="${oaEmailId}" oaEmailBodyId="${oaEmailBody.oaEmailBodyId}" boxId="${boxId}">
          <td align="left"><input type="checkbox" name="oaEmailIds" readFlag="${readFlg}" value="${oaEmailId}"/></td>
          <td nowrap="nowrap" click2expand=true>
          	<s:if test="readFlg == 1">
				<img src="${ctx}/resources/images/email/read.gif" title="已读">
			</s:if>
			<s:else>
				<img src="${ctx}/resources/images/email/unRead.gif" title="未读">
			</s:else>
          	<s:if test="replyFlg == 1">
				<img src="${ctx}/resources/images/email/replied.gif" title="已回复">
			</s:if>
          </td>
          <td align="left" onclick="attentionEmail('${oaEmailId}',this);">
	          <s:if test="attentionFlg == 1"><img title="点击取消置顶" attentionFlg="1" src="${ctx}/pics/email/attention.gif"/></s:if>
	          <s:else><img title="点击置顶显示" attentionFlg="0" src="${ctx}/pics/email/attention_cancel.gif"/></s:else>
          </td>
          <td click2expand=true>
          	<img alt="预览" title="预览" src="${ctx}/pics/email/preview_down.gif">
          </td>
          <% String emailUserName = CodeNameUtil.getUserNameByCd(JspUtil.findString("sender")); %>
          <td align="left"  click2open=true  title="<%=emailUserName %>" >
         	<div class="ellipsisDiv"> <%=emailUserName %></div>
          </td>
          <td align="left" click2open=true>
          	<div class="ellipsisDiv" style="float:left;">
	          	<s:if test="importLevelCd == 2">
		          	<span style="color:#FF6600">[重要]</span>
	          	</s:if>
	          	<s:elseif test="importLevelCd == 3">
		          	<span style="color:#FF0000">[非常重要]</span>
	          	</s:elseif>
	          	<span title="${subject}">
			          ${subject}
		        </span>
	        </div>
	        <div style="float:left">
	        	 <s:if test="readFlg == 0">
		          	<img title="新" class="newEmail" src="${ctx}/resources/images/common/new.gif" style="margin-top:5px;"/>
		          </s:if>
	        </div>
          </td>
          <td align="left" click2expand=true>
          	<s:if test="uploadFlg == 1">
          		<img  title="该含有附件" src="${ctx}/resources/images/common/atta_y.gif">
          	</s:if>
          	<s:if test="uploadFlg != 1">
          		&nbsp;
          	</s:if>
          </td>
          <td align="left" click2open=true title="<s:date name='createdDate' format='yyyy-MM-dd HH:mm EE'></s:date>">
          	<s:date name="createdDate" format="MM-dd(EE) HH:mm"></s:date>
          </td>
        </tr>
        <tr class="detailTr" id="detail_${oaEmailBody.oaEmailBodyId}" style="display: none;" title="双击关闭预览">
        	<td colspan="8">
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
	function attentionEmail(id,dom){
		var $img = $(dom).find("img");
		var attention = $img.attr("attentionFlg");
		if(attention == '1'){
			$img.attr("src","${ctx}/pics/email/attention_cancel.gif").attr("title","点击关置顶显示").attr("attentionFlg","0");
		}else{
			$img.attr("src","${ctx}/pics/email/attention.gif").attr("title","点击取消置顶").attr("attentionFlg","1");
		}
		$.post("${ctx}/oa/oa-email!attention.action",{oaEmailId:id},function(result){
		});
	}
</script>
