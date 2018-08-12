<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="d" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
  <body>
          	<h3><i class="fa fa-angle-right"></i> Print Penjualan</h3>
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
			<table class="table1 table-bordered table-striped table-condensed table-hover">
                   <thead>
                   <tr>
                   <th> Nomor Order</th>
                   <th> Nomor Faktur</th>
                   </tr>
                   </thead>
				   <tbody id="tbdOrder">
                   </tbody>
                   </table>
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
            <section id="unseen">
            <!--Table Order Penjualan-->
                
                   
                <table class="table1 table-bordered table-striped table-condensed table-hover">
                   <thead>
                   <tr>
                    <th> Nomor Carton </th>
                    <th> Banyak nya </th>
                   <th> Ctn </th>
                   <th> Nama barang </th> <!--TxtNamaBarang -->
                   <th> Harga Satuan</th> <!--TxtQtyOrder -->
                   <th> Disc %</th>
                   <th> Harga Netto</th>	
                   <th> Jumlah Netto(Rp)</th>
                   </tr>
                   </thead>
				   <tbody id="tbdOrder1">
                   </tbody>
                   </table>
                 </section>
               </div><!--content-panel-->
             </div><!--col-lg-4 -->			
		  </div><!--row -->
      	
           
            <script type="text/javascript">
            
					
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
        							//.append($('<td><\/td>').html(_item.productCode))
        							//.append($('<td><\/td>').html(_item.productName))
        							//.append($('<td><\/td>').html(_item.totQtyJualPcs))
        							//.append($('<td><\/td>').html(_item.hargaJualPcs))
        							//.append($('<td><\/td>').html(_item.totJual))
        							//.append($('<td><button type="button" onclick="orderRemoveCall('+_id+','+_idStock+','+_idPenjualan+','+_totQtyJualIsi2Ctn+','+_totStokPcs+');" class="btn btn-danger btn-xs"><i class="fa fa-trash-o "><\/i><\/button><\/td>'))
        								
        				);
        				
        				$('#tbdOrder1').append(
        						$('<tr><\/tr>')
        							//.append($('<td><\/td>').html(_item.orderNumb))
        							//.append($('<td><\/td>').html(_item.fakturNumb))
        							.append($('<td><\/td>').html(_item.productCode))
        							.append($('<td><\/td>').html(_item.productName))
        							.append($('<td><\/td>').html(_item.totQtyJualPcs))
        							.append($('<td><\/td>').html(_item.hargaJualPcs))
        							.append($('<td><\/td>').html(_item.totJual))
        							.append($('<td><button type="button" onclick="orderRemoveCall('+_id+','+_idStock+','+_idPenjualan+','+_totQtyJualIsi2Ctn+','+_totStokPcs+');" class="btn btn-danger btn-xs"><i class="fa fa-trash-o "><\/i><\/button><\/td>'))
        								
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
        		}
        		
			</script>
		
			
</body>
</html>
