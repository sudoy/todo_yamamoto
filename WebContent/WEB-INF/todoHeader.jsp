<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>todoリスト</title>
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link href="css/mystyle.css" rel="stylesheet">
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>
	<body class="bgc-gray">
		<nav class="navbar navbar-default bgc-white">
			<div class="container">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<a class="navbar-brand" href="#">Todoリスト</a>
				</div>
<c:if test="${!empty(name)}">
				<div class="navbar-form navbar-right">
					<div class="btn-group">
						<button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							${name} <span class="caret"></span>
						</button>
						<ul class="dropdown-menu">
							<li><a href="logout.html">ログアウト</a></li>
						</ul>
					</div>
				</div>
</c:if>
				<!-- Collect the nav links, forms, and other content for toggling -->
				<!-- /.navbar-collapse -->
			</div><!-- /.container-fluid -->
		</nav>