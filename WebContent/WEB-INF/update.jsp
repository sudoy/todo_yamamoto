<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="todo.utils.HTMLUtils" %>
<jsp:include page="todoHeader.jsp" />
<jsp:include page="todoError.jsp" />
		<div class="container">
			<div class="btm-border">
				<h1 class="formsize">登録フォーム</h1>
			</div>
		</div>
		<div class="container">
			<form class="form-horizontal" method="POST" action="update.html">
			<input type="hidden" name="id" value="${pack.id}">
				<div class="form-group">
					<label for="title" class="col-sm-2 textright textdown">題名</label>
					<div class="col-sm-10">
						<input type="text" name="title" class="form-control" id="title" value="${pack.title}" placeholder="題名">
					</div>
				</div>
				<div class="form-group">
					<label for="details" class="col-sm-2 textright textdown">詳細</label>
					<div class="col-sm-10">
						<textarea class="form-control" rows="3" name="details" id="details" placeholder="詳細">${pack.details}</textarea>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2 zeropadding textright bold">重要度</div>
					<div class="col-sm-10">
						<label><input type="radio" name="value" value="3" ${HTMLUtils.selectValue(pack,3,3)}> ★★★</label><br>
						<label><input type="radio" name="value" value="2" ${HTMLUtils.selectValue(pack,2,3)}> ★★</label><br>
						<label><input type="radio" name="value" value="1" ${HTMLUtils.selectValue(pack,1,3)}> ★</label>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2 zeropadding textright bold">ステータス</div>
					<div class="col-sm-10">
						<label><input type="radio" name="did" value="1" ${HTMLUtils.selectDid(pack,1,2)}>未完了</label><br>
						<label><input type="radio" name="did" value="2" ${HTMLUtils.selectDid(pack,2,2)}>完了</label>
					</div>
				</div>
				<div class="form-group">
					<label for="date" class="col-sm-2 textright textdown">期限</label>
					<div class="col-sm-10">
						<input type="text" name="limitdate" class="form-control" id="date" value="${pack.limitdate}" placeholder="期限">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2"></div>
					<div class="col-sm-8">
						<a class="btn btn-default" href="index.html" role="button">キャンセル</a>
						<button type="submit" class="btn btn-primary">更 新</button>
					</div>
					<div class="col-sm-2 textright">
						<a class="btn btn-danger" href="delete.html?id=${pack.id}" role="button">削 除</a>
					</div>
				</div>
			</form>
		</div>
<jsp:include page="todoFooter.jsp" />