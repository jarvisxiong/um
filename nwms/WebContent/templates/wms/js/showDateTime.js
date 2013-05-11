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
myclock=year+"-"+month+"-"+date+" "+hours+":"+minutes+":"+seconds;

liveclock.innerHTML=myclock;
setTimeout("showDateTime()",1000);
}// JavaScript Document