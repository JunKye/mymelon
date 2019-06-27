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
  <div class='title'>음원 제거</div>
  <form method="post"
        action="./delete.do"
        enctype="multipart/form-data" 
        onsubmit="return check()"> 

    <table align='center' border='1px' cellspacing='0px' cellpadding='5px'>
    <tr>
      <th>제목</th>
      <td><input type='text' name='title' size='50' value='${dto.title }'></td>    
    </tr>
    
    </table>    
    <div class="content">
        <p>음원을 삭제하시겠습니까?</p>
        <p>※ 관련 미디어 파일(mp3, mp4)도 전부 삭제됩니다</p>
    </div>

  <div class='bottom'>
    <input type="hidden" name="mediagroupno" value="${dto.mediagroupno }">
    <input type="hidden" name="mediano" value="${dto.mediano }">
    <input type='submit' value='삭제'>
    <input type='button' value='음원목록' onclick="location.href='./list.do'">
    <input type='button' value='HOME'  onclick="location.href='../mediagroup/list.do'">
  </div>
  
  </form>
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
