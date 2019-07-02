<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="todoHeader.jsp" />
		<div class="container">
			<div class="col-md-6 col-md-offset-3 bgc-white shadow tmargin30">
				<h1 class="size16 bold zeromargin padding15">ログイン</h1>
				<br>
				<div>
<jsp:include page="todoError.jsp" />
				<form class="form-horizontal" method="POST" action="login.html">
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-4 control-label">メールアドレス</label>
						<div class="col-sm-7">
							<input type="email" name="email" class="form-control" id="inputEmail3" placeholder="メールアドレス" value="${pack.email}">
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