<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
	var edit_path = '${ctx}/alarmcontact/edit';
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
		
		$('#psCode').combobox({
			url:'${ctx}/alarmcontact/pstree',
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
					<td>企业</td>
					<td><input id="psCode" name="psCode" type="text" data-options="required:true" value="${pageObj.psCode}"></td>
				</tr>
				<tr>
					<td>报警联系人姓名</td>
					<td><input name="id" type="hidden"  value="${pageObj.id}">
					<input name="man" type="text" placeholder="请输入报警方式名称" data-options="required:true" value="${pageObj.man}"></td>
				</tr>
				<tr>
					<td>联系方式</td>
					<td><input name="tel" type="text" placeholder="请输入联系方式" class="easyui-validatebox span2" data-options="required:true" value="${pageObj.tel}"></td>
				</tr>
			</table>
		</form>
	</div>
</div>