/**
 * 技术数据组件支撑
 */

/**导出及路径，basePath*/
(function() {
	var local = window.location;  
	var contextPath = local.pathname.split("/")[1];  
	basePath = local.protocol+"//"+local.host+"/"+contextPath;
}());

/**
 * 为String类型添加format方法
 */
if (!String.format) {
  String.format = function(format) {
    var args = Array.prototype.slice.call(arguments, 1);
    return format.replace(/{(\d+)}/g, function(match, number) { 
      return typeof args[number] != 'undefined'
        ? args[number] 
        : match
      ;
    });
  };
}

if(!String.replaceAll) {
	String.prototype.replaceAll = function(s1,s2){
		return this.replace(new RegExp(s1,"gm"),s2);
	}
}

Array.prototype.remove=function(index){ 
	this.splice(index,1); 
} 


function TabMap() {
	this.tabs=new Array();
	
	if(!TabMap.prototype._initialized) {
		TabMap.prototype._initialized=true;
		
		/**
		 * 添加tab，如果之前并没有重复，返回true，否则返回false
		 */
		TabMap.prototype.add=function(tab) {
			var key=tab.key;
			if(this.contains(key)<0) {
				this.tabs.push(tab);
				return true;
			}
			return false;
		}
		
		/**
		 * 删除和key对应的tab
		 */
		TabMap.prototype.remove=function(key) {
			var i=this.contains(key);
			if(i>=0) {
				this.tabs.remove(i);
			}
		}
		
		TabMap.prototype.get=function(key) {
			var i=this.contains(key);
			if(i>=0) {
				return this.tabs[i];
			}
			return null;
		}
		
		/**
		 * 检查key是否已存在，如果存在，返回其所在index，否则返回-1
		 */
		TabMap.prototype.contains=function(key) {
			for(var i=0;i<this.tabs.length;i++) {
				var currentTab=this.tabs[i];
				if(currentTab.key==key) {
					return i;
				}
			}
			return -1;
		}
	}
}

function KeySet() {
	this.values=new Array();
	if(!KeySet.prototype._initialized) {
		KeySet.prototype._initialized=true;
		
		/**
		 * 添加tab，如果之前并没有重复，返回true，否则返回false
		 */
		KeySet.prototype.add=function(value) {
			if(this.contains(value)<0) {
				this.values.push(value);
				return true;
			}
			return false;
		}
		
		KeySet.prototype.addAll=function(values) {
			for(var i=0;i<values.length;i++) {
				var value=values[i];
				this.add(value);
			}
		}
		
		KeySet.prototype.replaceAll=function(values) {
			this.values=new Array();
			this.addAll(values);
		}
		
		/**
		 * 删除和key对应的tab
		 */
		KeySet.prototype.remove=function(value) {
			var i=this.contains(value);
			if(i>=0) {
				this.values.remove(i);
			}
		}
		
		/**
		 * 检查key是否已存在，如果存在，返回其所在index，否则返回-1
		 */
		KeySet.prototype.contains=function(value) {
			for(var i=0;i<this.values.length;i++) {
				var currentValue=this.values[i];
				if(currentValue==value) {
					return i;
				}
			}
			return -1;
		}
		
		KeySet.prototype.values=function() {
			return this.values;
		}
	}
}

function KeyMap() {
	this.tabs=new Array();
	if(!KeyMap.prototype._initialized) {
		KeyMap.prototype._initialized=true;
		
		/**
		 * 添加tab，如果之前并没有重复，返回true，否则返回false
		 */
		KeyMap.prototype.put=function(key,value) {
			var i=this.contains(key);
			if(i<0) {
				this.tabs.push({key:key,value:value});
			} else {
				this.tabs[i]={key:key,value:value};
			}
		}
		
		KeyMap.prototype.keys=function() {
			var keys=new Array();
			for(i=0;i<this.tabs.length;i++) {
				var tab=this.tabs[i];
				keys.push(tab.key);
			}
			return keys;
		}
		
		KeyMap.prototype.length=function() {
			return tabs.length;
		}
		
		KeyMap.prototype.getKey=function(i) {
			return tabs[i].key;
		}
		
		KeyMap.prototype.getValue=function(i) {
			return tabs[i].value;
		}
		
		/**
		 * 删除和key对应的tab
		 */
		KeyMap.prototype.remove=function(key) {
			var i=this.contains(key);
			if(i>=0) {
				this.tabs.remove(i);
			}
		}
		
		KeyMap.prototype.get=function(key) {
			var i=this.contains(key);
			if(i>=0) {
				return this.tabs[i].value;
			}
			return null;
		}
		
		/**
		 * 检查key是否已存在，如果存在，返回其所在index，否则返回-1
		 */
		KeyMap.prototype.contains=function(key) {
			for(var i=0;i<this.tabs.length;i++) {
				var currentTab=this.tabs[i];
				if(currentTab.key==key) {
					return i;
				}
			}
			return -1;
		}
	}
}