
var Util = function() {
	"use strict";

	// ============ 扩展系统function ============
    String.prototype.startWith = function(s) {
        if (s == null || s == "" || this.length == 0 || s.length > this.length) {
            return false;
        }
        if (this.substr(0, s.length) == s) {
            return true;
        } else {
            return false;
        }
    };

    String.prototype.endWith = function(s) {
        if (s == null || s == "" || this.length == 0 || s.length > this.length) {
            return false;
        }
        if (this.substring(this.length - s.length) == s) {
            return true;
        } else {
            return false;
        }
    };

    String.prototype.constants = function(str) {
        if (this.indexOf(str) != -1) {
            return true;
        }
        return false;
    };
	// ========================================
    
    
    return {
    	
        //判断字符串是否为空
        isBlank: function(str) {
            if (!str || $.trim(str) == '') {
                return true;
            }
            return false;
        },

        //判断对象是否为空（是否不包含有属性）
        isEmpty: function(obj) {
            if (!obj) {
                return true;
            }
            for (var name in obj) {
                if(obj.hasOwnProperty(name)) {
                    return false;
                }
            }
            return true;
        },

        //判断是否为json对象
        isJson: function(obj){
	        var isjson = typeof(obj) == "object" && Object.prototype.toString.call(obj).toLowerCase() == "[object object]" && !obj.length;    
	        return isjson;
        },
        
        //生成唯一id
        getUniqueID: function(prefix) {
            return prefix +'_'+ Math.floor(Math.random() * (new Date()).getTime());
        }
    };
}();


/**
 * Url工具
 */
var URL = function(newUrl) {
	
	var url = newUrl;
	
    //为url设置或替换参数值
    var replace = function(key, value) {
        var returnObj = url;
        if (key) {
            key += '=';
            var pos1 = returnObj.indexOf(key);
            if (pos1 > 0) {
                pos1 += key.length;
                var pos2 = returnObj.indexOf('&', pos1);
                if (pos2 > -1) {
                    returnObj = returnObj.substring(0, pos1) + value + returnObj.substring(pos2);
                } else {
                    returnObj = returnObj.substring(0, pos1) + value;
                }
            } else {
                if (returnObj.indexOf('?') > -1) {
                    returnObj += '&' + key + value;
                } else {
                    returnObj += '?' + key + value;
                }
            }
        }
        url = returnObj;
        return returnObj;
    };

    var replaceObject = function(object) {
        for (var i in object) {
            this.replace(i, object[i]);
        }
        return url;
    };

    var getParam = function(key) {
        var returnObj = null;
        if (key) {
            key += '=';
            var pos1 = url.indexOf(key);
            if (pos1 > 0) {
                pos1 += key.length;
                var pos2 = url.indexOf('&', pos1);
                if (pos2 > -1) {
                    returnObj = url.substring(pos1, pos2);
                } else {
                    returnObj = url.substring(pos1);
                }
            }
        }
        return returnObj;
    };

    return {
        get: function() {
            return url;
        },
        set: function(newUrl) {
            url = newUrl;
        },
        replace: replace,
        replaceObject: replaceObject,
        getParam: getParam
    };
};