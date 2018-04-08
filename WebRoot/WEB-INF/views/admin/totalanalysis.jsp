<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="edge" />
<c:if test="${fn:contains(sessionInfo.resourceList, '/organization/edit')}">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/organization/delete')}">
	<script type="text/javascript">
	</script>
</c:if>
<title>总量计算与分析</title>
	<script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
	<script type="text/javascript">
	var dataGrid_path = '${ctx}/totalanalysis/dataGrid';
	var editPage_path = '${ctx}/totalanalysis/analysisPage?id=';
	var dataGrid;
	var dialogChart;
	var psId;var psCode;var psName;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : dataGrid_path,
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'id',
			sortName : 'id',
			sortOrder : 'asc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
			columns : [ [ {
				width : '100',
				title : '企业',
				field : 'psCode'
			} , {
				width : '180',
				title : '总烃',
				field : 'zt',
				sortable : true,
				formatter:function(value){
					return "<span title='" + value + "'>" + value + "</span>";
				}
			} , {
				width : '180',
				title : '甲烷',
				field : 'jw',
				sortable : true
			} , {
				width : '180',
				title : '非甲烷总烃',
				field : 'fjwzt',
				sortable : true
			} , {
				field : 'action',
				title : '操作',
				width : 120,
				formatter : function(value, row, index) {
					var str = '&nbsp;';
					str += $.formatString('<a href="javascript:void(0)" onclick="dia(\'{0}\',\'{1}\',\'{2}\',\'{3}\',\'{4}\');" >趋势分析</a>', row.id,row.code,row.psCode,'a24087','总烃');
					
					return str;
				}
			} ] ],
			toolbar : '#toolbar'
		});
		
		dialogChart = $('#dd').dialog({
		    title: '趋势分析',
		    closed: true,
		    cache: false,
		    modal: true,
		    buttons: '#myDlg-buttons'
		});
		$('#myDlg-closeBtn').click(function(){  
            $('#dd').dialog("close");  
        }); 
		
	});
	//href: 'get_content.php',
	function dia(id){
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {
			dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		initValue('radio');
		psId = id;
		psCode = arguments[1];
		psName = arguments[2];
		dialogChart.dialog('open');
		loadLine(id,arguments[1],arguments[2],arguments[3],arguments[4]);
	}
	require.config({
	    paths: {
	    	echarts: 'http://echarts.baidu.com/build/dist'
	        //echarts: 'http://localhost:8080/echarts/build/dist'
	    }
	});
	var lineOption = new Object();
	//var id = '${pageObj}';
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
	function loadLine(id) {
		var path;
		var oData;
		if(arguments.length==1){
			path = '${ctx}' + '/totalanalysis/analysis';
			oData = {"id":id};
		}else{
			path = '${ctx}' + '/totalanalysis/analysis2';
			oData = {"id":id,"psCode":arguments[1],"psName":arguments[2],"factorCode":arguments[3],"factorName":arguments[4]};
		}
		
	    $.ajax({
			type : "POST",
			url : path,
			data: oData,
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
	function initValue(name){
		var radio = document.getElementsByName(name);
		//document.getElementsByName(name)[0].checked=true;
		radio[0].checked=true;
	}
	function getValue(name){
        var radio = document.getElementsByName(name);
        for (i=0; i<radio.length; i++) {
            if (radio[i].checked) {
                return radio[i].value;
            }
        }
    }
	function radioClick(){
		
		if(getValue('radio')=='a24087'){
			loadLine(psId,psCode,psName,'a24087','总烃');
		}
		else if(getValue('radio')=='a05002'){
			loadLine(psId,psCode,psName,'a05002','甲烷');
		}
		else if(getValue('radio')=='a24088'){
			loadLine(psId,psCode,psName,'a24088','非甲烷总烃');
		}
	}
	</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'center',fit:true,border:false">
			<table id="dataGrid" data-options="fit:true,border:false"></table>
		</div>
	</div>
	
	<div id="dd" class="easyui-dialog" title="趋势分析" style="width:1000px;height:430px"
    	data-options="iconCls:'pic_14',resizable:true,modal:true">
    	<div style="height:30px; overflow: hidden;background-color: #fff">
			<table>
				<tr>
					<td>因子:</td>
					<td>
						<input type="radio" name="radio" checked="checked" onclick="radioClick()" value="a24087" />总烃
						<input type="radio" name="radio" onclick="radioClick()" value="a05002" />甲烷
						<input type="radio" name="radio" onclick="radioClick()" value="a24088" />非甲烷总烃
					</td>
				</tr>
			</table>
		</div>
    	<div id="line" style="width:100%;height:100%"></div>
	</div>
	<div id="myDlg-buttons">  
        <!--a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" id="myDlg-saveBtn">确定</a-->  
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" id="myDlg-closeBtn">关闭</a>  
	</div> 
</body>
</html>