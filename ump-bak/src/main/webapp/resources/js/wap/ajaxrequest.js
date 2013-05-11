
/**
 * class AJAXRequest
 * @constructor
 * @class		AJAXRequest
 * @param		{object}	[obj]					Init parameter object
 * @param		{string}	[obj.url=""]			Url to request
 * @param		{string}	[obj.content=""]		Content to send
 * @param		{string}	[obj.method="GET"]		Request method, GET or POST
 * @param		{string}	[obj.charset]			Charset of content
 * @param		{boolean}	[obj.async=true]		Async request, true or false
 * @param		{number}	[obj.timeout=3600000]	Request timeout in millisecond
 * @param		{function}	[obj.encode=encodeURIComponent]		Encoding method, default encodeURIComponent
 * @param		{function}	[obj.ontimeout]			Timeout callback
 * @param		{function}	[obj.onrequeststart]	Request start callback
 * @param		{function}	[obj.onrequestend]		Request end callback
 * @param		{function}	[obj.oncomplete]		Request complete successful callback
 * @param		{function}	[obj.onexception]		Handle request exception callback
 * @property	{string}	url						Url to request
 * @property	{string}	content					Content to send
 * @property	{string}	method					Request method, GET or POST
 * @property	{string}	charset					Charset of content
 * @property	{boolean}	async					Async request, true or false
 * @property	{number}	timeout					Request timeout in millisecond
 * @property	{function}	encode					Encoding method
 * @property	{function}	ontimeout				Timeout callback
 * @property	{function}	onrequeststart			Request start callback
 * @property	{function}	onrequestend			Request end callback
 * @property	{function}	oncomplete				Request complete successful callback
 * @property	{function}	onexception				Handle request exception callback
 * @example
 * // using default values to init AJAXRequest object.
 * var ajax1 = new AJAXRequest();
 * // using a parameter to init AJAXRequest object.
 * var ajax2 = new AJAXRequest({
 * 	url: "getdata.asp",	// get data from getdata.asp
 * 	method: "GET",		// GET method
 * 	oncomplete: function(obj) {
 * 		alert(obj.responesText);	// show data from getdata.asp
 * });
 */
function AJAXRequest(init) {
	var objPool = [], AJAX = this, _pool = AJAXRequest.__pool__ || (AJAXRequest.__pool__ = []);
	(function(obj) {
		// init xmlhttp pool, and some consts
		var emptyFun = function() { };

		// process inti parameter
		obj = obj ? obj : {};
		var prop = ['url', 'content', 'method', 'async', 'encode',      'timeout', 'ontimeout', 'onrequeststart', 'onrequestend', 'oncomplete', 'onexception'];
		var defs = ['',    '',        'GET',     true,    _GEC('UTF-8'), 3600000,   emptyFun,    emptyFun,         emptyFun,       emptyFun,     emptyFun];
		var pc = prop.length;
		while(pc--) {
			AJAX[prop[pc]] = getp(obj[prop[pc]], defs[pc]);
		}
		// get the first xmlhttp, if failed then return false
		if(!getXHR()) { return false; }
	})(init);

	// get param or its default
	function getp(p, d) { return p != undefined ? p : d; }

	// get XMLHttpRequest from pool
	function getXHR() {
		var xhr, _vers = [window.XMLHttpRequest, "MSXML2.XMLHTTP", "Microsoft.XMLHTTP",];
		for(var i = 0; i < _pool.length; i+=1) {
			if(_pool[i].readyState == 0 || _pool[i].readyState == 4) {
				return _pool[i];
			}
		}
		for(var i = 0; i < _vers.length; i+=1) {
			try {
				if(getOs()=="Safari"){
					xhr = new _vers[0];
				}else{
					xhr = (_vers[i] && typeof(_vers[i]) == "function" ? new _vers[i] : new ActiveXObject(_vers[i]));
				}
				
				break;
			} catch(e) {
				//alert(e.message);
				xhr = false;
				continue;
			}
		}
		if(!xhr) {
			throw 'Cannot init XMLHttpRequest object!';
			return false;
		}
		else {
			_pool[_pool.length] = xhr;
			return xhr;
		}
	}

	// get element by id
	function $(id) { return document.getElementById(id); }

	// convert anything to number
	function _N(d) { var n = d * 1; return(isNaN(n) ? 0 : n); }

	// convert anything to HtmlObject
	function _VO(v) { return (typeof(v) == "string" ? (v = $(v)) ? v : false : v); }

	// get an unique number
	function _GID(){return((new Date)*1);}

	// save update object
	function _SOP(id, ct) { objPool[id + ""] = ct; }

	// load update object
	function _LOP(id) { return(objPool[id + ""]); }

	// string replace function generator
	function _SRP(pre, reps, ps){
		return (function rep(str) {
					str = pre(str);
					for(var i = 0, n = reps.length; i < n; i+=1) {
						str = str.replace(reps[i], ps[i]);
					}
					return(str);
				});
	}

	// get encode method
	function _GEC(cs){
		if(cs.toUpperCase() == "UTF-8") {
			return(encodeURIComponent);
		}
		else {
			// replace sepcial chars: +
			return(_SRP(escape, [/\+/g], ["%2B"]));
		}
	}

	// set content to HtmlObject
	function _ST(obj, txt) {
		if(!obj.nodeName) {
			return;
		}
		var nn = "|" + obj.nodeName.toUpperCase() + "|";
		if("|INPUT|TEXTAREA|OPTION|".indexOf(nn) > -1) {
			obj.value = txt;
		}
		else {
			try {
				obj.innerHTML = txt;
			} catch(e) { };
		}
	}

	// generate a callback function
	function _CB(cb) {
		if(typeof(cb) == "function") {
			return cb;
		}
		else {
			cb = _VO(cb);
			if(cb) {
				return (function(obj) {
							_ST(cb, obj.responseText);
						});
			}
			else {
				return AJAX.oncomplete;
			}
		}
	}

	// generate parameters
	// p  output parameters array
	// v  input parameter values
	// d  default values
	// f  extra process function
	function $GP(v, d, f) {
		var i = 0, p = [];
		while(i < v.length) {
			p[i] = v[i] ? (f[i] ? f[i](v[i]) : v[i]) : d[i];
			i+=1;
		}
		while(i < d.length) {
			p[i]=d[i];
			i+=1;
		}
		return p;
	}

	// send request
	function send() {
		var ct, ctf = false, xhr = getXHR();
		// process parameters
		var p = $GP(arguments,
					[AJAX.url, AJAX.content, AJAX.oncomplete, AJAX.method, AJAX.async, null],
					[null, null, _CB, null, null, null]);
		var url = p[0], content = p[1], callback = p[2], method = p[3], async = p[4], extra = p[5];

		// is use POST method?
		var isPost = method.toUpperCase() == "POST" ? true : false;
		// check if url exists
		if(!url) {
			throw 'url is null';
			return false;
		}
		//JSONArray arr = JSONArray.fromObject(extra);
		//alert(extra.filter_LIKES_titlename);
		// event callback argument
		var ev = {
			url: url,
			content: content,
			method: method,
			params: extra
		};

		// append a timestamp to the url and open XMLHttpRequest
		if(!isPost){
			url += (url.indexOf("?") > -1 ? "&" : "?") + "timestamp=" + _GID()+"&"+_serialize(extra);
		}
		
		xhr.open(method, url, async);

		// request start event
		AJAX.onrequeststart(ev);

		// POST method needs a sepcial Content-Type
		if(isPost) {
			xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		}
		xhr.setRequestHeader("X-Request-With", "XMLHttpRequest");

		// set a timeout to cancel request when timeout
		ct = setTimeout(function() {
							ctf = true;
							xhr.abort();
						},
						AJAX.timeout);
		var rc = function() {
			if(ctf) {
				AJAX.ontimeout(ev);
				AJAX.onrequestend(ev);
			}
			else if(xhr.readyState == 4) {
				clearTimeout(ct);
				ev.status = xhr.status;
				try{
					if(xhr.status == 200) {
						callback(xhr, extra);
					}
					else {
						AJAX.onexception(ev);
					}
				}
				catch(e) {
					AJAX.onexception(ev);
				}
				AJAX.onrequestend(ev);
			}
		}
		xhr.onreadystatechange = rc;
		if(isPost) { 
			content = _serialize(extra);
			xhr.send(content); 
		} else 
		{ xhr.send(""); }
		if(async == false) {
			rc();
		}
		return true;
	}

	/**
	 * Set charset of content
	 *
	 * @param	{string}	charset	charset, UTF-8 or GB2312, and etc..
	 * @see AJAXRequest#charset
	 * @see AJAXRequest#encode
	 * @example
	 * var ajax = new AJAXRequest();
	 * ajax.setcharset("GB2312");
	 */
	this.setcharset = function(cs) {
        AJAX.encode = _GEC(cs);
	}

	/**
	 * Get data from sepcial url
	 *
	 * @param	{string}					[url]			Url to request
	 * @param	{function|object|string}	[oncomplete]	Successful callback, or Html object, or Html object's ID
	 * @param	{object}					[extra]			Extra data post to callback function
	 * @returns	{boolean}									Is the request successfully sent
	 * @example
	 * var ajax = new AJAXRequest();
	 * ajax.get("getdata.asp", function(obj) {
	 * 	alert(obj.responseText);	// show data from getdata.asp
	 * });
	 * ajax.get("getdata.asp", "txtData");	// show data from getdata.asp in html object "txtData"
	 */
	this.get = function(url, callback, extra) {
		return send(url, '', callback, 'GET', AJAX.async, extra);;
	}

	/**
	 * Update the sepcial object with data from request url
	 *
	 * @param	{function|object|string}	callback	oncomplete	Successful callback, or Html object, or Html object's ID
	 * @param	{string}					url			Url to request
	 * @param	{number}					interval	Interval to update
	 * @param	{number}					times		Total update times
	 * @param	{object}					[extra]		Extra data post to callback function
	 * @param   {method}					method      method is GET or POST		
	 * @returns	{string}								id of update request, use to stop the update
	 * @see		AJAXRequest#stopupdate
	 * @example
	 * var ajax = new AJAXRequest();
	 * ajax.update(function(obj) {
	 * 		alert(obj.responseText);
	 * 	},
	 * 	"getdata.asp",	// get data from getdata.asp
	 * 	1000,	// update per second
	 * 	3		// total update 3 times
	 * );
	 */
	this.update = function(callback, url, interval, times, extra, method) {
		interval = _N(interval);
		times = _N(times);
		if(typeof(method) == "undefined"){
			method = "GET";
		}else{
			method = method.toUpperCase();
		}
		if(interval < 1) {
			times = 1;
		}
        else if(times < 1) {
            times = Number.POSITIVE_INFINITY;
        }
		var sendfoo = function() {
			send(url, "", callback, method, AJAX.async, extra);
		};
		var updateid = _GID();
		var updatefoo = function(updateCount) {
			sendfoo();
			updateCount--;
			if(updateCount > 0) {
				_SOP(updateid, setTimeout(function(){
										updatefoo(updateCount);
									}, interval));
			}
		}
		updatefoo(times);
		return updateid;
	}

	/**
	 * Stop update an object
	 *
	 * @param	{string}	update_id	update id which return by update method
	 * @see		AJAXRequest#update
	 * @example
	 * var ajax = new AJAXRequest();
	 * var up = ajax.update("txtData", "getdata.asp");
	 * ajax.stopupdate(up);
	 */
	this.stopupdate = function(id) {
		clearTimeout(_LOP(id));
	}

	/**
	 * Post data to the sepcial url
	 *
	 * @param	{string}					[url]			Url to post data
	 * @param	{string}					[content]		Data to post
	 * @param	{function|object|string}	[oncomplete]	Successful callback, or Html object, or Html object's ID
	 * @param	{object}					[extra]			Extra data post to callback function
	 * @returns	{boolean}									Is the request successfully sent
	 * @see		AJAXRequest#postf
	 * @example
	 * var ajax = new AJAXRequest();
	 * ajax.post("postdata.asp", "the data to post", function(){});
	 */
	this.post = function(url, content, callback, extra) {
		return send(url, content, callback, "POST", AJAX.async, extra);
	}

	/**
	 * Post the sepcial form to an url
	 *
	 * @param	{string|object}				formObject		The form object or its ID
	 * @param	{function|object|String}	[oncomplete]	Successful callback, or Html object, or Html object's ID
	 * @param	{object}					[extra]			Extra data post to callback function
	 * @returns	{boolean}									Is the request successfully sent
	 * @see		AJAXRequest#post
	 * @example
	 * var ajax = new AJAXRequest();
	 * ajax.postf("dataForm", function(obj) {
	 * 	alert(obj.responseText);
	 * });
	 */
	this.postf = function(formObj, callback, extra) {
		var p=[],vaf,pcbf,purl,pc,pm,ac=arguments.length,av=arguments;
		// check form legal
		formObj = formObj ? _VO(formObj) : false;
		if(!formObj || formObj.nodeName != "FORM") {
			return false;
		}
		// process form validate
		validfoo = formObj.getAttribute("onvalidate");
		validfoo = validfoo ? (typeof(validfoo)=="string" ? new Function(validfoo) : validfoo) : null;
		if(validfoo && !validfoo()) {
			return false;
		}
		// get url and method from formObj's attributes
		var url = formObj.getAttribute("action"), method = formObj.getAttribute("method");
		// check if content is empty
		var content = AJAX.formToStr(formObj);
		if(content.length == 0) {
			return false;
		}
		// send the request
		if(method.toUpperCase()=="POST") {
			return send(url, content, callback, "POST", true, extra);
		}
		else {
			url += (url.indexOf("?") > -1 ? "&" : "?") + content;
			return send(url, "", callback, "GET", true, extra);
		}
	}

	/**
	 * Translate the form object to string
	 *
	 * @author	SurfChen <surfchen@gmail.com>
	 * @link	http://www.surfchen.org/
	 * @param	{object} formObject
	 * @returns {string} The string of the form
	 * @see		AJAXRequest#postf
	 * @ignore
	 */
	this.formToStr = function(formObj) {
		var qstr = "", and = "", elem, value;
		for(var i = 0; i< formObj.length; i+=1) {
			elem = formObj[i];
			if (elem.name!='') {
                value = undefined;
				switch(elem.type) {
					case "select-one":
						if(elem.selectedIndex > -1) {
							value = elem.options[elem.selectedIndex].value;
						}
						else {
							value = "";
						}
						break;
					case "checkbox":
					case "radio":
						if (elem.checked == true) {
							value = elem.value;
						}
						break;
					default:
						value = elem.value;
				}
                if(value != undefined) {
                    value = AJAX.encode(value);
                    qstr += and + elem.name + "=" + value;
                    and = "&";
                }
			}
		}
		return qstr;
	}
	
	//JSON值处理
	function _serialize(obj){  
	    var a = [];  
	    for(var k in obj){  
	        var val = obj[k];  
	        if(val.constructor == Array){  
	            for(var i=0,len=val.length;i<len;i++){  
	                a.push(k + '=' + encodeURIComponent(val[i]));  
	            }  
	        }else{  
	            a.push(k + '=' + encodeURIComponent(val));  
	        }  
	    }  
	    return a.join('&');  

	}  
}
function getOs()     
{     
  if(navigator.userAgent.indexOf("MSIE")>0) {     
               return "MSIE";       //IE浏览器   
  }   
  if(isFirefox=navigator.userAgent.indexOf("Firefox")>0){     
               return "Firefox";     //Firefox浏览器   
   }   
   if(isSafari=navigator.userAgent.indexOf("Safari")>0) {     
                return "Safari";      //Safan浏览器   
   }   
   if(isCamino=navigator.userAgent.indexOf("Camino")>0){     
                return "Camino";   //Camino浏览器   
   }   
   if(isMozilla=navigator.userAgent.indexOf("Gecko/")>0){     
                return "Gecko";    //Gecko浏览器   
   }     
}  
function $(s){
	return document.getElementById(s);
}
var ajax = new AJAXRequest();