var aMessage = {
	"int"	  :"请输入整数",
	"int+"	  :"请输入正整数",
	"int-"	  :"请输入负整数",
	"num"	  :"请输入数字",
	"num+"	  :"请输入正数",
	"num-"	  :"请输入负整数",
	"float"	  :"请输入浮点数",
	"float+"  :"请输入正浮点数",
	"float-"  :"请输入负浮点数",
	"email"	  :"请输入正确的邮箱地址",
	"color"	  :"请输入正确的颜色",
	"url"	  :"请输入正确的连接地址",
	"chinese" :"请输入中文",
	"ascii"	  :"请输入ascii字符",
	"zipcode" :"请输入正确的邮政编码",
	"mobile"  :"请输入正确的手机号码",
	"ip4"	  :"请输入正确的IP地址",
	"notempty":"不能为空",
	"image" :"请选择图片",
	"doc"     :"请选择Word文档(.doc)",
	"excel"   :"请选择Excel文档(.xls)",
	"xml"     :"请选择XML文件",
	"rar"	  :"请选择压缩文件",
	"date"	  :"请输入正确的日期，如2006-9-30",

	"dateInvalidation":"输入的日期不在指定范围",
	"selectNone":"请选择一个有效选项",
	"fileNameError":"文件名无效",
	"fileUploadError":"文件上传失败",

	"test" : "测试",
	"confirmDelete":"确认要删除吗?",
	"chooseAnItem":"请选择一个选项!"
}

function showDateTime()
{
var Digital=new Date();
var year=Digital.getYear();
var month =Digital.getMonth()+1;
var date = Digital.getDate();
var hours=Digital.getHours();
var minutes=Digital.getMinutes();
var seconds=Digital.getSeconds();

if(minutes<=9)
	minutes="0"+minutes;
if(seconds<=9)
	seconds="0"+seconds;
myclock= year+"-"+month+"-"+date+" "+hours+":"+minutes+":"+seconds;

liveclock.innerHTML=myclock;
setTimeout("showDateTime()",1000);
}