var local = window.location;  
var contextPath = local.pathname.split("/")[1];  
var basePath = local.protocol+"//"+local.host+"/"+contextPath;

loadHomePage();

function subPageChecks() {
	$('form.ajax').on('submit', function(e) {
		var url=$(this).attr("action");
		url=basePath+url;
		var redirect=$(this).attr("redirect");	
	    $(this).ajaxSubmit({
	        url: url, 
	        dataType:"json",
	        success: function(data) { 
	        	if(data.status=="OK") {
					renderBody(redirect);
				} else {
					alert("错误原因："+data.errorDescription);
				}
	        }
	    });
	    e.preventDefault();
	});
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
			treeNode[0]={text:"菜单管理",url:"/menu",type:"URL"};
			var menuArray=data.result;
			for(var i=0;i<menuArray.length;i++) {
				var menu=menuArray[i];
				var node=createNodeFromMenu(menu);
				treeNode[i+1]=node;
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
		return {text:menu.name,url:menu.url,type:menu.type};
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
			  renderBody(data.url);
		  }	    
	  },
	});
}

function renderBody(url) {
	url=basePath+url;
	$("#body").load(url,function(){
		subPageChecks();
	});
}

//后台增删页面调用，删除接口调用
function addPage(url,data) {
	var addPageUrl=url+"/add";
	renderBody(addPageUrl);
}
function editPage(url,id) {
	var editPageUrl=url+"/"+id+"/edit";
	renderBody(editPageUrl);
}
function del(url,id) {
	bootbox.confirm("确定要删除它吗？",
	function(result){
	if(!result)return;
		var deleteUrl=basePath+url+"/"+id;
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