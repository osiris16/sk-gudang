<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
%><%@
taglib prefix="d" uri="http://www.opensymphony.com/sitemesh/decorator" %><%@
taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><c:set
var="ctx" value="${pageContext.request.contextPath }" scope="request" /><c:set
var="cUser" value="${sessionScope['org.radot.current-user'] }" /><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="Dashboard">
	<meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

	<link rel="stylesheet" href="${ctx }/jquery-ui/jquery-ui.css">
	<link rel="stylesheet" href="${ctx }/assets/css/bootstrap.css">
	<link rel="stylesheet" href="${ctx }/assets/font-awesome/css/font-awesome.css">
	<link rel="stylesheet" href="${ctx }/assets/js/gritter/css/jquery.gritter.css">
	<link rel="stylesheet" href="${ctx }/assets/lineicons/style.css">
	<link href="${ctx }/assets/css/style.css" rel="stylesheet">
	<link href="${ctx }/assets/css/style-responsive.css" rel="stylesheet">

	<script type="text/javascript" src="${ctx }/paging/pagging.js"></script>
	
	<script type="text/javascript" src="${ctx }/jsFiddlePDF/dist/jspdf.min.js?ver2"></script>
	<script type="text/javascript" src="${ctx }/jsFiddlePDF/dist/jspdf.plugin.autotable.src.js"></script>
	


	<title>CS TOYS</title>

	<d:head />
	
</head>

<body id="docBody">
	<div id="head"></div>
	
	<script type="text/javascript" src="${ctx }/web/common.js"></script>
	<script type="text/javascript" src="${ctx }/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx }/jquery-ui/jquery-ui.js"></script>
	<script type="text/javascript" src="${ctx }/jquery-validator/jquery.validate.js"></script>
	<script type="text/javascript" src="${ctx }/jquery-validator/localization/messages_id.js"></script>
	<script type="text/javascript" src="${ctx }/jquery-validator/additional-methods.js"></script>
	<script type="text/javascript" src="${ctx }/jquery-json/json.js"></script>
	<script type="text/javascript" src="${ctx }/assets/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ctx }/assets/js/jquery.backstretch.min.js"></script>
	<script type="text/javascript" src="${ctx }/assets/js/chart-master/Chart.js"></script>
	<script type="text/javascript" src="${ctx }/assets/js/jquery.dcjqaccordion.2.7.js"></script>
	<script type="text/javascript" src="${ctx }/assets/js/jquery.scrollTo.min.js"></script>
	<%-- <script type="text/javascript" src="${ctx }/assets/js/jquery.nicescroll.js"></script> --%>
	<script type="text/javascript" src="${ctx }/assets/js/jquery.sparkline.js"></script>
	<script type="text/javascript" src="${ctx }/assets/js/common-scripts.js"></script>
	<script type="text/javascript" src="${ctx }/assets/js/gritter/js/jquery.gritter.js"></script>
	<script type="text/javascript" src="${ctx }/assets/js/gritter-conf.js"></script>
	<script type="text/javascript" src="${ctx }/assets/js/angular.js"></script>
	
	
	<!-- <script src="${ctx }/assets/js/common-scripts.js"></script> -->
	
<script>var _customPage = "";</script>
<section id="container" >
      <!--header start-->
      <header class="header black-bg">
              	
              	<div class="sidebar-toggle-box">
                <div class="fa fa-bars tooltips" data-placement="right" data-original-title="Toggle Navigation"></div>
              	</div>
            	
            	<!--logo start-->
            	<div class="nav notify-row" id="top_menu">
                </div>
                
            	<div class="top-menu">
           		<ul class="nav pull-right top-menu">
            	<li><a class="logout" onclick="javascript: logout()">Logout</a></li>
            	</ul>
            	</div>
            	<d:head />
       </header>
       
			<div id="sidebar" class="nav-collapse" style="overflow-x:scroll;" >
			
			<ul class="sidebar-menu" id="nav-accordion">
				<li>
					<h5 class="centered">User : ${cUser.name }</h5>
					<h5 id="htype" class="centered" style="color: Red;">${cUser.type }</h5>
				</li>

					
				<li id="beranda" class="mt">
							<a id="ancBeranda" href="beranda?menu=Beranda">
							<i class="fa fa-dashboard"></i>
							<span>Home</span>
							</a>
				</li>
				<li id="berandaSales" class="mt">
							<a id="ancBerandaSales" href="beranda?menu=BerandaSales">
							<i class="fa fa-dashboard"></i>
							<span>Home</span>
							</a>
				</li>

				<li id="stok" class="sub-menu">
					<a id="a000" href="javascript:;"> 
						<i class="fa fa-desktop"></i>
							<span>Stok Barang</span>
					</a>
					<ul id="subPurchase0" class="sub">
					<!-- style="display:none"; --> 
						<li id="liSalesmanstok">
						<a href="salesmanstok?anc=a000&parent=subPurchase0&menu=liSalesmanstok" id="ancSalesmanstok">Product / Barang</a>
						</li>
						<li id="liBarangKosong">
						<a href="barangKosong?anc=a000&parent=subPurchase0&menu=liStokKosong" id="ancBarangKosong">Stok Kosong</a>
						</li>
						
					</ul>
				</li>
				<li id="pembelian" class="sub-menu">
					<a id="a001" href="javascript:;"> 
						<i class="fa fa-dropbox"></i>
							<span>Pembelian</span>
					</a>
					<ul id="subPurchase" class="sub">
					<!-- style="display:none"; --> 
						<li id="liVendor">
							<a href="vendor?anc=a001&parent=subPurchase&menu=liVendor">Vendor</a>
						</li>
						<li id="liBarang">
							<a href="barang?anc=a001&parent=subPurchase&menu=liBarang">Barang</a>
						</li>
						<li id="liTrip">
							<a href="trip?anc=a001&parent=subPurchase&menu=liTrip">Trip</a>
						</li>
						
					</ul>
				</li>
				<!-- style="display:none" id="liMasterJual"  -->
				<li id="penjualan" class="sub-menu">
					<a id="a002" href="javascript:;"> 
						<i class="fa fa-dropbox"></i>
						<span>Penjualan</span>
					</a>
					
					<ul id="subPurchase2" class="sub">
						<li id="liCustomer">
							<a href="customer?anc=a002&parent=subPurchase2&menu=liCustomer">Customer</a>
						</li>
						<li id="liSalesman">
							<a href="salesman?anc=a002&parent=subPurchase2&menu=liSalesman">Salesman</a>
						</li>
						
						<li id="liPenjualan">
							<a href="penjualan?anc=a002&parent=subPurchase2&menu=liPenjualan">Penjualan</a>
						</li>
							
						<!-- <li id="liOrderJual">
							<a href="orderjual?anc=a002&parent=subPurchase2&menu=liOrderJual">Order Jual</a>
						</li> -->
						
						<li id="liReturPenjualan">
							<a href="returpenjualan?anc=a002&parent=subPurchase2&menu=liReturPenjualan">Retur Penjualan</a>
						</li>
					</ul>
				</li>
				
				<li id="liMasterReport" class="sub-menu" style="display:none;">
						<a id="a003" href="javascript:;"> 
						<i class="fa fa-print"></i>
						<span>PRINT OUT</span>
						</a>
						
					<ul id="subPurchase3" class="sub">
						<li id="PrintPenjualan">
							<a href="printjual?anc=a003&parent=subPurchase3&menu=liPrintJual">Print Penjualan</a>
						</li>

						<li id="PrintStock">
							<a href="systemuser?anc=a003&parent=subPurchase3&menu=liSystemUser">Print Stock</a>
						</li>
					</ul>
				</li>
				
				<li id="adminuser" class="sub-menu">
						<a id="a004" href="javascript:;"> 
						<i class="fa fa-user"></i>
						<span>Administrasi</span>
						</a>
						
					<ul id="subPurchase4" class="sub">
						<li id="liReport">
							<a href="report?anc=a004&parent=subPurchase4&menu=liReport">Report / Laporan</a>
						</li>
						<li id="liChangePass">
							<a href="changepass?anc=a004&parent=subPurchase4&menu=liChangePass">Ganti Password</a>
						</li>

						<li id="liSystemUser">
							<a href="systemuser?anc=a004&parent=subPurchase4&menu=liSystemUser">System User</a>
						</li>
						
						<li id="liCatatanAudit">
							<a href="catatanaudit?anc=a004&parent=subPurchase4&menu=liCatatanAudit">Catatan Audit</a>
						</li>
						
						<li>
						<a href="">------</a>
						
							</li>
					</ul>
				</li>
				</ul>
			</div>
		
			<section id="main-content">
			<section class="wrapper">
			
			<d:body />
			
			</section>
		</section>
	
	
		<footer class="site-footer">
          	<div class="text-center">
              CS TOYS 
           <a href="#head" class="go-top">
                  <i class="fa fa-angle-up"></i>
              </a>
          </div>
      </footer>
</section>

<script type="text/javascript" >
		$().ready(function(){
			try{
				$('#'+'${param.parent}').css('display','block');
				$('#'+'${param.anc}').attr('class','dcjq-parent active');
				$('#'+'${param.menu}').attr('class','active');
			}catch(e){
				alert(e);
			}
			
		});
	</script>
	
	
   <script type="text/javascript" >
    $(function() {
        function responsiveView() {
            var wSize = $(window).width();
            if (wSize <= 768) {
                $('#container').addClass('sidebar-close');
                $('#sidebar > ul').hide();
            }

            if (wSize > 768) {
                $('#container').removeClass('sidebar-close');
                $('#sidebar > ul').show();
            }
        }
        $(window).on('load', responsiveView);
        $(window).on('resize', responsiveView);
    });

    $('.fa-bars').click(function () {
        if ($('#sidebar > ul').is(":visible") === true) {
            $('#main-content').css({
                'margin-left': '0px'
            });
            $('#sidebar').css({
                'margin-left': '-210px'
            });
            $('#sidebar > ul').hide();
            $("#container").addClass("sidebar-closed");
        } else {
            $('#main-content').css({
                'margin-left': '210px'
            });
            $('#sidebar > ul').show();
            $('#sidebar').css({
                'margin-left': '0'
            });
            $("#container").removeClass("sidebar-closed");
        }
    });
    

    </script>
 <script type="text/javascript">
    
 var _type = document.getElementById("htype").innerHTML;
 if(_type == "ADMIN"){
	 document.getElementById("berandaSales").style.display = "none";	 
 }
 if(_type == "RESELLER"){
	 document.getElementById("beranda").style.display = "none";	 
 }
 
 if(_type ==  "RESELLER"){
	 document.getElementById("pembelian").style.display = "none";
 }
 if(_type ==  "RESELLER"){
	 document.getElementById("penjualan").style.display = "none";
 }
 if(_type ==  "RESELLER"){
	 document.getElementById("liReport").style.display = "none";
 }
 if(_type ==  "RESELLER"){
	 document.getElementById("liSystemUser").style.display = "none";
 }
 if(_type ==  "RESELLER"){
	 document.getElementById("liCatatanAudit").style.display = "none";
 }
    var logoutSent=function(data){
    	window.location.replace('login');
	};
	function logout(){
		if(confirm("Do you want to Logout ?")) {
			    this.click;
		var _data={};
		 JSON.post(_data,'${ctx }/json/logout',10000,logoutSent);
	}
	}
   
    </script>
   
</body>
</html>