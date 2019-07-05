<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="todo.utils.HTMLUtils" %>
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
					<a class="navbar-brand" href="index.html">Todoリスト</a>
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
				<div class="navbar-form navbar-right">
					<form method="POST" action="index.html">
						<div class="input-group">
							<input type="text" class="form-control" name="search" value="${search}" placeholder="検索">
							<span class="input-group-btn">
							<button type="submit" class="btn btn-default" name="searchbutton" value="" type="button">検索</button>
							</span>
						</div>
					</form>
				</div>
				<div class="navbar-form navbar-right">
					<form method="POST" action="index.html">
						<div class="btn-group" role="group" aria-label="...">
							<button type="submit" name="did1" value="" class="btn btn-warning" ${HTMLUtils.pushed(didValue,"1")}>
								<span class="glyphicon glyphicon-repeat" aria-hidden="true"></span> 未完了のみ
							</button>
							<button type="submit" name="did2" value="" class="btn btn-warning" ${HTMLUtils.pushed(didValue,"2")}>
								<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> 全て
							</button>
						</div>
					</form>
				</div>
</c:if>
				<!-- Collect the nav links, forms, and other content for toggling -->
				<!-- /.navbar-collapse -->
			</div><!-- /.container-fluid -->
		</nav>
			<div class="container">