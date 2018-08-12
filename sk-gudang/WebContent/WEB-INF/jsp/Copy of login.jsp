<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%><%@
taglib prefix="d" uri="http://www.opensymphony.com/sitemesh/decorator" %><%@
taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><c:set
var="ctx" value="${pageContext.request.contextPath }" scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
	<style type="text/css">
		.error {
			border-color:red;
			background-color:yellow;
		}
	</style>
	
	<link rel="stylesheet" href="${ctx }/css/login.css">
</head>
<body>
	<script type="text/javascript" src="${ctx }/web/common.js"></script>

	<script type="text/javascript" src="${ctx }/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx }/jquery-ui/jquery-ui.js"></script>
	<script type="text/javascript" src="${ctx }/jquery-validator/jquery.validate.js"></script>
	<script type="text/javascript" src="${ctx }/jquery-validator/localization/messages_id.js"></script>
	<script type="text/javascript" src="${ctx }/jquery-validator/additional-methods.js"></script>
	<script type="text/javascript" src="${ctx }/jquery-json/json.js"></script>

	<form action="" name="frmLogin" id="frmLogin" class="login">
		<div class="login-screen">
			<div class="app-title">
				<h1>Login</h1>
			</div>

			<div class="login-form">
				<div class="control-group">
				<input type="text" class="login-field" value="" id="txtUsername" name="txtUsername">
				<label class="login-field-icon fui-user" for="txtUsername"></label>
				</div>

				<div class="control-group">
				<input type="password" class="login-field" value="" id="txtpassword" name="txtpassword">
				<label class="login-field-icon fui-lock" for="txtpassword"></label>
				</div>

				<div class="control-group">
				<hr>
				<img id="imgKaptcha" alt="Kaptcha" src="${ctx }/file-images/transbg.png" style="height: 60px; width: 200px; margin-bottom: 5px;"><br>
				<input type="password" class="login-field" value="" id="txtKaptcha" name="txtKaptcha">
				<label class="login-field-icon fui-lock" for="txtKaptcha"></label>
				</div>

				<a class="btn btn-primary btn-large btn-block" href="javascript:" id="ancLogin">login</a>
			</div>
		</div>
	</form>
	
	<script type="text/javascript">
		
		$().ready(function(){
			
			$('#imgKaptcha').attr('src','${ctx }/web/kaptcha?'+new Date().getTime());
			
			$('#txtUsername').attr('placeholder','Username');
			$('#txtpassword').attr('placeholder','Password');
			$('#txtKaptcha').attr('placeholder','Kaptcha');
			
			$('#ancLogin').click(function(){
				$('#frmLogin').trigger('submit');
			});
			
			$('#frmLogin')
				.validate({
					  rules:{
						  txtUsername:{required:true},
						  txtpassword:{required:true},
						  txtKaptcha:{required:true}
					  },
					  errorPlacement:function(error,element){
						  //console.log(element.context);
					  }
				});
			
			$('#frmLogin')
				.trigger('reset')
				.attr('autocomplete','off')
				.submit(function(e){
					e.preventDefault();
					var _data={};
					_data['username']=$('#txtUsername').val();
					_data['password']=$('#txtpassword').val();
					_data['kaptcha']=$('#txtKaptcha').val();

					var _isValid=$(this).valid();
					$('#txtpassword').val('');
					$('#txtKaptcha').val('');

					if(!_isValid){
						$('#imgKaptcha').attr('src','${ctx }/web/kaptcha?'+new Date().getTime());
						return;
					}
										
					JSON.post(_data,'${ctx }/json/login',10000,loginSent,loginProblem,loginAlways);
				});
		});
	
		var loginSent=function(data){
			if(!data||data.code!==0){
				//alert(ERRORS[data.code].description);
				return;
			}
			
			alert('status: '+data.message);
		};
		
		var loginProblem=function(err){
			alert('problem: '+err);
		};

		var loginAlways=function(data){
			$('#imgKaptcha').attr('src','${ctx }/web/kaptcha?'+new Date().getTime());
		};

	</script>
	
</body>
</html>