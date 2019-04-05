package com.sindelantal.contrymusic.countryMusic.vo;

public class cityWeather {
	
	private String countryCod;
	private String cityName;
	private String tempMax;
	private String tempMin;
	private String curretTemp;
	
	
	
	
	
	public cityWeather() {
		super();
	}


	public cityWeather(String countryCod, String cityName, String tempMax, 
			String tempMin, String curretTemp) {
		super();
		this.countryCod = countryCod;
		this.cityName = cityName;
		this.tempMax = tempMax;
		this.tempMin = tempMin;
		this.curretTemp = curretTemp;
	}
	
	
	public String getCountry() {
		return countryCod;
	}
	public void setCountry(String countryCod) {
		this.countryCod = countryCod;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getTempMax() {
		return tempMax;
	}
	public void setTempMax(String tempMax) {
		this.tempMax = tempMax;
	}
	public String getTempMin() {
		return tempMin;
	}
	public void setTempMin(String tempMin) {
		this.tempMin = tempMin;
	}
	public String getCurretTemp() {
		return curretTemp;
	}
	public void setCurretTemp(String curretTemp) {
		this.curretTemp = curretTemp;
	}
	
	

}
