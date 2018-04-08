<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="inc.jsp"></jsp:include>
		<script type="text/javascript" src="${ctx}/jslib/My97DatePicker/WdatePicker.js" charset="utf-8"></script>
		<!-- [jQuery] -->
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<title>平阳县排污总量自动控制平台</title>
		<script type="text/javascript">
			var layout_west_tree_url = '';
			var sessionInfo_userId = '${sessionInfo.id}';
			if (sessionInfo_userId) {//如果没有登录,直接跳转到登录页面
				layout_west_tree_url = '${ctx}/resource/tree';
			}else{
				window.location.href='${ctx}/admin/index';
			}
			
			function logout(){
				$.messager.confirm('提示','确定要退出?',function(r){
					if (r){
						progressLoad();
						$.post( '${ctx}/admin/logout', function(result) {
							if(result.success){
								progressClose();
								window.location.href='${ctx}/admin/index';
							}
						}, 'json');
					}
				});
			}
			
			function editUserPwd() {
				parent.$.modalDialog({
					title : '修改密码',
					width : 300,
					height : 250,
					href : '${ctx}/user/editPwdPage',
					buttons : [ {
						text : '修改',
						handler : function() {
							var f = parent.$.modalDialog.handler.find('#editUserPwdForm');
							f.submit();
						}
					} ]
				});
			}
			
		</script>
	</head>
<body ng-app="myApp" ng-controller="myCtrl">
	<div id="loading" style="position: fixed;top: -50%;left: -50%;width: 200%;height: 200%;background: #fff;z-index: 100;overflow: hidden;">
		<img src="${ctx}/style/images/ajax-loader.gif" style="position: absolute;top: 0;left: 0;right: 0;bottom: 0;margin: auto;"/>
	</div>
	<nav class="navbar navbar-default" role="navigation">
		<div class="container-fluid"> 
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#example-navbar-collapse">
					<span class="sr-only">切换导航</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">平阳县排污总量自动控制平台</a>
			</div>
			<div class="collapse navbar-collapse" id="example-navbar-collapse">
				
				<ul class="nav navbar-nav">
				  <li ng-repeat="x in names" class="dropdown">
				  	<a class="dropdown-toggle" data-toggle="dropdown">
				  		{{x.text}}<b class="caret"></b>
					</a>
					<ul class="dropdown-menu">
						<li ng-repeat="node in nodes" ng-if="node.pid==x.id" class="list-group-item">
						<a href="{{node.attributes.url}}">{{node.text}}</a></li>
					</ul>
				  </li>
				</ul>
			</div>
		</div>
	</nav>
	<div ng-view></div>
	<script>
	//var app = angular.module('myApp', []);
	var app = angular.module("myApp", ["ngRoute"]);
	var datas;
	app.controller('myCtrl', function($scope,$http) {
		$scope.toggle = function(node){
			alert(node.attributes.url);
		};
		$http({
	        method: 'POST',
	        url: layout_west_tree_url
	    }).then(function successCallback(response) {
	    	var ns = new Array();
	    	datas = response.data;
	    	for(i=0;i<datas.length;i++){
	    		datas[i].attributes.url = "#"+datas[i].attributes.url;
	    		if(!datas[i].pid){
	    			ns.push(datas[i]);
	    		}
	    		else{
	    			
	    		}
	    	}
	        $scope.names = ns;
	        $scope.nodes = datas;
	        }, function errorCallback(response) {
	        	alert('error');
	    });
	});
	angular.module("myApp").config(["$routeProvider", function($routeProvider) {
		$routeProvider.when("/realtimemonitor/manager", {
			templateUrl: "/realtimemonitor/manager"
		}).when("/pollutanttype/manager", {
			templateUrl: "/pollutanttype/manager"
		}).when("/player/view/:playerId/:playerName", {
			templateUrl: "tmpl/player/view.html"
		}).when("/player/add", {
			templateUrl: "tmpl/player/add.html"
		}).when("/player/edit/:playerId", {
			templateUrl: "tmpl/player/edit.html"
		}).otherwise({
			redirectTo: "/admin/index"
		});
	}]);
	</script>
</body>
</html>