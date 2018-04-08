<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
	var edit_path = '${ctx}/alarmsetting/edit';
	$(function() {
		$('#EditForm').form({
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
		$('#pollutant').combobox({
			url:'${ctx}/alarmsetting/factortree',
			valueField:'value',
			textField:'text',
			multiple:false,
			panelHeight:'auto'
			});
		
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
		<form id="EditForm" method="post">
			<table class="grid">
				<tr>
					<td>污染物</td>
					<td><input name="id" type="hidden"  value="${pageObj.id}">
					<input name="factorName" type="hidden"  value="${pageObj.factorName}">
					<input id="pollutant" name="factorCode" type="text" class="span2" data-options="required:true" value="${pageObj.factorCode}"></td>
				</tr>
				<tr>
					<td>浓度预警值</td>
					<td><input name="concentrationEarlyWarningValue" type="text" class="span2" value="${pageObj.concentrationEarlyWarningValue}"></td>
				</tr>
				<tr>
					<td>浓度限制值</td>
					<td><input name="concentrationLimitValue" type="text" value="${pageObj.concentrationLimitValue}"></td>
				</tr>
				<tr>
					<td>排污量预警值</td>
					<td><input name="piEarlyWarningValue" type="text" value="${pageObj.piEarlyWarningValue}"></td>
				</tr>
				<tr>
					<td>排污量限制值</td>
					<td><input name="piLimitValue" type="text" value="${pageObj.piLimitValue}"></td>
				</tr>
			</table>
		</form>
	</div>
</div>