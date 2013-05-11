<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@ include file="/common/meta.jsp"%>
<meta http-equiv="Content-Type" content="text/html" />
<link rel="stylesheet" href="${ctx}/css/common.css" type="text/css" />


<s:if test="assistants==0">
			<span>没有已经设定的助理</span>	
		</s:if>
		<s:else>
			<div>已设定的助理</div>
			<div>
				<table>
					<thead>
						<tr>
							<th>助理</th>
							<th>权责分配</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="assistants" var="tmpAssistant">
							<tr>
								<td>
									<s:property value="tmpAssistant.accUserName"/>
								</td>
								<td>
									<s:property value="tmpAssistant.isJbpm"/>
									<s:property value="tmpAssistant.isFile"/>
									<s:property value="tmpAssistant.isRes"/>
								</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
		</s:else>