<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
	var edit_path = '${ctx}/columnsReport/edit';
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
		$('#pollutantFactorCode').combobox({
			url:'${ctx}/columnsReport/factortree',
			valueField:'value',
			textField:'text',
			multiple:false,
			panelHeight:'auto'
			});
		
		$('#unitCode').combobox({
			url:'${ctx}/columnsReport/unittree',
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
					<td>列名</td>
					<td><input name="id" type="hidden"  value="${pageObj.id}">
					<input id="columnName" name="columnName" type="text" data-options="required:true" value="${pageObj.columnName}"></td>
				</tr>
				<tr>
					<td>排序</td>
					<td><input name="columnSort" type="text" data-options="required:true" value="${pageObj.columnSort}"></td>
				</tr>
				<tr>
					<td>污染物因子</td>
					<td><input id="pollutantFactorCode" name="pollutantFactorCode" data-options="required:true" type="text" value="${pageObj.pollutantFactorCode}"></td>
				</tr>
				<tr>
					<td>单位</td>
					<td><input id="unitCode" name="unitCode" type="text" value="${pageObj.unitCode}"></td>
				</tr>
				<tr>
					<td>展示字段</td>
					<td><input name="dataType" type="text" value="${pageObj.dataType}"></td>
				</tr>
			</table>
		</form>
	</div>
</div>