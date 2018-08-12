<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="d" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
 
  <body>
          	<h3><i class="fa fa-angle-right"></i> List Salesman</h3>
            <!--Search Table Barang-->
			<div class="panel panel-default">
			<div class="panel-heading" >
			<i class="glyphicon glyphicon-search" title="cari"></i> Search</i>
			</div>
			<div class="panel-body" id="panelBody">
			<div class="col-lg-6">
			<div class="form-group">
			<label class="col-sm-2 col-sm-2 control-label">Kategori</label>
			<div class="col-sm-10">
			<select id = "selByValue" class="form-control2" style="width:200px;">
				<option value = "code">Kode</option>
				<option value = "name">Nama</option>
			</select>
			</div>
			<label class="col-sm-2 col-sm-2 control-label"></label>        
			<div class="col-sm-10">
			<input type="text" id="inpQuery" class="form-control2" style="width:200px;" >
			</div>
			</div>
			<label class="col-sm-2 col-sm-2 control-label"></label>
			<div class="col-sm-10">
			<button onclick="salesmanCall();" type="button" style="width:150px"; class="btn btn-outline btn-primary btn-lg btn-block"><i class="glyphicon glyphicon-search" title="cari"></i></button>
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
            <div style="border:1px solid white;height:350px;width:100%;overflow-x:scroll;overflow-y:scroll;">
		  		
            <div class="panel-body">
            <div class="table-responsive">
            <!--<div class="content-panel">-->
                      
                          <section id="unseen">
                          
                          
            <!--Table Salesman-->
                            
                            <table class="table2 table-bordered table-striped table-condensed table-hover">
                              <thead>
                              <tr>
                                  <th> Kode Salesman</th>
                                  <th> Nama Salesman</th>
                                  <th> ACTION</th>
                              </tr>
                              </thead>
								</div>
                              <tbody id="tbdSalesman">
                              </tbody>
                          </table>
                      </section>
                      
                  </div><!--content-panel-->
               </div><!--col-lg-4 -->			
		  	</div><!--row -->
      				
      		
                <div class="row mt">
          		<div class="col-lg-12">
                  <div class="form-panel">
                  	  <h4 class="mb"><i class="fa fa-angle-right"></i> Tambah Salesman</h4>
                  	  
                      
                      <form class="form-horizontal style-form" method="get">
                          <div class="form-group">
                          
                          <div class="col-lg-10">
                         
                              <label class="col-sm-2 col-sm-2 control-label">Kode Salesman</label>
                              
                                  <input id="txtSalesmanCode" name="txtSalesmanCode" type="text"  class="form-control2"  style="width:200px;" required/>
                                  
                          </div>
                          <div class="col-sm-10">
                          
                          	
                          		<label class="col-sm-2 col-sm-2 control-label">Nama Salesman</label>
                         		 
                          		<input id="txtSalesmanName" name="txtSalesmanName" type="text" class="form-control"  style="width:200px;" required/>   
                          
                          </div>
                          </div>
 						<div class="form-group" >
 						<div class="col-lg-10">
            			<button type="button" class="btn btn-theme" onclick="salesmanAddCall();">SIMPAN</button>    
        				</div>
                         </div>
                      </form>
                  </div>
                  </div>
          		</div><!-- col-lg-12-->      	
          	</div><!-- /row -->
          	
          	<!-- Modal Edit Salesman -->
<div class="modal fade" id="myEditSalesman" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>

                </button>
                <h4 class="modal-title" id="myModalLabel">Edit Salesman</h4>
                </div>
      				
                    <div class="row mt">
          		<div class="col-lg-12">
                  <div class="form-panel">
                  	  <h4 class="mb"><i class="fa fa-angle-right"></i> Edit Salesman</h4>
                      
                      <form class="form-horizontal style-form" method="get">
                          <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">Kode Salesman</label>
                              <div class="col-sm-10">
                                  <input id="txtEditSalesmanCode" name="txtEditSalesmanCode" type="text"  class="form-control2"  style="width:200px;"  required/>
                          		 
                          </div>
                          
                          <label class="col-sm-2 col-sm-2 control-label">Nama Salesman</label>
                           <div class="col-sm-10">
                                  <input id="txtEditSalesmanName" name="txtEditSalesmanName" type="text" class="form-control2"  style="width:200px;" required/>   
                          </div>
                          </div>
                         
                          <button type="button" class="btn btn-theme" onclick="salesmanEditCall();">Update</button>
                      </form>
                  </div>
          		</div><!-- col-lg-12-->      	
          	</div><!-- /row -->
          	</div>
          	</div>
          	</div>
          	<div class="modal fade" id="myViewSale" tabindex="-1" role="dialog" aria-labelledby="myModalViewSaleLabel" aria-hidden="true">
		    <div class="modal-dialog modal-lg">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
		
		                </button>
		                <h4 class="modal-title" id="myModalViewSaleLabel">Periode Penjualan</h4>
		                </div>
		      				
		                    <div class="row mt">
		          		<div class="col-lg-12">
		                  <div class="form-panel">
		                  	  <h4 class="mb"><i class="fa fa-angle-right"></i> Periode Penjualan Sales <i id="iName"></i></h4>
		                      
		                      <form class="form-horizontal style-form" method="get">
		                          <div class="form-group">
		                              <label class="col-sm-2 col-sm-2 control-label">Dari Tanggal</label>
		                              <div class="col-sm-10">
		                                  <input id="dateFrom" value="01/01/2009" class="form-control2" style="width:200px;" type="text">
		                          		 
		                          </div>
		                          
		                          <label class="col-sm-2 col-sm-2 control-label">Sampai Tanggal</label>
		                           <div class="col-sm-10">
		                                  <input id="dateTo" value="01/01/2009" class="form-control2" style="width:200px;" type="text">   
		                          </div>
		                          </div>
		                         
		                          
		                          
		                         
		                          <a type="button" id="btnViewSale" class="btn btn-theme" onclick="openPageSale(this)">Lihat</a>
		                      </form>
		                  </div>
		          		</div><!-- col-lg-12-->      	
		          	</div><!-- /row -->
		          	</div>
		          	</div>
          	</div>
          	
          	 <script type="text/javascript">
					/* UPDATE DATA Customer */
					
					function pushEdit(_id,_code,_name){
						_itemId = _id
    					var _data={};
    			
    					var d = document.getElementById("myEditSalesman");  //   Javascript
    					d.setAttribute('data-id' , _itemId); //
    					document.getElementById("txtEditSalesmanCode").value = _code;
						document.getElementById("txtEditSalesmanName").value = _name;
						
						}
	        		
	        		var salesmanEditSent = function(data) {
	        			var recid = document.getElementById("myEditSalesman");
						var _dataId= recid.getAttribute("data-id");
	        			var _items = data.items;
	        			
	        			for (var i = 0; i < _items.length; i++){
	        				var _item = _items[i];
	        				if (_item.id == _dataId) {
								document.getElementById("txtEditSalesmanCode").value = _item.code;
								document.getElementById("txtEditSalesmanName").value = _item.name;
								
								
	        				}
	        			}
	        			
	        		};
					
					var salesmanEditCall = function() {
						
						var _recid = document.getElementById("myEditSalesman");
						var _dataId= _recid.getAttribute("data-id");
						var _data={};
						_data['id'] = _dataId;
						_data['code']=$('#txtEditSalesmanCode').val().trim();
						_data['name']=$('#txtEditSalesmanName').val().trim();
						
						
						JSON.post(_data,'${ctx }/json/salesman-mod',10000,salesmanEdit,null,null);
						
					};
					
					/* END UPDATE DATA Edit */
					
					/* DELETE DATA */
		       		var salesmanRemoveCall = function(_id) {
		       			if(confirm("Do you want to delete ?")) {
		       			    this.click;
						var _data={};
						_data['id'] = _id;
						JSON.post(_data,'${ctx }/json/salesman-del',10000,salesmanCall,null,null);
						alert ("Data berhasil dihapus");
					
		       	 		}
					else {
						
					return;
					}
		       		};
		       		/*End delete*/
		       		</script>
          	 <script type="text/javascript">
          		
       		var salesmanAddSent = function(data) {
       			
    			var _items = data.items;
    			
    			for ( var i = 0; i < _items.length; i++) {
    				var _item = _items[i];
    				
    				//var _tot=_item.hpp*_item.kurs;
    				//.html(_tot);
    				$('#tbdSalesman').append(
    						$('<tr><\/tr>')
    							.append($('<td><\/td>').html(_item.code))
    							.append($('<td><\/td>').html(_item.name))
    							
    				);
    			}
    			
    		};
    		
			var salesmanAddCall = function() {
				var _data={};
				_data['code']=$('#txtSalesmanCode').val().trim();
				_data['name']=$('#txtSalesmanName').val().trim();
				
				JSON.post(_data,'${ctx }/json/salesman-add',10000,salesmanAdd,null,null);
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
				JSON.post(_data,'${ctx }/json/getPageSalesman',10000,salesmanSent,null,null);
				/* setPagging("page"+obj.value,"",""); */
			}
			
          		var salesmanSent = function(data) {
          			document.getElementById("tbdSalesman").innerHTML = "";
          			
          			if(data.page){
      					document.getElementById("totPage").innerHTML = data.page;
      					 setPagging("1",data.page,"1"); 
      					
      				}
      				
        			var _items = data.items;
        			$('#tbdSalesman').empty();
        			for ( var i = 0; i < _items.length; i++) {
        				var _item = _items[i];
        				var _id = "'"+_item.id+"'";
        				var _code = "'"+_item.code+"'";
        				var _name = "'"+_item.name+"'";
        				
        				$('#tbdSalesman').append(
        						$('<tr><\/tr>')
        							.append($('<td class="hidden";><\/td>').html(_item.id))
        							.append($('<td><\/td>').html(_item.code))
        							.append($('<td><\/td>').html(_item.name))
        							.append($('<td><button id="pushEditButton" type="button" onclick="pushEdit('+_id+','+_code+','+_name+')" data-toggle="modal" data-target="#myEditSalesman" class="btn btn-primary btn-xs"><i class="fa fa-pencil "><\/i><\/button> - <button type="button" onclick="salesmanRemoveCall('+_id+');" class="btn btn-danger btn-xs"><i class="fa fa-trash-o "><\/i><\/button><\/td>'))
									//<button id="" type="button" onclick="showSale('+_id+','+_name+')" data-toggle="modal" data-target="#myViewSale" class="btn btn-primary btn-xs"><i class="glyphicon glyphicon-search"><\/i><\/button> - 
        				);
        			}
        		};
        		
        		var salesmanCall = function() {
					var _data={};
					_data['queryData']=$('#inpQuery').val().trim();
					_data['byValue']=$('#selByValue').val().trim();
					JSON.post(_data,'${ctx }/json/salesman',10000,salesmanSent,null,null);
					var _pageTot = salesmanSent.page;
				};
				
				var salesmanAdd = function(data){
					
					if(data.code == 0){
					alert(data.message);
					
					salesmanCall();
					
					}
					if (data.code != 0) {
						
						alert(data.message);
					}
					else {
						
					}
					};
				var salesmanEdit = function(data){
					
					if(data.code == 0){
					alert(data.message);
					$('#myEditSalesman').modal('hide');
					salesmanCall();
					
					}
					if (data.code != 0) {
						
						alert(data.message);
					}
					else {
						
					}
					};
				salesmanCall();
				function showSale(id,name){
					document.getElementById("iName").innerHTML = name;
					document.getElementById("btnViewSale").value = id;
					
				}


				var _printSales = function(data){
					var _mes = data.message;
					window.location.replace('../img-sk/'+_mes+'.pdf');
				};
				function openPageSale(obj){
					var _dateTo = document.getElementById("dateTo").value;
					var _dateFrom = document.getElementById("dateFrom").value;
					var _toArr = _dateTo.split("/");
					var _to = _toArr[2]+"/"+_toArr[0]+"/"+_toArr[1];
					var _fromArr = _dateFrom.split("/");
					var _from = _fromArr[2]+"/"+_fromArr[0]+"/"+_fromArr[1];
					var _data={};
					_data['queryData']=obj.value;
					_data['dateTo'] = _to;
					_data['dateFrom'] = _from;
					_data['byValue']="PRINTSALESPENJ";
					JSON.post(_data,'${ctx }/json/salesman',10000,_printSales,null,null);
					//location.href = 'penjualan?id='+obj.value+'&to='+_dateTo+'&from='+_dateFrom+'&by=sales';
				}
		</script>
		
		<script type="text/javascript">
          /*Date Picker*/
          $(function()
        		  {
          	$('#dateTo').datepicker().datepicker("setDate", new Date());
          	
          	
          	$('#dateFrom').datepicker();
    		});
          </script>
      	 
  </body>
</html>
