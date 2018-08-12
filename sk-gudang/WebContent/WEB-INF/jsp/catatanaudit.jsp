<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="d" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">


<body>
          	<h3><i class="fa fa-angle-right"></i> Catatan Audit</h3>
             <textarea id="testL" class="col-sm-2 col-sm-2 control-label">Action</textarea>
            <!--Search Table Penjualan-->
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
			<select id = "selType" class="form-control2" style="width:200px;">
				<option value = "">--</option>
				<option value = "salesman">Salesman</option>
				<option value = "vendor">Vendor</option>
				<option value = "customer">Customer</option>
				<option value = "stock">Product/Stock</option>
				<option value = "penjualan">Penjualan</option>
				<option value = "detPenjualan">Detail Penjualan</option>
				<option value = "retPenjualan">Retur Penjualan</option>
				<option value = "trip">Trip</option>
				<option value = "detPembelian">Detail Pembelian</option>
			</select>
			
			</div>
			<label id="" class="col-sm-2 col-sm-2 control-label">Action</label>
                         
			<div class="col-sm-10">
			<select id = "selAction" class="form-control2" style="width:200px;">
				<option value = "">--</option>
				<option value = "add">Tambah</option>
				<option value = "delete">Hapus</option>
				<option value = "edit">Ubah</option>
				
			</select>
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-sm-2 col-sm-2 control-label"></label>
                         
			<button onclick="historyCall();" type="button" style="width:220px;" class="btn btn-outline btn-primary btn-lg btn-block"><i class="glyphicon glyphicon-search" title="cari"></i></button>
			</div>
			</div>
			</div>
			</div>
			<br/>
			<br/>
            <!-- Pagging -->
            <label> Page </label>
            <select onchange="getPage(this)"; id="selectPage">
            
            </select>
            <label> of </label>
            <label id = "totPage"></label>
            <!-- page end --> 
               
            <div style="border:1px solid white;height:400px;width:100%;overflow-y:scroll;">
		  	<div class="row mt">
			<div class="col-lg-12">
              <section id="unseen">
               <!--Table Barang-->
               
               <table class="table1 table-bordered table-striped table-condensed table-hover">
                      <thead>
                          <tr>
                       		<th> Tanggal</th>
                      		<th> Type</th>
                            <th> Kegiatan</th>
                            <th> Histori </th>
                            <th> Action</th>
                            
                          </tr>
                       </thead>
                       <tbody id="tdbHistory">
                       </tbody>
                       </table>
                 </section>
                 </div><!--content-panel-->
                 </div><!--col-lg-4 -->
                 </div><!--row -->
        
	<div class="modal fade" id="myModalDet" tabindex="-1" role="dialog" aria-labelledby="myModalDet" aria-hidden="true">
	 <div class="modal-dialog modal-lg">
        <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
			</button>
            
            <h4 class="modal-title" id="myModalLabel">List Pembelian</h4>
		</div>
            
            <div style="border:1px solid white;height:500px;width:100%;overflow-x:scroll;overflow-y:scroll;">
            <div class="modal-body">
               <div class="row mt">
          		<div class="col-lg-12">
                  <div class="form-panel" >
                  	 <h4 class="mb"><i class="fa fa-angle-right"></i> Tambh</h4>
                      <textarea id="panelJson" readonly="readonly"></textarea>
                </div>
                </div>
                </div>
                </div>
                </div>
                </div>
                </div>
    	</div>
	
	
   <!----------------------------------------------------  SCRIPT -------------------------------------------------------------->                   
			
			<!-- Start Tabel Barang -->
			<script type="text/javascript">
			
		
			function historyCall(){
				var months = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
   				var data = {};
   				data["dateFrom"] = $('#dateFrom').val().trim();
   				data["dateTo"] = $('#dateTo').val().trim();
   				data["type"] = $('#selType').val().trim();
   				data["act"] = $('#selAction').val().trim();
   					$.ajax({
   						url : "${ctx }/json/history",
   						type: "POST",
   						data : JSON.stringify(data),
   						dataType : "json",
   						success: function(data, textStatus, jqXHR)
   							{
   								var _tbBody = document.getElementById("tdbHistory");
   									var _items = data.items;
   									var _historyObject = [];
   									for ( var i = 0; i < _items.length; i++) {
										var item = _items[i];
										
										var now = new Date(item.tanggal);
										
										var _tr = document.createElement("tr");
										var _tgl = document.createElement("td");
											_tgl.innerHTML = now.getDate()+"/"+months[now.getMonth()]+"/"+now.getFullYear()+" "+now.getHours()+":"+now.getMinutes();
											_tr.appendChild(_tgl);
										var _type = document.createElement("td");
											_type.innerHTML = item.type;
											_tr.appendChild(_type);
										var _act = document.createElement("td");
											_act.innerHTML = item.actionType;
											_tr.appendChild(_act);
//<button type="button" onclick="" data-toggle="modal" data-target="#myModalCallPembelian" class="btn btn-danger btn-xs"><i class="glyphicon glyphicon-eye-open"></i>
										var _tdAct = document.createElement("td");
										var _buttonView = document.createElement("button");
											_buttonView.setAttribute("data-toggle", "modal");
											_buttonView.setAttribute("data-target", "#myModalDet");
											_buttonView.setAttribute("class", "btn btn-danger btn-xs");
											_buttonView.type = "button";
											_buttonView["acttype"] = item.type;
											_buttonView["looparr"] = i;
										var _json = JSON.parse(item.data_history);
										_historyObject.push(_json);
											_buttonView.onclick = function(){
												setDetail(_historyObject, this);
											};
											_buttonView.innerHTML = '<i class="glyphicon glyphicon-eye-open"></i>';
										_tdAct.appendChild(_buttonView);
										_tr.appendChild(_tdAct);
											
										

											_tbBody.appendChild(_tr);
										
									}
       							/* alert(data.items[0].type); */
   							},
   								error: function (jqXHR, textStatus, errorThrown)
   							{
   								alert(textStatus);
   							}
   						});
   			}
			function setDetail(objJson,obj){
						var _jsonMentah = objJson;
						var obj = _jsonMentah[obj.looparr];
					
						//var _response = _jsonMentah["response"];
						document.getElementById("panelJson").innerHTML = "";
					document.getElementById("panelJson").appendChild(document.createTextNode(JSON.stringify(obj,null, 4)));
					  // window[a](JSON.stringify(_jsonMentah[obj.looparr]));
			}
			
			function DetPenjualanTheme(ArrayObj){
			
				var _theme = '<div class="modal-dialog modal-lg">        <div class="modal-content">         <div class="modal-header">            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>			</button>                        <h4 class="modal-title" id="myModalLabel">List Pembelian</h4>		</div>                        <div style="border:1px solid white;height:500px;width:100%;overflow-x:scroll;overflow-y:scroll;">            <div class="modal-body">               <div class="row mt">          		<div class="col-lg-12">                  <div class="form-panel">                  	 <h4 class="mb"><i class="fa fa-angle-right"></i> Tambah Pembelian</h4>                      <form id="form1" class="form-horizontal style-form" method="get" action="">                                                  <div class="form-group">                         <label class="col-sm-2 col-sm-2 control-label">Valuta Beli</label>                         <div class="col-sm-10">                         <input class="form-control2"  style="width:100px;" id="TxtValutaBeli" name="TxtValutaBeli" readonly/>  						 						 <input type="text" name="TxtIdTrip" id="TxtIdTrip" class="hidden"  style="width:200px;" readonly/>                         <input type="text" name="TxtNoTripBeli" id="TxtNoTripBeli" class=""  style="width:200px;" readonly/>                         <input type="text" name="TxtDateTripBeli" id="TxtDateTripBeli" class=""  style="width:200px;" readonly/>                                                  </div>                                                  <label class="col-sm-2 col-sm-2 control-label">Kurs H.Beli</label>                         <div class="col-sm-10">                         <input type="text" id="TxtKursBeliTrip" name="TxtKursBeliTrip" class="form-control2"  style="width:150px;" readonly/>                         </div>                         </div>                                                       <div class="form-group">                         <label class="col-sm-2 col-sm-2 control-label">Kode Barang</label>                         <div class="col-sm-10">                         <input id="TxtKodeProduct" name="TxtKodeProduct" type="text" class="form-control2"  style="width:200px;" readonly/>                         <button type="button" class="btn btn-group btn-info" data-toggle="modal" data-target="#myModalProduct" onclick ="productCall();"><i class="fa fa-search "></i></button>						                          </div>                         <label class="col-sm-2 col-sm-2 control-label">Nama Barang</label>                         <div class="col-sm-10">                         <!-- Rec Id Barang -->                         <input id="TxtIdStock" name="TxtIdStock" type="text" class="hidden"  style="width:200px;" readonly/>                         <input id="TxtNamaProduct" name="TxtNamaProduct" type="text" class="form-control2"  style="width:200px;" readonly/>                         </div>						 <label class="col-sm-2 col-sm-2 control-label">Isi 1 Karton</label>                         <div class="col-sm-10">                         <input id="TxtIsiCtn" name="TxtIsiCtn" type="text" class="form-control2"  style="width:150px;"  readonly/>                         <input id="TxtSatIsiCtn" name="TxtSatIsiCtn" type="text" class="form-control2"  style="width:80px;"  readonly/>                         <label>Berisi</label>                         <input id="TxtIsiPcs" name="TxtIsiPcs" type="text" class="form-control2"  style="width:200px;"  readonly/>                         <input id="TxtSatuanPCS" name="TxtSatuanPCS" type="text" class="form-control2"  style="width:80px;" Value="PCS" readonly/>                         						 <!-- rec Id Barang -->						 <input id="TxtIdBarang" name="TxtIdBarang" type="text" class="hidden"  style="width:200px;" readonly/>                                                  </div>                         <label class="col-sm-2 col-sm-2 control-label">Sisa Stock</label>                         <div class="col-sm-10">                         <input id="TxtStockPcs" name="TxtStockPcs" type="text" class="form-control2"  style="width:150px;"  readonly/>                         <input id="TxtSatuanPCS2" name="TxtSatuanPCS2" type="text" class="form-control2"  style="width:80px;" Value="PCS" readonly/>                         <input id="TxtTotStockPcs" name="TxtTotStockPcs" type="text" class="hidden"  style="width:150px;"  readonly/>                                                  </div>                         </div>                                                   <div class="form-group">                         <label class="col-sm-2 col-sm-2 control-label">Qty Beli</label>                         <div class="col-sm-10">                         <input id="TxtQtyBeli" name="TxtQtyBeli" type="text" class="form-control2"  style="width:200px;"  />                         <input type="text" class="form-control2"  style="width:100px;"  value="Karton"  readonly/>                         </div>                                                   <label class="col-sm-2 col-sm-2 control-label">Jumlah</label>                         <div class="col-sm-10">                         <input id="TxtJumlahIsi" name="TxtJumlahIsi" type="text" class="form-control2"  style="width:200px;" readonly/>                         <input id="TxtJumlahSatIsi" name="TxtJumlahSatIsi" type="text" class="form-control2"  style="width:90px;" readonly/>                         </div>                         <label class="col-sm-2 col-sm-2 control-label"></label>                         <div class="col-sm-10">                         <input id="TxtJumlahQtyBeliPcs" name="TxtJumlahQtyBeliPcs" type="text" class="form-control2"  style="width:200px;" readonly/>                         <input id="" name="" type="text" class="form-control2"  value="PCS" style="width:80px;" readonly/>                         </div>                         <label class="col-sm-2 col-sm-2 control-label">Cost(%)</label>                         <div class="col-sm-10">                         <input id="TxtCost" name="TxtCost" type="text" class="form-control2"  style="width:150px;" readonly/>                         <input id="TxtTotCost" name="TxtTotCost" class="hidden"  style="width:100px;"  type="text" value="0">                          </div>                          </div>                                                <div class="form-group">                                                <label class="col-sm-2 col-sm-2 control-label">H.Beli VTA</label>                        <div class="col-sm-10">                        <input  id="TxtIsi2" name="TxtIsi2" type="text" class="form-control2 amount"  style="width:100px;" readonly/>                     	<input id="TxtValutaBeli1" name="TxtValutaBeli1" type="text" class="form-control2"  style="width:100px;" width="20%" readonly/>                        <input id="TxtHB" name="TxtHB" type="text" class="form-control2"  style="width:150px;"  placeholder="">                        </div>                                                                        <label class="col-sm-2 col-sm-2 control-label"></label>                        <div class="col-sm-10">                        <input  id="" name="" type="text" class="form-control2"  style="width:100px;" readonly/>                        <input id="TxtValutaBeliIdr" name="TxtValutaBeliIdr" type="text" class="form-control2"  style="width:100px;" width="20%" value="IDR" readonly/>                        <input id="TxtHBidr" name="TxtHBidr" type="text" class="form-control2"  style="width:200px;"  readonly/>                        </div>                                                <label class="col-sm-2 col-sm-2 control-label">H.Jual IDR</label>                        <div class="col-sm-10">                        <input id="TxtIsi3" name="TxtIsi3" type="text" class="form-control2"  style="width:100px;" readonly/>                        <input type="text" class="form-control2"  style="width:100px;" value="IDR" readonly/>                        <input id="TxtHJRP" name="TxtHJRP" type="text" class="form-control2"  style="width:200px;"  >                        </div>                                                </div>                                                <h4 class="mb"><i class="fa fa-angle-right"></i> Jumlah Pembelian</h4>                                                <div class="form-group">                        <label class="col-sm-2 col-sm-2 control-label">Perh.dalam $</label>                        <div class="col-sm-10">                        <input id="TxtValutaBeli2" name="TxtValutaBeli2" type="text" class="form-control2"  style="width:100px;" readonly/>                        <input id="TxtPerHD" name="TxtPerHD" class="form-control2"  style="width:150px;"  type="text" placeholder="Bruto" readonly/>                        <label> Bruto</label>                        &nbsp; &nbsp;                                                <input id="TxtDisc" name="TxtDisc" class="form-control2"  style="width:50px;"  type="text" value="0" readonly/>                        <input id="TxtTotDisc" name="TxtTotDisc" class="hidden"  style="width:100px;"  type="text" value="0" readonly/>                                                <label>% Diskon</label>                        &nbsp; &nbsp;                                                <input id="TxtJumNetPerHD" name="TxtJumNetPerHD" class="form-control2"  style="width:150px;"  type="text" placeholder="Netto" readonly/>                        <label>Netto</label>                        </div>                                                <label class="col-sm-2 col-sm-2 control-label">Perh.dalam.RP</label>                        <div class="col-sm-10">                        <input type="text" class="form-control2"  style="width:100px;" value="IDR" Readonly/>                        <input id="TxtPerHD1" name="TxtPerHD1" class="form-control2"  style="width:150px;"  type="text" placeholder="Bruto" readonly/>                                                <label> Bruto</label>                        &nbsp; &nbsp;                                                <input class="form-control2"  style="width:100px;"  type="text" value="------" disabled/>                        <label> Diskon</label>                        &nbsp; &nbsp;                                               <input id="TxtJumNetPerHD1" name="TxtJumNetPerHD1" class="form-control2"  style="width:150px;"  type="text" placeholder="Netto" readonly/ >                        <label>Netto</label>                        </div>                        </div>                                                <div class="form-group">                                                <label class="col-sm-2 col-sm-2 control-label">HB.$/PCS</label>                        <div class="col-sm-10">                        <input id="TxtValutaBeli3" name="TxtValutaBeli3" type="text" class="form-control2"  style="width:100px;" disabled>                        <input id="TxtHargaPcsVta" name="TxtHargaPcsVta" class="form-control2"  style="width:200px;"  type="text" >                        </div>                                                <label class="col-sm-2 col-sm-2 control-label">HB.RP/PCS</label>                        <div class="col-sm-10">                        <input type="text" class="form-control2"  style="width:100px;" value="IDR" disabled>                        <input id="TxtHargaPcsIdr" name="TxtHargaPcsIdr" class="form-control2"  style="width:200px;"  type="text" >                        </div>                        </div>                                                                                            </form>                </div>                </div>                </div>                </div>                </div>                </div>                </div>';
				
				return _theme;
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
          /*Date Picker*/
          $(function()
        		  {
          	//$('#DateOrder,#DateOrderEdit').datepicker().datepicker("setDate", new Date());
          	
          	
          	$('#dateFrom,#dateTo').datepicker();
    		});
          </script>
  </body>
</html>
