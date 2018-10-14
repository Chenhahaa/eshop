<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head></head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>会员注册</title>
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script src="js/jquery.validate.js" type="text/javascript"></script>
<!-- 引入自定义css文件 style.css -->
<link rel="stylesheet" href="css/style.css" type="text/css" />

<style>
body {
	margin-top: 20px;
	margin: 0 auto;
}

.carousel-inner .item img {
	width: 100%;
	height: 300px;
}

font {
	color: #3164af;
	font-size: 18px;
	font-weight: normal;
	padding: 0 10px;
}
</style>
<script type="text/javascript">
	$(function () {
		$("#username").blur(function () {
			if($("#username").val()){
				var xmlHttpRequest1 = new XMLHttpRequest()
				xmlHttpRequest1.onreadystatechange = function () {
					if (xmlHttpRequest1.readyState == 4) {
						if (xmlHttpRequest1.status == 200) {
							var resp = xmlHttpRequest1.responseText
							document.getElementById("usermsg").innerHTML = xmlHttpRequest1.responseText
						}
					}
				}
				xmlHttpRequest1.open("Post", "user")
				xmlHttpRequest1.setRequestHeader("CONTENT-TYPE", "application/x-www-form-urlencoded");
				xmlHttpRequest1.send("method=ajax&username=" + $("#username").val())
			}else{
				document.getElementById("usermsg").innerHTML="";
			}
		})
	})

	function checkForm(o){    var re=/^(1[58798]{9})|(15[89][0-9]{8})$/;
		if(!re.test(o.tel.value)){      alert('请输入正确的手机号码。');
			return false;
		}
	}
	$(function () {
		$("#reg_form").validate({
			rules:{
				username:{
					required:true,
					minlength:2
				},
				password:{
					required:true,
					minlength:5,
					maxlength:20
				},
				repassword:{
					required:true,
					minlength:5,
					maxlength:20,
					equalTo:"#inputPassword3"
				},
				telephone:{
					required:true
				},
				email:{
					required:true,
					email:true
				},
				agree:"required"
			},
			messages:{
				username:{
					required:"请输入你的名字",
					minlength:"最小长度为2"
				},
				password:{
					required:"请输入密码",
					minlength:"最小长度不能少于5",
					maxlength:"最大长度不能超过20"
				},
				repassword:{
					required:"请输入密码",
					minlength:"最小长度不能少于5",
					maxlength:"最大长度不超过20",
					equalTo:"两次密码输入不一致"
				},
				telephone:{
					required:"请输入你的电话好吗"
				},
				email:"请输入正确的邮箱地址",
			}
		})
	})


</script>

</head>
<body>

	<!-- 引入header.jsp -->
	<jsp:include page="/header.jsp"></jsp:include>

	<div class="container"
		style="width: 100%; background: url('image/regist_bg.jpg');">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8"
				style="background: #fff; padding: 40px 80px; margin: 30px; border: 7px solid #ccc;">

				<font>会员注册</font>USER REGISTER
				<form method=" post" action="/user" id="reg_form"  class="form-horizontal" style="margin-top: 5px;">
					<input type = "hidden" name = "method" value="register">

					<div class="form-group">
						<label for="username" class="col-sm-2 control-label">用户名</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="username"
								   name = "username"placeholder="请输入用户名">
							<span id="usermsg"></span>
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">密码</label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id="inputPassword3"
								name = "password" placeholder="请输入密码">
						</div>
					</div>
					<div class="form-group">
						<label for="confirmpwd" class="col-sm-2 control-label">确认密码</label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id="confirmpwd"
							name = "repassword"	placeholder="请输入确认密码">
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">手机号码</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="tel"
								   name = "telephone"	placeholder="请输入手机号码">
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">Email</label>
						<div class="col-sm-6">
							<input type="email" class="form-control" id="inputEmail3"
							name = "email"	placeholder="Email">
						</div>
					</div>
					<div class="form-group">
						<label for="usercaption" class="col-sm-2 control-label">姓名</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="usercaption"
							name = "name"	placeholder="请输入姓名">
						</div>
					</div>
					<div class="form-group opt">
						<label for="inlineRadio1" class="col-sm-2 control-label">性别</label>
						<div class="col-sm-6">
							<label class="radio-inline"> <input type="radio"
								name="sex" id="inlineRadio1" value="男">
								男
							</label> <label class="radio-inline"> <input type="radio"
								name="sex" id="inlineRadio2" value="女 ">
								女
							</label>
						</div>
					</div>
					<div class="form-group">
						<label for="date" class="col-sm-2 control-label">出生日期</label>
						<div class="col-sm-6">
							<input type="date" class="form-control" name = "birthday">
						</div>
					</div>

					<div class="form-group">
						<label for="date" class="col-sm-2 control-label">验证码</label>
						<div class="col-sm-3">
							<input type="text" class="form-control">

						</div>
						<div class="col-sm-2">
							<img src="image/captcha.jhtml" />
						</div>

					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<input type="submit" width="100" value="注册" name="submit"
								style="background: url('images/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0); height: 35px; width: 100px; color: white;">
						</div>
					</div>
				</form>
			</div>

			<div class="col-md-2"></div>

		</div>
	</div>

	<!-- 引入footer.jsp -->
	<jsp:include page="/footer.jsp"></jsp:include>

</body>
</html>




