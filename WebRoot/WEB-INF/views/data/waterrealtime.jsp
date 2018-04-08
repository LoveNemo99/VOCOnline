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
			url : '${ctx}' + '/statistics/dataGridRealTimeWater',
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
				title : '污水(升/秒)',
				field : 'avgB01',
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
				title : '总磷(mg/l)',
				field : 'avg101',
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
				title : '氨氮(mg/l)',
				field : 'avg060',
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
				field : 'avg011',
				title : 'COD(mg/l)',
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
				field : 'avg463',
				title : '电导率(ms/m)',
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
				field : 'avg001',
				title : 'PH(-)',
				width : '60',
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
			onLoadSuccess: function(data){
				chart_data = data.rows;
				var online_count = 0;
				var now = new Date();
				var company_count = chart_data.length;
				var pispeed = 0;//当前瞬时排水量
				var piSpeedCount = 0;//---
				var piXCod = 0;//---
				var piCount = 0;//正在排水的企业数
				var maxcod = 0,maxan = 0,maxzl = 0;//最大浓度
				var zlCount = 0;//总磷超标企业数
			    var codCount = 0;//COD超标企业数
			    var anCount = 0;//氨氮超标企业数
				for(var i=0;i<chart_data.length;i++){
					if(chart_data[i].isOnline){
						online_count = online_count + 1;
					}
					var datat = chart_data[i].time;
					datat = datat.replace(new RegExp("-","gm"),"/");
		            datat = datat.replace(new RegExp("T","gm")," ");
		            datat = datat.replace(new RegExp("Z","gm")," ");
		            var d = new Date(datat);
		            var ms = now.getTime() - d.getTime();
		            if(chart_data[i].pollutionSourceName=="凯发出口"){
	                	if (chart_data[i].avg101 != null && chart_data[i].avg101<=8 && chart_data[i].avg101>1){
	                    	zlCount +=1;
	                    }
	                    if (chart_data[i].avg011 != null && chart_data[i].avg011<=500 && chart_data[i].avg011>80){
	                    	codCount +=1;
	                    }
	                    if (chart_data[i].avg060 != null && chart_data[i].avg060<=35 && chart_data[i].avg060>15){
	                    	anCount +=1;
	                    }
	                }
	            	if(chart_data[i].avg101>maxzl){
	                	maxzl = chart_data[i].avg101;
	                }
	                if(chart_data[i].avg011>maxcod){
	                	maxcod = chart_data[i].avg011;
	                }
	                if(chart_data[i].avg060>maxan){
	                	maxan = chart_data[i].avg060;
	                }
		            if (ms/60000 < 30 && chart_data[i].avgB01 != undefined && chart_data[i].avgB01>2){
		            	//alert("ok2"+chart_data[i].avgB01);
		            	
		            	if(chart_data[i].pollutionSourceName!="凯发进口" && chart_data[i].pollutionSourceName!="凯发出口"){
		            		pispeed = pispeed + chart_data[i].avgB01;
		            		piCount += 1;
		            	}
		            	piSpeedCount = piSpeedCount + chart_data[i].avgB01;
		            	if(chart_data[i].avgB01>=0&&chart_data[i].avg011>=0){
		            		piXCod = piXCod + chart_data[i].avgB01 * chart_data[i].avg011;
		            	}
		            }
		            var codAvg = 0;//COD平均浓度
		        	if(piSpeedCount!=0){
		        		codAvg = piXCod/piSpeedCount;
		        	}
				}
				$("#tbody1 tr").remove();
				var tr="<tr><td>"+"在线企业数/总数"+"</td><td>"+online_count+"/"+company_count+"</td></tr>"+
				"<tr><td>"+"正在排水的企业数"+"</td><td>"+piCount+"</td></tr>"+
				"<tr><td>"+"当前瞬时排水量"+"</td><td>"+pispeed.toFixed(2)+"(升/秒)"+"</td></tr>"+
				"<tr><td>"+"</td><td>"+"</td></tr>"+
				"<tr><td>"+"COD超标企业数"+"</td><td>"+codCount+"</td></tr>"+
				"<tr><td>"+"COD最大浓度"+"</td><td>"+maxcod.toFixed(2)+"(mg/l)"+"</td></tr>"+
				"<tr><td>"+"COD平均浓度"+"</td><td>"+codAvg.toFixed(2)+"(mg/l)"+"</td></tr>"+
				"<tr><td>"+"</td><td>"+"</td></tr>"+
				"<tr><td>"+"氨氮超标企业数"+"</td><td>"+anCount+"</td></tr>"+
				"<tr><td>"+"氨氮最大浓度"+"</td><td>"+maxan.toFixed(2)+"(mg/l)"+"</td></tr>"+
				"<tr><td>"+"</td><td>"+"</td></tr>"+
				"<tr><td>"+"总磷超标企业数"+"</td><td>"+zlCount+"</td></tr>"+
				"<tr><td>"+"总磷最大浓度"+"</td><td>"+maxzl.toFixed(2)+"(mg/l)"+"</td></tr>";
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