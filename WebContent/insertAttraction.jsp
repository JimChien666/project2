 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 
response.setContentType("text/html;charset=UTF-8");
response.setHeader("Cache-Control","no-cache"); // HTTP 1.1
response.setHeader("Pragma","no-cache"); // HTTP 1.0
response.setDateHeader ("Expires", -1); // Prevents caching at the proxy server
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>班級報名註冊</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
</head>
<body>
<h2>
報名資料登入
</h2>



<form>
  <div class="form-group">
    <label for="exampleFormControlInput1">Email address</label>
    <input type="email" class="form-control" id="exampleFormControlInput1" placeholder="name@example.com">
  </div>
  <div class="form-group">
    <label for="exampleFormControlSelect1">Example select</label>
    <select class="form-control" id="exampleFormControlSelect1">
      <option>1</option>
      <option>2</option>
      <option>3</option>
      <option>4</option>
      <option>5</option>
    </select>
  </div>
  <div class="form-group">
    <label for="exampleFormControlSelect2">Example multiple select</label>
    <select multiple class="form-control" id="exampleFormControlSelect2">
      <option>1</option>
      <option>2</option>
      <option>3</option>
      <option>4</option>
      <option>5</option>
    </select>
  </div>
  <div class="form-group">
    <label for="exampleFormControlTextarea1">Example textarea</label>
    <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
  </div>
</form>










<form action=".\TestDB" method="post">
	<div class="form-group">
		<label for="exampleFormControlInput1">旅遊點名稱:</label>
		<input class="form-control" type="text" name="name" size="10" maxlength="10">
	</div>
	<div class="form-row">
	    <div class="form-group col-md-3">
	      <label for="inputCity">City</label>
	      <input type="text" class="form-control" id="inputCity">
	    </div>

	    <div class="form-group col-md-9">
	      <label for="inputZip">Zip</label>
	      <input type="text" class="form-control" id="inputZip">
	    </div>
  	</div>
    
<tr>
    <td width="150">聯絡地址:</td>
    <td><input type="text" name="city" size="3" maxlength="3">(縣/市)<input type="text" name="city" size="30" maxlength="3"></td>
</tr>
<tr>
	<td>內容:</td>
	<td><textarea type="text" name="content" size="50" maxlength="50" style="width:300px;height:100px;"></textarea></td>
</tr>
<tr>
    <td>聯絡電話:</td>
    <td><input type="text" name="tel" size="20"></td>
</tr>
<tr>
    <td>E-mail:</td>
    <td><input type="text" name="email" size="20" maxlength="20"></td>
</tr>

<tr>
    <td>旅遊點類型:</td>
    <td><input type="text" name="attractionType" size="20" maxlength="20"></td>
</tr>
</table>
<center>
<input type="submit" name="submit" value="送出">
</center>
</form>
</body>
</html>