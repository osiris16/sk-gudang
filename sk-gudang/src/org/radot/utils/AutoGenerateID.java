package org.radot.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.radot.hibernate.entities.ParamsEntity;
import org.radot.hibernate.entities.PenjualanEntity;
import org.radot.hibernate.persistences.ParamsPersistence;
import org.radot.hibernate.persistences.PenjualanPersistence;

public class AutoGenerateID {
	@SuppressWarnings("null")
	public static String keySeqTrip(String _key){
		String _hasil = "";
		String _seq = "";
		ParamsEntity _params = new ParamsPersistence().getByName(_key);
		if(null == _params){
			_params = new ParamsEntity();
			_params.setParamsKey(_key);
			_params.setParamsDesc("1");
			_params.save();
			_seq = "1";
		}else{
			
			int c = Integer.valueOf(_params.getParamsDesc())+1;
			_params.setParamsDesc(c+"");
			_seq = c+"";
			_params.modify();
		}
		
		int Loop = 3-_seq.length();
		
		String _a = "";
		for (int i = 0; i < Loop; i++) {
			_a = _a+"0";
		}
		_hasil = _key+"-"+_a+_seq;
		
		return _hasil;
	}
	public static String exGet(String Generate, String total) {
		int Loop = 3-total.length();
		Generate = Generate+"-";
		for (int i = 0; i < Loop; i++) {
		Generate = Generate+"0";
		}
		Generate = Generate+total;
		System.out.println(Generate);
		return Generate;
	}
	public static String exFaktur(String TotalGenerate) {
		List<String> _arrayDouble = new ArrayList<String>();
		_arrayDouble.add("A");
		_arrayDouble.add("B");
		_arrayDouble.add("C");
		_arrayDouble.add("D");
		_arrayDouble.add("E");
		_arrayDouble.add("F");
		_arrayDouble.add("G");
		_arrayDouble.add("H");
		_arrayDouble.add("I");
		_arrayDouble.add("J");
		_arrayDouble.add("K");
		_arrayDouble.add("L");
		_arrayDouble.add("M");
		_arrayDouble.add("N");
		_arrayDouble.add("O");
		_arrayDouble.add("P");
		_arrayDouble.add("Q");
		_arrayDouble.add("R");
		_arrayDouble.add("S");
		_arrayDouble.add("T");
		_arrayDouble.add("U");
		_arrayDouble.add("V");
		_arrayDouble.add("W");
		_arrayDouble.add("X");
		_arrayDouble.add("Y");
		_arrayDouble.add("Z");

		Date _now = new Date();	
		SimpleDateFormat _dateFormat = new SimpleDateFormat("MM - yyyy");

		String _datString = _dateFormat.format(_now);
		String[] _arrayDate = _datString.split(" - ");
		String _month = _arrayDate[0];
		String _year = _arrayDate[1];

		int _yearD = Integer.valueOf(_year.trim());
		int _idyear = _yearD - 2011;
		int _monthD = Integer.valueOf(_month.trim());
		int _idMonth = _monthD -1;

		String _autoId = _arrayDouble.get(_idyear)+_arrayDouble.get(_idMonth);
		String _forFak = _autoId;
		String total = "";
		ParamsEntity _seqData = (ParamsEntity) new ParamsPersistence().getByRecId(2l);
		
		if(!_autoId.equalsIgnoreCase(_seqData.getParamsKey())){
			_seqData.setParamsKey(_autoId);
			_seqData.setParamsDesc("1");
			_seqData.modify();
		}
		total = _seqData.getParamsDesc();
		
		
		int Loop = 3-total.length();
		_autoId = _autoId+"-";
		String _a = "";
		for (int i = 0; i < Loop; i++) {
			_a = _a+"0";
		}
		_autoId = _autoId+_a+total;
		
		PenjualanEntity _availPen = new PenjualanPersistence().getByFakturNumb(_autoId);
		PenjualanEntity _enk = new PenjualanPersistence().getByFakturNumbLike("%"+_forFak+"%");
		System.out.println(_forFak+" klklk ");
		if(null != _availPen){
			if(null != _enk){
				_autoId = _forFak;
				int _kll = Integer.valueOf(_enk.getFakturNumb().substring(3))+1;
				System.out.println(_kll);
				total = String.valueOf(_kll);
				Loop = 3-total.length();
				_autoId = _autoId+"-";
				 _a = "";
				for (int i = 0; i < Loop; i++) {
					_a = _a+"0";
				}
				_autoId = _autoId+_a+total;
			}
		}
		int _seqNext = Integer.valueOf(total);
		_seqData.setParamsDesc(String.valueOf(_seqNext+1));
		_seqData.modify();
		
		return _autoId;
		
	}
	@SuppressWarnings({ "unused", "unused", "null", "unused", "null" })
	public static String ex(String TotalGenerate) {
		List<String> _arrayDouble = new ArrayList<String>();
		_arrayDouble.add("A");
		_arrayDouble.add("B");
		_arrayDouble.add("C");
		_arrayDouble.add("D");
		_arrayDouble.add("E");
		_arrayDouble.add("F");
		_arrayDouble.add("G");
		_arrayDouble.add("H");
		_arrayDouble.add("I");
		_arrayDouble.add("J");
		_arrayDouble.add("K");
		_arrayDouble.add("L");
		_arrayDouble.add("M");
		_arrayDouble.add("N");
		_arrayDouble.add("O");
		_arrayDouble.add("P");
		_arrayDouble.add("Q");
		_arrayDouble.add("R");
		_arrayDouble.add("S");
		_arrayDouble.add("T");
		_arrayDouble.add("U");
		_arrayDouble.add("V");
		_arrayDouble.add("W");
		_arrayDouble.add("X");
		_arrayDouble.add("Y");
		_arrayDouble.add("Z");

		Date _now = new Date();	
		SimpleDateFormat _dateFormat = new SimpleDateFormat("MM - yyyy");

		String _datString = _dateFormat.format(_now);
		String[] _arrayDate = _datString.split(" - ");
		String _month = _arrayDate[0];
		String _year = _arrayDate[1];

		int _yearD = Integer.valueOf(_year.trim());
		int _idyear = _yearD - 2011;
		int _monthD = Integer.valueOf(_month.trim());
		int _idMonth = _monthD -1;

		String _autoId = _arrayDouble.get(_idyear)+_arrayDouble.get(_idMonth);
		String total = "";
		ParamsEntity _seqData = (ParamsEntity) new ParamsPersistence().getByRecId(1l);
		System.out.println(_autoId);
		
		if(!_autoId.equalsIgnoreCase(_seqData.getParamsKey())){
			_seqData.setParamsKey(_autoId);
			_seqData.setParamsDesc("1");
			_seqData.modify();
		}
		total = _seqData.getParamsDesc();
		int Loop = 3-total.length();
		_autoId = _autoId+"-";
		String _a = "";
		for (int i = 0; i < Loop; i++) {
			_a = _a+"0";
		}
		_autoId = _autoId+_a+total;
		
		int _seqNext = Integer.valueOf(_seqData.getParamsDesc());
		_seqData.setParamsDesc(String.valueOf(_seqNext+1));
		_seqData.modify();
		return _autoId;
		
	}
	public static String exPenjualanSave(String GeneratId, PenjualanEntity _ent) {
		
		PenjualanEntity _peEntity = new PenjualanPersistence().getByOrderNumbLike("%"+GeneratId.substring(0,2)+"%");
		int _lastOrderNumInt = 0;
		if (null != _peEntity){
			String _lastOrderNumb = _peEntity.getOrderNumb().substring(3);
			 _lastOrderNumInt = Integer.valueOf(_lastOrderNumb)+1;
			GeneratId = GeneratId.substring(0,3)+_lastOrderNumInt;
		}
		_ent.setOrderNumb(GeneratId);
		_ent.save();
		ParamsEntity _seqData = (ParamsEntity) new ParamsPersistence().getByRecId(1l);
		_seqData.setParamsDesc(String.valueOf(_lastOrderNumInt+1));
		_seqData.modify();
		System.out.println("---sss--");
		return "asdasd";
	}
	public static void main(String[] args) {
		//System.out.println(AutoGenerateID.keySeqTrip("P01"));
		/*String _a = "GC-121";
		System.out.println( _a.substring(3));*/
		
		System.out.println(AutoGenerateID.exFaktur(""));
//		PenjualanEntity _peEntity = new PenjualanPersistence().getByOrderNumbLike("%"+_a.substring(0,2)+"%");
//		System.out.println(_peEntity.getRecId());
	}
}
