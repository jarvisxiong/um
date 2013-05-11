var aMessage = {
	"int"	  :"Please input an int",
	"int+"	  :"Please input a positive int",
	"int-"	  :"Please input a negative int",
	"num"	  :"Please input a number",
	"num+"	  :"Please input a positive number",
	"num-"	  :"Please input a negative number",
	"float"	  :"Please input a float",
	"float+"  :"Please input a positive float",
	"float-"  :"Please input a negative float",
	"email"	  :"Please input an email address",
	"color"	  :"Please input a color, like #FFFFFF",
	"url"	  :"Please input an URL",
	"chinese" :"Please input Chinese character",
	"ascii"	  :"Please input ascii character",
	"zipcode" :"Please input correct zipcode", 
	"mobile"  :"Please input correct moblie number",
	"ip4"	  :"Please input correct IP4 address",
	"notempty":"Not NULL",
	"image"   :"Please choose an image file",
	"doc"     :"Please choose an word file(.doc)",
	"excel"   :"Please choose an excel file(.xls)",
	"xml"     :"Please choose a xml file",
	"rar"	  :"Please choose a compressed file",
	"date"	  :"Please input correct date, like 2006-9-30",
	
	"dateInvalidation":"The input date is out of range",
	"selectNone":"Please select an item",
	"fileNameError":"The filename is incorrect",
	"fileUploadError":"File upload failed",
	
	"test" : "test",
	"chooseAnItem":"Please Choose an Item!",
	"confirmDelete":"Do you want to delete this item?"
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
myclock= month+"/"+date+"/"+year+" "+hours+":"+minutes+":"+seconds;

liveclock.innerHTML=myclock;
setTimeout("showDateTime()",1000);
}
