<%--
  Created by IntelliJ IDEA.
  User: chenhaha
  Date: 2018/9/27
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style type="text/css">
    .subli{
     list-style: none;
        color: black;
        margin: 0 10px;
        float: left;
        padding: 5px 10px;
        border: solid 1px;
    }
        .subli a{
            text-decoration: none;
            color: black;
        }
    </style>
    <script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function (){
            $(".subul li").mouseover(function (){
                $.post("product",{method:"listJsonAll",cid:$(this).attr("id")},function(data){
                    $("#content").empty();
                    $(data).each(function () {
                        var str="<li>"+this.pname+"\t"+this.pdesc+"\t"+this.shop_price+"</li>"
                        $("#content").append(str)
                    })
                })
            })
        })
    </script>

    <title>Title</title>
</head>
<body>
<ul class="subul">
    <li class="subli" id="1"><a href="javascript:void(0)">手机数码</a></li>
    <li class="subli" id="2"><a href="javascript:void(0)">母婴产品</a></li>
    <li class="subli" id="4"><a href="javascript:void(0)">电脑设备</a></li>
</ul>
<div style="clear: both"></div>
<hr style="border: solid 1px black">
<div id="content"></div>

</body>
</html>
