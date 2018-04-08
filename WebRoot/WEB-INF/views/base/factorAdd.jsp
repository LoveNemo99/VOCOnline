<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {

		$('#AddForm').form({
			url : '${pageContext.request.contextPath}/pollutantfactor/add',
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
		
		$('#unit').combobox({
			url:'${ctx}/pollutantfactor/unit',
			valueField:'value',
			textField:'text',
			multiple:false,
			panelHeight:'auto'
			});
		
		$('#unitrtd').combobox({
			url:'${ctx}/pollutantfactor/unit',
			valueField:'value',
			textField:'text',
			multiple:false,
			panelHeight:'auto'
			});
		
		$('#categoryCode').combobox({
			url:'${ctx}/pollutantfactor/pollutanttype',
			valueField:'value',
			textField:'text',
			multiple:false,
			panelHeight:'auto'
			});
		
		$('#state').combobox({
			url:'${ctx}/pollutantfactor/usetree',
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
					<td>污染物类型</td>
					<td><input id="categoryCode" name="categoryCode" type="text" data-options="required:true" value=""></td>
				</tr>
				<tr>
					<td>因子代码</td>
					<td><input name="code" type="text" placeholder="请输入污染物因子代码" data-options="required:true" value=""></td>
				</tr>
				<tr>
					<td>因子名称</td>
					<td><input name="name" type="text" placeholder="请输入污染区因子名称" data-options="required:true" value=""></td>
				</tr>
				<tr>
					<td>单位</td>
					<td><input id="unit" name="measurementUnitCode" type="text" value=""></td>
				</tr>
				<tr>
					<td>实时单位</td>
					<td><input id="unitrtd" name="measurementRtdUnitCode" type="text" value=""></td>
				</tr>
				<tr>
					<td>下限值</td>
					<td><input name="lowerLimit" type="text" value=""></td>
				</tr>
				<tr>
					<td>上限值</td>
					<td><input name="upperLimit" type="text" value=""></td>
				</tr>
				<tr>
					<td>状态</td>
					<td><input id="state" name="state" type="text" value=""></td>
				</tr>
			</table>
		</form>
	</div>
</div>