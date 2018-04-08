<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
	var edit_path = '${ctx}/monthplan/cause';
	$(function() {
		$('#grantForm').form({
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
			url : "${ctx}/monthplan/editFactor1",
			dataType : "json",
			data:{"id":parseInt(id)},
			success : function(data) {
				//alert(data[0].licenceNum);
				$("#tbody1 tr").remove();
				var tr="";
				for(var i=0;i<data.length;i++){
					tr=tr+"<tr>";
					var idvalue='';
					if(data[i].id == undefined){}
					else{
						idvalue = data[i].id;
					}
					tr = tr+"<td><input name='list["+i+"].id' type='hidden'  value='"+idvalue+"'>"+"<input name='list["+i+"].mainId' type='hidden'  value='"+data[i].mainId+"'>";
					
					tr = tr+"<input name='list["+i+"].code' type='hidden'  value='"+data[i].code+"'>"+"<input name='list["+i+"].factorName' type='hidden' value='"+data[i].factorName+"'>"+data[i].factorName+"</td>";
					
					var allow = "";if(data[i].allow!=undefined){allow = data[i].allow;}
					tr = tr+"<td><input name='list["+i+"].allow' type='text' value='"+allow+"'></td>";
					
					tr=tr+"</tr>";
				}
				tr=tr+"<tr>"+"<td>备注</td><td><input name='cause' type='text'></td>"+"</tr>";
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
		<form id="grantForm" method="post">
			<table id="dg" class="grid">
				<thead>
					<tr>
						<th>污染物</th><th>污染物月排放限值</th>
					</tr>
				</thead>
				<tbody id='tbody1'>
					<tr></tr>
		        </tbody>
				
			</table>
		</form>
	</div>
</div>