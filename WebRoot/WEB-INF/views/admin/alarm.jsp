<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>报警记录</title>
	<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : '${ctx}' + '/alarm/dataGrid',
			fit : true,
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'id',
			sortName : 'id',
			sortOrder : 'desc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
			columns : [ [ {
				width : '90',
				title : '编号',
				field : 'id',
				sortable : true
			} , {
				width : '180',
				title : '企业',
				field : 'psName',
				sortable : true
			} , {
				width : '120',
				title : '排口',
				field : 'portName',
				sortable : true
			} , {
				width : '150',
				title : '报警类型',
				field : 'type',
				sortable : true
			} , {
				width : '280',
				title : '报警内容',
				field : 'text',
				sortable : true
			} , {
				width : '140',
				title : '报警时间',
				field : 'time'
			} , {
				width : '120',
				title : '状态',
				field : 'state',
				sortable : true
			} , {
				width : '120',
				title : '操作情况',
				field : 'operation',
				sortable : true
			} , {
				field : 'alarmMan',
				title : '短信接收人',
				width : '140'
			} , {
				field : 'tel',
				title : '联系方式',
				width : '140'
			} ] ],
			toolbar : '#toolbar'
		});
		
		$('#qy').combobox({
			url:'${ctx}/alarm/pstree',
			valueField:'text',
			textField:'text',
			multiple:false,
			panelHeight:'auto'
			});
		
		$('#bjlx').combobox({
			url:'${ctx}/alarm/alarmtypetree',
			valueField:'text',
			textField:'text',
			multiple:false,
			panelHeight:'auto'
			});
	});

	
	function searchFun() {
		dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
	}
	function cleanFun() {
		$('#searchForm input').val('');
		dataGrid.datagrid('load', {});
	}
	
	function exp(){
		$('#searchForm').form({
			url : '${ctx}/alarm/export',
			onSubmit : function() {
				
			},
			success : function(result) {
				
			}
		});
		$('#searchForm').submit();
	}
	</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false" style="height: 30px; overflow: hidden;background-color: #fff">
		<form id="searchForm" method="post">
			<table>
				<tr>
					<th>企业:</th>
					<td><input id="qy" name="company" type="text" placeholder="请输入企业名称"/></td>
					<th>报警类型:</th>
					<td><input id="bjlx" name="type" type="text" placeholder="请输入报警类型"/></td>
					<th>时间:</th>
					<td><input name="createDatetimeStart" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />至<input  name="createDatetimeEnd" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="cleanFun();">清空</a></td>
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center',border:true">
		<table id="dataGrid" data-options="fit:true,border:false"></table>
	</div>
	<div id="toolbar" style="display: none;">
		<c:if test="${fn:contains(sessionInfo.resourceList, '/role/add')}">
			<!--a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a-->
		</c:if>
		<a onclick="exp();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">导出</a>
	</div>
</body>
</html>