<%@ page contentType="text/html; charset=UTF-8" %> 
<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title>mediagroup/deleteForm.jsp</title> 
<style type="text/css"> 
*{ 
  font-family: gulim; 
  font-size: 24px; 
} 
</style> 
<link href="../css/style.css" rel="stylesheet" type="text/css">
</head> 
<body>
<DIV class="title">그룹명 수정</DIV>
<FORM name='frm' method='POST' action='./update.do' onsubmit="return check(this)">
  <DIV class='bottom'>	
  	<input type="hidden" name="mediagroupno" value="${dto.mediagroupno }">
    <p>그룹명 : ${dto.title }</p>
    <input type="text" name="title" placeholder="수정할 이름값?"><br>
    <input type='submit' value='수정'>
    <input type='button' value='취소' onclick="javascript:history.back()">
  </DIV>
</FORM>
</body>
<script type="text/javascript">
	function check(f) {
		if (f.title.value.length==0) {
			alert("수정할 이름을 입력 해주세요");
			f.title.focus();
			return false;
		}
		
		var a = confirm("정말 수정할래요?");
		if (a==true) {
			return true;
		} else {
			return false;
		}
	}
	
</script> 
</html> 
