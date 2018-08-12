<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="d" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
  <body>
          	<h3><i class="fa fa-angle-right"></i> Order Penjualan</h3>
            <button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModalAddOrder">ADD Order</button>
			<br/>
			<br/>
            
			</div>
			<!--Search Table Penjualan-->
			<div class="panel panel-default">
			<div class="panel-heading" >
			<i class="glyphicon glyphicon-search" title="cari"></i> Search</i>
			</div>
			<div class="panel-body" id="panelBody">
			<div class="col-lg-6">
			<div class="form-group">
			<label class="col-sm-2 col-sm-2 control-label">Kategori</label>
                         
			<div class="col-sm-10">
			<select id = "selByValueOrder" class="form-control2" style="width:200px;">
				<option value = "orderNumb">Nomor Order</option>
				<option value = "fakturNumb">Nomor Faktur</option>
			</select>
			
			</div>
			<label class="col-sm-2 col-sm-2 control-label"></label>
                         
			<div class="col-sm-10">
			<input type="text" id="inpQueryOrder" class="form-control2" style="width:200px;" >
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-sm-2 col-sm-2 control-label"></label>
                         
			<button onclick="orderCall();" type="button" style="width:220px;" class="btn btn-outline btn-primary btn-lg btn-block"><i class="glyphicon glyphicon-search" title="cari"></i></button>
			</div>
			</div>
			</div>
			</div>
			<br/>
			
			<!-- Pagging -->
            <label> Page </label>
            <select onchange="getPageOrder(this)"; id="selectPageOrder">
            
            </select>
            <label> of </label>
            <label id = "totPageOrder"></label>
            <!-- page end -->
            
            
            <div style="border:1px solid white;height:400px;width:100%;overflow-x:scroll;overflow-y:scroll;">
		  	<div class="row mt">
			<div class="col-lg-12">
            
            <!--Table Order Penjualan-->
                <table class="table1 table-bordered table-striped table-condensed table-hover">
                   <thead>
                   <tr>
                   <th> Nomor Order</th>
                   <th> Nomor Faktur</th>
                   <th> Kode barang </th>
                   <th> Nama barang </th> <!--TxtNamaBarang -->
                   <th> Qty Order (Pcs)</th> <!--TxtQtyOrder -->
                   <th> Hrg/PCS (IDR)</th>
                   <th> Total Jual (IDR)</th>
                   
                   <th>ACTION</th>
                   </tr>
                   </thead>
				   <tbody id="tbdOrder">
                   </tbody>
                   </table>
                 </section>
               </div><!--content-panel-->
             </div><!--col-lg-4 -->			
		  </div><!--row -->
      	
      	<!--Form ORder -->
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
                 <div class="form-group">
                  <label class="col-sm-2 col-sm-2 control-label">No. Order</label>
                  <div class="col-sm-10">
                  <input id="TxtOrderNumb" name="TxtOrderNumb" type="text"   class="form-control2"  style="width:150px;" readonly/>
                  <input id="TxtIdPenj" name="TxtIdPenj" type="text"   class="hidden"  style="width:150px;" readonly/>
                  
                  <button type="button" class="btn btn-group btn-info" data-toggle="modal" data-target="#myModalPenjualan" onclick="penjualanCall();"><i class="fa fa-search "></i></button>
                  </div>
                  </div>
					
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
                  <label>isi CTN</label>
                  <input type="text" id="TxtIsiCtn"  class="form-control2"  style="width:150px;" readonly/>
                  <input type="text" id="TxtSatIsiCtn"  class="form-control2"  style="width:80px;" readonly/>
                  <label>isi Pcs</label>
                  <input type="text" id="TxtIsiPcs"  class="form-control2"  style="width:150px;" readonly/>
                  <input type="text" id="TxtSatIsiPcs"  class="form-control2"  style="width:80px;" placeholder="PCS" readonly/>
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
                  
                  <label class="col-sm-2 col-sm-2 control-label">Sisa Stok PCS</label>
                  <div class="col-sm-10">
                  <input type="text" id="TxtStokPcs"  class="form-control2"  style="width:150px;" readonly/>
                  <input type="text" id="TxtSatStokPcs"  class="form-control2"  style="width:80px;" placeholder="PCS" readonly/>
                  </div>
                  
                  
                  <label class="col-sm-2 col-sm-2 control-label">Harga Jual Standart /</label>
                  <div class="col-sm-10">
                  <input type="text" id="TxtSatIsiCtn2"  class="form-control2"  style="width:80px;" readonly/>
                  <input type="text"   class="form-control2"  style="width:50px;" value="IDR" readonly/>
                  <input type="text" id="TxtHargaSatIsiCtn"  class="form-control2"  style="width:200px;" readonly/>
                  </div>
                  </div>
                   	
                  <h4 class="mb"><i class="fa fa-angle-right"></i> QTY Penjualan</h4>
                   
                   <div class="form-group">
                   <label class="col-sm-2 col-sm-2 control-label">Qty Order</label>
                   <div class="col-sm-10">
                   <input id="TxtQtyOrder" name="TxtQtyOrder" type="text" class="form-control2"  style="width:150px;" placeholder="">
                   <input type="text" class="form-control2"  style="width:100px;" width="20%" value="Pcs" readonly/> <!-- Harus CTN -->
                   </div>
                   </div>

					
                  	
                 	<div class="form-group">
                 	<label class="col-sm-2 col-sm-2 control-label">Harga (Pcs)</label>
                 	<div class="col-sm-10">
                 	<input class="form-control2"  style="width:150px;" id="TxtHargaPcs" name="TxtHargaPcs" type="text" >
                	</div>
                  	
                  	<%-- <label class="col-sm-2 col-sm-2 control-label">Discount</label>
                  	<div class="col-sm-10">
                  	<input id="TxtDiskon" name="TxtDiskon" type="text" class="form-control2"  style="width:100px;" ><label>%</label>
                  	</div> --%>
                  	
                  
                  	<label class="col-sm-2 col-sm-2 control-label">Jumlah (Rp)</label>
                  	<div class="col-sm-10">
                  	<input id="TxtTotalHarga" name="TxtTotalHarga" type="text"  class="form-control2"  style="width:200px;" placeholder="" readonly/>
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
                       		<th> Sisa Stok(PCS)</th>
                       		<th> Isi CTN</th>
                       		<th> Sat Isi CTN</th>
                       		<th> Isi PCS</th>
                       		<th> Harga / Isi CTN</th>
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


<!-- Tabel modal Penjualan -->
<div class="modal fade" id="myModalPenjualan" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>

                </button>
                
            <h4 class="modal-title" id="myModalLabel">List Penjualan</h4>
			</div>
			<!--Search Table Penjualan-->
			<div class="panel panel-default">
			<div class="panel-heading" >
			<i class="glyphicon glyphicon-search" title="cari"></i> Search</i>
			</div>
			<div class="panel-body" id="panelBody">
			<div class="col-lg-6">
			<div class="form-group">
			<label class="col-sm-2 col-sm-2 control-label">Kategori</label>
                         
			<div class="col-sm-10">
			<select id = "selByValuePenj" class="form-control2" style="width:200px;">
				<option value = "orderNumb">Nomor Order</option>
				<option value = "fakturNumb">Nomor Faktur</option>
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
			</div>
			</div>
			</div>
			</div>
			<br/>
			
			<!-- Pagging -->
            <label> Page </label>
            <select onchange="getPagePenj(this)"; id="selectPagePenj">
            
            </select>
            <label> of </label>
            <label id = "totPagePenj"></label>
            <!-- page end -->
            <div style="border:1px solid white;height:300px;width:100%;overflow-x:scroll;overflow-y:scroll;">
                 
            <div class="modal-body">
            <!-- Customer -->
               <table id="tblPenjualan" class="table2 table-bordered table-striped table-condensed">
					<thead>
                              <tr>
                                  <th> Tanggal Order</th>
                                  <th> No. Order </th>
                                  <th> No. Faktur </th>
                                  <th> Nama Customer</th>
                                  <th> Kota</th>
                                  <th> Alamat</th>
                                  <th> Kode Sales</th>
                                  <th> Nama Sales</th>
                                  
                              </tr>
                              </thead>
							</div>
					
                              <tbody id="tbdPenjualan">
                              </tbody>
                          </table>
                      
            </div>
            <div class="modal-footer"></div>
        </div>
        </div>
    </div>
</div> 
              
            <script type="text/javascript">
            /* DELETE DATA */
			
       		var orderRemoveCall = function(_id,_idStock,_idPenjualan,_totQtyJualIsi2Ctn,_totStokPcs) {
				
       			if(confirm("Do you want to delete ?")) {
       			    this.click;
				var _data={};
				 _data['id'] = _id;
				 _data['stockEnt'] =_idStock;
				 _data['penjualanEnt'] = _idPenjualan; 
				JSON.post(_data,'${ctx }/json/detpenjualan-del',10000,orderDelete,null,null);
				
       	 		}
			else {
				
			return;
			}
       			
       		};
       		/*End delete*/
       		</script>
            <script type="text/javascript">
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
						_data['totQtyJualPcs']=$('#TxtQtyOrder').val().trim();
						_data['hargaJualPcs']=$('#TxtHargaPcs').val().trim();
						
						_data['totJual']=$('#TxtTotalHarga').val().trim();
						
						JSON.post(_data,'${ctx }/json/detpenjualan-add',10000,orderAdd,null,null);
						
					};
					
            function getPageOrder(obj){
				var _data={};
				_data['page'] = "page"+obj.value;
				JSON.post(_data,'${ctx }/json/getPageDetPenjualan',1000000,orderSent,null,null);
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
        				var _totQtyJualIsi2Ctn = "'"+_item.totQtyJualIsi2Ctn+"'";
        				var _totStokPcs = "'"+_item._totStokPcs+"'";
        				//var _tot=_item.hpp*_item.kurs;
        				//.html(_tot);
        				$('#tbdOrder').append(
        						$('<tr><\/tr>')
        							.append($('<td><\/td>').html(_item.orderNumb))
        							.append($('<td><\/td>').html(_item.fakturNumb))
        							.append($('<td><\/td>').html(_item.productCode))
        							.append($('<td><\/td>').html(_item.productName))
        							.append($('<td><\/td>').html(_item.totQtyJualPcs))
        							.append($('<td><\/td>').html(_item.hargaJualPcs))
        							.append($('<td><\/td>').html(_item.totJual))
        							.append($('<td><button type="button" onclick="orderRemoveCall('+_id+','+_idStock+','+_idPenjualan+','+_totQtyJualIsi2Ctn+','+_totStokPcs+');" class="btn btn-danger btn-xs"><i class="fa fa-trash-o "><\/i><\/button><\/td>'))
        								
        				);
        			}
        		};
        		
        		var orderAdd = function(data){
					
					if(data.code == 0){
						alert(data.message);
						$('#myModalAddOrder').modal('hide');
						orderCall();
						
						}
						if (data.code != 0) {
							
							alert(data.message);
						}
						else {
							
						}
						};
						
				var orderDelete = function(data){
				if(data.code == 0){
					alert(data.message);
								
					orderCall();
										
					}
					else {
											
					}
					};
        		var orderCall = function() {
          			var _data={};
          			var _a = document.getElementById("inpQueryOrder").value;
          			if(_a == ""){
          				return;
          			}
          			_data['queryDataOrder']=$('#inpQueryOrder').val().trim();
					_data['byValueOrder']=$('#selByValueOrder').val().trim();
					JSON.post(_data,'${ctx }/json/detpenjualan',10000,orderSent,null,null);
        		};
        		
			</script>
		
			<!-- Tabel Penjualan -->
			<script type="text/javascript">
			function autoInputPenj(a,b){
				document.getElementById("TxtOrderNumb").value = b;
				document.getElementById("TxtIdPenj").value = a;
				
				$('#myModalPenjualan').modal('hide');
				
			}
			function getPagePenj(obj){
				var _data={};
				_data['page'] = "page"+obj.value;
				JSON.post(_data,'${ctx }/json/getPagePenjualan',10000,penjualanSent,null,null);
				/* setPagging("page"+obj.value,"",""); */
			}
			var penjualanSent = function(data) {
				document.getElementById("tbdPenjualan").innerHTML = "";
  				if(data.page){
  					document.getElementById("totPagePenj").innerHTML = data.page;
  					_customPage = "Penj";
  					 setPagging("1",data.page,"1");
  				}
				var _items = data.items;
				$('#tbdPenjualan').empty();
				for ( var i = 0; i < _items.length; i++) {
					var _item = _items[i];
					//var _tot=_item.hpp*_item.kurs;
					//.html(_tot);
					var _tr = document.createElement("tr");
    				_tr.setAttribute("onclick", "autoInputPenj('"+_item.id+"','"+_item.orderNumb+"')");
    				$('#tbdPenjualan').append(
    				$(_tr)
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
          		var penjualanCall = function() {
          			var _data={};
          			_data['queryDataPenj']=$('#inpQueryPenj').val().trim();
					_data['byValuePenj']=$('#selByValuePenj').val().trim();
					JSON.post(_data,'${ctx }/json/penjualan',10000,penjualanSent,null,null);
				};
				
				</script>
				
				<script type="text/javascript">
				function autoInputProd(a,b,c,d,e,f,g,h){
					document.getElementById("TxtProductCode").value = a;
					document.getElementById("TxtProductName").value = b;
					document.getElementById("TxtStokPcs").value = c;
					document.getElementById("TxtIsiCtn").value = d;
					document.getElementById("TxtSatIsiCtn").value = e;
					document.getElementById("TxtIsiPcs").value = f;
					document.getElementById("TxtStokCtn").value = g;
					document.getElementById("TxtIdStock").value = h;
					
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
				document.getElementById("tbdProduct").innerHTML = "";
  				if(data.page){
  					document.getElementById("totPageStock").innerHTML = data.page;
  					_customPage = "Stock";
  					 setPagging("1",data.page,"1");
  				}
				var _items = data.items;
				$('#tbdProduct').empty();
				for ( var i = 0; i < _items.length; i++) {
					var _item = _items[i];
					//var _tot=_item.hpp*_item.kurs;
					//.html(_tot);
					var _tr = document.createElement("tr");
    				_tr.setAttribute("onclick", "autoInputProd('"+_item.productCode+"','"+_item.productName+"','"+_item.totStokPcs+"','"+_item.isiCtn+"','"+_item.satIsiCtn+"','"+_item.isiPcs+"','"+_item.stokCtn+"','"+_item.stockId+"')");
    				$('#tbdProduct').append(
    				$(_tr)
	        						//.append($('<td><\/td>').html(_item.productBarcode))
        							.append($('<td><\/td>').html(_item.productCode))
        							.append($('<td><\/td>').html(_item.productName))
        							//.append($('<td><\/td>').html(_item.productMerk))
        							//.append($('<td><\/td>').html(_item.productGroup))
        							//.append($('<td><\/td>').html(_item.productMadeIn))
        							//.append($('<td><\/td>').html(_item.productPartNumb))
        							//.append($('<td><\/td>').html(_item.productStandartNo))
        							.append($('<td><\/td>').html(_item.stokCtn))
        							.append($('<td><\/td>').html(_item.totStokPcs))
        							.append($('<td><\/td>').html(_item.isiCtn))
									.append($('<td><\/td>').html(_item.satIsiCtn))
									.append($('<td><\/td>').html(_item.isiPcs))
									
									.append($('<td><\/td>').html(_item.hargaJualIsiCtn))
        							
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
				$('#TxtQtyOrder, #TxtHargaPcs').on('input',function() 
				{
		    		var TxtQtyOrder = parseInt($('#TxtQtyOrder').val());
		   			var TxtHargaPcs = parseInt($('#TxtHargaPcs').val());
		   			$('#TxtTotalHarga').val((TxtQtyOrder * TxtHargaPcs ? TxtQtyOrder * TxtHargaPcs : 0).toFixed(2));
				});
			
			</script>
			
</body>
</html>
