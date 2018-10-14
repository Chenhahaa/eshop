<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        var xmlHttpRequest1=new XMLHttpRequest()
         xmlHttpRequest1.onreadystatechange=function () {
             if(xmlHttpRequest1==4){
                 if(xmlHttpRequest1==200){
                     var resp = xmlHttpRequest1.responseText
                     alert(resp)
                 }
             }
         }
        xmlHttpRequest1.open("GET","ajaxtext?name=doris")
        xmlHttpRequest1.send(null)
    </script>


</head>
<body>

</body>
</html>
