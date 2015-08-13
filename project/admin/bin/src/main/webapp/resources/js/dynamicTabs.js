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

function DynamicTab() {	
	
	this.tabs=new TabMap();
	this.tabArea=$("<ul id=\"myTab\" class=\"nav nav-tabs\"></ul>");
	this.contentArea=$("<div id=\"myTabContent\" class=\"tab-content\"></div>");
	this.callback=null;
	
	this.init=function(selecter,callback) {
		this.callback=callback;
		var parentArea=$(selecter);
		this.tabArea.appendTo(parentArea);
		this.contentArea.appendTo(parentArea);
	}
	
	/**
	 * 创建新页面或者刷新已有页面
	 */
	this.create=function(tab) {
		if(this.tabs.add(tab)) {
			//TODO 添加tab
		} else {
			this.refresh(tab.key);
		}
	}
	
	/**
	 * 刷新已有页面
	 */
	this.refresh=function(key) {
		var tab=this.tabs.get(key);
		if(tab!=null) {
			//TODO 刷新tab
		}
	}
	
	/**
	 * 关闭页面
	 */
	this.close=function(key) {
		var tab=this.tabs.get(key);
		if(tab!=null) {
			//TODO 关闭tab
		}
		this.tabs.remove(key);
	}
	
	/**
	 * 在某个tab上进行跳转
	 */
	this.jump=function(oldKey,tab) {
		this.close(oldKey);
		this.create(tab);
	}
	
	this.contentFormat="<div class=\"tab-pane fade\" id=\"{0}\">{1}</div>"
	this.createContent(key,content) {
		var content=$(String.format(this.contentFormat,key,content));
		return content;
	}
	this.tabFormat="<li><a href=\"#{0}\" data-toggle=\"tab\">{1}</a></li>";
	this.createTab(key,name,content) {
		var title=content.find("#title").html();
		if(title!=null) {
			name=title;
		}
		var tab=$(String.format(this.tabFormat,key,name));
		return tab;
	}
}

function TabMap() {
	this.tabs=new Array();
	/**
	 * 添加tab，如果之前并没有重复，返回true，否则返回false
	 */
	this.add=function(tab) {
		var key=tab.key;
		if(this.contains(key)<0) {
			return false;
		}
		this.tabs.push(tab);
		return true;
	}
	/**
	 * 删除和key对应的tab
	 */
	this.remove=function(key) {
		var i=this.contains(key);
		if(i>=0) {
			this.tabs.remove(i);
		}
	}
	this.get=function(key) {
		var i=this.contains(key);
		if(i>=0) {
			return this.tabs[i];
		}
		return null;
	}
	/**
	 * 检查key是否已存在，如果存在，返回其所在index，否则返回-1
	 */
	this.contains=function(key) {
		for(var i=0;i<this.tabs.length;i++) {
			var currentTab=this.tabs[i];
			if(currentTab.key==key) {
				return i;
			}
		}
		return -1;
	}
}
