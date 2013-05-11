Validate = function(formId) {
	this.formId = formId;
	this.errorClass = "error";
	this.ruleReg = /^(\w){3,10}/;
	this.requiredReg = /required$/;
	this.limitReg = /\[(.*)\]/ig;
	var validateFunction = this.validate;
	// $("#" + formId).bind("submit",function() {
	// return validate();
	// });
};

Validate.prototype = {
	validate : function() {
		var flag = true;
		var validateElementFunction = this.validateElement;
		var regsRuleTmp = this.ruleReg;
		var regsRequiredTmp = this.requiredReg;
		var limitRegTmp = this.limitReg;
		var errorClassTmp = this.errorClass;
		$("#" + this.formId + " [validate]").not(".inputBorder_readOnly")
				.each(
						function(i) {
							var validateStr = $(this).attr("validate");
							//不校验隐藏元素
							var isParentHidden=false;
							$(this).parents().each(function(){
								var t = $(this).css("display");
								if( t == 'none'){
									isParentHidden=true;
									return;
								}
							});
							
							if (!isParentHidden){
								if (String.isNotEmpty(validateStr)) {
									var val=$(this).attr("value");
									if (flag) {
										flag = validateElementFunction(val, validateStr, this,
												regsRuleTmp, regsRequiredTmp,
												limitRegTmp, errorClassTmp);
									} else {
										validateElementFunction(val,
												validateStr, this, regsRuleTmp,
												regsRequiredTmp, limitRegTmp,
												errorClassTmp);
									}
								}
							}
						});
		return flag;
	},
	validateElement : function(value, validateStr, dom, regsRuleTmp,
			regsRequiredTmp, limitRegTmp, errorClassTmp) {
		var flag = true;
		if (String.isNotEmpty(validateStr)) {
			$(dom).removeClass(errorClassTmp);
			$(dom).css("color","");
			$(dom).css("border","");
			$(dom).removeAttr("title");
			var regsRule = regsRuleTmp.exec(validateStr);
			var rule = regsRule[0];
			var isRequired = regsRequiredTmp.test(validateStr);
			var limitRule = limitRegTmp.exec(validateStr);
			limitRegTmp.exec(validateStr);
			var min, max, scale, minValue;
			if (String.isNotEmpty(limitRule)) {
				var limitsTmp = limitRule[1];
				var arrayTmp = limitsTmp.split(",");
				min = arrayTmp[0];
				max = arrayTmp[1];
				if (arrayTmp.length >= 3) {
					scale = arrayTmp[2];
				}
				if (arrayTmp.length == 4) {
					minValue = arrayTmp[3];
				}
			}
			var msg = "";
			if (isRequired) {
				var checkedSize=$(dom).find("input[type='checkbox']").length;
				var checkedLength=$(dom).find("input[type='checkbox'][checked=true]");
				if (checkedSize>0 && checkedLength.length==0){
					flag = false;
				}else if (checkedSize==0&&String.isEmpty($.trim(value))) {
					flag = false;
				}
				if(!flag){
					msg = "必输条件";
					$(dom).addClass(errorClassTmp);
					$(dom).css("color","red");
					$(dom).css("border","2px solid red");
					$(dom).removeClass('Wdate');
					$(dom).attr("title", msg);
					$(dom).focus();
				}
			}
			if (rule == "num") {
				var flg = false;
				if (typeof (scale) === "undefined") {
					flg = !ChkUtil.isDigitLen(value, min, max);
				} else {
					flg = !ChkUtil.isFloat(value, min, max, scale);
				}
				if (!flg && isNotEmpty(minValue)) {
					flg = !(value > minValue);
				}
				if (flg) {
					msg = " 只能输入数字" + "，长度必须在" + min + "," + max + "之间;";
					if(isNotEmpty(scale)){
						msg=msg+"最多"+scale+"位小数;";
					}
					if(isNotEmpty(minValue)){
						msg=msg+"且必须大于"+minValue+";";
					}
					flag = false;
					$(dom).addClass(errorClassTmp);
					$(dom).attr("title", msg);
				}
			} else if (rule == "date") {
				if (!ChkUtil.isDate(value)) {
					msg = " 格式必须为日期";
					flag = false;
					$(dom).addClass(errorClassTmp);
					$(dom).attr("title", msg);
				}
			} else if (rule == "len") {
				if (!ChkUtil.isStringLen(value, min, max)) {
					msg = "长度必须在" + min + "," + max + "之间;";
					flag = false;
					$(dom).addClass(errorClassTmp);
					$(dom).attr("title", msg);
				}
			}

		}
		return flag
	}
};
function ChkUtil() {
}
// 校验是否为空(先删除二边空格再验证)
ChkUtil.isNull = function(str) {
	if (null == str || "" == str.trim()) {
		return true;
	} else {
		return false;
	}
};
// 校验是否全是数字
ChkUtil.isDigit = function(str) {
	var patrn = /^\d+$/;
	return patrn.test(str);
};
ChkUtil.isDigitLen = function(str, min, max) {
	var patrn = new RegExp('^(\\d)' + '{' + min + ',' + max + '}$');
	return patrn.test(str);
};
// 校验是否是整数
ChkUtil.isInteger = function(str) {
	var patrn = /^([+-]?)(\d+)$/;
	return patrn.test(str);
};
// 校验是否为正整数
ChkUtil.isPlusInteger = function(str) {
	var patrn = /^([+]?)(\d+)$/;
	return patrn.test(str);
};
// 校验是否为负整数
ChkUtil.isMinusInteger = function(str) {
	var patrn = /^-(\d+)$/;
	return patrn.test(str);
};
// 校验是否为浮点数
ChkUtil.isFloat = function(str, min, max, scale) {
	// var patrn = /^([+-]?)\d*\.\d+$/;
	var patrn = new RegExp('^(\\d)' + '{' + min + ',' + max + '}'
			+ '\\.{0,1}\\d' + '{0,' + scale + '}' + '$');

	return patrn.test(str);
};
// 校验是否为正浮点数
ChkUtil.isPlusFloat = function(str) {
	var patrn = /^([+]?)\d*\.\d+$/;
	return patrn.test(str);
};
// 校验是否为负浮点数
ChkUtil.isMinusFloat = function(str) {
	var patrn = /^-\d*\.\d+$/;
	return patrn.test(str);
};
// 校验是否仅中文
ChkUtil.isChinese = function(str) {
	var patrn = /[\u4E00-\u9FA5\uF900-\uFA2D]+$/;
	return patrn.test(str);
};
// 校验是否仅ACSII字符
ChkUtil.isAcsii = function(str) {
	var patrn = /^[\x00-\xFF]+$/;
	return patrn.test(str);
};
// 校验手机号码
ChkUtil.isMobile = function(str) {
	var patrn = /^0?1((3[0-9]{1})|(59)){1}[0-9]{8}$/;
	return patrn.test(str);
};
// 校验电话号码
ChkUtil.isPhone = function(str) {
	var patrn = /^(0[\d]{2,3}-)?\d{6,8}(-\d{3,4})?$/;
	return patrn.test(str);
};
// 校验URL地址
ChkUtil.isUrl = function(str) {
	var patrn = /^http[s]?:\/\/[\w-]+(\.[\w-]+)+([\w-\.\/?%&=]*)?$/;
	return patrn.test(str);
};
// 校验电邮地址
ChkUtil.isEmail = function(str) {
	var patrn = /^[\w-]+@[\w-]+(\.[\w-]+)+$/;
	return patrn.test(str);
};
// 校验邮编
ChkUtil.isZipCode = function(str) {
	var patrn = /^\d{6}$/;
	return patrn.test(str);
};
// 校验合法时间
ChkUtil.isDate = function(str) {
	if (!/\d{4}(\.|\/|\-)\d{1,2}(\.|\/|\-)\d{1,2}/.test(str)) {
		return false;
	}
	var r = str.match(/\d{1,4}/g);
	if (r == null) {
		return false;
	}
	;
	var d = new Date(r[0], r[1] - 1, r[2]);
	return (d.getFullYear() == r[0] && (d.getMonth() + 1) == r[1] && d
			.getDate() == r[2]);
};
// 校验字符串：只能输入6-20个字母、数字、下划线(常用手校验用户名和密码)
ChkUtil.isString6_20 = function(str) {
	var patrn = /^(\w){6,20}$/;
	return patrn.test(str);
};
ChkUtil.isStringLen = function(str, min, max) {
	var length=str.cnLength();
	return (length>=min&&length<=max);
	//var patrn = new RegExp('^(\\w)' + '{' + min + ',' + max + '}$');
	//return patrn.test(str);
};
function isEmpty(str) {
	return (typeof (str) === "undefined" || str === null || (str.length === 0));
};
function isNotEmpty(str) {
	return (!String.isEmpty(str));
};