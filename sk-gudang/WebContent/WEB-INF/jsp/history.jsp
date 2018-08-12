<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="d" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
<script type="text/javascript" src="${ctx }/paging/historyAction.js"></script>
</head>
  <body>
      
          	<h3><i class="fa fa-angle-right"></i> HISTORY</h3>
            
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
				<option value = "city">Kota</option>
				<option value = "country">Negara</option>
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
            <label> Page </label>
            <select onchange="getPage(this)"; id="selectPage">
            
            </select>
            <label> of </label>
            <label id = "totPage"></label>
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
                                  <th>ID</th>
                                  <th>Tanggal asdasasd </th>
                                  <th>Action Type</th>
                                  <th>Type</th>
                                  <th>User</th>
                                  <th>ACTION</th>
                              </tr>
                              </thead>
                              <tbody id="tbdHistory">
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
                        	<input id="txtCountryName" name="txtCountryName" type="text" class="form-control" style="width:200px;" required/>   
                        </div>
                        </div>
                          
                      	<div class="form-group">
                        <label class="col-sm-2 col-sm-2 control-label">Mata Uang</label>
                        <div class="col-sm-10">
                        	<select id="TxtMataUang" name="TxtMataUang" class="form-control" style="width:200px;" required/>
                          		<option value="IDR">IDR</option>
                          		<option value="USD">USD</option>
                          		<option value="HKD">HKD</option>
                          	</select>
                        </div>
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
               <div class="form-panel" id="modalHistory">
                  	  <h4 class="mb"><i class="fa fa-angle-right"></i> Detail History</h4>
                      
                       
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
		       			function historyCall(){
		       				var data = {};
		       				data["dateFrom"] = $('#dateFrom').val().trim();
		       				data["dateTo"] = $('#dateTo').val().trim();
		       					$.ajax({
		       						url : "Url",
		       						type: "POST",
		       						data : JSON.stringify(data),
		       						dataType : "json",
		       						success: function(data, textStatus, jqXHR)
		       							{
			       							alert(data.message);
		       							},
		       								error: function (jqXHR, textStatus, errorThrown)
		       							{
		       								alert(textStatus);
		       							}
		       						});
		       			}
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
