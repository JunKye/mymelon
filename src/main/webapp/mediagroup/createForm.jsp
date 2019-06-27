<%@ page contentType="text/html; charset=UTF-8" %> 
<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title>mediagroup/createForm.jsp</title> 
<style type="text/css"> 
*{ 
  font-family: gulim; 
  font-size: 24px; 
} 
</style> 
<link href="../css/style.css" rel="stylesheet" type="text/css">
</head> 
<body>
<DIV class="title">미디어 그룹 등록</DIV>
<FORM name='frm' method='POST' action='./create.do'>
  <TABLE class='table'>
    <TR>
      <TH>미디어 그룹 제목</TH>
      <TD><input type='text' name='title' size='50' value='댄스 음악'></TD>
    </TR>
  </TABLE>

  <DIV class='bottom'>
    <input type='submit' value='등록'>
    <input type='button' value='목록' onclick="window.location.href='./list.do'">
  </DIV>
</FORM>
</body> 
</html> 
