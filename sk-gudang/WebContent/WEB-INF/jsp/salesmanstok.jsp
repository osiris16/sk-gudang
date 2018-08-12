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
          	<h3><i class="fa fa-angle-right"></i> Product</h3>
             
           
			
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
				<option value = "tripNum">Nomor Trip</option>
				<option value = "tripNumSeq">Nomor Trip Urutan</option>
			</select>
			</div>
			<label class="col-sm-2 col-sm-2 control-label"></label>
                         
			<div class="col-sm-10">
			<input type="text" id="inpQuery" class="form-control2" style="width:200px;" >
			</div>
			
			</div>
			
			<div class="form-group">
			<label class="col-sm-2 col-sm-2 control-label"></label>
                         
			<button onclick="productCall();" type="button" style="width:220px;" class="btn btn-outline btn-primary ">Search</button>
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
            <!-- <button onclick = "lastPage(this)" type="button" >Last</button> -->
			
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
                            <th> Kode Barang</th>
                            <th> Nama Barang </th>
                            <th> Merek / Label </th>
                            <th> Jenis Barang</th>
                            <th> Made in</th>
                       		<th> Isi per CTN</th>
                       		<th> Sat. Isi</th>
                       		<th> Isi PCS</th>
                       		<th> Stok(PCS)</th>
                       		<th> Stok CTN </th>
                       		<th> Harga Jual/CTN</th>
                       		<th> Harga Jual/Pcs</th>
                          </tr>
                       </thead>
                       <tbody id="tbdProduct">
                       </tbody>
                       </table>
                 </section>
                 </div><!--content-panel-->
                 </div><!--col-lg-4 -->
                 </div><!--row -->
                 
		  	
	
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
				JSON.post(_data,'${ctx }/json/getPageStock',10000,productSent,null,null);
				/* setPagging("page"+obj.value,"",""); */
			}
			
			/* End Paging Barang */
			
			function _setPop(obj){
   				 $('#myModalHistory').modal('show');
   				 document.getElementById("buttonTripHistory").value = obj;
   				 document.getElementById("buttonOrderHistory").value = obj;
   				 $('#tbdHistory').empty();
   				 $('#tbdHistoryPenjualan').empty();
   				 
   			}
			/* Start Call Data Barang */
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
	        			var _merkBarang = "'"+_item.productMerk+"'";
	        			var _noSni = "'"+_item.productStandartNo+"'";
	        			var _barcode = "'"+_item.productBarcode+"'";
	        			var _madeIn = "'"+_item.productMadeIn+"'";
	        			var _noPabrik = "'"+_item.productPartNumb+"'";
	        			var _isiCtn = "'"+_item.isiCtn+"'";
	        			var _satIsiCtn = "'"+_item.satIsiCtn+"'";
	        			var _isiPcs = "'"+_item.isiPcs+"'";
	        			var _totStokPcs = "'"+_item.totStokPcs+"'";
	        			var _hargaJualIsiCtn = '';
	        			if(_item.hargaJualIsiCtn){
	        				_hargaJualIsiCtn = convertToMoney(_item.hargaJualIsiCtn);
	        			}else{
	        				_hargaJualIsiCtn = 0;
	        			}
	        			var _hJualCtnConv = convertToMoney(_item.hargaJualCtn);
	        			var _hJualPcsConv = convertToMoney(_item.hargaJualPcs);
	        			
            			$('#tbdProduct').append(
            					$('<tr data-toggle="modal"  ondblclick = "_setPop('+_stockId+')" ><\/tr>')
	        						.append($('<td><button onclick ="imgShow('+_img+')" data-toggle="modal" data-target="#myModalImgShow"><i class="fa fa-instagram"><\/i></button><\/td>'))
	        						
	        						//.append($('<td><\/td>').html(_item.productBarcode))
	        						.append($('<td><\/td>').html(_item.tripNumbStok))
        							.append($('<td><\/td>').html(_item.productCode))
        							.append($('<td><\/td>').html(_item.productName))
        							.append($('<td><\/td>').html(_item.productMerk))
        							.append($('<td><\/td>').html(_item.productGroup))
        							.append($('<td><\/td>').html(_item.productMadeIn))
        							//.append($('<td><\/td>').html(_item.productPartNumb))
        							//.append($('<td><\/td>').html(_item.productStandartNo))
        							.append($('<td><\/td>').html(_item.isiCtn))
        							.append($('<td><\/td>').html(_item.satIsiCtn))
        							.append($('<td><\/td>').html(_item.isiPcs)) 
        							.append($('<td><\/td>').html(_item.totStokPcs))
        							.append($('<td><\/td>').html(_item.stokCtn+" CTN"))
        							.append($('<td><\/td>').html("Rp "+_hJualCtnConv))
        							.append($('<td><\/td>').html("Rp "+_hJualPcsConv))
        							
        							//.append($('<td><button id="pushModalButton" type="button" onclick="pushModal('+_id+','+_idJenisBarang+','+_jenisBarang+','+_kodeBarang+','+_namaBarang+','+_merkBarang+','+_noSni+','+_barcode+','+_noPabrik+','+_madeIn+','+_isiCtn+','+_satIsiCtn+','+_isiPcs+','+_totStokPcs+','+_img+')" data-toggle="modal" data-target="#myModalUpdateProduct" class="btn btn-primary btn-xs"><i class="fa fa-pencil "><\/i><\/button> - <button type="button" onclick="productRemoveCall('+_item.stockId+');" class="btn btn-danger btn-xs"><i class="fa fa-trash-o "><\/i><\/button><\/td>'))
        							
        							);
            			
        			}
        		};
        		
        		
          		var productCall = function() {
					var _data={};
					//_data['dateFrom'] = $('#dateFrom').val().trim();
					//_data['dateTo'] = $('#dateTo').val().trim();
					_data['queryData']=$('#inpQuery').val().trim();
					_data['byValue']=$('#selByValue').val().trim();
					JSON.post(_data,'${ctx }/json/stock',10000,productSent,null,null);
					 var _pageTot = productSent.page; 
				};
				
				
						
				productCall();
				var fowardPrint = function(data){
					alert(data.message);
					window.location.replace('../img-sk/Stock_0.pdf');
				};
				function printCall(){
					var _data={};
					_data['queryData']="0";
					_data['byValue']="PRINTSTOCK";
					JSON.post(_data,'${ctx }/json/stock',1000000,fowardPrint,null,null);
					 
				}
				/* End Call Data Barang */
				
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
			</script>
			
			<!-- End Tabel Barang -->
			
				
			
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
		/* function convertToRupiah(angka){
				if(angka == ""){
					angka = 0;
				}
			    var rupiah = '';
			    var angkarev = angka.toString().split('').reverse().join('');
			    for(var i = 0; i < angkarev.length; i++) if(i%3 == 0) rupiah += angkarev.substr(i,3)+',';
			    var a = rupiah.split('',rupiah.length-1).reverse().join('');
			    
			    return a.replace(",.","."); */
			}
			</script>
  </body>
</html>
