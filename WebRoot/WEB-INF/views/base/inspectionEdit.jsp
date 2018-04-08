<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
	var edit_path = '${ctx}/inspection/edit';
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
			url:'${ctx}/inspection/pstree',
			valueField:'value',
			textField:'text',
			multiple:false,
			panelHeight:'auto'
			});
		$('#valveState').combobox({
			url:'${ctx}/inspection/statetree',
			valueField:'value',
			textField:'text',
			multiple:false,
			panelHeight:'auto'
			});
		$('#networkState').combobox({
			url:'${ctx}/inspection/statetree',
			valueField:'value',
			textField:'text',
			multiple:false,
			panelHeight:'auto'
			});
		$('#systemState').combobox({
			url:'${ctx}/inspection/statetree',
			valueField:'value',
			textField:'text',
			multiple:false,
			panelHeight:'auto'
			});
		$('#otherState').combobox({
			url:'${ctx}/inspection/statetree',
			valueField:'value',
			textField:'text',
			multiple:false,
			panelHeight:'auto'
			});
		
		$("#problemDescribe").val('${pageObj.problemDescribe}');
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
		<form id="EditForm" method="post">
			<table class="grid">
				<tr>
					<td>企业</td>
					<td><input name="id" type="hidden"  value="${pageObj.id}">
					<input name="yearMonth" type="hidden"  value="${pageObj.yearMonth}">
					<input name="createTime" type="hidden"  value="${pageObj.createTime}">
					<input name="modifyTime" type="hidden"  value="${pageObj.modifyTime}">
					<input name="createMan" type="hidden"  value="${pageObj.createMan}">
					<input name="modifyMan" type="hidden"  value="${pageObj.modifyMan}">
					<input id="psCode" name="psCode" type="text" data-options="required:true" value="${pageObj.psCode}"></td>
				</tr>
				<tr>
					<td>阀门状态</td>
					<td><input id="valveState" name="valveState" type="text"  value="${pageObj.valveState}"></td>
				</tr>
				<tr>
					<td>网络状态</td>
					<td><input id="networkState" name="networkState" type="text" value="${pageObj.networkState}"></td>
				</tr>
				<tr>
					<td>系统状态</td>
					<td><input id="systemState" name="systemState" type="text" value="${pageObj.systemState}"></td>
				</tr>
				<tr>
					<td>其他状态</td>
					<td><input id="otherState" name="otherState" type="text" value="${pageObj.otherState}"></td>
				</tr>
				<tr>
					<td>问题描述</td>
					<td colspan="3"><textarea id="problemDescribe" name="problemDescribe" rows="" cols="" ></textarea></td>
				</tr>
			</table>
		</form>
	</div>
</div>