<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>实时数据废水</title>
	<script type="text/javascript">
	var dataGrid;
	var chart_data;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : '${ctx}' + '/statistics/dataGridRealTimeGas',
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
				width : '50',
				title : '状态',
				field : 'isOnline',
				align : 'center',
				sortable : false,
				formatter : function(val,row,index){
			        if(val){
			        	return "在线";
			        }
			        return '离线';
			    }
			},{
				width : '100',
				title : '企业',
				field : 'pollutionSourceName',
				sortable : false
			}, {
				width : '140',
				title : '数据时间',
				field : 'time',
				sortable : true,
				order : 'desc',
				formatter : function(val,row,index){
			        if(val!=null){
			        	return val;
			        }
			    }
			} , {
				width : '110',
				title : '废气(m³/s)',
				field : 'avgB02',
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
				width : '110',
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
				width : '110',
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
				width : '110',
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
				width : '110',
				align : 'right',
				sortable : true,
				order : 'desc',
				formatter : function(val,row,index){
			        if(val!=null){
			        	return val.toFixed(2);
			        }
			        return '-';
			    }
			} ] ],
			onLoadSuccess: function(data){
				chart_data = data.rows;
				var online_count = 0;
				var company_count = chart_data.length;
				
				var piCount = 0;//正在排放的企业数
				var maxso2 = 0,maxnox = 0,maxyc = 0;//最大浓度
				var so2Count = 0;//二氧化硫超标企业数
			    var noxCount = 0;//氮氧化物超标企业数
			    var ycCount = 0;//烟尘超标企业数
				for(var i=0;i<chart_data.length;i++){
					if(chart_data[i].isOnline){
						online_count = online_count + 1;
					}
	                if(chart_data[i].avg02>maxso2){
	                	maxso2 = chart_data[i].avg02;
	                }
	                if(chart_data[i].avg03>maxnox){
	                	maxnox = chart_data[i].avg03;
	                }
	                if(chart_data[i].avg01>maxyc){
	                	maxyc = chart_data[i].avg01;
	                }
	                if (chart_data[i].avg02 != null && chart_data[i].avg02>260){
	                	so2Count +=1;
	                }
	                if (chart_data[i].avg03 != null && chart_data[i].avg03>400){
	                	noxCount +=1;
	                }
	                if (chart_data[i].avg01 != null && chart_data[i].avg01>80){
	                	ycCount +=1;
	                }
				}
				$("#tbody1 tr").remove();
				var tr="<tr><td>"+"在线企业数/总数"+"</td><td>"+online_count+"/"+company_count+"</td></tr>"+
				
				"<tr><td>"+"</td><td>"+"</td></tr>"+
				"<tr><td>"+"二氧化硫超标企业数"+"</td><td>"+so2Count+"</td></tr>"+
				"<tr><td>"+"二氧化硫最大浓度"+"</td><td>"+maxso2.toFixed(2)+"(mg/l)"+"</td></tr>"+
				"<tr><td>"+"</td><td>"+"</td></tr>"+
				"<tr><td>"+"氮氧化物超标企业数"+"</td><td>"+noxCount+"</td></tr>"+
				"<tr><td>"+"氮氧化物最大浓度"+"</td><td>"+maxnox.toFixed(2)+"(mg/l)"+"</td></tr>"+
				"<tr><td>"+"</td><td>"+"</td></tr>"+
				"<tr><td>"+"烟尘超标企业数"+"</td><td>"+ycCount+"</td></tr>"+
				"<tr><td>"+"烟尘最大浓度"+"</td><td>"+maxyc.toFixed(2)+"(mg/l)"+"</td></tr>";
				$("#tbody1").append(tr);
				$('#dg').datagrid();
			},
			toolbar : '#toolbar'
		});
		
		$('#qy').combobox({
			url:'${ctx}/alarm/pstree',
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
			url : '${ctx}/statistics/exportrealtimegas',
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
	<!--div data-options="region:'north',border:false" style="height: 30px; overflow: hidden;background-color: #fff">
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
	</div-->
	<div data-options="region:'center',border:true">
		<table id="dataGrid" data-options="fit:true,border:false"></table>
	</div>
	<div data-options="region:'east',border:true" style="width:240px;">
		<table id="dg" style="width:700px;height:auto;border:1px solid #ccc;">
	        <thead>
	            <tr>
	                <th data-options="field:'itemid',width:120,align:'left'">统计数据分类</th>
	                <th data-options="field:'productid',width:120,align:'right'">数据</th>
	            </tr>
	        </thead>
	        <tbody id='tbody1'>
	            
	        </tbody>
	    </table>
	</div>
	<div id="toolbar" style="display: none;">
		<c:if test="${fn:contains(sessionInfo.resourceList, '/role/add')}">
			<!--a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a-->
		</c:if>
		<form id="searchForm" method="post"></form>
		<a onclick="exp();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">导出</a>
	</div>
</body>
</html>