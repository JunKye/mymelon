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
<DIV class="title">그룹 삭제</DIV>

<DIV class="content">
<p>정말 삭제할거에요?</p>
<p>관련 미디어 파일(mp3,mp4)도 전부 삭제 됩니다.</p>
</DIV>
<FORM name='frm' method='POST' action='./delete.do' onsubmit="return check()">
  <DIV class='bottom'>
  	<input type="hidden" name="mediagroupno" value="${dto.mediagroupno }">
    <input type='submit' value='삭제'>
    <input type='button' value='취소' onclick="javascript:history.back()">
  </DIV>
</FORM>
</body> 
<script type="text/javascript">
	function check(f) {
		var a = confirm("정말 삭제 하실꺼요?");
		if (a==true) {
			return true;
		} else {
			return false;
		}
	}//function end
	
</script> 
</html> 
