<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
	var edit_path = '${ctx}/pscontact/edit';
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
			url:'${ctx}/info/pstree',
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
					<td><input id="psCode" name="psCode" type="text" data-options="required:true" value="${contact.psCode}"></td>
				</tr>
				<tr>
					<td>企业联系人</td>
					<td><input name="id" type="hidden"  value="${contact.id}">
					<input name="name" type="text" placeholder="请输入联系人姓名" data-options="required:true" value="${contact.name}"></td>
				</tr>
				<tr>
					<td>办公室电话</td>
					<td><input name="officePhone" type="text" value="${contact.officePhone}"></td>
				</tr>
				<tr>
					<td>手机号码</td>
					<td><input name="mobilePhone" type="text" value="${contact.mobilePhone}"></td>
				</tr>
				<tr>
					<td>邮件</td>
					<td><input name="email" type="text" value="${contact.email}"></td>
				</tr>
				<tr>
					<td>邮政编码</td>
					<td><input name="postalCode" type="text" value="${contact.postalCode}"></td>
				</tr>
				<tr>
					<td>地址</td>
					<td><input name="postalAddress" type="text" value="${contact.postalAddress}"></td>
				</tr>
				<tr>
					<td>传真</td>
					<td><input name="fax" type="text" value="${contact.fax}"></td>
				</tr>
			</table>
		</form>
	</div>
</div>