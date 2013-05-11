/*
 * Translated default messages for the jQuery validation plugin.
 * Language: CN
 * Author: Fayland Lam <fayland at gmail dot com>
 */
var language;
	if (navigator.language)    //对于mozilla, Firefor 
             language = navigator.language; 
   else if (navigator.browserLanguage) //对于IE 
          language = navigator.browserLanguage; 
if (language == "zh-cn"){
	jQuery.extend(jQuery.validator.messages, {
	        required: "必选字段",
			remote: "请修正该字段",
			email: "请输入正确格式的电子邮件",
			url: "请输入合法的网址",
			date: "请输入合法的日期",
			dateISO: "请输入合法的日期 (ISO).",
			number: "请输入合法的数字",
			digits: "只能输入整数",
			creditcard: "请输入合法的信用卡号",
			equalTo: "请再次输入相同的值",
			accept: "请输入拥有合法后缀名的字符串",
			maxlength: jQuery.validator.format("请输入一个长度最多是 {0} 的字符串"),
			minlength: jQuery.validator.format("请输入一个长度最少是 {0} 的字符串"),
			rangelength: jQuery.validator.format("请输入一个长度介于 {0} 和 {1} 之间的字符串"),
			range: jQuery.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
			max: jQuery.validator.format("请输入一个最大为 {0} 的值"),
			min: jQuery.validator.format("请输入一个最小为 {0} 的值")
		});
	}else if (language=="zh-tw"){
		jQuery.extend(jQuery.validator.messages, {
	        required: "必選字段",
			remote: "請修正字段",
			email: "請輸入正確格式的電子郵件",
			url: "請輸入合法的地址",
			date: "請輸入合法的日期",
			dateISO: "請輸入合法的日期 (ISO).",
			number: "請輸入合法的數字",
			digits: "智能輸入整數",
			creditcard: "請輸入合法的信用卡號",
			equalTo: "請再次輸入相同的值",
			accept: "請輸入擁有合法後綴名的字符串",
			maxlength: jQuery.validator.format("請輸入一個長度最多是 {0} 的字符串"),
			minlength: jQuery.validator.format("請輸入一個長度最少是 {0} 的字符串"),
			rangelength: jQuery.validator.format("請輸入一個長度介於 {0} 和 {1} 之間的字符串"),
			range: jQuery.validator.format("請輸入一個長度介於 {0} 和 {1} 之間的值"),
			max: jQuery.validator.format("請輸入一個最大為 {0} 的值"),
			min: jQuery.validator.format("請輸入一個最小為 {0} 的值")
		});
}