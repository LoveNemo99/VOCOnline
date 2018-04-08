<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="edge" />
<c:if test="${fn:contains(sessionInfo.roleName, '企业用户')}">
	<script type="text/javascript">
		$.canEdit = true;
		$.canDelete = true;
		$.canSubmit = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.roleName, '环保部门')}">
	<script type="text/javascript">
	    $.canGrant = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.roleName, '超级管理员')}">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<title>月计划</title>
	<script type="text/javascript">
	var del_question = '您是否要删除当前月计划？';
	var del_path = '${ctx}/monthplan/delete';
	var submit_path = '${ctx}/monthplan/submit';
	var dataGrid_path = '${ctx}/monthplan/dataGrid';
	var addPage_path = '${ctx}/monthplan/addPage';
	var editPage_path = '${ctx}/monthplan/editPage?id=';
	var factorPage_path = '${ctx}/monthplan/factorPage?id=';
	var grantPage_path = '${ctx}/monthplan/grantPage?id=';
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : dataGrid_path,
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'id',
			sortName : 'id',
			sortOrder : 'asc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
			columns : [ [ {
				width : '100',
				title : '企业名称',
				field : 'psCode',
				sortable : true
			} , {
				width : '120',
				title : '月份',
				field : 'month',
				sortable : true
			} , {
				width : '150',
				title : '申请人',
				field : 'applyMan',
				sortable : true
			} , {
				width : '150',
				title : '申请时间',
				field : 'applyTime',
				sortable : true
			} , {
				width : '200',
				title : '状态',
				field : 'approve',
				sortable : true
			} , {
				field : 'action',
				title : '操作',
				width : 180,
				formatter : function(value, row, index) {
					var str = '&nbsp;';
					if ($.canEdit) {
						str += $.formatString('<a href="javascript:void(0)" onclick="editFun(\'{0}\');" >编辑</a>', row.id);
						str += '&nbsp;&nbsp;&nbsp;&nbsp;';
						str += $.formatString('<a href="javascript:void(0)" onclick="factorEditFun(\'{0}\');" >因子设置</a>', row.id);
						str += '&nbsp;&nbsp;&nbsp;&nbsp;';
					}
					if ($.canDelete) {
						str += $.formatString('<a href="javascript:void(0)" onclick="deleteFun(\'{0}\');" >删除</a>', row.id);
						str += '&nbsp;&nbsp;&nbsp;&nbsp;';
					}
					if ($.canSubmit) {
						str += $.formatString('<a href="javascript:void(0)" onclick="submitFun(\'{0}\');" >提交</a>', row.id);
						str += '&nbsp;&nbsp;&nbsp;&nbsp;';
					}
					if ($.canGrant) {
						str += $.formatString('<a href="javascript:void(0)" onclick="grantFun(\'{0}\');" >审核</a>', row.id);
						str += '&nbsp;&nbsp;&nbsp;&nbsp;';
					}
					return str;
				}
			} ] ],
			toolbar : '#toolbar'
		});
		$('#psCode').combobox({
			url:'${ctx}/monthplan/pstree',
			valueField:'value',
			textField:'text',
			multiple:false,
			panelHeight:'auto'
			});
	});
	
	function addFun() {
		parent.$.modalDialog({
			title : '添加',
			width : 450,
			height : 280,
			href : addPage_path,
			buttons : [ {
				text : '添加',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#AddForm');
					f.submit();
				}
			} ]
		});
	}
	
	function deleteFun(id) {
		if (id == undefined) {//点击右键菜单才会触发这个
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {//点击操作里面的删除图标会触发这个
			dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.messager.confirm('询问', del_question, function(b) {
			if (b) {
				progressLoad();
				$.post(del_path, {
					id : id
				}, function(result) {
					if (result.success) {
						parent.$.messager.alert('提示', result.msg, 'info');
						dataGrid.datagrid('reload');
					}
					progressClose();
				}, 'JSON');
			}
		});
	}
	
	function submitFun(id) {
		if (id == undefined) {//点击右键菜单才会触发这个
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {//点击操作里面的删除图标会触发这个
			dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.messager.confirm('询问', '确认提交？', function(b) {
			if (b) {
				progressLoad();
				$.post(submit_path, {
					id : id
				}, function(result) {
					if (result.success) {
						parent.$.messager.alert('提示', result.msg, 'info');
						dataGrid.datagrid('reload');
					}
					progressClose();
				}, 'JSON');
			}
		});
	}
	
	function editFun(id) {
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {
			dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.modalDialog({
			title : '编辑',
			width : 450,
			height : 280,
			href : editPage_path + id,
			buttons : [ {
				text : '保存',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#EditForm');
					f.submit();
				}
			} ]
		});
	}
	function factorEditFun(id) {
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {
			dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.modalDialog({
			title : '因子设置',
			width : 300,
			height : 300,
			href : factorPage_path + id,
			buttons : [ {
				text : '保存',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#factorEditForm');
					f.submit();
				}
			} ]
		});
	}
	function grantFun(id) {
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {
			dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.modalDialog({
			title : '因子设置',
			width : 300,
			height : 300,
			href : grantPage_path + id,
			buttons : [ {
				text : '同意',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#grantForm');
					f.submit();
					$.ajax({
						type : "POST",
						url : "${ctx}/monthplan/grant",
						dataType : "json",
						data:{"id":parseInt(id)},
						success : function(data) {
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
							alert("数据加载失败，请重试！");
						}});
				}
			},{
				text : '不同意',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#grantForm');
					f.submit();
					$.ajax({
						type : "POST",
						url : "${ctx}/monthplan/notgrant",
						dataType : "json",
						data:{"id":parseInt(id)},
						success : function(data) {
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
							alert("数据加载失败，请重试！");
						}});
				}
			} ]
		});
	}
	function searchFun() {
		dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
	}
	function cleanFun() {
		$('#searchForm input').val('');
		dataGrid.datagrid('load', {});
	}
	
	</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<c:if test="${fn:contains(sessionInfo.roleName, '环保部门')}">
		<div data-options="region:'north',border:false" style="height: 30px; overflow: hidden;background-color: #fff">
		<form id="searchForm" method="post">
			<table>
				<tr>
					<th>企业:</th>
					<td><input id="psCode" name="psCode" type="text" placeholder="请输入企业名称"/></td>
					<th>月份:</th>
					<td><input name="month" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM'})" readonly="readonly">
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="cleanFun();">清空</a></td>
				</tr>
			</table>
		</form>
	</div>
	</c:if>
	<div data-options="region:'center',fit:true,border:false">
		<table id="dataGrid" data-options="fit:true,border:false"></table>
	</div>
	<div id="toolbar" style="display: none;">
		<c:if test="${fn:contains(sessionInfo.roleName, '企业用户')}">
			<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
		</c:if>
	</div>
</body>
</html>