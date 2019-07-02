<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>entry</title>
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
			<div class="col-md-6 col-md-offset-3 bgc-white shadow tmargin30">
				<h1 class="size16 bold zeromargin padding15">ログイン</h1>
				<br>
<c:if test="${!empty(err)}">
				<div class="alert alert-dismissible login" role="alert">
					<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<strong> エラーが発生しました！</strong><br>
					<ul>
						<li>${err}</li>
					</ul>
				</div>
</c:if>
				<form class="form-horizontal" method="POST" action="login.html">
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-4 control-label">メールアドレス</label>
						<div class="col-sm-7">
							<input type="email" name="email" class="form-control" id="inputEmail3" placeholder="メールアドレス">
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-4 control-label">パスワード</label>
						<div class="col-sm-7">
							<input type="password" name="pass" class="form-control" id="inputPassword3" placeholder="パスワード">
						</div>
					</div>
					<br>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10 textright">
							<button type="submit" class="btn btn-info"><span class="glyphicon glyphicon-off" aria-hidden="true"></span> ログイン</button>
						</div>
					</div>
				</form>
			</div>
		</div>
<jsp:include page="todoFooter.jsp" />