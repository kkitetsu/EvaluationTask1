<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>編集画面</title>
</head>
<body>
	<table>
		<form action="" method="post">
			<label for="title">janCD:<p><%=request.getAttribute("JAN_CD") %></p></label>
			<input type="hidden" id="janCD" name="janCD" value=<%=request.getAttribute("JAN_CD") %>>
			<label for="title">isbnCD:</label>
			<input type="text" id="isbnCD" name="isbnCD" value="">
			<label for="title">bookNM:</label>
			<input type="text" id="bookNM" name="bookNM" value="">
			<label for="title">price:</label>
			<input type="number" id="price" name="price" value="">
			<input type="submit" id="submit" name="action" value="変更確認">
			<input type="submit" id="cancel" name="action" value="キャンセル">
		</form>
	</table>
</body>
</html>