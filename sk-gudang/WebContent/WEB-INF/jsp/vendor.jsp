<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="d" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
  <body>
      
          	<h3><i class="fa fa-angle-right"></i> PEMASOK/VENDOR</h3>
            
            <!-- Add button -->
           	<button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModalAddVendor">ADD Vendor</button>
            <br/>
            <br/>
            <button id="showSearch" type="button" class="btn btn-group btn-info"><i class="glyphicon glyphicon-search" title="cari"></i> Search</i></button>
            
			<!--Search Table Barang-->
			<div id="searchProd" class="panel panel-default hidden">
			
			<div class="panel-heading" >
			<button id="hideSearch" type="button" class="btn btn-group btn-info">Hide</button>
			</div>
			
			<div class="panel-body" id="panelBody">
			<div class="col-lg-6">
			<div class="form-group">
			<label class="col-sm-2 col-sm-2 control-label">Kategori</label>
                         
			<div class="col-sm-10">
			<select id = "selByValue" class="form-control2" style="width:200px;">
				<option value = "name">Nama</option>
				<option value = "city">Kota</option>
				<option value = "country">Negara</option>
				<option value = "vta">Valuta</option>
				
			</select>
			</div>
			<label class="col-sm-2 col-sm-2 control-label"></label>
                         
			<div class="col-sm-10">
			<input type="text" id="inpQuery" class="form-control2" style="width:200px;" >
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-sm-2 col-sm-2 control-label"></label>
                         
			<button onclick="vendorCall();" type="button" style="width:220px;" class="btn btn-outline btn-primary btn-lg btn-block"><i class="glyphicon glyphicon-search" title="cari"></i></button>
			</div>
			</div>
			</div>
			</div>
			<br/>
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
			<div style="border:1px solid white;height:350px;width:100%;overflow-x:scroll;">
            <div class="panel-body">
            <div class="table-responsive">
            <section id="unseen">
                              <table id="tblVendor"  class="table1 table-bordered table-striped table-condensed table-hover">
                              
                              <thead >
                              
                              <tr>
                                  <th>Nama Vendor</th>
                                  <th> Negara</th>
                                  <th class="hidden" id="2"> Mata Uang</th>
                                  <th class="hidden" id="3"> Alamat 1</th>
                                  <th class="hidden" id="4"> Alamat 2</th>
                                  <th class="hidden" id="5"> Kota</th>
                                  <th> No. Telp</th>
                                  <th class="hidden" id="6"> No. Fax</th>
                                  <th class="hidden" id="7"> Email</th>
                                  <th> NPWP</th>
                                  <th class="hidden" id="8"> Cargo/Ekspedisi</th>
                                  <th>ACTION</th>
                              </tr>
                              </thead>
                              <tbody id="tbdVendor">
                              </tbody>
                          </table>
                      </section>
                      
                  </div><!----->
               </div><!---- -->			
		  	</div><!--scroll -->
      				
      				
 <div class="modal fade" id="myModalAddVendor" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>

                </button>
                
    			<h4 class="modal-title" id="myModalLabel">Add Vendor</h4>
                </div>
            	<div style="border:1px solid white;height:500px;width:100%;overflow-x:scroll;overflow-y:scroll;">
            
                <div class="row mt">
          		<div class="col-lg-12">
                <div class="form-panel">
                	<h4 class="mb"><i class="fa fa-angle-right"></i> Tambah Vendor</h4>
                      
                    <form class="form-horizontal style-form" method="get" name="vendorForm" ng-submit="registerVendor()" novalidate>
                        <div class="form-group">
                        <label class="col-sm-2 col-sm-2 control-label">Vendor/Pemasok</label>
                        <div class="col-sm-10">
                        	<input id="txtVendName" name="txtVendName" type="text" class="form-control" style="width:200px;" required/>   
                        </div>
                        <label class="col-sm-2 col-sm-2 control-label">Negara</label>
                        <div class="col-sm-10">
                        	<input value="CHINA" id="txtCountryName" name="txtCountryName" type="text" class="form-control" style="width:200px;" required/>   
                        </div>
                        </div>
                          
                      	<div class="form-group">
                        <label class="col-sm-2 col-sm-2 control-label">Mata Uang</label>
                        <input id="TxtMataUang" name="TxtMataUang" type="text" class="form-control" style="width:200px;" value="HKD" >
                        
                        </div>
                          
                      	<div class="form-group">
                       	<label class="col-sm-2 col-sm-2 control-label">Alamat 1</label>
                        <div class="col-sm-10">
                          	<input id="txtVendAddr1" name="txtVendAddr1" type="text" class="form-control" style="width:300px;" >
                        </div>
                          
                        <label class="col-sm-2 col-sm-2 control-label">Alamat 2</label>
                        <div class="col-sm-10">
                            <input id="txtVendAddr2" name="txtVendAddr2" type="text" class="form-control" style="width:300px;" >
                        </div>
                          
                        <label class="col-sm-2 col-sm-2 control-label">Kota</label>
                        <div class="col-sm-10">
                            <input id="txtVendCity" name="txtVendCity" type="text" class="form-control" style="width:200px;" >
                        </div>
                        </div>
                          
                        <div class="form-group">
                        <label class="col-sm-2 col-sm-2 control-label">No.TLP</label>
                        <div class="col-sm-5">
                            <input id="txtVendPhone" name="txtVendPhone" type="text" class="form-control" style="width:200px;" >
                        </div>
                          
                        <label class="col-sm-2 col-sm-2 control-label">No.Fax</label>
                        <div class="col-sm-5">
                            <input id="txtVendFax" name="txtVendFax" type="text" class="form-control" style="width:200px;">
                        </div>
                          
                        <label class="col-sm-2 col-sm-2 control-label">Email</label>
                        <div class="col-sm-10">
                            <input id="txtVendEmail" name="txtVendEmail" type="text" class="form-control" style="width:200px;">
                        </div>
                       	</div>
                          
                        <div class="form-group">
                        <label class="col-sm-2 col-sm-2 control-label">NPWP</label>
                        <div class="col-sm-10">
                            <input id="txtVendTax" name="txtVendTax"  type="text" class="form-control" style="width:200px;">
                        </div>
                          
                        <label class="col-sm-2 col-sm-2 control-label">Cargo Ekspedisi</label>
                        <div class="col-sm-10">
                            <input id="txtVendCargo" name="txtVendCargo" type="text" class="form-control" style="width:200px;">
                        </div>
                        </div>
                        <div>
                        
            			<button type="button" class="btn btn-theme" onclick="vendorAddCall();">SIMPAN</button>    
        				</div>
        				
                      </form>
                  </div>
          		</div><!-- col-lg-12-->      	
          	</div><!-- /row -->
          	</div></div><!-- /row -->
          	</div>
          	</div>
          	
          	
          	
          	
          	
          	
<!-------------------------------------- Tabel modal  -------------------------------------------->
<!-- Tabel modal Vendor -->
<div class="modal fade" id="myEditVendor" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
				</button>
                
                <h4 class="modal-title" id="myModalLabel">Edit Vendor</h4>
			</div>
			
            <div style="border:1px solid white;height:450px;width:100%;overflow-x:scroll;overflow-y:scroll;">
                 
            <div class="modal-body">
            <!-- Update Vendor -->
               <div class="form-panel">
                  	  <h4 class="mb"><i class="fa fa-angle-right"></i> Update Vendor</h4>
                      
                        <div class="form-group">
                        <label class="col-sm-2 col-sm-2 control-label">Vendor/Pemasok</label>
                        <div class="col-sm-10">
                        	<input id="txtVendNameEdit" name="txtVendNameEdit" type="text" class="form-control" style="width:200px;" required/>   
                        </div>
                          
                        <label class="col-sm-2 col-sm-2 control-label">Negara</label>
                        <div class="col-sm-10">
                        	<input id="txtCountryNameEdit" name="txtCountryNameEdit" type="text" class="form-control" style="width:200px;" required/>   
                        </div>
                        </div>
                          
                		<div class="form-group">
                        <label class="col-sm-2 col-sm-2 control-label">Mata Uang</label>
                       <input id="TxtMataUangEdit" name="TxtMataUangEdit" type="text" class="form-control" style="width:300px;" >
                        
                        </div>
                          
                       	<div class="form-group">
                        <label class="col-sm-2 col-sm-2 control-label">Alamat 1</label>
                        <div class="col-sm-10">
                        	<input id="txtVendAddr1Edit" name="txtVendAddr1Edit" type="text" class="form-control" style="width:300px;" >
                        </div>
                        
                        <label class="col-sm-2 col-sm-2 control-label">Alamat 2</label>
                        <div class="col-sm-10">
                        	<input id="txtVendAddr2Edit" name="txtVendAddr2Edit" type="text" class="form-control" style="width:300px;" >
                        </div>
                        <label class="col-sm-2 col-sm-2 control-label">Kota</label>
                        <div class="col-sm-10">
                        	<input id="txtVendCityEdit" name="txtVendCityEdit" type="text" class="form-control" style="width:200px;" >
                        </div>
                        </div>
                          
                       	<div class="form-group">
                        <label class="col-sm-2 col-sm-2 control-label">No.TLP</label>
                        <div class="col-sm-10">
                        	<input id="txtVendPhoneEdit" name="txtVendPhoneEdit" type="text" class="form-control" style="width:200px;"  >
                        </div>
                        
                        <label class="col-sm-2 col-sm-2 control-label">No.Fax</label>
                        <div class="col-sm-10">
                        	<input id="txtVendFaxEdit" name="txtVendFaxEdit" type="text" class="form-control" style="width:200px;" >
                        </div>
                        
                        <label class="col-sm-2 col-sm-2 control-label">Email</label>
                        <div class="col-sm-10">
                        	<input id="txtVendEmailEdit" name="txtVendEmailEdit" type="text" class="form-control" style="width:200px;"  >
                        </div>
                        </div>
                          
                        <div class="form-group">
                        <label class="col-sm-2 col-sm-2 control-label">NPWP</label>
                        <div class="col-sm-10">
                        	<input id="txtVendTaxEdit" name="txtVendTaxEdit" class="form-control" style="width:200px;"  type="text" >
                        </div>
                          
                        <label class="col-sm-2 col-sm-2 control-label">Cargo Ekspedisi</label>
                        <div class="col-sm-10">
                        	<input id="txtVendCargoEdit" name="txtVendCargoEdit" type="text"  class="form-control" style="width:200px;" >
                        </div>
                        </div>
                        <button type="button" id="buttonEdit" onclick="vendorEditCall()" class="btn btn-theme" >UPDATE</button>
                    
                    </div>
               <!-- END UPDATE VENDOR -->
            	</div>
            <div class="modal-footer"></div>
        </div>
       </div>
   </div>
</div>



                  
<!------------------------ SCRIPT ------------------------------------------ -->
					
					<script type="text/javascript">
					/* UPDATE DATA Vendor */
					
					function pushEdit(_id,_name,_country,_vta,_address1,_address2,_city,_phone,_fax,_email,_tax,_cargo){
						_itemId = _id
    					var _data={};
    			
    					var d = document.getElementById("myEditVendor");  //   Javascript
    					d.setAttribute('data-id' , _itemId); //
    					document.getElementById("txtVendNameEdit").value = _name;
						document.getElementById("txtCountryNameEdit").value = _country;
						document.getElementById("TxtMataUangEdit").value = _vta;
						document.getElementById("txtVendAddr1Edit").value = _address1;
						document.getElementById("txtVendAddr2Edit").value = _address2;
						document.getElementById("txtVendCityEdit").value = _city;
						document.getElementById("txtVendPhoneEdit").value = _phone;
						document.getElementById("txtVendFaxEdit").value = _fax;
						document.getElementById("txtVendEmailEdit").value = _email;
						document.getElementById("txtVendTaxEdit").value = _tax;
						document.getElementById("txtVendCargoEdit").value = _cargo;
						
						//JSON.post(_data,'${ctx }/json/product',10000,productModalSent,null,null);
    					}
	        		
	        		var vendorEditSent = function(data) {
	        			var recid = document.getElementById("myEditVendor");
						var _dataId= recid.getAttribute("data-id");
	        			var _items = data.items;
	        			
	        			for (var i = 0; i < _items.length; i++){
	        				var _item = _items[i];
	        				if (_item.id == _dataId) {
								document.getElementById("txtVendNameEdit").value = _item.name;
								document.getElementById("txtCountryNameEdit").value = _item.country;
								document.getElementById("TxtMataUangEdit").value = _item.vta;
								document.getElementById("txtVendAddr1Edit").value = _item.address1;
								document.getElementById("txtVendAddr2Edit").value = _item.address2;
								document.getElementById("txtVendCityEdit").value = _item.city;
								document.getElementById("txtVendPhoneEdit").value = _item.phone;
								document.getElementById("txtVendFaxEdit").value = _item.fax;
								document.getElementById("txtVendEmailEdit").value = _item.email;
								document.getElementById("txtVendTaxEdit").value = _item.tax;
								document.getElementById("txtVendCargoEdit").value = _item.cargo;
	        				}
	        			}
	        			
	        		};
					
					var vendorEditCall = function() {
						
						var _recid = document.getElementById("myEditVendor");
						var _dataId= _recid.getAttribute("data-id");
						var _data={};
						_data['id'] = _dataId;
						var _name = $('#txtVendNameEdit').val().trim();
						var _country = $('#txtCountryNameEdit').val().trim();
						var _vta = $('#TxtMataUangEdit').val().trim();
						var _address1 = $('#txtVendAddr1Edit').val().trim();
						var _address2 = $('#txtVendAddr2Edit').val().trim();
						var _city = $('#txtVendCityEdit').val().trim();
						var _phone = $('#txtVendPhoneEdit').val().trim();
						var _fax = $('#txtVendFaxEdit').val().trim();
						var _email = $('#txtVendEmailEdit').val().trim();
						var _tax = $('#txtVendTaxEdit').val().trim();
						var _cargo = $('#txtVendCargoEdit').val().trim();
						
						_data['name']=_name.replace("'",""); // validasi petik
						_data['country']=_country.replace("'","");
						_data['vta']=_vta.replace("'","");
						_data['address1']=_address1.replace("'","");
						_data['address2']=_address2.replace("'","");
						_data['city']=_city.replace("'","");
						_data['phone']=_phone.replace("'","");
						_data['fax']=_fax.replace("'","");
						_data['email']=_email.replace("'","");
						_data['tax']=_tax.replace("'","");
						_data['cargo']=_cargo.replace("'","");
						
						JSON.post(_data,'${ctx }/json/vendor-mod',10000,vendorEdit,null,null);
						
					};
					
					/* END UPDATE DATA Edit */
					
					/* DELETE DATA */
		       		var vendorRemoveCall = function(_id) {
		       			if(confirm("Do you want to delete ?")) {
		       			    this.click;
						var _data={};
						_data['id'] = _id;
						JSON.post(_data,'${ctx }/json/vendor-del',10000,vendorCall,null,null);
						alert ("Data berhasil dihapus");
					
		       	 		}
					else {
						
					return;
					}
		       		};
		       		/*End delete*/
		       		</script>
		       		
		       		<script type="text/javascript">
	          		var vendorAddSent = function(data){
	          			
	        			var _items = data.items;
	        			for ( var i = 0; i < _items.length; i++) {
	        				var _item = _items[i];
	        				var _id = "'"+_item.id+"'";
	        				var _dataItem = "'"+_item+"'";
	        				//var _tot=_item.hpp*_item.kurs;
	        				//.html(_tot);
	        				$('#tbdVendor').append(
	        						$('<tr><\/tr>')
	        							
	        							.append($('<td><\/td>').html(_item.name))
	        							.append($('<td><\/td>').html(_item.country))
	        							.append($('<td><\/td>').html(_item.vta))
	        							.append($('<td><\/td>').html(_item.address1))
	        							.append($('<td><\/td>').html(_item.address2))
	        							.append($('<td><\/td>').html(_item.city))
	        							.append($('<td><\/td>').html(_item.phone))
	        							.append($('<td><\/td>').html(_item.fax))
	        							.append($('<td><\/td>').html(_item.email))
	        							.append($('<td><\/td>').html(_item.tax))
	        							.append($('<td><\/td>').html(_item.cargo))
	        							.append($('<td><button id="pushEditButton" type="button" onclick="pushEdit('+_id+')" data-toggle="modal" data-target="#myEditVendor" class="btn btn-primary btn-xs"><i class="fa fa-pencil "><\/i><\/button> - <button type="button" onclick="vendorRemoveCall('+_id+');" class="btn btn-danger btn-xs"><i class="fa fa-trash-o "><\/i><\/button><\/td>'))

	        				);
	        				 
	        			}
	        			
	        		};
	        		
	        		
	          		var vendorAddCall = function() {
						var _data={};
						var _name = $('#txtVendName').val().trim();
						var _country = $('#txtCountryName').val().trim();
						var _vta = $('#TxtMataUang').val().trim();
						var _address1 = $('#txtVendAddr1').val().trim();
						var _address2 = $('#txtVendAddr2').val().trim();
						var _city = $('#txtVendCity').val().trim();
						var _phone = $('#txtVendPhone').val().trim();
						var _fax = $('#txtVendFax').val().trim();
						var _email = $('#txtVendEmail').val().trim();
						var _tax = $('#txtVendTax').val().trim();
						var _cargo = $('#txtVendCargo').val().trim();
						
						_data['name']=_name.replace("'",""); // validasi petik
						_data['country']=_country.replace("'","");
						_data['vta']=_vta.replace("'","");
						_data['address1']=_address1.replace("'","");
						_data['address2']=_address2.replace("'","");
						_data['city']=_city.replace("'","");
						_data['phone']=_phone.replace("'","");
						_data['fax']=_fax.replace("'","");
						_data['email']=_email.replace("'","");
						_data['tax']=_tax.replace("'","");
						_data['cargo']=_cargo.replace("'","");
						
						JSON.post(_data,'${ctx }/json/vendor-add',10000,vendorAdd,null,null);
					};
					/* Start Paging Vendor */
					
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
						JSON.post(_data,'${ctx }/json/getPageVendor',10000,vendorSent,null,null);
						/* setPagging("page"+obj.value,"",""); */
					}
					/* End Paging Vendor */
					var vendorSent = function(data) {
						document.getElementById("tbdVendor").innerHTML = "";
          					if(data.page){
          						document.getElementById("totPage").innerHTML = data.page;
          					 		setPagging("1",data.page,"1"); 
          						
          				}
          				var _items = data.items;
        				$('#tbdVendor').empty();
        				for ( var i = 0; i < _items.length; i++) {
        				var _item = _items[i];
        				var _id = "'"+_item.id+"'";
        				var _name = "'"+_item.name+"'";
        				var _country = "'"+_item.country+"'";
        				var _vta = "'"+_item.vta+"'";
        				var _address1 = "'"+_item.address1+"'";
        				var _address2 = "'"+_item.address2+"'";
        				var _city = "'"+_item.city+"'";
        				var _phone = "'"+_item.phone+"'";
        				var _fax = "'"+_item.fax+"'";
        				var _email = "'"+_item.email+"'";
        				var _tax = "'"+_item.tax+"'";
        				var _cargo = "'"+_item.cargo+"'";
        				
        				
        				//var _tot=_item.hpp*_item.kurs;
        				//alert(_dataItem);
        				//.html(_tot);
        				$('#tbdVendor').append(
        						$('<tr><\/tr>')
        							
        							.append($('<td><\/td>').html(_item.name))
        							.append($('<td><\/td>').html(_item.country))
        							.append($('<td class="hidden" id="2a"><\/td>').html(_item.vta))
        							.append($('<td class="hidden" id="3a"><\/td>').html(_item.address1))
        							.append($('<td class="hidden" id="4a"><\/td>').html(_item.address2))
        							.append($('<td class="hidden" id="5a"><\/td>').html(_item.city))
        							.append($('<td><\/td>').html(_item.phone))
        							.append($('<td class="hidden" id="6a"><\/td>').html(_item.fax))
        							.append($('<td class="hidden" id="7a"><\/td>').html(_item.email))
        							.append($('<td><\/td>').html(_item.tax))
        							.append($('<td class="hidden" id="8a"><\/td>').html(_item.cargo))
        							.append($('<td class="right"><button id="pushEditButton" type="button" onclick="pushEdit('+_id+','+_name+','+_country+','+_vta+','+_address1+','+_address2+','+_city+','+_phone+','+_fax+','+_email+','+_tax+','+_cargo+')" data-toggle="modal" data-target="#myEditVendor" class="btn btn-primary btn-xs"><i class="fa fa-pencil "><\/i><\/button> - <button type="button" onclick="vendorRemoveCall('+_id+');" class="btn btn-danger btn-xs"><i class="fa fa-trash-o "><\/i><\/button><\/td>'))
	        					);
        				}
        			};
				
        			var vendorCall = function() {
						var _data={};
						_data['queryData']=$('#inpQuery').val().trim();
						_data['byValue']=$('#selByValue').val().trim();
						JSON.post(_data,'${ctx }/json/vendor',10000,vendorSent,null,null);
						var _pageTot = vendorSent.page;
					};
					
					var vendorAdd = function(data){
					
						if(data.code == 0){
							alert(data.message);
							$('#myModalAddVendor').modal('hide');
							vendorCall();
							
							}
							if (data.code != 0) {
								
								alert(data.message);
							}
							else {
								
							}
							};
							
					var vendorEdit = function(data){
						if(data.code == 0){
							alert(data.message);
							$('#myEditVendor').modal('hide');
							vendorCall();
							
							}
							if (data.code != 0) {
								
								alert(data.message);
							}
							else {
								
							}
							
							};
						
					vendorCall();
					
					</script>
			
			<!-- Hide / Show Table -->
					<script type="text/javascript">
					$(document).ready(function(){
    					$("#hide").click(function(){
        					$("#2,#2a,#3,#3a,#4,#4a,#5,#5a,#6,#6a,#7,#7a,#8,#8a").addClass("hidden");
        				});
    					
    					$("#show").click(function(){
       						$("#2,#2a,#3,#3a,#4,#4a,#5,#5a,#6,#6a,#7,#7a,#8,#8a").removeClass("hidden");
        				});
    					
    				});

					$(document).ready(function(){
    					$("#hideSearch").click(function(){
       						$("#searchProd").addClass("hidden");
       					}); 
   						
    					$("#showSearch").click(function(){
       						$("#searchProd").removeClass("hidden");
      
   						});
   					});
					</script>

  </body>
</html>
