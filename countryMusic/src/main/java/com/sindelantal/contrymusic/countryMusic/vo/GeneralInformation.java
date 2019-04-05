package com.sindelantal.contrymusic.countryMusic.vo;

import java.util.List;

public class GeneralInformation {

	private String countryCod;
	private String cityName;
	private Integer totalPlayList;
	private List<SpotifyPlayList> playList;
	private String currentTemp;
	private String messageError;
	private String categoryMusic;
	
	
	
	public GeneralInformation() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public GeneralInformation(String countryCod, String cityName, Integer totalPlayList, List<SpotifyPlayList> playList,
			String currentTemp, String messageError, String categoryMusic) {
		super();
		this.countryCod = countryCod;
		this.cityName = cityName;
		this.totalPlayList = totalPlayList;
		this.playList = playList;
		this.currentTemp = currentTemp;
		this.messageError = messageError;
		this.categoryMusic = categoryMusic;
	}

	

	public String getCategoryMusic() {
		return categoryMusic;
	}


	public void setCategoryMusic(String categoryMusic) {
		this.categoryMusic = categoryMusic;
	}


	public String getMessageError() {
		return messageError;
	}







	public void setMessageError(String messageError) {
		this.messageError = messageError;
	}







	public String getCurrentTemp() {
		return currentTemp;
	}







	public void setCurrentTemp(String currentTemp) {
		this.currentTemp = currentTemp;
	}







	public String getCountryCod() {
		return countryCod;
	}
	public void setCountryCod(String countryCod) {
		this.countryCod = countryCod;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public Integer getTotalPlayList() {
		return totalPlayList;
	}
	public void setTotalPlayList(Integer totalPlayList) {
		this.totalPlayList = totalPlayList;
	}
	public List<SpotifyPlayList> getPlayList() {
		return playList;
	}
	public void setPlayList(List<SpotifyPlayList> playList) {
		this.playList = playList;
	}
	
	
	
}
