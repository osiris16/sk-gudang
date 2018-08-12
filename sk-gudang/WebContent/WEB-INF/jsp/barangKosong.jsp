<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="d" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">

<style>
.profile-pic{
  width:200px;
  max-height:200px;
}
.prevGambar, caption{
  display:block;
  margin:0 auto;
}
</style>


<body>
          	<h3><i class="fa fa-angle-right"></i> Stock Product Kosong --- Updated</h3>
            
            
            <!-- Pagging -->
            
            <select onchange="getPage(this)"; id="selectPage" class="hidden">
            
            </select>
           
            <label id = "totPage" class="hidden"></label>
            
            <!-- page end --> 
               
            <div style="border:1px solid white;height:400px;width:100%;overflow-y:scroll;">
		  	<div class="row mt">
			<div class="col-lg-12">
              <section id="unseen">
               <!--Table Barang-->
               
               <table class="table1 table-bordered table-striped table-condensed table-hover">
                      <thead>
                          <tr>
                       		<th> Gambar</th>
                       		<th> No Trip</th>
                      		<th> Barcode</th>
                            <th> Kode</th>
                            <th> No. Pabrik</th>
                            <th> Nama </th>
                            <th> Merek</th>
                            <th> Jenis</th>
                            <th> Made in</th>
                       		<th> No. SNI </th>
                       		<th> Isi CTN</th>
                       		<th> Isi PCS</th>
                       		<th> Stok CTN </th>
                       		<!-- <th> Stok(CTN)</th> -->
                       		<th> Harga Jual/CTN</th>
                       		<th> Harga Jual/PCS</th>
                            
                          </tr>
                       </thead>
                       <tbody id="tbdProduct">
                       </tbody>
                       </table>
                 </section>
                 </div><!--content-panel-->
                 </div><!--col-lg-4 -->
                 </div><!--row -->
                 
		  	
      				
 <!--Modal Tabel Form add Barang -->
   <div class="modal fade" id="myModalAddProduct" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    	<div class="modal-dialog modal-lg">
        	<div class="modal-content">
            	<div class="modal-header">
            	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
				</button>
            
            	<h4 class="modal-title" id="myModalLabel">Add Product</h4>
				</div>
			
				<div style="border:1px solid white;height:500px;width:100%;overflow-x:scroll;overflow-y:scroll;">
					<div class="row mt">
          				<div class="col-lg-10">
          				<div class="form-panel">
                  		<h4 class="mb"><i class="fa fa-angle-right"></i> Edit Product</h4>
                    
                   		 <form class="form-horizontal style-form" method="get" action="">
                    	 
                       	 <div class="form-group">
                         <label class="col-sm-2 col-sm-2 control-label">Image</label>
                         <div class="col-sm-10">
                         <img class="profile-pic"/>
                         <input class="file-upload" id="FileImgBarang" name="FileImgBarang" type="file" />
                         <div class="upload-button">Upload Image</div>
                         </div>
                         </div>
                      	
                      	 <div class="form-group">
                         <label class="col-sm-2 col-sm-2 control-label">Jenis Barang</label>
                         <div class="col-sm-10">
                         <input id="TxtJenisBarang" name="TxtJenisBarang" type="text" class="form-control2" style="width:200px;" placeholder="Jenis Barang" readonly/ >
                         <input id="TxtIdJenisBarang" name="TxtIdJenisBarang" value="23" type="text" class="hidden" style="width:200px;" placeholder="Jenis Barang" >
                         
                         <button type="button" class="btn btn-group btn-info" data-toggle="modal" data-target="#myModalJenisBarang" onclick ="productgroupCall();"><i class="fa fa-search "></i></button>
                         </div>
                         
                         <label class="col-sm-2 col-sm-2 control-label">Kode Barang</label>
                         <div class="col-sm-10">
                         <input id="TxtKodeBarang" name="TxtKodeBarang" type="text"   class="form-control2" style="width:200px;" placeholder="Kode Barang" required/>
                         &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                         &nbsp;
                         <label>No.Pabrik</label> 
                         <input id="TxtNoPabrik" name="TxtNoPabrik" type="text"  class="form-control2" style="width:200px;" >
                         </div>
                         
                         <label class="col-sm-2 col-sm-2 control-label">Nama Barang</label>
                         <div class="col-sm-10">
                         <input id="TxtNamaBarang" name="TxtNamaBarang" type="text" class="form-control2" style="width:200px;" placeholder="Nama Barang" required/>
                         </div>
                         
                         <label class="col-sm-2 col-sm-2 control-label">Merk Barang</label>
                         <div class="col-sm-10">
                         <input id="TxtMerkBarang" name="TxtMerkBarang" type="text" class="form-control2" style="width:200px;"  >
                         &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                         <label>Nomor Barcode</label>
                         <input id="TxtBarcode" name="TxtBarcode" type="text" class="form-control2" style="width:170px;">
                         </div>
                         
                         <label class="col-sm-2 col-sm-2 control-label">Asal Negara</label>
                         <div class="col-sm-10">
                         <input id="TxtAsalNegara" name="TxtAsalNegara" value="CHINA" type="text" class="form-control2" style="width:200px;" required/>
                         &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                         
                         <label>Nomor SNI</label>
                         <input id="TxtNoSNI" name="TxtNoSNI" type="text" class="form-control2" style="width:200px;">
                         </div>
                         </div>
                         
                       	 <div class="form-group">
                         <label class="col-sm-2 col-sm-2 control-label">Stok Awal</label>
                         <div class="col-sm-10">
                         <input id="TxtStokAwal" name="TxtStokAwal" type="number" class="form-control2" style="width:100px;" value="0" required/>
                         <input id="TxtStokAwalQTY" name="TxtStokAwalQTY" type="text" class="form-control2" style="width:100px;"  value="CTN"  readonly/>
                         </div>
                         <label class="col-sm-2 col-sm-2 control-label">Isi 1 Karton</label>
                         <div class="col-sm-10">
                         <input id="TxtIsiKarton" name="TxtIsiKarton" type="number"  class="form-control2" style="width:100px;" value="1" required/>
                         <input id="TxtSatuanIsi" name="TxtISatuanIsi" type="text"  class="form-control2" style="width:100px;" value="LSN" required/>
                         
                         
						 
						 <label>Berisi</label>
						 <input id="TxtIsiPcs" name="TxtIsiPcs" type="number"  class="form-control2" style="width:100px;" value="12" required/>
                         <input id="TxtSatPcs" name="TxtSatPcs" type="text"  class="form-control2" style="width:100px;" value="PCS" readonly/>
                         <label>**Minimal 1</label>
                         </div>
                         
                         
                         
                        <%--  <label class="col-sm-2 col-sm-2 control-label">Jumlah Stok Awal</label>
                         <div class="col-sm-10">
                         <input id="TxtJumlahStok" name="TxtJumlahStok" type="text" class="form-control2" style="width:100px;"  value="0">
                         <input id="TxtSatJumlahStok" name="TxtSatJumlahStok" type="text" class="form-control2" style="width:100px;"   value="LSN" readonly/>
                         </div>
                         
                        
                         <label class="col-sm-2 col-sm-2 control-label">Berisi</label>
                         <div class="col-sm-10">
                         <input id="TxtTotIsiPcs" name="TxtTotIsiPcs" type="text"  class="form-control2" style="width:100px;" value="0" readonly/>
                         <input id="TxtSatPcs1" name="TxtSatPcs1" type="text"  class="form-control2" style="width:100px;" value="PCS" readonly/>
                         
                         </div> --%>
                         </div>
                         <button type="button" onclick="viaAjax('');" class="btn btn-theme">SIMPAN</button>
                      	 </form>
                         </div>
                         </div>
                         
                      	 
                    </div>
                  </div>
                </div>
              </div>
            </div>
            
            
<!--Modal Tabel Form Update Barang -->
   <div class="modal fade" id="myModalUpdateProduct" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
    	<div class="modal-dialog modal-lg" style="width:1000px;">
        	<div class="modal-content">
            	<div class="modal-header">
            		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
					</button>
            
            	<h4 class="modal-title" id="myModalLabel">Edit Item</h4>
				</div>
			
				<div style="border:1px solid white;height:500px;width:100%;overflow-x:scroll;overflow-y:scroll;">
					<div class="row mt">
          				<div class="col-lg-10">
          				<div class="form-panel">
                  		<h4 class="mb"><i class="fa fa-angle-right"></i> Tambah Barang</h4>
                    
                   		 <form class="form-horizontal style-form" method="get" action="">
                    	 
                       	 <div class="form-group">
                         <label class="col-sm-2 col-sm-2 control-label">Image</label>
                         <div class="col-sm-10">
                         <img id="imgModalUpdate" class="profile-pic"/>
                         <input class="file-upload" id="FileImgBarangUpdate" name="FileImgBarangUpdate" type="file" />
                         <div class="upload-button"></div>
                         </div>
                         </div>
                      	
                      	 <div class="form-group">
                         <label class="col-sm-2 col-sm-2 control-label">Jenis Barang</label>
                         <div class="col-sm-10">
                         <input id="TxtJenisBarangEdit" name="TxtJenisBarangEdit" type="text" class="form-control2" style="width:200px;" placeholder="Jenis Barang" readonly/>
                         <input id="TxtIdJenisBarangEdit" name="TxtIdJenisBarangEdit" type="text" class="hidden" style="width:200px;" placeholder="Jenis Barang" >
                         
                         <button type="button" class="btn btn-group btn-info" data-toggle="modal" data-target="#myModalJenisBarang" onclick ="productgroupCall();"><i class="fa fa-search "></i></button>
                         </div>
                         
                         <label class="col-sm-2 col-sm-2 control-label">Kode Barang</label>
                         <div class="col-sm-10">
                         <input id="TxtKodeBarangEdit" name="TxtKodeBarangEdit" type="text"   class="form-control2" style="width:200px;" placeholder="Kode Barang" required/>
                         &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                         &nbsp;
                         <label>No.Pabrik</label> 
                         <input id="TxtNoPabrikEdit" name="TxtNoPabrikEdit" type="text"  class="form-control2" style="width:200px;" >
                         </div>
                         
                         <label class="col-sm-2 col-sm-2 control-label">Nama Barang</label>
                         <div class="col-sm-10">
                         <input id="TxtNamaBarangEdit" name="TxtNamaBarangEdit" type="text" class="form-control2" style="width:200px;" placeholder="Nama Barang" required/>
                         </div>
                         
                         <label class="col-sm-2 col-sm-2 control-label">Merk Barang</label>
                         <div class="col-sm-10">
                         <input id="TxtMerkBarangEdit" name="TxtMerkBarangEdit" type="text" class="form-control2" style="width:200px;"  >
                         &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                         <label>Nomor Barcode</label>
                         <input id="TxtBarcodeEdit" name="TxtBarcodeEdit" type="text" class="form-control2" style="width:170px;">
                         </div>
                         
                         <label class="col-sm-2 col-sm-2 control-label">Asal Negara</label>
                         <div class="col-sm-10">
                         <input id="TxtAsalNegaraEdit" name="TxtAsalNegaraEdit" type="text" class="form-control2" style="width:200px;" required/>
                         &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                         
                         <label>Nomor SNI</label>
                         <input id="TxtNoSniEdit" name="TxtNoSniEdit" type="text" class="form-control2" style="width:200px;">
                         </div>
                         </div>
                         
                       	 <div class="form-group">
                         <label class="col-sm-2 col-sm-2 control-label">Stok Awal</label>
                         <div class="col-sm-10">
                         <input id="TxtStokAwalEdit" name="TxtStokAwalEdit" type="text" class="form-control2" style="width:100px;" placeholder="0" required/>
                         <input id="TxtStokAwalQtyEdit" name="TxtStokAwalQtyEdit" type="text" class="form-control2" style="width:100px;"  value="CTN"  readonly/>
                         </div>
                         
                         <label class="col-sm-2 col-sm-2 control-label">Isi 1 Karton</label>
                         <div class="col-sm-10">
                         <input id="TxtIsiKartonEdit" name="TxtIsiKartonEdit" type="text"  class="form-control2" style="width:100px;" placeholder="0" required/>
                         <input id="TxtSatuanIsiEdit" name="TxtSatuanIsiEdit" type="text"  class="form-control2" style="width:100px;" required/>
                         
                         
						 <label>Berisi</label>
						 <input id="TxtIsiPcsEdit" name="TxtIsiPcsEdit" type="text"  class="form-control2" style="width:100px;" value="1" required/>
                         <input id="TxtSatPcsEdit" name="TxtSatPcsEdit" type="text"  class="form-control2" style="width:100px;" value="PCS" readonly/>
                         <label>**Minimal 1</label>
                         </div>
                         </div>
                         <div class="form-group">
                         <label class="col-sm-2 col-sm-2 control-label">Harga Jual.(CTN) RP.</label>
                         <div class="col-sm-10">
                         <input id="TxtHargaJualEdit" name="TxtHargaJualEdit" type="number" class="form-control2" style="width:150px;" required/>
                         </div>
                         </div>
                         
                         
                         <%-- <label class="col-sm-2 col-sm-2 control-label">Jumlah Stok Awal</label>
                         <div class="col-sm-10">
                         <input id="TxtJumlahStokEdit" name="TxtJumlahStokEdit" type="text" class="form-control2" style="width:100px;"  value="0">
                         <input id="TxtSatJumlahStokEdit" name="TxtSatJumlahStokEdit" type="text" class="form-control2" style="width:100px;"  readonly/>
                         </div>
                         
                        
                         <label class="col-sm-2 col-sm-2 control-label">Berisi</label>
                         <div class="col-sm-10">
                         <input id="TxtTotIsiPcsEdit" name="TxtTotIsiPcsEdit" type="text"  class="form-control2" style="width:100px;" value="0" readonly/>
                         <input id="TxtSatPcs1Edit" name="TxtSatPcs1Edit" type="text"  class="form-control2" style="width:100px;" value="PCS" readonly/>
                         
                         </div> --%>
                         
                         <button id="buttonEdit" type="button" onclick="viaAjaxUpdate();" class="btn btn-theme">SIMPAN</button>
                      	 </form>
                         </div>
                         </div>
                         
                    </div>
                  </div>
                </div>
              </div>
            </div>
            
            
 
<!-- Modal Jenis barang -->
<div class="modal fade" id="myModalJenisBarang" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Jenis Barang</h4>
			</div>
			
            <div style="border:1px solid white;height:450px;width:100%;overflow-x:scroll;overflow-y:scroll;">
            <div class="modal-body">
            
            	<!--Search Table Group-->
					<div class="panel panel-default">
					<div class="panel-heading" >
					<i class="glyphicon glyphicon-search" title="cari"></i> Search</i>
					</div>
					<div class="panel-body" id="panelBody">
					<div class="col-lg-6">
					<div class="form-group">
			
					<label class="col-sm-2 col-sm-2 control-label">Jenis</label>
					<div class="col-sm-10">
					<input type="text" id="inpGroup" class="form-control2" style="width:200px;" >
					<button onclick="productgroupCall();" type="button" class="btn btn-group btn-info"><i class="fa fa-search"></i></button>
					</div>
					</div>
					</div>
					</div>
					</div>
					<!--End Search Table Group-->
			
               <table class="table3 table-bordered table-striped table-condensed">
                      <thead>
                      <!--Add Table Group-->
							  <form class="form-horizontal style-form" method="get" >
                              <div class="col-sm-5">
                              <div class="form-group">
                              <input id="TxtAddJenisBarangModal" name="TxtAddJenisBarangModal" type="text" class="form-control2"  style="width:200px;" placeholder="Jenis Barang">
                              <button type="button" onclick="productgroupAddCall();" class="btn btn-theme" >Simpan</button>
							  </div>
                              </div>
                     		  </form>
                     		  <!--End Table Group-->
                     		  <!-- Pagging -->
                     		  <br/>
                     		  <br/>
           					  <label> Page </label>
            				  <select onchange="getPagePg(this)"; id="selectPagePg">
            
            				  </select>
            				  <label> of </label>
            				  <label id = "totPagePg"></label>
            				  <!-- page end -->
            				 <!--Table Group-->
                              <tr >
                             	  <th width="10%"> Kode </th>
                             	  <th width="80%"> Nama Jenis Barang</th>
                                  <th width="10%"> Action </th>
                              </tr>
                              </thead>
                              <tbody id="tbdJenisBarang">
                              </tbody>
                              <!--End Table Group-->
                          </table>
            		  </div>
            		<div class="modal-footer">
            	   </div>
        		</div>
        	</div>
    	</div>
	</div>
	
	<div class="modal fade" id="myModalImgShow" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Gambar</h4>
			</div>
			<div class="modal-body" >
            			<img id="imgModal" style="width:50%;height:30%;">
            	
            		  </div>
            		
        		</div>
        	</div>
    	</div>
	<!-- HISTORY -->
<div class="modal fade" id="myModalHistory" onclick ="" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>

                </button>
                
                <h4 class="modal-title" id="myModalLabel">History Barang</h4>
			</div>
            
			
			<!-- Pagging -->
          <button id="buttonTripHistory" onclick="callHistoryTrip(this.value)" type="button"  class="btn btn-primary btn-xs">Trip History</button>
          <button id="buttonOrderHistory" onclick="callHistoryOrder(this.value)" type="button"  class="btn btn-primary btn-xs">Order History</button>
            <!-- page end -->
            <div style="border:1px solid white;height:350px;width:100%;overflow-x:scroll;overflow-y:scroll;">
                 
            <div class="modal-body">
            <!-- Vendor -->
               <table  id="tblHistory"  class="table2 table-bordered table-striped table-condensed table-hover">
				<thead>
                              <tr>
                                  <th> No. Trip</th>
                                  <th> Tgl Trip</th>
                                  <th> Jml Ctn</th>
                                  <th> Jml PCS</th>
                                  <th> Harga Beli (Pcs)</th>
                              </tr>
                              </thead>
                             <tbody id="tbdHistory">
                              </tbody>
                          </table>
              <table  id="tblHistoryPenjualan"  class="table2 table-bordered table-striped table-condensed table-hover">
				<thead>
                              <tr>
                                  <th> Kode Penjualan</th>
                                  <th> Tgl Penjualan</th>
                                  <th> Tgl Kirim</th>
                                  <th> Customer</th>
                                  <th> Jml CTN</th>
                                  <th> Harga Jual/ CTN</th>
                              </tr>
                              </thead>
                             <tbody id="tbdHistoryPenjualan">
                              </tbody>
                          </table>
                      
            </div>
            <div class="modal-footer"></div>
        </div>
        </div>
    </div>
</div>
	
   <!----------------------------------------------------  SCRIPT -------------------------------------------------------------->                   
			
			<!-- Start Tabel Barang -->
			
       		<script type="text/javascript">
      	
			/* Start Paging Barang */
			function getPage(obj){
				var _data={};
				_data['page'] = "page"+obj.value;
				JSON.post(_data,'${ctx }/json/getPageStockKosong',10000,productSent,null,null);
				/* setPagging("page"+obj.value,"",""); */
			}
			
			/* End Paging Barang */
			
			/* Start Call Data Barang */
			
			function _setPop(obj){
				 $('#myModalHistory').modal('show');
				 document.getElementById("buttonTripHistory").value = obj;
				 document.getElementById("buttonOrderHistory").value = obj;
				 $('#tbdHistory').empty();
				 $('#tbdHistoryPenjualan').empty();
				 
			}
          		var productSent = function(data){
          			var _items = data.items;
          			if(_items.length == 0){
          				var _data={};
        				_data['page'] = "page1";
        				JSON.post(_data,'${ctx }/json/getPageStockKosong',10000,productSent,null,null);
          				return;
          			}
          			document.getElementById("tbdProduct").innerHTML = "";
          				if(data.page){
          					document.getElementById("totPage").innerHTML = data.page;
          					 setPagging("1",data.page,"1"); 
          					
          				}
          				
          				
            			$('#tbdProduct').empty();
            			for ( var i = 0; i < _items.length; i++) {
            			var _item = _items[i];
            			var _id = "'"+_item.prodId+"'";
            			var _stockId = "'"+_item.stockId+"'";
            			//var _idProd = "'"+_item.prodId+"'";
	        			var _img = "'"+_item.productImage+"'";
	        			var _idJenisBarang = "'"+_item.prodGroupId+"'";
	        			var _jenisBarang = "'"+_item.productGroup+"'";
	        			var _kodeBarang = "'"+_item.productCode+"'";
	        			var _namaBarang = "'"+_item.productName+"'";
	        			
	        			var _barcode = "''";
	        			var _noPabrik = "''";
	        			var _noSni = "''";
	        			var _merkBarang = "''";
	        			var _madeIn = "''";
	        			
	        			
	        			var _merkBarang = "'"+_item.productMerk+"'";
	        			if 	(_item.productBarcode)
	        				{
	        			 	_barcode = "'"+_item.productBarcode+"'";
	        				};
	        			if 	(_item.productPartNumb)
	        				{
	        				_noPabrik = "'"+_item.productPartNumb+"'";
	        				};
        				if 	(_item.productStandartNo)
    						{
        					 _noSni = "'"+_item.productStandartNo+"'";
    						};
    					if (_item.productMerk)
        					{
	        				_noPabrik = "'"+_item.productMerk+"'";
        					};
        				if (_item.productMadeIn)
    						{
        					_madeIn = "'"+_item.productMadeIn+"'";
    						};
	        			var _isiCtn = "'"+_item.isiCtn+"'";
	        			var _stokCtn = "'"+_item.stokCtn+"'";
	        			var _stokIsiCtn = "'"+_item.stokIsiCtn+"'";
	        			var _satIsiCtn = "'"+_item.satIsiCtn+"'";
	        			var _isiPcs = "'"+_item.isiPcs+"'";
	        			var _totStokPcs = "'"+_item.totStokPcs+"'";
	        			var _hJualIsiCtn = "'"+_item.hargaJualIsiCtn+"'";
	        			
	        			var _isiCtnConv = convertToItem(_item.isiCtn);
	        			var _stokCtnConv = convertToItem(_item.stokCtn);
	        			
	        			var _isiPcsConv = convertToItem(_item.isiPcs);
	        			var _stokCtnConv = convertToItem(_item.stokCtn);
	        			var _hJualCtnConv = convertToMoney(_item.hargaJualCtn);
	        			var _hJualPcsConv = convertToMoney(_item.hargaJualPcs);
	        			var _hJualCtn = "'"+_item.hargaJualCtn+"'";
            			$('#tbdProduct').append(
	        						$('<tr data-toggle="modal"  ondblclick = "_setPop('+_stockId+')" ><\/tr>')
	        						.append($('<td><button onclick ="imgShow('+_img+')" data-toggle="modal" data-target="#myModalImgShow"><i class="fa fa-instagram"><\/i></button><\/td>'))
	        						.append($('<td><\/td>').html(_item.tripNumbStok))
	        						.append($('<td><\/td>').html(_item.productBarcode))
        							.append($('<td><\/td>').html(_item.productCode))
        							.append($('<td><\/td>').html(_item.productPartNumb))
        							.append($('<td><\/td>').html(_item.productName))
        							.append($('<td><\/td>').html(_item.productMerk))
        							.append($('<td><\/td>').html(_item.productGroup))
        							.append($('<td><\/td>').html(_item.productMadeIn))
        							.append($('<td><\/td>').html(_item.productStandartNo))
        							.append($('<td><\/td>').html(_isiCtnConv+" "+ _item.satIsiCtn))
        							.append($('<td><\/td>').html(_isiPcsConv+" Pcs")) 
        							.append($('<td><\/td>').html(_stokCtnConv+" CTN"))
        							.append($('<td><\/td>').html("Rp "+_hJualCtnConv))
        							.append($('<td><\/td>').html("Rp "+_hJualPcsConv))
        							);
            			
        			}
        		};
          		var productCall = function() {
					var _data={};
					
					_data['byValue']="stokCtn";
					_data['queryData'] = "0";
					_data['_qtyStock'] = "0";
					
					
					JSON.post(_data,'${ctx }/json/barangKosong',10000,productSent,null,null);
					 var _pageTot = productSent.page; 
				};
				
				var productAdd = function(data){
					
					if(data.code == 0){
						alert(data.message);
						$('#myModalAddProduct').modal('hide');
						productCall();
						
						}
						if (data.code != 0) {
							
							alert(data.message);
						}
						else {
							
						}
						};
						
				var productEdit = function(data){
					if(data.code == 0){
						alert(data.message);
						$('#myModalUpdateProduct').modal('hide');
						productCall();
						
						}
						if (data.code != 0) {
							
							alert(data.message);
						}
						else {
							
						}
						
						};
				
				var productDelete = function(data){
					if(data.code == 0){
						alert(data.message);
						
							productCall();
								
							}
							
							else {
									
							}
								
							};
						
				productCall();
				var fowardPrint = function(data){
					//alert(data.message);
					window.location.replace('../img-sk/Stock_0.pdf');
				};
				function printCall(){
					var _data={};
					_data['queryData']=document.getElementById("sorBy").value;
					_data['byValue']="PRINTSTOCK";
					JSON.post(_data,'${ctx }/json/stock',1000000,fowardPrint,null,null);
					 
				}
				var fowardPrintExcel = function(data){
					window.location.replace('../img-sk/workbook.xls');
				};
				function downloadExcel(){
					var _data={};
					_data['queryData']=document.getElementById("sorBy").value;
					_data['byValue']="DOWNLOADEXCEL";
					JSON.post(_data,'${ctx }/json/stock',1000000,fowardPrintExcel,null,null);
				}
				function callHistoryTrip(stockId){
					$('#tbdHistory').empty();
					var _sentTable = function(data){
						var _items = data.items;
						
		    			for ( var i = 0; i < _items.length; i++) {
		    				
		    				var _item = _items[i];
		    				//var _tot=_item.hpp*_item.kurs;
		    				//.html(_tot);
		    				var _qtyBelCtnConv = convertToItem(_item.totQtyBeliCtn);
		    				var _qtyBelPcsConv = convertToItem(_item.totQtyBeliPcs);
		    				var _totHargaNettBeliIdrConv = convertToMoney(_item.totHargaNettBeliIdr);
		    				var _dateTrip = ($.datepicker.formatDate('d MM yy',new Date(_item.tripDate)));
		    				$('#tbdHistory').append(
		    						$('<tr><\/tr>')
		    							/* .append($('<td><\/td>').html(_item.productImage)) */
		    							.append($('<td><\/td>').html(_item.tripNumber))
		        						.append($('<td><\/td>').html(_dateTrip))
		        						.append($('<td><\/td>').html(_qtyBelCtnConv))
		        						.append($('<td><\/td>').html(_qtyBelPcsConv))
		        						.append($('<td><\/td>').html(_item.vendVta+":"+_totHargaNettBeliIdrConv))
		    				);
		    				 
		    			} 
					};
					var _data={};
					_data['queryData']="";
					_data['byValue']=stockId;
					JSON.post(_data,'${ctx }/json/tripHistory',10000,_sentTable,null,null);
					 
				}
				function callHistoryOrder(stockId){
					$('#tbdHistoryPenjualan').empty();
					var _sentTable = function(data){
						var _items = data.items;
						
		    			for ( var i = 0; i < _items.length; i++) {
		    				var _item = _items[i];
		    				//var _tot=_item.hpp*_item.kurs;
		    				//.html(_tot);
		    				
		    				var _dateOrder = ($.datepicker.formatDate('d MM yy',new Date(_item.datePenjualan)));
		    				
		    				var _dateOrderKirim = " - ";
		    				if(_item.datePenjualanKirim){
		    					var _dateOrderKirim = ($.datepicker.formatDate('d MM yy',new Date(_item.datePenjualanKirim)));
		    				}
		    				
		    				var _hJualCtnConv = convertToMoney(_item.hargaJualCtn);
		    				$('#tbdHistoryPenjualan').append(
		    						$('<tr><\/tr>')
		    							/* .append($('<td><\/td>').html(_item.productImage)) */
		    							.append($('<td><\/td>').html(_item.orderNumb))
		        						.append($('<td><\/td>').html(_dateOrder))
		        						.append($('<td><\/td>').html(_dateOrderKirim))
		        						.append($('<td><\/td>').html(_item.customerName))
		        						.append($('<td><\/td>').html(_item.totQtyJualCtn))
		        						.append($('<td><\/td>').html("Rp "+_hJualCtnConv))
		    				);
		    				 
		    			} 
					};
					var _data={};
					_data['queryData']="";
					_data['byValue']=stockId;
					JSON.post(_data,'${ctx }/json/penjulan-history',10000,_sentTable,null,null);
					 
				}
				/* End Call Data Barang */
			</script>
			
			<!-- End Tabel Barang -->
			
				
		    <!-- Start Table Jenis Barang/Product -->
		    <script type="text/javascript">   
               /*Start Autofill Jenis Barang*/
              function autoInputPG(a,b)
               {
            	   
					document.getElementById("TxtJenisBarang").value = a;
					document.getElementById("TxtIdJenisBarang").value = b;
					document.getElementById("TxtJenisBarangEdit").value = a;
					document.getElementById("TxtIdJenisBarangEdit").value = b;
					
					$('#myModalJenisBarang').modal('hide');
			   }
              /*End Autofill Jenis Barang*/
              
              /*Start Add Jenis Barang*/
              var productgroupAddSent = function(data) {
            	  alert (data.message);
      		  var _items = data.items;
      			for ( var i = 0; i < _items.length; i++) {
      				var _item = _items[i];
    				var _id = "'"+_item.id+"'";
    				var _dataItem = "'"+_item+"'";
      				//var _tot=_item.hpp*_item.kurs;
      				//.html(_tot);
      				$('#tbdJenisBarang').append(
      						$('<tr><\/tr>')
      							.append($('<td><\/td>').html(_item.id))
      							.append($('<td><\/td>').html(_item.name))
      							.append($('<td><button type="button" onclick="productgroupRemoveCall('+_item.id+');" class="btn btn-danger btn-xs"><i class="fa fa-trash-o "><\/i><\/button><\/td>'))
      				);
      			}
      			
      		};
      		
      		var productgroupAddCall = function() {
  				var _data={};
  				_data['name']=$('#TxtAddJenisBarangModal').val().trim();
  				JSON.post(_data,'${ctx }/json/productgroup-add',10000,productgroupAddSent,null,null);
  			};
  			/* End Add Jenis Barang */
      		
  			/* Start Call Jenis Barang */
  			function getPagePg(obj){
							var _data={};
							_data['page'] = "page"+obj.value;
							JSON.post(_data,'${ctx }/json/getPagePgroup',10000,productgroupSent,null,null);
							/* setPagging("page"+obj.value,"",""); */
						}
       						var productgroupSent = function(data) {
       			document.getElementById("tbdJenisBarang").innerHTML = "";
  				if(data.page){
  					document.getElementById("totPagePg").innerHTML = data.page;
  					_customPage = "Pg";
  					 setPagging("1",data.page,"1");
  				}
		        	var _items = data.items;
		        	$('#tbdJenisBarang').empty();
		        		for ( var i = 0; i < _items.length; i++) {
		        			var _item = _items[i];
		        			var _tr = document.createElement("tr");
								_tr.setAttribute("onclick", "autoInputPG('"+_item.name+"','"+_item.id+"')");
							$('#tbdJenisBarang').append(
							$(_tr)
		        							.append($('<td><\/td>').html(_item.id))
											.append($('<td><\/td>').html(_item.name))
											.append($('<td><button type="button" onclick="productgroupRemoveCall('+_item.id+');" class="btn btn-danger btn-xs"><i class="fa fa-trash-o "><\/i><\/button><\/td>'))
				        					
		        							);
		        			}
		        		};
						
		        		var productgroupCall = function() {
							var _data={};
							
							_data['name']=$('#inpGroup').val().trim();
							JSON.post(_data,'${ctx }/json/productgroup',10000,productgroupSent,null,null);
						};
						
						
					/* End Call Jenis Barang */
					
					/* DELETE DATA Jenis Barang*/
		       		function productgroupRemoveCall(_id) {
		       			if(confirm("Do you want to delete ?") == false) {
		       				return;
		       			}
		       			    this.click;
						var _data={};
						_data['id'] = _id;
						
						JSON.post(_data,'${ctx }/json/productgroup-del',10000,productgroupDel,null,null);
						
						var productgroupDel = function(data){
						if(data.code == 0){
						alert(data.message);
						productgroupCall();
							}
									
						else {
						}
					};
		       		};
		    /*End delete Barang */
		    </script>

					
			<script type="text/javascript">
			 /*Convert Gambar / Preview*/
				function gantiGambar(str){
					if(typeof str === "object"){
					str = str.target.result;
			}
				document.getElementsByClassName("prevGambar")[0].getElementsByTagName("imgProd")[0].src = str;
			}
				document.getElementsByName("FileImgBarang")[0].onchange = function(){
    			fileObjek = this;
    				if(fileObjek.files){
       			 file = fileObjek.files[0];
        			pembacaFIle = new FileReader;
        			pembacaFIle.onloadend = gantiGambar;
        			pembacaFIle.readAsDataURL(file);
   			 }else{
        		file = fileObjek.value;
        			gantiGambar(file);
    		}
			};
			</script>
				
			
			<!-- UPLOAD GAMBAR -->
			<script type="text/javascript">
			$(document).ready(function() {
			var readURL = function(input) {
        	if (input.files && input.files[0]) {
            var reader = new FileReader();
			reader.onload = function (e) {
                $('.profile-pic').attr('src', e.target.result);
            }
    
            reader.readAsDataURL(input.files[0]);
       		}
   			}
    		
			$(".file-upload").on('change', function(){
       		 readURL(this);
   			 });
    
    		$(".upload-button").on('click', function() {
       		$(".file-upload").click();
    		});
			});
			</script>
			
			<!-- Auto calculate -->
			<script type="text/javascript">
		
				$('#TxtIsiKarton, #TxtStokAwal, #TxtIsiPcs').on('input',function() {
   			 	var TxtIsiKarton = parseInt($('#TxtIsiKarton').val());
   				var TxtStokAwal = parseFloat($('#TxtStokAwal').val());
   		 		var TxtIsiPcs = parseFloat($('#TxtIsiPcs').val());
    			$('#TxtTotIsiPcs').val(((TxtIsiKarton * TxtStokAwal)* TxtIsiPcs ? (TxtIsiKarton * TxtStokAwal)* TxtIsiPcs : 0).toFixed(0));
				});
				
				$('#TxtIsiKarton, #TxtStokAwal').on('input',function() {
   			 	var TxtIsiKarton = parseInt($('#TxtIsiKarton').val());
   		 		var TxtStokAwal = parseFloat($('#TxtStokAwal').val());
    			$('#TxtJumlahStok').val((TxtIsiKarton * TxtStokAwal ? TxtIsiKarton * TxtStokAwal : 0).toFixed(0));
				});
				
				$('#TxtIsiKartonEdit, #TxtStokAwalEdit, #TxtIsiPcsEdit').on('input',function() {
	   			var TxtIsiKartonEdit = parseInt($('#TxtIsiKartonEdit').val());
	   			var TxtStokAwalEdit = parseFloat($('#TxtStokAwalEdit').val());
	   		 	var TxtIsiPcsEdit = parseFloat($('#TxtIsiPcsEdit').val());
	    		$('#TxtTotIsiPcsEdit').val(((TxtIsiKartonEdit * TxtStokAwalEdit)* TxtIsiPcsEdit ? (TxtIsiKartonEdit * TxtStokAwalEdit)* TxtIsiPcsEdit : 0).toFixed(0));
				});
					
				$('#TxtIsiKartonEdit, #TxtStokAwalEdit').on('input',function() {
	   			 var TxtIsiKartonEdit = parseInt($('#TxtIsiKartonEdit').val());
	   		 	var TxtStokAwalEdit = parseFloat($('#TxtStokAwalEdit').val());
	    		$('#TxtJumlahStokEdit').val((TxtIsiKartonEdit * TxtStokAwalEdit ? TxtIsiKartonEdit * TxtStokAwalEdit : 0).toFixed(0));
				});
				
				/* Perhitungan harga */
				/* $('#TxtKurs,#TxtHargaBeliAsing, #TxtHargaJualAsing').on('input',function() 
				{
					var TxtKurs = parseInt($('#TxtKurs').val());
					var TxtHargaBeliAsing = parseInt($('#TxtHargaBeliAsing').val());
		   			var TxtHargaJualAsing = parseFloat($('#TxtHargaJualAsing').val());
		   		$('#TxtProfitAsing').val((TxtHargaJualAsing - TxtHargaBeliAsing ? TxtHargaJualAsing - TxtHargaBeliAsing : 0).toFixed(2));
		    	$('#TxtHargaBeliRupiah').val((TxtKurs * TxtHargaBeliAsing ? TxtKurs * TxtHargaBeliAsing : 0).toFixed(2));
		    	$('#TxtHargaJualRupiah').val((TxtKurs * TxtHargaJualAsing ? TxtKurs * TxtHargaJualAsing : 0).toFixed(2));
		    	$('#TxtProfitRupiah').val(((TxtKurs * TxtHargaJualAsing)-(TxtKurs * TxtHargaBeliAsing) ? (TxtKurs * TxtHargaJualAsing)-(TxtKurs * TxtHargaBeliAsing) : 0).toFixed(2));
				}); */
			</script>
			
			<script type="text/javascript">
			function imgShow(img){
				document.getElementById("imgModal").src = "../img-sk/"+img;
				}
		    function viaAjax(obj){ 
		    	
		     var formdata = new FormData();
		     var _url = 'uploadImg?dimension='+obj; 
		     var _file = '#FileImgBarang';
		     var file = $(_file)[0].files[0];
		      formdata.append('fFile', file);
		      $.ajax({url: _url,
		        data: formdata,
		        processData: false,
		        contentType: false,
		        type: 'POST',
		        beforeSend: function(){
		          // add event or loading animation
		        },
		        success: function(ret) {
		        	productAddCall();
		        	/* var d = new Date();
		        	var _containerImg = document.getElementById("container_img"+obj);
		 			
		 			_nameFilelandscape = _ret[1];
		 			if(obj == 'landscape'){
		 				_nameFilelandscape = _ret[1];
		 				_containerImgSrc.src = "../MUAMALAT-IMG/"+_ret[0]+_nameFilelandscape+"?"+d.getTime();
		 			}else{
		 				_nameFile = _ret[1];
		 				_containerImgSrc.src = "../MUAMALAT-IMG/"+_ret[0]+_nameFile+"?"+d.getTime();
		 			}
		 		
		 			var _sizeCheck = 500000;
		 			if(_sizeCheck <= _ret[2]){
		 				document.getElementById("divAlertMes").style.display ="";
		 				document.getElementById("alertMessage").innerHTML ="Ukuran gambar maksimal adalah 500kb, ukuran gambar ini ";
		 				document.getElementById("alertLk").innerHTML = _ret[2]/1000 +"kb";
		        		
		        	}
		        	_containerImg.style.display = "";	 */	        }
		      });
		      return false;
		    }
		    function viaAjaxUpdate(obj){ 
		    	
			     var formdata = new FormData();
			     var _url = 'uploadImg?dimension='+obj; 
			     var _file = '#FileImgBarangUpdate';
			    
			     var file = $(_file)[0].files[0];
			     if(!file){
			    	 productUpdateCall();
			    	 return;
			     }
			      formdata.append('fFile', file);
			      $.ajax({url: _url,
			        data: formdata,
			        processData: false,
			        contentType: false,
			        type: 'POST',
			        beforeSend: function(){
			          // add event or loading animation
			        },
			        success: function(ret) {
			        	productUpdateCall();
			        	}
			      });
			      return false;
			    }
  			</script>
  
  			<script type="text/javascript">
			$(document).ready(function(){
     		$("#hide").click(function(){
       		$("#searchProd").addClass("hidden");
        
        
    		}); 
    		$("#show").click(function(){
        	$("#searchProd").removeClass("hidden");
       
    		});
    		});

			</script>
			
			 <script type="text/javascript">
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
