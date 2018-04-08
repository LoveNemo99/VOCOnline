<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>

</head>
<body>
<div id="line" style="width:100%;height:100%"></div>
<!--div class="easyui-layout" data-options="fit:true,border:false" >
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;" >
		<div id="line" style="width:100%;height:100%"></div>
	</div>
</div-->
<script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
<script type="text/javascript">
	require.config({
	    paths: {
	    	echarts: 'http://echarts.baidu.com/build/dist'
	        //echarts: 'http://localhost:8080/echarts/build/dist'
	    }
	});
	var lineOption = new Object();
	var id = '${pageObj}';
	//lineOption = JSON.parse('${pageObj}');
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
	function loadLine() {
	    $.ajax({
			type : "POST",
			url : '${ctx}' + '/totalanalysis/analysis',
			data:{"id":id},
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
	loadLine();
</script>
</body>
</html>