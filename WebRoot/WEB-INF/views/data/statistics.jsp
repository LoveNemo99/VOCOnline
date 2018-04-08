<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="edge" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页统计</title>
    <script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
	<script type="text/javascript">
	var dataGrid;
	var chart_data;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : '${ctx}' + '/statistics/dataGridWater',
			fit : true,
			striped : true,
			rownumbers : true,
			queryParams:{type:'B01'},
			singleSelect : true,
			columns : [ [ 
			    {
				width : '180',
				title : '企业',
				field : 'pollutionSourceName',
				sortable : true
			} , {
				width : '180',
				title : '污水排放量',
				field : 'couB01',
				sortable : true
			}] ],
			onLoadSuccess: function(data){
				chart_data = data.rows;
				chartData('B01','废水');
			}
			//toolbar : '#toolbar'
		});
	});
	
	function chartData(type_code,type_name){
		draw(chart_data,type_name);
		/*$.ajax({
			type : "POST",
			url : '${ctx}' + '/statistics/chartWater',
			dataType : "json",
			data:{"type":type_code},
			success : function(data) {
				chart_data=data;
				draw(chart_data,type_name);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("图表数据加载失败，请重试！");
			}
		});*/
	}
	
	function radioClick1(){
		dataGrid = $('#dataGrid').datagrid({
			url : '${ctx}' + '/statistics/dataGridWater',
			fit : true,striped : true,rownumbers : true,queryParams:{type:'B01'},
			singleSelect : true,pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50, 100, 200],
			columns : [ [ {width : '180',title : '企业',field : 'pollutionSourceName',sortable : true}, 
			{width : '180',title : '废水排放量',field : 'couB01',sortable : true}]],
			onLoadSuccess: function(data){chart_data = data.rows;chartData('B01','废水');}
		});
		
	}
	function radioClick2(){
		//dataGrid.datagrid("getColumnOption", "couB01").title = "COD排放量";
		//option.title = "COD排放量";
		//$('#dataGrid').datagrid();
		//dataGrid.datagrid('load', {type:'011'});
		dataGrid = $('#dataGrid').datagrid({
			url : '${ctx}' + '/statistics/dataGridWater',
			fit : true,striped : true,rownumbers : true,queryParams:{type:'011'},
			singleSelect : true,pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50, 100, 200],
			columns : [ [ {width : '180',title : '企业',field : 'pollutionSourceName',sortable : true}, 
			{width : '180',title : 'COD排放量',field : 'couB01',sortable : true}]],
			onLoadSuccess: function(data){chart_data = data.rows;chartData('011','COD');}
		});
		
	}
	function radioClick3(){
		dataGrid = $('#dataGrid').datagrid({
			url : '${ctx}' + '/statistics/dataGridWater',
			fit : true,striped : true,rownumbers : true,queryParams:{type:'060'},
			singleSelect : true,pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50, 100, 200],
			columns : [ [ {width : '180',title : '企业',field : 'pollutionSourceName',sortable : true}, 
			{width : '180',title : '氨氮排放量',field : 'couB01',sortable : true}]],
			onLoadSuccess: function(data){chart_data = data.rows;chartData('060','氨氮');}
		});
		
	}
	function radioClick4(){	
		dataGrid = $('#dataGrid').datagrid({
			url : '${ctx}' + '/statistics/dataGridWater',
			fit : true,striped : true,rownumbers : true,queryParams:{type:'101'},
			singleSelect : true,pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50, 100, 200],
			columns : [ [ {width : '180',title : '企业',field : 'pollutionSourceName',sortable : true}, 
			{width : '180',title : '总磷排放量',field : 'couB01',sortable : true}]],
			onLoadSuccess: function(data){chart_data = data.rows;chartData('101','总磷');}
		});
		
	}
	
	function draw(chart_data,type_name){
		// 路径配置
        require.config({
            paths: {
                echarts: 'http://echarts.baidu.com/build/dist'
            }
        });
        require(
            [
                'echarts',
                'echarts/chart/bar',
                'echarts/theme/macarons'
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('main'),'macarons'); 
                var option = new Object();
                option = {
               	    title:   {text: '2016年排污大户',subtext:'废水'},
               	    tooltip: {trigger: 'axis',axisPointer: {type: 'shadow'}},
               	    //legend:  {data: ['废水']},
               	    grid:    {left: '3%',right: '1%',bottom: '3%',containLabel: true},
               	    xAxis:   {type: 'value',boundaryGap: [0, 0.01]},
               	    yAxis:   {type: 'category',data: []},
               	    series:  [{name: '废水',type: 'bar',data: []}]
               	};
                var yAxis = new Object();
                var yData = new Array();
                
                var series = new Array();
                var series_item = new Object();
                var series_data = new Array();
                
                var title = new Object();
                
                for(var i=0;i<5;i++){
                	yData[i] = chart_data[5-i-1].pollutionSourceName;
                	series_data[i] = chart_data[5-i-1].couB01;
                }
                title.text = '2016年排污大户';
                title.subtext = type_name;
                option.title = title;//------
                
                yAxis.type = 'category';
                yAxis.data = yData;
                option.yAxis = yAxis;//------
                
                series_item.name=type_name;
                series_item.type='bar';
                series_item.data=series_data;
                series[0]=series_item;
                option.series=series;//------
                
                myChart.setOption(option); 
            }
        );
	}
	</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false" style="height: 30px; overflow: hidden;background-color: #fff">
		<!--form id="searchForm" method="post"-->
			<table>
				<tr>
				<td>污染物因子:</td>
				<td>
					<input type="radio" name="radio" checked="checked" onclick="radioClick1()" id="radio1" value="B01" />废水  
					<input type="radio" name="radio" onclick="radioClick2()" id="radio2" value="011" />COD
					<input type="radio" name="radio" onclick="radioClick3()" id="radio3" value="060" />氨氮
					<input type="radio" name="radio" onclick="radioClick4()" id="radio4" value="101" />总磷
				</td>
					
				</tr>
			</table>
		<!--/form-->
	</div>
	<div data-options="region:'center',border:true">
		<div id="main" style="width:100%;height:100%;"></div>
	</div>
	<div data-options="region:'east',border:true" style="width:400px;">
		<table id="dataGrid" data-options="fit:true,border:false"></table>
	</div>
	<div id="toolbar" style="display: none;">
		<c:if test="1">
			<!--a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a-->
		</c:if>
	</div>
</body>
</html>