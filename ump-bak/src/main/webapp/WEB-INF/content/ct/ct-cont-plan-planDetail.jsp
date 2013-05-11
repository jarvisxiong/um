<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<form id="ctContAdd" action="${ctx}/ct/ct-cont-plan!contSave.action" method="post">
			<input type="hidden" name="ctPlan.ctPlanId" value="${ctPlanId}">
	  		 <table class="shop-table" style="min-width: 350px;"  >
	          <col width="120" style="text-align: right;"/>
		      <col />
			<tr>
			  	<td><font style="color:red;">*</font>标段划分：</td>
		      	<td>
		      	<div   style="position:relative;"> 
			      	<span   style="margin-left:190px;width:18px;overflow:hidden;"> 
			      	<select id="entity.ledgerTypeCd"  style="width:208px;margin-left:-190px;height: 80%;padding-top:5px;"   onchange="doChange(this);" >
				   	 <option value="">请选择</option>
					 <option value="前期临时工程">前期临时工程</option>
					 <option value="独立分包">独立分包</option>
					 <option value="总包招标一体化、总承包单位直接负责工作">总包招标一体化、总承包单位直接负责工作</option>
					 <option value="总包招标一体化、总承包参与管理、协调、配合（三方合同）">总包招标一体化、总承包参与管理、协调、配合（三方合同）</option>
					 <option value="独立供应">独立供应</option>
					 <option value="独立承包">独立承包</option>
				   </select>
				   </span>
				   <input   name="entity.ledgerTypeCd"   style="width:190px;height: 80%;position:absolute;left:0px;padding-top:5px;">
				</div>
		      	</td>
			</tr>
			<tr>
			   <td><font style="color:red;">*</font>合同主体：</td>
			   <td>
				   <select  name="entity.contMainCd" style="width: 208px;height: 80%" id="contMainCd" value="${entity.contMainCd}" validate="required">
				   	 <option value="">请选择</option>
					 <option value="总包">总包</option>
					 <option value="指定分包">指定分包</option>
					 <option value="独立承包">独立承包</option>
					 <option value="材料供应">材料供应</option>
					 <option value="材料供应+安装">材料供应+安装</option>
					 <option value="设备供应">设备供应</option>
					 <option value="设备供应+安装">设备供应+安装</option>
				   </select>
			   </td>
			</tr>
			<tr>
			   <td><font style="color:red;">*</font>合同内容：</td>
		       <td rowspan="2"><textarea id="entity.contContentDesc" value="${entity.contContentDesc}" style="width:208px;height:60px;background-color: #DBE5F1;border: none;" name="entity.contContentDesc">${entity.contContentDesc}</textarea></td>
			 </tr>
			 <tr></tr>
			 <tr></tr>
			 <tr>
			   <td>合同属性：</td>
		       <td>
		       <div   style="position:relative;"> 
		       <span   style="margin-left:190px;width:18px;overflow:hidden;"> 
			      	<select id="entity.contPropCd" style="width:208px;margin-left:-190px;height: 80%;padding-top:5px;"    onchange="doChange(this);" validate="required" >
				   	 <option value="">请选择</option>
					 <option value="两方合同">两方合同</option>
					 <option value="三方合同">三方合同</option>
					 <option value="两方供货合同、三方安装合同">两方供货合同、三方安装合同</option>
					 <option value="总价包干">总价包干</option>
					 <option value="单价包干">单价包干</option>
					 <option value="单价合同">单价合同</option>
				   </select>
				</span>
				<input   name="entity.contPropCd"   style="width:190px;height: 80%;position:absolute;left:0px;padding-top:5px;">
				</div>
		      	</td>
			 </tr>
			 <tr>
			   <td>合同界面简要：</td>
		       <td>
		       <div   style="position:relative;"> 
		       <span   style="margin-left:190px;width:18px;overflow:hidden;"> 
			      	<select id="entity.contFaceCd"  style="width:208px;margin-left:-190px;height: 80%;padding-top:5px;"    onchange="doChange(this);"   validate="required" >
				   	 <option value="">请选择</option>
					 <option value="桩基施工图内所有项目">桩基施工图内所有项目</option>
					 <option value="围护设计施工图">围护设计施工图</option>
					 <option value="建筑施工图范围内所有单体建筑物、构筑物的施工">建筑施工图范围内所有单体建筑物、构筑物的施工</option>
					 <option value="市政雨污水井前所有管线">市政雨污水井前所有管线</option>
					 <option value="基本措施费规定之内容">基本措施费规定之内容</option>
					 <option value="施工图内所有项目">施工图内所有项目</option>
					 <option value="泛光照明施工图">泛光照明施工图</option>
				   </select>
				</span>
			      	<input   name="entity.contFaceCd"   style="width:190px;height: 80%;position:absolute;left:0px;padding-top:5px;">
			    </div>
		       </td>
			 </tr>
			 <tr></tr>
			 <tr>
		  	   <td>招标前置条件：</td>
		       <td>
		       <div   style="position:relative;"> 
		       <span   style="margin-left:190px;width:18px;overflow:hidden;"> 
			      	<select id="entity.contInviPreCd"  style="width:208px;margin-left:-190px;height: 80%;padding-top:5px;"   onchange="doChange(this);" validate="required" >
				   	 <option value="">请选择</option>
					 <option value="方案、总平图确定">方案、总平图确定</option>
					 <option value="桩型已选定，工程量基本确定">桩型已选定，工程量基本确定</option>
					 <option value="桩型已选定，底板标高已确定，地下室范围已确定，可以进行支护设计/扩初完成">桩型已选定，底板标高已确定，地下室范围已确定，可以进行支护设计/扩初完成</option>
					 <option value="材料选型选样完成，施工图已完成">材料选型选样完成，施工图已完成</option>
					 <option value="门窗铝型材、规格、玻璃已确定，若涉及深度不够可指定分包">门窗铝型材、规格、玻璃已确定，若涉及深度不够可指定分包</option>
					 <option value="施工图已完成">施工图已完成</option>
					 <option value="智能化管线预埋图/按照图纸常规时间，雨污水出图比主体晚半个月，采用模拟清单招标">智能化管线预埋图/按照图纸常规时间，雨污水出图比主体晚半个月，采用模拟清单招标</option>
					 <option value="钢筋数量、型号确定">钢筋数量、型号确定</option>
					 <option value="施工图已完成，如标准低并与总包合同">施工图已完成，如标准低并与总包合同</option>
					 <option value="施工图已完成，如设计不能同时出图，建议分2个标段招标">施工图已完成，如设计不能同时出图，建议分2个标段招标</option>
					 <option value="进场时间，电梯的型号、规格">进场时间，电梯的型号、规格</option>
					 <option value="方案+技术要求">方案+技术要求</option>
					 <option value="尺寸、材质、规格、型号">尺寸、材质、规格、型号</option>
					 <option value="土建结构施工完毕">土建结构施工完毕</option>
					 <option value="材料的型号、规格、数量、尺寸等相关技术参数">材料的型号、规格、数量、尺寸等相关技术参数</option>
					 <option value="设备的型号、数量、等相关技术指标">设备的型号、数量、等相关技术指标</option>
					 <option value="招标图+技术规格书">招标图+技术规格书</option>
				   </select>
			 </span>
			 <input   name="entity.contInviPreCd"   style="width:190px;height: 80%;position:absolute;left:0px;padding-top:5px;">
			 </div>
		       </td>
			 </tr>
			 <tr>
			   <td>施工面积(㎡)：</td>
			   <td><input type="text"  style="width:208px;height: 80%;padding-top:5px;" name="entity.consructArea" alt="amount"  value="${entity.consructArea}" maxlength="9"/></td>
			 </tr>
			  <tr>
			   <td rowspan="2">备注：</td>
		       <td rowspan="2"><textarea id="remark" value="${entity.remark}" style="width:208px;height:60px;background-color: #DBE5F1;border: none;" name="entity.remark"></textarea></td>
			 </tr>
		 </table>
		
</form>
<script type="text/javascript">
function doChange(ele){
 var _id=$(ele).attr("id");
 var _val=$(ele).val();
 $("input[name="+_id+"]").val(_val);
	
}
/*
function selectLedgerTypeShow(){
	$('#selLedgerTypeCd').show();
	$('#ledgerTypeCd').hide();
}
function selectLedgerType(){
	$('#ledgerTypeCd').val($('#selLedgerTypeCd').val());
	$('#selLedgerTypeCd').hide();
	$('#ledgerTypeCd').show();
}
function selectContPropCdShow(){
	$('#selectContPropCd').show();
	$('#contPropCd').hide();
}
function selectContProp(){
	$('#contPropCd').val($('#selectContPropCd').val());
	$('#selectContPropCd').hide();
	$('#contPropCd').show();
}
function selectContFaceCdShow(){
	$('#selectContFaceCd').show();
	$('#contFaceCd').hide();
}
function selectContFace(){
	$('#contFaceCd').val($('#selectContFaceCd').val());
	$('#selectContFaceCd').hide();
	$('#contFaceCd').show();
}
function selectContInviPreCdShow(){
	$('#selectContInviPreCd').show();
	$('#contInviPreCd').hide();
}
function selectContInviPre(){
	$('#contInviPreCd').val($('#selectContInviPreCd').val());
	$('#selectContInviPreCd').hide();
	$('#contInviPreCd').show();
}
*/
</script>