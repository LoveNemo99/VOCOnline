<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="inc.jsp"></jsp:include>
<title>用户登录</title>
<meta name="keywords" content="">
<meta name="description" content="">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link href="${ctx}/style/css/base.css" rel="stylesheet" type="text/css">
<link href="${ctx}/style/css/login.css" rel="stylesheet" type="text/css">

<script>
	//document.getElementById("bg").setAttribute('src',"../../style/images/py.jpg");
	var sessionInfo_userId = '${sessionInfo.id}';
	if (sessionInfo_userId) {//如果登录,直接跳转到index页面
		alert("ok");
		window.location.href='${ctx}/admin/index';
	}
	
	$(function() {
		
		$('#loginform').form({
		    url:'${ctx}/admin/login',
		    onSubmit : function() {
		    	progressLoad();
				var isValid = $(this).form('validate');
				if(!isValid){
					progressClose();
				} 
				return isValid;
			},
		    success:function(result){
		    	result = $.parseJSON(result);
		    	progressClose();
		    	if (result.success) {
		    		window.location.href='${ctx}/admin/index';
		    	}else{
		    		$.messager.show({
		    			title:'提示',
		    			msg:'<div class="light-info"><div class="light-tip icon-tip"></div><div>'+result.msg+'</div></div>',
		    			showType:'show'
		    		});
		    	}
		    }
		});
	});
	
	function submitForm(){
		//$('#loginform').submit();
		$('#loginform').form('submit');
	}
	
	function clearForm(){
		$('#loginform').form('clear');
	}
	
	//回车登录
	function enterlogin(){
		if (event.keyCode == 13){
        	event.returnValue=false;
        	event.cancel = true;
        	$('#loginform').form('submit');
    	}
	}
</script>
</head>
<body onkeydown="enterlogin();">
	<div class="layer" style="position:absolute; width:99%; height:99%; z-index:-1">    
		<img src="${ctx}/style/images/py.jpg" height="100%" width="100%"/>
	</div>
	<div class="layer2"></div>
	<div class="login">
		<form  method="post" id="loginform">
			<div id="logo2" class="center">
				<!-- <div class="zhb"></div> -->
				<img src="${ctx}/style/images/zhb.png" alt="">
				<div class="sub_title">VOC</div>
				<div class="title">在线监控管理平台</div>
			</div>
		    <div id="form">
		    	<div class="center"></div>
		    	<div class="center copy">
					<input class="text_input" type="text" name="loginname" value="" data-options="required:true" placeholder="请输入用户名">
					<input class="text_input" type="password" name="password" value="" placeholder="请输入密码"></input>
					<button class="login_button" onclick="submitForm()">登录</button>
				</div>
		    </div>
		    <div id="tip"></div>
	    </form>
	</div>
	<div class="footer">
                 江苏天泽环保科技有限公司&nbsp;&nbsp;技术支持
    </div>
	
	<!--[if lte IE 7]>
	<div id="ie6-warning"><p>您正在使用 低版本浏览器，在本页面可能会导致部分功能无法使用。建议您升级到 <a href="http://www.microsoft.com/china/windows/internet-explorer/" target="_blank">Internet Explorer 8</a> 或以下浏览器：
	<a href="http://www.mozillaonline.com/" target="_blank">Firefox</a> / <a href="http://www.google.com/chrome/?hl=zh-CN" target="_blank">Chrome</a> / <a href="http://www.apple.com.cn/safari/" target="_blank">Safari</a> / <a href="http://www.operachina.com/" target="_blank">Opera</a></p></div>
	<![endif]-->
	
	<style>
		/*ie6提示*/
		#ie6-warning{width:100%;position:absolute;top:0;left:0;background:#fae692;padding:5px 0;font-size:12px}
		#ie6-warning p{width:960px;margin:0 auto;}
	</style>
	</body>
</html>