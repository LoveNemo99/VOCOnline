<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="edge" />
<c:if test="${fn:contains(sessionInfo.resourceList, '/role/edit')}">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/role/delete')}">
	<script type="text/javascript">
	    $.canDelete = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/role/grant')}">
	<script type="text/javascript">
		$.canGrant = true;
	</script>
</c:if>
<title>ic卡充值记录</title>
	<script type="text/javascript">
	var del_question = '您是否要删除当前充值记录？';
	var del_path = '${ctx}/icrecharge/delete';
	var dataGrid_path = '${ctx}/icrecharge/dataGrid';
	var addPage_path = '${ctx}/icrecharge/addPage';
	var editPage_path = '${ctx}/icrecharge/editPage?id=';
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
				field : 'psBaseName',
				sortable : true
			} , {
				width : '100',
				title : '企业排口',
				field : 'psPiName',
				sortable : true
			} , {
				width : '100',
				title : '污染物',
				field : 'pollutantName',
				sortable : true
			} , {
				width : '100',
				title : '卡号',
				field : 'cardNum',
				sortable : true
			} , {
				width : '100',
				title : '充值量',
				field : 'rechargeQuantity',
				sortable : true
			} , {
				width : '150',
				title : '充值时间',
				field : 'rechargeTime',
				sortable : true
			} ] ],
			toolbar : '#toolbar'
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
	
	</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',fit:true,border:false">
		<table id="dataGrid" data-options="fit:true,border:false"></table>
	</div>
	<div id="toolbar" style="display: none;">
		<c:if test="${fn:contains(sessionInfo.resourceList, '/role/add')}">
			<!--a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a-->
		</c:if>
	</div>
</body>
</html>