<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="d" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request" />
<html lang="en">
  <body>
          	<h3><i class="fa fa-angle-right"></i>List User</h3>
            
            
            <!-- Pagging -->
            <label> Page </label>
            <select onchange="getPage(this)"; id="selectPage">
            
            </select>
            <label> of </label>
            <label id = "totPage"></label>
            <!-- page end --> 
            <div style="border:1px solid white;height:300px;width:100%;overflow-x:scroll;overflow-y:scroll;">
		  	<div class="row mt">
			  		<div class="col-lg-12">
                          <section id="unseen">
                            <table class="table2 table-bordered table-striped table-condensed">
                              <thead>
                              <tr>
                             	  <th style="width:1%;"> Reset Password</th>
                                  <th> User Id</th>
                                  <th> Kategori</th>
								 
                                  <th>ACTION</th>
                              </tr>
                              </thead>
                              <tbody id="tbdUser">
                              </tbody>
                          </table>
                      </section>
                      
                  </div><!--content-panel-->
               </div><!--col-lg-4 -->			
		  	</div><!--row -->
      				
      				<! -- Form Add User -->
      				
                    <div class="row mt">
          		<div class="col-lg-12">
                  <div class="form-panel">
                  	  <h4 class="mb"><i class="fa fa-angle-right"></i> Tambah User</h4>
                      
                      <form class="form-horizontal style-form" method="get">
                          <div class="form-group">
                          
                          <label class="col-sm-2 col-sm-2 control-label">Default Password</label>
                           <div class="col-sm-10">
                                  <input type="text" class="form-control2" style="width:200px;" value="Password" readonly/>   
                          </div>
						  <label class="col-sm-2 col-sm-2 control-label">User Id</label>
                           <div class="col-sm-10">
                                  <input id="txtIdUser" name="txtIdUser" type="text" class="form-control2" style="width:200px;" placeholder="ID login">   
                          </div>
						  <label class="col-sm-2 col-sm-2 control-label">Kategori</label>
                           <div class="col-sm-10">
                                  <select id="optKategori" name="optKategori" class="form-control2" style="width:200px;" >
									<option value="RESELLER"> Sales</option>
									<option value="ADMIN"> Admin</option>
									</select>
                          </div>
                          </div>
                          
                          <button type="button" onclick="userAddCall();" class="btn btn-theme">SIMPAN</button>
                      </form>
                      
                  </div>
          		</div>
          		
          		
          		<script type="text/javascript">
          		//2ac9cb7dc02b3c0083eb70898e549b63
          		
          		var userAddSent = function(data) {
       		
    			var _items = data.items;
    			for ( var i = 0; i < _items.length; i++) {
    				var _item = _items[i];
    				
    				//var _tot=_item.hpp*_item.kurs;
    				//.html(_tot);
    				$('#tbdUser').append(
    						$('<tr><\/tr>')
    							.append($('<td><button type="button" onclick="returpenjualanRemoveCall('+_id+');" class="btn btn-danger btn-xs"><i class="fa fa-random "><\/i><\/button><\/td>'))
    							.append($('<td><\/td>').html(_item.name))
    							.append($('<td><\/td>').html(_item.type))
    							.append($('<td><button type="button" onclick="returpenjualanRemoveCall('+_id+');" class="btn btn-danger btn-xs"><i class="fa fa-trash-o "><\/i><\/button><\/td>'))
    							
    				);
    			}
    			alert ("Data  disimpan");
    			};
    		
    		
				var userAddCall = function() {
				var _data={};
				_data['name']=$('#txtIdUser').val().trim();
				//_data['password']=$('#txtPassword').val().trim();
				_data['type']=$('#optKategori').val().trim();
				
				JSON.post(_data,'${ctx }/json/user-add',10000,userAdd,null,null);
				};
			
				function getPage(obj){
				var _data={};
				_data['page'] = "page"+obj.value;
				JSON.post(_data,'${ctx }/json/getPageUser',10000,userSent,null,null);
				/* setPagging("page"+obj.value,"",""); */
				}
			
          		var userSent = function(data) {
          			document.getElementById("tbdUser").innerHTML = "";
        			
        			if(data.page){
      					document.getElementById("totPage").innerHTML = data.page;
      					 setPagging("1",data.page,"1"); 
      					
      				}
        			var _items = data.items;
        			
        			$('#tbdUser').empty();
        			for ( var i = 0; i < _items.length; i++) {
        				var _item = _items[i];
        				var _id = "'"+_item.id+"'";
        				var _resetPass ="'"+"dc647eb65e6711e155375218212b3964"+"'";
        				//var _tot=_item.hpp*_item.kurs;
        				//.html(_tot);
        				$('#tbdUser').append(
        						$('<tr><\/tr>')
        						.append($('<td><button id="pushEditButton" type="button" onclick="resetPass('+_id+','+_resetPass+')" class="btn btn-primary btn-xs"><i class="fa fa-mail-reply"><\/i><\/button><\/td>'))
								.append($('<td><\/td>').html(_item.name))
    							.append($('<td><\/td>').html(_item.type))
    							.append($('<td><button type="button" onclick="deleteUser('+_id+');" class="btn btn-danger btn-xs"><i class="fa fa-trash-o "><\/i><\/button><\/td>'))
    							
        							
        				);
        			}
        		};
        		
        		/* DELETE DATA */
	       		var deleteUser = function(_id) {
	       			if(confirm("Do you want to delete ?")) {
	       			    this.click;
					var _data={};
					_data['id'] = _id;
					
					JSON.post(_data,'${ctx }/json/user-del',10000,userDelete,null,null);
					
	       	 		}
				else {
					
				return;
				}
	       		};
	       		/*End delete*/
	       		
        		var resetPass = function(_id,_resetPass) {
        			if(confirm("Reset Password ?")) {
           			    this.click;
    				var _data={};
    				_data['id'] = _id;
    				_data['password'] = _resetPass;
    				JSON.post(_data,'${ctx }/json/reset-password',10000,userCall,null,null);
    				
           	 		}
    			else {
    				
    			return;
    			}	
        		};
        		
        		var userAdd = function(data){
					
					if(data.code == 0){
						alert(data.message);
						
						userCall();
						
						}
						if (data.code != 0) {
							
							alert(data.message);
						}
						
						};
				var userDelete = function(data){
							
					if(data.code == 0){
						alert(data.message);
								
						userCall();
								
						}
						if (data.code != 0) {
									
							alert(data.message);
						}
								
						};		
          		var userCall = function() {
					var _data={};
					JSON.post(_data,'${ctx }/json/user',10000,userSent,null,null);
					var _pageTot = userSent.page;
				};
				userCall();
				
		</script>
  </body>
</html>
