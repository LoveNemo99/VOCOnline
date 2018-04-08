<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>远程反控采样</title>
	<script type="text/javascript">
	var dataGrid;
	var intervalId;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : '${ctx}' + '/sample/dataGrid',
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
				width : '80',
				title : '企业',
				field : 'pollutantSourceName',
				sortable : true
			} , {
				width : '250',
				title : '执行时间',
				field : 'executionTime',
				sortable : true
			}, {
				width : '120',
				title : '指令',
				field : 'ctrlCode',
				sortable : true,
				formatter : function(value, row, index) {
					if(value == '3012'){return '反控采样';}
				}
			} , {
				field : 'result',
				title : '执行结果',
				width : '270'
			} ] ],
			toolbar : '#toolbar'
		});
		
		$('#qy').combobox({
			url:'${ctx}/info/pstree',
			valueField:'text',
			textField:'text'
			});
		
		intervalId = setInterval(refresh,10000);
	});

	function autoFun(){
		var items = document.getElementsByName("auto");
		if(items[0].checked){
			intervalId = setInterval(refresh,5000);
		}else{
			clearInterval(intervalId);
		}
	}
	function refresh(){
		dataGrid.datagrid('reload');
	}
	
	function searchFun() {
		dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
	}
	function cleanFun() {
		$('#searchForm input').val('');
		dataGrid.datagrid('load', {});
	}
	
	function ctrlFun(){
		parent.$.modalDialog({
			title : '采样',
			width : 250,
			height : 250,
			href : '${ctx}/sample/addPage',
			buttons : [ {
				text : '确定',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#ctrlForm');
					f.submit();
				}
			} ]
		});
	}
	</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false" style="height: 30px; overflow: hidden;background-color: #fff">
		<form id="searchForm" method="post">
			<table>
				<tr>
					<th>企业:</th>
					<td><input id="qy" name="pollutantSourceName" type="text" placeholder="请输入企业名称"/></td>
					<th>时间:</th>
					<td><input name="startTime" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />至<input  name="endTime" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="cleanFun();">清空</a>
					<!--a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-btn',plain:true" onclick="tiFun();">提取阀门状态</a-->
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-btn',plain:true" onclick="ctrlFun();">远程反控采样</a></td>
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
		<input type="checkbox" name="auto" checked="checked" onclick="autoFun()"/>自动刷新
		<!--a onclick="exp();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">导出</a-->
	</div>
</body>
</html>