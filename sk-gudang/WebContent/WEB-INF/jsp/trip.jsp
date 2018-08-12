<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="d" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
  <body>
          	<h3><i class="fa fa-angle-right"></i> TRIP</h3>
            <button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModalAddTrip">ADD Trip</button>
            <br/>
			<br/>
			
			<button id="show" type="button" class="btn btn-group btn-info"><i class="glyphicon glyphicon-search" title="cari"></i> Search</i></button>
            
			<!--Search Table Trip-->
			<div id="searchTrip" class="panel panel-default hidden">
			
			<div class="panel-heading" >
			<button id="hide" type="button" class="btn btn-group btn-info">Hide</button>
			</div>
			<div class="panel-body" id="panelBody">
			<div class="col-lg-6">
			<div class="form-group">
			
			<label class="col-sm-2 col-sm-2 control-label">Dari</label>
			<div class="col-sm-10">
			<input type="text" id="dateFrom" value = "01/01/2009" class="form-control2" style="width:200px;" >
			</div>
			
			<label class="col-sm-2 col-sm-2 control-label">Sampai</label>
			<div class="col-sm-10">
			<input type="text" id="dateTo" value = "01/01/2100"  class="form-control2" style="width:200px;" >
			</div>
			<label class="col-sm-2 col-sm-2 control-label">No Trip</label>
			<div class="col-sm-10">
			<input type="text" id="tripno" class="form-control2" style="width:200px;" >
			</div>
			</div>
			<div class="form-group">
			<button onclick="tripCall('');" type="button" style="width:300px;" class="btn btn-outline btn-primary btn-lg btn-block"><i class="glyphicon glyphicon-search" title="cari"></i></button>
			<button onclick="tripPrint('DOWNLOADEXCEL');" type="button" style="width:300px;" class="btn btn-outline btn-primary btn-lg btn-block">Print To Excel</button>
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
            
            <div style="border:1px solid white;height:400px;width:100%;overflow-x:scroll;overflow-y:scroll;">
            	
		  	<div class="row mt">
			<div class="col-lg-12">
           
                    <section id="unseen">
            		<!--Table Trip-->
                        <table id="tblTrip" class="table1 table-bordered table-striped table-condensed table-hover" >
                            <thead>
                              <tr>
                              	  <th> </th>
                              	  <th> Cetak</th>
                              	  <th> No. Trip</th>
                                  <th> Date</th>
                                  <th> Vendor</th>
                                  <th> T.Carton</th>
                                  <th> T.Bruto Vta</th>
                                  <th> Kurs</th> 
                                  <th> T.Bruto Idr</th>
                                  <th> Cost</th>
                                  <th> Disc</th> 
                                  <th> T.Netto Vta</th>
                                  <th> T.Netto Idr</th>
                                  
                                  <th>ACTION</th>
                                  </tr>
                            </thead>
                           	<tbody id="tbdTrip">
                            </tbody>
                        </table>
                   	</section>
                  </div>
		  	</div><!--row-->
		  	
      				
<!-- Form Trip -->
	<div class="modal fade" id="myModalAddTrip" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    	<div class="modal-dialog modal-lg">
        	<div class="modal-content">
            	<div class="modal-header">
                	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
					</button>
                
                	<h4 class="modal-title" id="myModalLabel">ADD Trip</h4>
				</div>
			
<div style="border:1px solid white;height:500px;width:100%;overflow-x:scroll;overflow-y:scroll;">
	<div class="row mt">
		<div class="col-lg-12">
			<div class="form-panel">
			<h4 class="mb"><i class="fa fa-angle-right"></i> Tambah Trip</h4>
            
            <form class="form-horizontal style-form" method="get" >
            	<div class="form-group">
                <label class="col-sm-2 col-sm-2 control-label">Tgl. Trip</label>
                          <div class="col-sm-8">
                          <input class="form-control2"  style="width:150px;" type="text" id="DateTrip" name="DateTrip" required="required">
                          
                          <label class="control-label">&nbsp;&nbsp;&nbsp;&nbsp;</label>
                          
                          <label class="control-label">No. Trip</label>
                          <input class="form-control2"  style="width:150px;" id="TxtNoTrip" name="TxtNoTrip" type="text"  placeholder="No. Trip">
                          </div>
                          
                          </div>
                          
                          <%-- <div class="form-group">
                          <label class="col-sm-2 col-sm-2 control-label">Tgl. Nota</label>
                          <div class="col-sm-8">
                          <input class="form-control2" style="width:150px;" type="text" id="DateNota" name="DateNota" required="required">
                          
                          <label class="control-label">&nbsp;&nbsp;&nbsp;&nbsp;</label>
                          
                          <label class=" control-label">No. Nota</label>
                          <input class="form-control2"  style="width:150px;" id="TxtNoNota" nam="TxtNoNota" type="text" placeholder="No. Nota">
                          </div>
                              
                          </div> --%>
                          
                          <div class="form-group">
                          
                          <label class="col-sm-2 col-sm-2 control-label">Nama Vendor</label>
                          <div class="col-sm-10">
                          <input class="form-control"  style="width:200px;" id="TxtNamaVendor" name="TxtNamaVendor" type="text" readonly/>
                          <!-- Kode Vendor -->
                          <input class="hidden" id="TxtIdVendor" name="TxtIdVendor" type="text" readonly/>
                              
                          <!-- ModalTable -->
						  <button type="button" class="btn btn-group btn-info" data-toggle="modal" data-target="#myModalVendor" onclick ="vendorCall();"><i class="fa fa-search "></i></button>
						  </div>
                              
                          <label class="col-sm-2 col-sm-2 control-label">Negara</label>
                          <div class="col-sm-10">
                          <input class="form-control"  style="width:200px;" id="TxtNegara" name="TxtNegara" type="text" readonly/>
                              </div>
                              
                          <label class="col-sm-2 col-sm-2 control-label">Mata Uang</label>
                          <div class="col-sm-10">
                          <input class="form-control"  style="width:200px;" type="text" id="TxtVta" name="TxtVta" readonly/>
  						  </div>
                              
                          <label class="col-sm-2 col-sm-2 control-label">Kurs Beli IDR</label>
                          <div class="col-sm-10">
                          <input class="form-control"  style="width:200px;" id="TxtKursBeli" name="TxtKursBeli" type="number" value="1800">
                          
                          </div> 
                          <label class="col-sm-2 col-sm-2 control-label">Cost(%)</label>
                          <div class="col-sm-10">
                          <input id="TxtCostTrip" name="TxtCostTrip" type="number" class="form-control2"  style="width:80px;" value="0">
                          
                          </div> 
                          
                          <label class="col-sm-2 col-sm-2 control-label">Disc(%)</label>
                          <div class="col-sm-10">
                          <input id="TxtDiscTrip" name="TxtDiscTrip" type="number" class="form-control2"  style="width:80px;" value="0">
                          
                          </div> 
                             
                          <label class="col-sm-2 col-sm-2 control-label">Tgl. Terima</label>
                          <div class="col-sm-10">
                          <input class="form-control"  style="width:150px;" id="DateTerima" name="DateTerima" type="text" >
                          </div>
                          </div>
                          
                          
                          <button type="button" onclick="tripAddCall();" class="btn btn-theme">SIMPAN</button>
                      </form>
                  </div>
                  </div></div></div></div></div></div>
                  
                  
<!-------------------------------------- Tabel modal  -------------------------------------------->
<!-- Tabel modal Update Trip -->
<div class="modal fade" id="myEditTrip" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
				</button>
                <h4 class="modal-title" id="myModalLabel">Edit Trip</h4>
			</div>
			
			
            <div style="border:1px solid white;height:500px;width:100%;overflow-x:scroll;overflow-y:scroll;">
            <div class="modal-body">
            <!-- Trip Update -->
                  	  <h4 class="mb"><i class="fa fa-angle-right"></i> Edit Trip</h4>
                      
                      <form class="form-horizontal style-form" method="get" >
                      <div class="form-group">
                      
                      <label class="col-sm-2 control-label">Tgl. Trip</label>
                      <div class="col-sm-8">
                      <input id="DateTripEdit" name="DateTripEdit" class="form-control2"   style="width:150px;" type="text">
                      
                      <label class="control-label">&nbsp;&nbsp;&nbsp;&nbsp;</label>
                      
                      <label class="control-label">No. Trip</label>
                      <input id="TxtNoTripEdit" name="TxtNoTripEdit" type="text" class="form-control2"  style="width:150px;" >
                      </div>
                      </div>
                      
                      
                      <div class="form-group">
                      <label class="col-sm-2 col-sm-2 control-label">Nama Vendor</label>
                      <div class="col-sm-10">
                      <input id="TxtNamaVendorEdit" name="TxtNamaVendorEdit" type="text" class="form-control2"  style="width:200px;" readonly/>
                      <input id="TxtIdVendorEdit" name="TxtIdVendorEdit" type="text" class="hidden"  style="width:200px;" readonly/>
                      
                      <!-- ModalTable -->
					  <button type="button" class="btn btn-group btn-info" data-toggle="modal" data-target="#myModalVendor" onclick ="vendorCall();"><i class="fa fa-search "></i></button>
					  </div>
                          
                      <label class="col-sm-2 col-sm-2 control-label">Negara</label>
                      <div class="col-sm-10">
                      <input id="TxtNegaraEdit" name="TxtNegaraEdit" type="text" class="form-control2"  style="width:200px;" placeholder="Negara" readonly/>
                      </div>
                      
                      <label class="col-sm-2 col-sm-2 control-label">Mata Uang</label>
                      <div class="col-sm-10">
                      <input type="text" class="form-control2"  style="width:150px;" id="TxtVtaEdit" name="TxtVtaEdit" readonly/>
  					  </div>
                      
                      <label class="col-sm-2 col-sm-2 control-label">Kurs Beli IDR</label>
                      <div class="col-sm-10">
                      <input id="TxtKursBeliEdit" name="TxtKursBeliEdit" type="number" class="form-control2"  value="1" style="width:150px;">
                      
                      </div>
                      <label class="col-sm-2 col-sm-2 control-label">Cost(%)</label>
                          <div class="col-sm-10">
                          <input id="TxtCostTripEdit" name="TxtCostTripEdit" type="number" class="form-control2"  style="width:80px;"  required/>
                          
                          </div> 
                          
                          <label class="col-sm-2 col-sm-2 control-label">Disc(%)</label>
                          <div class="col-sm-10">
                          <input id="TxtDiscTripEdit" name="TxtDiscTripEdit" type="number" class="form-control2"  style="width:80px;" required/>
                          
                          </div> 
                      </div>
                        
                      <div class="form-group">
                      
                      <label class="col-sm-2 col-sm-2 control-label">Tgl. Terima</label>
                      <div class="col-sm-10">
                      <input id="DateTerimaEdit" name="DateTerimaEdit" type=text class="form-control2"  style="width:150px;">
                      </div>
                      </div>
                      
                      <button type="button" onclick="tripEditCall();" class="btn btn-theme">Update</button>
                      </form>
            </div>
        	<div class="modal-footer"></div>
        </div>
        </div>
    </div>
</div>




<!-- Tabel modal Vendor -->
<div class="modal fade" id="myModalVendor" onclick ="" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>

                </button>
                
                <h4 class="modal-title" id="myModalLabel">List Vendor</h4>
			</div>
			<button id="showSearchVend" type="button" class="btn btn-group btn-info"><i class="glyphicon glyphicon-search" title="cari"></i> Search</i></button>
            
			<!--Search Table Vendor-->
			<div id="searchVend" class="panel panel-default hidden">
			<div class="panel-heading" >
			<button id="hideSearchVend" type="button" class="btn btn-group btn-info">Hide</button>
			
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
			
			<!-- Pagging -->
			<!-- Pagging -->
			<button onclick = "firstPageVen(this)" type="button" >First</button>
            <button onclick = "prevPageVen(this)" type="button" >Prev</button>
            <label> Page </label>
            <select onchange="getPageVen(this)"; id="selectPageVen">
            
            </select>
            <label> of </label>
            <label id = "totPageVen"></label>
            <button onclick = "nextPageVen(this)" type="button" >Next</button>
            <!-- page end -->

            <div style="border:1px solid white;height:350px;width:100%;overflow-x:scroll;overflow-y:scroll;">
                 
            <div class="modal-body">
            <!-- Vendor -->
               <table  id="tblVendor"  class="table2 table-bordered table-striped table-condensed table-hover">
				<thead>
                              <tr>
                                  <th>Nama Pemasok/Vendor</th>
                                  <th> Negara</th>
                                  <th> Mata Uang</th>
                                  <th> Alamat 1</th>
                                  <th> Alamat 2</th>
                                  <th> Kota</th>
                                  <th> No. Telp</th>
                                  <th> No. Fax</th>
                                  <th> Email</th>
                                  <th> NPWP</th>
                                  <th> Cargo/Ekspedisi</th>
                                  
                              </tr>
                              </thead>
							</div>
                             <tbody id="tbdVendor">
                              
                              
                              </tbody>
                          </table>
                      
            </div>
            <div class="modal-footer"></div>
        </div>
        </div>
    </div>
</div>
</div>

<!--Form Pembelian -->
<div class="modal fade" id="myModalAddPembelian" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
			</button>
            
            <h4 class="modal-title" id="myModalLabel">List Pembelian</h4>
		</div>
            
            <div style="border:1px solid white;height:500px;width:100%;overflow-x:scroll;overflow-y:scroll;">
            <div class="modal-body">
               <div class="row mt">
          		<div class="col-lg-12">
                  <div class="form-panel">
                  	 <h4 class="mb"><i class="fa fa-angle-right"></i> Tambah Pembelian</h4>
                      <form id="form1" class="form-horizontal style-form" method="get" action="">
                         
                         <div class="form-group">
                         <label class="col-sm-2 col-sm-2 control-label">Valuta Beli</label>
                         <div class="col-sm-10">
                         <input class="form-control2"  style="width:100px;" id="TxtValutaBeli" name="TxtValutaBeli" readonly/>
  						 
						 <input type="text" name="TxtIdTrip" id="TxtIdTrip" class="hidden"  style="width:200px;" readonly/>
                         <input type="text" name="TxtNoTripBeli" id="TxtNoTripBeli" class="form-control2"  style="width:100px;" readonly/>
                         <input type="text" name="TxtDateTripBeli" id="TxtDateTripBeli" class="form-control2"  style="width:180px;" readonly/>
                         
                         </div>
                         
                         <label class="col-sm-2 col-sm-2 control-label">Kurs H.Beli</label>
                         <div class="col-sm-10">
                         <input type="text" id="TxtKursBeliTrip" name="TxtKursBeliTrip" class="form-control2"  style="width:150px;" readonly/>
                         </div>
                         </div>
                              
                         <div class="form-group">
                         <label class="col-sm-2 col-sm-2 control-label">Kode Barang</label>
                         <div class="col-sm-10">
                         <input id="TxtKodeProduct" name="TxtKodeProduct" type="text" class="form-control2"  style="width:200px;" readonly/>
                         <button type="button" class="btn btn-group btn-info" data-toggle="modal" data-target="#myModalProduct" onclick ="productCall();"><i class="fa fa-search "></i></button>
						 
                         </div>
                         <label class="col-sm-2 col-sm-2 control-label">Nama Barang</label>
                         <div class="col-sm-10">
                         <!-- Rec Id Barang -->
                         <input id="TxtIdStock" name="TxtIdStock" type="text" class="hidden"  style="width:200px;" readonly/>
                         <input id="TxtNamaProduct" name="TxtNamaProduct" type="text" class="form-control2"  style="width:200px;" readonly/>
                         </div>
						 <label class="col-sm-2 col-sm-2 control-label">Isi 1 Karton</label>
                         <div class="col-sm-10">
                         <input id="TxtIsiCtn" name="TxtIsiCtn" type="text" class="form-control2"  style="width:150px;"  readonly/>
                         <input id="TxtSatIsiCtn" name="TxtSatIsiCtn" type="text" class="form-control2"  style="width:80px;"  readonly/>
                         <label>Berisi</label>
                         <input id="TxtIsiPcs" name="TxtIsiPcs" type="text" class="form-control2"  style="width:200px;"  readonly/>
                         <input id="TxtSatuanPCS" name="TxtSatuanPCS" type="text" class="form-control2"  style="width:80px;" Value="PCS" readonly/>
                         
						 <!-- rec Id Barang -->
						 <input id="TxtIdBarang" name="TxtIdBarang" type="text" class="hidden"  style="width:200px;" readonly/>
                         
                         </div>
                         <label class="col-sm-2 col-sm-2 control-label">Sisa Stock</label>
                         <div class="col-sm-10">
                         <input id="TxtStockCtn" name="TxtStockCtn" type="text" class="form-control2"  style="width:150px;"  readonly/>
                         <input id="TxtSatuanCTN" name="TxtSatuanCTN" type="text" class="form-control2"  style="width:80px;" Value="CTN" readonly/>
                         <input id="TxtTotStockPcs" name="TxtTotStockPcs" type="text" class="hidden"  style="width:150px;"  readonly/>
                         
                         </div>
                         
                         <label class="col-sm-2 col-sm-2 control-label">
                          <input id="RadTotStockRetail" name="RadTotStock" value="retail" type="radio" checked="true" />
                          Stock Retails</label>
                         <div class="col-sm-10">
                         <input id="TxtStockRetailCtn" name="TxtStockRetailCtn" type="text" class="form-control2"  style="width:150px;"  readonly/>
                         <input id="TxtSatuanRetailCTN" name="TxtSatuanRetailCTN" type="text" class="form-control2"  style="width:80px;" Value="CTN" readonly/>
                         <input id="TxtTotStockRetailPcs" name="TxtTotStockRetailPcs" type="text" class="hidden"  style="width:150px;"  readonly/>
                        
                         
                         </div>
                         
                         <label class="col-sm-2 col-sm-2 control-label">
                          <input id="RadTotStockDeptStore" name="RadTotStock" value="deptStore" type="radio" />
                          Stock Dept Store</label>
                         <div class="col-sm-10">
                         <input id="TxtStockDeptStoreCtn" name="TxtStockDeptStoreCtn" type="text" class="form-control2"  style="width:150px;"  readonly/>
                         <input id="TxtSatuanDeptStoreCTN" name="TxtSatuanDeptStoreCTN" type="text" class="form-control2"  style="width:80px;" Value="CTN" readonly/>
                         <input id="TxtTotStockDeptStorePcs" name="TxtTotStockDeptStorePcs" type="text" class="hidden"  style="width:150px;"  readonly/>
                         
                         </div>
                         </div>
                          
                         <div class="form-group">
                         <label class="col-sm-2 col-sm-2 control-label">Qty Beli</label>
                         <div class="col-sm-10">
                         <input id="TxtQtyBeli" name="TxtQtyBeli" type="text" class="form-control2"  style="width:200px;"  />
                         <input type="text" class="form-control2"  style="width:100px;"  value="Karton"  readonly/>
                         </div>
                          
                         
                         <label class="col-sm-2 col-sm-2 control-label">Cost(%)</label>
                         <div class="col-sm-10">
                         <input id="TxtCost" name="TxtCost" type="text" class="form-control2"  style="width:150px;" readonly/>
                         </div> 
                         
                         <label class="col-sm-2 col-sm-2 control-label">Disc(%)</label>
                         <div class="col-sm-10">
                         <input id="TxtDisc" name="TxtDisc" type="text" class="form-control2"  style="width:150px;" readonly/>
                         </div> 
                         </div>
                         
                        <div class="form-group">
                        
                        <label class="col-sm-2 col-sm-2 control-label">H.Beli VTA</label>
                        <div class="col-sm-10">
                        <input  id="TxtSatIsiPcs" name="TxtSatIsiPcs" type="text" class="form-control2"  style="width:100px;" value="PCS" readonly/>
                     	<input id="TxtValutaBeli1" name="TxtValutaBeli1" type="text" class="form-control2"  style="width:100px;" width="20%" readonly/>
                        <input id="TxtHB" name="TxtHB" type="text" class="form-control2"  style="width:150px;"  >
                        <input  id="TxtSatCtn" name="TxtSatCtn" type="text" class="form-control2"  style="width:100px;" value="perCTN" readonly/>
                     	<input id="TxtHBperCart" name="TxtHBperCart" type="text" class="form-control2"  style="width:150px;"  readonly/>
                        </div>
                        <label class="col-sm-2 col-sm-2 control-label">H.Beli VTA Netto</label>
                        <div class="col-sm-10">
                        <input  id="TxtSatIsiPcs2" name="TxtSatIsiPcs2" type="text" class="form-control2"  style="width:100px;" value="PCS" readonly/>
                     	<input id="TxtValutaBeli2" name="TxtValutaBeli2" type="text" class="form-control2"  style="width:100px;" width="20%" readonly/>
                        <input id="TxtHBNetto" name="TxtHBNetto" type="text" class="form-control2"  style="width:150px;" readonly/ >
                        <input  id="TxtSatCtn1" name="TxtSatCtn1" type="text" class="form-control2"  style="width:100px;" value="perCTN" readonly/>
                     	<input id="TxtHBperCartNetto" name="TxtHBperCartNetto" type="text" class="form-control2"  style="width:150px;"  readonly/>
                        </div>
                        
                        <label class="col-sm-2 col-sm-2 control-label"></label>
                        <div class="col-sm-10">
                        <input  id="TxtSatIsiPcs" name="TxtSatIsiPcs" type="text" class="form-control2"  style="width:100px;" value="PCS" readonly/>
                     	<input id="TxtIDR" name="TxtIDR" value="IDR" type="text" class="form-control2"  style="width:100px;" width="20%" readonly/>
                        <input id="TxtHbIdr" name="TxtHbIdr" type="text" class="form-control2"  style="width:150px;" readonly/>
                        </div>
                        
                        <label class="col-sm-2 col-sm-2 control-label">H.Jual IDR</label>
                        <div class="col-sm-10">
                        <input id="TxtSatIsiPcs1" name="TxtSatIsiPcs1" type="text" class="form-control2"  value="PCS" style="width:100px;" readonly/>
                        <input type="text" class="form-control2"  style="width:100px;" value="IDR" readonly/>
                        <input id="TxtHJRP" name="TxtHJRP" type="text" class="form-control2"  style="width:200px;" >
                        <label>Profit</label>
                        <input id="TxtProfit" name="TxtProfit" type="text" class="form-control2"  style="width:200px;" readonly/>
                        </div>
                        </div>
                        
                       
                        <button type="button" onclick="pembelianAddCall();" class="btn btn-theme">SIMPAN</button>
                       
                       </form>
                </div>
                </div>
                </div>
                </div>
                </div>
                </div>
                </div>
                </div>
                
          </section><!--/wrapper -->
 	
<!-- Modal Pembelian-->
<div class="modal fade" id="myModalCallPembelian" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            
            <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
			</button>
            
            <h4 class="modal-title" id="myModalLabel">List Pembelian</h4>
			</div>
            
            <div style="border:1px solid white;height:400px;width:100%;overflow-x:scroll;overflow-y:scroll;">
            
            
            <!-- Pagging -->
			<button onclick = "firstPageBeli(this)" type="button" >First</button>
            <button onclick = "prevPageBeli(this)" type="button" >Prev</button>
            <label> Page </label>
            <select onchange="getPageBeli(this)"; id="selectPageBeli">
            
            </select>
            <label> of </label>
            <label id = "totPageBeli"></label>
            <button onclick = "nextPageBeli(this)" type="button" >Next</button>
            <!-- page end -->
            <br/>
            <table class="table1 table-bordered table-striped table-condensed table-hover">
                              <thead>
                              	<tr>
                              	  <th> No-Trip</th>
                              	  <th> Kd. Barang</th>
                                  <th> Nama Barang</th>
                                  <th> Qty Beli</th>
                                  <!-- <th> Sisa Stok (CTN)</th> -->
                                  <!-- <th> Isi CTN</th>
                                  <th> Satuan Isi CTN</th> -->
                                  <th> Disc</th>
                                  <th> Juml.Netto</th>
                                  <th> Kurs (RP)</th>
                                  <th> Juml.Netto</th>
                                  <!-- <th> Netto/Lsn (RP)</th>
                                  <th> Netto/Pcs (RP)</th> -->
                                  <!-- <th> HJ/pcs (RP)</th> -->
                                  <th> ACTION</th>
                              </tr>
                             </thead>
							 <tbody id="tbdPembelian">
                             </tbody>
                          	</table>
            			  </div>
            	<div class="modal-footer"></div>
        	</div>
        	</div>
    		</div>
    <!--Form Edit Pembelian -->
<div class="modal fade" id="myModalEditPembelian" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
			</button>
            
            <h4 class="modal-title" id="myModalLabel">Edit Pembelian</h4>
		</div>
            
            <div style="border:1px solid white;height:500px;width:100%;overflow-x:scroll;overflow-y:scroll;">
            <div class="modal-body">
               <div class="row mt">
          		<div class="col-lg-12">
                  <div class="form-panel">
                  	 <h4 class="mb"><i class="fa fa-angle-right"></i> Edit Pembelian</h4>
                      <form id="form1" class="form-horizontal style-form" method="get" action="">
                         
                         <div class="form-group">
                         <label class="col-sm-2 col-sm-2 control-label">Valuta Beli</label>
                         <div class="col-sm-10">
                         <input class="form-control2"  style="width:100px;" id="TxtValutaBeliEdit" name="TxtValutaBeliEdit" readonly/>
  						 <input type="text" name="TxtIdTripEdit" id="TxtIdTripEdit" class="hidden"  style="width:200px;" readonly/>
                         <input type="text" name="TxtNoTripBeliEdit" id="TxtNoTripBeliEdit" class="form-control2"  style="width:100px;" readonly/>
                         <input type="text" name="TxtDateTripBeliEdit" id="TxtDateTripBeliEdit" class="form-control2"  style="width:180px;" readonly/>
                         
                         </div>
                         
                         <label class="col-sm-2 col-sm-2 control-label">Kurs H.Beli</label>
                         <div class="col-sm-10">
                         <input type="text" id="TxtKursBeliTripEdit" name="TxtKursBeliTripEdit" class="form-control2"  style="width:150px;" readonly/>
                         </div>
                         </div>
                              
                         <div class="form-group">
                         <label class="col-sm-2 col-sm-2 control-label">Kode Barang</label>
                         <div class="col-sm-10">
                         <input id="TxtKodeProductEdit" name="TxtKodeProductEdit" type="text" class="form-control2"  style="width:200px;" readonly/>
                         <!-- Rec Id Barang -->
                         <input id="TxtIdStockEdit" name="TxtIdStockEdit" type="text" class="hidden"  style="width:200px;" readonly/>
                         
                         <button type="button" class="btn btn-group btn-info" data-toggle="modal" data-target="#myModalProduct" onclick ="productCall();"><i class="fa fa-search "></i></button>
						 
                         </div>
                         <label class="col-sm-2 col-sm-2 control-label">Nama Barang</label>
                         <div class="col-sm-10">
                         <input id="TxtNamaProductEdit" name="TxtNamaProductEdit" type="text" class="form-control2"  style="width:200px;" readonly/>
                         </div>
						 <label class="col-sm-2 col-sm-2 control-label">Isi 1 Karton</label>
                         <div class="col-sm-10">
                         <input id="TxtIsiCtnEdit" name="TxtIsiCtnEdit" type="text" class="form-control2"  style="width:150px;"  readonly/>
                         <input id="TxtSatIsiCtnEdit" name="TxtSatIsiCtnEdit" type="text" class="form-control2"  style="width:80px;"  readonly/>
                         <label>Berisi</label>
                         <input id="TxtIsiPcsEdit" name="TxtIsiPcsEdit" type="text" class="form-control2"  style="width:200px;"  readonly/>
                         <input id="TxtSatuanPCSEdit" name="TxtSatuanPCSEdit" type="text" class="form-control2"  style="width:80px;" Value="PCS" readonly/>
                         
						 <!-- rec Id Barang -->
						 <input id="TxtIdBarangEdit" name="TxtIdBarangEdit" type="text" class="hidden"  style="width:200px;" readonly/>
                         
                         </div>
                         <label class="col-sm-2 col-sm-2 control-label">Sisa Stock</label>
                         <div class="col-sm-10">
                         <input id="TxtStockCtnEdit" name="TxtStockCtnEdit" type="text" class="form-control2"  style="width:150px;"  readonly/>
                         <input id="TxtSatuanCTNEdit" name="TxtSatuanCTNEdit" type="text" class="form-control2"  style="width:80px;" Value="CTN" readonly/>
                         <input id="TxtTotStockPcsEdit" name="TxtTotStockPcsEdit" type="text" class="hidden"  style="width:150px;"  readonly/>
                         
                         </div>
                         </div>
                          
                         <div class="form-group">
                         <label class="col-sm-2 col-sm-2 control-label">Qty Beli</label>
                         <div class="col-sm-10">
                         <input id="TxtQtyBeliEdit" name="TxtQtyBeliEdit" type="text" class="form-control2"  style="width:200px;"  />
                         <input type="text" class="form-control2"  style="width:100px;"  value="Karton"  readonly/>
                         </div>
                          
                         
                         <label class="col-sm-2 col-sm-2 control-label">Cost(%)</label>
                         <div class="col-sm-10">
                         <input id="TxtCostEdit" name="TxtCostEdit" type="text" class="form-control2"  style="width:150px;" readonly/>
                         </div> 
                         
                         <label class="col-sm-2 col-sm-2 control-label">Disc(%)</label>
                         <div class="col-sm-10">
                         <input id="TxtDiscEdit" name="TxtDiscEdit" type="text" class="form-control2"  style="width:150px;" readonly/>
                         </div> 
                         </div>
                         
                        <div class="form-group">
                        
                        <label class="col-sm-2 col-sm-2 control-label">H.Beli VTA</label>
                        <div class="col-sm-10">
                        <input  id="TxtSatIsiPcsEdit" name="TxtSatIsiPcsEdit" type="text" class="form-control2"  style="width:100px;" value="PCS" readonly/>
                     	<input id="TxtValutaBeli1Edit" name="TxtValutaBeli1Edit" type="text" class="form-control2"  style="width:100px;" width="20%" readonly/>
                        <input id="TxtHBEdit" name="TxtHBEdit" type="text" class="form-control2"  style="width:150px;"  >
                        <input  id="TxtSatCtnEdit" name="TxtSatCtnEdit" type="text" class="form-control2"  style="width:100px;" value="perCTN" readonly/>
                     	<input id="TxtHBperCartEdit" name="TxtHBperCartEdit" type="text" class="form-control2"  style="width:150px;"  readonly/>
                        </div>
                        <label class="col-sm-2 col-sm-2 control-label">H.Beli VTA Netto</label>
                        <div class="col-sm-10">
                        <input  id="TxtSatIsiPcs2Edit" name="TxtSatIsiPcs2Edit" type="text" class="form-control2"  style="width:100px;" value="PCS" readonly/>
                     	<input id="TxtValutaBeli2Edit" name="TxtValutaBeli2Edit" type="text" class="form-control2"  style="width:100px;" width="20%" readonly/>
                        <input id="TxtHBNettoEdit" name="TxtHBNettoEdit" type="text" class="form-control2"  style="width:150px;" readonly/ >
                        <input  id="TxtSatCtn1Edit" name="TxtSatCtn1Edit" type="text" class="form-control2"  style="width:100px;" value="perCTN" readonly/>
                     	<input id="TxtHBperCartNettoEdit" name="TxtHBperCartNettoEdit" type="text" class="form-control2"  style="width:150px;"  readonly/>
                        </div>
                        
                        <label class="col-sm-2 col-sm-2 control-label"></label>
                        <div class="col-sm-10">
                        <input  id="TxtSatIsiPcsEdit" name="TxtSatIsiPcsEdit" type="text" class="form-control2"  style="width:100px;" value="PCS" readonly/>
                     	<input id="TxtIDREdit" name="TxtIDREdit" value="IDR" type="text" class="form-control2"  style="width:100px;" width="20%" readonly/>
                        <input id="TxtHbIdrEdit" name="TxtHbIdrEdit" type="text" class="form-control2"  style="width:150px;" readonly/>
                        </div>
                        
                        <label class="col-sm-2 col-sm-2 control-label">H.Jual IDR</label>
                        <div class="col-sm-10">
                        <input id="TxtSatIsiPcs1Edit" name="TxtSatIsiPcs1Edit" type="text" class="form-control2"  value="PCS" style="width:100px;" readonly/>
                        <input type="text" class="form-control2"  style="width:100px;" value="IDR" readonly/>
                        <input id="TxtHJRPEdit" name="TxtHJRPEdit" type="text" class="form-control2"  style="width:200px;" >
                        <label>Profit</label>
                        <input id="TxtProfitEdit" name="TxtProfitEdit" type="text" class="form-control2"  style="width:200px;" readonly/>
                        </div>
                        </div>
                        
                        <button type="button" onclick="pembelianEditCall();" class="btn btn-theme">Update</button>
                       
                       </form>
                </div>
                </div>
                </div>
                </div>
                </div>
                </div>
                </div>
                </div>
                
          </section><!--/wrapper -->
          
          <!-- Modal Barang-->
<div class="modal fade" id="myModalProduct" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            
            <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
			</button>
            
            <h4 class="modal-title" id="myModalLabel">List Barang</h4>
			</div>
            
            <div style="border:1px solid white;height:400px;width:100%;overflow-x:scroll;overflow-y:scroll;">
            
            <div class="modal-body">
            <!-- Barang -->
            <button id="showSearchProd" type="button" class="btn btn-group btn-info"><i class="glyphicon glyphicon-search" title="cari"></i> Search</i></button>
            
			<!--Search Table Barang-->
			<div id="searchProd" class="panel panel-default hidden">
			
			<div class="panel-heading" >
			<button id="hideSearchProd" type="button" class="btn btn-group btn-info">Hide</button>
			</div>
			<div class="panel-body" id="panelBody">
			<div class="col-lg-6">
			<div class="form-group">
			<label class="col-sm-2 col-sm-2 control-label">Kategori</label>
                         
			<div class="col-sm-10">
			<select id = "selByValueProd" class="form-control2" style="width:200px;">
				<option value = "code">Kode Barang</option>
				<option value = "name">Nama Barang</option>
			</select>
			</div>
			<label class="col-sm-2 col-sm-2 control-label"></label>
                         
			<div class="col-sm-10">
			<input type="text" id="inpQueryProd" class="form-control2" style="width:200px;" >
			</div>
			
			<%-- <label class="col-sm-2 col-sm-2 control-label">Nama Barang</label>
			<div class="col-sm-10">
			<input type="text" id="inpNmBarang" class="form-control2" style="width:200px;" >
			</div> --%>
			</div>
			
			<div class="form-group">
			<label class="col-sm-2 col-sm-2 control-label"></label>
                         
			<button onclick="productCall();" type="button" style="width:220px;" class="btn btn-outline btn-primary btn-lg btn-block"><i class="glyphicon glyphicon-search" title="cari"></i></button>
			</div>
			</div>
			</div>
			</div>
			
			<!-- Pagging -->
            <label> Page </label>
            <select onchange="getPageProd(this)"; id="selectPageProd">
            
            </select>
            <label> of </label>
            <label id = "totPageProd"></label>
            <!-- page end --> 
            <table id="tableBarang" class="table2 table-bordered table-striped table-condensed">
            <thead>
                            <tr>
                            	<th> No. Barcode </th>
                            	<th> Kode Barang</th>
                           		<th> Nama Barang </th>
                            	<th> Merek / Label </th>
                            	<th> Jenis Barang</th>
                           		<th> Made in</th>
                       			<th> No. Pabrik</th>
                       			<th> No. SNI </th>
                       			<th> Sisa Stok(CTN)</th>
                            </tr>
                            </thead>
							<tbody id="tbdProduct">
                            </tbody>
                          </table>
            			  </div>
            	<div class="modal-footer"></div>
        	</div>
        	</div>
    		</div>
    		</div>  
    		                       
<!------------------------ SCRIPT ------------------------------------------ -->
					
					<script type="text/javascript">
					/* UPDATE DATA MODAL */
					function pushEditTrip(_id,_numb,_tDate,_tNoteDate,_tNoteNumb,_vendId,_vName,_vCountry,_vVta,_currencyIdr,_dateReceive,_totDisc,_totCost){
	        			_itemId = _id;
	        			var _data={};
	        			
	        			var d = document.getElementById("myEditTrip");  //   Javascript
	        			d.setAttribute('data-id' , _itemId); //
	        			document.getElementById("TxtNoTripEdit").value = _numb;
	        			document.getElementById("DateTripEdit").value = _tDate;
						document.getElementById("TxtIdVendorEdit").value = _vendId;
						document.getElementById("TxtNamaVendorEdit").value = _vName;
						document.getElementById("TxtNegaraEdit").value = _vCountry;
						document.getElementById("TxtVtaEdit").value = _vVta;
						document.getElementById("TxtKursBeliEdit").value = _currencyIdr;
						document.getElementById("DateTerimaEdit").value = _dateReceive;
						document.getElementById("TxtCostTripEdit").value = _totCost;
						document.getElementById("TxtDiscTripEdit").value = _totDisc;
						
						
	        		}
	        		
	        		var tripEditSent = function(data) {
	        			var recid = document.getElementById("myEditTrip");
						var _dataId= recid.getAttribute("data-id");
	        			var _items = data.items;
	        			
	        			for (var i = 0; i < _items.length; i++){
	        				var _item = _items[i];
	        				if (_item.id == _dataId) {
								document.getElementById("DateTripEdit").value = ($.datepicker.formatDate('d MM yy',new Date(_item.trip_date)));
								document.getElementById("TxtNoTripEdit").value = _item.trip_numb;
								//document.getElementById("DateNotaEdit").value = ($.datepicker.formatDate('d MM yy',new Date(_item.trip_dateNote)));
								//document.getElementById("TxtNoNotaEdit").value = _item.trip_noteNumber;
								document.getElementById("TxtIdVendorEdit").value = _item.vendId;
								document.getElementById("TxtNamaVendorEdit").value = _item.trip_vendName;
								document.getElementById("TxtNegaraEdit").value = _item.trip_vendCountry;
								document.getElementById("TxtVtaEdit").value = _item.trip_vta;
								document.getElementById("TxtKursBeliEdit").value = _item.currencyIDR;
								document.getElementById("DateTerimaEdit").value = ($.datepicker.formatDate('d MM yy',new Date(_item.dateReceive)));
								document.getElementById("TxtCostTripEdit").value = _item.totCost;
								document.getElementById("TxtDiscTripEdit").value = _item.totDisc;
								
	        				}
	        			}
	        			
	        		}
					
					var tripEditCall = function() {
						var _recid = document.getElementById("myEditTrip");
						var _dataId= _recid.getAttribute("data-id");
						var _data={};
						_data['id'] = _dataId;
						_data['trip_date']=$('#DateTripEdit').datepicker('getDate').getTime();
						_data['trip_numb']=$('#TxtNoTripEdit').val().trim();
						//_data['trip_dateNote']=$('#DateNotaEdit').datepicker('getDate').getTime();
						//_data['trip_noteNumber']=$('#TxtNoNotaEdit').val().trim();
						_data['vendorEnt']=$('#TxtIdVendorEdit').val().trim();
						_data['currencyIDR']=$('#TxtKursBeliEdit').val().trim();
						_data['dateReceive']=$('#DateTerimaEdit').datepicker('getDate').getTime();
						_data['cost']=$('#TxtCostTripEdit').val();
						_data['disc']=$('#TxtDiscTripEdit').val();
						
						JSON.post(_data,'${ctx }/json/trip-mod',10000,tripEdit,null,null);
					};
					
					/* END UPDATE DATA MODAL asdasdasd */
					
					/* DELETE DATA */
		       		var tripRemoveCall = function(_id) {
		       			if(confirm("Do you want to delete ?")) {
		       			    this.click;
						var _data={};
						_data['id'] = _id;
						JSON.post(_data,'${ctx }/json/trip-del',10000,tripDelete,null,null);
						
		       	 		}
					else {
						
					return;
					}
		       		};
		       		/*End delete*/
		       		</script>

          			<script type="text/javascript">
          		
          			var tripAddSent = function(data){
						
					var _items = data.items;
		       		for ( var i = 0; i < _items.length; i++) {
		        		$('#tbdTrip').append(
		        			$('<tr><\/tr>')
		        			.append($('<td><\/td>').html(_item.trip_numb))
							.append($('<td><\/td>').html($.datepicker.formatDate('d MM yy',new Date(_item.trip_date))))
							/* .append($('<td><\/td>').html(_item.vendId)) */
							.append($('<td><\/td>').html(_item.trip_noteNumber))
							.append($('<td><\/td>').html($.datepicker.formatDate('d MM yy',new Date(_item.trip_dateNote))))
							.append($('<td><\/td>').html(_item.trip_vendName))
							.append($('<td><\/td>').html(_item.trip_vendCountry))
							.append($('<td><\/td>').html(_item.trip_vta))
							.append($('<td><\/td>').html(_item.currencyIDR))
							.append($('<td><\/td>').html($.datepicker.formatDate('d MM yy',new Date(_item.dateReceive))))
							
		        				);
		        				 
		        			} 
		        			
		        		};
		        		
		        		
		          		var tripAddCall = function() {
							var _data={};
							
							_data['trip_date']=$('#DateTrip').datepicker('getDate').getTime();
							_data['trip_numb']=$('#TxtNoTrip').val().trim();
							//_data['trip_dateNote']=$('#DateNota').datepicker('getDate').getTime();
							//_data['trip_noteNumber']=$('#TxtNoNota').val().trim();
							_data['vendorEnt']=$('#TxtIdVendor').val().trim();
							_data['currencyIDR']=$('#TxtKursBeli').val();
							_data['cost']=$('#TxtCostTrip').val();
							_data['disc']=$('#TxtDiscTrip').val();
							
							_data['dateReceive']=$('#DateTerima').datepicker('getDate').getTime();
						/* 	_data['harga'] = $('#TxtKursBeli').val(); */
							JSON.post(_data,'${ctx }/json/trip-add',10000,tripAdd,null,null);
						};
          		
						
						/* Start Paging */
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
							JSON.post(_data,'${ctx }/json/getPageTrip',10000,tripSent,null,null);
							/* setPagging("page"+obj.value,"",""); */
						}
						
						/* End Paging Barang */
						
						var tripSent = function(data) {
							
							document.getElementById("tbdTrip").innerHTML = "";
	          				if(data.page){
	          					document.getElementById("totPage").innerHTML = data.page;
	          					setPagging("1",data.page,"1"); 
	          					_customPage = "";
	          					
	          				}
							
		        			var _items = data.items;
		        			var _valNext = '';
		        			$('#tbdTrip').empty();
		        			for ( var i = 0; i < _items.length; i++) {
		        				var _item = _items[i];
		        				var _id = "'"+_item.id+"'";
		        				var _vendId = "'"+_item.vendId+"'";
		        				var _numb = "'"+_item.trip_numb+"'";
		        				var _tDate = "'"+($.datepicker.formatDate('d MM yy',new Date(_item.trip_date)))+"'";
		        				var _tNoteNumb = "'"+_item.trip_noteNumber+"'";
		        				var _tNoteDate = "'"+($.datepicker.formatDate('d MM yy',new Date(_item.trip_dateNote)))+"'";
		        				var _vName = "'"+_item.trip_vendName+"'";
		        				var _vCountry = "'"+_item.trip_vendCountry+"'";
		        				var _vVta = "'"+_item.trip_vta+"'";
		        				var _currencyIdr = "'"+_item.currencyIDR+"'";
		        				var _dateReceive = "'"+($.datepicker.formatDate('d MM yy',new Date(_item.dateReceive)))+"'";
		        				var _pembelianValue = "'"+"trip_numb"+"'";
		        				var _totDisc = "'"+_item.totDisc+"'";
		        				var _totCost = "'"+_item.totCost+"'";
		        				var rupiahtotBeliBrutoIdr = convertToMoney(_item.totBeliBrutoIdr);
		        				var rupiahtotBeliBrutoVta = convertToMoney(_item.totBeliBrutoVta);
		        				var rupiahtotBeliNettoIdr = convertToMoney(_item.totBeliNettoIdr);
		        				var rupiahtotBeliNettoVta = convertToMoney(_item.totBeliNettoVta);
		        				var rupiahtotBeliCtn = convertToItem(_item.totBeliCtn);
		        				var rupiahcurrency = convertToMoney(_item.currencyIDR);
		        				// paggin umpet
		        				if(i !=10){
		        				$('#tbdTrip').append(
		        						$('<tr ><\/tr>')
		        							.append($('<td><button id="pushModalButtonAddPembelian" type="button" onclick="autoInputBeli('+_id+','+_vVta+','+_currencyIdr+','+_totCost+','+_totDisc+','+_numb+','+_tDate+')" data-toggle="modal" data-target="#myModalAddPembelian" class="btn btn-primary btn-xs">Add Beli<\/button>-<button type="button" onclick="pembelianCall('+_pembelianValue+','+_numb+')" data-toggle="modal" data-target="#myModalCallPembelian" class="btn btn-danger btn-xs" class="btn btn-danger btn-xs">Lihat<\/button><\/td>'))
											.append($('<td><button type="button" value="'+_item.trip_numb+'" onclick="jsonGetArrayToPrint(this)" class="btn btn-danger btn-xs" class="btn btn-danger btn-xs">Cetak<\/button><\/td>'))
											.append($('<td><\/td>').html(_item.trip_numb))
		        							.append($('<td><\/td>').html($.datepicker.formatDate('d MM yy',new Date(_item.trip_date))))
		        							//.append($('<td><\/td>').html(_item.trip_noteNumber))
		        							//.append($('<td><\/td>').html($.datepicker.formatDate('d MM yy',new Date(_item.trip_dateNote))))
		        							.append($('<td><\/td>').html(_item.trip_vendName))
		        							//.append($('<td><\/td>').html(_item.trip_vendCountry))
		        							.append($('<td><\/td>').html(rupiahtotBeliCtn))
		        							//.append($('<td><\/td>').html(_item.trip_vta))
		        							.append($('<td><\/td>').html(_item.trip_vta+":"+rupiahtotBeliBrutoVta))
		        							.append($('<td><\/td>').html(rupiahcurrency))
		        							.append($('<td><\/td>').html(rupiahtotBeliBrutoIdr))
		        							.append($('<td><\/td>').html(_item.totCost+"%"))
		        							.append($('<td><\/td>').html(_item.totDisc+"%"))
		        							.append($('<td><\/td>').html(_item.trip_vta+":"+rupiahtotBeliNettoVta))
		        							.append($('<td><\/td>').html("Rp"+rupiahtotBeliNettoIdr))
		        							//.append($('<td><\/td>').html($.datepicker.formatDate('d MM yy',new Date(_item.dateReceive))))
		        							
		        							.append($('<td><button id="pushEditTripButton" type="button" onclick="pushEditTrip('+_id+','+_numb+','+_tDate+','+_tNoteDate+','+_tNoteNumb+','+_vendId+','+_vName+','+_vCountry+','+_vVta+','+_currencyIdr+','+_dateReceive+','+_totDisc+','+_totCost+')" data-toggle="modal" data-target="#myEditTrip" class="btn btn-primary btn-xs">Edit<\/button> - <button type="button" onclick="tripRemoveCall('+_id+');" class="btn btn-danger btn-xs" class="btn btn-danger btn-xs">Delete<\/button><\/td>'))
			        					);
		        				}
		        				// paggin umpet
		        			}
		        			
		        		};
		        		
		        		var tripCall = function() {
		        			var _data={};
		        			_data["dateFrom"] = $('#dateFrom').val().trim();
		        			_data["dateTo"] = $('#dateTo').val().trim();
		        			_data["tripNumb"] = $('#tripno').val().trim();
							JSON.post(_data,'${ctx }/json/trip',10000,tripSent,null,null);
							 var _pageTot = tripSent.page; 
							
						};
						var tripPrint = function() {
		        			var _data={};
		        			_data["dateFrom"] = $('#dateFrom').val().trim();
		        			_data["dateTo"] = $('#dateTo').val().trim();
		        			_data["actiontype"] = "DOWNLOADEXCEL";
		        			_data["tripNumb"] = $('#tripno').val().trim();
							JSON.post(_data,'${ctx }/json/trip',100000,tripSentPrint,null,null);
							 var _pageTot = tripSent.page; 
							
						};
						var tripSentPrint = function(data) {
							var _mes = data.message;
							if(_mes == "Kode Customer tidak ditemukan"){
								alert(_mes);
								return;
							}
							window.location.replace('../img-sk/'+_mes);
						};
						var tripAdd = function(data){
							
							if(data.code == 0){
								alert(data.message);
								$('#myModalAddTrip').modal('hide');
								tripCall();
								
								}
								if (data.code != 0) {
									
									alert(data.message);
								}
								else {
									
								}
								};
								
						var tripEdit = function(data){
							if(data.code == 0){
								alert(data.message);
								$('#myEditTrip').modal('hide');
								tripCall();
										
								}
								if (data.code != 0) {
											
									alert(data.message);
								}
								else {
											
									}
										
								};
						var tripDelete = function(data){
							if(data.code == 0){
								alert(data.message);
								tripCall();
												
								}
								
								else {
													
									}
												
								};		
						tripCall();
					</script>
		
		
			<!-- Tabel Vendor -->
					<script type="text/javascript">
						function autoInput(a,b,c,d){
							document.getElementById("TxtNamaVendor").value = a;
							document.getElementById("TxtNegara").value = b;
							document.getElementById("TxtVta").value = c;
							document.getElementById("TxtIdVendor").value = d;
							document.getElementById("TxtNamaVendorEdit").value = a;
							document.getElementById("TxtNegaraEdit").value = b;
							document.getElementById("TxtVtaEdit").value = c;
							document.getElementById("TxtIdVendorEdit").value = d;
							$('#myModalVendor').modal('hide');
							//document.getElementById("TxtNegara").value = b;
						}
						function firstPage(obj){
			       			var _selPage = document.getElementById("selectPage");
			       			_selPage.value = 0;
			       			getPage(_selPage);
			       		}
			       		function lastPageVen(obj){
			       			var _selPage = document.getElementById("selectPageVen");
			       			var _totPage = document.getElementById("totPageVen").innerHTML *1; 
			       			_selPage.value = _totPage-1;
			       			getPageVen(_selPage);
			       		}
			       		function nextPageVen(obj){
			       			var _selPage = document.getElementById("selectPageVen");
			       			var _currentPage = _selPage.value;
			       			var _totPage = document.getElementById("totPageVen").innerHTML *1; 
			       			if(_totPage == 1){
			       				return;
			       			}
			       			var _setNext = _currentPage*1+1;
			       			if(_setNext <=_totPage-1){
			       				_selPage.value = _setNext;
			           			getPageVen(_selPage);
			       			}
			       		}
						function prevPageVen(obj){
			       			var _selPage = document.getElementById("selectPageVen");
			       			var _currentPage = _selPage.value;
			       			var _totPage = document.getElementById("totPageVen").innerHTML *1; 
			       			if(_totPage == 1){
			       				return;
			       			}
			       			var _setPrev = _currentPage*1-1;
			       			if(_setPrev >=0){
			       				_selPage.value = _setPrev;
			           			getPageVen(_selPage);
			       			}
			       		}
						function getPageVen(obj){
							var _data={};
							_data['page'] = "page"+obj.value;
							JSON.post(_data,'${ctx }/json/getPageVendor',10000,vendorSent,null,null);
							/* setPagging("page"+obj.value,"",""); */
						}
						var vendorSent = function(data) {
							document.getElementById("tbdVendor").innerHTML = "";
	          				if(data.page){
	          					document.getElementById("totPageVen").innerHTML = data.page;
	          					_customPage = "Ven";
	          					 setPagging("1",data.page,"1");
	          				}
							var _items = data.items;
							$('#tbdVendor').empty();
							for ( var i = 0; i < _items.length; i++) {
								var _item = _items[i];
								//var _tot=_item.hpp*_item.kurs;
								//.html(_tot);
								var _tr = document.createElement("tr");
									_tr.setAttribute("onclick", "autoInput('"+_item.name+"','"+_item.country+"','"+_item.vta+"','"+_item.id+"')");
								$('#tbdVendor').append(
								$(_tr)
									.append($('<td class="hidden"><\/td>').html(_item.id))
									.append($('<td><\/td>').html(_item.name))
									.append($('<td><\/td>').html(_item.country))
									.append($('<td><\/td>').html(_item.vta))
									.append($('<td><\/td>').html(_item.address1))
									.append($('<td><\/td>').html(_item.address2))
									.append($('<td><\/td>').html(_item.city))
									.append($('<td><\/td>').html(_item.phone))
									.append($('<td><\/td>').html(_item.fax))
									.append($('<td><\/td>').html(_item.email))
									.append($('<td><\/td>').html(_item.taxNumber))
									.append($('<td><\/td>').html(_item.cargo))
							);
							}
							};
						var vendorCall = function() {
						var _data={};
						_data['queryData']=$('#inpQuery').val().trim();
						_data['byValue']=$('#selByValue').val().trim();
						JSON.post(_data,'${ctx }/json/vendor',10000,vendorSent,null,null);
						};
						
					</script>
		
			
		
					<script type="text/javascript">
          			/*Date Picker*/
          			$(function(){
          				$('#DateTrip,#DateNota,#DateTerima,#DateTripEdit,#DateNotaEdit,#DateTerimaEdit,#dateFromTrip,#dateToTrip').datepicker().datepicker("setDate", new Date());
          			});
          			$(function(){
          				$('#dateFrom,#dateTo,#TxtDateTripBeli').datepicker();
          			});
          			
        			</script>
          	
          			<script type="text/javascript">
					$(document).ready(function(){
					/* Hide Start */
     					$("#hide").click(function(){
        					$("#searchTrip").addClass("hidden");
    					}); 
	
     					$("#hideSearchVend").click(function(){
         					$("#searchVend").addClass("hidden");        
     					}); 
    				/* Hide End */
    
    				/* Show Start */
    					$("#show").click(function(){
        					$("#searchTrip").removeClass("hidden");      
    					});
    					$("#showSearchVend").click(function(){
        					$("#searchVend").removeClass("hidden");        
    					}); 
    				/* Show End */
   
						});

					</script>  
					
					<script type="text/javascript">
			
			/* DELETE DATA */
			
       		var pembelianRemoveCall = function(_id,_idStock,_idTrip) {
       			if(confirm("Do you want to delete ?")) {
       			    this.click;
				var _data={};
				 _data['id'] = _id;
				 _data['stockEnt'] = _idStock;
				 _data['tripEnt'] = _idTrip;
				
				JSON.post(_data,'${ctx }/json/detpembelian-del',10000,pembelianDelete,null,null);
				
       	 		}
			else {
				
			return;
			}
       			
       		};
       		/*End delete*/
       		</script>
          <script type="text/javascript">
          /* UPDATE DATA MODAL */
			function pushEditBeli(_id,_idTrip,_vendVta,_tripNumber,_tripDate,_tripKurss,_idStock,_productCode,_productName,_isiCtn,_satIsiCtn,_isiPcs,_stokCtn,_totQtyBeliCtn,_cost,_disc,_hargaJualPcs,_hargaJualPcsVta,_hargaJualCtnVta,_totHargaBrutBeliPcsIdr,_totHargaBrutBeliPcsVta,_totHargaNettBeliPcsIdr,_totHargaBrutBeliPcsVta,_totHargaBrutBeliIdr,_hargaBeliCtnVta,_hargaBeliCtnVtaNetto,_hargaBeliPcsVtaNetto,_hargaBeliPcsIdrNetto,_totHargaNettBeliIdr,_totHargaNettBeliVta,_profitIdr){
  			_itemId = _id;
  			var _data={};
  			
  				var d = document.getElementById("myModalEditPembelian");  //   Javascript
  				d.setAttribute('data-id' , _itemId); //
  				document.getElementById("TxtIdTripEdit").value = _idTrip;
  				document.getElementById("TxtValutaBeliEdit").value = _vendVta;
  				document.getElementById("TxtValutaBeli1Edit").value = _vendVta;
  				document.getElementById("TxtValutaBeli2Edit").value = _vendVta;
  				document.getElementById("TxtNoTripBeliEdit").value = _tripNumber;
  				document.getElementById("TxtDateTripBeliEdit").value = _tripDate;
				document.getElementById("TxtKursBeliTripEdit").value = _tripKurss;
				document.getElementById("TxtIdStockEdit").value = _idStock;
				document.getElementById("TxtKodeProductEdit").value = _productCode;
				document.getElementById("TxtNamaProductEdit").value = _productName;
				document.getElementById("TxtIsiCtnEdit").value = _isiCtn;
				document.getElementById("TxtSatIsiCtnEdit").value = _satIsiCtn;
				document.getElementById("TxtIsiPcsEdit").value = _isiPcs;
				document.getElementById("TxtStockCtnEdit").value = _stokCtn;
				document.getElementById("TxtQtyBeliEdit").value =_totQtyBeliCtn ;
				document.getElementById("TxtCostEdit").value = _cost;
				document.getElementById("TxtDiscEdit").value = _disc;
				
				document.getElementById("TxtHBEdit").value = _totHargaBrutBeliPcsVta;
				document.getElementById("TxtHBperCartEdit").value = _hargaBeliCtnVta;
				document.getElementById("TxtHBperCartNettoEdit").value = _hargaBeliCtnVtaNetto;
				document.getElementById("TxtHBNettoEdit").value = _hargaBeliPcsVtaNetto;
				document.getElementById("TxtHbIdrEdit").value = _hargaBeliPcsIdrNetto;
				
				//document.getElementById("TxtHbIdrEdit").value = _totHargaNettBeliIdr;
				document.getElementById("TxtHJRPEdit").value = _hargaJualPcs;
				document.getElementById("TxtProfitEdit").value = _profitIdr;
				
				
				/* JSON.post(_data,'${ctx }/json/trip',10000,tripModalSent,null,null); */
  		}
  		
  		var pembelianEditSent = function(data) {
  			var recid = document.getElementById("myModalEditPembelian");
				var _dataId= recid.getAttribute("data-id");
  			var _items = data.items;
  			
  			for (var i = 0; i < _items.length; i++){
  				var _item = _items[i];
  				if (_item.id == _dataId) {
						
  					
						document.getElementById("TxtIdTripEdit").value = ($.datepicker.formatDate('d MM yy',new Date(_idTrip)));
		  				document.getElementById("TxtValutaBeliEdit").value = _vendVta;
		  				document.getElementById("TxtNoTripBeliEdit").value = _tripNumber;
		  				document.getElementById("TxtDateTripBeliEdit").value = _tripDate;
						document.getElementById("TxtKursBeliTripEdit").value = _tripKurss;
						document.getElementById("TxtIdStockEdit").value = _idStock;
						document.getElementById("TxtKodeProductEdit").value = _productCode;
						document.getElementById("TxtNamaProductEdit").value = _productName;
						document.getElementById("TxtIsiCtnEdit").value = _isiCtn;
						document.getElementById("TxtSatIsiCtnEdit").value = _satIsiCtn;
						document.getElementById("TxtIsiPcsEdit").value = _isiPcs;
						document.getElementById("TxtStockCtnEdit").value = _stokCtn;
						document.getElementById("TxtQtyBeliEdit").value = _totQtyBeliCtn;
						document.getElementById("TxtCostEdit").value = _cost;
						document.getElementById("TxtDiscEdit").value = _disc;
						
  				}
  			}
  			
  		}
			
			var pembelianEditCall = function() {
				var _recid = document.getElementById("myModalEditPembelian");
				var _dataId= _recid.getAttribute("data-id");
				var _data={};
				_data['id'] = _dataId;
				_data['tripEnt']=$('#TxtIdTripEdit').val().trim();
				_data['tripNumb']=$('#TxtNoTripBeliEdit').val().trim();
				_data['tripDate']=$('#TxtDateTripBeliEdit').datepicker('getDate').getTime();
				_data['stockEnt']=$('#TxtIdStockEdit').val().trim();
				_data['qtyBeliCtn']=$('#TxtQtyBeliEdit').val().trim();
				_data['hargaBPcsVta']=$('#TxtHBEdit').val();
				_data['hargaJPcs']=$('#TxtHJRPEdit').val();
				_data['cost']=$('#TxtCostEdit').val();
				_data['disc']=$('#TxtDiscEdit').val();
				
				
				JSON.post(_data,'${ctx }/json/detpembelian-mod',10000,pembelianEdit,null,null);
			};
          /* Add pembelian */
          function autoInputBeli(a,b,c,d,e,f,g){
				document.getElementById("TxtIdTrip").value = a;
				document.getElementById("TxtValutaBeli").value = b;
				document.getElementById("TxtValutaBeli1").value = b;
				document.getElementById("TxtValutaBeli2").value = b;
				document.getElementById("TxtKursBeliTrip").value = c;
				document.getElementById("TxtCost").value = d;
				document.getElementById("TxtDisc").value = e;
				document.getElementById("TxtNoTripBeli").value = f;
				document.getElementById("TxtDateTripBeli").value = g;
				
				document.getElementById("TxtIdStock").value = "";
				document.getElementById("TxtKodeProduct").value = "";
				document.getElementById("TxtNamaProduct").value = "";
				document.getElementById("TxtIsiCtn").value = "0";
				document.getElementById("TxtIsiPcs").value = "0";
				document.getElementById("TxtStockCtn").value = "0";
				document.getElementById("TxtQtyBeli").value = "0";
				document.getElementById("TxtHB").value = "0";
				document.getElementById("TxtHBperCart").value = "0";
				document.getElementById("TxtHBNetto").value = "0";
				document.getElementById("TxtHBperCartNetto").value = "0";
				document.getElementById("TxtHbIdr").value = "0";
				document.getElementById("TxtHJRP").value = "0";
				document.getElementById("TxtProfit").value = "0";
				
				
            }
          	var pembelianAddSent = function(data){
    			var _items = data.items;
  				for ( var i = 0; i < _items.length; i++) {
  					var _item = _items[i];
  					var _id = "'"+_item.id+"'";
  					var _dataItem = "'"+_item+"'";
  					//var _tot=_item.hpp*_item.kurs;
  					//.html(_tot);
  						$('#tbdPembelian').append(
  							$('<tr><\/tr>')
  								//.append($('<td><\/td>').html(_item.productImage))
  								.append($('<td><\/td>').html(_item.tripNumber))
        							.append($('<td><\/td>').html($.datepicker.formatDate('d MM yy',new Date(_item.tripDate))))
        							.append($('<td><\/td>').html(_item.productCode))
        							.append($('<td><\/td>').html(_item.productName))
        							.append($('<td><\/td>').html(_item.totQtyBeliPcs))
        							.append($('<td><\/td>').html(_item.disc))
        							.append($('<td><\/td>').html(_item.vendVta))
        							.append($('<td><\/td>').html(_item.hargaNetBeliIsiCtnVta))
        							.append($('<td><\/td>').html(_item.tripKurs))
        							.append($('<td><\/td>').html(_item.hargaNetBeliIsiCtnIdr))
  							);
  				 
  						} 
  			
  						};
  		
  		
    				var pembelianAddCall = function() {
						var _data={};
						//_data['image']=$('#FileImgBarang').val().trim();
						_data['tripEnt']=$('#TxtIdTrip').val().trim();
						_data['tripNumb']=$('#TxtNoTripBeli').val().trim();
						_data['tripDate']=$('#TxtDateTripBeli').datepicker('getDate').getTime();
						_data['stockEnt']=$('#TxtIdStock').val().trim();
						_data['qtyBeliCtn']=$('#TxtQtyBeli').val().trim();
						_data['hargaBPcsVta']=$('#TxtHB').val();
						_data['hargaJPcs']=$('#TxtHJRP').val();
						_data['cost']=$('#TxtCost').val();
						_data['disc']=$('#TxtDisc').val();
						_data["RetailORDept"] = $('input[name=RadTotStock]:checked').val();
						JSON.post(_data,'${ctx }/json/detpembelian-add',10000,pembelianAdd,null,null);
					};
					/* Start Paging Pembelian */
					
					function firstPageBeli(obj){
       			var _selPage = document.getElementById("selectPageBeli");
       			_selPage.value = 0;
       			getPageBeli(_selPage);
       		}
       		function lastPageBeli(obj){
       			var _selPage = document.getElementById("selectPageBeli");
       			var _totPage = document.getElementById("totPageBeli").innerHTML *1; 
       			_selPage.value = _totPage-1;
       			getPageBeli(_selPage);
       		}
       		function nextPageBeli(obj){
       			var _selPage = document.getElementById("selectPageBeli");
       			var _currentPage = _selPage.value;
       			var _totPage = document.getElementById("totPageBeli").innerHTML *1; 
       			if(_totPage == 1){
       				return;
       			}
       			var _setNext = _currentPage*1+1;
       			if(_setNext <=_totPage-1){
       				_selPage.value = _setNext;
           			getPageBeli(_selPage);
       			}
       		}
			function prevPageBeli(obj){
       			var _selPage = document.getElementById("selectPageBeli");
       			var _currentPage = _selPage.value;
       			var _totPage = document.getElementById("totPageBeli").innerHTML *1; 
       			if(_totPage == 1){
       				return;
       			}
       			var _setPrev = _currentPage*1-1;
       			if(_setPrev >=0){
       				_selPage.value = _setPrev;
           			getPageBeli(_selPage);
       			}
       		}
			
				function getPageBeli(obj){
					var _data={};
					_data['page'] = "page"+obj.value;
					JSON.post(_data,'${ctx }/json/getPageDetPembelian',1000000,pembelianSent,null,null);
					/* setPagging("page"+obj.value,"",""); */
					}
				var pembelianSent = function(data) {
					document.getElementById("tbdPembelian").innerHTML = "";
  					if(data.page){
  						document.getElementById("totPageBeli").innerHTML = data.page;
  						_customPage = "Beli";
  						 setPagging("1",data.page,"1");
  					}
        				var _items = data.items;
        				$('#tbdPembelian').empty();
        				for ( var i = 0; i < _items.length; i++) {
        				var _item = _items[i];
        				var _id = "'"+_item.id+"'";
        				var _idStock = "'"+_item.idStock+"'";
        				var _idTrip = "'"+_item.idTrip+"'";
        				var _vendVta = "'"+_item.vendVta+"'";
        				var _tripNumber = "'"+_item.tripNumber+"'";
        				var _tripDate = "'"+($.datepicker.formatDate('d MM yy',new Date(_item.tripDate)))+"'";
        				var _tripKurss = "'"+_item.tripKurs+"'";
        				var _productCode = "'"+_item.productCode+"'";
        				var _productName = "'"+_item.productName+"'";
        				var _isiCtn = "'"+_item.isiCtn+"'";
        				var _isiPcs = "'"+_item.isiPcs+"'";
        				var _cost = "'"+_item.cost+"'";
        				var _disc = "'"+_item.disc+"'";
        				var _satIsiCtn = "'"+_item.satIsiCtn+"'";
        				var _totQtyBeliCtn = "'"+_item.totQtyBeliCtn+"'";
        				var _hargaJualPcs= "'"+_item.hargaJualPcs+"'";
        				var _hargaJualCtn= "'"+_item.hargaJualCtn+"'";
        				var _hargaJualPcsVta= "'"+_item.hargaJualPcsVta+"'";
        				var _hargaJualCtnVta= "'"+_item.hargaJualCtnVta+"'";
        				var _totHargaBrutBeliPcsIdr = "'"+_item.totHargaBrutBeliPcsIdr+"'";
        				var _totHargaBrutBeliPcsVta = "'"+_item.totHargaBrutBeliPcsVta+"'";
        				var _totHargaNettBeliPcsIdr = "'"+_item.totHargaNettBeliPcsIdr+"'";
        				var _totHargaBrutBeliPcsVta= "'"+_item.totHargaBrutBeliPcsVta+"'";
        				var _totHargaBrutBeliIdr = "'"+_item.totHargaBrutBeliIdr+"'";
        				var _totHargaBrutBeliVta = "'"+_item.totHargaBrutBeliVta+"'";
        				var _totHargaNettBeliIdr = "'"+_item.totHargaNettBeliIdr+"'";
        				var _totHargaNettBeliVta= "'"+_item.totHargaNettBeliVta+"'";
        				var _hargaBeliCtnVta = "'"+_item.hargaBeliCtnVta+"'";
        				var _hargaBeliCtnVtaNetto = "'"+_item.hargaBeliCtnVtaNetto+"'";
        				var _hargaBeliPcsVtaNetto = "'"+_item.hargaBeliPcsVtaNetto+"'";
        				var _hargaBeliPcsIdrNetto = "'"+_item.hargaBeliPcsIdrNetto+"'";
        				var _profitIdr = "'"+_item.profitIdr+"'";
        				var _totQtyBeliCtnConv = convertToItem(_item.totQtyBeliCtn);
        				var _stokCtnConv = convertToItem(_item.stokCtn);
        				var _totQtyBeliCtn = "'"+_item.totQtyBeliCtn+"'";
        				var _stokCtn = "'"+_item.stokCtn+"'";
        				var _totNettBeliVta = convertToMoney(_item.totHargaNettBeliVta);
        				var _totNettBeliIdr = convertToMoney(_item.totHargaNettBeliIdr);
        				var _tripKurs = convertToMoney(_item.tripKurs);
        				
        				
        				//var _tot=_item.hpp*_item.kurs;
        				//.html(_tot);
        				$('#tbdPembelian').append(
        						$('<tr><\/tr>')
        							.append($('<td><\/td>').html(_item.tripNumbStok))
        							.append($('<td><\/td>').html(_item.productCode))
        							.append($('<td><\/td>').html(_item.productName))
        							.append($('<td><\/td>').html(_totQtyBeliCtnConv+" CTN"))
        							//.append($('<td><\/td>').html(_stokCtnConv))
        							.append($('<td><\/td>').html(_item.disc+" %"))
        							.append($('<td><\/td>').html(_item.vendVta+":"+_totNettBeliVta))
        							.append($('<td><\/td>').html(_tripKurs))
        							.append($('<td><\/td>').html("Rp "+_totNettBeliIdr))
        							.append($('<td><button id="pushEditBeliButton" type="button" onclick="pushEditBeli('+_id+','+_idTrip+','+_vendVta+','+_tripNumber+','+_tripDate+','+_tripKurss+','+_idStock+','+_productCode+','+_productName+','+_isiCtn+','+_satIsiCtn+','+_isiPcs+','+_stokCtn+','+_totQtyBeliCtn+','+_cost+','+_disc+','+_hargaJualPcs+','+_hargaJualPcsVta+','+_hargaJualCtnVta+','+_totHargaBrutBeliPcsIdr+','+_totHargaBrutBeliPcsVta+','+_totHargaNettBeliPcsIdr+','+_totHargaBrutBeliPcsVta+','+_totHargaBrutBeliIdr+','+_hargaBeliCtnVta+','+_hargaBeliCtnVtaNetto+','+_hargaBeliPcsVtaNetto+','+_hargaBeliPcsIdrNetto+','+_totHargaNettBeliIdr+','+_totHargaNettBeliVta+','+_profitIdr+')" data-toggle="modal" data-target="#myModalEditPembelian" class="btn btn-primary btn-xs">Edit<\/button> - <button type="button" onclick="pembelianRemoveCall('+_id+','+_idStock+','+_idTrip+');" class="btn btn-danger btn-xs"><i class="fa fa-trash-o "><\/i><\/button><\/td>'))
        							
        								
        					);
        				}
        			};
        			
          			/* var pembelianCall = function(_id,_numb) {
					var _data={};
					_data["dateFrom"] = $('#dateFromBeli').val().trim();
        			_data["dateTo"] = $('#dateToBeli').val().trim();
					JSON.post(_data,'${ctx }/json/detpembelian',10000,pembelianSent,null,null);
					 var _pageTot = pembelianSent.page; 
					 
					}; */
					
					var pembelianCall = function(_pembelianValue,_numb) {
	          			var _data={};
	          			
	          			_data['queryDataBeli']=_numb;
						_data['byValueBeli']= _pembelianValue;
						JSON.post(_data,'${ctx }/json/detpembelian',10000,pembelianSent,null,null);
	        		};
					
					var pembelianAdd = function(data){
						
						if(data.code == 0){
							alert(data.message);
							$('#myModalAddPembelian').modal('hide');
							tripCall();
							pembelianCall();
							
							}
							if (data.code != 0) {
								
								alert(data.message);
							}
							
							};
					var pembelianEdit = function(data){
								
						if(data.code == 0){
							alert(data.message);
							$('#myModalEditPembelian').modal('hide');
							var _items = data.items;
							var _item = _items[0];
							pembelianCall('trip_numb',_item.tripNumber);
							tripCall();			
							}
							if (data.code != 0) {
										
								alert(data.message);
							}
									
							};	
									
					var pembelianDelete = function(data){
						if(data.code == 0){
							alert(data.message);
							//$('#myModalCallPembelian').modal('hide');
							var _items = data.items;
							var _item = _items[0];
							pembelianCall(_item.trip_numb,'trip_numb');
							tripCall();			
							}
							if (data.code != 0) {
							
								alert(data.message);
							}
							
							};
					
				
				</script>
		
		
			<!--Start Call Data Barang -->
			
			<script type="text/javascript">
				function autoInputProd(a,b,c,d,e,f,g,h,i){
					document.getElementById("TxtIdStock").value = a;
					document.getElementById("TxtKodeProduct").value = b;
					document.getElementById("TxtNamaProduct").value = c;
					document.getElementById("TxtIsiCtn").value = d;
					document.getElementById("TxtSatIsiCtn").value = e;
					document.getElementById("TxtIsiPcs").value = f;
					document.getElementById("TxtStockCtn").value = g;
					document.getElementById("TxtStockRetailCtn").value = h;
					document.getElementById("TxtStockDeptStoreCtn").value = i;
					
					document.getElementById("TxtIdStockEdit").value = a;
					document.getElementById("TxtKodeProductEdit").value = b;
					document.getElementById("TxtNamaProductEdit").value = c;
					document.getElementById("TxtIsiCtnEdit").value = d;
					document.getElementById("TxtSatIsiCtnEdit").value = e;
					document.getElementById("TxtIsiPcsEdit").value = f;
					document.getElementById("TxtStockCtnEdit").value = g;
					
					$('#myModalProduct').modal('hide');
					//document.getElementById("TxtNegara").value = b;
					}
						
				function getPageProd(obj){
					var _data={};
					_data['page'] = "page"+obj.value;
					JSON.post(_data,'${ctx }/json/getPageStock',10000,productSent,null,null);
					/* setPagging("page"+obj.value,"",""); */
					}
					var productSent = function(data) {
					document.getElementById("tbdProduct").innerHTML = "";
	          		if(data.page){
	          		document.getElementById("totPageProd").innerHTML = data.page;
	          		_customPage = "Prod";
	          		setPagging("1",data.page,"1");
	          		}
	          		var _items = data.items;
					$('#tbdProduct').empty();
					for ( var i = 0; i < _items.length; i++) {
						var _item = _items[i];
						//var _tot=_item.hpp*_item.kurs;
						//.html(_tot);
						var _tr = document.createElement("tr");
							_tr.setAttribute("onclick", "autoInputProd('"+_item.stockId+"','"+_item.productCode+"','"+_item.productName+"','"+_item.isiCtn+"','"+_item.satIsiCtn+"','"+_item.isiPcs+"','"+_item.stokCtn+"','"+_item.stokIsiCtnRetail+"','"+_item.stokIsiCtnDeptStore+"')");
						$('#tbdProduct').append(
						$(_tr)
    						.append($('<td class="hidden"><\/td>').html(_item.id))
    						.append($('<td><\/td>').html(_item.productBarcode))
							.append($('<td><\/td>').html(_item.productCode))
							.append($('<td><\/td>').html(_item.productName))
							.append($('<td><\/td>').html(_item.productMerk))
							.append($('<td><\/td>').html(_item.productGroup))
							.append($('<td><\/td>').html(_item.productMadeIn))
							.append($('<td><\/td>').html(_item.productPartNumb))
							.append($('<td><\/td>').html(_item.productStandartNo))
							.append($('<td><\/td>').html(_item.stokCtn))
							//.append($('<td class="hidden"><\/td>').html(_item.isiCtn))
							//.append($('<td class="hidden"><\/td>').html(_item.satIsiCtn))
							//.append($('<td class="hidden"><\/td>').html(_item.isiPcs))
							);
						}
					};
					
					var productCall = function() {
						var _data={};
						_data['queryData']=$('#inpQueryProd').val().trim();
						_data['byValue']=$('#selByValueProd').val().trim();
						JSON.post(_data,'${ctx }/json/stock',10000,productSent,null,null);
						 var _pageTot = productSent.page; 
					};
				</script>
      
			<!-- Auto calculation -->
			<script type="text/javascript">
			
			$('#TxtHB, #TxtIsiPcs, #TxtIsiCtn').on('input',function() {
			var TxtHB = parseFloat($('#TxtHB').val());
			var TxtIsiCtn = parseInt($('#TxtIsiCtn').val());
			var TxtIsiPcs = parseInt($('#TxtIsiPcs').val());
			$('#TxtHBperCart').val((TxtHB * TxtIsiCtn * TxtIsiPcs  ?  TxtHB * TxtIsiCtn * TxtIsiPcs : 0).toFixed(2));
			});
			
			$('#TxtHB, #TxtDisc, #TxtCost').on('input',function() {
			var TxtHB = parseFloat($('#TxtHB').val());
			var TxtDisc = parseFloat($('#TxtDisc').val());
			var TxtCost = parseFloat($('#TxtCost').val());
			$('#TxtHBNetto').val((TxtHB -(TxtHB*(TxtDisc / 100))+((TxtHB -(TxtHB*(TxtDisc / 100)))*(TxtCost / 100))  ?  TxtHB -(TxtHB*(TxtDisc / 100))+((TxtHB -(TxtHB*(TxtDisc / 100)))*(TxtCost / 100)) : 0).toFixed(2));
			});
			
			$('#TxtHB, #TxtDisc, #TxtCost, #TxtIsiPcs, #TxtIsiCtn').on('input',function() {
			var TxtHB = parseFloat($('#TxtHB').val());
			var TxtDisc = parseFloat($('#TxtDisc').val());
			var TxtCost = parseFloat($('#TxtCost').val());
			var TxtIsiCtn = parseInt($('#TxtIsiCtn').val());
			var TxtIsiPcs = parseInt($('#TxtIsiPcs').val());
			$('#TxtHBperCartNetto').val(((TxtHB -(TxtHB*(TxtDisc / 100))+(TxtHB*(TxtCost / 100))) * TxtIsiCtn * TxtIsiPcs  ?  (TxtHB -(TxtHB*(TxtDisc / 100))+(TxtHB*(TxtCost / 100))) * TxtIsiCtn * TxtIsiPcs : 0).toFixed(2));
			});
			
			$('#TxtHB, #TxtDisc, #TxtCost, #TxtIsiPcs, #TxtIsiCtn,#TxtKursBeliTrip').on('input',function() {
		  	var TxtHB = parseFloat($('#TxtHB').val());
			var TxtDisc = parseFloat($('#TxtDisc').val());
			var TxtCost = parseFloat($('#TxtCost').val());
			var TxtKursBeliTrip = parseInt($('#TxtKursBeliTrip').val());
			
		    $('#TxtHbIdr').val(((TxtHB -(TxtHB*(TxtDisc / 100))+((TxtHB -(TxtHB*(TxtDisc / 100)))*(TxtCost / 100)))*TxtKursBeliTrip ? (TxtHB -(TxtHB*(TxtDisc / 100))+((TxtHB -(TxtHB*(TxtDisc / 100)))*(TxtCost / 100)))*TxtKursBeliTrip : 0).toFixed(2));
			});
			
			$('#TxtHJRP, #TxtHbIdr').on('input',function() {
			 var TxtHbIdr = parseFloat($('#TxtHbIdr').val());
			 var TxtHJRP = parseFloat($('#TxtHJRP').val());
			$('#TxtProfit').val((TxtHJRP - TxtHbIdr ? TxtHJRP - TxtHbIdr : 0).toFixed(2));
			});
			
			$('#TxtHB, #TxtQtyBeli, #TxtIsiPcs, #TxtIsiCtn').on('input',function() {
		  	var TxtHB = parseFloat($('#TxtHB').val());
		    var TxtQtyBeli = parseInt($('#TxtQtyBeli').val());
		    var TxtIsiPcs = parseInt($('#TxtIsiPcs').val());
		    var TxtIsiCtn = parseInt($('#TxtIsiCtn').val());
		    $('#TxtPerHD').val(((TxtQtyBeli * TxtHB) * (TxtIsiPcs * TxtIsiCtn) ? (TxtQtyBeli * TxtHB) * (TxtIsiPcs * TxtIsiCtn) : 0).toFixed(2));
			});
		
			 //perh.dalam*jumlah IDR
			$('#TxtHB, #TxtQtyBeli, #TxtKursBeli, #TxtIsiPcs, #TxtIsiCtn').on('input',function() {
		    var TxtHB = parseFloat($('#TxtHB').val());
		    var TxtQtyBeli = parseInt($('#TxtQtyBeli').val());
		    var TxtKursBeli = parseFloat($('#TxtKursBeli').val());
		    var TxtIsiPcs = parseInt($('#TxtIsiPcs').val());
		    var TxtIsiCtn = parseInt($('#TxtIsiCtn').val());
		    $('#TxtPerHD1').val((TxtHB * TxtQtyBeli * TxtKursBeli * TxtIsiPcs * TxtIsiCtn ? TxtHB * TxtQtyBeli * TxtKursBeli * TxtIsiPcs * TxtIsiCtn : 0).toFixed(2));
			}); 
			/* ---- */
			$('#TxtHBEdit, #TxtIsiPcsEdit, #TxtIsiCtnEdit').on('input',function() {
				var TxtHBEdit = parseFloat($('#TxtHBEdit').val());
				var TxtIsiCtnEdit = parseInt($('#TxtIsiCtnEdit').val());
				var TxtIsiPcsEdit = parseInt($('#TxtIsiPcsEdit').val());
				$('#TxtHBperCartEdit').val((TxtHBEdit * TxtIsiCtnEdit * TxtIsiPcsEdit  ?  TxtHBEdit * TxtIsiCtnEdit * TxtIsiPcsEdit : 0).toFixed(2));
				});
				
				$('#TxtHBEdit, #TxtDiscEdit, #TxtCostEdit').on('input',function() {
				var TxtHBEdit = parseFloat($('#TxtHBEdit').val());
				var TxtDiscEdit = parseFloat($('#TxtDiscEdit').val());
				var TxtCostEdit = parseFloat($('#TxtCostEdit').val());
				$('#TxtHBNettoEdit').val((TxtHBEdit -(TxtHBEdit*(TxtDiscEdit / 100))+((TxtHBEdit -(TxtHBEdit*(TxtDiscEdit / 100)))*(TxtCostEdit / 100))  ?  TxtHBEdit -(TxtHBEdit*(TxtDiscEdit / 100))+((TxtHBEdit -(TxtHBEdit*(TxtDiscEdit / 100)))*(TxtCostEdit / 100)) : 0).toFixed(2));
				});
				
				$('#TxtHBEdit, #TxtDiscEdit, #TxtCostEdit, #TxtIsiPcsEdit, #TxtIsiCtnEdit').on('input',function() {
				var TxtHBEdit = parseFloat($('#TxtHBEdit').val());
				var TxtDiscEdit = parseFloat($('#TxtDiscEdit').val());
				var TxtCostEdit = parseFloat($('#TxtCostEdit').val());
				var TxtIsiCtnEdit = parseInt($('#TxtIsiCtnEdit').val());
				var TxtIsiPcsEdit = parseInt($('#TxtIsiPcsEdit').val());
				$('#TxtHBperCartNettoEdit').val(((TxtHBEdit -(TxtHBEdit*(TxtDiscEdit / 100))+(TxtHBEdit*(TxtCostEdit / 100))) * TxtIsiCtnEdit * TxtIsiPcsEdit  ?  (TxtHBEdit -(TxtHBEdit*(TxtDiscEdit / 100))+(TxtHBEdit*(TxtCostEdit / 100))) * TxtIsiCtnEdit * TxtIsiPcsEdit : 0).toFixed(2));
				});
				
				$('#TxtHBEdit, #TxtDiscEdit, #TxtCostEdit, #TxtIsiPcsEdit, #TxtIsiCtnEdit,#TxtKursBeliTripEdit').on('input',function() {
			  	var TxtHBEdit = parseFloat($('#TxtHBEdit').val());
				var TxtDiscEdit = parseFloat($('#TxtDiscEdit').val());
				var TxtCostEdit = parseFloat($('#TxtCostEdit').val());
				var TxtKursBeliTripEdit = parseInt($('#TxtKursBeliTripEdit').val());
				
			    $('#TxtHbIdrEdit').val(((TxtHBEdit -(TxtHBEdit*(TxtDiscEdit / 100))+((TxtHBEdit -(TxtHBEdit*(TxtDiscEdit / 100)))*(TxtCostEdit / 100)))*TxtKursBeliTripEdit ? (TxtHBEdit -(TxtHBEdit*(TxtDiscEdit / 100))+((TxtHBEdit -(TxtHBEdit*(TxtDiscEdit / 100)))*(TxtCostEdit / 100)))*TxtKursBeliTripEdit : 0).toFixed(2));
				});
				
				$('#TxtHJRPEdit, #TxtHbIdrEdit').on('input',function() {
				 var TxtHbIdrEdit = parseFloat($('#TxtHbIdrEdit').val());
				 var TxtHJRPEdit = parseFloat($('#TxtHJRPEdit').val());
				$('#TxtProfitEdit').val((TxtHJRPEdit - TxtHbIdrEdit ? TxtHJRPEdit - TxtHbIdrEdit : 0).toFixed(2));
				});
				
				$('#TxtHBEdit, #TxtQtyBeliEdit, #TxtIsiPcsEdit, #TxtIsiCtnEdit').on('input',function() {
			  	var TxtHBEdit = parseFloat($('#TxtHBEdit').val());
			    var TxtQtyBeliEdit = parseInt($('#TxtQtyBeliEdit').val());
			    var TxtIsiPcsEdit = parseInt($('#TxtIsiPcsEdit').val());
			    var TxtIsiCtnEdit = parseInt($('#TxtIsiCtnEdit').val());
			    $('#TxtPerHDEdit').val(((TxtQtyBeliEdit * TxtHBEdit) * (TxtIsiPcsEdit * TxtIsiCtnEdit) ? (TxtQtyBeliEdit * TxtHBEdit) * (TxtIsiPcsEdit * TxtIsiCtnEdit) : 0).toFixed(2));
				});
			
				 //perh.dalam*jumlah IDR
				$('#TxtHBEdit, #TxtQtyBeliEdit, #TxtKursBeliEdit, #TxtIsiPcsEdit, #TxtIsiCtnEdit').on('input',function() {
			    var TxtHBEdit = parseFloat($('#TxtHBEdit').val());
			    var TxtQtyBeliEdit = parseInt($('#TxtQtyBeliEdit').val());
			    var TxtKursBeliEdit = parseFloat($('#TxtKursBeliEdit').val());
			    var TxtIsiPcsEdit = parseInt($('#TxtIsiPcsEdit').val());
			    var TxtIsiCtnEdit = parseInt($('#TxtIsiCtnEdit').val());
			    $('#TxtPerHD1Edit').val((TxtHBEdit * TxtQtyBeliEdit * TxtKursBeliEdit * TxtIsiPcsEdit * TxtIsiCtnEdit ? TxtHBEdit * TxtQtyBeliEdit * TxtKursBeliEdit * TxtIsiPcsEdit * TxtIsiCtnEdit : 0).toFixed(2));
				});
			
			</script>
		
		
		   <script type="text/javascript">
         	 /*Date Picker*/
          	$(function(){
          	$('#dateFrom,#dateTo,#dateFromTrip,#dateToTrip,#TxtDateTripBeliEdit').datepicker();
          	});
       		 </script>
       		 
       	<script type="text/javascript">
			$(document).ready(function(){
     		$("#hide").click(function(){
        		$("#searchPemb").addClass("hidden");
    		});
     		$("#hideSearchTrip").click(function(){
     	    	$("#searchTrip").addClass("hidden");
     	    }); 
     		
     		$("#hideSearchProd").click(function(){
     	    	$("#searchProd").addClass("hidden");
     	    }); 
     		
    		$("#show").click(function(){
       			$("#searchPemb").removeClass("hidden");
   			});
    		
    		$("#showSearchTrip").click(function(){
            	$("#searchTrip").removeClass("hidden");      
            });
    		
    		$("#showSearchProd").click(function(){
            	$("#searchProd").removeClass("hidden");      
            });
    		
			});
			
		</script>
		
		
		<script type="text/javascript">
		 
		 function jsonGetArrayToPrint(obj){
			 var _data={};
   			
			 var _k = function(data){
				 window.location.replace('../img-sk/'+data.message+'.pdf');
				};
   			_data['queryDataBeli']=obj.value;
				_data['byValueBeli']= "trip_numbPrint";
				JSON.post(_data,'${ctx }/json/detpembelian',10000,_k,null,null);
		 }
			// Tampilan Currency
			function convertToMoney(n, currency){
			
				return  " " + n.toFixed(2).replace(/./g, function(c, i, a) {
		        return i > 0 && c !== "." && (a.length - i) % 3 === 0 ? "," + c : c;
		   	 	});
				}
			function convertToItem(n, currency){
					
				return  " " + n.toFixed(0).replace(/./g, function(c, i, a) {
		        return i > 0 && c !== "," && (a.length - i) % 3 === 0 ? "." + c : c;
		    	});
			  
				}
			</script>
		
  </body>
</html>
