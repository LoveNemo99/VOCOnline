<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>日数据废水</title>
	<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : '${ctx}' + '/statistics/dataGridYearGas',
			fit : true,
			striped : true,
			rownumbers : true,
			//pagination : true,
			singleSelect : true,
			remoteSort : true,
			//idField : 'id',
			//sortName : 'id',
			//sortOrder : 'desc',
			//pageSize : 50,
			//pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
			columns : [ [ {
				width : '100',
				title : '企业',
				field : 'pollutionSourceName',
				sortable : false
			}, {
				width : '120',
				title : '数据时间',
				field : 'time',
				sortable : true,
				order : 'desc',
				formatter : function(val,row,index){
			        if(val!=null){
			        	return val.substr(0,4)+'年';
			        }
			    }
			} , {
				width : '140',
				title : '废气(m³)',
				field : 'couB02',
				align : 'right',
				sortable : true,
				order : 'desc',
				formatter : function(val,row,index){
			        if(val!=null){
			        	return val.toFixed(2);
			        }
			        return '-';
			    }
			}, {
				width : '140',
				title : '氧气含量(%)',
				field : 'avgS01',
				align : 'right',
				sortable : true,
				order : 'desc',
				formatter : function(val,row,index){
			        if(val!=null){
			        	return val.toFixed(2);
			        }
			        return '-';
			    }
			}, {
				width : '140',
				title : '氮氧化物(mg/m³)',
				field : 'avg03',
				align : 'right',
				sortable : true,
				order : 'desc',
				formatter : function(val,row,index){
			        if(val!=null){
			        	return val.toFixed(2);
			        }
			        return '-';
			    }
			} , {
				field : 'avg02',
				title : '二氧化硫(mg/m³)',
				width : '140',
				align : 'right',
				sortable : true,
				order : 'desc',
				formatter : function(val,row,index){
			        if(val!=null){
			        	return val.toFixed(2);
			        }
			        return '-';
			    }
			} ,
			{
				field : 'avg01',
				title : '烟尘(mg/m³)',
				width : '140',
				align : 'right',
				sortable : true,
				order : 'desc',
				formatter : function(val,row,index){
			        if(val!=null){
			        	return val.toFixed(2);
			        }
			        return '-';
			    }
			}] ],
			toolbar : '#toolbar'
		});
		
		$('#qy').combobox({
			url:'${ctx}/statistics/gastree',
			valueField:'text',
			textField:'text'
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
			url : '${ctx}/statistics/exportyeargas',
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
					<td><input id="qy" name="pollutionSourceName" type="text" placeholder="请输入企业名称"/></td>
					<th>时间:</th>
					<td><input name="startTime" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />至<input  name="endTime" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
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