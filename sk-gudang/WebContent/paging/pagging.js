function setPagging (callPage, totPage, currentPage){
	var _selectPage;
	var a = "getPage(this)";
	if(_customPage != ""){
		_selectPage = document.getElementById("selectPage"+_customPage);
		
		a = "getPage"+_customPage+"(this)";
	}else{
		_selectPage = document.getElementById("selectPage");
	}

	_customPage = "";
	
		_selectPage.innerHTML = "";
//		document.getElementById("curentPage").innerHTML = callPage;
		if(currentPage != 1){
			return;
		}
		for ( var i = 0; i < totPage; i++) {
			var _option = document.createElement("option");
			_option.value = i;
			_option.innerHTML = i*1+1;
			
			_selectPage.appendChild(_option);
		}
		
}
