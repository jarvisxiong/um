//是否为空校验
function isEmpty(s) {
    var lll=trim(s);
    if( lll == null || lll.length == 0 )
        return true;
    else
        return false;
}

//删除字符串左边的空格
function ltrim(str) { 
    if(str.length==0)
        return(str);
    else {
        var idx=0;
        while(str.charAt(idx).search(/\s/)==0)
            idx++;
        return(str.substr(idx));
    }
}

//删除字符串右边的空格
function rtrim(str) { 
    if(str.length==0)
        return(str);
    else {
        var idx=str.length-1;
        while(str.charAt(idx).search(/\s/)==0)
            idx--;
        return(str.substring(0,idx+1));
    }
}

//删除字符串左右两边的空格
function trim(str) { 
    return(rtrim(ltrim(str)));
}

/**日期相比较,如果两个日期相等返回0，如果大于返回1，如果小于返回-1*/
function compareDate(date1, date2) {
    if (trim(date1) == trim(date2))      
        return 0;
    if (trim(date1) > trim(date2))      
        return 1;
    if (trim(date1) < trim(date2))      
        return -1;
}

//校验是否是Email
function isEmail(eml) {
    if(trim(eml)!='') {
      var re=new RegExp("@[\\w]+(\\.[\\w]+)+$");
      return(re.test(eml));
    }
    else
      return(true);
}

//是否是电话号
function isTel(tel) {
    var charcode;
    for (var i=0; i<tel.length; i++)    
    {
        charcode = tel.charCodeAt(i);
        if (charcode < 48 && charcode != 45 || charcode > 57)    
            return false;
    }
    return true;
}

//校验是否是实数
function isNumber(num) {
    var re=new RegExp("^-?[\\d]*\\.?[\\d]*$");
    if(re.test(num))
        return(!isNaN(parseFloat(num)));
    else
        return(false);
}

//校验是否是整数
function isInteger(num)    {
    var re=new RegExp("^-?[\\d]*$");
    if(re.test(num))
        return(!isNaN(parseInt(num)));
    else
        return(false);
}

/*
 * 校验是否是整数
 * 0为校验是否是整数
 * 1为校验是否是大于0的整数
 * -1为校验是否是负整数
 * */
function isIntegerOp(num,op){
    if(op==0){
        return /^(-|\+)?\d+$/.test(num);
    }
    if(op==1){
        if(num==0){return false;}
        return /^\d+$/.test(num);
    }
    if(op==-1){
        return /^-\d+$/.test(num);
    }
}

//短时间校验，形如 (13:04:06)
function isTime(str){
    var a = str.match(/^(\d{1,2})(?(\d{1,2})\2(\d{1,2})$/);
    if (a == null) { return false;}
    if (a[1]>24 || a[3]>60 || a[4]>60){
        return false;
    }
    return true;
}

//短日期校验，形如 (2003-12-05)
function isDateTimeShort(str){
    var r = str.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/); 
    if(r==null)return false; 
    var d= new Date(r[1], r[3]-1, r[4]); 
    return (d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[4]);
}

//长时间时间，形如 (2003-12-05 13:04:06)
function isDateTimeLong(str){
    var reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2})\d{1,2})\d{1,2})$/; 
    var r = str.match(reg); 
    if(r==null)return false; 
    var d= new Date(r[1], r[3]-1,r[4],r[5],r[6],r[7]); 
    return (d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[4]&&d.getHours()==r[5]&&d.getMinutes()==r[6]&&d.getSeconds()==r[7]);
}

//判断字符由字母和数字，下划线.且开头的只能是下划线和字母
function checkStr(str){
    return /^([a-zA-z_]{1}|[0-9]{1})([\w]*)$/g.test(str);
}


//身份证的验证
function isIdCardNo(num){
    if (isNaN(num)) {
        //alert("输入的不是数字！"); 
        return false;
    }
    var len = num.length, re; 
    if (len == 15)
        re = new RegExp(/^(\d{6})()?(\d{2})(\d{2})(\d{2})(\d{3})$/);
    else if (len == 18)
        re = new RegExp(/^(\d{6})()?(\d{4})(\d{2})(\d{2})(\d{3})(\d)$/);
    else {
        //alert("输入的数字位数不对！"); 
        return false;
        }
    var a = num.match(re);
    if (a != null){
    	
    	var B;
        if (len==15){
            var D = new Date("19"+a[3]+"/"+a[4]+"/"+a[5]);
            B = D.getYear()==a[3]&&(D.getMonth()+1)==a[4]&&D.getDate()==a[5];
        }else{
            var D = new Date(a[3]+"/"+a[4]+"/"+a[5]);
            B = D.getFullYear()==a[3]&&(D.getMonth()+1)==a[4]&&D.getDate()==a[5];
        }
        if (!B) {
        	//alert("输入的身份证号 "+ a[0] +" 里出生日期不对！"); 
        	return false;
        }
    }
    return true;
}

//全选，str为复选框的名字
function checkAll(str){
    var a = document.getElementsByName(str);
    var n = a.length;
    for (var i=0; i<n; i++){
        a[i].checked = true;
    }
}


//取消全选，str为复选框的名字
function checkAll(str){
    var a = document.getElementsByName(str);
    var n = a.length;
    for (var i=0; i<n; i++){
        a[i].checked = false;
    }
}

//校验是否是浮点型数字
function fucCheckFloat(NUM){
    var i,j,strTemp;
    strTemp="0123456789.";
    if ( NUM.length== 0)
        return false;
    for (i=0;i<NUM.length;i++){
        j=strTemp.indexOf(NUM.charAt(i));    
        if (j==-1){
        //说明有字符不是数字
            return false;
        }
    }
    //说明是数字
    return true;
}

//函数名：fucCheckTEL
//功能介绍：检查是否为电话号码
//参数说明：要检查的字符串
//返回值：true为是合法，false为不合法
function fucCheckTEL(TEL){
    var i,j,strTemp;
    strTemp="0123456789-()# ";
    for (i=0;i<TEL.length;i++){
        j=strTemp.indexOf(TEL.charAt(i));    
        if (j==-1){
        //说明有字符不合法
            return false;
        }
    }
    //说明合法
    return true;
}

//函数名：fucCheckLength
//功能介绍：检查字符串的长度
//参数说明：要检查的字符串
//返回值：长度值
function fucCheckLength(strTemp)
{
    var i,sum;
    sum=0;
    for(i=0;i< strTemp.length; i++)
    {
        if ((strTemp.charCodeAt(i)>=0) && (strTemp.charCodeAt(i)<=255))
            sum=sum+1;
        else
            sum=sum+2;
    }
    return sum;
}

//检验value中是否包含【'】、【"】、【/】、【\】,如果含有则返回true，否则返回false
function checkData(value){

     var pattern = new Array('\'','"','\,','/','\\');
    for (var i=0;i< pattern.length; i++){
        if (value.indexOf(pattern[i])>=0) {
            return true;
        } 
    }
    return false;
}