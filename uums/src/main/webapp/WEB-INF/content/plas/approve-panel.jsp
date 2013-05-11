<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<s:form id="mainSearchForm" action="approve!panelList.action" method="post">
<!-- 我审批别人的历史节点(不含已完成和驳回的) -->
<div style="background-color: #E4E7EC;">
	<input type="hidden" name="searchTypeCd" id="searchTypeCd" value="${searchTypeCd}"/>
	<input type="hidden" name="condApproveStatusCd" id="condApproveStatusCd" value="${condApproveStatusCd}"/>
	<input type="hidden" name="pageNo" id="pageNo" value="${pageNo}"/>
	

	<table class="content_table" style="background-color:white;">
		<col style="width:220px;">
		<col >
		<tr>
			<td colspan="2">
				<span class="span_h3">审批列表</span><span id="operaTip" style="color:red;margin-left:10px;"></span>
			</td>
		</tr>
		<tr>
			<td valign="top">
				<ul id="search_panel" class="search_panel" style="list-style-type:none;">
					<li reltype="1" onclick="searchPanel(this);">我的申请</li>
					<li reltype="2" onclick="searchPanel(this);">我的审批</li>
					<li reltype="3" onclick="searchPanel(this);">历史记录</li>
				</ul>
			</td>
			<td valign="top">
			
			  	<span style="float:left;margin:10px 5px 0 5px;">
			  		按申请类型:
			  	</span>
			  	<span style="float:left;margin:5px 15px 0 0;">
			  		<s:select onchange="changeQuickSearch()" list="@com.powerlong.plas.utils.DictMapUtil@getMapApproveApplyType()" id="condApplyTypeCd" name="condApplyTypeCd" listKey="key" listValue="value"/>
				</span>
				
				<%-- 万能搜索 --%>
			  	<span style="float:left;margin:10px 5px 0 0;">快速搜索: </span>
				<span style="float:left;margin:5px 0 0 0;" class="div_input">
	               	<input type="text" 
	               		  title="支持根据账号或姓名,精确搜索申请记录"  
	               		  class="input_search" 
	               		  name="searchMul"
	               		  onkeyup="changeQuickSearch()"
	               		  value="${searchMul}"
	               />
				</span>
				
	           	<%--
				<security:authorize ifAnyGranted="A_ADMIN">
	           	</security:authorize>
	           	 --%>
	           	
	           	<span class="btn_senior plgreen" href="javascrip:void(0)" id="btnMoreCond">
	           		高级搜索
	           	</span>
	           	<%--
	           	<span class="span_bn plred" id="btnBatchOperate" style="margin-right:20px;">批量操作</span>
	           	 --%>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<ul id="search_panel_sub" class="search_panel_sub" style="margin-left: 4px;list-style-type:none;color:#0167a2;">
					<li reltype="1" onclick="searchPanelSub(this);">未提交</li>
					<li reltype="2" onclick="searchPanelSub(this);">审批中</li>
					<li reltype="3" onclick="searchPanelSub(this);">完成</li>
					<li reltype="4" onclick="searchPanelSub(this);">驳回</li>
					<li reltype="9" onclick="searchPanelSub(this);">全部</li>
				</ul>
			</td>
		</tr>
	</table>
</div>
<%-- 批量操作 --%>
<div id="batchOperatePanel" style="display: none;">
	 <span class="span_bn" onclick="checkAll()">选择全部</span>
	 <span class="span_bn" onclick="unCheckAll()">取消全部</span>
	 <span class="span_bn" onclick="passAll();">批量同意</span>
	 <span class="span_bn" onclick="rejectAll();">批量驳回</span>
</div>
<%-- 搜索条件 --%>
<div id="searchSeniorPanel" style="clear: left; border: 1px solid #E0ECFF; padding: 5px; margin: 10px 0 30px 0; display: none;">
	<table style="width:100%;">
		<tbody>
		<tr>
			<th>调动部门:</th>
			<td><input type="text" name="condOrgName" id="condOrgName" style="cursor:pointer;background-color:#E7E7E7;" readonly="readonly"/>
				<input type="hidden" name="condOrgCd" />
				<input type="button" class="plgreen" onclick="cleanConOrg()" value="清空条件"/>
			</td>
		</tr>
		<tr>
			<th>被操作人:</th>
			<td><input type="text" name="condName" id="condName" style="cursor:pointer;background-color:#E7E7E7;"  readonly="readonly"/>
				<input type="hidden" name="condUiid"/>（可多选）
			</td>
		</tr>
		<tr>
			<th>职级:</th>
			<td>
				<s:select keyfield="true" id="condPermLevelCd" list="@com.powerlong.plas.utils.DictMapUtil@getMapPermLevelEmpty()" name="condPermLevelCd" listKey="key" listValue="value"/>
			</td>
		</tr>
		<tr>
			<th>编制落位:</th>
			<td><input type="text" name="condPosName" id="condPosName"  style="cursor:pointer;background-color:#E7E7E7;"  readonly="readonly"/>
				<input type="hidden" name="condPosCd" />
			</td>
		</tr>
		<tr>
			<th>职位:</th>
			<td><input type="text" name="condWorkDesc"/>
			</td>
		</tr>
		<tr class="applyRow">
			<th>发起人:</th>
			<td><input type="text" name="applyName" id="applyName" style="cursor:pointer;background-color:#E7E7E7;"  readonly="readonly"/>
				<input type="hidden" name="applyUiid"/>（可多选）
			</td>
		</tr>
		<tr class="chiefRow">
			<th></th>
			<td>
				<ul style="list-style-type: none;margin: 0;padding: 0;">
					<li style="float: left;"><input style="margin: 3px 5px;" type="checkbox" onclick="operateChiefTr(this)" name="isProcessedFlg"/>已经手</li>
		 			<li style="float: left;"><input style="margin: 3px 5px;" type="checkbox" onclick="operateChiefTr(this)" name="isProcessingFlg"/>正在办理</li>
				</ul>
			</td>
		</tr>
		<tr style="display: none;">
			<th>审批人:</th>
			<td><input type="text" name="chiefName" id="chiefName" style="cursor:pointer;background-color:#E7E7E7;"  readonly="readonly"/>
				<input type="hidden" name="chiefUiid"/>（可多选）
			</td>
		</tr>
		<tr>
			<th></th>
			<td>
				<input 	id="btnExecSearch" type="button" class="exec_search plgreen" 
						style="width: 80px;height: 30px;line-height: 30px;" value="搜索"
						onclick="submitSearchForm();"
					/>
				<input  id="btnExecClean" type="button" class="plgreen" 
						style="width: 80px;height: 30px;line-height: 30px;" value="清空"
						onclick="quickSearchClose()"
					/>
				<input  id="btnExecClose"  type="button" class="plred" 
						style="width: 80px;height: 30px;line-height: 30px;" value="关闭"
						onclick="quickSearchClose();"
					/>
            </td>
		</tr>
		</tbody>
	</table>
</div>
</s:form>
<!-- 搜素结果 -->
<div id="tableDiv" style="overflow:auto; border-right:1px solid #dddbdc;clear: left;">
	<%@ include file="approve-panelList.jsp"%>
</div>

<script type="text/javascript">

	$(function(){
		
		$('#btnBatchOperate').click(
			function(){
				var open = $(this).attr('open');
				//若已经打开
				if( open == '1'){
					$(this).removeClass('plred').html('批量操作').attr('title','批量操作');
					$('#batchOperatePanel').hide();//.slideUp(200)
					$(this).attr('open','')
				}else{
					$(this).addClass('plred').html('取消操作').attr('title','取消操作');
					$('#batchOperatePanel').show();//slideDown(200);//
					$(this).attr('open','1')
				}
			}
		);

		$('#btnMoreCond').click(
			function(){
				var open = $(this).attr('open');
				//若已经打开
				if( open == '1'){
					$('#searchSeniorPanel').hide();
					$('#tableDiv table').show();
					quickSearchFormClean();//清空
					$(this).attr('title', '显示更多搜索').attr('open','')
				}else{
					$('#searchSeniorPanel').show();
					$('#tableDiv table').hide();
					$(this).attr('title', '收起更多搜索').attr('open','1')
				}
			}
		);
		
		$('#btnExecSearch').hover(
			function(){
				$(this).removeClass('exec_search').addClass('exec_search_on');
			},
			function(){
				$(this).removeClass('exec_search_on').addClass('exec_search');
			}
		);
		
		if('${searchTypeCd}' != '' && '${condApproveStatusCd}' != ''){
			var url = _ctx + '/plas/approve!panelList.action';
			var data = {searchTypeCd: '${searchTypeCd}', searchMul: '${searchMul}', condApproveStatusCd: '${condApproveStatusCd}'};
			//链接搜索
			$.post(url, data, function(result){
				$('#tableDiv').html(result).show();
			});
		}else{
			if('${searchTypeCd}' == ''){
				$('#search_panel li[reltype=2]').click();
			}else{
				$('#search_panel li[reltype=${searchTypeCd}]').click();
			}
		}
		
		//调动机构
		$('#condOrgName').orgSelect();
		//编制落位
		$('#condPosName').userSelect({sysPosEId:'sysPosId'}); 
		//被操作人员
		$('#condName').userSelect({muti: true});
		//发起人员
		$('#applyName').userSelect({muti: true});
		//审批人员
		$('#chiefName').userSelect({muti: true});
	});
	
	function cleanConOrg(){
		$('#condOrgName').val('');
		$('#condOrgCd').val('');
	}
</script>