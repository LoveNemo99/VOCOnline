<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
	var edit_path = '${ctx}/licenceinfo/editFactor';
	
	/*$.ajax({
		type : "POST",
		url : "${ctx}/licenceinfo/editFactor",
		dataType : "json",
		data:{"alias":aliasStr,"starttime":st,"endtime":et,"pollutanttype":type_value,"gaptype":'hour'},
		success : function(data) {
			chart_data.length = 0;
			chart_data=data;
			draw(data);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("数据加载失败，请重试！");
		}
	});*/
	$(function() {
		$('#factorEditForm').form({
			url : edit_path,
			onSubmit : function() {
				progressLoad();
				var isValid = $(this).form('validate');
				if (!isValid) {
					progressClose();
				}
				return isValid;
			},
			success : function(result) {
				progressClose();
				result = $.parseJSON(result);
				if (result.success) {
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
					parent.$.modalDialog.handler.dialog('close');
				} else {
					parent.$.messager.alert('错误', result.msg, 'error');
				}
			}
		});
		var id = "${factors}";
		$.ajax({
			type : "POST",
			url : "${ctx}/licenceinfo/editFactor1",
			dataType : "json",
			data:{"id":parseInt(id)},
			success : function(data) {
				//alert(data[0].licenceNum);
				$("#tbody1 tr").remove();
				var tr="";
				for(var i=0;i<data.length;i++){
					tr=tr+"<tr>";
					/*tr = tr+"<td><input name='licenceNum' type='hidden'  value='"+data[i].licenceNum+"'>"+data[i].licenceNum+"</td>";
					tr = tr+"<td><input name='factorName' type='hidden' value='"+data[i].factorName+"'>"+data[i].factorName+"</td>";
					var dayPi = "";if(data[i].dayPi!=undefined){dayPi = data[i].dayPi;}
					tr = tr+"<td><input name='dayPi' type='text' value='"+dayPi+"'></td>";
					var yearPi = "";if(data[i].yearPi!=undefined){yearPi = data[i].yearPi;}
					tr = tr+"<td><input name='yearPi' type='text' value='"+yearPi+"'></td>";
					var upperLimit = "";if(data[i].upperLimit!=undefined){upperLimit = data[i].upperLimit;}
					tr = tr+"<td><input name='upperLimit' type='text' value='"+upperLimit+"'></td>";
					var lowerLimit = "";if(data[i].lowerLimit!=undefined){lowerLimit = data[i].lowerLimit;}
					tr = tr+"<td><input name='lowerLimit' type='text' value='"+lowerLimit+"'></td>";
					*/
					var idvalue='';
					if(data[i].id == undefined){
						
					}
					else{
						idvalue = data[i].id;
					}
					tr = tr+"<td><input name='list["+i+"].id' type='hidden'  value='"+idvalue+"'>"+"<input name='list["+i+"].licenceNum' type='hidden'  value='"+data[i].licenceNum+"'>"+data[i].licenceNum+"</td>";
					
					tr = tr+"<td><input name='list["+i+"].pollutantCode' type='hidden'  value='"+data[i].pollutantCode+"'>"+"<input name='list["+i+"].factorName' type='hidden' value='"+data[i].factorName+"'>"+data[i].factorName+"</td>";
					
					var dayPi = "";if(data[i].dayPi!=undefined){dayPi = data[i].dayPi;}
					tr = tr+"<td><input name='list["+i+"].dayPi' type='text' value='"+dayPi+"'></td>";
					var yearPi = "";if(data[i].yearPi!=undefined){yearPi = data[i].yearPi;}
					tr = tr+"<td><input name='list["+i+"].yearPi' type='text' value='"+yearPi+"'></td>";
					var upperLimit = "";if(data[i].upperLimit!=undefined){upperLimit = data[i].upperLimit;}
					tr = tr+"<td><input name='list["+i+"].upperLimit' type='text' value='"+upperLimit+"'></td>";
					var lowerLimit = "";if(data[i].lowerLimit!=undefined){lowerLimit = data[i].lowerLimit;}
					tr = tr+"<td><input name='list["+i+"].lowerLimit' type='text' value='"+lowerLimit+"'></td>";
					
					tr=tr+"</tr>";
				}
				$("#tbody1").append(tr);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("数据加载失败，请重试！");
			}
		});
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
		<form id="factorEditForm" method="post">
			<table id="dg" class="grid">
				<thead>
					<tr>
						<th>许可证编号</th><th>污染物</th><th>污染物日排放量</th>
						<th>污染物年排放量</th><th>污染物浓度上限</th><th>污染物浓度下限</th>
					</tr>
				</thead>
				<tbody id='tbody1'>
					<tr></tr>
		        </tbody>
				
			</table>
		</form>
	</div>
</div>