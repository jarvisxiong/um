<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>

<div>
	<div class="title">查看固定资产</div>
	<div style="margin-left: 5px;margin-top: 5px;" align="left">
		<div onclick="addAssmAccount();" class="plred" style="height: 22px;width: 50px;background: none repeat scroll 0 0 #6EB1CF;;border: 1px solid #597125;">新增</div>
		<div onclick="loadAccount();" class="plred" style="height: 22px;width: 50px;background: none repeat scroll 0 0 #A5C560;;border: 1px solid #597125;">返回</div>
		<div onclick="updateAccount('${assmAccountId}');" class="plred" style="height: 22px;width: 50px;background: none repeat scroll 0 0 #A5C560;;border: 1px solid #597125;">修改</div>
		<div onclick="deleteAccount('${assmAccountId}');" class="plred" style="height: 22px;width: 50px;margin-left: 5px;">删除</div>
	</div>
	<table class="table_div" border="1" bordercolor="#E5DBCC">
		<col style="width: 100px;"/>
		<col/>
		<tr style="line-height: 30px;">
			<td align="right" style="padding-right: 5px;">
				商业公司：
				<input type="hidden" name="assmAccountId" id="assmAccountId" value="${entity.assmAccountId}"/>
			</td>
			<td style="padding-left: 5px;"><p:code2name mapCodeName="mapBisProject" value="projectCd"/></td>			
		</tr>
		<tr style="line-height: 30px;">
			<td align="right" style="padding-right: 5px;">
				设备型号：
			</td>
			<td style="padding-left: 5px;"><p:code2name mapCodeName="mapAssmModelAll" value="assmModel.assmModelId"/></td>			
		</tr>
		<tr style="line-height: 30px;">
			<td align="right" style="padding-right: 5px;">
				设备型号编码：
			</td>
			<td style="padding-left: 5px;">${entity.assmCode}</td>			
		</tr>
		<tr style="line-height: 30px;">
			<td align="right" style="padding-right: 5px;">
				资产编码：
			</td>
			<td style="padding-left: 5px;">${entity.code}</td>
		</tr>
		<tr style="line-height: 30px;">
			<td align="right" style="padding-right: 5px;">
				资产名称：
			</td>
			<td style="padding-left: 5px;">${entity.assmName}</td>			
		</tr>
		<tr style="line-height: 30px;">
			<td align="right" style="padding-right: 5px;">
				使用部门：
			</td>
			<td style="padding-left: 5px;"><%= CodeNameUtil.getDeptNameByCd(JspUtil.findString("entity.useDepartment"))%></td>			
		</tr>
		<tr style="line-height: 30px;">
			<td align="right" style="padding-right: 5px;">
				使用情况：
			</td>
			<td style="padding-left: 5px;"><p:code2name mapCodeName="mapUseStatus" value="useStatus"/></td>			
		</tr>
		<tr style="line-height: 30px;">
			<td align="right" style="padding-right: 5px;">
				存放地点：
			</td>
			<td style="padding-left: 5px;"><p:code2name mapCodeName="mapStorageSites" value="storageSites"/></td>			
		</tr>
		<tr style="line-height: 30px;">
			<td align="right" style="padding-right: 5px;">
				增加方式：
			</td>
			<td style="padding-left: 5px;"><p:code2name mapCodeName="mapAddWays" value="addWay"/></td>			
		</tr>
		<tr style="line-height: 30px;">
			<td align="right" style="padding-right: 5px;">
				保管人员：
			</td>
			<td style="padding-left: 5px;">${keeperName}</td>			
		</tr>
		<tr style="line-height: 30px;">
			<td align="right" style="padding-right: 5px;">
				原值&nbsp;(元)：
			</td>
			<td style="padding-left: 5px;">${entity.srcValue}</td>			
		</tr>		
		<tr style="line-height: 30px;">
			<td align="right" style="padding-right: 5px;">
				折旧年限：
			</td>
			<td style="padding-left: 5px;">${entity.depreYeadNum}</td>			
		</tr>		
		<tr style="line-height: 30px;">
			<td align="right" style="padding-right: 5px;">
				使用时间：
			</td>
			<td style="padding-left: 5px;"><s:date name="entity.useDate" format="yyyy年MM月dd日"/></td>			
		</tr>		
		<tr style="line-height: 30px;">
			<td align="right" style="padding-right: 5px;">
				折旧方式：
			</td>
			<td style="padding-left: 5px;">${entity.depreWay}</td>			
		</tr>		
		<tr style="line-height: 30px;">
			<td align="right" style="padding-right: 5px;">
				残&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;值：
			</td>
			<td style="padding-left: 5px;">${entity.remainVal}</td>			
		</tr>		
		<tr style="line-height: 30px;">
			<td align="right" style="padding-right: 5px;">
				当前配置：
			</td>
			<td style="padding-left: 5px;">${currNum}</td>			
		</tr>		
		<tr style="line-height: 30px;">
			<td align="right" style="padding-right: 5px;">
				标准配置：
			</td>
			<td style="padding-left: 5px;">${stanNum}</td>			
		</tr>		
	</table>
</div>
