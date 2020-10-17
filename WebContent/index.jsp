<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script>
        <script src="javascript/test.js"></script>
    </head>
    <body>
        <form action="">
            <table border="0">
                <tr><td>name:</td><td><input type="text" name="name"/></td></tr>
                <tr><td>password:</td><td><input type="password" name="pwd"/></td></tr>
                <tr><td></td><td><input type="button" value="GETsubmit"/></td></tr>
            </table>
        </form>
        <div id="show"></div>
    </body>
</html>