<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
	var edit_path = '${ctx}/licenceinfo/edit';
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
			url:'${ctx}/licenceinfo/tree',
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
					<td>企业名称</td>
					<td><input name="id" type="hidden"  value="${pageObj.id}">
					<input id="psCode" name="psCode" type="text" data-options="required:true" value="${pageObj.psCode}"></td>
				</tr>
				<tr>
					<td>许可证编号</td>
					<td><input name="licenceNum" type="text" data-options="required:true" value="${pageObj.licenceNum}"></td>
				</tr>
				<tr>
					<td>生效日期</td>
					<td><input name="startDateStr" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" readonly="readonly" value="${pageObj.startDateStr}"/></td>
				</tr>
				<tr>
					<td>截止日期</td>
					<td><input name="endDateStr" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" readonly="readonly" value="${pageObj.endDateStr}"/></td>
				</tr>
				<tr>
					<td>发证机关</td>
					<td><input name="department" type="text" value="${pageObj.department}"></td>
				</tr>
			</table>
		</form>
	</div>
</div>