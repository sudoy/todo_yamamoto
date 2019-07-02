<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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