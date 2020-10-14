<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table border="1" style="margin-left: 40%; margin-bottom: 50px;margin-top: 50px;">
  <tr>
    <td width='76'>
        <c:if test="${param.page > 1}">
           <div id="pfirst">
           		<c:if test="${param.cityName != null}">
              		<a href="${pageContext.servletContext.contextPath}/nn/controler/ShowAllAttractionsServlet?attrId=${param.attrId}&page=1&showNum=9&name=${param.name}&cityName=${param.cityName} }&cityId={param.cityId}">第一頁</a>
             	 </c:if>
             	 <c:if test="${param.cityName == null}">
              		<a href="${pageContext.servletContext.contextPath}/nn/controler/ShowAllAttractionsServlet?attrId=${param.attrId}&page=1&showNum=9&name=${param.name}">第一頁</a>
             	 </c:if>
           </div>
        </c:if>
     </td>
     <td width='76'>
        <c:if test="${param.page > 1}">
           <div id="pprev">
           <c:if test="${param.cityName != null}">
              <a href="${pageContext.servletContext.contextPath}/nn/controler/ShowAllAttractionsServlet?attrId=${param.attrId}&page=${param.page - 1}&showNum=9&name=${param.name}&cityName=${param.cityName} }&cityId={param.cityId}">上一頁</a>
              </c:if>  
           <c:if test="${param.cityName == null}">
              <a href="${pageContext.servletContext.contextPath}/nn/controler/ShowAllAttractionsServlet?attrId=${param.attrId}&page=${param.page - 1}&showNum=9&name=${param.name}">上一頁</a>
           </c:if>  
           </div>
        </c:if>  
     </td>
     <td width='76'>
            <c:if test="${param.page < maxPage}">
                <div id="pnext">
                <c:if test="${param.cityName != null}">
                   <a href="${pageContext.servletContext.contextPath}/nn/controler/ShowAllAttractionsServlet?attrId=${param.attrId}&page=${param.page + 1}&showNum=9&name=${param.name}&cityName=${param.cityName} }&cityId={param.cityId}">下一頁</a>
               	</c:if>
                <c:if test="${param.cityName == null}">
                   <a href="${pageContext.servletContext.contextPath}/nn/controler/ShowAllAttractionsServlet?attrId=${param.attrId}&page=${param.page + 1}&showNum=9&name=${param.name}">下一頁</a>
               	</c:if>
                </div>
            </c:if>
     </td>  
     <td width='76'>
            <c:if test="${param.page < maxPage}">
                <div id="plast">
                <c:if test="${param.cityName != null}">
                    <a href="${pageContext.servletContext.contextPath}/nn/controler/ShowAllAttractionsServlet?attrId=${param.attrId}&page=${maxPage}&showNum=9&name=${param.name}&cityName=${param.cityName} }&cityId={param.cityId}">最末頁</a>
               </c:if>
               <c:if test="${param.cityName == null}">
                    <a href="${pageContext.servletContext.contextPath}/nn/controler/ShowAllAttractionsServlet?attrId=${param.attrId}&page=${maxPage}&showNum=9&name=${param.name}">最末頁</a>
               </c:if>
                </div>
            </c:if>
     </td>
     <td width='176' align="center">
                      第${param.page}頁 / 共${maxPage}頁
     </td>  
</tr>
</table>
</div>
</body>
</html>