<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
	var edit_path = '${ctx}/division/edit';
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
		$('#parentCode').combobox({
			url:'${ctx}/division/tree',
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
					<td>行政区划代码</td>
					<td><input name="id" type="hidden"  value="${pageObj.id}">
					<input name="code" type="text" placeholder="请输入行政区代码" class="easyui-validatebox span2" data-options="required:true" value="${pageObj.code}"></td>
				</tr>
				<tr>
					<td>行政区划名称</td>
					<td><input name="name" type="text" placeholder="请输入行政区名称" class="easyui-validatebox span2" data-options="required:true" value="${pageObj.name}"></td>
				</tr>
				<tr>
					<td>上级行政区</td>
					<td><input id="parentCode" name="parentCode" type="text" value="${pageObj.parentCode}"></td>
				</tr>
			</table>
		</form>
	</div>
</div>