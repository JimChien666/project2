<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<style>
th, td{
width: 200px;
align: left;
}
.counter li{float:left; list-style-type:none; width:30px; height:30px; text-align:center; line-height:30px; border:#999 thin solid; background-color:#fff;}
.counter li input{font-size:20px; width:100%; height:100%; outline:none; -webkit-appearance:none; background:none; margin:0; padding:0; border: 1px solid transparent; border-radius: 0;}
#countnum{ border-left:hidden; border-right:hidden; color:#666}
ul,li{margin:0; padding:0; display:inline;}
</style>
<meta charset="UTF-8">
<script type="text/javascript" src="<c:url value='/js/jquery-1.12.2.min.js' />"></script>

<script>
	$(document).ready( function () {
	    $('#table_id').DataTable();
	} );

</script>
<jsp:include page="../fragments/links.jsp" />
</head>
<body>
<jsp:include page="../fragments/headerArea.jsp" />



<table id="table_id" class="display">
    <thead>
        <tr>
            <th>Column 1</th>
            <th>Column 2</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>Row 1 Data 1</td>
            <td>Row 1 Data 2</td>
        </tr>
        <tr>
            <td>Row 2 Data 1</td>
            <td>Row 2 Data 2</td>
        </tr>
    </tbody>
</table>


<jsp:include page="../fragments/footerArea.jsp" />
<jsp:include page="../fragments/allJs.jsp" />
</body>
</html>