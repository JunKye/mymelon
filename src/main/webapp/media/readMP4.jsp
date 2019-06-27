<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mediagroup/read.jsp</title>
<style type="text/css">
* {
	font-family: gulim;
	font-size: 24px;
}
</style>
<link href="../css/style.css" rel="stylesheet" type="text/css">
</head>
<body>

	<div class="title">${dto.title }</div>
    <div class="content">
     	<video src="./temp/${dto.filename }" 
		       poster="storage/${dto.poster }"
		       width="500px"
		       controls autoplay></video>
		<hr>
	</div>

	<div class="title">재생목록</div>
    <table>
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>등록일</th>
            <th>파일명</th>
        </tr>
        <c:forEach var="dto" items="${list }">
            <tr onclick="window.location='./read.do?mediano=${dto.mediano }'" class="ehover">
                <td>${dto.mediano }</td>
                <td><a href='./read.do?mediano=${dto.mediano }'>${dto.title }</a> </td>
                <td>${dto.rdate }</td>
                <td>${dto.filename }</td>
            </tr>
        </c:forEach>
    </table>
       <div class="bottom">
        <input type="button" value="음원등록"
            onclick="location.href='./create.do?mediagroupno=${dto.mediagroupno }'">
       <%-- 
        <input type='button' value='음원목록'
               onclick="location.href='./list.do?mediagroupno=${mediagroupno }'">
        --%>
        <input type="button" value="HOME" onclick="location.href='../home.do'">
    </div>
    

</body>
</html>
