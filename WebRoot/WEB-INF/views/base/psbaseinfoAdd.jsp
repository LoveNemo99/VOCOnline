<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {

		$('#AddForm').form({
			url : '${pageContext.request.contextPath}/info/add',
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
		
		$('#administrativeDivision').combobox({
			url:'${ctx}/info/divisionTree',
			valueField:'value',
			textField:'text',
			multiple:false
			});
		
		$('#registrationType').combobox({
			url:'${ctx}/info/enRegistrationTree',
			valueField:'value',
			textField:'text',
			multiple:false
			});
		
		$('#enterpriseType').combobox({
			url:'${ctx}/info/enCategoryTree',
			valueField:'value',
			textField:'text',
			multiple:false
			});
		
		$('#enterpriseScale').combobox({
			url:'${ctx}/info/sizeTree',
			valueField:'value',
			textField:'text',
			multiple:false
			});
		$('#subordinateRelationship').combobox({
			url:'${ctx}/info/affiliationTree',
			valueField:'value',
			textField:'text',
			multiple:false
			});
		$('#industryCategory').combobox({
			url:'${ctx}/info/inCategoryTree',
			valueField:'value',
			textField:'text',
			multiple:false
			});
		/*$('#basin').combobox({
			url:'${ctx}/info/basinTree',
			valueField:'value',
			textField:'text',
			multiple:false
			});*/
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;" >
		<form id="AddForm" method="post">
			<table class="grid">
				<tr>
					<td>污染源编码</td>
					<td><input name="code" type="text" value=""></td>
					<td>污染源名称</td>
					<td><input name="psName" type="text" data-options="required:true" value=""></td>
				</tr>
				<tr>
					<td>行政区划</td>
					<td><input id="administrativeDivision" name="administrativeDivisionCode" type="text" value=""></td>
					<td>注册类型</td>
					<td><input id="registrationType" name="registrationCode" type="text" value=""></td>
				</tr>
				<tr>
					<td>单位类别</td>
					<td><input id="enterpriseType" name="enterpriseCategoryCode" type="text" value=""></td>
					<td>企业规模</td>
					<td><input id="enterpriseScale" name="enterpriseSizeCode" type="text" value=""></td>
				</tr>
				<tr>
					<td>单位隶属关系</td>
					<td><input id="subordinateRelationship" name="enterpriseAffiliationCode" type="text" value=""></td>
					<td>行业类别</td>
					<td><input id="industryCategory" name="industryCategoryCode" type="text" value=""></td>
				</tr>
				<tr>
					<td>别名</td>
					<td><input id="alias" name="alias" type="text" value=""></td>
					<td>地址</td>
					<td><input name="psAddress" type="text" value=""></td>
				</tr>
				<tr>
					<td>环保联系人</td>
					<td><input name="environmentMan" type="text" value=""></td>
					<td>环保联系人电话</td>
					<td><input name="environmentTel" type="text" value=""></td>
				</tr>
			</table>
		</form>
	</div>
</div>