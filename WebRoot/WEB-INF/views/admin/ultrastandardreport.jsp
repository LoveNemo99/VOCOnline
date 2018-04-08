<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>超标报表</title>
	<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : '${ctx}' + '/ultrastandard/dataGrid',
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
				field : 'psCode',
				sortable : true
			} , {
				width : '120',
				title : '排口',
				field : 'portCode',
				sortable : true
			} , {
				width : '120',
				title : '污染物因子',
				field : 'factorCode',
				sortable : true
			} , {
				width : '140',
				title : '时间',
				field : 'time'
			} , {
				width : '120',
				title : '监测值区间',
				field : 'monitorValue',
				sortable : true
			} , {
				width : '120',
				title : '标准值',
				field : 'standardValue',
				sortable : true
			} , {
				width : '150',
				title : '超标次数',
				field : 'ultraStandardTimes',
				sortable : true
			} , {
				width : '120',
				title : '超标小时数',
				field : 'ultraStandardHours',
				sortable : true
			} , {
				width : '120',
				title : '操作',
				field : 'operation',
				sortable : true
			}]],
			toolbar : '#toolbar'
		});
		
		$('#qy').combobox({
			url:'${ctx}/ultrastandard/pstree',
			valueField:'value',
			textField:'text',
			multiple:false,
			panelHeight:'auto',
			onSelect: function(rec){
				var ur = '${ctx}/ultrastandard/porttree?psCode='+rec.value;
				$('#port').combobox('reload', ur);
			}
			});
		
		$('#port').combobox({
			valueField:'value',
			textField:'text',
			multiple:false,
			panelHeight:'auto'
			});
		
		$('#factor').combobox({
			url:'${ctx}/ultrastandard/factortree',
			valueField:'value',
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
			url : '${ctx}/ultrastandard/export',
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
					<td><input id="qy" name="psCode" type="text" placeholder="请输入企业名称"/></td>
					<th>排口:</th>
					<td><input id="port" name="portCode" type="text" placeholder="请输入排口名称"/></td>
					<th>因子:</th>
					<td><input id="factor" name="factorCode" type="text" placeholder="请输入因子名称"/></td>
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