document.write("<style>" +
		"#title {display:none}"+
		"</style>");

//tab数据格式：name,url,key

function DynamicTab() {	
	
	this.tabs=new TabMap();
	this.tabArea=$("<ul id=\"myTab\" class=\"nav nav-tabs\"></ul>");
	this.contentArea=$("<div id=\"myTabContent\" class=\"tab-content\"></div>");
	this.parentArea=null;
	this.callback;
	
	if(!DynamicTab._initialized) {
		DynamicTab._initialized=true;
		
		DynamicTab.prototype.init=function(selecter,callback) {
			this.callback=callback;
			this.parentArea=$(selecter);
			this.tabArea.appendTo(this.parentArea);
			this.contentArea.appendTo(this.parentArea);
		}
		
		/**创建新页面或者刷新已有页面*/
		DynamicTab.prototype.create=function(tab) {
			if(this.tabs.add(tab)) {
				var dt=this;
				$.ajax({
					url:tab.url,
					success:function(data) {
						var content=dt.createContent(tab.key,data);
						var tabNode=dt.createTab(tab.key,tab.name,content);
						content.appendTo(dt.contentArea);
						tabNode.appendTo(dt.tabArea);
						dt.activeTab(tab.key);
						if(dt.callback) {
							dt.callback();
						}
					}
				});				
			} else {
				this.refresh(tab.key);
			}
		}
		
		/**刷新已有页面*/
		DynamicTab.prototype.refresh=function(key) {
			var tab=this.tabs.get(key);
			if(tab!=null) {
				var dt=this;
				$.ajax({
					url:tab.url,
					success:function(data) {
						dt.refreshContent(tab.key,data);
						dt.activeTab(tab.key);
						if(dt.callback) {
							dt.callback();
						}
					}
				});
			}
		}
		
		/**关闭页面*/
		DynamicTab.prototype.close=function(key) {
			var tab=this.tabs.get(key);
			if(tab!=null) {
				this.tabArea.children("li[key='"+key+"']").remove();
				var content=this.contentArea.children("#"+key);
				content.remove();
			}
			this.tabs.remove(key);
			this.activeTab();
		}
		
		/**在某个tab上进行跳转*/
		DynamicTab.prototype.jump=function(oldKey,tab) {
			this.close(oldKey);
			this.create(tab);
		}
		
		/**创建内容*/
		DynamicTab.prototype.contentFormat="<div class=\"tab-pane fade\" id=\"{0}\" style=\"margin-top:1em;\">{1}</div>"
		DynamicTab.prototype.createContent=function(key,content) {
			var contentNode=$(String.format(this.contentFormat,key,content));
			return contentNode;
		}
		
		DynamicTab.prototype.refreshContent=function(key,content) {
			var contentNode=this.contentArea.children("#"+key);
			contentNode.html(content);
		}
		
		/**创建标签*/
		DynamicTab.prototype.tabFormat="<li key=\"{0}\">" +
				"<a href=\"#{0}\" data-toggle=\"tab\">{1}</a>" +
				"<img src=\""+basePath+"/resources/image/close.png\" onclick=\"dynamicTab.close('{0}')\" style=\"position:absolute;top:0;right:0;height:18px;width:18px;cursor:pointer\"/>"
				"</li>";
		DynamicTab.prototype.createTab=function(key,name,content) {
			var title=content.find("#title").html();
			if(title!=null) {
				name=title;
			}
			var tab=$(String.format(this.tabFormat,key,name));
			return tab;
		}
		
		DynamicTab.prototype.activeTab=function(key) {
			this.tabArea.children("li").each(function(){
				$(this).removeClass("active");
			});
			this.contentArea.children("div").each(function(){
				$(this).removeClass("active");
				$(this).removeClass("in");
			})
			if(!key) {//如果是close的情况，将第一个tab设为active
				var firstTab=$($(this.tabArea.children("li")).get(0));
				var firstContent=$($(this.contentArea.children("div")).get(0));
				firstTab.addClass("active");
				firstContent.addClass("active");
				firstContent.addClass("in");
			} else {
				this.tabArea.children("li[key='"+key+"']").addClass("active");
				this.contentArea.children("#"+key).addClass("active");
				this.contentArea.children("#"+key).addClass("in");
			}			
		}
	}
}

//初始化dynamicTab实例
dynamicTab=new DynamicTab();
window.dynamicTab=dynamicTab;
