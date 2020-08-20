<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
input{
width:50px;
height:50px;
}
.output{
height:50px;
background: #e9e9e9;
font-size:24px;
font-weight:bold;
text-align: right;
padding:0px,5px;
}
</style>
</head>
<body>

	<form action="../servletTest/calc4">
		<table>
			<tr>
				<td class="output" colspan="4">${3+4}
				</td>
			</tr>
			<tr>
				<td>
				<input type="submit" name="oper" value="ce"></td>
				<td><input type="submit" name="oper" value="c"></td>
				<td><input type="submit" name="oper" value="BS"></td>
				<td><input type="submit" name="oper" value="/">
				</td>
			</tr>
			<tr>
				<td>
				<input type="submit" name="value" value="7"></td>
				<td><input type="submit" name="value" value="8"></td>
				<td><input type="submit" name="value" value="9"></td>
				<td><input type="submit" name="oper" value="*">
				</td>
			</tr>
			<tr>
				<td>
				<input type="submit" name="value" value="4"></td>
				<td><input type="submit" name="value" value="5"></td>
				<td><input type="submit" name="value" value="6"></td>
				<td><input type="submit" name="oper" value="-">
				</td>
			</tr>
			<tr>
				<td>
				<input type="submit" name="value" value="1"></td>
				<td><input type="submit" name="value" value="2"></td>
				<td><input type="submit" name="value" value="3"></td>
				<td><input type="submit" name="oper" value="+">
				</td>
			</tr>
			<tr>
				<td></td>
				
				<td><input type="submit" name="value" value="0"></td>
				<td><input type="submit" name="dot" value="."></td>
				<td><input type="submit" name="oper" value="=">
				</td>
			</tr>
		</table>

	</form>

</body>
</html>