<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="todoHeader.jsp" />
		<div class="container">
			<table class="table">
			<tr class="trhnc">
				<th class="col-sm-1">#</th>
				<th class="col-sm-4">題名</th>
				<th class="col-sm-3">重要度</th>
				<th class="col-sm-4">期限</th>
			</tr>
			<tr>
				<td class="tcenter">1</td>
				<td class="tcenter"><a href="update.html">テストテスト</a></td>
				<td class="tcenter">★★★</td>
				<td class="tcenter">2015/06/20</td>
			</tr>
			<tr>
				<td class="tcenter">2</td>
				<td class="tcenter"><a href="update.html">テストテスト</a></td>
				<td class="tcenter">★</td>
				<td class="tcenter">2015/06/22</td>
			</tr>
			<tr>
				<td class="tcenter">3</td>
				<td class="tcenter"><a href="update.html">テストテスト</a></td>
				<td class="tcenter">★★★</td>
				<td class="tcenter">2015/06/20</td>
			</tr>
			<tr>
				<td class="tcenter">4</td>
				<td class="tcenter"><a href="update.html">テストテスト</a></td>
				<td class="tcenter">★★</td>
				<td class="tcenter"></td>
			</tr>
			</table>
			<div><a class="btn btn-primary" href="entry.html" role="button">追加</a></div>
		</div>
<jsp:include page="todoFooter.jsp" />