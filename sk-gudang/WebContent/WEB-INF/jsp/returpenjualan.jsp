<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="d" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<body>
          	<h3><i class="fa fa-angle-right"></i> Retur Penjualan</h3>
            <button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModalAddRetur">ADD Retur Penjualan</button>
			<br/>
			<br/>
			
			<!--Search Table Retur Penjualan-->
			<div class="panel panel-default">
			<div class="panel-heading" >
			<i class="glyphicon glyphicon-search" title="cari"></i> Search</i>
			</div>
			<div class="panel-body" id="panelBody">
			<div class="col-lg-6">
			<div class="form-group">
			<label class="col-sm-2 col-sm-2 control-label">Kategori</label>
                         
			<div class="col-sm-10">
			<select id = "selByValueRetur" class="form-control2" style="width:200px;">
				<option value = "noRetur">Nomor Order/Retur</option>
				
			</select>
			
			</div>
			<label class="col-sm-2 col-sm-2 control-label"></label>
                         
			<div class="col-sm-10">
			<input type="text" id="inpQueryRetur" class="form-control2" style="width:200px;" >
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-sm-2 col-sm-2 control-label"></label>
                         
			<button onclick="returpenjualanCall();" type="button" style="width:220px;" class="btn btn-outline btn-primary btn-lg btn-block"><i class="glyphicon glyphicon-search" title="cari"></i></button>
			</div>
			</div>
			</div>
			</div>
			<br/>
			
			<!-- Pagging -->
            <label> Page </label>
            <select onchange="getPageRtr(this)"; id="selectPageRtr">
            
            </select>
            <label> of </label>
            <label id = "totPageRtr"></label>
            <!-- page end -->
            <div style="border:1px solid white;height:450px;width:100%;overflow-x:scroll;overflow-y:scroll;">
		  		
            <div class="row mt">
			  		<div class="col-lg-12">
                      <!--<div class="content-panel">-->
                      
                          <section id="unseen">
                          
            <!--Table Retur-->
                            
                            <table class="table1 table-bordered table-striped table-condensed table-hover">
                              <thead>
                              <tr>
                                  <th>Tanggal Retur</th>
                                  <th>No. Order</th>
                                  <th>Kode Barang</th>
                                  <th>Nama Barang</th>
                                  <th>Nama Customer</th>
                                  <th>Qty Retur (Ctn)</th>
                                  <th>Nilai Retur (Idr)</th>
                                  <th>Penerima Retur</th>
                                  <th>Alasan Retur</th>
                                  <th>Kode Sales</th>
                                  <th>Action</th>
                              </tr>
                              </thead>

                              <tbody id="tbdRetur">
                              
                              </tbody>
                          </table>
                      </section>
                      
                  </div><!--content-panel-->
               </div><!--col-lg-4 -->			
		  	</div><!--row -->
 
 <div class="modal fade" id="myModalAddRetur" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
                      <form class="form-horizontal style-form" method="get" action="">
                          
                          	  <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">Tanggal Retur</label>
                              <div class="col-sm-10">
                              <input id="DateRetur" name="DateRetur" type="text"   class="form-control2"  style="width:150px;" placeholder="">
                              </div> 
                              </div>  
                                
                              <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">No. Order</label>
                              <div class="col-sm-10">
                              <input id="TxtNoOrder" name="TxtNoOrder" type="text"   class="form-control2"  style="width:150px;" readonly/>
                              <input id="TxtIdOrder" name="TxtIdOrder" type="text"   class="hidden"  style="width:150px;" readonly/>
                              <button type="button" class="btn btn-group btn-info" data-toggle="modal" data-target="#myModalOrder" onclick ="orderCall();"><i class="fa fa-search "></i></button>
                              </div>
                              <label class="col-sm-2 col-sm-2 control-label">No. Retur</label>
                              <div class="col-sm-10">
                              <input id="TxtNoRetur" name="TxtNoRetur" type="text"   class="form-control2"  style="width:150px;" readonly/>
                              
                              </div>
                              </div>
                              
                              <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">Nama Customer</label>
                              <div class="col-sm-10">
                              <input id="TxtNamaCustomer" name="TxtNamaCustomer" type="text" class="form-control2"  style="width:200px;" placeholder="" readonly/>
                              </div>
                              <label class="col-sm-2 col-sm-2 control-label">Alamat</label>
                              <div class="col-sm-10">
                              <input id="TxtAlamat" name="TxtAlamat" type="text" class="form-control2"  style="width:250px;" readonly/>
                              </div>
                              <label class="col-sm-2 col-sm-2 control-label">Kota</label>
                              <div class="col-sm-10">
                              <input id="TxtKota" name="TxtKota" type="text" class="form-control2"  style="width:150px;" readonly/>
                              </div>
						      </div>
                             
                              <div class="form-group">
                              
                              <label class="col-sm-2 col-sm-2 control-label">Kode Barang</label>
                              <div class="col-md-10">
                                 <input id="TxtKodeBarang" name="TxtKodeBarang" type="text"  class="form-control2"  style="width:150px;" placeholder="" readonly/>
                                 <input id="TxtIdStock" name="TxtIdStock" type="text"  class="hidden"  style="width:150px;" placeholder="" readonly/>
                                 
                                    
                              </div>
                              <label class="col-sm-2 col-sm-2 control-label">Nama Barang</label>
                              <div class="col-sm-10">
                                  <input id="TxtNamaBarang" name="TxtNamaBarang" type="text"   class="form-control2"  style="width:200px;" placeholder="" readonly/>
                              </div>
                              <label class="col-sm-2 col-sm-2 control-label">QTY Jual (CTN)</label>
                              <div class="col-sm-10">
                                  <input id="TxtTotQtyJualCtn" name="TxtTotQtyJualCtn" type="number"   class="form-control2"  style="width:200px;" value="0" readonly/>
                              </div>
                              <label class="col-sm-2 col-sm-2 control-label">Harga Jual Bruto(CTN)</label>
                              <div class="col-sm-10">
                              <input  id="TxtNilaiReturBruto" name="TxtNilaiReturBruto" type="number" value="0" class="form-control2"  style="width:200px;" value="0" readonly/> <!--Tampilkan List DB sales-->
                              </div>
                              <label class="col-sm-2 col-sm-2 control-label">Harga Jual Netto(CTN)</label>
                              <div class="col-sm-10">
                                  <input id="TxtHargaJualCtn" name="TxtHargaJualCtn" type="number"   class="form-control2"  style="width:200px;" value="0" readonly/>
                              	  
                              </div>
                              </div>
                              
                              <h4 class="mb"><i class="fa fa-angle-right"></i> QTY Retur</h4>
                              <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">Total Retur (CTN)</label>
                              <div class="col-sm-10">
                              <input id="TxtTotQtyCtnRetur" name="TxtTotQtyCtnRetur" type="number" value="0" class="form-control2"  style="width:150px;" width="20%" > <!-- Isi satu karton barang tsb -->
                              <input type="text" class="form-control2"  style="width:80px;" width="20%" value="CTN" readonly/> <!-- Isi satu karton barang tsb -->
                              </div>
							  <label class="col-sm-2 col-sm-2 control-label">Total Nilai Retur</label>
                              <div class="col-sm-10">
                              <input type="text" class="form-control2"  style="width:80px;" width="20%" value="IDR" readonly/> <!-- Isi satu karton barang tsb -->
                              <input id="TxtNilaiRetur" name="TxtNilaiRetur" type="number" value="0" class="form-control2"  style="width:200px;" placeholder=""> <!--Tampilkan List DB sales-->
                              </div>
							  </div>
                                
                                
                              <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">Penerima</label>
                              <div class="col-sm-10">
                              <input type="text" id="TxtPenerima" name="TxtPenerima"  class="form-control2"  style="width:200px;">
                              </div>
                              <label class="col-sm-2 col-sm-2 control-label">Keterangan / Alasan Retur</label>
                              <div class="col-sm-10">
                              <input type="text" id="TxtKeterangan" name="TxtKeterangan"  class="form-control2"  style="width:500px;">
                              </div>
                           </div>
                           
                              
                              <button type="button" onclick="returpenjualanAddCall();" class="btn btn-theme">SIMPAN</button>
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
      				<!--Form Retur -->
                    
              
<!-- Modal -->
<div class="modal fade" id="myModalOrder" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">Penjualan List</h4>
			</div>
						
            	<div style="border:1px solid white;height:500px;width:100%;overflow-x:scroll;overflow-y:scroll;">
            		<div class="modal-body">
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
                 	 	<table class="table1 table-bordered table-striped table-condensed">
                       		<thead>
                              		<tr>
                                  		<th> Nomor Order</th>
                   						<th> Nomor Faktur</th>
                   						<th> Kode Customer</th>
                   						<th> Nama Customer</th>
                   						<th> Kode Salesman</th>
                   						<th> Kode barang </th>
                   						<th> Nama barang </th> <!--TxtNamaBarang -->
                  						<th> Qty Order</th> <!--TxtQtyOrder -->
                   						<th> Hrg netto/CTN</th>
                   						<th> Total Jual</th>
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
             
              
              
              
<!----------------------------------------  SCRIPT ---------------------------------->
            
            <script type="text/javascript">
            /* DELETE DATA */
       		var returpenjualanRemoveCall = function(_id,_stockId,_penjualanId,orderId,_qtyRetur) {
       			if(confirm("Do you want to delete ?")) {
       			    this.click;
				var _data={};
				 _data['id'] = _id;
				 _data['stockEnt'] =_stockId;
				 _data['penjualanEnt'] = _penjualanId;
				 _data['detPenjualanEnt'] = orderId;
				 _data['totQtyReturCtn'] = _qtyRetur;
				JSON.post(_data,'${ctx }/json/returpenjualan-del',10000,returpenjualanDelete,null,null);
				
       	 		}
			else {
				
			return;
			}
       		};
       		/*End delete*/
              var returpenjualanAddSent = function(data){
					
					var _items = data.items;
		       		for ( var i = 0; i < _items.length; i++) {
		        			$('#tbdReturPenjualan').append(
		        				$('<tr><\/tr>')
		        					.append($('<td><\/td>').html($.datepicker.formatDate('d MM yy',new Date(_item.dateRetur))))
        							.append($('<td><\/td>').html(_item.orderNumb))
        							.append($('<td><\/td>').html(_item.productCode))
        							.append($('<td><\/td>').html(_item.productName))
        							.append($('<td><\/td>').html(_item.customerName))
        							//.append($('<td><\/td>').html(_item.customerAddr))
        							//.append($('<td><\/td>').html(_item.customerCity))
        							.append($('<td><\/td>').html(_item.totQtyReturCtn))
        							.append($('<td><\/td>').html(_item.nilaiReturCtn))
        							.append($('<td><\/td>').html(_item.penerimaRetur))
        							.append($('<td><\/td>').html(_item.alasanRetur))
        							.append($('<td><\/td>').html(_item.salesCode))
		        					
		        				);
		        				 
		        			} 
		        			
		        		};
      		
        		var returpenjualanAddCall = function() {
					var _data={};
					_data['detPenjualanEnt']=$('#TxtIdOrder').val().trim();
					_data['dateRetur']=$('#DateRetur').datepicker('getDate').getTime();
					_data['noRetur']=$('#TxtNoRetur').val().trim(); 
					_data['totQtyReturCtn']=$('#TxtTotQtyCtnRetur').val().trim();
					_data['nilaiNettoReturIdr']=$('#TxtNilaiRetur').val().trim();
					_data['hargaBrutoCtn']=$('#TxtNilaiReturBruto').val().trim();
					_data['penerima']=$('#TxtPenerima').val().trim();
					_data['alasanRetur']=$('#TxtKeterangan').val().trim();
					
					JSON.post(_data,'${ctx }/json/returpenjualan-add',10000,returpenjualanAdd,null,null);
        		
				};
              
              
				function getPageRtr(obj){
					var _data={};
					_data['page'] = "page"+obj.value;
					JSON.post(_data,'${ctx }/json/getPageReturPenjualan',10000,returpenjualanSent,null,null);
					/* setPagging("page"+obj.value,"",""); */
				}
				var returpenjualanSent = function(data) {
					document.getElementById("tbdRetur").innerHTML = "";
      				if(data.page){
      					document.getElementById("totPageRtr").innerHTML = data.page;
      					_customPage = "Rtr";
      					 setPagging("1",data.page,"1");
      				}
					var _items = data.items;
					$('#tbdRetur').empty();
					for ( var i = 0; i < _items.length; i++) {
						var _item = _items[i];
		    			var _id = "'"+_item.id+"'";
		    			var _stockId = "'"+_item.stockId+"'";
		    			var _orderId = "'"+_item.orderId+"'";
		    			var _penjualanId = "'"+_item.penjualanId+"'";
		    			var _qtyRetur ="'"+_item.totQtyReturCtn+"'";
		    			var _nilaiRetur = convertToMoney(_item.nilaiNettoRetur);
		    			$('#tbdRetur').append(
		    						$('<tr><\/tr>')
        							.append($('<td><\/td>').html($.datepicker.formatDate('d MM yy',new Date(_item.dateRetur))))
        							.append($('<td><\/td>').html(_item.orderNumb))
        							.append($('<td><\/td>').html(_item.productCode))
        							.append($('<td><\/td>').html(_item.productName))
        							.append($('<td><\/td>').html(_item.customerName))
        							//.append($('<td><\/td>').html(_item.customerAddr))
        							//.append($('<td><\/td>').html(_item.customerCity))
        							.append($('<td><\/td>').html(_item.totQtyReturCtn))
        							.append($('<td><\/td>').html(_nilaiRetur))
        							.append($('<td><\/td>').html(_item.penerimaRetur))
        							.append($('<td><\/td>').html(_item.alasanRetur))
        							.append($('<td><\/td>').html(_item.salesmanCode))
        							.append($('<td><button type="button" onclick="returpenjualanRemoveCall('+_id+','+_stockId+','+_penjualanId+','+_orderId+','+_qtyRetur+');" class="btn btn-danger btn-xs"><i class="fa fa-trash-o "><\/i><\/button><\/td>'))
        							
        				);
        			}
        		};
          		
				var returpenjualanAdd = function(data){
					
					if(data.code == 0){
						alert(data.message);
						$('#myModalAddRetur').modal('hide');
						returpenjualanCall();
						
						}
						if (data.code != 0) {
							
							alert(data.message);
						}
						else {
							
						}
						};
				
						
				var returpenjualanDelete = function(data){
					if(data.code == 0){
						alert(data.message);
						returpenjualanCall();
						}
						else {
						}
						};
						
				var returpenjualanCall = function() {
		          	var _data={};
		          	_data['queryDataRetur']=$('#inpQueryRetur').val().trim();
					_data['byValueRetur']=$('#selByValueRetur').val().trim();
					JSON.post(_data,'${ctx }/json/returpenjualan',10000,returpenjualanSent,null,null);
					var _pageTot = returpenjualanSent.page;
						};	
						
				returpenjualanCall (); 
		</script>
		
            <!-- Tabel Order Penjualan -->
			<script type="text/javascript">
			function autoInputOrder(a,b,c,d,e,f,g,h,i,j,k){
				document.getElementById("TxtIdOrder").value = a;
				document.getElementById("TxtNoOrder").value = b;
				document.getElementById("TxtNoRetur").value = b;
				document.getElementById("TxtNamaCustomer").value = c;
				document.getElementById("TxtAlamat").value = d;
				document.getElementById("TxtKota").value = e;
				document.getElementById("TxtKodeBarang").value = f;
				document.getElementById("TxtNamaBarang").value = g;
				document.getElementById("TxtTotQtyJualCtn").value = h;
				document.getElementById("TxtHargaJualCtn").value = i;
				document.getElementById("TxtIdStock").value = j;
				document.getElementById("TxtNilaiReturBruto").value = k;
				
				$('#myModalOrder').modal('hide');
				
			}
			
			/* Start Paging Barang */
			function getPageOrder(obj){
			var _data={};
			_data['page'] = "page"+obj.value;
			JSON.post(_data,'${ctx }/json/getPageDetPenjualan',10000,orderSent,null,null);
			/* setPagging("page"+obj.value,"",""); */
			}
		
			/* End Paging Barang */
		
			/* Start Call Data Barang */
			var orderSent = function(data) {
				var _items = data.items;
      			if(_items.length == 0){
      				var _data={};
    				_data['page'] = "page1";
    				JSON.post(_data,'${ctx }/json/getPageDetPenjualan',10000,orderSent,null,null);
      				return;
      			}
			document.getElementById("tbdOrder").innerHTML = "";
				if(data.page){
					document.getElementById("totPageOrder").innerHTML = data.page;
					_customPage = "Order";
					 setPagging("1",data.page,"1");
				}
			$('#tbdOrder').empty();
			for ( var i = 0; i < _items.length; i++) {
				var _item = _items[i];
				//var _tot=_item.hpp*_item.kurs;
				//.html(_tot);
				var _qtyJualCtn = _item.totQtyJualCtn;
				var _hargaJualNettoCtn = _item.hargaJualNettoCtn;
				var _hargaJualBrutoCtn = ""+convertToMoney(_item.hargaJualBrutoCtn)+"";
				var _totJualNettoIdr = convertToMoney(_item.totJualNettoIdr);
				
				var _tr = document.createElement("tr");
    				_tr.setAttribute("onclick", "autoInputOrder('"+_item.id+"','"+_item.orderNumb+"','"+_item.customerName+"','"+_item.customerAddr+"','"+_item.customerCity+"','"+_item.productCode+"','"+_item.productName+"','"+_qtyJualCtn+"','"+_hargaJualNettoCtn+"','"+_item.stockId+"','"+_item.hargaJualBrutoCtn+"')");
    				$('#tbdOrder').append(
    				$(_tr)
        							.append($('<td><\/td>').html(_item.orderNumb))
        							.append($('<td><\/td>').html(_item.fakturNumb))
        							.append($('<td><\/td>').html(_item.customerCode))
        							.append($('<td><\/td>').html(_item.customerName))
        							.append($('<td><\/td>').html(_item.salesmanCode))
        							//.append($('<td><\/td>').html(_item.salesmanName))
        							.append($('<td><\/td>').html(_item.productCode))
        							.append($('<td><\/td>').html(_item.productName))
        							.append($('<td><\/td>').html(_qtyJualCtn+" Ctn"))
        							.append($('<td><\/td>').html("Rp "+_hargaJualNettoCtn))
        							.append($('<td><\/td>').html("Rp "+_totJualNettoIdr))
        							
        							
        				);
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
		
				
				
              	
              	<script type="text/javascript">
	         	 /*Date Picker*/
	          	$(function(){
	          	$('#DateRetur').datepicker().datepicker("setDate", new Date());
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
             
           <script type="text/javascript">
			
				$('#TxtTotQtyCtnRetur,#TxtHargaJualCtn').on('input',function() 
				{
		    		var TxtTotQtyCtnRetur = parseInt($('#TxtTotQtyCtnRetur').val());
		   			var TxtHargaJualCtn = parseFloat($('#TxtHargaJualCtn').val());
		   			$('#TxtNilaiRetur').val((TxtTotQtyCtnRetur * TxtHargaJualCtn  ? TxtTotQtyCtnRetur * TxtHargaJualCtn : 0).toFixed(2));
				});
		 </script>
            
  </body>
</html>
