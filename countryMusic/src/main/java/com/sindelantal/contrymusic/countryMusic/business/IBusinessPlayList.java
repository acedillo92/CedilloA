package com.sindelantal.contrymusic.countryMusic.business;

import java.util.List;

import com.sindelantal.contrymusic.countryMusic.vo.GeneralInformation;

public interface IBusinessPlayList {

	List<GeneralInformation> getInformationPlayList(String nameCity, String longitud);
}
