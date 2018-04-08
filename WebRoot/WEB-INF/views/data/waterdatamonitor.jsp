<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>实时数据监控</title>
	<script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
	<script type="text/javascript">
	var dataGrid;
	var chart_data;
	var dialogChart;
	
	var lineOption = new Object();
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : '${ctx}' + '/realtimemonitor/dataGrid',
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
				field : 'onlineState',
				align : 'center',
				sortable : false
			} , {
				width : '100',
				title : '企业',
				field : 'psCode',
				sortable : false
			} , {
				width : '100',
				title : '排口',
				field : 'piCode',
				sortable : false
			}, {
				width : '140',
				title : '数据时间',
				field : 'dataTime',
				sortable : true,
				order : 'desc',
				formatter : function(val,row,index){
			        if(val!=null){
			        	return val;
			        }
			    }
			} , {
				width : '100',
				title : '排放状态',
				field : 'piState',
				sortable : false
			} , {
				width : '110',
				title : '甲烷(ng/m³)',
				field : 'data1',
				align : 'right',
				sortable : true,
				order : 'desc',
				formatter : function(val,row,index){
					var v = '';
					if(val!=null){v = val.toFixed(2);}
					else{v = '-';}
					var s = $.formatString('<a href="javascript:void(0)" onclick="dia(\'{1}\',\'{2}\',\'{3}\',\'{4}\',\'{5}\',\'{6}\');" >{0}</a>', v,row.psCode,row.piCode,row.code,"a05002","甲烷","24h");
			        return s;
			    }
			}, {
				width : '110',
				title : '非甲烷总烃(mg/m³)',
				field : 'data2',
				align : 'right',
				sortable : true,
				order : 'desc',
				formatter : function(val,row,index){
					var v = '';
					if(val!=null){v = val.toFixed(2);}
					else{v = '-';}
					var s = $.formatString('<a href="javascript:void(0)" onclick="dia(\'{1}\',\'{2}\',\'{3}\',\'{4}\',\'{5}\',\'{6}\');" >{0}</a>', v,row.psCode,row.piCode,row.code,"a24088","非甲烷总烃","24h");
			        return s;
			    }
			}, {
				width : '110',
				title : '总烃(mg/m³)',
				field : 'data3',
				align : 'right',
				sortable : true,
				order : 'desc',
				formatter : function(val,row,index){
					var v = '';
					if(val!=null){v = val.toFixed(2);}
					else{v = '-';}
					var s = $.formatString('<a href="javascript:void(0)" onclick="dia(\'{1}\',\'{2}\',\'{3}\',\'{4}\',\'{5}\',\'{6}\');" >{0}</a>', v,row.psCode,row.piCode,row.code,"a24087","总烃","24h");
			        return s;
			    }
			} , {
				field : 'data4',
				title : '苯(mg/m³)',
				width : '110',
				align : 'right',
				sortable : true,
				order : 'desc',
				formatter : function(val,row,index){
					var v = '';
					if(val!=null){v = val.toFixed(2);}
					else{v = '-';}
					var s = $.formatString('<a href="javascript:void(0)" onclick="dia(\'{1}\',\'{2}\',\'{3}\',\'{4}\',\'{5}\',\'{6}\');" >{0}</a>', v,row.psCode,row.piCode,row.code,"a25002","苯","24h");
			        return s;
			    }
			} , {
				field : 'data5',
				title : '甲苯(mg/m³)',
				width : '90',
				align : 'right',
				sortable : true,
				order : 'desc',
				formatter : function(val,row,index){
					var v = '';
					if(val!=null){v = val.toFixed(2);}
					else{v = '-';}
					var s = $.formatString('<a href="javascript:void(0)" onclick="dia(\'{1}\',\'{2}\',\'{3}\',\'{4}\',\'{5}\',\'{6}\');" >{0}</a>', v,row.psCode,row.piCode,row.code,"a25003","甲苯","24h");
			        return s;
			    }
			} , {
				field : 'data6',
				title : '乙苯(mg/m³)',
				width : '90',
				align : 'right',
				sortable : true,
				order : 'desc',
				formatter : function(val,row,index){
					var v = '';
					if(val!=null){v = val.toFixed(2);}
					else{v = '-';}
					var s = $.formatString('<a href="javascript:void(0)" onclick="dia(\'{1}\',\'{2}\',\'{3}\',\'{4}\',\'{5}\',\'{6}\');" >{0}</a>', v,row.psCode,row.piCode,row.code,"a25004","乙苯","24h");
			        return s;
			    }
			} , {
				field : 'data7',
				title : '间二甲苯(mg/m³)',
				width : '90',
				align : 'right',
				sortable : true,
				order : 'desc',
				formatter : function(val,row,index){
					var v = '';
					if(val!=null){v = val.toFixed(2);}
					else{v = '-';}
					var s = $.formatString('<a href="javascript:void(0)" onclick="dia(\'{1}\',\'{2}\',\'{3}\',\'{4}\',\'{5}\',\'{6}\');" >{0}</a>', v,row.psCode,row.piCode,row.code,"a25007","间二甲苯","24h");
			        return s;
			    }
			} , {
				field : 'data8',
				title : '对二甲苯(mg/m³)',
				width : '90',
				align : 'right',
				sortable : true,
				order : 'desc',
				formatter : function(val,row,index){
					var v = '';
					if(val!=null){v = val.toFixed(2);}
					else{v = '-';}
					var s = $.formatString('<a href="javascript:void(0)" onclick="dia(\'{1}\',\'{2}\',\'{3}\',\'{4}\',\'{5}\',\'{6}\');" >{0}</a>', v,row.psCode,row.piCode,row.code,"a25008","对二甲苯","24h");
			        return s;
			    }
			} , {
				field : 'data9',
				title : '邻二甲苯(mg/m³)',
				width : '90',
				align : 'right',
				sortable : true,
				order : 'desc',
				formatter : function(val,row,index){
					var v = '';
					if(val!=null){v = val.toFixed(2);}
					else{v = '-';}
					var s = $.formatString('<a href="javascript:void(0)" onclick="dia(\'{1}\',\'{2}\',\'{3}\',\'{4}\',\'{5}\',\'{6}\');" >{0}</a>', v,row.psCode,row.piCode,row.code,"a25006","邻二甲苯","24h");
			        return s;
			    }
			} , {
				field : 'data10',
				title : '烟气流速(m/s)',
				width : '90',
				align : 'right',
				sortable : true,
				order : 'desc',
				formatter : function(val,row,index){
					var v = '';
					if(val!=null){v = val.toFixed(2);}
					else{v = '-';}
					var s = $.formatString('<a href="javascript:void(0)" onclick="dia(\'{1}\',\'{2}\',\'{3}\',\'{4}\',\'{5}\',\'{6}\');" >{0}</a>', v,row.psCode,row.piCode,row.code,"a01011","烟气流速","24h");
			        return s;
			    }
			} , {
				field : 'data12',
				title : '烟气温度(℃)',
				width : '90',
				align : 'right',
				sortable : true,
				order : 'desc',
				formatter : function(val,row,index){
					var v = '';
					if(val!=null){v = val.toFixed(2);}
					else{v = '-';}
					var s = $.formatString('<a href="javascript:void(0)" onclick="dia(\'{1}\',\'{2}\',\'{3}\',\'{4}\',\'{5}\',\'{6}\');" >{0}</a>', v,row.psCode,row.piCode,row.code,"a01012","烟气温度","24h");
			        return s;
			    }
			} , {
				field : 'data13',
				title : '烟气压力(kpa)',
				width : '90',
				align : 'right',
				sortable : true,
				order : 'desc',
				formatter : function(val,row,index){
					var v = '';
					if(val!=null){v = val.toFixed(2);}
					else{v = '-';}
					var s = $.formatString('<a href="javascript:void(0)" onclick="dia(\'{1}\',\'{2}\',\'{3}\',\'{4}\',\'{5}\',\'{6}\');" >{0}</a>', v,row.psCode,row.piCode,row.code,"a01013","烟气压力","24h");
			        return s;
			    }
			} , {
				field : 'data14',
				title : '烟气湿度(%)',
				width : '90',
				align : 'right',
				sortable : true,
				order : 'desc',
				formatter : function(val,row,index){
					var v = '';
					if(val!=null){v = val.toFixed(2);}
					else{v = '-';}
					var s = $.formatString('<a href="javascript:void(0)" onclick="dia(\'{1}\',\'{2}\',\'{3}\',\'{4}\',\'{5}\',\'{6}\');" >{0}</a>', v,row.psCode,row.piCode,row.code,"a01014","烟气湿度","24h");
			        return s;
			    }
			} , {
				field : 'data15',
				title : '烟道截面积(m²)',
				width : '90',
				align : 'right',
				sortable : true,
				order : 'desc',
				formatter : function(val,row,index){
					var v = '';
					if(val!=null){v = val.toFixed(2);}
					else{v = '-';}
					var s = $.formatString('<a href="javascript:void(0)" onclick="dia(\'{1}\',\'{2}\',\'{3}\',\'{4}\',\'{5}\',\'{6}\');" >{0}</a>', v,row.psCode,row.piCode,row.code,"a01016","烟道截面积","24h");
			        return s;
			    }
			} , {
				field : 'data16',
				title : '进样压力(kpa)',
				width : '90',
				align : 'right',
				sortable : true,
				order : 'desc',
				formatter : function(val,row,index){
					var v = '';
					if(val!=null){v = val.toFixed(2);}
					else{v = '-';}
					var s = $.formatString('<a href="javascript:void(0)" onclick="dia(\'{1}\',\'{2}\',\'{3}\',\'{4}\',\'{5}\',\'{6}\');" >{0}</a>', v,row.psCode,row.piCode,row.code,"a01013","进样压力","24h");
			        return s;
			    }
			} ] ],
			onLoadSuccess: function(data){
				
			},
			toolbar : '#toolbar'
		});
		
		$('#qy').combobox({
			url:'${ctx}/alarm/pstree',
			valueField:'value',
			textField:'text'
			});
		dialogChart = $('#dd').dialog({
		    title: '数据',
		    width: 1020,
		    height: 460,
		    closed: true,
		    cache: false,
		    modal: true,
		    buttons: '#myDlg-buttons'
		});
		$('#myDlg-closeBtn').click(function(){
            $('#dd').dialog("close");
        });
	});
	require.config({
	    paths: {
	    	echarts: 'http://echarts.baidu.com/build/dist'
	        //echarts: 'http://localhost:8080/echarts/build/dist'
	    }
	});
	
	function dia(psName,piName,piCode,factorCode,factorName,timeType){
		dialogChart.dialog('open');
		loadLine(psName,piName,piCode,factorCode,factorName,timeType);
	}

	
	function drawLine(){
		require(['echarts','echarts/chart/line','echarts/theme/macarons'],
			function (ec) {
		    	var lineChart = ec.init(document.getElementById('line'),'macarons');
		    	lineChart.clear();
		    	lineChart.showLoading({text: '正在努力的读取数据中...'});
		        if(lineOption != null){
		        	lineChart.setOption(lineOption,true);
		        }
		        lineChart.hideLoading();
		    }
		);
	}
	function loadLine(psName,piName,piCode,factorCode,factorName,timeType) {
	    $.ajax({
			type : "POST",
			url : '${ctx}' + '/realtimemonitor/line',
			data:{"psName":psName,"piName":piName,"piCode":piCode,"factorCode":factorCode,
				"factorName":factorName,"timeType":timeType},
			dataType : "json",
			success : function(data) {
				if(data.success){
					lineOption = JSON.parse(data.obj);
					drawLine();
				}
				else{
					alert('提示',data.msg);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("图表数据加载失败，请重试！");
			}
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
			url : '${ctx}/statistics/exportrealtimewater',
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
					<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a>
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
		<form id="searchForm" method="post"></form>
		<!--a onclick="exp();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">导出</a-->
	</div>
	
	<div id="dd" class="easyui-dialog" title="数据" style="width:1000px;height:460px;"
    	data-options="iconCls:'pic_14',resizable:true,modal:true">
    	<div id="line" style="width:100%;height:100%"></div>
	</div>
	<div id="myDlg-buttons">  
        <!--a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" id="myDlg-saveBtn">确定</a-->  
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" id="myDlg-closeBtn">关闭</a>  
	</div> 
</body>
</html>