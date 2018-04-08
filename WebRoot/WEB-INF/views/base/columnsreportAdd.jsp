<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {

		$('#AddForm').form({
			url : '${pageContext.request.contextPath}/columnsReport/add',
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
<div class="easyui-layout" data-options="fit:true,border:false" >
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;" >
		<form id="AddForm" method="post">
			<table class="grid">
				<tr>
					<td>列名</td>
					<td><input id="columnName" name="columnName" type="text" data-options="required:true" value=""></td>
				</tr>
				<tr>
					<td>排序</td>
					<td><input name="columnSort" type="text" data-options="required:true" value=""></td>
				</tr>
				<tr>
					<td>污染物因子</td>
					<td><input id="pollutantFactorCode" name="pollutantFactorCode" data-options="required:true" type="text" value=""></td>
				</tr>
				<tr>
					<td>单位</td>
					<td><input id="unitCode" name="unitCode" type="text" value=""></td>
				</tr>
				<tr>
					<td>展示字段</td>
					<td><input name="dataType" type="text" value=""></td>
				</tr>
			</table>
		</form>
	</div>
</div>