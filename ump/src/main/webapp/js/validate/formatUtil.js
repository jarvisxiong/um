//金额格式,3位逗号隔开
function formatMoney(s, n) {
	n = n > 0 && n <= 20 ? n : 2;
	s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
	var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1];
	t = "";
	for (i = 0; i < l.length; i++) {
		t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
	}
	var rtn=t.split("").reverse().join("");
	if (n>0){
		rtn=rtn+ "." + r;
	}
	return rtn;
}

function formatNumber(s){
	s = parseInt((s + "").replace(/[^\d-]/g, ""))+"";
	var l = s.split("").reverse();
	//.reverse();
	t = "";
	for (i = 0; i < l.length; i++) {
		t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
	}
	var rtn=t.split("").reverse().join("");
	return rtn;
}

function formatNumber1(srcEle) {
	var s = $.trim(srcEle.val());
	if (!parseFloat(s)&&s!='0') {
		srcEle.val("");//原值0.00
	} else {
		s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(2) + "";
		var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1];
		t = "";
		for (i = 0; i < l.length; i++) {
			t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "" : "");
		}
		var rtn=t.split("").reverse().join("");
		rtn=rtn+ "." + r;
		if(rtn.length>10){
			srcEle.val("");
			alert('金额须小于10位');
			return;
		}
		srcEle.val(rtn);
	}
}
// 数值格式
function formatFloat(s) {
	return parseFloat(s.replace(/[^\d\.-]/g, ""));
}
// 数值格式
function formatRate(eleId) {
	var v = $("#" + eleId).val();
	if (!v) {
		v = 0;
	} else {
		v = v.replace(/[^\d\.-]/g, "");
		v = parseFloat(v);
	}
	$("#" + eleId).val(v + "%");
}
// 格式化文本输入框输入的数字
function formatVal(srcEle) {
	var v = $.trim(srcEle.val());
	if (!parseFloat(v)&&v!='0') {
		srcEle.val("");//原值0.00
	} else {
		srcEle.val(formatMoney(v, 2));
	}
}
function formatNum(srcEle){
	var v = $.trim(srcEle.val());
	if (!parseFloat(v)) {
		srcEle.val("");
	} else {
		srcEle.val(formatNumber(v));
	}
}
// 格式化文本输入框输入的数字
function formatValById(srcEleId) {
	var v = $("#" + srcEleId).val();
	if (isEmpty()) {
	} else {
		v = v.replace(/[^\d\.-]/g, "");
		v = parseFloat(v);
		srcEle.val(formatMoney(v, 2));
	}

}
// 解析文本框输入的数值为浮点数
function getVal(eleId) {
	return $.trim($("#" + eleId).val()) == "" ? 0.00 : formatFloat($(
			"#" + eleId).val());
}

function getValByJ(_dom) {
	return $.trim(_dom.val()) == "" ? 0.00 : formatFloat(_dom.val());
}

function clearNoNum(obj) {
	obj.value = obj.value.replace(/[^\d.]/g, "");
	obj.value = obj.value.replace(/^\./g, "");
	obj.value = obj.value.replace(/\.{2,}/g, ".");
	obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace("$#$",
			".");
}
// 输入正负浮点数
function clearNoNum_1(obj) {
	obj.value = obj.value.replace(/[^\d\.-]/g, "");
	obj.value = obj.value.replace(/^\./g, "");
	obj.value = obj.value.replace(/\.{2,}/g, ".");
	obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace("$#$",
			".");
}
//输入正整数
function clearNoNum_2(obj) {
	obj.value = obj.value.replace(/[^\d\.-]/g, "");
	obj.value = obj.value.replace(/^\./g, "");
	obj.value = obj.value.replace(/\.{2,}/g, "");
	obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace("$#$",
			"");
}
//小写转大写
function i2c(n) {
	if (typeof (n) == "number") {
		if (-1 == n.toString().indexOf(".")) {
			return seti2c(n);
		} else {
			var i, istr, f, fstr, a, rstr;
			a = n.toString().split(".");
			i = a[0];
			f = a[1];
			istr = seti2c(i);
			fstr = setf2c(f);
			rstr = istr + fstr;
			rstr = rstr.replace(/^圆/, "");
			return rstr;
		}
	} else {
		return "---";
	}
}

function seti2c(n) {
	var ns = n.toString();
	var tempstr = "";
	for ( var i = 1; i < ns.length + 1; i++) {
		switch (i) {
		case 1:
			var t = ns.substr(ns.length - i, 1);
			if (t != "0") {
				tempstr = t + "圆" + tempstr;
			} else {
				tempstr = "圆" + tempstr;
			}
			break;
		case 2:
			var t = ns.substr(ns.length - i, 1);
			if (t != "0") {
				tempstr = t + "拾" + tempstr;
			} else {
				tempstr = "0" + tempstr;
			}
			break;
		case 3:
			var t = ns.substr(ns.length - i, 1);
			if (t != "0") {
				tempstr = t + "佰" + tempstr;
			} else {
				tempstr = "0" + tempstr;
			}
			break;
		case 4:
			var t = ns.substr(ns.length - i, 1);
			if (t != "0") {
				tempstr = t + "仟" + tempstr;
			} else {
				tempstr = "0" + tempstr;
			}
			break;
		case 5:
			var t = ns.substr(ns.length - i, 1);
			if (t != "0") {
				tempstr = t + "万" + tempstr;
			} else {
				tempstr = "万" + tempstr;
			}
			break;
		case 6:
			var t = ns.substr(ns.length - i, 1);
			if (t != "0") {
				tempstr = t + "拾" + tempstr;
			} else {
				tempstr = "0" + tempstr;
			}
			break;
		case 7:
			var t = ns.substr(ns.length - i, 1);
			if (t != "0") {
				tempstr = t + "佰" + tempstr;
			} else {
				tempstr = "0" + tempstr;
			}
			break;
		case 8:
			var t = ns.substr(ns.length - i, 1);
			if (t != "0") {
				tempstr = t + "仟" + tempstr;
			} else {
				tempstr = "0" + tempstr;
			}
			break;
		case 9:
			var t = ns.substr(ns.length - i, 1);
			if (t != "0") {
				tempstr = t + "亿" + tempstr;
			} else {
				tempstr = "亿" + tempstr;
			}
			break;
		case 10:
			var t = ns.substr(ns.length - i, 1);
			if (t != "0") {
				tempstr = t + "拾" + tempstr;
			} else {
				tempstr = "0" + tempstr;
			}
			break;
		case 11:
			var t = ns.substr(ns.length - i, 1);
			if (t != "0") {
				tempstr = t + "佰" + tempstr;
			} else {
				tempstr = "0" + tempstr;
			}
			break;
		case 12:
			var t = ns.substr(ns.length - i, 1);
			if (t != "0") {
				tempstr = t + "仟" + tempstr;
			} else {
				tempstr = "0" + tempstr;
			}
			break;
		default:
			break;
		}
	}
	return g2b(tempstr);
}

function setf2c(n) {
	var ns = n.toString();
	var tempstr = "";
	for ( var i = 0; i < ns.length; i++) {
		switch (i) {
		case 0:
			var t = ns.substr(i, 1);
			if (t != "0") {
				tempstr += t + "角";
			} else {
				tempstr += "0";
			}
			break;
		case 1:
			var t = ns.substr(i, 1);
			if (t != "0") {
				tempstr += t + "分";
			}
			break;
		default:
			break;
		}
	}
	return g2b(tempstr);
}

function g2b(s) {
	rs = s.replace(/0+/g, "0");
	rs = rs.replace(/0(圆|万|亿)/g, "$1");
	rs = rs.replace("亿万", "亿");
	rs = rs.replace(/0/g, "零");
	rs = rs.replace(/1/g, "壹");
	rs = rs.replace(/2/g, "贰");
	rs = rs.replace(/3/g, "叁");
	rs = rs.replace(/4/g, "肆");
	rs = rs.replace(/5/g, "伍");
	rs = rs.replace(/6/g, "陆");
	rs = rs.replace(/7/g, "柒");
	rs = rs.replace(/8/g, "捌");
	rs = rs.replace(/9/g, "玖");
	return rs;
}