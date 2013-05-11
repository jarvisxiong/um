<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<form id="mailForm" method="post" action="">
  		<div class="mailBoxTitle">
     		 <b>收件箱</b> &nbsp;&nbsp;
     		 (共&nbsp;<b id="oTotal">25</b>&nbsp;封，其中&nbsp;
     		 <a title="" href="#">未读邮件</a>&nbsp;
     		 <b>0</b>&nbsp;封)
     	</div> 
      <div>
        <table id="mailBoxTable" class="commonTable" style="width:99%">
          <thead>
            <tr>
              <th width="15px"></th>
              <th width="30px"><input id="oFormCheckAll" type="checkbox" title="全选/不选　本页所有邮件"/></th>
              <th width="150px">发件人</th>
              <th >主题</th>
              <th width="40px">附件</th>
              <th width="150px">日期</th>
            </tr>
          </thead>
          <tbody>
            <tr>
            	<td ><div class="mailJia">&nbsp;</div></td>
            	<td colspan="5">日期：今天</td>
            </tr>
            <tr>
              <td></td>
              <td align="center"><input type="checkbox"/></td>
              <td>发件人1</td>
              <td>主题1</td>
              <td></td>
              <td>日期1</td>
            </tr>
            <tr>
              <td></td>
              <td align="center"><input type="checkbox" /></td>
              <td>发件人2</td>
              <td>主题2</td>
              <td></td>
              <td>日期2</td>
            </tr>
             <tr>
             	<td><div class="mailJia">&nbsp;</div></td>
            	<td colspan="5">日期：昨天</td>
            </tr>
            <tr>
              <td></td>
              <td align="center"><input type="checkbox"/></td>
              <td>发件人3</td>
              <td>主题3</td>
              <td></td>
              <td>日期3</td>
            </tr>
             <tr>
             	<td><div class="mailJia">&nbsp;</div></td>
            	<td colspan="5">日期：更久之前</td>
            </tr>
            <tr>
              <td></td>
              <td align="center"><input type="checkbox"/></td>
              <td>发件人4</td>
              <td>主题5</td>
              <td></td>
              <td>日期5</td>
            </tr>
          </tbody>
          <tfoot>
          	<tr>
	          	<td colspan="6">
		          	选择：
			          	<a href="javascript:void(0)" type="all">全部</a>&nbsp;-&nbsp;
			          	<a href="javascript:void(0)" type="unread">未读</a>&nbsp;-&nbsp;
			          	<a href="javascript:void(0)" type="read">已读</a>&nbsp;-&nbsp;
			          	<a href="javascript:void(0)" type="reverse">反选</a>&nbsp;-&nbsp;
			          	<a href="javascript:void(0)" type="no">不选</a>
	          	</td>
          	</tr>
          </tfoot>
        </table>
    </div>
</form>