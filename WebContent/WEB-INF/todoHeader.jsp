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
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#">Todoリスト</a>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<!-- /.navbar-collapse -->
			</div><!-- /.container-fluid -->
		</nav>
		<div class="container">
<c:if test="${!empty(success)}">
			<div class="alert alert-success alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<strong> 成功しました！</strong><br>
				<ul>
					<li>${success}</li>
				</ul>
			</div>
</c:if>
<c:if test="${!empty(err)}">
			<div class="alert alert-warning alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<strong> エラーが発生しました！</strong><br>
				<ul>
				<c:forEach var="errlist" items="${err}">
					<li>${errlist}</li>
				</c:forEach>
				</ul>
			</div>
</c:if>

		</div>