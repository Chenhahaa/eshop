<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        if($("#username")).var{


        var xmlHttpRequest1=new XMLHttpRequest()
        xmlHttpRequest1.onreadystatechange=function () {
            if(xmlHttpRequest1.readyState==4){
                if(xmlHttpRequest1.status==200){
                    var resp = xmlHttpRequest1.responseText
                    document.getElementsById("usermsg").innerHTML=xmlHttpRequest1.responseText
                }
            }
        }
        xmlHttpRequest1.open("Post","user")
        xmlHttpRequest1.setRequestHeader("CONTENT-TYPE","applicatino/x-www-form-urlencoded");
        xmlHttpRequest1.send("mathod=ajax&username"+$("#username").val())
        }
    </script>


</head>
<body>

</body>
</html>
