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
<title>信息看板</title>
	
	
</head>
<!-- class="easyui-layout" data-options="fit:true,border:false" -->
<body style="padding:0;margin:0">
	<div style="height: 30px; overflow: hidden;background-color: #fff">
		<table>
			<tr>
				<td>企业数:</td>
				<td><b id="ps_count"></b></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;监测点数:</td>
				<td><b id="port_count"></b></td>
			</tr>
		</table>
	</div>
	<div id="pie" style="width:100%;height:180px"></div>
	
	<div style="height: 30px; overflow: hidden;background-color: #fff">
		<form id="searchForm" method="post">
			<table>
				<tr>
					<th>企业:</th>
					<td><input id="qy" name="psName" type="text" placeholder="请输入企业名称"/></td>
					<th>排口:</th>
					<td><input id="port" name="piCode" type="text" placeholder="请输入排口名称"/></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="cleanFun();">清空</a></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="line" style="width:100%;height:300px"></div>
	
	<div style="height: 30px; overflow: hidden;background-color: #fff">
		<table>
			<tr>
				<td>类型:</td>
				<td>
					<input type="radio" name="radio" checked="checked" onclick="radioClick()" value="factor1" />总烃排放量
					<input type="radio" name="radio" onclick="radioClick()" value="factor2" />甲烷排放量
					<input type="radio" name="radio" onclick="radioClick()" value="factor3" />非甲烷总烃排放量
				</td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;年排放量:</td>
				<td><b id="year_pi"></b></td>
			</tr>
		</table>
	</div>
	<div id="bar" style="width:100%;height:280px"></div>
	
	
	<div id="toolbar" style="display: none;">
		<c:if test="1">
			<!--a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a-->
		</c:if>
	</div>
	<script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
	<script type="text/javascript">
		document.getElementById('ps_count').innerHTML = '123';
		document.getElementById('port_count').innerHTML = '123';
		document.getElementById('year_pi').innerHTML = '3500';
		require.config({
		    paths: {
		        echarts: 'http://localhost:8080/echarts/build/dist'
		    }
		});
		var option = new Object();
		var barOption = new Object();
		var lineOption = new Object();
		function draw(){
			require(['echarts','echarts/chart/pie','echarts/theme/macarons'],
				function (ec) {
			    	var pieChar = ec.init(document.getElementById('pie'),'macarons');
			    	pieChar.clear();
			    	pieChar.showLoading({text: '正在努力的读取数据中...'});
			        if(option != null){
			        	var labelFromatter=
			        	{
			        		normal:
			        		{
			        			label:
			        			{
			        		    	formatter : function (params){return 100 - params.value + '%'},
			        		    	textStyle:{baseline : 'top'}
			        			}
			        		},
			        	};
			        	for(var i=0;i<5;i++){
			        		option.series[i].itemStyle=labelFromatter;
			        	}
			        	pieChar.setOption(option,true);
			        }
			        pieChar.hideLoading();
			    }
			);
		}
		function drawBar(){
			require(['echarts','echarts/chart/bar','echarts/theme/macarons'],
				function (ec) {
			    	var barChart = ec.init(document.getElementById('bar'),'macarons');
			    	barChart.clear();
			    	barChart.showLoading({text: '正在努力的读取数据中...'});
			        if(barOption != null){
			        	barChart.setOption(barOption,true);
			        }
			        barChart.hideLoading();
			    }
			);
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
		function loadPie() {
	        $.ajax({
				type : "POST",
				url : '${ctx}' + '/reportData/piedata',
				dataType : "json",
				success : function(data) {
					if(data.success){
						option = JSON.parse(data.obj);
						draw();
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
		function loadBar(code) {
	        $.ajax({
				type : "POST",
				url : '${ctx}' + '/reportData/bardata2',
				data : {'code':code},
				dataType : "json",
				success : function(data) {
					if(data.success){
						barOption = JSON.parse(data.obj);
						drawBar();
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
	    function loadLine() {
	        $.ajax({
				type : "POST",
				url : '${ctx}' + '/reportData/linedata2',
				dataType : "json",
				data:{'psName':arguments[0],'piCode':arguments[1]},
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
	    
	    $(function() {
		    $('#qy').combobox({
				url:'${ctx}/offline/pstree',
				valueField:'text',
				textField:'text',
				multiple:false,
				panelHeight:'auto',
				onSelect: function(rec){
					var ur = '${ctx}/offline/porttree?psCode='+rec.value;
					$('#port').combobox('reload', ur);
				}
				});
			
			$('#port').combobox({
				valueField:'value',
				textField:'text',
				multiple:false,
				panelHeight:'auto'
				});
	    });
	    function searchFun() {
	    	var psName = searchForm.psName.value;
	    	var piCode = searchForm.piCode.value;
	    	loadLine(psName,piCode);
		}
		loadPie();
		loadBar('a24087');
		loadLine();
	</script>
</body>
</html>