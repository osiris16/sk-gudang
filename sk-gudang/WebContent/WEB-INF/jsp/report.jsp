<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="d" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
  <body>
      
          	
			<br/>
			<br/>
			</div>
          	<br/>
          	<br/>
          	<br/>
          	<button id="showSearch1" type="button" class="btn btn-group btn-info"><i class="glyphicon glyphicon-search" title="cari"></i> Report Customer</i></button>
           <div id="searchCustomer" class="panel panel-default hidden">
			
			<div class="panel-heading" >
			<label >Report Customer</label>
			</div>
			
			<div class="panel-body" id="panelBody">
			<div class="col-lg-6">
			<div class="form-group">
			<label class="col-sm-2 col-sm-2 control-label">Dari</label>
			<div class="col-sm-10">
			<input type="text" id="dateFromCustomer" value = "01/01/2009" class="form-control2" style="width:200px;" >
			</div>
			
			<label class="col-sm-2 col-sm-2 control-label">Sampai</label>
			<div class="col-sm-10">
			<input type="text" id="dateToCustomer" value = "01/01/2100"  class="form-control2" style="width:200px;" >
			</div>
			<label class="col-sm-2 col-sm-2 control-label">Disc</label>
			<div class="col-sm-10">
				 <div data-role="rangeslider">
			        <label for="cust-disc-min">Min:<i id="cust-disMin">0</i></label>
			        <input style="width: 50%" onchange="document.getElementById('cust-disMin').innerHTML = this.value"  type="range" name="cust-disc-min" id="cust-disc-min" value="0" min="0" max="100">
			        <label for="cust-disc-max">Max:<i id="cust-disMax">100</i></label>
			        <input style="width: 50%" onchange="document.getElementById('cust-disMax').innerHTML = this.value" type="range" name="cust-disc-max" id="cust-disc-max" value="100" min="0" max="100">
			      </div>
			</div>
			<div class="form-group">
			
			<label class="col-sm-2 col-sm-2 control-label">Kode Customer</label>
                         
			
			<label class="col-sm-2 col-sm-2 control-label"></label>
                         
			<div class="col-sm-10">
			<input type="text" id="inpQueryCustomer" class="form-control2" style="width:200px;" >
			<button type="button" class="btn btn-group btn-info" data-toggle="modal" data-target="#myModalCustomer" onclick ="customerCall();"><i class="fa fa-search "></i></button>
                             
			</div>
			</div>
			
			
			<div class="form-group">
			
			<div class="col-sm-6">
			<br/>
            <button onclick="openPageCust();" type="button" class="btn btn-primary"><i class="fa fa-print"></i> Detail Transaksi(PDF)</button>
			<button onclick="openPageCustFaktur();" type="button" class="btn btn-primary"><i class="fa fa-print"></i> Faktur Penjualan(PDF)</button>
			<button onclick="openPageCustFakturExcel();" type="button" class="btn btn-success"><i class="glyphicon glyphicon-download-alt"></i> Faktur Penjualan (CSV)</button>
			</div>
			</div>
			
			</div>
			</div>
			</div>
			<br/>
			<br/>
			</div>
            <br/>
            <br/>
            <button id="showSearch" type="button" class="btn btn-group btn-info"><i class="glyphicon glyphicon-search" title="cari"></i> Report Salesman</i></button>
            
			<!--Search Table Barang-->
			<div id="searchProd" class="panel panel-default hidden">
			
			<div class="panel-heading" >
			<label >Report Salesman</label>
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
			<div class="form-group">
			
			<label class="col-sm-2 col-sm-2 control-label">Kode Sales</label>
            <label class="col-sm-2 col-sm-2 control-label"></label>
            <div class="col-sm-10">
			<input type="text" id="inpQuery" class="form-control2" style="width:200px;" >
			</div>
			<label class="col-sm-2 col-sm-2 control-label">Disc</label>
			<div class="col-sm-10">
				 <div data-role="rangeslider">
			        <label for="disc-min">Min:<i id="disMin">0</i></label>
			        <input style="width: 50%" onchange="document.getElementById('disMin').innerHTML = this.value"  type="range" name="disc-min" id="disc-min" value="0" min="0" max="100">
			        <label for="disc-max">Max:<i id="disMax">100</i></label>
			        <input style="width: 50%" onchange="document.getElementById('disMax').innerHTML = this.value" type="range" name="disc-max" id="disc-max" value="100" min="0" max="100">
			      <br/>
			      </div>
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-sm-2 col-sm-2 control-label"></label>
			<div class="col-sm-6">
            <button onclick="openPageSale();" type="button" class="btn btn-primary"><i class="fa fa-print"></i>Detail Transaksi(PDF)</button>
			<button onclick="openPageSaleFaktur();" type="button" class="btn btn-primary"><i class="fa fa-print"></i>Faktur Penjualan(PDF)</button>
			<button onclick="openPageSaleFakturExcel();" type="button" class="btn btn-success" bgcolor="white;"><i class="glyphicon glyphicon-download-alt"></i>Faktur Penjualan(CSV)</button>
			</div>
			
			</div>
			</div>
			</div>
			</div>
			</div>
			<br/>
			<br/>
			
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
            <label> Page </label>
            <select onchange="getPageCust(this)"; id="selectPageCust">
            
            </select>
            <label> of </label>
            <label id = "totPageCust"></label>
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
	

                  
<!------------------------ SCRIPT ------------------------------------------ -->
					
					<script type="text/javascript">
					
					var _printSales = function(data){
						var _mes = data.message;
						window.open('../img-sk/'+_mes+'.pdf','popUpWindow','height=600,width=1000,left=10,top=10,,scrollbars=yes,menubar=no');
					};
					function openPageSale(obj){
						var _dateTo = document.getElementById("dateTo").value;
						var _dateFrom = document.getElementById("dateFrom").value;
						var _toArr = _dateTo.split("/");
						var _to = _toArr[2]+"/"+_toArr[0]+"/"+_toArr[1];
						var _fromArr = _dateFrom.split("/");
						var _from = _fromArr[2]+"/"+_fromArr[0]+"/"+_fromArr[1];
						var _discMin = document.getElementById("disc-min").value;
						var _discMax = document.getElementById("disc-max").value;
						var _data={};
						_data['discMin'] = _discMin;
						_data['discMax'] = _discMax;
						_data['queryData']=document.getElementById("inpQuery").value;
						_data['dateTo'] = _to;
						_data['dateFrom'] = _from;
						
						_data['byValue']="PRINTSALESPENJ";
						JSON.post(_data,'${ctx }/json/salesman',10000,_printSales,null,null);
						//location.href = 'penjualan?id='+obj.value+'&to='+_dateTo+'&from='+_dateFrom+'&by=sales';
					}
					var _printSalesFaktur = function(data){
						var _mes = data.message;
						window.open('../img-sk/'+_mes+'.pdf','popUpWindow','height=400,width=1000,left=10,top=10,,scrollbars=yes,menubar=no');
						//window.location.replace('../img-sk/'+_mes+'.pdf');
					};
					function openPageSaleFaktur(obj){
						var _dateTo = document.getElementById("dateTo").value;
						var _dateFrom = document.getElementById("dateFrom").value;
						var _toArr = _dateTo.split("/");
						var _to = _toArr[2]+"/"+_toArr[0]+"/"+_toArr[1];
						var _fromArr = _dateFrom.split("/");
						var _from = _fromArr[2]+"/"+_fromArr[0]+"/"+_fromArr[1];
						var _discMin = document.getElementById("disc-min").value;
						var _discMax = document.getElementById("disc-max").value;
						var _data={};
						_data['discMin'] = _discMin;
						_data['discMax'] = _discMax;
						_data['queryData']=document.getElementById("inpQuery").value;
						_data['dateTo'] = _to;
						_data['dateFrom'] = _from;
						_data['byValue']="PRINTSALESPENJFAKTUR";
						JSON.post(_data,'${ctx }/json/salesman',10000,_printSalesFaktur,null,null);
						//location.href = 'penjualan?id='+obj.value+'&to='+_dateTo+'&from='+_dateFrom+'&by=sales';
					};
					var _printSalesFakturExcel = function(data){
						var _mes = data.message;
						if(_mes == "Kode Sales tidak ditemukan"){
							alert(_mes);
							return;
						}
						window.location.replace('../img-sk/'+_mes);
					};
					function openPageSaleFakturExcel(obj){
						var _dateTo = document.getElementById("dateTo").value;
						var _dateFrom = document.getElementById("dateFrom").value;
						var _toArr = _dateTo.split("/");
						var _to = _toArr[2]+"/"+_toArr[0]+"/"+_toArr[1];
						var _fromArr = _dateFrom.split("/");
						var _from = _fromArr[2]+"/"+_fromArr[0]+"/"+_fromArr[1];
						var _discMin = document.getElementById("disc-min").value;
						var _discMax = document.getElementById("disc-max").value;
						var _data={};
						_data['discMin'] = _discMin;
						_data['discMax'] = _discMax;
						_data['queryData']=document.getElementById("inpQuery").value;
						_data['dateTo'] = _to;
						_data['dateFrom'] = _from;
						_data['byValue']="PRINTSALESPENJFAKTUREXCEL";
						JSON.post(_data,'${ctx }/json/salesman',10000,_printSalesFakturExcel,null,null);
						//location.href = 'penjualan?id='+obj.value+'&to='+_dateTo+'&from='+_dateFrom+'&by=sales';
					};
					var _printCustomer = function(data){
						var _mes = data.message;
						window.open('../img-sk/'+_mes+'.pdf','popUpWindow','height=600,width=1000,left=10,top=10,,scrollbars=yes,menubar=no');
					};
					function openPageCust(obj){
						var _dateTo = document.getElementById("dateToCustomer").value;
						var _dateFrom = document.getElementById("dateFromCustomer").value;
						var _toArr = _dateTo.split("/");
						var _to = _toArr[2]+"/"+_toArr[0]+"/"+_toArr[1];
						var _fromArr = _dateFrom.split("/");
						var _from = _fromArr[2]+"/"+_fromArr[0]+"/"+_fromArr[1];
						var _discMin = document.getElementById("cust-disc-min").value;
						var _discMax = document.getElementById("cust-disc-max").value;
						var _data={};
						_data['queryData']=document.getElementById("inpQueryCustomer").value;
						_data['dateTo'] = _to;
						_data['dateFrom'] = _from;
						_data['discMin'] = _discMin;
						_data['discMax'] = _discMax;
						_data['byValue']="PRINTCUSTOMERPENJ";
						JSON.post(_data,'${ctx }/json/customer',10000,_printCustomer,null,null);
						//location.href = 'penjualan?id='+obj.value+'&to='+_dateTo+'&from='+_dateFrom+'&by=sales';
					}
					var _printCustFaktur = function(data){
						var _mes = data.message;
						//window.location.replace('../img-sk/'+_mes+'.pdf');
						window.open('../img-sk/'+_mes+'.pdf','popUpWindow','height=600,width=1000,left=10,top=10,,scrollbars=yes,menubar=no');
					};
					function openPageCustFaktur(obj){
						var _dateTo = document.getElementById("dateToCustomer").value;
						var _dateFrom = document.getElementById("dateFromCustomer").value;
						var _toArr = _dateTo.split("/");
						var _to = _toArr[2]+"/"+_toArr[0]+"/"+_toArr[1];
						var _fromArr = _dateFrom.split("/");
						var _from = _fromArr[2]+"/"+_fromArr[0]+"/"+_fromArr[1];
						var _discMin = document.getElementById("cust-disc-min").value;
						var _discMax = document.getElementById("cust-disc-max").value;
						var _data={};
						_data['discMin'] = _discMin;
						_data['discMax'] = _discMax;
						_data['queryData']=document.getElementById("inpQueryCustomer").value;
						_data['dateTo'] = _to;
						_data['dateFrom'] = _from;
						_data['byValue']="PRINTCUSTOMERPENJFAKTUR";
						JSON.post(_data,'${ctx }/json/customer',10000,_printCustFaktur,null,null);
						//location.href = 'penjualan?id='+obj.value+'&to='+_dateTo+'&from='+_dateFrom+'&by=sales';
					}
					var _printCustFakturExcel = function(data){
						var _mes = data.message;
						if(_mes == "Kode Customer tidak ditemukan"){
							alert(_mes);
							return;
						}
						
						window.location.replace('../img-sk/'+_mes);
					};
					function openPageCustFakturExcel(obj){
						var _dateTo = document.getElementById("dateToCustomer").value;
						var _dateFrom = document.getElementById("dateFromCustomer").value;
						var _toArr = _dateTo.split("/");
						var _to = _toArr[2]+"/"+_toArr[0]+"/"+_toArr[1];
						var _fromArr = _dateFrom.split("/");
						var _from = _fromArr[2]+"/"+_fromArr[0]+"/"+_fromArr[1];
						var _discMin = document.getElementById("cust-disc-min").value;
						var _discMax = document.getElementById("cust-disc-max").value;
						var _data={};
						_data['discMin'] = _discMin;
						_data['discMax'] = _discMax;
						_data['queryData']=document.getElementById("inpQueryCustomer").value;
						_data['dateTo'] = _to;
						_data['dateFrom'] = _from;
						_data['byValue']="PRINTCUSTOMERPENJFAKTUREXCEL";
						JSON.post(_data,'${ctx }/json/customer',10000,_printCustFakturExcel,null,null);
						//location.href = 'penjualan?id='+obj.value+'&to='+_dateTo+'&from='+_dateFrom+'&by=sales';
					}
					
		       		</script>
		       		
		       		
			
			<!-- Hide / Show Table -->
					<script type="text/javascript">
					

					$(document).ready(function(){
    					$("#hideSearch").click(function(){
       						$("#searchProd").addClass("hidden");
       					}); 
   						
    					$("#showSearch").click(function(){
    						var _searchProd = document.getElementById("searchProd").getAttribute("class");
    						if(_searchProd=="panel panel-default"){
    							$("#searchProd").addClass("hidden");
    						}else{
    							$("#searchProd").removeClass("hidden");
    						}
   						});
    					$("#showSearch1").click(function(){
    						var _searchProd = document.getElementById("searchCustomer").getAttribute("class");
    						if(_searchProd=="panel panel-default"){
    							$("#searchCustomer").addClass("hidden");
    						}else{
    							$("#searchCustomer").removeClass("hidden");
    						}
   						});
    					$("#showSearch2").click(function(){
    						var _searchProd = document.getElementById("searchPenjualan").getAttribute("class");
    						if(_searchProd=="panel panel-default"){
    							$("#searchPenjualan").addClass("hidden");
    						}else{
    							$("#searchPenjualan").removeClass("hidden");
    						}
   						});
   					});
					</script>
					<script type="text/javascript">
         	 		/*Date Picker*/
          			$(function(){
          			$('#dateFrom,#dateTo,#dateFromCustomer,#dateToCustomer,#dateFromPenjualan,#dateToPenjualan').datepicker();
          			});
       				</script>
       				
       				<script type="text/javascript">
		/*Customer*/
					function autoInputCust(a){
						document.getElementById("inpQueryCustomer").value = a;
						
						$('#myModalCustomer').modal('hide');
						
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
		        				_tr.setAttribute("onclick", "autoInputCust('"+_item.code+"')");
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

  </body>
</html>
