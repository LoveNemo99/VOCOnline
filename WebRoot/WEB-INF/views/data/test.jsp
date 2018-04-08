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
<!-- <script src="http://echarts.baidu.com/build/dist/echarts.js"></script> -->
<script src="http://localhost:8080/echarts/build/dist/echarts.js"></script>
<script type="text/javascript">
	require.config({
	    paths: {
	        echarts: 'http://localhost:8080/echarts/build/dist'
	    }
	});
	var option = new Object();
	function draw(){
		require(['echarts','echarts/chart/bar','echarts/chart/line','echarts/theme/macarons'],
		//require(['echart','chart/bar','chart/line','theme/macarons'],
			function (ec) {
				
		    	var psLineChar = ec.init(document.getElementById('psLine'),'macarons');
		    	psLineChar.clear();
		        psLineChar.showLoading({text: '正在努力的读取数据中...'});
		        if(option != null){
		        	var tooltip = {trigger: 'axis',axisPointer : {type : 'shadow'},
				            formatter: function (params) {var tar = params[0];
				                return tar.name + '<br/>' + tar.seriesName + ' : ' + tar.value;}}
				    option.tooltip = tooltip;
		        	psLineChar.setOption(option,true);
		        }
                psLineChar.hideLoading();
		    }
		);
	}
    
    function loadDrugs(timeType,factorType) {
        $.ajax({
			type : "POST",
			url : '${ctx}' + '/reportData/chartdata',
			dataType : "json",
			data:{"timeType":timeType,"factorType":factorType},
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
        /*$.getJSON('${ctx}' + '/reportData/test', function (data) {  
            if (data.success) {  
                option = JSON.parse(data.obj);   
            } else {  
                alert('提示', data.msg);  
            }  
        });*/
    }

    function getValue(name){
        var radio = document.getElementsByName(name);
        for (i=0; i<radio.length; i++) {
            if (radio[i].checked) {
                return radio[i].value;
            }
        }
    }
    
    //载入图表
    loadDrugs("day","废气");
    function radioClick(){
    	loadDrugs(getValue("radio"),getValue("radio2"));
    }
</script>
</head>
<!--body style="padding:0"-->
<body class="easyui-layout" data-options="fit:true,border:false">
	<!--div style="padding:10px;clear: both;">  
	    <div id="psLine" style="height:500px;"></div>  
	</div-->
	<div data-options="region:'north',border:false" style="height: 30px; overflow: hidden;background-color: #fff">
			<table><tr>
				<td>类型:</td>
				<td>
					<input type="radio" name="radio" checked="checked" onclick="radioClick()" value="day" />日数据  
					<input type="radio" name="radio" onclick="radioClick()" value="month" />月数据
					<input type="radio" name="radio" onclick="radioClick()" value="year" />年数据
				</td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;污染物:</td>
				<td>
					<input type="radio" name="radio2" checked="checked" onclick="radioClick()" value="废气" />废气  
					<input type="radio" name="radio2" onclick="radioClick()" value="二氧化硫" />二氧化硫
					<input type="radio" name="radio2" onclick="radioClick()" value="氮氧化物" />氮氧化物
				</td>
				</tr>
			</table>
		<!--/form-->
	</div>
	<div data-options="region:'center',border:true">
		<div id="psLine" style="width:100%;height:100%;"></div>
	</div>

</body>  
<!-- script src="jslib/echart/echarts-all.js"></script>
 -->


</html>