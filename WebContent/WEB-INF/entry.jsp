<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="todo.utils.HTMLUtils" %>
<jsp:include page="todoHeader.jsp" />
		<div class="container">
			<div class="btm-border">
				<h1 class="formsize">登録フォーム</h1>
			</div>
		</div>
		<div class="container">
			<form class="form-horizontal" method="POST" action="entry.html">
				<div class="form-group">
					<label for="title" class="col-sm-2 textright textdown">題名</label>
					<div class="col-sm-10">
						<input type="text" name="title" class="form-control" id="title" value="${Epack.title}" placeholder="題名">
					</div>
				</div>
				<div class="form-group">
					<label for="details" class="col-sm-2 textright textdown">詳細</label>
					<div class="col-sm-10">
						<textarea class="form-control" rows="3" name="details" id="details" placeholder="詳細">${Epack.details}</textarea>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2 textright bold">重要度</div>
					<div class="col-sm-10">
						<label><input type="radio" name="value" value="3" ${HTMLUtils.checkedRadio(Epack,3)}> ★★★</label><br>
						<label><input type="radio" name="value" value="2" ${HTMLUtils.checkedRadio(Epack,2)}> ★★</label><br>
						<label><input type="radio" name="value" value="1" ${HTMLUtils.checkedRadio(Epack,1)}> ★</label>
					</div>
				</div>
				<div class="form-group">
					<label for="date" class="col-sm-2  koumoku textright textdown">期限</label>
					<div class="col-sm-10">
						<input type="text" name="limitdate" class="form-control" id="date" value="${Epack.limitdate}" placeholder="期限">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2"></div>
					<div class="col-sm-8">
						<a class="btn btn-default" href="index.html" role="button">キャンセル</a>
						<button type="submit" class="btn btn-primary">追 加</button>
					</div>
				</div>
			</form>
		</div>
<jsp:include page="todoFooter.jsp" />