<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="todoHeader.jsp" />
		<div class="container">
			<table class="table">
			<tr class="trhnc">
				<th class="col-sm-1">#</th>
				<th class="col-sm-4">題名</th>
				<th class="col-sm-3">重要度</th>
				<th class="col-sm-4">期限</th>
			</tr>
			<c:forEach var="idm" items="${id}" varStatus="i">
			<tr>
				<td class="tcenter">${idm}</td>
				<td class="tcenter"><a href="update.html">${title.get(i.index)}</a></td>
				<td class="tcenter">${value.get(i.index)}</td>
				<td class="tcenter">${limitdate.get(i.index)}</td>
			</tr>
			</c:forEach>
			</table>
			<div><a class="btn btn-primary" href="entry.html" role="button">追加</a></div>
		</div>
<jsp:include page="todoFooter.jsp" />