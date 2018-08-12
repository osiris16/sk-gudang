<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="d" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
  
  <body>
      <!--main content start-->
          	<h3><i class="fa fa-angle-right"></i> Customer</h3>
            <button type="button" class="btn btn-group btn-info" data-toggle="modal" data-target="#myModalAddCust">ADD Customer</button>
            <!--Search Table Barang-->
			<div class="panel panel-default">
			<div class="panel-heading" >
			<i class="glyphicon glyphicon-search" title="cari"></i> Search</i>
			</div>
			<div class="panel-body" id="panelBody">
			<div class="col-lg-6">
			<div class="form-group">
			<label class="col-sm-2 col-sm-2 control-label">Kategori</label>
			
            <div class="col-sm-10">
			<select id = "selByValue" class="form-control2" style="width:200px;">
				<option value = "name">Nama</option>
				<option value = "city">Kota</option>
			</select>
			
			</div>
			<label class="col-sm-2 col-sm-2 control-label"></label>
                         
			<div class="col-sm-10">
			<input type="text" id="inpQuery" class="form-control2" style="width:200px;" >
			</div>
			</div>
			<label class="col-sm-2 col-sm-2 control-label"></label>
			<div class="col-sm-10">
			<button onclick="customerCall();" type="button" style="width:150px"; class="btn btn-outline btn-primary btn-lg btn-block"><i class="glyphicon glyphicon-search" title="cari"></i></button>
			</div>
			</div>
			</div>
			</div>
			
			<br/>
            <!-- Pagging -->
			<button onclick = "firstPage(this)" type="button" >First</button>
            <button onclick = "prevPage(this)" type="button" >Prev</button>
            <label> Page </label>
            <select onchange="getPage(this)"; id="selectPage">
            
            </select>
            <label> of </label>
            <label id = "totPage"></label>
            <button onclick = "nextPage(this)" type="button" >Next</button>
            <!-- page end -->
            <br/>
           
            <button id="show">Show Detail</button>
            <button id="hide">Hide</button>
            <div style="border:1px solid white;height:400px;width:100%;overflow-x:scroll;overflow-y:scroll;">
		  	<div class="row mt">
            
			  		<div class="col-lg-12">
                      <!--<div class="content-panel">-->
                      
                          <section id="unseen">
                           
                            <table class="table2 table-bordered table-striped table-condensed table-hover">
                              <thead>
                              <tr>
                                  <th class="hidden" id="1"> Kode Customer</th>
                                  <th > Nama Customer</th>
                                  <th > Alamat 1</th>
                                  <th class="hidden" id="2"> Alamat 2</th>
                                  <th > Kota</th>
                                  <th class="hidden" id="3"> Kontak</th>
                                  <th > No. Telp</th>
                                  <th class="hidden" id="4"> No. Fax</th>
                                  <th > NPWP</th>
                                  <th >ACTION</th>
                              </tr>
                              </thead>

                              <tbody id="tbdCustomer">
                              
                              </tbody>
                          </table>
                      </section>
                      
                  </div><!--content-panel-->
               </div><!--col-lg-4 -->			
		  	</div><!--row -->
		  	
<!-- Modal Customer -->
<div class="modal fade" id="myModalAddCust" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>

                </button>
                <h4 class="modal-title" id="myModalLabel">Add Customer</h4>
                </div>
      				
                    <div class="row mt">
          		<div class="col-lg-12">
                  <div class="form-panel">
                  	  <h4 class="mb"><i class="fa fa-angle-right"></i> Tambah Customer</h4>
                      
                      <form class="form-horizontal style-form" method="get">
                          <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">Kode Customer</label>
                              <div class="col-sm-10">
                                  <input id="txtCustomerCode" name="txtCustomerCode" type="text"  class="form-control2"  style="width:200px;"  required/>
                          		 
                          </div>
                          
                          <label class="col-sm-2 col-sm-2 control-label">Nama Customer</label>
                           <div class="col-sm-10">
                                  <input id="txtCustomerName" name="txtCustomerName" type="text" class="form-control2"  style="width:200px;" required/>   
                          </div>
                          </div>
                         
                          
                       <div class="form-group">
                          <label class="col-sm-2 col-sm-2 control-label">Alamat 1</label>
                           <div class="col-sm-10">
                                  <input id="txtAddress1" name="txtAddress1" type="text" class="form-control2"  style="width:250px;"  >
                           </div>
                          
                           <label class="col-sm-2 col-sm-2 control-label">Alamat 2</label>
                            <div class="col-sm-10">
                                  <input id="txtAddress2" name="txtAddress2" type="text" class="form-control2"  style="width:200px;" >
                           </div>
                          
                           <label class="col-sm-2 col-sm-2 control-label">Kota</label>
                           <div class="col-sm-10">
                                  <input id="txtCity" name="txtCity" type="text" class="form-control2"  style="width:150px;"  >
                          </div>
                          </div>
                          
                          <div class="form-group">
                          <label class="col-sm-2 col-sm-2 control-label">Kontak</label>
                           <div class="col-sm-10">
                                  <input id="txtContact" name="txtContact" type="text" class="form-control2"  style="width:200px;"  >
                           </div>
                           <label class="col-sm-2 col-sm-2 control-label">No.TLP</label>
                           <div class="col-sm-10">
                                  <input id="txtPhone" name="txtPhone" type="text" class="form-control2"  style="width:200px;"  >
                           </div>
                          
                            <label class="col-sm-2 col-sm-2 control-label">No.Fax</label>
                            <div class="col-sm-10">
                                  <input id="txtFax" name="txtFax" type="text" class="form-control2"  style="width:200px;"  >
                            </div>
                            </div>
                          
                            
                          <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">NPWP</label>
                            <div class="col-sm-10">
                                  <input id="txtTax" name="txtTax" class="form-control2"  style="width:200px;"  type="text" >
                            </div>
                          
                          </div>
                          <button type="button" class="btn btn-theme" onclick="customerAddCall();">SIMPAN</button>
                      </form>
                  </div>
          		</div><!-- col-lg-12-->      	
          	</div><!-- /row -->
          	</div>
          	</div>
          	</div>
          	
          	<!-- Modal Edit Customer -->
<div class="modal fade" id="myEditCustomer" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>

                </button>
                <h4 class="modal-title" id="myModalLabel">Edit Customer</h4>
                </div>
      				
                    <div class="row mt">
          		<div class="col-lg-12">
                  <div class="form-panel">
                  	  <h4 class="mb"><i class="fa fa-angle-right"></i> Edit Customer</h4>
                      
                      <form class="form-horizontal style-form" method="get">
                          <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">Kode Customer</label>
                              <div class="col-sm-10">
                                  <input id="txtEditCustomerCode" name="txtEditCustomerCode" type="text"  class="form-control2"  style="width:200px;"  required/>
                          		 
                          </div>
                          
                          <label class="col-sm-2 col-sm-2 control-label">Nama Customer</label>
                           <div class="col-sm-10">
                                  <input id="txtEditCustomerName" name="txtEditCustomerName" type="text" class="form-control2"  style="width:200px;" required/>   
                          </div>
                          </div>
                         
                          
                       <div class="form-group">
                          <label class="col-sm-2 col-sm-2 control-label">Alamat 1</label>
                           <div class="col-sm-10">
                                  <input id="txtEditAddress1" name="txtEditAddress1" type="text" class="form-control2"  style="width:250px;"  >
                           </div>
                          
                           <label class="col-sm-2 col-sm-2 control-label">Alamat 2</label>
                            <div class="col-sm-10">
                                  <input id="txtEditAddress2" name="txtEditAddress2" type="text" class="form-control2"  style="width:200px;" >
                           </div>
                          
                           <label class="col-sm-2 col-sm-2 control-label">Kota</label>
                           <div class="col-sm-10">
                                  <input id="txtEditCity" name="txtEditCity" type="text" class="form-control2"  style="width:150px;"  >
                          </div>
                          </div>
                          
                          <div class="form-group">
                          <label class="col-sm-2 col-sm-2 control-label">Kontak</label>
                           <div class="col-sm-10">
                                  <input id="txtEditContact" name="txtEditContact" type="text" class="form-control2"  style="width:200px;"  >
                           </div>
                           <label class="col-sm-2 col-sm-2 control-label">No.TLP</label>
                           <div class="col-sm-10">
                                  <input id="txtEditPhone" name="txtEditPhone" type="text" class="form-control2"  style="width:200px;"  >
                           </div>
                          
                            <label class="col-sm-2 col-sm-2 control-label">No.Fax</label>
                            <div class="col-sm-10">
                                  <input id="txtEditFax" name="txtEditFax" type="text" class="form-control2"  style="width:200px;"  >
                            </div>
                            </div>
                          
                            
                          <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">NPWP</label>
                            <div class="col-sm-10">
                                  <input id="txtEditTax" name="txtEditTax" class="form-control2"  style="width:200px;"  type="text" >
                            </div>
                          
                          </div>
                          <button type="button" class="btn btn-theme" onclick="customerEditCall();">Update</button>
                      </form>
                  </div>
          		</div><!-- col-lg-12-->      	
          	</div><!-- /row -->
          	</div>
          	</div>
          	</div>
          	<div class="modal fade" id="myViewSale" tabindex="-1" role="dialog" aria-labelledby="myModalViewSaleLabel" aria-hidden="true">
		    <div class="modal-dialog modal-lg">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
		
		                </button>
		                <h4 class="modal-title" id="myModalViewSaleLabel">Periode Penjualan</h4>
		                </div>
		      				
		                    <div class="row mt">
		          		<div class="col-lg-12">
		                  <div class="form-panel">
		                  	  <h4 class="mb"><i class="fa fa-angle-right"></i> Periode Penjualan <i id="iName"></i></h4>
		                      
		                      <form class="form-horizontal style-form" method="get">
		                          <div class="form-group">
		                              <label class="col-sm-2 col-sm-2 control-label">Dari Tanggal</label>
		                              <div class="col-sm-10">
		                                  <input id="dateFrom" value="01/01/2009" class="form-control2" style="width:200px;" type="text">
		                          		 
		                          </div>
		                          
		                          <label class="col-sm-2 col-sm-2 control-label">Sampai Tanggal</label>
		                           <div class="col-sm-10">
		                                  <input id="dateTo" value="01/01/2009" class="form-control2" style="width:200px;" type="text">   
		                          </div>
		                          </div>
		                          <a type="button" id="btnViewSale" class="btn btn-theme" onclick="openPageSale(this)">Lihat</a>
		                      </form>
		                  </div>
		          		</div><!-- col-lg-12-->      	
		          	</div><!-- /row -->
		          	</div>
		          	</div>
          	</div>
          	
          	<script type="text/javascript">
					/* UPDATE DATA Customer */
					
					function pushEdit(_id,_code,_name,_address1,_address2,_city,_contact,_phone,_fax,_tax){
						_itemId = _id
    					var _data={};
    			
    					var d = document.getElementById("myEditCustomer");  //   Javascript
    					d.setAttribute('data-id' , _itemId); //
    					document.getElementById("txtEditCustomerCode").value = _code;
						document.getElementById("txtEditCustomerName").value = _name;
						document.getElementById("txtEditAddress1").value = _address1;
						document.getElementById("txtEditAddress2").value = _address2;
						document.getElementById("txtEditCity").value = _city;
						document.getElementById("txtEditContact").value = _contact;
						document.getElementById("txtEditPhone").value = _phone;
						document.getElementById("txtEditFax").value = _fax;
						document.getElementById("txtEditTax").value = _tax;
						
						//JSON.post(_data,'${ctx }/json/product',10000,productModalSent,null,null);
    					}
	        		
	        		var customerEditSent = function(data) {
	        			var recid = document.getElementById("myEditCustomer");
						var _dataId= recid.getAttribute("data-id");
	        			var _items = data.items;
	        			
	        			for (var i = 0; i < _items.length; i++){
	        				var _item = _items[i];
	        				if (_item.id == _dataId) {
								document.getElementById("txtEditCustomerCode").value = _item.code;
								document.getElementById("txtEditCustomerName").value = _item.name;
								document.getElementById("txtEditAddress1").value = _item.address1;
								document.getElementById("txtEditAddress2").value = _item.address2;
								document.getElementById("txtEditCity").value = _item.city;
								document.getElementById("txtEditContact").value = _item.contact;
								document.getElementById("txtEditPhone").value = _item.phone;
								document.getElementById("txtEditFax").value = _item.fax;
								document.getElementById("txtEditTax").value = _item.tax;
								
	        				}
	        			}
	        			
	        		};
					
					var customerEditCall = function() {
						
						var _recid = document.getElementById("myEditCustomer");
						var _dataId= _recid.getAttribute("data-id");
						var _data={};
						_data['id'] = _dataId;
						var _code = $('#txtEditCustomerCode').val().trim();
						var _name = $('#txtEditCustomerName').val().trim();
						var _addr1 = $('#txtEditAddress1').val().trim();
						var _addr2 = $('#txtEditAddress2').val().trim();
						var _city = $('#txtEditCity').val().trim();
						var _contact = $('#txtEditContact').val().trim();
						var _phone = $('#txtEditPhone').val().trim();
						var _fax = $('#txtEditFax').val().trim();
						var _tax = $('#txtEditTax').val().trim();
						
						_data['code']=_code.replace("'",""); // validasi petik
						_data['name']=_name.replace("'","");
						_data['address1']=_addr1.replace("'","");
						_data['address2']=_addr2.replace("'","");
						_data['city']=_city.replace("'","");
						_data['contact']=_contact.replace("'","");
						_data['phone']=_phone.replace("'","");
						_data['fax']=_fax.replace("'","");
						_data['tax']=_tax.replace("'","");
						JSON.post(_data,'${ctx }/json/customer-mod',10000,customerEdit,null,null);
						
					};
					
					/* END UPDATE DATA Edit */
					
					/* DELETE DATA */
		       		var customerRemoveCall = function(_id,_code,_name) {
		       			if(confirm("Do you want to delete ?")) {
		       			    this.click;
						var _data={};
						_data['id'] = _id;
						_data['code'] = _code;
						_data['name'] = _name;
						
						JSON.post(_data,'${ctx }/json/customer-del',10000,customerDelete,null,null);
						
		       	 		}
					else {
						
					return;
					}
		       		};
		       		/*End delete*/
		    </script>
		       		
          	<script type="text/javascript">
          	var customerAddSent = function(data) {
    			var _items = data.items;
    			
    			for ( var i = 0; i < _items.length; i++) {
    				var _item = _items[i];
    				
    				//var _tot=_item.hpp*_item.kurs;
    				//.html(_tot);
    				$('#tbdCustomer').append(
    						$('<tr><\/tr>')
    							.append($('<td><\/td>').html(_item.code))
    							.append($('<td><\/td>').html(_item.name))
    							.append($('<td><\/td>').html(_item.address1))
    							.append($('<td><\/td>').html(_item.address2))
    							.append($('<td><\/td>').html(_item.city))
    							.append($('<td><\/td>').html(_item.contact))
    							.append($('<td><\/td>').html(_item.phone))
    							.append($('<td><\/td>').html(_item.fax))
    							.append($('<td><\/td>').html(_item.tax))
    				);
    				} 
    			};
      		var customerAddCall = function() {
				var _data={};
				var _code = $('#txtCustomerCode').val().trim();
				var _name = $('#txtCustomerName').val().trim();
				var _addr1 = $('#txtAddress1').val().trim();
				var _addr2 = $('#txtAddress2').val().trim();
				var _city = $('#txtCity').val().trim();
				var _contact = $('#txtContact').val().trim();
				var _phone = $('#txtPhone').val().trim();
				var _fax = $('#txtFax').val().trim();
				var _tax = $('#txtTax').val().trim();
				
				_data['code']=_code.replace("'",""); // validasi petik
				_data['name']=_name.replace("'","");
				_data['address1']=_addr1.replace("'","");
				_data['address2']=_addr2.replace("'","");
				_data['city']=_city.replace("'","");
				_data['contact']=_contact.replace("'","");
				_data['phone']=_phone.replace("'","");
				_data['fax']=_fax.replace("'","");
				_data['tax']=_tax.replace("'","");
				
				JSON.post(_data,'${ctx }/json/customer-add',10000,customerAdd,null,null);
				
			};
			
			
			function firstPage(obj){
       			var _selPage = document.getElementById("selectPage");
       			_selPage.value = 0;
       			getPage(_selPage);
       		}
       		function lastPage(obj){
       			var _selPage = document.getElementById("selectPage");
       			var _totPage = document.getElementById("totPage").innerHTML *1; 
       			_selPage.value = _totPage-1;
       			getPage(_selPage);
       		}
       		function nextPage(obj){
       			var _selPage = document.getElementById("selectPage");
       			var _currentPage = _selPage.value;
       			var _totPage = document.getElementById("totPage").innerHTML *1; 
       			if(_totPage == 1){
       				return;
       			}
       			var _setNext = _currentPage*1+1;
       			if(_setNext <=_totPage-1){
       				_selPage.value = _setNext;
           			getPage(_selPage);
       			}
       		}
			function prevPage(obj){
       			var _selPage = document.getElementById("selectPage");
       			var _currentPage = _selPage.value;
       			var _totPage = document.getElementById("totPage").innerHTML *1; 
       			if(_totPage == 1){
       				return;
       			}
       			var _setPrev = _currentPage*1-1;
       			if(_setPrev >=0){
       				_selPage.value = _setPrev;
           			getPage(_selPage);
       			}
       		}
			function getPage(obj){
				var _data={};
				_data['page'] = "page"+obj.value;
				JSON.post(_data,'${ctx }/json/getPageCustomer',10000,customerSent,null,null);
				/* setPagging("page"+obj.value,"",""); */
			}
			
          		var customerSent = function(data) {
          			document.getElementById("tbdCustomer").innerHTML = "";
          			
          			if(data.page){
      					document.getElementById("totPage").innerHTML = data.page;
      					 setPagging("1",data.page,"1"); 
      					
      				}
      				
        			var _items = data.items;
        			$('#tbdCustomer').empty();
        			for ( var i = 0; i < _items.length; i++) {
        				var _item = _items[i];
        				var _id = "'"+_item.id+"'";
        				var _code = "'"+_item.code+"'";
        				var _name = "'"+_item.name+"'";
        				var _address1 = "'"+_item.address1+"'";
        				var _address2 = "'"+_item.address2+"'";
        				var _city = "'"+_item.city+"'";
        				var _contact = "'"+_item.contact+"'";
        				var _phone = "'"+_item.phone+"'";
        				var _fax = "'"+_item.fax+"'";
        				var _tax = "'"+_item.tax+"'";
        				
        				
        				$('#tbdCustomer').append(
        						$('<tr><\/tr>')
        							.append($('<td class="hidden" id="1a"><\/td>').html(_item.code))
        							.append($('<td><\/td>').html(_item.name))
        							.append($('<td><\/td>').html(_item.address1))
        							.append($('<td class="hidden" id="2a"><\/td>').html(_item.address2))
        							.append($('<td><\/td>').html(_item.city))
        							.append($('<td class="hidden" id="3a"><\/td>').html(_item.contact))
        							.append($('<td><\/td>').html(_item.phone))
        							.append($('<td class="hidden" id="4a"><\/td>').html(_item.fax))
        							.append($('<td><\/td>').html(_item.tax))
        							.append($('<td><button id="pushEditButton" type="button" onclick="pushEdit('+_id+','+_code+','+_name+','+_address1+','+_address2+','+_city+','+_contact+','+_phone+','+_fax+','+_tax+')" data-toggle="modal" data-target="#myEditCustomer" class="btn btn-primary btn-xs"><i class="fa fa-pencil "><\/i><\/button> - <button type="button" onclick="customerRemoveCall('+_id+','+_code+','+_name+');" class="btn btn-danger btn-xs"><i class="fa fa-trash-o "><\/i><\/button><\/td>'))
									//<button type="button" onclick="showSale('+_id+','+_name+');" data-toggle="modal" data-target="#myViewSale" class="btn btn-primary btn-xs"><i class="glyphicon glyphicon-search"></i></button> - 
        				);
        			}
        		};
        		
        		var customerCall = function() {
					var _data={};
					_data['queryData']=$('#inpQuery').val().trim();
					_data['byValue']=$('#selByValue').val().trim();
					JSON.post(_data,'${ctx }/json/customer',10000,customerSent,null,null);
					var _pageTot = customerSent.page;
				};
				
				var customerAdd = function(data){
					
					if(data.code == 0){
					alert(data.message);
					$('#myModalAddCust').modal('hide');
					customerCall();
					
					}
					if (data.code != 0) {
						
						alert(data.message);
					}
					else {
						
					}
					};
					
				var customerEdit = function(data){
					
					if(data.code == 0){
					alert(data.message);
					$('#myEditCustomer').modal('hide');
					customerCall();
					
					}
					if (data.code != 0) {
						
						alert(data.message);
					}
					else {
						
					}
					};
					
				var customerDelete = function(data){
						
					if(data.code == 0){
					alert(data.message);
					customerCall();
						
					}
					if (data.code != 0) {
							
						alert("Data Masih Terpakai Hubungi Operator");
					}
					else {
						
					}
					};
				customerCall();
				$(function(){
		          	$('#dateTo,#dateFrom').datepicker().datepicker("setDate", new Date());
		          	});
		</script>
		
		
		<!-- Hide / Show Table -->
		<script type="text/javascript">
		
		$(document).ready(function(){
    		$("#hide").click(function(){
       		 $("#1,#1a,#2,#2a,#3,#3a,#4,#4a").addClass("hidden");
        	});
    	$("#show").click(function(){
        $("#1,#1a,#2,#2a,#3,#3a,#4,#4a").removeClass("hidden");
        
   		 });
		});
		function showSale(id,name){
			document.getElementById("iName").innerHTML = name;
			document.getElementById("btnViewSale").value = id;
			
		}


		
		function openPageSale(obj){
			var _dateTo = document.getElementById("dateTo").value;
			var _dateFrom = document.getElementById("dateFrom").value;
			location.href = 'penjualan?id='+obj.value+'&to='+_dateTo+'&from='+_dateFrom+'&by=customer';
		}
		</script>
		
		<script type="text/javascript">
          /*Date Picker*/
          $(function()
        		  {
          	$('#dateTo').datepicker().datepicker("setDate", new Date());
          	
          	
          	$('#dateFrom').datepicker();
    		});
          </script>
          
          
  </body>
</html>
