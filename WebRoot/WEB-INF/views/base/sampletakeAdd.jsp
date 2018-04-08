<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {

		$('#ctrlForm').form({
			url : '${pageContext.request.contextPath}/sample/add',
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
		
		$('#bottle').combobox({
			url:'${pageContext.request.contextPath}/sample/choosetree',
			valueField:'value',
			textField:'text'
			});
		
		$('#psName').combobox({
			url:'${ctx}/sample/pstree',
			valueField:'id',
			textField:'text',
			multiple:false,
			onSelect: function(rec){
				var ur = '${ctx}/sample/pitree?id='+rec.id;
				$('#psPiName').combobox('reload', ur);
			}
			});
		
		$('#psPiName').combobox({
			valueField:'value',
			textField:'text'
			});
		
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;" >
		<form id="ctrlForm" method="post">
			<table class="grid">
				<tr>
					<td>企业名称</td>
					<td><input id="psName" name="pollutantSourceName" type="text" data-options="required:true" value=""></td>
				</tr>
				<tr>
					<td>排口名称</td>
					<td><input id="psPiName" name="MN" type="text" data-options="required:true" value=""></td>
				</tr>
				<tr>
					<td>瓶号</td>
					<td><input id="bottle" name="bottleId" type="text" data-options="required:true" value=""></td>
				</tr>
				<tr>
					<td>密码</td>
					<td><input name="pwd" type="text" value=""></td>
				</tr>
			</table>
		</form>
	</div>
</div>