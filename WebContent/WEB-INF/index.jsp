<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="todo.utils.HTMLUtils" %>
<jsp:include page="todoHeader.jsp" />
<jsp:include page="todoError.jsp" />
		<form method="POST" action="index.html">
		<div class="container">
			<button type="submit" class="btn btn-success" name="update"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 完了</button><br><br>
		</div>
		<div class="container">
			<table class="table">
			<tr class="trhnc">
				<th class="col-sm-1">#</th>
				<th class="col-sm-4">題名</th>
				<th class="col-sm-3">重要度</th>
				<th class="col-sm-4">期限</th>
			</tr>
<c:forEach var="p" items="${pack}" varStatus="i">
			<tr>
				<td class="tcenter">
				<input type="hidden" name="id" value="${p.id}">
				<input type="hidden" name="did" value="${p.did}">
				<label><input type="checkbox" name="check" value="${p.id}" ${HTMLUtils.checked(p)}>${p.id}</label>
				</td>
				<td class="tcenter"><a href="update.html?id=${p.id}">${p.title}</a></td>
				<td class="tcenter">${p.value}</td>
				<td class="tcenter">${p.limitdate}</td>
			</tr>
</c:forEach>
			</table>
			</form>
			<div><a class="btn btn-primary" href="entry.html" role="button">追加</a></div>
		</div>
<jsp:include page="todoFooter.jsp" />