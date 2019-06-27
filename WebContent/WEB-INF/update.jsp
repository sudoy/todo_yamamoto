<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="todoHeader.jsp" />
		<div class="container">
			<form class="form-horizontal" action="index.html">
				<div class="form-group">
					<label for="title" class="col-sm-2 textright textdown">題名</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="title" value="テストテスト" placeholder="題名">
					</div>
				</div>
				<div class="form-group">
					<label for="details" class="col-sm-2  koumoku textright textdown">詳細</label>
					<div class="col-sm-10">
						<textarea class="form-control" rows="3" id="details" placeholder="詳細">SQLの確認テストの採点と報告書を作成する。</textarea>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2 textright bold">重要度</div>
					<div class="col-sm-10">
						<label><input type="radio" name="radio" value="3" checked="checked"> ★★★</label><br>
						<label><input type="radio" name="radio" value="2"> ★★</label><br>
						<label><input type="radio" name="radio" value="1"> ★</label>
					</div>
				</div>
				<div class="form-group">
					<label for="date" class="col-sm-2 textright textdown">期限</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="date" placeholder="期限" value="2015/06/15">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2"></div>
					<div class="col-sm-8">
						<button type="submit" class="btn btn-default">キャンセル</button>
						<button type="submit" class="btn btn-primary">更 新</button>
					</div>
					<div class="col-sm-2 textright">
						<button type="submit" class="btn btn-danger">削 除</button>
					</div>
				</div>
			</form>
		</div>
		<!--/container-fluid-->
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="js/bootstrap.min.js"></script>
	</body>
</html>