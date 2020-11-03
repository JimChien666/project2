<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Upload File</title>
</head>
<body>
<h2>File Upload</h2>
<form action="filuploadAction.contoller" method="post" enctype="multipart/form-data">
   <p>
   Please Select Your Photo to Upload:<br/>
   <input type="file" name="myFiles"/><br/>
   </p>
   <input type="submit" value="Upload"/>
</form>
</body>
</html>