<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {

		$('#AddForm').form({
			url : '${pageContext.request.contextPath}/alarmsetting/add',
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
		
		$('#pollutant').combobox({
			url:'${ctx}/alarmsetting/factortree',
			valueField:'value',
			textField:'text',
			multiple:false,
			panelHeight:'auto'
			});
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;" >
		<form id="AddForm" method="post">
			<table class="grid">
				<tr>
					<td>污染物</td>
					<td><input id="pollutant" name="factorCode" type="text" data-options="required:true" value=""></td>
				</tr>
				<tr>
					<td>浓度预警值</td>
					<td><input name="concentrationEarlyWarningValue" type="text" value=""></td>
				</tr>
				<tr>
					<td>浓度限制值</td>
					<td><input name="concentrationLimitValue" type="text" value=""></td>
				</tr>
				<tr>
					<td>排污量预警值</td>
					<td><input name="piEarlyWarningValue" type="text" value=""></td>
				</tr>
				<tr>
					<td>排污量限制值</td>
					<td><input name="piLimitValue" type="text" value=""></td>
				</tr>
			</table>
		</form>
	</div>
</div>