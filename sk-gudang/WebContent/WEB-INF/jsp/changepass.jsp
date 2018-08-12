<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="d" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
  <body>
          	
      				<! -- Form Profile -->
      				
                    <div class="row mt">
          		<div class="col-lg-12">
                  <div class="form-panel">
                  	  <h4 class="mb"><i class="fa fa-angle-right"></i> Profile</h4>
                      
                      <form class="form-horizontal style-form" method="get">
                          <div class="form-group">
                             
							 
						  <label class="col-sm-2 col-sm-2 control-label">Password Lama</label>
                           <div class="col-sm-10">
                                  <input id="TxtOldPass" name="TxtOldPass" type="password" class="form-control2" style="width:200px;" placeholder="Old Pass">   
                          </div>
						  
						  <label class="col-sm-2 col-sm-2 control-label">Password Baru</label>
                           <div class="col-sm-10">
                                  <input id="TxtNewPass" name="TxtNewPass" type="password" class="form-control2" style="width:200px;" placeholder="New Pass">   
                          </div>
                          <label class="col-sm-2 col-sm-2 control-label">Konfirmasi Password Baru</label>
                           <div class="col-sm-10">
                                  <input id="TxtConfirmNewPass" name="TxtConfirmNewPass" type="password" class="form-control2" style="width:200px;" placeholder="Confirm">   
                          </div>
						  </div>
                         
                          
                       
                          <button type="button" onclick="chPass();" class="btn btn-theme">SIMPAN</button>
                      </form>
                  </div>
          		</div><!-- col-lg-12--> 
          		</div>
          		<script type="text/javascript">
          		var chPass = function() {
          			
						var _data={};
						
						var _Password1 = document.getElementById("TxtNewPass").value ;
						var _Password2 = document.getElementById("TxtConfirmNewPass").value ;
						
						if($("#TxtNewPass").val() == "")
							{
							alert("Password tidak boleh kosong");
							}
						else{
						if(_Password1 !=_Password2){
							alert("Password Baru Tidak Sama");
						}
						else{
						_data['oldPass']=$('#TxtOldPass').val();
						_data['newPass']=$('#TxtNewPass').val();
						_data['confirmNewPass']=$('#TxtConfirmNewPass').val();
						
						JSON.post(_data,'${ctx }/json/changepassword',10000,chPassRespond,null,null);
						}
						}
					};
					var chPassRespond = function(data){
							
							alert(data.message);
							
							};
          		</script>

  </body>
</html>
