<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>media/createForm.jsp</title>
  <style type="text/css">
  *{
    font-family: gulim;
    font-size: 20px;
  }
  </style>
  <link href="../css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
  <div class='title'>음원 업데이트</div>
  <form method="post"
        action="./update.do"
        enctype="multipart/form-data" 
        onsubmit="return check(this)"> 

    <table align='center' border='1px' cellspacing='0px' cellpadding='5px'>
    <tr>
      <th>제목</th>
      <td><input type='text' name='title' size='50' value='${dto.title }'></td>    
    </tr>
    <tr>
      <th>Poster</th>
      <td>
	      기존파일 : <img src="./storage/${dto.poster }"><br>
	      수정파일 : <input type='file' name='posterMF' size=''>
	  </td>    
    </tr>
    <tr>
      <th>File</th>
      <td>
                기존파일 : <input type='text' disabled="disabled" value='${dto.filename }'><br>
                수정파일 : <input type='file' name='filenameMF' size=''>
      </td>    
    </tr>
    
    </table>    
    <div class="content">
        <p>음원을 수정 하시겠습니까?</p>
        <p>※  수정한 내용은 되돌릴 수 없습니다.</p>
    </div>

  <div class='bottom'>
    <input type="hidden" name="mediagroupno" value="${dto.mediagroupno }">
    <input type="hidden" name="mediano" value="${dto.mediano }">
    <input type='submit' value='수정'>
    <input type='button' value='음원목록' onclick="location.href='./list.do'">
    <input type='button' value='HOME'  onclick="location.href='../mediagroup/list.do'">
  </div>
  
  </form>
</body>
<script type="text/javascript">
    function check(f) {
        var a = confirm("정말 수정 하실꺼요?");
        if (a==true) { 
        	var title = f.title.value.trim(); 
	        if (title.length<1) {
	            alert("제목을 반드시 입력해 주셔야 합니다.");
	            return false;
	        }
	        var posterMF = f.posterMF.value.trim(); 
	        if (posterMF.length<1) {
	            alert("포스터를 반드시 선택해 주셔야 합니다.");
	            return false;
	        }
	        var filenameMF = f.filenameMF.value.trim(); 
	        if (filenameMF.length<1) {
	            alert("음원파일을을 선택해 주셔야 합니다.");
	            return false;
	        }        
	        
	        return true;
        } else {
            return false;
        }
        
       
       
    }//function end
    </script> 
    
</html>
