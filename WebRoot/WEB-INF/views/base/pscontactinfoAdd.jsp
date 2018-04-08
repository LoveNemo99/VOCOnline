<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {

		$('#AddForm').form({
			url : '${pageContext.request.contextPath}/pscontact/add',
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
<div class="easyui-layout" data-options="fit:true,border:false" >
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;" >
		<form id="AddForm" method="post">
			<table class="grid">
				<tr>
					<td>企业</td>
					<td><input id="psCode" name="psCode" type="text" data-options="required:true" value=""></td>
				</tr>
				<tr>
					<td>联系人姓名</td>
					<td><input name="name" type="text" value="" data-options="required:true"></td>
				</tr>
				<tr>
					<td>办公室电话</td>
					<td><input name="officePhone" type="text" value=""></td>
				</tr>
				<tr>
					<td>手机号码</td>
					<td><input name="mobilePhone" type="text" value=""></td>
				</tr>
				<tr>
					<td>邮件</td>
					<td><input name="email" type="text" value=""></td>
				</tr>
				<tr>
					<td>邮政编码</td>
					<td><input name="postalCode" type="text" value=""></td>
				</tr>
				<tr>
					<td>地址</td>
					<td><input name="postalAddress" type="text" value=""></td>
				</tr>
				<tr>
					<td>传真</td>
					<td><input name="fax" type="text" value=""></td>
				</tr>
			</table>
		</form>
	</div>
</div>