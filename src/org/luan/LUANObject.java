package org.luan;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class LUANObject implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private ConcurrentHashMap<String, String> stringMap = null;
	private ConcurrentHashMap<String, Integer> intMap = null;
	private ConcurrentHashMap<String, Double> doubleMap = null;
	private ConcurrentHashMap<String, Boolean> booleanMap = null;
	
	private ConcurrentHashMap<String, String[]> stringArrMap = null;
	private ConcurrentHashMap<String, int[]> intArrMap = null;
	private ConcurrentHashMap<String, double[]> doubleArrMap = null;
	private ConcurrentHashMap<String, boolean[]> booleanArrMap = null;
	private ConcurrentHashMap<String, byte[]> byteArrMap = null;
	
	private ConcurrentHashMap<String, String[][]> stringTwoDeminsionalArrMap = null;
	private ConcurrentHashMap<String, int[][]> intTwoDeminsionalArrMap = null;
	private ConcurrentHashMap<String, double[][]> doubleTwoDeminsionalArrMap = null;
	private ConcurrentHashMap<String, boolean[][]> booleanTwoDeminsionalArrMap = null;
		
	private void initilizeLUANObject(){
		this.stringMap = new ConcurrentHashMap<String, String>();
		this.intMap = new ConcurrentHashMap<String, Integer>();
		this.doubleMap = new ConcurrentHashMap<String, Double>();
		this.booleanMap = new ConcurrentHashMap<String, Boolean>();
		this.stringArrMap = new ConcurrentHashMap<String, String[]>();
		this.intArrMap = new ConcurrentHashMap<String, int[]>();
		this.doubleArrMap = new ConcurrentHashMap<String, double[]>();
		this.booleanArrMap = new ConcurrentHashMap<String, boolean[]>();
		this.byteArrMap = new ConcurrentHashMap<String, byte[]>();
		this.intTwoDeminsionalArrMap = new ConcurrentHashMap<String, int[][]>();
		this.doubleTwoDeminsionalArrMap = new ConcurrentHashMap<String, double[][]>();
		this.booleanTwoDeminsionalArrMap = new ConcurrentHashMap<String, boolean[][]>();
		this.stringTwoDeminsionalArrMap = new ConcurrentHashMap<String, String[][]>();
	}
	
	public LUANObject(){
		initilizeLUANObject();
	}
	
	public LUANObject(String luanObjectstring)throws Exception{
		if(luanObjectstring.substring(0, 11).equals("|#|||#|||#|")){
			initilizeLUANObject();
			readStringAndCreateLUANOBject(luanObjectstring);
		}else{
			throw new LUANException("LUANObject must begin with |#|||#|||#|");
		}
	}
	
	public LUANObject(byte[] luanObject)throws Exception{
		String luanObjectstring;
		luanObjectstring = new String(luanObject,"ISO-8859-1");//_ISO-_8859-1_
		if(luanObjectstring.substring(0, 11).equals("|#|||#|||#|")){
			initilizeLUANObject();
			readStringAndCreateLUANOBject(luanObjectstring);
		}else{
			throw new LUANException("LUANObject must begin with |#|||#|||#|");
		}
	}
	/**************************************************************************************/
	/*****************************************Object Adders********************************/
	/**************************************************************************************/
	
	public void put(String key,String value)throws LUANException{
		if(!stringMap.containsKey(key)){
			stringMap.put(key, value);
		}else{
			throw new LUANException("Can not put value with this key ("+key+"), key already excists in the String map!");
		}	
	}
	
	public void put(String key,int value)throws LUANException{
		if(!intMap.containsKey(key)){
			intMap.put(key, value);
		}else{
			throw new LUANException("Can not put value with this key ("+key+"), key already excists in the Integer map!");
		}
	}
	
	public void put(String key,double value)throws LUANException{
		if(!doubleMap.containsKey(key)){
			doubleMap.put(key, value);
		}else{
			throw new LUANException("Can not put value with this key ("+key+"), key already excists in the double map!");
		}
	}
	
	public void put(String key,boolean value)throws LUANException{
		if(!booleanMap.containsKey(key)){
			booleanMap.put(key, value);
		}else{
			throw new LUANException("Can not put value with this key ("+key+"), key already excists in the booleam map!");
		}
	}
	
	public void put(String key,String[] value)throws LUANException{
		if(!stringArrMap.containsKey(key)){
			stringArrMap.put(key, value);
		}else{
			throw new LUANException("Can not put value with this key ("+key+"), key already excists in the String[] map!");
		}
	}
	
	public void put(String key,int[] value)throws LUANException{
		if(!intArrMap.containsKey(key)){
			intArrMap.put(key, value);
		}else{
			throw new LUANException("Can not put value with this key ("+key+"), key already excists in the int[] map!");
		}
	}
	
	public void put(String key,double[] value)throws LUANException{
		if(!doubleArrMap.containsKey(key)){
			doubleArrMap.put(key, value);
		}else{
			throw new LUANException("Can not put value with this key ("+key+"), key already excists in the double[] map!");
		}
	}
	
	public void put(String key,boolean[] value)throws LUANException{
		if(!booleanArrMap.containsKey(key)){
			booleanArrMap.put(key, value);
		}else{
			throw new LUANException("Can not put value with this key ("+key+"), key already excists in the boolean[] map!");
		}
	}
	
	public void put(String key,byte[] value)throws LUANException{
		if(!byteArrMap.containsKey(key)){
			byteArrMap.put(key, value);
		}else{
			throw new LUANException("Can not put value with this key ("+key+"), key already excists in the byte[] map!");
		}
	}
	
	public void put(String key,int[][] value)throws LUANException{
		if(!intTwoDeminsionalArrMap.containsKey(key)){
			intTwoDeminsionalArrMap.put(key, value);
		}else{
			throw new LUANException("Can not put value with this key ("+key+"), key already excists in the int[][] map!");
		}
	}
	
	public void put(String key,double[][] value)throws LUANException{
		if(!doubleTwoDeminsionalArrMap.containsKey(key)){
			doubleTwoDeminsionalArrMap.put(key, value);
		}else{
			throw new LUANException("Can not put value with this key ("+key+"), key already excists in the double[][] map!");
		}
	}
	
	public void put(String key,boolean[][] value)throws LUANException{
		if(!booleanTwoDeminsionalArrMap.containsKey(key)){
			booleanTwoDeminsionalArrMap.put(key, value);
		}else{
			throw new LUANException("Can not put value with this key ("+key+"), key already excists in the boolean[][] map!");
		}
	}
	
	public void put(String key,String[][] value)throws LUANException{
		if(!stringTwoDeminsionalArrMap.containsKey(key)){
			stringTwoDeminsionalArrMap.put(key, value);
		}else{
			throw new LUANException("Can not put value with this key ("+key+"), key already excists in the String[][] map!");
		}
	}
	
	/**************************************************************************************/
	/************************************Object Getters************************************/
	/**************************************************************************************/
	
	public String getString(String key)throws LUANException{
		if(this.stringMap.containsKey(key)){
			return this.stringMap.get(key);
		}else{
			throw new LUANException("Can not get value with this key ("+key+"), key does not excists in the String map!");
		}
	}
	
	public int getInteger(String key)throws LUANException{
		if(this.intMap.containsKey(key)){
			return this.intMap.get(key);
		}else{
			throw new LUANException("Can not get value with this key ("+key+"), key does not excists in the int map!");
		}
	}
	
	public double getDouble(String key)throws LUANException{
		if(this.doubleMap.containsKey(key)){
			return this.doubleMap.get(key);
		}else{
			throw new LUANException("Can not get value with this key ("+key+"), key does not excists in the double map!");
		}
	}
	
	public boolean getBoolean(String key)throws LUANException{
		if(this.booleanMap.containsKey(key)){
			return this.booleanMap.get(key);
		}else{
			throw new LUANException("Can not get value with this key ("+key+"), key does not excists in the boolean map!");
		}
	}
	
	public String[] getStringArray(String key)throws LUANException{
		if(this.stringArrMap.containsKey(key)){
			return this.stringArrMap.get(key);
		}else{
			throw new LUANException("Can not get value with this key ("+key+"), key does not excists in the String[] map!");
		}
	}
	
	public int[] getIntegerArray(String key)throws LUANException{
		if(this.intArrMap.containsKey(key)){
			return this.intArrMap.get(key);
		}else{
			throw new LUANException("Can not get value with this key ("+key+"), key does not excists in the int[] map!");
		}
	}
	
	public double[] getDoubleArray(String key)throws LUANException{
		if(this.doubleArrMap.containsKey(key)){
			return this.doubleArrMap.get(key);
		}else{
			throw new LUANException("Can not get value with this key ("+key+"), key does not excists in the double[] map!");
		}
	}
	
	public boolean[] getBooleanArray(String key)throws LUANException{
		if(this.booleanArrMap.containsKey(key)){
			return this.booleanArrMap.get(key);
		}else{
			throw new LUANException("Can not get value with this key ("+key+"), key does not excists in the boolean[] map!");
		}
	}
	
	public byte[] getByteArray(String key)throws LUANException{
		if(this.byteArrMap.containsKey(key)){
			return this.byteArrMap.get(key);
		}else{
			throw new LUANException("Can not get value with this key ("+key+"), key does not excists in the byte[] map!");
		}
	}
	
	public int[][] getIntegerTwoDimensionalArray(String key)throws LUANException{
		if(this.intTwoDeminsionalArrMap.containsKey(key)){
			return this.intTwoDeminsionalArrMap.get(key);
		}else{
			throw new LUANException("Can not get value with this key ("+key+"), key does not excists in the int[][] map!");
		}
	}
	
	public double[][] getDoubleTwoDimensionalArray(String key)throws LUANException{
		if(this.doubleTwoDeminsionalArrMap.containsKey(key)){
			return this.doubleTwoDeminsionalArrMap.get(key);
		}else{
			throw new LUANException("Can not get value with this key ("+key+"), key does not excists in the double[][] map!");
		}
	}
	
	public boolean[][] getBooleanTwoDimensionalArray(String key)throws LUANException{
		if(this.booleanTwoDeminsionalArrMap.containsKey(key)){
			return this.booleanTwoDeminsionalArrMap.get(key);
		}else{
			throw new LUANException("Can not get value with this key ("+key+"), key does not excists in the boolean[][] map!");
		}
	}
	
	public String[][] getStringTwoDimensionalArray(String key)throws LUANException{
		if(this.stringTwoDeminsionalArrMap.containsKey(key)){
			return this.stringTwoDeminsionalArrMap.get(key);
		}else{
			throw new LUANException("Can not get value with this key ("+key+"), key does not excists in the String[][] map!");
		}
	}
	
	/**************************************************************************************/
	/************************************Object Contains:er??***********************************/
	/**************************************************************************************/
	
	public boolean containsString(String key){
		try{
			if(this.stringMap.containsKey(key)){
				return true;
			}
		}catch(Exception ex){
			return false;
		}
		return false;
	}
	
	public boolean containsInteger(String key){
		try{
			if(this.intMap.containsKey(key)){
				return true;
			}
		}catch(Exception ex){
			return false;
		}
		return false;
	}
	
	public boolean containsDouble(String key){
		try{
			if(this.doubleMap.containsKey(key)){
				return true;
			}
		}catch(Exception ex){
			return false;
		}
		return false;
	}
	
	public boolean containsBoolean(String key){
		try{
			if(this.booleanMap.containsKey(key)){
				return true;
			}
		}catch(Exception ex){
			return false;
		}
		return false;
	}
	
	public boolean containsStringArray(String key){
		try{
			if(this.stringArrMap.containsKey(key)){
				return true;
			}
		}catch(Exception ex){
			return false;
		}
		return false;
	}
	
	public boolean containsIntArray(String key){
		try{
			if(this.intArrMap.containsKey(key)){
				return true;
			}
		}catch(Exception ex){
			return false;
		}
		return false;
	}
	
	public boolean containsDoubleArray(String key){
		try{
			if(this.doubleArrMap.containsKey(key)){
				return true;
			}
		}catch(Exception ex){
			return false;
		}
		return false;
	}
	
	public boolean containsBooleanArray(String key){
		try{
			if(this.booleanArrMap.containsKey(key)){
				return true;
			}
		}catch(Exception ex){
			return false;
		}
		return false;
	}
	
	public boolean containsByteArray(String key){
		try{
			if(this.byteArrMap.containsKey(key)){
				return true;
			}
		}catch(Exception ex){
			return false;
		}
		return false;
	}
	
	public boolean containsIntTwoDeminsionalArray(String key){
		try{
			if(this.intTwoDeminsionalArrMap.containsKey(key)){
				return true;
			}
		}catch(Exception ex){
			return false;
		}
		return false;
	}
	
	public boolean containsDoubleTwoDeminsionalArray(String key){
		try{
			if(this.doubleTwoDeminsionalArrMap.containsKey(key)){
				return true;
			}
		}catch(Exception ex){
			return false;
		}
		return false;
	}
	
	public boolean containsBooleanTwoDeminsionalArray(String key){
		try{
			if(this.booleanTwoDeminsionalArrMap.containsKey(key)){
				return true;
			}
		}catch(Exception ex){
			return false;
		}
		return false;
	}
	
	public boolean containsStringTwoDeminsionalArray(String key){
		try{
			if(this.stringTwoDeminsionalArrMap.containsKey(key)){
				return true;
			}
		}catch(Exception ex){
			return false;
		}
		return false;
	}
	
	
	
	/**************************************************************************************/
	/************************************Object Removers***********************************/
	/**************************************************************************************/
	
	
	public void removeString(String key)throws LUANException{
		if(this.stringMap.containsKey(key)){
			this.stringMap.remove(key);
		}else{
			throw new LUANException("Can not remove value with this key ("+key+"), key does not excists in the String map!");
		}
	}
	
	public void removeInteger(String key)throws LUANException{
		if(this.intMap.containsKey(key)){
			this.intMap.remove(key);
		}else{
			throw new LUANException("Can not remove value with this key ("+key+"), key does not excists in the Integer map!");
		}
	}
	
	public void removeDouble(String key)throws LUANException{
		if(this.doubleMap.containsKey(key)){
			this.doubleMap.remove(key);
		}else{
			throw new LUANException("Can not remove value with this key ("+key+"), key does not excists in the double map!");
		}
	}
	
	public void removeBoolean(String key)throws LUANException{
		if(this.booleanMap.containsKey(key)){
			this.booleanMap.remove(key);
		}else{
			throw new LUANException("Can not remove value with this key ("+key+"), key does not excists in the boolean map!");
		}	
	}
	
	public void removeStringArray(String key)throws LUANException{
		if(this.stringArrMap.containsKey(key)){
			this.stringArrMap.remove(key);
		}else{
			throw new LUANException("Can not remove value with this key ("+key+"), key does not excists in the String[] map!");
		}
	}
	
	public void removeIntegerArray(String key)throws LUANException{
		if(this.intArrMap.containsKey(key)){
			this.intArrMap.remove(key);
		}else{
			throw new LUANException("Can not remove value with this key ("+key+"), key does not excists in the int[] map!");
		}
	}
	
	public void removeDoubleArray(String key)throws LUANException{
		if(this.doubleArrMap.containsKey(key)){
			this.doubleArrMap.remove(key);
		}else{
			throw new LUANException("Can not remove value with this key ("+key+"), key does not excists in the double[] map!");
		}
	}
	
	public void removeBooleanArray(String key)throws LUANException{
		if(this.booleanArrMap.containsKey(key)){
			this.booleanArrMap.remove(key);
		}else{
			throw new LUANException("Can not remove value with this key ("+key+"), key does not excists in the boolean[] map!");
		}
	}
	
	public void removeByteArray(String key)throws LUANException{
		if(this.byteArrMap.containsKey(key)){
			this.byteArrMap.remove(key);
		}else{
			throw new LUANException("Can not remove value with this key ("+key+"), key does not excists in the byte[] map!");
		}
	}
	
	public void removeIntegerTwoDimensionalArray(String key)throws LUANException{
		if(this.intTwoDeminsionalArrMap.containsKey(key)){
			this.intTwoDeminsionalArrMap.remove(key);
		}else{
			throw new LUANException("Can not remove value with this key ("+key+"), key does not excists in the int[][] map!");
		}
	}
	
	public void removeDoubleTwoDimensionalArray(String key)throws LUANException{
		if(this.doubleTwoDeminsionalArrMap.containsKey(key)){
			this.doubleTwoDeminsionalArrMap.remove(key);
		}else{
			throw new LUANException("Can not remove value with this key ("+key+"), key does not excists in the double[][] map!");
		}
	}
	
	public void removeBooleanTwoDimensionalArray(String key)throws LUANException{
		if(this.booleanTwoDeminsionalArrMap.containsKey(key)){
			this.booleanTwoDeminsionalArrMap.remove(key);
		}else{
			throw new LUANException("Can not remove value with this key ("+key+"), key does not excists in the boolean[][] map!");
		}
	}
	
	public void removeStringTwoDimensionalArray(String key)throws LUANException{
		if(this.stringTwoDeminsionalArrMap.containsKey(key)){
			this.stringTwoDeminsionalArrMap.remove(key);
		}else{
			throw new LUANException("Can not remove value with this key ("+key+"), key does not excists in the String[][] map!");
		}
	}
	
	/**************************************************************************************/
	/**************************Under the Hood - Advanced (TO-String)***********************/
	/**************************************************************************************/
	
	public String toString(){
		String toString=null;
		try{
			toString=new String(""); 
			if(intMap.size()>0){
				toString=toString+convertIntegerMapToString()+",#,,,#,,,#,,#,,,#,,,#,";
			}
			if(doubleMap.size()>0){
				toString=toString+convertDoubleMapToString()+",#,,,#,,,#,,#,,,#,,,#,";
			}
			if(booleanMap.size()>0){
				toString=toString+convertBooleanMapToString()+",#,,,#,,,#,,#,,,#,,,#,";
			}
			if(stringMap.size()>0){
				toString=toString+convertStringMapToString()+",#,,,#,,,#,,#,,,#,,,#,";		
				
			}
			if(intArrMap.size()>0){
				toString=toString+convertIntegerArrayMapToString()+",#,,,#,,,#,,#,,,#,,,#,";
			}
			if(doubleArrMap.size()>0){
				toString=toString+convertDoubleArrayMapToString()+",#,,,#,,,#,,#,,,#,,,#,";
			}
			if(booleanArrMap.size()>0){
				toString=toString+convertBooleanArrayMapToString()+",#,,,#,,,#,,#,,,#,,,#,";
			}
			if(stringArrMap.size()>0){
				toString=toString+convertStringArrayMapToString()+",#,,,#,,,#,,#,,,#,,,#,";
			}
			if(byteArrMap.size()>0){
				toString=toString+convertByteArrayMapToString()+",#,,,#,,,#,,#,,,#,,,#,";
			}
			if(intTwoDeminsionalArrMap.size()>0){
				toString=toString+convertTwoDimensionalIntegerArrayMapToString()+",#,,,#,,,#,,#,,,#,,,#,";
			}
			if(doubleTwoDeminsionalArrMap.size()>0){
				toString=toString+convertTwoDimensionalDoubleArrayMapToString()+",#,,,#,,,#,,#,,,#,,,#,";
			}
			if(booleanTwoDeminsionalArrMap.size()>0){
				toString=toString+convertTwoDimensionalBooleanArrayMapToString()+",#,,,#,,,#,,#,,,#,,,#,";
			}
			if(stringTwoDeminsionalArrMap.size()>0){
				toString=toString+convertTwoDimensionalStringArrayMapToString()+",#,,,#,,,#,,#,,,#,,,#,";
			}
			//toString=toString+enderMapToString();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return toString;
	}
	
	public byte[] getBytes(){
		byte[] bytes=null;
		try {
			bytes=toString().getBytes("ISO-8859-1");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return bytes;
	}
	
	private String convertStringMapToString()throws Exception{
		String string_for_stringMap= new String("");
		string_for_stringMap="|#|||#|||#|stringMap;#;;;#;;;#;";
		for (Object key : this.stringMap.keySet()) {
			string_for_stringMap=string_for_stringMap+"{#{{{#{{{#{"+key.toString()+":#:::#:::#:"+this.stringMap.get(key)+"}#}}}#}}}#},#,,,#,,,#,";
		}
		string_for_stringMap=string_for_stringMap.substring(0, string_for_stringMap.length())+"|#|||#|||#|";
		return string_for_stringMap;
	}
	
	private String convertIntegerMapToString()throws Exception{
		String string_for_intMap= new String("");
		string_for_intMap="|#|||#|||#|intMap;#;;;#;;;#;";
		for (Object key : this.intMap.keySet()) {
			string_for_intMap=string_for_intMap+"{#{{{#{{{#{"+key.toString()+":#:::#:::#:"+this.intMap.get(key)+"}#}}}#}}}#},#,,,#,,,#,";
		}
		string_for_intMap=string_for_intMap.substring(0, string_for_intMap.length())+"|#|||#|||#|";
		return string_for_intMap;
	}
	
	private String convertDoubleMapToString()throws Exception{
		String string_for_doubleMap= new String("");
		string_for_doubleMap="|#|||#|||#|doubleMap;#;;;#;;;#;";
		for (Object key : this.doubleMap.keySet()) {
			string_for_doubleMap=string_for_doubleMap+"{#{{{#{{{#{"+key.toString()+":#:::#:::#:"+this.doubleMap.get(key)+"}#}}}#}}}#},#,,,#,,,#,";
		}
		string_for_doubleMap=string_for_doubleMap.substring(0, string_for_doubleMap.length())+"|#|||#|||#|";
		return string_for_doubleMap;
	}
	
	private String convertBooleanMapToString()throws Exception{
		String string_for_booleanMap= new String("");
		string_for_booleanMap="|#|||#|||#|booleanMap;#;;;#;;;#;";
		for (Object key : this.booleanMap.keySet()) {
			string_for_booleanMap=string_for_booleanMap+"{#{{{#{{{#{"+key.toString()+":#:::#:::#:"+this.booleanMap.get(key)+"}#}}}#}}}#},#,,,#,,,#,";
		}
		string_for_booleanMap=string_for_booleanMap.substring(0, string_for_booleanMap.length())+"|#|||#|||#|";
		return string_for_booleanMap;
	}
	
	private String convertStringArrayMapToString()throws Exception{
		String string_for_stringArrMap= new String("");
		string_for_stringArrMap="|#|||#|||#|stringArrMap;#;;;#;;;#;";
		for (Object key : this.stringArrMap.keySet()) {
			string_for_stringArrMap=string_for_stringArrMap+"{#{{{#{{{#{"+key+":#:::#:::#:"+convertToString(this.stringArrMap.get(key))+"}#}}}#}}}#},#,,,#,,,#,";
		}
		string_for_stringArrMap=string_for_stringArrMap.substring(0, string_for_stringArrMap.length())+"|#|||#|||#|";
		return string_for_stringArrMap;
	}
	
	private String convertIntegerArrayMapToString()throws Exception{
		String string_for_intArrMap= new String("");
		string_for_intArrMap="|#|||#|||#|intArrMap;#;;;#;;;#;";
		for (Object key : this.intArrMap.keySet()) {
			string_for_intArrMap=string_for_intArrMap+"{#{{{#{{{#{"+key+":#:::#:::#:"+convertToString(this.intArrMap.get(key))+"}#}}}#}}}#},#,,,#,,,#,";
		}
		string_for_intArrMap=string_for_intArrMap.substring(0, string_for_intArrMap.length())+"|#|||#|||#|";
		return string_for_intArrMap;
	}
	
	private String convertDoubleArrayMapToString()throws Exception{
		String string_for_doubleArrMap= new String("");
		string_for_doubleArrMap="|#|||#|||#|doubleArrMap;#;;;#;;;#;";
		for (Object key : this.doubleArrMap.keySet()) {
			string_for_doubleArrMap=string_for_doubleArrMap+"{#{{{#{{{#{"+key+":#:::#:::#:"+convertToString(this.doubleArrMap.get(key))+"}#}}}#}}}#},#,,,#,,,#,";
		}
		string_for_doubleArrMap=string_for_doubleArrMap.substring(0, string_for_doubleArrMap.length())+"|#|||#|||#|";
		return string_for_doubleArrMap;
	}
	
	private String convertBooleanArrayMapToString()throws Exception{
		String string_for_booleanArrMap= new String("");
		string_for_booleanArrMap="|#|||#|||#|booleanArrMap;#;;;#;;;#;";
		for (Object key : this.booleanArrMap.keySet()) {
			string_for_booleanArrMap=string_for_booleanArrMap+"{#{{{#{{{#{"+key+":#:::#:::#:"+convertToString(this.booleanArrMap.get(key))+"}#}}}#}}}#},#,,,#,,,#,";
		}
		string_for_booleanArrMap=string_for_booleanArrMap.substring(0, string_for_booleanArrMap.length())+"|#|||#|||#|";
		return string_for_booleanArrMap;
	}
	
	private String convertTwoDimensionalIntegerArrayMapToString()throws Exception{
		String string_for_int2dArrMap= new String("");
		string_for_int2dArrMap="|#|||#|||#|intTwoDimensionalArrMap;#;;;#;;;#;";
		for (Object key : this.intTwoDeminsionalArrMap.keySet()) {
			string_for_int2dArrMap=string_for_int2dArrMap+"{#{{{#{{{#{"+key+":#:::#:::#:"+convertTwoDimensionalToString(this.intTwoDeminsionalArrMap.get(key))+"}#}}}#}}}#},#,,,#,,,#,";
		}
		string_for_int2dArrMap=string_for_int2dArrMap+"|#|||#|||#|";
		return string_for_int2dArrMap;
	}
	
	private String convertTwoDimensionalDoubleArrayMapToString()throws Exception{
		String string_for_Double2dArrMap=new String("");
		string_for_Double2dArrMap="|#|||#|||#|doubleTwoDimensionalArrMap;#;;;#;;;#;";
		for (Object key : this.doubleTwoDeminsionalArrMap.keySet()) {
			string_for_Double2dArrMap=string_for_Double2dArrMap+"{#{{{#{{{#{"+key+":#:::#:::#:"+convertTwoDimensionalToString(this.doubleTwoDeminsionalArrMap.get(key))+"}#}}}#}}}#},#,,,#,,,#,";
		}
		string_for_Double2dArrMap=string_for_Double2dArrMap+"|#|||#|||#|";
		return string_for_Double2dArrMap;
	}
	
	private String convertTwoDimensionalBooleanArrayMapToString()throws Exception{
		String string_for_boolean2dArrMap=new String("");
		string_for_boolean2dArrMap="|#|||#|||#|booleanTwoDimensionalArrMap;#;;;#;;;#;";
		for (Object key : this.booleanTwoDeminsionalArrMap.keySet()) {
			string_for_boolean2dArrMap=string_for_boolean2dArrMap+"{#{{{#{{{#{"+key+":#:::#:::#:"+convertTwoDimensionalToString(this.booleanTwoDeminsionalArrMap.get(key))+"}#}}}#}}}#},#,,,#,,,#,";
		}
		string_for_boolean2dArrMap=string_for_boolean2dArrMap+"|#|||#|||#|";
		return string_for_boolean2dArrMap;
	}
	
	private String convertTwoDimensionalStringArrayMapToString()throws Exception{
		String string_for_string2dArrMap=new String("");
		string_for_string2dArrMap="|#|||#|||#|stringTwoDimensionalArrMap;#;;;#;;;#;";
		for (Object key : this.stringTwoDeminsionalArrMap.keySet()) {
			string_for_string2dArrMap=string_for_string2dArrMap+"{#{{{#{{{#{"+key+":#:::#:::#:"+convertTwoDimensionalToString(this.stringTwoDeminsionalArrMap.get(key))+"}#}}}#}}}#},#,,,#,,,#,";
		}
		string_for_string2dArrMap=string_for_string2dArrMap+"|#|||#|||#|";
		return string_for_string2dArrMap;
	}
	
	private String convertByteArrayMapToString()throws Exception{
		String string_for_byteArrMap=new String("");;
		try{
			string_for_byteArrMap="|#|||#|||#|byteArrMap;#;;;#;;;#;";
			for (Object key : this.byteArrMap.keySet()) {
				string_for_byteArrMap=string_for_byteArrMap+"{#{{{#{{{#{"+key+":#:::#:::#:"+new String(this.byteArrMap.get(key),"ISO-8859-1")+"}#}}}#}}}#},#,,,#,,,#,";
			}
			string_for_byteArrMap=string_for_byteArrMap.substring(0, string_for_byteArrMap.length())+"|#|||#|||#|";
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return string_for_byteArrMap;
	}
	
	private String convertToString(int[] intArr)throws Exception{
		String intArray=new String("");
		intArray="[#[[[#[[[#[";
		for(int i=0;i<intArr.length;i++){
			intArray=intArray+intArr[i]+",#,,,#,,,#,";
		}
		intArray=intArray/*intArray.substring(0, intArray.length())*/+"]#]]]#]]]#]";
		return intArray;
	}
	
	private String convertToString(double[] doubleArr)throws Exception{
		String doubleArray=new String("");
		doubleArray="[#[[[#[[[#[";
		for(int i=0;i<doubleArr.length;i++){
			doubleArray=doubleArray+doubleArr[i]+",#,,,#,,,#,";
		}
		doubleArray=doubleArray.substring(0, doubleArray.length())+"]#]]]#]]]#]";
		return doubleArray;
	}
	
	private String convertToString(String[] stringArr)throws Exception{
		String stringArray=new String("");
		stringArray="[#[[[#[[[#[";
		for(int i=0;i<stringArr.length;i++){
			stringArray=stringArray+stringArr[i]+",#,,,#,,,#,";
		}
		stringArray=stringArray.substring(0, stringArray.length())+"]#]]]#]]]#]";
		return stringArray;
	}
	
	private String convertToString(boolean[] booleanArr)throws Exception{
		String booleanArray=new String("");
		booleanArray="[#[[[#[[[#[";
		for(int i=0;i<booleanArr.length;i++){
			booleanArray=booleanArray+booleanArr[i]+",#,,,#,,,#,";
		}
		booleanArray=booleanArray.substring(0, booleanArray.length())+"]#]]]#]]]#]";
		return booleanArray;
	}
		
	private String convertTwoDimensionalToString(int[][] intArr)throws Exception{
		String stringIntArray=new String("");
		for(int i=0;i<intArr.length;i++){
			stringIntArray=stringIntArray+"[#[[[#[[[#[";
			for(int q=0;q<intArr[0].length;q++){
				stringIntArray=stringIntArray+intArr[i][q]+",#,,,#,,,#,";
			}
			stringIntArray=stringIntArray+"]#]]]#]]]#]";
		}
		return stringIntArray;
	}
	
	private String convertTwoDimensionalToString(double[][] doubleArr)throws Exception{
		String stringDoubleArray=new String("");
		for(int i=0;i<doubleArr.length;i++){
			stringDoubleArray=stringDoubleArray+"[#[[[#[[[#[";
			for(int q=0;q<doubleArr[0].length;q++){
				stringDoubleArray=stringDoubleArray+doubleArr[i][q]+",#,,,#,,,#,";
			}
			stringDoubleArray=stringDoubleArray+"]#]]]#]]]#]";
		}
		return stringDoubleArray;
	}
	
	private String convertTwoDimensionalToString(boolean[][] booleanArr)throws Exception{
		String stringBooleanArray=new String("");
		for(int i=0;i<booleanArr.length;i++){
			stringBooleanArray=stringBooleanArray+"[#[[[#[[[#[";
			for(int q=0;q<booleanArr[0].length;q++){
				stringBooleanArray=stringBooleanArray+booleanArr[i][q]+",#,,,#,,,#,";
			}
			stringBooleanArray=stringBooleanArray+"]#]]]#]]]#]";
		}
		return stringBooleanArray;
	}
	
	private String convertTwoDimensionalToString(String[][] stringArr)throws Exception{
		String stringArray=new String("");
		for(int i=0;i<stringArr.length;i++){
			stringArray=stringArray+"[#[[[#[[[#[";
			for(int q=0;q<stringArr[0].length;q++){
				stringArray=stringArray+stringArr[i][q]+",#,,,#,,,#,";
			}
			stringArray=stringArray+"]#]]]#]]]#]";
		}
		return stringArray;
	}
	
	/**************************************************************************************/
	/************************Under the Hood - Advanced (FROM-String)***********************/
	/**************************************************************************************/
	private void readStringAndCreateLUANOBject(String luanObjectString){
		ArrayList <String>mapList=startingToStringInitilizeLUANObject(luanObjectString);
		segment_SortOutAndInitlizeMaps(mapList);
		//FROM method: segment_SortOutAndInitlizeMaps(mapList)
	    //follow the flow and see the connected methods
	}
	
	private ArrayList <String> startingToStringInitilizeLUANObject(String s){
		ArrayList <String>mapList = new ArrayList<String>();
		int beginIndex=-1;
		int endIndex=0;
		for(int i=1;(i+11)<s.length();i++){
			if(s.substring(i, i+11).equals("|#|||#|||#|")  ){
				beginIndex=endIndex+11;
				endIndex=i;
				if(!s.substring(beginIndex, endIndex).equals(",#,,,#,,,#,,#,,,#,,,#,")){
					mapList.add(s.substring(beginIndex, endIndex));
				}
			}
		}
		return mapList;
	}
	
	private void segment_SortOutAndInitlizeMaps(ArrayList <String>mapList){
		for(int i=0;i<mapList.size();i++){
			String string=mapList.get(i);
			int seperator=0;
			for(int q=0;(q+11)<string.length();q++){
				if(string.substring(q,q+11).equals(";#;;;#;;;#;")){
					seperator=q+11;
				}
			}
			String map="";
			String mapName="";
			if(seperator>=11){
				map=string.substring(seperator, string.length());
				mapName=string.substring(0, seperator-11);
				fillSegmentedMapString(map, mapName);
			}
		}
	}
	
	
	private void fillSegmentedMapString(String map,String mapName){
		if(mapName.equals("intMap")){
			initilizeIntMap(map);
		}else if(mapName.equals("doubleMap")){
			initilizeDoubleMap(map);
		}else if(mapName.equals("booleanMap")){
			initilizeBooleanMap(map);
		}else if(mapName.equals("stringMap")){
			initilizeStringMap(map);
		}else if(mapName.equals("objectMap")){
			//initilizeObjectMap(map);
		}else if(mapName.equals("stringArrMap")){
			initilizeStringArrMap(map);
		}else if(mapName.equals("intArrMap")){
			initilizeIntArrMap(map);
		}else if(mapName.equals("doubleArrMap")){
			initilizeDoubleArrMap(map);
		}else if(mapName.equals("booleanArrMap")){
			initilizeBooleanArrMap(map);
		}else if(mapName.equals("byteArrMap")){
			initilizeByteArrMap(map);
		}else if(mapName.equals("intTwoDimensionalArrMap")){//
			initilizeTwoDimensionalIntArrMap(map);
		}else if(mapName.equals("doubleTwoDimensionalArrMap")){//
			initilizeTwoDimensionalDoubleArrMap(map);
		}else if(mapName.equals("booleanTwoDimensionalArrMap")){//
			initilizeTwoDimensionalBooleanArrMap(map);
		}else if(mapName.equals("stringTwoDimensionalArrMap")){//
			initilizeTwoDimensionalStringArrMap(map);
		}
	}
	
	private void initilizeIntMap(String map){
		String key="",keyAndValue="";
		int beginIndex=0,endIndex=0;
		int value=0;
		for(int q=0;q<map.length();q++){//takes out the key & value
			if(q<map.length()-11 && map.substring(q,q+11).equals("{#{{{#{{{#{")){
				beginIndex=q+11;
			}else if(q<map.length()-11 && map.substring(q,q+11).equals("}#}}}#}}}#}")){
				endIndex=q;
				keyAndValue=map.substring(beginIndex, endIndex);
				for(int i=0;i<keyAndValue.length();i++){//seperates key & value
					if(keyAndValue.substring(i, i+11).equals(":#:::#:::#:")){
						key=keyAndValue.substring(0, i);
						value=Integer.parseInt(keyAndValue.substring(i+11, keyAndValue.length()));
						intMap.put(key, value);
						i=keyAndValue.length()+1;
					}
				}
			}
		}
	}
	
	private void initilizeDoubleMap(String map){
		String key="",keyAndValue="";
		int beginIndex=0,endIndex=0;
		double value=0;
		for(int q=0;q<map.length();q++){//takes out the key & value
			if(q<map.length()-11 && map.substring(q,q+11).equals("{#{{{#{{{#{")){
				beginIndex=q+11;
			}else if(q<map.length()-11 && map.substring(q,q+11).equals("}#}}}#}}}#}")){
				endIndex=q;
				keyAndValue=map.substring(beginIndex, endIndex);
				for(int i=0;i<keyAndValue.length();i++){//seperates key & value
					if(keyAndValue.substring(i, i+11).equals(":#:::#:::#:")){
						key=keyAndValue.substring(0, i);
						value=convertToDouble(keyAndValue.substring(i+11, keyAndValue.length()));
						doubleMap.put(key, value);
						i=keyAndValue.length()+1;
					}
				}
			}
		}
	}

	private void initilizeBooleanMap(String map){
		String key="",keyAndValue="";
		int beginIndex=0,endIndex=0;
		boolean value;
		for(int q=0;q<map.length();q++){//takes out the key & value
			if(q<map.length()-11 && map.substring(q,q+11).equals("{#{{{#{{{#{")){
				beginIndex=q+11;
			}else if(q<map.length()-11 && map.substring(q,q+11).equals("}#}}}#}}}#}")){
				endIndex=q;
				keyAndValue=map.substring(beginIndex, endIndex);
				for(int i=0;i<keyAndValue.length();i++){//seperates key & value
					if(keyAndValue.substring(i, i+11).equals(":#:::#:::#:")){
						key=keyAndValue.substring(0, i);
						value=Boolean.parseBoolean(keyAndValue.substring(i+11, keyAndValue.length()));
						booleanMap.put(key, value);
						i=keyAndValue.length()+1;
					}
				}
			}
		}
	}
	
	private void initilizeStringMap(String map){
		String key="",keyAndValue="";
		int beginIndex=0,endIndex=0;
		String value="";
		for(int q=0;q<map.length();q++){//takes out the key & value
			if(q<map.length()-11 && map.substring(q,q+11).equals("{#{{{#{{{#{")){
				beginIndex=q+11;
			}else if(q<map.length()-11 && map.substring(q,q+11).equals("}#}}}#}}}#}")){
				endIndex=q;
				keyAndValue=map.substring(beginIndex, endIndex);
				for(int i=0;i<keyAndValue.length();i++){//seperates key & value
					if(keyAndValue.substring(i, i+11).equals(":#:::#:::#:")){
						key=keyAndValue.substring(0, i);
						value=keyAndValue.substring(i+11, keyAndValue.length());
						stringMap.put(key, value);
						i=keyAndValue.length()+1;
					}
				}
			}
		}
	}
	
	private void initilizeIntArrMap(String map){
		String key="",keyAndValue="";
		int beginIndex=0,endIndex=0;
		int[] value=null;
		for(int q=0;q<map.length();q++){//takes out the key & value
			if(q<map.length()-11 && map.substring(q,q+11).equals("{#{{{#{{{#{")){
				beginIndex=q+11;
			}else if(q<map.length()-11 && map.substring(q,q+11).equals("}#}}}#}}}#}")){
				endIndex=q;
				keyAndValue=map.substring(beginIndex, endIndex);
				for(int i=0;i<keyAndValue.length();i++){//seperates key & value
					if(keyAndValue.substring(i, i+11).equals(":#:::#:::#:")){
						key=keyAndValue.substring(0, i);
						String intArr=keyAndValue.substring(i+11, keyAndValue.length());
						value=convertToIntArray(intArr);
						intArrMap.put(key, value);
						i=keyAndValue.length()+1;
					}
				}
			}
		}
	}
	
	private void initilizeDoubleArrMap(String map){
		String key="",keyAndValue="";
		int beginIndex=0,endIndex=0;
		double[] value=null;
		for(int q=0;q<map.length();q++){//takes out the key & value
			if(q<map.length()-11 && map.substring(q,q+11).equals("{#{{{#{{{#{")){
				beginIndex=q+11;
			}else if(q<map.length()-11 && map.substring(q,q+11).equals("}#}}}#}}}#}")){
				endIndex=q;
				keyAndValue=map.substring(beginIndex, endIndex);
				for(int i=0;i<keyAndValue.length();i++){//seperates key & value
					if(keyAndValue.substring(i, i+11).equals(":#:::#:::#:")){
						key=keyAndValue.substring(0, i);
						String doubleArr=keyAndValue.substring(i+11, keyAndValue.length());
						value=convertToDoubleArray(doubleArr);
						doubleArrMap.put(key, value);
						i=keyAndValue.length()+1;
					}
				}
			}
		}
	}
	
	private void initilizeBooleanArrMap(String map){
		String key="",keyAndValue="";
		int beginIndex=0,endIndex=0;
		boolean[] value=null;
		for(int q=0;q<map.length();q++){//takes out the key & value
			if(q<map.length()-11 && map.substring(q,q+11).equals("{#{{{#{{{#{")){
				beginIndex=q+11;
			}else if(q<map.length()-11 && map.substring(q,q+11).equals("}#}}}#}}}#}")){
				endIndex=q;
				keyAndValue=map.substring(beginIndex, endIndex);
				for(int i=0;i<keyAndValue.length();i++){//seperates key & value
					if(keyAndValue.substring(i, i+11).equals(":#:::#:::#:")){
						key=keyAndValue.substring(0, i);
						String booleanArr=keyAndValue.substring(i+11, keyAndValue.length());
						value=convertToBooleanArray(booleanArr);
						booleanArrMap.put(key, value);
						i=keyAndValue.length()+1;
					}
				}
			}
		}
	}
		
	private void initilizeStringArrMap(String map){
		String key="",keyAndValue="";
		int beginIndex=0,endIndex=0;
		String[] value=null;
		for(int q=0;q<map.length();q++){//takes out the key & value
			if(q<map.length()-11 && map.substring(q,q+11).equals("{#{{{#{{{#{")){
				beginIndex=q+11;
			}else if(q<map.length()-11 && map.substring(q,q+11).equals("}#}}}#}}}#}")){
				endIndex=q;
				keyAndValue=map.substring(beginIndex, endIndex);
				for(int i=0;i<keyAndValue.length();i++){//seperates key & value
					if(keyAndValue.substring(i, i+11).equals(":#:::#:::#:")){
						key=keyAndValue.substring(0, i);
						String stringArr=keyAndValue.substring(i+11, keyAndValue.length());
						value=convertToStringArray(stringArr);
						stringArrMap.put(key, value);
						i=keyAndValue.length()+1;
					}
				}
			}
		}
	}
	
	private void initilizeByteArrMap(String map){
		String key="",keyAndValue="";
		int beginIndex=0,endIndex=0;
		byte[] value=null;
		for(int q=0;(q+11)<map.length();q++){//takes out the key & value
			if(map.substring(q, q+11).equals("{#{{{#{{{#{")){
				beginIndex=q+11;
			}else if(map.substring(q, q+11).equals("}#}}}#}}}#}")){
				endIndex=q+11;
				keyAndValue=map.substring(beginIndex, endIndex);
				for(int i=0;i<keyAndValue.length();i++){//seperates key & value
					if(keyAndValue.substring(i, i+11).equals(":#:::#:::#:")){
						key=keyAndValue.substring(0, i);
						try {
							value=keyAndValue.substring(i+11, keyAndValue.length()).getBytes("ISO-8859-1");
						} catch (Exception ex) {
							ex.printStackTrace();
						}
						byteArrMap.put(key, value);
						i=keyAndValue.length()+1;
					}
				}
			}
		}
	}
	
	private void initilizeTwoDimensionalIntArrMap(String map){
		String key="",keyAndValue="";
		int beginIndex=0,endIndex=0;
		int[][] value=null;
		for(int q=0;q<map.length();q++){//takes out the key & value
			if(q<map.length()-11 && map.substring(q,q+11).equals("{#{{{#{{{#{")){
				beginIndex=q+11;
			}else if(q<map.length()-11 && map.substring(q,q+11).equals("}#}}}#}}}#}")){
				endIndex=q;
				keyAndValue=map.substring(beginIndex, endIndex);
				for(int i=0;i<keyAndValue.length();i++){//seperates key & value
					if(keyAndValue.substring(i, i+11).equals(":#:::#:::#:")){
						key=keyAndValue.substring(0, i);
						String int2D_Arr=keyAndValue.substring(i+11, keyAndValue.length());
						value=convertToTwoDimensionalIntArray(int2D_Arr);
						intTwoDeminsionalArrMap.put(key, value);
						i=keyAndValue.length()+1;
					}
				}
			}
		}
	}
	
	private void initilizeTwoDimensionalDoubleArrMap(String map){
		String key="",keyAndValue="";
		int beginIndex=0,endIndex=0;
		double[][] value=null;
		for(int q=0;q<map.length();q++){//takes out the key & value
			if(q<map.length()-11 && map.substring(q,q+11).equals("{#{{{#{{{#{")){
				beginIndex=q+11;
			}else if(q<map.length()-11 && map.substring(q,q+11).equals("}#}}}#}}}#}")){
				endIndex=q;
				keyAndValue=map.substring(beginIndex, endIndex);
				for(int i=0;i<keyAndValue.length();i++){//seperates key & value
					if(keyAndValue.substring(i, i+11).equals(":#:::#:::#:")){
						key=keyAndValue.substring(0, i);
						String double2D_Arr=keyAndValue.substring(i+11, keyAndValue.length());
						value=convertToTwoDimensionalDoubleArray(double2D_Arr);
						doubleTwoDeminsionalArrMap.put(key, value);
						i=keyAndValue.length()+1;
					}
				}
			}
		}
	}
	
	private void initilizeTwoDimensionalBooleanArrMap(String map){
		String key="",keyAndValue="";
		int beginIndex=0,endIndex=0;
		boolean[][] value=null;
		for(int q=0;q<map.length();q++){//takes out the key & value
			if(q<map.length()-11 && map.substring(q,q+11).equals("{#{{{#{{{#{")){
				beginIndex=q+11;
			}else if(q<map.length()-11 && map.substring(q,q+11).equals("}#}}}#}}}#}")){
				endIndex=q;
				keyAndValue=map.substring(beginIndex, endIndex);
				for(int i=0;i<keyAndValue.length();i++){//seperates key & value
					if(keyAndValue.substring(i, i+11).equals(":#:::#:::#:")){
						key=keyAndValue.substring(0, i);
						String boolean2D_Arr=keyAndValue.substring(i+11, keyAndValue.length());
						value=convertToTwoDimensionalBooleanArray(boolean2D_Arr);
						booleanTwoDeminsionalArrMap.put(key, value);
						i=keyAndValue.length()+1;
					}
				}
			}
		}
	}
	
	private void initilizeTwoDimensionalStringArrMap(String map){
		String key="",keyAndValue="";
		int beginIndex=0,endIndex=0;
		String[][] value=null;
		for(int q=0;q<map.length();q++){//takes out the key & value
			if(q<map.length()-11 && map.substring(q,q+11).equals("{#{{{#{{{#{")){
				beginIndex=q+11;
			}else if(q<map.length()-11 && map.substring(q,q+11).equals("}#}}}#}}}#}")){
				endIndex=q;
				keyAndValue=map.substring(beginIndex, endIndex);
				for(int i=0;i<keyAndValue.length();i++){//seperates key & value
					if(keyAndValue.substring(i, i+11).equals(":#:::#:::#:")){
						key=keyAndValue.substring(0, i);
						String string2D_Arr=keyAndValue.substring(i+11, keyAndValue.length());
						value=convertToTwoDimensionalStringArray(string2D_Arr);
						stringTwoDeminsionalArrMap.put(key, value);
						i=keyAndValue.length()+1;
					}
				}
			}
		}
	}
	
	private int[] convertToIntArray(String string){
		ArrayList <Integer>intList = new ArrayList<Integer>();
		int beginIndex=0;
		int endIndex=0;
		for(int i=11;(i+11)<string.length();i++){
			if(string.substring(i,i+11).equals(",#,,,#,,,#,") || string.substring(i,i+11).equals("]#]]]#]]]#]")){
				beginIndex=endIndex+11;
				endIndex=i;
				String value=string.substring(beginIndex, endIndex);
				if(stringIsEmpty(value)==false){
					intList.add(Integer.parseInt(string.substring(beginIndex, endIndex)));
				}
			}
		}
		int[] intArray = new int[intList.size()]; 
		for(int i=0;i<intList.size();i++){
			intArray[i]=intList.get(i);
		} 
		return intArray;
	}
	
	private double[] convertToDoubleArray(String string){
		ArrayList <Double>doubleList = new ArrayList<Double>();
		int beginIndex=0;
		int endIndex=0;
		for(int i=11;(i+11)<string.length();i++){
			if(string.substring(i,i+11).equals(",#,,,#,,,#,") || string.substring(i,i+11).equals("]#]]]#]]]#]")){
				beginIndex=endIndex+11;
				endIndex=i;
				String value=string.substring(beginIndex, endIndex);
				if(stringIsEmpty(value)==false){
					doubleList.add(convertToDouble(string.substring(beginIndex, endIndex)));
				}
			}
		}
		double[] doubleArray = new double[doubleList.size()]; 
		for(int i=0;i<doubleList.size();i++){
			doubleArray[i]=doubleList.get(i);
		} 
		return doubleArray;
	}
	
	private String[] convertToStringArray(String string){
		ArrayList <String>stringList = new ArrayList<String>();
		int beginIndex=0;
		int endIndex=0;
		for(int i=11;(i+11)<string.length();i++){
			if(string.substring(i,i+11).equals(",#,,,#,,,#,") || string.substring(i,i+11).equals("]#]]]#]]]#]")){
				beginIndex=endIndex+11;
				endIndex=i;
				String value=string.substring(beginIndex, endIndex);
				if(stringIsEmpty(value)==false){
					stringList.add(string.substring(beginIndex, endIndex));
				}
			}
		}
		return stringList.toArray(new String[stringList.size()]);
	}
	
	private boolean[] convertToBooleanArray(String string){
		ArrayList <Boolean>booleanList = new ArrayList<Boolean>();
		int beginIndex=0;
		int endIndex=0;
		for(int i=11;(i+11)<string.length();i++){
			if(string.substring(i,i+11).equals(",#,,,#,,,#,") || string.substring(i,i+11).equals("]#]]]#]]]#]")){
				beginIndex=endIndex+11;
				endIndex=i;
				String value=string.substring(beginIndex, endIndex);
				if(stringIsEmpty(value)==false){
					booleanList.add(Boolean.parseBoolean(string.substring(beginIndex, endIndex)));
				}
			}
		}
		boolean[] booleanArray = new boolean[booleanList.size()]; 
		for(int i=0;i<booleanList.size();i++){
			booleanArray[i]=booleanList.get(i);
		} 
		return booleanArray;
	}
	
	private int[][] convertToTwoDimensionalIntArray(String string){
		int rows=0;
		int cols=0;
		ArrayList <String>columnList= new ArrayList<String>();
		ArrayList <String>valueList= new ArrayList<String>();
		int beginIndex=0,endIndex=0;
		for(int i=0;(i+10)<string.length();i++){
			if(string.substring(i, i+11).equals("[#[[[#[[[#[") ){
				beginIndex=i+11;
			}
			if(string.substring(i, i+11).equals("]#]]]#]]]#]")){
				endIndex=i;
				columnList.add(string.substring(beginIndex, endIndex));
			}
		}	
		for(int i=0;i<columnList.size();i++){
			String index=columnList.get(i);
			beginIndex=0;
			endIndex=0;
			valueList.add("######_NewDimension_######");
			cols=0;
			rows++;
			for(int q=0;(q+10)<index.length();q++){
				if(index.substring(q, q+11).equals(",#,,,#,,,#,")){
					endIndex=q;
					String value=index.substring(beginIndex, endIndex);
					beginIndex=q+11;
					valueList.add(value);
					cols++;
				}
			}
		}
		int[][] array = new int[rows][cols];
		rows=0;
		cols=0;
		for(int i=0;i<valueList.size();i++){
			if(i!=0 && valueList.get(i).equals("######_NewDimension_######")){
				rows++;
				cols=0;
			}
			else if(!valueList.get(i).equals("######_NewDimension_######")){
				array[rows][cols]=Integer.parseInt(valueList.get(i));
				cols++;
			}
		}
		return array;
	}
	
	private double[][] convertToTwoDimensionalDoubleArray(String string){
		int rows=0;
		int cols=0;
		ArrayList <String>columnList= new ArrayList<String>();
		ArrayList <String>valueList= new ArrayList<String>();
		int beginIndex=0,endIndex=0;
		for(int i=0;(i+10)<string.length();i++){
			if(string.substring(i, i+11).equals("[#[[[#[[[#[") ){
				beginIndex=i+11;
			}
			if(string.substring(i, i+11).equals("]#]]]#]]]#]")){
				endIndex=i;
				columnList.add(string.substring(beginIndex, endIndex));
			}
		}	
		for(int i=0;i<columnList.size();i++){
			String index=columnList.get(i);
			beginIndex=0;
			endIndex=0;
			valueList.add("######_NewDimension_######");
			cols=0;
			rows++;
			for(int q=0;(q+10)<index.length();q++){
				if(index.substring(q, q+11).equals(",#,,,#,,,#,")){
					endIndex=q;
					String value=index.substring(beginIndex, endIndex);
					beginIndex=q+11;
					valueList.add(value);
					cols++;
				}
			}
		}
		double[][] array = new double[rows][cols];
		rows=0;
		cols=0;
		for(int i=0;i<valueList.size();i++){
			if(i!=0 && valueList.get(i).equals("######_NewDimension_######")){
				rows++;
				cols=0;
			}
			else if(!valueList.get(i).equals("######_NewDimension_######")){
				array[rows][cols]=convertToDouble(valueList.get(i));
				cols++;
			}
		}
		return array;
	}
	
	private boolean[][] convertToTwoDimensionalBooleanArray(String string){
		int rows=0;
		int cols=0;
		ArrayList <String>columnList= new ArrayList<String>();
		ArrayList <String>valueList= new ArrayList<String>();
		int beginIndex=0,endIndex=0;
		for(int i=0;(i+10)<string.length();i++){
			if(string.substring(i, i+11).equals("[#[[[#[[[#[") ){
				beginIndex=i+11;
			}
			if(string.substring(i, i+11).equals("]#]]]#]]]#]")){
				endIndex=i;
				columnList.add(string.substring(beginIndex, endIndex));
			}
		}	
		for(int i=0;i<columnList.size();i++){
			String index=columnList.get(i);
			beginIndex=0;
			endIndex=0;
			valueList.add("######_NewDimension_######");
			cols=0;
			rows++;
			for(int q=0;(q+10)<index.length();q++){
				if(index.substring(q, q+11).equals(",#,,,#,,,#,")){
					endIndex=q;
					String value=index.substring(beginIndex, endIndex);
					beginIndex=q+11;
					valueList.add(value);
					cols++;
				}
			}
		}
		boolean[][] array = new boolean[rows][cols];
		rows=0;
		cols=0;
		for(int i=0;i<valueList.size();i++){
			if(i!=0 && valueList.get(i).equals("######_NewDimension_######")){
				rows++;
				cols=0;
			}
			else if(!valueList.get(i).equals("######_NewDimension_######")){
				array[rows][cols]=Boolean.parseBoolean(valueList.get(i));
				cols++;
			}
		}
		return array;
	}
	
	private String[][] convertToTwoDimensionalStringArray(String string){
		int rows=0;
		int cols=0;
		ArrayList <String>columnList= new ArrayList<String>();
		ArrayList <String>valueList= new ArrayList<String>();
		int beginIndex=0,endIndex=0;
		for(int i=0;(i+10)<string.length();i++){
			if(string.substring(i, i+11).equals("[#[[[#[[[#[") ){
				beginIndex=i+11;
			}
			if(string.substring(i, i+11).equals("]#]]]#]]]#]")){
				endIndex=i;
				columnList.add(string.substring(beginIndex, endIndex));
			}
		}	
		for(int i=0;i<columnList.size();i++){
			String index=columnList.get(i);
			beginIndex=0;
			endIndex=0;
			valueList.add("######_NewDimension_######");
			cols=0;
			rows++;
			for(int q=0;(q+10)<index.length();q++){
				if(index.substring(q, q+11).equals(",#,,,#,,,#,")){
					endIndex=q;
					String value=index.substring(beginIndex, endIndex);
					beginIndex=q+11;
					valueList.add(value);
					cols++;
				}
			}
		}
		String[][] array = new String[rows][cols];
		rows=0;
		cols=0;
		for(int i=0;i<valueList.size();i++){
			if(i!=0 && valueList.get(i).equals("######_NewDimension_######")){
				rows++;
				cols=0;
			}
			else if(!valueList.get(i).equals("######_NewDimension_######")){
				array[rows][cols]=valueList.get(i);
				cols++;
			}
		}
		return array;
	}
	
	private double convertToDouble(String doubleString){
		char[] doubleChar= new char[doubleString.length()];
		for(int i=0;i<doubleString.length();i++){
			if(doubleString.charAt(i)==','){
				doubleChar[i]='.';
			}else{
				doubleChar[i]=doubleString.charAt(i);
			}
		}
		return Double.parseDouble(new String(doubleChar));
	}
	
	private boolean stringIsEmpty(String string){
		for(int i=0;i<string.length();i++){
			if(string.charAt(i)!=' '){
				return false;
			}
		}
		return true;
	}
	 
}
