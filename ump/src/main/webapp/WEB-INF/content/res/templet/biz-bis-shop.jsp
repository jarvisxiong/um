<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-header.jsp"%>
<%-- 商业商家资源入库 --%>
<div align="center" class="billContent">
<s:set var="canEdit">
			<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
			true
			</s:if>
			<s:else>
			false
			</s:else>
			</s:set>
<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
	<%@ include file="template-var.jsp"%>
	<s:set var="conItemCount"><s:property value="templateBean.bizBisShopConns.size()"/></s:set>
	<div  id="bisShopDiv">
	<table  class="mainTable" id="bisShopTable">
		<col width="100px"/>
		<col width="120px"/>
		<col/>
		<col width="120px"/>
		<col/>
		<tr>
		   <td class="td_title" rowspan="3" >品牌资料</td>
		   <td class="td_title">品牌名(中)</td>
		   <td><input validate="required" class="inputBorder" type="text" name="templateBean.nameCn" value="${templateBean.nameCn}"/></td>
		   <td class="td_title">品牌名(英)</td>
		   <td><input validate="required" class="inputBorder" type="text" name="templateBean.nameEn" value="${templateBean.nameEn}"/></td>
		</tr>
		<tr>
		   <td class="td_title">经营性质</td>
		   <td>
		    <table class="tb_checkbox" validate="required">
				<tr>
				<td><s:checkbox name="templateBean.manageCd1"  cssClass="group"></s:checkbox>主力店&nbsp;</td>
				<td><s:checkbox name="templateBean.manageCd2"  cssClass="group"></s:checkbox>次主力店&nbsp;</td>
				<%--<td><s:checkbox name="templateBean.manageCd3"  cssClass="group"></s:checkbox>小商铺</td> --%>
				</tr>
			</table>
		   </td>
		   <td class="td_title">品牌简介 	</td>
		   <td><input class="inputBorder" type="text" name="templateBean.brandDesc" value="${templateBean.brandDesc}"/></td>
		</tr>
		<tr>
		  <td class="td_title">商家类别</td>
		  <td colspan="3"><input validate="required" class="inputBorder" type="text" id="bisSortName" name="templateBean.bisSortName" value="${templateBean.bisSortName}" onclick="selectBisShop();"/>
		  <input type="hidden" name="templateBean.bisSortId" id="bisSortId" value="${templateBean.bisSortId}" />
		  </td>
		</tr>
		<tr>
		   <td class="td_title" rowspan="4" >公司信息</td>
		   <td class="td_title">公司名称</td>
		   <td colspan="3"><input class="inputBorder" type="text" name="templateBean.companyName" value="${templateBean.companyName}"/></td>
		</tr>
		<tr>
		   <td class="td_title">创建人</td>
		   <td><input class="inputBorder" type="text" name="templateBean.founderPeople" value="${templateBean.founderPeople}"/></td>
		   <td class="td_title">创建时间 	</td>
		   <td><input class="inputBorder" type="text" name="templateBean.founderDate" value="${templateBean.founderDate}" onfocus="WdatePicker()"/></td>
		</tr>
		<tr>
		   <td class="td_title">产地</td>
		   <td><input validate="required" class="inputBorder" type="text" name="templateBean.produceAddr" value="${templateBean.produceAddr}"/></td>
		   <td class="td_title">注册地</td>
		   <td><input validate="required" class="inputBorder" type="text" name="templateBean.registerAddr" value="${templateBean.registerAddr}"/></td>
		</tr>
		<tr>
		   <td class="td_title">品牌发源地</td>
		   <td><input validate="required" class="inputBorder" type="text" name="templateBean.brandCradleland" value="${templateBean.brandCradleland}"/></td>
		   <td class="td_title">网站</td>
		   <td><input class="inputBorder" type="text" name="templateBean.webSite" value="${templateBean.webSite}"/></td>
		</tr>
	</table>
	<s:iterator value="templateBean.bizBisShopConns" var="item" status="s">
	 <table  class="mainTable" style="margin-top:5px;">
	    <col width="120px"/>
		<col width="150"/>
		<col width="100px"/>
		<col/>
	  <tr>
	   <td class="td_title">代理商/供应商名称</td>
	   <td colspan="3"><input class="inputBorder" validate="required" type="text" style="width:95%;" name="templateBean.bizBisShopConns[<s:property value="#s.index" />].partName" value="${item.partName}"/>
	   <s:if test="#canEdit=='true'">
	   <a href="javascript:void(0)" onclick="delItem(this)"><img title="删除" src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
	   </s:if>
	  </td>
	  </tr>
	  <tr>
	   <td class="td_title">代理商/供应商性质</td>
	   <td colspan="3">
	     <table class="tb_checkbox">
			<tr>
			<td><s:checkbox name="templateBean.bizBisShopConns[%{#s.index}].supplierAttributeCd1"  cssClass="group"></s:checkbox>直营&nbsp;</td>
			<td><s:checkbox name="templateBean.bizBisShopConns[%{#s.index}].supplierAttributeCd2"  cssClass="group"></s:checkbox>全国总代理&nbsp;</td>
			<td><s:checkbox name="templateBean.bizBisShopConns[%{#s.index}].supplierAttributeCd3"  cssClass="group"></s:checkbox>区域代理&nbsp;</td>
			<td><s:checkbox name="templateBean.bizBisShopConns[%{#s.index}].supplierAttributeCd4"  cssClass="group"></s:checkbox>省代理&nbsp;</td>
			<td><s:checkbox name="templateBean.bizBisShopConns[%{#s.index}].supplierAttributeCd5"  cssClass="group"></s:checkbox>市代理&nbsp;</td>
			<td><s:checkbox name="templateBean.bizBisShopConns[%{#s.index}].supplierAttributeCd6"  cssClass="group"></s:checkbox>加盟&nbsp;</td>
			<td><s:checkbox name="templateBean.bizBisShopConns[%{#s.index}].supplierAttributeCd7"  cssClass="group"></s:checkbox>个体自营&nbsp;</td>
			</tr>
		</table>
	   </td>
	  </tr>
	  <tr>
	   <td class="td_title">公司地址</td>
	   <td><input class="inputBorder" type="text" name="templateBean.bizBisShopConns[<s:property value="#s.index" />].partAddress" value="${item.partAddress}"/></td>
	   <td class="td_title">公司电话</td>
	   <td><input class="inputBorder" type="text" name="templateBean.bizBisShopConns[<s:property value="#s.index" />].principalPhone" value="${item.principalPhone}"/></td>
	  </tr>
	  <tr>
	   <td class="td_title">手机</td>
	   <td><input class="inputBorder" type="text" name="templateBean.bizBisShopConns[<s:property value="#s.index" />].principalTel" value="${item.principalTel}"/></td>
	   <td class="td_title">联络人</td>
	   <td><input class="inputBorder" type="text" name="templateBean.bizBisShopConns[<s:property value="#s.index" />].principal" value="${item.principal}"/></td>
	  </tr>
	  <tr>
	   <td class="td_title">职务</td>
	   <td><input class="inputBorder" type="text" name="templateBean.bizBisShopConns[<s:property value="#s.index" />].principalPos" value="${item.principalPos}"/></td>
	   <td class="td_title">传真</td>
	   <td><input class="inputBorder" type="text" name="templateBean.bizBisShopConns[<s:property value="#s.index" />].fax" value="${item.fax}"/></td>
	  </tr>
	  <tr>
	   <td class="td_title">邮编</td>
	   <td colspan="3"><input class="inputBorder" type="text" name="templateBean.bizBisShopConns[<s:property value="#s.index" />].postCode" value="${item.postCode}"/></td>
	  </tr>
	  <tr>
	   <td class="td_title">已合作门店</td>
	   <td><input class="inputBorder" type="text" name="templateBean.bizBisShopConns[<s:property value="#s.index" />].cooperatedShop" value="${item.cooperatedShop}"/></td>
	   <td class="td_title">可合作门店</td>
	   <td><input class="inputBorder" type="text" name="templateBean.bizBisShopConns[<s:property value="#s.index" />].toCooperateShop" value="${item.toCooperateShop}"/></td>
	  </tr>
	  <tr>
	   <td class="td_title">经营区域/城市</td>
	   <td colspan="3"><input class="inputBorder" type="text" name="templateBean.bizBisShopConns[<s:property value="#s.index" />].manageCity" value="${item.manageCity}"/></td>
	  </tr>
	  <tr>
	   <td class="td_title">主要合作条件</td>
	   <td colspan="3"><input class="inputBorder" type="text" name="templateBean.bizBisShopConns[<s:property value="#s.index" />].conditionForCooperation" value="${item.conditionForCooperation}"/></td>
	  </tr>
	  <tr>
	   <td class="td_title">备注</td>
	   <td colspan="3"><input class="inputBorder" type="text" name="templateBean.bizBisShopConns[<s:property value="#s.index" />].remark" value="${item.remark}"/></td>
	  </tr>
	</table>
	</s:iterator>
	</div>
	<table  class="mainTable conn" style="display: none;" id="connTable" style="margin-top:5px;">
	    <col width="120px"/>
		<col/>
		<col width="100px"/>
		<col/>
	  <tr>
	   <td class="td_title">代理商/供应商名称</td>
	   <td colspan="3"><input class="inputBorder" validate="required" style="width:95%;" type="text" name="templateBean.bizBisShopConns[0].partName" value="${templateBean.bizBisShopConns[0].partName}"/>
	   <a href="javascript:void(0)" onclick="delItem(this)"><img title="删除" src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
	   </td>
	  </tr>
	  <tr>
	   <td class="td_title">代理商/供应商性质</td>
	   <td colspan="3">
	     <table class="tb_checkbox">
			<tr>
			<td><s:checkbox name="templateBean.bizBisShopConns[0].supplierAttributeCd1"  cssClass="group"></s:checkbox>直营&nbsp;</td>
			<td><s:checkbox name="templateBean.bizBisShopConns[0].supplierAttributeCd2"  cssClass="group"></s:checkbox>全国总代理&nbsp;</td>
			<td><s:checkbox name="templateBean.bizBisShopConns[0].supplierAttributeCd3"  cssClass="group"></s:checkbox>区域代理&nbsp;</td>
			<td><s:checkbox name="templateBean.bizBisShopConns[0].supplierAttributeCd4"  cssClass="group"></s:checkbox>省代理&nbsp;</td>
			<td><s:checkbox name="templateBean.bizBisShopConns[0].supplierAttributeCd5"  cssClass="group"></s:checkbox>市代理&nbsp;</td>
			<td><s:checkbox name="templateBean.bizBisShopConns[0].supplierAttributeCd6"  cssClass="group"></s:checkbox>加盟&nbsp;</td>
			<td><s:checkbox name="templateBean.bizBisShopConns[0].supplierAttributeCd7"  cssClass="group"></s:checkbox>个体自营&nbsp;</td>
			</tr>
		</table>
	   </td>
	  </tr>
	  <tr>
	   <td class="td_title">公司地址</td>
	   <td><input class="inputBorder" type="text" name="templateBean.bizBisShopConns[0].partAddress" value="${templateBean.bizBisShopConns[0].partAddress}"/></td>
	   <td class="td_title">公司电话</td>
	   <td><input class="inputBorder" type="text" name="templateBean.bizBisShopConns[0].principalPhone" value="${templateBean.bizBisShopConns[0].principalPhone}"/></td>
	  </tr>
	  <tr>
	   <td class="td_title">手机</td>
	   <td><input class="inputBorder" type="text" name="templateBean.bizBisShopConns[0].principalTel" value="${templateBean.bizBisShopConns[0].principalTel}"/></td>
	   <td class="td_title">联络人</td>
	   <td><input class="inputBorder" type="text" name="templateBean.bizBisShopConns[0].principal" value="${templateBean.bizBisShopConns[0].principal}"/></td>
	  </tr>
	  <tr>
	   <td class="td_title">职务</td>
	   <td><input class="inputBorder" type="text" name="templateBean.bizBisShopConns[0].principalPos" value="${templateBean.bizBisShopConns[0].principalPos}"/></td>
	   <td class="td_title">传真</td>
	   <td><input class="inputBorder" type="text" name="templateBean.bizBisShopConns[0].fax" value="${templateBean.bizBisShopConns[0].fax}"/></td>
	  </tr>
	  <tr>
	   <td class="td_title">邮编</td>
	   <td colspan="3"><input class="inputBorder" type="text" name="templateBean.bizBisShopConns[0].postCode" value="${templateBean.bizBisShopConns[0].postCode}"/></td>
	  </tr>
	  <tr>
	   <td class="td_title">已合作门店</td>
	   <td><input class="inputBorder" type="text" name="templateBean.bizBisShopConns[0].cooperatedShop" value="${templateBean.bizBisShopConns[0].cooperatedShop}"/></td>
	   <td class="td_title">可合作门店</td>
	   <td><input class="inputBorder" type="text" name="templateBean.bizBisShopConns[0].toCooperateShop" value="${templateBean.bizBisShopConns[0].toCooperateShop}"/></td>
	  </tr>
	  <tr>
	   <td class="td_title">经营区域/城市</td>
	   <td colspan="3"><input class="inputBorder" type="text" name="templateBean.bizBisShopConns[0].manageCity" value="${templateBean.bizBisShopConns[0].manageCity}"/></td>
	  </tr>
	  <tr>
	   <td class="td_title">主要合作条件</td>
	   <td colspan="3"><input class="inputBorder" type="text" name="templateBean.bizBisShopConns[0].conditionForCooperation" value="${templateBean.bizBisShopConns[0].conditionForCooperation}"/></td>
	  </tr>
	  <tr>
	   <td class="td_title">备注</td>
	   <td colspan="3"><input class="inputBorder" type="text" name="templateBean.bizBisShopConns[0].remark" value="${templateBean.bizBisShopConns[0].remark}"/></td>
	  </tr>
	</table>
	<s:if test="#canEdit=='true'">
	  <input type="button"  class="btn_blue_75_55" value="增加经销商" onclick="addItem();" />
	</s:if>
	<%@ include file="template-approver.jsp"%>
</form>
</div>
<%@ include file="template-footer.jsp"%>
<script type="text/javascript">
var trApprover=$("#connTable").clone();
$("#connTable").remove();
var cloneCount = 0;
var index=${conItemCount};

function addItem(){
	var trApprover_new=trApprover.clone();
	cloneCount++;
	$(trApprover_new).show();
	$(trApprover_new).find(".inputBorder").each(function(i){
		if (this.name){
			this.name=this.name.replace('0',index);
		}
		if (this.id){
			this.id=this.id+index;
		}
	});
	$(trApprover_new).find(".group").each(function(i){
		if (this.name){
			this.name=this.name.replace('0',index);
		}
		if (this.id){
			this.id=this.id+index;
		}
	});
	$(trApprover_new).attr("id","connTable"+index);
	//$(trApprover_new).css("width",$("#bisShopTable").width()-5);
	$("#bisShopDiv").append(trApprover_new);
	index++;
}
function delItem(dom){
	$(dom).parent().parent().parent().remove();
}
function selectBisShop(){
	var selectId ="";
	var selectName ="";
	ymPrompt.confirmInfo({
		icoCls:"",
		title:"商家类别",
		message:"<div id='parentDiv'></div>",
		useSlide:true,
		winPos:"c",
		width:300,
		height:400,
		allowRightMenu:true,
		showMask:false,
		afterShow:function(){
			if($("#parentDiv").length>0)
			$.post("${ctx}/bis/bis-shop-sort!loadSortTreeByRes.action?haveChecked=true", function(result){
				var tree = new TreePanel({
					renderTo:"parentDiv",
					'root' : eval('('+result+')'),
					'ctx':'${ctx}'
				});
				tree.on(function(node){
				});
				tree.addListener("check",function(){
					selectId =tree.getAllCheckedId();
					selectName =tree.getCheckedName();
				});
				tree.render();
				doExpandTreeNode(tree.getRootNode().childNodes,$("#bisSortId").val());
			});
	},
	handler:function(e){
		if("ok"==e){
			$("#bisSortName").val(selectName);
			$("#bisSortId").val(selectId);
		}
	}
	});
	
}
function doExpandTreeNode(children,sortId){
	if(children!=null&&children.length>0){
		for(var i=0;i<children.length;i++){
			var node =children[i];
			node.expand();
			if(sortId.indexOf(node.attributes.id)>=0){
				node.setCheck("1");
			}
			doExpandTreeNode(node.childNodes,sortId);
		}
	}
}
</script>
<!-- 默认1条申请记录 -->
<s:if test="resApproveInfoId==null">
<script type="text/javascript">
	addItem();
</script>
</s:if>