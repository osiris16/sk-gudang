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
          	<h3><i class="fa fa-angle-right"></i> Product taufik</h3>
            
            <button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModalAddProduct"><i class="fa fa-plus-circle"></i> Tambah Product</button>
			<br/>
			<br/>
			<!-- Cetak stok per bulan -->
			<div class="form-group">
			<label class="col-sm-2 col-sm-2 control-label">Bulan</label>
			<div class="col-sm-10">
			<select class="form-control2" id="selBulanPrint" style="width:200px;">
				<option value = "01"> Januari</option>
				<option value = "02"> Februari</option>
				<option value = "03"> Maret</option>
				<option value = "04"> April</option>
				<option value = "05"> Mei</option>
				<option value = "06"> Juni</option>
				<option value = "07"> Juli</option>
				<option value = "08"> Agustus</option>
				<option value = "09"> September</option>
				<option value = "10"> Oktober</option>
				<option value = "11"> November</option>
				<option value = "12"> Desember</option>
			</select>
			
			<select class="form-control2" id="selTahunPrint" style="width:200px;">
				<option value = "2016"> 2016</option>
				<option value = "2017"> 2017</option>
				<option value = "2018"> 2018</option>
				<option value = "2019"> 2019</option>
				<option value = "2020"> 2020</option>
				<option value = "2021"> 2021</option>
				<option value = "2022"> 2022</option>
				<option value = "2023"> 2023</option>
				<option value = "2024"> 2024</option>
				<option value = "2025"> 2025</option>
				<option value = "2026"> 2026</option>
				<option value = "2027"> 2027</option>
			</select>
			<button onclick="prinwithDate();" type="button" class="btn btn-group btn-info"><i class="glyphicon glyphicon-print"></i> Cetak dengan bulan dan tahun </button>
			
			
			</div>
			</div>
			<br/>
			<br/>
			
			<!-- Cetak stok per bulan -->
			
			<div class="form-group">
			<label class="col-sm-2 col-sm-2 control-label">Urutkan</label>

			<select id="sorBy" class="form-control2" style="width:150px;">
				<option value = "prod_code"> Kode Barang </option>
				<option value = "prod_name"> Nama Barang</option>
			</select>
			<button onclick="printCall();" type="button" class="btn btn-group btn-info"><i class="glyphicon glyphicon-print"></i> Cetak Stok Saat Ini(PDF)</button>
			
			<button onclick="downloadExcel()" type="button" class="btn btn-group btn-info"><i class="glyphicon glyphicon-download-alt"></i> Cetak Stok Saat Ini (CSV)</button>
			</div>
			<br/>
			<br/>
			
			
			<button id="show" type="button" class="btn btn-group btn-info"><i class="glyphicon glyphicon-search" title="cari"></i> Search</i></button>
            
			<!--Search Table Barang-->
			<div id="searchProd" class="panel panel-default hidden">
			
			<div class="panel-heading" >
			<button id="hide" type="button" class="btn btn-group btn-info">Hide</button>
			</div>
			<div class="panel-body" id="panelBody">
			<div class="col-lg-6">
			<div class="form-group">
			<label class="col-sm-2 col-sm-2 control-label">Kategori</label>
                         
			<div class="col-sm-10">
			<select id = "selByValue" class="form-control2" style="width:200px;">
				<option value = "code">Kode Barang</option>
				<option value = "name">Nama Barang</option>
				<option value = "stokCtn">Sisa Stok</option>
				<option value = "tripNumSeq">Nomor Trip Urutan</option>
				<!-- <option value = "tripNumSeq">Nomor Trip Urutan</option> -->
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
                       		<th> Harga Jual/Pcs</th>
                            <th> Action</th>
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
                         <input class="file-upload boxadd" id="FileImgBarang" name="FileImgBarang" type="file" />
                         <div class="upload-button">Upload Image</div>
                         </div>
                         </div>
                      	
                      	 <div class="form-group">
                         <label class="col-sm-2 col-sm-2 control-label">Jenis Barang</label>
                         <div class="col-sm-10">
                         <input id="TxtJenisBarang" name="TxtJenisBarang" type="text" class="form-control2 boxadd" style="width:200px;" placeholder="Jenis Barang" readonly/ >
                         <input id="TxtIdJenisBarang" name="TxtIdJenisBarang" value="23" type="text" class="hidden" style="width:200px;" placeholder="Jenis Barang" >
                         
                         <button type="button" class="btn btn-group btn-info" data-toggle="modal" data-target="#myModalJenisBarang" onclick ="productgroupCall();"><i class="fa fa-search "></i></button>
                         </div>
                         
                         <label class="col-sm-2 col-sm-2 control-label">Kode Barang</label>
                         <div class="col-sm-10">
                         <input id="TxtKodeBarang" name="TxtKodeBarang" type="text"   class="form-control2 boxadd" style="width:200px;" placeholder="Kode Barang" required/>
                         &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                         &nbsp;
                         <label>No.Pabrik</label> 
                         <input id="TxtNoPabrik" name="TxtNoPabrik" type="text"  class="form-control2 boxadd" style="width:200px;" >
                         </div>
                         
                         <label class="col-sm-2 col-sm-2 control-label">Nama Barang</label>
                         <div class="col-sm-10">
                         <input id="TxtNamaBarang" name="TxtNamaBarang" type="text" class="form-control2 boxadd" style="width:200px;" placeholder="Nama Barang" required/>
                         </div>
                         
                         <label class="col-sm-2 col-sm-2 control-label">Merk Barang</label>
                         <div class="col-sm-10">
                         <input id="TxtMerkBarang" name="TxtMerkBarang" type="text" class="form-control2 boxadd" style="width:200px;"  >
                         &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                         <label>Nomor Barcode</label>
                         <input id="TxtBarcode" name="TxtBarcode" type="text" class="form-control2 boxadd" style="width:170px;">
                         </div>
                         
                         <label class="col-sm-2 col-sm-2 control-label">Asal Negara</label>
                         <div class="col-sm-10">
                         <input id="TxtAsalNegara" name="TxtAsalNegara" value="CHINA" type="text" class="form-control2 boxadd" style="width:200px;" required/>
                         &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                         
                         <label>Nomor SNI</label>
                         <input id="TxtNoSNI" name="TxtNoSNI" type="text" class="form-control2 boxadd" style="width:200px;">
                         </div>
                         </div>
                         
                       	 <div class="form-group">
                       	 	 <div class ="box box-default">
                       	 	 	
                       	 	 	<div class="box-body">
                       	 	 		
                       	 	 		<div class="col-md-12">
                       	 	 			<div class="form-group">
						                  <label class="col-sm-2 col-sm-2 control-label">Stok Awal All</label>
						
						                  <div class="col-sm-10">
						                   	<input id="TxtStokAwal" name="TxtStokAwal" type="number" class="form-control2" style="width:100px;" value="0" required/>
						                   	 <input id="TxtStokAwalQTY" name="TxtStokAwalQTY" type="text" class="form-control2" style="width:100px;"  value="CTN"  readonly/>
						                  </div>
						                </div>
                       	 	 		</div>
                       	 	 		
                       	 	 		<div class="col-md-12">
                       	 	 			<div class="form-group">
						                  <label class="col-sm-2 col-sm-2 control-label">Stok Awal Retail</label>
						
						                  <div class="col-sm-10">
						                   	<input id="TxtStokAwalRetail" name="TxtStokAwalRetail" type="number" class="form-control2" style="width:100px;" value="0" required/>
						                   	 <input id="TxtStokAwalQTYRetail" name="TxtStokAwalQTYRetail" type="text" class="form-control2" style="width:100px;"  value="CTN"  readonly/>
						                  </div>
						                </div>
                       	 	 		</div>
                       	 	 		
                       	 	 		<div class="col-md-12">
                       	 	 			<div class="form-group">
						                  <label class="col-sm-2 col-sm-2 control-label">Stok Awal Dept store</label>
						
						                  <div class="col-sm-10">
						                   	<input id="TxtStokAwalDeptStore" name="TxtStokAwalDeptStore" type="number" class="form-control2" style="width:100px;" value="0" required/>
						                   	 <input id="TxtStokAwalQTYDeptStore" name="TxtStokAwalQTYDeptStore" type="text" class="form-control2" style="width:100px;"  value="CTN"  readonly/>
						                  </div>
						                </div>
                       	 	 		</div>
                       	 	 		
			                        
                       	 	 	</div>
                       	 	 	 
                       	 	 </div>
	                        
	                        <div class="col-md-12">
	                        	<label class="col-sm-2 col-sm-2 control-label">Isi 1 Karton</label>
		                         <div class="col-sm-10">
		                         <input id="TxtIsiKarton" name="TxtIsiKarton" type="number"  class="form-control2" style="width:100px;" value="1" required/>
		                         <input id="TxtSatuanIsi" name="TxtISatuanIsi" type="text"  class="form-control2" style="width:100px;" value="LSN" required/>
		                         
		                         
								 
								 <label>Berisi</label>
								 <input id="TxtIsiPcs" name="TxtIsiPcs" type="number"  class="form-control2" style="width:100px;" value="12" required/>
		                         <input id="TxtSatPcs" name="TxtSatPcs" type="text"  class="form-control2" style="width:100px;" value="PCS" readonly/>
		                         <label>**Minimal 1</label>
		                         </div>
	                        </div>
	                         
                         
                     
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
	                         <label class="col-sm-2 col-sm-2 control-label">Stok Awal All</label>
	                         <div class="col-sm-10">
	                         <input id="TxtStokAwalEdit" name="TxtStokAwalEdit" type="text" class="form-control2" style="width:100px;" placeholder="0" required/>
	                         <input id="TxtStokAwalQtyEdit" name="TxtStokAwalQtyEdit" type="text" class="form-control2" style="width:100px;"  value="CTN"  readonly/>
	                         </div>
                         </div>
                         
                         <div class="form-group">
	                         <label class="col-sm-2 col-sm-2 control-label">Stok Awal Retail</label>
	                         <div class="col-sm-10">
	                         <input id="TxtStokAwalRetailEdit" name="TxtStokAwalRetailEdit" type="text" class="form-control2" style="width:100px;" placeholder="0" required/>
	                         <input id="TxtStokAwalQtyRetailEdit" name="TxtStokAwalQtyRetailEdit" type="text" class="form-control2" style="width:100px;"  value="CTN"  readonly/>
	                         </div>
                         </div>
                         
                         <div class="form-group">
	                         <label class="col-sm-2 col-sm-2 control-label">Stok Awal Dept store</label>
	                         <div class="col-sm-10">
	                         <input id="TxtStokAwalDeptStoreEdit" name="TxtStokAwalDeptStoreEdit" type="text" class="form-control2" style="width:100px;" placeholder="0" required/>
	                         <input id="TxtStokAwalQtyDeptStoreEdit" name="TxtStokAwalQtyDeptStoreEdit" type="text" class="form-control2" style="width:100px;"  value="CTN"  readonly/>
	                         </div>
                         </div>
                         
                          <div class="form-group">
	                         <label class="col-sm-2 col-sm-2 control-label">Isi 1 Karton</label>
	                         <div class="col-sm-10">
	                         <input id="TxtIsiKartonEdit" name="TxtIsiKartonEdit" type="text"  class="form-control2" style="width:100px;" placeholder="0" required/>
	                         <input id="TxtSatuanIsiEdit" name="TxtSatuanIsiEdit" type="text"  class="form-control2" style="width:100px;" required/>
	                         </div>
                         
                       
							 <label>Berisi</label>
							 <input id="TxtIsiPcsEdit" name="TxtIsiPcsEdit" type="text"  class="form-control2" style="width:100px;" value="1" required/>
	                         <input id="TxtSatPcsEdit" name="TxtSatPcsEdit" type="text"  class="form-control2" style="width:100px;" value="PCS" readonly/>
	                         <label>**Minimal 1</label>
                      
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
           					  <!-- Pagging -->
			<button onclick = "firstPagePg(this)" type="button" >First</button>
            <button onclick = "prevPagePg(this)" type="button" >Prev</button>
            <label> Page </label>
            <select onchange="getPagePg(this)"; id="selectPagePg">
            
            </select>
            <label> of </label>
            <label id = "totPagePg"></label>
            <button onclick = "nextPagePg(this)" type="button" >Next</button>
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
          <table  id="tblTotalHistory"  class="table2 table-bordered table-striped table-condensed table-hover">
			<thead>
                              <tr>
                              	  <th> Jml Ctn Beli</th>
                              	  <th> Jml Transaksi Beli</th>
                                  <th> Jml Ctn Jual</th>
                                  <th> Jml Transaksi Jual</th>
                                  
                              </tr>
                              </thead>
                             <tbody id="tbdTotHistory">
                             	<tr>
	                             	<td id="tdCtnBeli"></td>
	                             	<td id="tdTransBeli"></td>
	                             	<td id="tdCtnJual"></td>
	                             	<td id="tdTransJual"></td>
	                             	
                             	</tr>
                              </tbody>
                          </table>
			<!-- page end -->
            <div style="border:1px solid white;height:500px;width:100%;overflow-x:scroll;overflow-y:scroll;">
                 
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
			/* UPDATE DATA MODAL */
			var DATA_MODAL = {};
			/* function pushModal(_id,_idJenisBarang,_jenisBarang,_kodeBarang,_namaBarang,_merkBarang,_noPabrik,_barcode,_noSni,_madeIn,_stokCtn,_stokIsiCtn,_isiCtn,_isiCtnRetail,_isiCtnDeptStore,_satIsiCtn,_isiPcs,_totStokPcs,_img,_hJualCtn){ */
    		function pushModal(il){
    			var _data={};
    			_item = DATA_MODAL[il];
    			
    			var _id = _item.prodId;
    			var _stockId =_item.stockId;
    			//var _idProd = "'"+_item.prodId+"'";
    			var _img =_item.productImage;
    			var _idJenisBarang = _item.prodGroupId;
    			var _jenisBarang =_item.productGroup;
    			var _kodeBarang = _item.productCode;
    			var _namaBarang =_item.productName;
    			
    			var _barcode = "";
    			var _noPabrik = "";
    			var _noSni = "";
    			var _merkBarang = "";
    			var _madeIn = "";
    			
    			
    			var _merkBarang = _item.productMerk;
    			if 	(_item.productBarcode)
    				{
    			 	_barcode = _item.productBarcode;
    				};
    			if 	(_item.productPartNumb)
    				{
    				_noPabrik = _item.productPartNumb;
    				};
				if 	(_item.productStandartNo)
					{
					 _noSni = _item.productStandartNo;
					};
				if (_item.productMerk)
					{
    				_noPabrik = _item.productMerk;
					};
				if (_item.productMadeIn)
					{
					_madeIn = _item.productMadeIn;
					};
    			var _isiCtn = _item.isiCtn;
    			var _stokCtn = _item.stokCtn;
    			var _stokCtnRetail = _item.stokIsiCtnRetail;
    			var _stokCtnDeptStore =_item.stokIsiCtnDeptStore
    			var _stokIsiCtn = _item.stokIsiCtn;
    			var _satIsiCtn = _item.satIsiCtn;
    			var _isiPcs = _item.isiPcs;
    			var _totStokPcs = _item.totStokPcs;
    			var _hJualIsiCtn = _item.hargaJualIsiCtn;
    			
    			var _isiCtnConv = "";
    			try {
    				_isiCtnConv = convertToItem(_item.isiCtn);
				} catch (e) {
					
				}
    			
    			//var _stokCtnConv = convertToItem(_item.stokCtn);
    			var _isiPcsConv = ""
    			try {
    				_isiPcsConv = convertToItem(_item.isiPcs);
				} catch (e) {
					// TODO: handle exception
				}
				var _stokCtnConv = "";
				try {
					_stokCtnConv = convertToItem(_item.stokCtn);
				} catch (e) {
					_stokCtnConv = _item.stokCtn;
				}
    			var _hJualCtnConv = "";
    			try {
    				_hJualCtnConv = convertToMoney(_item.hargaJualCtn);
				} catch (e) {
					// TODO: handle exception
				}
				
    			var _hJualCtn = "'"+_item.hargaJualCtn+"'";
    			
    			var _hJualPcsConv = "";
    			try {
    				_hJualPcsConv = convertToMoney(_item.hargaJualPcs);
				} catch (e) {
					// TODO: handle exception
				}
    			
    			document.getElementById("FileImgBarangUpdate").value = '';
    			document.getElementById("buttonEdit").value = _id;
    			if(_idJenisBarang != "undefined"){
    				document.getElementById("TxtIdJenisBarangEdit").value = _idJenisBarang;
    			}else {
    				document.getElementById("TxtIdJenisBarangEdit").value = "";
    			}
    			if(_jenisBarang != "undefined"){
    				document.getElementById("TxtJenisBarangEdit").value = _jenisBarang;
    			}else{
    				document.getElementById("TxtJenisBarangEdit").value = "";
    			}
    			
    			document.getElementById("TxtKodeBarangEdit").value = _kodeBarang;
    			document.getElementById("TxtNamaBarangEdit").value = _namaBarang;
    			document.getElementById("TxtMerkBarangEdit").value = _merkBarang;
    			document.getElementById("TxtNoPabrikEdit").value = _noPabrik;
    			document.getElementById("TxtBarcodeEdit").value = _barcode;
    			document.getElementById("TxtNoSniEdit").value = _noSni;
    			document.getElementById("TxtAsalNegaraEdit").value = _madeIn;
    			document.getElementById("TxtStokAwalEdit").value = _stokCtn;
    			document.getElementById("TxtStokAwalRetailEdit").value = _stokCtnRetail;
    			document.getElementById("TxtStokAwalDeptStoreEdit").value = _stokCtnDeptStore;
    			//document.getElementById("TxtJumlahStokEdit").value = _stokIsiCtn;
    			document.getElementById("TxtIsiKartonEdit").value = _isiCtn;
    			document.getElementById("TxtSatuanIsiEdit").value = _satIsiCtn;
    			document.getElementById("TxtIsiPcsEdit").value = _isiPcs;
    			//document.getElementById("TxtTotIsiPcsEdit").value = _totStokPcs;
    			document.getElementById("imgModalUpdate").src ="../img-sk/"+_img;
    			document.getElementById("TxtHargaJualEdit").value = _hJualCtn;
    			
    			
				//JSON.post(_data,'${ctx }/json/product',10000,productModalSent,null,null);
    		}
		
    		var productModalSent = function(data) {
    			var recid = document.getElementById("myModalUpdateProduct");
				var _dataId= recid.getAttribute("data-id");
    			var _items = data.items;
    			
    			for (var i = 0; i < _items.length; i++){
    				var _item = _items[i];
    				if (_item.id == _dataId) {
						
        				document.getElementById("TxtBarcodeEdit").value = _item.productBarcode;
						document.getElementById("TxtKodeBarangEdit").value = _item.productCode;
						document.getElementById("TxtNamaBarangEdit").value = _item.productName;
						document.getElementById("TxtMerkBarangEdit").value = _item.productMerk;
						document.getElementById("TxtIdJenisBarangEdit").value = _item.prodGroupId;
						document.getElementById("TxtJenisBarangEdit").value = _item.productGroup;
						document.getElementById("TxtAsalNegaraEdit").value = _item.productMadeIn;
						document.getElementById("TxtNoPabrikEdit").value = _item.ProductPartNumb;
						document.getElementById("TxtNoSniEdit").value = _item.ProductStandartNo;
						document.getElementById("TxtIsiKartonEdit").value = _item.isiCtn;
						document.getElementById("TxtSatuanIsiEdit").value = _item.satIsiCtn;
						document.getElementById("TxtIsiPcsEdit").value = _item.isiPcs;
						document.getElementById("TxtTotIsiPcsEdit").value = _item.totStokPcs;
						document.getElementById("TxtHargaJualEdit").value = _item.hargaJualCtn;
					}
    			}
    			
    		}
			
			var productUpdateCall = function() {
				var _recid = document.getElementById("buttonEdit").value;
				
				//var _dataId= _recid.getAttribute("data-id");
				var _data={};
				_data['id'] = _recid;
				
				var _barcode = $('#TxtBarcodeEdit').val().trim();
				var _code = $('#TxtKodeBarangEdit').val().trim();
				var _name = $('#TxtNamaBarangEdit').val().trim();
				var _jenis = $('#TxtIdJenisBarangEdit').val().trim();
				var _pabrik = $('#TxtNoPabrikEdit').val().trim();
				var _merk = $('#TxtMerkBarangEdit').val().trim();
				var _sni = $('#TxtNoSniEdit').val().trim();
				var _madeIn = $('#TxtAsalNegaraEdit').val().trim();
				var _stok = $('#TxtStokAwalEdit').val().trim();
				var _stokRetail = $('#TxtStokAwalRetailEdit').val().trim();
				var _stokDeptStore = $('#TxtStokAwalDeptStoreEdit').val().trim();
				var _isiCtn = $('#TxtIsiKartonEdit').val().trim();
				var _hargaJual = $('#TxtHargaJualEdit').val().trim();
				var _satIsi = $('#TxtSatuanIsiEdit').val().trim();
				var _isiPcs = $('#TxtIsiPcsEdit').val().trim();
				
				_data['barcode']=_barcode.replace("'",""); // validasi petik
				_data['code']=_code.replace("'","");
				_data['name']=_name.replace("'","");
				_data['productgroupEnt']=_jenis.replace("'","");
				_data['partNumb']=_pabrik.replace("'","");
				_data['merk']=_merk.replace("'","");
				_data['standartNo']=_sni.replace("'","");
				_data['madeIn']=_madeIn.replace("'","");
				_data['stokCtn']=_stok.replace("'","");
				_data['stokCtnRetail'] = _stokRetail.replace("'","");
				_data['stokCtnDeptStore'] = _stokDeptStore.replace("'","");
				_data['isiCtn']=_isiCtn.replace("'","");
				_data['hargaJualCtn']=_hargaJual.replace("'","");
				_data['satIsiCtn']=_satIsi.replace("'","");
				_data['isiPcs']=_isiPcs.replace("'","");
				JSON.post(_data,'${ctx }/json/stock-mod',10000,productEdit,null,null);
			};
			
			/* END UPDATE DATA MODAL */
			
			/* DELETE DATA */
       		var productRemoveCall = function(_id) {
       			if(confirm("Do you want to delete ?")) {
       			    this.click;
				var _data={};
				_data['id'] = _id;
				JSON.post(_data,'${ctx }/json/stock-del',10000,productDelete,null,null);
				
       	 		}
			else {
				
			return;
			}
       		};
       		/*End delete*/
       		</script>
       		
       		<script type="text/javascript">
      		var productAddSent = function(data){
      			
    			var _items = data.items;
    			for ( var i = 0; i < _items.length; i++) {
    				var _item = _items[i];
    				
    				//var _tot=_item.hpp*_item.kurs;
    				//.html(_tot);
    				$('#tbdProduct').append(
    						$('<tr><\/tr>')
    							/* .append($('<td><\/td>').html(_item.productImage)) */
    							.append($('<td><\/td>').html(_item.productBarcode))
        						.append($('<td><\/td>').html(_item.productCode))
        						.append($('<td><\/td>').html(_item.productName))
        						.append($('<td><\/td>').html(_item.productMerk))
        						.append($('<td><\/td>').html(_item.productGroup))
        						.append($('<td><\/td>').html(_item.productMadeIn))
        						.append($('<td><\/td>').html(_item.productPartNumb))
        						.append($('<td><\/td>').html(_item.productStandartNo))
        						.append($('<td><\/td>').html(_item.isiCtn))
        						.append($('<td><\/td>').html(_item.satIsiCtn))
        						.append($('<td><\/td>').html(_item.isiPcs))
        						.append($('<td><\/td>').html(_item.totStokPcs))
    							
    				);
    				 
    			} 
    			
    		};
    		
    		
      		var productAddCall = function() {
      			
				var _data={};
				var _barcode = $('#TxtBarcode').val().trim();
				var _code = $('#TxtKodeBarang').val().trim();
				var _name = $('#TxtNamaBarang').val().trim();
				var _jenis = $('#TxtIdJenisBarang').val().trim();
				var _pabrik = $('#TxtNoPabrik').val().trim();
				var _merk = $('#TxtMerkBarangEdit').val().trim();
				var _sni = $('#TxtNoSNI').val().trim();
				var _madeIn = $('#TxtAsalNegara').val().trim();
				
				var _stok = $('#TxtStokAwal').val().trim();
				var _stokRetail = $('#TxtStokAwalRetail').val().trim();
				var _stokDeptStore = $('#TxtStokAwalDeptStore').val().trim();
				
				var _isiCtn = $('#TxtIsiKarton').val().trim();
				var _satIsi = $('#TxtSatuanIsi').val().trim();
				var _isiPcs = $('#TxtIsiPcs').val().trim();
				
				
				_data['barcode']=_barcode.replace("'",""); // validasi petik
				_data['code']=_code.replace("'","");
				_data['name']=_name.replace("'","");
				_data['productgroupEnt']=_jenis.replace("'","");
				_data['partNumb']=_pabrik.replace("'","");
				_data['merk']=_merk.replace("'","");
				_data['standartNo']=_sni.replace("'","");
				_data['madeIn']=_madeIn.replace("'","");
				
				_data['stokCtn']=_stok.replace("'","");
				_data['stokCtnRetail']=_stokRetail.replace("'","");
				_data['stokCtnDeptStore']=_stokDeptStore.replace("'","");
				
				_data['isiCtn']=_isiCtn.replace("'","");
				_data['satIsiCtn']=_satIsi.replace("'","");
				_data['isiPcs']=_isiPcs.replace("'","");
				
				
				
				JSON.post(_data,'${ctx }/json/stock-add',10000,productAdd,null,null);
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
			/* Start Paging Barang */
			function getPage(obj){
				var _data={};
				_data['page'] = "page"+obj.value;
				JSON.post(_data,'${ctx }/json/getPageStock',10000,productSent,null,null);
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
				 $('#tdCtnBeli').empty();
				 $('#tdTransBeli').empty();
				 $('#tdCtnJual').empty();
				 $('#tdTransJual').empty();
				 
			}
          		var productSent = function(data){
          			var _items = data.items;
          			if(_items.length == 0){
          				var _data={};
        				_data['page'] = "page1";
        				JSON.post(_data,'${ctx }/json/getPageStock',10000,productSent,null,null);
          				return;
          			}
          			document.getElementById("tbdProduct").innerHTML = "";
          				if(data.page){
          					document.getElementById("totPage").innerHTML = data.page;
          					 setPagging("1",data.page,"1"); 
          					
          				}
          				
          				
            			$('#tbdProduct').empty();
            			DATA_MODAL = _items;
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
	        			var _stokCtnRetail = "'"+_item.stokIsiCtnRetail+"'";
	        			var _stokCtnDeptStore = "'"+_item.stokIsiCtnDeptStore+"'";
	        			var _stokIsiCtn = "'"+_item.stokIsiCtn+"'";
	        			var _satIsiCtn = "'"+_item.satIsiCtn+"'";
	        			var _isiPcs = "'"+_item.isiPcs+"'";
	        			var _totStokPcs = "'"+_item.totStokPcs+"'";
	        			var _hJualIsiCtn = "'"+_item.hargaJualIsiCtn+"'";
	        			
	        			var _isiCtnConv = "";
	        			try {
	        				_isiCtnConv = convertToItem(_item.isiCtn);
						} catch (e) {
							
						}
	        			
	        			//var _stokCtnConv = convertToItem(_item.stokCtn);
	        			var _isiPcsConv = ""
	        			try {
	        				_isiPcsConv = convertToItem(_item.isiPcs);
						} catch (e) {
							// TODO: handle exception
						}
						var _stokCtnConv = "";
						try {
							_stokCtnConv = convertToItem(_item.stokCtn);
						} catch (e) {
							_stokCtnConv = _item.stokCtn;
						}
	        			var _hJualCtnConv = "";
	        			try {
	        				_hJualCtnConv = convertToMoney(_item.hargaJualCtn);
						} catch (e) {
							// TODO: handle exception
						}
						
	        			var _hJualCtn = "'"+_item.hargaJualCtn+"'";
	        			
	        			var _hJualPcsConv = "";
	        			try {
	        				_hJualPcsConv = convertToMoney(_item.hargaJualPcs);
						} catch (e) {
							// TODO: handle exception
						}
						
				
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
        							.append($('<td><button id="pushModalButton" type="button" onclick="pushModal('+i+')" data-toggle="modal" data-target="#myModalUpdateProduct" class="btn btn-primary btn-xs">Edit<\/button><button type="button" onclick="productRemoveCall('+_item.stockId+');" class="btn btn-danger btn-xs">Delete<\/button><\/td>'))
        							//.append($('<td><button id="pushModalButton" type="button" onclick="pushModal('+_id+','+_idJenisBarang+','+_jenisBarang+','+_kodeBarang+','+_namaBarang+','+_merkBarang+','+_noPabrik+','+_barcode+','+_noSni+','+_madeIn+','+_stokCtn+','+_stokIsiCtn+','+_isiCtn+','+_satIsiCtn+','+_stokCtnRetail+','+_stokCtnDeptStore+','+_isiPcs+','+_totStokPcs+','+_img+','+_hJualCtn+')" data-toggle="modal" data-target="#myModalUpdateProduct" class="btn btn-primary btn-xs">Edit<\/button><button type="button" onclick="productRemoveCall('+_item.stockId+');" class="btn btn-danger btn-xs">Delete<\/button><\/td>'))
        							);
            			
        			}
        		};
          		var productCall = function() {
					var _data={};
					var _kjh = $('#selByValue').val().trim();
					
					_data['byValue']=$('#selByValue').val().trim();
					if(_kjh == 'stokCtn'){
					   _data['queryData'] = parseInt($('#inpQuery').val().trim());
					   _data['_qtyStock'] = parseInt($('#inpQuery').val().trim());
					}else{
						_data['queryData']=$('#inpQuery').val().trim();
					}
					
					JSON.post(_data,'${ctx }/json/stock',10000,productSent,null,null);
					 var _pageTot = productSent.page; 
				};
				
				var productAdd = function(data){
					
					if(data.code == 0){
						alert(data.message);
						$('#myModalAddProduct').modal('hide');
						clearTextboxAdd();
						productCall();
						
						}
						if (data.code != 0) {
							
							alert(data.message);
							location.reload();
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
							location.reload();
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
				function prinwithDate(){
					var _data={};
					_data['dateFrom']=$("#selBulanPrint").val() +" - "+$("#selTahunPrint").val();
					_data['actiontype']="PRINTSTOCKWITHDATE";
					var _name = $("#nav-accordion li .centered").html();
					_data["byValuePenj"] = _name;
					JSON.post(_data,'${ctx }/json/printStockWithDate',1000000,function(data){
						var code = data["code"];
						if(code == 0){
							window.location.replace('../img-sk/'+data['message']);
						}
						
					},null,null);
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
						document.getElementById('tdTransBeli').innerHTML = _items.length;
						var _ctnBeliTotal = 0;
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
		    				_ctnBeliTotal = _ctnBeliTotal+_qtyBelCtnConv*1;
		    			}
		    			document.getElementById('tdCtnBeli').innerHTML = _ctnBeliTotal;
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
						document.getElementById('tdTransJual').innerHTML = _items.length;
						var _ctnTOtal = 0;
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
		    				_ctnTOtal = _ctnTOtal + _item.totQtyJualCtn*1;
		    			}
		    			document.getElementById('tdCtnJual').innerHTML = _ctnTOtal;
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
  			function firstPagePg(obj){
       			var _selPage = document.getElementById("selectPagePg");
       			_selPage.value = 0;
       			getPagePg(_selPage);
       		}
       		function lastPagePg(obj){
       			var _selPage = document.getElementById("selectPagePg");
       			var _totPage = document.getElementById("totPagePg").innerHTML *1; 
       			_selPage.value = _totPage-1;
       			getPagePg(_selPage);
       		}
       		function nextPagePg(obj){
       			var _selPage = document.getElementById("selectPagePg");
       			var _currentPage = _selPage.value;
       			var _totPage = document.getElementById("totPagePg").innerHTML *1; 
       			if(_totPage == 1){
       				return;
       			}
       			var _setNext = _currentPage*1+1;
       			if(_setNext <=_totPage-1){
       				_selPage.value = _setNext;
           			getPagePg(_selPage);
       			}
       		}
			function prevPagePg(obj){
       			var _selPage = document.getElementById("selectPagePg");
       			var _currentPage = _selPage.value;
       			var _totPage = document.getElementById("totPagePg").innerHTML *1; 
       			if(_totPage == 1){
       				return;
       			}
       			var _setPrev = _currentPage*1-1;
       			if(_setPrev >=0){
       				_selPage.value = _setPrev;
           			getPagePg(_selPage);
       			}
       		}
  			
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
			
			function clearTextboxAdd(){
				 $('.boxadd').val('');
				 $('.profile-pic').attr('src', "");
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
