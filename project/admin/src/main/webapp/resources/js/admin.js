
loadHomePage();

dynamicTab.init("#body",subPageChecks);

function subPageChecks() {
	$('form.ajax').on('submit', function(e) {
		var url=$(this).attr("action");
		url=basePath+url;
		var oldkey=$(this).parent("div.tab-pane").attr("id");
		var redirect=$(this).attr("redirect");			
	    $(this).ajaxSubmit({
	        url: url, 
	        dataType:"json",
	        success: function(data) { 
	        	if(data.status=="OK") {	
	        		alert(data.result);
					if(typeof(redirect)=="undefined" || redirect==null) {
						dynamicTab.close(oldkey);
					} else {
						jumpTo(oldkey,redirect);
					}
				} else {
					alert("错误原因："+data.errorDescription);
				}
	        }
	    });
	    e.preventDefault();
	});
	
	$('a.ajax').on('click', function(e) {
		var url=$(this).attr("href");
		var oldkey=$(this).parent("div.tab-pane").attr("id");
		if($(this).attr("target")=="_Blank") {
			createTab(url);
		} else {
			jumpTo(oldkey,url);
		}		
		e.preventDefault();
	})
}

function createTab(url) {
	var key=url.split("?")[0];
	var fullurl=basePath+url;
    key=key.replaceAll("/","-");
    var tab={key:key,url:fullurl,name:"未命名"};
    dynamicTab.create(tab);
}

function jumpTo(oldkey,url,key) {
	if(!key) {
		key=url.split("?")[0];
		key=key.replaceAll("/","-");
	}	
	var fullurl=basePath+url;    
    var tab={key:key,url:fullurl,name:"未命名"};
    dynamicTab.jump(oldkey,tab);
}

function refreshTab(url) {
	var key=url.split("?")[0];
    key=key.replaceAll("/","-");
    dynamicTab.refresh(key);
}

function ajax(params) {
	if(params.success) {
		var success=params.success;
		params.success=function(data) {
			if(data.status=="OK") {
				success(data.result);
			} else {
				alert("错误原因："+data.errorDescription);
			}
		}
	}
	var url=params.url;
	url=basePath+url;
	params.url=url;
	params.dataType="json";
	$.ajax(params);
}

//加载首页，ajax获取导航菜单，写死第一条为菜单管理
function loadHomePage() {
	var url=basePath+"/menu/accessedTops";
	$.ajax({
		url:url,
		dataType:"json",
		success:function(data) {
			var treeNode=new Array();
			var menuArray=data.result;
			for(var i=0;i<menuArray.length;i++) {
				var menu=menuArray[i];
				var node=createNodeFromMenu(menu);
				treeNode[i]=node;
			}
			treeview(treeNode);			
		},
		error:function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
}

function createNodeFromMenu(menu) {
	if(menu.type=="URL") {
		return {text:menu.name,url:menu.url,type:menu.type,id:menu.id};
	} else {
		var nodes=createNodesFromMenus(menu.subMenus);
		return {text:menu.name,type:menu.type,nodes:nodes}
	}
}

function createNodesFromMenus(menus) {
	var nodes=new Array();
	for(var i=0;i<menus.length;i++) {
		var menu=menus[i];
		var node=createNodeFromMenu(menu);
		nodes.push(node);
	}
	return nodes;
}

function treeview(data) {
	$('#tree').treeview({
	  data: data,
	  levels: 3,
	  backColor: 'transparent',
	  color: "#337ab7",	  
	  onNodeSelected: function(event, data) {
		  if(data.type=="URL") {
			  createTab(data.url);
		  }	    
	  },
	});
}

//后台增删页面调用，删除接口调用
function addPage(url,params) {
	var addPageUrl=url+"/add";
	if(params) {
		addPageUrl=addPageUrl+"?"+params;
	}
	createTab(addPageUrl);
}
function editPage(url,id) {
	var editPageUrl=url+"/"+id+"/edit";
	createTab(editPageUrl);
}
function del(url,id) {
	bootbox.confirm("确定要删除它吗？",
	function(result){
	if(!result)return;
		var deleteUrl=url+"/"+id;
		ajax({
			url:deleteUrl,
			method:"DELETE",
			dataType:"json",
			success:function() {
				alert("删除成功！");
				$("#"+id).remove();
			},
			error:function(XMLHttpRequest, textStatus, errorThrown) {
				alert(errorThrown);
			}
		});
	});
}

/**
 * 分页
 */
function pagination(totalPages,startPage,url,size,id) {
	startPage=startPage?startPage:1;
	if(totalPages==0) totalPages=1;
	size=size?size:8;
	id=id?id:"#pagination";
	var visiblePages=5;
	var pagination=$(id);
	oldkey=pagination.parent("div.tab-pane").attr("id");
	pagination.twbsPagination({
		startPage:startPage,
        totalPages: totalPages,
        visiblePages: visiblePages,
        first:"首页",
        prev:"上一页",
        next:"下一页",
        last:"尾页",
        paginationClass:'pagination',
        onPageClick: function (event, page) {
        	var key=url.replaceAll("/","-");
            var jumpUrl=url+"/page/"+page+"?size="+size;
            jumpTo(oldkey,jumpUrl,key);
        }
    });
	pagination.find("li.active").removeClass("active");
	pagination.find("a:contains('"+startPage+"')").parent("li.page").addClass("active");
}