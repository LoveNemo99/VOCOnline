<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>日数据报表</title>
<script type="text/javascript">
	var columns_data;
	var dataGrid;
	var columns=new Array();
	$.ajax({
		type : "POST",
		url : "${ctx}/columnsReport/titlesgas",
		dataType : "json",
		//data:{"alias":aliasStr,"starttime":st,"endtime":et,"pollutanttype":type_value,"gaptype":'hour'},
		success : function(data) {
			
			var column={};  
            column["title"]='企业名称';  
            column["field"]='pollutionSourceName';  
            column["width"]=100;column["sortable"]=false;
            columns.push(column);
            var column={};  
            column["title"]='排口';  
            column["field"]='port';  
            column["width"]=100;column["sortable"]=false;
            columns.push(column);
            var column={};  
            column["title"]='监测时间';  
            column["field"]='time';column['order']='desc';
            column["width"]=150;column["sortable"]=true;
            column["formatter"]=function(val,row,index){if(val!=null){return val;}};
            columns.push(column);
            for(var i=0;i<data.length;i++){
            	var column={};
            	column["title"]=data[i].columnName+"("+data[i].unitSymbol+")";
            	var x=i+1;
                column["field"]='field'+x;column['order']='desc';
                column["width"]=140;column["sortable"]=true;column["align"]='right';
                column["formatter"]=function(val,row,index){if(val!=null){return val.toFixed(2);}return '-';};
                columns.push(column);
            }
            
			creatDataGrid();
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("数据加载失败，请重试！");
		}
	});
	
	function creatDataGrid(){
		dataGrid = $('#dataGrid').datagrid({
			url : '${ctx}' + '/reportData/dataGridDayGas',
			fit : true,
			striped : true,
			rownumbers : true,
			singleSelect : true,
			remoteSort : true,
			columns : [columns],
			toolbar : '#toolbar'
		});
		$('#qy').combobox({
			url:'${ctx}/info/pstree',
			valueField:'value',
			textField:'text'
			});
	}

	function searchFun() {
		dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
	}
	function cleanFun() {
		$('#searchForm input').val('');
		dataGrid.datagrid('load', {});
	}
	
	function exp(){
		$('#searchForm').form({
			url : '${ctx}/reportData/exportdaygas',
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
					<td><input name="startTime" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
					至<input  name="endTime" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="cleanFun();">清空</a></td>
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