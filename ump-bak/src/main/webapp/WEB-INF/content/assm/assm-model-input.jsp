<%@page
	import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>
<%@ include file="/common/global.jsp"%>

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.DictContants"%>
<script type="text/javascript">
var _ctx = '${ctx}'; 
</script>
<style>
.inpWidth{
	width: 160px;
	}
</style>
<div >
	<form>
		<input type="hidden" name="assmModelId" id="upd_assmModelId" value="${entity.assmModelId}"/>
		<table class="newTableForm" style="margin-top: 10px;float: left;">
			<colgroup>
			<col width="110px;"></col>
			<col></col>
			</colgroup>
			<tr style="line-height: 30px;">
				<td align="right" style="padding-right: 10px;">
					<label>设备名称：</label>
				</td>
				<td>
					<input type="text" name="assmName" id="upd_assmName" class="inpWidth" style="width: 200px;" value="${entity.assmName}"></input>
				</td>			
			</tr>
			<tr style="line-height: 30px;">
				<td align="right" style="padding-right: 10px;">
					<label>编&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
				</td>
				<td>
					<input type="text" name="assmCode" id="upd_assmCode" class="inpWidth" style="width: 200px;" value="${entity.assmCode}"></input>
				</td>			
			</tr>
			<tr style="line-height: 30px;">
				<td align="right" style="padding-right: 10px;">
					<label>专业编码：</label>
				</td>
				<td>
					<input type="text" name="proCode" id="upd_proCode" class="inpWidth" style="width: 200px;" value="${entity.proCode}"></input>
				</td>			
			</tr>
			<tr style="line-height: 30px;">
				<td align="right" style="padding-right: 10px;">
					<label>长&nbsp;编&nbsp;码：</label>
				</td>
				<td>
					<input type="text" name="fullCode" id="upd_fullCode" class="inpWidth" style="width: 200px;" value="${entity.fullCode}" ></input>
				</td>			
			</tr>
			<tr style="line-height: 30px;">
				<td align="right" style="padding-right: 10px;">
					<label>级&nbsp;&nbsp;&nbsp;&nbsp;次：</label>
				</td>
				<td>
					<input type="text" name="assmLevel" class="inpWidth" value="${entity.assmLevel}" style="width: 200px;" disabled="disabled"  style="background-color: #EDEFF3;"></input>
				</td>			
			</tr>
			<tr style="line-height: 30px;">
				<td>&nbsp;</td>
				<td id="body">
					<div class="btns clearfix" style="margin-top: 10px;">
						<button type="button" class="green min" onclick="updateModel();">保存</button>
						<button type="button" class="red min" onclick="ymPromptclose();">取消</button>
					</div>
				</td>
			</tr>
		</table>
	</form>
</div>


<script type="text/javascript">
var data ;
$(function(){
	
});

//关闭修改框
function ymPromptclose(){
	ymPrompt.close();
}
</script>