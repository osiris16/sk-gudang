
var _dataHistory;
var historyCall = function(obj) {
var _data={};
_data['queryData']=$('#inpQuery').val().trim();
_data['byValue']=$('#selByValue').val().trim();
JSON.post(_data,obj+'/json/history',10000,historySent,null,null);
//var _pageTot = vendorSent.page;
		};
var historySent = function(data) {
/*document.getElementById("tbdHistory").innerHTML = "";
          		if(data.page){
          document.getElementById("totPage").innerHTML = data.page;
          		 		setPagging("1",data.page,"1"); 
          
          		}*/
          	var _items = data.items;
        	$('#tbdHistory').empty();
        	_dataHistory = data.items;
        	for ( var i = 0; i < _items.length; i++) {
        	var _item = _items[i];
        	var _id = "'"+_item.id+"'";
        	var _name = "'"+_item.name+"'";
        	var _type = _item.type;
        
        	$('#tbdHistory').append(
        	$('<tr><\/tr>')
        	.append($('<td><\/td>').html(_item.id))
        	.append($('<td><\/td>').html(_item.tanggal))
        	.append($('<td><\/td>').html(_item.actionType))
        	.append($('<td><\/td>').html(_item.type))
        	.append($('<td><\/td>').html(_item.name))
        	.append($('<td class="right"><button id="pushEditButton" type="button" onclick="setDetail'+_type+'('+i+')" data-toggle="modal" data-target="#myEditVendor" class="btn btn-primary btn-xs"><i class="fa fa-pencil "><\/i><\/button> - <button type="button" onclick="vendorRemoveCall('+_id+');" class="btn btn-danger btn-xs"><i class="fa fa-trash-o "><\/i><\/button><\/td>'))
	        );
        	}
        };
        
 var setDetailVendor = function(data){
	 var _bodyModal = '<div class="form-group">     <label class="col-sm-2 col-sm-2 control-label">Vendor/Pemasok</label>     <div class="col-sm-10">     	<input id="txtVendNameEdit" name="txtVendNameEdit" type="text" class="form-control" style="width:200px;" required/>        </div>            <label class="col-sm-2 col-sm-2 control-label">Negara</label>     <div class="col-sm-10">     	<input id="txtCountryNameEdit" name="txtCountryNameEdit" type="text" class="form-control" style="width:200px;" required/>        </div>     </div>       		<div class="form-group">     <label class="col-sm-2 col-sm-2 control-label">Mata Uang</label>     <div class="col-sm-10">     	<select id="TxtMataUangEdit" name="TxtMataUangEdit" class="form-control" style="width:200px;">       		<option value="IDR">IDR</option>       		<option value="USD">USD</option>       		<option value="HKD">HKD</option>       	</select>    	</div>     </div>           	<div class="form-group">     <label class="col-sm-2 col-sm-2 control-label">Alamat 1</label>     <div class="col-sm-10">     	<input id="txtVendAddr1Edit" name="txtVendAddr1Edit" type="text" class="form-control" style="width:300px;" >     </div>          <label class="col-sm-2 col-sm-2 control-label">Alamat 2</label>     <div class="col-sm-10">     	<input id="txtVendAddr2Edit" name="txtVendAddr2Edit" type="text" class="form-control" style="width:300px;" >     </div>     <label class="col-sm-2 col-sm-2 control-label">Kota</label>     <div class="col-sm-10">     	<input id="txtVendCityEdit" name="txtVendCityEdit" type="text" class="form-control" style="width:200px;" >     </div>     </div>           	<div class="form-group">     <label class="col-sm-2 col-sm-2 control-label">No.TLP</label>     <div class="col-sm-10">     	<input id="txtVendPhoneEdit" name="txtVendPhoneEdit" type="text" class="form-control" style="width:200px;"  >     </div>          <label class="col-sm-2 col-sm-2 control-label">No.Fax</label>     <div class="col-sm-10">     	<input id="txtVendFaxEdit" name="txtVendFaxEdit" type="text" class="form-control" style="width:200px;" >     </div>          <label class="col-sm-2 col-sm-2 control-label">Email</label>     <div class="col-sm-10">     	<input id="txtVendEmailEdit" name="txtVendEmailEdit" type="text" class="form-control" style="width:200px;"  >     </div>     </div>            <div class="form-group">     <label class="col-sm-2 col-sm-2 control-label">NPWP</label>     <div class="col-sm-10">     	<input id="txtVendTaxEdit" name="txtVendTaxEdit" class="form-control" style="width:200px;"  type="text" >     </div>            <label class="col-sm-2 col-sm-2 control-label">Cargo Ekspedisi</label>     <div class="col-sm-10">     	<input id="txtVendCargoEdit" name="txtVendCargoEdit" type="text"  class="form-control" style="width:200px;" >     </div>     </div>     <button type="button" id="buttonEdit" onclick="vendorEditCall()" class="btn btn-theme" >UPDATE</button> ';
	 var _data = _dataHistory[data].data_history;
	 var _data= JSON.parse(_data);
	 var _historyModal = document.getElementById("modalHistory").innerHTML = _bodyModal;
	 var _params = _data.params;
	 document.getElementById("txtVendNameEdit").value = _params.name;
	 document.getElementById("txtCountryNameEdit").value = _params.country;
	 
 };
 
 var setDetailProduct = function(data){
	 var _bodyModal = '<div class="form-group">                <label class="col-sm-2 col-sm-2 control-label">Tgl. Trip</label>                          <div class="col-sm-8">                          <input class="form-control2"  style="width:150px;" type="text" id="DateTrip" name="DateTrip" required="required">                                                    <label class="control-label">&nbsp;&nbsp;&nbsp;&nbsp;</label>                                                    <label class="control-label">No. Trip</label>                          <input class="form-control2"  style="width:150px;" id="TxtNoTrip" name="TxtNoTrip" type="text"  placeholder="No. Trip">                          </div>                                                    </div>                                                    <div class="form-group">                          <label class="col-sm-2 col-sm-2 control-label">Tgl. Nota</label>                          <div class="col-sm-8">                          <input class="form-control2"  style="width:150px;" type="text" id="DateNota" name="DateNota" required="required">                                                    <label class="control-label">&nbsp;&nbsp;&nbsp;&nbsp;</label>                                                    <label class=" control-label">No. Nota</label>                          <input class="form-control2"  style="width:150px;" id="TxtNoNota" nam="TxtNoNota" type="text" placeholder="No. Nota">                          </div>                                                        </div>                                                    <div class="form-group">                                                    <label class="col-sm-2 col-sm-2 control-label">Nama Vendor</label>                          <div class="col-sm-10">                          <input class="form-control"  style="width:200px;" id="TxtNamaVendor" name="TxtNamaVendor" type="text" readonly/>                          <!-- Kode Vendor -->                          <input class="hidden" id="TxtIdVendor" name="TxtIdVendor" type="text" readonly/>                                                        <!-- ModalTable -->						  <button type="button" class="btn btn-group btn-info" data-toggle="modal" data-target="#myModalVendor" onclick ="vendorCall();"><i class="fa fa-search "></i></button>						  </div>                                                        <label class="col-sm-2 col-sm-2 control-label">Negara</label>                          <div class="col-sm-10">                          <input class="form-control"  style="width:200px;" id="TxtNegara" name="TxtNegara" type="text" readonly/>                              </div>                                                        <label class="col-sm-2 col-sm-2 control-label">Mata Uang</label>                          <div class="col-sm-10">                          <input class="form-control"  style="width:200px;" type="text" id="TxtVta" name="TxtVta" readonly/>  						  </div>                                                        <label class="col-sm-2 col-sm-2 control-label">Kurs Beli IDR</label>                          <div class="col-sm-10">                          <input class="form-control"  style="width:200px;" id="TxtKursBeli" name="TxtKursBeli" type="text" >                          </div>                                                         <label class="col-sm-2 col-sm-2 control-label">Tgl. Terima</label>                          <div class="col-sm-10">                          <input class="form-control"  style="width:150px;" id="DateTerima" name="DateTerima" type="text" >                          </div>                          </div>';
	 var _data = _dataHistory[data].data_history;
	 var _data= JSON.parse(_data);
	 var _historyModal = document.getElementById("modalHistory").innerHTML = _bodyModal;
	 var _params = _data.params;
	 document.getElementById("txtVendNameEdit").value = _params.name;
	 document.getElementById("txtCountryNameEdit").value = _params.country;
	 
 };
	