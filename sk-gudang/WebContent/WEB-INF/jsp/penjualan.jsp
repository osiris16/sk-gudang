<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="d" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<body>
          	<h3><i class="fa fa-angle-right"></i> Penjualan</h3>
            
            <button type="button" class="btn btn-group btn-info" data-toggle="modal" data-target="#myModalAddPenjualan">ADD Penjualan</button>
            <br/>
			<br/>
            <button id="show" type="button" class="btn btn-group btn-info"><i class="glyphicon glyphicon-search" title="cari"></i> Search</i></button>
            
            <!--Search Table Penjualan-->
            <div id="searchPenjualan" class="panel panel-default hidden">
			<div class="panel-heading" >
			<button id="hide" type="button" class="btn btn-group btn-info">Hide</button>
			</div>
			<div class="panel panel-default">
			<div class="panel-heading" >
			<i class="glyphicon glyphicon-search" title="cari"></i> Search</i>
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
			<label class="col-sm-2 col-sm-2 control-label">Kategori</label>
                         
			<div class="col-sm-10">
			<select id = "selByValuePenj" class="form-control2" style="width:200px;">
				<option value = "orderNumb">Nomor Order</option>
				<option value = "fakturNumb">Nomor Faktur</option>
				<option value = "custName">Nama Customer</option>
			</select>
			
			</div>
			<label class="col-sm-2 col-sm-2 control-label"></label>
                         
			<div class="col-sm-10">
			<input type="text" id="inpQueryPenj" class="form-control2" style="width:200px;" >
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-sm-2 col-sm-2 control-label"></label>
                         
			<button onclick="penjualanCall();" type="button" style="width:220px;" class="btn btn-outline btn-primary btn-lg btn-block"><i class="glyphicon glyphicon-search" title="cari"></i></button>
			<label class="col-sm-2 col-sm-2 control-label"></label>
			<button onclick="penjualanPrint('DOWNLOADEXCEL');" type="button" style="width:220px;" class="btn btn-outline btn-primary btn-lg btn-block">Print To Excel</button>
			
			</div>
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
            <div style="border:1px solid white;height:400px;width:100%;overflow-x:scroll;overflow-y:scroll;">
		  	<div class="row mt">
			<div class="col-lg-12">
            <!--<div class="content-panel">-->
            <section id="unseen">
            <!--Table Penjualan-->
                 <table class="table1 table1 table-bordered table-striped table-condensed table-hover">
                              <thead>
                              <tr>
                              	  <th> Status Order</th>
                              	  <th> Tambah Order</th>
                              	  <th> Faktur</th>
                                  <th> Tanggal Order</th>
                                  <th> No. Order </th>
                                  <th> Nama Customer</th>
                                  <th> No. Faktur </th>
                                  <th> Total Penjulan Bruto</th>
                                  <th> Total Disc </th>
                                  <th> Total Ppn </th>
                                  <th> Total Penjulan Netto</th>
                                  
                                  <th> Kota</th>
                                  <!-- <th> Alamat</th> -->
                                  <th> Kode Sales</th>
                                  <th> Keterangan</th>
                                  <!-- <th> Nama Sales</th> -->
                                  
                                  <th>ACTION</th>
                              </tr>
                              </thead>
							 <tbody id="tbdPenjualan">
							 
                             </tbody>
                          </table>
                      </section>
                      
                  </div><!--content-panel-->
               </div><!--col-lg-4 -->			
		  	</div><!--row -->
		  	
      				
      				<!--Form Penjualan -->
<!-- 
<div id="appletPrint" style ="height:800px;background:gre">
<applet code="sk-gudang/jquery/appletPrintFaktur.java"></applet>
</div> -->
<div class="modal fade" id="myModalAddPenjualan" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>

                </button>
                <h4 class="modal-title" id="myModalLabel">Add Penjualan</h4>
                </div>
                <div style="border:1px solid white;height:500px;width:100%;overflow-x:scroll;overflow-y:scroll;">
                    <div class="row mt">
          		<div class="col-lg-12">
                  <div class="form-panel">
                  	  <h4 class="mb"><i class="fa fa-angle-right"></i> Tambah Penjualan</h4>
                     
                      
                      <form class="form-horizontal style-form" method="get">
                      	<button type="reset" value="Reset">Clear All</button>
                          <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">Tanggal</label>
                              <div class="col-sm-10">
                                  <input id="DateOrder" name="DateOrder" type="text" class="form-control2"  style="width:150px;" required/>
                                  
                                  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
                                  
                                  <!-- Dijadiin Kode unik penjualan -->
                                 <!--  <label>NO.FAKTUR</label> 
                                  <input id="TxtFakturNumb" name="TxtFakturNumb" type="text" class="form-control2"  style="width:150px;" >
                             -->
                              </div>
                              </div>
                              
                          <div class="form-group">
                             <input id="TxtCodeCustomer" name="TxtCodeCustomer" type="text" class="hidden"  style="width:200px;" >
                             
                             <label class="col-sm-2 col-sm-2 control-label">Customer</label>
                             <div class="col-sm-10">
                             <input id="TxtNameCustomer" name="TxtNameCustomer" type="text" class="form-control2"  style="width:200px;" readonly/>
                             <button type="button" class="btn btn-group btn-info" data-toggle="modal" data-target="#myModalCustomer" onclick ="customerCall();"><i class="fa fa-search "></i></button>
                             </div>
                             <input id="TxtIdCustomer" name="TxtIdCustomer" type="text" class="hidden"  style="width:200px;" readonly/>
                             
                             <label class="col-sm-2 col-sm-2 control-label">Alamat</label>
                             <div class="col-sm-10">
                             <input id="TxtAddrrCustomer" name="TxtAddrrCustomer" type="text" class="form-control2"  style="width:250px;" readonly/>
                             </div>
                             <label class="col-sm-2 col-sm-2 control-label">Kota</label>
                             <div class="col-sm-10">
                             <input id="TxtCityCustomer" name="TxtCityCustomer" type="text" class="form-control2"  style="width:150px;" readonly/>
                             </div>
                             </div>
                             
                             <%-- <div class="form-group">
                             <label class="col-sm-2 col-sm-2 control-label">JKW/TGL.JT</label>
                             <div class="col-sm-10">
                             <input id="TxtJkw" name="TxtJkw" type="text" class="form-control2"  style="width:100px;"  placeholder="Jangkauan">
                             
                             <label>Hari</label>
                             <input id="DateJkw" name="DateJkw" type="text" class="form-control2"  style="width:150px;" >
                             </div>
                             </div> --%>
                             
                          	 <div class="form-group">
                          	  <label class="col-sm-2 col-sm-2 control-label">Kode salesman</label>
                          	 <div class="col-sm-10">
                          	 <input  id="TxtCodeSalesman" name="TxtCodeSalesman" type="text" class="form-control2"  style="width:200px;" placeholder="" readonly/>
                          	 <button type="button" class="btn btn-group btn-info" data-toggle="modal" data-target="#myModalSalesman" onclick="salesmanCall();"><i class="fa fa-search"></i></button>
                         	 </div>
                          	 <label class="col-sm-2 col-sm-2 control-label">Nama salesman</label>
                          	 <div class="col-sm-10">
                          	 <input  id="TxtNameSalesman" name="TxtNameSalesman" type="text" class="form-control2"  style="width:200px;" placeholder="" readonly/>
                          	 <input  id="TxtIdSalesman" name="TxtIdSalesman" type="text" class="hidden"  style="width:200px;" placeholder=""readonly/>
                             </div>
                                  
                              <label class="col-sm-2 col-sm-2 control-label">PPn </label>
                              <div  class="col-sm-10">
                              <input type="number" id="TxtPpn" name="TxtPpn" class="form-control2" value="0" style="width:80px;">
							  <label>%(Persen)</label>
							  </div>
                              </div>
                              
                              <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">CATATAN</label>
                              <div class="col-sm-10">
                                  <input id="TxtCatatan" name="TxtCatatan" type="text" class="form-control"  style="width:500px;" placeholder="">
                              </div>
                          </div>
                        
						<button type="button" onclick="penjualanAddCall();" class="btn btn-theme" >SIMPAN</button>                              
                      
                      </form>
                      </div>
              </div>
              </div>
              </div>
              </div>
              </div></div>
              
<div class="modal fade" id="myModalEditPenjualan" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>

                </button>
                <h4 class="modal-title" id="myModalLabel">Edit Penjualan</h4>
                </div>
                <div style="border:1px solid white;height:500px;width:100%;overflow-x:scroll;overflow-y:scroll;">
                    <div class="row mt">
          		<div class="col-lg-12">
                  <div class="form-panel">
                  	  <h4 class="mb"><i class="fa fa-angle-right"></i> Edit Penjualan</h4>
                     
                      
                      <form class="form-horizontal style-form" method="get">
                      	
                      	<div class="form-group">
                           <label class="col-sm-2 col-sm-2 control-label">No. Order</label>
                           <div class="col-sm-10">
                           <input id="TxtOrderNumbEdit" name="TxtOrderNumbEdit" type="text" class="form-control2"  style="width:150px;" readonly/>
                            
                           </div>
                           </div>
                           
                          <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">Tanggal</label>
                              <div class="col-sm-10">
                                  <input id="DateOrderEdit" name="DateOrderEdit" type="text" class="form-control2"  style="width:150px;" required/>
                                  
                                  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
                                  
                              </div>
                              </div>
                              
                          <div class="form-group">
                             <input id="TxtCodeCustomerEdit" name="TxtCodeCustomerEdit" type="text" class="hidden"  style="width:200px;" >
                             
                             <label class="col-sm-2 col-sm-2 control-label">Customer</label>
                             <div class="col-sm-10">
                             <input id="TxtNameCustomerEdit" name="TxtNameCustomerEdit" type="text" class="form-control2"  style="width:200px;" readonly/>
                             <button type="button" class="btn btn-group btn-info" data-toggle="modal" data-target="#myModalCustomer" onclick ="customerCall();"><i class="fa fa-search "></i></button>
                             </div>
                             <input id="TxtIdCustomerEdit" name="TxtIdCustomerEdit" type="text" class="hidden"  style="width:200px;" readonly/>
                             
                             <label class="col-sm-2 col-sm-2 control-label">Alamat</label>
                             <div class="col-sm-10">
                             <input id="TxtAddrrCustomerEdit" name="TxtAddrrCustomerEdit" type="text" class="form-control2"  style="width:250px;" readonly/>
                             </div>
                             <label class="col-sm-2 col-sm-2 control-label">Kota</label>
                             <div class="col-sm-10">
                             <input id="TxtCityCustomerEdit" name="TxtCityCustomerEdit" type="text" class="form-control2"  style="width:150px;" readonly/>
                             </div>
                             </div>
                             
                          	 <div class="form-group">
                          	  <label class="col-sm-2 col-sm-2 control-label">Kode salesman</label>
                          	 <div class="col-sm-10">
                          	 <input  id="TxtCodeSalesmanEdit" name="TxtCodeSalesmanEdit" type="text" class="form-control2"  style="width:200px;" placeholder="" readonly/>
                          	 <button type="button" class="btn btn-group btn-info" data-toggle="modal" data-target="#myModalSalesman" onclick="salesmanCall();"><i class="fa fa-search"></i></button>
                         	 </div>
                          	 <label class="col-sm-2 col-sm-2 control-label">Nama salesman</label>
                          	 <div class="col-sm-10">
                          	 <input  id="TxtNameSalesmanEdit" name="TxtNameSalesmanEdit" type="text" class="form-control2"  style="width:200px;" placeholder="" readonly/>
                          	 <input  id="TxtIdSalesmanEdit" name="TxtIdSalesmanEdit" type="text" class="hidden"  style="width:200px;" placeholder=""readonly/>
                             </div>
                             
                             <label class="col-sm-2 col-sm-2 control-label">PPn </label>
                              <div  class="col-sm-10">
                              
                               <input type="number" id="TxtPpnEdit" name="TxtPpnEdit" class="form-control2"  value="0" style="width:80px;">
							   <label>%(Persen)</label>
							  </div>
                             </div>
                              
                              <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">CATATAN</label>
                              <div class="col-sm-10">
                                  <input id="TxtCatatanEdit" name="TxtCatatanEdit" type="text" class="form-control"  style="width:500px;" placeholder="">
                              </div>
                          </div>
                          
                          <div class="form-group">
                          	<label class="col-sm-2 col-sm-2 control-label">Total Bruto</label>
                          	 <div class="col-sm-10">
                          	 <input  id="TxtTotBrutoEdit" name="TxtTotBrutoEdit" type="text" class="form-control2"  style="width:200px;" placeholder="" >
                          	 </div>
                          	 <label class="col-sm-2 col-sm-2 control-label">Total Disc</label>
                          	 <div class="col-sm-10">
                          	 <input  id="TxtTotDiscEdit" name="TxtTotDiscEdit" type="text" class="form-control2"  style="width:200px;" placeholder="" >
                          	 </div>
                          	 <label class="col-sm-2 col-sm-2 control-label">Total Ppn</label>
                          	 <div class="col-sm-10">
                          	 <input  id="TxtTotPpnEdit" name="TxtTotPpnEdit" type="text" class="form-control2"  style="width:200px;" placeholder="" >
                          	
                          	 </div>
                          	 <label class="col-sm-2 col-sm-2 control-label">Total Netto</label>
                          	 <div class="col-sm-10">
                          	 <input  id="TxtTotNettoEdit" name="TxtTotNettoEdit" type="text" class="form-control2"  style="width:200px;" placeholder="" >
                          	 </div>
                          	 </div>
                        
						<button id="buttonEdit" type="button" onclick="penjualanUpdateCall();" class="btn btn-theme" value="Submit">UPDATE</button>                              
                      </form>
                      </div>
              </div>
              </div>
              </div>
              </div>
              </div></div>
              <!-- Modal Order-->
	<div class="modal fade" id="myModalOrderCall" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    	<div class="modal-dialog modal-lg">
        	<div class="modal-content">
           		<div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
				</button>
                <h4 class="modal-title" id="myModalLabel">List Order</h4>
				</div>
            	
           <!-- Pagging -->
			<button onclick = "firstPageOrder(this)" type="button" >First</button>
            <button onclick = "prevPageOrder(this)" type="button" >Prev</button>
            <label> Page </label>
            <select onchange="getPageOrder(this)"; id="selectPageOrder">
            
            </select>
            <label> of </label>
            <label id = "totPageOrder"></label>
            <button onclick = "nextPageOrder(this)" type="button" >Next</button>
            <!-- page end -->
            
            
            <div style="border:1px solid white;height:400px;width:100%;overflow-x:scroll;overflow-y:scroll;">
		  	<div class="row mt">
			<div class="col-lg-12">
            
            <!--Table Order Penjualan-->
                <table class="table1 table-bordered table-striped table-condensed table-hover">
                   <thead>
                   <tr>
                   <!-- <th> Nomor Order</th>
                   <th> Nomor Faktur</th> -->
                   <th> Kode barang </th>
                   <th> Nama barang </th> <!--TxtNamaBarang -->
                   <th> Qty Jual Ctn</th> <!--TxtQtyOrder -->
                   <th> Qty Jual</th>
                   <th> Hrg/Pcs</th>
                   <th> Total Bruto</th>
                   <th> Disc</th>
                   <th> Ppn</th>
                   <th> Total Netto</th>
                   <th> Keterangan</th>
                   <th>ACTION</th>
                   </tr>
                   </thead>
				   <tbody id="tbdOrder">
                   </tbody>
                   </table>
            </div>
           <div class="modal-footer"></div>
        </div>
      </div>
   </div>
 </div>
 </div> 
              
              
   <!--Form ORder -->
<div class="modal fade" id="myModalEditOrder" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
<div class="modal-dialog modal-lg">
        <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
			</button>
            
            <h4 class="modal-title" id="myModalLabel">List Order</h4>
		</div>
            
            <div style="border:1px solid white;height:500px;width:100%;overflow-x:scroll;overflow-y:scroll;">
            <div class="modal-body">
               <div class="row mt">
          		<div class="col-lg-12">
                  <div class="form-panel">
                  	 <h4 class="mb"><i class="fa fa-angle-right"></i> Tambah Order</h4>
                      <form class="form-horizontal style-form" method="get">
                  <input id="TxtIdPenjEdit" name="TxtIdPenjEdit" type="text"   class="hidden"  style="width:150px;" readonly/>
                  
				  <div class="form-group">
				  <label class="col-sm-2 col-sm-2 control-label">Kode Barang</label>
                  <div class="col-sm-10">
                  <input id="TxtProductCodeEdit" name="TxtProductCodeEdit" type="text"  class="form-control2"  style="width:200px;" readonly/>
                  <button type="button" class="btn btn-group btn-info" data-toggle="modal" data-target="#myModalProduct" onclick="productCall();"><i class="fa fa-search "></i></button>
                  </div>
                  <input id="TxtIdStockEdit" name="TxtIdStockEdit" type="text"  class="hidden"  style="width:200px;">
                  <input id="TxtIdPenjualanEdit" name="TxtIdPenjualanEdit" type="text"  class="hidden"  style="width:200px;">
                   	
                  <label class="col-sm-2 col-sm-2 control-label">Nama Barang</label>
                  <div class="col-sm-10">
                  <input id="TxtProductNameEdit" name="TxtProductNameEdit" type="text"  class="form-control2"  style="width:200px;" readonly/>
                  </div>
                  <label class="col-sm-2 col-sm-2 control-label">Sisa Stok CTN</label>
                  <div class="col-sm-10">
                  <input type="text" id="TxtStokCtnEdit"  class="form-control2"  style="width:150px;" readonly/>
                  <input type="text" id="TxtSatStokCTNEdit"  class="form-control2"  style="width:80px;" placeholder="CTN" readonly/>
                  </div>
              	  <label class="col-sm-2 col-sm-2 control-label">
                  <input id="RadTotStockRetail" name="RadTotStock" value="retail" type="radio" checked="true" />
                  Stok CTN Retail</label>
                  <div class="col-sm-10">
                  <input type="text" id="TxtStokCtnRetailEdit"  class="form-control2"  style="width:150px;" readonly/>
                  <input type="text" id="TxtSatStokCTNRetailEdit"  class="form-control2"  style="width:80px;" placeholder="CTN" readonly/>
                  </div>
                  <label class="col-sm-2 col-sm-2 control-label">
                  <input id="RadTotStockDeptStore" name="RadTotStock" value="deptStore" type="radio" />
                  Stok CTN DeptStore</label>
                  <div class="col-sm-10">
                  <input type="text" id="TxtStokCtnDeptStoreEdit"  class="form-control2"  style="width:150px;" readonly/>
                  <input type="text" id="TxtSatStokCTNDeptStoreEdit"  class="form-control2"  style="width:80px;" placeholder="CTN" readonly/>
                  </div> 
                  <label class="col-sm-2 col-sm-2 control-label">Harga Jual Standart /</label>
                  <div class="col-sm-10">
                  <input type="text" class="form-control2"  style="width:80px;" value= "CTN" readonly/>
                  <input type="text"   class="form-control2"  style="width:50px;" value="IDR" readonly/>
                  <input type="number" id="TxtHargaJualCtnEdit" name="TxtHargaJualCtnEdit" class="form-control2"  style="width:200px;" readonly/>
                  <input type="number" id="TxtHargaJualPcsEdit" class="form-control2"  style="width:200px;" >
                   <input type="text" id="TxtIsiCtnEdit" class="hidden"  style="width:80px;" value= "CTN" readonly/>
                   <input type="text"  id="TxtIsiPcsEdit" class="hidden"  style="width:50px;" value="IDR" readonly/>
                  
                  </div>
                  
                  
                  <label class="col-sm-2 col-sm-2 control-label">PPN</label>
                  <div class="col-sm-10">
                  <input type="text" id="TxtPpnJualEdit"  class="form-control2"  style="width:80px;" readonly/>
                  </div>
                  </div>
                   	
                  <h4 class="mb"><i class="fa fa-angle-right"></i> QTY Penjualan</h4>
                   
                   <div class="form-group">
                   <label class="col-sm-2 col-sm-2 control-label">Qty Order</label>
                   <div class="col-sm-10">
                   <input id="TxtQtyOrderCtnEdit" name="TxtQtyOrderCtnEdit" type="number" class="form-control2"  style="width:150px;" value="0" required/>
                   <input type="text" class="form-control2"  style="width:100px;" width="20%" value="CTN" readonly/> <!-- Harus CTN -->
                   </div>
                   
                   <label class="col-sm-2 col-sm-2 control-label">Qty Order</label>
                   <div class="col-sm-10">
                   <input id="TxtQtyOrderPcsEdit" type="number" class="form-control2"  style="width:150px;" value="0" required/>
                   <input type="text" class="form-control2"  style="width:100px;" width="20%" value="PCS" readonly/> <!-- Harus CTN -->
                   </div>
                   </div>

                 	<div class="form-group">
                 	<label class="col-sm-2 col-sm-2 control-label">Jumlah Bruto(CTN)</label>
                 	<div class="col-sm-10">
                 	<input id="TxtTotalHargaBrutoEdit" name="TxtTotalHargaBrutoEdit" type="text"  class="form-control2"  style="width:200px;" placeholder="" readonly/>
                 	</div>
                  	
                  	<label class="col-sm-2 col-sm-2 control-label">Discount</label>
                 	<div class="col-sm-10">
                  	<input id="TxtDiskonEdit" name="TxtDiskonEdit" type="text" class="form-control2"  style="width:100px;" value="0"><label>%</label>
                  	</div>
                  	
                  	<label class="col-sm-2 col-sm-2 control-label">Jumlah Netto(CTN) Belum PPN</label>
                  	<div class="col-sm-10">
                  	<input id="TxtTotalHargaNettoEdit" name="TxtTotalHargaNettoEdit" type="text"  class="form-control2"  style="width:200px;" placeholder="" readonly/>
                 	</div>
                 	</div>
                 	
                 	<div class="form-group">
                   
                   <label class="col-sm-2 col-sm-2 control-label">Keterangan</label>
                   <div class="col-sm-10">
                   <input id="TxtKetOrderEdit" type="text" class="form-control2"  style="width:300px;" width="20%" placeholder="Keterangan"/> 
                   </div>
                   </div>
                  	<button type="button" onclick="orderEditCall();" class="btn btn-theme">UPDATE</button>
                 </form>
                
                </div>
                </div>
                </div>
                </div>
                </div>
                </div>
                </div>
                </div>
                
                   <!--Form Add ORder -->
<div class="modal fade" id="myModalAddOrder" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
<div class="modal-dialog modal-lg">
        <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
			</button>
            
            <h4 class="modal-title" id="myModalLabel">List Order</h4>
		</div>
            
            <div style="border:1px solid white;height:500px;width:100%;overflow-x:scroll;overflow-y:scroll;">
            <div class="modal-body">
               <div class="row mt">
          		<div class="col-lg-12">
                  <div class="form-panel">
                  	 <h4 class="mb"><i class="fa fa-angle-right"></i> Tambah Order</h4>
                      
                      <form class="form-horizontal style-form" method="get">
                      
                      
                 <input id="TxtIdPenj" name="TxtIdPenj" type="text"   class="hidden"  style="width:150px;" readonly/>
                  
				  <div class="form-group">
				  <label class="col-sm-2 col-sm-2 control-label">Kode Barang</label>
                  <div class="col-sm-10">
                  <input id="TxtProductCode" name="TxtProductCode" type="text"  class="form-control2"  style="width:200px;" readonly/>
                  <button type="button" class="btn btn-group btn-info" data-toggle="modal" data-target="#myModalProduct" onclick="productCall();"><i class="fa fa-search "></i></button>
                  </div>
                  <input id="TxtIdStock" name="TxtIdStock" type="text"  class="hidden"  style="width:200px;">
                   	
                  <label class="col-sm-2 col-sm-2 control-label">Nama Barang</label>
                  <div class="col-sm-10">
                  <input id="TxtProductName" name="TxtProductName" type="text"  class="form-control2"  style="width:200px;" readonly/>
                  </div>
                  <label class="col-sm-2 col-sm-2 control-label">Sisa Stok CTN</label>
                  <div class="col-sm-10">
                  <input type="text" id="TxtStokCtn"  class="form-control2"  style="width:150px;" readonly/>
                  <input type="text" id="TxtSatStokCTN"  class="form-control2"  style="width:80px;" placeholder="CTN" readonly/>
                  </div>
                  
                  <label class="col-sm-2 col-sm-2 control-label">
                  <input id="RadTotStockRetailAdd" name="RadTotStockAdd" value="deptStore" type="radio" checked="true"/>
                  Stok CTN Retail</label>
                  <div class="col-sm-10">
                  <input type="text" id="TxtStokRetailCtn"  class="form-control2"  style="width:150px;" readonly/>
                  <input type="text" id="TxtSatStokRetailCTN"  class="form-control2"  style="width:80px;" placeholder="CTN" readonly/>
                  </div>
                  
                  <label class="col-sm-2 col-sm-2 control-label">
                  <input id="RadTotStockDeptStoreAdd" name="RadTotStockAdd" value="deptStore" type="radio" />
                  Stok CTN DeptStore</label>
                  <div class="col-sm-10">
                  <input type="text" id="TxtStokDeptStoreCtn"  class="form-control2"  style="width:150px;" readonly/>
                  <input type="text" id="TxtSatStokDeptStoreCTN"  class="form-control2"  style="width:80px;" placeholder="CTN" readonly/>
                  </div>
                  
                  <label class="col-sm-2 col-sm-2 control-label">Harga Jual Standart /</label>
                  <div class="col-sm-10">
                  <input type="text" class="form-control2"  style="width:80px;" value= "CTN" readonly/>
                  <input type="text"   class="form-control2"  style="width:50px;" value="IDR" readonly/>
                  <input type="number" id="TxtHargaJualCtn" name="TxtHargaJualCtn" class="form-control2"  style="width:200px;" readonly/>
                  <input type="number" id="TxtHargaJualPcs" name="TxtHargaJualPcs" class="form-control2"  style="width:200px;" >
                  <input type="text" id="TxtIsiCtn" class="hidden"  style="width:80px;" value= "CTN" readonly/>
                  <input type="text"  id="TxtIsiPcs" class="hidden"  style="width:50px;" value="IDR" readonly/>
                  
                  </div>
                  
                  
                  
                  <label class="col-sm-2 col-sm-2 control-label">PPN</label>
                  <div class="col-sm-10">
                  <input type="text" id="TxtPpnJual"  clear="false" class="form-control2"  style="width:80px;" readonly/>
                  
                  </div>
                  </div>
                   	
                  <h4 class="mb"><i class="fa fa-angle-right"></i> QTY Penjualan</h4>
                   
                   <div class="form-group">
                   <label class="col-sm-2 col-sm-2 control-label">Qty Order</label>
                   <div class="col-sm-10">
                   <input id="TxtQtyOrderCtn" name="TxtQtyOrderCtn" type="number" class="form-control2"  style="width:150px;" value="0" required/>
                   <input type="text" class="form-control2"  style="width:100px;" width="20%" value="CTN" readonly/> <!-- Harus CTN -->
                   </div>
                   <label class="col-sm-2 col-sm-2 control-label">Qty Order</label>
                   <div class="col-sm-10">
                   <input id="TxtQtyOrderPcs" name="TxtQtyOrderPcs" type="number" class="form-control2"  style="width:150px;" value="0" required/>
                   <input type="text" class="form-control2"  style="width:100px;" width="20%" value="PCS" readonly/> <!-- Harus CTN -->
                   </div>
                   </div>
                   
					<div class="form-group">
                 	<label class="col-sm-2 col-sm-2 control-label">Jumlah Bruto(CTN)</label>
                 	<div class="col-sm-10">
                 	<input id="TxtTotalHargaBruto" name="TxtTotalHargaBruto" type="text"  class="form-control2"  style="width:200px;" placeholder="" readonly/>
                 	</div>
                  	
                  	<label class="col-sm-2 col-sm-2 control-label">Discount</label>
                 	<div class="col-sm-10">
                  	<input id="TxtDiskon" name="TxtDiskon" type="text" class="form-control2"  style="width:100px;" value="0"><label>%</label>
                  	</div>
                  	
                  	<label class="col-sm-2 col-sm-2 control-label">Jumlah Netto(CTN) Belum PPN</label>
                  	<div class="col-sm-10">
                  	<input id="TxtTotalHargaNetto" name="TxtTotalHargaNetto" type="text"  class="form-control2"  style="width:200px;" placeholder="" readonly/>
                 	</div>
                 	</div>
                 	<div class="form-group">
                   
                   <label class="col-sm-2 col-sm-2 control-label">Keterangan</label>
                   <div class="col-sm-10">
                   <input id="TxtKetOrder" type="text" class="form-control2"  style="width:300px;" width="20%" placeholder="Keterangan"/> 
                   </div>
                   </div>
                  	<button type="button" onclick="orderAddCall();" class="btn btn-theme">SIMPAN</button>
                 </form>
                
                </div>
                </div>
                </div>
                </div>
                </div>
                </div>
                </div>
                </div>
<!-------------------------------------- Tabel modal  -------------------------------------------->
<!-- Modal Barang-->
	<div class="modal fade" id="myModalProduct" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    	<div class="modal-dialog modal-lg">
        	<div class="modal-content">
           		<div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
				</button>
                <h4 class="modal-title" id="myModalLabel">List Barang</h4>
				</div>
            	<!--Search Table Barang-->
			<div class="panel-body" id="panelBody">
			<div class="col-lg-6">
			<div class="form-group">
			<label class="col-sm-2 col-sm-2 control-label">Kategori</label>
                         
			<div class="col-sm-10">
			<select id = "selByValue" class="form-control2" style="width:200px;">
				<option value = "code">Kode Barang</option>
				<option value = "name">Nama Barang</option>
			</select>
			</div>
			<label class="col-sm-2 col-sm-2 control-label"></label>
                         
			<div class="col-sm-10">
			<input type="text" id="inpQuery" class="form-control2" style="width:200px;" >
			</div>
			
			</div>
			
			<div class="form-group">
			<label class="col-sm-2 col-sm-2 control-label"></label>
                         
			<button onclick="productCall();" type="button" style="width:220px;" class="btn btn-outline btn-primary btn-lg btn-block"><i class="glyphicon glyphicon-search" title="cari"></i></button>
			</div>
			</div>
			
            <!-- Pagging -->
            <label> Page </label>
            <select onchange="getPageStock(this)"; id="selectPageStock">
            
            </select>
            <label> of </label>
            <label id = "totPageStock"></label>
            <!-- page end --> 
            	<div style="border:1px solid white;height:300px;width:100%;overflow-x:scroll;overflow-y:scroll;">
            	<div class="modal-body">
            	<!-- Barang -->
            	<table id="tableProduct" class="table2 table-bordered table-hover table-striped table-condensed">
            	<thead>
                           <tr>
                       <!-- <th>Gambar</th> 
                      		<th> No. Barcode </th>-->
                            <th> Kode Barang</th>
                            <th> Nama Barang </th>
                            <!-- <th> Merek / Label </th>
                            <th> Jenis Barang</th>
                            <th> Made in</th>
                       		<th> No. Pabrik</th>
                       		<th> No. SNI </th> -->
                       		<th> Sisa Stok(CTN)</th>
                       		<th> Isi CTN</th>
                       		<th> Sat Isi CTN</th>
                       		<th> Isi PCS</th>
                       		<th> Harga Jual CTN</th>
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
 </div>
 
 
			 
			 
<!-- Tabel modal Customer -->
<div class="modal fade" id="myModalCustomer" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>

                </button>
                
                <h4 class="modal-title" id="myModalLabel">List Customer</h4>
			</div>
			
			<!--Search Table Customer-->
			<div class="panel panel-default">
			<div class="panel-heading" >
			<i class="glyphicon glyphicon-search" title="cari"></i> Search</i>
			</div>
			<div class="panel-body" id="panelBody">
			<div class="col-lg-6">
			<div class="form-group">
			<label class="col-sm-2 col-sm-2 control-label">Kategori</label>
			
            <div class="col-sm-10">
			<select id = "selByValueCust" class="form-control2" style="width:200px;">
				<option value = "name">Nama</option>
				<option value = "city">Kota</option>
			</select>
			
			</div>
			<label class="col-sm-2 col-sm-2 control-label"></label>
                         
			<div class="col-sm-10">
			<input type="text" id="inpQueryCust" class="form-control2" style="width:200px;" >
			</div>
			</div>
			<label class="col-sm-2 col-sm-2 control-label"></label>
			<div class="col-sm-10">
			<button onclick="customerCall();" type="button" style="width:150px"; class="btn btn-outline btn-primary btn-lg btn-block"><i class="glyphicon glyphicon-search" title="cari"></i></button>
			</div>
			</div>
			</div>
			</div>
			
			<!-- Pagging -->
			<button onclick = "firstPageCust(this)" type="button" >First</button>
            <button onclick = "prevPageCust(this)" type="button" >Prev</button>
            <label> Page </label>
            <select onchange="getPageCust(this)"; id="selectPageCust">
            
            </select>
            <label> of </label>
            <label id = "totPageCust"></label>
            <button onclick = "nextPageCust(this)" type="button" >Next</button>
            <!-- page end -->
            <div style="border:1px solid white;height:300px;width:100%;overflow-x:scroll;overflow-y:scroll;">
                 
            <div class="modal-body">
            <!-- Customer -->
               <table id="tblCustomer" class="table2 table-bordered table-striped table-condensed">
					<thead>
                              <tr style="width:70%";>
                                  <th width="2%"> Kode Customer</th>
                                  <th width="1%"> Nama Customer</th>
                                  <th width="2%"> Alamat 1</th>
                                  <th width="2%"> Alamat 2</th>
                                  <th width="2%"> Kota</th>
                                  
                              </tr>
                              </thead>
					</div>
					
                              <tbody id="tbdCustomer">
                               </tbody>
                          </table>
            </div>
            <div class="modal-footer"></div>
        </div>
        </div>
    </div>
</div> 

<!-- Tabel modal Customer -->
<div class="modal fade" id="myModalSend" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
				</button>
             </div>
			
              
            <div class="modal-body">
            <select id="optSended" class="form-control2">
            <option value="Terkirim">Terkirim</option>
            <option value="Belum Terkirim">Belum terkirim</option>
            </select>
            <button onclick="sendCall();" type="button" style="width:150px"; class="btn btn-outline btn-primary btn-lg btn-block">Simpan</button>
			
            </div>
        </div>
    </div>
</div>

<!-- Tabel modal Salesman -->
<div class="modal fade" id="myModalSalesman" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>

                </button>
                
                <h4 class="modal-title" id="myModalLabel">List Salesman</h4>
			</div>
			<!--Search Table salesman-->
			<div class="panel panel-default">
			<div class="panel-heading" >
			<i class="glyphicon glyphicon-search" title="cari"></i> Search</i>
			</div>
			<div class="panel-body" id="panelBody">
			<div class="col-lg-6">
			<div class="form-group">
			<label class="col-sm-2 col-sm-2 control-label">Kategori</label>
			<div class="col-sm-10">
			<select id = "selByValueSales" class="form-control2" style="width:200px;">
				<option value = "code">Kode</option>
				<option value = "name">Nama</option>
			</select>
			
			</div>
			<label class="col-sm-2 col-sm-2 control-label"></label>    
			<div class="col-sm-10">
			<input  type="text" id="inpQuerySales" class="form-control2" style="width:200px;" >
			</div>
			</div>
			<div class="form-group">
			<button onclick="salesmanCall();" type="button" style="width:300px;" class="btn btn-outline btn-primary btn-lg btn-block"><i class="glyphicon glyphicon-search" title="cari"></i></button>
			</div>
			</div>
			</div>
			</div>
			<!-- Pagging -->
			<button onclick = "firstPageSlm(this)" type="button" >First</button>
            <button onclick = "prevPageSlm(this)" type="button" >Prev</button>
            <label> Page </label>
            <select onchange="getPageSlm(this)"; id="selectPageSlm">
            
            </select>
            <label> of </label>
            <label id = "totPageSlm"></label>
            <button onclick = "nextPageSlm(this)" type="button" >Next</button>
            <!-- page end -->
            <div style="border:1px solid white;height:300px;width:100%;overflow-x:scroll;overflow-y:scroll;">
                 
            <div class="modal-body">
            <!-- Customer -->
               <table class="table2 table-bordered table-striped table-condensed">
				<thead>
                              <tr style="width:70%";>
                                  <th width="10%"> Kode Sales</th>
                                  <th width="90%"> Nama Sales</th>
                                  
                              </tr>
                              </thead>
							  </div>
                              <tbody id="tbdSalesman">
                              </tbody>
                          </table>
                      
            </div>
            <div class="modal-footer"></div>
        </div>
        </div>
    </div>
</div> 
  

 
 <div class="modal fade" id="myModalFaktur" tabindex="-1" role="dialog" aria-labelledby="myModalFaktur" aria-hidden="true">
    	<div class="modal-dialog modal-lg">
        	<div class="modal-content">
           		<div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
				</button>
                <h4 class="modal-title" id="myModalLabelFaktur">Faktur</h4>
				</div>
            	
            
            
            <div id="stylePdf" style="border:1px solid white;height:400px;width:100%;overflow-x:scroll;overflow-y:scroll;">
		  	<div class="row mt">
			<div class="col-lg-12">
	            <table style="font-size: 10px" class="table1 table-bordered">
	            	<tr>
	            		<th>No Faktur</th >
	            		<th id="nomorFaktur"></th>
					</tr>
	            	<tr>
	            	<td width="20%">Tanggal Faktur</td>
	            	<td id="tglFaktur" width="20%" style="border-right-width: 2px"></td>
	            	</tr>
	            	<tr>
	            	<td width="20%">S</td>
	            	<td width="20%" id="txtSalesCode"></td>
	            	</tr>
	            	<tr>
	            		<td>Customer name</td>
	            		<td id="tdCustomerName"></td>
	            	</tr>
	            	<tr>
	            		<td>Customer Adress</td>
	            		<td id="tdCustomerAddress"></td>
	            	</tr>
	            	<tr>
	            		<td>Customer City</td>
	            		<td id="tdCustomerCity"></td>
	            	</tr>
	            </table>
            <!--Table Order Penjualan-->
                <table style="font-size: 10px" class="table1 table-bordered table-striped table-condensed table-hover">
                   <thead>
                   <tr>
                   <th style="width:20%;padding: 10px"><a>Nomor Carton</a></th>
                   <th style="width:10%"><a>Banyak (PCS)</a></th>
                   <th> Ctn </th>
                   <th> Kode Barang</th>
                   <th> Nama barang </th> <!--TxtNamaBarang -->
                   <th> Harga/Pcs</th> <!--TxtQtyOrder -->
                   <th> Disc %</th>
                   <th> H.Nett/Pcs</th>
                   <th> Harga Netto</th>	
                   </tr>
                   </thead>
				   <tbody id="tbdFaktur">
                   
                   </tbody>
                   
                   </table>
            </div>
           <div class="modal-footer">
           <label>Total : RP </label>
           <input type="text" id="txtNettoFakturBeforePpn" class="" style="width:150px;">
           
           </div>
           <div class="modal-footer">
           <label>PPN : RP </label>
           <input type="text" id="txtPpnFaktur" class="" style="width:150px;">
           
           </div>
           <div class="modal-footer">
           <label>Total Netto : RP </label>
           <input type="text" id="txtNettoFaktur" class="" style="width:150px;">
           </div>
           <div class="modal-footer">
           <label>Nama tanda tangan </label>
           <input type="text" id="txtNameTTD" class="form-control2" style="width:500px;">
           </div>
           <div class="modal-footer">
           <label>Ket : </label>
           <input type="text" id="txtKeteranganFaktur" class="form-control2" style="width:500px;">
           </div>
        </div>
      </div>
   </div>
   <button id="printPdf" onclick="getNoFaktur(this);" type="button" style="width:220px;" class="btn btn-outline btn-primary btn-lg btn-block">Cetak Faktur</button>
   <button id="printPdfSuratJalan" onclick="getSuratJalan(this);" type="button" style="width:220px;" class="btn btn-outline btn-primary btn-lg btn-block">Surat Jalan Faktur</button>
   <!-- <button id="printPdfDotMatrix" value="dotmatrix" onclick="getNoFaktur(this);" type="button" style="width:220px;" class="btn btn-outline btn-primary btn-lg btn-block">Cetak Raw Text </button>
  --></div>
 </div> 
  

                      
<!-- --------------------------SCRIPT---------------- -->
				<script type="text/javascript">
			    function rawPrint(zpl) {
			      var printWindow = window.open();
			      printWindow.document.open('text/plain')
			      printWindow.document.write(zpl);
			      printWindow.document.close();
			      printWindow.focus();
			      printWindow.print();
			      printWindow.close();
			    }
			  </script>
			<script type="text/javascript">
			/* UPDATE DATA MODAL */
			function pushModalEditPenjualan(_id,_orderDate,_orderNumb,_fakturNumb,_custId,_custCode,_custName,_custCity,_custAddress,_salesId,_salesCode,_salesName,_totDiscA,_totPpnA,_totBrutoA,_totNettoA,_ppn,_ket){
    			
    			var _data={};
    			
    			document.getElementById("buttonEdit").value = _id;
    			document.getElementById("DateOrderEdit").value = _orderDate;
    			document.getElementById("TxtOrderNumbEdit").value = _orderNumb;
    			/* document.getElementById("TxtFakturNumbEdit").value = _fakturNumb; */
    			document.getElementById("TxtIdCustomerEdit").value = _custId;
    			document.getElementById("TxtCodeCustomerEdit").value = _custCode;
    			document.getElementById("TxtNameCustomerEdit").value = _custName;
    			document.getElementById("TxtCityCustomerEdit").value = _custCity;
    			document.getElementById("TxtAddrrCustomerEdit").value = _custAddress;
    			document.getElementById("TxtIdSalesmanEdit").value = _salesId;
    			document.getElementById("TxtCodeSalesmanEdit").value = _salesCode;
    			document.getElementById("TxtNameSalesmanEdit").value = _salesName;
    			document.getElementById("TxtTotDiscEdit").value = _totDiscA;
    			document.getElementById("TxtTotPpnEdit").value = _totPpnA;
    			document.getElementById("TxtTotBrutoEdit").value = _totBrutoA;
    			document.getElementById("TxtTotNettoEdit").value = _totNettoA;
    			document.getElementById("TxtPpnEdit").value = _ppn;
    			document.getElementById("TxtCatatanEdit").value = _ket;
    			
    			
				//JSON.post(_data,'${ctx }/json/product',10000,productModalSent,null,null);
    		}
		
    		var penjualanUpdateSent = function(data) {
    			var recid = document.getElementById("myModalEditPenjualan");
				var _dataId= recid.getAttribute("data-id");
    			var _items = data.items;
    			
    			for (var i = 0; i < _items.length; i++){
    				var _item = _items[i];
    				if (_item.id == _dataId) {
						
        				
        				document.getElementById("DateOrderEdit").value = _item.orderDate;
            			document.getElementById("TxtOrderNumbEdit").value = _item.orderNumb;
            			/* document.getElementById("TxtFakturNumbEdit").value = _item.fakturNumb; */
            			document.getElementById("TxtIdCustomerEdit").value = _item.custId;
            			document.getElementById("TxtCodeCustomerEdit").value = _item.custCode;
            			document.getElementById("TxtNameCustomerEdit").value = _item.custName;
            			document.getElementById("TxtCityCustomerEdit").value = _item.custCity;
            			document.getElementById("TxtAddrrCustomerEdit").value = _item.custAddress;
            			document.getElementById("TxtIdSalesmanEdit").value = _item.salesId;
            			document.getElementById("TxtCodeSalesmanEdit").value = _item.salesCode;
            			document.getElementById("TxtNameSalesmanEdit").value = _item.salesName;
            			document.getElementById("TxtPpnEdit").value = _item.ppn;
            			document.getElementById("TxtCatatanEdit").value = _item.keterangan;
            			
					}
    			}
    			
    		}
    		
			var penjualanUpdateCall = function() {
				var _recid = document.getElementById("buttonEdit").value;
				var _data={};
				_data['id'] = _recid;
				_data['orderDate']=$('#DateOrderEdit').datepicker('getDate').getTime();
				_data['orderNumb']=$('#TxtOrderNumbEdit').val().trim();
				/* _data['fakturNumb']=$('#TxtFakturNumbEdit').val().trim(); */
				_data['customerEnt']=$('#TxtIdCustomerEdit').val().trim();
				_data['salesmanEnt']=$('#TxtIdSalesmanEdit').val().trim();
				_data['ppn']=$('#TxtPpnEdit').val().trim();
				_data['totDisc']=$('#TxtTotDiscEdit').val().trim();
				_data['totPpn']=$('#TxtTotPpnEdit').val().trim();
				_data['brutoDefault']=$('#TxtTotBrutoEdit').val().trim();
				_data['nettoDefault']=$('#TxtTotNettoEdit').val().trim();
				_data['keterangan']=$('#TxtCatatanEdit').val().trim();
				
				JSON.post(_data,'${ctx }/json/penjualan-mod',10000,penjualanEdit,null,null);
			};
			
			/* END UPDATE DATA MODAL */
			
			/* DELETE DATA */
       		var penjualanRemoveCall = function(_id) {
       			
				if(confirm("Do you want to delete ?")) {
       			    this.click;
				var _data={};
				_data['id'] = _id;
				JSON.post(_data,'${ctx }/json/penjualan-del',10000,penjualanDelete,null,null);
				
       	 		}
			else {
			return;
			}
       		};
       		/*End delete*/
       		</script>
       		
              <script type="text/javascript">
              var penjualanAddSent = function(data){
					
					var _items = data.items;
		       		for ( var i = 0; i < _items.length; i++) {
		        			$('#tbdPenjualan').append(
		        				$('<tr><\/tr>')
		        					.append($('<td><\/td>').html($.datepicker.formatDate('d MM yy',new Date(_item.orderDate))))
        							.append($('<td><\/td>').html(_item.orderNumb))
        							.append($('<td><\/td>').html(_item.fakturNumb))
        							.append($('<td><\/td>').html(_item.custName))
        							.append($('<td><\/td>').html(_item.custCity))
        							.append($('<td><\/td>').html(_item.custAddress))
        							.append($('<td><\/td>').html(_item.salesCode))
        							.append($('<td><\/td>').html(_item.salesName))
		        					
		        				);
		        				 
		        			} 
		        			
		        		};
      		
        		var penjualanAddCall = function() {
					var _data={};
					
					_data['orderDate']=$('#DateOrder').datepicker('getDate').getTime();
					/* _data['fakturNumb']=$('#TxtFakturNumb').val().trim(); */
					_data['customerEnt']=$('#TxtIdCustomer').val().trim();
					_data['salesmanEnt']=$('#TxtIdSalesman').val().trim();
					_data['ppn']=$('#TxtPpn').val().trim();
					_data['keterangan']=$('#TxtCatatan').val().trim();
					
					
					JSON.post(_data,'${ctx }/json/penjualan-add',10000,penjualanAdd,null,null);
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
					JSON.post(_data,'${ctx }/json/getPagePenjualan',10000,penjualanSent,null,null);
					/* setPagging("page"+obj.value,"",""); */
				}
				function getUrlVars() {
					var vars = {};
					var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
					vars[key] = value;
					});
					return vars;
				}
				// terkirim start
				var _setPop=function(_id){
				// $('#myModalSend').modal('show');
				var _data={};
				_data['id'] = _id;
					//_data['terkirim']=$('#optSended').val().trim();
					JSON.post(_data,'${ctx }/json/sended',10000,sendRespon,null,null);
				};
				var sendRespon = function(data){
					
					penjualanCall();
						
				};
				// terkirim end
				
				var penjualanSent = function(data) {
					document.getElementById("tbdPenjualan").innerHTML = "";
      				if(data.page){
      					document.getElementById("totPage").innerHTML = data.page;
      					 setPagging("1",data.page,"1"); 
      					
      				}
      				
    			var _items = data.items;
    			$('#tbdPenjualan').empty();
    			for ( var i = 0; i < _items.length; i++) {
    				
    				var _item = _items[i];
    				var _id = "'"+_item.id+"'";
    				var _orderDate = "'"+($.datepicker.formatDate('d MM yy',new Date(_item.orderDate)))+"'";
    				var _orderNumb = "'"+_item.orderNumb+"'";
    				var _fakturNumb = "'"+_item.fakturNumb+"'";
    				var _custId = "'"+_item.custId+"'";
    				var _custCode = "'"+_item.custCode+"'";
    				var _custName = "'"+_item.custName+"'";
    				var _custCity = "'"+_item.custCity+"'";
    				var _custAddress = "'"+_item.custAddress+"'";
    				var _salesId = "'"+_item.salesId+"'";
    				var _salesCode = "''";
    				if(_item.salesCode){
    					_salesCode = "'"+_item.salesCode+"'";
    				}
    				var _salesName = "'"+_item.salesName+"'";
    				var _orderValue = "'"+"orderNumb"+"'";
    				var _penId = "'"+_item.id+"'";
    				var _totBrutoFaktur = convertToMoney(_item.totJualBruto);
    				var _totDisc = convertToMoney(_item.totDisc);
    				var _ppn = "'"+_item.ppn+"'";
    				var _totNettoFakturBeforePpn ="'"+convertToMoney(_item.totJualNettoBeforePpn)+"'";
    				var _totPpnFaktur = "'"+convertToMoney(_item.totPpn)+"'";
    				var _totNettoFaktur = "'"+convertToMoney(_item.totJualNetto)+"'";
    				var _totPpnFakturr = convertToMoney(_item.totPpn);
    				var _totNettoFakturr = convertToMoney(_item.totJualNetto);
    				//push
    				var _totDiscA = "'"+_item.totDisc+"'";
    				var _totPpnA = "'"+_item.totPpn+"'";
    				var _totBrutoA = "'"+_item.totJualBruto+"'";
    				var _totNettoA = "'"+_item.totJualNetto+"'";
    				var _ket = "'"+_item.keterangan+"'";
    				
    				
    				$('#tbdPenjualan').append(
    						$('<tr tr data-toggle="modal"  ondblclick = "_setPop('+_id+')"><\/tr>')
    								.append($('<td style="color : Red;font-weight: bold;text-decoration: underline;"><\/td>').html(_item.terkirim))
    								.append($('<td><button id="pushModalButtonAddOrder" type="button" onclick="autoInputOrder('+_id+','+_ppn+')" data-toggle="modal" data-target="#myModalAddOrder" class="btn btn-primary btn-xs">Add Order<\/button> - <button type="button" onclick="orderCall('+_orderNumb+','+_orderValue+')" data-toggle="modal" data-target="#myModalOrderCall" class="btn btn-danger btn-xs">Lihat<\/button><\/td>'))
									.append($('<td><button type="button" onclick="orderCallFaktur('+_orderNumb+','+_penId+','+_fakturNumb+','+_salesCode+','+_totNettoFaktur+','+_totPpnFaktur+','+_totNettoFakturBeforePpn+','+_ket+');" data-toggle="modal" data-target="#myModalFaktur" class="btn btn-danger btn-xs">Cetak<\/button><\/td>'))
									.append($('<td><\/td>').html($.datepicker.formatDate('d MM yy',new Date(_item.orderDate))))
        							.append($('<td><\/td>').html(_item.orderNumb))
        							.append($('<td><\/td>').html(_item.custName))
        							.append($('<td><\/td>').html(_item.fakturNumb))
        							.append($('<td><\/td>').html("Rp "+_totBrutoFaktur))
        							.append($('<td><\/td>').html("Rp "+_totDisc))
        							.append($('<td><\/td>').html("Rp "+_totPpnFakturr))
        							.append($('<td><\/td>').html("Rp "+_totNettoFakturr))
        							//.append($('<td><\/td>').html(_item.custCode))
        							
        							.append($('<td><\/td>').html(_item.custCity))
        							//.append($('<td><\/td>').html(_item.custAddress))
        							.append($('<td><\/td>').html(_item.salesCode))
        							.append($('<td><\/td>').html(_item.keterangan))
        							//.append($('<td><\/td>').html(_item.salesName))
									.append($('<td><button id="pushModalButton" type="button" onclick="pushModalEditPenjualan('+_id+','+_orderDate+','+_orderNumb+','+_fakturNumb+','+_custId+','+_custCode+','+_custName+','+_custCity+','+_custAddress+','+_salesId+','+_salesCode+','+_salesName+','+_totDiscA+','+_totPpnA+','+_totBrutoA+','+_totNettoA+','+_ppn+','+_ket+')" data-toggle="modal" data-target="#myModalEditPenjualan" class="btn btn-primary btn-xs">Edit<\/button> - <button type="button" onclick="penjualanRemoveCall('+_id+');" class="btn btn-danger btn-xs">Delete<\/button><\/td>'))

        				);
        			}
        		};
          		var penjualanCall = function(byT,id, _from, _to) {
          			
          			
          			var _data={};
          		
          			var _By = $('#selByValuePenj').val().trim();
          			var _qe = $('#inpQueryPenj').val().trim();
					if(byT){
						if(byT == 'customer'){
							_By = 'customerId';
							_qe = id;
						}else{
							_By = 'salesId';
							_qe = id;
						}
						document.getElementById("dateFrom").value = _from;
						document.getElementById("dateTo").value = _to;
					}
          			
          			_data['byValuePenj']=_By;
          			_data['queryDataPenj']=_qe;
					_data["dateFrom"] = $('#dateFrom').val().trim();
        			_data["dateTo"] = $('#dateTo').val().trim();
					JSON.post(_data,'${ctx }/json/penjualan',10000,penjualanSent,null,null);
					var _pageTot = penjualanSent.page;
				};
				
				var penjualanAdd = function(data){
					
					if(data.code == 0){
						
						alert(data.message);
						$('#myModalAddPenjualan').modal('hide');
						 
						 
						penjualanCall();
						
						}
						if (data.code != 0) {
							var _items = data.items;
							for ( var i = 0; int < _items.length; i++) {
								document.getElementById("TxtOrderNumb").value = _items[i].fakturNumb;	
							}
							alert(data.message);
						}
						else {
							
						}
						};
				var penjualanEdit = function(data){
					if(data.code == 0){
						alert(data.message);
						$('#myModalEditPenjualan').modal('hide');
						penjualanCall();
						
						}
						if (data.code != 0) {
							
							alert(data.message);
						}
						else {
							
						}
						
						};
						
				var penjualanDelete = function(data){
					if(data.code == 0){
						alert(data.message);
						penjualanCall();
						}
						else {
						}
						};
				function _onloadPenje(){
					var _urlParam = getUrlVars();
					if(_urlParam['by']){
						penjualanCall(_urlParam['by'],_urlParam['id'],_urlParam['from'],_urlParam['to']);
					}else{
						penjualanCall();	
					}
					
				}		
				_onloadPenje();
				function _getTgl(){
					$('#tglFaktur').html($.datepicker.formatDate('d MM yy',new Date()));
				}
		</script>
		
		<script type="text/javascript">
		/*Customer*/
					function autoInputCust(a,b,c,d,e){
						document.getElementById("TxtCodeCustomer").value = a;
						document.getElementById("TxtNameCustomer").value = b;
						document.getElementById("TxtAddrrCustomer").value = c;
						document.getElementById("TxtCityCustomer").value = d;
						document.getElementById("TxtIdCustomer").value = e;
						document.getElementById("TxtCodeCustomerEdit").value = a;
						document.getElementById("TxtNameCustomerEdit").value = b;
						document.getElementById("TxtAddrrCustomerEdit").value = c;
						document.getElementById("TxtCityCustomerEdit").value = d;
						document.getElementById("TxtIdCustomerEdit").value = e;
						$('#myModalCustomer').modal('hide');
						
						}
						
					function firstPageCust(obj){
		       			var _selPage = document.getElementById("selectPageCust");
		       			_selPage.value = 0;
		       			getPageCust(_selPage);
		       		}
		       		function lastPageCust(obj){
		       			var _selPage = document.getElementById("selectPageCust");
		       			var _totPage = document.getElementById("totPageCust").innerHTML *1; 
		       			_selPage.value = _totPage-1;
		       			getPageCust(_selPage);
		       		}
		       		function nextPageCust(obj){
		       			var _selPage = document.getElementById("selectPageCust");
		       			var _currentPage = _selPage.value;
		       			var _totPage = document.getElementById("totPageCust").innerHTML *1; 
		       			if(_totPage == 1){
		       				return;
		       			}
		       			var _setNext = _currentPage*1+1;
		       			if(_setNext <=_totPage-1){
		       				_selPage.value = _setNext;
		           			getPageCust(_selPage);
		       			}
		       		}
					function prevPageCust(obj){
		       			var _selPage = document.getElementById("selectPageCust");
		       			var _currentPage = _selPage.value;
		       			var _totPage = document.getElementById("totPageCust").innerHTML *1; 
		       			if(_totPage == 1){
		       				return;
		       			}
		       			var _setPrev = _currentPage*1-1;
		       			if(_setPrev >=0){
		       				_selPage.value = _setPrev;
		           			getPageCust(_selPage);
		       			}
		       		}
						function getPageCust(obj){
							var _data={};
							_data['page'] = "page"+obj.value;
							JSON.post(_data,'${ctx }/json/getPageCustomer',10000,customerSent,null,null);
							/* setPagging("page"+obj.value,"",""); */
						}
						var customerSent = function(data) {
							document.getElementById("tbdCustomer").innerHTML = "";
	          				if(data.page){
	          					document.getElementById("totPageCust").innerHTML = data.page;
	          					_customPage = "Cust";
	          					 setPagging("1",data.page,"1");
	          				}
							var _items = data.items;
							$('#tbdCustomer').empty();
							for ( var i = 0; i < _items.length; i++) {
								var _item = _items[i];
								//var _tot=_item.hpp*_item.kurs;
								//.html(_tot);
								var _tr = document.createElement("tr");
		        				_tr.setAttribute("onclick", "autoInputCust('"+_item.code+"','"+_item.name+"','"+_item.address1+"','"+_item.city+"','"+_item.id+"')");
		        				$('#tbdCustomer').append(
		        				$(_tr)
		        							.append($('<td class="hidden"><\/td>').html(_item.id))
		        							.append($('<td><\/td>').html(_item.code))
		        							.append($('<td><\/td>').html(_item.name))
		        							.append($('<td><\/td>').html(_item.address1))
		        							.append($('<td><\/td>').html(_item.address2))
		        							.append($('<td><\/td>').html(_item.city))
		        							
		        				);
		        			}
		        		};
						var customerCall = function() {
						var _data={};
						_data['queryData']=$('#inpQueryCust').val().trim();
						_data['byValue']=$('#selByValueCust').val().trim();
						JSON.post(_data,'${ctx }/json/customer',10000,customerSent,null,null);
						var _pageTot = customerSent.page;
						};
					</script>
		
					<script type="text/javascript">
					/*Salesman*/
						function autoInputSlm(a,b,c){
							document.getElementById("TxtIdSalesman").value = a;
							document.getElementById("TxtCodeSalesman").value = b;
							document.getElementById("TxtNameSalesman").value = c;
							document.getElementById("TxtIdSalesmanEdit").value = a;
							document.getElementById("TxtCodeSalesmanEdit").value = b;
							document.getElementById("TxtNameSalesmanEdit").value = c;
							$('#myModalSalesman').modal('hide');
						}	
		
						function firstPageSlm(obj){
			       			var _selPage = document.getElementById("selectPageSlm");
			       			_selPage.value = 0;
			       			getPageSlm(_selPage);
			       		}
			       		function lastPageSlm(obj){
			       			var _selPage = document.getElementById("selectPageSlm");
			       			var _totPage = document.getElementById("totPageSlm").innerHTML *1; 
			       			_selPage.value = _totPage-1;
			       			getPageSlm(_selPage);
			       		}
			       		function nextPageSlm(obj){
			       			var _selPage = document.getElementById("selectPageSlm");
			       			var _currentPage = _selPage.value;
			       			var _totPage = document.getElementById("totPageSlm").innerHTML *1; 
			       			if(_totPage == 1){
			       				return;
			       			}
			       			var _setNext = _currentPage*1+1;
			       			if(_setNext <=_totPage-1){
			       				_selPage.value = _setNext;
			           			getPageSlm(_selPage);
			       			}
			       		}
						function prevPageSlm(obj){
			       			var _selPage = document.getElementById("selectPageSlm");
			       			var _currentPage = _selPage.value;
			       			var _totPage = document.getElementById("totPageSlm").innerHTML *1; 
			       			if(_totPage == 1){
			       				return;
			       			}
			       			var _setPrev = _currentPage*1-1;
			       			if(_setPrev >=0){
			       				_selPage.value = _setPrev;
			           			getPageSlm(_selPage);
			       			}
			       		}
						function getPageSlm(obj){
							var _data={};
							_data['page'] = "page"+obj.value;
							JSON.post(_data,'${ctx }/json/getPageSalesman',10000,salesmanSent,null,null);
							/* setPagging("page"+obj.value,"",""); */
						}
						var salesmanSent = function(data) {
							document.getElementById("tbdSalesman").innerHTML = "";
	          				if(data.page){
	          					document.getElementById("totPageSlm").innerHTML = data.page;
	          					_customPage = "Slm";
	          					 setPagging("1",data.page,"1");
	          				}
							var _items = data.items;
							$('#tbdSalesman').empty();
							for ( var i = 0; i < _items.length; i++) {
								var _item = _items[i];
								//var _tot=_item.hpp*_item.kurs;
								//.html(_tot);
								var _tr = document.createElement("tr");
								_tr.setAttribute("onclick", "autoInputSlm('"+_item.id+"','"+_item.code+"','"+_item.name+"')");
			        			$('#tbdSalesman').append(
			        			$(_tr)
			        							.append($('<td class="hidden"><\/td>').html(_item.id))
			        							.append($('<td><\/td>').html(_item.code))
			        							.append($('<td><\/td>').html(_item.name))
			        							
			        				);
			        			}
			        		};
			        		/* var salesmanCall = function() {
								var _data={};
								_data['queryData']=$('#inpQuery').val().trim();
								_data['byValue']=$('#selByValue').val().trim();
								JSON.post(_data,'${ctx }/json/salesman',10000,salesmanSent,null,null);
								var _pageTot = salesmanSent.page;
							}; */
						var salesmanCall = function() {
						var _data={};
						_data['queryData']=$('#inpQuerySales').val().trim();
						_data['byValue']=$('#selByValueSales').val().trim();
						JSON.post(_data,'${ctx }/json/salesman',10000,salesmanSent,null,null);
						var _pageTot = salesmanSent.page;
							};
					</script>
					
				
		<script type="text/javascript">
		/* UPDATE DATA MODAL */
		function pushEditOrder(_id,_idStock,_idPenjualan,_prodCode,_prodName,_sisaStokEdit,_hargaJualStd,_hargaJualPcs2,_ppn,_totQtyJualCtnEdit,_totQtyJualPcsEdit,_totBrutoFktEdit,_disc,_totNettoFakturBfrPpnEdit,_isiCtnEdit,_isiPcsEdit,_ketOrder){
			_itemId = _id;
			var _data={};
			
			var d = document.getElementById("myModalEditOrder");  //   Javascript
			d.setAttribute('data-id' , _itemId); //
			document.getElementById("TxtIdStockEdit").value = _idStock;
			document.getElementById("TxtIdPenjualanEdit").value = _idPenjualan;
			document.getElementById("TxtProductCodeEdit").value = _prodCode;
			document.getElementById("TxtProductNameEdit").value = _prodName;
			document.getElementById("TxtStokCtnEdit").value = _sisaStokEdit;
			document.getElementById("TxtHargaJualCtnEdit").value = _hargaJualStd;
			document.getElementById("TxtHargaJualPcsEdit").value = _hargaJualPcs2;
			document.getElementById("TxtPpnJualEdit").value = _ppn;
			document.getElementById("TxtQtyOrderCtnEdit").value = _totQtyJualCtnEdit;
			document.getElementById("TxtQtyOrderPcsEdit").value = _totQtyJualPcsEdit;
			document.getElementById("TxtTotalHargaBrutoEdit").value = _totBrutoFktEdit;
			document.getElementById("TxtDiskonEdit").value = _disc;
			document.getElementById("TxtTotalHargaNettoEdit").value = _totNettoFakturBfrPpnEdit;
			document.getElementById("TxtIsiCtnEdit").value = _isiCtnEdit;
			document.getElementById("TxtIsiPcsEdit").value = _isiPcsEdit;
			document.getElementById("TxtKetOrderEdit").value = _ketOrder;
			
			
		}
		
		var orderEditSent = function(data) {
			var recid = document.getElementById("myModalEditOrder");
			var _dataId= recid.getAttribute("data-id");
			var _items = data.items;
			
			for (var i = 0; i < _items.length; i++){
				var _item = _items[i];
				if (_item.id == _dataId) {
					document.getElementById("TxtIdStockEdit").value = _item.stockId;
					document.getElementById("TxtIdPenjualanEdit").value = _item.penjualanId;
					document.getElementById("TxtProductCodeEdit").value = _item.productCode;
					document.getElementById("TxtProductNameEdit").value = _item.productName;
					document.getElementById("TxtStokCtnEdit").value = _item.sisaStokEdit;
					document.getElementById("TxtHargaJualCtnEdit").value = _item.hargaJualCtnStd;
					document.getElementById("TxtHargaJualPcsEdit").value = _item.hargaJualPcs;
					document.getElementById("TxtPpnJualEdit").value = _item.ppn;
					document.getElementById("TxtQtyOrderCtnEdit").value = _item.totQtyJualCtn;
					document.getElementById("TxtQtyOrderPcsEdit").value = _item.totQtyJualPcs;
					document.getElementById("TxtTotalHargaBrutoEdit").value = _item.totJualBrutoIdr;
					document.getElementById("TxtDiskonEdit").value = _item.disc;
					document.getElementById("TxtTotalHargaNettoEdit").value = _item.totJualNettoIdrBeforePpn;
					document.getElementById("TxtKetOrder").value = _item.keterangan;
				}
			}
			
		}
		
		var orderEditCall = function() {
			var _recid = document.getElementById("myModalEditOrder");
			var _dataId= _recid.getAttribute("data-id");
			var _data={};
			_data['id'] = _dataId;
			_data['penjualanEnt']=$('#TxtIdPenjualanEdit').val().trim();
			_data['stockEnt']=$('#TxtIdStockEdit').val().trim();
			_data['totQtyJualCtn']=$('#TxtQtyOrderCtnEdit').val().trim();
			_data['totQtyJualPcs']=$('#TxtQtyOrderPcsEdit').val().trim();
			_data['ppn']=$('#TxtPpnJualEdit').val().trim();
			//_data['hargaJualCtn']=$('#TxtHargaJualCtnEdit').val().trim();
			_data['hargaJualPcs']=$('#TxtHargaJualPcsEdit').val().trim();
			_data['disc']=$('#TxtDiskonEdit').val().trim();
			_data['keterangan']=$('#TxtKetOrderEdit').val().trim();
			
			JSON.post(_data,'${ctx }/json/detpenjualan-mod',10000,orderEdit,null,null);
		};    
		/* DELETE DATA */
			
       		var orderRemoveCall = function(_id,_idStock,_idPenjualan,_totQtyJualIsi2Ctn,_totStokPcs) {
				
       			if(confirm("Do you want to delete ?") == true) {
       			 this.click;
 				var _data={};
 				 _data['id'] = _id;
 				 _data['stockEnt'] =_idStock;
 				 _data['penjualanEnt'] = _idPenjualan; 
 				JSON.post(_data,'${ctx }/json/detpenjualan-del',10000,orderDelete,null,null);
       			}
       		};
       		/*End delete*/
       		</script>
            <script type="text/javascript">
            function autoInputOrder(a,b){
				document.getElementById("TxtIdPenj").value = a;
				document.getElementById("TxtPpnJual").value = b;
				
				document.getElementById("TxtIdStock").value = "";
				document.getElementById("TxtProductCode").value = "";
				document.getElementById("TxtProductName").value = "";
				document.getElementById("TxtStokCtn").value = "0";
				document.getElementById("TxtHargaJualCtn").value = "0";
				document.getElementById("TxtHargaJualPcs").value = "0";
				document.getElementById("TxtQtyOrderCtn").value = "0";
				document.getElementById("TxtQtyOrderPcs").value = "0";
				document.getElementById("TxtTotalHargaBruto").value = "0";
				document.getElementById("TxtDiskon").value = "0";
				document.getElementById("TxtTotalHargaNetto").value = "0";
				
            }
            
            /* Add pembelian */
          	var orderAddSent = function(data){
  				var _items = data.items;
  				for ( var i = 0; i < _items.length; i++) {
  					var _item = _items[i];
  					var _id = "'"+_item.id+"'";
  					var _dataItem = "'"+_item+"'";
  					//var _tot=_item.hpp*_item.kurs;
  					//.html(_tot);
  						$('#tbdOrder').append(
  							$('<tr><\/tr>')
  								//.append($('<td><\/td>').html(_item.productImage))
  									.append($('<td><\/td>').html(_item.orderNumb))
        							.append($('<td><\/td>').html(_item.fakturNumb))
        							.append($('<td><\/td>').html(_item.productCode))
        							.append($('<td><\/td>').html(_item.productName))
        							.append($('<td><\/td>').html(_item.totQtyJualPcs))
        							.append($('<td><\/td>').html(_item.hargaJualPcs))
        							.append($('<td><\/td>').html(_item.totJual))
  							);
  				 
  						} 
  			
  					};
  		
  		
    				var orderAddCall = function() {
						var _data={};
						 
						_data['penjualanEnt']=$('#TxtIdPenj').val().trim();
						_data['stockEnt']=$('#TxtIdStock').val().trim();
						_data['totQtyJualCtn']=$('#TxtQtyOrderCtn').val().trim();
						_data['totQtyJualPcs']=$('#TxtQtyOrderPcs').val().trim();
						
						_data['ppn']=$('#TxtPpnJual').val().trim();
						_data['hargaJualCtn']=$('#TxtHargaJualCtn').val().trim();
						_data['hargaJualPcs']=$('#TxtHargaJualPcs').val().trim();
						_data['disc']=$('#TxtDiskon').val().trim();
						_data['keterangan']=$('#TxtKetOrder').val().trim();
						JSON.post(_data,'${ctx }/json/detpenjualan-add',10000,orderAdd,null,null);
						
					};
					
					function firstPageOrder(obj){
		       			var _selPage = document.getElementById("selectPageOrder");
		       			_selPage.value = 0;
		       			getPageOrder(_selPage);
		       		}
		       		function lastPageOrder(obj){
		       			var _selPage = document.getElementById("selectPageOrder");
		       			var _totPage = document.getElementById("totPageOrder").innerHTML *1; 
		       			_selPage.value = _totPage-1;
		       			getPageOrder(_selPage);
		       		}
		       		function nextPageOrder(obj){
		       			var _selPage = document.getElementById("selectPageOrder");
		       			var _currentPage = _selPage.value;
		       			var _totPage = document.getElementById("totPageOrder").innerHTML *1; 
		       			if(_totPage == 1){
		       				return;
		       			}
		       			var _setNext = _currentPage*1+1;
		       			if(_setNext <=_totPage-1){
		       				_selPage.value = _setNext;
		           			getPageOrder(_selPage);
		       			}
		       		}
					function prevPageOrder(obj){
		       			var _selPage = document.getElementById("selectPageOrder");
		       			var _currentPage = _selPage.value;
		       			var _totPage = document.getElementById("totPageOrder").innerHTML *1; 
		       			if(_totPage == 1){
		       				return;
		       			}
		       			var _setPrev = _currentPage*1-1;
		       			if(_setPrev >=0){
		       				_selPage.value = _setPrev;
		           			getPageOrder(_selPage);
		       			}
		       		}
            function getPageOrder(obj){
				var _data={};
				_data['page'] = "page"+obj.value;
				JSON.post(_data,'${ctx }/json/getPageDetPenjualan',1000000,orderSent,null,null);
				/* setPagging("page"+obj.value,"",""); */
			}
            function setFaktur (data){
            	if(data.code != 2 && data.code != 3){
            		document.getElementById("nomorFaktur").innerHTML = data.message;
            		getNoFaktur("ss");
            	}else{
            		var _mes = data.message;
            		if(data.code == 3){
            			rawPrint(_mes);
            		}else{
            			window.open('../img-sk/'+_mes+'.pdf','popUpWindow','height=600,width=1000,left=10,top=10,,scrollbars=yes,menubar=no');
            		}
            		
					
            	}
            	
            }
            function setSuratJalan (data){
            	
            	var _mes = data.message;
            	
            	window.open('../img-sk/'+_mes+'.pdf','popUpWindow','height=600,width=1000,left=10,top=10,,scrollbars=yes,menubar=no');
            	
            }
            function getSuratJalan(obj){
            	var _noFaktur = document.getElementById("nomorFaktur").innerHTML;
				var _data={};
				
				_data["content"] = JSON.stringify(tableToJson());
            	var _nofaktur = document.getElementById("nomorFaktur").innerHTML;
            	var _date = document.getElementById("tglFaktur").innerHTML;
            	var _custName = document.getElementById("tdCustomerName").innerHTML;
            	var _custAddress = document.getElementById("tdCustomerAddress").innerHTML;
            	var _custCity = document.getElementById("tdCustomerCity").innerHTML;
            	var _sales = document.getElementById("txtSalesCode").innerHTML;
            	var _txtNettoFakturBeforePpn = document.getElementById("txtNettoFakturBeforePpn").value;
            	var _txtPpnFaktur = document.getElementById("txtPpnFaktur").value;
            	var _txtNettoFaktur = document.getElementById("txtNettoFaktur").value;
            	var _txtKeteranganFaktur = document.getElementById("txtKeteranganFaktur").value;
            	
            	
            	 var rowData = [];
            	 rowData.push(_nofaktur);
            	 rowData.push(_date);
            	 rowData.push(_custName);
            	 rowData.push(_custAddress);
            	 rowData.push(_custCity);
            	 rowData.push(_sales);
            	 rowData.push(_txtNettoFakturBeforePpn);
            	 rowData.push(_txtPpnFaktur);
            	 rowData.push(_txtNettoFaktur);
            	 rowData.push(_txtKeteranganFaktur);
            	 _data["destination"] = JSON.stringify(rowData);
            	_data["offset"] = obj.value;
            	_data["typePdf"] = "SURATJALAN";
				JSON.post(_data,'${ctx }/json/getnomorfaktur',1000000,setSuratJalan,null,null);
				/* setPagging("page"+obj.value,"",""); */
			}
            function getNoFaktur(obj){
            	var _noFaktur = document.getElementById("nomorFaktur").innerHTML;
				var _data={};
				if(_noFaktur == "" || _noFaktur == " "){
            		_data["id"] = obj.value;
            	} else{
            		_data["content"] = JSON.stringify(tableToJson());
            	} 
            	var _nofaktur = document.getElementById("nomorFaktur").innerHTML;
            	var _date = document.getElementById("tglFaktur").innerHTML;
            	var _custName = document.getElementById("tdCustomerName").innerHTML;
            	var _custAddress = document.getElementById("tdCustomerAddress").innerHTML;
            	var _custCity = document.getElementById("tdCustomerCity").innerHTML;
            	var _sales = document.getElementById("txtSalesCode").innerHTML;
            	var _txtNettoFakturBeforePpn = document.getElementById("txtNettoFakturBeforePpn").value;
            	var _txtPpnFaktur = document.getElementById("txtPpnFaktur").value;
            	var _txtNettoFaktur = document.getElementById("txtNettoFaktur").value;
            	var _txtKeteranganFaktur = document.getElementById("txtKeteranganFaktur").value;
            	var _txtNameTTD = document.getElementById("txtNameTTD").value;
            	
            	 var rowData = [];
            	 rowData.push(_nofaktur);
            	 rowData.push(_date);
            	 rowData.push(_custName);
            	 rowData.push(_custAddress);
            	 rowData.push(_custCity);
            	 rowData.push(_sales);
            	 rowData.push(_txtNettoFakturBeforePpn);
            	 rowData.push(_txtPpnFaktur);
            	 rowData.push(_txtNettoFaktur);
            	 rowData.push(_txtKeteranganFaktur);
            	 rowData.push(_txtNameTTD);
            	 _data["destination"] = JSON.stringify(rowData);
            	_data["offset"] = obj.value;
				JSON.post(_data,'${ctx }/json/getnomorfaktur',1000000,setFaktur,null,null);
				/* setPagging("page"+obj.value,"",""); */
			}
			var orderSent = function(data) {
				document.getElementById("tbdOrder").innerHTML = "";
  				if(data.page){
  					document.getElementById("totPageOrder").innerHTML = data.page;
  					_customPage = "Order";
  					 setPagging("1",data.page,"1");
  				}
        			var _items = data.items;
        			$('#tbdOrderJual').empty();
        			for ( var i = 0; i < _items.length; i++) 
        			{
        				var _item = _items[i];
        				var _id = "'"+_item.id+"'";
        				var _idStock = "'"+_item.stockId+"'";
        				var _idPenjualan = "'"+_item.penjualanId+"'";
        				var _prodCode = "'"+_item.productCode+"'";
        				var _prodName = "'"+_item.productName+"'";
        				var _sisaStokEdit = "'"+_item.sisaStokEdit+"'";
        				var _hargaJualStd = "'"+_item.hargaJualCtnStd+"'";
        				var _totQtyJualCtnEdit = "'"+_item.totQtyJualCtn+"'";
        				var _totQtyJualPcsEdit = "'"+_item.totQtyJualPcs+"'";
        				var _totBrutoFktEdit = "'"+_item.totJualBrutoIdr+"'";
        				var _hargaJualPcs2 = "'"+_item.hargaJualPcs+"'";
        				var _totNettoFakturBfrPpnEdit = "'"+_item.totJualNettoIdrBeforePpn+"'";
        				var _ppn = "'"+_item.ppn+"'";
        				var _disc = "'"+_item.disc+"'";
        				var _isiCtnEdit = "'"+_item.isiCtn+"'";
        				var _isiPcsEdit = "'"+_item.isiPcs+"'";
        				
        				var _idPenjualan = "'"+_item.penjualanId+"'";
        				var _totQtyJualCtn = convertToItem(_item.totQtyJualCtn);
        				var _totQtyJualPcs = convertToItem(_item.totQtyJualPcs);
        				var _hargaJualPcs = convertToMoney(_item.hargaJualPcs);
        				var _hargaJualPcsDisc = convertToMoney(_item.hargaJualPcsDisc);
        				var _totBrutoFaktur = convertToMoney(_item.totJualBrutoIdr);
        				var _totNettoFakturBeforePpn = convertToMoney(_item.totJualNettoIdrBeforePpn);
        				var _totNettoFaktur = convertToMoney(_item.totJualNettoIdr);
        				var _ketOrder = "'"+_item.keterangan+"'";
        				
        				
        				//var _tot=_item.hpp*_item.kurs;
        				//.html(_tot);
        				$('#tbdOrder').append(
        						$('<tr><\/tr>')
        							.append($('<td><\/td>').html(_item.productCode))
        							.append($('<td><\/td>').html(_item.productName))
        							.append($('<td><\/td>').html(_totQtyJualCtn+" Ctn"))
        							.append($('<td><\/td>').html(_totQtyJualPcs+" pcs"))
        							.append($('<td><\/td>').html("Rp "+_hargaJualPcs))
        							.append($('<td><\/td>').html("Rp "+_totBrutoFaktur))
        							.append($('<td><\/td>').html(_item.disc+"%"))
        							.append($('<td><\/td>').html(_item.ppn+"%"))
        							.append($('<td><\/td>').html("Rp "+_totNettoFaktur))
        							.append($('<td style="color : Red;font-weight: bold;"><\/td>').html(_item.keterangan))
        							.append($('<td><button id="pushEditOrderButton" type="button" onclick="pushEditOrder('+_id+','+_idStock+','+_idPenjualan+','+_prodCode+','+_prodName+','+_sisaStokEdit+','+_hargaJualStd+','+_hargaJualPcs2+','+_ppn+','+_totQtyJualCtnEdit+','+_totQtyJualPcsEdit+','+_totBrutoFktEdit+','+_disc+','+_totNettoFakturBfrPpnEdit+','+_isiCtnEdit+','+_isiPcsEdit+','+_ketOrder+')" data-toggle="modal" data-target="#myModalEditOrder" class="btn btn-primary btn-xs">Edit<\/button> - <button type="button" onclick="orderRemoveCall('+_id+','+_idStock+','+_idPenjualan+');" class="btn btn-danger btn-xs"><i class="fa fa-trash-o "><\/i><\/button><\/td>'))
        								
        				);
        			}
        		};
        		
        		// PRINT START
        		var orderSentFaktur = function(data) {
    				document.getElementById("tbdFaktur").innerHTML = "";
      				
            			var _items = data.items;
            			//alert(_items.length+" jumlah data");
            			var _noCtn = "";
            			var _countCtn = 0;
            			for ( var i = 0; i <= _items.length; i++) 
            			{	var _item = _items[i];
            				if(i==0){
            					document.getElementById("tdCustomerName").innerHTML = _item.customerName;
            					document.getElementById("tdCustomerAddress").innerHTML = _item.customerAddr;
            					document.getElementById("tdCustomerCity").innerHTML = _item.customerCity;
            				}
            				
            				var _id = "'"+_item.id+"'";
            				var _idStock = "'"+_item.stockId+"'";
            				var _idPenjualan = "'"+_item.penjualanId+"'";
            				var _rpHargaJualPcs = convertToMoney(_item.hargaJualPcs);
            				var _rpHargaJualPcsDisc = convertToMoney(_item.hargaJualPcsDisc);
            				var _rpTotJualNetto = convertToMoney(_item.totJualNettoIdr);
            				var _rpTotNettoFakturBeforePpn = convertToMoney(_item.totJualNettoIdrBeforePpn);
            				var _ctn = _item.totQtyJualCtn;
            				var _totQtyJualPcsConv = convertToItem(_item.totQtyJualPcs);
            				if(i == 0){
            					if(_ctn != 1){
            						_noCtn = '1-'+_ctn;	
            						_countCtn = _ctn;
            					}else{
            						_noCtn = '1';
            						_countCtn = 1;
            					}
            				}else{
            					if(_ctn == 1){
            						_noCtn = _countCtn*1+1;
            						_countCtn = _countCtn*1+1;
            					}else{
            						
            						var _a = _countCtn+_ctn-1+1;
            						_noCtn = _countCtn*1-1+2+"-"+_a;
            						_countCtn = _countCtn*1;
            						_countCtn = _countCtn + _ctn *1;
            					}
            				}
            				//var _tot=_item.hpp*_item.kurs;
            				//.html(_tot);
            				 var _inputBanyakPCS =document.createElement("input");
            				_inputBanyakPCS.value = _totQtyJualPcsConv;
            				var _inputNetto = document.createElement("input");
            				_inputNetto.value =_rpTotNettoFakturBeforePpn; 
            				$('#tbdFaktur').append(
            						$('<tr><\/tr>')
            							
            							/* .append($('<td><\/td>').html("No. "+_noCtn))
            							.append($('<td><\/td>').html(_inputBanyakPCS))
            							.append($('<td><\/td>').html(_ctn+" CTN"))
            							.append($('<td><\/td>').html(_item.productCode))
            							.append($('<td><\/td>').html(_item.productName))
            							.append($('<td><\/td>').html("Rp "+_rpHargaJualPcs))
            							.append($('<td><\/td>').html(_item.disc+" %"))
            							.append($('<td><\/td>').html("Rp "+_rpHargaJualPcsDisc))
            							.append($('<td><\/td>').html(_inputNetto)) */
            							.append($('<td><\/td>').html(_noCtn))
            							.append($('<td><\/td>').html(_inputBanyakPCS))
            							.append($('<td><\/td>').html(_ctn))
            							.append($('<td><\/td>').html(_item.productCode))
            							.append($('<td><\/td>').html(_item.productName))
            							.append($('<td><\/td>').html(_rpHargaJualPcs))
            							.append($('<td><\/td>').html(_item.disc))
            							.append($('<td><\/td>').html(_rpHargaJualPcsDisc))
            							.append($('<td><\/td>').html(_inputNetto))
            							
            							
            				);
            				
            			}
            		};
        		//end print
        		var orderAdd = function(data){
					
					if(data.code == 0){
						alert(data.message);
						$('#myModalAddOrder').modal('hide');
						orderCall();
						penjualanCall();
						}
						if (data.code != 0) {
							
							alert(data.message);
						}
						else {
							
						}
						};
				var orderEdit = function(data){
							
					if(data.code == 0){
						alert(data.message);
						$('#myModalEditOrder').modal('hide');
						var _items = data.items;
						var _item = _items[0];
						orderCall(_item.orderNumb,'orderNumb');
						penjualanCall();			
						}
						if (data.code != 0) {
											
							alert(data.message);
						}
										
						};			
						
				var orderDelete = function(data){
				if(data.code == 0){
					alert(data.message);
					//$('#myModalOrderCall').modal('hide');
					var _items = data.items;
					var _item = _items[0];
					orderCall(_item.orderNumb,'orderNumb');
					penjualanCall();			
					}
					if (data.code != 0) {
					
					alert(data.message);
					}
					else {
					
					}
					
					};
        		var orderCall = function(_orderNumb,_orderValue) {
          			var _data={};
          			/* var _a = document.getElementById(_id).value;
          			if(_a == ""){
          				return;
          			} */
          			_data['queryDataOrder']=_orderNumb;
					_data['byValueOrder']= _orderValue;
					
					JSON.post(_data,'${ctx }/json/detpenjualan',10000,orderSent,null,null);
        		};
        		var orderCallFaktur = function(_orderNo,_pen,_faktur,_salesCode,_totNettoFaktur,_totPpnFaktur,_totNettoFakturBeforePpn,_ket) {
          			var _data={};
          			/* var _a = document.getElementById(_id).value;
          			if(_a == ""){
          				return;
          			} */
          			document.getElementById("txtSalesCode").innerHTML = _salesCode;
          			document.getElementById("txtNettoFaktur").value = _totNettoFaktur;
          			document.getElementById("txtPpnFaktur").value = _totPpnFaktur;
          			document.getElementById("txtNettoFakturBeforePpn").value = _totNettoFakturBeforePpn;
          			document.getElementById("txtKeteranganFaktur").value = _ket;
          			document.getElementById("printPdf").value = "";
          			document.getElementById("nomorFaktur").innerHTML = "";
          			if(_faktur == "" || _faktur == " " ){
          				document.getElementById("printPdf").value = _pen;
          			}else{
          				if(_faktur != "undefined"){
          					document.getElementById("nomorFaktur").innerHTML = _faktur;
          				}else{
          					document.getElementById("printPdf").value = _pen;
          				}
          				
          			}
          			document.getElementById("printPdfSuratJalan").value = _pen;
          			_data [''];
          			_data['queryDataOrder']=_orderNo;
					_data['byValueOrder']= "orderNumb";
					_data['fakturChek'] = "check";
					JSON.post(_data,'${ctx }/json/detpenjualan',10000,orderSentFaktur,null,null);
					_getTgl();
					
        		};
        		
			</script>
		
				
				<script type="text/javascript">
				function autoInputProd(a,b,c,d,e,f,g,h){
					document.getElementById("TxtProductCode").value = a;
					document.getElementById("TxtProductName").value = b;
					document.getElementById("TxtStokCtn").value = c;
					document.getElementById("TxtIdStock").value = d;
					document.getElementById("TxtHargaJualCtn").value = e;
					document.getElementById("TxtHargaJualPcs").value = f;
					document.getElementById("TxtIsiCtn").value = g;
					document.getElementById("TxtIsiPcs").value = h;
					
					document.getElementById("TxtProductCodeEdit").value = a;
					document.getElementById("TxtProductNameEdit").value = b;
					document.getElementById("TxtStokCtnEdit").value = c;
					document.getElementById("TxtIdStockEdit").value = d;
					document.getElementById("TxtHargaJualCtnEdit").value = e;
					document.getElementById("TxtHargaJualPcsEdit").value = f;
					document.getElementById("TxtIsiCtnEdit").value = g;
					document.getElementById("TxtIsiPcsEdit").value = h;
					//document.getElementById("TxtHargaJualPcsEdit").value = f;
					
					
					
					$('#myModalProduct').modal('hide');
					
				}
				/* Start Paging Barang */
				function getPageStock(obj){
				var _data={};
				_data['page'] = "page"+obj.value;
				JSON.post(_data,'${ctx }/json/getPageStock',10000,productSent,null,null);
				/* setPagging("page"+obj.value,"",""); */
				}
			
				/* End Paging Barang */
			
				/* Start Call Data Barang */
				var productSent = function(data) {
					var _items = data.items;
          			if(_items.length == 0){
          				var _data={};
        				_data['page'] = "page1";
        				JSON.post(_data,'${ctx }/json/getPageStock',10000,productSent,null,null);
          				return;
          			}
				document.getElementById("tbdProduct").innerHTML = "";
  				if(data.page){
  					document.getElementById("totPageStock").innerHTML = data.page;
  					_customPage = "Stock";
  					 setPagging("1",data.page,"1");
  				}
				$('#tbdProduct').empty();
				for ( var i = 0; i < _items.length; i++) {
					var _item = _items[i];
					//var _tot=_item.hpp*_item.kurs;
					//.html(_tot);
					var _tr = document.createElement("tr");
    				_tr.setAttribute("onclick", "autoInputProd('"+_item.productCode+"','"+_item.productName+"','"+_item.stokCtn+"','"+_item.stockId+"','"+_item.hargaJualCtn+"','"+_item.hargaJualPcs+"','"+_item.isiCtn+"','"+_item.isiPcs+"')");
    				$('#tbdProduct').append(
    				$(_tr)
	        						
        							.append($('<td><\/td>').html(_item.productCode))
        							.append($('<td><\/td>').html(_item.productName))
        							.append($('<td><\/td>').html(_item.stokCtn))
        							.append($('<td><\/td>').html(_item.isiCtn))
									.append($('<td><\/td>').html(_item.satIsiCtn))
									.append($('<td><\/td>').html(_item.isiPcs))
									
									.append($('<td><\/td>').html(_item.hargaJualCtn))
									.append($('<td><\/td>').html(_item.hargaJualPcs))
        							
        							);
        				}
        			};
          			var productCall = function() {
					var _data={};
					_data['queryData']=$('#inpQuery').val().trim();
					_data['byValue']=$('#selByValue').val().trim();
					JSON.post(_data,'${ctx }/json/stock',10000,productSent,null,null);
					};
				
				/* End Call Data Barang */
				</script>
				
			<!-- auto Calculate -->
			<script type="text/javascript">
			<!-- QtyBeli*IsiKarton)-->
				$('#TxtQtyOrderPcs,#TxtHargaJualPcs').on('input',function() 
				{
		    		var TxtQtyOrderPcs = parseInt($('#TxtQtyOrderPcs').val());
		   			var TxtHargaJualPcs = parseFloat($('#TxtHargaJualPcs').val());
		   			$('#TxtTotalHargaBruto').val((TxtQtyOrderPcs * TxtHargaJualPcs  ? TxtQtyOrderPcs * TxtHargaJualPcs : 0).toFixed(2));
				});
				
				$('#TxtQtyOrderCtn,#TxtIsiCtn,#TxtIsiPcs').on('input',function() 
						{
					    	var TxtIsiCtn = parseInt($('#TxtIsiCtn').val());
					    	var TxtIsiPcs = parseInt($('#TxtIsiPcs').val());
					   		var TxtQtyOrderCtn = parseFloat($('#TxtQtyOrderCtn').val());
					   		$('#TxtQtyOrderPcs').val((TxtQtyOrderCtn*TxtIsiCtn*TxtIsiPcs  ? TxtQtyOrderCtn*TxtIsiCtn*TxtIsiPcs : 0).toFixed(2));
						});
				
				$('#TxtIsiCtn,#TxtIsiPcs,#TxtHargaJualPcs').on('input',function() 
					{
				    	var TxtIsiCtn = parseInt($('#TxtIsiCtn').val());
				    	var TxtIsiPcs = parseInt($('#TxtIsiPcs').val());
				   		var TxtHargaJualPcs = parseFloat($('#TxtHargaJualPcs').val());
				   		$('#TxtHargaJualCtn').val((TxtHargaJualPcs*(TxtIsiCtn*TxtIsiPcs)  ? TxtHargaJualPcs*(TxtIsiCtn*TxtIsiPcs) : 0).toFixed(2));
					});
				
				$('#TxtQtyOrderPcs, #TxtHargaJualPcs, #TxtDiskon').on('input',function() 
				{
				    var TxtQtyOrderPcs = parseInt($('#TxtQtyOrderPcs').val());
				    var TxtHargaJualPcs = parseFloat($('#TxtHargaJualPcs').val());
				   	var TxtDiskon = parseFloat($('#TxtDiskon').val());
				   	$('#TxtTotalHargaNetto').val(((TxtQtyOrderPcs * TxtHargaJualPcs) - (TxtQtyOrderPcs * TxtHargaJualPcs) * (TxtDiskon * 0.01) ? (TxtQtyOrderPcs * TxtHargaJualPcs) - (TxtQtyOrderPcs * TxtHargaJualPcs) * (TxtDiskon * 0.01) : 0).toFixed(2));
				});
				
				$('#TxtQtyOrderCtn,#TxtHargaJualCtn').on('input',function() 
						{
						    var TxtQtyOrderCtn = parseInt($('#TxtQtyOrderCtn').val());
						   	var TxtHargaJualCtn = parseFloat($('#TxtHargaJualCtn').val());
						   	$('#TxtTotalHargaBruto').val((TxtQtyOrderCtn * TxtHargaJualCtn  ? TxtQtyOrderCtn * TxtHargaJualCtn : 0).toFixed(2));
					});
				
				$('#TxtQtyOrderCtn, #TxtHargaJualCtn, #TxtDiskon').on('input',function() 
						{
						    var TxtQtyOrderCtn = parseInt($('#TxtQtyOrderCtn').val());
						    var TxtHargaJualCtn = parseFloat($('#TxtHargaJualCtn').val());
						   	var TxtDiskon = parseFloat($('#TxtDiskon').val());
						   	$('#TxtTotalHargaNetto').val(((TxtQtyOrderCtn * TxtHargaJualCtn) - (TxtQtyOrderCtn * TxtHargaJualCtn) * (TxtDiskon * 0.01) ? (TxtQtyOrderCtn * TxtHargaJualCtn) - (TxtQtyOrderCtn * TxtHargaJualCtn) * (TxtDiskon * 0.01) : 0).toFixed(2));
						});
				
				$('#TxtQtyOrderPcs,#TxtHargaJualPcs').on('input',function() 
						{
						    var TxtQtyOrderPcs = parseInt($('#TxtQtyOrderPcs').val());
						   	var TxtHargaJualPcs = parseFloat($('#TxtHargaJualPcs').val());
						   	$('#TxtTotalHargaBruto').val((TxtQtyOrderPcs * TxtHargaJualPcs  ? TxtQtyOrderPcs * TxtHargaJualPcs : 0).toFixed(2));
					});
				
				$('#TxtQtyOrderPcs, #TxtHargaJualPcs, #TxtDiskon').on('input',function() 
						{
						    var TxtQtyOrderPcs = parseInt($('#TxtQtyOrderPcs').val());
						    var TxtHargaJualPcs = parseFloat($('#TxtHargaJualPcs').val());
						   	var TxtDiskon = parseFloat($('#TxtDiskon').val());
						   	$('#TxtTotalHargaNetto').val(((TxtQtyOrderPcs * TxtHargaJualPcs) - (TxtQtyOrderPcs * TxtHargaJualPcs) * (TxtDiskon * 0.01) ? (TxtQtyOrderPcs * TxtHargaJualPcs) - (TxtQtyOrderPcs * TxtHargaJualPcs) * (TxtDiskon * 0.01) : 0).toFixed(2));
						});
				
				
				/*EDIT ORDER  */
				$('#TxtIsiCtnEdit,#TxtIsiPcsEdit,#TxtHargaJualPcsEdit').on('input',function() 
						{
					    	var TxtIsiCtnEdit = parseInt($('#TxtIsiCtnEdit').val());
					    	var TxtIsiPcsEdit = parseInt($('#TxtIsiPcsEdit').val());
					   		var TxtHargaJualPcsEdit = parseFloat($('#TxtHargaJualPcsEdit').val());
					   		$('#TxtHargaJualCtnEdit').val((TxtHargaJualPcsEdit*(TxtIsiCtnEdit*TxtIsiPcsEdit)  ? TxtHargaJualPcsEdit*(TxtIsiCtnEdit*TxtIsiPcsEdit) : 0).toFixed(2));
						});
				$('#TxtQtyOrderCtnEdit,#TxtHargaJualCtnEdit').on('input',function() 
					{
						    var TxtQtyOrderCtnEdit = parseInt($('#TxtQtyOrderCtnEdit').val());
						   	var TxtHargaJualCtnEdit = parseFloat($('#TxtHargaJualCtnEdit').val());
						   	$('#TxtTotalHargaBrutoEdit').val((TxtQtyOrderCtnEdit * TxtHargaJualCtnEdit  ? TxtQtyOrderCtnEdit * TxtHargaJualCtnEdit : 0).toFixed(2));
					});
				
				$('#TxtQtyOrderCtnEdit,#TxtIsiPcsEdit,#TxtIsiCtnEdit').on('input',function() 
						{
						    var TxtQtyOrderCtnEdit = parseInt($('#TxtQtyOrderCtnEdit').val());
						   	var TxtIsiCtnEdit = parseFloat($('#TxtIsiCtnEdit').val());
						   	var TxtIsiPcsEdit = parseInt($('#TxtIsiPcsEdit').val());
						   
						   	$('#TxtQtyOrderPcsEdit').val((TxtQtyOrderCtnEdit * TxtIsiCtnEdit * TxtIsiPcsEdit ? TxtQtyOrderCtnEdit * TxtIsiCtnEdit * TxtIsiPcsEdit : 0).toFixed(2));
						});
				
				$('#TxtQtyOrderCtnEdit, #TxtHargaJualCtnEdit, #TxtDiskonEdit').on('input',function() 
						{
							var TxtQtyOrderCtnEdit = parseInt($('#TxtQtyOrderCtnEdit').val());
							var TxtHargaJualCtnEdit = parseFloat($('#TxtHargaJualCtnEdit').val());
							var TxtDiskonEdit = parseFloat($('#TxtDiskonEdit').val());
							$('#TxtTotalHargaNettoEdit').val(((TxtQtyOrderCtnEdit * TxtHargaJualCtnEdit) - (TxtQtyOrderCtnEdit * TxtHargaJualCtnEdit) * (TxtDiskonEdit * 0.01) ? (TxtQtyOrderCtnEdit * TxtHargaJualCtnEdit) - (TxtQtyOrderCtnEdit * TxtHargaJualCtnEdit) * (TxtDiskonEdit * 0.01) : 0).toFixed(2));
						});
				
				$('#TxtQtyOrderPcsEdit,#TxtHargaJualPcsEdit').on('input',function() 
				{
				    var TxtQtyOrderPcsEdit = parseInt($('#TxtQtyOrderPcsEdit').val());
				   	var TxtHargaJualPcsEdit = parseFloat($('#TxtHargaJualPcsEdit').val());
				   	$('#TxtTotalHargaBrutoEdit').val((TxtQtyOrderPcsEdit * TxtHargaJualPcsEdit  ? TxtQtyOrderPcsEdit * TxtHargaJualPcsEdit : 0).toFixed(2));
				});
						
				$('#TxtQtyOrderPcsEdit, #TxtHargaJualPcsEdit, #TxtDiskonEdit').on('input',function() 
				{
					var TxtQtyOrderPcsEdit = parseInt($('#TxtQtyOrderPcsEdit').val());
					var TxtHargaJualPcsEdit = parseFloat($('#TxtHargaJualPcsEdit').val());
					var TxtDiskonEdit = parseFloat($('#TxtDiskonEdit').val());
					$('#TxtTotalHargaNettoEdit').val(((TxtQtyOrderPcsEdit * TxtHargaJualPcsEdit) - (TxtQtyOrderPcsEdit * TxtHargaJualPcsEdit) * (TxtDiskonEdit * 0.01) ? (TxtQtyOrderPcsEdit * TxtHargaJualPcsEdit) - (TxtQtyOrderPcsEdit * TxtHargaJualPcsEdit) * (TxtDiskonEdit * 0.01) : 0).toFixed(2));
				});
				
				$('#TxtQtyOrderPcsEdit,#TxtHargaJualPcsEdit').on('input',function() 
						{
						    var TxtQtyOrderPcsEdit = parseInt($('#TxtQtyOrderPcsEdit').val());
						   	var TxtHargaJualPcsEdit = parseFloat($('#TxtHargaJualPcsEdit').val());
						   	$('#TxtTotalHargaBrutoEdit').val((TxtQtyOrderPcsEdit * TxtHargaJualPcsEdit  ? TxtQtyOrderPcsEdit * TxtHargaJualPcsEdit : 0).toFixed(2));
					});
				
				$('#TxtQtyOrderPcsEdit, #TxtHargaJualPcsEdit, #TxtDiskonEdit').on('input',function() 
						{
						    var TxtQtyOrderPcsEdit = parseInt($('#TxtQtyOrderPcsEdit').val());
						    var TxtHargaJualPcsEdit = parseFloat($('#TxtHargaJualPcsEdit').val());
						   	var TxtDiskonEdit = parseFloat($('#TxtDiskonEdit').val());
						   	$('#TxtTotalHargaNettoEdit').val(((TxtQtyOrderPcsEdit * TxtHargaJualPcsEdit) - (TxtQtyOrderPcsEdit * TxtHargaJualPcsEdit) * (TxtDiskonEdit * 0.01) ? (TxtQtyOrderPcsEdit * TxtHargaJualPcsEdit) - (TxtQtyOrderPcsEdit * TxtHargaJualPcsEdit) * (TxtDiskonEdit * 0.01) : 0).toFixed(2));
						});
				
			
			</script>
			<script type="text/javascript">
          
          
          function tableToJson(){
        	  var dataJson = [];
        	  var tableFak = document.getElementById("tbdFaktur");
        	 
        	  for ( var i = 0; i < tableFak.rows.length; i++) {
        		  var tableCell = tableFak.rows[i];
        		  var rowData = [];
        		  	for ( var j = 0; j < tableCell.cells.length; j++) {
        		  		if(j==1 || j==8){
        		  			rowData.push(tableCell.cells[j].firstChild.value);
        		  		}else{
        		  			rowData.push(tableCell.cells[j].innerHTML);
        		  		}
        		  								
					}
				dataJson.push(rowData);
			}
        	  return dataJson;
          }
          function plusSpace(ob, lg){
        	  var _ret = ob;
        	  var n = ob.length;
        	  var _sc = lg - ob.length;
        	  for ( var i = 0; i < _sc; i++) {
        		  _ret = _ret+" ";
			}
        	  return _ret;
          }
          function cetakPDF(){
        	 var doc = new jsPDF('p', 'pt','letter');
        	var header = ['  No.\n  CTN', '   Jum.\n  (PCS)', '  Ctn', '    Kode barang', '            Nama barang', '    Harga/Pcs', 'Disc\n   %','    H.Nett/Pcs', '     Harga Netto'];
        	var dataBody = tableToJson();
        	/*  alert(JSON.stringify(dataBody));
        	return;  */
        	var _nofaktur = document.getElementById("nomorFaktur").innerHTML;
        	var _date = document.getElementById("tglFaktur").innerHTML;
        	var _sales = document.getElementById("txtSalesCode").innerHTML;
        	
        	var _totNettoBeforePpn = document.getElementById("txtNettoFakturBeforePpn").value;
        	var _totNetto = document.getElementById("txtNettoFaktur").value;
        	var _totPpnFaktur = document.getElementById("txtPpnFaktur").value;
        	var judul = "INVOICE";
        	doc.setFontSize(16);
        	doc.text(judul,210,doc.autoTableEndPosY() + 40);
        	var _line = "=======================================================================================\n";
        	var _newLine = _line.substring(0,44);
        	var _pipe ="";
        	for(var i = 0;i<=48;i++){
        		_pipe=" "+_pipe;
        	}
        	var _pipes ="";
        	for(var i = 0;i<=32;i++){
        		_pipes=" "+_pipes;
        	}
        	var _allNofaktur = " NO FAKTUR      :"+_nofaktur;
        	var _dateAll =     "\n Tanggal Faktur   :"+_date;
        	var _custName = document.getElementById("tdCustomerName").innerHTML;
        	var _custAddress = document.getElementById("tdCustomerAddress").innerHTML;
        	var _custCity = document.getElementById("tdCustomerCity").innerHTML;
            var _textHeader = _line+ plusSpace(_allNofaktur,_newLine.length)+_pipe+plusSpace(_dateAll, _newLine.length)+_pipes+"\n"+_newLine+"\n "+"S              :"+_sales+"\n"+_line;
			var _customer = " Kepada Yth,\n "+_custName+"\n "+_custAddress+" \n "+ _custCity+",";
        			doc.setFontSize(10);
        			doc.text(_textHeader,40,doc.autoTableEndPosY() + 60);
        			doc.text(_customer, 300,doc.autoTableEndPosY() + 70);
        			doc.text("||",295,doc.autoTableEndPosY() + 65);
        			doc.text("||",295,doc.autoTableEndPosY() + 70);
        			doc.text("||",295,doc.autoTableEndPosY() + 75);
        			doc.text("||",295,doc.autoTableEndPosY() + 80);
        			doc.text("||",295,doc.autoTableEndPosY() + 85);
        			doc.text("||",295,doc.autoTableEndPosY() + 90);
        			doc.text("||",295,doc.autoTableEndPosY() + 95);
        			doc.text("||",295,doc.autoTableEndPosY() + 100);
        			doc.text("||",295,doc.autoTableEndPosY() + 105);
        			doc.text("||",295,doc.autoTableEndPosY() + 110);
        			
        	      	/* doc.autoTable(header, dataBody,{
        	      		startY: doc.autoTableEndPosY() + 120, theme:"grid",
        	      		headerStyles:{fillColor:[255,255,255],textColor:[0,0,0],lineColor:[0,0,0],lineWidth:0.1}
        	      			
        	      	}); */
        	      	doc.autoTable(header, dataBody,{
        	      		startY: doc.autoTableEndPosY() + 120, theme:"grid",
        	      		headerStyles:{fillColor:[255,255,255],textColor:[0,0,0],lineColor:[0,0,0],lineWidth:0.1,fontSize:8},
	        	      	columnStyles: {
	        	      		
	        	        	0: {columnWidth: 35,fontSize:7},
	        	    		1: {columnWidth: 40,fontSize:7},
	        	        	2: {columnWidth: 35,fontSize:7},
	        	        	3: {columnWidth: 75,fontSize:7},
	        	        	4: {columnWidth: 110,fontSize:7},
	        	        	5: {fontSize:7},
	        	        	6: {columnWidth: 30,fontSize:7},
	        	        	7: {fontSize:7},
	        	        	8: {fontSize:7}
	        	        	/*8: {fontSize:6} */
	        	        }
        	      			
        	      	});
        	      	doc.text( "  Total = RP "+_totNettoBeforePpn+"",450,doc.autoTableEndPosY() + 20);
        	      	if(parseInt( _totPpnFaktur) > 0){
        	      		doc.text( "  PPN  = RP "+_totPpnFaktur+"",450,doc.autoTableEndPosY() + 30);
        	      	}
        	      	
        	      	
        	      	doc.text( "  Total Netto = RP "+_totNetto+"",420,doc.autoTableEndPosY() + 40);
        	      	doc.text( "  ==========================",420,doc.autoTableEndPosY() + 47);
        	      	doc.output('datauri');
        	    	
        	          if(type == 'datauri') {
        	        	  window.open('data:application/pdf;base64,' + Base64.encode(buffer));
        	        	}
        	      }
        </script>
        <script type="text/javascript">
		// Tampilan Currency
			function convertToMoney(n, currency){
			
				return  " " + n.toFixed(0).replace(/./g, function(c, i, a) {
		        return i > 0 && c !== "." && (a.length - i) % 3 === 0 ? "," + c : c;
		    	});
				}
			function convertToItem(n, currency){
					
				return  " " + n.toFixed(0).replace(/./g, function(c, i, a) {
		        return i > 0 && c !== "," && (a.length - i) % 3 === 0 ? "." + c : c;
		    	});
		
				}
			</script>
			
			<!-- Print penjualan to EXCEL -->
			<script type="text/javascript">
			var penjualanPrint = function() {
		        			var _data={};
		        			_data["dateFrom"] = $('#dateFrom').val().trim();
		        			_data["dateTo"] = $('#dateTo').val().trim();
		        			_data["actiontype"] = "DOWNLOADEXCEL";
		        			_data["orderNumb"] = ("");
							JSON.post(_data,'${ctx }/json/penjualanprint',100000,penjualanSentPrint,null,null);
							 var _pageTot = penjualanSent.page; 
							
						};
						var penjualanSentPrint = function(data) {
							var _mes = data.message;
							if(_mes == "Kode Penjualan tidak ditemukan"){
								alert(_mes);
								return;
							}
							window.location.replace('../img-sk/'+_mes);
						};
						</script>
			<script type="text/javascript">
          /*Date Picker*/
          $(function()
        		  {
          	$('#DateOrder,#DateOrderEdit').datepicker().datepicker("setDate", new Date());
          	
          	
          	$('#dateFrom,#dateTo').datepicker();
    		});
          
          </script>
          
          
          <script type="text/javascript">
			$(document).ready(function(){
     		
				$("#hide").click(function(){
					$("#searchPenjualan").addClass("hidden");
				}); 
				
				$("#show").click(function(){
					$("#searchPenjualan").removeClass("hidden");      
				});
     		
			});
			
		</script>
  </body>
</html>
