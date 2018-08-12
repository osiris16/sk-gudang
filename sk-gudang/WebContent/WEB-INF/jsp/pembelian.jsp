<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="d" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<body>
  
          	<h3><i class="fa fa-angle-right"></i> Pembelian</h3>
          	<button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModalAddPembelian">ADD Pembelian</button>
			<br/>
			<br/>
			
			<button id="show" type="button" class="btn btn-group btn-info"><i class="glyphicon glyphicon-search" title="cari"></i> Search</i></button>
            
			<!--Search Table Pembelian-->
			<div id="searchPemb" class="panel panel-default hidden">
			
			<div class="panel-heading" >
			<button id="hide" type="button" class="btn btn-group btn-info">Hide</button>
			</div>
			<div class="panel-body" id="panelBody">
			<div class="col-lg-6">
			<div class="form-group">
			
			<label class="col-sm-2 col-sm-2 control-label">Dari</label>
			<div class="col-sm-10">
			<input type="text" id="dateFrom" value = "01/01/2010" class="form-control2" style="width:200px;" >
			</div>
			
			<label class="col-sm-2 col-sm-2 control-label">Sampai</label>
			<div class="col-sm-10">
			<input type="text" id="dateTo" value = "01/01/2100"  class="form-control2" style="width:200px;" >
			</div>
			</div>
			<div class="form-group">
			<button onclick="pembelianCall();" type="button" style="width:300px;" class="btn btn-outline btn-primary btn-lg btn-block"><i class="glyphicon glyphicon-search" title="cari"></i></button>
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
            <br/>
            <div style="border:1px solid white;height:400px;width:100%;overflow-x:scroll;overflow-y:scroll;">
		  	<div class="row mt">
			  		<div class="col-lg-12">
                      <!--<div class="content-panel">-->
                      
                          <section id="unseen">
                          
            				<!--Table Pembelian-->
            				
                            <table class="table1 table-bordered table-striped table-condensed">
                              <thead>
                              	<tr>
                              	  <th> Nomor TRIP </th>
                              	  <th> Tanggal TRIP </th>
                                  <th> Kd. Barang</th>
                                  <th> Nama Barang</th>
                                  <th> Qty Beli (Pcs)</th>
                                  <th> Sisa Stok (Pcs)</th>
                                  <!-- <th> Isi CTN</th>
                                  <th> Satuan Isi CTN</th> -->
                                  <th> Disc %</th>
                                  <th> Valuta</th>
                                  <th> Juml.Netto (VTA)</th>
                                  <th> Kurs (RP)</th>
                                  <th> Juml.Netto (RP)</th>
                                  <!-- <th> Netto/Lsn (RP)</th>
                                  <th> Netto/Pcs (RP)</th> -->
                                  <!-- <th> HJ/pcs (RP)</th> -->
                                  <th> ACTION</th>
                              </tr>
                             </thead>
							 <tbody id="tbdPembelian">
                             </tbody>
                          	</table>
                      </section>
                   </div><!--content-panel-->
               </div><!--col-lg-4 -->			
		  	</div><!--row -->
      				
      				<!--Form Pembelian -->
<div class="modal fade" id="myModalAddPembelian" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
			</button>
            
            <h4 class="modal-title" id="myModalLabel">List Barang</h4>
		</div>
            
            <div style="border:1px solid white;height:500px;width:100%;overflow-x:scroll;overflow-y:scroll;">
            <div class="modal-body">
               <div class="row mt">
          		<div class="col-lg-12">
                  <div class="form-panel">
                  	 <h4 class="mb"><i class="fa fa-angle-right"></i> Tambah Pembelian</h4>
                      <form id="form1" class="form-horizontal style-form" method="get" action="">
                         
                         <div class="form-group">
                         <label class="col-sm-2 col-sm-2 control-label">No. Trip</label>
                         <div class="col-sm-10">
                         <input type="text" name="TxtNoTrip" id="TxtNoTrip" class="form-control2"  style="width:200px;" readonly/>
                         <button type="button" class="btn btn-group btn-info" data-toggle="modal" data-target="#myModalTrip" onclick ="tripCall();"><i class="fa fa-search "></i></button>
                         
                         <!-- REC ID TRIP -->
						 <input type="text" name="TxtIdTrip" id="TxtIdTrip" class="hidden"  style="width:200px;" readonly/>
                         &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
                         
                         <label>Valuta Beli</label> 
                         <input class="form-control2"  style="width:100px;" id="TxtValutaBeli" name="TxtValutaBeli" readonly/>
  						 </div>
                         
                         <label class="col-sm-2 col-sm-2 control-label">Tanggal Trip</label>
                         <div class="col-sm-10">
                         <input id="DateTrip" name="DateTrip" type="text"   class="form-control2"  style="width:200px;" readonly/>
                         &nbsp; &nbsp; &nbsp; &nbsp;  
                         
                         <label>Kurs H.Beli</label> 
                         <input type="text" id="TxtKursBeli" name="TxtKursBeli" class="form-control2"  style="width:150px;" readonly/>
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
                         <input id="TxtStockPcs" name="TxtStockPcs" type="text" class="form-control2"  style="width:150px;"  readonly/>
                         <input id="TxtSatuanPCS2" name="TxtSatuanPCS2" type="text" class="form-control2"  style="width:80px;" Value="PCS" readonly/>
                         <input id="TxtTotStockPcs" name="TxtTotStockPcs" type="text" class="hidden"  style="width:150px;"  readonly/>
                         
                         </div>
                         </div>
                          
                         <div class="form-group">
                         <label class="col-sm-2 col-sm-2 control-label">Qty Beli</label>
                         <div class="col-sm-10">
                         <input id="TxtQtyBeli" name="TxtQtyBeli" type="text" class="form-control2"  style="width:200px;"  />
                         <input type="text" class="form-control2"  style="width:100px;"  value="Karton"  readonly/>
                         </div>
                          
                         <label class="col-sm-2 col-sm-2 control-label">Jumlah</label>
                         <div class="col-sm-10">
                         <input id="TxtJumlahIsi" name="TxtJumlahIsi" type="text" class="form-control2"  style="width:200px;" readonly/>
                         <input id="TxtJumlahSatIsi" name="TxtJumlahSatIsi" type="text" class="form-control2"  style="width:90px;" readonly/>
                         </div>
                         <label class="col-sm-2 col-sm-2 control-label"></label>
                         <div class="col-sm-10">
                         <input id="TxtJumlahQtyBeliPcs" name="TxtJumlahQtyBeliPcs" type="text" class="form-control2"  style="width:200px;" readonly/>
                         <input id="" name="" type="text" class="form-control2"  value="PCS" style="width:80px;" readonly/>
                         </div>
                         </div>
                        
                        <div class="form-group">
                        
                        <label class="col-sm-2 col-sm-2 control-label">H.Beli VTA</label>
                        <div class="col-sm-10">
                        <input  id="TxtIsi2" name="TxtIsi2" type="text" class="form-control2"  style="width:100px;" readonly/>
                        <input id="TxtValutaBeli1" name="TxtValutaBeli1" type="text" class="form-control2"  style="width:100px;" width="20%" readonly/>
                        <input id="TxtHB" name="TxtHB" type="text" class="form-control2"  style="width:150px;"  placeholder="">
                        </div>
                        
                        
                        <label class="col-sm-2 col-sm-2 control-label"></label>
                        <div class="col-sm-10">
                        <input  id="" name="" type="text" class="form-control2"  style="width:100px;" readonly/>
                        <input id="TxtValutaBeliIdr" name="TxtValutaBeliIdr" type="text" class="form-control2"  style="width:100px;" width="20%" value="IDR" readonly/>
                        <input id="TxtHBidr" name="TxtHBidr" type="text" class="form-control2"  style="width:200px;"  readonly/>
                        </div>
                        
                        <label class="col-sm-2 col-sm-2 control-label">H.Jual IDR</label>
                        <div class="col-sm-10">
                        <input id="TxtIsi3" name="TxtIsi3" type="text" class="form-control2"  style="width:100px;" readonly/>
                        <input type="text" class="form-control2"  style="width:100px;" value="IDR" readonly/>
                        <input id="TxtHJRP" name="TxtHJRP" type="text" class="form-control2"  style="width:200px;"  >
                        </div>
                        <label class="col-sm-2 col-sm-2 control-label">Cost(VTA)</label>
                        <div class="col-sm-10">
                        <input id="TxtCost" name="TxtCost" type="text" class="form-control2"  style="width:150px;">
                        </div>
                        </div>
                        
                        <h4 class="mb"><i class="fa fa-angle-right"></i> Jumlah Pembelian</h4>
                        
                        <div class="form-group">
                        <label class="col-sm-2 col-sm-2 control-label">Perh.dalam $</label>
                        <div class="col-sm-10">
                        <input id="TxtValutaBeli2" name="TxtValutaBeli2" type="text" class="form-control2"  style="width:100px;" readonly/>
                        <input id="TxtPerHD" name="TxtPerHD" class="form-control2"  style="width:150px;"  type="text" sreadonly/>
                        
                        <label> Bruto</label>
                        &nbsp; &nbsp;
                        <input id="TxtDisc" name="TxtDisc" class="form-control2"  style="width:100px;"  type="text" value="0">
                        
                        <label>% Diskon</label>
                        &nbsp; &nbsp;
                        <input id="TxtJumNetPerHD" name="TxtJumNetPerHD" class="form-control2"  style="width:150px;"  type="text" placeholder="Netto" readonly/>
                        
                        <label>Netto</label>
                        </div>
                        
                        <label class="col-sm-2 col-sm-2 control-label">Perh.dalam.RP</label>
                        <div class="col-sm-10">
                        <input type="text" class="form-control2"  style="width:100px;" value="IDR" Readonly/>
                        <input id="TxtPerHD1" name="TxtPerHD1" class="form-control2"  style="width:150px;"  type="text" placeholder="Bruto" readonly/>
                        
                        <label> Bruto</label>
                        &nbsp; &nbsp;
                        <input class="form-control2"  style="width:100px;"  type="text" value="------" disabled/>
                        
                        <label> Diskon</label>
                        &nbsp; &nbsp;
                        <input id="TxtJumNetPerHD1" name="TxtJumNetPerHD1" class="form-control2"  style="width:150px;"  type="text" placeholder="Netto" readonly/ >
                        
                        <label>Netto</label>
                        </div>
                        </div>
                        
                        <div class="form-group">
                        
                        <label class="col-sm-2 col-sm-2 control-label">HB.$/PCS</label>
                        <div class="col-sm-10">
                        <input id="TxtValutaBeli3" name="TxtValutaBeli3" type="text" class="form-control2"  style="width:100px;" disabled>
                        <input id="TxtHargaPcsVta" name="TxtHargaPcsVta" class="form-control2"  style="width:200px;"  type="text" >
                        </div>
                        
                        <label class="col-sm-2 col-sm-2 control-label">HB.RP/PCS</label>
                        <div class="col-sm-10">
                        <input type="text" class="form-control2"  style="width:100px;" value="IDR" disabled>
                        <input id="TxtHargaPcsIdr" name="TxtHargaPcsIdr" class="form-control2"  style="width:200px;"  type="text" >
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
          
          
          
<!-------------------------------------------- MODAL TABEL -------------------------------------->
          

<!-- Tabel Modal TRIP -->
 <div class="modal fade" id="myModalTrip" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                 <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>

                 </button>
                
                 <h4 class="modal-title" id="myModalLabel">Trip List</h4>
			</div>
			
			<button id="showSearchTrip" type="button" class="btn btn-group btn-info"><i class="glyphicon glyphicon-search" title="cari"></i> Search</i></button>
            
			<!--Search Table Trip-->
			<div id="searchTrip" class="panel panel-default hidden">
			
			<div class="panel-heading" >
			<button id="hideSearchTrip" type="button" class="btn btn-group btn-info">Hide</button>
			</div>
			<div class="panel-body" id="panelBody">
			<div class="col-lg-6">
			<div class="form-group">
			
			<label class="col-sm-2 col-sm-2 control-label">Dari</label>
			<div class="col-sm-10">
			<input type="text" id="dateFromTrip" value = "06/01/2010" class="form-control2" style="width:200px;" >
			</div>
			
			<label class="col-sm-2 col-sm-2 control-label">Sampai</label>
			<div class="col-sm-10">
			<input type="text" id="dateToTrip" value = "01/01/2100"  class="form-control2" style="width:200px;" >
			</div>
			</div>
			<div class="form-group">
			<button onclick="tripCall();" type="button" style="width:300px;" class="btn btn-outline btn-primary btn-lg btn-block"><i class="glyphicon glyphicon-search" title="cari"></i></button>
			</div>
			</div>
			</div>
			</div>
			
				<!-- Pagging -->
            <label> Page </label>
            <select onchange="getPageTrip(this)"; id="selectPageTrip">
            
            </select>
            <label> of </label>
            <label id = "totPageTrip"></label>
            <!-- page end -->
            <br/> 
            
            <div style="border:1px solid white;height:400px;width:100%;overflow-x:scroll;overflow-y:scroll;">
                 
            <div class="modal-body">
          	<!-- Tabel Trip -->
           	<table class="table1 table-bordered table-striped table-condensed">
           	                 <thead>
                              <tr>
                              	  <th> No. Trip</th>
                                  <th> Tgl. Trip</th>
                                  <th> Nama Vendor</th>
                                  <th> Negara</th>
                                  <th> Valuta</th>
                                  <th> Kurs</th>
                                  <th> No. Nota</th>
                                  <th> Tgl. Nota</th>
                                  <th> Tgl. Terima</th>
                              </tr>
                              </thead>
							<tbody id="tbdTrip">
                            </tbody> 
                          </table> 
            		</div>
            <div class="modal-footer"></div>
        	</div>
        	</div>
   			</div>
			</div>


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
			<select id = "selByValue" class="form-control2" style="width:200px;">
				<option value = "code">Kode Barang</option>
				<option value = "name">Nama Barang</option>
			</select>
			</div>
			<label class="col-sm-2 col-sm-2 control-label"></label>
                         
			<div class="col-sm-10">
			<input type="text" id="inpQuery" class="form-control2" style="width:200px;" >
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
                       			<th> Sisa Stok(PCS)</th>
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

<!-------------------------------------------- SCRIPT -------------------------------------->
          <script type="text/javascript">
			
			/* DELETE DATA */
			
       		var pembelianRemoveCall = function(_id,_idStock,_idTrip,_totQtyBeliPcs,_totStokPcs) {
       			if(confirm("Do you want to delete ?")) {
       			    this.click;
				var _data={};
				 _data['id'] = _id;
				 _data['stockEnt'] =_idStock;
				 _data['tripEnt'] = _idTrip; 
				/*_data['totStockPcs']= _totStokPcs;
				_data['qtyBeliPcs']= _totQtyBeliPcs; */
				JSON.post(_data,'${ctx }/json/detpembelian-del',10000,pembelianDelete,null,null);
				
       	 		}
			else {
				
			return;
			}
       			
       		};
       		/*End delete*/
       		</script>
          <script type="text/javascript">
          /* Add pembelian */
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
						_data['stockEnt']=$('#TxtIdStock').val().trim();
						_data['qtyBeliPcs']=$('#TxtJumlahQtyBeliPcs').val().trim();
						_data['hargaBisiCtnVta']=$('#TxtHB').val().trim();
						_data['hargaBisiCtnIdr']=$('#TxtHBidr').val().trim();
						_data['hargaJisiCtn']=$('#TxtHJRP').val().trim();
						_data['totHargaBeliVta']=$('#TxtJumNetPerHD').val().trim();
						_data['totHargaBeliIdr']=$('#TxtJumNetPerHD1').val().trim();
						_data['cost']=$('#TxtCost').val().trim();
						_data['disc']=$('#TxtDisc').val().trim();
						JSON.post(_data,'${ctx }/json/detpembelian-add',10000,pembelianAdd,null,null);
					};
					/* Start Paging Pembelian */
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
						JSON.post(_data,'${ctx }/json/getPageDetPembelian',10000,pembelianSent,null,null);
						/* setPagging("page"+obj.value,"",""); */
					}
					
					/* End Paging Pembelian */
         		 /*Tabel Pembelian*/
          			var pembelianSent = function(data) {
          			document.getElementById("tbdPembelian").innerHTML = "";
                  		if(data.page){
                  			document.getElementById("totPage").innerHTML = data.page;
                  			setPagging("1",data.page,"1"); 
                  					
                  		}
        				var _items = data.items;
        				$('#tbdPembelian').empty();
        				for ( var i = 0; i < _items.length; i++) {
        				var _item = _items[i];
        				var _id = "'"+_item.id+"'";
        				var _idStock = "'"+_item.idStock+"'";
        				var _idTrip = "'"+_item.idTrip+"'";
        				var _totQtyBeliPcs = "'"+_item.totQtyBeliPcs+"'";
        				var _totStokPcs = "'"+_item._totStokPcs+"'";
        				
        				//var _tot=_item.hpp*_item.kurs;
        				//.html(_tot);
        				$('#tbdPembelian').append(
        						$('<tr><\/tr>')
        							.append($('<td><\/td>').html(_item.tripNumber))
        							.append($('<td><\/td>').html($.datepicker.formatDate('d MM yy',new Date(_item.tripDate))))
        							.append($('<td><\/td>').html(_item.productCode))
        							.append($('<td><\/td>').html(_item.productName))
        							.append($('<td><\/td>').html(_item.totQtyBeliPcs))
        							.append($('<td><\/td>').html(_item.totStokPcs))
        							.append($('<td><\/td>').html(_item.disc))
        							.append($('<td><\/td>').html(_item.vendVta))
        							.append($('<td><\/td>').html(_item.hargaNetBeliIsiCtnVta))
        							.append($('<td><\/td>').html(_item.tripKurs))
        							.append($('<td><\/td>').html(_item.hargaNetBeliIsiCtnIdr))
        							.append($('<td><button type="button" onclick="pembelianRemoveCall('+_id+','+_idStock+','+_idTrip+','+_totQtyBeliPcs+','+_totStokPcs+');" class="btn btn-danger btn-xs"><i class="fa fa-trash-o "><\/i><\/button><\/td>'))
        							
        								
        					);
        				}
        			};
        			
          			var pembelianCall = function() {
					var _data={};
					_data["dateFrom"] = $('#dateFrom').val().trim();
        			_data["dateTo"] = $('#dateTo').val().trim();
					JSON.post(_data,'${ctx }/json/detpembelian',10000,pembelianSent,null,null);
					 var _pageTot = pembelianSent.page; 
					 
					};
					
					var pembelianAdd = function(data){
						
						if(data.code == 0){
							alert(data.message);
							$('#myModalAddPembelian').modal('hide');
							pembelianCall();
							
							}
							if (data.code != 0) {
								
								alert(data.message);
							}
							else {
								
							}
							};
							
					var pembelianDelete = function(data){
						if(data.code == 0){
							alert(data.message);
									
							pembelianCall();
											
							}
							else {
												
							}
							};
					pembelianCall();
				
				</script>
		
			<!-- Tabel Trip -->
			<script type="text/javascript">
				function autoInput(a,b,c,d,e){
				document.getElementById("TxtNoTrip").value = a;
				document.getElementById("DateTrip").value = b;
				document.getElementById("TxtValutaBeli").value = c;
				document.getElementById("TxtValutaBeli1").value = c;
				document.getElementById("TxtValutaBeli2").value = c;
				document.getElementById("TxtValutaBeli3").value = c;
				document.getElementById("TxtKursBeli").value = d;
				document.getElementById("TxtIdTrip").value = e;
				$('#myModalTrip').modal('hide');
				
				//document.getElementById("TxtNegara").value = b;
				}
				/* Start Paging */
				function getPageTrip(obj){
				var _data={};
				_data['page'] = "page"+obj.value;
				JSON.post(_data,'${ctx }/json/getPageTrip',10000,tripSent,null,null);
				/* setPagging("page"+obj.value,"",""); */
				}
			
				/* End Paging Barang */
			
				var tripSent = function(data) {
				document.getElementById("tbdTrip").innerHTML = "";
	          	if(data.page){
	          	document.getElementById("totPageTrip").innerHTML = data.page;
	          	_customPage = "Trip";
	          	setPagging("1",data.page,"1");
	          	}
				
  				var _items = data.items;
				$('#tbdTrip').empty();
				for ( var i = 0; i < _items.length; i++) {
					var _item = _items[i];
					//var _tot=_item.hpp*_item.kurs;
					//.html(_tot);
					var _tr = document.createElement("tr");
						_tr.setAttribute("onclick", "autoInput('"+_item.trip_numb+"','"+$.datepicker.formatDate('d MM yy',new Date(_item.trip_date))+"','"+_item.trip_vta+"','"+_item.currencyIDR+"','"+_item.id+"')");
					$('#tbdTrip').append(
					$(_tr)
    							.append($('<td class="hidden"><\/td>').html(_item.id))
    							.append($('<td><\/td>').html(_item.trip_numb))
    							.append($('<td><\/td>').html($.datepicker.formatDate('d MM yy',new Date(_item.trip_date))))
    							.append($('<td><\/td>').html(_item.trip_vendName))
    							.append($('<td><\/td>').html(_item.trip_vendCountry))
    							.append($('<td><\/td>').html(_item.trip_vta))
    							.append($('<td><\/td>').html(_item.currencyIDR))
    							.append($('<td><\/td>').html(_item.trip_noteNumber))
    							.append($('<td><\/td>').html($.datepicker.formatDate('d MM yy',new Date(_item.trip_dateNote))))
    							.append($('<td><\/td>').html($.datepicker.formatDate('d MM yy',new Date(_item.dateReceive))))
    							);
    				}
    				// paggin umpet
    			
    				};
    		
    				var tripCall = function() {
	        			var _data={};
	        			_data["dateFrom"] = $('#dateFromTrip').val().trim();
	        			_data["dateTo"] = $('#dateToTrip').val().trim();
						JSON.post(_data,'${ctx }/json/trip',10000,tripSent,null,null);
						 var _pageTot = tripSent.page; 
						
					};
					
			</script>
		
			<!--Start Call Data Barang -->
			
			<script type="text/javascript">
				function autoInputProd(a,b,c,d,e,f,g,h){
					document.getElementById("TxtIdStock").value = a;
					document.getElementById("TxtKodeProduct").value = b;
					document.getElementById("TxtNamaProduct").value = c;
					document.getElementById("TxtIsiCtn").value = d;
					document.getElementById("TxtSatIsiCtn").value = e;
					document.getElementById("TxtIsi2").value = e;
					document.getElementById("TxtIsi3").value = e;
					document.getElementById("TxtJumlahSatIsi").value = e;
					document.getElementById("TxtIsiPcs").value = f;
					document.getElementById("TxtStockPcs").value = g;
					
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
							_tr.setAttribute("onclick", "autoInputProd('"+_item.stockId+"','"+_item.productCode+"','"+_item.productName+"','"+_item.isiCtn+"','"+_item.satIsiCtn+"','"+_item.isiPcs+"','"+_item.totStokPcs+"')");
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
							.append($('<td><\/td>').html(_item.totStokPcs))
							.append($('<td class="hidden"><\/td>').html(_item.isiCtn))
							.append($('<td class="hidden"><\/td>').html(_item.satIsiCtn))
							.append($('<td class="hidden"><\/td>').html(_item.isiPcs))
							);
						}
					};
					
					var productCall = function() {
						var _data={};
						_data['queryData']=$('#inpQuery').val().trim();
						_data['byValue']=$('#selByValue').val().trim();
						JSON.post(_data,'${ctx }/json/stock',10000,productSent,null,null);
						 var _pageTot = productSent.page; 
					};
				</script>
      
			<!-- Auto calculation -->
			<script type="text/javascript">
			<!-- QtyBeli*IsiKarton)-->
			
			// perh.dalam*jumlah
			$('#TxtHB, #TxtQtyBeli, #TxtIsiPcs, #TxtIsiCtn').on('input',function() {
		  	var TxtHB = parseFloat($('#TxtHB').val());
		    var TxtQtyBeli = parseInt($('#TxtQtyBeli').val());
		    var TxtIsiPcs = parseInt($('#TxtIsiPcs').val());
		    var TxtIsiCtn = parseInt($('#TxtIsiCtn').val());
		    $('#TxtPerHD').val(((TxtQtyBeli * TxtHB) * (TxtIsiPcs * TxtIsiCtn) ? (TxtQtyBeli * TxtHB) * (TxtIsiPcs * TxtIsiCtn) : 0).toFixed(2));
			});
		
			/* //perh.dalam*jumlah IDR
			$('#TxtHB, #TxtQtyBeli, #TxtKursBeli, #TxtIsiPcs, #TxtIsiCtn').on('input',function() {
		    var TxtHB = parseFloat($('#TxtHB').val());
		    var TxtQtyBeli = parseInt($('#TxtQtyBeli').val());
		    var TxtKursBeli = parseFloat($('#TxtKursBeli').val());
		    var TxtIsiPcs = parseInt($('#TxtIsiPcs').val());
		    var TxtIsiCtn = parseInt($('#TxtIsiCtn').val());
		    $('#TxtPerHD1').val((TxtHB * TxtQtyBeli * TxtKursBeli * TxtIsiPcs * TxtIsiCtn ? TxtHB * TxtQtyBeli * TxtKursBeli * TxtIsiPcs * TxtIsiCtn : 0).toFixed(2));
			}); */
		
			
			/* //Netto
			$('#TxtPerHD, #TxtDisc, #TxtPerHD1,#TxtCost, #TxtKursBeli ').on('input',function() {
		    var TxtPerHD = parseFloat($('#TxtPerHD').val());
		    var TxtDisc = parseFloat($('#TxtDisc').val());
		    var TxtCost = parseFloat($('#TxtCost').val());
		    var TxtKursBeli = parseFloat($('#TxtKursBeli').val());
		    var TxtPerHD1 = parseFloat($('#TxtPerHD1').val());
		    
		    $('#TxtJumNetPerHD').val(((TxtPerHD - ((TxtPerHD * TxtDisc)/100))+ TxtCost ? (TxtPerHD - ((TxtPerHD * TxtDisc) /100)) + TxtCost : 0).toFixed(2));
		    $('#TxtJumNetPerHD1').val(((TxtPerHD1 - (((TxtPerHD1 * TxtDisc)/100)))+(TxtCost * TxtKursBeli) ? ((TxtPerHD1 - ((TxtPerHD1 * TxtDisc)/100)))+(TxtCost * TxtKursBeli) : 0).toFixed(2));
		    
			}); */
		
			
			</script>
		
		
		   <script type="text/javascript">
         	 /*Date Picker*/
          	$(function(){
          	$('#dateFrom,#dateTo,#dateFromTrip,#dateToTrip').datepicker();
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
  </body>
</html>
