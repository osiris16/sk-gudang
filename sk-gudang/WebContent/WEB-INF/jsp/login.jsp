<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%><%@
taglib prefix="d" uri="http://www.opensymphony.com/sitemesh/decorator" %><%@
taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><c:set
var="ctx" value="${pageContext.request.contextPath }" scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    
    <style type="text/css">
    	.error {
			border-color:red;
			background-color:yellow;
		}
    </style>
    
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Dashboard">
    <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

    <title>CS TOYS</title>

    <!-- Bootstrap core CSS -->
    <link href="${ctx }/assets/css/bootstrap.css" rel="stylesheet">
    <!--external css-->
    <link href="${ctx }/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <!-- Custom styles for this template -->
    <link href="${ctx }/assets/css/style.css" rel="stylesheet">
    <link href="${ctx }/assets/css/style-responsive.css" rel="stylesheet">

    
  </head>

  <body>

	<script type="text/javascript" src="${ctx }/web/common.js"></script>
	<script type="text/javascript" src="${ctx }/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx }/jquery-ui/jquery-ui.js"></script>
	<script type="text/javascript" src="${ctx }/jquery-validator/jquery.validate.js"></script>
	<script type="text/javascript" src="${ctx }/jquery-validator/localization/messages_id.js"></script>
	<script type="text/javascript" src="${ctx }/jquery-validator/additional-methods.js"></script>
	<script type="text/javascript" src="${ctx }/jquery-json/json.js"></script>
		

      <!-- MAIN CONTENT-->
      
	  <div id="login-page">
	  	<div class="container">
		      <form class="form-login" id="frmLogin" name="frmLogin" action="">
		        <h2 class="form-login-heading">sign in now</h2>
		        <div class="login-wrap">
		            <input id="txtUserId" name="txtUserId" type="text" class="form-control" autocomplete="on">
		            <br>
		            <input id="txtPassword" name="txtPassword" type="password" class="form-control" >
		            <br>
		            <center>
		            <img src="${ctx }/web/kaptcha" alt="captcha image" style="width:50%"> </center>
		            <input id="txtCaptcha" name="txtCaptcha" type="text" class="form-control" >
		            <button class="btn btn-theme btn-block"  type="submit"><i class="fa fa-lock"></i> SIGN INN</button>
		            <hr>
		        </div>
		      </form>	  	
	  	</div>
	  </div>

    <!-- js placed at the end of the document so the pages load faster -->
    <script src="${ctx }/assets/js/bootstrap.min.js"></script>
    
    <!--BACKSTRETCH-->
    
    <!-- You can use an image of whatever size. This script will stretch to fit in any screen size.-->
    <script type="text/javascript" src="${ctx }/assets/js/jquery.backstretch.min.js"></script>
    <script>
    
    	var loginSent=function(data){
    		var _code = data.code;
    		var _usrType = data.usrType;
    		if(_code == 0){
    			if(_usrType == "ADMIN"){
    				window.location.replace('beranda');
    			}else if (_usrType == "RESELLER"){
    				window.location.replace('berandaSales');
    			}else{
    				window.location.replace('beranda');
    			}
    		
    			return;
    		}
    		alert(data.code+"\n"+"ID atau Password atau Capthca Salah");
    		

    		window.location.replace('login');
    	};
    	
    	
    	var loginProblem=function(data){};
    	var loginAlways=function(data){};
    	
        $.backstretch("${ctx }/assets/img/login-bg.jpg", {speed: 500});
        
        $().ready(function(){
        	$('#frmLogin')
				.trigger('reset')
				.attr('autocomplete','off')
	        	.submit(function(e){
	        		e.preventDefault();
	        		
	        		var _data={};
					_data['username']=$('#txtUserId').val();
					_data['password']=$('#txtPassword').val();
					_data['kaptcha']=$('#txtCaptcha').val();
	
					var _isValid=$(this).valid();
					$('#txtPassword').val('');
					$('#txtKaptcha').val('');
					if(!_isValid){
						return;
					}
					
					JSON.post(_data,'${ctx }/json/login',10000,loginSent,loginProblem,loginAlways);
	        	});
        	
        	$('#frmLogin')
				.validate({
					  rules:{
						  txtUserId:{required:true},
						  txtPassword:{required:true},
						  txtCaptcha:{required:true}
					  },
					  errorPlacement:function(error,element){
						  //console.log(element.context);
					  }
				});
        	
        	$('#txtUserId').attr('placeholder','User ID').attr('autofocus','autofocus');
        	$('#txtPassword').attr('placeholder','Password');
        	$('#txtCaptcha').attr('placeholder','Captcha');
        	
        });
    </script>
    
  </body>
</html>
