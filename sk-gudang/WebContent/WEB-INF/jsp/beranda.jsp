<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="d"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"
	scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">

<body>
        <!--main content start-->
	
		<h3>
			<i class="fa fa-angle-right"></i> Selamat Datang
		</h3>
					
		<div class="widget widget-nopad">
			<div class="widget-header">
				<i class="icon-bookmark"></i>
				<h3>Pengumuman</h3>
				<button type="button" class="btn btn-group btn-info" data-toggle="modal" data-target="#myModalAddAnnounce">Add Announce</button>
				<br/>
				<!-- Pagging -->
            	<label> Page </label>
           	 	<select onchange="getPage(this)"; id="selectPage">
            
            	</select>
            	<label> of </label>
            	<label id = "totPage"></label>
            	<!-- page end -->
            	<br/> 
				<div style="border:1px solid white;height:400px;width:100%;overflow-x:scroll;overflow-y:scroll;">
				<table  id="myTable"  class="table3 table-bordered table-striped table-condensed table-hover">
					<thead>
						
						<tr id = "headerTable">
						
							<th width="10%"><i class="fa fa-bullhorn"></i> Tanggal</th>
							<th width="10%">Kepada</th>
							<th >Pengumuman</th>
							<!-- <th width="8%">ACTION</th>hanya admin yang bisa menggunakan. type user lain tidak -->
						</tr>
					</thead>
					
					<tbody id="tbdAnnounce">
					
					
					</tbody>
					
				</table>
				
				</div>
				
				<center><p>-----------------------------------------------</p></center>
			</div>
		</div>
	
<div class="modal fade" id="myModalAddAnnounce" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>

                </button>
                
                <h4 class="modal-title" id="myModalLabel">Tambah Pengumuman</h4>
			</div>
			<div class="row mt">
          		<div class="col-lg-12">
                  <div class="form-panel">
                  	  
                      <form class="form-horizontal style-form" method="get" >
                      		<table>
                      			
                      			<tr>
                      				<td class="col-sm-2 col-sm-2 control-label">Tanggal</td>
                      				<td class="col-sm-10"><input id="txtDate" name="txtDate" type="text" class="form-control2"  style="width:200px;"></td>
                      			
                      			</tr>
                      			<tr>
                      				<td class="col-sm-2 col-sm-2 control-label">Kepada</td>
                      				<td class="col-sm-10"><input id="txtDestination" name="txtDestination" type="text" class="form-control2"  style="width:200px;" required/></td>
                      			</tr>
                      			<tr>
                      				<td class="col-sm-2 col-sm-2 control-label">Pengumuman</td>
                      				<td class="col-sm-10"><textarea style="height: 100px; width: 500px;" id="txtContent" name="txtContent" class="form-control2" cols="40" rows="3" required/></textarea></td>
                      			</tr>
                      		</table>
                              
                          <button type="button" onclick="announceSaveCall();" class="btn btn-theme" value="Submit">SIMPAN</button>
					</form>
				</div>
			</div>
		</div>
		</div>
		</div>
		</div>
	
	<script type="text/javascript">
	
	
	/* Start Paging */
	function getPage(obj){
		var _data={};
		_data['page'] = "page"+obj.value;
		JSON.post(_data,'${ctx }/json/getPageAnnounce',10000,announceSent,null,null);
		/* setPagging("page"+obj.value,"",""); */
	}
	
	/* End Paging Barang */
	
	/* Table Pengumuman */
	var announceSent = function(data) {
		document.getElementById("tbdAnnounce").innerHTML = "";
			if(data.page){
				document.getElementById("totPage").innerHTML = data.page;
				setPagging("1",data.page,"1"); 
				_customPage = "";
				
			}
	var _items = data.items;
	$('#tbdAnnounce').empty();
	for ( var i = 0; i < _items.length; i++) {
	var _item = _items[i];
	var _id = "'"+_item.id+"'";
	if(document.getElementById("htype").innerHTML == "ADMIN"){
			var _actionTd = document.createElement("th");
				_actionTd.innerHTML = "ACTION";
			document.getElementById("headerTable").appendChild(_actionTd);
		$('#tbdAnnounce').append(
				$('<tr><\/tr>')
					.append($('<td><\/td>').html($.datepicker.formatDate('d MM yy',new Date(_item.date))))
					.append($('<td><\/td>').html(_item.destination))
					.append($('<td><\/td>').html(_item.content))
					.append($('<td><button type="button" onclick="announceRemoveCall('+_id+');" class="btn btn-danger btn-xs"><i class="fa fa-trash-o "><\/i><\/button><\/td>'))
					
		);
	
		
	}else{
		$('#tbdAnnounce').append(
				$('<tr><\/tr>')
					.append($('<td><\/td>').html($.datepicker.formatDate('d MM yy',new Date(_item.date))))
					.append($('<td><\/td>').html(_item.destination))
					.append($('<td><\/td>').html(_item.content))
					
		);
	}
		
		}
	};

	var announceSaveSent = function(data) {
		var _items = data.items;
		for ( var i = 0; i < _items.length; i++) {
			var _item = _items[i];
			$('#tbdAnnounce').append(
					$('<tr><\/tr>')
						.append($('<td><\/td>').html($.datepicker.formatDate('d MM yy',new Date(_item.date))))
						.append($('<td><\/td>').html(_item.destination))
						.append($('<td><\/td>').html(_item.content))
			);
		}
	};
	
	var announceSaveCall = function() {
		var _data={};
		_data['date']=$('#txtDate').datepicker('getDate').getTime();
		_data['destination']=$('#txtDestination').val().trim();
		_data['content']=$('#txtContent').val().trim();
		JSON.post(_data,'${ctx }/json/announce-add',10000,announceSave,null,null);
	};
	//announce delete
	var announceRemoveCall = function(_id) {
			if(confirm("Do you want to delete ?")) {
			    this.click;
		var _data={};
		_data['id'] = _id;
		JSON.post(_data,'${ctx }/json/announce-del',10000,announceDelete,null,null);
		
	 		}
	else {
		
	return;
	}
		};
	var announceCall = function() {
		var _data={};
		JSON.post(_data,'${ctx }/json/announce',10000,announceSent,null,null);
	};
	
	$(document).ready(
			function() {
				announceCall();
				
				$('#divToolTip').attr('data-placement','right').attr('data-original-title','Toggle Navigation');
				$('#txtDate').datepicker();
				

				
			});
	
	var announceSave = function(data){
		
		if(data.code == 0){
			alert(data.message);
			$('#myModalAddAnnounce').modal('hide');
			announceCall();
			
			}
			if (data.code != 0) {
				
				alert(data.message);
			}
			else {
				
			}
			};
			
	var announceDelete = function(data){
		if(data.code == 0){
			alert(data.message);
			announceCall();
									
			}
					
			else {
										
				}
									
			};		
	</script>
	

</body>
</html>
