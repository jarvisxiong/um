<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@page import="com.hhz.ump.util.CodeNameUtil" %>
<%@page import="com.hhz.ump.util.JspUtil" %>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/global.jsp" %>
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/base.css"/>
    <link rel="stylesheet" href="${ctx}/resources/css/common/common.css" type="text/css"/>
    <link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/thickbox.css"/>
    <link rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/js/prompt/skin/custom_1/ymPrompt.css"/>
    <link rel="stylesheet" href="${ctx}/resources/css/bis/bis-shop.css" type="text/css"/>
    <script type="text/javascript" src="${ctx}/js/jquery.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/common/MaskLayer.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/bis/bis-shop.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/bis/bis.project.select.js"></script>
</head>
<body>
<form action="${ctx}/bis/bis-shop!save.action" method="post" id="bis-shop-form">
<input type="hidden" id="shopSortId" name="shopSortId" value="${shopSortId}"/>
<input type="hidden" id="id"/>
<input type="hidden" id="bisShopId" name="id" value="${bisShopId}"/>
<input type="hidden" id="deleteBl" name="deleteBl" value="${deleteBl}"/>
<input type="hidden" id="logo" name="logo" value="${logo}"/>
<input type="hidden" id="storeImg" name="storeImg" value="${storeImg }"/>
<input type="hidden" id="bisShopAudit" name="bisShopAudit" value="${bisShopAudit}"/>

<div id="header">
    <div class="title1 clearfix">
        <h2 style="width: auto;float: left;margin-right: 20px;">商家信息</h2>
        <s:if test="mergeId!='mergeId'">
       		<h2 style="font-size: 12px;">
	            <span style="font-weight:normal;">当前状态:</span>
	            <span id="tipStatus" style="margin-left:5px;font-weight: bold;">新增</span>
	            <span id="auditStat" style="margin-left:5px;font-weight: bold;"></span>
            </h2>
            <div class="btns">
            	<security:authorize ifAnyGranted="A_SHOP_NEW_HQ,A_SHOP_NEW_PRO">
		            <button type="button" class="red min" id="btn_cancel" onclick="cancelBis();">取消</button>
		            <button type="button" class="green min" id="btn_save" onclick="doShopSave();">保存</button>
	            </security:authorize>
            
		        <security:authorize ifAnyGranted="A_SHOP_MERGE">
		            <s:if test="deleteBl!=2">
		            	<button class="blue min" type="button" onclick="mergeQuery('${bisShopId}');">合并</button>
		            </s:if>
		        </security:authorize>
		        
	            <s:if test="deleteBl!=2&&haveEditor==1">
             		<security:authorize ifAnyGranted="A_SHOP_NEW_HQ,A_SHOP_NEW_PRO">
                  		<button type="button" class="blue min" id="btn_edit" onclick="editShop('${bisShopId}');">编辑</button>
                  	</security:authorize>
	            </s:if>
	            <s:else>
	                <security:authorize ifAnyGranted="A_SHOP_LOOK_HB">
		                <s:if test="deleteBl==2">
		                    <button type="button" class="blue" id="btn_query" onclick="mergeQueryByOne('${bisShopId}');">查看合并内容</button>
		                </s:if>
	                </security:authorize>
	            </s:else>
	            
	            <security:authorize ifAnyGranted="A_SHOP_COMMIT">  
		            <s:if test="bisShopAudit==0">
			            <button type="button" class="blue min" id="btn_sub" onclick="doShopSave('1');">提交</button>
		            </s:if>
	            </security:authorize>
	            
	            <%--只有主力店、次主力店才有预审核 --%>
	            <s:if test="manageCd!=3">
	                    <s:if test="bisShopAudit==1">
			                <security:authorize ifAnyGranted="A_SHOP_AUDITION">
		            			<button type="button" class="blue min" id="btn_preAudit" onclick="doShopSave('2');">预审核</button>
	                		</security:authorize>
	                    </s:if>
	                    <s:elseif test="bisShopAudit==2">
			                <security:authorize ifAnyGranted="A_SHOP_REJECTION">
			            		<button type="button" class="red min" id="btn_reback" onclick="doShopSave('1');">预驳回</button>
	                		</security:authorize>
	                    </s:elseif>
	                    <s:if test="bisShopAudit==2">
	               			<security:authorize ifAnyGranted="A_SHOP_AUDIT_ALL">
			            		<button type="button" class="blue min" id="btn_audit" onclick="doShopSave('3');">审核</button>
	               			</security:authorize>
	                    </s:if>
	                    <s:elseif test="bisShopAudit==3">
	                		<security:authorize ifAnyGranted="A_SHOP_REJECT">
			            		<button type="button" class="red min" id="btn_reback" onclick="doShopSave('2');">驳回</button>
	                		</security:authorize>
	                    </s:elseif>
	            </s:if>
	            <%--审核小商铺 A_SHOP_CHECK_UNIM --%>
	            <s:else>
	                    <s:if test="bisShopAudit==1">
	               			<security:authorize ifAnyGranted="A_SHOP_AUDIT_ALL">
	                      		<button type="button" class="blue min" id="btn_audit" onclick="doShopSave('3');">审核</button>
	                		</security:authorize>
	                    </s:if>
	                    <s:elseif test="bisShopAudit==3">
	                		<security:authorize ifAnyGranted="A_SHOP_REJECT">
	                   			<button type="button" class="red min" id="btn_reback" onclick="doShopSave('1');">驳回</button>
	                		</security:authorize>
	                    </s:elseif>
	            </s:else>
	            
	            <security:authorize ifAnyGranted="A_SHOP_UPDATE_SALE">
		            <button type="button" class="blue" id="btn_updateCreator" onclick="updateCreator('${bisShopId}');">更改业务员</button>
	            </security:authorize>
            </div>
   		 </s:if>
    </div>
</div>

<div style="position: absolute;bottom:0;top:39px;width:100%;overflow:auto;+overflow:visible;">
	<table style="width:100%;">
		<tr>
			<td valign="top" width="150px" style="background-color:#e4e7ec;border-right:1px solid #8c8f94;">
				<%--树结构 --%>
				<div id="sortMarginDiv"
				     style="height:this.parentNode.offsetHeight;width:150px;bottom:0;top:2px; float:left;background-color:#e4e7ec;">
				    <div id="sortInputDiv" style="height:auto;width: 150px; overflow:auto;padding-top:5px;"></div>
				</div>
			</td>
			<td valign="top">
				<div id="div_main_cont" style="margin-left: 5px;padding-top:10px;">
					<s:if test="bisShopId!=null">
					    <div id="div_shop_prop" style="margin-left:10px;padding-bottom:5px;">
					        <table style="width:100%;">
					            <tr>
					                <td><span style="font-size: 14px;">项目：</span><span
					                        style="font-size: 14px;color:#0693e3;">${centerName}</span></td>
					                <td><span style="font-size: 14px;">业务员：</span>
					                    <span style="font-size: 14px;color:#0693e3;"
					                          id="salesmanSpan"><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("salesman"))%></span>
					                </td>
					                <td><span style="font-size: 14px;">建档日期：</span>
					                    <span style="font-size: 14px;color:#0693e3;"><s:date name="createdDate" format="yyyy-MM-dd"/></span>
					                </td>
					            </tr>
					        </table>
					    </div>
					</s:if>
					<s:else>
						<div style="margin-left:10px;padding-bottom:5px;">
						   <span style="font-size: 14px;">项目：</span><span style="font-size: 14px;color:#0693e3;">
							   <input type="text" id="bisProjectName" class="required" value="${centerName}"/> 
							   <input type="hidden" id="bisProjectCd" name="bisShopDept" value="${bisShopDept}"/>
						   </span>
						</div>
					</s:else>
					
					<%--注意：这个属性值必须放在security:authorize标签的外面，否则在js中无法取到这个值 --%>
					<input type="hidden" id="selecManageCd" value="${manageCd}"/>
					
					
					<%-- 有查阅（一级）、查阅（全部）权限的人，能查看基本资料 --%>
					<security:authorize ifAnyGranted="A_SHOP_FIRST_VIEW,A_SHOP_ALL_VIEW">
					<div>
					    <fieldset>
					        <legend>&nbsp;&nbsp;1&nbsp;基本资料&nbsp;<img src="${ctx}/resources/images/common/show_white.png"/></legend>
					        <table class="shop-table">
					            <col width="10%"/>
					            <col width="15%"/>
					            <col width="30%"/>
					            <col width="15%"/>
					            <col width="30%"/>
					            <tr>
					                <td rowspan="5">品牌资料</td>
					                <td>品牌名(中)</td>
					                <td><input type="text" id="nameCn" name="nameCn" value="${nameCn}" onkeyup="existsShopList(this);"/>
					                </td>
					                <td>品牌名(英)</td>
					                <td><input type="text" id="nameEn" name="nameEn" value="${nameEn}"/></td>
					            </tr>
					            <tr>
					                <td>品牌简介</td>
					                <td colspan="3"><input type="text" name="brandDesc" id="brandDesc" value="${brandDesc}"/></td>
					            </tr>
					            <tr>
					                <td>商家定位</td>
					                <td>
					                	<input type="text"
					                           value="<p:code2name mapCodeName='@com.hhz.ump.util.DictMapUtil@getMapShopManageType()' value='manageCd' />"
					                           class="shop_query"/>
						                 <s:select list="mapShopManageType" listKey="key" listValue="value" name="manageCd"
							                           cssClass="shop_edit required" onchange="doSelectManange(this);" style="width:100%;"/>
					                </td>
					                <td>商家级别</td>
					                <td>
					                	<s:select id="sleecShopLevelCd" name="shopLevel" list="mapShopLevel" listKey="key" listValue="value" cssClass="shop_edit required" style="width:100%;"/>
					                	<input type="text"  
					                	       value="<p:code2name mapCodeName='@com.hhz.ump.util.DictMapUtil@getMapShopLevel()' value='shopLevel' />"
					                	       class="shop_query"/>
					                </td>
					            </tr>
					            <tr>
					                <td>配合区域</td>
					                <td colspan="3">
					                	<input type="hidden" id="cooperateAreaCd" name="cooperateAreaCd" value="${cooperateAreaCd}"/>
					                	<input class="shop_query" type="text" value="${cooperateArea}"/>
					                	<input class="shop_edit required" 
					                	       type="text" 
					                	       name="cooperateArea" 
					                	       id="cooperateArea" 
					                	       value="${cooperateArea}" 
					                	       onclick="selectArea()" 
					                	       readonly="readonly"/>
					                </td>
					            </tr>
					            <tr>
					                <td>战略商家</td>
					                <td style="text-align: left;">
					                	<span class="shop_query">
								      		<s:if test="strategicShop==1">是</s:if>
								      		<s:elseif test="strategicShop==2">否</s:elseif>
								    	</span>
							        	<select name="strategicShop" class="shop_edit required" style="width:100%;">
							            	<option value=""></option>
							            	<option value="1" <s:if test="strategicShop==1">selected</s:if> >是
							            	</option>
							            	<option value="2" <s:if test="strategicShop==2">selected</s:if>>否
							            	</option>
							        	</select>
					                </td>
					                <td>标杆商家</td>
					                <td style="text-align: left;">
					                	<span class="shop_query">
								      		<s:if test="benchmarkShop==1">是</s:if>
								      		<s:elseif test="benchmarkShop==2">否</s:elseif>
								    	</span>
							        	<select name="benchmarkShop" class="shop_edit required" style="width:100%;">
							            	<option value=""></option>
							            	<option value="1" <s:if test="benchmarkShop==1">selected</s:if> >是
							            	</option>
							            	<option value="2" <s:if test="benchmarkShop==2">selected</s:if>>否
							            	</option>
							        	</select>
							        </td>
					            </tr>
					            <tr>
					                <td rowspan="5">公司信息</td>
					                <td>公司名称</td>
					                <td><input type="text" id="companyName" name="companyName" value="${companyName}"
					                                       class="required"/></td>
					                <td>公司电话</td>
					                <td><input type="text" id="companyTelBaseinfo" name="companyTelBaseinfo" value="${companyTelBaseinfo}"
					                                       class="required"/></td>
					            </tr>
					            <tr>
					                <td>&nbsp;&nbsp;创建人</td>
					                <td><input type="text" name="founderPeople" value="${founderPeople}"/></td>
					                <td>&nbsp;&nbsp;创建时间</td>
					                <td><input type="text" id="founderDate" name="founderDate"
					                           value='<s:date name="founderDate" format="yyyy" />' onfocus="WdatePicker({dateFmt:'yyyy'})"/>
					                </td>
					            </tr>
					            <tr>
					                <td>产地</td>
					                <td><input type="text" name="produceAddr" value="${produceAddr}" class="required"/></td>
					                <td>注册地</td>
					                <td><input type="text" name="registerAddr" value="${registerAddr}" class="required"/></td>
					            </tr>
					            <tr>
					                <td>品牌发源地</td>
					                <td><input type="text" name="brandCradleland" value="${brandCradleland}" class="required"/></td>
					                <td>&nbsp;&nbsp;网站</td>
					                <td><input type="text" name="webSite" value="${webSite}"/></td>
					            </tr>
					            <tr>
					                <td>LOGO</td>
					                <td>
					                    <!--
							  <span>
							  <div style="height:80px;width:100px;float:left;">
							  <s:if test="logoFile!=null">
							   <s:url id="downSmall" action="show" namespace="/app">
									<s:param name="fileName">${logoFile.smallPicName}</s:param>
									<s:param name="id">${logoFile.appAttachFileId}</s:param>
									<s:param name="bizModuleCd">${logoFile.bizModuleCd}</s:param>
								</s:url>
								<img src="${downSmall}" title="${logoFile.realFileName}" style="cursor: pointer;height:80px;width:100px;" alt="${logoFile.realFileName}"/>
								</s:if> 
								</div>
							  </span>
							   -->
							   <span class="shop_query">
							      <img onclick="showAttachment('${logo}')" title="图片"
					                      <s:if test="logo==null||logo==''">
					                          src="${ctx}/resources/images/common/atta.gif"
					                      </s:if> <s:else> src="${ctx}/resources/images/common/atta_y.gif" </s:else>
					                      />
							   </span>
							  <span class="shop_edit">
							    <img id="logo_img" onclick="openAttachment(this,'${logo}','logo','logo_img')" title="请上传图片"
					                 <s:if test="logo==null||logo==''">src="${ctx}/resources/images/common/atta.gif"</s:if>
					                 <s:else>src="${ctx}/resources/images/common/atta_y.gif"</s:else>
					                    />
							  </span>
					                </td>
					                <td>门店图片</td>
					                <td>
							   <span class="shop_query">
							      <img onclick="showAttachment('${storeImg}')" title="请上传图片"
					                      <s:if test="storeImg==null||storeImg==''">
					                          src="${ctx}/resources/images/common/atta.gif"
					                      </s:if> <s:else> src="${ctx}/resources/images/common/atta_y.gif" </s:else>
					                      />
							   </span>
							  <span class="shop_edit">
							    <img id="sotre_img" onclick="openAttachment(this,'${storeImg}','storeImg','sotre_img')" title="请上传图片"
					                 <s:if test="storeImg==null||storeImg==''">src="${ctx}/resources/images/common/atta.gif"</s:if>
					                 <s:else>src="${ctx}/resources/images/common/atta_y.gif"</s:else>
					                    />
							  </span>
					                </td>
					            </tr>
					        </table>
					        <div class="foot-ellipsis"></div>
					    </fieldset>
					</div>
					<div>
					    <fieldset>
					        <legend>&nbsp;&nbsp;2&nbsp;商务资料&nbsp;<img src="${ctx}/resources/images/common/show_white.png"/></legend>
					        <table class="shop-table">
					            <col width="10%"/>
					            <col width="15%"/>
					            <col width="30%"/>
					            <col width="15%"/>
					            <col width="30%"/>
					            <tr>
					                <td rowspan="8">联系方式</td>
					                <td>总部地址</td>
					                <td><input type="text" name="hqAddr" value="${hqAddr}" class="required" maxlength="150"/></td>
					                <td>&nbsp;&nbsp;公司电话</td>
					                <td><input type="text" name="companyTel" value="${companyTel}" class="required" maxlength="50"/></td>
					            </tr>
					            <tr>
					                <td>&nbsp;&nbsp;邮编</td>
					                <td><input type="text" name="postCode" value="${postCode}" maxlength="50"/></td>
					                <td>&nbsp;&nbsp;传真</td>
					                <td><input type="text" name="fax" value="${fax}" maxlength="50"/></td>
					            </tr>
					            <tr>
					                <td>拓展部门1</td>
					                <td colspan="3"><input type="text" id="principalNew" name="principal" value="${principal}"
					                                       class="required" maxlength="50"/></td>
					            </tr>
					            <tr>
					                <td>&nbsp;&nbsp;职务1</td>
					                <td><input type="text" name="principalPos" value="${principalPos}" maxlength="50"/></td>
					                <td>&nbsp;&nbsp;电话1</td>
					                <td><input type="text" name="principalPhone" value="${principalPhone}" class="required" maxlength="50"/>
					                </td>
					            </tr>
					            <tr>
					                <td>拓展部门2</td>
					                <td colspan="3"><input type="text" id="principalNew2" name="principal2" value="${principal2}"
					                                       maxlength="50"/></td>
					            </tr>
					            <tr>
					                <td>&nbsp;&nbsp;职务2</td>
					                <td><input type="text" name="principalPos2" value="${principalPos2}" maxlength="50"/></td>
					                <td>&nbsp;&nbsp;电话2</td>
					                <td><input type="text" name="principalPhone2" value="${principalPhone2}" maxlength="50"/></td>
					            </tr>
					            <tr>
					                <td>拓展部门3</td>
					                <td colspan="3"><input type="text" id="principalNew3" name="principal3" value="${principal3}"
					                                       maxlength="50"/></td>
					            </tr>
					            <tr>
					                <td>&nbsp;&nbsp;职务3</td>
					                <td><input type="text" name="principalPos3" value="${principalPos3}" maxlength="50"/></td>
					                <td>&nbsp;&nbsp;电话3</td>
					                <td><input type="text" name="principalPhone3" value="${principalPhone3}" maxlength="50"/></td>
					            </tr>
					            <tr>
					                <td rowspan="4">市场定位</td>
					                <td>竞争品牌</td>
					                <td><input type="text" name="competeBrand" value="${competeBrand}" maxlength="500"/></td>
					                <td>品牌代言人</td>
					                <td><input type="text" name="spokesman" value="${spokesman}" maxlength="50"/></td>
					            </tr>
					            <tr>
					                <td>消费年龄层</td>
					                <td><input type="text" name="consumeAge" value="${consumeAge}" maxlength="10"/></td>
					                <td>消费对象</td>
					                <td><input type="text" name="consumeObj" value="${consumeObj}" maxlength="500"/></td>
					            </tr>
					            <tr>
					                <td>年销售总额(万元)</td>
					                <td><input type="text" name="yearTotalMoney" value="${yearTotalMoney}" alt="amount" class="required"/>
					                </td>
					                <td>销售价格区间(元)</td>
					                <td><input type="text" name="singleYearMinName" value="${singleYearMinName}" maxlength="50"/></td>
					            </tr>
					            <tr>
					                <td>产品风格</td>
					                <td colspan="3"><input type="text" name="productStyle" value="${productStyle}" maxlength="150"/></td>
					            </tr>
					        </table>
					        <div class="foot-ellipsis"></div>
					    </fieldset>
					</div>
					<div>
						<fieldset>
							<legend>&nbsp;&nbsp;3&nbsp;合作要求&nbsp;<img src="${ctx}/resources/images/common/show_white.png"/></legend>
							<table class="shop-table">
							<col width="8%"/>
							<col width="8%"/>
							<col width="9%"/>
							<col width="15%"/>
							<col width="20%"/>
							<col width="15%"/>
							<col width="20%"/>
							<tr>
							    <td rowspan="6">基本资料</td>
							    <td colspan="3">拓店计划</td>
							    <td colspan="3"><input type="text" name="openPlan" value="${openPlan}" maxlength="200"/></td>
							</tr>
							<tr>
							    <td colspan="3">重点发展区域</td>
							    <td><input type="text" name="importantArea" value="${importantArea}" maxlength="100"/></td>
							    <td>其他发展区域</td>
							    <td><input type="text" name="otherArea" value="${otherArea}" maxlength="100"/></td>
							</tr>
							<tr>
							    <td colspan="3">全球总店数</td>
							    <td><input type="text" name="totalShopNum" value="${totalShopNum}" alt="amount"/></td>
							    <td>主要分布国家</td>
							    <td><input type="text" name="distributeCoun" value="${distributeCoun}" maxlength="100"/></td>
							</tr>
							<tr>
							    <td colspan="3">全国总店数</td>
							    <td><input type="text" name="countryNum" value="${countryNum}" alt="amount"/></td>
							    <td>主要分布城市</td>
							    <td><input type="text" name="distributeCity" value="${distributeCity}" maxlength="100"/></td>
							</tr>
							<tr>
							    <td colspan="3">已合作门店(非宝龙)</td>
							    <td><input type="text" name="cooperatedShopUn" value="${cooperatedShopUn}" maxlength="50"/></td>
							    <td>已合作门店(宝龙)</td>
							    <td><input type="text" name="cooperatedShop" value="${cooperatedShop}" maxlength="50"/></td>
							</tr>
							<tr>
							    <td colspan="3">合作区域(宝龙)</td>
							    <td>
							        <!--
								     <input type="text" value='<p:code2name mapCodeName="mapBisCooperative" value="shopTypeCd" />' class="shop_query"/>
								     <s:select list="mapBisCooperative" listKey="key" listValue="value" id="selectShopTypeCd" name="shopTypeCd" cssClass="shop_edit" style="width:100%;"/>
								    -->
							        <input type="text" value='${areaCooperStr}' class="shop_query"/>
							        <input type="hidden" name="shopTypeCd" value="${shopTypeCd}"/>
							        <input type="text" onclick="selectCooperated();" id="inputSeleCoop" name="areaCooperStr"
							               value="${areaCooperStr}" class="shop_edit"/>
							    </td>
							    <td>拟合作区域(宝龙)</td>
							    <td><input type="text" name="toCooperateShop" value="${toCooperateShop}" maxlength="50"/></td>
							</tr>
							<tr>
							    <td rowspan="3">一般要求</td>
							    <td rowspan="3">商圈数据</td>
							    <td rowspan="2">人文地理</td>
							    <td>人口数量(万)</td>
							    <td><input type="text" name="sizeOfPopulation" value="${sizeOfPopulation}" alt="amount"/></td>
							    <td>居民平均收入(元/年)</td>
							    <td><input type="text" name="averageIncome" value="${averageIncome}" alt="amount"/></td>
							</tr>
							<tr>
							    <td>GDP</td>
							    <td colspan="3"><input type="text" name="gdp" value="${gdp}"/></td>
							</tr>
							<tr>
							    <td>市场要求</td>
							    <td>商圈情况</td>
							    <td><input type="text" name="businessDistrict" value="${businessDistrict}" maxlength="50"/></td>
							    <td>交通要求</td>
							    <td><input type="text" name="trafficRequire" value="${trafficRequire}" maxlength="50"/></td>
							</tr>
							<tr>
							    <td rowspan="19">建筑要求</td>
							    <td rowspan="5">一般内容</td>
							    <td>若属餐饮(必填)</td>
							    <td>使用明火</td>
							    <td>
								    <span class="shop_query">
								      <s:if test="userFireBl==1">是</s:if>
								      <s:elseif test="userFireBl==2">否</s:elseif>
								    </span>
							        <select name="userFireBl" class="shop_edit" style="width:98%;">
							            <option value=""></option>
							            <option value="1"
							                    <s:if test="userFireBl==1">selected</s:if> >是
							            </option>
							            <option value="2" <s:if test="userFireBl==2">selected</s:if>>否</option>
							        </select>
							    </td>
							    <td>面积</td>
							    <td>
									    <span class="shop_query">
									      <s:if test="fireArea==1">100-200㎡</s:if>
									      <s:elseif test="fireArea==2">200-500㎡</s:elseif>
									      <s:elseif test="fireArea==3">500-1000㎡</s:elseif>
									      <s:elseif test="fireArea==4">1000㎡以上</s:elseif>
									    </span>
							        <select name="fireArea" class="shop_edit" style="width:98%;">
							            <option value=""></option>
							            <option value="1" <s:if test="fireArea==1">selected</s:if>>100-200㎡</option>
							            <option value="2" <s:if test="fireArea==2">selected</s:if>>200-500㎡</option>
							            <option value="3" <s:if test="fireArea==3">selected</s:if>>500-1000㎡</option>
							            <option value="4" <s:if test="fireArea==4">selected</s:if>>1000㎡以上</option>
							        </select>
							    </td>
							</tr>
							<tr>
							    <td rowspan="2">面积</td>
							    <td>经营面积(㎡)</td>
							    <td><input type="text" name="mallSquare" value="${mallSquare}" class="build_required" maxlength="50"/></td>
							    <td>仓库面积(㎡)</td>
							    <td><input type="text" name="warehouseSquare" value="${warehouseSquare}" class="build_required" maxlength="50"/>
							    </td>
							</tr>
							<tr>
							    <td>后勤面积(㎡)</td>
							    <td><input type="text" name="logisticsSquare" value="${logisticsSquare}" class="build_required" maxlength="50"/>
							    </td>
							    <td>厨房面积(㎡)</td>
							    <td><input type="text" name="kitchenSquare" value="${kitchenSquare}" class="build_required" maxlength="50"/></td>
							</tr>
							<tr>
							    <td rowspan="2">停车位</td>
							    <td>收货车位(个)</td>
							    <td><input type="text" name="receiveStall" value="${receiveStall}" class="build_required" maxlength="50"/></td>
							    <td>顾客车位(个)</td>
							    <td><input type="text" name="customerStall" value="${customerStall}" class="build_required" maxlength="50"/></td>
							</tr>
							<tr>
							    <td>自行车位(个)</td>
							    <td colspan="3"><input type="text" name="bicycleStall" value="${bicycleStall}" class="build_required"
							                           maxlength="50"/></td>
							</tr>
							<tr>
							    <td rowspan="5">建筑物要求</td>
							    <td rowspan="2">层高</td>
							    <td>卖场层高(米)</td>
							    <td><input type="text" name="mallFloorHeight" value="${mallFloorHeight}" class="build_required" maxlength="50"/>
							    </td>
							    <td>停车场层高(米)</td>
							    <td><input type="text" name="parkFloorHeight" value="${parkFloorHeight}" class="build_required" maxlength="50"/>
							    </td>
							</tr>
							<tr>
							    <td>收货场地面积(㎡)</td>
							    <td colspan="3"><input type="text" name="receivePlace" value="${receivePlace}" class="build_required"
							                           maxlength="50"/></td>
							</tr>
							<tr>
							    <td>承重量</td>
							    <td>卖场承重(kg/平米)</td>
							    <td><input type="text" name="mallBearing" value="${mallBearing}" class="build_required" maxlength="50"/></td>
							    <td>车库承重</td>
							    <td><input type="text" name="parkBearing" value="${parkBearing}" class="build_required" maxlength="50"/></td>
							</tr>
							<tr>
							    <td rowspan="2">柱子</td>
							    <td>柱间距(m*m)</td>
							    <td><input type="text" name="span" value="${span}" class="build_required" maxlength="100"/></td>
							    <td>柱子尺寸(m)</td>
							    <td><input type="text" name="pillarSize" value="${pillarSize}" class="build_required" maxlength="100"/></td>
							</tr>
							<tr>
							    <td>柱与外墙距离(m)</td>
							    <td colspan="3"><input type="text" name="distancePillarWall" value="${distancePillarWall}" class="build_required"
							                           maxlength="100"/></td>
							</tr>
							<tr>
							    <td rowspan="2">电、水、煤容量</td>
							    <td rowspan="2">供电</td>
							    <td>入铺电缆(W/平米)</td>
							    <td><input type="text" name="cable" value="${cable}" class="build_required" maxlength="100"/></td>
							    <td>供水</td>
							    <td><input type="text" name="waterSupply" value="${waterSupply}" class="build_required" maxlength="100"/></td>
							</tr>
							<tr>
							    <td>供气</td>
							    <td colspan="3"><input type="text" name="airSupply" value="${airSupply}" class="build_required" maxlength="100"/>
							    </td>
							</tr>
							<tr>
							    <td rowspan="6">设备要求</td>
							    <td rowspan="2">空调</td>
							    <td>夏季要求</td>
							    <td><input type="text" name="summerRequire" value="${summerRequire}" class="build_required" maxlength="100"/></td>
							    <td>冬季要求</td>
							    <td><input type="text" name="winterRequire" value="${winterRequire}" class="build_required" maxlength="100"/></td>
							</tr>
							<tr>
							    <td>冷却塔</td>
							    <td colspan="3"><input type="text" name="coolingTower" value="${coolingTower}" class="build_required"
							                           maxlength="100"/></td>
							</tr>
							<tr>
							    <td rowspan="2">风口</td>
							    <td>新风口位置</td>
							    <td><input type="text" name="freshWindPortPosi" value="${freshWindPortPosi}" class="build_required"
							               maxlength="100"/></td>
							    <td>排风口位置</td>
							    <td><input type="text" name="exhaustOutletPosi" value="${exhaustOutletPosi}" class="build_required"
							               maxlength="100"/></td>
							</tr>
							<tr>
							    <td>油烟净化器</td>
							    <td colspan="3"><input type="text" name="lamblackAbsorber" value="${lamblackAbsorber}" class="build_required"
							                           maxlength="100"/></td>
							</tr>
							<tr>
							    <td rowspan="2">上下排</td>
							    <td>排管道口径(DN)</td>
							    <td><input type="text" name="pipelineCaliber" value="${pipelineCaliber}" class="build_required" maxlength="100"/>
							    </td>
							    <td>隔油池</td>
							    <td><input type="text" name="separationTank" value="${separationTank}" class="build_required" maxlength="100"/></td>
							</tr>
							<tr>
							    <td>化粪池</td>
							    <td colspan="3"><input type="text" name="septicTank" value="${septicTank}" class="build_required" maxlength="100"/>
							    </td>
							</tr>
							<tr>
							    <td colspan="3">广告位需求</td>
							    <td colspan="3"><input type="text" name="adPos"
							                           value='<s:if test="adPos==null">__店招_广告位</s:if><s:else>${adPos}</s:else>'
							                           class="build_required"/></td>
							</tr>
							<tr>
							    <td colspan="2">主要合作条件</td>
							    <td colspan="5"><input type="text" name="comCooperCondition" value="${comCooperCondition}"/></td>
							</tr>
							<tr>
							    <td colspan="2">租赁优惠及装补</td>
							    <td colspan="5"><input type="text" name="rentPromotions" value="${rentPromotions}" maxlength="200"/></td>
							</tr>
							</table>
							<div class="foot-ellipsis"></div>
						</fieldset>
					</div>
					
					<div>
					    <fieldset>
					        <legend>&nbsp;&nbsp;4&nbsp;代理商/经销商信息&nbsp;<img src="${ctx}/resources/images/common/show_white.png"/></legend>
					        <table class="shop-table">
					            <col width="15%"/>
					            <col width="35%"/>
					            <col width="15%"/>
					            <col width="35%"/>
					            <s:iterator value="bisShopConns" status="st">
					                <tr id='${bisShopConnId}'>
					                    <td>代理商/经销商名称</td>
					                    <td><input class="required" type="text" name="bisShopConns[<s:property value="#st.index"/>].partName"
					                               value="${partName}"/></td>
					                    <td>代理商/经销商性质</td>
					                    <td colspan="3">
					                        <input type="text"
					                               value="<p:code2name mapCodeName='mapBisSupplier' value='supplierAttributeCd' />"
					                               class="shop_query" style="width:100%;"/>
					                        <input type="hidden" name="bisShopConns[<s:property value='#st.index'/>].supplierAttributeCd"
					                               value="${supplierAttributeCd}"/>
					                        <s:select list="mapBisSupplier" listKey="key" listValue="value" id="selectAttributeCd"
					                                  onchange="doAttributeCd(this);" cssClass="shop_edit supplierAttr"
					                                  style="width:100%;"/>
					                    </td>
					                </tr>
					                <tr>
					                    <td>公司地址</td>
					                    <td><input type="text" name="bisShopConns[<s:property value="#st.index"/>].partAddress"
					                               value="${partAddress}"/></td>
					                    <td>公司电话</td>
					                    <td><input type="text" name="bisShopConns[<s:property value="#st.index"/>].principalPhone"
					                               value="${principalPhone}"/></td>
					                </tr>
					                <tr>
					                    <td>&nbsp;&nbsp;手机</td>
					                    <td><input type="text" name="bisShopConns[<s:property value="#st.index"/>].principalTel"
					                               value="${principalTel}"/></td>
					                    <td>联络人</td>
					                    <td><input type="text" name="bisShopConns[<s:property value="#st.index"/>].principal"
					                               value="${principal}"/></td>
					                </tr>
					                <tr>
					                    <td>职务</td>
					                    <td><input type="text" name="bisShopConns[<s:property value="#st.index"/>].principalPos"
					                               value="${principalPos}"/></td>
					                    <td>传真</td>
					                    <td><input type="text" name="bisShopConns[<s:property value="#st.index"/>].fax" value="${fax}"/>
					                    </td>
					                </tr>
					                <tr>
					                    <td>&nbsp;&nbsp;邮编</td>
					                    <td colspan="3"><input type="text" name="bisShopConns[<s:property value="#st.index"/>].postCode"
					                                           value="${postCode}"/></td>
					                </tr>
					                <tr>
					                    <td>已合作门店</td>
					                    <td><input type="text" name="bisShopConns[<s:property value="#st.index"/>].cooperatedShop"
					                               value="${cooperatedShop}"/></td>
					                    <td>可合作门店</td>
					                    <td><input type="text" name="bisShopConns[<s:property value="#st.index"/>].toCooperateShop"
					                               value="${toCooperateShop}"/></td>
					                </tr>
					                <tr>
					                    <td>经营区域/城市</td>
					                    <td colspan="3"><input type="text" name="bisShopConns[<s:property value="#st.index"/>].manageCity"
					                                           value="${manageCity}"/></td>
					                </tr>
					                <tr>
					                    <td>主要合作条件</td>
					                    <td colspan="3"><input type="text"
					                                           name="bisShopConns[<s:property value="#st.index"/>].conditionForCooperation"
					                                           value="${conditionForCooperation}"/></td>
					                </tr>
					                <tr>
					                    <td>备注</td>
					                    <td colspan="3"><input type="text" name="bisShopConns[<s:property value="#st.index"/>].remark"
					                                           value="${remark}"/></td>
					                </tr>
					                <tr>
					                </tr>
					                <tr>
					                    <td colspan="8">&nbsp;
					                        <img border="0" src="${ctx}/resources/images/common/del_22_22.gif"
					                             onclick="doDeleteConn('${bisShopConnId}');" style="display:none;"/>
					                    </td>
					                </tr>
					            </s:iterator>
					        </table>
					        <div class="foot-ellipsis"></div>
					        <table id="conn_new" class="shop-table" style="display:none;">
					            <col width="15%"/>
					            <col width="35%"/>
					            <col width="15%"/>
					            <col width="35%"/>
					            <tr>
					                <td>代理商/经销商名称</td>
					                <td><input type="text" name="bisShopConns[${fn:length(bisShopConns)}].partName" id="partName"/></td>
					                <td>代理商/经销商性质</td>
					                <td>
					                    <input type="hidden" name="bisShopConns[${fn:length(bisShopConns)}].supplierAttributeCd"/>
					                    <s:select list="mapBisSupplier" listKey="key" listValue="value" onchange="doAttributeCd(this);"
					                              cssClass="shop_edit" style="width:100%;"/>
					                </td>
					            </tr>
					            <tr>
					                <td>公司地址</td>
					                <td><input type="text" name="bisShopConns[${fn:length(bisShopConns)}].partAddress"/></td>
					                <td>公司电话</td>
					                <td><input type="text" name="bisShopConns[${fn:length(bisShopConns)}].principalPhone"/></td>
					            </tr>
					            <tr>
					                <td>&nbsp;&nbsp;手机</td>
					                <td><input type="text" name="bisShopConns[${fn:length(bisShopConns)}].principalTel"/></td>
					                <td>联络人</td>
					                <td><input type="text" name="bisShopConns[${fn:length(bisShopConns)}].principal"/></td>
					            </tr>
					            <tr>
					                <td>职务</td>
					                <td><input type="text" name="bisShopConns[${fn:length(bisShopConns)}].principalPos"/></td>
					                <td>传真</td>
					                <td><input type="text" name="bisShopConns[${fn:length(bisShopConns)}].fax"/></td>
					            </tr>
					            <tr>
					                <td>&nbsp;&nbsp;邮编</td>
					                <td colspan="3"><input type="text" name="bisShopConns[${fn:length(bisShopConns)}].postCode"/></td>
					            </tr>
					            <tr>
					                <td>已合作门店</td>
					                <td><input type="text" name="bisShopConns[${fn:length(bisShopConns)}].cooperatedShop"/></td>
					                <td>可合作门店</td>
					                <td><input type="text" name="bisShopConns[${fn:length(bisShopConns)}].toCooperateShop"/></td>
					            </tr>
					            <tr>
					                <td>经营区域/城市</td>
					                <td colspan="3"><input type="text" name="bisShopConns[${fn:length(bisShopConns)}].manageCity"/></td>
					            </tr>
					            <tr>
					                <td>主要合作条件</td>
					                <td colspan="3"><input type="text"
					                                       name="bisShopConns[${fn:length(bisShopConns)}].conditionForCooperation"/></td>
					            </tr>
					            <tr>
					                <td>备注</td>
					                <td colspan="3"><input type="text" name="bisShopConns[${fn:length(bisShopConns)}].remark"/></td>
					            </tr>
					        </table>
					        <div><input type="button" title="新增" class="btn_blue" value="新增" onclick="newBisShopConn(this);" style="margin-top: 5px;padding-left:15px;padding-top: 1px;background: #0072bb url('${ctx}/resources/images/res/res_add.png') no-repeat scroll 5px center;"/>
					            <input type="button" title="取消" class="btn_red" style="display:none;margin-top: 5px;" value="取消" 
					                   onclick="cancelNewConn(this);"/>
					        </div>
					    </fieldset>
					</div>
					<div>
					    <fieldset>
					        <legend>&nbsp;&nbsp;5&nbsp;拜访记录&nbsp;<img src="${ctx}/resources/images/common/show_white.png"/></legend>
					        <table class="shop-table">
					            <col width="25%"/>
					            <col width="45%"/>
					            <col width="15%"/>
					            <col width="15%"/>
					            <s:iterator value="bisShopVisits" status="st">
					                <tr>
					                    <td>第<s:property value="#st.index+1"/>次洽谈情况</td>
					                    <td>
					                        <input type="text" name="bisShopVisits[<s:property value="#st.index"/>].visitContent"
					                               value="${visitContent}"/>
					                    </td>
					                    <td>洽谈时间</td>
					                    <td>
					                        <input type="text" name="bisShopVisits[<s:property value="#st.index"/>].visitTime"
					                               value='<s:date name="visitTime" format="yyyy-MM-dd"/>' onfocus="WdatePicker()"/>
					                    </td>
					                </tr>
					            </s:iterator>
					            <tr class="newTr_bisShopVisits" style="display:none;">
					                <td>第${fn:length(bisShopVisits)+1}次洽谈情况</td>
					                <td><input type="text" name="bisShopVisits[${fn:length(bisShopVisits)}].visitContent"/></td>
					                <td>洽谈时间</td>
					                <td>
					                    <input type="text" name="bisShopVisits[${fn:length(bisShopVisits)}].visitTime"
					                           onfocus="WdatePicker()"/>
					                </td>
					            </tr>
					        </table>
					
					        <div class="foot-ellipsis"></div>
					        <div><input type="button" title="新增" class="btn_blue" value="新增" style="margin-top: 5px;padding-left:15px;padding-top: 1px;background: #0072bb url('${ctx}/resources/images/res/res_add.png') no-repeat scroll 5px center;"
					                    onclick="newBisShopChild('newTr_bisShopVisits',this);"/>
					            <input type="button" title="取消" class="btn_red" style="display:none;margin-top: 5px;" value="取消"
					                   onclick="cancelNewChild('newTr_bisShopVisits',this);"/>
					        </div>
				       </fieldset>
					</div>
					<div>
					    <fieldset>
					        <legend>&nbsp;&nbsp;6&nbsp;资信项目&nbsp;<img src="${ctx}/resources/images/common/show_white.png"/></legend>
					        <table class="shop-table">
					            <col width="15%"/>
					            <col width="35%"/>
					            <col width="15%"/>
					            <col width="35%"/>
					            <s:iterator value="bisShopCredits" status="st">
					                <tr>
					                    <td>资料名称</td>
					                    <td><input type="text" name="bisShopCredits[<s:property value="#st.index"/>].inforName"
					                               value="${inforName}"/></td>
					                    <td>摘要说明</td>
					                    <td><input type="text" name="bisShopCredits[<s:property value="#st.index"/>].summaryDesc"
					                               value="${summaryDesc}"/></td>
					                </tr>
					                <tr>
					                    <td>资料时限</td>
					                    <td><input type="text" name="bisShopCredits[<s:property value="#st.index"/>].inforTime"
					                               value="<s:date name="inforTime" format="yyyy-MM-dd"/>"/></td>
					                    <td>附件</td>
					                    <td>
						  <span class="shop_query">
						      <img onclick="showAttachment('${attachId}')" title="请上传图片"
					                  <s:if test="attachId==null||attachId==''">
					                      src="${ctx}/resources/images/common/atta.gif"
					                  </s:if> <s:else> src="${ctx}/resources/images/common/atta_y.gif" </s:else>
					                  />
						   </span>
						  <span class="shop_edit">
						    <input type="hidden" id="attachId<s:property value="#st.index"/>"
					               name="bisShopCredits[<s:property value="#st.index"/>].attachId" value="${attachId}"/>
						  <img id="attach_id<s:property value="#st.index"/>"
					           onclick="openAttachment(this,'${attachId}','attachId<s:property value="#st.index"/>','attach_id<s:property value="#st.index"/>')"
					           title="请上传图片"
					              <s:if test="attachId==null||attachId==''">
					                  src="${ctx}/resources/images/common/atta.gif"
					              </s:if> <s:else> src="${ctx}/resources/images/common/atta_y.gif" </s:else>
					              />
						  </span>
					                    </td>
					                </tr>
					                <tr>
					                    <td colspan="4"></td>
					                </tr>
					            </s:iterator>
					            <tr class="newTr_bisShopCredits" style="display:none;">
					                <td>资料名称</td>
					                <td><input type="text" name="bisShopCredits[${fn:length(bisShopCredits)}].inforName"/></td>
					                <td>摘要说明</td>
					                <td><input type="text" name="bisShopCredits[${fn:length(bisShopCredits)}].summaryDesc"/></td>
					            </tr>
					            <tr class="newTr_bisShopCredits" style="display:none;">
					                <td>资料时限</td>
					                <td><input type="text" name="bisShopCredits[${fn:length(bisShopCredits)}].inforTime"
					                           onfocus="WdatePicker();"/></td>
					                <td>附件</td>
					                <td>
					                    <input type="hidden" id="attachId${fn:length(bisShopCredits)}"
					                           name="bisShopCredits[${fn:length(bisShopCredits)}].attachId"/>
					                    <img id="attach_id${fn:length(bisShopCredits)}"
					                         onclick="openAttachment(this,'','attachId${fn:length(bisShopCredits)}','attach_id${fn:length(bisShopCredits)}')"
					                         title="请上传图片" src="${ctx}/resources/images/common/atta.gif"/></td>
					            </tr>
					        </table>
					        <div class="foot-ellipsis"></div>
					        <div><input type="button" title="新增" class="btn_blue" value="新增" style="margin-top: 5px;padding-left:15px;padding-top: 1px;background: #0072bb url('${ctx}/resources/images/res/res_add.png') no-repeat scroll 5px center;"
					                    onclick="newBisShopChild('newTr_bisShopCredits',this);"/>
					            <input type="button" title="取消" class="btn_red" style="display:none;margin-top: 5px;" value="取消"
					                   onclick="cancelNewChild('newTr_bisShopCredits',this);"/>
					        </div>
						</fieldset>
					</div>
				    </security:authorize>
				    
				    
					<%-- <security:authorize ifAnyGranted="A_SHOP_LEVEL_QUER"> --%>
					<%-- 具有二级权限和全部权限的人才能查看审核信息 --%>
					<security:authorize ifAnyGranted="A_SHOP_SECOND_VIEW,A_SHOP_ALL_VIEW,A_SHOP_LEVEL_QUER">
					    <div id="auditDiv" style="display:none;">
					        <fieldset>
					            <legend>&nbsp;&nbsp;&nbsp;审核信息&nbsp;<img src="${ctx}/resources/images/common/show_white.png"/></legend>
					            <table class="shop-table">
					            	<tr>
					                    <td colspan="2">考察等级</td>
					                    <td colspan="5">
							             	<span style="float: left;" id="investigaionLevel">
							             		<p:code2name mapCodeName="mapShopInvestLevel" value="investigationLevel" />
							             	</span>
					                    	<security:authorize ifAnyGranted="A_SHOP_SECOND_VIEW,A_SHOP_ALL_VIEW">
							                 	<span><s:select list="mapShopInvestLevel" listKey="key" listValue="value" id="investigationLevel"
				                                        name="investigationLevel" cssClass="shop_edit" style="width:100%;"/></span>
				                            </security:authorize>
					                    </td>
					                    <td></td>
					                </tr>
					            	<%--
					                <tr>
					                    <td colspan="2">资料验证</td>
					                    <td colspan="5">
					                        <input type="text" value="<p:code2name mapCodeName='mapInfoVeri' value='supInfoVeri' />"
					                               class="shop_query"/>
					                        <span><s:select list="mapInfoVeri" listKey="key" listValue="value" id="selecInfoVeriCd"
					                                        name="supInfoVeri" cssClass="shop_edit" style="width:100%;"/></span></td>
					                    <td></td>
					                </tr>
					                <tr>
					                    <td colspan="2">现场考察结果</td>
					                    <td colspan="5">
					                        <input type="text" value="<p:code2name mapCodeName='mapExamResu' value='supExamResu' />"
					                               class="shop_query"/>
					                        <span><s:select list="mapExamResu" listKey="key" listValue="value" id="selecSupExamResu"
					                                        name="supExamResu" cssClass="shop_edit" style="width:100%;"/></span>
					                    </td>
					                    <td>
										   <span class="shop_query">
										   		<img onclick="showAttachment('${examResuAttch}')" title="考察结果附件"
								                      <s:if test="examResuAttch==null||examResuAttch==''">
								                          src="${ctx}/resources/images/common/atta.gif"
								                      </s:if>
								                      <s:else>
								                       	  src="${ctx}/resources/images/common/atta_y.gif" 
								                      </s:else>
									              />
											</span>
											<span class="shop_edit">
												<input type="hidden" id="examResuAttch" name="examResuAttch" value="${examResuAttch}"/>
											    <img id="resu_img" onclick="openAttachment(this,'${examResuAttch}','examResuAttch','resu_img')"
									                 title="请上传考察结果附件"
									                 <s:if test="examResuAttch==null||examResuAttch==''">src="${ctx}/resources/images/common/atta.gif"</s:if>
									                 <s:else>src="${ctx}/resources/images/common/atta_y.gif"</s:else>
									             />
											</span>
					                    </td>
					                </tr>
					                <tr>
					                    <td colspan="2">考察人员</td>
					                    <td colspan="5">
					                        <input type="text" name="examResuPerson" value="${examResuPerson}"/>
					                    </td>
					                    <td></td>
					                </tr>
					                <tr>
					                    <td colspan="2">考察内容</td>
					                    <td colspan="5">
					                        <input type="text" name="examResuContent" value="${examResuContent}"/>
					                    </td>
					                    <td></td>
					                </tr>
					                <tr>
					                    <td colspan="2">考察等级</td>
					                    <td colspan="5">
					                    	<input type="text" value="<p:code2name mapCodeName='mapShopInvestLevel' value='investigationLevel' />"
					                               class="shop_query"/>
					                        <span><s:select list="mapShopInvestLevel" listKey="key" listValue="value" id="investigationLevel"
					                                        name="investigationLevel" cssClass="shop_edit" style="width:100%;"/></span>
					                    </td>
					                    <td></td>
					                </tr>
					                <tr>
					                    <td colspan="2">合作档案</td>
					                    <td colspan="5">
					                        <input type="text" name="cooperateArchives" value="${cooperateArchives}"/>
					                    </td>
					                    <td></td>
					                </tr>
					                <tr>
					                    <td colspan="2">履约评估</td>
					                    <td colspan="5">
					                        <input type="text" value="<p:code2name mapCodeName='mapEval' value='supEvaluate' />"
					                               class="shop_query"/>
					                        <span><s:select list="mapEval" listKey="key" listValue="value" id="selectBisShopEval"
					                                        name="supEvaluate" cssClass="shop_edit" style="width:100%;"/></span>
					                    </td>
					                    <td>
										   <span class="shop_query">
											      <img onclick="showAttachment('${supevalattach}')" title="履约评估附件"
									                      <s:if test="supevalattach==null||supevalattach==''">
									                          src="${ctx}/resources/images/common/atta.gif"
									                      </s:if> <s:else> src="${ctx}/resources/images/common/atta_y.gif" </s:else>
									                      />
											   </span>
											  <span class="shop_edit">
											  <input type="hidden" id="supevalattach" name="supevalattach" value="${supevalattach}"/>
											    <img id="eval_img" onclick="openAttachment(this,'${supevalattach}','supevalattach','eval_img')"
									                 title="请上传履约评估附件"
									                 <s:if test="supevalattach==null||supevalattach==''">src="${ctx}/resources/images/common/atta.gif"</s:if>
									                 <s:else>src="${ctx}/resources/images/common/atta_y.gif"</s:else>
									                    />
											  </span>
					                    </td>
					                </tr>
					                <tr>
					                    <td colspan="2">品牌级别</td>
					                    <td colspan="5">
					                        <input type="text" value="<p:code2name mapCodeName='mapEvaluate' value='supManaEval' />"
					                               class="shop_query"/>
					                        <span><s:select list="mapEvaluate" listKey="key" listValue="value" id="selectBisShopEval"
					                                        name="supManaEval" cssClass="shop_edit" style="width:100%;"/></span>
					                    </td>
					                    <td>
								    	 	<span class="shop_query">
										      	<img onclick="showAttachment('${supManaEvalAttach}')" title="履约评估附件"
								                      <s:if test="supManaEvalAttach==null||supManaEvalAttach==''">
								                          src="${ctx}/resources/images/common/atta.gif"
								                      </s:if> <s:else> src="${ctx}/resources/images/common/atta_y.gif" </s:else>
								                      />
										   </span>
										  <span class="shop_edit">
										  		<input type="hidden" id="supManaEvalAttach" name="supManaEvalAttach" value="${supManaEvalAttach}"/>
										    	<img id="manaEval_img"
								                 onclick="openAttachment(this,'${supManaEvalAttach}','supManaEvalAttach','manaEval_img')"
								                 title="请上传履约评估附件"
								                 <s:if test="supManaEvalAttach==null||supManaEvalAttach==''">src="${ctx}/resources/images/common/atta.gif"</s:if>
								                 <s:else>src="${ctx}/resources/images/common/atta_y.gif"</s:else>
								                    />
										  </span>
					                    </td>
					                </tr>
					                --%>
					            </table>
					            <div class="foot-ellipsis"></div>
					        </fieldset>
					    </div>
					</security:authorize>
				</div>
			</td>
		</tr>
	</table>
</div>
</form>

<script type="text/javascript">
$(function () {
    var id = $("#bisShopId").val();
    $(" td:not(:text)").addClass("td_bold");
    if ("" == id) {
        editShop("");
        if ($('#bisProjectName')) {
            $('#bisProjectName').onSelect({
                muti:false,
                callback:function (project) {
                    $("#bisProjectName").val(project.projectName);
                    $("#bisProjectCd").val(project.orgCd);
                }
            });
        }
    } else {
        //搜索
        sortTreeByQuery(id, "sortInputDiv");
        $('#tipStatus').text('查看/不可编辑');
        var auditStat = $('#bisShopAudit').val();
        var deleteBl = $('#deleteBl').val();
        if ("2" == deleteBl) {
            $('#auditStat').text('已合并');
        } else {
            if (auditStat == 0) {
                $('#auditStat').text('未提交');
            } else if (auditStat == 1) {
                $('#auditStat').text('待审核');
            } else if (auditStat == 2) {
                $('#auditStat').text('预审核');
            } else if (auditStat == 3) {
                $('#auditStat').text('已审核');
                //若为已审核，则不能编辑
                $("#btn_edit").hide();
            }
        }
        $(':text,textarea').attr('readonly', 'readonly');
        $(".shop_edit").hide();
        $("#btn_cancel,.btn_orange").hide();
    }
    //若为主、次主力店，则显示审核部分
    var manageCd = $("#selecManageCd").val();
    if ("1" == manageCd || "2" == manageCd) {
        $("#auditDiv").show();
    }
    $('.foot-ellipsis').prev().prev().click(
            function () {
                var thi = $(this);
                var _this = thi.next().next();
                if (_this.css("display") == "none") {
                    _this.show().prev().hide();
                    thi.find("img").attr("src", "${ctx}/resources/images/common/hide_white.png");
                } else {
                    _this.hide().prev().show();
                    thi.find("img").attr("src", "${ctx}/resources/images/common/show_white.png");
                }
                autoHeight();
            });
    var main_height = $("#div_main_cont").height();
    $("#sortMarginDiv,#sortInputDiv").css("height", main_height);
});
function editShop(id) {
    $("#sortInputDiv").text("");
    if ("" == id) {
        //若新增，则加载树
        loadSortTree(true, true, false, "sortInputDiv");
    } else {
        $('#tipStatus').text('修改/可编辑');
        loadTreeCheckSelect(id, "sortInputDiv");
    }
    $(':text:not(.typeRead),textarea').css('backgroundColor', '#DBE5F1').attr('readOnly', '');
    $('input[alt="amount"]').live('keyup', function () {
        clearNoNum_1(this);
        if ($('.percent').val() > 100) {
            this.value = 0;
        }
    });
    if ($('#selecManageCd').val() == 1) {
        $(".build_required").addClass("required");
    }
    $("#btn_edit").hide();
    $("#btn_save,#btn_cancel,#btn_sub,#btn_audit").show();
    $(".shop_edit,.btn_orange").show();
    $(".shop_query").hide();
    $(".supplierAttr").each(function (i, dom) {
        $(dom).val($(dom).prev().prev().val());
    });
	<security:authorize ifAnyGranted="A_SHOP_SECOND_VIEW,A_SHOP_ALL_VIEW">
 		$("#investigaionLevel").hide();//考察等级
	</security:authorize>
}
function newBisShopConn(dom) {
    $("#conn_new").show();
    $("#partName").attr("class","required");
    $(dom).hide().next().show();
    return false;
}
function cancelNewConn(dom) {
    $("#conn_new").hide();
    $("#partName").attr("class","");
    $(dom).hide().prev().show();
    return false;
}
function newBisShopChild(newTr, dom) {
    $("." + newTr).show();
    $(dom).hide().next().show();
    return false;
}
function cancelNewChild(newTr, dom) {
    $("." + newTr).hide();
    $(dom).hide().prev().show();
    return false;
}
/**
 * 取消编辑或新增，还原页面到初始化状态
 */
function cancelBis() {
    window.location.href = window.location.href;
}
function openAttachment(dom, entityId, hiddenId, showImgId, title) {
    var _title = title || '上传附件';
    if (!entityId || entityId == '') {
        if ($.trim($('#' + hiddenId).val()) == '') {
            var curr_user_cont = '<%= SpringSecurityUtils.getCurrentUiid()%>';
            entityId = 'shop_' + curr_user_cont + '_' + String((new Date().getTime()) ^ Math.random());
            $('#' + hiddenId).val(entityId);
        } else {
            entityId = $('#' + hiddenId).val();
        }
    }
    ymPrompt.win({
        message:"${ctx}/app/app-attachment!list.action?bizEntityId=" + entityId + "&bizModuleCd=bisShop&filterType=image|office",
        width:500,
        height:300,
        title:_title,
        afterShow:function () {
        },
        iframe:true,
        handler:function (e) {
            param = {attachId:entityId};
            $.post("${ctx}/bis/bis-shop!queryAttach.action", param, function (result) {
                if ("1" == result) {
                    $("#" + showImgId).attr("src", "${ctx}/resources/images/common/atta_y.gif");
                } else {
                    $("#" + showImgId).attr("src", "${ctx}/resources/images/common/atta.gif");
                }
            });

        }
    });
}
function showAttachment(entityId) {
    ymPrompt.win({
        message:"${ctx}/app/app-attachment!list.action?bizEntityId=" + entityId + "&bizModuleCd=bisShop&filterType=image|office&onlyShow=true",
        width:500,
        height:300,
        title:'附件查看',
        afterShow:function () {
        },
        iframe:true
    });
}
function doAttributeCd(dom) {
    $(dom).prev().val($(dom).val());
}
function selectCooperated() {
    ymPrompt.confirmInfo({
        icoCls:"",
        title:"合作区域",
        message:"<div id='parentDiv'></div>",
        useSlide:true,
        winPos:"c",
        width:500,
        height:300,
        allowRightMenu:true,
        showMask:false,
        afterShow:function () {
            $.post("${ctx}/bis/bis-shop!getBisCooperative.action", function (result) {
                result = eval(result);
                var code = $("#inputSeleCoop").prev().val();
                var codeStr = "";
                if (code.length > 0) {
                    coopMult = code;
                    codeStr = code.split(",");
                }

                $.each(result, function (i, node) {
                    for (var a in node) {
                        var typeName = '<div onclick="selectCoopMult(this,' + a + ',\'' + node[a] + '\');"';
                        typeName = typeName + ' style="cursor:pointer;float:left;height:26px; line-height:26px; width:50px; font-size:14px; font-weight:bolder; margin-bottom:2px;';
                        if (codeStr.length > 0) {
                            for (var j = 0; j < codeStr.length; j++) {
                                if (a == codeStr[j]) {
                                    typeName = typeName + 'background-color:#6EB1CF";';
                                    if (coopMultName.indexOf(node[a] + ",") < 0) {
                                        coopMultName = coopMultName + node[a] + ",";
                                    }
                                    break;
                                }
                            }
                        }
                        typeName = typeName + '">' + node[a] + '</div>';
                        // if(i%2==1){
                        //   typeName=typeName+'<br>';
                        // }
                        $("#parentDiv").append(typeName);
                    }
                });
            });
        },
        handler:function (e) {
            if ("ok" == e) {
                $("#inputSeleCoop").val(coopMultName.substring(1, coopMultName.length - 1));
                $("#inputSeleCoop").prev().val(coopMult.substring(coopMult.substring(1, coopMult.length - 1)));
            }
        }
    });
}
var coopMult = ",";
var coopMultName = ",";
function selectCoopMult(dom, area, areaName) {
    if (coopMult.indexOf("," + area + ",") < 0) {
        //未有则选中
        $(dom).css("background-color", "#6EB1CF");
        coopMult = coopMult + area + ",";
        coopMultName = coopMultName + areaName + ",";
    } else {
        //有则不选中
        $(dom).css("background-color", "#ffffff");
        coopMult = coopMult.replace("," + area + ",", ",");
        coopMultName = coopMultName.replace("," + areaName + ",", ",");
    }
}
function doDeleteConn(connId) {
    $.post("${ctx}/bis/bis-shop!deleteConn.action", {deleteId:connId}, function (result) {
        if (result) {
            if ("1" == result) {
                alert("删除成功");
                doBisShop($("#bisShopId").val());
            } else {
                alert("该代理商已被合同引用，不能删除");
            }
        }
    });
}
function existsShopList(dom) {
    var value = $(dom).val();
    var id = $("#bisShopId").val();
    if ("" != value)
        $.post("${ctx}/bis/bis-shop!existsShopList.action", {nameCn:value, id:id}, function (result) {
            result = eval(result);
            var htmlL = '';
            $.each(result, function (i, n) {
                var h = "";
                for (var j = 0; j < 1; j++) {
                    if (h != "") {
                        h = h + "|";
                    }
                    //h=h+"<span>"+n[1]+"</span>";
                }
                var html = "<div style='border-bottom: 1px solid #fff;cursor: pointer;'>";
                for (var i in n) {
                    html = html + n[i];
                }
                htmlL += html + "</div>";
            });
            if ($("#searchYmpDiv").length <= 0 || $("#ym-window").css('display') == 'none') {
                ymPrompt.win({
                    icoCls:"",
                    title:"现有相同名称商家提示",
                    message:'<div id="searchYmpDiv"></div>',
                    useSlide:true,
                    winPos:"c",
                    width:300,
                    height:200,
                    allowRightMenu:true,
                    showMask:false
                });
            }
            $("#searchYmpDiv").html(htmlL);
        });
}
function mergeQuery(id) {
    var url = "${ctx}/bis/bis-shop!mergeQuery.action?mergeId=" + id;
    parent.TabUtils.newTab("bis-shop-merge", "商家合并", url, true);
}
function mergeQueryByOne(id) {
    if (id) {
        inputUrl = _ctx + "/bis/bis-shop!mergeQueryByOne.action?id=" + id;
        parent.TabUtils.newTab("bis-shop-mergeInput", "合并查看", inputUrl, true);
    }
}
function updateCreator(id) {
    if (id) {
        var salesMan = $("#salesmanSpan").text();
        var mesHtml = '<div id="searchUpdateDiv" style="padding-left:50px;padding-top:10px;">' +
                '<div style="margin:10px;">当前业务员：<input type="text"  value="' + salesMan + '" readonly="readonly" /></div>' +
                '<div style="margin-left:10px;margin-top:10px;">更改业务员：<input type="text" onkeyup="queryUser(this);" id="selectedUserName" /></div>' +
                '<div id="searchDiv" style="display:none;padding:3px 5px;margin-left:82px;position: absolute;z-index:9999999;border: 1px solid gray;background-color: #e8e8e8"></div>' +
                '<input type="hidden" id="selectedUiid" />' +
                '<div style="margin-top:20px;"><input type="button" class="btn_blue" style="width:150px;" value="仅替换当前商家资源" onclick="updateSalesmanByOne(\'' + id + '\',\'selectedUiid\');"/> ' +
                '<input type="button" class="btn_blue" style="width:150px;" value="替换该用户所有商家资源" onclick="updateSalesmanByAll(\'' + id + '\',\'selectedUiid\');"/> </div>' +
                '</div>';
        ymPrompt.win({
            icoCls:"",
            title:"更改业务员",
            message:mesHtml,
            useSlide:true,
            winPos:"c",
            width:400,
            height:300,
            allowRightMenu:true,
            showMask:false
        });
    }
}
/**
 * 快速搜索用户
 */
function queryUser(dom) {
    if ("" != $(dom).val()) {
        $.post("${ctx}/bis/bis-shop!quickSearchUser.action", {value:$(dom).val()}, function (result) {
            result = eval(result);
            var htmlL = '';
            $.each(result, function (i, n) {
                var html = "<div id='" + n['uiid'] + "' style='padding-left:5px;font-size:12px;border: 1px solid #fff;cursor: pointer;' onclick='selectedUiid(this);'>";
                htmlL += html + n['uiid'] + "|" + n['userName'] + "</div>";//+"</div>";
                //html = "|<div id='"+n['userName']+"' style='padding-left:5px;font-size:12px;border: 1px solid #fff;cursor: pointer;'>";
                //htmlL +="|"+html+n['userName'];"</div>";

            });
            //$('#tmpSearch').append('');
            $("#searchDiv").show().html(htmlL);
        });
    }
    //$(dom).quickSearch( _ctx+"/res/res-approve-info!queryAppAuth.action",['resNo'],{resNo:'tmpSearch',resId:'tmpSearchId'},{},showTemSearch);
}
function selectedUiid(dom) {
    if ($(dom).attr("id")) {
        $("#selectedUiid").val($(dom).attr("id"));
        var a = $(dom).text().split("|");
        $("#selectedUserName").val(a[1]);
        $("#searchDiv").hide();
    }
}
function updateSalesmanByOne(id, selectedUiid) {
    var selectedId = $("#" + selectedUiid).val();
    $.post("${ctx}/bis/bis-shop!updateSalesmanByOne.action", {id:id, selectedUiid:selectedId}, function (result) {
        if ("ok" == result) {
            alert("操作成功");
            ymPrompt.close();
            //刷新页面
            var url = "${ctx}/bis/bis-shop!input.action?id=" + id;
            parent.TabUtils.newTab("bis-shop-input", "商家信息", url, true);
        } else {
            alert("操作不成功,请选择正确业务员");
        }
    });
}
function updateSalesmanByAll(id, selectedUiid) {
    var selectedId = $("#" + selectedUiid).val();
    $.post("${ctx}/bis/bis-shop!updateSalesmanByAll.action", {id:id, selectedUiid:selectedId}, function (result) {
        if ("ok" == result) {
            alert("操作成功");
            ymPrompt.close();
            //刷新页面
            var url = "${ctx}/bis/bis-shop!input.action?id=" + id;
            parent.TabUtils.newTab("bis-shop-input", "商家信息", url, true);
        } else {
            alert("操作不成功,请选择正确业务员");
        }
    });
}

/**
 * 选择省市
 */
function selectArea(){
	ymPrompt.confirmInfo( {
		icoCls : "",
		autoClose:false,
		message : "<div id='selectAreaDiv'><img align='absMiddle' src='"
			+ _ctx + "/images/loading.gif'></div>",
		width : 450,
		height : 500,
		title : "选择省份",
		afterShow : function() {
			var url = _ctx+"/bis/bis-shop!getCooperateAreaList.action";
			var cooperateArea = $("#cooperateArea").val();
			var cooperateAreaCd = $("#cooperateAreaCd").val();
			var data = {cooperateAreaCd:cooperateAreaCd,cooperateArea:cooperateArea};
			$.post(url, data, function(result) {
				$("#selectAreaDiv").html(result);
			});
		},
		handler : function(btn){
			if(btn=='ok'){
				//选中的省份
				var provinceName = $('#provinceName').val();
				var provinceNameCd = $('#provinceNameCd').val();
				if(provinceName == null || provinceName =='' || provinceNameCd == null || provinceNameCd == ''){
					alert('请选择省份');
					return false;
				}
				$("#cooperateAreaCd").val(provinceNameCd);
				$("#cooperateArea").val(provinceName);
			}
			ymPrompt.close();
		},
		btn:[["确定",'ok'],["退出",'cancel']]
	});
}
</script>
</body>
</html>