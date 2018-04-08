<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {

		$('#AddForm').form({
			url : '${pageContext.request.contextPath}/monthplan/add',
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
		
		/*$('#psName').combobox({
			url:'${ctx}/monthplan/pstree',
			valueField:'id',
			textField:'text',
			multiple:false,
			onSelect: function(rec){
				var ur = '${ctx}/monthplan/pitree?id='+rec.id;
				$('#psPiName').combobox('reload', ur);
			}
			});
		
		$('#psPiName').combobox({
			valueField:'value',
			textField:'text'
			});*/
		
		$('#psCode').combobox({
			url:'${ctx}/monthplan/pstree',
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
					<td>月份</td>
					<td><input name="month" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM'})" readonly="readonly"></td>
				</tr>
				
			</table>
		</form>
	</div>
</div>