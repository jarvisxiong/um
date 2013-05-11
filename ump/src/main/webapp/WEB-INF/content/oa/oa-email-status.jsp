<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<div>
	<s:if test="readMailNum == 0">
		<div class="btn_green_55_20" style="float:left;margin:5 10px;" onclick="deleteMail('clear2','${id}',3);ymPrompt.close();" title="将删除，所有人将无法看到" >删除</div>
		<div title="编辑" style="float:left;margin:5 10px;" class="btn_green_55_20" onclick="readMail('','${id}',3,'edit');ymPrompt.close();">编辑</div>
	</s:if>
</div>
<div style="clear:both;">
    <table width="90%" cellpadding="0" cellspacing="0">
        <tr>
          <th align="center" width="100px">状态</th>
          <th align="center">收件人</th>
          <th align="center">操作</th>
        </tr>
		<s:if test="oaEmails4status.size()>0">
      	<s:iterator value="oaEmails4status">
      	<tr>
      	<s:if test="readFlg == 0">
       		<td align="center"><img src="${ctx}/pics/email/unRead.gif" title="收件人未读" ></td>
        </s:if>
        <s:if test="readFlg == 1" >
        	<td align="center"><img src="${ctx}/pics/email/read.gif" title="收件人已读"></td>
        </s:if>
        <s:if test="readFlg == 2" >
        	<td align="center"><img src="${ctx}/resources/images/email/del.gif" title="收件人已彻底删除"></td>
        </s:if>
          	<td align="center" class="cusorP"><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("userCd"))%></td>
      	<s:if test="readFlg == 0">
        	<td align="center"><img src="${ctx}/resources/images/email/del.gif" title="删除发给此人的" style="cursor:pointer;" onClick="deleteUserBySingle('${oaEmailBodyId}','${userCd}');"></td>
        </s:if>
        <s:else>
        	<td>&nbsp;</td>
        </s:else>
       	</tr>
      	</s:iterator>
		</s:if>
		<s:if test="oaEmails.size()==0">
			<tr><td colspan="2" align="center"><div class="noResult">所有收件人都已经将彻底删除！</div></td></tr>
		</s:if>
	</table>
</div>

