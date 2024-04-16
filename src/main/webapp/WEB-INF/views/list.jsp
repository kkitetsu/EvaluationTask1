<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>一覧画面</title>
<style>
	* { background-color: pink; }
	body { color: black; }
	.table_list {
	    margin-left: auto;
	    margin-right: auto;
	}
	table, th, td {
	  border: 1px solid blue;
	  border-collapse: collapse;
	}
</style>
</head>
<body>
	
	<table class="table_list">
		<% // ----------------------------------------------------------------- %>
		<% ArrayList<ArrayList<String>> list = (ArrayList<ArrayList<String>>)request.getAttribute("rows"); %>
		<% for (int i = 0; i < list.size(); i++) { %>
				<% ArrayList<String> eachRow = list.get(i); %>
				<tr>
				<% for (int j = 0; j < eachRow.size(); j++) { %>
					<td><%=eachRow.get(j) %></td>
				<% } %>
				<form action="" method="post">
					<td><input type="hidden" value=<%=eachRow.get(0) %> name="JAN_CD"/></td>
					<td><input type="submit" value="変更" name="action"/></td>
				</tr>
				</form>
		<% } %>
		<% // ----------------------------------------------------------------- %>
	</table>
	
</body>
</html>