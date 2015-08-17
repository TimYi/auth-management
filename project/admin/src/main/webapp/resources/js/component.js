/**
 * 
 */

/**
 * 权限树
 * 权限树data格式：{id,name,code,type,description,operations:{id,name,code,type,description}}
 */
function PermissionTree(selector,origionPermissions,size) {
	
	this.area=$(selector);		
	this.treeviewArea=$("<div id='treeview-area'></div>");
	this.treeviewArea.appendTo(this.area);
	this.treeviewPagination=$("<div id='treeview-pagination'></div>");
	this.treeviewPagination.appendTo(this.area);	
	this.permissions=new KeySet();
	
	if(!PermissionTree.prototype._initialized) {
		PermissionTree.prototype._initialized=true;
		
		/**绘制权限树*/
		PermissionTree.prototype.render=function(data) {
			var nodes=this.resolveNodes(data.result);
			var pt=this;
			this.treeviewArea.treeview({
				data: nodes,
			    levels: 2,
			    backColor: 'transparent',
			    color: "#337ab7",	 
			    showCheckbox:true,
			    onNodeChecked: function(event, node) {
				    pt.addPermission(node.id);
			    },
			    onNodeUnchecked: function(event, node) {
			    	pt.removePermission(node.id);
			    }
			});			
			this.treeviewPagination.twbsPagination({
				startPage: data.page,
		        totalPages: data.totalPages,
		        visiblePages: 5,
		        first:"首页",
		        prev:"上一页",
		        next:"下一页",
		        last:"尾页",
		        paginationClass:'pagination',
		        onPageClick: function (event, page) {
		        	pt.refresh(page,data.size);
		        }
		    });
			this._renderSelectedPermissions();
		}
		
		/**将权限数据解析为权限树模型*/
		PermissionTree.prototype.resolveNodes=function(permissions) {
			var nodes=new Array();
			for(var i=0;i<permissions.length;i++) {
				var p=permissions[i];
				var node=this.resolveNode(p);
				if(typeof(p.operations)!="undefined" && p.operations!=null) {
					var subNodes=new Array();
					for(var j=0;j<p.operations.length;j++) {
						var o=p.operations[j];
						var subNode=this.resolveNode(o);
						subNodes.push(subNode);
					}
					node.nodes=subNodes;
				}
				nodes.push(node);
			}
			return nodes;
		}
		
		/**解析单个权限*/
		PermissionTree.prototype.resolveNode=function(p) {
			var node={text:p.name,id:p.id,type:p.type,code:p.code,description:p.description};
			return node;
		}
		
		/**重新绘制选中的权限*/
		PermissionTree.prototype._renderSelectedPermissions=function() {
			var treeview=this.treeviewArea.data('treeview');			
			var nodes=treeview.findNodesByIds(this.permissions.values);
			treeview.uncheckAll({silent: true });
			for(var i=0;i<nodes.length;i++) {
				var node=nodes[i];
				treeview.checkNode(node,{silent:true});
			}
		}
		
		PermissionTree.prototype.RequestUrl=basePath+"/permission/rest";
			
		/**请求新页面，并刷新整个权限树*/
		PermissionTree.prototype.refresh=function(page,size) {
			var url=this.RequestUrl+"/page/"+page+"?size="+size;
			var pt=this;
			$.ajax({
				url:url,
				dataType:'json',
				success:function(data) {
					if(data.status=="OK") {
						pt.render(data.result);
					} else {
						alert(data.errorDescription);
					}
				},
				error:function(XMLHttpRequest, textStatus, errorThrown) {
					alert("错误:"+errorThrown);
				}
			});
		}
		
		/**取出已选权限id列表*/
		PermissionTree.prototype.getPermissions=function() {
			return this.permissions.values;
		}
		
		/**刷新已有权限列表*/
		PermissionTree.prototype.setPermissions=function(permissions) {
			if(!permissions) permissions=[];
			this.permissions.replaceAll(permissions);
			this._renderSelectedPermissions();
		}
		
		PermissionTree.prototype.addPermission=function(permission) {
			this.permissions.add(permission);
		}
		
		PermissionTree.prototype.removePermission=function(permission) {
			this.permissions.remove(permission);
		}
	}
	
	size=size?size:8;
	if(origionPermissions) {
		this.permissions.addAll(origionPermissions);
	}
	this.refresh(1,size);
}

//数据结构roles：{id:id,permissions:[p1,p2]}
function DepartmentRolePermissionTree(selector, rolePermissions, size) {
	
	this.roles=new KeyMap();
	if(typeof(rolePermissions)!="undefined") {
		for(var i=0;i<rolePermissions.length;i++) {
			var r=rolePermissions[i];
			var ps=new Array();
			for(var j=0;j<r.permissions.length;j++) {
				var p=r.permissions[j];
				ps.push(p);
			}
			this.roles.put(r.id,ps);
		}
	}
	this.area=$(selector);
	this.roleArea=$("<div class='col-sm-6'></div>");
	this.roleArea.appendTo(this.area);
	this.permissionArea=$("<div class='col-sm-6'></div>");
	this.permissionArea.appendTo(this.area);
	
	this.treeviewArea=$("<div></div>");
	this.treeviewArea.appendTo(this.roleArea);
	this.treeviewPagination=$("<div'></div>");
	this.treeviewPagination.appendTo(this.roleArea);
	
	this.permissionTree=new PermissionTree(this.permissionArea);
	
	if(!DepartmentRolePermissionTree.prototype._initialized) {
		DepartmentRolePermissionTree.prototype._initialized=true;
		
		DepartmentRolePermissionTree.prototype.render=function(data) {
			var nodes=this.resolveNodes(data.result);
			var pt=this;
			this.treeviewArea.treeview({
				data: nodes,
			    levels: 1,
			    backColor: 'transparent',
			    color: "#337ab7",	 
			    showCheckbox:true,
			    onNodeChecked: function(event, node) {
			    	pt.check(node);
			    	pt.focus(node);
			    },
			    onNodeUnchecked: function(event, node) {
			    	pt.uncheck(node);
			    },
			    onNodeSelected: function(event, node) {
			    	pt.focus(node);
			    },
			    onNodeUnselected: function(event, node) {
			    	pt.blur(node);
			    }
			});			
			this.treeviewPagination.twbsPagination({
				startPage: data.page,
		        totalPages: data.totalPages,
		        visiblePages: 5,
		        first:"首页",
		        prev:"上一页",
		        next:"下一页",
		        last:"尾页",
		        paginationClass:'pagination',
		        onPageClick: function (event, page) {
		        	pt.refresh(page,data.size);
		        }
		    });
			this._renderCheckedRoles();
		}
		
		/**重新绘制选中的权限*/
		DepartmentRolePermissionTree.prototype._renderCheckedRoles=function() {
			var treeview=this.treeviewArea.data('treeview');			
			var nodes=treeview.findNodesByIds(this.roles.keys());
			treeview.uncheckAll({silent: true });
			for(var i=0;i<nodes.length;i++) {
				var node=nodes[i];
				treeview.checkNode(node,{silent:true});
			}
		}
		
		/**将权限数据解析为权限树模型*/
		DepartmentRolePermissionTree.prototype.resolveNodes=function(roles) {
			var nodes=new Array();
			for(var i=0;i<roles.length;i++) {
				var r=roles[i];
				var node=this.resolveNode(r);
				nodes.push(node);
			}
			return nodes;
		}
		
		/**解析单个权限*/
		DepartmentRolePermissionTree.prototype.resolveNode=function(r) {
			var node={text:r.name,id:r.id,type:r.type,description:r.description};
			return node;
		}
		
		/**选中*/
		DepartmentRolePermissionTree.prototype.check=function(node) {
			this.roles.put(node.id,[]);
		}
		
		/**取消选中*/
		DepartmentRolePermissionTree.prototype.uncheck=function(node) {
			this.roles.remove(node.id);
		}
		
		/**聚焦*/
		DepartmentRolePermissionTree.prototype.focus=function(node) {
			this.savePermissions();
			this.currentId=node.id;
			var permissions=this.roles.get(this.currentId);
			this.permissionTree.setPermissions(permissions);
		}
		
		/**失去焦点*/
		DepartmentRolePermissionTree.prototype.blur=function() {
			this.savePermissions();
			this.currentId=null;
			this.permissionTree.setPermissions([]);
		}
		
		/**保存对一个角色的操作数据*/
		DepartmentRolePermissionTree.prototype.savePermissions=function() {
			if(this.currentId && this.currentId!=null) {
				if(this.roles.contains(this.currentId)>=0) {
	    			var permissions=this.permissionTree.getPermissions();
		    		this.roles.put(this.currentId,permissions);
	    		}
			}
		}
		
		/**刷新角色区域*/
		DepartmentRolePermissionTree.prototype.RequestUrl=basePath+"/role/rest/department/";
		
		/**请求新页面，并刷新整个角色权限限树*/
		DepartmentRolePermissionTree.prototype.refresh=function(page,size) {
			var url=this.RequestUrl+"/page/"+page+"?size="+size;
			var pt=this;
			$.ajax({
				url:url,
				dataType:'json',
				success:function(data) {
					if(data.status=="OK") {
						pt.render(data.result);
					} else {
						alert(data.errorDescription);
					}
				},
				error:function(XMLHttpRequest, textStatus, errorThrown) {
					alert("错误:"+errorThrown);
				}
			});
		}
	}
	size=size?size:8;
	this.refresh(1,size);
}