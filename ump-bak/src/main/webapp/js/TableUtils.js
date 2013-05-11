/**
 * @author shixy
 * @requires jquery.js jquery.form.pack.js
 * 本工具类是基于jQuery框架，中间AJAX部分引用了jquery.form插件，故在使用本工具时需要在前面引入jquery.js和jquery.form.pack.js
 * 用于表格事件的绑定、点击表格展示详细信息
 * 保存、修改、删除通用方法
 * 适用条件: 本工具类只适用于特定的规则页面
 * 	<table id="tableId">
 * 		<thead></thead>
 *		<tbody>
 *			<tr id="main_dataId" class="mainTr"><td>value</td></tr>
 *			<tr id="detail_dataId" class="detailTr"><td>
 *				<fieldSet>
 *					<form></form>
 *				</fieldSet>
 *			</td></tr>
 *		</tbody>
 *	</table> 
 * 具体可参照dly-schedule-task.jsp页面
 * 
 * 完善中......
 * 
 */
var TableUtils = {
	/**
	 * 给指定表格添加点击事件，点击时展开/收缩详细信息
	 * @params tableId  表格唯一ID
	 * @params picSrc 数组 表格第一列显示的图片  第一个元素为原始图片 第二个为点击后的图片
	 */
	bindTable : function(tableId, picSrc) {
		$("#" + tableId + " tbody tr.mainTr").toggle(function() {
			$(".foldTr").trigger("click");
			$(this).addClass("foldTr");
			if (picSrc)
				$(this).children().eq(0).children().attr("src", picSrc[0]);
			$(this).next().show();
		}, function() {
			$(this).removeClass("foldTr");
			if (picSrc)
				$(this).children().eq(0).children().attr("src", picSrc[1]);
			$(this).next().hide();
		});

		$("#" + tableId + " tbody tr.detailTr :input").keyup(
				function() {
					var name = $(this).attr("name");
					$(this).parents(".detailTr").prev().children("[id=" + name + "]").text($(this).val());
				});
	},
	/**
	 * 通用AJAX保存方法(只用于新增的保存)
	 * @params formId 需要提交的表单的Id
	 * @params divId  保存完需要刷新的DIV 的ID
	 * @params callback  可选类型：字符串和Function
	 * 			当传入字符串时字符串必须为刷新的表格的Id,用于刷新后重新绑定事件
	 * 			当传入Function时,用户定义的方法中可自行调用表格绑定事件以及其他的方法
	 */
	saveObject : function(formId, divId, callback) {
		$("#" + formId).ajaxSubmit(function(result) {
			$("#" + divId).html(result);
			if ($.isFunction(callback)){
				callback;
			}else{
				TableUtils.bindTable(callback);
			}
		});
	},
	/**
	 * 通用AJAX更新方法
	 * @params dataId 需要修改的数据行的Id
	 */
	updateObject : function(dataId) {
		$("#form_" + dataId).ajaxSubmit(function(result) {
			$("#form_" + dataId + " input[name=recordVersion]").val(result);
			$(".foldTr").trigger("click");
		});
	},
	/**
	 * 通用AJAX删除方法
	 * @params dataId  需要删除的数据行Id
	 * @params url 删除需要提交的路径
	 */
	deleteObject : function(dataId,url) {
		$.post(url, {id : dataId}, function(result) {
			if (result) {
				$("#main_" + dataId).remove();
				$("#detail_" + dataId).remove();
			}
		});
	}

}