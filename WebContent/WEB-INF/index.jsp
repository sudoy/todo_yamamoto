<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="todo.utils.HTMLUtils" %>
<jsp:include page="todoHeader.jsp" />
<jsp:include page="todoError.jsp" />
		<form method="POST" action="index.html">
		<div class="container">
			<div class="col-sm-6">
				<button type="submit" class="btn btn-success" name="update"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 完了</button><br><br>
			</div>
			<div class="col-sm-6 text-right">
				<div><a class="btn btn-primary" href="entry.html" role="button">追加</a></div>
			</div>

		</div>
		<div class="container">
			<table class="table">
			<tr class="trhnc">
				<th class="col-sm-1 zeropadding">
				<button type="submit" name="sort" value="id" class="btn btn-link col-sm-12">#
				<span class="${HTMLUtils.sortIcon(sort,'id')}" aria-hidden="true"></span></button>
				</th>
				<th class="col-sm-4 zeropadding">
				<button type="submit" name="sort" value="title" class="btn btn-link col-sm-12">題名
				<span class="${HTMLUtils.sortIcon(sort,'title')}" aria-hidden="true"></span></button>
				</th>
				<th class="col-sm-3 zeropadding">
				<button type="submit" name="sort" value="value" class="btn btn-link col-sm-12">重要度
				<span class="${HTMLUtils.sortIcon(sort,'value')}" aria-hidden="true"></span></button>
				</th>
				<th class="col-sm-4 zeropadding">
				<button type="submit" name="sort" value="limitdate" class="btn btn-link col-sm-12">期限
				<span class="${HTMLUtils.sortIcon(sort,'limitdate')}" aria-hidden="true"></span></button>
				</th>
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
<c:if test="${page > 1}">
			<div class="col-sm-12 text-center">
				<nav aria-label="...">
					<ul class="pagination">
					<li class="${HTMLUtils.checkStartEnd(1,nowPage)}">
						<a href="${HTMLUtils.pageIndexControl(nowPage - 1,0)}" aria-label="Previous">
						<span aria-hidden="true">&laquo;</span>
						</a>
					</li>
<c:forEach var="index" begin="1" end="${page}">
					<li class="${HTMLUtils.nowPageSet(nowPage,index)}"><a href="index.html?page=${index}">${index}</a></li>
</c:forEach>
					<li class="${HTMLUtils.checkStartEnd(page,nowPage)}">
						<a href="${HTMLUtils.pageIndexControl(nowPage + 1,page + 1)}" aria-label="Next">
						<span aria-hidden="true">&raquo;</span>
						</a>
					</li>
					</ul>
				</nav>
			</div>
</c:if>
		</div>
<jsp:include page="todoFooter.jsp" />