function getJREVersions() {
	function _64(_65) {
		var _66 = "JavaWebStart.isInstalled." + _65 + ".0";
		if (!ActiveXObject) {
			return false;
		}
		try {
			return (new ActiveXObject(_66) != null);
		} catch (exception) {
			return false;
		}
	}

	function _67(v1, v2) {
		var v1a = v1.split(".");
		var v2a = v2.split(".");
		var _68 = v1a.length;
		if (v2a.length > _68) {
			_68 = v2a.length;
		}

		for ( var i = 0; i < _68; i++) {
			if (v1a.length == i) {
				return v2;
			} else {
				if (v2a.length == i) {
					return v1;
				} else {
					if (v1a[i] < v2a[i]) {
						return v2;
					} else {
						if (v1a[i] > v2a[i]) {
							return v1;
						}
					}
				}
			}
		}
		return v1;
	}

	if (!navigator.javaEnabled()) {
		return "none";
	}
	var _69 = "undefined";
	if (navigator.userAgent.toLowerCase().indexOf("msie") != -1) {
		if (_64("1.8.0")) {
			_69 = "1.8.0";
		} else {
			if (_64("1.7.0")) {
				_69 = "1.7.0";
			} else {
				if (_64("1.6.0")) {
					_69 = "1.6.0";
				} else {
					if (_64("1.5.0")) {
						_69 = "1.5.0";
					} else {
						if (_64("1.4.2")) {
							_69 = "1.4.2";
						} else {
							_69 = "none";
						}
					}
				}
			}
		}
	} else {
		if (navigator.mimeTypes) {
			for ( var i = 0; i < navigator.mimeTypes.length; i++) {
				s = navigator.mimeTypes[i].type;
				var p = /^application\/x-java-applet\x3Bversion=([0-9\.]+)/;
				var m = s.match(p);
				if (m != null) {
					if (_69 != "undefined") {
						_69 = _67(_69, RegExp.$1);
					} else {
						_69 = RegExp.$1;
					}
				}
			}
		}
	}
	return _69;
}