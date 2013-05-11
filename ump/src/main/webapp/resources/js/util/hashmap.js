//----------------------------------------------------------------------------
//HashMap
function HashMap() {
	this.arrKey = new Array();
	this.arrValue = new Array();

	// ���Key�Ƿ����
	this.exists = function(strKey) {
		strKey = strKey.toUpperCase();
		for ( var i = 0; i < this.arrKey.length; i++) {
			if (this.arrKey[i] == strKey) {
				return true;
			}
		}
		return false;
	};
	this.length = function() {
		return this.arrKey.length;
	};

	// ����Key��Value
	this.put = function(strKey, objValue) {
		strKey = strKey.toUpperCase();
		for ( var i = 0; i < this.arrKey.length; i++) {
			if (this.arrKey[i] == strKey) {
				this.arrValue[i] = objValue;
				return;
			}
		}
		this.arrKey[this.arrKey.length] = strKey;
		this.arrValue[this.arrValue.length] = objValue;
	};

	// ��ȡָ��Key��Value�����Key�����ڣ�����null
	this.get = function(strKey) {
		strKey = strKey.toUpperCase();
		for ( var i = 0; i < this.arrKey.length; i++) {
			if (this.arrKey[i] == strKey) {
				return this.arrValue[i];
			}
		}
		return null;
	};

	// ɾ��һ��Key
	this.remove = function(strKey) {
		strKey = strKey.toUpperCase();
		for ( var i = 0; i < this.arrKey.length; i++) {
			if (this.arrKey[i] == strKey) {
				this.arrKey.splice(i, 1);
				this.arrValue.splice(i, 1);
				return;
			}
		}
	};

	// ��ȡ���е�Key����
	this.getKeys = function() {
		return this.arrKey;
	};

	//��ȡ���е�Value����
	this.getValues = function() {
		return this.arrValue;
	};
}