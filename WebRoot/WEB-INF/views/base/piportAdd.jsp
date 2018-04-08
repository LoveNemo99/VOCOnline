<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {

		$('#AddForm').form({
			url : '${pageContext.request.contextPath}/piport/add',
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
			url:'${ctx}/piport/pstree',
			valueField:'value',
			textField:'text',
			multiple:false,
			panelHeight:'auto'
			});
		
		$('#pollutantTypeCode').combobox({
			url:'${ctx}/piport/pollutanttree',
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
					<td>污染物类别</td>
					<td><input id="pollutantTypeCode" name="pollutantTypeCode" type="text" data-options="required:true" value=""></td>
				</tr>
				<tr>
					<td>排口编码</td>
					<td><input name="portCode" type="text" placeholder="请输入排口编码" data-options="required:true" value=""></td>
				</tr>
				<tr>
					<td>排口名称</td>
					<td><input name="portName" type="text" placeholder="请输入排口名称" data-options="required:true" value=""></td>
				</tr>
				<tr>
					<td>MN号</td>
					<td><input name="mn" type="text" placeholder="请输入mn号" data-options="required:true" value=""></td>
				</tr>
				<tr>
					<td>ip地址</td>
					<td><input name="ip" type="text" placeholder="请输入ip地址" data-options="required:true" value=""></td>
				</tr>
				<tr>
					<td>设备信息</td>
					<td colspan="3"><textarea name="remark" rows="" cols="" ></textarea></td>
				</tr>
			</table>
		</form>
	</div>
</div>